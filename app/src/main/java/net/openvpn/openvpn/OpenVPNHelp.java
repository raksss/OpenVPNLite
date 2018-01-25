package net.openvpn.openvpn;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import java.io.IOException;
import java.util.Locale;

public class OpenVPNHelp extends OpenVPNClientBase {
    private static final String TAG = "OpenVPNHelp";

    public void onCreate(Bundle savedInstanceState) {
        String help_content;
        super.onCreate(savedInstanceState);
        String language = Locale.getDefault().getLanguage();
        String help_dir = String.format("help/%s", new Object[]{language});
        Log.d(TAG, String.format("Localized help directory: %s", new Object[]{help_dir}));
        try {
            help_content = FileUtil.readAsset(this, String.format("%s/index.html", new Object[]{help_dir}));
        } catch (IOException e) {
            help_content = null;
            help_dir = "help/default";
        }
        if (help_content == null) {
            try {
                help_content = FileUtil.readAsset(this, String.format("%s/index.html", new Object[]{help_dir}));
            } catch (IOException e2) {
                Log.e(TAG, "error reading help file", e2);
                finish();
            }
        }
        WebView webview = new WebView(this);
        webview.getSettings().setBuiltInZoomControls(true);
        setContentView(webview);
        webview.loadData(help_content.replaceAll("\\n+", "%20"), "text/html", "UTF-8");
    }
}
