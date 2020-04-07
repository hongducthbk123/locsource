package com.tencent.bugly.proguard;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.tencent.bugly.proguard.d */
/* compiled from: BUGLY */
public final class C1987d extends C1986c {

    /* renamed from: f */
    private static HashMap<String, byte[]> f1568f = null;

    /* renamed from: g */
    private static HashMap<String, HashMap<String, byte[]>> f1569g = null;

    /* renamed from: e */
    private C1989f f1570e = new C1989f();

    /* renamed from: a */
    public final <T> void mo19541a(String str, T t) {
        if (str.startsWith(".")) {
            throw new IllegalArgumentException("put name can not startwith . , now is " + str);
        }
        super.mo19541a(str, t);
    }

    /* renamed from: b */
    public final void mo19556b() {
        super.mo19556b();
        this.f1570e.f1575a = 3;
    }

    /* renamed from: a */
    public final byte[] mo19543a() {
        if (this.f1570e.f1575a != 2) {
            if (this.f1570e.f1577c == null) {
                this.f1570e.f1577c = "";
            }
            if (this.f1570e.f1578d == null) {
                this.f1570e.f1578d = "";
            }
        } else if (this.f1570e.f1577c.equals("")) {
            throw new IllegalArgumentException("servantName can not is null");
        } else if (this.f1570e.f1578d.equals("")) {
            throw new IllegalArgumentException("funcName can not is null");
        }
        C1993i iVar = new C1993i(0);
        iVar.mo19574a(this.f1448b);
        if (this.f1570e.f1575a == 2) {
            iVar.mo19583a((Map<K, V>) this.f1447a, 0);
        } else {
            iVar.mo19583a((Map<K, V>) this.f1565d, 0);
        }
        this.f1570e.f1579e = C1995k.m2012a(iVar.mo19575a());
        C1993i iVar2 = new C1993i(0);
        iVar2.mo19574a(this.f1448b);
        this.f1570e.mo19550a(iVar2);
        byte[] a = C1995k.m2012a(iVar2.mo19575a());
        int length = a.length;
        ByteBuffer allocate = ByteBuffer.allocate(length + 4);
        allocate.putInt(length + 4).put(a).flip();
        return allocate.array();
    }

    /* renamed from: a */
    public final void mo19542a(byte[] bArr) {
        if (bArr.length < 4) {
            throw new IllegalArgumentException("decode package must include size head");
        }
        try {
            C1991h hVar = new C1991h(bArr, 4);
            hVar.mo19564a(this.f1448b);
            this.f1570e.mo19549a(hVar);
            if (this.f1570e.f1575a == 3) {
                C1991h hVar2 = new C1991h(this.f1570e.f1579e);
                hVar2.mo19564a(this.f1448b);
                if (f1568f == null) {
                    HashMap<String, byte[]> hashMap = new HashMap<>();
                    f1568f = hashMap;
                    hashMap.put("", new byte[0]);
                }
                this.f1565d = hVar2.mo19568a((Map<K, V>) f1568f, 0, false);
                return;
            }
            C1991h hVar3 = new C1991h(this.f1570e.f1579e);
            hVar3.mo19564a(this.f1448b);
            if (f1569g == null) {
                f1569g = new HashMap<>();
                HashMap hashMap2 = new HashMap();
                hashMap2.put("", new byte[0]);
                f1569g.put("", hashMap2);
            }
            this.f1447a = hVar3.mo19568a((Map<K, V>) f1569g, 0, false);
            new HashMap();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: b */
    public final void mo19558b(String str) {
        this.f1570e.f1577c = str;
    }

    /* renamed from: c */
    public final void mo19559c(String str) {
        this.f1570e.f1578d = str;
    }

    /* renamed from: b */
    public final void mo19557b(int i) {
        this.f1570e.f1576b = 1;
    }
}
