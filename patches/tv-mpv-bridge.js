/**
 * Stremio TV <-> Linux Shell Native MPV, M5 BT Remote & Gamepad Bridge
 * Injected into WebKitGTK at start (preload) for Stremio TV (tv.strem.io)
 */
;(function() {
    'use strict';
    if (window.__STREMIO_TV_BRIDGE_INITIALIZED__) return;
    window.__STREMIO_TV_BRIDGE_INITIALIZED__ = true;

    console.log("[Stremio TV Bridge] Initializing TV Shell Native MPV, M5 BT Remote & Gamepad Bridge...");

    // =========================================================================
    // 0. Indestructible LocalStorage Persistence (Android TV Experience)
    // =========================================================================
    const STORAGE_SYNC_URL = "http://127.0.0.1:11471/__stremio_storage__";
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

    try {
        // Restore pre-injected storage snapshot if provided by local UI server
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
    // 1. Suppress Duplicate Internal Gamepad Poller in tv.strem.io (Module 60715)
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
    // 2. Qt WebChannel Transport & Native MPV ShellTransport Bridge
    // =========================================================================
    window.qt = window.qt || {};
    const existingTransport = window.qt.webChannelTransport || {};
    const existingSend = typeof existingTransport.send === 'function'
        ? existingTransport.send.bind(existingTransport)
        : null;

    const earlyIpcQueue = [];
    window.qt.webChannelTransport = {
        send: function(message) {
            if (existingSend) {
                existingSend(message);
            } else if (window.webkit && window.webkit.messageHandlers && window.webkit.messageHandlers.ipc) {
                window.webkit.messageHandlers.ipc.postMessage(message);
            } else if (window.ipc && typeof window.ipc.postMessage === 'function') {
                window.ipc.postMessage(message);
            }
        },
        onmessage: function(event) {
            earlyIpcQueue.push(event);
        }
    };

    const BLOCKED_SET_PROPS = [];

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
                        } else if (eventName === 'mpv-event-ended') {
                            this.setMpvVisualState(false);
                        }
                        this.emit(eventName, data);
                    }
                } catch (err) {
                    console.error("[Stremio TV Bridge] IPC message parse error:", err);
                }
            };

            // Wire directly to window.qt.webChannelTransport.onmessage (used by stremio-linux-shell webview.rs)
            window.qt.webChannelTransport.onmessage = handleMessage;

            // Flush any messages queued before ShellTransport initialized
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
                    // 1. QWebChannel transport initialization packet (type: 3)
                    window.qt.webChannelTransport.send(JSON.stringify({ type: 3, id: 0 }));
                    // 2. Notify stremio-linux-shell that the web UI is ready
                    this.send('app-ready', {});
                    // 3. Enforce fullscreen 10-foot mode
                    this.send('win-set-visibility', { fullscreen: true });
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
            if (eventName === 'mpv-set-prop') {
                const name = Array.isArray(args) ? args[0] : (args && args.name);
                const hasValue = Array.isArray(args) ? args.length >= 2 : (args && 'value' in args);
                if (typeof name !== 'string' || !hasValue) {
                    return;
                }
                if (BLOCKED_SET_PROPS.includes(name)) {
                    return;
                }
            } else if (eventName === 'mpv-load') {
                this.setMpvVisualState(true);
            } else if (eventName === 'mpv-command' && Array.isArray(args)) {
                if (args[0] === 'loadfile') {
                    this.setMpvVisualState(true);
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

    // Scoped Object.prototype.shellTransport getter/setter so tv.strem.io's
    // selectVideoImplementation(commandArgs, options) selects ShellVideo (libmpv)
    // without breaking strict-mode property assignments on unrelated objects.
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
    // 3. Smart TV Platform Shim (Clean Exit from Couch UI + Splash Dismissal)
    // =========================================================================
    // Ensure window.loadedApp exists and is called once #app renders so the HTML
    // splash overlay never hangs (even on the #/ Login route where MetaPreview is absent).
    window.loadedApp = window.loadedApp || function() {
        const splash = document.getElementById('loading-screen') || document.getElementById('splash');
        if (splash && splash.parentNode) {
            splash.parentNode.removeChild(splash);
        }
    };

    function watchAppMountAndRoute() {
        const checkHash = () => {
            if (!window.location.hash.startsWith('#/player')) {
                shellTransportInstance.setMpvVisualState(false);
            }
        };
        origWindowAddEventListener('hashchange', checkHash);

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
                    // Bypass loader.js's hardcoded 10-second (1e4 ms) delay once UI is ready
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

    // Shim window.tizen so tv.strem.io's native "Are you sure you want to quit?" modal
    // (Platform.quit in module 66307) cleanly exits the Flatpak back to Steam Game Mode.
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
                            console.log("[Stremio TV Bridge] Exiting Stremio TV via Platform.quit()...");
                            syncStorageToDisk();
                            setTimeout(() => {
                                try { shellTransportInstance.send('quit', {}); } catch (e) {}
                                try { window.close(); } catch (e) {}
                            }, 100);
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
    // 4. Dynamic CSS Transparency for Native MPV OpenGL Underlay
    // =========================================================================
    function injectTransparencyStyles() {
        const styleId = "stremio-tv-transparent-bridge";
        if (document.getElementById(styleId)) return;
        const style = document.createElement("style");
        style.id = styleId;
        style.textContent = `
            html, body {
                background-color: #0b0b12;
                transition: background-color 0.15s ease;
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
            /* Guarantee video layer transparency during playback */
            [class*="player-container"],
            [class*="video-container"],
            [class*="Player_playerContainer"],
            .route-player {
                background: transparent !important;
                background-color: transparent !important;
            }
        `;
        (document.head || document.documentElement).appendChild(style);
    }
    if (document.readyState === "loading") {
        document.addEventListener("DOMContentLoaded", injectTransparencyStyles);
    } else {
        injectTransparencyStyles();
    }

    // =========================================================================
    // 5. Key Dispatch Helper & Editable Input Detection (M5 BT QWERTY Support)
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
        // Guarantee e.keyCode and e.which match tv.strem.io module 60715's integer checks
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
    // 6. Comprehensive Gamepad API -> Spatial Navigation Mapper (with Cooldown)
    // =========================================================================
    // Uses tv.strem.io's exact module 60715 keyCodes:
    //   up: 38, down: 40, left: 37, right: 39, enter: 13, back: 8/27,
    //   playPause: 32, seekPrev: 412, seekNext: 417
    const BUTTON_CONFIG = {
        // D-Pad buttons
        12: { key: 'ArrowUp', code: 'ArrowUp', keyCode: 38 },
        13: { key: 'ArrowDown', code: 'ArrowDown', keyCode: 40 },
        14: { key: 'ArrowLeft', code: 'ArrowLeft', keyCode: 37 },
        15: { key: 'ArrowRight', code: 'ArrowRight', keyCode: 39 },

        // Face Buttons
        0:  { key: 'Enter', code: 'Enter', keyCode: 13 },              // A / Cross (Confirm / Select)
        1:  { key: 'Escape', code: 'Escape', keyCode: 27 },            // B / Circle (Back)
        2:  { key: ' ', code: 'Space', keyCode: 32 },                  // X / Square (Play / Pause)
        3:  { key: 'MediaSubtitles', code: 'KeyV', keyCode: 86, action: 'subtitles' }, // Y / Triangle (Subtitles)

        // Bumpers (Seek in Player)
        4:  { key: 'MediaRewind', code: 'MediaRewind', keyCode: 412 },         // L1 / LB (Seek Prev)
        5:  { key: 'MediaFastForward', code: 'MediaFastForward', keyCode: 417 }, // R1 / RB (Seek Next)

        // Menu / View
        8:  { key: 'Escape', code: 'Escape', keyCode: 27 },            // View / Select (Back)
        9:  { key: ' ', code: 'Space', keyCode: 32 }                   // Menu / Start (Play / Pause)
    };

    const AXIS_DEADZONE = 0.5;
    const COOLDOWN_MS = 180;
    let lastNativeKeyTime = 0;
    const buttonState = {};
    const repeatTimers = {};
    let pollTimer = null;

    function triggerButtonConfig(type, cfg) {
        if (type === 'keydown' && cfg.action === 'subtitles') {
            if (window.location.hash.startsWith('#/player') && !window.location.href.includes('subtitles=true')) {
                window.location.href = window.location.href + (window.location.href.includes('?') ? '&' : '?') + 'subtitles=true';
                return;
            }
        }
        dispatchKeyEvent(type, cfg);
    }

    function checkButtonState(id, isPressed, cfg, isDirectional = false) {
        const wasPressed = buttonState[id] || false;
        if (isPressed && !wasPressed) {
            buttonState[id] = true;
            // Anti-double-input guard: skip if Steam Input already sent a keyboard event
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
            pollTimer = setInterval(pollGamepads, 16); // 60Hz polling
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
    // 7. M5 BT English Remote Control (2.4GHz / BT 5.0 + Air-Mouse + QWERTY)
    // =========================================================================
    // 7a. Air-Mouse Right-Click -> Back Navigation:
    // On the M5 BT remote (and G10/G20/WeChip air remotes), pressing the Back/Return
    // button while in Air-Mouse mode emits a mouse right-click (contextmenu).
    // Suppress the browser context menu and trigger Stremio Back navigation!
    origWindowAddEventListener('contextmenu', (e) => {
        e.preventDefault();
        e.stopPropagation();
        dispatchKeyEvent('keydown', { key: 'Escape', code: 'Escape', keyCode: 27 });
    }, true);

    // 7b. Air-Mouse Left-Click on <input>/<textarea>:
    // tv.strem.io module 60715 calls e.preventDefault() on document mousedown, which
    // prevents clicking an <input> with the M5 BT Air-Mouse from focusing it.
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

    // 7c. Keydown Normalizer & M5 BT Rear QWERTY Text-Input Protection:
    origWindowAddEventListener('keydown', (e) => {
        if (e.isBridgeSynthetic) return;
        lastNativeKeyTime = Date.now();

        const editing = isEditableFocused();

        // Hardware Volume Keys on M5 BT Remote (work globally even when editing)
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

        // When typing on the M5 BT rear QWERTY keyboard inside a Search or Login <input>:
        // Protect Backspace (8) and letters x (88), g (71), j (74), e (69), h (72)
        // from tv.strem.io module 60715's document keydown listener (which otherwise
        // treats keyCode 8 and 88 as 'back' and navigates away from the input page!).
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
            // Let ArrowUp, ArrowDown, Enter, Escape bubble to spatial navigation so the user
            // can move focus out of the input box or submit; stop propagation for typing keys
            // that collide with module 60715's media/back keycodes (8, 88, 71, 74, 69, 72).
            const collidingCodes = [8, 69, 71, 72, 74, 88];
            if (collidingCodes.includes(e.keyCode) || (e.key && e.key.length === 1)) {
                e.stopPropagation();
            }
            return;
        }

        // Not editing an <input>: handle M5 BT Front Remote & Media keys
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
                if (window.location.hash.startsWith('#/player') && !window.location.href.includes('ctx.settings=true')) {
                    e.preventDefault();
                    e.stopPropagation();
                    window.location.href = window.location.href + (window.location.href.includes('?') ? '&' : '?') + 'ctx.settings=true';
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
