package com.tencent.bugly.proguard;

import android.content.Context;
import com.tencent.bugly.BuglyStrategy.C1910a;
import com.tencent.bugly.crashreport.common.info.C1938a;
import com.tencent.bugly.crashreport.common.strategy.C1941a;
import java.util.Map;
import java.util.UUID;

/* renamed from: com.tencent.bugly.proguard.u */
/* compiled from: BUGLY */
public final class C2011u implements Runnable {

    /* renamed from: a */
    private int f1670a;

    /* renamed from: b */
    private int f1671b;

    /* renamed from: c */
    private final Context f1672c;

    /* renamed from: d */
    private final int f1673d;

    /* renamed from: e */
    private final byte[] f1674e;

    /* renamed from: f */
    private final C1938a f1675f;

    /* renamed from: g */
    private final C1941a f1676g;

    /* renamed from: h */
    private final C2005r f1677h;

    /* renamed from: i */
    private final C2007t f1678i;

    /* renamed from: j */
    private final int f1679j;

    /* renamed from: k */
    private final C2006s f1680k;

    /* renamed from: l */
    private final C2006s f1681l;

    /* renamed from: m */
    private String f1682m;

    /* renamed from: n */
    private final String f1683n;

    /* renamed from: o */
    private final Map<String, String> f1684o;

    /* renamed from: p */
    private int f1685p;

    /* renamed from: q */
    private long f1686q;

    /* renamed from: r */
    private long f1687r;

    /* renamed from: s */
    private boolean f1688s;

    /* renamed from: t */
    private boolean f1689t;

    public C2011u(Context context, int i, int i2, byte[] bArr, String str, String str2, C2006s sVar, boolean z, boolean z2) {
        this(context, i, i2, bArr, str, str2, sVar, z, 2, C1910a.MAX_USERDATA_VALUE_LENGTH, z2, null);
    }

    public C2011u(Context context, int i, int i2, byte[] bArr, String str, String str2, C2006s sVar, boolean z, int i3, int i4, boolean z2, Map<String, String> map) {
        this.f1670a = 2;
        this.f1671b = C1910a.MAX_USERDATA_VALUE_LENGTH;
        this.f1682m = null;
        this.f1685p = 0;
        this.f1686q = 0;
        this.f1687r = 0;
        this.f1688s = true;
        this.f1689t = false;
        this.f1672c = context;
        this.f1675f = C1938a.m1667a(context);
        this.f1674e = bArr;
        this.f1676g = C1941a.m1752a();
        this.f1677h = C2005r.m2062a(context);
        this.f1678i = C2007t.m2069a();
        this.f1679j = i;
        this.f1682m = str;
        this.f1683n = str2;
        this.f1680k = sVar;
        C2007t tVar = this.f1678i;
        this.f1681l = null;
        this.f1688s = z;
        this.f1673d = i2;
        if (i3 > 0) {
            this.f1670a = i3;
        }
        if (i4 > 0) {
            this.f1671b = i4;
        }
        this.f1689t = z2;
        this.f1684o = map;
    }

    /* renamed from: a */
    private void m2102a(C1980am amVar, boolean z, int i, String str, int i2) {
        String str2;
        switch (this.f1673d) {
            case 630:
            case 830:
                str2 = "crash";
                break;
            case 640:
            case 840:
                str2 = "userinfo";
                break;
            default:
                str2 = String.valueOf(this.f1673d);
                break;
        }
        if (z) {
            C2014w.m2113a("[Upload] Success: %s", str2);
        } else {
            C2014w.m2119e("[Upload] Failed to upload(%d) %s: %s", Integer.valueOf(i), str2, str);
            if (this.f1688s) {
                this.f1678i.mo19622a(i2, (C1980am) null);
            }
        }
        if (this.f1686q + this.f1687r > 0) {
            this.f1678i.mo19623a(this.f1678i.mo19617a(this.f1689t) + this.f1686q + this.f1687r, this.f1689t);
        }
        if (this.f1680k != null) {
            C2006s sVar = this.f1680k;
            int i3 = this.f1673d;
            long j = this.f1686q;
            long j2 = this.f1687r;
            sVar.mo19357a(z);
        }
        if (this.f1681l != null) {
            C2006s sVar2 = this.f1681l;
            int i4 = this.f1673d;
            long j3 = this.f1686q;
            long j4 = this.f1687r;
            sVar2.mo19357a(z);
        }
    }

    /* renamed from: a */
    private static boolean m2103a(C1980am amVar, C1938a aVar, C1941a aVar2) {
        if (amVar == null) {
            C2014w.m2118d("resp == null!", new Object[0]);
            return false;
        } else if (amVar.f1524a != 0) {
            C2014w.m2119e("resp result error %d", Byte.valueOf(amVar.f1524a));
            return false;
        } else {
            try {
                if (!C2018y.m2158a(amVar.f1527d) && !C1938a.m1668b().mo19409i().equals(amVar.f1527d)) {
                    C2001o.m2035a().mo19604a(C1941a.f1252a, "key_ip", amVar.f1527d.getBytes("UTF-8"), (C2000n) null, true);
                    aVar.mo19401d(amVar.f1527d);
                }
                if (!C2018y.m2158a(amVar.f1529f) && !C1938a.m1668b().mo19410j().equals(amVar.f1529f)) {
                    C2001o.m2035a().mo19604a(C1941a.f1252a, "key_imei", amVar.f1529f.getBytes("UTF-8"), (C2000n) null, true);
                    aVar.mo19403e(amVar.f1529f);
                }
            } catch (Throwable th) {
                C2014w.m2114a(th);
            }
            aVar.f1207i = amVar.f1528e;
            if (amVar.f1525b == 510) {
                if (amVar.f1526c == null) {
                    C2014w.m2119e("[Upload] Strategy data is null. Response cmd: %d", Integer.valueOf(amVar.f1525b));
                    return false;
                }
                C1982ao aoVar = (C1982ao) C1967a.m1892a(amVar.f1526c, C1982ao.class);
                if (aoVar == null) {
                    C2014w.m2119e("[Upload] Failed to decode strategy from server. Response cmd: %d", Integer.valueOf(amVar.f1525b));
                    return false;
                }
                aVar2.mo19433a(aoVar);
            }
            return true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:101:0x03c0, code lost:
        m2102a(null, false, 1, "status of server is " + r5, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0331, code lost:
        if (r5 == 0) goto L_0x03d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0334, code lost:
        if (r5 != 2) goto L_0x03c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x033f, code lost:
        if ((r11.f1686q + r11.f1687r) <= 0) goto L_0x0356;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0341, code lost:
        r11.f1678i.mo19623a((r11.f1678i.mo19617a(r11.f1689t) + r11.f1686q) + r11.f1687r, r11.f1689t);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0356, code lost:
        r11.f1678i.mo19622a(r5, (com.tencent.bugly.proguard.C1980am) null);
        com.tencent.bugly.proguard.C2014w.m2113a("[Upload] Session ID is invalid, will try again immediately (pid=%d | tid=%d).", java.lang.Integer.valueOf(android.os.Process.myPid()), java.lang.Integer.valueOf(android.os.Process.myTid()));
        r11.f1678i.mo19618a(r11.f1679j, r11.f1673d, r11.f1674e, r11.f1682m, r11.f1683n, r11.f1680k, r11.f1670a, r11.f1671b, true, r11.f1684o);
     */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r11 = this;
            r0 = 0
            r11.f1685p = r0     // Catch:{ Throwable -> 0x0030 }
            r0 = 0
            r11.f1686q = r0     // Catch:{ Throwable -> 0x0030 }
            r0 = 0
            r11.f1687r = r0     // Catch:{ Throwable -> 0x0030 }
            byte[] r0 = r11.f1674e     // Catch:{ Throwable -> 0x0030 }
            android.content.Context r1 = r11.f1672c     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r1 = com.tencent.bugly.crashreport.common.info.C1939b.m1732e(r1)     // Catch:{ Throwable -> 0x0030 }
            if (r1 != 0) goto L_0x0020
            r1 = 0
            r2 = 0
            r3 = 0
            java.lang.String r4 = "network is not available"
            r5 = 0
            r0 = r11
            r0.m2102a(r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x0030 }
        L_0x001f:
            return
        L_0x0020:
            if (r0 == 0) goto L_0x0025
            int r1 = r0.length     // Catch:{ Throwable -> 0x0030 }
            if (r1 != 0) goto L_0x003b
        L_0x0025:
            r1 = 0
            r2 = 0
            r3 = 0
            java.lang.String r4 = "request package is empty!"
            r5 = 0
            r0 = r11
            r0.m2102a(r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x0030 }
            goto L_0x001f
        L_0x0030:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C2014w.m2114a(r0)
            if (r1 != 0) goto L_0x001f
            r0.printStackTrace()
            goto L_0x001f
        L_0x003b:
            com.tencent.bugly.proguard.t r1 = r11.f1678i     // Catch:{ Throwable -> 0x0030 }
            boolean r2 = r11.f1689t     // Catch:{ Throwable -> 0x0030 }
            long r2 = r1.mo19617a(r2)     // Catch:{ Throwable -> 0x0030 }
            int r1 = r0.length     // Catch:{ Throwable -> 0x0030 }
            long r4 = (long) r1     // Catch:{ Throwable -> 0x0030 }
            long r4 = r4 + r2
            r6 = 2097152(0x200000, double:1.0361308E-317)
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 < 0) goto L_0x0086
            java.lang.String r0 = "[Upload] Upload too much data, try next time: %d/%d"
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x0030 }
            r4 = 0
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ Throwable -> 0x0030 }
            r1[r4] = r2     // Catch:{ Throwable -> 0x0030 }
            r2 = 1
            r4 = 2097152(0x200000, double:1.0361308E-317)
            java.lang.Long r3 = java.lang.Long.valueOf(r4)     // Catch:{ Throwable -> 0x0030 }
            r1[r2] = r3     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.proguard.C2014w.m2119e(r0, r1)     // Catch:{ Throwable -> 0x0030 }
            r1 = 0
            r2 = 0
            r3 = 0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r4 = "over net consume: "
            r0.<init>(r4)     // Catch:{ Throwable -> 0x0030 }
            r4 = 2048(0x800, double:1.0118E-320)
            java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r4 = "K"
            java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r4 = r0.toString()     // Catch:{ Throwable -> 0x0030 }
            r5 = 0
            r0 = r11
            r0.m2102a(r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x0030 }
            goto L_0x001f
        L_0x0086:
            java.lang.String r1 = "[Upload] Run upload task with cmd: %d"
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x0030 }
            r3 = 0
            int r4 = r11.f1673d     // Catch:{ Throwable -> 0x0030 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Throwable -> 0x0030 }
            r2[r3] = r4     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.proguard.C2014w.m2117c(r1, r2)     // Catch:{ Throwable -> 0x0030 }
            android.content.Context r1 = r11.f1672c     // Catch:{ Throwable -> 0x0030 }
            if (r1 == 0) goto L_0x00a7
            com.tencent.bugly.crashreport.common.info.a r1 = r11.f1675f     // Catch:{ Throwable -> 0x0030 }
            if (r1 == 0) goto L_0x00a7
            com.tencent.bugly.crashreport.common.strategy.a r1 = r11.f1676g     // Catch:{ Throwable -> 0x0030 }
            if (r1 == 0) goto L_0x00a7
            com.tencent.bugly.proguard.r r1 = r11.f1677h     // Catch:{ Throwable -> 0x0030 }
            if (r1 != 0) goto L_0x00b3
        L_0x00a7:
            r1 = 0
            r2 = 0
            r3 = 0
            java.lang.String r4 = "illegal access error"
            r5 = 0
            r0 = r11
            r0.m2102a(r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x0030 }
            goto L_0x001f
        L_0x00b3:
            com.tencent.bugly.crashreport.common.strategy.a r1 = r11.f1676g     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.crashreport.common.strategy.StrategyBean r1 = r1.mo19435c()     // Catch:{ Throwable -> 0x0030 }
            if (r1 != 0) goto L_0x00c7
            r1 = 0
            r2 = 0
            r3 = 0
            java.lang.String r4 = "illegal local strategy"
            r5 = 0
            r0 = r11
            r0.m2102a(r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x0030 }
            goto L_0x001f
        L_0x00c7:
            r3 = 0
            java.util.HashMap r7 = new java.util.HashMap     // Catch:{ Throwable -> 0x0030 }
            r7.<init>()     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r2 = "prodId"
            com.tencent.bugly.crashreport.common.info.a r4 = r11.f1675f     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r4 = r4.mo19404f()     // Catch:{ Throwable -> 0x0030 }
            r7.put(r2, r4)     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r2 = "bundleId"
            com.tencent.bugly.crashreport.common.info.a r4 = r11.f1675f     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r4 = r4.f1201c     // Catch:{ Throwable -> 0x0030 }
            r7.put(r2, r4)     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r2 = "appVer"
            com.tencent.bugly.crashreport.common.info.a r4 = r11.f1675f     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r4 = r4.f1208j     // Catch:{ Throwable -> 0x0030 }
            r7.put(r2, r4)     // Catch:{ Throwable -> 0x0030 }
            java.util.Map<java.lang.String, java.lang.String> r2 = r11.f1684o     // Catch:{ Throwable -> 0x0030 }
            if (r2 == 0) goto L_0x00f3
            java.util.Map<java.lang.String, java.lang.String> r2 = r11.f1684o     // Catch:{ Throwable -> 0x0030 }
            r7.putAll(r2)     // Catch:{ Throwable -> 0x0030 }
        L_0x00f3:
            boolean r2 = r11.f1688s     // Catch:{ Throwable -> 0x0030 }
            if (r2 == 0) goto L_0x015e
            java.lang.String r2 = "cmd"
            int r4 = r11.f1673d     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r4 = java.lang.Integer.toString(r4)     // Catch:{ Throwable -> 0x0030 }
            r7.put(r2, r4)     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r2 = "platformId"
            r4 = 1
            java.lang.String r4 = java.lang.Byte.toString(r4)     // Catch:{ Throwable -> 0x0030 }
            r7.put(r2, r4)     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r2 = "sdkVer"
            com.tencent.bugly.crashreport.common.info.a r4 = r11.f1675f     // Catch:{ Throwable -> 0x0030 }
            r4.getClass()     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r4 = "2.4.0"
            r7.put(r2, r4)     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r2 = "strategylastUpdateTime"
            long r4 = r1.f1242p     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r1 = java.lang.Long.toString(r4)     // Catch:{ Throwable -> 0x0030 }
            r7.put(r2, r1)     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.proguard.t r1 = r11.f1678i     // Catch:{ Throwable -> 0x0030 }
            boolean r1 = r1.mo19624a(r7)     // Catch:{ Throwable -> 0x0030 }
            if (r1 != 0) goto L_0x0137
            r1 = 0
            r2 = 0
            r3 = 0
            java.lang.String r4 = "failed to add security info to HTTP headers"
            r5 = 0
            r0 = r11
            r0.m2102a(r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x0030 }
            goto L_0x001f
        L_0x0137:
            r1 = 2
            byte[] r0 = com.tencent.bugly.proguard.C2018y.m2163a(r0, r1)     // Catch:{ Throwable -> 0x0030 }
            if (r0 != 0) goto L_0x014a
            r1 = 0
            r2 = 0
            r3 = 0
            java.lang.String r4 = "failed to zip request body"
            r5 = 0
            r0 = r11
            r0.m2102a(r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x0030 }
            goto L_0x001f
        L_0x014a:
            com.tencent.bugly.proguard.t r1 = r11.f1678i     // Catch:{ Throwable -> 0x0030 }
            byte[] r0 = r1.mo19625a(r0)     // Catch:{ Throwable -> 0x0030 }
            if (r0 != 0) goto L_0x015e
            r1 = 0
            r2 = 0
            r3 = 0
            java.lang.String r4 = "failed to encrypt request body"
            r5 = 0
            r0 = r11
            r0.m2102a(r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x0030 }
            goto L_0x001f
        L_0x015e:
            r6 = r0
            com.tencent.bugly.proguard.t r0 = r11.f1678i     // Catch:{ Throwable -> 0x0030 }
            int r1 = r11.f1679j     // Catch:{ Throwable -> 0x0030 }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0030 }
            r0.mo19619a(r1, r4)     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.proguard.s r0 = r11.f1680k     // Catch:{ Throwable -> 0x0030 }
            if (r0 == 0) goto L_0x0172
            com.tencent.bugly.proguard.s r0 = r11.f1680k     // Catch:{ Throwable -> 0x0030 }
            int r0 = r11.f1673d     // Catch:{ Throwable -> 0x0030 }
        L_0x0172:
            com.tencent.bugly.proguard.s r0 = r11.f1681l     // Catch:{ Throwable -> 0x0030 }
            if (r0 == 0) goto L_0x017a
            com.tencent.bugly.proguard.s r0 = r11.f1681l     // Catch:{ Throwable -> 0x0030 }
            int r0 = r11.f1673d     // Catch:{ Throwable -> 0x0030 }
        L_0x017a:
            java.lang.String r2 = r11.f1682m     // Catch:{ Throwable -> 0x0030 }
            r5 = -1
            r0 = 0
            r1 = r0
            r0 = r2
        L_0x0180:
            int r4 = r1 + 1
            int r2 = r11.f1670a     // Catch:{ Throwable -> 0x0030 }
            if (r1 >= r2) goto L_0x04ac
            r1 = 1
            if (r4 <= r1) goto L_0x01b1
            java.lang.String r1 = "[Upload] Failed to upload last time, wait and try(%d) again."
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x0030 }
            r3 = 0
            java.lang.Integer r8 = java.lang.Integer.valueOf(r4)     // Catch:{ Throwable -> 0x0030 }
            r2[r3] = r8     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.proguard.C2014w.m2118d(r1, r2)     // Catch:{ Throwable -> 0x0030 }
            int r1 = r11.f1671b     // Catch:{ Throwable -> 0x0030 }
            long r2 = (long) r1     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.proguard.C2018y.m2170b(r2)     // Catch:{ Throwable -> 0x0030 }
            int r1 = r11.f1670a     // Catch:{ Throwable -> 0x0030 }
            if (r4 != r1) goto L_0x01b1
            java.lang.String r0 = "[Upload] Use the back-up url at the last time: %s"
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x0030 }
            r2 = 0
            java.lang.String r3 = r11.f1683n     // Catch:{ Throwable -> 0x0030 }
            r1[r2] = r3     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.proguard.C2014w.m2118d(r0, r1)     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r0 = r11.f1683n     // Catch:{ Throwable -> 0x0030 }
        L_0x01b1:
            java.lang.String r1 = "[Upload] Send %d bytes"
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x0030 }
            r3 = 0
            int r8 = r6.length     // Catch:{ Throwable -> 0x0030 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ Throwable -> 0x0030 }
            r2[r3] = r8     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.proguard.C2014w.m2117c(r1, r2)     // Catch:{ Throwable -> 0x0030 }
            boolean r1 = r11.f1688s     // Catch:{ Throwable -> 0x0030 }
            if (r1 == 0) goto L_0x04b7
            java.lang.String r0 = m2101a(r0)     // Catch:{ Throwable -> 0x0030 }
            r2 = r0
        L_0x01ca:
            java.lang.String r0 = "[Upload] Upload to %s with cmd %d (pid=%d | tid=%d)."
            r1 = 4
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x0030 }
            r3 = 0
            r1[r3] = r2     // Catch:{ Throwable -> 0x0030 }
            r3 = 1
            int r8 = r11.f1673d     // Catch:{ Throwable -> 0x0030 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ Throwable -> 0x0030 }
            r1[r3] = r8     // Catch:{ Throwable -> 0x0030 }
            r3 = 2
            int r8 = android.os.Process.myPid()     // Catch:{ Throwable -> 0x0030 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ Throwable -> 0x0030 }
            r1[r3] = r8     // Catch:{ Throwable -> 0x0030 }
            r3 = 3
            int r8 = android.os.Process.myTid()     // Catch:{ Throwable -> 0x0030 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ Throwable -> 0x0030 }
            r1[r3] = r8     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.proguard.C2014w.m2117c(r0, r1)     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.proguard.r r0 = r11.f1677h     // Catch:{ Throwable -> 0x0030 }
            byte[] r1 = r0.mo19615a(r2, r6, r11, r7)     // Catch:{ Throwable -> 0x0030 }
            if (r1 != 0) goto L_0x0216
            java.lang.String r0 = "Failed to upload for no response!"
            java.lang.String r1 = "[Upload] Failed to upload(%d): %s"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0030 }
            r8 = 0
            r9 = 1
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Throwable -> 0x0030 }
            r3[r8] = r9     // Catch:{ Throwable -> 0x0030 }
            r8 = 1
            r3[r8] = r0     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.proguard.C2014w.m2119e(r1, r3)     // Catch:{ Throwable -> 0x0030 }
            r3 = 1
            r1 = r4
            r0 = r2
            goto L_0x0180
        L_0x0216:
            com.tencent.bugly.proguard.r r0 = r11.f1677h     // Catch:{ Throwable -> 0x0030 }
            java.util.Map<java.lang.String, java.lang.String> r3 = r0.f1641a     // Catch:{ Throwable -> 0x0030 }
            boolean r0 = r11.f1688s     // Catch:{ Throwable -> 0x0030 }
            if (r0 == 0) goto L_0x03d8
            if (r3 == 0) goto L_0x0226
            int r0 = r3.size()     // Catch:{ Throwable -> 0x0030 }
            if (r0 != 0) goto L_0x0298
        L_0x0226:
            java.lang.String r0 = "[Upload] Headers is empty."
            r8 = 0
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.proguard.C2014w.m2118d(r0, r8)     // Catch:{ Throwable -> 0x0030 }
            r0 = 0
        L_0x022f:
            if (r0 != 0) goto L_0x0300
            java.lang.String r0 = "[Upload] Headers from server is not valid, just try again (pid=%d | tid=%d)."
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x0030 }
            r8 = 0
            int r9 = android.os.Process.myPid()     // Catch:{ Throwable -> 0x0030 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Throwable -> 0x0030 }
            r1[r8] = r9     // Catch:{ Throwable -> 0x0030 }
            r8 = 1
            int r9 = android.os.Process.myTid()     // Catch:{ Throwable -> 0x0030 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Throwable -> 0x0030 }
            r1[r8] = r9     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.proguard.C2014w.m2117c(r0, r1)     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r0 = "[Upload] Failed to upload for no status header."
            java.lang.String r1 = "[Upload] Failed to upload(%d): %s"
            r8 = 2
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x0030 }
            r9 = 0
            r10 = 1
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ Throwable -> 0x0030 }
            r8[r9] = r10     // Catch:{ Throwable -> 0x0030 }
            r9 = 1
            r8[r9] = r0     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.proguard.C2014w.m2119e(r1, r8)     // Catch:{ Throwable -> 0x0030 }
            if (r3 == 0) goto L_0x02f3
            java.util.Set r0 = r3.entrySet()     // Catch:{ Throwable -> 0x0030 }
            java.util.Iterator r1 = r0.iterator()     // Catch:{ Throwable -> 0x0030 }
        L_0x026e:
            boolean r0 = r1.hasNext()     // Catch:{ Throwable -> 0x0030 }
            if (r0 == 0) goto L_0x02f3
            java.lang.Object r0 = r1.next()     // Catch:{ Throwable -> 0x0030 }
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r3 = "[key]: %s, [value]: %s"
            r8 = 2
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x0030 }
            r9 = 0
            java.lang.Object r10 = r0.getKey()     // Catch:{ Throwable -> 0x0030 }
            r8[r9] = r10     // Catch:{ Throwable -> 0x0030 }
            r9 = 1
            java.lang.Object r0 = r0.getValue()     // Catch:{ Throwable -> 0x0030 }
            r8[r9] = r0     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r0 = java.lang.String.format(r3, r8)     // Catch:{ Throwable -> 0x0030 }
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.proguard.C2014w.m2117c(r0, r3)     // Catch:{ Throwable -> 0x0030 }
            goto L_0x026e
        L_0x0298:
            java.lang.String r0 = "status"
            boolean r0 = r3.containsKey(r0)     // Catch:{ Throwable -> 0x0030 }
            if (r0 != 0) goto L_0x02af
            java.lang.String r0 = "[Upload] Headers does not contain %s"
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x0030 }
            r9 = 0
            java.lang.String r10 = "status"
            r8[r9] = r10     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.proguard.C2014w.m2118d(r0, r8)     // Catch:{ Throwable -> 0x0030 }
            r0 = 0
            goto L_0x022f
        L_0x02af:
            java.lang.String r0 = "Bugly-Version"
            boolean r0 = r3.containsKey(r0)     // Catch:{ Throwable -> 0x0030 }
            if (r0 != 0) goto L_0x02c7
            java.lang.String r0 = "[Upload] Headers does not contain %s"
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x0030 }
            r9 = 0
            java.lang.String r10 = "Bugly-Version"
            r8[r9] = r10     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.proguard.C2014w.m2118d(r0, r8)     // Catch:{ Throwable -> 0x0030 }
            r0 = 0
            goto L_0x022f
        L_0x02c7:
            java.lang.String r0 = "Bugly-Version"
            java.lang.Object r0 = r3.get(r0)     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r8 = "bugly"
            boolean r8 = r0.contains(r8)     // Catch:{ Throwable -> 0x0030 }
            if (r8 != 0) goto L_0x02e5
            java.lang.String r8 = "[Upload] Bugly version is not valid: %s"
            r9 = 1
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch:{ Throwable -> 0x0030 }
            r10 = 0
            r9[r10] = r0     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.proguard.C2014w.m2118d(r8, r9)     // Catch:{ Throwable -> 0x0030 }
            r0 = 0
            goto L_0x022f
        L_0x02e5:
            java.lang.String r8 = "[Upload] Bugly version from headers is: %s"
            r9 = 1
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch:{ Throwable -> 0x0030 }
            r10 = 0
            r9[r10] = r0     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.proguard.C2014w.m2117c(r8, r9)     // Catch:{ Throwable -> 0x0030 }
            r0 = 1
            goto L_0x022f
        L_0x02f3:
            java.lang.String r0 = "[Upload] Failed to upload for no status header."
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.proguard.C2014w.m2117c(r0, r1)     // Catch:{ Throwable -> 0x0030 }
            r3 = 1
            r1 = r4
            r0 = r2
            goto L_0x0180
        L_0x0300:
            java.lang.String r0 = "status"
            java.lang.Object r0 = r3.get(r0)     // Catch:{ Throwable -> 0x0394 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x0394 }
            int r5 = java.lang.Integer.parseInt(r0)     // Catch:{ Throwable -> 0x0394 }
            java.lang.String r0 = "[Upload] Status from server is %d (pid=%d | tid=%d)."
            r8 = 3
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ Throwable -> 0x0394 }
            r9 = 0
            java.lang.Integer r10 = java.lang.Integer.valueOf(r5)     // Catch:{ Throwable -> 0x0394 }
            r8[r9] = r10     // Catch:{ Throwable -> 0x0394 }
            r9 = 1
            int r10 = android.os.Process.myPid()     // Catch:{ Throwable -> 0x0394 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ Throwable -> 0x0394 }
            r8[r9] = r10     // Catch:{ Throwable -> 0x0394 }
            r9 = 2
            int r10 = android.os.Process.myTid()     // Catch:{ Throwable -> 0x0394 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ Throwable -> 0x0394 }
            r8[r9] = r10     // Catch:{ Throwable -> 0x0394 }
            com.tencent.bugly.proguard.C2014w.m2117c(r0, r8)     // Catch:{ Throwable -> 0x0394 }
            if (r5 == 0) goto L_0x03d8
            r0 = 2
            if (r5 != r0) goto L_0x03c0
            long r0 = r11.f1686q     // Catch:{ Throwable -> 0x0030 }
            long r2 = r11.f1687r     // Catch:{ Throwable -> 0x0030 }
            long r0 = r0 + r2
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 <= 0) goto L_0x0356
            com.tencent.bugly.proguard.t r0 = r11.f1678i     // Catch:{ Throwable -> 0x0030 }
            boolean r1 = r11.f1689t     // Catch:{ Throwable -> 0x0030 }
            long r0 = r0.mo19617a(r1)     // Catch:{ Throwable -> 0x0030 }
            long r2 = r11.f1686q     // Catch:{ Throwable -> 0x0030 }
            long r0 = r0 + r2
            long r2 = r11.f1687r     // Catch:{ Throwable -> 0x0030 }
            long r0 = r0 + r2
            com.tencent.bugly.proguard.t r2 = r11.f1678i     // Catch:{ Throwable -> 0x0030 }
            boolean r3 = r11.f1689t     // Catch:{ Throwable -> 0x0030 }
            r2.mo19623a(r0, r3)     // Catch:{ Throwable -> 0x0030 }
        L_0x0356:
            com.tencent.bugly.proguard.t r0 = r11.f1678i     // Catch:{ Throwable -> 0x0030 }
            r1 = 0
            r0.mo19622a(r5, r1)     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r0 = "[Upload] Session ID is invalid, will try again immediately (pid=%d | tid=%d)."
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x0030 }
            r2 = 0
            int r3 = android.os.Process.myPid()     // Catch:{ Throwable -> 0x0030 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ Throwable -> 0x0030 }
            r1[r2] = r3     // Catch:{ Throwable -> 0x0030 }
            r2 = 1
            int r3 = android.os.Process.myTid()     // Catch:{ Throwable -> 0x0030 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ Throwable -> 0x0030 }
            r1[r2] = r3     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.proguard.C2014w.m2113a(r0, r1)     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.proguard.t r0 = r11.f1678i     // Catch:{ Throwable -> 0x0030 }
            int r1 = r11.f1679j     // Catch:{ Throwable -> 0x0030 }
            int r2 = r11.f1673d     // Catch:{ Throwable -> 0x0030 }
            byte[] r3 = r11.f1674e     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r4 = r11.f1682m     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r5 = r11.f1683n     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.proguard.s r6 = r11.f1680k     // Catch:{ Throwable -> 0x0030 }
            int r7 = r11.f1670a     // Catch:{ Throwable -> 0x0030 }
            int r8 = r11.f1671b     // Catch:{ Throwable -> 0x0030 }
            r9 = 1
            java.util.Map<java.lang.String, java.lang.String> r10 = r11.f1684o     // Catch:{ Throwable -> 0x0030 }
            r0.mo19618a(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ Throwable -> 0x0030 }
            goto L_0x001f
        L_0x0394:
            r0 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r1 = "[Upload] Failed to upload for format of status header is invalid: "
            r0.<init>(r1)     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r1 = java.lang.Integer.toString(r5)     // Catch:{ Throwable -> 0x0030 }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r1 = "[Upload] Failed to upload(%d): %s"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0030 }
            r8 = 0
            r9 = 1
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Throwable -> 0x0030 }
            r3[r8] = r9     // Catch:{ Throwable -> 0x0030 }
            r8 = 1
            r3[r8] = r0     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.proguard.C2014w.m2119e(r1, r3)     // Catch:{ Throwable -> 0x0030 }
            r3 = 1
            r1 = r4
            r0 = r2
            goto L_0x0180
        L_0x03c0:
            r1 = 0
            r2 = 0
            r3 = 1
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r4 = "status of server is "
            r0.<init>(r4)     // Catch:{ Throwable -> 0x0030 }
            java.lang.StringBuilder r0 = r0.append(r5)     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r4 = r0.toString()     // Catch:{ Throwable -> 0x0030 }
            r0 = r11
            r0.m2102a(r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x0030 }
            goto L_0x001f
        L_0x03d8:
            java.lang.String r0 = "[Upload] Received %d bytes"
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x0030 }
            r4 = 0
            int r6 = r1.length     // Catch:{ Throwable -> 0x0030 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Throwable -> 0x0030 }
            r2[r4] = r6     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.proguard.C2014w.m2117c(r0, r2)     // Catch:{ Throwable -> 0x0030 }
            boolean r0 = r11.f1688s     // Catch:{ Throwable -> 0x0030 }
            if (r0 == 0) goto L_0x044d
            int r0 = r1.length     // Catch:{ Throwable -> 0x0030 }
            if (r0 != 0) goto L_0x0426
            java.util.Set r0 = r3.entrySet()     // Catch:{ Throwable -> 0x0030 }
            java.util.Iterator r1 = r0.iterator()     // Catch:{ Throwable -> 0x0030 }
        L_0x03f7:
            boolean r0 = r1.hasNext()     // Catch:{ Throwable -> 0x0030 }
            if (r0 == 0) goto L_0x041a
            java.lang.Object r0 = r1.next()     // Catch:{ Throwable -> 0x0030 }
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch:{ Throwable -> 0x0030 }
            java.lang.String r2 = "[Upload] HTTP headers from server: key = %s, value = %s"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0030 }
            r4 = 0
            java.lang.Object r5 = r0.getKey()     // Catch:{ Throwable -> 0x0030 }
            r3[r4] = r5     // Catch:{ Throwable -> 0x0030 }
            r4 = 1
            java.lang.Object r0 = r0.getValue()     // Catch:{ Throwable -> 0x0030 }
            r3[r4] = r0     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.proguard.C2014w.m2117c(r2, r3)     // Catch:{ Throwable -> 0x0030 }
            goto L_0x03f7
        L_0x041a:
            r1 = 0
            r2 = 0
            r3 = 1
            java.lang.String r4 = "response data from server is empty"
            r5 = 0
            r0 = r11
            r0.m2102a(r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x0030 }
            goto L_0x001f
        L_0x0426:
            com.tencent.bugly.proguard.t r0 = r11.f1678i     // Catch:{ Throwable -> 0x0030 }
            byte[] r0 = r0.mo19629b(r1)     // Catch:{ Throwable -> 0x0030 }
            if (r0 != 0) goto L_0x043a
            r1 = 0
            r2 = 0
            r3 = 1
            java.lang.String r4 = "failed to decrypt response from server"
            r5 = 0
            r0 = r11
            r0.m2102a(r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x0030 }
            goto L_0x001f
        L_0x043a:
            r1 = 2
            byte[] r0 = com.tencent.bugly.proguard.C2018y.m2175b(r0, r1)     // Catch:{ Throwable -> 0x0030 }
            if (r0 != 0) goto L_0x044e
            r1 = 0
            r2 = 0
            r3 = 1
            java.lang.String r4 = "failed unzip(Gzip) response from server"
            r5 = 0
            r0 = r11
            r0.m2102a(r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x0030 }
            goto L_0x001f
        L_0x044d:
            r0 = r1
        L_0x044e:
            boolean r1 = r11.f1688s     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.proguard.am r1 = com.tencent.bugly.proguard.C1967a.m1889a(r0, r1)     // Catch:{ Throwable -> 0x0030 }
            if (r1 != 0) goto L_0x0462
            r1 = 0
            r2 = 0
            r3 = 1
            java.lang.String r4 = "failed to decode response package"
            r5 = 0
            r0 = r11
            r0.m2102a(r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x0030 }
            goto L_0x001f
        L_0x0462:
            boolean r0 = r11.f1688s     // Catch:{ Throwable -> 0x0030 }
            if (r0 == 0) goto L_0x046b
            com.tencent.bugly.proguard.t r0 = r11.f1678i     // Catch:{ Throwable -> 0x0030 }
            r0.mo19622a(r5, r1)     // Catch:{ Throwable -> 0x0030 }
        L_0x046b:
            java.lang.String r2 = "[Upload] Response cmd is: %d, length of sBuffer is: %d"
            r0 = 2
            java.lang.Object[] r3 = new java.lang.Object[r0]     // Catch:{ Throwable -> 0x0030 }
            r0 = 0
            int r4 = r1.f1525b     // Catch:{ Throwable -> 0x0030 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Throwable -> 0x0030 }
            r3[r0] = r4     // Catch:{ Throwable -> 0x0030 }
            r4 = 1
            byte[] r0 = r1.f1526c     // Catch:{ Throwable -> 0x0030 }
            if (r0 != 0) goto L_0x049d
            r0 = 0
        L_0x047f:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x0030 }
            r3[r4] = r0     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.proguard.C2014w.m2117c(r2, r3)     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.crashreport.common.info.a r0 = r11.f1675f     // Catch:{ Throwable -> 0x0030 }
            com.tencent.bugly.crashreport.common.strategy.a r2 = r11.f1676g     // Catch:{ Throwable -> 0x0030 }
            boolean r0 = m2103a(r1, r0, r2)     // Catch:{ Throwable -> 0x0030 }
            if (r0 != 0) goto L_0x04a1
            r2 = 0
            r3 = 2
            java.lang.String r4 = "failed to process response package"
            r5 = 0
            r0 = r11
            r0.m2102a(r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x0030 }
            goto L_0x001f
        L_0x049d:
            byte[] r0 = r1.f1526c     // Catch:{ Throwable -> 0x0030 }
            int r0 = r0.length     // Catch:{ Throwable -> 0x0030 }
            goto L_0x047f
        L_0x04a1:
            r2 = 1
            r3 = 2
            java.lang.String r4 = "successfully uploaded"
            r5 = 0
            r0 = r11
            r0.m2102a(r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x0030 }
            goto L_0x001f
        L_0x04ac:
            r1 = 0
            r2 = 0
            java.lang.String r4 = "failed after many attempts"
            r5 = 0
            r0 = r11
            r0.m2102a(r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x0030 }
            goto L_0x001f
        L_0x04b7:
            r2 = r0
            goto L_0x01ca
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2011u.run():void");
    }

    /* renamed from: a */
    public final void mo19633a(long j) {
        this.f1685p++;
        this.f1686q += j;
    }

    /* renamed from: b */
    public final void mo19634b(long j) {
        this.f1687r += j;
    }

    /* renamed from: a */
    private static String m2101a(String str) {
        if (C2018y.m2158a(str)) {
            return str;
        }
        try {
            return String.format("%s?aid=%s", new Object[]{str, UUID.randomUUID().toString()});
        } catch (Throwable th) {
            C2014w.m2114a(th);
            return str;
        }
    }
}
