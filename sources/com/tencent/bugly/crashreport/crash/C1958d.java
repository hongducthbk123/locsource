package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import com.tencent.bugly.crashreport.common.info.C1938a;
import com.tencent.bugly.crashreport.common.strategy.C1941a;
import com.tencent.bugly.proguard.C2012v;
import com.tencent.bugly.proguard.C2014w;
import com.tencent.bugly.proguard.C2018y;
import java.util.Map;

/* renamed from: com.tencent.bugly.crashreport.crash.d */
/* compiled from: BUGLY */
public final class C1958d {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static C1958d f1385a = null;

    /* renamed from: b */
    private C1941a f1386b;

    /* renamed from: c */
    private C1938a f1387c;

    /* renamed from: d */
    private C1953b f1388d;

    /* renamed from: e */
    private Context f1389e;

    /* renamed from: a */
    static /* synthetic */ void m1829a(C1958d dVar) {
        C2014w.m2117c("[ExtraCrashManager] Trying to notify Bugly agents.", new Object[0]);
        try {
            Class cls = Class.forName("com.tencent.bugly.agent.GameAgent");
            String str = "com.tencent.bugly";
            dVar.f1387c.getClass();
            String str2 = "";
            if (!"".equals(str2)) {
                str = str + "." + str2;
            }
            C2018y.m2155a(cls, "sdkPackageName", (Object) str, (Object) null);
            C2014w.m2117c("[ExtraCrashManager] Bugly game agent has been notified.", new Object[0]);
        } catch (Throwable th) {
            C2014w.m2113a("[ExtraCrashManager] no game agent", new Object[0]);
        }
    }

    /* JADX INFO: used method not loaded: com.tencent.bugly.crashreport.crash.b.b(com.tencent.bugly.crashreport.crash.CrashDetailBean):null, types can be incorrect */
    /* JADX INFO: used method not loaded: com.tencent.bugly.proguard.x.a(boolean):null, types can be incorrect */
    /* JADX INFO: used method not loaded: com.tencent.bugly.crashreport.crash.b.a(com.tencent.bugly.crashreport.crash.CrashDetailBean):null, types can be incorrect */
    /* JADX INFO: used method not loaded: com.tencent.bugly.crashreport.crash.b.a(com.tencent.bugly.crashreport.crash.CrashDetailBean, long, boolean):null, types can be incorrect */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00f9, code lost:
        if (r10 != 8) goto L_0x00fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00fb, code lost:
        r10 = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        r5 = new com.tencent.bugly.crashreport.crash.CrashDetailBean();
        r5.f1266B = com.tencent.bugly.crashreport.common.info.C1939b.m1735g();
        r5.f1267C = com.tencent.bugly.crashreport.common.info.C1939b.m1731e();
        r5.f1268D = com.tencent.bugly.crashreport.common.info.C1939b.m1739i();
        r5.f1269E = r8.f1387c.mo19416p();
        r5.f1270F = r8.f1387c.mo19415o();
        r5.f1271G = r8.f1387c.mo19417q();
        r5.f1309w = com.tencent.bugly.proguard.C2018y.m2145a(r8.f1389e, com.tencent.bugly.crashreport.crash.C1955c.f1358d, (java.lang.String) null);
        r5.f1288b = r10;
        r5.f1291e = r8.f1387c.mo19408h();
        r5.f1292f = r8.f1387c.f1208j;
        r5.f1293g = r8.f1387c.mo19423w();
        r5.f1299m = r8.f1387c.mo19406g();
        r5.f1300n = r11;
        r5.f1301o = r12;
        r1 = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0176, code lost:
        if (r13 == null) goto L_0x0241;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0178, code lost:
        r2 = r13.split(org.apache.commons.p052io.IOUtils.LINE_SEPARATOR_UNIX);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x017f, code lost:
        if (r2.length <= 0) goto L_0x0184;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0181, code lost:
        r1 = r2[0];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0184, code lost:
        r2 = r1;
        r1 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0186, code lost:
        r5.f1302p = r2;
        r5.f1303q = r1;
        r5.f1304r = java.lang.System.currentTimeMillis();
        r5.f1307u = com.tencent.bugly.proguard.C2018y.m2168b(r5.f1303q.getBytes());
        r5.f1311y = com.tencent.bugly.proguard.C2018y.m2152a(com.tencent.bugly.crashreport.crash.C1955c.f1359e, false);
        r5.f1312z = r8.f1387c.f1202d;
        r5.f1265A = r9.getName() + "(" + r9.getId() + ")";
        r5.f1272H = r8.f1387c.mo19425y();
        r5.f1294h = r8.f1387c.mo19422v();
        r5.f1276L = r8.f1387c.f1181a;
        r5.f1277M = r8.f1387c.mo19394a();
        r5.f1279O = r8.f1387c.mo19380F();
        r5.f1280P = r8.f1387c.mo19381G();
        r5.f1281Q = r8.f1387c.mo19426z();
        r5.f1282R = r8.f1387c.mo19379E();
        r8.f1388d.mo19463b(r5);
        r5.f1310x = com.tencent.bugly.proguard.C2015x.m2126a(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x021e, code lost:
        if (r5.f1278N != null) goto L_0x0227;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0220, code lost:
        r5.f1278N = new java.util.LinkedHashMap();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0227, code lost:
        if (r14 == null) goto L_0x022e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0229, code lost:
        r5.f1278N.putAll(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x022e, code lost:
        if (r5 != null) goto L_0x0248;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0230, code lost:
        com.tencent.bugly.proguard.C2014w.m2119e("[ExtraCrashManager] Failed to package crash data.", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0238, code lost:
        com.tencent.bugly.proguard.C2014w.m2119e("[ExtraCrashManager] Successfully handled.", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0241, code lost:
        r2 = r1;
        r1 = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        com.tencent.bugly.crashreport.crash.C1953b.m1792a(r0, com.tencent.bugly.proguard.C2018y.m2143a(), r8.f1387c.f1202d, r9, r11 + org.apache.commons.p052io.IOUtils.LINE_SEPARATOR_UNIX + r12 + org.apache.commons.p052io.IOUtils.LINE_SEPARATOR_UNIX + r13, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x027b, code lost:
        if (r8.f1388d.mo19461a(r5) != false) goto L_0x0285;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x027d, code lost:
        r8.f1388d.mo19459a(r5, 3000, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0285, code lost:
        com.tencent.bugly.proguard.C2014w.m2119e("[ExtraCrashManager] Successfully handled.", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:?, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0295 A[Catch:{ Throwable -> 0x028e, all -> 0x02a1 }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void m1830a(com.tencent.bugly.crashreport.crash.C1958d r8, java.lang.Thread r9, int r10, java.lang.String r11, java.lang.String r12, java.lang.String r13, java.util.Map r14) {
        /*
            r2 = 1
            r6 = 0
            switch(r10) {
                case 4: goto L_0x00a4;
                case 5: goto L_0x0013;
                case 6: goto L_0x0013;
                case 7: goto L_0x0005;
                case 8: goto L_0x00a8;
                default: goto L_0x0005;
            }
        L_0x0005:
            java.lang.String r0 = "[ExtraCrashManager] Unknown extra crash type: %d"
            java.lang.Object[] r1 = new java.lang.Object[r2]
            java.lang.Integer r2 = java.lang.Integer.valueOf(r10)
            r1[r6] = r2
            com.tencent.bugly.proguard.C2014w.m2118d(r0, r1)
        L_0x0012:
            return
        L_0x0013:
            java.lang.String r0 = "Cocos"
        L_0x0015:
            java.lang.String r1 = "[ExtraCrashManager] %s Crash Happen"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r6] = r0
            com.tencent.bugly.proguard.C2014w.m2119e(r1, r2)
            com.tencent.bugly.crashreport.common.strategy.a r1 = r8.f1386b     // Catch:{ Throwable -> 0x028e }
            boolean r1 = r1.mo19434b()     // Catch:{ Throwable -> 0x028e }
            if (r1 != 0) goto L_0x0043
            java.lang.String r1 = "waiting for remote sync"
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x028e }
            com.tencent.bugly.proguard.C2014w.m2119e(r1, r2)     // Catch:{ Throwable -> 0x028e }
            r1 = r6
        L_0x0030:
            com.tencent.bugly.crashreport.common.strategy.a r2 = r8.f1386b     // Catch:{ Throwable -> 0x028e }
            boolean r2 = r2.mo19434b()     // Catch:{ Throwable -> 0x028e }
            if (r2 != 0) goto L_0x0043
            r2 = 500(0x1f4, double:2.47E-321)
            com.tencent.bugly.proguard.C2018y.m2170b(r2)     // Catch:{ Throwable -> 0x028e }
            int r1 = r1 + 500
            r2 = 3000(0xbb8, float:4.204E-42)
            if (r1 < r2) goto L_0x0030
        L_0x0043:
            com.tencent.bugly.crashreport.common.strategy.a r1 = r8.f1386b     // Catch:{ Throwable -> 0x028e }
            boolean r1 = r1.mo19434b()     // Catch:{ Throwable -> 0x028e }
            if (r1 != 0) goto L_0x0053
            java.lang.String r1 = "[ExtraCrashManager] There is no remote strategy, but still store it."
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x028e }
            com.tencent.bugly.proguard.C2014w.m2118d(r1, r2)     // Catch:{ Throwable -> 0x028e }
        L_0x0053:
            com.tencent.bugly.crashreport.common.strategy.a r1 = r8.f1386b     // Catch:{ Throwable -> 0x028e }
            com.tencent.bugly.crashreport.common.strategy.StrategyBean r1 = r1.mo19435c()     // Catch:{ Throwable -> 0x028e }
            boolean r2 = r1.f1233g     // Catch:{ Throwable -> 0x028e }
            if (r2 != 0) goto L_0x00ac
            com.tencent.bugly.crashreport.common.strategy.a r2 = r8.f1386b     // Catch:{ Throwable -> 0x028e }
            boolean r2 = r2.mo19434b()     // Catch:{ Throwable -> 0x028e }
            if (r2 == 0) goto L_0x00ac
            java.lang.String r1 = "[ExtraCrashManager] Crash report was closed by remote , will not upload to Bugly , print local for helpful!"
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x028e }
            com.tencent.bugly.proguard.C2014w.m2119e(r1, r2)     // Catch:{ Throwable -> 0x028e }
            java.lang.String r1 = com.tencent.bugly.proguard.C2018y.m2143a()     // Catch:{ Throwable -> 0x028e }
            com.tencent.bugly.crashreport.common.info.a r2 = r8.f1387c     // Catch:{ Throwable -> 0x028e }
            java.lang.String r2 = r2.f1202d     // Catch:{ Throwable -> 0x028e }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x028e }
            r3.<init>()     // Catch:{ Throwable -> 0x028e }
            java.lang.StringBuilder r3 = r3.append(r11)     // Catch:{ Throwable -> 0x028e }
            java.lang.String r4 = "\n"
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Throwable -> 0x028e }
            java.lang.StringBuilder r3 = r3.append(r12)     // Catch:{ Throwable -> 0x028e }
            java.lang.String r4 = "\n"
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Throwable -> 0x028e }
            java.lang.StringBuilder r3 = r3.append(r13)     // Catch:{ Throwable -> 0x028e }
            java.lang.String r4 = r3.toString()     // Catch:{ Throwable -> 0x028e }
            r5 = 0
            r3 = r9
            com.tencent.bugly.crashreport.crash.C1953b.m1792a(r0, r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x028e }
            java.lang.String r0 = "[ExtraCrashManager] Successfully handled."
            java.lang.Object[] r1 = new java.lang.Object[r6]
            com.tencent.bugly.proguard.C2014w.m2119e(r0, r1)
            goto L_0x0012
        L_0x00a4:
            java.lang.String r0 = "Unity"
            goto L_0x0015
        L_0x00a8:
            java.lang.String r0 = "H5"
            goto L_0x0015
        L_0x00ac:
            switch(r10) {
                case 5: goto L_0x00c7;
                case 6: goto L_0x00c7;
                case 7: goto L_0x00af;
                case 8: goto L_0x00df;
                default: goto L_0x00af;
            }
        L_0x00af:
            java.lang.String r0 = "[ExtraCrashManager] Unknown extra crash type: %d"
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x028e }
            r2 = 0
            java.lang.Integer r3 = java.lang.Integer.valueOf(r10)     // Catch:{ Throwable -> 0x028e }
            r1[r2] = r3     // Catch:{ Throwable -> 0x028e }
            com.tencent.bugly.proguard.C2014w.m2118d(r0, r1)     // Catch:{ Throwable -> 0x028e }
            java.lang.String r0 = "[ExtraCrashManager] Successfully handled."
            java.lang.Object[] r1 = new java.lang.Object[r6]
            com.tencent.bugly.proguard.C2014w.m2119e(r0, r1)
            goto L_0x0012
        L_0x00c7:
            boolean r1 = r1.f1238l     // Catch:{ Throwable -> 0x028e }
            if (r1 != 0) goto L_0x00f7
            java.lang.String r1 = "[ExtraCrashManager] %s report is disabled."
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x028e }
            r3 = 0
            r2[r3] = r0     // Catch:{ Throwable -> 0x028e }
            com.tencent.bugly.proguard.C2014w.m2119e(r1, r2)     // Catch:{ Throwable -> 0x028e }
            java.lang.String r0 = "[ExtraCrashManager] Successfully handled."
            java.lang.Object[] r1 = new java.lang.Object[r6]
            com.tencent.bugly.proguard.C2014w.m2119e(r0, r1)
            goto L_0x0012
        L_0x00df:
            boolean r1 = r1.f1239m     // Catch:{ Throwable -> 0x028e }
            if (r1 != 0) goto L_0x00f7
            java.lang.String r1 = "[ExtraCrashManager] %s report is disabled."
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x028e }
            r3 = 0
            r2[r3] = r0     // Catch:{ Throwable -> 0x028e }
            com.tencent.bugly.proguard.C2014w.m2119e(r1, r2)     // Catch:{ Throwable -> 0x028e }
            java.lang.String r0 = "[ExtraCrashManager] Successfully handled."
            java.lang.Object[] r1 = new java.lang.Object[r6]
            com.tencent.bugly.proguard.C2014w.m2119e(r0, r1)
            goto L_0x0012
        L_0x00f7:
            r1 = 8
            if (r10 != r1) goto L_0x00fc
            r10 = 5
        L_0x00fc:
            com.tencent.bugly.crashreport.crash.CrashDetailBean r5 = new com.tencent.bugly.crashreport.crash.CrashDetailBean     // Catch:{ Throwable -> 0x028e }
            r5.<init>()     // Catch:{ Throwable -> 0x028e }
            long r2 = com.tencent.bugly.crashreport.common.info.C1939b.m1735g()     // Catch:{ Throwable -> 0x028e }
            r5.f1266B = r2     // Catch:{ Throwable -> 0x028e }
            long r2 = com.tencent.bugly.crashreport.common.info.C1939b.m1731e()     // Catch:{ Throwable -> 0x028e }
            r5.f1267C = r2     // Catch:{ Throwable -> 0x028e }
            long r2 = com.tencent.bugly.crashreport.common.info.C1939b.m1739i()     // Catch:{ Throwable -> 0x028e }
            r5.f1268D = r2     // Catch:{ Throwable -> 0x028e }
            com.tencent.bugly.crashreport.common.info.a r1 = r8.f1387c     // Catch:{ Throwable -> 0x028e }
            long r2 = r1.mo19416p()     // Catch:{ Throwable -> 0x028e }
            r5.f1269E = r2     // Catch:{ Throwable -> 0x028e }
            com.tencent.bugly.crashreport.common.info.a r1 = r8.f1387c     // Catch:{ Throwable -> 0x028e }
            long r2 = r1.mo19415o()     // Catch:{ Throwable -> 0x028e }
            r5.f1270F = r2     // Catch:{ Throwable -> 0x028e }
            com.tencent.bugly.crashreport.common.info.a r1 = r8.f1387c     // Catch:{ Throwable -> 0x028e }
            long r2 = r1.mo19417q()     // Catch:{ Throwable -> 0x028e }
            r5.f1271G = r2     // Catch:{ Throwable -> 0x028e }
            android.content.Context r1 = r8.f1389e     // Catch:{ Throwable -> 0x028e }
            int r2 = com.tencent.bugly.crashreport.crash.C1955c.f1358d     // Catch:{ Throwable -> 0x028e }
            r3 = 0
            java.lang.String r1 = com.tencent.bugly.proguard.C2018y.m2145a(r1, r2, r3)     // Catch:{ Throwable -> 0x028e }
            r5.f1309w = r1     // Catch:{ Throwable -> 0x028e }
            r5.f1288b = r10     // Catch:{ Throwable -> 0x028e }
            com.tencent.bugly.crashreport.common.info.a r1 = r8.f1387c     // Catch:{ Throwable -> 0x028e }
            java.lang.String r1 = r1.mo19408h()     // Catch:{ Throwable -> 0x028e }
            r5.f1291e = r1     // Catch:{ Throwable -> 0x028e }
            com.tencent.bugly.crashreport.common.info.a r1 = r8.f1387c     // Catch:{ Throwable -> 0x028e }
            java.lang.String r1 = r1.f1208j     // Catch:{ Throwable -> 0x028e }
            r5.f1292f = r1     // Catch:{ Throwable -> 0x028e }
            com.tencent.bugly.crashreport.common.info.a r1 = r8.f1387c     // Catch:{ Throwable -> 0x028e }
            java.lang.String r1 = r1.mo19423w()     // Catch:{ Throwable -> 0x028e }
            r5.f1293g = r1     // Catch:{ Throwable -> 0x028e }
            com.tencent.bugly.crashreport.common.info.a r1 = r8.f1387c     // Catch:{ Throwable -> 0x028e }
            java.lang.String r1 = r1.mo19406g()     // Catch:{ Throwable -> 0x028e }
            r5.f1299m = r1     // Catch:{ Throwable -> 0x028e }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x028e }
            r1.<init>()     // Catch:{ Throwable -> 0x028e }
            java.lang.StringBuilder r1 = r1.append(r11)     // Catch:{ Throwable -> 0x028e }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x028e }
            r5.f1300n = r1     // Catch:{ Throwable -> 0x028e }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x028e }
            r1.<init>()     // Catch:{ Throwable -> 0x028e }
            java.lang.StringBuilder r1 = r1.append(r12)     // Catch:{ Throwable -> 0x028e }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x028e }
            r5.f1301o = r1     // Catch:{ Throwable -> 0x028e }
            java.lang.String r1 = ""
            if (r13 == 0) goto L_0x0241
            java.lang.String r2 = "\n"
            java.lang.String[] r2 = r13.split(r2)     // Catch:{ Throwable -> 0x028e }
            int r3 = r2.length     // Catch:{ Throwable -> 0x028e }
            if (r3 <= 0) goto L_0x0184
            r1 = 0
            r1 = r2[r1]     // Catch:{ Throwable -> 0x028e }
        L_0x0184:
            r2 = r1
            r1 = r13
        L_0x0186:
            r5.f1302p = r2     // Catch:{ Throwable -> 0x028e }
            r5.f1303q = r1     // Catch:{ Throwable -> 0x028e }
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x028e }
            r5.f1304r = r2     // Catch:{ Throwable -> 0x028e }
            java.lang.String r1 = r5.f1303q     // Catch:{ Throwable -> 0x028e }
            byte[] r1 = r1.getBytes()     // Catch:{ Throwable -> 0x028e }
            java.lang.String r1 = com.tencent.bugly.proguard.C2018y.m2168b(r1)     // Catch:{ Throwable -> 0x028e }
            r5.f1307u = r1     // Catch:{ Throwable -> 0x028e }
            int r1 = com.tencent.bugly.crashreport.crash.C1955c.f1359e     // Catch:{ Throwable -> 0x028e }
            r2 = 0
            java.util.Map r1 = com.tencent.bugly.proguard.C2018y.m2152a(r1, r2)     // Catch:{ Throwable -> 0x028e }
            r5.f1311y = r1     // Catch:{ Throwable -> 0x028e }
            com.tencent.bugly.crashreport.common.info.a r1 = r8.f1387c     // Catch:{ Throwable -> 0x028e }
            java.lang.String r1 = r1.f1202d     // Catch:{ Throwable -> 0x028e }
            r5.f1312z = r1     // Catch:{ Throwable -> 0x028e }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x028e }
            r1.<init>()     // Catch:{ Throwable -> 0x028e }
            java.lang.String r2 = r9.getName()     // Catch:{ Throwable -> 0x028e }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Throwable -> 0x028e }
            java.lang.String r2 = "("
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Throwable -> 0x028e }
            long r2 = r9.getId()     // Catch:{ Throwable -> 0x028e }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Throwable -> 0x028e }
            java.lang.String r2 = ")"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Throwable -> 0x028e }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x028e }
            r5.f1265A = r1     // Catch:{ Throwable -> 0x028e }
            com.tencent.bugly.crashreport.common.info.a r1 = r8.f1387c     // Catch:{ Throwable -> 0x028e }
            java.lang.String r1 = r1.mo19425y()     // Catch:{ Throwable -> 0x028e }
            r5.f1272H = r1     // Catch:{ Throwable -> 0x028e }
            com.tencent.bugly.crashreport.common.info.a r1 = r8.f1387c     // Catch:{ Throwable -> 0x028e }
            java.util.Map r1 = r1.mo19422v()     // Catch:{ Throwable -> 0x028e }
            r5.f1294h = r1     // Catch:{ Throwable -> 0x028e }
            com.tencent.bugly.crashreport.common.info.a r1 = r8.f1387c     // Catch:{ Throwable -> 0x028e }
            long r2 = r1.f1181a     // Catch:{ Throwable -> 0x028e }
            r5.f1276L = r2     // Catch:{ Throwable -> 0x028e }
            com.tencent.bugly.crashreport.common.info.a r1 = r8.f1387c     // Catch:{ Throwable -> 0x028e }
            boolean r1 = r1.mo19394a()     // Catch:{ Throwable -> 0x028e }
            r5.f1277M = r1     // Catch:{ Throwable -> 0x028e }
            com.tencent.bugly.crashreport.common.info.a r1 = r8.f1387c     // Catch:{ Throwable -> 0x028e }
            int r1 = r1.mo19380F()     // Catch:{ Throwable -> 0x028e }
            r5.f1279O = r1     // Catch:{ Throwable -> 0x028e }
            com.tencent.bugly.crashreport.common.info.a r1 = r8.f1387c     // Catch:{ Throwable -> 0x028e }
            int r1 = r1.mo19381G()     // Catch:{ Throwable -> 0x028e }
            r5.f1280P = r1     // Catch:{ Throwable -> 0x028e }
            com.tencent.bugly.crashreport.common.info.a r1 = r8.f1387c     // Catch:{ Throwable -> 0x028e }
            java.util.Map r1 = r1.mo19426z()     // Catch:{ Throwable -> 0x028e }
            r5.f1281Q = r1     // Catch:{ Throwable -> 0x028e }
            com.tencent.bugly.crashreport.common.info.a r1 = r8.f1387c     // Catch:{ Throwable -> 0x028e }
            java.util.Map r1 = r1.mo19379E()     // Catch:{ Throwable -> 0x028e }
            r5.f1282R = r1     // Catch:{ Throwable -> 0x028e }
            com.tencent.bugly.crashreport.crash.b r1 = r8.f1388d     // Catch:{ Throwable -> 0x028e }
            r1.mo19463b(r5)     // Catch:{ Throwable -> 0x028e }
            r1 = 0
            byte[] r1 = com.tencent.bugly.proguard.C2015x.m2126a(r1)     // Catch:{ Throwable -> 0x028e }
            r5.f1310x = r1     // Catch:{ Throwable -> 0x028e }
            java.util.Map<java.lang.String, java.lang.String> r1 = r5.f1278N     // Catch:{ Throwable -> 0x028e }
            if (r1 != 0) goto L_0x0227
            java.util.LinkedHashMap r1 = new java.util.LinkedHashMap     // Catch:{ Throwable -> 0x028e }
            r1.<init>()     // Catch:{ Throwable -> 0x028e }
            r5.f1278N = r1     // Catch:{ Throwable -> 0x028e }
        L_0x0227:
            if (r14 == 0) goto L_0x022e
            java.util.Map<java.lang.String, java.lang.String> r1 = r5.f1278N     // Catch:{ Throwable -> 0x028e }
            r1.putAll(r14)     // Catch:{ Throwable -> 0x028e }
        L_0x022e:
            if (r5 != 0) goto L_0x0248
            java.lang.String r0 = "[ExtraCrashManager] Failed to package crash data."
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x028e }
            com.tencent.bugly.proguard.C2014w.m2119e(r0, r1)     // Catch:{ Throwable -> 0x028e }
            java.lang.String r0 = "[ExtraCrashManager] Successfully handled."
            java.lang.Object[] r1 = new java.lang.Object[r6]
            com.tencent.bugly.proguard.C2014w.m2119e(r0, r1)
            goto L_0x0012
        L_0x0241:
            java.lang.String r2 = ""
            r7 = r2
            r2 = r1
            r1 = r7
            goto L_0x0186
        L_0x0248:
            java.lang.String r1 = com.tencent.bugly.proguard.C2018y.m2143a()     // Catch:{ Throwable -> 0x028e }
            com.tencent.bugly.crashreport.common.info.a r2 = r8.f1387c     // Catch:{ Throwable -> 0x028e }
            java.lang.String r2 = r2.f1202d     // Catch:{ Throwable -> 0x028e }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x028e }
            r3.<init>()     // Catch:{ Throwable -> 0x028e }
            java.lang.StringBuilder r3 = r3.append(r11)     // Catch:{ Throwable -> 0x028e }
            java.lang.String r4 = "\n"
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Throwable -> 0x028e }
            java.lang.StringBuilder r3 = r3.append(r12)     // Catch:{ Throwable -> 0x028e }
            java.lang.String r4 = "\n"
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Throwable -> 0x028e }
            java.lang.StringBuilder r3 = r3.append(r13)     // Catch:{ Throwable -> 0x028e }
            java.lang.String r4 = r3.toString()     // Catch:{ Throwable -> 0x028e }
            r3 = r9
            com.tencent.bugly.crashreport.crash.C1953b.m1792a(r0, r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x028e }
            com.tencent.bugly.crashreport.crash.b r0 = r8.f1388d     // Catch:{ Throwable -> 0x028e }
            boolean r0 = r0.mo19461a(r5)     // Catch:{ Throwable -> 0x028e }
            if (r0 != 0) goto L_0x0285
            com.tencent.bugly.crashreport.crash.b r0 = r8.f1388d     // Catch:{ Throwable -> 0x028e }
            r2 = 3000(0xbb8, double:1.482E-320)
            r1 = 0
            r0.mo19459a(r5, r2, r1)     // Catch:{ Throwable -> 0x028e }
        L_0x0285:
            java.lang.String r0 = "[ExtraCrashManager] Successfully handled."
            java.lang.Object[] r1 = new java.lang.Object[r6]
            com.tencent.bugly.proguard.C2014w.m2119e(r0, r1)
            goto L_0x0012
        L_0x028e:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C2014w.m2114a(r0)     // Catch:{ all -> 0x02a1 }
            if (r1 != 0) goto L_0x0298
            r0.printStackTrace()     // Catch:{ all -> 0x02a1 }
        L_0x0298:
            java.lang.String r0 = "[ExtraCrashManager] Successfully handled."
            java.lang.Object[] r1 = new java.lang.Object[r6]
            com.tencent.bugly.proguard.C2014w.m2119e(r0, r1)
            goto L_0x0012
        L_0x02a1:
            r0 = move-exception
            java.lang.String r1 = "[ExtraCrashManager] Successfully handled."
            java.lang.Object[] r2 = new java.lang.Object[r6]
            com.tencent.bugly.proguard.C2014w.m2119e(r1, r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.C1958d.m1830a(com.tencent.bugly.crashreport.crash.d, java.lang.Thread, int, java.lang.String, java.lang.String, java.lang.String, java.util.Map):void");
    }

    private C1958d(Context context) {
        C1955c a = C1955c.m1808a();
        if (a != null) {
            this.f1386b = C1941a.m1752a();
            this.f1387c = C1938a.m1667a(context);
            this.f1388d = a.f1369n;
            this.f1389e = context;
            C2012v.m2106a().mo19636a(new Runnable() {
                public final void run() {
                    C1958d.m1829a(C1958d.this);
                }
            });
        }
    }

    /* renamed from: a */
    public static C1958d m1828a(Context context) {
        if (f1385a == null) {
            f1385a = new C1958d(context);
        }
        return f1385a;
    }

    /* renamed from: a */
    public static void m1831a(Thread thread, int i, String str, String str2, String str3, Map<String, String> map) {
        final Thread thread2 = thread;
        final int i2 = i;
        final String str4 = str;
        final String str5 = str2;
        final String str6 = str3;
        final Map<String, String> map2 = map;
        C2012v.m2106a().mo19636a(new Runnable() {
            public final void run() {
                try {
                    if (C1958d.f1385a == null) {
                        C2014w.m2119e("[ExtraCrashManager] Extra crash manager has not been initialized.", new Object[0]);
                    } else {
                        C1958d.m1830a(C1958d.f1385a, thread2, i2, str4, str5, str6, map2);
                    }
                } catch (Throwable th) {
                    if (!C2014w.m2116b(th)) {
                        th.printStackTrace();
                    }
                    C2014w.m2119e("[ExtraCrashManager] Crash error %s %s %s", str4, str5, str6);
                }
            }
        });
    }
}
