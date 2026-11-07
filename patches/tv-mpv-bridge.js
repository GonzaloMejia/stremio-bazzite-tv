/**
 * Stremio TV <-> Linux Shell Native MPV & Gamepad Bridge
 */
(function initQtTransport() {
  window.qt = window.qt || {};
  const transport = window.qt.webChannelTransport || {};
  window.qt.webChannelTransport = transport;

  let appOnMessage = typeof transport.onmessage === 'function' ? transport.onmessage : null;
  const inboundQueue = [];

  Object.defineProperty(transport, 'onmessage', {
    configurable: true,
    enumerable: true,
    get: function() {
      return function(msg) {
        if (typeof appOnMessage === 'function') {
          try { return appOnMessage.call(transport, msg); } catch (e) { console.error(e); }
        } else {
          inboundQueue.push(msg);
        }
      };
    },
    set: function(fn) {
      if (typeof fn === 'function') {
        appOnMessage = fn;
        while (inboundQueue.length > 0) {
          const m = inboundQueue.shift();
          try { appOnMessage.call(transport, m); } catch (e) { console.error(e); }
        }
      }
    }
  });
})();

;(function() {
    'use strict';
    if (window.__STREMIO_TV_BRIDGE_INITIALIZED__) return;
    window.__STREMIO_TV_BRIDGE_INITIALIZED__ = true;

    fetch('http://127.0.0.1:11471/__log?msg=bridge-loaded').catch(() => {});

    // IPC Helper
    function interceptAndSend(message) {
        try {
            if (window.qt && window.qt.webChannelTransport) window.qt.webChannelTransport.send(message);
            else if (window.ipc) window.ipc.postMessage(typeof message === 'string' ? message : JSON.stringify(message));
        } catch (e) { console.error("[Bridge] IPC Error:", e); }
    }

    // Subtitle Raising
    try {
        const style = document.createElement('style');
        style.textContent = `
            body.stremio-controls-visible [class*="subtitle-container"],
            body.stremio-controls-visible .stremio-video-subtitles {
                bottom: 150px !important;
                transform: translateY(-130px) !important;
                position: absolute !important;
                transition: transform 0.2s ease !important;
            }
        `;
        document.head.appendChild(style);
        
        let isControlsVisible = false;
        const check = () => {
            if (!window.location.hash.startsWith('#/player')) return;
            const controls = document.querySelector('[class*="control-bar-container"]');
            const visible = controls && controls.offsetWidth > 0 && controls.offsetHeight > 0 && getComputedStyle(controls).opacity !== '0';
            if (visible !== isControlsVisible) {
                isControlsVisible = visible;
                document.body.classList.toggle('stremio-controls-visible', visible);
                interceptAndSend({type: 6, args: ['mpv-set-prop', ['sub-pos', visible ? 75 : 100]]});
            }
        };
        new MutationObserver(check).observe(document.body, { attributes: true, subtree: true });
        ['mousemove', 'pointerdown', 'keydown', 'wheel'].forEach(evt => window.addEventListener(evt, check, true));
    } catch(e) {}

    // Input Fixes
    function dispatchSyntheticKey(key, code, keyCode) {
        const target = document.activeElement && document.activeElement !== document.body ? document.activeElement : document;
        target.dispatchEvent(new KeyboardEvent('keydown', { key: key, code: code, keyCode: keyCode, which: keyCode, bubbles: true, cancelable: true }));
        target.dispatchEvent(new KeyboardEvent('keyup', { key: key, code: code, keyCode: keyCode, which: keyCode, bubbles: true, cancelable: true }));
    }

    const FOCUSABLE_SELECTOR = '[tabindex]:not([tabindex="-1"]), a[href], button, input, select, [role="button"], [class*="tile"], [class*="poster"], [class*="item-container"], [class*="option"], [class*="button-container"], [class*="control"]';
    function findFocusableTile(el) { return el ? (el.closest('[tabindex]') || el.closest(FOCUSABLE_SELECTOR)) : null; }

    window.addEventListener('mousemove', (e) => {
        document.documentElement.style.cursor = 'auto';
        const hit = document.elementFromPoint(e.clientX, e.clientY);
        const focusTarget = findFocusableTile(hit);
        if (focusTarget && focusTarget !== document.activeElement) focusTarget.focus({ preventScroll: true });
    }, true);

    window.addEventListener('mousedown', (e) => {
        if (e.button === 0) {
            const focusTarget = findFocusableTile(e.target);
            if (focusTarget) focusTarget.focus({ preventScroll: true });
        }
    }, true);

    window.addEventListener('click', (e) => {
        if (!e.isTrusted) return;
        const target = findFocusableTile(e.target);
        if (target) {
            target.focus({ preventScroll: true });
            setTimeout(() => dispatchSyntheticKey('Enter', 'Enter', 13), 40);
        }
    }, true);

    window.addEventListener('contextmenu', (e) => {
        e.preventDefault();
        dispatchSyntheticKey('Escape', 'Escape', 27);
        setTimeout(() => window.history.back(), 50);
    }, true);

    window.addEventListener('keydown', (e) => {
        if (['BrowserBack', 'XF86Back', 'GoBack', 'Backspace', 'Escape'].includes(e.key)) {
            e.preventDefault();
            window.history.back();
        }
    }, true);

    // Gamepad SSE Receiver
    const sse = new EventSource('http://127.0.0.1:11471/__gamepad_stream');
    sse.onmessage = (e) => {
        const { key, keyCode } = JSON.parse(e.data);
        dispatchSyntheticKey(key, key, keyCode);
    };

})();
