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

                // Robustness: Block specific bad methods
                if (eventName === 'app-ready') return; // If Rust rejects

                // mpv-set-prop color normalization
                if (eventName === 'mpv-set-prop' && Array.isArray(args)) {
                    const name = args[0];
                    if (['sub-color', 'sub-back-color', 'sub-border-color'].includes(name)) {
                        args[1] = formatMpvColor(args[1]);
                    }
                    if (args[1] === null || args[1] === undefined) return;
                }
                
                // mpv-observe-prop filtering
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
        } catch (e) {
            console.error("[Stremio TV Bridge] IPC Intercept Error:", e);
        }
    }

    // Hook WebKit
    if (window.webkit && window.webkit.messageHandlers && window.webkit.messageHandlers.ipc) {
        Object.defineProperty(window.webkit.messageHandlers.ipc, 'postMessage', {
            value: interceptAndSend,
            writable: false,
            configurable: false
        });
    }

    // Hook Qt
    window.qt = window.qt || {};
    Object.defineProperty(window.qt, 'webChannelTransport', {
        get: () => ({ send: interceptAndSend }),
        configurable: false
    });

    // =========================================================================
    // 1. Color Normalization
    // =========================================================================
    function formatMpvColor(val) {
        if (typeof val !== 'string') return val;
        val = val.trim();
        // Just strip alpha for now if it's #RRGGBBAA to #RRGGBB, libmpv v0.41 might be picky
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
                        transform: translateY(-110px) !important;
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
            } catch(e) { console.error("SubtitleRaisingManager init error", e); }
        }

        updateSubtitlePosition() {
            try {
                if (this.isControlsVisible) {
                    document.body.classList.add('stremio-controls-visible');
                    interceptAndSend({type: 6, args: ['mpv-set-prop', ['sub-pos', 82]]});
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
            this.init();
        }
        init() {
            window.addEventListener('gamepadconnected', () => {
                console.log("[Stremio TV Bridge] Gamepad connected.");
                if (!this.pollInterval) this.pollInterval = setInterval(() => this.poll(), 50);
            });
        }
        poll() {
            const gamepads = navigator.getGamepads();
            for (let i = 0; i < gamepads.length; i++) {
                const gp = gamepads[i];
                if (!gp) continue;
                // Simplified mapping for demonstration - needs full implementation
                if (gp.buttons[0].pressed) this.dispatchKey(13); // A
            }
        }
        dispatchKey(keyCode) {
            const evt = new KeyboardEvent('keydown', { keyCode: keyCode, bubbles: true });
            document.dispatchEvent(evt);
        }
    }
    new GamepadControllerManager();

})();
