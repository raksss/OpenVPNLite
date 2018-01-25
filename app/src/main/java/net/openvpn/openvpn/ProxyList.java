package net.openvpn.openvpn;

import android.content.Context;
import android.util.Log;
import java.io.IOException;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class ProxyList {
    private static final String TAG = "ProxyList";
    private String backing_file = null;
    private Context context = null;
    private boolean dirty = false;
    private String enabled_name = null;
    private TreeMap<String, Item> list = new TreeMap(String.CASE_INSENSITIVE_ORDER);
    private String none_name = null;

    public static class InternalError extends RuntimeException {
    }

    public static class Item {
        public boolean allow_cleartext_auth = false;
        public String friendly_name = null;
        public String host = BuildConfig.FLAVOR;
        public String password = BuildConfig.FLAVOR;
        public String port = BuildConfig.FLAVOR;
        public boolean remember_creds = false;
        public String username = BuildConfig.FLAVOR;

        public String name() {
            if (this.friendly_name != null) {
                return this.friendly_name;
            }
            return String.format("%s:%s", new Object[]{this.host, this.port});
        }

        public boolean is_valid() {
            return this.host.length() > 0 && this.port.length() > 0;
        }

        public boolean invalidate_creds() {
            if (!this.remember_creds) {
                return false;
            }
            this.username = BuildConfig.FLAVOR;
            this.password = BuildConfig.FLAVOR;
            this.remember_creds = false;
            return true;
        }

        private JSONObject persist() {
            try {
                JSONObject j = new JSONObject();
                if (this.friendly_name != null) {
                    j.put("friendly_name", this.friendly_name);
                }
                j.put("host", this.host);
                j.put("port", this.port);
                j.put("remember_creds", this.remember_creds);
                j.put("allow_cleartext_auth", this.allow_cleartext_auth);
                if (!this.remember_creds) {
                    return j;
                }
                j.put("username", this.username);
                j.put("password", this.password);
                return j;
            } catch (JSONException e) {
                Log.e(ProxyList.TAG, "ProxyList.Item.persist", e);
                return null;
            }
        }

        private static Item unpersist(JSONObject j) {
            try {
                Item i = new Item();
                if (j.isNull("friendly_name")) {
                    i.friendly_name = null;
                } else {
                    i.friendly_name = j.getString("friendly_name");
                }
                i.host = j.getString("host");
                i.port = j.getString("port");
                i.remember_creds = j.getBoolean("remember_creds");
                i.allow_cleartext_auth = j.getBoolean("allow_cleartext_auth");
                if (!j.isNull("username")) {
                    i.username = j.getString("username");
                }
                if (j.isNull("password")) {
                    return i;
                }
                i.password = j.getString("password");
                return i;
            } catch (JSONException e) {
                Log.e(ProxyList.TAG, "ProxyList.Item.unpersist", e);
                return null;
            }
        }
    }

    public Item get(String name) {
        if (is_none(name)) {
            return null;
        }
        return (Item) this.list.get(name);
    }

    public Item get_enabled_item() {
        return get(this.enabled_name);
    }

    public void put(Item item) {
        if (item != null) {
            String name = item.name();
            if (!is_none(name)) {
                this.list.put(name, item);
                this.dirty = true;
            }
        }
    }

    public void remove(String name) {
        if (!is_none(name)) {
            this.list.remove(name);
            set_enabled(null);
            this.dirty = true;
        }
    }

    public void set_enabled(String name) {
        String prev = this.enabled_name;
        if (name == null) {
            name = this.enabled_name;
        }
        if (is_none(name)) {
            this.enabled_name = this.none_name;
        } else if (get(name) != null) {
            this.enabled_name = name;
        } else {
            this.enabled_name = this.none_name;
        }
        if (prev == null || !prev.equals(this.enabled_name)) {
            this.dirty = true;
        }
    }

    public String get_enabled(boolean include_none) {
        if (include_none || !is_none(this.enabled_name)) {
            return this.enabled_name;
        }
        return null;
    }

    public String[] get_name_list(boolean include_none) {
        int size = this.list.size();
        String[] ret = (String[]) this.list.keySet().toArray(new String[((include_none ? 1 : 0) + size)]);
        if (include_none) {
            ret[size] = this.none_name;
        }
        return ret;
    }

    public boolean is_none(String name) {
        return name == null || name.equals(this.none_name);
    }

    public int size() {
        return this.list.size();
    }

    public boolean has_saved_creds(String name) {
        Item item = get(name);
        if (item == null || item.username.length() <= 0) {
            return false;
        }
        return true;
    }

    public void forget_creds(String name) {
        forget_creds(get(name));
    }

    public void forget_creds() {
        for (Item item : this.list.values()) {
            forget_creds(item);
        }
    }

    ProxyList(String none_name) {
        if (none_name == null) {
            throw new InternalError();
        }
        this.none_name = none_name;
        set_enabled(null);
    }

    public void set_backing_file(Context context, String backing_file) {
        this.context = context;
        this.backing_file = backing_file;
    }

    public void load() {
        try {
            if (this.backing_file != null) {
                ProxyList pl = unpersist((JSONObject) new JSONTokener(FileUtil.readFileAppPrivate(this.context, this.backing_file)).nextValue(), this.none_name);
                this.list = pl.list;
                this.enabled_name = pl.enabled_name;
                this.dirty = false;
            }
        } catch (IOException e) {
            Log.d(TAG, "ProxyList.load: no proxy file present");
        } catch (Exception e2) {
            Log.e(TAG, "ProxyList.load", e2);
        }
    }

    public void save() {
        try {
            if (this.dirty && this.backing_file != null) {
                FileUtil.writeFileAppPrivate(this.context, this.backing_file, persist().toString(4));
                this.dirty = false;
            }
        } catch (Exception e) {
            Log.e(TAG, "ProxyList.save", e);
        }
    }

    private void forget_creds(Item item) {
        if (item != null) {
            item.username = BuildConfig.FLAVOR;
            item.password = BuildConfig.FLAVOR;
            this.dirty = true;
        }
    }

    private JSONObject persist() throws JSONException {
        String e;
        try {
            JSONObject j = new JSONObject();
            e = get_enabled(false);
            if (e != null) {
                j.put("enabled_name", e);
            }
            JSONArray ja = new JSONArray();
            for (Item item : this.list.values()) {
                JSONObject ij = item.persist();
                if (ij != null) {
                    ja.put(ij);
                }
            }
            j.put("list", ja);
            return j;
        } catch (Exception e2) {
            Log.e(TAG, "ProxyList.persist", e2);
            return null;
        }
    }

    private static ProxyList unpersist(JSONObject j, String none_name) {
        try {
            ProxyList pl = new ProxyList(none_name);
            if (!j.isNull("enabled_name")) {
                pl.enabled_name = j.getString("enabled_name");
            }
            JSONArray ja = j.getJSONArray("list");
            int length = ja.length();
            for (int i = 0; i < length; i++) {
                pl.put(Item.unpersist(ja.getJSONObject(i)));
            }
            pl.set_enabled(null);
            return pl;
        } catch (JSONException e) {
            Log.e(TAG, "ProxyList.unpersist", e);
            return null;
        }
    }
}
