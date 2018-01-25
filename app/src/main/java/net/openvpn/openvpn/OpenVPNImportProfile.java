package net.openvpn.openvpn;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import net.openvpn.openvpn.HttpsClient.AuthContext;
import net.openvpn.openvpn.HttpsClient.CancelDetect.I;

public class OpenVPNImportProfile extends OpenVPNClientBase implements OnClickListener, OnEditorActionListener, I {
    private static final String TAG = "OpenVPNImportProfile";
    private int generation;
    private PrefUtil prefs;
    private Set<String> server_history;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.generation = 1;
        setContentView(R.layout.import_profile);
        this.prefs = new PrefUtil(PreferenceManager.getDefaultSharedPreferences(this));
        this.server_history = this.prefs.get_string_set("import_server_history");
        if (this.server_history == null) {
            this.server_history = new HashSet();
        }
        Button cancel_button = (Button) findViewById(R.id.import_cancel_button);
        ((Button) findViewById(R.id.import_button)).setOnClickListener(this);
        cancel_button.setOnClickListener(this);
        ((TextView) findViewById(R.id.password)).setOnEditorActionListener(this);
        set_ui_state(false);
        set_server_history_autocomplete();
        doBindService();
    }

    public int cancel_generation() {
        return this.generation;
    }

    public static void forget_server_history(PrefUtil prefs) {
        prefs.delete_key("import_server_history");
    }

    private void set_ui_state(boolean pending) {
        Button import_button = (Button) findViewById(R.id.import_button);
        ProgressBar progress = (ProgressBar) findViewById(R.id.import_progress);
        if (pending) {
            import_button.setEnabled(false);
            progress.setVisibility(0);
            return;
        }
        import_button.setEnabled(true);
        progress.setVisibility(8);
    }

    private void set_server_history_autocomplete() {
        ((AutoCompleteTextView) findViewById(R.id.import_server)).setAdapter(new ArrayAdapter(this, R.layout.import_server_item, (String[]) Arrays.copyOf(this.server_history.toArray(), this.server_history.size(), String[].class)));
    }

    private void add_to_server_history(String server) {
        this.server_history.add(server);
        this.prefs.set_string_set("import_server_history", this.server_history);
    }

    public void onClick(View v) {
        Log.d(TAG, "onClick");
        int viewid = v.getId();
        if (viewid == 2131427468) {
            this.generation++;
            EditText username_edit = (EditText) findViewById(R.id.username);
            EditText password_edit = (EditText) findViewById(R.id.password);
            CheckBox autologin_checkbox = (CheckBox) findViewById(R.id.import_autologin_checkbox);
            final String server = ((EditText) findViewById(R.id.import_server)).getText().toString();
            String username = username_edit.getText().toString();
            AuthContext ac = new AuthContext(server, username, OpenVPNDebug.pw_repl(username, password_edit.getText().toString()));
            if (server.length() > 0 && username.length() > 0) {
                importProfileRemote(ac, autologin_checkbox.isChecked(), this, new Runnable() {
                    public void run() {
                        OpenVPNImportProfile.this.add_to_server_history(server);
                        OpenVPNImportProfile.this.finish();
                    }
                }, new Runnable() {
                    public void run() {
                        OpenVPNImportProfile.this.generation = OpenVPNImportProfile.this.generation + 1;
                        OpenVPNImportProfile.this.set_ui_state(false);
                    }
                }, null, true, true);
                set_ui_state(true);
            }
        } else if (viewid == 2131427469) {
            this.generation++;
            finish();
        }
    }

    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        TextView password_edit = (TextView) findViewById(R.id.password);
        if (!action_enter(actionId, event) || v != password_edit) {
            return false;
        }
        onClick((Button) findViewById(R.id.import_button));
        return true;
    }

    private void clear_auth() {
        EditText password_edit = (EditText) findViewById(R.id.password);
        if (password_edit != null) {
            password_edit.setText(BuildConfig.FLAVOR);
        }
    }

    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        stop();
        super.onDestroy();
    }

    private void stop() {
        this.generation++;
        clear_auth();
        doUnbindService();
    }

    protected void post_bind() {
        Log.d(TAG, "post_bind");
    }
}
