package p004cn.jiguang.p015d.p021d;

import android.content.Context;
import java.io.File;
import p004cn.jiguang.p015d.p026g.C0484e;

/* renamed from: cn.jiguang.d.d.o */
final class C0458o implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Context f434a;

    C0458o(Context context) {
        this.f434a = context;
    }

    public final void run() {
        try {
            File[] a = C0484e.m803a(C0457n.m700e(this.f434a), true);
            if (a != null && a.length > 0) {
                for (File a2 : a) {
                    C0457n.m694a(this.f434a, a2);
                }
            }
        } catch (Exception e) {
        }
    }
}
