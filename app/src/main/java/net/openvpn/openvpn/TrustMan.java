package net.openvpn.openvpn;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.X509Certificate;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class TrustMan implements X509TrustManager {
    private static final String KEYSTORE_FILE = "trusted-certs.keystore";
    private static final String TAG = "TrustMan";
    private static int generation = 0;
    private KeyStore appKeyStore;
    private X509TrustManager appTrustManager;
    private int current_generation;
    private X509TrustManager defaultTrustManager;
    private File keyStoreFile;
    private Callback parent;

    public interface Callback {
        void onTrustFail(TrustContext trustContext);

        void onTrustSucceed(boolean z);
    }

    public static class Error extends Exception {
        public Error(String message) {
            super("TrustMan: " + message);
        }
    }

    public static class TrustContext {
        public String authType;
        public X509Certificate[] chain;
        public CertificateException excep;

        public String toString() {
            return "TrustContext chain=" + this.chain + " authType=" + this.authType + " excep=" + this.excep;
        }
    }

    public static class TrustFail extends CertificateException {
        TrustFail(CertificateException ce) {
            super(ce);
        }
    }

    public TrustMan(Context c) throws Error {
        this.keyStoreFile = new File(c.getFilesDir() + File.separator + KEYSTORE_FILE);
        reload();
    }

    private void check_reload() {
        try {
            if (this.current_generation != generation) {
                reload();
            }
        } catch (Error e) {
            Log.e(TAG, "check_reload", e);
        }
    }

    private void reload() throws Error {
        Log.d(TAG, String.format("reload certs: gen=%d/%d", new Object[]{Integer.valueOf(this.current_generation), Integer.valueOf(generation)}));
        KeyStore aks = loadAppKeyStore();
        if (aks == null) {
            throw new Error("could not load appKeyStore");
        }
        X509TrustManager dtm = getTrustManager(null, "default");
        if (dtm == null) {
            throw new Error("could not load defaultTrustManager");
        }
        X509TrustManager atm = getTrustManager(aks, "app-init");
        if (atm == null) {
            throw new Error("could not load appTrustManager");
        }
        this.current_generation = generation;
        this.appKeyStore = aks;
        this.defaultTrustManager = dtm;
        this.appTrustManager = atm;
    }

    public static void forget_certs(Context c) {
        boolean status = c.deleteFile(KEYSTORE_FILE);
        generation++;
        Log.d(TAG, String.format("forget certs: fn=%s status=%b gen=%d", new Object[]{KEYSTORE_FILE, Boolean.valueOf(status), Integer.valueOf(generation)}));
    }

    public void setCallback(Callback cb) {
        this.parent = cb;
    }

    public void clearCallback() {
        this.parent = null;
    }

    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        checkCertTrusted(chain, authType, false);
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        checkCertTrusted(chain, authType, true);
    }

    public X509Certificate[] getAcceptedIssuers() {
        check_reload();
        return this.defaultTrustManager.getAcceptedIssuers();
    }

    private void callOnTrustSucceed(boolean appTrusted) {
        if (this.parent != null) {
            this.parent.onTrustSucceed(appTrusted);
        }
    }

    private void checkCertTrusted(X509Certificate[] chain, String authType, boolean isServer) throws CertificateException {
        Log.d(TAG, "checkCertTrusted(" + chain + ", " + authType + ", " + isServer + ")");
        check_reload();
        try {
            Log.d(TAG, "checkCertTrusted: trying appTrustManager");
            if (isServer) {
                this.appTrustManager.checkServerTrusted(chain, authType);
            } else {
                this.appTrustManager.checkClientTrusted(chain, authType);
            }
            callOnTrustSucceed(true);
        } catch (CertificateException ae) {
            if (isExpiredException(ae)) {
                Log.d(TAG, "checkCertTrusted: accepting expired certificate from keystore");
                callOnTrustSucceed(true);
            } else if (isCertKnown(chain[0])) {
                Log.d(TAG, "checkCertTrusted: accepting cert already stored in keystore");
                callOnTrustSucceed(true);
            } else {
                try {
                    Log.d(TAG, "checkCertTrusted: trying defaultTrustManager");
                    if (isServer) {
                        this.defaultTrustManager.checkServerTrusted(chain, authType);
                    } else {
                        this.defaultTrustManager.checkClientTrusted(chain, authType);
                    }
                    callOnTrustSucceed(false);
                } catch (CertificateException e) {
                    TrustContext tc = new TrustContext();
                    tc.chain = chain;
                    tc.authType = authType;
                    tc.excep = e;
                    if (this.parent != null) {
                        this.parent.onTrustFail(tc);
                    }
                    throw new TrustFail(e);
                }
            }
        }
    }

    private X509TrustManager getTrustManager(KeyStore ks, String name) {
        try {
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("X509");
            tmf.init(ks);
            for (TrustManager t : tmf.getTrustManagers()) {
                if (t instanceof X509TrustManager) {
                    return (X509TrustManager) t;
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "getTrustManager(" + ks + "," + name + ")", e);
        }
        return null;
    }

    private KeyStore loadAppKeyStore() {
        KeyStore ks = null;
        try {
            ks = KeyStore.getInstance(KeyStore.getDefaultType());
            try {
                ks.load(null, null);
                ks.load(new FileInputStream(this.keyStoreFile), "OpenVPN".toCharArray());
            } catch (FileNotFoundException e) {
                Log.d(TAG, "loadAppKeyStore(" + this.keyStoreFile + ") - file does not exist");
            } catch (Exception e2) {
                Log.e(TAG, "loadAppKeyStore(" + this.keyStoreFile + ")", e2);
            }
        } catch (KeyStoreException e3) {
            Log.e(TAG, "loadAppKeyStore()", e3);
        }
        return ks;
    }

    public void trustCert(TrustContext tc) {
        Log.d(TAG, "trust cert: " + tc.toString());
        try {
            this.appKeyStore.setCertificateEntry(tc.chain[0].getSubjectDN().toString(), tc.chain[0]);
            X509TrustManager atm = getTrustManager(this.appKeyStore, "app-reload");
            if (atm != null) {
                this.appTrustManager = atm;
            }
            try {
                FileOutputStream fos = new FileOutputStream(this.keyStoreFile);
                this.appKeyStore.store(fos, "OpenVPN".toCharArray());
                fos.close();
            } catch (Exception e) {
                Log.e(TAG, "trustCert(" + this.keyStoreFile + ")", e);
            }
        } catch (KeyStoreException e2) {
            Log.e(TAG, "trustCert(" + tc.chain + ")", e2);
        }
    }

    private boolean isCertKnown(X509Certificate cert) {
        try {
            return this.appKeyStore.getCertificateAlias(cert) != null;
        } catch (KeyStoreException e) {
            return false;
        }
    }

    private boolean isExpiredException(Throwable e) {
        while (!(e instanceof CertificateExpiredException)) {
            e = e.getCause();
            if (e == null) {
                return false;
            }
        }
        return true;
    }

    public static boolean isTrustFail(Exception e) {
        for (Throwable t = e; t != null; t = t.getCause()) {
            if (t instanceof TrustFail) {
                return true;
            }
        }
        return false;
    }
}
