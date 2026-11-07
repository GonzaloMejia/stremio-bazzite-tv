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
            } else if (window.webkit && window.webkit.messageHandlers && window.webkit.messageHandlers.ipc) {
                window.webkit.messageHandlers.ipc.postMessage(message);
            }
        }
    };
    if (typeof window.qt.webChannelTransport.onmessage !== 'function') {
        window.qt.webChannelTransport.onmessage = function(event) {};
    }

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
            if (!this.listeners[eventName]) this.listeners[eventName] = [];
            this.listeners[eventName].push(callback);
        }

        emit(eventName, data) {
            if (this.listeners[eventName]) {
                this.listeners[eventName].forEach(cb => {
                    try { cb(data); } catch (e) { console.error(e); }
                });
            }
        }
    }

    const shellTransportInstance = new ShellTransport();
    window.shellTransport = shellTransportInstance;
    window.stremio_server_ipc_key = "LINUX";

    // 2. Subtitle Raising & Input Helper
    function dispatchSyntheticKey(key, code, keyCode) {
        const target = document.activeElement && document.activeElement !== document.body ? document.activeElement : document;
        target.dispatchEvent(new KeyboardEvent('keydown', { key: key, code: code, keyCode: keyCode, which: keyCode, bubbles: true, cancelable: true }));
        target.dispatchEvent(new KeyboardEvent('keyup', { key: key, code: code, keyCode: keyCode, which: keyCode, bubbles: true, cancelable: true }));
    }

    class SubtitleRaisingManager {
        constructor() {
            this.init();
        }
        init() {
            const style = document.createElement('style');
            style.textContent = `
                html, body, #app { background: transparent !important; }
                body.stremio-controls-visible [class*="subtitle-container"],
                body.stremio-controls-visible .stremio-video-subtitles {
                    bottom: 150px !important;
                    transform: translateY(-130px) !important;
                    position: absolute !important;
                    transition: transform 0.2s ease !important;
                }
            `;
            document.head.appendChild(style);
            
            const check = () => {
                if (!window.location.hash.startsWith('#/player')) return;
                const controls = document.querySelector('[class*="control-bar-container"]');
                const visible = controls && controls.offsetWidth > 0 && controls.offsetHeight > 0 && getComputedStyle(controls).opacity !== '0';
                document.body.classList.toggle('stremio-controls-visible', visible);
                shellTransportInstance.send('mpv-set-prop', ['sub-pos', visible ? 75 : 100]);
            };
            new MutationObserver(check).observe(document.body, { attributes: true, subtree: true });
            ['mousemove', 'pointerdown', 'keydown', 'wheel'].forEach(evt => window.addEventListener(evt, check, true));
        }
    }
    new SubtitleRaisingManager();

    // 3. Mouse & Spatial Navigation Fixes
    const FOCUSABLE_SELECTOR = '[tabindex]:not([tabindex="-1"]), a[href], button, input, select, [role="button"], [class*="tile"], [class*="poster"], [class*="item-container"], [class*="option"], [class*="button-container"], [class*="control"]';
    
    function findFocusableTile(el) { return el ? (el.closest('[tabindex]') || el.closest(FOCUSABLE_SELECTOR)) : null; }

    window.addEventListener('mousemove', (e) => {
        document.documentElement.style.cursor = 'auto';
        const hit = document.elementFromPoint(e.clientX, e.clientY);
        const focusTarget = findFocusableTile(hit);
        if (focusTarget && focusTarget !== document.activeElement) focusTarget.focus({ preventScroll: true });
    }, true);

    window.addEventListener('mousedown', (e) => {
        if (e.button === 0) {
            const focusTarget = findFocusableTile(e.target);
            if (focusTarget) focusTarget.focus({ preventScroll: true });
        }
    }, true);

    window.addEventListener('click', (e) => {
        if (!e.isTrusted) return;
        const target = findFocusableTile(e.target);
        if (target) {
            target.focus({ preventScroll: true });
            setTimeout(() => dispatchSyntheticKey('Enter', 'Enter', 13), 40);
        }
    }, true);

    window.addEventListener('contextmenu', (e) => {
        e.preventDefault();
        dispatchSyntheticKey('Escape', 'Escape', 27);
        setTimeout(() => window.history.back(), 50);
    }, true);

    window.addEventListener('keydown', (e) => {
        if (['BrowserBack', 'XF86Back', 'GoBack', 'Backspace', 'Escape'].includes(e.key)) {
            e.preventDefault();
            window.history.back();
        }
    }, true);

    // 4. Gamepad SSE Receiver
    const sse = new EventSource('http://127.0.0.1:11471/__gamepad_stream');
    sse.onmessage = (e) => {
        const { key, keyCode } = JSON.parse(e.data);
        dispatchSyntheticKey(key, key, keyCode);
    };

})();
