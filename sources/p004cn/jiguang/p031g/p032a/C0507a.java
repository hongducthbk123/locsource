package p004cn.jiguang.p031g.p032a;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import p004cn.jiguang.p013b.C0379a;
import p004cn.jiguang.p013b.C0380b;
import p004cn.jiguang.p015d.C0385a;
import p004cn.jiguang.p015d.p017b.C0417d;
import p004cn.jiguang.p015d.p021d.C0445b;
import p004cn.jiguang.p015d.p021d.C0453j;
import p004cn.jiguang.p029e.C0501d;

/* renamed from: cn.jiguang.g.a.a */
public final class C0507a extends C0380b {

    /* renamed from: a */
    private static boolean f566a = false;

    /* renamed from: b */
    private static C0379a f567b;

    /* renamed from: c */
    private static final DeathRecipient f568c = new C0508b();

    /* renamed from: a */
    public static void m999a(C0379a aVar) {
        if (aVar != f567b) {
            if (f567b != null) {
                try {
                    f567b.asBinder().unlinkToDeath(f568c, 0);
                } catch (Throwable th) {
                }
            }
            f567b = aVar;
            try {
                f567b.asBinder().linkToDeath(f568c, 0);
            } catch (Throwable th2) {
            }
        }
        f566a = false;
    }

    /* renamed from: b */
    public static C0379a m1000b() {
        return f567b;
    }

    /* renamed from: c */
    public static boolean m1001c() {
        return f567b != null;
    }

    /* renamed from: d */
    public static boolean m1002d() {
        return f566a;
    }

    /* renamed from: e */
    public static void m1003e() {
        f566a = true;
    }

    /* renamed from: a */
    public final IBinder mo6339a(String str, String str2) {
        C0445b.m618a();
        return C0445b.m628c(str, str2);
    }

    /* renamed from: a */
    public final void mo6340a(String str, Bundle bundle) {
        if (str != null && bundle != null) {
            try {
                C0453j.m665a().mo6567a(C0385a.f198e, str, bundle);
            } catch (Throwable th) {
                C0501d.m907c("DataShare", "onAction error:" + th.getMessage());
            }
        }
    }

    /* renamed from: a */
    public final boolean mo6341a() {
        C0417d.m446a();
        return C0417d.m457d();
    }
}
