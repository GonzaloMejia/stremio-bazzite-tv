# Project Blueprint: Building a Native Couch-Friendly Stremio Flatpak for Bazzite Using REA

## 1. Executive Summary & Architectural Reality

### The Core Problem
The official Linux Flatpak for Stremio (`com.stremio.Stremio`) uses a desktop-oriented web interface (`stremio-web`) designed for mouse and keyboard navigation. Running the Android TV APK (`com.stremio.one`) inside Waydroid on Bazzite provides a great 10-foot UI, but introduces container overhead, HDMI-CEC/controller sleep quirks, and hardware decoding friction.

### Why Direct Binary Conversion Fails
**REA (`morluto/rea`)** is a Model Context Protocol (MCP) reverse-engineering orchestration framework, not a binary cross-compiler or Android translation runtime. You cannot directly "compile" an `.apk` into a Linux ELF Flatpak because:
1. **UI Layer:** The Android TV APK relies on Dalvik/ART bytecode (`classes.dex`) calling Google's `androidx.leanback` and Android `SurfaceFlinger` APIs, which do not exist natively on Linux (and are not supported by Android Translation Layer / ATL).
2. **Media Layer:** The APK uses Android's `Media3 ExoPlayer` + `MediaCodec`, whereas native Linux uses `libmpv` via VAAPI/Vulkan.

### Component Comparison Matrix

| Layer | Stremio Linux Flatpak (`com.stremio.Stremio`) | Stremio Android TV APK (`com.stremio.one`) | Target Hybrid Flatpak (Our Goal) |
| :--- | :--- | :--- | :--- |
| **Application Shell** | `stremio-linux-shell` (Rust + GTK4 + WebKitGTK 6.0) or Qt5 (`stremio-shell`) | Android ART Activity (`MainActivity` in Kotlin/Java) | `stremio-linux-shell` (Rust + GTK4 + WebKitGTK 6.0) |
| **UI / UX Layer** | `stremio-web` (Desktop Mouse/KB React SPA) | Android Jetpack Leanback XML + Custom Focus Views | **Path A:** Stremio Theater WebUI (`tv.strem.io`) + IPC Bridge<br>**Path B:** REA-Extracted Leanback UI ported to 10-foot Web/Tauri |
| **Core State Engine** | `stremio-core-web` (Rust compiled to WASM) | `libstremio_core.so` (Rust compiled to JNI `.so`) | `stremio-core` (WASM or native Rust crate) |
| **Streaming Server** | `server.js` running on Node.js | `server.js` running on embedded Node.js | `server.js` running on Node.js (Flatpak bundled) |
| **Video Player** | Native `libmpv` (Hardware Accelerated) | `ExoPlayer` / `libvlc` via Android `SurfaceView` | Native `libmpv` (Hardware Accelerated) |

---

## 2. Environment Setup: REA + Gemini CLI in Antigravity IDE (Bazzite)

Because **Bazzite** is an immutable Fedora Atomic (`ostree`) distribution, avoid layering reverse-engineering dependencies onto the host OS. Run your Antigravity IDE workspace and REA toolchains inside a **Distrobox** container (e.g., Fedora or Arch container) or install the tools via **Homebrew (`brew`)**.

### Step 2.1: Install Required Host/Container Dependencies
REA requires specific external binaries to decompile Android APKs and inspect native shared objects (`.so`).

```bash
# Inside your Distrobox (Fedora/Arch) or via Homebrew on Bazzite:
# 1. Ensure procps-ng is installed (REA requires a standard `ps` binary on Linux)
sudo dnf install -y procps-ng java-21-openjdk-devel flatpak-builder git jq unzip

# 2. Install apktool, jadx, radare2, and ghidra via Homebrew (works cleanly on Bazzite)
brew install apktool jadx radare2
```

### Step 2.2: Download the Pinned Headless JADX MCP JAR
REA requires the headless JADX MCP server JAR to expose decompiled DEX classes to Gemini CLI.

```bash
mkdir -p ~/.local/share/rea/jars
curl -L -o ~/.local/share/rea/jars/jadx-headless-mcp-0.7.1-all.jar \
  "https://github.com/morluto/jadx-headless-mcp/releases/download/v0.7.1/jadx-headless-mcp-0.7.1-all.jar"
```

### Step 2.3: Configure REA Environment Variables for Antigravity IDE
Add these environment variables to your shell profile (`~/.bashrc` or `~/.zshrc`) or directly inside your Antigravity IDE MCP configuration for `rea`.

> **CRITICAL:** Stremio's Android TV APK is a large multidex archive. You **must** increase `REA_JADX_OPERATION_TIMEOUT_MS` to `600000` (10 minutes), or the initial cold decompilation will time out at REA's default 120 seconds.

```bash
export REA_JADX_MCP_JAR="$HOME/.local/share/rea/jars/jadx-headless-mcp-0.7.1-all.jar"
export REA_JADX_OPERATION_TIMEOUT_MS=600000
export REA_GHIDRA_HEADLESS="/path/to/ghidra/support/analyzeHeadless" # Optional: only needed if inspecting libstremio_core.so
```

### Step 2.4: Workspace Directory Structure
Organize your Antigravity workspace as follows so Gemini CLI can cleanly access all artifacts:

```text
stremio-tv-flatpak/
├── STREMIO_TV_FLATPAK_REA_GUIDE.md   # This instruction file
├── artifacts/
│   ├── com.stremio.one.apk           # Place your Stremio Android TV APK here
│   └── flatpak-extracted/            # Extracted files from current Linux Flatpak
├── analysis/                         # Output directory for REA reverse-engineering reports
├── patches/                          # Custom JS/Rust patches for D-pad & Theater UI
└── flatpak-build/                    # Flatpak manifest (com.stremio.Stremio.json)
```

---

## 3. Pre-Flight Diagnostics (Run Before Starting)

Run these commands in your Antigravity terminal and record the outputs so Gemini CLI knows the exact versions in your workspace:

```bash
# 1. Check installed Stremio Flatpak architecture & commit
flatpak info com.stremio.Stremio

# 2. Copy the installed Flatpak runtime files into our workspace for inspection
mkdir -p ./artifacts/flatpak-extracted
cp -r /var/lib/flatpak/app/com.stremio.Stremio/current/active/files/* ./artifacts/flatpak-extracted/ 2>/dev/null || \
cp -r ~/.local/share/flatpak/app/com.stremio.Stremio/current/active/files/* ./artifacts/flatpak-extracted/

# 3. Verify APK presence and architecture
ls -lh ./artifacts/com.stremio.one.apk
unzip -l ./artifacts/com.stremio.one.apk | grep "lib/"
```

---

## 4. Execution Path A: The "Stremio Theater" Hybrid Flatpak (Fastest & Most Stable)

### Concept
Stremio maintains a 10-foot Smart TV WebUI (`https://tv.strem.io` / `stremio-theater`) built specifically for TV remotes and D-pads. However, pointing a regular browser at `tv.strem.io` lacks native `libmpv` hardware decoding and embedded `server.js` torrent streaming.
By using **REA** to analyze how `stremio-linux-shell` communicates with its WebView (`window.transport` / Qt WebChannel / WebKitGTK script message handlers), we can patch `stremio-linux-shell` to load the 10-foot TV UI while routing all video playback directly to native `libmpv`.

### Step 4.1: Agent Prompt for Gemini CLI (Path A)
Copy and paste this prompt into Gemini CLI inside Antigravity IDE:

```text
@STREMIO_TV_FLATPAK_REA_GUIDE.md
We are executing Path A.
1. Inspect the extracted Linux Flatpak files in `./artifacts/flatpak-extracted/`. Identify whether this build uses the Rust `stremio-linux-shell` (GTK4/WebKitGTK) or the legacy `stremio-shell` (Qt5 WebEngine).
2. Use REA's static/binary and JS analysis tools (`triage-binary` or `analyze-javascript-application`) on the shell binary and bundled web assets to document the exact IPC bridge between the WebView and native `mpv` (e.g., `qt.webChannelTransport` or `window.webkit.messageHandlers`).
3. Write a preload/injection script (`./patches/tv-mpv-bridge.js`) that maps the player events in `https://tv.strem.io` (or local Theater bundle) to the native `libmpv` IPC bridge used by the Linux shell, plus gamepad/remote keybindings (Escape/Backspace -> Back, Arrow keys -> Spatial Navigation).
4. Output the complete report to `./analysis/path-a-ipc-bridge.md`.
```

### Step 4.2: Cloning and Patching the Shell for Flatpak
Once Gemini CLI extracts the IPC specification, build the custom Flatpak:

```bash
# Clone the official Stremio Linux Shell repository into flatpak-build
git clone --recursive https://github.com/Stremio/stremio-linux-shell.git ./flatpak-build/stremio-linux-shell
```

Have Gemini CLI apply the following modifications to `./flatpak-build/stremio-linux-shell`:
1. **Default WebUI Override:** Change the default production WebUI URL constant in the Rust/C++ source (or wrapper launch script) to point to the 10-foot TV interface or bundled local Theater build.
2. **Fullscreen & Kiosk Mode:** Force fullscreen window state on startup so it behaves like a native console app in Bazzite Game Mode / Desktop Mode.
3. **Gamepad / Remote Input Hook:** Inject a lightweight Gamepad API poller into the WebView initialization so Xbox/PlayStation controllers on Bazzite translate `DPad` and `A`/`B` button presses into `ArrowUp`, `ArrowDown`, `ArrowLeft`, `ArrowRight`, `Enter`, and `Escape` DOM KeyboardEvents.

---

## 5. Execution Path B: Full APK Decompilation & UI Reconstruction via REA

### Concept
If you prefer the exact native Android TV Leanback layout, behavior, and OSD (On-Screen Display) from `com.stremio.one.apk` rather than the Smart TV web build, use **REA's Android reverse-engineering workflow (`reverse-android-app`)** to extract the APK's complete UI state machine, design tokens, and `libstremio_core.so` JNI bindings, and have Gemini CLI port those views into the Linux shell.

### Step 5.1: Phase 1 — APK Triage & Manifest Extraction (Prompt for Gemini CLI)

```text
@STREMIO_TV_FLATPAK_REA_GUIDE.md
We are executing Path B (Phase 1: APK Triage).
Use the REA MCP server (`reverse-android-app` workflow, `apktool`, and `jadx` tools) on `./artifacts/com.stremio.one.apk`:
1. Decode the AndroidManifest.xml and resources (`res/layout`, `res/values`, `res/drawable`).
2. Identify the main Android TV Leanback entry activities and fragments (look for `TvActivity`, `MainActivity`, BrowseSupportFragment, DetailsSupportFragment, and PlayerActivity).
3. Map out how the Kotlin/Java UI layer communicates with `libstremio_core.so` via JNI. List every JNI bridge class and native method signature.
4. Save your findings to `./analysis/apk-architecture-map.md`.
```

### Step 5.2: Phase 2 — Extracting the 10-Foot Player OSD & Focus Graph (Prompt for Gemini CLI)

```text
@STREMIO_TV_FLATPAK_REA_GUIDE.md
Reference `./analysis/apk-architecture-map.md`.
Using REA's JADX decompiler tools (`get-class-source`, `search-method`, `get-method-decompiled`) on `./artifacts/com.stremio.one.apk`:
1. Decompile the Android TV Video Player Activity/Fragment, Subtitle Selector Dialog, Audio Track Dialog, and Stream/Addon Picker sidebar.
2. Extract the exact D-pad focus transition rules (`nextFocusUp`, `nextFocusDown`, `nextFocusLeft`, `nextFocusRight`, and keycode handlers for `KEYCODE_DPAD_CENTER`, `KEYCODE_BACK`, `KEYCODE_MEDIA_PLAY_PAUSE`).
3. Extract all XML dimensions, color palettes, typography scales, and SVG/VectorDrawable icons from the decoded APK resources.
4. Document the complete UI/UX specification in `./analysis/android-tv-ui-spec.md`.
```

### Step 5.3: Phase 3 — Rebuilding the Extracted Android TV UI for the Linux Flatpak (Prompt for Gemini CLI)

```text
@STREMIO_TV_FLATPAK_REA_GUIDE.md
Using `./analysis/apk-architecture-map.md` and `./analysis/android-tv-ui-spec.md`:
We do NOT need to reverse-engineer `libstremio_core.so` or `ExoPlayer` because our Linux Flatpak already provides `stremio-core` and hardware-accelerated `libmpv`.
1. Generate a 10-foot couch UI frontend that replicates the exact layout, focus navigation, and Player OSD of the Android TV APK (`com.stremio.one.apk`), wired directly to the Linux shell's `libmpv` IPC bridge and `stremio-core` state models.
2. Ensure full support for both Keyboard/2.4GHz USB Remotes (Arrow keys, Enter, Backspace/Escape) and Standard Gamepads (via `navigator.getGamepads()`).
3. Package the build output so it drops directly into the Flatpak web asset directory (`./flatpak-build/`).
```

---

## 6. Building & Installing the Custom Flatpak on Bazzite

Once Gemini CLI has generated your patched shell or rebuilt 10-foot UI in `./flatpak-build`, compile and install it locally on Bazzite using `flatpak-builder`.

### Step 6.1: Ensure Flatpak SDKs are Installed
```bash
flatpak remote-add --user --if-not-exists flathub https://dl.flathub.org/repo/flathub.flatpakrepo
# Install the Freedesktop/GNOME SDK matching the Stremio manifest
flatpak install --user flathub org.gnome.Platform//46 org.gnome.Sdk//46 org.freedesktop.Sdk.Extension.rust-stable//23.08 org.freedesktop.Sdk.Extension.node20//23.08
```

### Step 6.2: Build and Install the Flatpak Locally
```bash
cd ./flatpak-build
flatpak-builder --user --install --force-clean build-dir com.stremio.Stremio.json
```

### Step 6.3: Add to Bazzite Steam Game Mode (Living Room Couch Setup)
To launch your custom Stremio Flatpak directly from Bazzite's Steam Big Picture / Game Mode interface with full controller support:

1. Switch to Desktop Mode or run via SSH:
   ```bash
   steamos-add-to-steam /var/lib/flatpak/exports/share/applications/com.stremio.Stremio.desktop || \
   steamos-add-to-steam ~/.local/share/flatpak/exports/share/applications/com.stremio.Stremio.desktop
   ```
2. In Steam Game Mode, open **Stremio -> Gear Icon (Properties) -> Controller**:
   - Set the controller template to **Gamepad with Directional Pad** (if using the built-in `navigator.getGamepads()` hook) OR
   - Map **D-Pad -> Arrow Keys**, **A Button -> Enter**, **B Button -> Escape / Backspace**, **X Button -> Space (Play/Pause)**, and **Y Button -> V (Subtitles)**.

---

## 7. Troubleshooting Checklist for Gemini CLI + REA

- **JADX MCP Timeout on First Run:** If REA returns a timeout error when inspecting `com.stremio.one.apk`, verify `echo $REA_JADX_OPERATION_TIMEOUT_MS` returns `600000` in the same shell that launched Antigravity IDE.
- **Hardware Acceleration (`libmpv`) Inside Flatpak:** Ensure the Flatpak manifest (`com.stremio.Stremio.json`) retains `--device=dri` in `finish-args` so Bazzite's Mesa VAAPI/Vulkan drivers pass through to `libmpv`.
- **Wayland Window Scaling on 4K TVs:** If text looks blurry on a 4K TV under Bazzite Game Mode (`gamescope`), add `--socket=wayland` to the Flatpak `finish-args` or launch Stremio via `gamescope -w 3840 -h 2160 -f -- flatpak run com.stremio.Stremio`.