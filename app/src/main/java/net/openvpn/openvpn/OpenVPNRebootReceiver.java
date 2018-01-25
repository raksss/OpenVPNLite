package net.openvpn.openvpn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class OpenVPNRebootReceiver extends BroadcastReceiver {
    private static final String TAG = "OpenVPNRebootReceiver";

    public void onReceive(Context context, Intent intent) {
        OpenVPNClientBase.autostart(context);
    }
}
