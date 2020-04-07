package com.tencent.bugly.crashreport.crash.anr;

import android.content.Context;
import android.os.FileObserver;
import com.tencent.bugly.crashreport.common.info.C1938a;
import com.tencent.bugly.crashreport.common.info.C1939b;
import com.tencent.bugly.crashreport.common.strategy.C1941a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.C1953b;
import com.tencent.bugly.crashreport.crash.C1955c;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.C2012v;
import com.tencent.bugly.proguard.C2014w;
import com.tencent.bugly.proguard.C2015x;
import com.tencent.bugly.proguard.C2018y;
import java.io.File;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.p052io.IOUtils;

/* renamed from: com.tencent.bugly.crashreport.crash.anr.b */
/* compiled from: BUGLY */
public final class C1950b {

    /* renamed from: a */
    private AtomicInteger f1334a = new AtomicInteger(0);

    /* renamed from: b */
    private long f1335b = -1;

    /* renamed from: c */
    private final Context f1336c;

    /* renamed from: d */
    private final C1938a f1337d;

    /* renamed from: e */
    private final C2012v f1338e;

    /* renamed from: f */
    private final C1941a f1339f;

    /* renamed from: g */
    private final String f1340g;

    /* renamed from: h */
    private final C1953b f1341h;

    /* renamed from: i */
    private FileObserver f1342i;

    /* renamed from: j */
    private boolean f1343j = true;

    public C1950b(Context context, C1941a aVar, C1938a aVar2, C2012v vVar, C1953b bVar) {
        this.f1336c = C2018y.m2138a(context);
        this.f1340g = context.getDir("bugly", 0).getAbsolutePath();
        this.f1337d = aVar2;
        this.f1338e = vVar;
        this.f1339f = aVar;
        this.f1341h = bVar;
    }

    /* renamed from: a */
    private CrashDetailBean m1774a(C1949a aVar) {
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        try {
            crashDetailBean.f1266B = C1939b.m1735g();
            crashDetailBean.f1267C = C1939b.m1731e();
            crashDetailBean.f1268D = C1939b.m1739i();
            crashDetailBean.f1269E = this.f1337d.mo19416p();
            crashDetailBean.f1270F = this.f1337d.mo19415o();
            crashDetailBean.f1271G = this.f1337d.mo19417q();
            crashDetailBean.f1309w = C2018y.m2145a(this.f1336c, C1955c.f1358d, (String) null);
            crashDetailBean.f1310x = C2015x.m2126a(true);
            crashDetailBean.f1288b = 3;
            crashDetailBean.f1291e = this.f1337d.mo19408h();
            crashDetailBean.f1292f = this.f1337d.f1208j;
            crashDetailBean.f1293g = this.f1337d.mo19423w();
            crashDetailBean.f1299m = this.f1337d.mo19406g();
            crashDetailBean.f1300n = "ANR_EXCEPTION";
            crashDetailBean.f1301o = aVar.f1332f;
            crashDetailBean.f1303q = aVar.f1333g;
            crashDetailBean.f1278N = new HashMap();
            crashDetailBean.f1278N.put("BUGLY_CR_01", aVar.f1331e);
            int i = -1;
            if (crashDetailBean.f1303q != null) {
                i = crashDetailBean.f1303q.indexOf(IOUtils.LINE_SEPARATOR_UNIX);
            }
            crashDetailBean.f1302p = i > 0 ? crashDetailBean.f1303q.substring(0, i) : "GET_FAIL";
            crashDetailBean.f1304r = aVar.f1329c;
            if (crashDetailBean.f1303q != null) {
                crashDetailBean.f1307u = C2018y.m2168b(crashDetailBean.f1303q.getBytes());
            }
            crashDetailBean.f1311y = aVar.f1328b;
            crashDetailBean.f1312z = this.f1337d.f1202d;
            crashDetailBean.f1265A = "main(1)";
            crashDetailBean.f1272H = this.f1337d.mo19425y();
            crashDetailBean.f1294h = this.f1337d.mo19422v();
            crashDetailBean.f1295i = this.f1337d.mo19383I();
            crashDetailBean.f1308v = aVar.f1330d;
            crashDetailBean.f1275K = this.f1337d.f1212n;
            crashDetailBean.f1276L = this.f1337d.f1181a;
            crashDetailBean.f1277M = this.f1337d.mo19394a();
            crashDetailBean.f1279O = this.f1337d.mo19380F();
            crashDetailBean.f1280P = this.f1337d.mo19381G();
            crashDetailBean.f1281Q = this.f1337d.mo19426z();
            crashDetailBean.f1282R = this.f1337d.mo19379E();
        } catch (Throwable th) {
            if (!C2014w.m2114a(th)) {
                th.printStackTrace();
            }
        }
        return crashDetailBean;
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x017d A[Catch:{ all -> 0x01e7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x01b0 A[SYNTHETIC, Splitter:B:52:0x01b0] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x01d6 A[SYNTHETIC, Splitter:B:69:0x01d6] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean m1775a(java.lang.String r12, java.lang.String r13, java.lang.String r14) {
        /*
            r11 = 3
            r5 = 2
            r3 = 1
            r4 = 0
            com.tencent.bugly.crashreport.crash.anr.TraceFileHelper$a r6 = com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.readTargetDumpInfo(r14, r12, r3)
            if (r6 == 0) goto L_0x0016
            java.util.Map<java.lang.String, java.lang.String[]> r1 = r6.f1326d
            if (r1 == 0) goto L_0x0016
            java.util.Map<java.lang.String, java.lang.String[]> r1 = r6.f1326d
            int r1 = r1.size()
            if (r1 > 0) goto L_0x0021
        L_0x0016:
            java.lang.String r1 = "not found trace dump for %s"
            java.lang.Object[] r2 = new java.lang.Object[r3]
            r2[r4] = r14
            com.tencent.bugly.proguard.C2014w.m2119e(r1, r2)
            r1 = r4
        L_0x0020:
            return r1
        L_0x0021:
            java.io.File r1 = new java.io.File
            r1.<init>(r13)
            boolean r2 = r1.exists()     // Catch:{ Exception -> 0x0057 }
            if (r2 != 0) goto L_0x0040
            java.io.File r2 = r1.getParentFile()     // Catch:{ Exception -> 0x0057 }
            boolean r2 = r2.exists()     // Catch:{ Exception -> 0x0057 }
            if (r2 != 0) goto L_0x003d
            java.io.File r2 = r1.getParentFile()     // Catch:{ Exception -> 0x0057 }
            r2.mkdirs()     // Catch:{ Exception -> 0x0057 }
        L_0x003d:
            r1.createNewFile()     // Catch:{ Exception -> 0x0057 }
        L_0x0040:
            boolean r2 = r1.exists()
            if (r2 == 0) goto L_0x004c
            boolean r2 = r1.canWrite()
            if (r2 != 0) goto L_0x0091
        L_0x004c:
            java.lang.String r1 = "backup file create fail %s"
            java.lang.Object[] r2 = new java.lang.Object[r3]
            r2[r4] = r13
            com.tencent.bugly.proguard.C2014w.m2119e(r1, r2)
            r1 = r4
            goto L_0x0020
        L_0x0057:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.proguard.C2014w.m2114a(r1)
            if (r2 != 0) goto L_0x0061
            r1.printStackTrace()
        L_0x0061:
            java.lang.String r2 = "backup file create error! %s  %s"
            java.lang.Object[] r5 = new java.lang.Object[r5]
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.Class r7 = r1.getClass()
            java.lang.String r7 = r7.getName()
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r7 = ":"
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r1 = r1.getMessage()
            java.lang.StringBuilder r1 = r6.append(r1)
            java.lang.String r1 = r1.toString()
            r5[r4] = r1
            r5[r3] = r13
            com.tencent.bugly.proguard.C2014w.m2119e(r2, r5)
            r1 = r4
            goto L_0x0020
        L_0x0091:
            r2 = 0
            java.io.BufferedWriter r5 = new java.io.BufferedWriter     // Catch:{ IOException -> 0x01ea, all -> 0x01d2 }
            java.io.FileWriter r7 = new java.io.FileWriter     // Catch:{ IOException -> 0x01ea, all -> 0x01d2 }
            r8 = 0
            r7.<init>(r1, r8)     // Catch:{ IOException -> 0x01ea, all -> 0x01d2 }
            r5.<init>(r7)     // Catch:{ IOException -> 0x01ea, all -> 0x01d2 }
            java.util.Map<java.lang.String, java.lang.String[]> r1 = r6.f1326d     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.String r2 = "main"
            java.lang.Object r1 = r1.get(r2)     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.String[] r1 = (java.lang.String[]) r1     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            if (r1 == 0) goto L_0x00e4
            int r2 = r1.length     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            if (r2 < r11) goto L_0x00e4
            r2 = 0
            r2 = r1[r2]     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            r7 = 1
            r7 = r1[r7]     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            r8 = 2
            r1 = r1[r8]     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.String r9 = "\"main\" tid="
            r8.<init>(r9)     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.StringBuilder r1 = r8.append(r1)     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.String r8 = " :\n"
            java.lang.StringBuilder r1 = r1.append(r8)     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.String r2 = "\n"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.StringBuilder r1 = r1.append(r7)     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.String r2 = "\n\n"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            r5.write(r1)     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            r5.flush()     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
        L_0x00e4:
            java.util.Map<java.lang.String, java.lang.String[]> r1 = r6.f1326d     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.util.Set r1 = r1.entrySet()     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.util.Iterator r6 = r1.iterator()     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
        L_0x00ee:
            boolean r1 = r6.hasNext()     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            if (r1 == 0) goto L_0x01b6
            java.lang.Object r1 = r6.next()     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            r0 = r1
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            r2 = r0
            java.lang.Object r1 = r2.getKey()     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.String r7 = "main"
            boolean r1 = r1.equals(r7)     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            if (r1 != 0) goto L_0x00ee
            java.lang.Object r1 = r2.getValue()     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            if (r1 == 0) goto L_0x00ee
            java.lang.Object r1 = r2.getValue()     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.String[] r1 = (java.lang.String[]) r1     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            int r1 = r1.length     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            if (r1 < r11) goto L_0x00ee
            java.lang.Object r1 = r2.getValue()     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.String[] r1 = (java.lang.String[]) r1     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            r7 = 0
            r7 = r1[r7]     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.Object r1 = r2.getValue()     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.String[] r1 = (java.lang.String[]) r1     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            r8 = 1
            r8 = r1[r8]     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.Object r1 = r2.getValue()     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.String[] r1 = (java.lang.String[]) r1     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            r9 = 2
            r9 = r1[r9]     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.String r1 = "\""
            r10.<init>(r1)     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.Object r1 = r2.getKey()     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.StringBuilder r1 = r10.append(r1)     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.String r2 = "\" tid="
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.StringBuilder r1 = r1.append(r9)     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.String r2 = " :\n"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.StringBuilder r1 = r1.append(r7)     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.String r2 = "\n"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.StringBuilder r1 = r1.append(r8)     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.String r2 = "\n\n"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            r5.write(r1)     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            r5.flush()     // Catch:{ IOException -> 0x0175, all -> 0x01e5 }
            goto L_0x00ee
        L_0x0175:
            r1 = move-exception
            r2 = r5
        L_0x0177:
            boolean r3 = com.tencent.bugly.proguard.C2014w.m2114a(r1)     // Catch:{ all -> 0x01e7 }
            if (r3 != 0) goto L_0x0180
            r1.printStackTrace()     // Catch:{ all -> 0x01e7 }
        L_0x0180:
            java.lang.String r3 = "dump trace fail %s"
            r5 = 1
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x01e7 }
            r6 = 0
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x01e7 }
            r7.<init>()     // Catch:{ all -> 0x01e7 }
            java.lang.Class r8 = r1.getClass()     // Catch:{ all -> 0x01e7 }
            java.lang.String r8 = r8.getName()     // Catch:{ all -> 0x01e7 }
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ all -> 0x01e7 }
            java.lang.String r8 = ":"
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ all -> 0x01e7 }
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x01e7 }
            java.lang.StringBuilder r1 = r7.append(r1)     // Catch:{ all -> 0x01e7 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x01e7 }
            r5[r6] = r1     // Catch:{ all -> 0x01e7 }
            com.tencent.bugly.proguard.C2014w.m2119e(r3, r5)     // Catch:{ all -> 0x01e7 }
            if (r2 == 0) goto L_0x01b3
            r2.close()     // Catch:{ IOException -> 0x01c7 }
        L_0x01b3:
            r1 = r4
            goto L_0x0020
        L_0x01b6:
            r5.close()     // Catch:{ IOException -> 0x01bc }
        L_0x01b9:
            r1 = r3
            goto L_0x0020
        L_0x01bc:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.proguard.C2014w.m2114a(r1)
            if (r2 != 0) goto L_0x01b9
            r1.printStackTrace()
            goto L_0x01b9
        L_0x01c7:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.proguard.C2014w.m2114a(r1)
            if (r2 != 0) goto L_0x01b3
            r1.printStackTrace()
            goto L_0x01b3
        L_0x01d2:
            r1 = move-exception
            r5 = r2
        L_0x01d4:
            if (r5 == 0) goto L_0x01d9
            r5.close()     // Catch:{ IOException -> 0x01da }
        L_0x01d9:
            throw r1
        L_0x01da:
            r2 = move-exception
            boolean r3 = com.tencent.bugly.proguard.C2014w.m2114a(r2)
            if (r3 != 0) goto L_0x01d9
            r2.printStackTrace()
            goto L_0x01d9
        L_0x01e5:
            r1 = move-exception
            goto L_0x01d4
        L_0x01e7:
            r1 = move-exception
            r5 = r2
            goto L_0x01d4
        L_0x01ea:
            r1 = move-exception
            goto L_0x0177
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.anr.C1950b.m1775a(java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    /* renamed from: a */
    public final boolean mo19454a() {
        return this.f1334a.get() != 0;
    }

    /* JADX INFO: used method not loaded: com.tencent.bugly.crashreport.crash.b.a(com.tencent.bugly.crashreport.crash.CrashDetailBean):null, types can be incorrect */
    /* JADX INFO: used method not loaded: com.tencent.bugly.crashreport.crash.b.a(com.tencent.bugly.crashreport.crash.CrashDetailBean, long, boolean):null, types can be incorrect */
    /* JADX INFO: used method not loaded: com.tencent.bugly.crashreport.crash.b.b(com.tencent.bugly.crashreport.crash.CrashDetailBean):null, types can be incorrect */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x025d, code lost:
        com.tencent.bugly.proguard.C2014w.m2118d("ANR Report is closed!", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0266, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0267, code lost:
        r12.f1334a.set(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x026d, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:?, code lost:
        com.tencent.bugly.proguard.C2014w.m2113a("found visiable anr , start to upload!", new java.lang.Object[0]);
        r5 = m1774a(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x027a, code lost:
        if (r5 != null) goto L_0x0285;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x027c, code lost:
        com.tencent.bugly.proguard.C2014w.m2119e("pack anr fail!", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0285, code lost:
        com.tencent.bugly.crashreport.crash.C1955c.m1808a().mo19467a(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        com.tencent.bugly.proguard.C2014w.m2117c("read trace first dump for create time!", new java.lang.Object[0]);
        r0 = -1;
        r2 = com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.readFirstDumpInfo(r13, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0292, code lost:
        if (r5.f1287a < 0) goto L_0x02e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0294, code lost:
        com.tencent.bugly.proguard.C2014w.m2113a("backup anr record success!", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x029c, code lost:
        if (r13 == null) goto L_0x02c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x02a7, code lost:
        if (new java.io.File(r13).exists() == false) goto L_0x02c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x02a9, code lost:
        r12.f1334a.set(3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x02b7, code lost:
        if (m1775a(r13, r7.f1330d, r7.f1327a) == false) goto L_0x02c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x02b9, code lost:
        com.tencent.bugly.proguard.C2014w.m2113a("backup trace success", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x02c1, code lost:
        com.tencent.bugly.crashreport.crash.C1953b.m1792a("ANR", com.tencent.bugly.proguard.C2018y.m2143a(), r7.f1327a, null, r7.f1331e, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x02d5, code lost:
        if (r12.f1341h.mo19461a(r5) != false) goto L_0x02df;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0029, code lost:
        if (r2 == null) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x02d7, code lost:
        r12.f1341h.mo19459a(r5, 3000, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x02df, code lost:
        r12.f1341h.mo19463b(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x02e6, code lost:
        com.tencent.bugly.proguard.C2014w.m2118d("backup anr record fail!", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x02f2, code lost:
        r4 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002b, code lost:
        r0 = r2.f1325c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0031, code lost:
        if (r0 != -1) goto L_0x02f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0033, code lost:
        com.tencent.bugly.proguard.C2014w.m2118d("trace dump fail could not get time!", new java.lang.Object[0]);
        r4 = java.lang.System.currentTimeMillis();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004c, code lost:
        if (java.lang.Math.abs(r4 - r12.f1335b) >= 10000) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004e, code lost:
        com.tencent.bugly.proguard.C2014w.m2118d("should not process ANR too Fre in %d", java.lang.Integer.valueOf(p004cn.jiguang.api.utils.ByteBufferUtils.ERROR_CODE));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005f, code lost:
        r12.f1334a.set(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r12.f1335b = r4;
        r12.f1334a.set(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r6 = com.tencent.bugly.proguard.C2018y.m2152a(com.tencent.bugly.crashreport.crash.C1955c.f1359e, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0078, code lost:
        if (r6 == null) goto L_0x0080;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x007e, code lost:
        if (r6.size() > 0) goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0080, code lost:
        com.tencent.bugly.proguard.C2014w.m2118d("can't get all thread skip this anr", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0088, code lost:
        r12.f1334a.set(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x008f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        com.tencent.bugly.proguard.C2014w.m2114a(r0);
        com.tencent.bugly.proguard.C2014w.m2119e("get all thread stack fail!", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x009b, code lost:
        r12.f1334a.set(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        r7 = r12.f1336c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00ab, code lost:
        if (10000 >= 0) goto L_0x0104;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00ad, code lost:
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b0, code lost:
        com.tencent.bugly.proguard.C2014w.m2117c("to find!", new java.lang.Object[0]);
        r0 = (android.app.ActivityManager) r7.getSystemService("activity");
        r8 = r2 / 500;
        r1 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00c6, code lost:
        r2 = r1;
        com.tencent.bugly.proguard.C2014w.m2117c("waiting!", new java.lang.Object[0]);
        r1 = r0.getProcessesInErrorState();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00d3, code lost:
        if (r1 == null) goto L_0x0108;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00d5, code lost:
        r3 = r1.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00dd, code lost:
        if (r3.hasNext() == false) goto L_0x0108;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00df, code lost:
        r1 = (android.app.ActivityManager.ProcessErrorStateInfo) r3.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00e8, code lost:
        if (r1.condition != 2) goto L_0x00d9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00ea, code lost:
        com.tencent.bugly.proguard.C2014w.m2117c("found!", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00f2, code lost:
        if (r1 != null) goto L_0x011e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00f4, code lost:
        com.tencent.bugly.proguard.C2014w.m2117c("proc state is unvisiable!", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00fc, code lost:
        r12.f1334a.set(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0104, code lost:
        r2 = 10000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        com.tencent.bugly.proguard.C2018y.m2170b(500);
        r1 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0112, code lost:
        if (((long) r2) < r8) goto L_0x02ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0114, code lost:
        com.tencent.bugly.proguard.C2014w.m2117c("end!", new java.lang.Object[0]);
        r1 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0124, code lost:
        if (r1.pid == android.os.Process.myPid()) goto L_0x013b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0126, code lost:
        com.tencent.bugly.proguard.C2014w.m2117c("not mind proc!", r1.processName);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0133, code lost:
        r12.f1334a.set(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:?, code lost:
        com.tencent.bugly.proguard.C2014w.m2113a("found visiable anr , start to process!", new java.lang.Object[0]);
        r2 = r12.f1336c;
        r12.f1339f.mo19435c();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0150, code lost:
        if (r12.f1339f.mo19434b() != false) goto L_0x016f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0152, code lost:
        com.tencent.bugly.proguard.C2014w.m2119e("waiting for remote sync", new java.lang.Object[0]);
        r0 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0162, code lost:
        if (r12.f1339f.mo19434b() != false) goto L_0x016f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0164, code lost:
        com.tencent.bugly.proguard.C2018y.m2170b(500);
        r0 = r0 + 500;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x016d, code lost:
        if (r0 < 3000) goto L_0x015c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x016f, code lost:
        r0 = new java.io.File(r2.getFilesDir(), "bugly/bugly_trace_" + r4 + ".txt");
        r7 = new com.tencent.bugly.crashreport.crash.anr.C1949a();
        r7.f1329c = r4;
        r7.f1330d = r0.getAbsolutePath();
        r7.f1327a = r1.processName;
        r7.f1332f = r1.shortMsg;
        r7.f1331e = r1.longMsg;
        r7.f1328b = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01a8, code lost:
        if (r6 == null) goto L_0x01f4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01aa, code lost:
        r1 = r6.keySet().iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01b6, code lost:
        if (r1.hasNext() == false) goto L_0x01f4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01b8, code lost:
        r0 = (java.lang.String) r1.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01c4, code lost:
        if (r0.startsWith("main(") == false) goto L_0x01b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01c6, code lost:
        r7.f1333g = (java.lang.String) r6.get(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01cf, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01d4, code lost:
        if (com.tencent.bugly.proguard.C2014w.m2114a(r0) == false) goto L_0x01d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x01d6, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01d9, code lost:
        com.tencent.bugly.proguard.C2014w.m2119e("handle anr error %s", r0.getClass().toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01f4, code lost:
        r1 = "anr tm:%d\ntr:%s\nproc:%s\nsMsg:%s\n lMsg:%s\n threads:%d";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:?, code lost:
        r2 = new java.lang.Object[6];
        r2[0] = java.lang.Long.valueOf(r7.f1329c);
        r2[1] = r7.f1330d;
        r2[2] = r7.f1327a;
        r2[3] = r7.f1332f;
        r2[4] = r7.f1331e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0219, code lost:
        if (r7.f1328b != null) goto L_0x024c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x021b, code lost:
        r0 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x021c, code lost:
        r2[5] = java.lang.Integer.valueOf(r0);
        com.tencent.bugly.proguard.C2014w.m2117c(r1, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x022b, code lost:
        if (r12.f1339f.mo19434b() != false) goto L_0x0253;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x022d, code lost:
        com.tencent.bugly.proguard.C2014w.m2119e("crash report sync remote fail, will not upload to Bugly , print local for helpful!", new java.lang.Object[0]);
        com.tencent.bugly.crashreport.crash.C1953b.m1792a("ANR", com.tencent.bugly.proguard.C2018y.m2143a(), r7.f1327a, null, r7.f1331e, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0244, code lost:
        r12.f1334a.set(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:?, code lost:
        r0 = r7.f1328b.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x025b, code lost:
        if (r12.f1339f.mo19435c().f1236j != false) goto L_0x026e;
     */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo19452a(java.lang.String r13) {
        /*
            r12 = this;
            monitor-enter(r12)
            java.util.concurrent.atomic.AtomicInteger r0 = r12.f1334a     // Catch:{ all -> 0x0066 }
            int r0 = r0.get()     // Catch:{ all -> 0x0066 }
            if (r0 == 0) goto L_0x0013
            java.lang.String r0 = "trace started return "
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0066 }
            com.tencent.bugly.proguard.C2014w.m2117c(r0, r1)     // Catch:{ all -> 0x0066 }
            monitor-exit(r12)     // Catch:{ all -> 0x0066 }
        L_0x0012:
            return
        L_0x0013:
            java.util.concurrent.atomic.AtomicInteger r0 = r12.f1334a     // Catch:{ all -> 0x0066 }
            r1 = 1
            r0.set(r1)     // Catch:{ all -> 0x0066 }
            monitor-exit(r12)     // Catch:{ all -> 0x0066 }
            java.lang.String r0 = "read trace first dump for create time!"
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x01cf }
            com.tencent.bugly.proguard.C2014w.m2117c(r0, r1)     // Catch:{ Throwable -> 0x01cf }
            r0 = -1
            r2 = 0
            com.tencent.bugly.crashreport.crash.anr.TraceFileHelper$a r2 = com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.readFirstDumpInfo(r13, r2)     // Catch:{ Throwable -> 0x01cf }
            if (r2 == 0) goto L_0x002d
            long r0 = r2.f1325c     // Catch:{ Throwable -> 0x01cf }
        L_0x002d:
            r2 = -1
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 != 0) goto L_0x02f2
            java.lang.String r0 = "trace dump fail could not get time!"
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x01cf }
            com.tencent.bugly.proguard.C2014w.m2118d(r0, r1)     // Catch:{ Throwable -> 0x01cf }
            long r0 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x01cf }
            r4 = r0
        L_0x0040:
            long r0 = r12.f1335b     // Catch:{ Throwable -> 0x01cf }
            long r0 = r4 - r0
            long r0 = java.lang.Math.abs(r0)     // Catch:{ Throwable -> 0x01cf }
            r2 = 10000(0x2710, double:4.9407E-320)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 >= 0) goto L_0x0069
            java.lang.String r0 = "should not process ANR too Fre in %d"
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x01cf }
            r2 = 0
            r3 = 10000(0x2710, float:1.4013E-41)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ Throwable -> 0x01cf }
            r1[r2] = r3     // Catch:{ Throwable -> 0x01cf }
            com.tencent.bugly.proguard.C2014w.m2118d(r0, r1)     // Catch:{ Throwable -> 0x01cf }
            java.util.concurrent.atomic.AtomicInteger r0 = r12.f1334a
            r1 = 0
            r0.set(r1)
            goto L_0x0012
        L_0x0066:
            r0 = move-exception
            monitor-exit(r12)
            throw r0
        L_0x0069:
            r12.f1335b = r4     // Catch:{ Throwable -> 0x01cf }
            java.util.concurrent.atomic.AtomicInteger r0 = r12.f1334a     // Catch:{ Throwable -> 0x01cf }
            r1 = 1
            r0.set(r1)     // Catch:{ Throwable -> 0x01cf }
            int r0 = com.tencent.bugly.crashreport.crash.C1955c.f1359e     // Catch:{ Throwable -> 0x008f }
            r1 = 0
            java.util.Map r6 = com.tencent.bugly.proguard.C2018y.m2152a(r0, r1)     // Catch:{ Throwable -> 0x008f }
            if (r6 == 0) goto L_0x0080
            int r0 = r6.size()     // Catch:{ Throwable -> 0x01cf }
            if (r0 > 0) goto L_0x00a3
        L_0x0080:
            java.lang.String r0 = "can't get all thread skip this anr"
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x01cf }
            com.tencent.bugly.proguard.C2014w.m2118d(r0, r1)     // Catch:{ Throwable -> 0x01cf }
            java.util.concurrent.atomic.AtomicInteger r0 = r12.f1334a
            r1 = 0
            r0.set(r1)
            goto L_0x0012
        L_0x008f:
            r0 = move-exception
            com.tencent.bugly.proguard.C2014w.m2114a(r0)     // Catch:{ Throwable -> 0x01cf }
            java.lang.String r0 = "get all thread stack fail!"
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x01cf }
            com.tencent.bugly.proguard.C2014w.m2119e(r0, r1)     // Catch:{ Throwable -> 0x01cf }
            java.util.concurrent.atomic.AtomicInteger r0 = r12.f1334a
            r1 = 0
            r0.set(r1)
            goto L_0x0012
        L_0x00a3:
            android.content.Context r7 = r12.f1336c     // Catch:{ Throwable -> 0x01cf }
            r0 = 10000(0x2710, double:4.9407E-320)
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 >= 0) goto L_0x0104
            r0 = 0
            r2 = r0
        L_0x00b0:
            java.lang.String r0 = "to find!"
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x01cf }
            com.tencent.bugly.proguard.C2014w.m2117c(r0, r1)     // Catch:{ Throwable -> 0x01cf }
            java.lang.String r0 = "activity"
            java.lang.Object r0 = r7.getSystemService(r0)     // Catch:{ Throwable -> 0x01cf }
            android.app.ActivityManager r0 = (android.app.ActivityManager) r0     // Catch:{ Throwable -> 0x01cf }
            r8 = 500(0x1f4, double:2.47E-321)
            long r8 = r2 / r8
            r1 = 0
            r2 = r1
        L_0x00c6:
            java.lang.String r1 = "waiting!"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x01cf }
            com.tencent.bugly.proguard.C2014w.m2117c(r1, r3)     // Catch:{ Throwable -> 0x01cf }
            java.util.List r1 = r0.getProcessesInErrorState()     // Catch:{ Throwable -> 0x01cf }
            if (r1 == 0) goto L_0x0108
            java.util.Iterator r3 = r1.iterator()     // Catch:{ Throwable -> 0x01cf }
        L_0x00d9:
            boolean r1 = r3.hasNext()     // Catch:{ Throwable -> 0x01cf }
            if (r1 == 0) goto L_0x0108
            java.lang.Object r1 = r3.next()     // Catch:{ Throwable -> 0x01cf }
            android.app.ActivityManager$ProcessErrorStateInfo r1 = (android.app.ActivityManager.ProcessErrorStateInfo) r1     // Catch:{ Throwable -> 0x01cf }
            int r7 = r1.condition     // Catch:{ Throwable -> 0x01cf }
            r10 = 2
            if (r7 != r10) goto L_0x00d9
            java.lang.String r0 = "found!"
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x01cf }
            com.tencent.bugly.proguard.C2014w.m2117c(r0, r2)     // Catch:{ Throwable -> 0x01cf }
        L_0x00f2:
            if (r1 != 0) goto L_0x011e
            java.lang.String r0 = "proc state is unvisiable!"
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x01cf }
            com.tencent.bugly.proguard.C2014w.m2117c(r0, r1)     // Catch:{ Throwable -> 0x01cf }
            java.util.concurrent.atomic.AtomicInteger r0 = r12.f1334a
            r1 = 0
            r0.set(r1)
            goto L_0x0012
        L_0x0104:
            r0 = 10000(0x2710, double:4.9407E-320)
            r2 = r0
            goto L_0x00b0
        L_0x0108:
            r10 = 500(0x1f4, double:2.47E-321)
            com.tencent.bugly.proguard.C2018y.m2170b(r10)     // Catch:{ Throwable -> 0x01cf }
            int r1 = r2 + 1
            long r2 = (long) r2     // Catch:{ Throwable -> 0x01cf }
            int r2 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
            if (r2 < 0) goto L_0x02ef
            java.lang.String r0 = "end!"
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x01cf }
            com.tencent.bugly.proguard.C2014w.m2117c(r0, r1)     // Catch:{ Throwable -> 0x01cf }
            r1 = 0
            goto L_0x00f2
        L_0x011e:
            int r0 = r1.pid     // Catch:{ Throwable -> 0x01cf }
            int r2 = android.os.Process.myPid()     // Catch:{ Throwable -> 0x01cf }
            if (r0 == r2) goto L_0x013b
            java.lang.String r0 = "not mind proc!"
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x01cf }
            r3 = 0
            java.lang.String r1 = r1.processName     // Catch:{ Throwable -> 0x01cf }
            r2[r3] = r1     // Catch:{ Throwable -> 0x01cf }
            com.tencent.bugly.proguard.C2014w.m2117c(r0, r2)     // Catch:{ Throwable -> 0x01cf }
            java.util.concurrent.atomic.AtomicInteger r0 = r12.f1334a
            r1 = 0
            r0.set(r1)
            goto L_0x0012
        L_0x013b:
            java.lang.String r0 = "found visiable anr , start to process!"
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x01cf }
            com.tencent.bugly.proguard.C2014w.m2113a(r0, r2)     // Catch:{ Throwable -> 0x01cf }
            android.content.Context r2 = r12.f1336c     // Catch:{ Throwable -> 0x01cf }
            com.tencent.bugly.crashreport.common.strategy.a r0 = r12.f1339f     // Catch:{ Throwable -> 0x01cf }
            r0.mo19435c()     // Catch:{ Throwable -> 0x01cf }
            com.tencent.bugly.crashreport.common.strategy.a r0 = r12.f1339f     // Catch:{ Throwable -> 0x01cf }
            boolean r0 = r0.mo19434b()     // Catch:{ Throwable -> 0x01cf }
            if (r0 != 0) goto L_0x016f
            java.lang.String r0 = "waiting for remote sync"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x01cf }
            com.tencent.bugly.proguard.C2014w.m2119e(r0, r3)     // Catch:{ Throwable -> 0x01cf }
            r0 = 0
        L_0x015c:
            com.tencent.bugly.crashreport.common.strategy.a r3 = r12.f1339f     // Catch:{ Throwable -> 0x01cf }
            boolean r3 = r3.mo19434b()     // Catch:{ Throwable -> 0x01cf }
            if (r3 != 0) goto L_0x016f
            r8 = 500(0x1f4, double:2.47E-321)
            com.tencent.bugly.proguard.C2018y.m2170b(r8)     // Catch:{ Throwable -> 0x01cf }
            int r0 = r0 + 500
            r3 = 3000(0xbb8, float:4.204E-42)
            if (r0 < r3) goto L_0x015c
        L_0x016f:
            java.io.File r0 = new java.io.File     // Catch:{ Throwable -> 0x01cf }
            java.io.File r2 = r2.getFilesDir()     // Catch:{ Throwable -> 0x01cf }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01cf }
            java.lang.String r7 = "bugly/bugly_trace_"
            r3.<init>(r7)     // Catch:{ Throwable -> 0x01cf }
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Throwable -> 0x01cf }
            java.lang.String r7 = ".txt"
            java.lang.StringBuilder r3 = r3.append(r7)     // Catch:{ Throwable -> 0x01cf }
            java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x01cf }
            r0.<init>(r2, r3)     // Catch:{ Throwable -> 0x01cf }
            com.tencent.bugly.crashreport.crash.anr.a r7 = new com.tencent.bugly.crashreport.crash.anr.a     // Catch:{ Throwable -> 0x01cf }
            r7.<init>()     // Catch:{ Throwable -> 0x01cf }
            r7.f1329c = r4     // Catch:{ Throwable -> 0x01cf }
            java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ Throwable -> 0x01cf }
            r7.f1330d = r0     // Catch:{ Throwable -> 0x01cf }
            java.lang.String r0 = r1.processName     // Catch:{ Throwable -> 0x01cf }
            r7.f1327a = r0     // Catch:{ Throwable -> 0x01cf }
            java.lang.String r0 = r1.shortMsg     // Catch:{ Throwable -> 0x01cf }
            r7.f1332f = r0     // Catch:{ Throwable -> 0x01cf }
            java.lang.String r0 = r1.longMsg     // Catch:{ Throwable -> 0x01cf }
            r7.f1331e = r0     // Catch:{ Throwable -> 0x01cf }
            r7.f1328b = r6     // Catch:{ Throwable -> 0x01cf }
            if (r6 == 0) goto L_0x01f4
            java.util.Set r0 = r6.keySet()     // Catch:{ Throwable -> 0x01cf }
            java.util.Iterator r1 = r0.iterator()     // Catch:{ Throwable -> 0x01cf }
        L_0x01b2:
            boolean r0 = r1.hasNext()     // Catch:{ Throwable -> 0x01cf }
            if (r0 == 0) goto L_0x01f4
            java.lang.Object r0 = r1.next()     // Catch:{ Throwable -> 0x01cf }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x01cf }
            java.lang.String r2 = "main("
            boolean r2 = r0.startsWith(r2)     // Catch:{ Throwable -> 0x01cf }
            if (r2 == 0) goto L_0x01b2
            java.lang.Object r0 = r6.get(r0)     // Catch:{ Throwable -> 0x01cf }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x01cf }
            r7.f1333g = r0     // Catch:{ Throwable -> 0x01cf }
            goto L_0x01b2
        L_0x01cf:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C2014w.m2114a(r0)     // Catch:{ all -> 0x0266 }
            if (r1 != 0) goto L_0x01d9
            r0.printStackTrace()     // Catch:{ all -> 0x0266 }
        L_0x01d9:
            java.lang.String r1 = "handle anr error %s"
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0266 }
            r3 = 0
            java.lang.Class r0 = r0.getClass()     // Catch:{ all -> 0x0266 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0266 }
            r2[r3] = r0     // Catch:{ all -> 0x0266 }
            com.tencent.bugly.proguard.C2014w.m2119e(r1, r2)     // Catch:{ all -> 0x0266 }
            java.util.concurrent.atomic.AtomicInteger r0 = r12.f1334a
            r1 = 0
            r0.set(r1)
            goto L_0x0012
        L_0x01f4:
            java.lang.String r1 = "anr tm:%d\ntr:%s\nproc:%s\nsMsg:%s\n lMsg:%s\n threads:%d"
            r0 = 6
            java.lang.Object[] r2 = new java.lang.Object[r0]     // Catch:{ Throwable -> 0x01cf }
            r0 = 0
            long r4 = r7.f1329c     // Catch:{ Throwable -> 0x01cf }
            java.lang.Long r3 = java.lang.Long.valueOf(r4)     // Catch:{ Throwable -> 0x01cf }
            r2[r0] = r3     // Catch:{ Throwable -> 0x01cf }
            r0 = 1
            java.lang.String r3 = r7.f1330d     // Catch:{ Throwable -> 0x01cf }
            r2[r0] = r3     // Catch:{ Throwable -> 0x01cf }
            r0 = 2
            java.lang.String r3 = r7.f1327a     // Catch:{ Throwable -> 0x01cf }
            r2[r0] = r3     // Catch:{ Throwable -> 0x01cf }
            r0 = 3
            java.lang.String r3 = r7.f1332f     // Catch:{ Throwable -> 0x01cf }
            r2[r0] = r3     // Catch:{ Throwable -> 0x01cf }
            r0 = 4
            java.lang.String r3 = r7.f1331e     // Catch:{ Throwable -> 0x01cf }
            r2[r0] = r3     // Catch:{ Throwable -> 0x01cf }
            r3 = 5
            java.util.Map<java.lang.String, java.lang.String> r0 = r7.f1328b     // Catch:{ Throwable -> 0x01cf }
            if (r0 != 0) goto L_0x024c
            r0 = 0
        L_0x021c:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x01cf }
            r2[r3] = r0     // Catch:{ Throwable -> 0x01cf }
            com.tencent.bugly.proguard.C2014w.m2117c(r1, r2)     // Catch:{ Throwable -> 0x01cf }
            com.tencent.bugly.crashreport.common.strategy.a r0 = r12.f1339f     // Catch:{ Throwable -> 0x01cf }
            boolean r0 = r0.mo19434b()     // Catch:{ Throwable -> 0x01cf }
            if (r0 != 0) goto L_0x0253
            java.lang.String r0 = "crash report sync remote fail, will not upload to Bugly , print local for helpful!"
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x01cf }
            com.tencent.bugly.proguard.C2014w.m2119e(r0, r1)     // Catch:{ Throwable -> 0x01cf }
            java.lang.String r0 = "ANR"
            java.lang.String r1 = com.tencent.bugly.proguard.C2018y.m2143a()     // Catch:{ Throwable -> 0x01cf }
            java.lang.String r2 = r7.f1327a     // Catch:{ Throwable -> 0x01cf }
            r3 = 0
            java.lang.String r4 = r7.f1331e     // Catch:{ Throwable -> 0x01cf }
            r5 = 0
            com.tencent.bugly.crashreport.crash.C1953b.m1792a(r0, r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x01cf }
        L_0x0244:
            java.util.concurrent.atomic.AtomicInteger r0 = r12.f1334a
            r1 = 0
            r0.set(r1)
            goto L_0x0012
        L_0x024c:
            java.util.Map<java.lang.String, java.lang.String> r0 = r7.f1328b     // Catch:{ Throwable -> 0x01cf }
            int r0 = r0.size()     // Catch:{ Throwable -> 0x01cf }
            goto L_0x021c
        L_0x0253:
            com.tencent.bugly.crashreport.common.strategy.a r0 = r12.f1339f     // Catch:{ Throwable -> 0x01cf }
            com.tencent.bugly.crashreport.common.strategy.StrategyBean r0 = r0.mo19435c()     // Catch:{ Throwable -> 0x01cf }
            boolean r0 = r0.f1236j     // Catch:{ Throwable -> 0x01cf }
            if (r0 != 0) goto L_0x026e
            java.lang.String r0 = "ANR Report is closed!"
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x01cf }
            com.tencent.bugly.proguard.C2014w.m2118d(r0, r1)     // Catch:{ Throwable -> 0x01cf }
            goto L_0x0244
        L_0x0266:
            r0 = move-exception
            java.util.concurrent.atomic.AtomicInteger r1 = r12.f1334a
            r2 = 0
            r1.set(r2)
            throw r0
        L_0x026e:
            java.lang.String r0 = "found visiable anr , start to upload!"
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x01cf }
            com.tencent.bugly.proguard.C2014w.m2113a(r0, r1)     // Catch:{ Throwable -> 0x01cf }
            com.tencent.bugly.crashreport.crash.CrashDetailBean r5 = r12.m1774a(r7)     // Catch:{ Throwable -> 0x01cf }
            if (r5 != 0) goto L_0x0285
            java.lang.String r0 = "pack anr fail!"
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x01cf }
            com.tencent.bugly.proguard.C2014w.m2119e(r0, r1)     // Catch:{ Throwable -> 0x01cf }
            goto L_0x0244
        L_0x0285:
            com.tencent.bugly.crashreport.crash.c r0 = com.tencent.bugly.crashreport.crash.C1955c.m1808a()     // Catch:{ Throwable -> 0x01cf }
            r0.mo19467a(r5)     // Catch:{ Throwable -> 0x01cf }
            long r0 = r5.f1287a     // Catch:{ Throwable -> 0x01cf }
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 < 0) goto L_0x02e6
            java.lang.String r0 = "backup anr record success!"
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x01cf }
            com.tencent.bugly.proguard.C2014w.m2113a(r0, r1)     // Catch:{ Throwable -> 0x01cf }
        L_0x029c:
            if (r13 == 0) goto L_0x02c1
            java.io.File r0 = new java.io.File     // Catch:{ Throwable -> 0x01cf }
            r0.<init>(r13)     // Catch:{ Throwable -> 0x01cf }
            boolean r0 = r0.exists()     // Catch:{ Throwable -> 0x01cf }
            if (r0 == 0) goto L_0x02c1
            java.util.concurrent.atomic.AtomicInteger r0 = r12.f1334a     // Catch:{ Throwable -> 0x01cf }
            r1 = 3
            r0.set(r1)     // Catch:{ Throwable -> 0x01cf }
            java.lang.String r0 = r7.f1330d     // Catch:{ Throwable -> 0x01cf }
            java.lang.String r1 = r7.f1327a     // Catch:{ Throwable -> 0x01cf }
            boolean r0 = m1775a(r13, r0, r1)     // Catch:{ Throwable -> 0x01cf }
            if (r0 == 0) goto L_0x02c1
            java.lang.String r0 = "backup trace success"
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x01cf }
            com.tencent.bugly.proguard.C2014w.m2113a(r0, r1)     // Catch:{ Throwable -> 0x01cf }
        L_0x02c1:
            java.lang.String r0 = "ANR"
            java.lang.String r1 = com.tencent.bugly.proguard.C2018y.m2143a()     // Catch:{ Throwable -> 0x01cf }
            java.lang.String r2 = r7.f1327a     // Catch:{ Throwable -> 0x01cf }
            r3 = 0
            java.lang.String r4 = r7.f1331e     // Catch:{ Throwable -> 0x01cf }
            com.tencent.bugly.crashreport.crash.C1953b.m1792a(r0, r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x01cf }
            com.tencent.bugly.crashreport.crash.b r0 = r12.f1341h     // Catch:{ Throwable -> 0x01cf }
            boolean r0 = r0.mo19461a(r5)     // Catch:{ Throwable -> 0x01cf }
            if (r0 != 0) goto L_0x02df
            com.tencent.bugly.crashreport.crash.b r0 = r12.f1341h     // Catch:{ Throwable -> 0x01cf }
            r2 = 3000(0xbb8, double:1.482E-320)
            r1 = 1
            r0.mo19459a(r5, r2, r1)     // Catch:{ Throwable -> 0x01cf }
        L_0x02df:
            com.tencent.bugly.crashreport.crash.b r0 = r12.f1341h     // Catch:{ Throwable -> 0x01cf }
            r0.mo19463b(r5)     // Catch:{ Throwable -> 0x01cf }
            goto L_0x0244
        L_0x02e6:
            java.lang.String r0 = "backup anr record fail!"
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x01cf }
            com.tencent.bugly.proguard.C2014w.m2118d(r0, r1)     // Catch:{ Throwable -> 0x01cf }
            goto L_0x029c
        L_0x02ef:
            r2 = r1
            goto L_0x00c6
        L_0x02f2:
            r4 = r0
            goto L_0x0040
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.anr.C1950b.mo19452a(java.lang.String):void");
    }

    /* renamed from: c */
    private synchronized void m1777c() {
        if (m1780e()) {
            C2014w.m2118d("start when started!", new Object[0]);
        } else {
            this.f1342i = new FileObserver("/data/anr/", 8) {
                public final void onEvent(int i, String str) {
                    if (str != null) {
                        String str2 = "/data/anr/" + str;
                        if (!str2.contains("trace")) {
                            C2014w.m2118d("not anr file %s", str2);
                            return;
                        }
                        C1950b.this.mo19452a(str2);
                    }
                }
            };
            try {
                this.f1342i.startWatching();
                C2014w.m2113a("start anr monitor!", new Object[0]);
                this.f1338e.mo19636a(new Runnable() {
                    public final void run() {
                        C1950b.this.mo19455b();
                    }
                });
            } catch (Throwable th) {
                this.f1342i = null;
                C2014w.m2118d("start anr monitor failed!", new Object[0]);
                if (!C2014w.m2114a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* renamed from: d */
    private synchronized void m1779d() {
        if (!m1780e()) {
            C2014w.m2118d("close when closed!", new Object[0]);
        } else {
            try {
                this.f1342i.stopWatching();
                this.f1342i = null;
                C2014w.m2118d("close anr monitor!", new Object[0]);
            } catch (Throwable th) {
                C2014w.m2118d("stop anr monitor failed!", new Object[0]);
                if (!C2014w.m2114a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* renamed from: e */
    private synchronized boolean m1780e() {
        return this.f1342i != null;
    }

    /* renamed from: b */
    private synchronized void m1776b(boolean z) {
        if (z) {
            m1777c();
        } else {
            m1779d();
        }
    }

    /* renamed from: f */
    private synchronized boolean m1781f() {
        return this.f1343j;
    }

    /* renamed from: c */
    private synchronized void m1778c(boolean z) {
        if (this.f1343j != z) {
            C2014w.m2113a("user change anr %b", Boolean.valueOf(z));
            this.f1343j = z;
        }
    }

    /* renamed from: a */
    public final void mo19453a(boolean z) {
        m1778c(z);
        boolean f = m1781f();
        C1941a a = C1941a.m1752a();
        if (a != null) {
            f = f && a.mo19435c().f1233g;
        }
        if (f != m1780e()) {
            C2014w.m2113a("anr changed to %b", Boolean.valueOf(f));
            m1776b(f);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo19455b() {
        long b = C2018y.m2166b() - C1955c.f1360f;
        File file = new File(this.f1340g);
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                String str = "bugly_trace_";
                String str2 = ".txt";
                int length = str.length();
                int i = 0;
                for (File file2 : listFiles) {
                    String name = file2.getName();
                    if (name.startsWith(str)) {
                        try {
                            int indexOf = name.indexOf(str2);
                            if (indexOf > 0 && Long.parseLong(name.substring(length, indexOf)) >= b) {
                            }
                        } catch (Throwable th) {
                            C2014w.m2119e("tomb format error delete %s", name);
                        }
                        if (file2.delete()) {
                            i++;
                        }
                    }
                }
                C2014w.m2117c("clean tombs %d", Integer.valueOf(i));
            }
        }
    }

    /* renamed from: a */
    public final synchronized void mo19451a(StrategyBean strategyBean) {
        boolean z = true;
        synchronized (this) {
            if (strategyBean != null) {
                if (strategyBean.f1236j != m1780e()) {
                    C2014w.m2118d("server anr changed to %b", Boolean.valueOf(strategyBean.f1236j));
                }
                if (!strategyBean.f1236j || !m1781f()) {
                    z = false;
                }
                if (z != m1780e()) {
                    C2014w.m2113a("anr changed to %b", Boolean.valueOf(z));
                    m1776b(z);
                }
            }
        }
    }
}
