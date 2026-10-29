#!/bin/bash
# ==============================================================================
# Stremio TV Couch Launcher for Bazzite / Steam Game Mode Flatpak
# Starts the offline-first local TV UI server (127.0.0.1:11471) and launches
# stremio-linux-shell in borderless fullscreen 10-foot mode with clean teardown.
# ==============================================================================
set -e

# 1. GPU & VA-API Hardware Acceleration Configuration
if ls /dev/nvidia0 &>/dev/null 2>&1; then
    export GSK_RENDERER=opengl
elif grep -qi "0x1002" /sys/class/drm/card*/device/vendor 2>/dev/null; then
    export LIBVA_DRIVER_NAME="${LIBVA_DRIVER_NAME:-radeonsi}"
fi

# 2. Ensure persistent data directories exist
export XDG_DATA_HOME="${XDG_DATA_HOME:-$HOME/.var/app/com.stremio.Stremio/data}"
export XDG_CONFIG_HOME="${XDG_CONFIG_HOME:-$HOME/.var/app/com.stremio.Stremio/config}"
export XDG_CACHE_HOME="${XDG_CACHE_HOME:-$HOME/.var/app/com.stremio.Stremio/cache}"
mkdir -p "${XDG_DATA_HOME}" "${XDG_CONFIG_HOME}" "${XDG_CACHE_HOME}" "$HOME/.stremio-server" 2>/dev/null || true

# 3. Configure window state for TV 10-foot Couch Mode
gsettings set com.stremio.Stremio remember-window-state true 2>/dev/null || true
gsettings set com.stremio.Stremio window-fullscreen true 2>/dev/null || true

# 4. Clean Process Termination Trap (prevents Steam Game Mode from hanging on "Stopping...")
UI_PID=""
SHELL_PID=""
cleanup() {
    echo "[Stremio TV] Shutting down Stremio TV services..."
    if [ -n "${SHELL_PID}" ] && kill -0 "${SHELL_PID}" 2>/dev/null; then
        kill -TERM "${SHELL_PID}" 2>/dev/null || true
    fi
    if [ -n "${UI_PID}" ] && kill -0 "${UI_PID}" 2>/dev/null; then
        kill -TERM "${UI_PID}" 2>/dev/null || true
    fi
    pkill -f "/app/libexec/stremio/server.js" 2>/dev/null || true
    pkill -f "/app/libexec/stremio/tv-ui-server.js" 2>/dev/null || true
}
trap cleanup EXIT SIGTERM SIGINT

# 5. Start Local Offline-First TV Web UI Server on http://127.0.0.1:11471
export STREMIO_TV_UI_PORT=11471
if [ -f "/app/libexec/stremio/tv-ui-server.js" ] && [ -x "/app/bin/node" ]; then
    echo "[Stremio TV] Starting local offline-first TV UI server on http://127.0.0.1:${STREMIO_TV_UI_PORT}..."
    /app/bin/node /app/libexec/stremio/tv-ui-server.js &
    UI_PID=$!

    # Wait up to 2 seconds for local UI server to listen
    for _ in {1..40}; do
        if (echo > /dev/tcp/127.0.0.1/${STREMIO_TV_UI_PORT}) >/dev/null 2>&1; then
            break
        fi
        sleep 0.05
    done
    TV_WEB_URL="http://127.0.0.1:${STREMIO_TV_UI_PORT}/"
else
    TV_WEB_URL="https://tv.strem.io"
fi

# 6. Check if --url was already passed by caller
HAS_URL=0
for arg in "$@"; do
    if [[ "$arg" == "--url"* ]] || [[ "$arg" == "-u"* ]]; then
        HAS_URL=1
        break
    fi
done

EXTRA_ARGS=()
if [ "$HAS_URL" -eq 0 ]; then
    EXTRA_ARGS+=(--url="${TV_WEB_URL}")
fi

echo "[Stremio TV] Launching Stremio Linux Shell in 10-foot TV Mode (${TV_WEB_URL})..."
/app/libexec/stremio/stremio \
    "${EXTRA_ARGS[@]}" \
    "$@" &
SHELL_PID=$!

wait "${SHELL_PID}" 2>/dev/null || true
