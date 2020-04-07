package p004cn.jiguang.p015d.p020c;

/* renamed from: cn.jiguang.d.c.b */
public final class C0425b {

    /* renamed from: a */
    private C0426c[] f336a = new C0426c[17];

    /* renamed from: a */
    public final int mo6482a(C0433j jVar) {
        int i = -1;
        for (C0426c cVar = this.f336a[(jVar.hashCode() & ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED) % 17]; cVar != null; cVar = cVar.f339c) {
            if (cVar.f337a.equals(jVar)) {
                i = cVar.f338b;
            }
        }
        return i;
    }

    /* renamed from: a */
    public final void mo6483a(int i, C0433j jVar) {
        if (i <= 16383) {
            int hashCode = (jVar.hashCode() & ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED) % 17;
            C0426c cVar = new C0426c(0);
            cVar.f337a = jVar;
            cVar.f338b = i;
            cVar.f339c = this.f336a[hashCode];
            this.f336a[hashCode] = cVar;
        }
    }
}
