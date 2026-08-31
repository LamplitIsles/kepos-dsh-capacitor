import type { CapacitorConfig } from "@capacitor/cli";

const config: CapacitorConfig = {
  appId: "ai.kepos.companion",
  appName: "KeposAI",
  webDir: "www",
  server: {
    url: "http://127.0.0.1:13080/companion",
    cleartext: true,
    allowNavigation: ["127.0.0.1"]
  }
};

export default config;
