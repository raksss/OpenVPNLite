package net.openvpn.openvpn;

import android.content.Context;
import android.os.Build.VERSION;
import android.security.KeyChain;
import android.util.Log;
import java.lang.reflect.Method;
import java.security.InvalidKeyException;
import java.security.PrivateKey;

public class JellyBeanHack extends JellyBeanHackBase {
    private static final String TAG = "JellyBeanHack";
    private String alias_;
    private PrivateKey pk_;
    private boolean rsa_sign_initialized = false;

    private static native void pkey_retain(int i);

    private static native byte[] rsa_sign(byte[] bArr, int i) throws InvalidKeyException;

    private static native int rsa_sign_init();

    public static JellyBeanHack newJellyBeanHack() {
        Log.i(TAG, String.format("Build.VERSION.SDK_INT=%d", new Object[]{Integer.valueOf(VERSION.SDK_INT)}));
        if (VERSION.SDK_INT == 16) {
            return new JellyBeanHack();
        }
        return null;
    }

    private JellyBeanHack() {
        resetPrivateKey();
        if (rsa_sign_init() == 1) {
            this.rsa_sign_initialized = true;
        }
        Log.i(TAG, String.format("JellyBeanHack: rsa_sign_initialized=%b", new Object[]{Boolean.valueOf(this.rsa_sign_initialized)}));
    }

    public boolean enabled() {
        return this.rsa_sign_initialized;
    }

    public synchronized void resetPrivateKey() {
        Log.i(TAG, "JellyBeanHack: resetPrivateKey");
        this.alias_ = null;
        this.pk_ = null;
    }

    public synchronized PrivateKey getPrivateKey(Context context, String alias) throws Exception {
        PrivateKey pk;
        pk = get_pk(alias);
        if (pk == null) {
            pk = set_pk(alias, KeyChain.getPrivateKey(context, alias));
        }
        return pk;
    }

    public synchronized byte[] rsaSign(PrivateKey pk, byte[] data) throws Exception {
        return rsa_sign(data, openssl_pkey(pk));
    }

    private synchronized PrivateKey get_pk(String alias) {
        PrivateKey privateKey;
        if (this.alias_ == null || !this.alias_.equals(alias)) {
            privateKey = null;
        } else {
            privateKey = this.pk_;
        }
        return privateKey;
    }

    private synchronized PrivateKey set_pk(String alias, PrivateKey pk) throws Exception {
        this.alias_ = null;
        this.pk_ = null;
        if (pk != null) {
            pkey_retain(openssl_pkey(pk));
            if (alias != null && alias.length() > 0) {
                this.alias_ = alias;
                this.pk_ = pk;
            }
        }
        return this.pk_;
    }

    private static int openssl_pkey(PrivateKey privkey) throws Exception {
        if (privkey == null) {
            return 0;
        }
        Method getKey = privkey.getClass().getSuperclass().getDeclaredMethod("getOpenSSLKey", new Class[0]);
        getKey.setAccessible(true);
        Object opensslkey = getKey.invoke(privkey, new Object[0]);
        getKey.setAccessible(false);
        Method getPkeyContext = opensslkey.getClass().getDeclaredMethod("getPkeyContext", new Class[0]);
        getPkeyContext.setAccessible(true);
        int pkey = ((Integer) getPkeyContext.invoke(opensslkey, new Object[0])).intValue();
        getPkeyContext.setAccessible(false);
        return pkey;
    }
}
