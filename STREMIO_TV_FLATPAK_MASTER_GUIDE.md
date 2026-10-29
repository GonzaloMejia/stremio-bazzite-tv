# Stremio Android TV to Linux Bazzite Flatpak — Master Deployment & REA Guide (v2.0)

## 1. Architectural Overview & Objective
This guide documents the complete reverse-engineering and packaging workflow to bridge the Stremio 10-foot Smart TV Web interface (`tv.strem.io`) into a native Linux Flatpak (`stremio-linux-shell`) running on Bazzite / SteamOS.

Instead of running bytecode via Waydroid (which lacks the Android TV Leanback framework and MediaCodec hardware acceleration bindings), this project packages:
* **The Application Shell:** `stremio-linux-shell` v1.2.2 (Rust + GTK4 + WebKitGTK 6.0 + `libmpv`) built with native VA-API (`radeonsi` on AMD GPUs) & Wayland support.
* **The Offline UI Layer (`tv-ui-server.js` + `stremio-tv-web/`):** All 40 static assets of `tv.strem.io` are bundled inside `/app/share/stremio-tv-web` and served from `http://127.0.0.1:11471/` with an online read-through cache and atomic disk-backed `localStorage` persistence (`$XDG_DATA_HOME/stremio-tv-storage.json`).
* **The Two-Way IPC, M5 BT Remote & Gamepad Bridge (`tv-mpv-bridge.js`):** Handles the bidirectional `window.qt.webChannelTransport.onmessage` protocol with `src/app/ipc/preload.js`, mounts `ShellTransport` via a scoped `Object.prototype.shellTransport` getter/setter, suppresses `tv.strem.io`'s duplicate internal gamepad poller, provides a 60Hz `COOLDOWN_MS = 180` Gamepad poller, and maps the **M5 BT English (2.4GHz + BT 5.0 + 6-Axis Air-Mouse + Rear Mini QWERTY Keyboard)** remote control directly to `tv.strem.io` spatial navigation and `libmpv` volume controls.

---

## 2. Project Directory Structure
```text
stremio-bazzite-tv/
├── flatpak-build/               # Flatpak manifest, offline assets, launcher, and bridge scripts
│   ├── com.stremio.Stremio.json # Flatpak Builder manifest with offline UI + persistent dirs
│   ├── build-and-install.sh     # Pre-build backup extractor + offline bundler + installer
│   ├── bundle-tv-web.py         # Recursive tv.strem.io asset downloader
│   ├── bundle-tv-web.sh         # Shell wrapper for offline asset bundling
│   ├── stremio-tv-web/          # Bundled offline tv.strem.io assets (40 files)
│   ├── stremio-tv.sh            # Main Flatpak entrypoint (server.js + tv-ui-server.js + shell)
│   ├── tv-ui-server.js          # Local offline HTTP server + localStorage disk persistence
│   └── tv-mpv-bridge.js         # Two-way IPC, libmpv bridge, Gamepad & M5 BT remote driver
├── patches/                     # Reference copies of runtime patches
│   ├── stremio-tv.sh
│   ├── tv-ui-server.js
│   └── tv-mpv-bridge.js
├── BAZZITE_TV_DEPLOYMENT_GUIDE.md
├── STREMIO_TV_FLATPAK_MASTER_GUIDE.md
├── TV_BRIDGE_FIX_GUIDE.md
└── stremio_tv_bazzite_engineering_handover_report.md
```

---

## 3. Core Architectural Fixes & Implementations

### A. Two-Way `window.qt.webChannelTransport` IPC Bridge
* **Root Cause:** `stremio-linux-shell`'s `src/app/ipc/preload.js` defines `window.qt = { webChannelTransport: { send: ... } }` and forwards every Rust `libmpv` event (`mpv-prop-change`, `mpv-event-ended`, `visibility-change`) by calling `window.qt.webChannelTransport.onmessage({ data: message })`. Previously, defining a no-op `onmessage: function() {}` silently dropped all incoming `libmpv` playback telemetry.
* **Fix:** `tv-mpv-bridge.js` hooks `window.qt.webChannelTransport.onmessage`, parses the JSON IPC messages from Rust (`type === 1`), and routes `args` (`["mpv-prop-change", propData]`) to `ShellTransport.emit()`. On startup, it performs the QWebChannel handshake (`{ type: 3, id: 0 }`), sends `app-ready` (`["", "1.2.2"]`), and forces fullscreen (`win-set-visibility: [{ fullscreen: true }]`).

### B. Offline Bundled `tv.strem.io` UI + Indestructible Login Persistence
* **Root Cause of Splash Screen Hang:** Loading `tv.strem.io` via `http://127.0.0.1:11470/proxy/d=https%3A%2F%2Ftv.strem.io%2F` broke Webpack's relative publicPath calculation (`f.p = e + "../../"` in `runtime.js`), causing `core.worker.chunk.js` and WASM assets to 404 against the root of `127.0.0.1:11470`.
* **Fix:** `tv-ui-server.js` runs on `http://127.0.0.1:11471` and serves `/app/share/stremio-tv-web` at root (`/`), keeping all relative chunk and Web Worker paths intact offline.
* **Android TV-Style Login Persistence:** Both Flatpak sandbox persistence flags (`--persist=.stremio-server`, `--persist=.local`, `--persist=.cache`, `--persist=.config`) and an automatic `localStorage` file mirror (`$XDG_DATA_HOME/stremio-tv-storage.json`) ensure the user's Stremio account session (`profile`) and addons survive restarts and offline boots indefinitely.

### C. M5 BT English Remote Control & DualSense/Xbox Gamepad Support
* **Gamepad Deduplication & Cooldown:** Intercepts `window.addEventListener('gamepadconnected')` to disable `tv.strem.io`'s built-in 100ms gamepad poller (module `60715`) and replaces it with a single 60Hz `requestAnimationFrame` poller with `COOLDOWN_MS = 180` and accurate `tv.strem.io` keyCodes (`412` Rewind, `417` Fast-Forward, `32` Play/Pause, `27` Back, `13` Enter).
* **M5 BT Remote Control Support:**
  * **Air-Mouse Right-Click (`contextmenu`):** Suppressed browser menu and mapped to Back (`Escape`, `keyCode: 27`).
  * **Air-Mouse Left-Click on `<input>`:** Explicitly focuses `<input>` / `<textarea>` elements for typing.
  * **Rear Mini QWERTY Keyboard Protection:** Prevents `tv.strem.io` from hijacking letter keys (`e`, `g`, `h`, `j`, `x`) and `Backspace` as navigation shortcuts while an `<input>` is focused.
  * **Hardware Volume Keys (`AudioVolumeUp` / `AudioVolumeDown` / `AudioVolumeMute`):** Adjust `libmpv` volume directly (`mpv-set-prop` `volume` / `mute`).

---

## 4. Step-by-Step Build and Deployment Instructions (Bazzite / SteamOS)

1. **Pull the Latest Changes on Bazzite:**
   ```bash
   cd ~/stremio-bazzite-tv
   git pull origin main
   ```

2. **Build and Install the Flatpak:**
   ```bash
   cd ~/stremio-bazzite-tv/flatpak-build
   chmod +x *.sh
   ./build-and-install.sh
   ```

3. **Launch in 10-Foot TV Mode:**
   ```bash
   flatpak run com.stremio.Stremio
   ```
