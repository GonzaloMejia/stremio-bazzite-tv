# Stremio Android TV to Linux Bazzite Flatpak — Master Deployment & REA Guide (v1.0)

## 1. Architectural Overview & Objective
This guide documents the complete reverse-engineering and packaging workflow to bridge the Stremio 10-foot Android TV Web interface (`tv.strem.io`) into a native Linux Flatpak (`stremio-linux-shell`) running on Bazzite / SteamOS.

Instead of running bytecode via Waydroid (which lacks the Android TV Leanback framework and MediaCodec hardware acceleration bindings), this project packages:
* **The Application Shell:** `stremio-linux-shell` (Rust + GTK4 + WebKitGTK 6.0 + `libmpv`) built with native VA-API & Wayland support.
* **The UI Layer:** Stremio Theater Web UI (`tv.strem.io`) loaded via local streaming proxy.
* **The IPC & Gamepad/HID Bridge (`tv-mpv-bridge.js`):** Intercepts WebKitGTK IPC messages, mocks Qt WebChannel transport, drops malformed Web-MPV property setters, maps 2.4GHz USB HID remotes / gamepads to spatial navigation keys, and routes playback requests directly to native hardware-accelerated `libmpv`.

---

## 2. Project Directory Structure
```text
stremio-bazzite-tv/
├── Stremio-android-tv/          # Unpacked Android TV APK bundle components (not committed)
│   ├── base.apk
│   └── split_config.arm64_v8a.apk
├── Stremio-flatpak/             # Official Flatpak reference files (not committed)
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

### B. MPV Property Setter Validation (`Invalid mpv-set-prop name` Fix)
* **Issue:** The shell logs `Invalid mpv-set-prop name` / `Invalid mpv-set-prop value`.
* **Root cause (verified against the shipped bundle and shell source):**
  * `ShellVideo` (module `78945` in `tv-stremio-video.chunk.js`) sends `mpv-set-prop` as an **array** `[name, value]`, not an object.
  * Its prop-name map has **no entry for `subtitlesOffset`**, so it sends `[undefined, value]`, which serializes to `[null, value]`. `src/app/ipc/request.rs` rejects that with `Invalid mpv-set-prop name`.
  * On every stream load it also sends `["no-sub-ass"]` with no value, which is rejected with `Invalid mpv-set-prop value`.
  * `sub-color`, `sub-back-color` and `sub-border-color` are **not** the problem. `stremio-linux-shell` v1.2.2 lists them in `STRING_PROPERTIES` (`src/app/video/config.rs`) and applies them, so filtering them would break subtitle color settings.
* **Solution:** Validate the payload shape inside `ShellTransport.send` and drop only the malformed calls:
  ```javascript
  if (eventName === 'mpv-set-prop') {
      const name = Array.isArray(args) ? args[0] : (args && args.name);
      const hasValue = Array.isArray(args) ? args.length >= 2 : (args && 'value' in args);
      if (typeof name !== 'string' || !hasValue) {
          return; // drop [null, value] (subtitlesOffset) and ["no-sub-ass"]
      }
      if (BLOCKED_SET_PROPS.includes(name)) {
          return; // opt-in blocklist, empty by default
      }
  }
  ```
* **Opt-in blocklist:** `BLOCKED_SET_PROPS` at the top of `tv-mpv-bridge.js` is empty by default. To reproduce the original subtitle-prop filtering, set it to `['sub-color', 'sub-back-color', 'sub-border-color', 'sub-font-size']`.
* **Known limitation:** Because `subtitlesOffset` has no mapping in ShellVideo, the subtitle vertical-offset setting in the TV UI does nothing under the native player.

### C. 2.4GHz USB HID Remote Control Normalizer
* **Issue:** Standard USB air remotes (WeChip, G10/G20, Minix, Fire TV dongles) emit consumer media keys (`BrowserBack`, `MediaPlayPause`) that web apps do not map to spatial navigation.
* **Solution:** Global `keydown` capture listener in `tv-mpv-bridge.js` normalizes HID events into Stremio TV keyboard events:

  | Remote key (`event.key`) | Normalized to |
  |---|---|
  | `Select` | `Enter` (13) |
  | `BrowserBack`, `GoBack`, `Back` | `Backspace` (8) |
  | `MediaPlayPause`, `MediaPlay`, `MediaPause` | `Space` (32) |
  | `MediaTrackPrevious`, `MediaRewind` | `e` (69), seek back |
  | `MediaTrackNext`, `MediaFastForward` | `h` (72), seek forward |
  | `ContextMenu` | `Escape` (27) |

---

## 4. Step-by-Step Build and Deployment Instructions (Bazzite / SteamOS)

1. **Clone or Pull the Repository on Bazzite:**
   ```bash
   git clone https://github.com/GonzaloMejia/stremio-bazzite-tv.git ~/stremio-bazzite-tv  # first time
   cd ~/stremio-bazzite-tv
   git pull origin main
   ```

2. **`flatpak-builder` availability (automatic):**
   `build-and-install.sh` uses the host `flatpak-builder` if present. Otherwise it installs and uses the Flathub-packaged `org.flatpak.Builder` (`flatpak run org.flatpak.Builder ...`). No manual step is needed.
   If you want a `flatpak-builder` command on PATH anyway:
   ```bash
   flatpak install --user -y flathub org.flatpak.Builder
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
   flatpak run com.stremio.Stremio                              # default: tv.strem.io via local proxy
   flatpak run com.stremio.Stremio --url="https://tv.strem.io"  # direct, overrides the default
   ```
