# Stremio TV on Bazzite & SteamOS (10-Foot Couch Mode)

[![Flatpak](https://img.shields.io/badge/Flatpak-com.stremio.Stremio-blue.svg)](https://flatpak.org)
[![Platform](https://img.shields.io/badge/Platform-Bazzite%20%7C%20SteamOS-orange.svg)](https://bazzite.gg)
[![UI](https://img.shields.io/badge/Interface-10--Foot%20TV%20Couch-purple.svg)](https://tv.strem.io)
[![Player](https://img.shields.io/badge/Video%20Engine-Hardware%20libmpv%20(VAAPI%2FVulkan)-green.svg)](https://mpv.io)
[![Input](https://img.shields.io/badge/Input-Gamepad%20Spatial%20Navigation-red.svg)](https://w3c.github.io/gamepad/)

A custom, native Flatpak build of **Stremio** specifically architected for **Bazzite TV boxes**, **Steam Deck**, and **SteamOS / Gamescope** living room setups.

It bridges the leanback, 10-foot couch interface of **Stremio Android TV / Web** with the desktop performance of **native hardware-accelerated `libmpv`** (VA-API / Vulkan) and a **60Hz spatial navigation gamepad bridge**.

---

## Architecture Overview

```
                      +------------------------------------------+
                      |           Bazzite TV Box                 |
                      |   (Steam Game Mode / Gamescope Wayland)  |
                      +------------------------------------------+
                                           |
                    +----------------------------------------------+
                    |  com.stremio.Stremio Flatpak (TV Edition)    |
                    |                                              |
                    |  +----------------------------------------+  |
                    |  |   stremio-linux-shell (Rust + GTK4)    |  |
                    |  |  +----------------------------------+  |  |
                    |  |  |  WebKitGTK 6.0 Web Overlay       |  |  |
                    |  |  |  - Loads tv.strem.io (10-ft UI)  |  |  |
                    |  |  |  - Injected tv-mpv-bridge.js     |  |  |
                    |  |  |    * ShellTransport -> Rust IPC   |  |  |
                    |  |  |    * Gamepad -> Spatial Nav Keys  |  |  |
                    |  |  |    * Viewport Transparency CSS    |  |  |
                    |  |  +----------------------------------+  |  |
                    |  |  |  GTK4 Hardware Underlay          |  |  |
                    |  |  |  - libmpv (VAAPI / Vulkan / DRI) |  |  |
                    |  |  +----------------------------------+  |  |
                    |  +----------------------------------------+  |
                    +----------------------------------------------+
```

### Why this approach?
- **No Android Emulation Overheads**: Avoids Waydroid, VM latency, or emulation layers.
- **Hardware Video Acceleration**: Streams hand off directly from the UI to native `libmpv` via IPC, enabling zero-copy VAAPI / Vulkan 4K HDR playback.
- **Full Gamepad Navigation**: Physical gamepads (Xbox, PlayStation, Steam Deck) control the entire UI with smooth spatial navigation and auto-repeat acceleration.
- **Steam Big Picture & Gamescope Native**: Integrates seamlessly with `--socket=wayland`, `--device=all`, and automatic borderless fullscreen.

---

## Quick Install on Bazzite / SteamOS

On your Bazzite TV box or Steam Deck (Desktop Mode terminal or SSH):

```bash
# 1. Clone this repository
git clone https://github.com/GonzaloMejia/stremio-bazzite-tv.git
cd stremio-bazzite-tv

# 2. Grant execution permissions
chmod +x build-and-install.sh flatpak-build/*.sh

# 3. Build, install, and register with Steam
./build-and-install.sh
```

The script automatically:
1. Installs GNOME 50 Platform & Rust SDK dependencies if needed.
2. Compiles `libplacebo`, `libass`, `libmpv`, and `stremio-linux-shell` with the embedded TV bridge.
3. Installs the Flatpak into user storage (`~/.local/share/flatpak`).
4. Registers the shortcut in **Steam Game Mode** using `steamos-add-to-steam`.

---

## Living Room Controller Mapping

The injected preload bridge (`tv-mpv-bridge.js`) polls physical controllers at 60 Hz and translates inputs into Stremio TV spatial navigation events:

| Controller Button (Xbox / PS / Deck) | Action | Target Behavior in Stremio TV |
|---|---|---|
| **D-Pad (Up / Down / Left / Right)** | Directional Keys | Spatial navigation with auto-repeat across carousels and menus. |
| **Left Analog Stick** | Directional Keys | Spatial navigation with deadzone filtering & acceleration. |
| **A / Cross (Button 0)** | `Enter` | Select item / Confirm / Play selected stream. |
| **B / Circle (Button 1)** | `Backspace` | Back / Exit details view / Return to previous row. |
| **X / Square (Button 2)** | `Spacebar` | Play / Pause toggle during playback. |
| **Y / Triangle (Button 3)** | `KeyV` | Cycle subtitle tracks. |
| **Left Bumper / LB (Button 4)** | `KeyE` | Seek backward (10 seconds). |
| **Right Bumper / RB (Button 5)** | `KeyH` | Seek forward (10 seconds). |
| **View / Select (Button 8)** | `Backspace` | Quick back. |
| **Menu / Start (Button 9)** | `Escape` | Menu / dismiss overlay controls. |

### 2.4GHz USB HID Remote Control Support
The bridge automatically normalizes input from USB air remotes (WeChip, G10/G20, Minix, OSMC, Fire TV USB remotes):

| USB Remote Button | Normalized Key | Function in Stremio TV |
|---|---|---|
| **Back / Return / Exit** | `Backspace` (8) | Back to previous view / exit player. |
| **Play / Pause** | `Spacebar` (32) | Toggle playback. |
| **Rewind ($\ll$)** | `KeyE` (69) | Seek backward 10s. |
| **Fast Forward ($\gg$)** | `KeyH` (72) | Seek forward 10s. |
| **Menu / Context** | `Escape` (27) | Open / dismiss sidebar drawer. |

---

## Steam Game Mode Configuration

1. Switch to **Steam Game Mode** (or Big Picture Mode).
2. Go to **Library** $\rightarrow$ **Non-Steam Games** $\rightarrow$ **Stremio**.
3. Under **Controller Settings** (Gamepad icon):
   - Choose the **Gamepad** template (Standard Xbox 360 / Generic Controller).
   - *(Optional for trackpad users)*: Choose **Gamepad with Joystick Trackpad**.
4. Press **Play** to launch directly into fullscreen TV mode!

---

## Hardware Decoding & Audio Passthrough

### Custom MPV Configuration
You can configure high-end TV audio passthrough (TrueHD / DTS-HD / Dolby Digital Plus) and Vulkan video scaling by creating `mpv.conf`:

```bash
mkdir -p ~/.var/app/com.stremio.Stremio/config/mpv
nano ~/.var/app/com.stremio.Stremio/config/mpv/mpv.conf
```

**Recommended TV Couch `mpv.conf`:**
```ini
# Hardware acceleration & GPU renderer
hwdec=auto-safe
vo=gpu-next
gpu-api=vulkan

# Audio Passthrough for AV Receivers / Soundbars
audio-spdif=ac3,dts,eac3,truehd,dtshd

# High Quality Video Scaling
scale=ewa_lanczos
cscale=ewa_lanczos
dscale=mitchell

# Cache configuration for smooth 4K HDR streaming
cache=yes
demuxer-max-bytes=400MiB
demuxer-max-back-bytes=150MiB
```

### Verify VAAPI Acceleration
Run inside the Flatpak sandbox to confirm your GPU driver:
```bash
flatpak run --command=sh com.stremio.Stremio -c "vainfo"
```

---

## Technical Documentation & Reverse Engineering

This project is backed by deep reverse-engineering of Stremio's Android TV APK, WebUI, and Linux Shell IPC protocols:

- [**Android TV Component Architecture Specification**](analysis/android-tv-components-spec.md): Full analysis of all 28 decompiled Android TV classes, horizontal Leanback carousels, catalog discovery, search, and ExoPlayer/MPV player wrappers.
- [**Linux Shell IPC & Preload Bridge Report**](analysis/path-a-ipc-bridge.md): Deep dive into WebKitGTK IPC mechanisms, `ShellTransport` prototype injection, and the Gamepad API to Spatial Navigation bridge.
- [**Full Reverse-Engineering Guide**](STREMIO_TV_FLATPAK_REA_GUIDE.md): Master blueprint and analysis log.

---

## Repository Structure

```
├── README.md                          # Main project guide & quick install
├── build-and-install.sh               # Root installation launcher
├── BAZZITE_TV_DEPLOYMENT_GUIDE.md     # Detailed deployment guide
├── STREMIO_TV_FLATPAK_REA_GUIDE.md    # Master REA guide
├── flatpak-build/                     # Self-contained Flatpak build files
│   ├── com.stremio.Stremio.json       # Flatpak manifest (builds libmpv + TV bridge)
│   ├── tv-mpv-bridge.js               # Injected preload bridge script
│   ├── stremio-tv.sh                  # TV launcher with GSettings & Wayland fixes
│   ├── cargo-sources.json             # Offline Rust crate vendor manifest
│   ├── com.stremio.Stremio.metainfo.xml # AppStream metadata
│   └── build-and-install.sh           # Internal build script
├── analysis/                          # Reverse engineering findings & decompiled code
│   ├── android-tv-components-spec.md  # Architectural specification
│   ├── path-a-ipc-bridge.md           # IPC & Gamepad technical report
│   ├── apk-architecture-map.md        # APK structural map
│   └── components/                    # Decompiled Android TV Kotlin/Java classes
└── patches/                           # Backup patches for standalone shell builds
```

---

## License

Stremio Linux Shell and components are licensed under the GPL-3.0 License. Refer to the upstream repository for additional details.
