#!/usr/bin/env node
/**
 * Stremio TV Local Offline-First UI Server & Persistent Storage Mirror
 * Runs on http://127.0.0.1:11471 inside the Flatpak sandbox.
 *
 * 1. Serves offline-bundled tv.strem.io assets from /app/share/stremio-tv-web/
 * 2. Read-through caches any missing assets from https://tv.strem.io into
 *    $XDG_DATA_HOME/stremio-tv-web-cache/ so subsequent launches are 100% offline.
 * 3. Persists localStorage (auth token, profile, library, settings) to disk at
 *    $XDG_DATA_HOME/stremio-tv-storage.json and injects it into index.html on boot.
 */
'use strict';

const http = require('http');
const https = require('https');
const fs = require('fs');
const path = require('path');

const PORT = parseInt(process.env.STREMIO_TV_UI_PORT || '11471', 10);
const HOST = '127.0.0.1';
const BUNDLE_DIR = process.env.STREMIO_TV_BUNDLE_DIR || '/app/share/stremio-tv-web';
const DATA_DIR = process.env.XDG_DATA_HOME || path.join(process.env.HOME || '/tmp', '.local/share');
const CACHE_DIR = path.join(DATA_DIR, 'stremio-tv-web-cache');
const STORAGE_FILE = path.join(DATA_DIR, 'stremio-tv-storage.json');
const UPSTREAM_ORIGIN = 'https://tv.strem.io';

try {
    fs.mkdirSync(CACHE_DIR, { recursive: true });
} catch (e) {}

const MIME_TYPES = {
    '.html': 'text/html; charset=utf-8',
    '.js': 'application/javascript; charset=utf-8',
    '.mjs': 'application/javascript; charset=utf-8',
    '.css': 'text/css; charset=utf-8',
    '.wasm': 'application/wasm',
    '.json': 'application/json; charset=utf-8',
    '.svg': 'image/svg+xml',
    '.png': 'image/png',
    '.jpg': 'image/jpeg',
    '.jpeg': 'image/jpeg',
    '.ico': 'image/x-icon',
    '.ttf': 'font/ttf',
    '.otf': 'font/otf',
    '.woff': 'font/woff',
    '.woff2': 'font/woff2'
};

function getMimeType(filePath) {
    const ext = path.extname(filePath).toLowerCase();
    return MIME_TYPES[ext] || 'application/octet-stream';
}

function readPersistedStorage() {
    try {
        if (fs.existsSync(STORAGE_FILE)) {
            return JSON.parse(fs.readFileSync(STORAGE_FILE, 'utf8'));
        }
    } catch (e) {
        console.error('[Stremio TV UI Server] Failed to read storage file:', e.message);
    }
    return {};
}

function writePersistedStorage(data) {
    try {
        const current = readPersistedStorage();
        const merged = Object.assign({}, current, data);
        const finalData = Object.keys(data).length === 0 ? {} : merged;
        const tmpFile = STORAGE_FILE + '.tmp';
        fs.writeFileSync(tmpFile, JSON.stringify(finalData, null, 2), 'utf8');
        fs.renameSync(tmpFile, STORAGE_FILE);
    } catch (e) {
        console.error('[Stremio TV UI Server] Failed to write storage file:', e.message);
    }
}

function injectBootstrapIntoHtml(htmlBuffer) {
    let html = htmlBuffer.toString('utf8');
    html = html.replace(/https:\/\/tv\.strem\.io\//g, '/');

    const storageSnapshot = JSON.stringify(readPersistedStorage());
    const bootstrapScript = `<script>
window.__INITIAL_STREMIO_STORAGE__ = ${storageSnapshot};
try {
    for (const [k, v] of Object.entries(window.__INITIAL_STREMIO_STORAGE__)) {
        if (typeof v === 'string' && window.localStorage.getItem(k) === null) {
            window.localStorage.setItem(k, v);
        }
    }
} catch (e) {}
window.loadedApp = window.loadedApp || function() {};
</script>`;

    if (html.includes('<head>')) {
        html = html.replace('<head>', '<head>\n' + bootstrapScript);
    } else {
        html = bootstrapScript + '\n' + html;
    }
    return Buffer.from(html, 'utf8');
}

function fetchFromUpstream(urlPath) {
    return new Promise((resolve, reject) => {
        const targetUrl = UPSTREAM_ORIGIN + (urlPath.startsWith('/') ? urlPath : '/' + urlPath);
        const req = https.get(targetUrl, {
            headers: {
                'User-Agent': 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.0 Safari/605.1.15'
            },
            timeout: 15000
        }, (res) => {
            if (res.statusCode && res.statusCode >= 300 && res.statusCode < 400 && res.headers.location) {
                fetchFromUpstream(res.headers.location).then(resolve).catch(reject);
                return;
            }
            if (res.statusCode !== 200) {
                reject(new Error(`Upstream status ${res.statusCode} for ${urlPath}`));
                res.resume();
                return;
            }
            const chunks = [];
            res.on('data', (c) => chunks.push(c));
            res.on('end', () => resolve({
                buffer: Buffer.concat(chunks),
                contentType: res.headers['content-type']
            }));
        });
        req.on('error', reject);
        req.on('timeout', () => {
            req.destroy(new Error('Upstream timeout'));
        });
    });
}

const server = http.createServer(async (req, res) => {
    res.setHeader('Access-Control-Allow-Origin', '*');
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS');
    res.setHeader('Access-Control-Allow-Headers', 'Content-Type');

    if (req.method === 'OPTIONS') {
        res.writeHead(204);
        res.end();
        return;
    }

    const parsedUrl = new URL(req.url || '/', `http://${HOST}:${PORT}`);
    let pathname = decodeURIComponent(parsedUrl.pathname);

    if (pathname === '/__stremio_storage__') {
        if (req.method === 'GET') {
            res.writeHead(200, { 'Content-Type': 'application/json; charset=utf-8' });
            res.end(JSON.stringify(readPersistedStorage()));
            return;
        }
        if (req.method === 'POST') {
            const chunks = [];
            req.on('data', (c) => chunks.push(c));
            req.on('end', () => {
                try {
                    const body = JSON.parse(Buffer.concat(chunks).toString('utf8'));
                    if (body && typeof body === 'object') {
                        writePersistedStorage(body);
                    }
                    res.writeHead(200, { 'Content-Type': 'application/json' });
                    res.end('{"ok":true}');
                } catch (e) {
                    res.writeHead(400, { 'Content-Type': 'application/json' });
                    res.end('{"ok":false}');
                }
            });
            return;
        }
    }

    if (pathname === '/' || pathname === '') {
        pathname = '/index.html';
    }
    const safeRelPath = path.normalize(pathname).replace(/^(\.\.(\/|\\|$))+/, '').replace(/^[/\\]+/, '');
    const bundlePath = path.join(BUNDLE_DIR, safeRelPath);
    const cachePath = path.join(CACHE_DIR, safeRelPath);

    let fileBuffer = null;
    if (fs.existsSync(bundlePath) && fs.statSync(bundlePath).isFile()) {
        fileBuffer = fs.readFileSync(bundlePath);
    } else if (fs.existsSync(cachePath) && fs.statSync(cachePath).isFile()) {
        fileBuffer = fs.readFileSync(cachePath);
    }

    if (!fileBuffer) {
        try {
            const upstream = await fetchFromUpstream('/' + safeRelPath);
            fileBuffer = upstream.buffer;
            try {
                fs.mkdirSync(path.dirname(cachePath), { recursive: true });
                fs.writeFileSync(cachePath, fileBuffer);
            } catch (cacheErr) {}
        } catch (err) {
            res.writeHead(404, { 'Content-Type': 'text/plain; charset=utf-8' });
            res.end('Not found: ' + safeRelPath);
            return;
        }
    }

    if (safeRelPath === 'index.html') {
        fileBuffer = injectBootstrapIntoHtml(fileBuffer);
    }

    res.writeHead(200, {
        'Content-Type': getMimeType(safeRelPath),
        'Cache-Control': safeRelPath === 'index.html' ? 'no-cache' : 'public, max-age=31536000'
    });
    res.end(fileBuffer);
});

server.listen(PORT, HOST, () => {
    console.log(`[Stremio TV UI Server] Serving offline-first TV UI on http://${HOST}:${PORT}`);
});
