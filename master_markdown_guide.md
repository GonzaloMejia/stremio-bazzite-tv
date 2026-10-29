# Stremio Android TV to Linux Bazzite Flatpak — Master Deployment & REA Guide (v1.0)

## 1. Architectural Overview & Objective
This guide documents the complete reverse-engineering and packaging workflow to bridge the Stremio 10-foot Android TV Web interface (`tv.strem.io`) into a native Linux Flatpak (`stremio-linux-shell`) running on Bazzite / SteamOS.

Instead of running bytecode via Waydroid (which lacks the Android TV Leanback framework and MediaCodec hardware acceleration bindings), this project packages:
* **The Application Shell:** `stremio-linux-shell` (Rust + GTK4 + WebKitGTK 6.0 + `libmpv`) built with native VA-API & Wayland support.
* **The UI Layer:** Stremio Theater Web UI (`tv.strem.io`) loaded via local streaming proxy.
* **The IPC & Gamepad/HID Bridge (`tv-mpv-bridge.js`):** Intercepts WebKitGTK IPC messages, mocks Qt WebChannel transport, filters unsupported Web-MPV subtitle properties, maps 2.4GHz USB HID remotes / gamepads to spatial navigation keys, and routes playback requests directly to native hardware-accelerated `libmpv`.

---

## 2. Project Directory Structure
```text
stremio-bazzite-tv/
├── Stremio-android-tv/          # Unpacked Android TV APK bundle components
│   ├── base.apk
│   └── split_config.arm64_v8a.apk
├── Stremio-flatpak/             # Official Flatpak reference files
│   └── com.stremio.Stremio.flatpakref
├── flatpak-build/               # Flatpak manifest, launcher, and bridge scripts
│   ├── com.stremio.Stremio.json
│   ├── build-and-install.sh
│   ├── stremio-tv.sh
│   └── tv-mpv-bridge.js
├── patches/                     # Applied source and runtime patches
│   ├── stremio-tv.sh
│   └── tv-mpv-bridge.js
├── BAZZITE_TV_DEPLOYMENT_GUIDE.md
└── STREMIO_TV_FLATPAK_MASTER_GUIDE.md
```

---

## 3. Core Architectural Fixes & Implementations

### A. Qt WebChannel Mock for WebKitGTK Compatibility
* **Issue:** `stremio-linux-shell` uses WebKitGTK, but Stremio's web shell looks for Qt WebEngine (`window.qt.webChannelTransport`), throwing `TypeError: window.qt.webChannelTransport.onmessage is not a function`.
* **Solution:** Mock the transport and fallback handler inside `tv-mpv-bridge.js`:
  ```javascript
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
  ```

### B. MPV Property Setter Filtering (`sub-color` Fix)
* **Issue:** Stremio web UI attempts to configure subtitle styles via `mpv-set-prop` for properties like `sub-color`, `sub-back-color`, and `sub-border-color`. The native shell rejects these as `Invalid mpv-set-prop name`, triggering a fallback to the unsupported HTML5 video player block.
* **Solution:** Intercept and filter unsupported property names inside the `send` method of `tv-mpv-bridge.js`:
  ```javascript
  send(eventName, args) {
      if (eventName === 'mpv-set-prop') {
          const unsupportedProps = ['sub-color', 'sub-back-color', 'sub-border-color', 'sub-font-size'];
          if (args && unsupportedProps.includes(args.name)) {
              return; // Silently filter out web-only subtitle properties
          }
      }
      const payload = JSON.stringify({ type: 6, args: [eventName, args] });
      if (window.ipc && typeof window.ipc.postMessage === 'function') {
          window.ipc.postMessage(payload);
      } else if (window.webkit && window.webkit.messageHandlers && window.webkit.messageHandlers.ipc) {
          window.webkit.messageHandlers.ipc.postMessage(payload);
      }
  }
  ```

### C. 2.4GHz USB HID Remote Control Normalizer
* **Issue:** Standard USB air remotes (WeChip, G10/G20, Minix, Fire TV dongles) emit consumer media keys (`BrowserBack`, `MediaPlayPause`) that web apps do not map to spatial navigation.
* **Solution:** Global `keydown` capture listener in `tv-mpv-bridge.js` normalizes HID events into Stremio TV keyboard events (`Backspace`, `Enter`, `Space`, `e`, `h`, `Escape`).

---

## 4. Step-by-Step Build and Deployment Instructions (Bazzite / SteamOS)

1. **Clone or Pull the Repository on Bazzite:**
   ```bash
   cd ~/stremio-bazzite-tv
   git pull origin main
   ```

2. **Ensure `flatpak-builder` is Available:**
   If missing on the immutable host, install via Flathub user runtime:
   ```bash
   flatpak install -y flathub org.flatpak.Builder
   mkdir -p ~/.local/bin
   cat << 'EOF' > ~/.local/bin/flatpak-builder
   #!/usr/bin/env bash
   exec flatpak run org.flatpak.Builder "$@"
   EOF
   chmod +x ~/.local/bin/flatpak-builder
   export PATH="$HOME/.local/bin:$PATH"
   ```

3. **Run the Installer Script:**
   ```bash
   chmod +x build-and-install.sh flatpak-build/*.sh
   ./build-and-install.sh
   ```

4. **Launch in 10-Foot TV Mode:**
   ```bash
   flatpak run com.stremio.Stremio --url="https://tv.strem.io"
   ```