package net.openvpn.openvpn;

import android.os.Bundle;
import android.util.Log;

public class OpenVPNDisconnect extends OpenVPNClientBase {
    private static final String TAG = "OpenVPNDisconnect";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "disconnect");
        submitDisconnectIntent(false);
        finish();
    }
}
