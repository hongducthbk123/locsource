package p004cn.jiguang.p015d.p028h;

import android.content.Context;

/* renamed from: cn.jiguang.d.h.g */
final class C0496g implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Context f544a;

    /* renamed from: b */
    final /* synthetic */ boolean f545b;

    /* renamed from: c */
    final /* synthetic */ C0495f f546c;

    C0496g(C0495f fVar, Context context, boolean z) {
        this.f546c = fVar;
        this.f544a = context;
        this.f545b = z;
    }

    public final void run() {
        this.f546c.f541a.mo6632a(this.f544a, this.f545b);
        this.f546c.f542b.mo6632a(this.f544a, this.f545b);
    }
}
