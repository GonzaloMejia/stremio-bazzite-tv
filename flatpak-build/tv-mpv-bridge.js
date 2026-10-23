/**
 * Stremio TV <-> Linux Shell Native MPV & Gamepad Bridge
 * Injected into WebKitGTK at start (preload) for tv.strem.io
 */
(function() {
    console.log("[Stremio TV Bridge] Initializing TV Shell Native MPV & Gamepad Bridge...");

    // Mock Qt WebChannel transport for WebKitGTK compatibility
    window.qt = window.qt || {};
    window.qt.webChannelTransport = window.qt.webChannelTransport || {
        onmessage: function(event) {},
        send: function(message) {
            if (window.ipc && typeof window.ipc.postMessage === 'function') {
                window.ipc.postMessage(message);
            }
        }
    };
    if (typeof window.qt.webChannelTransport.onmessage !== 'function') {
        window.qt.webChannelTransport.onmessage = function(event) {};
    }

    // Optional blocklist for mpv-set-prop names. Empty by default because the shell
    // supports all property names ShellVideo sends. To reproduce the master guide's
    // filtering, use: ['sub-color', 'sub-back-color', 'sub-border-color', 'sub-font-size']
    const BLOCKED_SET_PROPS = [];

    // 1. Native MPV ShellTransport Bridge
    class ShellTransport {
        constructor() {
            this.listeners = {};
            this.initIpcListener();
        }

        initIpcListener() {
            const handleMessage = (event) => {
                try {
                    let msg = event.data;
                    if (typeof msg === 'string') {
                        msg = JSON.parse(msg);
                    }
                    if (msg && Array.isArray(msg.args)) {
                        const [eventName, data] = msg.args;
                        this.emit(eventName, data);
                    }
                } catch (err) {
                    console.error("[Stremio TV Bridge] IPC message parse error:", err);
                }
            };

            if (window.ipc && typeof window.ipc.addEventListener === 'function') {
                window.ipc.addEventListener('message', handleMessage);
            } else {
                window.addEventListener('message', handleMessage);
            }
        }

        send(eventName, args) {
            // MPV property setter validation.
            // ShellVideo sends mpv-set-prop as an ARRAY: [name, value].
            // stremio-linux-shell (src/app/ipc/request.rs) rejects:
            //   - non-string names  -> "Invalid mpv-set-prop name"
            //     (ShellVideo has no prop-name mapping for subtitlesOffset, so it sends [null, value])
            //   - missing values    -> "Invalid mpv-set-prop value"
            //     (ShellVideo sends ["no-sub-ass"] on every load)
            // NOTE: sub-color / sub-back-color / sub-border-color are supported by the
            // shell (src/app/video/config.rs STRING_PROPERTIES) and are intentionally passed through.
            if (eventName === 'mpv-set-prop') {
                const name = Array.isArray(args) ? args[0] : (args && args.name);
                const hasValue = Array.isArray(args) ? args.length >= 2 : (args && 'value' in args);
                if (typeof name !== 'string' || !hasValue) {
                    console.debug("[Stremio TV Bridge] Dropped invalid mpv-set-prop:", JSON.stringify(args));
                    return;
                }
                if (BLOCKED_SET_PROPS.includes(name)) {
                    console.debug("[Stremio TV Bridge] Dropped blocked mpv-set-prop:", name);
                    return;
                }
            }

            const payload = JSON.stringify({
                type: 6,
                args: [eventName, args]
            });
            if (window.ipc && typeof window.ipc.postMessage === 'function') {
                window.ipc.postMessage(payload);
            } else if (window.webkit && window.webkit.messageHandlers && window.webkit.messageHandlers.ipc) {
                window.webkit.messageHandlers.ipc.postMessage(payload);
            } else {
                console.warn("[Stremio TV Bridge] IPC channel unavailable for send:", eventName);
            }
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

    // Export on window and inject into Object.prototype so tv.strem.io's selectVideoImplementation
    // automatically selects ShellVideo over HTML5 video
    window.shellTransport = shellTransportInstance;
    try {
        Object.defineProperty(Object.prototype, 'shellTransport', {
            get() {
                return shellTransportInstance;
            },
            configurable: true
        });
    } catch (e) {
        console.warn("[Stremio TV Bridge] Unable to attach to Object.prototype:", e);
    }

    window.stremio_server_ipc_key = "LINUX";

    // 2. Comprehensive Gamepad API -> Spatial Navigation Keyboard Mapper
    // Maps Xbox / DualShock / DualSense / Steam Deck controllers to TV keyboard events
    const BUTTON_CONFIG = {
        // D-Pad buttons
        12: { key: 'ArrowUp', keyCode: 38 },
        13: { key: 'ArrowDown', keyCode: 40 },
        14: { key: 'ArrowLeft', keyCode: 37 },
        15: { key: 'ArrowRight', keyCode: 39 },

        // Face Buttons
        0:  { key: 'Enter', keyCode: 13 },         // A / Cross (Confirm / Select)
        1:  { key: 'Backspace', keyCode: 8 },      // B / Circle (Back)
        2:  { key: ' ', code: 'Space', keyCode: 32 }, // X / Square (Play / Pause)
        3:  { key: 'v', keyCode: 86 },             // Y / Triangle (Subtitles)

        // Bumpers (Seek)
        4:  { key: 'e', keyCode: 69 },             // Left Bumper (Seek Prev)
        5:  { key: 'h', keyCode: 72 },             // Right Bumper (Seek Next)

        // Menu / View
        8:  { key: 'Backspace', keyCode: 8 },      // View / Select
        9:  { key: 'Escape', keyCode: 27 }         // Menu / Start
    };

    // Inject CSS to ensure player viewport transparency allows native MPV underlay to show through
    function injectTransparencyStyles() {
        const styleId = "stremio-tv-transparent-bridge";
        if (document.getElementById(styleId)) return;
        const style = document.createElement("style");
        style.id = styleId;
        style.textContent = `
            html, body {
                background-color: transparent !important;
            }
            /* Guarantee video layer transparency during playback */
            [class*="player-container"], [class*="video-container"], [class*="Player_playerContainer"], .route-player {
                background-color: transparent !important;
            }
        `;
        if (document.head) {
            document.head.appendChild(style);
        } else if (document.documentElement) {
            document.documentElement.appendChild(style);
        }
    }
    if (document.readyState === "loading") {
        document.addEventListener("DOMContentLoaded", injectTransparencyStyles);
    } else {
        injectTransparencyStyles();
    }

    const AXIS_DEADZONE = 0.5;
    const buttonState = {};
    const repeatTimers = {};
    let pollTimer = null;

    function dispatchKeyEvent(type, cfg) {
        const evt = new KeyboardEvent(type, {
            key: cfg.key,
            code: cfg.code || cfg.key,
            keyCode: cfg.keyCode,
            which: cfg.keyCode,
            bubbles: true,
            cancelable: true,
            view: window
        });

        // Dispatch on active focused element if available, otherwise document
        const target = document.activeElement && document.activeElement !== document.body
            ? document.activeElement
            : document;
        target.dispatchEvent(evt);
    }

    function checkButtonState(id, isPressed, cfg, isDirectional = false) {
        const wasPressed = buttonState[id] || false;
        if (isPressed && !wasPressed) {
            buttonState[id] = true;
            dispatchKeyEvent('keydown', cfg);

            // For directional controls (D-pad & stick), enable smooth key-repeat
            if (isDirectional) {
                if (repeatTimers[id]) {
                    clearTimeout(repeatTimers[id].initial);
                    clearInterval(repeatTimers[id].interval);
                }
                repeatTimers[id] = {
                    initial: setTimeout(() => {
                        repeatTimers[id].interval = setInterval(() => {
                            if (buttonState[id]) {
                                dispatchKeyEvent('keydown', cfg);
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
            dispatchKeyEvent('keyup', cfg);
        }
    }

    function pollGamepads() {
        if (!navigator.getGamepads) return;
        const gamepads = navigator.getGamepads();
        for (let i = 0; i < gamepads.length; i++) {
            const gp = gamepads[i];
            if (!gp) continue;

            // 1. Analog Sticks to D-Pad (with deadzone & repeat)
            const axisX = gp.axes[0] || 0;
            const axisY = gp.axes[1] || 0;

            checkButtonState(i + '_axis_up', axisY < -AXIS_DEADZONE, { key: 'ArrowUp', keyCode: 38 }, true);
            checkButtonState(i + '_axis_down', axisY > AXIS_DEADZONE, { key: 'ArrowDown', keyCode: 40 }, true);
            checkButtonState(i + '_axis_left', axisX < -AXIS_DEADZONE, { key: 'ArrowLeft', keyCode: 37 }, true);
            checkButtonState(i + '_axis_right', axisX > AXIS_DEADZONE, { key: 'ArrowRight', keyCode: 39 }, true);

            // 2. Hardware Buttons (D-pad has repeat, face buttons do not)
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

    window.addEventListener('gamepadconnected', (e) => {
        console.log("[Stremio TV Bridge] Gamepad connected:", e.gamepad.id);
        startPolling();
    });

    window.addEventListener('gamepaddisconnected', () => {
        const gamepads = navigator.getGamepads ? navigator.getGamepads() : [];
        const hasAny = Array.from(gamepads).some(gp => gp !== null);
        if (!hasAny && pollTimer) {
            clearInterval(pollTimer);
            pollTimer = null;
        }
    });

    // Start immediately in case controller is already connected
    startPolling();

    // 3. 2.4GHz USB HID Remote Control Normalizer
    // Normalizes USB air remotes, G10/G20, WeChip, and Android TV USB dongles
    window.addEventListener('keydown', (e) => {
        if (e.isBridgeSynthetic) return;

        let mapped = null;
        switch (e.key) {
            case 'Select':
                mapped = { key: 'Enter', code: 'Enter', keyCode: 13 };
                break;
            case 'BrowserBack':
            case 'GoBack':
            case 'Back':
                mapped = { key: 'Backspace', code: 'Backspace', keyCode: 8 };
                break;
            case 'MediaPlayPause':
            case 'MediaPlay':
            case 'MediaPause':
                mapped = { key: ' ', code: 'Space', keyCode: 32 };
                break;
            case 'MediaTrackPrevious':
            case 'MediaRewind':
                mapped = { key: 'e', code: 'KeyE', keyCode: 69 };
                break;
            case 'MediaTrackNext':
            case 'MediaFastForward':
                mapped = { key: 'h', code: 'KeyH', keyCode: 72 };
                break;
            case 'ContextMenu':
                mapped = { key: 'Escape', code: 'Escape', keyCode: 27 };
                break;
        }

        if (mapped) {
            e.preventDefault();
            e.stopPropagation();

            const syntheticEvt = new KeyboardEvent('keydown', {
                key: mapped.key,
                code: mapped.code,
                keyCode: mapped.keyCode,
                which: mapped.keyCode,
                bubbles: true,
                cancelable: true,
                view: window
            });
            syntheticEvt.isBridgeSynthetic = true;

            const target = document.activeElement && document.activeElement !== document.body
                ? document.activeElement
                : document;
            target.dispatchEvent(syntheticEvt);
        }
    }, true);

    console.log("[Stremio TV Bridge] Preload bridge loaded successfully.");
})();
