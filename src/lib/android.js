const NATIVE_INTERFACE = window['__ANDROID__'] || null;

function nativeCall(api, ...args) {
  if (NATIVE_INTERFACE) {
    NATIVE_INTERFACE[api](...args);
  }
}

export function showToast(text) {
  nativeCall('showToast', text);
}
