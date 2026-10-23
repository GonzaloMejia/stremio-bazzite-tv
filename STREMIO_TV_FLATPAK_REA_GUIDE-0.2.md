# Stremio Android TV to Linux Bazzite Flatpak Bridge Guide

## 1. Architectural Overview & Objective
This guide outlines the complete reverse-engineering and packaging workflow to bridge the Stremio 10-foot Android TV Web interface (`tv.strem.io`) into a native Linux Flatpak (`stremio-linux-shell`) running on Bazzite / SteamOS. 

Instead of running bytecode via Waydroid (which lacks Android TV Leanback framework and MediaCodec hardware acceleration bindings), this project packages:
* **The Application Shell:** `stremio-linux-shell` (Rust + GTK4 + WebKitGTK 6.0 + `libmpv`).
* **The UI Layer:** Stremio Theater Web UI (`tv.strem.io`).
* **The IPC & Gamepad Bridge (`tv-mpv-bridge.js`):** Intercepts WebKitGTK IPC messages, mocks Qt WebChannel transport, maps 2.4GHz USB HID remotes / gamepads to spatial navigation keys, and connects web player requests to native hardware-accelerated `libmpv`.

---

## 2. Project Directory Structure
```text
stremio-bazzite-tv/
├── Stremio-android-tv/          # Unpacked Android TV APK bundle components
│   ├── base.apk
│   ├── split_config.arm64_v8a.apk
│   └── ...
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
└── STREMIO_TV_FLATPAK_REA_GUIDE.md
```

---

## 3. Verification & Architectural Alignment

### 3.1 The Application Shell (`stremio-linux-shell`)
- **Manifest:** `flatpak-build/com.stremio.Stremio.json` compiles `libplacebo`, `libass`, `libmpv` (with VA-API & Wayland enabled), and `stremio-linux-shell` v1.2.2.
- **Node Runtime:** Bundles Node.js v22 to host `server.js` (local streaming server proxy).
- **Embedded Preload:** Injects `tv-mpv-bridge.js` directly into `src/app/ipc/preload.js` at compile time via `cat tv-mpv-bridge.js >> src/app/ipc/preload.js`.

### 3.2 The UI Layer (`tv.strem.io`)
- **Launcher:** `flatpak-build/stremio-tv.sh` forwards arguments and boots into:
  `http://127.0.0.1:11470/proxy/d=https%3A%2F%2Ftv.strem.io%2F` with `--no-window-decorations`.
- **Display Server Compatibility:** Enforces Wayland sockets, GSettings fullscreen state, and automatically exports `GSK_RENDERER=opengl` when Nvidia GPUs (`/dev/nvidia0`) are detected.

### 3.3 The IPC & Controller Bridge (`tv-mpv-bridge.js`)
- **Qt WebChannel Mock:** Defines `window.qt.webChannelTransport` with both `send` and fallback `onmessage` handlers to eliminate the `TypeError: window.qt.webChannelTransport.onmessage is not a function` error loop in WebKitGTK.
- **Native MPV Video Bridge:** Injects `shellTransport` on `window` and `Object.prototype`, allowing `selectVideoImplementation` in `tv.strem.io` to activate `ShellVideo` and route commands directly to GTK4 `libmpv`.
- **Viewport Transparency:** Dynamically injects CSS rules ensuring `html`, `body`, and `.player-container` stay transparent, exposing hardware-accelerated video underneath.
- **Gamepad Navigation:** 60 Hz polling loop for `navigator.getGamepads()` mapping Xbox / DualSense / Steam Deck buttons and analog sticks to spatial navigation keys with auto-repeat acceleration (350ms initial, 120ms interval).
- **2.4GHz USB HID Remote Control Normalizer:** Captures USB air remotes (G10, G20, WeChip, Minix, OSMC, Fire TV USB dongles) and maps `BrowserBack`, `MediaPlayPause`, `MediaTrackPrevious`, `MediaTrackNext`, and `ContextMenu` to Stremio TV keys (`Backspace`, `Space`, `KeyE`, `KeyH`, `Escape`).

---

## 4. Build, Installation & Deployment on Bazzite

```bash
# 1. Clone repository
git clone https://github.com/GonzaloMejia/stremio-bazzite-tv.git
cd stremio-bazzite-tv

# 2. Grant permissions
chmod +x build-and-install.sh flatpak-build/*.sh

# 3. Build & install
./build-and-install.sh
```