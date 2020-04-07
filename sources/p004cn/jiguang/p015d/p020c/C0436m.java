package p004cn.jiguang.p015d.p020c;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Arrays;

/* renamed from: cn.jiguang.d.c.m */
public abstract class C0436m implements Serializable, Cloneable, Comparable {

    /* renamed from: e */
    private static final DecimalFormat f376e;

    /* renamed from: a */
    protected C0433j f377a;

    /* renamed from: b */
    protected int f378b;

    /* renamed from: c */
    protected int f379c;

    /* renamed from: d */
    protected long f380d;

    static {
        DecimalFormat decimalFormat = new DecimalFormat();
        f376e = decimalFormat;
        decimalFormat.setMinimumIntegerDigits(3);
    }

    protected C0436m() {
    }

    /* renamed from: a */
    static C0436m m582a(C0427d dVar, int i) {
        C0433j jVar = new C0433j(dVar);
        int g = dVar.mo6493g();
        int g2 = dVar.mo6493g();
        if (i == 0) {
            return m583a(jVar, g, g2);
        }
        long h = dVar.mo6494h();
        int g3 = dVar.mo6493g();
        C0436m a = m584a(jVar, g, g2, h);
        if (dVar == null) {
            return a;
        }
        if (dVar.mo6487b() < g3) {
            throw new C0443t("truncated record");
        }
        dVar.mo6485a(g3);
        a.mo6533a(dVar);
        if (dVar.mo6487b() > 0) {
            throw new C0443t("invalid record length");
        }
        dVar.mo6489c();
        return a;
    }

    /* renamed from: a */
    public static C0436m m583a(C0433j jVar, int i, int i2) {
        if (jVar.mo6521a()) {
            return m584a(jVar, i, i2, 0);
        }
        throw new C0437n(jVar);
    }

    /* renamed from: a */
    private static final C0436m m584a(C0433j jVar, int i, int i2, long j) {
        C0439p pVar = new C0439p();
        pVar.f377a = jVar;
        pVar.f378b = i;
        pVar.f379c = i2;
        pVar.f380d = j;
        return pVar;
    }

    /* renamed from: h */
    private byte[] mo6548h() {
        C0428e eVar = new C0428e();
        mo6535a(eVar, true);
        return eVar.mo6502b();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public abstract String mo6531a();

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo6532a(long j) {
        this.f380d = j;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public abstract void mo6533a(C0427d dVar);

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo6534a(C0428e eVar, C0425b bVar) {
        this.f377a.mo6519a(eVar, bVar);
        eVar.mo6503c(this.f378b);
        eVar.mo6503c(this.f379c);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public abstract void mo6535a(C0428e eVar, boolean z);

    /* renamed from: a */
    public final boolean mo6536a(C0436m mVar) {
        return this.f378b == mVar.f378b && this.f379c == mVar.f379c && this.f377a.equals(mVar.f377a);
    }

    /* renamed from: b */
    public final C0433j mo6537b() {
        return this.f377a;
    }

    /* renamed from: c */
    public final int mo6538c() {
        return this.f378b;
    }

    public int compareTo(Object obj) {
        int i = 0;
        C0436m mVar = (C0436m) obj;
        if (this == mVar) {
            return 0;
        }
        int compareTo = this.f377a.compareTo(mVar.f377a);
        if (compareTo != 0) {
            return compareTo;
        }
        int i2 = this.f379c - mVar.f379c;
        if (i2 != 0) {
            return i2;
        }
        int i3 = this.f378b - mVar.f378b;
        if (i3 != 0) {
            return i3;
        }
        byte[] h = mo6548h();
        byte[] h2 = mVar.mo6548h();
        while (i < h.length && i < h2.length) {
            int i4 = (h[i] & 255) - (h2[i] & 255);
            if (i4 != 0) {
                return i4;
            }
            i++;
        }
        return h.length - h2.length;
    }

    /* renamed from: d */
    public final int mo6540d() {
        return this.f378b;
    }

    /* renamed from: e */
    public final int mo6541e() {
        return this.f379c;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof C0436m)) {
            return false;
        }
        C0436m mVar = (C0436m) obj;
        if (this.f378b == mVar.f378b && this.f379c == mVar.f379c && this.f377a.equals(mVar.f377a)) {
            return Arrays.equals(mo6548h(), mVar.mo6548h());
        }
        return false;
    }

    /* renamed from: f */
    public final long mo6543f() {
        return this.f380d;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: g */
    public final C0436m mo6544g() {
        try {
            return (C0436m) clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException();
        }
    }

    public int hashCode() {
        C0428e eVar = new C0428e();
        this.f377a.mo6518a(eVar);
        eVar.mo6503c(this.f378b);
        eVar.mo6503c(this.f379c);
        eVar.mo6498a(0);
        int a = eVar.mo6495a();
        eVar.mo6503c(0);
        mo6535a(eVar, true);
        eVar.mo6497a((eVar.mo6495a() - a) - 2, a);
        byte[] b = eVar.mo6502b();
        int i = 0;
        for (byte b2 : b) {
            i += (i << 3) + (b2 & 255);
        }
        return i;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.f377a);
        if (stringBuffer.length() < 8) {
            stringBuffer.append("\t");
        }
        if (stringBuffer.length() < 16) {
            stringBuffer.append("\t");
        }
        stringBuffer.append("\t");
        String a = mo6531a();
        if (!a.equals("")) {
            stringBuffer.append("\t");
            stringBuffer.append(a);
        }
        return stringBuffer.toString();
    }
}
