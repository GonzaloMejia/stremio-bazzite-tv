#!/usr/bin/env node
'use strict';
const http = require('http'), fs = require('fs'), path = require('path');
const PORT = parseInt(process.env.STREMIO_TV_UI_PORT || '11471', 10);
const HOST = '127.0.0.1';
const WEB_ROOT = process.env.STREMIO_TV_BUNDLE_DIR || '/app/share/stremio-tv-web';
const sseClients = [];
const keyDebounce = new Map();

process.on('uncaughtException', (err) => console.error('[Stremio TV UI Server] Uncaught exception:', err.message));

function emitGamepadKey(key, code, keyCode, src) {
    const now = Date.now();
    if ((keyDebounce.get(keyCode) || 0) + 110 > now) return;
    keyDebounce.set(keyCode, now);
    console.log(`[Stremio TV Gamepad] ${key} (${src})`);
    const data = `data: ${JSON.stringify({ key, code, keyCode })}\n\n`;
    sseClients.forEach(c => c.write(data));
}

function processEventBuffer(buf, src) {
    for (let i = 0; i + 24 <= buf.length; i += 24) {
        const type = buf.readUInt16LE(i + 16), code = buf.readUInt16LE(i + 18), val = buf.readInt32LE(i + 20);
        if (type === 3) {
            if (code === 16) emitGamepadKey(val < 0 ? 'ArrowLeft' : 'ArrowRight', 'Arrow', val < 0 ? 37 : 39, src);
            if (code === 17) emitGamepadKey(val < 0 ? 'ArrowUp' : 'ArrowDown', 'Arrow', val < 0 ? 38 : 40, src);
        } else if (type === 1 && (val === 1 || val === 2)) {
            const map = { 544: [38, 'ArrowUp'], 706: [38, 'ArrowUp'], 103: [38, 'ArrowUp'], 545: [40, 'ArrowDown'], 707: [40, 'ArrowDown'], 108: [40, 'ArrowDown'], 546: [37, 'ArrowLeft'], 704: [37, 'ArrowLeft'], 105: [37, 'ArrowLeft'], 547: [39, 'ArrowRight'], 705: [39, 'ArrowRight'], 106: [39, 'ArrowRight'] };
            if (map[code]) emitGamepadKey(map[code][1], map[code][1], map[code][0], src);
            else if (val === 1) {
                const btnMap = { 304: [13, 'Enter'], 305: [27, 'Escape'], 307: [32, ' '], 308: [83, 's'], 314: [27, 'Escape'], 158: [27, 'Escape'], 315: [13, 'Enter'] };
                if (btnMap[code]) emitGamepadKey(btnMap[code][1], btnMap[code][1], btnMap[code][0], src);
            }
        }
    }
}

function initGamepadReader() {
    for (let i = 0; i < 32; i++) {
        const dev = `/dev/input/event${i}`;
        if (fs.existsSync(dev)) {
            try {
                const stream = fs.createReadStream(dev);
                stream.on('data', (b) => processEventBuffer(b, dev));
                stream.on('error', () => {});
            } catch (e) {}
        }
    }
    for (let i = 0; i < 4; i++) {
        const js = `/dev/input/js${i}`;
        if (fs.existsSync(js)) {
            try {
                const fd = fs.openSync(js, 'r');
                const buf = Buffer.alloc(8);
                setInterval(() => {
                    while (fs.readSync(fd, buf, 0, 8, null) === 8) {
                        const val = buf.readInt16LE(4), raw = buf.readUInt8(6), init = (raw & 0x80) !== 0, type = raw & ~0x80, num = buf.readUInt8(7);
                        if (!init && type === 2) {
                            if (num === 0 || num === 6) emitGamepadKey(val < -16000 ? 'ArrowLeft' : 'ArrowRight', 'Arrow', val < -16000 ? 37 : 39, js);
                            if (num === 1 || num === 7) emitGamepadKey(val < -16000 ? 'ArrowUp' : 'ArrowDown', 'Arrow', val < -16000 ? 38 : 40, js);
                        } else if (!init && type === 1 && val === 1) {
                            const map = { 12: [38, 'ArrowUp'], 13: [40, 'ArrowDown'], 14: [37, 'ArrowLeft'], 15: [39, 'ArrowRight'], 0: [13, 'Enter'], 1: [27, 'Escape'], 2: [32, ' '], 3: [83, 's'], 6: [27, 'Escape'], 8: [27, 'Escape'], 7: [13, 'Enter'], 9: [13, 'Enter'] };
                            if (map[num]) emitGamepadKey(map[num][1], map[num][1], map[num][0], js);
                        }
                    }
                }, 50);
            } catch (e) {}
        }
    }
}
initGamepadReader(); setInterval(initGamepadReader, 3000);

const MIME = {'.html': 'text/html; charset=utf-8', '.js': 'application/javascript; charset=utf-8', '.css': 'text/css; charset=utf-8'};
const server = http.createServer((req, res) => {
    try {
        const parsedUrl = new URL(req.url, `http://${HOST}:${PORT}`);
        const pathname = decodeURIComponent(parsedUrl.pathname);

        if (pathname === '/__log') { console.log(`[Stremio TV Bridge] ${parsedUrl.searchParams.get('msg')}`); res.writeHead(200); res.end(); return; }
        if (pathname === '/__gamepad_stream') {
            res.writeHead(200, {'Content-Type': 'text/event-stream', 'Cache-Control': 'no-store', 'Connection': 'keep-alive'});
            sseClients.push(res); req.on('close', () => sseClients.splice(sseClients.indexOf(res), 1));
            return;
        }
        if (pathname === '/__quit') { process.exit(0); }

        let p = pathname === '/' ? '/index.html' : pathname;
        let filePath = path.join(WEB_ROOT, path.normalize(p).replace(/^(\.\.(\/|\\|$))+/, '').replace(/^[/\\]+/, ''));
        if (fs.existsSync(filePath) && fs.statSync(filePath).isDirectory()) filePath = path.join(filePath, 'index.html');
        
        if (fs.existsSync(filePath) && fs.statSync(filePath).isFile()) {
            res.writeHead(200, { 'Content-Type': MIME[path.extname(filePath)] || 'application/octet-stream' });
            res.end(fs.readFileSync(filePath));
        } else {
            res.writeHead(404); res.end('Not found');
        }
    } catch (err) {
        console.error('[Stremio TV UI Server] Request error:', err.message);
        if (!res.headersSent) { res.writeHead(500); res.end('Internal Server Error'); }
    }
});
server.listen(PORT, HOST, () => console.log(`[Stremio TV UI Server] http://${HOST}:${PORT}`));
