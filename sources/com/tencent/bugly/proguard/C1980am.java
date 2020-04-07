package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

/* renamed from: com.tencent.bugly.proguard.am */
/* compiled from: BUGLY */
public final class C1980am extends C1994j {

    /* renamed from: i */
    private static byte[] f1522i;

    /* renamed from: j */
    private static Map<String, String> f1523j = new HashMap();

    /* renamed from: a */
    public byte f1524a = 0;

    /* renamed from: b */
    public int f1525b = 0;

    /* renamed from: c */
    public byte[] f1526c = null;

    /* renamed from: d */
    public String f1527d = "";

    /* renamed from: e */
    public long f1528e = 0;

    /* renamed from: f */
    public String f1529f = "";

    /* renamed from: g */
    public Map<String, String> f1530g = null;

    /* renamed from: h */
    private String f1531h = "";

    /* renamed from: a */
    public final void mo19550a(C1993i iVar) {
        iVar.mo19576a(this.f1524a, 0);
        iVar.mo19577a(this.f1525b, 1);
        if (this.f1526c != null) {
            iVar.mo19586a(this.f1526c, 2);
        }
        if (this.f1527d != null) {
            iVar.mo19581a(this.f1527d, 3);
        }
        iVar.mo19578a(this.f1528e, 4);
        if (this.f1531h != null) {
            iVar.mo19581a(this.f1531h, 5);
        }
        if (this.f1529f != null) {
            iVar.mo19581a(this.f1529f, 6);
        }
        if (this.f1530g != null) {
            iVar.mo19583a(this.f1530g, 7);
        }
    }

    static {
        byte[] bArr = new byte[1];
        f1522i = bArr;
        bArr[0] = 0;
        f1523j.put("", "");
    }

    /* renamed from: a */
    public final void mo19549a(C1991h hVar) {
        this.f1524a = hVar.mo19562a(this.f1524a, 0, true);
        this.f1525b = hVar.mo19563a(this.f1525b, 1, true);
        byte[] bArr = f1522i;
        this.f1526c = hVar.mo19573c(2, false);
        this.f1527d = hVar.mo19572b(3, false);
        this.f1528e = hVar.mo19565a(this.f1528e, 4, false);
        this.f1531h = hVar.mo19572b(5, false);
        this.f1529f = hVar.mo19572b(6, false);
        this.f1530g = (Map) hVar.mo19567a(f1523j, 7, false);
    }
}
