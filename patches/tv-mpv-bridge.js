/**
 * Stremio TV <-> Linux Shell Native MPV, M5 BT Remote & Gamepad Bridge
 * Injected into WebKitGTK at start (preload) for Stremio TV (tv.strem.io)
 */
;(function() {
    'use strict';
    if (window.__STREMIO_TV_BRIDGE_INITIALIZED__) return;
    window.__STREMIO_TV_BRIDGE_INITIALIZED__ = true;

    // =========================================================================
    // 0. Immediate Synchronous Initialization (Fix Startup TypeError)
    // =========================================================================
    window.qt = window.qt || {};
    window.qt.webChannelTransport = window.qt.webChannelTransport || {
        send: function() {},
        onmessage: function() {}
    };
    if (typeof window.qt.webChannelTransport.onmessage !== 'function') {
        window.qt.webChannelTransport.onmessage = function() {};
    }

    console.log("[Stremio TV Bridge] Initializing TV Shell Native MPV, M5 BT Remote & Gamepad Bridge...");

    // =========================================================================
    // 1. Indestructible LocalStorage Persistence (Android TV Experience)
    // =========================================================================
    const STORAGE_SYNC_URL = "http://127.0.0.1:11471/__stremio_storage__";
    const QUIT_URL = "http://127.0.0.1:11471/__quit";
    let isRestoringStorage = false;
    let storageSyncTimer = null;

    function syncStorageToDisk() {
        if (isRestoringStorage) return;
        if (storageSyncTimer) clearTimeout(storageSyncTimer);
        storageSyncTimer = setTimeout(() => {
            try {
                const dump = {};
                for (let i = 0; i < window.localStorage.length; i++) {
                    const k = window.localStorage.key(i);
                    if (k !== null) {
                        dump[k] = window.localStorage.getItem(k);
                    }
                }
                fetch(STORAGE_SYNC_URL, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(dump),
                    keepalive: true
                }).catch(() => {});
            } catch (e) {}
        }, 150);
    }

    function requestCleanQuit() {
        console.log("[Stremio TV Bridge] Clean quit requested. Posting to UI Server...");
        try { syncStorageToDisk(); } catch (e) {}
        fetch(QUIT_URL, { method: 'POST', keepalive: true }).catch(() => {});
        setTimeout(() => {
            try { window.close(); } catch (e) {}
        }, 150);
    }

    try {
        if (window.__INITIAL_STREMIO_STORAGE__ && typeof window.__INITIAL_STREMIO_STORAGE__ === 'object') {
            isRestoringStorage = true;
            for (const [k, v] of Object.entries(window.__INITIAL_STREMIO_STORAGE__)) {
                if (typeof v === 'string' && window.localStorage.getItem(k) === null) {
                    window.localStorage.setItem(k, v);
                }
            }
            isRestoringStorage = false;
        }

        const origSetItem = Storage.prototype.setItem;
        const origRemoveItem = Storage.prototype.removeItem;
        const origClear = Storage.prototype.clear;

        Storage.prototype.setItem = function(key, value) {
            origSetItem.call(this, key, value);
            if (this === window.localStorage) syncStorageToDisk();
        };
        Storage.prototype.removeItem = function(key) {
            origRemoveItem.call(this, key);
            if (this === window.localStorage) syncStorageToDisk();
        };
        Storage.prototype.clear = function() {
            origClear.call(this);
            if (this === window.localStorage) syncStorageToDisk();
        };
    } catch (e) {
        console.warn("[Stremio TV Bridge] Storage hook warning:", e);
    }

    // =========================================================================
    // 2. Suppress Duplicate Internal Gamepad Poller in tv.strem.io (Module 60715)
    // =========================================================================
    const origWindowAddEventListener = window.addEventListener.bind(window);
    window.addEventListener = function(type, listener, options) {
        if ((type === 'gamepadconnected' || type === 'gamepaddisconnected') &&
            listener && !listener.__isStremioBridgeListener) {
            console.log("[Stremio TV Bridge] Suppressed duplicate internal " + type + " listener from web UI.");
            return;
        }
        return origWindowAddEventListener(type, listener, options);
    };

    // =========================================================================
    // 3. Subtitle Color Normalization (#RRGGBBAA -> #AARRGGBB for libmpv v0.41.0)
    // =========================================================================
    function formatMpvColor(val) {
        if (typeof val !== 'string') return val;
        val = val.trim();
        if (val.startsWith('#')) {
            const hex = val.slice(1);
            if (hex.length === 8) {
                // RRGGBBAA -> AARRGGBB
                const rrggbb = hex.slice(0, 6);
                const aa = hex.slice(6, 8);
                return '#' + aa + rrggbb;
            } else if (hex.length === 6) {
                return '#FF' + hex;
            }
        }
        return val;
    }

    // =========================================================================
    // 4. Qt WebChannel Transport & Native MPV ShellTransport Bridge
    // =========================================================================
    const existingTransport = window.qt.webChannelTransport || {};
    const existingSend = typeof existingTransport.send === 'function'
        ? existingTransport.send.bind(existingTransport)
        : null;

    const earlyIpcQueue = [];
    window.qt.webChannelTransport.send = function(message) {
        if (existingSend) {
            existingSend(message);
        } else if (window.webkit && window.webkit.messageHandlers && window.webkit.messageHandlers.ipc) {
            window.webkit.messageHandlers.ipc.postMessage(message);
        } else if (window.ipc && typeof window.ipc.postMessage === 'function') {
            window.ipc.postMessage(message);
        }
    };
    window.qt.webChannelTransport.onmessage = function(event) {
        earlyIpcQueue.push(event);
    };

    const BLOCKED_SET_PROPS = ['vo', 'input-defalt-bindings', 'input-default-bindings'];

    class ShellTransport {
        constructor() {
            this.listeners = {};
            this.mpvVolume = 100;
            this.mpvMuted = false;
            this.isPlaying = false;
            this.initIpcListener();
            this.initHandshake();
        }

        initIpcListener() {
            const handleMessage = (event) => {
                try {
                    let msg = (event && typeof event === 'object' && 'data' in event) ? event.data : event;
                    if (typeof msg === 'string') {
                        msg = JSON.parse(msg);
                    }
                    if (!msg) return;

                    let eventName = null;
                    let data = undefined;

                    if (Array.isArray(msg.args)) {
                        [eventName, data] = msg.args;
                    } else if (Array.isArray(msg)) {
                        [eventName, data] = msg;
                    }

                    if (eventName) {
                        if (eventName === 'mpv-prop-change' && data && typeof data === 'object') {
                            if (data.name === 'volume' && typeof data.data === 'number') {
                                this.mpvVolume = data.data;
                            } else if (data.name === 'mute' && typeof data.data === 'boolean') {
                                this.mpvMuted = data.data;
                            }
                            if (window.TrackHudManager) {
                                window.TrackHudManager.handlePropChange(data);
                            }
                        } else if (eventName === 'mpv-event-ended') {
                            this.setMpvVisualState(false);
                        }
                        this.emit(eventName, data);
                    }
                } catch (err) {
                    console.error("[Stremio TV Bridge] IPC message parse error:", err);
                }
            };

            window.qt.webChannelTransport.onmessage = handleMessage;

            while (earlyIpcQueue.length > 0) {
                handleMessage(earlyIpcQueue.shift());
            }

            if (window.ipc && typeof window.ipc.addEventListener === 'function') {
                window.ipc.addEventListener('message', handleMessage);
            }
        }

        initHandshake() {
            const sendInit = () => {
                try {
                    window.qt.webChannelTransport.send(JSON.stringify({ type: 3, id: 0 }));
                    this.send('app-ready', {});
                    this.send('win-set-visibility', { fullscreen: true });
                    // Hardware Acceleration (AMD VAAPI) on init
                    this.send('mpv-set-prop', ['hwdec', 'vaapi']);
                    // Observe track properties for HUD
                    this.send('mpv-observe-prop', ['track-list', 'track-list']);
                    this.send('mpv-observe-prop', ['aid', 'aid']);
                    this.send('mpv-observe-prop', ['sid', 'sid']);
                    this.send('mpv-observe-prop', ['sub-delay', 'sub-delay']);
                    this.send('mpv-observe-prop', ['sub-scale', 'sub-scale']);
                } catch (err) {
                    console.warn("[Stremio TV Bridge] Handshake warning:", err);
                }
            };

            if (document.readyState === 'loading') {
                document.addEventListener('DOMContentLoaded', sendInit, { once: true });
            } else {
                setTimeout(sendInit, 0);
            }
        }

        setMpvVisualState(playing) {
            this.isPlaying = playing;
            if (!document.documentElement) return;
            if (playing) {
                document.documentElement.classList.add('mpv-playing');
            } else {
                document.documentElement.classList.remove('mpv-playing');
            }
        }

        send(eventName, args) {
            if (eventName === 'quit' || eventName === 'app-quit') {
                requestCleanQuit();
                return;
            }

            if (eventName === 'mpv-set-prop') {
                let name = Array.isArray(args) ? args[0] : (args && args.name);
                let val = Array.isArray(args) ? args[1] : (args && args.value);
                if (typeof name !== 'string') return;

                if (BLOCKED_SET_PROPS.includes(name)) return;

                // Filter out null/undefined values
                if (val === null || val === undefined) return;

                // Color normalization for subtitles (Raw -9 fix)
                if (name === 'sub-color' || name === 'sub-back-color' || name === 'sub-border-color') {
                    val = formatMpvColor(val);
                    if (Array.isArray(args)) args[1] = val;
                    else if (args && 'value' in args) args.value = val;
                }
            } else if (eventName === 'mpv-observe-prop') {
                // Filter invalid observe-prop names
                const invalidProps = ['input-default-bindings'];
                if (Array.isArray(args)) {
                    if (invalidProps.includes(args[0])) return;
                } else if (args && typeof args === 'string') {
                    if (invalidProps.includes(args)) return;
                }
            } else if (eventName === 'mpv-load') {
                this.setMpvVisualState(true);
                // Set default clean subtitle properties & HW acceleration
                this.send('mpv-set-prop', ['hwdec', 'vaapi']);
                this.send('mpv-set-prop', ['sub-color', '#FFFFFFFF']);
                this.send('mpv-set-prop', ['sub-border-color', '#FF000000']);
                this.send('mpv-set-prop', ['sub-border-size', 2.5]);
                this.send('mpv-set-prop', ['sub-font-size', 48]);
            } else if (eventName === 'mpv-command' && Array.isArray(args)) {
                if (args[0] === 'loadfile') {
                    this.setMpvVisualState(true);
                    this.send('mpv-set-prop', ['hwdec', 'vaapi']);
                } else if (args[0] === 'stop') {
                    this.setMpvVisualState(false);
                }
            }

            const payload = JSON.stringify({
                type: 6,
                args: [eventName, args]
            });

            if (window.qt && window.qt.webChannelTransport && typeof window.qt.webChannelTransport.send === 'function') {
                window.qt.webChannelTransport.send(payload);
            } else if (window.webkit && window.webkit.messageHandlers && window.webkit.messageHandlers.ipc) {
                window.webkit.messageHandlers.ipc.postMessage(payload);
            } else if (window.ipc && typeof window.ipc.postMessage === 'function') {
                window.ipc.postMessage(payload);
            } else {
                console.warn("[Stremio TV Bridge] IPC channel unavailable for send:", eventName);
            }
        }

        adjustVolume(delta) {
            const nextVol = Math.max(0, Math.min(100, Math.round((this.mpvVolume || 100) + delta)));
            this.mpvVolume = nextVol;
            this.send('mpv-set-prop', ['volume', nextVol]);
            if (delta > 0 && this.mpvMuted) {
                this.mpvMuted = false;
                this.send('mpv-set-prop', ['mute', false]);
            }
        }

        toggleMute() {
            this.mpvMuted = !this.mpvMuted;
            this.send('mpv-set-prop', ['mute', this.mpvMuted]);
        }

        on(eventName, callback) {
            if (!this.listeners[eventName]) {
                this.listeners[eventName] = [];
            }
            this.listeners[eventName].push(callback);
        }

        off(eventName, callback) {
            if (this.listeners[eventName]) {
                this.listeners[eventName] = this.listeners[eventName].filter(cb => cb !== callback);
            }
        }

        emit(eventName, data) {
            if (this.listeners[eventName]) {
                this.listeners[eventName].forEach(cb => {
                    try {
                        cb(data);
                    } catch (e) {
                        console.error("[Stremio TV Bridge] Error in listener for " + eventName + ":", e);
                    }
                });
            }
        }
    }

    const shellTransportInstance = new ShellTransport();
    window.shellTransport = shellTransportInstance;
    window.stremio_server_ipc_key = "LINUX";

    try {
        Object.defineProperty(Object.prototype, 'shellTransport', {
            get() {
                if (this === window || (this && typeof this === 'object' && ('containerElement' in this || '_stremioShellTransport' in this))) {
                    return shellTransportInstance;
                }
                return this && this._customShellTransport;
            },
            set(val) {
                try {
                    Object.defineProperty(this, '_customShellTransport', {
                        value: val,
                        writable: true,
                        configurable: true,
                        enumerable: false
                    });
                } catch (e) {}
            },
            configurable: true,
            enumerable: false
        });
    } catch (e) {
        console.warn("[Stremio TV Bridge] Unable to attach to Object.prototype:", e);
    }

    // =========================================================================
    // 5. Automatic Route Exit Detection (Stop libmpv when leaving #/player)
    // =========================================================================
    let wasInPlayerRoute = false;
    function checkRouteState() {
        const inPlayer = window.location.hash.startsWith('#/player');
        if (wasInPlayerRoute && !inPlayer) {
            console.log("[Stremio TV Bridge] Leaving player route (#/player). Stopping libmpv...");
            try {
                shellTransportInstance.send('mpv-command', ['stop']);
                shellTransportInstance.send('mpv-set-prop', ['pause', true]);
                shellTransportInstance.setMpvVisualState(false);
                if (window.TrackHudManager) {
                    window.TrackHudManager.close();
                }
            } catch (err) {
                console.error("[Stremio TV Bridge] Error stopping libmpv on route exit:", err);
            }
        }
        wasInPlayerRoute = inPlayer;
    }
    origWindowAddEventListener('hashchange', checkRouteState);
    origWindowAddEventListener('popstate', checkRouteState);
    setInterval(checkRouteState, 250);

    // =========================================================================
    // 6. Smart TV Platform Shim & Clean Exit
    // =========================================================================
    window.loadedApp = window.loadedApp || function() {
        const splash = document.getElementById('loading-screen') || document.getElementById('splash');
        if (splash && splash.parentNode) {
            splash.parentNode.removeChild(splash);
        }
    };

    function watchAppMountAndRoute() {
        let splashDismissed = false;
        const appObserver = new MutationObserver(() => {
            if (splashDismissed) return;
            const appEl = document.getElementById('app');
            if (appEl && appEl.children.length > 0) {
                const hasLoaderOnly = appEl.querySelector('[class*="loader-container"]');
                if (!hasLoaderOnly) {
                    splashDismissed = true;
                    if (typeof window.loadedApp === 'function') {
                        try { window.loadedApp(); } catch (e) {}
                    }
                    setTimeout(() => {
                        const loaders = document.querySelectorAll('[class*="full_page_loader"]');
                        loaders.forEach(el => {
                            if (el && el.parentNode) {
                                el.parentNode.removeChild(el);
                            }
                        });
                    }, 600);
                }
            }
        });
        if (document.documentElement) {
            appObserver.observe(document.documentElement, { childList: true, subtree: true });
        }
    }

    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', watchAppMountAndRoute, { once: true });
    } else {
        watchAppMountAndRoute();
    }

    if (typeof window.tizen === 'undefined') {
        window.tizen = {
            tvinputdevice: {
                registerKey: function() {},
                unregisterKey: function() {}
            },
            application: {
                getCurrentApplication: function() {
                    return {
                        exit: function() {
                            requestCleanQuit();
                        }
                    };
                },
                launchAppControl: function(appControl, appId, onSuccess, onError) {
                    if (typeof onSuccess === 'function') {
                        try { onSuccess(); } catch (e) {}
                    }
                    if (appControl && appControl.uri && appId !== 'IkWsFHtOY9.StremioService') {
                        window.open(appControl.uri, '_blank');
                    }
                }
            },
            ApplicationControlData: function(key, value) {
                this.key = key;
                this.value = value;
            },
            ApplicationControl: function(operation, uri) {
                this.operation = operation;
                this.uri = uri;
            }
        };
    }

    // =========================================================================
    // 7. Dynamic CSS Transparency & Leanback Android TV Styling
    // =========================================================================
    function injectStyles() {
        const styleId = "stremio-tv-bridge-styles";
        if (document.getElementById(styleId)) return;
        const style = document.createElement("style");
        style.id = styleId;
        style.textContent = `
            html, body {
                background-color: #0b0b12;
                transition: background-color 0.15s ease;
            }
            body.mouse-active, body.mouse-active * {
                cursor: auto !important;
            }
            html.mpv-playing,
            html.mpv-playing body,
            html.mpv-playing #app,
            html.mpv-playing .routes-container,
            html.mpv-playing .route-container,
            html.mpv-playing .route-content {
                background: transparent !important;
                background-color: transparent !important;
            }
            [class*="player-container"],
            [class*="video-container"],
            [class*="Player_playerContainer"],
            .route-player {
                background: transparent !important;
                background-color: transparent !important;
            }

            /* Android TV Card Focus & Leanback Scaling */
            [tabindex]:focus,
            .focusable:focus,
            a:focus,
            button:focus,
            [data-focusable]:focus,
            [class*="card"]:focus,
            [class*="poster"]:focus,
            [class*="item"]:focus {
                outline: 3px solid #8a2be2 !important;
                outline-offset: 2px !important;
                transform: scale(1.04) translateZ(0) !important;
                box-shadow: 0 12px 30px rgba(138, 43, 226, 0.45) !important;
                transition: transform 0.18s cubic-bezier(0.2, 0.9, 0.3, 1), box-shadow 0.18s ease, outline 0.18s ease !important;
                z-index: 10 !important;
            }

            /* Quick Settings / Track HUD Overlay */
            #stremio-tv-track-hud {
                position: fixed;
                top: 0;
                right: 0;
                width: 440px;
                height: 100vh;
                background: rgba(12, 12, 20, 0.93);
                backdrop-filter: blur(20px);
                -webkit-backdrop-filter: blur(20px);
                border-left: 1px solid rgba(255, 255, 255, 0.12);
                box-shadow: -15px 0 35px rgba(0, 0, 0, 0.85);
                z-index: 999999;
                color: #ffffff;
                font-family: 'Inter', system-ui, -apple-system, sans-serif;
                display: flex;
                flex-direction: column;
                padding: 24px;
                box-sizing: border-box;
                transform: translateX(100%);
                transition: transform 0.25s cubic-bezier(0.1, 0.9, 0.2, 1);
            }
            #stremio-tv-track-hud.hud-open {
                transform: translateX(0);
            }
            #stremio-tv-track-hud h2 {
                margin: 0 0 16px 0;
                font-size: 20px;
                font-weight: 700;
                color: #ffffff;
                display: flex;
                align-items: center;
                justify-content: space-between;
                border-bottom: 1px solid rgba(255, 255, 255, 0.1);
                padding-bottom: 12px;
            }
            #stremio-tv-track-hud .hud-tabs {
                display: flex;
                gap: 8px;
                margin-bottom: 16px;
            }
            #stremio-tv-track-hud .hud-tab {
                flex: 1;
                padding: 8px 12px;
                background: rgba(255, 255, 255, 0.08);
                border: 1px solid rgba(255, 255, 255, 0.1);
                border-radius: 8px;
                color: #aaaaaa;
                text-align: center;
                font-size: 13px;
                font-weight: 600;
                cursor: pointer;
            }
            #stremio-tv-track-hud .hud-tab.active,
            #stremio-tv-track-hud .hud-tab:focus {
                background: #8a2be2;
                color: #ffffff;
                border-color: #a259ff;
                outline: none;
            }
            #stremio-tv-track-hud .hud-content {
                flex: 1;
                overflow-y: auto;
                display: flex;
                flex-direction: column;
                gap: 8px;
            }
            #stremio-tv-track-hud .hud-item {
                padding: 12px 16px;
                background: rgba(255, 255, 255, 0.05);
                border: 1px solid rgba(255, 255, 255, 0.08);
                border-radius: 10px;
                font-size: 14px;
                color: #dddddd;
                cursor: pointer;
                display: flex;
                align-items: center;
                justify-content: space-between;
            }
            #stremio-tv-track-hud .hud-item:focus,
            #stremio-tv-track-hud .hud-item.selected {
                background: rgba(138, 43, 226, 0.35);
                border-color: #8a2be2;
                color: #ffffff;
                outline: none;
            }
            #stremio-tv-track-hud .hud-item.selected::after {
                content: "✓";
                font-weight: bold;
                color: #a259ff;
            }
            #stremio-tv-track-hud .hud-ctrl-row {
                display: flex;
                align-items: center;
                justify-content: space-between;
                padding: 10px 14px;
                background: rgba(255, 255, 255, 0.05);
                border-radius: 10px;
                margin-top: 6px;
            }
            #stremio-tv-track-hud .hud-btn {
                padding: 6px 14px;
                background: rgba(255, 255, 255, 0.12);
                border: 1px solid rgba(255, 255, 255, 0.2);
                border-radius: 6px;
                color: #fff;
                font-weight: 600;
                cursor: pointer;
            }
            #stremio-tv-track-hud .hud-btn:focus {
                background: #8a2be2;
                outline: none;
            }
        `;
        (document.head || document.documentElement).appendChild(style);
    }
    if (document.readyState === "loading") {
        document.addEventListener("DOMContentLoaded", injectStyles);
    } else {
        injectStyles();
    }

    // =========================================================================
    // 8. Track & Subtitle Quick Settings HUD Overlay Manager
    // =========================================================================
    class TrackHud {
        // ... (existing TrackHud implementation)
    }

    // =========================================================================
    // 8.5 Dynamic Subtitle Raising Manager
    // =========================================================================
    class SubtitleRaisingManager {
        constructor() {
            this.observer = null;
            this.isControlsVisible = false;
            this.init();
        }

        init() {
            const style = document.createElement('style');
            style.id = 'stremio-subtitle-raising-styles';
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

            this.observer = new MutationObserver(checkControls);
            if (document.body) {
                this.observer.observe(document.body, { attributes: true, subtree: true, attributeFilter: ['class', 'style'] });
            }

            ['mousemove', 'pointerdown', 'keydown', 'wheel'].forEach(evt => {
                window.addEventListener(evt, checkControls, true);
            });
        }

        updateSubtitlePosition() {
            if (this.isControlsVisible) {
                document.body.classList.add('stremio-controls-visible');
                shellTransportInstance.send('mpv-set-prop', ['sub-pos', 82]);
            } else {
                document.body.classList.remove('stremio-controls-visible');
                shellTransportInstance.send('mpv-set-prop', ['sub-pos', 100]);
            }
        }
    }

    const subtitleRaisingManager = new SubtitleRaisingManager();
    window.SubtitleRaisingManager = subtitleRaisingManager;

    const trackHudInstance = new TrackHud();
    window.TrackHudManager = trackHudInstance;

    // =========================================================================
    // 9. Key Dispatch Helper & Editable Input Detection
    // =========================================================================
    function isEditableFocused() {
        const el = document.activeElement;
        if (!el) return false;
        const tag = (el.tagName || '').toUpperCase();
        return tag === 'INPUT' || tag === 'TEXTAREA' || el.isContentEditable === true;
    }

    function createTvKeyboardEvent(type, cfg) {
        const evt = new KeyboardEvent(type, {
            key: cfg.key,
            code: cfg.code || cfg.key,
            keyCode: cfg.keyCode,
            which: cfg.keyCode,
            bubbles: true,
            cancelable: true,
            view: window
        });
        try {
            Object.defineProperty(evt, 'keyCode', { get: () => cfg.keyCode });
            Object.defineProperty(evt, 'which', { get: () => cfg.keyCode });
        } catch (e) {}
        evt.isBridgeSynthetic = true;
        return evt;
    }

    function dispatchKeyEvent(type, cfg) {
        const evt = createTvKeyboardEvent(type, cfg);
        const target = document.activeElement && document.activeElement !== document.body
            ? document.activeElement
            : document;
        target.dispatchEvent(evt);
    }

    // =========================================================================
    // 10. Comprehensive Gamepad API -> Spatial Navigation Mapper
    // =========================================================================
    const BUTTON_CONFIG = {
        12: { key: 'ArrowUp', code: 'ArrowUp', keyCode: 38 },
        13: { key: 'ArrowDown', code: 'ArrowDown', keyCode: 40 },
        14: { key: 'ArrowLeft', code: 'ArrowLeft', keyCode: 37 },
        15: { key: 'ArrowRight', code: 'ArrowRight', keyCode: 39 },

        0:  { key: 'Enter', code: 'Enter', keyCode: 13 },
        1:  { key: 'Escape', code: 'Escape', keyCode: 27 },
        2:  { key: ' ', code: 'Space', keyCode: 32 },
        3:  { key: 'MediaSubtitles', code: 'KeyV', keyCode: 86, action: 'subtitles' },

        4:  { key: 'MediaRewind', code: 'MediaRewind', keyCode: 412 },
        5:  { key: 'MediaFastForward', code: 'MediaFastForward', keyCode: 417 },

        8:  { key: 'Escape', code: 'Escape', keyCode: 27 },
        9:  { key: ' ', code: 'Space', keyCode: 32 }
    };

    const AXIS_DEADZONE = 0.5;
    const COOLDOWN_MS = 180;
    let lastNativeKeyTime = 0;
    const buttonState = {};
    const repeatTimers = {};
    let pollTimer = null;

    function triggerButtonConfig(type, cfg) {
        if (type === 'keydown' && cfg.action === 'subtitles') {
            trackHudInstance.toggle();
            return;
        }
        dispatchKeyEvent(type, cfg);
    }

    function checkButtonState(id, isPressed, cfg, isDirectional = false) {
        const wasPressed = buttonState[id] || false;
        if (isPressed && !wasPressed) {
            buttonState[id] = true;
            if (Date.now() - lastNativeKeyTime >= COOLDOWN_MS) {
                triggerButtonConfig('keydown', cfg);
            }

            if (isDirectional) {
                if (repeatTimers[id]) {
                    clearTimeout(repeatTimers[id].initial);
                    clearInterval(repeatTimers[id].interval);
                }
                repeatTimers[id] = {
                    initial: setTimeout(() => {
                        repeatTimers[id].interval = setInterval(() => {
                            if (buttonState[id] && (Date.now() - lastNativeKeyTime >= COOLDOWN_MS)) {
                                triggerButtonConfig('keydown', cfg);
                            }
                        }, 120);
                    }, 350)
                };
            }
        } else if (!isPressed && wasPressed) {
            buttonState[id] = false;
            if (repeatTimers[id]) {
                clearTimeout(repeatTimers[id].initial);
                clearInterval(repeatTimers[id].interval);
                delete repeatTimers[id];
            }
            if (Date.now() - lastNativeKeyTime >= COOLDOWN_MS) {
                triggerButtonConfig('keyup', cfg);
            }
        }
    }

    function pollGamepads() {
        if (!navigator.getGamepads) return;
        const gamepads = navigator.getGamepads();
        for (let i = 0; i < gamepads.length; i++) {
            const gp = gamepads[i];
            if (!gp) continue;

            const axisX = gp.axes[0] || 0;
            const axisY = gp.axes[1] || 0;

            checkButtonState(i + '_axis_up', axisY < -AXIS_DEADZONE, { key: 'ArrowUp', code: 'ArrowUp', keyCode: 38 }, true);
            checkButtonState(i + '_axis_down', axisY > AXIS_DEADZONE, { key: 'ArrowDown', code: 'ArrowDown', keyCode: 40 }, true);
            checkButtonState(i + '_axis_left', axisX < -AXIS_DEADZONE, { key: 'ArrowLeft', code: 'ArrowLeft', keyCode: 37 }, true);
            checkButtonState(i + '_axis_right', axisX > AXIS_DEADZONE, { key: 'ArrowRight', code: 'ArrowRight', keyCode: 39 }, true);

            for (const [btnIdx, cfg] of Object.entries(BUTTON_CONFIG)) {
                const btn = gp.buttons[btnIdx];
                const pressed = btn ? (typeof btn === 'object' ? btn.pressed : btn > 0.5) : false;
                const isDir = btnIdx >= 12 && btnIdx <= 15;
                checkButtonState(i + '_btn_' + btnIdx, pressed, cfg, isDir);
            }
        }
    }

    function startPolling() {
        if (!pollTimer) {
            pollTimer = setInterval(pollGamepads, 16);
        }
    }

    const onGamepadConnected = (e) => {
        console.log("[Stremio TV Bridge] Gamepad connected:", e.gamepad.id);
        startPolling();
    };
    onGamepadConnected.__isStremioBridgeListener = true;

    const onGamepadDisconnected = () => {
        const gamepads = navigator.getGamepads ? navigator.getGamepads() : [];
        const hasAny = Array.from(gamepads).some(gp => gp !== null);
        if (!hasAny && pollTimer) {
            clearInterval(pollTimer);
            pollTimer = null;
        }
    };
    onGamepadDisconnected.__isStremioBridgeListener = true;

    origWindowAddEventListener('gamepadconnected', onGamepadConnected);
    origWindowAddEventListener('gamepaddisconnected', onGamepadDisconnected);
    startPolling();

    // =========================================================================
    // 11. Mouse Movement, Air-Mouse Clicks, Scroll Wheel & Key Normalization
    // =========================================================================
    let mouseTimer = null;
    origWindowAddEventListener('mousemove', () => {
        if (document.body) {
            document.body.classList.add('mouse-active');
            if (mouseTimer) clearTimeout(mouseTimer);
            mouseTimer = setTimeout(() => {
                if (document.body) document.body.classList.remove('mouse-active');
            }, 3500);
        }
    }, true);

    // M5 Air-Mouse Click / Pointerup Bridge to TV Spatial Navigation
    origWindowAddEventListener('pointerup', (e) => {
        if (e.button !== 0 || !e.target) return;
        const target = e.target;

        // If click is inside HUD or HUD open, let normal DOM handle it
        if (trackHudInstance.isOpen) return;

        const focusable = target.closest('[tabindex], .focusable, a, button, [data-focusable], [role="button"], input, select, textarea, [class*="card"], [class*="poster"], [class*="item"], [class*="button"]');
        if (focusable) {
            try { focusable.focus(); } catch (err) {}
            if (!isEditableFocused()) {
                dispatchKeyEvent('keydown', { key: 'Enter', code: 'Enter', keyCode: 13 });
            }
        }
    }, true);

    // Scroll Wheel Support -> Throttled ArrowUp / ArrowDown
    let lastWheelTime = 0;
    origWindowAddEventListener('wheel', (e) => {
        if (Date.now() - lastWheelTime < 90) return;
        lastWheelTime = Date.now();
        if (e.deltaY > 0) {
            dispatchKeyEvent('keydown', { key: 'ArrowDown', code: 'ArrowDown', keyCode: 40 });
        } else if (e.deltaY < 0) {
            dispatchKeyEvent('keydown', { key: 'ArrowUp', code: 'ArrowUp', keyCode: 38 });
        }
    }, { capture: true, passive: true });

    // Air-Mouse Right-Click -> Back Navigation
    origWindowAddEventListener('contextmenu', (e) => {
        e.preventDefault();
        e.stopPropagation();
        if (trackHudInstance.isOpen) {
            trackHudInstance.close();
        } else {
            dispatchKeyEvent('keydown', { key: 'Escape', code: 'Escape', keyCode: 27 });
        }
    }, true);

    // Focus input on mousedown
    origWindowAddEventListener('mousedown', (e) => {
        if (e.button === 0 && e.target) {
            const tag = (e.target.tagName || '').toUpperCase();
            if (tag === 'INPUT' || tag === 'TEXTAREA') {
                setTimeout(() => {
                    try { e.target.focus(); } catch (err) {}
                }, 0);
            }
        }
    }, true);

    // Keydown Capture Phase Normalization
    origWindowAddEventListener('keydown', (e) => {
        if (e.isBridgeSynthetic) return;
        lastNativeKeyTime = Date.now();

        // If HUD is open, handle HUD navigation
        if (trackHudInstance.isOpen) {
            if (e.key === 'Escape' || e.keyCode === 27) {
                e.preventDefault();
                e.stopPropagation();
                trackHudInstance.close();
                return;
            }
        }

        const editing = isEditableFocused();

        // Volume keys
        if (e.key === 'AudioVolumeUp' || e.key === 'XF86AudioRaiseVolume' || e.keyCode === 175) {
            shellTransportInstance.adjustVolume(5);
            return;
        }
        if (e.key === 'AudioVolumeDown' || e.key === 'XF86AudioLowerVolume' || e.keyCode === 174) {
            shellTransportInstance.adjustVolume(-5);
            return;
        }
        if (e.key === 'AudioVolumeMute' || e.key === 'XF86AudioMute' || e.keyCode === 173) {
            shellTransportInstance.toggleMute();
            return;
        }

        if (editing) {
            if (e.key === 'BrowserBack' || e.key === 'GoBack' || e.key === 'XF86Back') {
                e.preventDefault();
                e.stopPropagation();
                dispatchKeyEvent('keydown', { key: 'Escape', code: 'Escape', keyCode: 27 });
                return;
            }
            if (e.key === 'BrowserHome' || e.key === 'XF86HomePage') {
                e.preventDefault();
                e.stopPropagation();
                if (document.activeElement && typeof document.activeElement.blur === 'function') {
                    document.activeElement.blur();
                }
                window.location.hash = '#/home';
                return;
            }
            const collidingCodes = [8, 69, 71, 72, 74, 88];
            if (collidingCodes.includes(e.keyCode) || (e.key && e.key.length === 1)) {
                e.stopPropagation();
            }
            return;
        }

        // Toggle Subtitle / Audio Track HUD via 'S', 'C', 'V', or Menu keys in Player
        if ((e.key === 's' || e.key === 'S' || e.key === 'c' || e.key === 'C' || e.key === 'v' || e.key === 'V') &&
            window.location.hash.startsWith('#/player')) {
            e.preventDefault();
            e.stopPropagation();
            trackHudInstance.toggle();
            return;
        }

        if (e.key === 'BrowserHome' || e.key === 'XF86HomePage' || e.key === 'Home' || e.keyCode === 172 || e.keyCode === 36) {
            e.preventDefault();
            e.stopPropagation();
            window.location.hash = '#/home';
            return;
        }

        let mapped = null;
        switch (e.key) {
            case 'Select':
            case 'OK':
                mapped = { key: 'Enter', code: 'Enter', keyCode: 13 };
                break;
            case 'BrowserBack':
            case 'GoBack':
            case 'XF86Back':
            case 'Back':
                mapped = { key: 'Escape', code: 'Escape', keyCode: 27 };
                break;
            case 'MediaPlayPause':
            case 'XF86AudioPlay':
                mapped = { key: ' ', code: 'Space', keyCode: 32 };
                break;
            case 'MediaPlay':
                mapped = { key: 'MediaPlay', code: 'MediaPlay', keyCode: 415 };
                break;
            case 'MediaPause':
                mapped = { key: 'MediaPause', code: 'MediaPause', keyCode: 19 };
                break;
            case 'MediaStop':
            case 'XF86AudioStop':
                mapped = { key: 'MediaStop', code: 'MediaStop', keyCode: 413 };
                break;
            case 'MediaTrackPrevious':
            case 'MediaRewind':
            case 'XF86AudioRewind':
            case 'XF86AudioPrev':
                mapped = { key: 'MediaRewind', code: 'MediaRewind', keyCode: 412 };
                break;
            case 'MediaTrackNext':
            case 'MediaFastForward':
            case 'XF86AudioForward':
            case 'XF86AudioNext':
                mapped = { key: 'MediaFastForward', code: 'MediaFastForward', keyCode: 417 };
                break;
            case 'ContextMenu':
            case 'Menu':
            case 'XF86MenuPB':
            case 'F10':
                if (window.location.hash.startsWith('#/player')) {
                    e.preventDefault();
                    e.stopPropagation();
                    trackHudInstance.toggle();
                    return;
                }
                mapped = { key: 'Escape', code: 'Escape', keyCode: 27 };
                break;
        }

        if (mapped) {
            e.preventDefault();
            e.stopPropagation();
            dispatchKeyEvent('keydown', mapped);
        }
    }, true);

    console.log("[Stremio TV Bridge] Preload bridge loaded successfully.");
})();
