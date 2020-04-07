package p004cn.jiguang.p015d.p025f;

/* renamed from: cn.jiguang.d.f.d */
public final class C0477d {

    /* renamed from: a */
    private static C0477d f483a;

    /* renamed from: c */
    private static final Object f484c = new Object();

    /* renamed from: b */
    private C0474a f485b;

    private C0477d() {
    }

    /* renamed from: a */
    public static C0477d m767a() {
        if (f483a == null) {
            synchronized (f484c) {
                if (f483a == null) {
                    f483a = new C0477d();
                }
            }
        }
        return f483a;
    }

    /* renamed from: b */
    public final C0474a mo6616b() {
        if (this.f485b == null) {
            this.f485b = new C0476c();
        }
        return this.f485b;
    }
}
