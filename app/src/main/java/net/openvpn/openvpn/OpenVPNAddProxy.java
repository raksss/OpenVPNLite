package net.openvpn.openvpn;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import net.openvpn.openvpn.ProxyList.Item;

public class OpenVPNAddProxy extends OpenVPNClientBase implements OnClickListener, OnEditorActionListener {
    private static final String TAG = "OpenVPNAddProxy";
    CheckBox allow_cleartext_auth_checkbox;
    Button cancel_button;
    EditText friendly_name_edit;
    EditText host_edit;
    String mod_proxy_name;
    EditText port_edit;
    private PrefUtil prefs;
    Button save_button;
    TextView title_textview;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_proxy);
        this.prefs = new PrefUtil(PreferenceManager.getDefaultSharedPreferences(this));
        this.title_textview = (TextView) findViewById(R.id.proxy_title);
        this.friendly_name_edit = (EditText) findViewById(R.id.proxy_friendly_name);
        this.host_edit = (EditText) findViewById(R.id.proxy_host);
        this.port_edit = (EditText) findViewById(R.id.proxy_port);
        this.allow_cleartext_auth_checkbox = (CheckBox) findViewById(R.id.proxy_allow_cleartext_auth_checkbox);
        this.save_button = (Button) findViewById(R.id.proxy_save_button);
        this.cancel_button = (Button) findViewById(R.id.proxy_cancel_button);
        this.save_button.setOnClickListener(this);
        this.cancel_button.setOnClickListener(this);
        this.port_edit.setOnEditorActionListener(this);
        doBindService();
    }

    public void onClick(View v) {
        Log.d(TAG, "onClick");
        int viewid = v.getId();
        if (viewid == R.id.proxy_save_button) {
            ProxyList proxy_list = get_proxy_list();
            if (proxy_list != null) {
                Item item = new Item();
                String friendly_name = this.friendly_name_edit.getText().toString().trim();
                if (friendly_name.length() > 0) {
                    item.friendly_name = friendly_name;
                }
                item.host = this.host_edit.getText().toString().trim();
                item.port = this.port_edit.getText().toString().trim();
                item.allow_cleartext_auth = this.allow_cleartext_auth_checkbox.isChecked();
                if (item.is_valid()) {
                    String name = item.name();
                    if (!name.equals(this.mod_proxy_name)) {
                        proxy_list.remove(this.mod_proxy_name);
                    }
                    proxy_list.put(item);
                    proxy_list.set_enabled(name);
                    proxy_list.save();
                    gen_ui_reset_event(false);
                    finish();
                    return;
                }
                return;
            }
            Log.d(TAG, "proxy_list is null on save!");
            finish();
        } else if (viewid == R.id.proxy_cancel_button) {
            finish();
        }
    }

    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (!action_enter(actionId, event) || v != this.port_edit) {
            return false;
        }
        onClick(this.save_button);
        return true;
    }

    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        stop();
        super.onDestroy();
    }

    private void stop() {
        doUnbindService();
    }

    protected void post_bind() {
        Intent intent = getIntent();
        if (intent != null) {
            this.mod_proxy_name = intent.getStringExtra("net.openvpn.openvpn.PROXY_NAME");
            if (this.mod_proxy_name != null) {
                this.title_textview.setText(R.string.proxy_title_modify);
            }
            ProxyList proxy_list = get_proxy_list();
            if (this.mod_proxy_name != null && proxy_list != null) {
                Item item = proxy_list.get(this.mod_proxy_name);
                if (item != null) {
                    if (item.friendly_name != null) {
                        this.friendly_name_edit.setText(item.friendly_name);
                    }
                    this.host_edit.setText(item.host);
                    this.port_edit.setText(item.port);
                    this.allow_cleartext_auth_checkbox.setChecked(item.allow_cleartext_auth);
                }
            }
        }
    }
}
