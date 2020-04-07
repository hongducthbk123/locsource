package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

/* renamed from: com.tencent.bugly.proguard.ap */
/* compiled from: BUGLY */
public final class C1983ap extends C1994j {

    /* renamed from: i */
    private static Map<String, String> f1549i = new HashMap();

    /* renamed from: a */
    public long f1550a = 0;

    /* renamed from: b */
    public byte f1551b = 0;

    /* renamed from: c */
    public String f1552c = "";

    /* renamed from: d */
    public String f1553d = "";

    /* renamed from: e */
    public String f1554e = "";

    /* renamed from: f */
    public Map<String, String> f1555f = null;

    /* renamed from: g */
    public String f1556g = "";

    /* renamed from: h */
    public boolean f1557h = true;

    /* renamed from: a */
    public final void mo19550a(C1993i iVar) {
        iVar.mo19578a(this.f1550a, 0);
        iVar.mo19576a(this.f1551b, 1);
        if (this.f1552c != null) {
            iVar.mo19581a(this.f1552c, 2);
        }
        if (this.f1553d != null) {
            iVar.mo19581a(this.f1553d, 3);
        }
        if (this.f1554e != null) {
            iVar.mo19581a(this.f1554e, 4);
        }
        if (this.f1555f != null) {
            iVar.mo19583a(this.f1555f, 5);
        }
        if (this.f1556g != null) {
            iVar.mo19581a(this.f1556g, 6);
        }
        iVar.mo19585a(this.f1557h, 7);
    }

    static {
        f1549i.put("", "");
    }

    /* renamed from: a */
    public final void mo19549a(C1991h hVar) {
        this.f1550a = hVar.mo19565a(this.f1550a, 0, true);
        this.f1551b = hVar.mo19562a(this.f1551b, 1, true);
        this.f1552c = hVar.mo19572b(2, false);
        this.f1553d = hVar.mo19572b(3, false);
        this.f1554e = hVar.mo19572b(4, false);
        this.f1555f = (Map) hVar.mo19567a(f1549i, 5, false);
        this.f1556g = hVar.mo19572b(6, false);
        boolean z = this.f1557h;
        this.f1557h = hVar.mo19571a(7, false);
    }
}
