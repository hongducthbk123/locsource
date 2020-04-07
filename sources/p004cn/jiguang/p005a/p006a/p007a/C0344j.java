package p004cn.jiguang.p005a.p006a.p007a;

import p004cn.jiguang.p031g.C0533n;

/* renamed from: cn.jiguang.a.a.a.j */
final class C0344j implements C0341g {

    /* renamed from: a */
    final /* synthetic */ C0533n f55a;

    /* renamed from: b */
    final /* synthetic */ int f56b;

    /* renamed from: c */
    final /* synthetic */ C0342h f57c;

    C0344j(C0342h hVar, C0533n nVar, int i) {
        this.f57c = hVar;
        this.f55a = nVar;
        this.f56b = i;
    }

    /* renamed from: a */
    public final void mo6200a() {
        synchronized (C0342h.f45i) {
            this.f55a.mo6706a("thread" + this.f56b);
            this.f57c.f53h[this.f56b] = true;
            C0342h.f45i.notifyAll();
        }
    }
}
