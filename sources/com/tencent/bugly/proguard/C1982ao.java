package com.tencent.bugly.proguard;

import com.tencent.bugly.crashreport.crash.jni.C1966b;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.tencent.bugly.proguard.ao */
/* compiled from: BUGLY */
public final class C1982ao extends C1994j implements Cloneable {

    /* renamed from: m */
    private static C1981an f1534m = new C1981an();

    /* renamed from: n */
    private static Map<String, String> f1535n = new HashMap();

    /* renamed from: o */
    private static /* synthetic */ boolean f1536o;

    /* renamed from: a */
    public boolean f1537a = true;

    /* renamed from: b */
    public boolean f1538b = true;

    /* renamed from: c */
    public boolean f1539c = true;

    /* renamed from: d */
    public String f1540d = "";

    /* renamed from: e */
    public String f1541e = "";

    /* renamed from: f */
    public C1981an f1542f = null;

    /* renamed from: g */
    public Map<String, String> f1543g = null;

    /* renamed from: h */
    public long f1544h = 0;

    /* renamed from: i */
    public int f1545i = 0;

    /* renamed from: j */
    private String f1546j = "";

    /* renamed from: k */
    private String f1547k = "";

    /* renamed from: l */
    private int f1548l = 0;

    static {
        boolean z;
        if (!C1982ao.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        f1536o = z;
        f1535n.put("", "");
    }

    public final boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        C1982ao aoVar = (C1982ao) o;
        if (!C1995k.m2011a(this.f1537a, aoVar.f1537a) || !C1995k.m2011a(this.f1538b, aoVar.f1538b) || !C1995k.m2011a(this.f1539c, aoVar.f1539c) || !C1995k.m2010a((Object) this.f1540d, (Object) aoVar.f1540d) || !C1995k.m2010a((Object) this.f1541e, (Object) aoVar.f1541e) || !C1995k.m2010a((Object) this.f1542f, (Object) aoVar.f1542f) || !C1995k.m2010a((Object) this.f1543g, (Object) aoVar.f1543g) || !C1995k.m2009a(this.f1544h, aoVar.f1544h) || !C1995k.m2010a((Object) this.f1546j, (Object) aoVar.f1546j) || !C1995k.m2010a((Object) this.f1547k, (Object) aoVar.f1547k) || !C1995k.m2008a(this.f1548l, aoVar.f1548l) || !C1995k.m2008a(this.f1545i, aoVar.f1545i)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        try {
            throw new Exception("Need define key first!");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public final Object clone() {
        boolean z = false;
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            if (f1536o) {
                return z;
            }
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    public final void mo19550a(C1993i iVar) {
        iVar.mo19585a(this.f1537a, 0);
        iVar.mo19585a(this.f1538b, 1);
        iVar.mo19585a(this.f1539c, 2);
        if (this.f1540d != null) {
            iVar.mo19581a(this.f1540d, 3);
        }
        if (this.f1541e != null) {
            iVar.mo19581a(this.f1541e, 4);
        }
        if (this.f1542f != null) {
            iVar.mo19579a((C1994j) this.f1542f, 5);
        }
        if (this.f1543g != null) {
            iVar.mo19583a(this.f1543g, 6);
        }
        iVar.mo19578a(this.f1544h, 7);
        if (this.f1546j != null) {
            iVar.mo19581a(this.f1546j, 8);
        }
        if (this.f1547k != null) {
            iVar.mo19581a(this.f1547k, 9);
        }
        iVar.mo19577a(this.f1548l, 10);
        iVar.mo19577a(this.f1545i, 11);
    }

    /* renamed from: a */
    public final void mo19549a(C1991h hVar) {
        boolean z = this.f1537a;
        this.f1537a = hVar.mo19571a(0, true);
        boolean z2 = this.f1538b;
        this.f1538b = hVar.mo19571a(1, true);
        boolean z3 = this.f1539c;
        this.f1539c = hVar.mo19571a(2, true);
        this.f1540d = hVar.mo19572b(3, false);
        this.f1541e = hVar.mo19572b(4, false);
        this.f1542f = (C1981an) hVar.mo19566a((C1994j) f1534m, 5, false);
        this.f1543g = (Map) hVar.mo19567a(f1535n, 6, false);
        this.f1544h = hVar.mo19565a(this.f1544h, 7, false);
        this.f1546j = hVar.mo19572b(8, false);
        this.f1547k = hVar.mo19572b(9, false);
        this.f1548l = hVar.mo19563a(this.f1548l, 10, false);
        this.f1545i = hVar.mo19563a(this.f1545i, 11, false);
    }

    /* renamed from: a */
    public final void mo19551a(StringBuilder sb, int i) {
        C1966b bVar = new C1966b(sb, i);
        bVar.mo19531a(this.f1537a, "enable");
        bVar.mo19531a(this.f1538b, "enableUserInfo");
        bVar.mo19531a(this.f1539c, "enableQuery");
        bVar.mo19539b(this.f1540d, "url");
        bVar.mo19539b(this.f1541e, "expUrl");
        bVar.mo19526a((C1994j) this.f1542f, "security");
        bVar.mo19529a(this.f1543g, "valueMap");
        bVar.mo19525a(this.f1544h, "strategylastUpdateTime");
        bVar.mo19539b(this.f1546j, "httpsUrl");
        bVar.mo19539b(this.f1547k, "httpsExpUrl");
        bVar.mo19524a(this.f1548l, "eventRecordCount");
        bVar.mo19524a(this.f1545i, "eventTimeInterval");
    }
}
