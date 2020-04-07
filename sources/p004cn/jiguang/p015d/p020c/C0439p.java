package p004cn.jiguang.p015d.p020c;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;

/* renamed from: cn.jiguang.d.c.p */
public final class C0439p extends C0436m {

    /* renamed from: e */
    private int f385e;

    /* renamed from: f */
    private int f386f;

    /* renamed from: g */
    private int f387g;

    /* renamed from: h */
    private C0433j f388h;

    C0439p() {
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final String mo6531a() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.f385e + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        stringBuffer.append(this.f386f + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        stringBuffer.append(this.f387g + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        stringBuffer.append(this.f388h);
        return stringBuffer.toString();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo6533a(C0427d dVar) {
        this.f385e = dVar.mo6493g();
        this.f386f = dVar.mo6493g();
        this.f387g = dVar.mo6493g();
        this.f388h = new C0433j(dVar);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo6535a(C0428e eVar, boolean z) {
        eVar.mo6503c(this.f385e);
        eVar.mo6503c(this.f386f);
        eVar.mo6503c(this.f387g);
        this.f388h.mo6520a(eVar, (C0425b) null, true);
    }

    /* renamed from: h */
    public final int mo6548h() {
        return this.f387g;
    }

    /* renamed from: i */
    public final C0433j mo6549i() {
        return this.f388h;
    }
}
