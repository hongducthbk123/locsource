package com.tencent.bugly.crashreport.common.strategy;

import android.content.Context;
import android.os.Parcelable;
import com.tencent.bugly.C1911a;
import com.tencent.bugly.crashreport.biz.C1934b;
import com.tencent.bugly.crashreport.common.info.C1938a;
import com.tencent.bugly.proguard.C1982ao;
import com.tencent.bugly.proguard.C2000n;
import com.tencent.bugly.proguard.C2001o;
import com.tencent.bugly.proguard.C2004q;
import com.tencent.bugly.proguard.C2012v;
import com.tencent.bugly.proguard.C2014w;
import com.tencent.bugly.proguard.C2018y;
import java.util.List;
import java.util.Map;

/* renamed from: com.tencent.bugly.crashreport.common.strategy.a */
/* compiled from: BUGLY */
public final class C1941a {

    /* renamed from: a */
    public static int f1252a = 1000;

    /* renamed from: b */
    private static C1941a f1253b = null;

    /* renamed from: c */
    private final List<C1911a> f1254c;

    /* renamed from: d */
    private final C2012v f1255d;

    /* renamed from: e */
    private final StrategyBean f1256e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public StrategyBean f1257f = null;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public Context f1258g;

    private C1941a(Context context, List<C1911a> list) {
        this.f1258g = context;
        this.f1256e = new StrategyBean();
        this.f1254c = list;
        this.f1255d = C2012v.m2106a();
    }

    /* renamed from: a */
    public static synchronized C1941a m1753a(Context context, List<C1911a> list) {
        C1941a aVar;
        synchronized (C1941a.class) {
            if (f1253b == null) {
                f1253b = new C1941a(context, list);
            }
            aVar = f1253b;
        }
        return aVar;
    }

    /* renamed from: a */
    public final void mo19431a(long j) {
        this.f1255d.mo19637a(new Thread() {
            public final void run() {
                try {
                    Map a = C2001o.m2035a().mo19601a(C1941a.f1252a, (C2000n) null, true);
                    if (a != null) {
                        byte[] bArr = (byte[]) a.get("key_imei");
                        byte[] bArr2 = (byte[]) a.get("key_ip");
                        if (bArr != null) {
                            C1938a.m1667a(C1941a.this.f1258g).mo19403e(new String(bArr));
                        }
                        if (bArr2 != null) {
                            C1938a.m1667a(C1941a.this.f1258g).mo19401d(new String(bArr2));
                        }
                    }
                    C1941a aVar = C1941a.this;
                    C1941a.this.f1257f = C1941a.m1755d();
                } catch (Throwable th) {
                    if (!C2014w.m2114a(th)) {
                        th.printStackTrace();
                    }
                }
                C1941a.this.mo19432a(C1941a.this.f1257f, false);
            }
        }, j);
    }

    /* renamed from: a */
    public static synchronized C1941a m1752a() {
        C1941a aVar;
        synchronized (C1941a.class) {
            aVar = f1253b;
        }
        return aVar;
    }

    /* renamed from: b */
    public final synchronized boolean mo19434b() {
        return this.f1257f != null;
    }

    /* renamed from: c */
    public final StrategyBean mo19435c() {
        if (this.f1257f != null) {
            return this.f1257f;
        }
        return this.f1256e;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo19432a(StrategyBean strategyBean, boolean z) {
        C2014w.m2117c("[Strategy] Notify %s", C1934b.class.getName());
        C1934b.m1640a(strategyBean, z);
        for (C1911a aVar : this.f1254c) {
            try {
                C2014w.m2117c("[Strategy] Notify %s", aVar.getClass().getName());
                aVar.onServerStrategyChanged(strategyBean);
            } catch (Throwable th) {
                if (!C2014w.m2114a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* renamed from: a */
    public final void mo19433a(C1982ao aoVar) {
        if (aoVar != null) {
            if (this.f1257f == null || aoVar.f1544h != this.f1257f.f1242p) {
                StrategyBean strategyBean = new StrategyBean();
                strategyBean.f1233g = aoVar.f1537a;
                strategyBean.f1235i = aoVar.f1539c;
                strategyBean.f1234h = aoVar.f1538b;
                if (C2018y.m2179c(aoVar.f1540d)) {
                    C2014w.m2117c("[Strategy] Upload url changes to %s", aoVar.f1540d);
                    strategyBean.f1244r = aoVar.f1540d;
                }
                if (C2018y.m2179c(aoVar.f1541e)) {
                    C2014w.m2117c("[Strategy] Exception upload url changes to %s", aoVar.f1541e);
                    strategyBean.f1245s = aoVar.f1541e;
                }
                if (aoVar.f1542f != null && !C2018y.m2158a(aoVar.f1542f.f1532a)) {
                    strategyBean.f1247u = aoVar.f1542f.f1532a;
                }
                if (aoVar.f1544h != 0) {
                    strategyBean.f1242p = aoVar.f1544h;
                }
                if (aoVar.f1543g != null && aoVar.f1543g.size() > 0) {
                    strategyBean.f1248v = aoVar.f1543g;
                    String str = (String) aoVar.f1543g.get("B11");
                    if (str == null || !str.equals("1")) {
                        strategyBean.f1236j = false;
                    } else {
                        strategyBean.f1236j = true;
                    }
                    String str2 = (String) aoVar.f1543g.get("B3");
                    if (str2 != null) {
                        strategyBean.f1251y = Long.valueOf(str2).longValue();
                    }
                    strategyBean.f1243q = (long) aoVar.f1545i;
                    strategyBean.f1250x = (long) aoVar.f1545i;
                    String str3 = (String) aoVar.f1543g.get("B27");
                    if (str3 != null && str3.length() > 0) {
                        try {
                            int parseInt = Integer.parseInt(str3);
                            if (parseInt > 0) {
                                strategyBean.f1249w = parseInt;
                            }
                        } catch (Exception e) {
                            if (!C2014w.m2114a(e)) {
                                e.printStackTrace();
                            }
                        }
                    }
                    String str4 = (String) aoVar.f1543g.get("B25");
                    if (str4 == null || !str4.equals("1")) {
                        strategyBean.f1238l = false;
                    } else {
                        strategyBean.f1238l = true;
                    }
                }
                C2014w.m2113a("[Strategy] enableCrashReport:%b, enableQuery:%b, enableUserInfo:%b, enableAnr:%b, enableBlock:%b, enableSession:%b, enableSessionTimer:%b, sessionOverTime:%d, enableCocos:%b, strategyLastUpdateTime:%d", Boolean.valueOf(strategyBean.f1233g), Boolean.valueOf(strategyBean.f1235i), Boolean.valueOf(strategyBean.f1234h), Boolean.valueOf(strategyBean.f1236j), Boolean.valueOf(strategyBean.f1237k), Boolean.valueOf(strategyBean.f1240n), Boolean.valueOf(strategyBean.f1241o), Long.valueOf(strategyBean.f1243q), Boolean.valueOf(strategyBean.f1238l), Long.valueOf(strategyBean.f1242p));
                this.f1257f = strategyBean;
                C2001o.m2035a().mo19606b(2);
                C2004q qVar = new C2004q();
                qVar.f1634b = 2;
                qVar.f1633a = strategyBean.f1231e;
                qVar.f1637e = strategyBean.f1232f;
                qVar.f1639g = C2018y.m2161a((Parcelable) strategyBean);
                C2001o.m2035a().mo19605a(qVar);
                mo19432a(strategyBean, true);
            }
        }
    }

    /* renamed from: d */
    public static StrategyBean m1755d() {
        List a = C2001o.m2035a().mo19600a(2);
        if (a != null && a.size() > 0) {
            C2004q qVar = (C2004q) a.get(0);
            if (qVar.f1639g != null) {
                return (StrategyBean) C2018y.m2142a(qVar.f1639g, StrategyBean.CREATOR);
            }
        }
        return null;
    }
}
