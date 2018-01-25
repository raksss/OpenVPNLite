package net.openvpn.openvpn;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.Arrays;

public class SpinUtil {
    public static String[] get_spinner_list(Spinner spin) {
        ArrayAdapter<String> aa = (ArrayAdapter) spin.getAdapter();
        if (aa == null) {
            return null;
        }
        int len = aa.getCount();
        String[] ret = new String[len];
        for (int i = 0; i < len; i++) {
            ret[i] = (String) aa.getItem(i);
        }
        return ret;
    }

    public static int get_spinner_count(Spinner spin) {
        ArrayAdapter<String> aa = (ArrayAdapter) spin.getAdapter();
        if (aa == null) {
            return 0;
        }
        return aa.getCount();
    }

    public static String get_spinner_list_item(Spinner spin, int position) {
        ArrayAdapter<String> aa = (ArrayAdapter) spin.getAdapter();
        if (aa == null) {
            return null;
        }
        return (String) aa.getItem(position);
    }

    public static String get_spinner_selected_item(Spinner spin) {
        return (String) spin.getSelectedItem();
    }

    public static void set_spinner_selected_item(Spinner spin, String selected_item) {
        if (selected_item != null) {
            String sel = get_spinner_selected_item(spin);
            if (sel == null || !selected_item.equals(sel)) {
                ArrayAdapter<String> aa = (ArrayAdapter) spin.getAdapter();
                int len = aa.getCount();
                for (int pos = 0; pos < len; pos++) {
                    if (selected_item.equals(aa.getItem(pos))) {
                        spin.setSelection(pos);
                    }
                }
            }
        }
    }

    public static void show_spinner(Context context, Spinner spin, String[] content) {
        if (content != null) {
            String[] live_content = get_spinner_list(spin);
            if (live_content == null || !Arrays.equals(content, live_content)) {
                ArrayAdapter<String> aa = new ArrayAdapter(context, 17367048, content);
                aa.setDropDownViewResource(17367049);
                spin.setAdapter(aa);
            }
        }
    }
}
