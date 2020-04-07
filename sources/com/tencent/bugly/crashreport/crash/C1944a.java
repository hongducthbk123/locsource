package com.tencent.bugly.crashreport.crash;

/* renamed from: com.tencent.bugly.crashreport.crash.a */
/* compiled from: BUGLY */
public final class C1944a implements Comparable<C1944a> {

    /* renamed from: a */
    public long f1313a = -1;

    /* renamed from: b */
    public long f1314b = -1;

    /* renamed from: c */
    public String f1315c = null;

    /* renamed from: d */
    public boolean f1316d = false;

    /* renamed from: e */
    public boolean f1317e = false;

    /* renamed from: f */
    public int f1318f = 0;

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        C1944a aVar = (C1944a) obj;
        if (aVar != null) {
            long j = this.f1314b - aVar.f1314b;
            if (j <= 0) {
                return j < 0 ? -1 : 0;
            }
        }
        return 1;
    }
}
