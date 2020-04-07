package com.tencent.bugly.crashreport.crash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcelable;
import com.android.vending.expansion.zipfile.APEZProvider;
import com.tencent.bugly.BuglyStrategy.C1910a;
import com.tencent.bugly.C1925b;
import com.tencent.bugly.crashreport.common.info.C1938a;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.crashreport.common.strategy.C1941a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.C1967a;
import com.tencent.bugly.proguard.C1974ag;
import com.tencent.bugly.proguard.C1976ai;
import com.tencent.bugly.proguard.C1977aj;
import com.tencent.bugly.proguard.C1978ak;
import com.tencent.bugly.proguard.C1979al;
import com.tencent.bugly.proguard.C1994j;
import com.tencent.bugly.proguard.C2000n;
import com.tencent.bugly.proguard.C2001o;
import com.tencent.bugly.proguard.C2004q;
import com.tencent.bugly.proguard.C2006s;
import com.tencent.bugly.proguard.C2007t;
import com.tencent.bugly.proguard.C2014w;
import com.tencent.bugly.proguard.C2018y;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

/* renamed from: com.tencent.bugly.crashreport.crash.b */
/* compiled from: BUGLY */
public final class C1953b {

    /* renamed from: a */
    private static int f1346a = 0;

    /* renamed from: b */
    private Context f1347b;

    /* renamed from: c */
    private C2007t f1348c;

    /* renamed from: d */
    private C2001o f1349d;

    /* renamed from: e */
    private C1941a f1350e;

    /* renamed from: f */
    private C2000n f1351f;

    /* renamed from: g */
    private C1910a f1352g;

    public C1953b(int i, Context context, C2007t tVar, C2001o oVar, C1941a aVar, C1910a aVar2, C2000n nVar) {
        f1346a = i;
        this.f1347b = context;
        this.f1348c = tVar;
        this.f1349d = oVar;
        this.f1350e = aVar;
        this.f1352g = aVar2;
        this.f1351f = nVar;
    }

    /* renamed from: a */
    private static List<C1944a> m1791a(List<C1944a> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        for (C1944a aVar : list) {
            if (aVar.f1316d && aVar.f1314b <= currentTimeMillis - 86400000) {
                arrayList.add(aVar);
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0145  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.tencent.bugly.crashreport.crash.CrashDetailBean m1788a(java.util.List<com.tencent.bugly.crashreport.crash.C1944a> r11, com.tencent.bugly.crashreport.crash.CrashDetailBean r12) {
        /*
            r10 = this;
            r3 = 0
            if (r11 == 0) goto L_0x0009
            int r0 = r11.size()
            if (r0 != 0) goto L_0x000b
        L_0x0009:
            r1 = r12
        L_0x000a:
            return r1
        L_0x000b:
            r1 = 0
            java.util.ArrayList r2 = new java.util.ArrayList
            r0 = 10
            r2.<init>(r0)
            java.util.Iterator r4 = r11.iterator()
        L_0x0017:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x002b
            java.lang.Object r0 = r4.next()
            com.tencent.bugly.crashreport.crash.a r0 = (com.tencent.bugly.crashreport.crash.C1944a) r0
            boolean r5 = r0.f1317e
            if (r5 == 0) goto L_0x0017
            r2.add(r0)
            goto L_0x0017
        L_0x002b:
            int r0 = r2.size()
            if (r0 <= 0) goto L_0x014b
            java.util.List r4 = r10.m1796b(r2)
            if (r4 == 0) goto L_0x014b
            int r0 = r4.size()
            if (r0 <= 0) goto L_0x014b
            java.util.Collections.sort(r4)
            r2 = r3
        L_0x0041:
            int r0 = r4.size()
            if (r2 >= r0) goto L_0x00a1
            java.lang.Object r0 = r4.get(r2)
            com.tencent.bugly.crashreport.crash.CrashDetailBean r0 = (com.tencent.bugly.crashreport.crash.CrashDetailBean) r0
            if (r2 != 0) goto L_0x0054
        L_0x004f:
            int r1 = r2 + 1
            r2 = r1
            r1 = r0
            goto L_0x0041
        L_0x0054:
            java.lang.String r5 = r0.f1305s
            if (r5 == 0) goto L_0x0148
            java.lang.String r0 = r0.f1305s
            java.lang.String r5 = "\n"
            java.lang.String[] r5 = r0.split(r5)
            if (r5 == 0) goto L_0x0148
            int r6 = r5.length
            r0 = r3
        L_0x0064:
            if (r0 >= r6) goto L_0x0148
            r7 = r5[r0]
            java.lang.String r8 = r1.f1305s
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.StringBuilder r9 = r9.append(r7)
            java.lang.String r9 = r9.toString()
            boolean r8 = r8.contains(r9)
            if (r8 != 0) goto L_0x009e
            int r8 = r1.f1306t
            int r8 = r8 + 1
            r1.f1306t = r8
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = r1.f1305s
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.StringBuilder r7 = r8.append(r7)
            java.lang.String r8 = "\n"
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r7 = r7.toString()
            r1.f1305s = r7
        L_0x009e:
            int r0 = r0 + 1
            goto L_0x0064
        L_0x00a1:
            r0 = r1
        L_0x00a2:
            if (r0 != 0) goto L_0x0145
            r0 = 1
            r12.f1296j = r0
            r12.f1306t = r3
            java.lang.String r0 = ""
            r12.f1305s = r0
            r1 = r12
        L_0x00ae:
            java.util.Iterator r2 = r11.iterator()
        L_0x00b2:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x0101
            java.lang.Object r0 = r2.next()
            com.tencent.bugly.crashreport.crash.a r0 = (com.tencent.bugly.crashreport.crash.C1944a) r0
            boolean r3 = r0.f1317e
            if (r3 != 0) goto L_0x00b2
            boolean r3 = r0.f1316d
            if (r3 != 0) goto L_0x00b2
            java.lang.String r3 = r1.f1305s
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            long r6 = r0.f1314b
            java.lang.StringBuilder r4 = r4.append(r6)
            java.lang.String r4 = r4.toString()
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x00b2
            int r3 = r1.f1306t
            int r3 = r3 + 1
            r1.f1306t = r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r1.f1305s
            java.lang.StringBuilder r3 = r3.append(r4)
            long r4 = r0.f1314b
            java.lang.StringBuilder r0 = r3.append(r4)
            java.lang.String r3 = "\n"
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r0 = r0.toString()
            r1.f1305s = r0
            goto L_0x00b2
        L_0x0101:
            long r2 = r1.f1304r
            long r4 = r12.f1304r
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x000a
            java.lang.String r0 = r1.f1305s
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            long r4 = r12.f1304r
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r2 = r2.toString()
            boolean r0 = r0.contains(r2)
            if (r0 != 0) goto L_0x000a
            int r0 = r1.f1306t
            int r0 = r0 + 1
            r1.f1306t = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r1.f1305s
            java.lang.StringBuilder r0 = r0.append(r2)
            long r2 = r12.f1304r
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r2 = "\n"
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r0 = r0.toString()
            r1.f1305s = r0
            goto L_0x000a
        L_0x0145:
            r1 = r0
            goto L_0x00ae
        L_0x0148:
            r0 = r1
            goto L_0x004f
        L_0x014b:
            r0 = r1
            goto L_0x00a2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.C1953b.m1788a(java.util.List, com.tencent.bugly.crashreport.crash.CrashDetailBean):com.tencent.bugly.crashreport.crash.CrashDetailBean");
    }

    /* renamed from: a */
    public final boolean mo19461a(CrashDetailBean crashDetailBean) {
        return mo19462a(crashDetailBean, -123456789);
    }

    /* renamed from: a */
    public final boolean mo19462a(CrashDetailBean crashDetailBean, int i) {
        if (crashDetailBean == null) {
            return true;
        }
        if (C1955c.f1366l != null && !C1955c.f1366l.isEmpty()) {
            C2014w.m2117c("Crash filter for crash stack is: %s", C1955c.f1366l);
            if (crashDetailBean.f1303q.contains(C1955c.f1366l)) {
                C2014w.m2118d("This crash contains the filter string set. It will not be record and upload.", new Object[0]);
                return true;
            }
        }
        if (C1955c.f1367m != null && !C1955c.f1367m.isEmpty()) {
            C2014w.m2117c("Crash regular filter for crash stack is: %s", C1955c.f1367m);
            if (Pattern.compile(C1955c.f1367m).matcher(crashDetailBean.f1303q).find()) {
                C2014w.m2118d("This crash matches the regular filter string set. It will not be record and upload.", new Object[0]);
                return true;
            }
        }
        int i2 = crashDetailBean.f1288b;
        String str = crashDetailBean.f1300n;
        String str2 = crashDetailBean.f1302p;
        String str3 = crashDetailBean.f1303q;
        long j = crashDetailBean.f1304r;
        String str4 = crashDetailBean.f1299m;
        String str5 = crashDetailBean.f1291e;
        String str6 = crashDetailBean.f1289c;
        if (this.f1351f != null) {
            C2000n nVar = this.f1351f;
            String str7 = crashDetailBean.f1312z;
            if (!nVar.mo19596c()) {
                return true;
            }
        }
        if (crashDetailBean.f1288b != 2) {
            C2004q qVar = new C2004q();
            qVar.f1634b = 1;
            qVar.f1635c = crashDetailBean.f1312z;
            qVar.f1636d = crashDetailBean.f1265A;
            qVar.f1637e = crashDetailBean.f1304r;
            this.f1349d.mo19606b(1);
            this.f1349d.mo19605a(qVar);
            C2014w.m2115b("[crash] a crash occur, handling...", new Object[0]);
        } else {
            C2014w.m2115b("[crash] a caught exception occur, handling...", new Object[0]);
        }
        List<C1944a> b = m1795b();
        List list = null;
        if (b != null && b.size() > 0) {
            ArrayList arrayList = new ArrayList(10);
            ArrayList<C1944a> arrayList2 = new ArrayList<>(10);
            arrayList.addAll(m1791a(b));
            b.removeAll(arrayList);
            if (!C1925b.f1097c && C1955c.f1357c) {
                boolean z = false;
                for (C1944a aVar : b) {
                    if (crashDetailBean.f1307u.equals(aVar.f1315c)) {
                        if (aVar.f1317e) {
                            z = true;
                        }
                        arrayList2.add(aVar);
                    }
                    z = z;
                }
                if (z || arrayList2.size() >= 2) {
                    C2014w.m2113a("same crash occur too much do merged!", new Object[0]);
                    CrashDetailBean a = m1788a((List<C1944a>) arrayList2, crashDetailBean);
                    for (C1944a aVar2 : arrayList2) {
                        if (aVar2.f1313a != a.f1287a) {
                            arrayList.add(aVar2);
                        }
                    }
                    mo19464c(a);
                    m1797c((List<C1944a>) arrayList);
                    C2014w.m2115b("[crash] save crash success. For this device crash many times, it will not upload crashes immediately", new Object[0]);
                    return true;
                }
            }
            list = arrayList;
        }
        mo19464c(crashDetailBean);
        if (list != null && !list.isEmpty()) {
            m1797c(list);
        }
        C2014w.m2115b("[crash] save crash success", new Object[0]);
        return false;
    }

    /* renamed from: a */
    public final List<CrashDetailBean> mo19458a() {
        StrategyBean c = C1941a.m1752a().mo19435c();
        if (c == null) {
            C2014w.m2118d("have not synced remote!", new Object[0]);
            return null;
        } else if (!c.f1233g) {
            C2014w.m2118d("Crashreport remote closed, please check your APP ID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            C2014w.m2115b("[init] WARNING! Crashreport closed by server, please check your APP ID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            return null;
        } else {
            long currentTimeMillis = System.currentTimeMillis();
            long b = C2018y.m2166b();
            List b2 = m1795b();
            if (b2 == null || b2.size() <= 0) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            Iterator it = b2.iterator();
            while (it.hasNext()) {
                C1944a aVar = (C1944a) it.next();
                if (aVar.f1314b < b - C1955c.f1360f) {
                    it.remove();
                    arrayList.add(aVar);
                } else if (aVar.f1316d) {
                    if (aVar.f1314b >= currentTimeMillis - 86400000) {
                        it.remove();
                    } else if (!aVar.f1317e) {
                        it.remove();
                        arrayList.add(aVar);
                    }
                } else if (((long) aVar.f1318f) >= 3 && aVar.f1314b < currentTimeMillis - 86400000) {
                    it.remove();
                    arrayList.add(aVar);
                }
            }
            if (arrayList.size() > 0) {
                m1797c((List<C1944a>) arrayList);
            }
            ArrayList arrayList2 = new ArrayList();
            List b3 = m1796b(b2);
            if (b3 != null && b3.size() > 0) {
                String str = C1938a.m1668b().f1208j;
                Iterator it2 = b3.iterator();
                while (it2.hasNext()) {
                    CrashDetailBean crashDetailBean = (CrashDetailBean) it2.next();
                    if (!str.equals(crashDetailBean.f1292f)) {
                        it2.remove();
                        arrayList2.add(crashDetailBean);
                    }
                }
            }
            if (arrayList2.size() > 0) {
                m1799d((List<CrashDetailBean>) arrayList2);
            }
            return b3;
        }
    }

    /* renamed from: a */
    public final void mo19459a(CrashDetailBean crashDetailBean, long j, boolean z) {
        boolean z2 = false;
        if (C1955c.f1365k) {
            C2014w.m2113a("try to upload right now", new Object[0]);
            ArrayList arrayList = new ArrayList();
            arrayList.add(crashDetailBean);
            if (crashDetailBean.f1288b == 7) {
                z2 = true;
            }
            mo19460a(arrayList, 3000, z, z2, z);
            if (this.f1351f != null) {
                C2000n nVar = this.f1351f;
                int i = crashDetailBean.f1288b;
            }
        }
    }

    /* renamed from: a */
    public final void mo19460a(final List<CrashDetailBean> list, long j, boolean z, boolean z2, boolean z3) {
        C1978ak akVar;
        if (!C1938a.m1667a(this.f1347b).f1203e || this.f1348c == null) {
            return;
        }
        if (z3 || this.f1348c.mo19628b(C1955c.f1355a)) {
            StrategyBean c = this.f1350e.mo19435c();
            if (!c.f1233g) {
                C2014w.m2118d("remote report is disable!", new Object[0]);
                C2014w.m2115b("[crash] server closed bugly in this app. please check your appid if is correct, and re-install it", new Object[0]);
            } else if (list != null && list.size() != 0) {
                try {
                    String str = this.f1348c.f1644a ? c.f1245s : c.f1246t;
                    String str2 = this.f1348c.f1644a ? StrategyBean.f1229c : StrategyBean.f1227a;
                    int i = this.f1348c.f1644a ? 830 : 630;
                    Context context = this.f1347b;
                    C1938a b = C1938a.m1668b();
                    if (context == null || list == null || list.size() == 0 || b == null) {
                        C2014w.m2118d("enEXPPkg args == null!", new Object[0]);
                        akVar = null;
                    } else {
                        C1978ak akVar2 = new C1978ak();
                        akVar2.f1495a = new ArrayList<>();
                        for (CrashDetailBean a : list) {
                            akVar2.f1495a.add(m1790a(context, a, b));
                        }
                        akVar = akVar2;
                    }
                    if (akVar == null) {
                        C2014w.m2118d("create eupPkg fail!", new Object[0]);
                        return;
                    }
                    byte[] a2 = C1967a.m1896a((C1994j) akVar);
                    if (a2 == null) {
                        C2014w.m2118d("send encode fail!", new Object[0]);
                        return;
                    }
                    C1979al a3 = C1967a.m1888a(this.f1347b, i, a2);
                    if (a3 == null) {
                        C2014w.m2118d("request package is null.", new Object[0]);
                        return;
                    }
                    C19541 r5 = new C2006s() {
                        /* renamed from: a */
                        public final void mo19357a(boolean z) {
                            C1953b bVar = C1953b.this;
                            C1953b.m1793a(z, list);
                        }
                    };
                    if (z) {
                        this.f1348c.mo19620a(f1346a, a3, str, str2, r5, j, z2);
                    } else {
                        this.f1348c.mo19621a(f1346a, a3, str, str2, r5, false);
                    }
                } catch (Throwable th) {
                    C2014w.m2119e("req cr error %s", th.toString());
                    if (!C2014w.m2116b(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public static void m1793a(boolean z, List<CrashDetailBean> list) {
        if (list != null && list.size() > 0) {
            C2014w.m2117c("up finish update state %b", Boolean.valueOf(z));
            for (CrashDetailBean crashDetailBean : list) {
                C2014w.m2117c("pre uid:%s uc:%d re:%b me:%b", crashDetailBean.f1289c, Integer.valueOf(crashDetailBean.f1298l), Boolean.valueOf(crashDetailBean.f1290d), Boolean.valueOf(crashDetailBean.f1296j));
                crashDetailBean.f1298l++;
                crashDetailBean.f1290d = z;
                C2014w.m2117c("set uid:%s uc:%d re:%b me:%b", crashDetailBean.f1289c, Integer.valueOf(crashDetailBean.f1298l), Boolean.valueOf(crashDetailBean.f1290d), Boolean.valueOf(crashDetailBean.f1296j));
            }
            for (CrashDetailBean a : list) {
                C1955c.m1808a().mo19467a(a);
            }
            C2014w.m2117c("update state size %d", Integer.valueOf(list.size()));
        }
        if (!z) {
            C2014w.m2115b("[crash] upload fail.", new Object[0]);
        }
    }

    /* renamed from: b */
    public final void mo19463b(CrashDetailBean crashDetailBean) {
        int i;
        String str;
        if (crashDetailBean != null) {
            if (this.f1352g != null || this.f1351f != null) {
                try {
                    C2014w.m2113a("[crash callback] start user's callback:onCrashHandleStart()", new Object[0]);
                    switch (crashDetailBean.f1288b) {
                        case 0:
                            i = 0;
                            break;
                        case 1:
                            i = 2;
                            break;
                        case 2:
                            i = 1;
                            break;
                        case 3:
                            i = 4;
                            break;
                        case 4:
                            i = 3;
                            break;
                        case 5:
                            i = 5;
                            break;
                        case 6:
                            i = 6;
                            break;
                        case 7:
                            i = 7;
                            break;
                        default:
                            return;
                    }
                    int i2 = crashDetailBean.f1288b;
                    String str2 = crashDetailBean.f1300n;
                    String str3 = crashDetailBean.f1302p;
                    String str4 = crashDetailBean.f1303q;
                    long j = crashDetailBean.f1304r;
                    Map map = null;
                    if (this.f1351f != null) {
                        C2000n nVar = this.f1351f;
                        String b = this.f1351f.mo19595b();
                        if (b != null) {
                            map = new HashMap(1);
                            map.put("userData", b);
                        }
                    } else if (this.f1352g != null) {
                        map = this.f1352g.onCrashHandleStart(i, crashDetailBean.f1300n, crashDetailBean.f1301o, crashDetailBean.f1303q);
                    }
                    if (map != null && map.size() > 0) {
                        crashDetailBean.f1278N = new LinkedHashMap(map.size());
                        for (Entry entry : map.entrySet()) {
                            if (!C2018y.m2158a((String) entry.getKey())) {
                                String str5 = (String) entry.getKey();
                                if (str5.length() > 100) {
                                    str5 = str5.substring(0, 100);
                                    C2014w.m2118d("setted key length is over limit %d substring to %s", Integer.valueOf(100), str5);
                                }
                                String str6 = str5;
                                if (C2018y.m2158a((String) entry.getValue()) || ((String) entry.getValue()).length() <= 30000) {
                                    str = ((String) entry.getValue());
                                } else {
                                    str = ((String) entry.getValue()).substring(((String) entry.getValue()).length() - 30000);
                                    C2014w.m2118d("setted %s value length is over limit %d substring", str6, Integer.valueOf(C1910a.MAX_USERDATA_VALUE_LENGTH));
                                }
                                crashDetailBean.f1278N.put(str6, str);
                                C2014w.m2113a("add setted key %s value size:%d", str6, Integer.valueOf(str.length()));
                            }
                        }
                    }
                    C2014w.m2113a("[crash callback] start user's callback:onCrashHandleStart2GetExtraDatas()", new Object[0]);
                    byte[] bArr = null;
                    if (this.f1351f != null) {
                        bArr = this.f1351f.mo19594a();
                    } else if (this.f1352g != null) {
                        bArr = this.f1352g.onCrashHandleStart2GetExtraDatas(i, crashDetailBean.f1300n, crashDetailBean.f1301o, crashDetailBean.f1303q);
                    }
                    crashDetailBean.f1283S = bArr;
                    if (crashDetailBean.f1283S != null) {
                        if (crashDetailBean.f1283S.length > 30000) {
                            C2014w.m2118d("extra bytes size %d is over limit %d will drop over part", Integer.valueOf(crashDetailBean.f1283S.length), Integer.valueOf(C1910a.MAX_USERDATA_VALUE_LENGTH));
                        }
                        C2014w.m2113a("add extra bytes %d ", Integer.valueOf(crashDetailBean.f1283S.length));
                    }
                } catch (Throwable th) {
                    C2014w.m2118d("crash handle callback somthing wrong! %s", th.getClass().getName());
                    if (!C2014w.m2114a(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
    }

    /* renamed from: d */
    private static ContentValues m1798d(CrashDetailBean crashDetailBean) {
        int i;
        int i2 = 1;
        if (crashDetailBean == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (crashDetailBean.f1287a > 0) {
                contentValues.put(APEZProvider.FILEID, Long.valueOf(crashDetailBean.f1287a));
            }
            contentValues.put("_tm", Long.valueOf(crashDetailBean.f1304r));
            contentValues.put("_s1", crashDetailBean.f1307u);
            String str = "_up";
            if (crashDetailBean.f1290d) {
                i = 1;
            } else {
                i = 0;
            }
            contentValues.put(str, Integer.valueOf(i));
            String str2 = "_me";
            if (!crashDetailBean.f1296j) {
                i2 = 0;
            }
            contentValues.put(str2, Integer.valueOf(i2));
            contentValues.put("_uc", Integer.valueOf(crashDetailBean.f1298l));
            contentValues.put("_dt", C2018y.m2161a((Parcelable) crashDetailBean));
            return contentValues;
        } catch (Throwable th) {
            if (C2014w.m2114a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private static CrashDetailBean m1787a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex(APEZProvider.FILEID));
            CrashDetailBean crashDetailBean = (CrashDetailBean) C2018y.m2142a(blob, CrashDetailBean.CREATOR);
            if (crashDetailBean == null) {
                return crashDetailBean;
            }
            crashDetailBean.f1287a = j;
            return crashDetailBean;
        } catch (Throwable th) {
            if (!C2014w.m2114a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: c */
    public final void mo19464c(CrashDetailBean crashDetailBean) {
        if (crashDetailBean != null) {
            ContentValues d = m1798d(crashDetailBean);
            if (d != null) {
                long a = C2001o.m2035a().mo19598a("t_cr", d, (C2000n) null, true);
                if (a >= 0) {
                    C2014w.m2117c("insert %s success!", "t_cr");
                    crashDetailBean.f1287a = a;
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        com.tencent.bugly.proguard.C2014w.m2118d("unknown id!", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a8, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00ab, code lost:
        r8.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a8 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:19:0x005c] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ab  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.tencent.bugly.crashreport.crash.CrashDetailBean> m1796b(java.util.List<com.tencent.bugly.crashreport.crash.C1944a> r11) {
        /*
            r10 = this;
            r8 = 4
            r6 = 0
            r7 = 0
            if (r11 == 0) goto L_0x000b
            int r0 = r11.size()
            if (r0 != 0) goto L_0x000d
        L_0x000b:
            r0 = r7
        L_0x000c:
            return r0
        L_0x000d:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.util.Iterator r1 = r11.iterator()
        L_0x0016:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L_0x0034
            java.lang.Object r0 = r1.next()
            com.tencent.bugly.crashreport.crash.a r0 = (com.tencent.bugly.crashreport.crash.C1944a) r0
            java.lang.String r2 = " or _id"
            java.lang.StringBuilder r2 = r9.append(r2)
            java.lang.String r3 = " = "
            java.lang.StringBuilder r2 = r2.append(r3)
            long r4 = r0.f1313a
            r2.append(r4)
            goto L_0x0016
        L_0x0034:
            java.lang.String r3 = r9.toString()
            int r0 = r3.length()
            if (r0 <= 0) goto L_0x0042
            java.lang.String r3 = r3.substring(r8)
        L_0x0042:
            r9.setLength(r6)
            com.tencent.bugly.proguard.o r0 = com.tencent.bugly.proguard.C2001o.m2035a()     // Catch:{ Throwable -> 0x00ed, all -> 0x00e7 }
            java.lang.String r1 = "t_cr"
            r2 = 0
            r4 = 0
            r5 = 0
            r6 = 1
            android.database.Cursor r8 = r0.mo19599a(r1, r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x00ed, all -> 0x00e7 }
            if (r8 != 0) goto L_0x005c
            if (r8 == 0) goto L_0x005a
            r8.close()
        L_0x005a:
            r0 = r7
            goto L_0x000c
        L_0x005c:
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0071, all -> 0x00a8 }
            r6.<init>()     // Catch:{ Throwable -> 0x0071, all -> 0x00a8 }
        L_0x0061:
            boolean r0 = r8.moveToNext()     // Catch:{ Throwable -> 0x0071, all -> 0x00a8 }
            if (r0 == 0) goto L_0x00af
            com.tencent.bugly.crashreport.crash.CrashDetailBean r0 = m1787a(r8)     // Catch:{ Throwable -> 0x0071, all -> 0x00a8 }
            if (r0 == 0) goto L_0x0083
            r6.add(r0)     // Catch:{ Throwable -> 0x0071, all -> 0x00a8 }
            goto L_0x0061
        L_0x0071:
            r0 = move-exception
            r1 = r8
        L_0x0073:
            boolean r2 = com.tencent.bugly.proguard.C2014w.m2114a(r0)     // Catch:{ all -> 0x00ea }
            if (r2 != 0) goto L_0x007c
            r0.printStackTrace()     // Catch:{ all -> 0x00ea }
        L_0x007c:
            if (r1 == 0) goto L_0x0081
            r1.close()
        L_0x0081:
            r0 = r7
            goto L_0x000c
        L_0x0083:
            java.lang.String r0 = "_id"
            int r0 = r8.getColumnIndex(r0)     // Catch:{ Throwable -> 0x009d, all -> 0x00a8 }
            long r0 = r8.getLong(r0)     // Catch:{ Throwable -> 0x009d, all -> 0x00a8 }
            java.lang.String r2 = " or _id"
            java.lang.StringBuilder r2 = r9.append(r2)     // Catch:{ Throwable -> 0x009d, all -> 0x00a8 }
            java.lang.String r3 = " = "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Throwable -> 0x009d, all -> 0x00a8 }
            r2.append(r0)     // Catch:{ Throwable -> 0x009d, all -> 0x00a8 }
            goto L_0x0061
        L_0x009d:
            r0 = move-exception
            java.lang.String r0 = "unknown id!"
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x0071, all -> 0x00a8 }
            com.tencent.bugly.proguard.C2014w.m2118d(r0, r1)     // Catch:{ Throwable -> 0x0071, all -> 0x00a8 }
            goto L_0x0061
        L_0x00a8:
            r0 = move-exception
        L_0x00a9:
            if (r8 == 0) goto L_0x00ae
            r8.close()
        L_0x00ae:
            throw r0
        L_0x00af:
            java.lang.String r0 = r9.toString()     // Catch:{ Throwable -> 0x0071, all -> 0x00a8 }
            int r1 = r0.length()     // Catch:{ Throwable -> 0x0071, all -> 0x00a8 }
            if (r1 <= 0) goto L_0x00df
            r1 = 4
            java.lang.String r2 = r0.substring(r1)     // Catch:{ Throwable -> 0x0071, all -> 0x00a8 }
            com.tencent.bugly.proguard.o r0 = com.tencent.bugly.proguard.C2001o.m2035a()     // Catch:{ Throwable -> 0x0071, all -> 0x00a8 }
            java.lang.String r1 = "t_cr"
            r3 = 0
            r4 = 0
            r5 = 1
            int r0 = r0.mo19597a(r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x0071, all -> 0x00a8 }
            java.lang.String r1 = "deleted %s illegle data %d"
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x0071, all -> 0x00a8 }
            r3 = 0
            java.lang.String r4 = "t_cr"
            r2[r3] = r4     // Catch:{ Throwable -> 0x0071, all -> 0x00a8 }
            r3 = 1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x0071, all -> 0x00a8 }
            r2[r3] = r0     // Catch:{ Throwable -> 0x0071, all -> 0x00a8 }
            com.tencent.bugly.proguard.C2014w.m2118d(r1, r2)     // Catch:{ Throwable -> 0x0071, all -> 0x00a8 }
        L_0x00df:
            if (r8 == 0) goto L_0x00e4
            r8.close()
        L_0x00e4:
            r0 = r6
            goto L_0x000c
        L_0x00e7:
            r0 = move-exception
            r8 = r7
            goto L_0x00a9
        L_0x00ea:
            r0 = move-exception
            r8 = r1
            goto L_0x00a9
        L_0x00ed:
            r0 = move-exception
            r1 = r7
            goto L_0x0073
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.C1953b.m1796b(java.util.List):java.util.List");
    }

    /* renamed from: b */
    private static C1944a m1794b(Cursor cursor) {
        boolean z = true;
        if (cursor == null) {
            return null;
        }
        try {
            C1944a aVar = new C1944a();
            aVar.f1313a = cursor.getLong(cursor.getColumnIndex(APEZProvider.FILEID));
            aVar.f1314b = cursor.getLong(cursor.getColumnIndex("_tm"));
            aVar.f1315c = cursor.getString(cursor.getColumnIndex("_s1"));
            aVar.f1316d = cursor.getInt(cursor.getColumnIndex("_up")) == 1;
            if (cursor.getInt(cursor.getColumnIndex("_me")) != 1) {
                z = false;
            }
            aVar.f1317e = z;
            aVar.f1318f = cursor.getInt(cursor.getColumnIndex("_uc"));
            return aVar;
        } catch (Throwable th) {
            if (C2014w.m2114a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        com.tencent.bugly.proguard.C2014w.m2118d("unknown id!", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x008a, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x008d, code lost:
        r6.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x008a A[ExcHandler: all (th java.lang.Throwable), Splitter:B:7:0x003e] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x008d  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.tencent.bugly.crashreport.crash.C1944a> m1795b() {
        /*
            r9 = this;
            r7 = 0
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            r0 = 6
            java.lang.String[] r2 = new java.lang.String[r0]     // Catch:{ Throwable -> 0x00cf, all -> 0x00c9 }
            r0 = 0
            java.lang.String r1 = "_id"
            r2[r0] = r1     // Catch:{ Throwable -> 0x00cf, all -> 0x00c9 }
            r0 = 1
            java.lang.String r1 = "_tm"
            r2[r0] = r1     // Catch:{ Throwable -> 0x00cf, all -> 0x00c9 }
            r0 = 2
            java.lang.String r1 = "_s1"
            r2[r0] = r1     // Catch:{ Throwable -> 0x00cf, all -> 0x00c9 }
            r0 = 3
            java.lang.String r1 = "_up"
            r2[r0] = r1     // Catch:{ Throwable -> 0x00cf, all -> 0x00c9 }
            r0 = 4
            java.lang.String r1 = "_me"
            r2[r0] = r1     // Catch:{ Throwable -> 0x00cf, all -> 0x00c9 }
            r0 = 5
            java.lang.String r1 = "_uc"
            r2[r0] = r1     // Catch:{ Throwable -> 0x00cf, all -> 0x00c9 }
            com.tencent.bugly.proguard.o r0 = com.tencent.bugly.proguard.C2001o.m2035a()     // Catch:{ Throwable -> 0x00cf, all -> 0x00c9 }
            java.lang.String r1 = "t_cr"
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 1
            android.database.Cursor r6 = r0.mo19599a(r1, r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x00cf, all -> 0x00c9 }
            if (r6 != 0) goto L_0x003e
            if (r6 == 0) goto L_0x003c
            r6.close()
        L_0x003c:
            r0 = r7
        L_0x003d:
            return r0
        L_0x003e:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0053, all -> 0x008a }
            r0.<init>()     // Catch:{ Throwable -> 0x0053, all -> 0x008a }
        L_0x0043:
            boolean r1 = r6.moveToNext()     // Catch:{ Throwable -> 0x0053, all -> 0x008a }
            if (r1 == 0) goto L_0x0091
            com.tencent.bugly.crashreport.crash.a r1 = m1794b(r6)     // Catch:{ Throwable -> 0x0053, all -> 0x008a }
            if (r1 == 0) goto L_0x0065
            r8.add(r1)     // Catch:{ Throwable -> 0x0053, all -> 0x008a }
            goto L_0x0043
        L_0x0053:
            r0 = move-exception
            r7 = r6
        L_0x0055:
            boolean r1 = com.tencent.bugly.proguard.C2014w.m2114a(r0)     // Catch:{ all -> 0x00cc }
            if (r1 != 0) goto L_0x005e
            r0.printStackTrace()     // Catch:{ all -> 0x00cc }
        L_0x005e:
            if (r7 == 0) goto L_0x0063
            r7.close()
        L_0x0063:
            r0 = r8
            goto L_0x003d
        L_0x0065:
            java.lang.String r1 = "_id"
            int r1 = r6.getColumnIndex(r1)     // Catch:{ Throwable -> 0x007f, all -> 0x008a }
            long r2 = r6.getLong(r1)     // Catch:{ Throwable -> 0x007f, all -> 0x008a }
            java.lang.String r1 = " or _id"
            java.lang.StringBuilder r1 = r0.append(r1)     // Catch:{ Throwable -> 0x007f, all -> 0x008a }
            java.lang.String r4 = " = "
            java.lang.StringBuilder r1 = r1.append(r4)     // Catch:{ Throwable -> 0x007f, all -> 0x008a }
            r1.append(r2)     // Catch:{ Throwable -> 0x007f, all -> 0x008a }
            goto L_0x0043
        L_0x007f:
            r1 = move-exception
            java.lang.String r1 = "unknown id!"
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x0053, all -> 0x008a }
            com.tencent.bugly.proguard.C2014w.m2118d(r1, r2)     // Catch:{ Throwable -> 0x0053, all -> 0x008a }
            goto L_0x0043
        L_0x008a:
            r0 = move-exception
        L_0x008b:
            if (r6 == 0) goto L_0x0090
            r6.close()
        L_0x0090:
            throw r0
        L_0x0091:
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x0053, all -> 0x008a }
            int r1 = r0.length()     // Catch:{ Throwable -> 0x0053, all -> 0x008a }
            if (r1 <= 0) goto L_0x00c1
            r1 = 4
            java.lang.String r2 = r0.substring(r1)     // Catch:{ Throwable -> 0x0053, all -> 0x008a }
            com.tencent.bugly.proguard.o r0 = com.tencent.bugly.proguard.C2001o.m2035a()     // Catch:{ Throwable -> 0x0053, all -> 0x008a }
            java.lang.String r1 = "t_cr"
            r3 = 0
            r4 = 0
            r5 = 1
            int r0 = r0.mo19597a(r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x0053, all -> 0x008a }
            java.lang.String r1 = "deleted %s illegle data %d"
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x0053, all -> 0x008a }
            r3 = 0
            java.lang.String r4 = "t_cr"
            r2[r3] = r4     // Catch:{ Throwable -> 0x0053, all -> 0x008a }
            r3 = 1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x0053, all -> 0x008a }
            r2[r3] = r0     // Catch:{ Throwable -> 0x0053, all -> 0x008a }
            com.tencent.bugly.proguard.C2014w.m2118d(r1, r2)     // Catch:{ Throwable -> 0x0053, all -> 0x008a }
        L_0x00c1:
            if (r6 == 0) goto L_0x00c6
            r6.close()
        L_0x00c6:
            r0 = r8
            goto L_0x003d
        L_0x00c9:
            r0 = move-exception
            r6 = r7
            goto L_0x008b
        L_0x00cc:
            r0 = move-exception
            r6 = r7
            goto L_0x008b
        L_0x00cf:
            r0 = move-exception
            goto L_0x0055
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.C1953b.m1795b():java.util.List");
    }

    /* renamed from: c */
    private static void m1797c(List<C1944a> list) {
        if (list != null && list.size() != 0) {
            StringBuilder sb = new StringBuilder();
            for (C1944a aVar : list) {
                sb.append(" or _id").append(" = ").append(aVar.f1313a);
            }
            String sb2 = sb.toString();
            if (sb2.length() > 0) {
                sb2 = sb2.substring(4);
            }
            sb.setLength(0);
            try {
                C2014w.m2117c("deleted %s data %d", "t_cr", Integer.valueOf(C2001o.m2035a().mo19597a("t_cr", sb2, (String[]) null, (C2000n) null, true)));
            } catch (Throwable th) {
                if (!C2014w.m2114a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* renamed from: d */
    private static void m1799d(List<CrashDetailBean> list) {
        if (list != null) {
            try {
                if (list.size() != 0) {
                    StringBuilder sb = new StringBuilder();
                    for (CrashDetailBean crashDetailBean : list) {
                        sb.append(" or _id").append(" = ").append(crashDetailBean.f1287a);
                    }
                    String sb2 = sb.toString();
                    if (sb2.length() > 0) {
                        sb2 = sb2.substring(4);
                    }
                    sb.setLength(0);
                    C2014w.m2117c("deleted %s data %d", "t_cr", Integer.valueOf(C2001o.m2035a().mo19597a("t_cr", sb2, (String[]) null, (C2000n) null, true)));
                }
            } catch (Throwable th) {
                if (!C2014w.m2114a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* renamed from: a */
    private static C1977aj m1790a(Context context, CrashDetailBean crashDetailBean, C1938a aVar) {
        C1976ai aiVar;
        int i;
        boolean z = true;
        if (context == null || crashDetailBean == null || aVar == null) {
            C2014w.m2118d("enExp args == null", new Object[0]);
            return null;
        }
        C1977aj ajVar = new C1977aj();
        switch (crashDetailBean.f1288b) {
            case 0:
                ajVar.f1473a = crashDetailBean.f1296j ? "200" : "100";
                break;
            case 1:
                ajVar.f1473a = crashDetailBean.f1296j ? "201" : "101";
                break;
            case 2:
                ajVar.f1473a = crashDetailBean.f1296j ? "202" : "102";
                break;
            case 3:
                ajVar.f1473a = crashDetailBean.f1296j ? "203" : "103";
                break;
            case 4:
                ajVar.f1473a = crashDetailBean.f1296j ? "204" : "104";
                break;
            case 5:
                ajVar.f1473a = crashDetailBean.f1296j ? "207" : "107";
                break;
            case 6:
                ajVar.f1473a = crashDetailBean.f1296j ? "206" : "106";
                break;
            case 7:
                ajVar.f1473a = crashDetailBean.f1296j ? "208" : "108";
                break;
            default:
                C2014w.m2119e("crash type error! %d", Integer.valueOf(crashDetailBean.f1288b));
                break;
        }
        ajVar.f1474b = crashDetailBean.f1304r;
        ajVar.f1475c = crashDetailBean.f1300n;
        ajVar.f1476d = crashDetailBean.f1301o;
        ajVar.f1477e = crashDetailBean.f1302p;
        ajVar.f1479g = crashDetailBean.f1303q;
        ajVar.f1480h = crashDetailBean.f1311y;
        ajVar.f1481i = crashDetailBean.f1289c;
        ajVar.f1482j = null;
        ajVar.f1484l = crashDetailBean.f1299m;
        ajVar.f1485m = crashDetailBean.f1291e;
        ajVar.f1478f = crashDetailBean.f1265A;
        ajVar.f1492t = C1938a.m1668b().mo19409i();
        ajVar.f1486n = null;
        if (crashDetailBean.f1295i != null && crashDetailBean.f1295i.size() > 0) {
            ajVar.f1487o = new ArrayList<>();
            for (Entry entry : crashDetailBean.f1295i.entrySet()) {
                C1974ag agVar = new C1974ag();
                agVar.f1453a = ((PlugInBean) entry.getValue()).f1152a;
                agVar.f1455c = ((PlugInBean) entry.getValue()).f1154c;
                agVar.f1456d = ((PlugInBean) entry.getValue()).f1153b;
                agVar.f1454b = aVar.mo19418r();
                ajVar.f1487o.add(agVar);
            }
        }
        if (crashDetailBean.f1294h != null && crashDetailBean.f1294h.size() > 0) {
            ajVar.f1488p = new ArrayList<>();
            for (Entry entry2 : crashDetailBean.f1294h.entrySet()) {
                C1974ag agVar2 = new C1974ag();
                agVar2.f1453a = ((PlugInBean) entry2.getValue()).f1152a;
                agVar2.f1455c = ((PlugInBean) entry2.getValue()).f1154c;
                agVar2.f1456d = ((PlugInBean) entry2.getValue()).f1153b;
                ajVar.f1488p.add(agVar2);
            }
        }
        if (crashDetailBean.f1296j) {
            ajVar.f1483k = crashDetailBean.f1306t;
            if (crashDetailBean.f1305s != null && crashDetailBean.f1305s.length() > 0) {
                if (ajVar.f1489q == null) {
                    ajVar.f1489q = new ArrayList<>();
                }
                try {
                    ajVar.f1489q.add(new C1976ai(1, "alltimes.txt", crashDetailBean.f1305s.getBytes("utf-8")));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    ajVar.f1489q = null;
                }
            }
            String str = "crashcount:%d sz:%d";
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(ajVar.f1483k);
            if (ajVar.f1489q != null) {
                i = ajVar.f1489q.size();
            } else {
                i = 0;
            }
            objArr[1] = Integer.valueOf(i);
            C2014w.m2117c(str, objArr);
        }
        if (crashDetailBean.f1309w != null) {
            if (ajVar.f1489q == null) {
                ajVar.f1489q = new ArrayList<>();
            }
            try {
                ajVar.f1489q.add(new C1976ai(1, "log.txt", crashDetailBean.f1309w.getBytes("utf-8")));
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                ajVar.f1489q = null;
            }
        }
        if (!C2018y.m2158a(crashDetailBean.f1284T)) {
            if (ajVar.f1489q == null) {
                ajVar.f1489q = new ArrayList<>();
            }
            try {
                aiVar = new C1976ai(1, "crashInfos.txt", crashDetailBean.f1284T.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e3) {
                e3.printStackTrace();
                aiVar = null;
            }
            if (aiVar != null) {
                C2014w.m2117c("attach crash infos", new Object[0]);
                ajVar.f1489q.add(aiVar);
            }
        }
        if (crashDetailBean.f1285U != null) {
            if (ajVar.f1489q == null) {
                ajVar.f1489q = new ArrayList<>();
            }
            C1976ai a = m1789a("backupRecord.zip", context, crashDetailBean.f1285U);
            if (a != null) {
                C2014w.m2117c("attach backup record", new Object[0]);
                ajVar.f1489q.add(a);
            }
        }
        if (crashDetailBean.f1310x != null && crashDetailBean.f1310x.length > 0) {
            C1976ai aiVar2 = new C1976ai(2, "buglylog.zip", crashDetailBean.f1310x);
            C2014w.m2117c("attach user log", new Object[0]);
            if (ajVar.f1489q == null) {
                ajVar.f1489q = new ArrayList<>();
            }
            ajVar.f1489q.add(aiVar2);
        }
        if (crashDetailBean.f1288b == 3) {
            if (ajVar.f1489q == null) {
                ajVar.f1489q = new ArrayList<>();
            }
            if (crashDetailBean.f1278N != null && crashDetailBean.f1278N.containsKey("BUGLY_CR_01")) {
                try {
                    ajVar.f1489q.add(new C1976ai(1, "anrMessage.txt", ((String) crashDetailBean.f1278N.get("BUGLY_CR_01")).getBytes("utf-8")));
                    C2014w.m2117c("attach anr message", new Object[0]);
                } catch (UnsupportedEncodingException e4) {
                    e4.printStackTrace();
                    ajVar.f1489q = null;
                }
                crashDetailBean.f1278N.remove("BUGLY_CR_01");
            }
            if (crashDetailBean.f1308v != null) {
                C1976ai a2 = m1789a("trace.zip", context, crashDetailBean.f1308v);
                if (a2 != null) {
                    C2014w.m2117c("attach traces", new Object[0]);
                    ajVar.f1489q.add(a2);
                }
            }
        }
        if (crashDetailBean.f1288b == 1) {
            if (ajVar.f1489q == null) {
                ajVar.f1489q = new ArrayList<>();
            }
            if (crashDetailBean.f1308v != null) {
                C1976ai a3 = m1789a("tomb.zip", context, crashDetailBean.f1308v);
                if (a3 != null) {
                    C2014w.m2117c("attach tombs", new Object[0]);
                    ajVar.f1489q.add(a3);
                }
            }
        }
        if (aVar.f1157B != null && !aVar.f1157B.isEmpty()) {
            if (ajVar.f1489q == null) {
                ajVar.f1489q = new ArrayList<>();
            }
            StringBuilder sb = new StringBuilder();
            for (String append : aVar.f1157B) {
                sb.append(append);
            }
            try {
                ajVar.f1489q.add(new C1976ai(1, "martianlog.txt", sb.toString().getBytes("utf-8")));
                C2014w.m2117c("attach pageTracingList", new Object[0]);
            } catch (UnsupportedEncodingException e5) {
                e5.printStackTrace();
            }
        }
        if (crashDetailBean.f1283S != null && crashDetailBean.f1283S.length > 0) {
            if (ajVar.f1489q == null) {
                ajVar.f1489q = new ArrayList<>();
            }
            ajVar.f1489q.add(new C1976ai(1, "userExtraByteData", crashDetailBean.f1283S));
            C2014w.m2117c("attach extraData", new Object[0]);
        }
        ajVar.f1490r = new HashMap();
        ajVar.f1490r.put("A9", crashDetailBean.f1266B);
        ajVar.f1490r.put("A11", crashDetailBean.f1267C);
        ajVar.f1490r.put("A10", crashDetailBean.f1268D);
        ajVar.f1490r.put("A23", crashDetailBean.f1292f);
        ajVar.f1490r.put("A7", aVar.f1204f);
        ajVar.f1490r.put("A6", aVar.mo19419s());
        ajVar.f1490r.put("A5", aVar.mo19418r());
        ajVar.f1490r.put("A22", aVar.mo19408h());
        ajVar.f1490r.put("A2", crashDetailBean.f1270F);
        ajVar.f1490r.put("A1", crashDetailBean.f1269E);
        ajVar.f1490r.put("A24", aVar.f1206h);
        ajVar.f1490r.put("A17", crashDetailBean.f1271G);
        ajVar.f1490r.put("A3", aVar.mo19411k());
        ajVar.f1490r.put("A16", aVar.mo19413m());
        ajVar.f1490r.put("A25", aVar.mo19414n());
        ajVar.f1490r.put("A14", aVar.mo19412l());
        ajVar.f1490r.put("A15", aVar.mo19423w());
        ajVar.f1490r.put("A13", aVar.mo19424x());
        ajVar.f1490r.put("A34", crashDetailBean.f1312z);
        if (aVar.f1222x != null) {
            ajVar.f1490r.put("productIdentify", aVar.f1222x);
        }
        try {
            ajVar.f1490r.put("A26", URLEncoder.encode(crashDetailBean.f1272H, "utf-8"));
        } catch (UnsupportedEncodingException e6) {
            e6.printStackTrace();
        }
        if (crashDetailBean.f1288b == 1) {
            ajVar.f1490r.put("A27", crashDetailBean.f1274J);
            ajVar.f1490r.put("A28", crashDetailBean.f1273I);
            ajVar.f1490r.put("A29", crashDetailBean.f1297k);
        }
        ajVar.f1490r.put("A30", crashDetailBean.f1275K);
        ajVar.f1490r.put("A18", crashDetailBean.f1276L);
        ajVar.f1490r.put("A36", (!crashDetailBean.f1277M));
        ajVar.f1490r.put("F02", aVar.f1215q);
        ajVar.f1490r.put("F03", aVar.f1216r);
        ajVar.f1490r.put("F04", aVar.mo19402e());
        ajVar.f1490r.put("F05", aVar.f1217s);
        ajVar.f1490r.put("F06", aVar.f1214p);
        ajVar.f1490r.put("F08", aVar.f1220v);
        ajVar.f1490r.put("F09", aVar.f1221w);
        ajVar.f1490r.put("F10", aVar.f1218t);
        if (crashDetailBean.f1279O >= 0) {
            ajVar.f1490r.put("C01", crashDetailBean.f1279O);
        }
        if (crashDetailBean.f1280P >= 0) {
            ajVar.f1490r.put("C02", crashDetailBean.f1280P);
        }
        if (crashDetailBean.f1281Q != null && crashDetailBean.f1281Q.size() > 0) {
            for (Entry entry3 : crashDetailBean.f1281Q.entrySet()) {
                ajVar.f1490r.put("C03_" + ((String) entry3.getKey()), entry3.getValue());
            }
        }
        if (crashDetailBean.f1282R != null && crashDetailBean.f1282R.size() > 0) {
            for (Entry entry4 : crashDetailBean.f1282R.entrySet()) {
                ajVar.f1490r.put("C04_" + ((String) entry4.getKey()), entry4.getValue());
            }
        }
        ajVar.f1491s = null;
        if (crashDetailBean.f1278N != null && crashDetailBean.f1278N.size() > 0) {
            ajVar.f1491s = crashDetailBean.f1278N;
            C2014w.m2113a("setted message size %d", Integer.valueOf(ajVar.f1491s.size()));
        }
        String str2 = "%s rid:%s sess:%s ls:%ds isR:%b isF:%b isM:%b isN:%b mc:%d ,%s ,isUp:%b ,vm:%d";
        Object[] objArr2 = new Object[12];
        objArr2[0] = crashDetailBean.f1300n;
        objArr2[1] = crashDetailBean.f1289c;
        objArr2[2] = aVar.mo19402e();
        objArr2[3] = Long.valueOf((crashDetailBean.f1304r - crashDetailBean.f1276L) / 1000);
        objArr2[4] = Boolean.valueOf(crashDetailBean.f1297k);
        objArr2[5] = Boolean.valueOf(crashDetailBean.f1277M);
        objArr2[6] = Boolean.valueOf(crashDetailBean.f1296j);
        if (crashDetailBean.f1288b != 1) {
            z = false;
        }
        objArr2[7] = Boolean.valueOf(z);
        objArr2[8] = Integer.valueOf(crashDetailBean.f1306t);
        objArr2[9] = crashDetailBean.f1305s;
        objArr2[10] = Boolean.valueOf(crashDetailBean.f1290d);
        objArr2[11] = Integer.valueOf(ajVar.f1490r.size());
        C2014w.m2117c(str2, objArr2);
        return ajVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x005b A[Catch:{ all -> 0x00e2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0060 A[SYNTHETIC, Splitter:B:22:0x0060] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00c3 A[SYNTHETIC, Splitter:B:46:0x00c3] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:61:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.tencent.bugly.proguard.C1976ai m1789a(java.lang.String r9, android.content.Context r10, java.lang.String r11) {
        /*
            r2 = 1
            r0 = 0
            r8 = 0
            if (r11 == 0) goto L_0x0007
            if (r10 != 0) goto L_0x000f
        L_0x0007:
            java.lang.String r1 = "rqdp{  createZipAttachment sourcePath == null || context == null ,pls check}"
            java.lang.Object[] r2 = new java.lang.Object[r8]
            com.tencent.bugly.proguard.C2014w.m2118d(r1, r2)
        L_0x000e:
            return r0
        L_0x000f:
            java.lang.String r1 = "zip %s"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r8] = r11
            com.tencent.bugly.proguard.C2014w.m2117c(r1, r2)
            java.io.File r1 = new java.io.File
            r1.<init>(r11)
            java.io.File r3 = new java.io.File
            java.io.File r2 = r10.getCacheDir()
            r3.<init>(r2, r9)
            r2 = 5000(0x1388, float:7.006E-42)
            boolean r1 = com.tencent.bugly.proguard.C2018y.m2157a(r1, r3, r2)
            if (r1 != 0) goto L_0x0038
            java.lang.String r1 = "zip fail!"
            java.lang.Object[] r2 = new java.lang.Object[r8]
            com.tencent.bugly.proguard.C2014w.m2118d(r1, r2)
            goto L_0x000e
        L_0x0038:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream
            r1.<init>()
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x00e4, all -> 0x00be }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00e4, all -> 0x00be }
            r4 = 1000(0x3e8, float:1.401E-42)
            byte[] r4 = new byte[r4]     // Catch:{ Throwable -> 0x0054 }
        L_0x0046:
            int r5 = r2.read(r4)     // Catch:{ Throwable -> 0x0054 }
            if (r5 <= 0) goto L_0x0074
            r6 = 0
            r1.write(r4, r6, r5)     // Catch:{ Throwable -> 0x0054 }
            r1.flush()     // Catch:{ Throwable -> 0x0054 }
            goto L_0x0046
        L_0x0054:
            r1 = move-exception
        L_0x0055:
            boolean r4 = com.tencent.bugly.proguard.C2014w.m2114a(r1)     // Catch:{ all -> 0x00e2 }
            if (r4 != 0) goto L_0x005e
            r1.printStackTrace()     // Catch:{ all -> 0x00e2 }
        L_0x005e:
            if (r2 == 0) goto L_0x0063
            r2.close()     // Catch:{ IOException -> 0x00b3 }
        L_0x0063:
            boolean r1 = r3.exists()
            if (r1 == 0) goto L_0x000e
            java.lang.String r1 = "del tmp"
            java.lang.Object[] r2 = new java.lang.Object[r8]
            com.tencent.bugly.proguard.C2014w.m2117c(r1, r2)
            r3.delete()
            goto L_0x000e
        L_0x0074:
            byte[] r4 = r1.toByteArray()     // Catch:{ Throwable -> 0x0054 }
            java.lang.String r1 = "read bytes :%d"
            r5 = 1
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x0054 }
            r6 = 0
            int r7 = r4.length     // Catch:{ Throwable -> 0x0054 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ Throwable -> 0x0054 }
            r5[r6] = r7     // Catch:{ Throwable -> 0x0054 }
            com.tencent.bugly.proguard.C2014w.m2117c(r1, r5)     // Catch:{ Throwable -> 0x0054 }
            com.tencent.bugly.proguard.ai r1 = new com.tencent.bugly.proguard.ai     // Catch:{ Throwable -> 0x0054 }
            r5 = 2
            java.lang.String r6 = r3.getName()     // Catch:{ Throwable -> 0x0054 }
            r1.<init>(r5, r6, r4)     // Catch:{ Throwable -> 0x0054 }
            r2.close()     // Catch:{ IOException -> 0x00a8 }
        L_0x0095:
            boolean r0 = r3.exists()
            if (r0 == 0) goto L_0x00a5
            java.lang.String r0 = "del tmp"
            java.lang.Object[] r2 = new java.lang.Object[r8]
            com.tencent.bugly.proguard.C2014w.m2117c(r0, r2)
            r3.delete()
        L_0x00a5:
            r0 = r1
            goto L_0x000e
        L_0x00a8:
            r0 = move-exception
            boolean r2 = com.tencent.bugly.proguard.C2014w.m2114a(r0)
            if (r2 != 0) goto L_0x0095
            r0.printStackTrace()
            goto L_0x0095
        L_0x00b3:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.proguard.C2014w.m2114a(r1)
            if (r2 != 0) goto L_0x0063
            r1.printStackTrace()
            goto L_0x0063
        L_0x00be:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x00c1:
            if (r2 == 0) goto L_0x00c6
            r2.close()     // Catch:{ IOException -> 0x00d7 }
        L_0x00c6:
            boolean r1 = r3.exists()
            if (r1 == 0) goto L_0x00d6
            java.lang.String r1 = "del tmp"
            java.lang.Object[] r2 = new java.lang.Object[r8]
            com.tencent.bugly.proguard.C2014w.m2117c(r1, r2)
            r3.delete()
        L_0x00d6:
            throw r0
        L_0x00d7:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.proguard.C2014w.m2114a(r1)
            if (r2 != 0) goto L_0x00c6
            r1.printStackTrace()
            goto L_0x00c6
        L_0x00e2:
            r0 = move-exception
            goto L_0x00c1
        L_0x00e4:
            r1 = move-exception
            r2 = r0
            goto L_0x0055
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.C1953b.m1789a(java.lang.String, android.content.Context, java.lang.String):com.tencent.bugly.proguard.ai");
    }

    /* renamed from: a */
    public static void m1792a(String str, String str2, String str3, Thread thread, String str4, CrashDetailBean crashDetailBean) {
        C1938a b = C1938a.m1668b();
        if (b != null) {
            C2014w.m2119e("#++++++++++Record By Bugly++++++++++#", new Object[0]);
            C2014w.m2119e("# You can use Bugly(http:\\\\bugly.qq.com) to get more Crash Detail!", new Object[0]);
            C2014w.m2119e("# PKG NAME: %s", b.f1201c);
            C2014w.m2119e("# APP VER: %s", b.f1208j);
            C2014w.m2119e("# LAUNCH TIME: %s", C2018y.m2148a(new Date(C1938a.m1668b().f1181a)));
            C2014w.m2119e("# CRASH TYPE: %s", str);
            C2014w.m2119e("# CRASH TIME: %s", str2);
            C2014w.m2119e("# CRASH PROCESS: %s", str3);
            if (thread != null) {
                C2014w.m2119e("# CRASH THREAD: %s", thread.getName());
            }
            if (crashDetailBean != null) {
                C2014w.m2119e("# REPORT ID: %s", crashDetailBean.f1289c);
                String str5 = "# CRASH DEVICE: %s %s";
                Object[] objArr = new Object[2];
                objArr[0] = b.f1205g;
                objArr[1] = b.mo19424x().booleanValue() ? "ROOTED" : "UNROOT";
                C2014w.m2119e(str5, objArr);
                C2014w.m2119e("# RUNTIME AVAIL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.f1266B), Long.valueOf(crashDetailBean.f1267C), Long.valueOf(crashDetailBean.f1268D));
                C2014w.m2119e("# RUNTIME TOTAL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.f1269E), Long.valueOf(crashDetailBean.f1270F), Long.valueOf(crashDetailBean.f1271G));
                if (!C2018y.m2158a(crashDetailBean.f1274J)) {
                    C2014w.m2119e("# EXCEPTION FIRED BY %s %s", crashDetailBean.f1274J, crashDetailBean.f1273I);
                } else if (crashDetailBean.f1288b == 3) {
                    String str6 = "# EXCEPTION ANR MESSAGE:\n %s";
                    Object[] objArr2 = new Object[1];
                    objArr2[0] = crashDetailBean.f1278N == null ? "null" : ((String) crashDetailBean.f1278N.get("BUGLY_CR_01"));
                    C2014w.m2119e(str6, objArr2);
                }
            }
            if (!C2018y.m2158a(str4)) {
                C2014w.m2119e("# CRASH STACK: ", new Object[0]);
                C2014w.m2119e(str4, new Object[0]);
            }
            C2014w.m2119e("#++++++++++++++++++++++++++++++++++++++++++#", new Object[0]);
        }
    }
}
