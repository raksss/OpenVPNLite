package net.openvpn.openvpn;
 
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.net.VpnService;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v4.content.ContextCompat;
import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.os.Process;
import android.widget.TextView.OnEditorActionListener;
import net.openvpn.openvpn.OpenVPNService.Challenge;
import net.openvpn.openvpn.OpenVPNService.ConnectionStats;
import net.openvpn.openvpn.OpenVPNService.EventMsg;
import net.openvpn.openvpn.OpenVPNService.Profile;
import net.openvpn.openvpn.OpenVPNService.ProfileList;
 
public class OpenVPNClient extends OpenVPNClientBase implements OnRequestPermissionsResultCallback, OnClickListener, OnTouchListener, OnItemSelectedListener, OnEditorActionListener {
    private static final int REQUEST_IMPORT_PKCS12 = 3;
    private static final int REQUEST_IMPORT_PROFILE = 2;
    private static final int REQUEST_VPN_ACTOR_RIGHTS = 1;
    private static final boolean RETAIN_AUTH = false;
    private static final int S_BIND_CALLED = 1;
    private static final int S_ONSTART_CALLED = 2;
    private static final String TAG = "OpenVPNClient";
    private static final int UIF_PROFILE_SETTING_FROM_SPINNER = 262144;
    private static final int UIF_REFLECTED = 131072;
    private static final int UIF_RESET = 65536;
    private static final boolean UI_OVERLOADED = false;
    private String autostart_profile_name;
    private View button_group;
    private TextView bytes_in_view;
    private TextView bytes_out_view;
    private TextView challenge_view;
    private View conn_details_group;
    private Button connect_button;
    private View cr_group;
    private FinishOnConnect delayed_finish_on_connect = FinishOnConnect.DISABLED;
    private TextView details_more_less;
    private Button disconnect_button;
    private TextView duration_view;
    private FinishOnConnect finish_on_connect = FinishOnConnect.DISABLED;
    private View info_group;
    private boolean last_active = RETAIN_AUTH;
    private TextView last_pkt_recv_view;
    private ScrollView main_scroll_view;
    private EditText password_edit;
    private View password_group;
    private CheckBox password_save_checkbox;
    private EditText pk_password_edit;
    private View pk_password_group;
    private CheckBox pk_password_save_checkbox;
    private View post_import_help_blurb;
    private PrefUtil prefs;
    private ImageButton profile_edit;
    private View profile_group;
    private Spinner profile_spin;
    private ProgressBar progress_bar;
    private ImageButton proxy_edit;
    private View proxy_group;
    private Spinner proxy_spin;
    private PasswordUtil pwds;
    private EditText response_edit;
    private View server_group;
    private Spinner server_spin;
    private int startup_state = 0;
    private View stats_expansion_group;
    private View stats_group;
    private Handler stats_timer_handler = new Handler();
    private Runnable stats_timer_task = new Runnable() {
        public void run() {
            OpenVPNClient.this.show_stats();
            OpenVPNClient.this.schedule_stats();
        }
    };
    private ImageView status_icon_view;
    private TextView status_view;
    private boolean stop_service_on_client_exit = RETAIN_AUTH;
    private View[] textgroups;
    private TextView[] textviews;
    private Handler ui_reset_timer_handler = new Handler();
    private Runnable ui_reset_timer_task = new Runnable() {
        public void run() {
            if (!OpenVPNClient.this.is_active()) {
                OpenVPNClient.this.ui_setup(OpenVPNClient.RETAIN_AUTH, OpenVPNClient.UIF_RESET, null);
            }
        }
    };
    private EditText username_edit;
    private View username_group;

	public void createConnectShortcut(String prof_name, String toString)
	{
		// TODO: Implement this method
	}
 
    private enum FinishOnConnect {
        DISABLED,
        ENABLED,
        ENABLED_ACROSS_ONSTART,
        PENDING
    }
 
    private enum ProfileSource {
        UNDEF,
        SERVICE,
        PRIORITY,
        PREFERENCES,
        SPINNER,
        LIST0
    }
 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String str = TAG;
        Object[] objArr = new Object[S_BIND_CALLED];
        objArr[0] = intent.toString();
        Log.d(str, String.format("CLI: onCreate intent=%s", objArr));
        this.prefs = new PrefUtil(PreferenceManager.getDefaultSharedPreferences(this));
        this.pwds = new PasswordUtil(PreferenceManager.getDefaultSharedPreferences(this));
        init_default_preferences(this.prefs);
        if (this.prefs.get_boolean("ui_dark_theme", RETAIN_AUTH)) {
            setCurrentTheme(16973931);
        } else {
            setCurrentTheme(16973934);
        }
        setContentView(R.layout.form);
        load_ui_elements();
        doBindService();
        warn_app_expiration(this.prefs);
        new AppRate(this).setMinDaysUntilPrompt(14).setMinLaunchesUntilPrompt(10).init();
    }

	private void setCurrentTheme(int p0)
	{
		// TODO: Implement this method
	}
 
    protected void onNewIntent(Intent intent) {
        String str = TAG;
        Object[] objArr = new Object[S_BIND_CALLED];
        objArr[0] = intent.toString();
        Log.d(str, String.format("CLI: onNewIntent intent=%s", objArr));
        setIntent(intent);
    }
 
    protected void post_bind() {
        Log.d(TAG, "CLI: post bind");
        this.startup_state |= S_BIND_CALLED;
        process_autostart_intent(is_active());
        render_last_event();
    }
 
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
 
    public void event(EventMsg ev) {
        render_event(ev, RETAIN_AUTH, is_active(), RETAIN_AUTH);
    }
 
    private void render_last_event() {
        boolean active = is_active();
        EventMsg ev = get_last_event();
        if (ev != null) {
            render_event(ev, true, active, true);
        } else if (n_profiles_loaded() > 0) {
            render_event(EventMsg.disconnected(), true, active, true);
        } else {
            hide_status();
            ui_setup(active, UIF_RESET, null);
            show_progress(0, active);
        }
        EventMsg pev = get_last_event_prof_manage();
        if (pev != null) {
            render_event(pev, true, active, true);
        }
    }
 
    private boolean show_conn_info_field(String text, int field_id, int row_id) {
        int i = 0;
        boolean vis = text.length() > 0 ? true : RETAIN_AUTH;
        TextView tv = (TextView) findViewById(field_id);
        View row = findViewById(row_id);
        tv.setText(text);
        if (!vis) {
            i = 8;
        }
        row.setVisibility(i);
        return vis;
    }
 
    private void reset_conn_info() {
        show_conn_info(new ClientAPI_ConnectionInfo());
    }
 
    private void show_conn_info(ClientAPI_ConnectionInfo ci) {
        this.info_group.setVisibility((((((((RETAIN_AUTH | show_conn_info_field(ci.getVpnIp4(), R.id.ipv4_addr, R.id.ipv4_addr_row)) | show_conn_info_field(ci.getVpnIp6(), R.id.ipv6_addr, R.id.ipv6_addr_row)) | show_conn_info_field(ci.getUser(), R.id.user, R.id.user_row)) | show_conn_info_field(ci.getClientIp(), R.id.client_ip, R.id.client_ip_row)) | show_conn_info_field(ci.getServerHost(), R.id.server_host, R.id.server_host_row)) | show_conn_info_field(ci.getServerIp(), R.id.server_ip, R.id.server_ip_row)) | show_conn_info_field(ci.getServerPort(), R.id.server_port, R.id.server_port_row)) | show_conn_info_field(ci.getServerProto(), R.id.server_proto, R.id.server_proto_row) ? 0 : 8);
        set_visibility_stats_expansion_group();
    }
 
    private void set_visibility_stats_expansion_group() {
        int i = 0;
        boolean expand_stats = this.prefs.get_boolean("expand_stats", RETAIN_AUTH);
        View view = this.stats_expansion_group;
        if (!expand_stats) {
            i = 8;
        }
        view.setVisibility(i);
        this.details_more_less.setText(expand_stats ? R.string.touch_less : R.string.touch_more);
    }
 
    private void render_event(EventMsg ev, boolean reset, boolean active, boolean cached) {
        int flags = ev.flags;
        if (ev.is_reflected(this)) {
            flags |= UIF_REFLECTED;
        }
        if (reset || (flags & 8) != 0 || ev.profile_override != null) {
            ui_setup(active, UIF_RESET | flags, ev.profile_override);
        } else if (ev.res_id == R.string.core_thread_active) {
            active = true;
            ui_setup(true, flags, null);
        } else if (ev.res_id == R.string.core_thread_inactive) {
            active = RETAIN_AUTH;
            ui_setup(RETAIN_AUTH, flags, null);
        }
        switch (ev.res_id) {
            case R.string.connected /*2131034168*/:
                this.main_scroll_view.fullScroll(33);
                break;
            case R.string.info_msg /*2131034237*/:
                if (ev.info.startsWith("OPEN_URL:")) {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(ev.info.substring(9)));
                    intent.putExtra("com.android.browser.application_id", getPackageName());
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                        break;
                    }
                }
                break;
            case R.string.tap_not_supported /*2131034362*/:
                if (!cached) {
                    ok_dialog(resString(R.string.tap_unsupported_title), resString(R.string.tap_unsupported_error));
                    break;
                }
                break;
            case R.string.tun_iface_create /*2131034371*/:
                if (!cached) {
                    ok_dialog(resString(R.string.tun_ko_title), resString(R.string.tun_ko_error));
                    break;
                }
                break;
            case R.string.warn_msg /*2131034390*/:
                this.delayed_finish_on_connect = FinishOnConnect.PENDING;
                final Activity self = this;
                ok_dialog(resString(R.string.warning_title), ev.info, new Runnable() {
                    public void run() {
                        if (!(OpenVPNClient.this.delayed_finish_on_connect == FinishOnConnect.PENDING || OpenVPNClient.this.delayed_finish_on_connect == FinishOnConnect.DISABLED)) {
                            self.finish();
                        }
                        OpenVPNClient.this.delayed_finish_on_connect = FinishOnConnect.DISABLED;
                    }
                });
                break;
        }
        if (ev.priority >= S_BIND_CALLED) {
            if (ev.icon_res_id >= 0) {
                show_status_icon(ev.icon_res_id);
            }
            if (ev.res_id == R.string.connected) {
                show_status(ev.res_id);
                if (ev.conn_info != null) {
                    show_conn_info(ev.conn_info);
                }
            } else if (ev.info.length() > 0) {
                Object[] objArr = new Object[S_ONSTART_CALLED];
                objArr[0] = resString(ev.res_id);
                objArr[S_BIND_CALLED] = ev.info;
                show_status(String.format("%s : %s", objArr));
            } else {
                show_status(ev.res_id);
            }
        }
        show_progress(ev.progress, active);
        show_stats();
        if (ev.res_id == R.string.connected && this.finish_on_connect != FinishOnConnect.DISABLED) {
            if (this.prefs.get_boolean("autostart_finish_on_connect", RETAIN_AUTH)) {
                final OpenVPNClient self = this;
                if (this.delayed_finish_on_connect == FinishOnConnect.PENDING)
				{
                    this.delayed_finish_on_connect = this.finish_on_connect;
                    return;
                }
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        if (OpenVPNClient.this.finish_on_connect != FinishOnConnect.DISABLED) {
                            self.finish();
                        }
                    }
                }, 1000);
                return;
            }
            this.finish_on_connect = FinishOnConnect.DISABLED;
        }
    }

	private void ok_dialog(String resString, String info, Object run)
	{
		// TODO: Implement this method
	}
 
    private void stop_service() {
        submitDisconnectIntent(true);
    }
 
    private void stop() {
        cancel_stats();
        doUnbindService();
        if (this.stop_service_on_client_exit) {
            Log.d(TAG, "CLI: stopping service");
            stop_service();
        }
    }
 
    protected void onStop() {
        Log.d(TAG, "CLI: onStop");
        cancel_stats();
        super.onStop();
    }
 
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "CLI: onStart");
        this.startup_state |= S_ONSTART_CALLED;
        if (this.finish_on_connect == FinishOnConnect.ENABLED) {
            this.finish_on_connect = FinishOnConnect.ENABLED_ACROSS_ONSTART;
        }
        boolean active = is_active();
        if (active) {
            schedule_stats();
        }
        if (process_autostart_intent(active)) {
            ui_setup(active, UIF_RESET, null);
        }
    }
 
    protected void onDestroy() {
        stop();
        Log.d(TAG, "CLI: onDestroy called");
        super.onDestroy();
    }
 
    private boolean process_autostart_intent(boolean active) {
        if ((this.startup_state & REQUEST_IMPORT_PKCS12) == REQUEST_IMPORT_PKCS12) {
            Intent intent = getIntent();
            String apn_key = "net.openvpn.openvpn.AUTOSTART_PROFILE_NAME";
            String apn = intent.getStringExtra(apn_key);
            if (apn != null) {
                this.autostart_profile_name = null;
                String str = TAG;
                Object[] objArr = new Object[S_BIND_CALLED];
                objArr[0] = apn;
                Log.d(str, String.format("CLI: autostart: %s", objArr));
                intent.removeExtra(apn_key);
                if (!active) {
                    ProfileList proflist = profile_list();
                    if (proflist == null || proflist.get_profile_by_name(apn) == null) {
                        ok_dialog(resString(R.string.profile_not_found), apn);
                    } else {
                        this.autostart_profile_name = apn;
                        return true;
                    }
                } else if (!current_profile().get_name().equals(apn)) {
                    this.autostart_profile_name = apn;
                    submitDisconnectIntent(RETAIN_AUTH);
                }
            }
        }
        return RETAIN_AUTH;
    }
 
    private void cancel_ui_reset() {
        this.ui_reset_timer_handler.removeCallbacks(this.ui_reset_timer_task);
    }
 
    private void schedule_ui_reset(long delay) {
        cancel_ui_reset();
        this.ui_reset_timer_handler.postDelayed(this.ui_reset_timer_task, delay);
    }
 
    private void hide_status() {
        this.status_view.setVisibility(8);
    }
 
    private void show_status(String text) {
        this.status_view.setVisibility(0);
        this.status_view.setText(text);
    }
 
    private void show_status(int res_id) {
        this.status_view.setVisibility(0);
        this.status_view.setText(res_id);
    }
 
    private void show_status_icon(int res_id) {
        this.status_icon_view.setImageResource(res_id);
    }
 
    private void show_progress(int progress, boolean active) {
        if (progress <= 0 || progress >= 99) {
            this.progress_bar.setVisibility(8);
            return;
        }
        this.progress_bar.setVisibility(0);
        this.progress_bar.setProgress(progress);
    }
 
    private void cancel_stats() {
        this.stats_timer_handler.removeCallbacks(this.stats_timer_task);
    }
 
    private void schedule_stats() {
        cancel_stats();
        this.stats_timer_handler.postDelayed(this.stats_timer_task, 1000);
    }
 
    private static String render_bandwidth(long bw) {
        String postfix;
        float div;
        Object[] objArr;
        float bwf = (float) bw;
        if (bwf >= 1.0E12f) {
            postfix = "TB";
            div = 1.0995116E12f;
        } else if (bwf >= 1.0E9f) {
            postfix = "GB";
            div = 1.0737418E9f;
        } else if (bwf >= 1000000.0f) {
            postfix = "MB";
            div = 1048576.0f;
        } else if (bwf >= 1000.0f) {
            postfix = "KB";
            div = 1024.0f;
        } else {
            objArr = new Object[S_BIND_CALLED];
            objArr[0] = Float.valueOf(bwf);
            return String.format("%.0f", objArr);
        }
        objArr = new Object[S_ONSTART_CALLED];
        objArr[0] = Float.valueOf(bwf / div);
        objArr[S_BIND_CALLED] = postfix;
        return String.format("%.2f %s", objArr);
    }
 
    private String render_last_pkt_recv(int sec) {
        if (sec >= 3600) {
            return resString(R.string.lpr_gt_1_hour_ago);
        }
        String resString;
        Object[] objArr;
        if (sec >= 120) {
            resString = resString(R.string.lpr_gt_n_min_ago);
            objArr = new Object[S_BIND_CALLED];
            objArr[0] = Integer.valueOf(sec / 60);
            return String.format(resString, objArr);
        } else if (sec >= S_ONSTART_CALLED) {
            resString = resString(R.string.lpr_n_sec_ago);
            objArr = new Object[S_BIND_CALLED];
            objArr[0] = Integer.valueOf(sec);
            return String.format(resString, objArr);
        } else if (sec == S_BIND_CALLED) {
            return resString(R.string.lpr_1_sec_ago);
        } else {
            if (sec == 0) {
                return resString(R.string.lpr_lt_1_sec_ago);
            }
            return BuildConfig.FLAVOR;
        }
    }
 
    private void show_stats() {
        if (is_active()) {
            ConnectionStats stats = get_connection_stats();
            this.last_pkt_recv_view.setText(render_last_pkt_recv(stats.last_packet_received));
            this.duration_view.setText(OpenVPNClientBase.render_duration(stats.duration));
            this.bytes_in_view.setText(render_bandwidth(stats.bytes_in));
            this.bytes_out_view.setText(render_bandwidth(stats.bytes_out));
        }
    }
 
    private void clear_stats() {
        this.last_pkt_recv_view.setText(BuildConfig.FLAVOR);
        this.duration_view.setText(BuildConfig.FLAVOR);
        this.bytes_in_view.setText(BuildConfig.FLAVOR);
        this.bytes_out_view.setText(BuildConfig.FLAVOR);
        reset_conn_info();
    }
 
    private int n_profiles_loaded() {
        ProfileList proflist = profile_list();
        if (proflist != null) {
            return proflist.size();
        }
        return 0;
    }
 
    private String selected_profile_name() {
        String ret = null;
        ProfileList proflist = profile_list();
        if (proflist != null && proflist.size() > 0) {
            ret = proflist.size() == S_BIND_CALLED ? ((Profile) proflist.get(0)).get_name() : SpinUtil.get_spinner_selected_item(this.profile_spin);
        }
        if (ret == null) {
            return "UNDEFINED_PROFILE";
        }
        return ret;
    }
 
    private Profile selected_profile() {
        ProfileList proflist = profile_list();
        if (proflist != null) {
            return proflist.get_profile_by_name(selected_profile_name());
        }
        return null;
    }
 
    private void clear_auth() {
        this.username_edit.setText(BuildConfig.FLAVOR);
        this.pk_password_edit.setText(BuildConfig.FLAVOR);
        this.password_edit.setText(BuildConfig.FLAVOR);
        this.response_edit.setText(BuildConfig.FLAVOR);
    }
 
    private void ui_setup(boolean active, int flags, String profile_override) {
        boolean orig_active = active;
        boolean autostart = RETAIN_AUTH;
        cancel_ui_reset();
        if (!((UIF_RESET & flags) == 0 && orig_active == this.last_active)) {
            clear_auth();
            if (!(active || this.autostart_profile_name == null)) {
                autostart = true;
                profile_override = this.autostart_profile_name;
                this.autostart_profile_name = null;
            }
            ProfileList proflist = profile_list();
            Profile prof = null;
            if (proflist == null || proflist.size() <= 0) {
                this.profile_group.setVisibility(8);
            } else {
                ProfileSource ps = ProfileSource.UNDEF;
                SpinUtil.show_spinner(this, this.profile_spin, proflist.profile_names());
                if (active) {
                    ps = ProfileSource.SERVICE;
                    prof = current_profile();
                }
                if (prof == null && profile_override != null) {
                    ps = ProfileSource.PRIORITY;
                    prof = proflist.get_profile_by_name(profile_override);
                    if (prof == null) {
                        Log.d(TAG, "CLI: profile override not found");
                        autostart = RETAIN_AUTH;
                    }
                }
                if (prof == null) {
                    if ((UIF_PROFILE_SETTING_FROM_SPINNER & flags) != 0) {
                        ps = ProfileSource.SPINNER;
                        prof = proflist.get_profile_by_name(SpinUtil.get_spinner_selected_item(this.profile_spin));
                    } else {
                        ps = ProfileSource.PREFERENCES;
                        prof = proflist.get_profile_by_name(this.prefs.get_string("profile"));
                    }
                }
                if (prof == null) {
                    ps = ProfileSource.LIST0;
                    prof = (Profile) proflist.get(0);
                }
                if (ps != ProfileSource.PREFERENCES && (UIF_REFLECTED & flags) == 0) {
                    this.prefs.set_string("profile", prof.get_name());
                    gen_ui_reset_event(true);
                }
                if (ps != ProfileSource.SPINNER) {
                    SpinUtil.set_spinner_selected_item(this.profile_spin, prof.get_name());
                }
                this.profile_group.setVisibility(0);
                this.profile_spin.setEnabled(!active ? true : RETAIN_AUTH);
                this.profile_edit.setVisibility(active ? 8 : 0);
            }
            if (prof != null) {
                if ((UIF_RESET & flags) != 0) {
                    prof.reset_dynamic_challenge();
                }
                EditText focus = null;
                if (!active && (flags & 32) != 0) {
                    this.post_import_help_blurb.setVisibility(0);
                } else if (active) {
                    this.post_import_help_blurb.setVisibility(8);
                }
                ProxyList proxy_list = get_proxy_list();
                if (active || proxy_list.size() <= 0) {
                    this.proxy_group.setVisibility(8);
                } else {
                    SpinUtil.show_spinner(this, this.proxy_spin, proxy_list.get_name_list(true));
                    String name = proxy_list.get_enabled(true);
                    if (name != null) {
                        SpinUtil.set_spinner_selected_item(this.proxy_spin, name);
                    }
                    this.proxy_group.setVisibility(0);
                }
                if (active || !prof.server_list_defined()) {
                    this.server_group.setVisibility(8);
                } else {
                    SpinUtil.show_spinner(this, this.server_spin, prof.get_server_list().display_names());
                    String server = this.prefs.get_string_by_profile(prof.get_name(), "server");
                    if (server != null) {
                        SpinUtil.set_spinner_selected_item(this.server_spin, server);
                    }
                    this.server_group.setVisibility(0);
                }
                if (active) {
                    this.username_group.setVisibility(8);
                    this.pk_password_group.setVisibility(8);
                    this.password_group.setVisibility(8);
                } else {
                    boolean is_pwd_save;
                    String saved_pwd;
                    boolean udef = prof.userlocked_username_defined();
                    boolean autologin = prof.get_autologin();
                    boolean pk_pwd_req = prof.get_private_key_password_required();
                    boolean dynamic_challenge = prof.is_dynamic_challenge();
                    if ((!autologin || (autologin && udef)) && !dynamic_challenge) {
                        if (udef) {
                            this.username_edit.setText(prof.get_userlocked_username());
                            set_enabled(this.username_edit, RETAIN_AUTH);
                        } else {
                            set_enabled(this.username_edit, true);
                            String pref_username = this.prefs.get_string_by_profile(prof.get_name(), "username");
                            if (pref_username != null) {
                                this.username_edit.setText(pref_username);
                            } else if (null == null) {
                                focus = this.username_edit;
                            }
                        }
                        this.username_group.setVisibility(0);
                    } else {
                        this.username_group.setVisibility(8);
                    }
                    if (pk_pwd_req) {
                        is_pwd_save = this.prefs.get_boolean_by_profile(prof.get_name(), "pk_password_save", RETAIN_AUTH);
                        saved_pwd = null;
                        this.pk_password_group.setVisibility(0);
                        this.pk_password_save_checkbox.setChecked(is_pwd_save);
                        if (is_pwd_save) {
                            saved_pwd = this.pwds.get("pk", prof.get_name());
                        }
                        if (saved_pwd != null) {
                            this.pk_password_edit.setText(saved_pwd);
                        } else if (focus == null) {
                            focus = this.pk_password_edit;
                        }
                    } else {
                        this.pk_password_group.setVisibility(8);
                    }
                    if (autologin || dynamic_challenge) {
                        this.password_group.setVisibility(8);
                    } else {
                        boolean is_auth_pw_save = prof.get_allow_password_save();
                        is_pwd_save = (is_auth_pw_save && this.prefs.get_boolean_by_profile(prof.get_name(), "auth_password_save", RETAIN_AUTH)) ? true : RETAIN_AUTH;
                        saved_pwd = null;
                        this.password_group.setVisibility(0);
                        this.password_save_checkbox.setEnabled(is_auth_pw_save);
                        this.password_save_checkbox.setChecked(is_pwd_save);
                        if (is_pwd_save) {
                            saved_pwd = this.pwds.get("auth", prof.get_name());
                        }
                        if (saved_pwd != null) {
                            this.password_edit.setText(saved_pwd);
                        } else if (focus == null) {
                            focus = this.password_edit;
                        }
                    }
                }
                if (active || prof.get_autologin() || !prof.challenge_defined()) {
                    this.cr_group.setVisibility(8);
                } else {
                    this.cr_group.setVisibility(0);
                    Challenge chal = prof.get_challenge();
                    this.challenge_view.setText(chal.get_challenge());
                    this.challenge_view.setVisibility(0);
                    if (chal.get_response_required()) {
                        if (chal.get_echo()) {
                            this.response_edit.setTransformationMethod(SingleLineTransformationMethod.getInstance());
                        } else {
                            this.response_edit.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        }
                        this.response_edit.setVisibility(0);
                        if (focus == null) {
                            focus = this.response_edit;
                        }
                    } else {
                        this.response_edit.setVisibility(8);
                    }
                    if (prof.is_dynamic_challenge()) {
                        schedule_ui_reset(prof.get_dynamic_challenge_expire_delay());
                    }
                }
                this.button_group.setVisibility(0);
                if (orig_active) {
                    this.conn_details_group.setVisibility(0);
                    this.connect_button.setVisibility(8);
                    this.disconnect_button.setVisibility(0);
                } else {
                    this.conn_details_group.setVisibility(8);
                    this.connect_button.setVisibility(0);
                    this.disconnect_button.setVisibility(8);
                }
                if (focus != null) {
                    autostart = RETAIN_AUTH;
                }
                req_focus(focus);
            } else {
                this.post_import_help_blurb.setVisibility(8);
                this.proxy_group.setVisibility(8);
                this.server_group.setVisibility(8);
                this.username_group.setVisibility(8);
                this.pk_password_group.setVisibility(8);
                this.password_group.setVisibility(8);
                this.cr_group.setVisibility(8);
                this.conn_details_group.setVisibility(8);
                this.button_group.setVisibility(8);
                show_status_icon(R.drawable.info);
                show_status(R.string.no_profiles_loaded);
            }
            if (orig_active) {
                schedule_stats();
            } else {
                cancel_stats();
            }
        }
        this.last_active = orig_active;
        if (autostart && !this.last_active) {
            this.finish_on_connect = FinishOnConnect.ENABLED;
            start_connect();
        }
    }
 
    private void set_enabled(EditText editText, boolean state) {
        editText.setEnabled(state);
        editText.setFocusable(state);
        editText.setFocusableInTouchMode(state);
    }
 
    private void raise_file_selection_dialog(int requestCode) {
        switch (requestCode) {
            case S_ONSTART_CALLED /*2*/:
                raise_file_selection_dialog(S_ONSTART_CALLED, R.string.select_profile);
                return;
            case REQUEST_IMPORT_PKCS12 /*3*/:
                raise_file_selection_dialog(REQUEST_IMPORT_PKCS12, R.string.select_pkcs12);
                return;
            default:
                return;
        }
    }
 
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (grantResults.length != 0) {
            switch (requestCode) {
                case S_ONSTART_CALLED /*2*/:
                case REQUEST_IMPORT_PKCS12 /*3*/:
                    int i = 0;
                    while (i < grantResults.length) {
                        if (permissions[i].equals("android.permission.READ_EXTERNAL_STORAGE") && grantResults[i] == 0) {
                            raise_file_selection_dialog(requestCode);
                        }
                        i += S_BIND_CALLED;
                    }
                    return;
                default:
                    return;
            }
        }
    }
 
    private void request_file_selection_dialog(int requestCode) {
        if (ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") == 0) {
            raise_file_selection_dialog(requestCode);
            return;
        }
        String[] perms = new String[S_BIND_CALLED];
        perms[0] = "android.permission.READ_EXTERNAL_STORAGE";
        ActivityCompat.requestPermissions(this, perms, requestCode);
    }
 
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about_menu /*2131427498*/:
                startActivityForResult(new Intent(this, OpenVPNAbout.class), 0);
                return true;
            case R.id.help_menu /*2131427499*/:
                startActivityForResult(new Intent(this, OpenVPNHelp.class), 0);
                return true;
            case R.id.import_private_tunnel_profile /*2131427501*/:
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getText(R.string.privatetunnel_import).toString())));
                break;
            case R.id.import_profile_remote /*2131427502*/:
                startActivityForResult(new Intent(this, OpenVPNImportProfile.class), 0);
                return true;
            case R.id.import_profile /*2131427503*/:
                request_file_selection_dialog(S_ONSTART_CALLED);
                return true;
            case R.id.import_pkcs12 /*2131427504*/:
                request_file_selection_dialog(REQUEST_IMPORT_PKCS12);
                return true;
            case R.id.preferences /*2131427505*/:
                startActivityForResult(new Intent(this, OpenVPNPrefs.class), 0);
                return true;
            case R.id.add_proxy /*2131427506*/:
                String prefix = OpenVPNService.INTENT_PREFIX;
                startActivityForResult(new Intent(this, OpenVPNAddProxy.class), 0);
                return true;
            case R.id.add_shortcut_connect /*2131427508*/:
                startActivityForResult(new Intent(this, OpenVPNAddShortcut.class), 0);
                return true;
            case R.id.add_shortcut_disconnect /*2131427509*/:
                createDisconnectShortcut(resString(R.string.disconnect_shortcut_title));
                return true;
            case R.id.add_shortcut_app /*2131427510*/:
                createAppShortcut(resString(R.string.app_shortcut_title));
                return true;
            case R.id.show_log /*2131427511*/:
                startActivityForResult(new Intent(this, OpenVPNLog.class), 0);
                return true;
            case R.id.show_raw_stats /*2131427513*/:
                startActivityForResult(new Intent(this, OpenVPNStats.class), 0);
                return true;
            case R.id.forget_creds /*2131427514*/:
                forget_creds_with_confirm();
                return true;
            case R.id.exit_partial /*2131427515*/:
                finish();
                return true;
            case R.id.exit_full /*2131427516*/:
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        this.stop_service_on_client_exit = true;
        finish();
        return true;
    }

	private void createAppShortcut(String resString)
	{
		// TODO: Implement this method
	}

	private void createDisconnectShortcut(String resString)
	{
		// TODO: Implement this method
	}
 
    public void onClick(View v) {
        cancel_ui_reset();
        this.autostart_profile_name = null;
        this.finish_on_connect = FinishOnConnect.DISABLED;
        int viewid = v.getId();
        if (viewid == R.id.connect) {
            start_connect();
        } else if (viewid == R.id.disconnect) {
            submitDisconnectIntent(RETAIN_AUTH);
        } else if (viewid == R.id.profile_edit || viewid == R.id.proxy_edit) {
            openContextMenu(v);
        }
    }
 
    private void start_connect() {
        cancel_ui_reset();
        Intent intent = VpnService.prepare(this);
        if (intent != null) {
            try {
                Log.d(TAG, "CLI: requesting VPN actor rights");
                startActivityForResult(intent, S_BIND_CALLED);
                return;
            } catch (ActivityNotFoundException e) {
                Log.e(TAG, "CLI: requesting VPN actor rights failed", e);
                ok_dialog(resString(R.string.vpn_permission_dialog_missing_title), resString(R.string.vpn_permission_dialog_missing_text));
                return;
            }
        }
        Log.d(TAG, "CLI: app is already authorized as VPN actor");
        resolve_epki_alias_then_connect();
    }
 
    public boolean onTouch(View v, MotionEvent event) {
        boolean new_expand_stats = RETAIN_AUTH;
        if (v.getId() != R.id.conn_details_boxed || event.getAction() != 0) {
            return RETAIN_AUTH;
        }
        if (!this.prefs.get_boolean("expand_stats", RETAIN_AUTH)) {
            new_expand_stats = true;
        }
        this.prefs.set_boolean("expand_stats", new_expand_stats);
        set_visibility_stats_expansion_group();
        return true;
    }
 
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        cancel_ui_reset();
        int viewid = parent.getId();
        if (viewid == R.id.profile) {
            ui_setup(is_active(), 327680, null);
        } else if (viewid == R.id.proxy) {
            ProxyList proxy_list = get_proxy_list();
            if (proxy_list != null) {
                proxy_list.set_enabled(SpinUtil.get_spinner_list_item(this.proxy_spin, position));
                proxy_list.save();
                gen_ui_reset_event(true);
            }
        } else if (viewid == R.id.server) {
            String server = SpinUtil.get_spinner_list_item(this.server_spin, position);
            this.prefs.set_string_by_profile(SpinUtil.get_spinner_selected_item(this.profile_spin), "server", server);
            gen_ui_reset_event(true);
        }
    }
 
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
 
    private void menu_add(ContextMenu menu, int id, boolean enabled, String menu_key) {
        MenuItem item = menu.add(0, id, 0, id).setEnabled(enabled);
        if (menu_key != null) {
            item.setIntent(new Intent().putExtra("net.openvpn.openvpn.MENU_KEY", menu_key));
        }
    }
 
    private String get_menu_key(MenuItem item) {
        if (item != null) {
            Intent intent = item.getIntent();
            if (intent != null) {
                return intent.getStringExtra("net.openvpn.openvpn.MENU_KEY");
            }
        }
        return null;
    }
 
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        boolean z = RETAIN_AUTH;
        Log.d(TAG, "CLI: onCreateContextMenu");
        super.onCreateContextMenu(menu, v, menuInfo);
        int viewid = v.getId();
        if (!is_active() && (viewid == R.id.profile || viewid == R.id.profile_edit)) {
            Profile prof = selected_profile();
            if (prof != null) {
                String profile_name = prof.get_name();
                menu.setHeaderTitle(profile_name);
                if (SpinUtil.get_spinner_count(this.profile_spin) > S_BIND_CALLED) {
                    z = true;
                }
                menu_add(menu, R.string.profile_context_menu_change_profile, z, null);
                menu_add(menu, R.string.profile_context_menu_create_shortcut, true, profile_name);
                menu_add(menu, R.string.profile_context_menu_delete, prof.is_deleteable(), profile_name);
                menu_add(menu, R.string.profile_context_menu_rename, prof.is_renameable(), profile_name);
                menu_add(menu, R.string.profile_context_forget_creds, true, profile_name);
            } else {
                menu.setHeaderTitle(R.string.profile_context_none_selected);
            }
            menu_add(menu, R.string.profile_context_cancel, true, null);
        } else if (!is_active()) {
            if (viewid == R.id.proxy || viewid == R.id.proxy_edit) {
                ProxyList proxy_list = get_proxy_list();
                if (proxy_list != null) {
                    String proxy_name = proxy_list.get_enabled(true);
                    boolean is_none = proxy_list.is_none(proxy_name);
                    menu.setHeaderTitle(proxy_name);
                    menu_add(menu, R.string.proxy_context_change_proxy, SpinUtil.get_spinner_count(this.proxy_spin) > S_BIND_CALLED ? true : RETAIN_AUTH, null);
                    menu_add(menu, R.string.proxy_context_edit, !is_none ? true : RETAIN_AUTH, proxy_name);
                    if (!is_none) {
                        z = true;
                    }
                    menu_add(menu, R.string.proxy_context_delete, z, proxy_name);
                    menu_add(menu, R.string.proxy_context_forget_creds, proxy_list.has_saved_creds(proxy_name), proxy_name);
                } else {
                    menu.setHeaderTitle(R.string.proxy_context_none_selected);
                }
                menu_add(menu, R.string.proxy_context_cancel, true, null);
            }
        }
    }
 
    public boolean onContextItemSelected(MenuItem item) {
        Log.d(TAG, "CLI: onContextItemSelected");
        String prof_name;
        String proxy_name;
        switch (item.getItemId()) {
            case R.string.profile_context_cancel /*2131034278*/:
            case R.string.proxy_context_cancel /*2131034308*/:
                return true;
            case R.string.profile_context_forget_creds /*2131034279*/:
                ProfileList proflist = profile_list();
                if (proflist == null) {
                    return true;
                }
                Profile prof = proflist.get_profile_by_name(get_menu_key(item));
                if (prof == null) {
                    return true;
                }
                prof_name = prof.get_name();
                this.pwds.remove("pk", prof_name);
                this.pwds.remove("auth", prof_name);
                prof.forget_cert();
                ui_setup(is_active(), UIF_RESET, null);
                return true;
            case R.string.profile_context_menu_change_profile /*2131034280*/:
                this.profile_spin.performClick();
                return true;
            case R.string.profile_context_menu_create_shortcut /*2131034281*/:
                prof_name = get_menu_key(item);
                if (prof_name == null) {
                    return true;
                }
                launch_create_profile_shortcut_dialog(prof_name);
                return true;
            case R.string.profile_context_menu_delete /*2131034282*/:
                prof_name = get_menu_key(item);
                if (prof_name == null) {
                    return true;
                }
                submitDeleteProfileIntentWithConfirm(prof_name);
                return true;
            case R.string.profile_context_menu_rename /*2131034283*/:
                prof_name = get_menu_key(item);
                if (prof_name == null) {
                    return true;
                }
                launch_rename_profile_dialog(prof_name);
                return true;
            case R.string.proxy_context_change_proxy /*2131034309*/:
                this.proxy_spin.performClick();
                return true;
            case R.string.proxy_context_delete /*2131034310*/:
                delete_proxy_with_confirm(get_menu_key(item));
                return true;
            case R.string.proxy_context_edit /*2131034311*/:
                proxy_name = get_menu_key(item);
                if (proxy_name == null) {
                    return true;
                }
                startActivityForResult(new Intent(this, OpenVPNAddProxy.class).putExtra("net.openvpn.openvpn.PROXY_NAME", proxy_name), 0);
                return true;
            case R.string.proxy_context_forget_creds /*2131034313*/:
                proxy_name = get_menu_key(item);
                ProxyList proxy_list = get_proxy_list();
                if (proxy_list == null) {
                    return true;
                }
                proxy_list.forget_creds(proxy_name);
                proxy_list.save();
                return true;
            default:
                return RETAIN_AUTH;
        }
    }
 
    private void launch_create_profile_shortcut_dialog(final String prof_name) {
        View view = getLayoutInflater().inflate(R.layout.create_shortcut_dialog, null);
        final EditText name_field = (EditText) view.findViewById(R.id.shortcut_name);
        name_field.setText(prof_name);
        name_field.selectAll();
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case -1:
                        OpenVPNClient.this.createConnectShortcut(prof_name, name_field.getText().toString());
                        return;
                    default:
                        return;
                }
            }
        };
        new Builder(this).setTitle(R.string.create_shortcut_title).setView(view).setPositiveButton(R.string.create_shortcut_yes, dialogClickListener).setNegativeButton(R.string.create_shortcut_cancel, dialogClickListener).show();
    }
 
    private void launch_rename_profile_dialog(final String orig_prof_name) {
        View view = getLayoutInflater().inflate(R.layout.rename_profile_dialog, null);
        final EditText name_field = (EditText) view.findViewById(R.id.rename_profile_name);
        name_field.setText(orig_prof_name);
        name_field.selectAll();
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case -1:
                        OpenVPNClient.this.submitRenameProfileIntent(orig_prof_name, name_field.getText().toString());
                        return;
                    default:
                        return;
                }
            }
        };
        new Builder(this).setTitle(R.string.rename_profile_title).setView(view).setPositiveButton(R.string.rename_profile_yes, dialogClickListener).setNegativeButton(R.string.rename_profile_cancel, dialogClickListener).show();
    }
 
    private void delete_proxy_with_confirm(final String proxy_name) {
        final ProxyList proxy_list = get_proxy_list();
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case -1:
                        if (proxy_list != null) {
                            proxy_list.remove(proxy_name);
                            proxy_list.save();
                            OpenVPNClient.this.gen_ui_reset_event(OpenVPNClient.RETAIN_AUTH);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
        new Builder(this).setTitle(R.string.proxy_delete_confirm_title).setMessage(proxy_name).setPositiveButton(R.string.proxy_delete_confirm_yes, dialogClickListener).setNegativeButton(R.string.proxy_delete_confirm_cancel, dialogClickListener).show();
    }
 
    private void forget_creds_with_confirm() {
        final Context context = this;
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case -1:
                        OpenVPNClient.this.pwds.regenerate(true);
                        ProfileList proflist = OpenVPNClient.this.profile_list();
                        if (proflist != null) {
                            proflist.forget_certs();
                        }
                        TrustMan.forget_certs(context);
                        OpenVPNImportProfile.forget_server_history(OpenVPNClient.this.prefs);
                        ProxyList proxy_list = OpenVPNClient.this.get_proxy_list();
                        if (proxy_list != null) {
                            proxy_list.forget_creds();
                            proxy_list.save();
                        }
                        OpenVPNClient.this.ui_setup(OpenVPNClient.this.is_active(), OpenVPNClient.UIF_RESET, null);
                        return;
                    default:
                        return;
                }
            }
        };
        new Builder(this).setTitle(R.string.forget_creds_title).setMessage(R.string.forget_creds_message).setPositiveButton(R.string.forget_creds_yes, dialogClickListener).setNegativeButton(R.string.forget_creds_cancel, dialogClickListener).show();
    }
 
    public PendingIntent get_configure_intent(int requestCode) {
        return PendingIntent.getActivity(this, requestCode, getIntent(), 268435456);
    }
 
    private void resolve_epki_alias_then_connect() {
        resolveExternalPkiAlias(selected_profile(), new EpkiPost() {
            public void post_dispatch(String alias) {
                OpenVPNClient.this.do_connect(alias);
            }
        });
    }
 
    private void do_connect(String epki_alias) {
        String app_name = "net.openvpn.connect.android";
        String proxy_name = null;
        String server = null;
        String username = null;
        String password = null;
        String pk_password = null;
        String response = null;
        boolean is_auth_pwd_save = RETAIN_AUTH;
        String profile_name = selected_profile_name();
        if (this.proxy_group.getVisibility() == 0) {
            ProxyList proxy_list = get_proxy_list();
            if (proxy_list != null) {
                proxy_name = proxy_list.get_enabled(RETAIN_AUTH);
            }
        }
        if (this.server_group.getVisibility() == 0) {
            server = SpinUtil.get_spinner_selected_item(this.server_spin);
        }
        if (this.username_group.getVisibility() == 0) {
            username = this.username_edit.getText().toString();
            if (username.length() > 0) {
                this.prefs.set_string_by_profile(profile_name, "username", username);
            }
        }
        if (this.pk_password_group.getVisibility() == 0) {
            pk_password = this.pk_password_edit.getText().toString();
            boolean is_pk_pwd_save = this.pk_password_save_checkbox.isChecked();
            this.prefs.set_boolean_by_profile(profile_name, "pk_password_save", is_pk_pwd_save);
            if (is_pk_pwd_save) {
                this.pwds.set("pk", profile_name, pk_password);
            } else {
                this.pwds.remove("pk", profile_name);
            }
        }
        if (this.password_group.getVisibility() == 0) {
            password = this.password_edit.getText().toString();
            is_auth_pwd_save = this.password_save_checkbox.isChecked();
            this.prefs.set_boolean_by_profile(profile_name, "auth_password_save", is_auth_pwd_save);
            if (is_auth_pwd_save) {
                this.pwds.set("auth", profile_name, password);
            } else {
                this.pwds.remove("auth", profile_name);
            }
        }
        if (this.cr_group.getVisibility() == 0) {
            response = this.response_edit.getText().toString();
        }
        clear_auth();
        String vpn_proto = this.prefs.get_string("vpn_proto");
        String ipv6 = this.prefs.get_string("ipv6");
        String conn_timeout = this.prefs.get_string("conn_timeout");
        String compression_mode = this.prefs.get_string("compression_mode");
        clear_stats();
        submitConnectIntent(profile_name, server, vpn_proto, ipv6, conn_timeout, username, password, is_auth_pwd_save, pk_password, response, epki_alias, compression_mode, proxy_name, null, null, true, get_gui_version(app_name));
    }
 
    private void import_profile(String path) {
        submitImportProfileViaPathIntent(path);
    }
 
    protected void onActivityResult(int request, int result, Intent data) {
        String str = TAG;
        Object[] objArr = new Object[S_ONSTART_CALLED];
        objArr[0] = Integer.valueOf(request);
        objArr[S_BIND_CALLED] = Integer.valueOf(result);
        Log.d(str, String.format("CLI: onActivityResult request=%d result=%d", objArr));
        String path;
        switch (request) {
            case S_BIND_CALLED /*1*/:
                if (result == -1) {
                    resolve_epki_alias_then_connect();
                    return;
                } else if (result != 0) {
                    return;
                } else {
                    if (this.finish_on_connect == FinishOnConnect.ENABLED) {
                        finish();
                        return;
                    } else if (this.finish_on_connect == FinishOnConnect.ENABLED_ACROSS_ONSTART) {
                        this.finish_on_connect = FinishOnConnect.ENABLED;
                        start_connect();
                        return;
                    } else {
                        return;
                    }
                }
            case S_ONSTART_CALLED /*2*/:
                if (result == -1) {
                    path = data.getStringExtra(FileDialog.RESULT_PATH);
                    str = TAG;
                    objArr = new Object[S_BIND_CALLED];
                    objArr[0] = path;
                    Log.d(str, String.format("CLI: IMPORT_PROFILE: %s", objArr));
                    import_profile(path);
                    return;
                }
                return;
            case REQUEST_IMPORT_PKCS12 /*3*/:
                if (result == -1) {
                    path = data.getStringExtra(FileDialog.RESULT_PATH);
                    str = TAG;
                    objArr = new Object[S_BIND_CALLED];
                    objArr[0] = path;
                    Log.d(str, String.format("CLI: IMPORT_PKCS12: %s", objArr));
                    import_pkcs12(path);
                    return;
                }
                return;
            default:
                super.onActivityResult(request, result, data);
                return;
        }
    }
 
    private TextView last_visible_edittext() {
        for (int i = 0; i < this.textgroups.length; i += S_BIND_CALLED) {
            if (this.textgroups[i].getVisibility() == 0) {
                return this.textviews[i];
            }
        }
        return null;
    }
 
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (v != last_visible_edittext()) {
            return RETAIN_AUTH;
        }
        if (action_enter(actionId, event) && this.connect_button.getVisibility() == 0) {
            onClick(this.connect_button);
        }
        return true;
    }
 
    private void req_focus(EditText editText) {
        boolean auto_keyboard = this.prefs.get_boolean("auto_keyboard", RETAIN_AUTH);
        if (editText != null) {
            editText.requestFocus();
            if (auto_keyboard) {
                raise_keyboard(editText);
                return;
            }
            return;
        }
        this.main_scroll_view.requestFocus();
        if (auto_keyboard) {
            dismiss_keyboard();
        }
    }
 
    private void raise_keyboard(EditText editText) {
        InputMethodManager mgr = (InputMethodManager) getSystemService("input_method");
        if (mgr != null) {
            mgr.showSoftInput(editText, S_BIND_CALLED);
        }
    }
 
    private void dismiss_keyboard() {
        InputMethodManager mgr = (InputMethodManager) getSystemService("input_method");
        if (mgr != null) {
            TextView[] textViewArr = this.textviews;
            int length = textViewArr.length;
            for (int i = 0; i < length; i += S_BIND_CALLED) {
                mgr.hideSoftInputFromWindow(textViewArr[i].getWindowToken(), 0);
            }
        }
    }
 
    private void load_ui_elements() {
        this.main_scroll_view = (ScrollView) findViewById(R.id.main_scroll_view);
        this.post_import_help_blurb = findViewById(R.id.post_import_help_blurb);
        this.profile_group = findViewById(R.id.profile_group);
        this.proxy_group = findViewById(R.id.proxy_group);
        this.server_group = findViewById(R.id.server_group);
        this.username_group = findViewById(R.id.username_group);
        this.password_group = findViewById(R.id.password_group);
        this.pk_password_group = findViewById(R.id.pk_password_group);
        this.cr_group = findViewById(R.id.cr_group);
        this.conn_details_group = findViewById(R.id.conn_details_group);
        this.stats_group = findViewById(R.id.stats_group);
        this.stats_expansion_group = findViewById(R.id.stats_expansion_group);
        this.info_group = findViewById(R.id.info_group);
        this.button_group = findViewById(R.id.button_group);
        this.profile_spin = (Spinner) findViewById(R.id.profile);
        this.profile_edit = (ImageButton) findViewById(R.id.profile_edit);
        this.proxy_spin = (Spinner) findViewById(R.id.proxy);
        this.proxy_edit = (ImageButton) findViewById(R.id.proxy_edit);
        this.server_spin = (Spinner) findViewById(R.id.server);
        this.challenge_view = (TextView) findViewById(R.id.challenge);
        this.username_edit = (EditText) findViewById(R.id.username);
        this.password_edit = (EditText) findViewById(R.id.password);
        this.pk_password_edit = (EditText) findViewById(R.id.pk_password);
        this.response_edit = (EditText) findViewById(R.id.response);
        this.password_save_checkbox = (CheckBox) findViewById(R.id.password_save);
        this.pk_password_save_checkbox = (CheckBox) findViewById(R.id.pk_password_save);
        this.status_view = (TextView) findViewById(R.id.status);
        this.status_icon_view = (ImageView) findViewById(R.id.status_icon);
        this.progress_bar = (ProgressBar) findViewById(R.id.progress);
        this.connect_button = (Button) findViewById(R.id.connect);
        this.disconnect_button = (Button) findViewById(R.id.disconnect);
        this.details_more_less = (TextView) findViewById(R.id.details_more_less);
        this.last_pkt_recv_view = (TextView) findViewById(R.id.last_pkt_recv);
        this.duration_view = (TextView) findViewById(R.id.duration);
        this.bytes_in_view = (TextView) findViewById(R.id.bytes_in);
        this.bytes_out_view = (TextView) findViewById(R.id.bytes_out);
        this.connect_button.setOnClickListener(this);
        this.disconnect_button.setOnClickListener(this);
        this.profile_spin.setOnItemSelectedListener(this);
        this.proxy_spin.setOnItemSelectedListener(this);
        this.server_spin.setOnItemSelectedListener(this);
        registerForContextMenu(this.profile_spin);
        registerForContextMenu(this.proxy_spin);
        findViewById(R.id.conn_details_boxed).setOnTouchListener(this);
        this.profile_edit.setOnClickListener(this);
        registerForContextMenu(this.profile_edit);
        this.proxy_edit.setOnClickListener(this);
        registerForContextMenu(this.proxy_edit);
        this.username_edit.setOnEditorActionListener(this);
        this.password_edit.setOnEditorActionListener(this);
        this.pk_password_edit.setOnEditorActionListener(this);
        this.response_edit.setOnEditorActionListener(this);
        this.textgroups = new View[]{this.cr_group, this.password_group, this.pk_password_group, this.username_group};
        this.textviews = new EditText[]{this.response_edit, this.password_edit, this.pk_password_edit, this.username_edit};
    }
}
