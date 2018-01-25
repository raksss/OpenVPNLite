package net.openvpn.openvpn;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.security.auth.x500.X500Principal;

abstract class CertWarn implements OnCancelListener, OnClickListener {
    public static final int RESPONSE_ACCEPT = 1;
    public static final int RESPONSE_REJECT = 0;
    private static final String TAG = "CertWarn";

    protected abstract void done(int i);

    public CertWarn(Context context, X509Certificate cert, String excep) {
        Runnable cancel = new Runnable() {
            public void run() {
                CertWarn.this.done(CertWarn.RESPONSE_REJECT);
            }
        };
        try {
            new Builder(context).setTitle(R.string.cert_warn_title).setView(inflateCertificateView(context, cert, excep)).setPositiveButton(R.string.cert_warn_accept, this).setNegativeButton(R.string.cert_warn_reject, this).setOnCancelListener(this).create().show();
        } catch (Exception e) {
            Log.e(TAG, "AlertDialog error", e);
            new Handler().postDelayed(cancel, 0);
        }
    }

    public void onClick(DialogInterface dialog, int button_id) {
        dialog.dismiss();
        switch (button_id) {
            case -1:
                done(RESPONSE_ACCEPT);
                return;
            default:
                done(RESPONSE_REJECT);
                return;
        }
    }

    public void onCancel(DialogInterface dialog) {
        done(RESPONSE_REJECT);
    }

    private static final String fingerprint(byte[] bytes) {
        if (bytes == null) {
            return BuildConfig.FLAVOR;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = RESPONSE_REJECT; i < bytes.length; i += RESPONSE_ACCEPT) {
            Object[] objArr = new Object[RESPONSE_ACCEPT];
            objArr[RESPONSE_REJECT] = Byte.valueOf(bytes[i]);
            sb.append(String.format("%02X", objArr));
            if (i + RESPONSE_ACCEPT != bytes.length) {
                sb.append(':');
            }
        }
        return sb.toString();
    }

    private static String getDigest(X509Certificate x509Certificate, String algorithm) {
        if (x509Certificate == null) {
            return BuildConfig.FLAVOR;
        }
        try {
            return fingerprint(MessageDigest.getInstance(algorithm).digest(x509Certificate.getEncoded()));
        } catch (CertificateEncodingException e) {
            return BuildConfig.FLAVOR;
        } catch (NoSuchAlgorithmException e2) {
            return BuildConfig.FLAVOR;
        }
    }

    private static String getSerialNumber(X509Certificate x509Certificate) {
        if (x509Certificate == null) {
            return BuildConfig.FLAVOR;
        }
        BigInteger serialNumber = x509Certificate.getSerialNumber();
        if (serialNumber == null) {
            return BuildConfig.FLAVOR;
        }
        return fingerprint(serialNumber.toByteArray());
    }

    private static String formatCertificateDate(Date certificateDate, DateFormat dateFormat) {
        if (certificateDate == null) {
            return BuildConfig.FLAVOR;
        }
        return dateFormat.format(certificateDate);
    }

    private static HashMap<String, String> parse_dn(String dn) {
        HashMap<String, String> ret = new HashMap();
        StringBuilder[] sb = new StringBuilder[]{new StringBuilder(), new StringBuilder()};
        int sbi = RESPONSE_REJECT;
        boolean esc = false;
        for (int i = RESPONSE_REJECT; i < dn.length(); i += RESPONSE_ACCEPT) {
            char c = dn.charAt(i);
            if (!esc && c == '\\') {
                esc = true;
            } else if (!esc && c == '=') {
                sbi = RESPONSE_ACCEPT;
            } else if (esc || c != ',') {
                StringBuilder s = sb[sbi];
                if (s.length() > 0 || c != ' ') {
                    s.append(c);
                }
                esc = false;
            } else {
                if (sb[RESPONSE_REJECT].length() > 0 && sb[RESPONSE_ACCEPT].length() > 0) {
                    ret.put(sb[RESPONSE_REJECT].toString(), sb[RESPONSE_ACCEPT].toString());
                    sb[RESPONSE_REJECT].setLength(RESPONSE_REJECT);
                    sb[RESPONSE_ACCEPT].setLength(RESPONSE_REJECT);
                }
                sbi = RESPONSE_REJECT;
            }
        }
        if (sb[RESPONSE_REJECT].length() > 0 && sb[RESPONSE_ACCEPT].length() > 0) {
            ret.put(sb[RESPONSE_REJECT].toString(), sb[RESPONSE_ACCEPT].toString());
        }
        return ret;
    }

    private static HashMap<String, String> parse_dn(X500Principal p) {
        return parse_dn(p.getName("RFC2253"));
    }

    private View inflateCertificateView(Context context, X509Certificate cert, String error) {
        View certificateView = LayoutInflater.from(context).inflate(R.layout.cert_warn, null);
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(context);
        ((TextView) certificateView.findViewById(R.id.cert_error)).setText(error);
        HashMap<String, String> issuer = parse_dn(cert.getIssuerX500Principal());
        HashMap<String, String> subject = parse_dn(cert.getSubjectX500Principal());
        ((TextView) certificateView.findViewById(R.id.to_common)).setText((CharSequence) subject.get("CN"));
        ((TextView) certificateView.findViewById(R.id.to_org)).setText((CharSequence) subject.get("O"));
        ((TextView) certificateView.findViewById(R.id.to_org_unit)).setText((CharSequence) subject.get("OU"));
        ((TextView) certificateView.findViewById(R.id.by_common)).setText((CharSequence) issuer.get("CN"));
        ((TextView) certificateView.findViewById(R.id.by_org)).setText((CharSequence) issuer.get("O"));
        ((TextView) certificateView.findViewById(R.id.by_org_unit)).setText((CharSequence) issuer.get("OU"));
        ((TextView) certificateView.findViewById(R.id.serial_number)).setText(getSerialNumber(cert));
        ((TextView) certificateView.findViewById(R.id.issued_on)).setText(formatCertificateDate(cert.getNotBefore(), dateFormat));
        ((TextView) certificateView.findViewById(R.id.expires_on)).setText(formatCertificateDate(cert.getNotAfter(), dateFormat));
        ((TextView) certificateView.findViewById(R.id.sha256_fingerprint)).setText(getDigest(cert, "SHA256"));
        ((TextView) certificateView.findViewById(R.id.sha1_fingerprint)).setText(getDigest(cert, "SHA1"));
        return certificateView;
    }
}
