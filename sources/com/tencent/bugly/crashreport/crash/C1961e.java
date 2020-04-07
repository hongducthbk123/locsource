package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import android.os.Process;
import com.tencent.bugly.crashreport.common.info.C1938a;
import com.tencent.bugly.crashreport.common.info.C1939b;
import com.tencent.bugly.crashreport.common.strategy.C1941a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.C2014w;
import com.tencent.bugly.proguard.C2015x;
import com.tencent.bugly.proguard.C2018y;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashMap;
import org.apache.commons.p052io.IOUtils;

/* renamed from: com.tencent.bugly.crashreport.crash.e */
/* compiled from: BUGLY */
public final class C1961e implements UncaughtExceptionHandler {

    /* renamed from: h */
    private static String f1397h = null;

    /* renamed from: i */
    private static final Object f1398i = new Object();

    /* renamed from: a */
    private Context f1399a;

    /* renamed from: b */
    private C1953b f1400b;

    /* renamed from: c */
    private C1941a f1401c;

    /* renamed from: d */
    private C1938a f1402d;

    /* renamed from: e */
    private UncaughtExceptionHandler f1403e;

    /* renamed from: f */
    private UncaughtExceptionHandler f1404f;

    /* renamed from: g */
    private boolean f1405g = false;

    /* renamed from: j */
    private int f1406j;

    public C1961e(Context context, C1953b bVar, C1941a aVar, C1938a aVar2) {
        this.f1399a = context;
        this.f1400b = bVar;
        this.f1401c = aVar;
        this.f1402d = aVar2;
    }

    /* renamed from: a */
    public final synchronized void mo19484a() {
        if (this.f1406j >= 10) {
            C2014w.m2113a("java crash handler over %d, no need set.", Integer.valueOf(10));
        } else {
            UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            if (defaultUncaughtExceptionHandler != null && !getClass().getName().equals(defaultUncaughtExceptionHandler.getClass().getName())) {
                if ("com.android.internal.os.RuntimeInit$UncaughtHandler".equals(defaultUncaughtExceptionHandler.getClass().getName())) {
                    C2014w.m2113a("backup system java handler: %s", defaultUncaughtExceptionHandler.toString());
                    this.f1404f = defaultUncaughtExceptionHandler;
                    this.f1403e = defaultUncaughtExceptionHandler;
                } else {
                    C2014w.m2113a("backup java handler: %s", defaultUncaughtExceptionHandler.toString());
                    this.f1403e = defaultUncaughtExceptionHandler;
                }
                m1833a(defaultUncaughtExceptionHandler);
                Thread.setDefaultUncaughtExceptionHandler(this);
                this.f1405g = true;
                this.f1406j++;
                C2014w.m2113a("registered java monitor: %s", toString());
            }
        }
    }

    /* renamed from: b */
    public final synchronized void mo19487b() {
        this.f1405g = false;
        C2014w.m2113a("close java monitor!", new Object[0]);
        if (Thread.getDefaultUncaughtExceptionHandler().getClass().getName().contains("bugly")) {
            C2014w.m2113a("Java monitor to unregister: %s", toString());
            Thread.setDefaultUncaughtExceptionHandler(this.f1403e);
            this.f1406j--;
        }
    }

    /* renamed from: a */
    private synchronized void m1833a(UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.f1403e = uncaughtExceptionHandler;
    }

    /* renamed from: b */
    private CrashDetailBean m1835b(Thread thread, Throwable th, boolean z, String str, byte[] bArr) {
        String a;
        if (th == null) {
            C2014w.m2118d("We can do nothing with a null throwable.", new Object[0]);
            return null;
        }
        boolean l = C1955c.m1808a().mo19479l();
        String str2 = (!l || !z) ? "" : " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]";
        if (l && z) {
            C2014w.m2119e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
        }
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.f1266B = C1939b.m1735g();
        crashDetailBean.f1267C = C1939b.m1731e();
        crashDetailBean.f1268D = C1939b.m1739i();
        crashDetailBean.f1269E = this.f1402d.mo19416p();
        crashDetailBean.f1270F = this.f1402d.mo19415o();
        crashDetailBean.f1271G = this.f1402d.mo19417q();
        crashDetailBean.f1309w = C2018y.m2145a(this.f1399a, C1955c.f1358d, (String) null);
        crashDetailBean.f1310x = C2015x.m2126a(z);
        String str3 = "user log size:%d";
        Object[] objArr = new Object[1];
        objArr[0] = Integer.valueOf(crashDetailBean.f1310x == null ? 0 : crashDetailBean.f1310x.length);
        C2014w.m2113a(str3, objArr);
        crashDetailBean.f1288b = z ? 0 : 2;
        crashDetailBean.f1291e = this.f1402d.mo19408h();
        crashDetailBean.f1292f = this.f1402d.f1208j;
        crashDetailBean.f1293g = this.f1402d.mo19423w();
        crashDetailBean.f1299m = this.f1402d.mo19406g();
        String name = th.getClass().getName();
        String b = m1836b(th, 1000);
        if (b == null) {
            b = "";
        }
        String str4 = "stack frame :%d, has cause %b";
        Object[] objArr2 = new Object[2];
        objArr2[0] = Integer.valueOf(th.getStackTrace().length);
        objArr2[1] = Boolean.valueOf(th.getCause() != null);
        C2014w.m2119e(str4, objArr2);
        String str5 = "";
        if (th.getStackTrace().length > 0) {
            str5 = th.getStackTrace()[0].toString();
        }
        Throwable th2 = th;
        while (th2 != null && th2.getCause() != null) {
            th2 = th2.getCause();
        }
        if (th2 == null || th2 == th) {
            crashDetailBean.f1300n = name;
            crashDetailBean.f1301o = b + str2;
            if (crashDetailBean.f1301o == null) {
                crashDetailBean.f1301o = "";
            }
            crashDetailBean.f1302p = str5;
            a = m1832a(th, C1955c.f1359e);
            crashDetailBean.f1303q = a;
        } else {
            crashDetailBean.f1300n = th2.getClass().getName();
            crashDetailBean.f1301o = m1836b(th2, 1000);
            if (crashDetailBean.f1301o == null) {
                crashDetailBean.f1301o = "";
            }
            crashDetailBean.f1302p = th2.getStackTrace()[0].toString();
            StringBuilder sb = new StringBuilder();
            sb.append(name).append(":").append(b).append(IOUtils.LINE_SEPARATOR_UNIX);
            sb.append(str5);
            sb.append("\n......");
            sb.append("\nCaused by:\n");
            sb.append(crashDetailBean.f1300n).append(":").append(crashDetailBean.f1301o).append(IOUtils.LINE_SEPARATOR_UNIX);
            a = m1832a(th2, C1955c.f1359e);
            sb.append(a);
            crashDetailBean.f1303q = sb.toString();
        }
        crashDetailBean.f1304r = System.currentTimeMillis();
        crashDetailBean.f1307u = C2018y.m2168b(crashDetailBean.f1303q.getBytes());
        try {
            crashDetailBean.f1311y = C2018y.m2152a(C1955c.f1359e, false);
            crashDetailBean.f1312z = this.f1402d.f1202d;
            crashDetailBean.f1265A = thread.getName() + "(" + thread.getId() + ")";
            crashDetailBean.f1311y.put(crashDetailBean.f1265A, a);
            crashDetailBean.f1272H = this.f1402d.mo19425y();
            crashDetailBean.f1294h = this.f1402d.mo19422v();
            crashDetailBean.f1295i = this.f1402d.mo19383I();
            crashDetailBean.f1276L = this.f1402d.f1181a;
            crashDetailBean.f1277M = this.f1402d.mo19394a();
            crashDetailBean.f1279O = this.f1402d.mo19380F();
            crashDetailBean.f1280P = this.f1402d.mo19381G();
            crashDetailBean.f1281Q = this.f1402d.mo19426z();
            crashDetailBean.f1282R = this.f1402d.mo19379E();
        } catch (Throwable th3) {
            C2014w.m2119e("handle crash error %s", th3.toString());
        }
        if (z) {
            this.f1400b.mo19463b(crashDetailBean);
        } else {
            boolean z2 = str != null && str.length() > 0;
            boolean z3 = bArr != null && bArr.length > 0;
            if (z2) {
                crashDetailBean.f1278N = new HashMap(1);
                crashDetailBean.f1278N.put("UserData", str);
            }
            if (z3) {
                crashDetailBean.f1283S = bArr;
            }
        }
        return crashDetailBean;
    }

    /* renamed from: a */
    private static boolean m1834a(Thread thread) {
        boolean z;
        synchronized (f1398i) {
            if (f1397h == null || !thread.getName().equals(f1397h)) {
                f1397h = thread.getName();
                z = false;
            } else {
                z = true;
            }
        }
        return z;
    }

    /* renamed from: a */
    public final void mo19486a(Thread thread, Throwable th, boolean z, String str, byte[] bArr) {
        String str2;
        if (z) {
            C2014w.m2119e("Java Crash Happen cause by %s(%d)", thread.getName(), Long.valueOf(thread.getId()));
            if (m1834a(thread)) {
                C2014w.m2113a("this class has handled this exception", new Object[0]);
                if (this.f1404f != null) {
                    C2014w.m2113a("call system handler", new Object[0]);
                    this.f1404f.uncaughtException(thread, th);
                } else {
                    C2014w.m2119e("current process die", new Object[0]);
                    Process.killProcess(Process.myPid());
                    System.exit(1);
                }
            }
        } else {
            C2014w.m2119e("Java Catch Happen", new Object[0]);
        }
        try {
            if (!this.f1405g) {
                C2014w.m2117c("Java crash handler is disable. Just return.", new Object[0]);
                if (!z) {
                    return;
                }
                if (this.f1403e != null && m1837b(this.f1403e)) {
                    C2014w.m2119e("sys default last handle start!", new Object[0]);
                    this.f1403e.uncaughtException(thread, th);
                    C2014w.m2119e("sys default last handle end!", new Object[0]);
                } else if (this.f1404f != null) {
                    C2014w.m2119e("system handle start!", new Object[0]);
                    this.f1404f.uncaughtException(thread, th);
                    C2014w.m2119e("system handle end!", new Object[0]);
                } else {
                    C2014w.m2119e("crashreport last handle start!", new Object[0]);
                    C2014w.m2119e("current process die", new Object[0]);
                    Process.killProcess(Process.myPid());
                    System.exit(1);
                    C2014w.m2119e("crashreport last handle end!", new Object[0]);
                }
            } else {
                if (!this.f1401c.mo19434b()) {
                    C2014w.m2119e("waiting for remote sync", new Object[0]);
                    int i = 0;
                    while (!this.f1401c.mo19434b()) {
                        C2018y.m2170b(500);
                        i += 500;
                        if (i >= 3000) {
                            break;
                        }
                    }
                }
                if (!this.f1401c.mo19434b()) {
                    C2014w.m2118d("no remote but still store!", new Object[0]);
                }
                if (this.f1401c.mo19435c().f1233g || !this.f1401c.mo19434b()) {
                    CrashDetailBean b = m1835b(thread, th, z, str, bArr);
                    if (b == null) {
                        C2014w.m2119e("pkg crash datas fail!", new Object[0]);
                        if (!z) {
                            return;
                        }
                        if (this.f1403e != null && m1837b(this.f1403e)) {
                            C2014w.m2119e("sys default last handle start!", new Object[0]);
                            this.f1403e.uncaughtException(thread, th);
                            C2014w.m2119e("sys default last handle end!", new Object[0]);
                        } else if (this.f1404f != null) {
                            C2014w.m2119e("system handle start!", new Object[0]);
                            this.f1404f.uncaughtException(thread, th);
                            C2014w.m2119e("system handle end!", new Object[0]);
                        } else {
                            C2014w.m2119e("crashreport last handle start!", new Object[0]);
                            C2014w.m2119e("current process die", new Object[0]);
                            Process.killProcess(Process.myPid());
                            System.exit(1);
                            C2014w.m2119e("crashreport last handle end!", new Object[0]);
                        }
                    } else {
                        C1953b.m1792a(z ? "JAVA_CRASH" : "JAVA_CATCH", C2018y.m2143a(), this.f1402d.f1202d, thread, C2018y.m2147a(th), b);
                        if (!this.f1400b.mo19461a(b)) {
                            this.f1400b.mo19459a(b, 3000, z);
                        }
                        if (!z) {
                            return;
                        }
                        if (this.f1403e != null && m1837b(this.f1403e)) {
                            C2014w.m2119e("sys default last handle start!", new Object[0]);
                            this.f1403e.uncaughtException(thread, th);
                            C2014w.m2119e("sys default last handle end!", new Object[0]);
                        } else if (this.f1404f != null) {
                            C2014w.m2119e("system handle start!", new Object[0]);
                            this.f1404f.uncaughtException(thread, th);
                            C2014w.m2119e("system handle end!", new Object[0]);
                        } else {
                            C2014w.m2119e("crashreport last handle start!", new Object[0]);
                            C2014w.m2119e("current process die", new Object[0]);
                            Process.killProcess(Process.myPid());
                            System.exit(1);
                            C2014w.m2119e("crashreport last handle end!", new Object[0]);
                        }
                    }
                } else {
                    C2014w.m2119e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
                    if (z) {
                        str2 = "JAVA_CRASH";
                    } else {
                        str2 = "JAVA_CATCH";
                    }
                    C1953b.m1792a(str2, C2018y.m2143a(), this.f1402d.f1202d, thread, C2018y.m2147a(th), null);
                    if (!z) {
                        return;
                    }
                    if (this.f1403e != null && m1837b(this.f1403e)) {
                        C2014w.m2119e("sys default last handle start!", new Object[0]);
                        this.f1403e.uncaughtException(thread, th);
                        C2014w.m2119e("sys default last handle end!", new Object[0]);
                    } else if (this.f1404f != null) {
                        C2014w.m2119e("system handle start!", new Object[0]);
                        this.f1404f.uncaughtException(thread, th);
                        C2014w.m2119e("system handle end!", new Object[0]);
                    } else {
                        C2014w.m2119e("crashreport last handle start!", new Object[0]);
                        C2014w.m2119e("current process die", new Object[0]);
                        Process.killProcess(Process.myPid());
                        System.exit(1);
                        C2014w.m2119e("crashreport last handle end!", new Object[0]);
                    }
                }
            }
        } catch (Throwable th2) {
            if (z) {
                if (this.f1403e != null && m1837b(this.f1403e)) {
                    C2014w.m2119e("sys default last handle start!", new Object[0]);
                    this.f1403e.uncaughtException(thread, th);
                    C2014w.m2119e("sys default last handle end!", new Object[0]);
                } else if (this.f1404f != null) {
                    C2014w.m2119e("system handle start!", new Object[0]);
                    this.f1404f.uncaughtException(thread, th);
                    C2014w.m2119e("system handle end!", new Object[0]);
                } else {
                    C2014w.m2119e("crashreport last handle start!", new Object[0]);
                    C2014w.m2119e("current process die", new Object[0]);
                    Process.killProcess(Process.myPid());
                    System.exit(1);
                    C2014w.m2119e("crashreport last handle end!", new Object[0]);
                }
            }
            throw th2;
        }
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        synchronized (f1398i) {
            mo19486a(thread, th, true, null, null);
        }
    }

    /* renamed from: b */
    private static boolean m1837b(UncaughtExceptionHandler uncaughtExceptionHandler) {
        StackTraceElement[] stackTrace;
        if (uncaughtExceptionHandler == null) {
            return true;
        }
        String name = uncaughtExceptionHandler.getClass().getName();
        String str = "uncaughtException";
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            String className = stackTraceElement.getClassName();
            String methodName = stackTraceElement.getMethodName();
            if (name.equals(className) && str.equals(methodName)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public final synchronized void mo19485a(StrategyBean strategyBean) {
        if (strategyBean != null) {
            if (strategyBean.f1233g != this.f1405g) {
                C2014w.m2113a("java changed to %b", Boolean.valueOf(strategyBean.f1233g));
                if (strategyBean.f1233g) {
                    mo19484a();
                } else {
                    mo19487b();
                }
            }
        }
    }

    /* renamed from: a */
    private static String m1832a(Throwable th, int i) {
        if (th == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
            if (th.getStackTrace() != null) {
                StackTraceElement[] stackTrace = th.getStackTrace();
                int length = stackTrace.length;
                int i2 = 0;
                while (i2 < length) {
                    StackTraceElement stackTraceElement = stackTrace[i2];
                    if (i <= 0 || sb.length() < i) {
                        sb.append(stackTraceElement.toString()).append(IOUtils.LINE_SEPARATOR_UNIX);
                        i2++;
                    } else {
                        sb.append("\n[Stack over limit size :" + i + " , has been cutted !]");
                        return sb.toString();
                    }
                }
            }
        } catch (Throwable th2) {
            C2014w.m2119e("gen stack error %s", th2.toString());
        }
        return sb.toString();
    }

    /* renamed from: b */
    private static String m1836b(Throwable th, int i) {
        if (th.getMessage() == null) {
            return "";
        }
        if (th.getMessage().length() <= 1000) {
            return th.getMessage();
        }
        return th.getMessage().substring(0, 1000) + "\n[Message over limit size:1000" + ", has been cutted!]";
    }
}
