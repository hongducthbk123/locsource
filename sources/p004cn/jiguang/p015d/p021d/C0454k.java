package p004cn.jiguang.p015d.p021d;

import android.content.Context;
import android.os.Bundle;
import p004cn.jiguang.p015d.p017b.C0395a;

/* renamed from: cn.jiguang.d.d.k */
final class C0454k implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C0453j f420a;

    /* renamed from: b */
    private String f421b;

    /* renamed from: c */
    private Bundle f422c;

    /* renamed from: d */
    private int f423d;

    /* renamed from: e */
    private Context f424e;

    public C0454k(C0453j jVar, Context context, String str, Bundle bundle, int i) {
        this.f420a = jVar;
        this.f421b = str;
        this.f422c = bundle;
        this.f424e = context;
        this.f423d = i;
    }

    public final void run() {
        if (this.f423d == 1) {
            C0453j.m667a(this.f420a, this.f421b, this.f422c);
        } else if (C0395a.m388c()) {
            this.f420a.mo6569c(this.f424e, this.f421b, this.f422c);
        } else if (this.f420a.f419e) {
            C0453j.m667a(this.f420a, this.f421b, this.f422c);
        }
    }
}
