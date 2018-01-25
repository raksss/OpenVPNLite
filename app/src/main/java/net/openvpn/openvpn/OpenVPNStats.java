package net.openvpn.openvpn;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import java.util.ArrayList;

public class OpenVPNStats extends OpenVPNClientBase {
    private static final String TAG = "OpenVPNClientStats";
    private StatsAdapter adapter;
    private GridView gridview;
    private Handler stats_timer_handler = new Handler();
    private Runnable stats_timer_task = new Runnable() {
        public void run() {
            OpenVPNStats.this.show_stats();
            OpenVPNStats.this.schedule_stats();
        }
    };

    private static class Stat {
        public int name_idx;
        public long value;

        private Stat() {
        }
    }

    private class StatsAdapter extends BaseAdapter {
        private String[] stat_names = OpenVPNService.stat_names();
        private ArrayList<Stat> stats;

        StatsAdapter() {
        }

        public void update_stats(ClientAPI_LLVector new_stats) {
            ArrayList<Stat> build_stats = new ArrayList();
            for (int i = 0; i < this.stat_names.length; i++) {
                long value = new_stats.get(i);
                if (value > 0) {
                    Stat s = new Stat();
                    s.name_idx = i;
                    s.value = value;
                    build_stats.add(s);
                }
            }
            this.stats = build_stats;
            notifyDataSetChanged();
        }

        public int getCount() {
            if (this.stats != null) {
                return this.stats.size() * 2;
            }
            return 0;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        private String text_at_pos(int pos) {
            Stat s = (Stat) this.stats.get(pos / 2);
            if (pos % 2 == 0) {
                return this.stat_names[s.name_idx];
            }
            return String.format("%d", new Object[]{Long.valueOf(s.value)});
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv;
            if (convertView == null) {
                tv = new TextView(OpenVPNStats.this);
                tv.setLayoutParams(new LayoutParams(-2, -2));
            } else {
                tv = (TextView) convertView;
            }
            tv.setText(text_at_pos(position));
            return tv;
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats);
        this.adapter = new StatsAdapter();
        this.gridview = (GridView) findViewById(R.id.stats_grid);
        this.gridview.setAdapter(this.adapter);
        doBindService();
    }

    private void cancel_stats() {
        this.stats_timer_handler.removeCallbacks(this.stats_timer_task);
    }

    private void schedule_stats() {
        cancel_stats();
        this.stats_timer_handler.postDelayed(this.stats_timer_task, 1000);
    }

    private void show_stats() {
        ClientAPI_LLVector sv = get_stat_values_full();
        if (sv != null) {
            this.adapter.update_stats(sv);
        }
    }

    protected void onDestroy() {
        Log.d(TAG, "STATS: onDestroy");
        stop();
        super.onDestroy();
    }

    private void stop() {
        cancel_stats();
        doUnbindService();
    }

    protected void onStop() {
        Log.d(TAG, "STATS: onStop");
        cancel_stats();
        super.onStop();
    }

    protected void onStart() {
        Log.d(TAG, "STATS: onStart");
        show_stats();
        schedule_stats();
        super.onStart();
    }

    protected void post_bind() {
        Log.d(TAG, "STATS: post_bind");
        show_stats();
    }
}
