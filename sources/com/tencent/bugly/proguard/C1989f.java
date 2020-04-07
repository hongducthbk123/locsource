package com.tencent.bugly.proguard;

import com.facebook.places.model.PlaceFields;
import com.tencent.bugly.crashreport.crash.jni.C1966b;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.tencent.bugly.proguard.f */
/* compiled from: BUGLY */
public final class C1989f extends C1994j {

    /* renamed from: k */
    private static byte[] f1572k = null;

    /* renamed from: l */
    private static Map<String, String> f1573l = null;

    /* renamed from: m */
    private static /* synthetic */ boolean f1574m;

    /* renamed from: a */
    public short f1575a = 0;

    /* renamed from: b */
    public int f1576b = 0;

    /* renamed from: c */
    public String f1577c = null;

    /* renamed from: d */
    public String f1578d = null;

    /* renamed from: e */
    public byte[] f1579e;

    /* renamed from: f */
    private byte f1580f = 0;

    /* renamed from: g */
    private int f1581g = 0;

    /* renamed from: h */
    private int f1582h = 0;

    /* renamed from: i */
    private Map<String, String> f1583i;

    /* renamed from: j */
    private Map<String, String> f1584j;

    static {
        boolean z;
        if (!C1989f.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        f1574m = z;
    }

    public final boolean equals(Object o) {
        C1989f fVar = (C1989f) o;
        if (!C1995k.m2008a(1, (int) fVar.f1575a) || !C1995k.m2008a(1, (int) fVar.f1580f) || !C1995k.m2008a(1, fVar.f1581g) || !C1995k.m2008a(1, fVar.f1576b) || !C1995k.m2010a((Object) Integer.valueOf(1), (Object) fVar.f1577c) || !C1995k.m2010a((Object) Integer.valueOf(1), (Object) fVar.f1578d) || !C1995k.m2010a((Object) Integer.valueOf(1), (Object) fVar.f1579e) || !C1995k.m2008a(1, fVar.f1582h) || !C1995k.m2010a((Object) Integer.valueOf(1), (Object) fVar.f1583i) || !C1995k.m2010a((Object) Integer.valueOf(1), (Object) fVar.f1584j)) {
            return false;
        }
        return true;
    }

    public final Object clone() {
        boolean z = false;
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            if (f1574m) {
                return z;
            }
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    public final void mo19550a(C1993i iVar) {
        iVar.mo19584a(this.f1575a, 1);
        iVar.mo19576a(this.f1580f, 2);
        iVar.mo19577a(this.f1581g, 3);
        iVar.mo19577a(this.f1576b, 4);
        iVar.mo19581a(this.f1577c, 5);
        iVar.mo19581a(this.f1578d, 6);
        iVar.mo19586a(this.f1579e, 7);
        iVar.mo19577a(this.f1582h, 8);
        iVar.mo19583a(this.f1583i, 9);
        iVar.mo19583a(this.f1584j, 10);
    }

    /* renamed from: a */
    public final void mo19549a(C1991h hVar) {
        try {
            this.f1575a = hVar.mo19569a(this.f1575a, 1, true);
            this.f1580f = hVar.mo19562a(this.f1580f, 2, true);
            this.f1581g = hVar.mo19563a(this.f1581g, 3, true);
            this.f1576b = hVar.mo19563a(this.f1576b, 4, true);
            this.f1577c = hVar.mo19572b(5, true);
            this.f1578d = hVar.mo19572b(6, true);
            if (f1572k == null) {
                f1572k = new byte[]{0};
            }
            byte[] bArr = f1572k;
            this.f1579e = hVar.mo19573c(7, true);
            this.f1582h = hVar.mo19563a(this.f1582h, 8, true);
            if (f1573l == null) {
                HashMap hashMap = new HashMap();
                f1573l = hashMap;
                hashMap.put("", "");
            }
            this.f1583i = (Map) hVar.mo19567a(f1573l, 9, true);
            if (f1573l == null) {
                HashMap hashMap2 = new HashMap();
                f1573l = hashMap2;
                hashMap2.put("", "");
            }
            this.f1584j = (Map) hVar.mo19567a(f1573l, 10, true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("RequestPacket decode error " + C1988e.m1958a(this.f1579e));
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public final void mo19551a(StringBuilder sb, int i) {
        C1966b bVar = new C1966b(sb, i);
        bVar.mo19530a(this.f1575a, "iVersion");
        bVar.mo19520a(this.f1580f, "cPacketType");
        bVar.mo19524a(this.f1581g, "iMessageType");
        bVar.mo19524a(this.f1576b, "iRequestId");
        bVar.mo19539b(this.f1577c, "sServantName");
        bVar.mo19539b(this.f1578d, "sFuncName");
        bVar.mo19532a(this.f1579e, "sBuffer");
        bVar.mo19524a(this.f1582h, "iTimeout");
        bVar.mo19529a(this.f1583i, PlaceFields.CONTEXT);
        bVar.mo19529a(this.f1584j, "status");
    }
}
