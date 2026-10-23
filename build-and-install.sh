#!/usr/bin/env bash
# ==============================================================================
# Stremio TV Couch Flatpak Builder & Installer (Repository Root Launcher)
# Target: Bazzite / SteamOS Game Mode
# ==============================================================================
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "${SCRIPT_DIR}/flatpak-build"
exec ./build-and-install.sh "$@"
