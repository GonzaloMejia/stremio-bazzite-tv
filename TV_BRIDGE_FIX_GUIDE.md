# Stremio TV Flatpak - WebKitGTK Qt Bridge Fix

## Root Cause Analysis
When running the custom Stremio TV Flatpak built on `stremio-linux-shell` (v1.2.2), the application uses **WebKitGTK** rather than Qt WebEngine. However, the Stremio WebUI (`tv.strem.io`) expects `window.qt.webChannelTransport` to handle native communication. This results in the following error loop in Konsole:
`TypeError: window.qt.webChannelTransport.onmessage is not a function`

## Solution
We must mock the Qt WebChannel transport inside `tv-mpv-bridge.js` so that WebKitGTK's IPC messaging successfully intercepts and routes messages without throwing errors.

### Implementation Steps

1. **Update `flatpak-build/tv-mpv-bridge.js`**
   Add the following mock handler to the top of the IIFE wrapper:
   ```javascript
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