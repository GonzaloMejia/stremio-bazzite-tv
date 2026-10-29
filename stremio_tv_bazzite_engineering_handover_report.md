# Technical Engineering Handover & Post-Mortem Report

**Project:** Stremio 10-Foot Native TV Port for Bazzite / SteamOS (`com.stremio.Stremio`)  
**Repository / Workspace:** `~/stremio-bazzite-tv`  
**Target Platform:** Bazzite Linux (Wayland / Gamescope / Steam Game Mode & KDE Plasma Desktop)  
**Date:** October 29, 2026  

---

## 1. Executive Summary & Core Objective

**Mission Statement:**  
> Recreate the most exact UI/UX clone/port of the Android TV version of the Stremio app, developed natively for Bazzite as a Flatpak, that runs smoothly for a user from a couch with a joypad controller or a Smart TV remote control, always updating everything relevant on GitHub.

### Current Blocker Summary
The application compiles, installs as a Flatpak, registers with Steam Game Mode, and launches the Node.js streaming server (`server.js`) and the Rust WebKitGTK shell (`stremio-linux-shell` v1.2.2). However, when pointing the shell at the Stremio TV Web UI (`https://tv.strem.io`), the UI hangs indefinitely on the initial Stremio splash logo. The terminal logs consistently output:

```text
ERROR stremio_linux_shell::app::webview: Failed to send message: TypeError: undefined is not a function
```

Additionally, during the final step of the previous troubleshooting session, a faulty Python file-overwrite script accidentally wiped the core logic of `flatpak-build/tv-mpv-bridge.js` and `patches/tv-mpv-bridge.js`, replacing the entire ~12.7 KB MPV/IPC bridge with a 20-line mock stub.

---

## 2. System Architecture & Build Pipeline

### 2.1 Component Stack
* **Host Environment:** Bazzite Linux (`x86_64`), AMD GPU (`radeonsi` VAAPI configured).
* **Flatpak Runtime:** `org.gnome.Platform` / `org.gnome.Sdk` version `50`, with `org.freedesktop.Sdk.Extension.rust-stable` and `org.freedesktop.Platform.codecs-extra` (`25.08-extra`).
* **Application Shell:** `stremio-linux-shell` (`v1.2.2` tag from upstream GitHub), built offline via Cargo. Uses **WebKitGTK** for the webview and C-bindings to **libmpv** (`v0.41.0` built with `libplacebo v7.360.1` and `libass 0.17.4`) for video rendering.
* **Streaming Backend:** Node.js (`v22.15.0`) executing `/app/libexec/stremio/server.js`.
* **Entrypoint Wrapper:** `stremio-tv.sh` installed to `/app/bin/stremio`.
* **Bridge Layer:** `tv-mpv-bridge.js`, which is appended to `src/app/ipc/preload.js` at compile time (`cat tv-mpv-bridge.js >> src/app/ipc/preload.js`) before `cargo build --release` embeds `preload.js` into the Rust binary via `include_str!()`.

### 2.2 Workspace Directory Structure
```text
~/stremio-bazzite-tv/
├── analysis/                          # Architecture notes & upstream comparisons
├── flatpak-build/
│   ├── build-and-install.sh           # Core flatpak-builder execution & Steam registration
│   ├── cargo-sources.json             # Offline Rust crate dependencies
│   ├── com.stremio.Stremio.json       # Flatpak manifest
│   ├── com.stremio.Stremio.metainfo.xml
│   ├── stremio-tv.sh                  # Launch wrapper inside the sandbox
│   └── tv-mpv-bridge.js               # Preload injection script (CURRENTLY WIPED - NEEDS RESTORE)
├── patches/
│   └── tv-mpv-bridge.js               # Backup/patch source of bridge script (CURRENTLY WIPED - NEEDS RESTORE)
├── BAZZITE_TV_DEPLOYMENT_GUIDE.md
├── STREMIO_TV_FLATPAK_REA_GUIDE.md
├── STREMIO_TV_FLATPAK_REA_GUIDE-0.2.md
├── TV_BRIDGE_FIX_GUIDE.md
└── build-and-install.sh               # Root wrapper calling flatpak-build/build-and-install.sh
```

---

## 3. Root Cause Analysis (Why the Previous Session Failed)

The previous debugging session fell into a counterproductive loop due to four compounding technical errors:

### 3.1 Misinterpreting the Direction of the IPC Failure
The error `ERROR stremio_linux_shell::app::webview: Failed to send message: TypeError: undefined is not a function` is logged by the **Rust binary's `webview.rs` module**. 
* **How `stremio-linux-shell` works:** In `src/app/ipc/preload.js`, the upstream shell initializes the transport layer between Rust and JavaScript. When the Rust shell wants to send an event *down* to the webview (such as `app-ready`, window state changes, or `mpv` property updates), Rust calls `webview.evaluate_javascript(...)` to invoke a specific global callback function in the JS context (typically `window.chrome.webview` / `window.__stremio_ipc_callback` or a handler passed into `window.ipc`).
* **Why it failed on `https://tv.strem.io`:** The standard desktop web app (`web.stremio.com`) initializes the shell handshake in a specific way that `stremio-linux-shell` expects. The 10-foot TV UI (`tv.strem.io`), or the custom `tv-mpv-bridge.js` appended to `preload.js`, either overwrote the object that `preload.js` set up, or failed to register the callback function (`onMessage` / `transport.on`) before sending `win-ready` / `app-ready` to Rust. When Rust immediately replied via `evaluate_javascript`, the target JS function was `undefined`, throwing `TypeError: undefined is not a function` inside WebKit and halting the startup handshake. Because the handshake never completed, the splash screen logo never dismissed.

### 3.2 Blindly Mocking Host-to-Client Calls as Client-to-Host Shims
The previous session repeatedly injected shims for `window.ipc.postMessage` and `window.webkit.messageHandlers.ipc.postMessage`. Those functions handle **Client-to-Host** (JS $\rightarrow$ Rust) communication. Mocking them at the top of `tv-mpv-bridge.js` did nothing to fix **Host-to-Client** (Rust $\rightarrow$ JS) evaluation failures, and in fact risked shadowing the real WebKitGTK `window.webkit.messageHandlers.ipc` object registered natively by `stremio-linux-shell`.

### 3.3 Bash String-Interpolation Corruption (`!window` History Expansion)
When running `python3 -c "..."` inside double quotes in an interactive Bash shell (`Konsole`), Bash intercepted the `!window` negation operator as **Bash history expansion** before Python ever saw the string. Specifically:
```javascript
if (!window.webkit.messageHandlers.ipc) {
```
was mangled by Bash into:
```javascript
if (window.webkit.messageHandlers.ipc = window.webkit.messageHandlers.ipc || {) {
```
This injected a fatal JavaScript `SyntaxError` directly into `preload.js`. Because WebKitGTK aborts executing the entire preload script upon hitting a `SyntaxError`, *none* of the preload or bridge code was running at all during the later tests.

### 3.4 Destructive File Overwrite at the End of the Session
In the final step of the previous session, `open(path, 'w')` was executed in Python with only a 20-line stub (`pristine_code`), completely erasing the ~12,712-byte implementation in both `flatpak-build/tv-mpv-bridge.js` and `patches/tv-mpv-bridge.js`.

---

## 4. Immediate Recovery & Diagnostic Steps for the Engineering Team

> **CRITICAL WARNING:** Do **NOT** run `./build-and-install.sh` before completing Step 1, or `flatpak-builder` may overwrite any cached build directories containing the intact `preload.js` / `tv-mpv-bridge.js` code.

### Step 1: Restore `tv-mpv-bridge.js` from Git or Build Cache
First, check Git status and restore the wiped files from the repository:
```bash
cd ~/stremio-bazzite-tv
git status
git diff
git checkout -- flatpak-build/tv-mpv-bridge.js patches/tv-mpv-bridge.js
```
If uncommitted changes existed in `tv-mpv-bridge.js` prior to the session that were not yet committed to Git, check the `.flatpak-builder` cache or `/app/libexec/stremio/tv-mpv-bridge.js` inside the currently installed Flatpak OSTree repo before rebuilding:
```bash
# Check cached flatpak build directories
find ~/stremio-bazzite-tv/flatpak-build/.flatpak-builder/build/ -name "preload.js" -o -name "tv-mpv-bridge.js"

# Check the existing Flatpak build-dir staging folder
ls -la ~/stremio-bazzite-tv/flatpak-build/build-dir/files/libexec/stremio/
```

### Step 2: Inspect the Actual Upstream `stremio-linux-shell` v1.2.2 Source
Instead of guessing what `stremio-linux-shell` expects, inspect the exact Rust source code checked out by `flatpak-builder`:
```bash
find ~/stremio-bazzite-tv/flatpak-build/.flatpak-builder/build/ -path "*/src/app/*" \( -name "*.rs" -o -name "preload.js" \)
```
Specifically, examine:
1. `src/app/webview.rs` (around the exact log macro `error!("Failed to send message: {}", ...)`). See what JavaScript string `evaluate_javascript` is executing when `send_message` is called.
2. `src/app/ipc/preload.js` (the original lines before `tv-mpv-bridge.js` is concatenated). See how `window.ipc` is constructed and what variable holds the callback function that Rust invokes.

---

## 5. Architectural Roadmap to Achieve the Goal

To deliver an authentic Android TV Stremio clone on Bazzite with joypad and remote support, the engineering team must verify and complete four distinct subsystems:

### 5.1 Subsystem A: The WebKitGTK $\leftrightarrow$ Rust IPC Handshake & MPV Bridge
1. **Contract Alignment:** Inspect `src/app/webview.rs` and `src/app/ipc/preload.js` in `stremio-linux-shell v1.2.2`. Identify the exact function signature Rust calls to send messages to the webview.
2. **Preload Scope Preservation:** Ensure `tv-mpv-bridge.js` (which gets appended via `cat tv-mpv-bridge.js >> src/app/ipc/preload.js`) wraps its internal variables in an IIFE `(function() { ... })();` so it never collides with lexical `const`/`let` declarations in `preload.js`, while properly attaching the listener to the existing `ipc` object rather than redefining `window.ipc`.
3. **Handshake Spoofing for `tv.strem.io`:** Determine why `https://tv.strem.io` hangs on the splash screen:
   * Does `tv.strem.io` wait for a specific Tizen/WebOS/Android TV native bridge event, or standard Stremio transport `app-ready` (`["app-ready", {...}]`)?
   * Ensure `tv-mpv-bridge.js` intercepts the TV frontend's player commands (play, pause, seek, audio/subtitle track selection, timeupdate events) and translates them cleanly into the exact JSON array protocol `[id, type, args]` expected by `stremio-linux-shell`'s `libmpv` wrapper.

### 5.2 Subsystem B: Hardware-Accelerated Rendering (`libmpv` + Wayland/Gamescope)
1. **GL/EGL Context Sharing:** In `stremio-linux-shell`, `libmpv` renders into an OpenGL framebuffer underneath a transparent WebKitGTK webview. Verify that when `tv.strem.io` transitions into the player view, the HTML/CSS background of the webview becomes strictly `background: transparent !important` so the underlying `libmpv` video plane is visible while the 10-foot OSD controls remain rendered on top.
2. **VAAPI Verification:** In `com.stremio.Stremio.json`, `--env=MPV_HA=no` is currently set alongside `--env=LIBVA_DRIVER_NAME=radeonsi`. Audit why `MPV_HA=no` is present—if it disables hardware acceleration in `stremio-linux-shell`, test toggling it or configuring `hwdec=vaapi` via the bridge's `mpv-set-prop` initialization calls so 4K HEVC/AV1 streams decode on the AMD GPU without dropping frames.

### 5.3 Subsystem C: 10-Foot Input Handling (Joypad & 2.4GHz Smart TV Remote)
1. **Spatial Navigation in `tv.strem.io`:** `tv.strem.io` is built natively for D-Pad navigation (`ArrowUp`, `ArrowDown`, `ArrowLeft`, `ArrowRight`, `Enter`, `Escape` / `Backspace`).
2. **2.4GHz USB Dongle Remote Control:** Most 2.4GHz RF remotes enumerate on Linux as a composite `evdev` HID keyboard/consumer-control device:
   * Verify keycodes emitted by the remote's **Back/Return** button (often `KEY_BACK`, `KEY_ESC`, or `XF86Back` / `BrowserBack`).
   * In `tv-mpv-bridge.js`, attach a capture-phase `keydown` listener that normalizes `XF86Back`, `BrowserBack`, `GoBack`, and right-click context events (if the remote has an air-mouse toggle) into the exact `Escape` / `Backspace` key event that `tv.strem.io` expects to navigate back a screen without exiting the app.
   * Map media keys (`MediaPlayPause`, `MediaStop`, `AudioVolumeUp`, `AudioVolumeDown`) directly to player bridge actions.
3. **Joypad Controller (Gamepad API $\rightarrow$ KeyEvents):**
   * Verify whether `tv.strem.io` natively polls `navigator.getGamepads()` or if Steam Input in Game Mode translates the controller into keyboard arrows.
   * Build a lightweight `requestAnimationFrame` Gamepad poller inside `tv-mpv-bridge.js` (with proper deadzone and repeat-delay debouncing) that translates standard W3C Gamepad buttons:
     * D-Pad / Left Stick $\rightarrow$ `ArrowUp` / `ArrowDown` / `ArrowLeft` / `ArrowRight`
     * South Button (`A` / Cross) $\rightarrow$ `Enter`
     * East Button (`B` / Circle) $\rightarrow$ `Escape` / `Backspace` (Back)
     * Shoulder Buttons (`LB`/`RB`) $\rightarrow$ Seek `-10s` / `+10s` during playback
     * `Start` / `Y` $\rightarrow$ Play/Pause or Subtitles menu
   * **Anti-Double-Input Guard:** Ensure that if Steam Input is *already* mapping the gamepad to keyboard events, the JS Gamepad poller detects simultaneous keyboard + gamepad triggers and prevents double-stepping through menus.

### 5.4 Subsystem D: Steam Game Mode & Lifecycle Integration
1. **Window Fullscreen & Resolution:** Under Gamescope (Steam Game Mode), ensure the GTK4 window created by `stremio-linux-shell` requests fullscreen immediately or adapts cleanly to the Gamescope virtual display resolution without headerbars.
2. **Clean Process Termination:** Currently, exiting or killing the Flatpak leaves orphaned `node /app/libexec/stremio/server.js` processes or causes `Broken pipe` socket errors. Update `stremio-tv.sh` with a `trap "kill 0" EXIT SIGTERM SIGINT` handler so that closing the window from Steam Game Mode cleanly terminates both `stremio-linux-shell` and the background `node` server.

---

## 6. Recommended Test Suite & Verification Plan

Engineers should execute the following tests in order:

1. **Test 1: Preload & WebInspector Diagnostic Test**
   * Enable the WebKitGTK Inspector inside the Flatpak build by launching with:
     ```bash
     WEBKIT_INSPECTOR_HTTP_SERVER=127.0.0.1:9222 flatpak run com.stremio.Stremio --url="https://tv.strem.io"
     ```
   * Open `http://127.0.0.1:9222` in a desktop browser to inspect the real-time JS console, network requests, and DOM inside the WebKitGTK webview. Verify zero `SyntaxError` or `TypeError` logs on startup.
2. **Test 2: IPC Handshake & Local Server Connectivity**
   * Verify in the WebKit console that `tv.strem.io` successfully connects to `http://127.0.0.1:11470` (the local `server.js` instance) and loads user addons and library metadata.
3. **Test 3: Native `libmpv` Playback & OSD Overlay**
   * Play a 4K HDR/HEVC stream (e.g., via Real-Debrid / AIOStreams).
   * Confirm `libmpv` renders the video hardware-accelerated (check GPU engine load via `nvtop` or `amdgpu_top`), audio outputs via PipeWire, subtitles render via `libass`, and the 10-foot UI overlay remains visible and responsive over the video.
4. **Test 4: Input Matrix Test (Desktop Mode vs. Steam Game Mode)**
   * Test full navigation (Home $\rightarrow$ Discover $\rightarrow$ Select Title $\rightarrow$ Select Stream $\rightarrow$ Play $\rightarrow$ Seek $\rightarrow$ Change Subtitles $\rightarrow$ Back to Home) using:
     1. Keyboard only.
     2. 2.4GHz USB Dongle Smart TV Remote (specifically testing the Back, OK, and directional buttons).
     3. Bluetooth/USB Joypad Controller inside **Steam Game Mode** (Gamescope).
5. **Test 5: Git & Reproducibility Audit**
   * Run `git status` and `git diff` to ensure `patches/tv-mpv-bridge.js` and `flatpak-build/tv-mpv-bridge.js` are identical, clean, well-commented, and pushed to the remote GitHub repository.

---

## 7. Definition of Done (DoD)

The project is considered **Done** only when all of the following criteria are met:

- [ ] **Zero IPC Errors:** Launching `flatpak run com.stremio.Stremio` produces zero `stremio_linux_shell::app::webview: Failed to send message` errors in stdout/stderr.
- [ ] **Boot to 10-Foot UI:** The app transitions past the Stremio splash logo into the `tv.strem.io` home screen within < 3 seconds on Bazzite.
- [ ] **Native `libmpv` Playback:** Streams play smoothly using the embedded `libmpv` engine (not fallback HTML5 `<video>`, avoiding codec limitations), with full support for embedded MKV subtitles, multi-audio tracks, and hardware decoding.
- [ ] **Couch Controller & Remote Parity:** Every screen, modal, player OSD control, and "Back" action can be navigated seamlessly from the couch using both a gamepad controller and the 2.4GHz USB remote control without ever needing a mouse or keyboard.
- [ ] **Steam Game Mode Native Feel:** Launches directly from the Steam Game Mode library grid with proper artwork, runs fullscreen under Gamescope, and exits cleanly back to Steam when closed without hanging processes.
- [ ] **GitHub Synchronization:** All scripts (`flatpak-build/tv-mpv-bridge.js`, `patches/tv-mpv-bridge.js`, `com.stremio.Stremio.json`, `stremio-tv.sh`) and documentation are committed with clean commit history and pushed to GitHub.

---

## 8. Questions for the Project Owner (Information for Engineers to Confirm)

To ensure the engineering team doesn't make wrong assumptions, please confirm the following when handing this off:

1. **Git State of `tv-mpv-bridge.js`:** Was the working version of `tv-mpv-bridge.js` committed to Git prior to today's troubleshooting session, or do we need to extract the pre-wipe copy from `flatpak-build/.flatpak-builder/build/` before running another build?
2. **UI Source Preference:** Are you strictly targeting the hosted `https://tv.strem.io` web app via `--url="https://tv.strem.io"`, or do you want the assets bundled offline inside the Flatpak so the UI loads even if `tv.strem.io` is unreachable?
3. **Remote Control Keycodes:** For your custom 2.4GHz USB remote control, are there any specific buttons (e.g., Back, Home, Menu, Info, or Color buttons) that currently fail to register or trigger unwanted browser actions (like right-click context menus)?
4. **Authentication Persistence:** Is your Stremio account login (`localStorage` / IndexedDB inside WebKitGTK) currently persisting across Flatpak restarts, or does the Flatpak sandbox need an explicit `--persist=` path added for WebKit's `~/.var/app/com.stremio.Stremio/data` / `cache` directories?