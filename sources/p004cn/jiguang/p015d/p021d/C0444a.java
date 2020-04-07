package p004cn.jiguang.p015d.p021d;

import java.io.Serializable;
import p004cn.jiguang.p031g.C0530k;

/* renamed from: cn.jiguang.d.d.a */
public final class C0444a implements Serializable {

    /* renamed from: a */
    public String f392a = "";

    /* renamed from: b */
    public String f393b = "";

    /* renamed from: c */
    public int f394c = 0;

    /* renamed from: d */
    public String f395d;

    public C0444a() {
    }

    public C0444a(String str, String str2, int i) {
        this.f392a = str;
        this.f393b = str2;
        this.f394c = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0444a)) {
            return false;
        }
        C0444a aVar = (C0444a) obj;
        return !C0530k.m1099a(this.f392a) && !C0530k.m1099a(this.f393b) && !C0530k.m1099a(aVar.f392a) && !C0530k.m1099a(aVar.f393b) && C0530k.m1100a(this.f392a, aVar.f392a) && C0530k.m1100a(this.f393b, aVar.f393b);
    }

    public final String toString() {
        return "AWakeInfo{pk_name='" + this.f392a + '\'' + ", sv_name='" + this.f393b + '\'' + ", target_version=" + this.f394c + ", providerAuthority='" + this.f395d + '\'' + '}';
    }
}
