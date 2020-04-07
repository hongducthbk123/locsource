package p004cn.jpush.android.api;

import java.util.Set;

/* renamed from: cn.jpush.android.api.a */
public final class C0559a {

    /* renamed from: a */
    public String f729a;

    /* renamed from: b */
    public Set<String> f730b;

    /* renamed from: c */
    public TagAliasCallback f731c;

    /* renamed from: d */
    public int f732d;

    /* renamed from: e */
    public int f733e = 0;

    /* renamed from: f */
    public int f734f = 0;

    /* renamed from: g */
    private long f735g;

    public C0559a(String str, Set<String> set, TagAliasCallback tagAliasCallback, long j, int i, int i2) {
        this.f729a = str;
        this.f730b = set;
        this.f731c = tagAliasCallback;
        this.f735g = j;
        this.f733e = i;
        this.f734f = i2;
    }

    public C0559a(int i, Set<String> set, long j, int i2, int i3) {
        this.f732d = i;
        this.f730b = set;
        this.f735g = j;
        this.f733e = i2;
        this.f734f = i3;
    }

    public C0559a(int i, String str, long j, int i2, int i3) {
        this.f732d = i;
        this.f729a = str;
        this.f735g = j;
        this.f733e = i2;
        this.f734f = i3;
    }

    /* renamed from: a */
    public final boolean mo6826a(long j) {
        if (this.f733e != 0 || System.currentTimeMillis() - this.f735g <= 30000) {
            return false;
        }
        return true;
    }

    public final String toString() {
        return "CallBackParams{sendTime=" + this.f735g + ", alias='" + this.f729a + '\'' + ", tags=" + this.f730b + ", tagAliasCallBack=" + this.f731c + ", sequence=" + this.f732d + ", protoType=" + this.f733e + ", action=" + this.f734f + '}';
    }
}
