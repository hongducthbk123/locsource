package p004cn.jiguang.p015d.p021d;

import java.net.InetAddress;
import java.net.UnknownHostException;
import p004cn.jiguang.p029e.C0501d;

/* renamed from: cn.jiguang.d.d.d */
final class C0447d extends Thread {

    /* renamed from: a */
    private String f400a = null;

    /* renamed from: b */
    private InetAddress f401b = null;

    public C0447d(String str) {
        this.f400a = str;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final synchronized InetAddress mo6554a() {
        return this.f401b;
    }

    public final void run() {
        try {
            this.f401b = InetAddress.getByName(this.f400a);
        } catch (UnknownHostException e) {
            C0501d.m904a("ConnectingHelper", "Unknown host exception!", e);
        } catch (Exception e2) {
            C0501d.m904a("ConnectingHelper", "The failure appears to have been a lack of INTERNET !", e2);
        }
    }
}
