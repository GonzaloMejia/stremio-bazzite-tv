#!/bin/bash
# Stremio TV Couch Launcher for Bazzite / Steam Game Mode Flatpak
set -e

# Use GSK OpenGL renderer for Nvidia GPUs if detected
if ls /dev/nvidia0 &>/dev/null 2>&1; then
    export GSK_RENDERER=opengl
fi

# Configure window state for TV 10-foot Couch Mode
gsettings set com.stremio.Stremio remember-window-state true 2>/dev/null || true
gsettings set com.stremio.Stremio window-fullscreen true 2>/dev/null || true

# 10-foot TV Couch UI URL proxied through local Stremio streaming server
TV_WEB_URL="http://127.0.0.1:11470/proxy/d=https%3A%2F%2Ftv.strem.io%2F"

# Check if --url was already passed by caller
HAS_URL=0
for arg in "$@"; do
    if [[ "$arg" == "--url"* ]] || [[ "$arg" == "-u"* ]]; then
        HAS_URL=1
        break
    fi
done

EXTRA_ARGS=()
if [ "$HAS_URL" -eq 0 ]; then
    EXTRA_ARGS+=(--url "${TV_WEB_URL}")
fi

echo "[Stremio TV] Launching Stremio Linux Shell in 10-foot TV Mode..."
exec /app/libexec/stremio/stremio \
    "${EXTRA_ARGS[@]}" \
    --no-window-decorations \
    "$@"
