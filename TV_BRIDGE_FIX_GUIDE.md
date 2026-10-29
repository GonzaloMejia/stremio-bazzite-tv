# Stremio TV Flatpak - Two-Way WebKitGTK Qt Bridge Fix

## Root Cause Analysis
`stremio-linux-shell` (v1.2.2) uses **WebKitGTK 6.0**, injecting `src/app/ipc/preload.js` before any page script. `preload.js` defines:
```javascript
window.qt = {
    webChannelTransport: {
        send: (message) => {
            window.ipc.postMessage(message);
        },
    },
};
```
And whenever the Rust `libmpv` backend emits an event (`mpv-prop-change`, `mpv-event-ended`, `mpv-event-error`, `visibility-change`), Rust evaluates:
```javascript
window.qt.webChannelTransport.onmessage({ data: message });
```

### Why the Minimal No-Op Snippet Broke Playback
Replacing `tv-mpv-bridge.js` with a 15-line snippet where `onmessage: function(event) {}` is an empty no-op prevented the `TypeError: window.qt.webChannelTransport.onmessage is not a function` exception, **but** it discarded every incoming `libmpv` event and omitted `window.shellTransport` (`ShellTransport`), the `Object.prototype.shellTransport` hook, the QWebChannel initialization handshake (`{ type: 3, id: 0 }`), and the Gamepad / M5 BT remote control normalizers.

## Complete Solution in `flatpak-build/tv-mpv-bridge.js`
1. **Safe Append During Build (`com.stremio.Stremio.json`):**
   ```bash
   printf '\n;\n' >> src/app/ipc/preload.js && cat tv-mpv-bridge.js >> src/app/ipc/preload.js
   ```
   This guarantees a newline and semicolon separator after `preload.js`'s final statement before our IIFE begins.
2. **Two-Way IPC Handler:**
   `window.qt.webChannelTransport.onmessage` parses incoming JSON payloads from Rust (`data.type === 1 && Array.isArray(data.args)`) and routes `(data.args[0], data.args[1])` directly to `shellTransportInstance.emit(eventName, eventData)`.
3. **Scoped `Object.prototype.shellTransport` Getter:**
   Exposes `shellTransportInstance` only when accessed on `window` or on `stremio-video`'s `options` object (`'containerElement' in this`), avoiding strict-mode setter crashes on unrelated third-party objects.
