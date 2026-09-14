import type { CapacitorConfig } from "@capacitor/cli";

const config: CapacitorConfig = {
  appId: "ai.kepos.companion",
  appName: "KeposAI",
  webDir: "www",
  server: {
    url: "http://prod-lamplit.localhost:17480/",
    cleartext: true,
    allowNavigation: ["prod-lamplit.localhost"]
  }
};

export default config;
