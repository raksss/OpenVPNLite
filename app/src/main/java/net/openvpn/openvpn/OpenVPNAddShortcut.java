package net.openvpn.openvpn;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import net.openvpn.openvpn.OpenVPNService.ProfileList;

public class OpenVPNAddShortcut extends OpenVPNClientBase implements OnClickListener, OnItemSelectedListener, OnEditorActionListener {
    private static final String TAG = "OpenVPNAddShortcut";
    private Button cancel_button;
    private Button create_button;
    private PrefUtil prefs;
    private Spinner profile_spin;
    private EditText shortcut_name_edit;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_shortcut);
        this.prefs = new PrefUtil(PreferenceManager.getDefaultSharedPreferences(this));
        this.profile_spin = (Spinner) findViewById(R.id.profile);
        this.shortcut_name_edit = (EditText) findViewById(R.id.shortcut_name);
        this.create_button = (Button) findViewById(R.id.create_shortcut_button);
        this.cancel_button = (Button) findViewById(R.id.cancel_shortcut_button);
        this.create_button.setOnClickListener(this);
        this.cancel_button.setOnClickListener(this);
        this.profile_spin.setOnItemSelectedListener(this);
        this.shortcut_name_edit.setOnEditorActionListener(this);
        doBindService();
    }

    public void onClick(View v) {
        Log.d(TAG, "onClick");
        if (v.getId() == R.id.create_shortcut_button) {
            createConnectShortcut(SpinUtil.get_spinner_selected_item(this.profile_spin), this.shortcut_name_edit.getText().toString());
        }
        finish();
    }

	private void createConnectShortcut(String _spinner_selected_item, String toString)
	{
		// TODO: Implement this method
	}

    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (!action_enter(actionId, event) || v != this.shortcut_name_edit) {
            return false;
        }
        onClick(this.create_button);
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
        String prof_name;
        String[] prof_name_array;
        ProfileList proflist = profile_list();
        if (proflist == null || proflist.size() <= 0) {
            this.profile_spin.setEnabled(false);
            this.shortcut_name_edit.setEnabled(false);
            this.create_button.setEnabled(false);
            prof_name = resString(R.string.shortcut_no_profiles);
            prof_name_array = new String[]{prof_name};
        } else {
            prof_name = current_profile().get_name();
            prof_name_array = proflist.profile_names();
        }
        SpinUtil.show_spinner(this, this.profile_spin, prof_name_array);
        SpinUtil.set_spinner_selected_item(this.profile_spin, prof_name);
        set_shortcut_name(prof_name);
    }

    public void onItemSelected(AdapterView<?> adapterView, View v, int position, long id) {
        set_shortcut_name(SpinUtil.get_spinner_selected_item(this.profile_spin));
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    private void set_shortcut_name(String name) {
        if (name != null) {
            this.shortcut_name_edit.setText(name);
            this.shortcut_name_edit.selectAll();
            this.shortcut_name_edit.requestFocus();
        }
    }
}
