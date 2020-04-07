package p004cn.jiguang.p005a.p006a.p007a;

/* renamed from: cn.jiguang.a.a.a.f */
final class C0340f implements Runnable {

    /* renamed from: a */
    final /* synthetic */ byte[] f40a;

    /* renamed from: b */
    final /* synthetic */ int f41b;

    /* renamed from: c */
    final /* synthetic */ int f42c;

    /* renamed from: d */
    final /* synthetic */ C0341g f43d;

    /* renamed from: e */
    final /* synthetic */ C0338d f44e;

    C0340f(C0338d dVar, byte[] bArr, int i, int i2, C0341g gVar) {
        this.f44e = dVar;
        this.f40a = bArr;
        this.f41b = i;
        this.f42c = i2;
        this.f43d = gVar;
    }

    public final void run() {
        this.f44e.m30b(this.f40a, this.f41b, this.f42c);
        if (this.f43d != null) {
            this.f43d.mo6200a();
        }
        this.f44e.f35a.quit();
    }
}
