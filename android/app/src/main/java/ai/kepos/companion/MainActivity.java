package ai.kepos.companion;

import android.util.Log;
import android.webkit.WebView;
import com.getcapacitor.BridgeActivity;
import com.getcapacitor.WebViewListener;

public class MainActivity extends BridgeActivity {

    private static final String TAG = "KeposAI";
    private boolean keepAliveRequested;

    @Override
    protected void load() {
        bridgeBuilder.addWebViewListener(
            new WebViewListener() {
                @Override
                public void onPageLoaded(WebView webView) {
                    if (isConfiguredCompanionPage(webView) && !keepAliveRequested) {
                        keepAliveRequested = true;
                        enableBackgroundMode();
                    }
                }
            }
        );

        super.load();
    }

    private boolean isConfiguredCompanionPage(WebView webView) {
        return bridge != null && bridge.getAppUrl() != null && bridge.getAppUrl().equals(webView.getUrl());
    }

    private void enableBackgroundMode() {
        bridge.eval(
            "(async () => {" +
            "const backgroundMode = window.Capacitor.registerPlugin('BackgroundMode');" +
            "const permission = await backgroundMode.checkNotificationsPermission();" +
            "if (permission.notifications !== 'granted') {" +
            "const requested = await backgroundMode.requestNotificationsPermission();" +
            "if (requested.notifications !== 'granted') {" +
            "console.error('KeposAI background keepalive requires notification permission');" +
            "return;" +
            "}" +
            "}" +
            "await backgroundMode.enable({" +
            "title: 'KeposAI'," +
            "text: 'KeposAI Companion is active in the background'," +
            "channelName: 'KeposAI'," +
            "channelDescription: 'Keeps the KeposAI Companion connection active'," +
            "icon: 'ic_launcher'," +
            "hidden: false," +
            "disableWebViewOptimization: true" +
            "});" +
            "})().catch((error) => console.error('KeposAI background keepalive failed', error));",
            null
        );
        Log.i(TAG, "Requested Companion background keepalive");
    }
}
