package p004cn.jiguang.p015d.p016a;

import android.text.TextUtils;

/* renamed from: cn.jiguang.d.a.c */
public final class C0388c {

    /* renamed from: a */
    private String f214a;

    /* renamed from: b */
    private String f215b;

    /* renamed from: c */
    private String f216c;

    public C0388c(String str, String str2, String str3) {
        this.f214a = str;
        this.f215b = str2;
        this.f216c = str3;
    }

    /* renamed from: a */
    public final String mo6352a() {
        return this.f214a;
    }

    /* renamed from: b */
    public final String mo6353b() {
        return this.f215b;
    }

    /* renamed from: c */
    public final String mo6354c() {
        return this.f216c;
    }

    /* renamed from: d */
    public final boolean mo6355d() {
        return TextUtils.isEmpty(this.f214a) && TextUtils.isEmpty(this.f215b) && TextUtils.isEmpty(this.f216c);
    }
}
