package p004cn.jpush.android.p040d;

import p004cn.jiguang.api.JCoreInterface;

/* renamed from: cn.jpush.android.d.j */
public final class C0587j {

    /* renamed from: a */
    private String f775a;

    /* renamed from: b */
    private String f776b;

    /* renamed from: c */
    private boolean f777c = false;

    /* renamed from: d */
    private long f778d;

    public C0587j(String str, String str2) {
        if (JCoreInterface.getDebugMode()) {
            this.f775a = str;
            this.f776b = str2;
            this.f778d = System.currentTimeMillis();
        }
    }

    /* renamed from: a */
    public final void mo6856a() {
        if (JCoreInterface.getDebugMode() && !this.f777c) {
            this.f778d = System.currentTimeMillis();
        }
    }
}
