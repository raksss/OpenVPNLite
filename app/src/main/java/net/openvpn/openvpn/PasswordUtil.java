package net.openvpn.openvpn;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;
import android.util.Log;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class PasswordUtil {
    private static final String TAG = "PasswordUtil";
    private String cipherName;
    private IvParameterSpec ivParms;
    private SharedPreferences mSettings;
    private String prefPrefix = "pwdv1";
    private byte[] salt;
    private SecretKey secret;

    PasswordUtil(SharedPreferences prefs) {
        this.mSettings = prefs;
        regenerate(false);
    }

    void regenerate(boolean reset) {
        this.cipherName = "AES/CBC/PKCS5Padding";
        byte[] iv = new byte[]{(byte) -42, (byte) -31, (byte) -117, (byte) 101, (byte) 25, (byte) 119, Byte.MAX_VALUE, (byte) 37, (byte) 121, (byte) -54, (byte) 46, (byte) 49, (byte) -35, (byte) -48, (byte) -72, (byte) 97};
        String pw = "It was a bright cold day in April, and the clocks were striking thirteen. Winston Smith, his chin nuzzled into his breast in an effort to escape the vile wind, slipped quickly through the glass doors of Victory Mansions, though not quickly enough to prevent a swirl of gritty dust from entering along with him.";
        this.salt = null;
        if (!reset) {
            this.salt = get_salt();
        }
        if (reset || this.salt == null) {
            remove(null);
            generate_salt();
            this.salt = get_salt();
        }
        if (this.salt != null) {
            try {
                this.secret = new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(pw.toCharArray(), this.salt, 16, 128)).getEncoded(), this.cipherName);
                this.ivParms = new IvParameterSpec(iv);
                return;
            } catch (Exception e) {
                Log.e(TAG, "regenerate", e);
            }
        }
        this.secret = null;
        this.prefPrefix = null;
    }

    private void check_salt() {
        byte[] s = get_salt();
        if (s != null && this.salt != null && !Arrays.equals(s, this.salt)) {
            regenerate(false);
        }
    }

    private void generate_salt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        Editor editor = this.mSettings.edit();
        editor.putString(key_string("settings", "entropy"), Base64.encodeToString(salt, 2));
        editor.apply();
    }

    private byte[] get_salt() {
        byte[] bArr = null;
        try {
            String salt_b64 = this.mSettings.getString(key_string("settings", "entropy"), null);
            if (salt_b64 != null) {
                bArr = Base64.decode(salt_b64, 0);
            }
        } catch (Exception e) {
        }
        return bArr;
    }

    private String en(String pwd_cleartext) {
        check_salt();
        if (!(pwd_cleartext == null || this.secret == null || this.prefPrefix == null)) {
            try {
                Cipher cipher = Cipher.getInstance(this.cipherName);
                cipher.init(1, this.secret, this.ivParms);
                return Base64.encodeToString(cipher.doFinal(pwd_cleartext.getBytes("UTF-8")), 2);
            } catch (Exception e) {
                Log.e(TAG, "en", e);
                regenerate(true);
            }
        }
        return null;
    }

    private String de(String pwd_ciphertext) {
        check_salt();
        if (!(pwd_ciphertext == null || this.secret == null || this.prefPrefix == null)) {
            try {
                byte[] ciphertext = Base64.decode(pwd_ciphertext, 0);
                Cipher cipher = Cipher.getInstance(this.cipherName);
                cipher.init(2, this.secret, this.ivParms);
                return new String(cipher.doFinal(ciphertext), "UTF-8");
            } catch (Exception e) {
                Log.e(TAG, "de", e);
                regenerate(true);
            }
        }
        return null;
    }

    private String key_string(String type, String account) {
        return String.format("%s.%s.%s", new Object[]{this.prefPrefix, type, account});
    }

    private String key_prefix(String type) {
        if (type != null) {
            return String.format("%s.%s.", new Object[]{this.prefPrefix, type});
        }
        return String.format("%s.", new Object[]{this.prefPrefix});
    }

    public String get(String type, String account) {
        String str = null;
        try {
            str = de(this.mSettings.getString(key_string(type, account), null));
        } catch (ClassCastException e) {
            Log.d(TAG, "get() class cast exception");
            regenerate(true);
        }
        return str;
    }

    public void set(String type, String account, String pw) {
        Editor editor = this.mSettings.edit();
        String key = key_string(type, account);
        String ciphertext = en(pw);
        if (ciphertext != null) {
            editor.putString(key, ciphertext);
            editor.apply();
        }
    }

    public void remove(String type, String account) {
        Editor editor = this.mSettings.edit();
        editor.remove(key_string(type, account));
        editor.apply();
    }

    public void remove(String type) {
        Editor editor = this.mSettings.edit();
        Set<String> pref_keys = this.mSettings.getAll().keySet();
        String prefix = key_prefix(type);
        for (String key : pref_keys) {
            if (key.startsWith(prefix)) {
                editor.remove(key);
            }
        }
        editor.apply();
    }
}
