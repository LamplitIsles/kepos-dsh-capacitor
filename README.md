# KeposAI

An Android Capacitor entry app for the Kepos local web application running on
the same device at `http://127.0.0.1:13080`.

## Build and install

```sh
bun install
bun run android:install
```

The local build uses Homebrew OpenJDK 21, as required by Capacitor 8.

## Android background keepalive

KeposAI enables the `@anuradev/capacitor-background-mode` foreground service
after the configured local Companion page finishes loading. The service keeps
the Companion WebView eligible to run while the app is backgrounded and shows
an ongoing, user-visible KeposAI notification.

Before testing the keepalive, grant notification access when Android prompts
for it and disable battery optimization for KeposAI in Android system
settings. Battery optimization is a precondition for this spike; KeposAI does
not provide a settings flow or fallback for optimized devices.

### Manual device verification

With an attached Android device and the local Companion service running:

1. Build, synchronize, install, and launch the debug app with
   `bun run android:install`.
2. Grant notification access if prompted and confirm the ongoing KeposAI
   notification is visible.
3. Switch away from KeposAI for a practical interval (for example, several
   minutes), then return to the app.
4. Confirm the Companion remains connected and does not show its ordinary DSH
   loading/reconnect path. Capture `adb logcat` output if connection behavior
   needs to be recorded.

This is an Android-device spike, not a guarantee of indefinite process
survival. Android may still terminate the foreground service. Removing KeposAI
from Recents stops the dependency's `START_NOT_STICKY` service, so the
keepalive does not survive that action.
