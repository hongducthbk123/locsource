package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import com.google.android.vending.expansion.downloader.Constants;
import com.tencent.bugly.BuglyStrategy.C1910a;
import com.tencent.bugly.crashreport.common.info.C1938a;
import com.tencent.bugly.crashreport.common.strategy.C1941a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.anr.C1950b;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.proguard.C2000n;
import com.tencent.bugly.proguard.C2001o;
import com.tencent.bugly.proguard.C2004q;
import com.tencent.bugly.proguard.C2007t;
import com.tencent.bugly.proguard.C2012v;
import com.tencent.bugly.proguard.C2014w;
import com.tencent.bugly.proguard.C2018y;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: com.tencent.bugly.crashreport.crash.c */
/* compiled from: BUGLY */
public final class C1955c {

    /* renamed from: a */
    public static int f1355a = 0;

    /* renamed from: b */
    public static boolean f1356b = false;

    /* renamed from: c */
    public static boolean f1357c = true;

    /* renamed from: d */
    public static int f1358d = 20000;

    /* renamed from: e */
    public static int f1359e = 20000;

    /* renamed from: f */
    public static long f1360f = 604800000;

    /* renamed from: g */
    public static String f1361g = null;

    /* renamed from: h */
    public static boolean f1362h = false;

    /* renamed from: i */
    public static String f1363i = null;

    /* renamed from: j */
    public static int f1364j = 5000;

    /* renamed from: k */
    public static boolean f1365k = true;

    /* renamed from: l */
    public static String f1366l = null;

    /* renamed from: m */
    public static String f1367m = null;

    /* renamed from: p */
    private static C1955c f1368p;

    /* renamed from: n */
    public final C1953b f1369n;
    /* access modifiers changed from: private */

    /* renamed from: o */
    public final Context f1370o;
    /* access modifiers changed from: private */

    /* renamed from: q */
    public final C1961e f1371q;

    /* renamed from: r */
    private final NativeCrashHandler f1372r;

    /* renamed from: s */
    private C1941a f1373s = C1941a.m1752a();

    /* renamed from: t */
    private C2012v f1374t;

    /* renamed from: u */
    private final C1950b f1375u;

    /* renamed from: v */
    private Boolean f1376v;

    private C1955c(int i, Context context, C2012v vVar, boolean z, C1910a aVar, C2000n nVar, String str) {
        f1355a = i;
        Context a = C2018y.m2138a(context);
        this.f1370o = a;
        this.f1374t = vVar;
        this.f1369n = new C1953b(i, a, C2007t.m2069a(), C2001o.m2035a(), this.f1373s, aVar, nVar);
        C1938a a2 = C1938a.m1667a(a);
        this.f1371q = new C1961e(a, this.f1369n, this.f1373s, a2);
        this.f1372r = NativeCrashHandler.getInstance(a, a2, this.f1369n, this.f1373s, vVar, z, str);
        a2.f1158C = this.f1372r;
        this.f1375u = new C1950b(a, this.f1373s, a2, vVar, this.f1369n);
    }

    /* renamed from: a */
    public static synchronized void m1810a(int i, Context context, boolean z, C1910a aVar, C2000n nVar, String str) {
        synchronized (C1955c.class) {
            if (f1368p == null) {
                f1368p = new C1955c(1004, context, C2012v.m2106a(), z, aVar, null, null);
            }
        }
    }

    /* renamed from: a */
    public static synchronized C1955c m1808a() {
        C1955c cVar;
        synchronized (C1955c.class) {
            cVar = f1368p;
        }
        return cVar;
    }

    /* renamed from: a */
    public final void mo19466a(StrategyBean strategyBean) {
        this.f1371q.mo19485a(strategyBean);
        this.f1372r.onStrategyChanged(strategyBean);
        this.f1375u.mo19451a(strategyBean);
        C2012v.m2106a().mo19637a(new Thread() {
            public final void run() {
                List list;
                if (C2018y.m2156a(C1955c.this.f1370o, "local_crash_lock", 10000)) {
                    List a = C1955c.this.f1369n.mo19458a();
                    if (a != null && a.size() > 0) {
                        int size = a.size();
                        if (((long) size) > 100) {
                            list = new ArrayList();
                            Collections.sort(a);
                            for (int i = 0; ((long) i) < 100; i++) {
                                list.add(a.get((size - 1) - i));
                            }
                        } else {
                            list = a;
                        }
                        C1955c.this.f1369n.mo19460a(list, 0, false, false, false);
                    }
                    C2018y.m2173b(C1955c.this.f1370o, "local_crash_lock");
                }
            }
        }, 0);
    }

    /* renamed from: b */
    public final boolean mo19469b() {
        Boolean bool = this.f1376v;
        if (bool != null) {
            return bool.booleanValue();
        }
        String str = C1938a.m1668b().f1202d;
        List<C2004q> a = C2001o.m2035a().mo19600a(1);
        ArrayList arrayList = new ArrayList();
        if (a == null || a.size() <= 0) {
            this.f1376v = Boolean.valueOf(false);
            return false;
        }
        for (C2004q qVar : a) {
            if (str.equals(qVar.f1635c)) {
                this.f1376v = Boolean.valueOf(true);
                arrayList.add(qVar);
            }
        }
        if (arrayList.size() > 0) {
            C2001o.m2035a().mo19602a((List<C2004q>) arrayList);
        }
        return true;
    }

    /* renamed from: c */
    public final synchronized void mo19470c() {
        this.f1371q.mo19484a();
        this.f1372r.setUserOpened(true);
        this.f1375u.mo19453a(true);
    }

    /* renamed from: d */
    public final synchronized void mo19471d() {
        this.f1371q.mo19487b();
        this.f1372r.setUserOpened(false);
        this.f1375u.mo19453a(false);
    }

    /* renamed from: e */
    public final void mo19472e() {
        this.f1371q.mo19484a();
    }

    /* renamed from: f */
    public final void mo19473f() {
        this.f1372r.setUserOpened(false);
    }

    /* renamed from: g */
    public final void mo19474g() {
        this.f1372r.setUserOpened(true);
    }

    /* renamed from: h */
    public final void mo19475h() {
        this.f1375u.mo19453a(true);
    }

    /* renamed from: i */
    public final void mo19476i() {
        this.f1375u.mo19453a(false);
    }

    /* renamed from: j */
    public final synchronized void mo19477j() {
        this.f1372r.testNativeCrash();
    }

    /* renamed from: k */
    public final synchronized void mo19478k() {
        int i = 0;
        synchronized (this) {
            C1950b bVar = this.f1375u;
            while (true) {
                int i2 = i + 1;
                if (i >= 30) {
                    break;
                }
                try {
                    C2014w.m2113a("try main sleep for make a test anr! try:%d/30 , kill it if you don't want to wait!", Integer.valueOf(i2));
                    C2018y.m2170b((long) Constants.ACTIVE_THREAD_WATCHDOG);
                    i = i2;
                } catch (Throwable th) {
                    if (!C2014w.m2114a(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
    }

    /* renamed from: l */
    public final boolean mo19479l() {
        return this.f1375u.mo19454a();
    }

    /* renamed from: a */
    public final void mo19468a(Thread thread, Throwable th, boolean z, String str, byte[] bArr, boolean z2) {
        final Thread thread2 = thread;
        final Throwable th2 = th;
        final boolean z3 = z2;
        this.f1374t.mo19636a(new Runnable(false, null, null) {
            public final void run() {
                try {
                    C2014w.m2117c("post a throwable %b", Boolean.valueOf(false));
                    C1955c.this.f1371q.mo19486a(thread2, th2, false, null, null);
                    if (z3) {
                        C2014w.m2113a("clear user datas", new Object[0]);
                        C1938a.m1667a(C1955c.this.f1370o).mo19375A();
                    }
                } catch (Throwable th) {
                    if (!C2014w.m2116b(th)) {
                        th.printStackTrace();
                    }
                    C2014w.m2119e("java catch error: %s", th2.toString());
                }
            }
        });
    }

    /* renamed from: a */
    public final void mo19467a(CrashDetailBean crashDetailBean) {
        this.f1369n.mo19464c(crashDetailBean);
    }

    /* renamed from: a */
    public final void mo19465a(long j) {
        C2012v.m2106a().mo19637a(new Thread() {
            public final void run() {
                List list;
                if (C2018y.m2156a(C1955c.this.f1370o, "local_crash_lock", 10000)) {
                    List a = C1955c.this.f1369n.mo19458a();
                    if (a != null && a.size() > 0) {
                        int size = a.size();
                        if (((long) size) > 100) {
                            list = new ArrayList();
                            Collections.sort(a);
                            for (int i = 0; ((long) i) < 100; i++) {
                                list.add(a.get((size - 1) - i));
                            }
                        } else {
                            list = a;
                        }
                        C1955c.this.f1369n.mo19460a(list, 0, false, false, false);
                    }
                    C2018y.m2173b(C1955c.this.f1370o, "local_crash_lock");
                }
            }
        }, 0);
    }
}
