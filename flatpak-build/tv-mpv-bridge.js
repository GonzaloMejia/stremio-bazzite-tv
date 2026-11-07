/**
 * Stremio TV <-> Linux Shell Native MPV, M5 BT Remote & Gamepad Bridge
 * Injected into WebKitGTK at start (preload) for Stremio TV (tv.strem.io)
 */
;(function() {
    'use strict';
    if (window.__STREMIO_TV_BRIDGE_INITIALIZED__) return;
    window.__STREMIO_TV_BRIDGE_INITIALIZED__ = true;

    // Beacon for debugging
    fetch('http://127.0.0.1:11471/__log?msg=bridge-loaded').catch(() => {});
    console.log("[Stremio TV Bridge] Bridge loaded successfully.");

    // =========================================================================
    // 0. Robust IPC Interception
    // =========================================================================
    const originalPostMessage = (window.webkit && window.webkit.messageHandlers && window.webkit.messageHandlers.ipc && window.webkit.messageHandlers.ipc.postMessage) ? window.webkit.messageHandlers.ipc.postMessage.bind(window.webkit.messageHandlers.ipc) : null;

    function interceptAndSend(message) {
        try {
            let parsed = typeof message === 'string' ? JSON.parse(message) : message;
            
            // IPC Filtering logic
            if (parsed && parsed.type === 6 && Array.isArray(parsed.args)) {
                const [eventName, args] = parsed.args;
                if (eventName === 'app-ready') return; 
                if (eventName === 'mpv-set-prop' && Array.isArray(args)) {
                    const name = args[0];
                    if (['sub-color', 'sub-back-color', 'sub-border-color'].includes(name)) {
                        args[1] = formatMpvColor(args[1]);
                    }
                    if (args[1] === null || args[1] === undefined) return;
                }
                if (eventName === 'mpv-observe-prop') {
                    const name = Array.isArray(args) ? args[0] : args;
                    if (['input-default-bindings'].includes(name)) return;
                }
            }

            if (originalPostMessage) {
                originalPostMessage(typeof message === 'string' ? message : JSON.stringify(message));
            } else if (window.qt && window.qt.webChannelTransport && typeof window.qt.webChannelTransport.send === 'function') {
                window.qt.webChannelTransport.send(message);
            }
        } catch (e) { console.error("[Stremio TV Bridge] IPC Intercept Error:", e); }
    }

    if (window.webkit && window.webkit.messageHandlers && window.webkit.messageHandlers.ipc) {
        Object.defineProperty(window.webkit.messageHandlers.ipc, 'postMessage', { value: interceptAndSend, writable: false, configurable: false });
    }
    window.qt = window.qt || {};
    Object.defineProperty(window.qt, 'webChannelTransport', { get: () => ({ send: interceptAndSend }), configurable: false });

    // =========================================================================
    // 1. Color Normalization
    // =========================================================================
    function formatMpvColor(val) {
        if (typeof val !== 'string') return val;
        val = val.trim();
        if (val.startsWith('#') && val.length === 9) return '#' + val.slice(1, 7);
        return val;
    }

    // =========================================================================
    // 2. Subtitle Raising Manager
    // =========================================================================
    class SubtitleRaisingManager {
        constructor() {
            this.isControlsVisible = false;
            this.init();
        }
        init() {
            try {
                const style = document.createElement('style');
                style.textContent = `
                    body.stremio-controls-visible [class*="subtitle-container"],
                    body.stremio-controls-visible [class*="subtitles-container"],
                    body.stremio-controls-visible .subtitles {
                        bottom: 150px !important;
                        transform: translateY(-130px) !important;
                        position: absolute !important;
                        transition: transform 0.2s ease !important;
                    }
                `;
                document.head.appendChild(style);
                const checkControls = () => {
                    if (!window.location.hash.startsWith('#/player')) return;
                    const controls = document.querySelector('[class*="control-bar-container"]');
                    const visible = controls && controls.offsetWidth > 0 && controls.offsetHeight > 0 && getComputedStyle(controls).opacity !== '0';
                    if (visible !== this.isControlsVisible) {
                        this.isControlsVisible = visible;
                        this.updateSubtitlePosition();
                    }
                };
                new MutationObserver(checkControls).observe(document.body, { attributes: true, subtree: true, attributeFilter: ['class', 'style'] });
                ['mousemove', 'pointerdown', 'keydown', 'wheel'].forEach(evt => window.addEventListener(evt, checkControls, true));
            } catch(e) {}
        }
        updateSubtitlePosition() {
            try {
                if (this.isControlsVisible) {
                    document.body.classList.add('stremio-controls-visible');
                    interceptAndSend({type: 6, args: ['mpv-set-prop', ['sub-pos', 75]]});
                } else {
                    document.body.classList.remove('stremio-controls-visible');
                    interceptAndSend({type: 6, args: ['mpv-set-prop', ['sub-pos', 100]]});
                }
            } catch(e) {}
        }
    }
    new SubtitleRaisingManager();

    // =========================================================================
    // 3. Gamepad Manager (8BitDo)
    // =========================================================================
    class GamepadControllerManager {
        constructor() {
            this.pollInterval = null;
            this.buttonStates = {};
            this.lastAxisTime = 0;
            this.init();
        }
        init() {
            window.addEventListener('gamepadconnected', (e) => {
                console.log("[Stremio TV Bridge] Gamepad connected: " + e.gamepad.id);
                if (!this.pollInterval) this.pollInterval = setInterval(() => this.poll(), 50);
            });
        }
        dispatchKey(code, key) {
            const down = new KeyboardEvent('keydown', { keyCode: code, key: key, bubbles: true });
            const up = new KeyboardEvent('keyup', { keyCode: code, key: key, bubbles: true });
            document.activeElement.dispatchEvent(down);
            document.activeElement.dispatchEvent(up);
        }
        poll() {
            const gp = navigator.getGamepads()[0];
            if (!gp) return;
            const now = Date.now();
            // D-Pad / Sticks (Debounced)
            if (now - this.lastAxisTime > 150) {
                if (gp.buttons[12].pressed || gp.axes[1] < -0.4) { this.dispatchKey(38, 'ArrowUp'); this.lastAxisTime = now; }
                else if (gp.buttons[13].pressed || gp.axes[1] > 0.4) { this.dispatchKey(40, 'ArrowDown'); this.lastAxisTime = now; }
                else if (gp.buttons[14].pressed || gp.axes[0] < -0.4) { this.dispatchKey(37, 'ArrowLeft'); this.lastAxisTime = now; }
                else if (gp.buttons[15].pressed || gp.axes[0] > 0.4) { this.dispatchKey(39, 'ArrowRight'); this.lastAxisTime = now; }
            }
            // Buttons (One-shot)
            const map = { 0: [13, 'Enter'], 1: [27, 'Escape'], 2: [32, ' '], 3: [83, 's'], 8: [27, 'Escape'], 9: [27, 'Escape'] };
            for(const b in map) {
                if(gp.buttons[b].pressed && !this.buttonStates[b]) {
                    this.dispatchKey(map[b][0], map[b][1]);
                }
                this.buttonStates[b] = gp.buttons[b].pressed;
            }
        }
    }
    new GamepadControllerManager();

    // =========================================================================
    // 4. Input Fixes (Mouse/Back)
    // =========================================================================
    window.addEventListener('pointerup', (e) => {
        if (e.target) {
            e.target.focus();
            const down = new KeyboardEvent('keydown', { keyCode: 13, key: 'Enter', bubbles: true });
            const up = new KeyboardEvent('keyup', { keyCode: 13, key: 'Enter', bubbles: true });
            e.target.dispatchEvent(down);
            e.target.dispatchEvent(up);
        }
    }, true);

    window.addEventListener('keydown', (e) => {
        if (['BrowserBack', 'XF86Back', 'GoBack', 'Backspace'].includes(e.key)) {
            e.preventDefault();
            window.history.back();
        }
    }, true);

})();
