package net.openvpn.openvpn;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import java.io.IOException;

public class OpenVPNAbout extends OpenVPNClientBase {
    private static final String TAG = "OpenVPNAbout";

	private Object expire_string;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        String versionName = "0.0";
        int versionCode = 0;
        try {
            PackageInfo pi = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionName = pi.versionName;
            versionCode = pi.versionCode;
        } catch (Exception e) {
            Log.e(TAG, "cannot obtain version info", e);
        }
        get_text_view(R.id.about_text).setText(String.format(resString(R.string.about_text), new Object[]{versionName, Integer.valueOf(versionCode)}));
        get_text_view(R.id.core_text).setText(OpenVPNClientBase.get_openvpn_core_platform());
        if (OpenVPNClientBase.get_app_expire_string() != null) {
            get_text_view(R.id.about_beta_expire_warn).setText(String.format(resString(R.string.beta_expire_warn), new Object[]{expire_string}));
        } else {
            findViewById(R.id.about_expire_group).setVisibility(8);
        }
        try {
            get_text_view(R.id.about_textview).setText(FileUtil.readAsset(this, "about.txt"));
        } catch (IOException e2) {
            Log.e(TAG, "Error opening about.txt", e2);
        }
    }

    private TextView get_text_view(int res_id) {
        return (TextView) findViewById(res_id);
    }
}
