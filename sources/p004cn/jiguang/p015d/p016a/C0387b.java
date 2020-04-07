package p004cn.jiguang.p015d.p016a;

/* renamed from: cn.jiguang.d.a.b */
public final class C0387b {

    /* renamed from: a */
    private static volatile Long f212a;

    /* renamed from: b */
    private static volatile Long f213b;

    /* renamed from: a */
    static synchronized void m299a(long j, long j2) {
        synchronized (C0387b.class) {
            f212a = Long.valueOf(j);
            f213b = Long.valueOf(j2);
        }
    }

    /* renamed from: a */
    static boolean m300a() {
        return (f212a == null || f213b == null) ? false : true;
    }

    /* renamed from: b */
    static long m301b() {
        return f212a.longValue() - f213b.longValue();
    }
}
