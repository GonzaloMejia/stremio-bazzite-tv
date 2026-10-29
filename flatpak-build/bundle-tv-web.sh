#!/usr/bin/env bash
# ==============================================================================
# Stremio TV Offline Web UI Bundler Wrapper
# ==============================================================================
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
OUT_DIR="${SCRIPT_DIR}/stremio-tv-web"
mkdir -p "${OUT_DIR}"

echo "[*] Bundling Stremio TV (tv.strem.io) web assets for offline Flatpak execution..."
python3 "${SCRIPT_DIR}/bundle-tv-web.py" "${OUT_DIR}"
