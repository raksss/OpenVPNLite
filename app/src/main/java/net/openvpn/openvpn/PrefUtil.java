package net.openvpn.openvpn;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import java.util.Set;

public class PrefUtil {
    private static final String TAG = "PrefUtil";
    private SharedPreferences mSettings;

	private Object pkey;

    PrefUtil(SharedPreferences prefs) {
        this.mSettings = prefs;
    }

    public boolean contains_key(String key) {
        return this.mSettings.contains(key);
    }

    public String get_string(String key) {
        try {
            String value = this.mSettings.getString(key, null);
            Log.d(TAG, String.format("get_string: %s='%s'", new Object[]{key, value}));
            return value;
        } catch (ClassCastException e) {
            Log.d(TAG, String.format("get_string %s class cast exception", new Object[]{key}));
            return null;
        }
    }

    public Set<String> get_string_set(String key) {
        try {
            Set<String> value = this.mSettings.getStringSet(key, null);
            Log.d(TAG, String.format("get_string_set: %s='%s'", new Object[]{key, value}));
            return value;
        } catch (ClassCastException e) {
            Log.d(TAG, String.format("get_string_set %s class cast exception", new Object[]{key}));
            return null;
        }
    }

    public boolean get_boolean(String key, boolean default_value) {
        try {
            boolean value = this.mSettings.getBoolean(key, default_value);
            Log.d(TAG, String.format("get_boolean: %s=%b", new Object[]{key, Boolean.valueOf(value)}));
            return value;
        } catch (ClassCastException e) {
            Log.d(TAG, String.format("get_boolean %s class cast exception", new Object[]{key}));
            return default_value;
        }
    }

    public void set_string(String key, String value) {
        Editor editor = this.mSettings.edit();
        editor.putString(key, value);
        Log.d(TAG, String.format("set_string: %s='%s'", new Object[]{key, value}));
        editor.apply();
    }

    public void set_string_set(String key, Set<String> value) {
        Editor editor = this.mSettings.edit();
        editor.putStringSet(key, value);
        Log.d(TAG, String.format("set_string: %s='%s'", new Object[]{key, value}));
        editor.apply();
    }

    public void set_boolean(String key, boolean value) {
        Editor editor = this.mSettings.edit();
        editor.putBoolean(key, value);
        Log.d(TAG, String.format("set_boolean: %s=%b", new Object[]{key, Boolean.valueOf(value)}));
        editor.apply();
    }

    public String get_string_by_profile(String profile_name, String key) {
        try {
            String value = this.mSettings.getString(key_by_profile(profile_name, key), null);
            Log.d(TAG, String.format("get_string_by_profile: key='%s' value='%s'", new Object[]{pkey, value}));
            return value;
        } catch (ClassCastException e) {
            Log.d(TAG, "get_string_by_profile class cast exception");
            return null;
        }
    }

    public void set_string_by_profile(String profile_name, String key, String value) {
        Editor editor = this.mSettings.edit();
        String pkey = key_by_profile(profile_name, key);
        Log.d(TAG, String.format("set_string_by_profile: key='%s' value='%s'", new Object[]{pkey, value}));
        editor.putString(pkey, value);
        editor.apply();
    }

    public boolean get_boolean_by_profile(String profile_name, String key, boolean default_value) {
        try {
            boolean value = this.mSettings.getBoolean(key_by_profile(profile_name, key), default_value);
            Log.d(TAG, String.format("get_boolean_by_profile: key='%s' value=%b", new Object[]{pkey, Boolean.valueOf(value)}));
            return value;
        } catch (ClassCastException e) {
            Log.d(TAG, "get_boolean_by_profile class cast exception");
            return default_value;
        }
    }

    public void set_boolean_by_profile(String profile_name, String key, boolean value) {
        Editor editor = this.mSettings.edit();
        String pkey = key_by_profile(profile_name, key);
        Log.d(TAG, String.format("set_boolean_by_profile: key='%s' value=%b", new Object[]{pkey, Boolean.valueOf(value)}));
        editor.putBoolean(pkey, value);
        editor.apply();
    }

    public void delete_key(String key) {
        Editor editor = this.mSettings.edit();
        Log.d(TAG, String.format("delete_key: key='%s'", new Object[]{key}));
        editor.remove(key);
        editor.apply();
    }

    public void delete_key_by_profile(String profile_name, String key) {
        Editor editor = this.mSettings.edit();
        String pkey = key_by_profile(profile_name, key);
        Log.d(TAG, String.format("delete_key_by_profile: key='%s'", new Object[]{pkey}));
        editor.remove(pkey);
        editor.apply();
    }

    private String key_by_profile(String profile_name, String key) {
        return String.format("%s.%s", new Object[]{key, profile_name});
    }
}
