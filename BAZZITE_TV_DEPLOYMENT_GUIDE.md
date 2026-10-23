# Stremio TV on Bazzite: Deployment & Setup Guide

This guide details how to build, deploy, and run the custom **Stremio TV (10-Foot Couch Mode)** Flatpak on your **Bazzite TV box** (Fedora Atomic / ostree running Steam Big Picture with Gamescope).

---

## 1. Architectural Summary

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

### Why this architecture?
1. **True 10-Foot TV Experience**: Replaces desktop Stremio with the leanback, TV-optimized interface (`tv.strem.io`) designed for big screens.
2. **Native Hardware-Accelerated Video**: Video decoding and rendering is handled directly by native `libmpv` (Mesa VAAPI / Vulkan), completely bypassing browser video pipeline bottlenecks and dropped frames.
3. **Seamless Living Room Controls**: Physical gamepads (Xbox, PlayStation, Steam Deck, generic Bluetooth controllers) navigate carousels, catalogs, and media playback seamlessly via our spatial navigation bridge with key-repeat acceleration.
4. **Gamescope & Wayland Native**: Built with `--socket=wayland` and `--device=all`, integrating smoothly into Steam Game Mode without window decorations or black border glitches.

---

## 2. Flatpak Build Tree Overview

All necessary build files are self-contained in the [`flatpak-build/`](file:///d:/Vibe%20Coding%20Projects/REA/flatpak-build) directory:

| File | Purpose |
|---|---|
| [`com.stremio.Stremio.json`](file:///d:/Vibe%20Coding%20Projects/REA/flatpak-build/com.stremio.Stremio.json) | The Flatpak manifest. Compiles `libplacebo`, `libass`, `libmpv`, embeds the TV bridge, and bundles Node.js. |
| [`tv-mpv-bridge.js`](file:///d:/Vibe%20Coding%20Projects/REA/flatpak-build/tv-mpv-bridge.js) | The preload script embedded into `stremio-linux-shell`. Implements `ShellTransport` and the 60Hz Gamepad -> Keyboard mapper. |
| [`stremio-tv.sh`](file:///d:/Vibe%20Coding%20Projects/REA/flatpak-build/stremio-tv.sh) | The entrypoint launcher. Configures fullscreen GSettings and directs the shell to `tv.strem.io` via the local streaming proxy. |
| [`cargo-sources.json`](file:///d:/Vibe%20Coding%20Projects/REA/flatpak-build/cargo-sources.json) | Offline Rust crate dependency vendor manifest for reproducible building. |
| [`com.stremio.Stremio.metainfo.xml`](file:///d:/Vibe%20Coding%20Projects/REA/flatpak-build/com.stremio.Stremio.metainfo.xml) | AppStream metadata specification for desktop environments and software centers. |
| [`build-and-install.sh`](file:///d:/Vibe%20Coding%20Projects/REA/flatpak-build/build-and-install.sh) | One-command build, installation, and Steam registration script. |

---

## 3. Deployment Instructions

### Step 1: Transfer `flatpak-build` to your Bazzite TV Box

Transfer the `flatpak-build` folder to your Bazzite machine via SSH/SCP, rsync, or USB drive:

```bash
# Example via scp:
scp -r "d:/Vibe Coding Projects/REA/flatpak-build" bazzite@<TV_BOX_IP>:~/stremio-tv-build

# Or if you are using git on Bazzite:
cd ~/stremio-tv-build
```

### Step 2: Run the One-Click Build & Install Script

On your Bazzite TV box, open a terminal (or SSH in) and run:

```bash
cd ~/stremio-tv-build
chmod +x build-and-install.sh stremio-tv.sh
./build-and-install.sh
```

The script will automatically:
1. Verify `flatpak` and `flatpak-builder`.
2. Install the required GNOME 50 Platform and Rust SDK extensions.
3. Build the custom Stremio TV Flatpak using `cargo-sources.json` and embed `tv-mpv-bridge.js`.
4. Install the Flatpak into user storage (`~/.local/share/flatpak`).
5. Register Stremio with Steam Game Mode using `steamos-add-to-steam`.

*(Manual alternative command)*:
```bash
flatpak-builder --user --install --force-clean build-dir com.stremio.Stremio.json
```

---

## 4. Steam Game Mode Configuration

1. **Launch Steam Game Mode** (or switch to Big Picture mode).
2. Go to **Library** -> **Non-Steam Games**.
3. Select **Stremio**.
4. In the game details view, select the **Controller Settings** (Gamepad icon):
   - **Template**: Choose **Gamepad** (standard Xbox 360 / Generic Gamepad layout).
   - If you want trackpad mouse emulation on Steam Deck or DualSense, choose **Gamepad with Joystick Trackpad**.
5. Press **Play**! Stremio will boot directly into the fullscreen 10-foot TV interface.

---

## 5. Living Room Controller Cheat Sheet

The controller bridge automatically maps standard controller buttons to the TV spatial navigation system:

| Controller Input (Xbox / DualSense / Deck) | Action | Function in Stremio TV |
|---|---|---|
| **D-Pad (Up / Down / Left / Right)** | Directional Keys | Spatial navigation between tiles, rows, and sidebar menus. |
| **Left Analog Stick** | Directional Keys | Spatial navigation with deadzone filtering and auto-repeat acceleration. |
| **A / Cross (Button 0)** | `Enter` | Select item / Confirm / Play stream. |
| **B / Circle (Button 1)** | `Backspace` | Back / Exit detail page / Return to home carousels. |
| **X / Square (Button 2)** | `Space` | Play / Pause toggle during playback. |
| **Y / Triangle (Button 3)** | `KeyV` | Cycle subtitle tracks during playback. |
| **Left Bumper / LB (Button 4)** | `KeyE` | Seek backward (10 seconds). |
| **Right Bumper / RB (Button 5)** | `KeyH` | Seek forward (10 seconds). |
| **View / Select (Button 8)** | `Backspace` | Quick back. |
| **Menu / Start (Button 9)** | `Escape` | Main menu / dismiss overlay. |

---

## 6. Advanced Customization & Hardware Decoding

### Hardware Acceleration (VA-API / Vulkan)
The Flatpak manifest grants:
- `--device=dri`: Direct Rendering Infrastructure access.
- `--device=all`: Direct input devices and video decoders.
- `--socket=wayland`: Native Wayland pipe.

To verify VAAPI hardware decoding within the container:
```bash
flatpak run --command=sh com.stremio.Stremio -c "vainfo"
```

### Custom MPV Configuration (Audio Passthrough & Shaders)
You can customize `mpv` settings (such as TrueHD/DTS-HD audio passthrough, subtitle styles, and upscaling shaders) by creating an `mpv.conf` file:

```bash
mkdir -p ~/.var/app/com.stremio.Stremio/config/mpv
nano ~/.var/app/com.stremio.Stremio/config/mpv/mpv.conf
```

**Recommended TV Couch `mpv.conf`:**
```ini
# Hardware decoding
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

---

## 7. Troubleshooting

- **Black screen on video playback?**
  Ensure WebKit background transparency is intact. Our updated [`tv-mpv-bridge.js`](file:///d:/Vibe%20Coding%20Projects/REA/flatpak-build/tv-mpv-bridge.js) automatically injects CSS rules to keep `html`, `body`, and player containers transparent, allowing native MPV underneath to show through.
- **Nvidia GPU artifacting?**
  [`stremio-tv.sh`](file:///d:/Vibe%20Coding%20Projects/REA/flatpak-build/stremio-tv.sh) automatically sets `GSK_RENDERER=opengl` when `/dev/nvidia0` is detected.
- **Controller not responding?**
  Ensure Steam Input is set to "Gamepad" template and not "Desktop Keyboard".
