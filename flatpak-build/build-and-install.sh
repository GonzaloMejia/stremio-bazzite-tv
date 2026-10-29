#!/usr/bin/env bash
# ==============================================================================
# Stremio TV Couch Flatpak Builder & Installer for Bazzite / SteamOS
# ==============================================================================
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "${SCRIPT_DIR}"

echo "=========================================================="
echo " Building Stremio TV Flatpak (10-Foot UI + Native MPV)"
echo " Target: Bazzite / SteamOS Game Mode"
echo "=========================================================="

# 0. Pre-build safeguard: Extract/backup pre-wipe tv-mpv-bridge.js from .flatpak-builder cache if present
CACHED_BRIDGE=$(ls -t .flatpak-builder/build/stremio-*/tv-mpv-bridge.js 2>/dev/null | head -n 1 || true)
if [ -n "${CACHED_BRIDGE}" ] && [ -f "${CACHED_BRIDGE}" ]; then
    CACHED_LINES=$(wc -l < "${CACHED_BRIDGE}" || echo 0)
    if [ "${CACHED_LINES}" -gt 50 ]; then
        cp -f "${CACHED_BRIDGE}" "${SCRIPT_DIR}/tv-mpv-bridge.pre-wipe.bak.js"
        echo "[+] Extracted pre-wipe tv-mpv-bridge.js backup from ${CACHED_BRIDGE} (${CACHED_LINES} lines)"
    fi
fi

# Verify active tv-mpv-bridge.js is intact (not the 18-line stub)
ACTIVE_LINES=$(wc -l < "${SCRIPT_DIR}/tv-mpv-bridge.js" 2>/dev/null || echo 0)
if [ "${ACTIVE_LINES}" -lt 50 ]; then
    if [ -f "${SCRIPT_DIR}/../patches/tv-mpv-bridge.js" ] && [ "$(wc -l < "${SCRIPT_DIR}/../patches/tv-mpv-bridge.js")" -gt 50 ]; then
        cp -f "${SCRIPT_DIR}/../patches/tv-mpv-bridge.js" "${SCRIPT_DIR}/tv-mpv-bridge.js"
        echo "[+] Restored full tv-mpv-bridge.js from ../patches/tv-mpv-bridge.js"
    elif [ -f "${SCRIPT_DIR}/tv-mpv-bridge.pre-wipe.bak.js" ]; then
        cp -f "${SCRIPT_DIR}/tv-mpv-bridge.pre-wipe.bak.js" "${SCRIPT_DIR}/tv-mpv-bridge.js"
        echo "[+] Restored pre-wipe tv-mpv-bridge.js from cached build backup"
    fi
fi

# 1. Check prerequisites
if ! command -v flatpak &>/dev/null; then
    echo "[-] Error: 'flatpak' command not found."
    exit 1
fi

# 2. Bundle offline tv.strem.io web assets into stremio-tv-web/
chmod +x "${SCRIPT_DIR}/bundle-tv-web.sh" "${SCRIPT_DIR}/stremio-tv.sh" "${SCRIPT_DIR}/tv-ui-server.js" 2>/dev/null || true
bash "${SCRIPT_DIR}/bundle-tv-web.sh"

# 3. Add Flathub repository if missing
echo "[*] Ensuring Flathub remote is configured..."
flatpak remote-add --user --if-not-exists flathub https://dl.flathub.org/repo/flathub.flatpakrepo

# 4. Resolve flatpak-builder (host binary, or Flathub org.flatpak.Builder on immutable hosts)
if command -v flatpak-builder &>/dev/null; then
    FLATPAK_BUILDER=(flatpak-builder)
else
    echo "[!] 'flatpak-builder' not on PATH (expected on Bazzite/SteamOS)."
    echo "[*] Using Flathub-packaged org.flatpak.Builder instead..."
    flatpak install --user -y flathub org.flatpak.Builder
    FLATPAK_BUILDER=(flatpak run org.flatpak.Builder)
fi

# 5. Install required GNOME 50 Runtime/SDK and extensions
#    (SDK extensions follow the freedesktop base branch: GNOME 50 -> 25.08)
echo "[*] Installing required Flatpak SDK & Runtime dependencies..."
flatpak install --user -y --noninteractive flathub \
    org.gnome.Platform//50 \
    org.gnome.Sdk//50 \
    org.freedesktop.Sdk.Extension.rust-stable//25.08 \
    org.freedesktop.Platform.codecs-extra//25.08-extra || \
    echo "[!] Some dependencies failed to pre-install; flatpak-builder will retry via --install-deps-from."

# 6. Build and install custom Flatpak
echo "[*] Building and installing com.stremio.Stremio (TV Edition)..."
"${FLATPAK_BUILDER[@]}" --user --install --force-clean \
    --install-deps-from=flathub \
    build-dir com.stremio.Stremio.json

echo "[+] Stremio TV Flatpak installed successfully!"

# 7. Integrate with Steam Game Mode
DESKTOP_PATH="${HOME}/.local/share/flatpak/exports/share/applications/com.stremio.Stremio.desktop"
if [ ! -f "${DESKTOP_PATH}" ]; then
    DESKTOP_PATH="/var/lib/flatpak/exports/share/applications/com.stremio.Stremio.desktop"
fi

if command -v steamos-add-to-steam &>/dev/null; then
    echo "[*] Registering Stremio with Steam Game Mode..."
    steamos-add-to-steam "${DESKTOP_PATH}" || true
    echo "[+] Stremio is now registered in Steam Game Mode!"
else
    echo "[*] SteamOS shortcut utility not found. You can add Stremio to Steam manually:"
    echo "    Steam -> Games -> 'Add a Non-Steam Game to My Library...' -> Stremio"
fi

echo "=========================================================="
echo " Installation Complete!"
echo " Launch with: flatpak run com.stremio.Stremio"
echo " Or switch to Steam Game Mode to launch from couch UI."
echo "=========================================================="
