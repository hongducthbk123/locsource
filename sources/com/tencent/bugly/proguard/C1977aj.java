package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.tencent.bugly.proguard.aj */
/* compiled from: BUGLY */
public final class C1977aj extends C1994j {

    /* renamed from: A */
    private static ArrayList<C1976ai> f1465A = new ArrayList<>();

    /* renamed from: B */
    private static Map<String, String> f1466B = new HashMap();

    /* renamed from: C */
    private static Map<String, String> f1467C = new HashMap();

    /* renamed from: v */
    private static Map<String, String> f1468v = new HashMap();

    /* renamed from: w */
    private static C1975ah f1469w = new C1975ah();

    /* renamed from: x */
    private static C1974ag f1470x = new C1974ag();

    /* renamed from: y */
    private static ArrayList<C1974ag> f1471y = new ArrayList<>();

    /* renamed from: z */
    private static ArrayList<C1974ag> f1472z = new ArrayList<>();

    /* renamed from: a */
    public String f1473a = "";

    /* renamed from: b */
    public long f1474b = 0;

    /* renamed from: c */
    public String f1475c = "";

    /* renamed from: d */
    public String f1476d = "";

    /* renamed from: e */
    public String f1477e = "";

    /* renamed from: f */
    public String f1478f = "";

    /* renamed from: g */
    public String f1479g = "";

    /* renamed from: h */
    public Map<String, String> f1480h = null;

    /* renamed from: i */
    public String f1481i = "";

    /* renamed from: j */
    public C1975ah f1482j = null;

    /* renamed from: k */
    public int f1483k = 0;

    /* renamed from: l */
    public String f1484l = "";

    /* renamed from: m */
    public String f1485m = "";

    /* renamed from: n */
    public C1974ag f1486n = null;

    /* renamed from: o */
    public ArrayList<C1974ag> f1487o = null;

    /* renamed from: p */
    public ArrayList<C1974ag> f1488p = null;

    /* renamed from: q */
    public ArrayList<C1976ai> f1489q = null;

    /* renamed from: r */
    public Map<String, String> f1490r = null;

    /* renamed from: s */
    public Map<String, String> f1491s = null;

    /* renamed from: t */
    public String f1492t = "";

    /* renamed from: u */
    private boolean f1493u = true;

    /* renamed from: a */
    public final void mo19550a(C1993i iVar) {
        iVar.mo19581a(this.f1473a, 0);
        iVar.mo19578a(this.f1474b, 1);
        iVar.mo19581a(this.f1475c, 2);
        if (this.f1476d != null) {
            iVar.mo19581a(this.f1476d, 3);
        }
        if (this.f1477e != null) {
            iVar.mo19581a(this.f1477e, 4);
        }
        if (this.f1478f != null) {
            iVar.mo19581a(this.f1478f, 5);
        }
        if (this.f1479g != null) {
            iVar.mo19581a(this.f1479g, 6);
        }
        if (this.f1480h != null) {
            iVar.mo19583a(this.f1480h, 7);
        }
        if (this.f1481i != null) {
            iVar.mo19581a(this.f1481i, 8);
        }
        if (this.f1482j != null) {
            iVar.mo19579a((C1994j) this.f1482j, 9);
        }
        iVar.mo19577a(this.f1483k, 10);
        if (this.f1484l != null) {
            iVar.mo19581a(this.f1484l, 11);
        }
        if (this.f1485m != null) {
            iVar.mo19581a(this.f1485m, 12);
        }
        if (this.f1486n != null) {
            iVar.mo19579a((C1994j) this.f1486n, 13);
        }
        if (this.f1487o != null) {
            iVar.mo19582a((Collection<T>) this.f1487o, 14);
        }
        if (this.f1488p != null) {
            iVar.mo19582a((Collection<T>) this.f1488p, 15);
        }
        if (this.f1489q != null) {
            iVar.mo19582a((Collection<T>) this.f1489q, 16);
        }
        if (this.f1490r != null) {
            iVar.mo19583a(this.f1490r, 17);
        }
        if (this.f1491s != null) {
            iVar.mo19583a(this.f1491s, 18);
        }
        if (this.f1492t != null) {
            iVar.mo19581a(this.f1492t, 19);
        }
        iVar.mo19585a(this.f1493u, 20);
    }

    static {
        f1468v.put("", "");
        f1471y.add(new C1974ag());
        f1472z.add(new C1974ag());
        f1465A.add(new C1976ai());
        f1466B.put("", "");
        f1467C.put("", "");
    }

    /* renamed from: a */
    public final void mo19549a(C1991h hVar) {
        this.f1473a = hVar.mo19572b(0, true);
        this.f1474b = hVar.mo19565a(this.f1474b, 1, true);
        this.f1475c = hVar.mo19572b(2, true);
        this.f1476d = hVar.mo19572b(3, false);
        this.f1477e = hVar.mo19572b(4, false);
        this.f1478f = hVar.mo19572b(5, false);
        this.f1479g = hVar.mo19572b(6, false);
        this.f1480h = (Map) hVar.mo19567a(f1468v, 7, false);
        this.f1481i = hVar.mo19572b(8, false);
        this.f1482j = (C1975ah) hVar.mo19566a((C1994j) f1469w, 9, false);
        this.f1483k = hVar.mo19563a(this.f1483k, 10, false);
        this.f1484l = hVar.mo19572b(11, false);
        this.f1485m = hVar.mo19572b(12, false);
        this.f1486n = (C1974ag) hVar.mo19566a((C1994j) f1470x, 13, false);
        this.f1487o = (ArrayList) hVar.mo19567a(f1471y, 14, false);
        this.f1488p = (ArrayList) hVar.mo19567a(f1472z, 15, false);
        this.f1489q = (ArrayList) hVar.mo19567a(f1465A, 16, false);
        this.f1490r = (Map) hVar.mo19567a(f1466B, 17, false);
        this.f1491s = (Map) hVar.mo19567a(f1467C, 18, false);
        this.f1492t = hVar.mo19572b(19, false);
        boolean z = this.f1493u;
        this.f1493u = hVar.mo19571a(20, false);
    }
}
