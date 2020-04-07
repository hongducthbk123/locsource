package p004cn.jiguang.net;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/* renamed from: cn.jiguang.net.DefaultHostVerifier */
public class DefaultHostVerifier implements HostnameVerifier {

    /* renamed from: a */
    private boolean f613a = false;

    public boolean verify(String str, SSLSession sSLSession) {
        return !this.f613a;
    }
}
