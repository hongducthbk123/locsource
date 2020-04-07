package p004cn.jiguang.p015d.p017b;

import p004cn.jiguang.p029e.C0501d;
import p004cn.jiguang.p031g.C0506a;

/* renamed from: cn.jiguang.d.b.h */
public final class C0421h {

    /* renamed from: a */
    int f313a;

    /* renamed from: b */
    int f314b = 0;

    /* renamed from: c */
    long f315c;

    /* renamed from: d */
    byte[] f316d;

    /* renamed from: e */
    int f317e;

    /* renamed from: f */
    String f318f;

    public C0421h(byte[] bArr, String str, int i) {
        this.f316d = bArr;
        this.f313a = i;
        this.f318f = str;
        if (bArr == null || bArr.length < 24) {
            C0501d.m907c("RequestCacheManager", "parse requesting failed");
            return;
        }
        this.f317e = C0506a.m929a(bArr[3]);
        this.f315c = 0;
        for (int i2 = 0; i2 < 8; i2++) {
            this.f315c = (this.f315c << 8) + ((long) (bArr[i2 + 4] & 255));
        }
    }

    /* renamed from: a */
    public final String mo6466a() {
        return C0420g.m488b(this.f315c, this.f318f);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C0421h hVar = (C0421h) obj;
        if (this.f315c != hVar.f315c) {
            return false;
        }
        if (this.f317e != hVar.f317e) {
            return false;
        }
        return this.f318f != null ? this.f318f.equals(hVar.f318f) : hVar.f318f == null;
    }

    public final int hashCode() {
        return (this.f318f != null ? this.f318f.hashCode() : 0) + ((((((int) (this.f315c ^ (this.f315c >>> 32))) + 31) * 31) + this.f317e) * 31);
    }

    public final String toString() {
        return "Requesting{timeout=" + this.f313a + ", times=" + this.f314b + ", rid=" + this.f315c + ", command=" + this.f317e + ", sdkType='" + this.f318f + '\'' + '}';
    }
}
