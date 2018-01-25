package net.openvpn.openvpn;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import java.io.IOException;
import net.openvpn.openvpn.FileUtil.FileTooLarge;
import net.openvpn.openvpn.OpenVPNService.MergedProfile;

public class OpenVPNAttachmentReceiver extends OpenVPNClientBase implements OnClickListener {
    private static final String TAG = "OpenVPNAttachmentReceiver";
    private Button accept_button;
    private Button cancel_button;
    private Uri filePathUri;
    private MergedProfile profile;
    private String profileContent;
    private TextView profile_description;
    private TextView profile_name;
    private TextView profile_note;
    private ReadError readError = ReadError.NONE;

    private enum ReadError {
        NONE,
        OTHER,
        PROFILE_TOO_LARGE
		}

    private void setReadError(Exception e) {
        if (e instanceof FileTooLarge) {
            this.readError = ReadError.PROFILE_TOO_LARGE;
        } else {
            this.readError = ReadError.OTHER;
        }
    }

    private String basename() {
        String bn = FileUtil.uriBasename(this.filePathUri);
        if (bn == null || !bn.endsWith(".ovpn")) {
            return "client.ovpn";
        }
        return bn;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, String.format("onCreate intent=%s", new Object[]{getIntent().toString()}));
        this.profileContent = null;
		Intent intent = getIntent();
        this.readError = ReadError.NONE;
        this.filePathUri = intent.getData();
        if (this.filePathUri != null) {
            Log.d(TAG, String.format("import path=%s", new Object[]{this.filePathUri.toString()}));
            if (this.filePathUri != null) {
                try {
                    String path = this.filePathUri.getPath();
                    if (path != null) {
                        this.profileContent = FileUtil.readFile(path, max_profile_size());
                    }
                } catch (IOException e) {
                    Log.e(TAG, "profile read error via file URI", e);
                    setReadError(e);
                }
                try {
                    if (this.profileContent == null && this.readError != ReadError.PROFILE_TOO_LARGE) {
                        this.profileContent = FileUtil.readUri(this, this.filePathUri, max_profile_size());
                    }
                } catch (IOException e2) {
                    Log.e(TAG, "profile read error via content URI", e2);
                    setReadError(e2);
                }
            }
        }
        if (this.filePathUri == null || this.profileContent == null) {
            String str = TAG;
            String str2 = "error reading profile from %s";
            Object[] objArr = new Object[1];
            objArr[0] = this.filePathUri != null ? this.filePathUri.toString() : "NULL";
            Log.i(str, String.format(str2, objArr));
            if (this.readError == ReadError.OTHER || this.readError == ReadError.NONE) {
                do_finish();
                return;
            }
        }
        setContentView(R.layout.attachment_receiver);
        this.profile_name = (TextView) findViewById(R.id.ar_profile_name);
        this.profile_description = (TextView) findViewById(R.id.ar_profile_description);
        this.profile_note = (TextView) findViewById(R.id.ar_profile_note);
        this.profile_note.setVisibility(8);
        this.accept_button = (Button) findViewById(R.id.ar_accept_button);
        this.accept_button.setOnClickListener(this);
        this.accept_button.setEnabled(false);
        this.cancel_button = (Button) findViewById(R.id.ar_cancel_button);
        this.cancel_button.setOnClickListener(this);
        doBindService();
    }

    private void launch_main_page() {
        Intent i = new Intent(this, OpenVPNClient.class);
        i.addFlags(67108864);
        try {
            startActivity(i);
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "launch_main_page", e);
        }
    }

    private void do_finish() {
        launch_main_page();
        finish();
    }

    public void onClick(View v) {
        Log.d(TAG, "onClick");
        int viewid = v.getId();
        if (viewid == R.id.ar_accept_button) {
            Log.d(TAG, "Accept button");
            if (!(this.filePathUri == null || this.profile == null || this.profile.profile_content == null)) {
                submitImportProfileIntent(this.profile.profile_content, basename(), false);
            }
            do_finish();
        } else if (viewid == R.id.ar_cancel_button) {
            Log.d(TAG, "Cancel button");
            do_finish();
        }
    }

    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        doUnbindService();
        super.onDestroy();
    }

    protected void post_bind() {
        boolean exists = true;
        Log.d(TAG, "post_bind");
        if (this.filePathUri != null && this.profileContent != null) {
            String error;
            this.profile = merge_parse_profile(basename(), this.profileContent);
            if (this.profile != null) {
                error = this.profile.get_error();
                if (error == null) {
                    String name = this.profile.get_name();
                    String description = this.profile.get_type_string();
                    this.profile_name.setText(name);
                    this.profile_description.setText(description);
                    this.accept_button.setEnabled(true);
                    if (profile_list().get_profile_by_name(name) == null) {
                        exists = false;
                    }
                    if (exists) {
                        this.profile_note.setText(resString(R.string.ar_warn_replace));
                        this.profile_note.setVisibility(0);
                    }
                }
            } else {
                error = "error parsing profile";
            }
            if (error != null) {
                setError(error);
            }
        } else if (this.readError == ReadError.PROFILE_TOO_LARGE) {
            setError(String.format(resString(R.string.profile_too_large), new Object[]{Long.valueOf(max_profile_size())}));
        } else {
            Log.i(TAG, "internal error in post_bind");
            do_finish();
        }
    }

    private void setError(String text) {
        if (this.filePathUri != null) {
            this.profile_name.setText(basename());
        }
        this.profile_description.setText(text);
        this.profile_description.setTextColor(Color.parseColor("#ff8080"));
        this.profile_description.setTypeface(null, 1);
    }
}

