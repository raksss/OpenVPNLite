package net.openvpn.openvpn;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import net.openvpn.openvpn.OpenVPNService.LogMsg;

public class OpenVPNLog extends OpenVPNClientBase implements OnClickListener {
    private static final String TAG = "OpenVPNClientLog";
    private Button mPause;
    private Button mResume;
    private ScrollView mScrollView;
    private TextView mTextView;
    private ArrayList<LogMsg> pause_buffer;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log);
        this.mTextView = (TextView) findViewById(R.id.log_textview);
        this.mScrollView = (ScrollView) findViewById(R.id.log_scrollview);
        this.mPause = (Button) findViewById(R.id.log_pause);
        this.mResume = (Button) findViewById(R.id.log_resume);
        this.mPause.setOnClickListener(this);
        this.mResume.setOnClickListener(this);
        this.mTextView.setMovementMethod(ScrollingMovementMethod.getInstance());
        doBindService();
    }

    private void set_pause_state(boolean state) {
        if (state) {
            this.mPause.setVisibility(8);
            this.mResume.setVisibility(0);
            this.pause_buffer = new ArrayList();
            return;
        }
        this.mPause.setVisibility(0);
        this.mResume.setVisibility(8);
        if (this.pause_buffer != null) {
            Iterator it = this.pause_buffer.iterator();
            while (it.hasNext()) {
                this.mTextView.append(((LogMsg) it.next()).line);
            }
            scroll_textview_to_bottom();
            this.pause_buffer = null;
        }
    }

    private void scroll_textview_to_bottom() {
        this.mScrollView.post(new Runnable() {
            public void run() {
                OpenVPNLog.this.mScrollView.smoothScrollTo(0, OpenVPNLog.this.mTextView.getBottom());
            }
        });
    }

    private void refresh_log_view() {
        ArrayDeque<LogMsg> hist = log_history();
        if (hist != null) {
            StringBuilder builder = new StringBuilder();
            Iterator it = hist.iterator();
            while (it.hasNext()) {
                builder.append(((LogMsg) it.next()).line);
            }
            this.mTextView.setText(builder.toString());
            scroll_textview_to_bottom();
        }
    }

    public void onClick(View v) {
        Log.d(TAG, "LOG: onClick");
        int viewid = v.getId();
        if (viewid == R.id.log_pause) {
            Log.d(TAG, "LOG: onClick PAUSE");
            set_pause_state(true);
        } else if (viewid == R.id.log_resume) {
            Log.d(TAG, "LOG: onClick RESUME");
            set_pause_state(false);
        }
    }

    public void log(LogMsg lm) {
        if (this.pause_buffer == null) {
            this.mTextView.append(lm.line);
            scroll_textview_to_bottom();
            return;
        }
        this.pause_buffer.add(lm);
    }

    protected void onDestroy() {
        Log.d(TAG, "LOG: onDestroy");
        stop();
        super.onDestroy();
    }

    private void stop() {
        doUnbindService();
    }

    protected void post_bind() {
        Log.d(TAG, "LOG: post_bind");
        refresh_log_view();
        set_pause_state(false);
    }
}
