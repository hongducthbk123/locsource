package p004cn.jiguang.p015d.p016a;

import android.content.Context;
import java.util.Random;
import p004cn.jiguang.p031g.p033b.C0514e;

/* renamed from: cn.jiguang.d.a.f */
public final class C0391f {

    /* renamed from: a */
    private static C0514e f228a;

    /* renamed from: a */
    public static synchronized long m348a() {
        long longValue;
        synchronized (C0391f.class) {
            longValue = ((Long) m351b(null).mo6688a("next_rid", Long.valueOf(-1))).longValue();
            if (longValue != -1) {
                longValue = m349a(longValue);
                m351b(null).mo6692b("next_rid", Long.valueOf(longValue));
            }
            if (longValue == -1) {
                longValue = m349a((long) Math.abs(new Random().nextInt(32767)));
                m351b(null).mo6692b("next_rid", Long.valueOf(longValue));
            }
        }
        return longValue;
    }

    /* renamed from: a */
    private static long m349a(long j) {
        return (j % 2 == 0 ? 1 + j : j + 2) % 32767;
    }

    /* renamed from: a */
    public static void m350a(Context context) {
        f228a = C0514e.m1032a(context, "cn.jpush.preferences.v2.rid");
    }

    /* renamed from: b */
    private static C0514e m351b(Context context) {
        if (f228a == null) {
            m350a((Context) null);
        }
        return f228a;
    }
}
