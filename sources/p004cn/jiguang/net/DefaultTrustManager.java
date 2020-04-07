package p004cn.jiguang.net;

import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/* renamed from: cn.jiguang.net.DefaultTrustManager */
public class DefaultTrustManager implements X509TrustManager {
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
