package com.tencent.bugly.proguard;

/* renamed from: com.tencent.bugly.proguard.ai */
/* compiled from: BUGLY */
public final class C1976ai extends C1994j implements Cloneable {

    /* renamed from: d */
    private static byte[] f1461d;

    /* renamed from: a */
    private byte f1462a = 0;

    /* renamed from: b */
    private String f1463b = "";

    /* renamed from: c */
    private byte[] f1464c = null;

    public C1976ai() {
    }

    public C1976ai(byte b, String str, byte[] bArr) {
        this.f1462a = b;
        this.f1463b = str;
        this.f1464c = bArr;
    }

    /* renamed from: a */
    public final void mo19550a(C1993i iVar) {
        iVar.mo19576a(this.f1462a, 0);
        iVar.mo19581a(this.f1463b, 1);
        if (this.f1464c != null) {
            iVar.mo19586a(this.f1464c, 2);
        }
    }

    /* renamed from: a */
    public final void mo19549a(C1991h hVar) {
        this.f1462a = hVar.mo19562a(this.f1462a, 0, true);
        this.f1463b = hVar.mo19572b(1, true);
        if (f1461d == null) {
            byte[] bArr = new byte[1];
            f1461d = bArr;
            bArr[0] = 0;
        }
        byte[] bArr2 = f1461d;
        this.f1464c = hVar.mo19573c(2, false);
    }

    /* renamed from: a */
    public final void mo19551a(StringBuilder sb, int i) {
    }
}
