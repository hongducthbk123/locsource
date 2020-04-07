package p004cn.jiguang.net;

import java.io.ByteArrayInputStream;
import java.security.KeyStore;
import java.security.KeyStore.TrustedCertificateEntry;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* renamed from: cn.jiguang.net.SSLTrustManager */
public class SSLTrustManager implements X509TrustManager {

    /* renamed from: a */
    private X509TrustManager f637a;

    public SSLTrustManager(String str) {
        TrustManager[] trustManagers;
        try {
            CertificateFactory instance = CertificateFactory.getInstance("X.509");
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
            X509Certificate x509Certificate = (X509Certificate) instance.generateCertificate(byteArrayInputStream);
            byteArrayInputStream.close();
            TrustedCertificateEntry trustedCertificateEntry = new TrustedCertificateEntry(x509Certificate);
            KeyStore instance2 = KeyStore.getInstance(KeyStore.getDefaultType());
            instance2.load(null, null);
            instance2.setEntry("ca_root", trustedCertificateEntry, null);
            TrustManagerFactory instance3 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance3.init(instance2);
            for (TrustManager trustManager : instance3.getTrustManagers()) {
                if (trustManager instanceof X509TrustManager) {
                    this.f637a = (X509TrustManager) trustManager;
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        if (x509CertificateArr == null || x509CertificateArr.length == 0) {
            throw new CertificateException("Check Server x509Certificates is empty");
        } else if (x509CertificateArr[0] != null) {
            x509CertificateArr[0].checkValidity();
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        return this.f637a.getAcceptedIssuers();
    }
}
