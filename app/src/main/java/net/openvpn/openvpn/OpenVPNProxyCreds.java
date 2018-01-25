package net.openvpn.openvpn;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
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

public class OpenVPNProxyCreds extends OpenVPNClientBase implements OnClickListener, OnEditorActionListener {
    private static final String TAG = "OpenVPNProxyCreds";
    Button cancel_button;
    Button ok_button;
    EditText password_edit;
    private PrefUtil prefs;
    TextView prev_creds_not_accepted_textview;
    String profile_name;
    String proxy_name;
    TextView proxy_title_textview;
    CheckBox remember_creds_checkbox;
    private Handler ui_reset_timer_handler = new Handler();
    private Runnable ui_reset_timer_task = new Runnable() {
        public void run() {
            OpenVPNProxyCreds.this.gen_proxy_context_expired_event();
            OpenVPNProxyCreds.this.finish();
        }
    };
    EditText username_edit;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proxy_creds);
        this.prefs = new PrefUtil(PreferenceManager.getDefaultSharedPreferences(this));
        this.prev_creds_not_accepted_textview = (TextView) findViewById(R.id.prev_creds_not_accepted);
        this.proxy_title_textview = (TextView) findViewById(R.id.proxy_creds_title);
        this.username_edit = (EditText) findViewById(R.id.proxy_username);
        this.password_edit = (EditText) findViewById(R.id.proxy_password);
        this.remember_creds_checkbox = (CheckBox) findViewById(R.id.proxy_remember_creds);
        this.ok_button = (Button) findViewById(R.id.proxy_ok_button);
        this.cancel_button = (Button) findViewById(R.id.proxy_cancel_button);
        this.ok_button.setOnClickListener(this);
        this.cancel_button.setOnClickListener(this);
        this.password_edit.setOnEditorActionListener(this);
        doBindService();
    }

    public void onClick(View v) {
        Log.d(TAG, "onClick");
        int viewid = v.getId();
        if (viewid == R.id.proxy_ok_button) {
            if (!(this.proxy_name == null || this.profile_name == null)) {
                String prefix = OpenVPNService.INTENT_PREFIX;
                startService(new Intent(this, OpenVPNService.class).setAction(OpenVPNService.ACTION_SUBMIT_PROXY_CREDS).putExtra(prefix + ".PROFILE", this.profile_name).putExtra(prefix + ".PROXY_NAME", this.proxy_name).putExtra(prefix + ".PROXY_USERNAME", this.username_edit.getText().toString()).putExtra(prefix + ".PROXY_PASSWORD", this.password_edit.getText().toString()).putExtra(prefix + ".PROXY_REMEMBER_CREDS", this.remember_creds_checkbox.isChecked()));
            }
            finish();
        } else if (viewid == R.id.proxy_cancel_button) {
            finish();
        }
    }

    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (!action_enter(actionId, event) || v != this.password_edit) {
            return false;
        }
        onClick(this.ok_button);
        return true;
    }

    private void cancel_ui_reset() {
        this.ui_reset_timer_handler.removeCallbacks(this.ui_reset_timer_task);
    }

    private void schedule_ui_reset(long delay) {
        cancel_ui_reset();
        this.ui_reset_timer_handler.postDelayed(this.ui_reset_timer_task, delay);
    }

    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        cancel_ui_reset();
        stop();
        super.onDestroy();
    }

    private void stop() {
        doUnbindService();
    }

    protected void post_bind() {
        Intent intent = getIntent();
        if (intent != null) {
            this.profile_name = intent.getStringExtra("net.openvpn.openvpn.PROFILE");
            this.proxy_name = intent.getStringExtra("net.openvpn.openvpn.PROXY_NAME");
            if (this.proxy_name != null) {
                this.proxy_title_textview.setText(String.format(resString(R.string.proxy_creds_title), new Object[]{this.proxy_name}));
                if (intent.getIntExtra("net.openvpn.openvpn.N_RETRIES", 0) > 0) {
                    this.prev_creds_not_accepted_textview.setVisibility(0);
                }
                long expires = intent.getLongExtra("net.openvpn.openvpn.EXPIRES", 0);
                if (expires > 0) {
                    long remaining_time = expires - SystemClock.elapsedRealtime();
                    if (remaining_time > 0) {
                        schedule_ui_reset(remaining_time);
                        return;
                    } else {
                        finish();
                        return;
                    }
                }
                return;
            }
            finish();
        }
    }
}
