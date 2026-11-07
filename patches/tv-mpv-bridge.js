/**
 * Stremio TV <-> Linux Shell Native MPV, M5 BT Remote & Gamepad Bridge
 */
;(function() {
    'use strict';
    if (window.__STREMIO_TV_BRIDGE_INITIALIZED__) return;
    window.__STREMIO_TV_BRIDGE_INITIALIZED__ = true;

    // Beacon
    fetch('http://127.0.0.1:11471/__log?msg=bridge-loaded').catch(() => {});

    // =========================================================================
    // 0. Robust & Safe IPC Interception
    // =========================================================================
    const originalPostMessage = (window.webkit && window.webkit.messageHandlers && window.webkit.messageHandlers.ipc) ? window.webkit.messageHandlers.ipc.postMessage.bind(window.webkit.messageHandlers.ipc) : null;

    function interceptAndSend(message) {
        try {
            let parsed = typeof message === 'string' ? JSON.parse(message) : message;
            if (parsed && parsed.type === 6 && Array.isArray(parsed.args)) {
                const [eventName, args] = parsed.args;
                if (eventName === 'app-ready') return;
                if (eventName === 'mpv-set-prop' && Array.isArray(args)) {
                    if (['sub-color', 'sub-back-color', 'sub-border-color'].includes(args[0])) args[1] = formatMpvColor(args[1]);
                    if (args[1] === null || args[1] === undefined) return;
                }
                if (eventName === 'mpv-observe-prop' && ['input-default-bindings'].includes(Array.isArray(args) ? args[0] : args)) return;
            }
            if (originalPostMessage) originalPostMessage(typeof message === 'string' ? message : JSON.stringify(message));
            else if (window.qt && window.qt.webChannelTransport && typeof window.qt.webChannelTransport.send === 'function') window.qt.webChannelTransport.send(message);
        } catch (e) { console.error("[Bridge] IPC Error:", e); }
    }

    // Lazy Proxy for window.qt
    Object.defineProperty(window, 'qt', {
        configurable: true,
        get() { return this._qt || {}; },
        set(val) {
            this._qt = val;
            if (val && !val.webChannelTransport) {
                Object.defineProperty(val, 'webChannelTransport', {
                    get() { return this._wct || { send: () => {} }; },
                    set(wct) { this._wct = wct; },
                    configurable: true
                });
            }
        }
    });

    // =========================================================================
    // 1. Color Normalization
    // =========================================================================
    function formatMpvColor(val) {
        if (typeof val !== 'string') return val;
        val = val.trim();
        return (val.startsWith('#') && val.length === 9) ? '#' + val.slice(1, 7) : val;
    }

    // =========================================================================
    // 2. Subtitle Raising Manager
    // =========================================================================
    try {
        const style = document.createElement('style');
        style.textContent = `
            body.stremio-controls-visible [class*="subtitle-container"],
            body.stremio-controls-visible [class*="subtitles-container"],
            body.stremio-controls-visible .subtitles {
                bottom: 150px !important;
                transform: translateY(-130px) !important;
                position: absolute !important;
            }
        `;
        document.head.appendChild(style);
        
        let isControlsVisible = false;
        const check = () => {
            if (!window.location.hash.startsWith('#/player')) return;
            const controls = document.querySelector('[class*="control-bar-container"]');
            const visible = controls && controls.offsetWidth > 0 && controls.offsetHeight > 0 && getComputedStyle(controls).opacity !== '0';
            if (visible !== isControlsVisible) {
                isControlsVisible = visible;
                document.body.classList.toggle('stremio-controls-visible', visible);
                interceptAndSend({type: 6, args: ['mpv-set-prop', ['sub-pos', visible ? 75 : 100]]});
            }
        };
        new MutationObserver(check).observe(document.body, { attributes: true, subtree: true });
        ['mousemove', 'pointerdown', 'keydown', 'wheel'].forEach(evt => window.addEventListener(evt, check, true));
    } catch(e) {}

    // =========================================================================
    // 3. Gamepad Manager (8BitDo)
    // =========================================================================
    class GamepadManager {
        constructor() { setInterval(() => this.poll(), 50); this.lastAxis = 0; this.btnStates = {}; }
        poll() {
            const gp = navigator.getGamepads()[0];
            if (!gp) return;
            const now = Date.now();
            if (now - this.lastAxis > 150) {
                const dpad = [gp.buttons[12], gp.buttons[13], gp.buttons[14], gp.buttons[15]];
                const axes = [gp.axes[1] < -0.4 || gp.axes[7] < -0.4, gp.axes[1] > 0.4 || gp.axes[7] > 0.4, gp.axes[0] < -0.4 || gp.axes[6] < -0.4, gp.axes[0] > 0.4 || gp.axes[6] > 0.4];
                if (dpad[0].pressed || axes[0]) { this.dispatch(38, 'ArrowUp'); this.lastAxis = now; }
                else if (dpad[1].pressed || axes[1]) { this.dispatch(40, 'ArrowDown'); this.lastAxis = now; }
                else if (dpad[2].pressed || axes[2]) { this.dispatch(37, 'ArrowLeft'); this.lastAxis = now; }
                else if (dpad[3].pressed || axes[3]) { this.dispatch(39, 'ArrowRight'); this.lastAxis = now; }
            }
            const map = { 0: [13, 'Enter'], 1: [27, 'Escape'], 2: [32, ' '], 3: [83, 's'], 8: [27, 'Escape'], 9: [27, 'Escape'] };
            for(const b in map) {
                if(gp.buttons[b].pressed && !this.btnStates[b]) this.dispatch(map[b][0], map[b][1]);
                this.btnStates[b] = gp.buttons[b].pressed;
            }
        }
        dispatch(c, k) {
            document.activeElement.dispatchEvent(new KeyboardEvent('keydown', { keyCode: c, key: k, bubbles: true }));
            document.activeElement.dispatchEvent(new KeyboardEvent('keyup', { keyCode: c, key: k, bubbles: true }));
        }
    }
    new GamepadManager();

    // =========================================================================
    // 4. Input Fixes
    // =========================================================================
    window.addEventListener('pointerup', (e) => {
        if (e.target) {
            e.target.focus();
            const d = new KeyboardEvent('keydown', { keyCode: 13, key: 'Enter', bubbles: true });
            const u = new KeyboardEvent('keyup', { keyCode: 13, key: 'Enter', bubbles: true });
            e.target.dispatchEvent(d); e.target.dispatchEvent(u);
        }
    }, true);
    window.addEventListener('keydown', (e) => {
        if (['BrowserBack', 'XF86Back', 'GoBack', 'Backspace'].includes(e.key)) {
            e.preventDefault();
            window.history.back();
        }
    }, true);

})();
