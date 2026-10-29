#!/usr/bin/env python3
"""
Stremio TV Offline Web UI Bundler
Crawls and downloads https://tv.strem.io (HTML, JS chunks, WASM, Worker, CSS,
fonts, and icons) into flatpak-build/stremio-tv-web/ for offline Flatpak bundling.
"""
import os
import re
import sys
import urllib.request
import urllib.parse
from concurrent.futures import ThreadPoolExecutor, as_completed

OUT_DIR = sys.argv[1] if len(sys.argv) > 1 else os.path.join(os.path.dirname(os.path.abspath(__file__)), "stremio-tv-web")
BASE_URL = "https://tv.strem.io/"
UA = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.0 Safari/605.1.15"

os.makedirs(OUT_DIR, exist_ok=True)
downloaded = set()


def fetch_bytes(rel_path):
    rel_path = rel_path.lstrip("/")
    url = urllib.parse.urljoin(BASE_URL, rel_path)
    req = urllib.request.Request(url, headers={"User-Agent": UA})
    with urllib.request.urlopen(req, timeout=30) as resp:
        return resp.read()


def save_asset(rel_path):
    rel_path = rel_path.split("?")[0].split("#")[0].lstrip("/")
    if not rel_path:
        rel_path = "index.html"
    if rel_path in downloaded:
        return None
    downloaded.add(rel_path)
    dest = os.path.join(OUT_DIR, *rel_path.split("/"))
    os.makedirs(os.path.dirname(dest), exist_ok=True)
    try:
        data = fetch_bytes(rel_path)
        with open(dest, "wb") as f:
            f.write(data)
        print(f"  [+] {rel_path} ({len(data)} bytes)")
        return data
    except Exception as e:
        print(f"  [!] Skipped {rel_path}: {e}")
        return None


def main():
    index_bytes = save_asset("index.html")
    if not index_bytes:
        if os.path.exists(os.path.join(OUT_DIR, "index.html")):
            print("[*] Offline bundle already exists in stremio-tv-web/, keeping cached copy.")
            return
        print("[-] Warning: Could not reach https://tv.strem.io; runtime server will fetch & cache on demand.")
        return

    index_html = index_bytes.decode("utf-8", errors="ignore")
    m_hash = re.search(r"([0-9a-f]{40})/", index_html)
    build_hash = m_hash.group(1) if m_hash else ""
    print(f"[*] Detected tv.strem.io build hash: {build_hash}")

    queue = set()
    for m in re.finditer(r'(?:src|href)="([^"]+)"', index_html):
        queue.add(m.group(1).lstrip("/"))

    initial_texts = {}
    for p in list(queue):
        b = save_asset(p)
        if b:
            initial_texts[p] = b.decode("utf-8", errors="ignore")

    runtime_txt = next((v for k, v in initial_texts.items() if k.endswith("runtime.js")), "")
    js_chunk_map = {}
    css_chunk_map = {}

    m_fu = re.search(r'f\.u=function\(e\)\{return"([^"]+)"\+(\{[^}]+\})\[e\]\|\|e\)\+"([^"]+)"\}', runtime_txt)
    if not m_fu:
        m_fu = re.search(r'f\.u=function\(e\)\{return"([^"]+)"\+\((\{[^}]+\})\[e\]\|\|e\)\+"([^"]+)"\}', runtime_txt)
    if m_fu:
        js_prefix, map_body, js_suffix = m_fu.group(1), m_fu.group(2), m_fu.group(3)
        for pair in re.finditer(r'(\d+):"([^"]+)"', map_body):
            cid, cname = pair.group(1), pair.group(2)
            js_chunk_map[cid] = f"{js_prefix}{cname}{js_suffix}"
            queue.add(js_chunk_map[cid])

    m_css = re.search(r'f\.miniCssF=function\(e\)\{return"([^"]+)"\+(\{[^}]+\})\[e\]\+"([^"]+)"\}', runtime_txt)
    if m_css:
        css_prefix, map_body, css_suffix = m_css.group(1), m_css.group(2), m_css.group(3)
        for pair in re.finditer(r'(\d+):"([^"]+)"', map_body):
            cid, cname = pair.group(1), pair.group(2)
            css_chunk_map[cid] = f"{css_prefix}{cname}{css_suffix}"
            queue.add(css_chunk_map[cid])

    for p, txt in initial_texts.items():
        p_dir = os.path.dirname(p)
        if m_fu:
            for em in re.finditer(r"\.e\((\d+)\)", txt):
                cid = em.group(1)
                if cid not in js_chunk_map:
                    queue.add(f"{js_prefix}{cid}{js_suffix}")
        for am in re.finditer(
            r'["\'(]([a-zA-Z0-9_./-]+\.(?:png|jpg|jpeg|svg|ico|ttf|woff|woff2|otf|wasm|json|css|js))["\')]',
            txt,
        ):
            ap = am.group(1)
            if ap.startswith(("http://", "https://", "data:")):
                continue
            if build_hash and ap.startswith(build_hash + "/"):
                queue.add(ap)
            elif p.endswith(".css") and not ap.startswith("/"):
                norm = os.path.normpath(os.path.join(p_dir, ap)).replace("\\", "/")
                queue.add(norm)

    for _ in range(4):
        pending = [p for p in queue if p not in downloaded]
        if not pending:
            break
        new_items = set()
        with ThreadPoolExecutor(max_workers=6) as pool:
            futs = {pool.submit(save_asset, p): p for p in pending}
            for fut in as_completed(futs):
                p = futs[fut]
                data = fut.result()
                if not data:
                    continue
                if p.endswith((".js", ".css", ".html", ".json")):
                    txt = data.decode("utf-8", errors="ignore")
                    p_dir = os.path.dirname(p)
                    if m_fu:
                        for em in re.finditer(r"\.e\((\d+)\)", txt):
                            cid = em.group(1)
                            if cid not in js_chunk_map:
                                new_items.add(f"{js_prefix}{cid}{js_suffix}")
                    for am in re.finditer(
                        r'["\'(]([a-zA-Z0-9_./-]+\.(?:png|jpg|jpeg|svg|ico|ttf|woff|woff2|otf|wasm|json|css|js))["\')]',
                        txt,
                    ):
                        ap = am.group(1)
                        if ap.startswith(("http://", "https://", "data:")):
                            continue
                        if build_hash and ap.startswith(build_hash + "/"):
                            new_items.add(ap)
                        elif p.endswith(".css") and not ap.startswith("/"):
                            norm = os.path.normpath(os.path.join(p_dir, ap)).replace("\\", "/")
                            new_items.add(norm)
        queue.update(new_items)

    print(f"[+] Offline bundle complete: {len(downloaded)} files saved to {OUT_DIR}")


if __name__ == "__main__":
    main()
