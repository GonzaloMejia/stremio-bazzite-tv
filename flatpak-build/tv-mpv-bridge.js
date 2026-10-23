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

    console.log("[Stremio TV Bridge] Preload bridge loaded successfully.");
})();
