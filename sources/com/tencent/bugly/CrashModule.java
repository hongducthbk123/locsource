package com.tencent.bugly;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.bugly.BuglyStrategy.C1910a;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.common.info.C1938a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.BuglyBroadcastRecevier;
import com.tencent.bugly.crashreport.crash.C1955c;
import com.tencent.bugly.crashreport.crash.C1958d;
import com.tencent.bugly.proguard.C1997m;
import com.tencent.bugly.proguard.C2000n;
import com.tencent.bugly.proguard.C2014w;

/* compiled from: BUGLY */
public class CrashModule extends C1911a {
    public static final int MODULE_ID = 1004;

    /* renamed from: c */
    private static int f1089c = 0;

    /* renamed from: d */
    private static boolean f1090d = false;

    /* renamed from: e */
    private static CrashModule f1091e = new CrashModule();

    /* renamed from: a */
    private long f1092a;

    /* renamed from: b */
    private C1910a f1093b;

    public static CrashModule getInstance() {
        f1091e.f1094id = 1004;
        return f1091e;
    }

    public static boolean hasInitialized() {
        return f1090d;
    }

    public synchronized void init(Context context, boolean z, BuglyStrategy buglyStrategy) {
        if (context != null) {
            if (!f1090d) {
                C2014w.m2113a("Initializing crash module.", new Object[0]);
                C1997m a = C1997m.m2013a();
                int i = f1089c + 1;
                f1089c = i;
                a.mo19590a(1004, i);
                f1090d = true;
                CrashReport.setContext(context);
                m1611a(context, buglyStrategy);
                C1955c.m1810a(1004, context, z, this.f1093b, (C2000n) null, (String) null);
                C1955c a2 = C1955c.m1808a();
                a2.mo19472e();
                if (buglyStrategy == null || buglyStrategy.isEnableNativeCrashMonitor()) {
                    a2.mo19474g();
                } else {
                    C2014w.m2113a("[crash] Closed native crash monitor!", new Object[0]);
                    a2.mo19473f();
                }
                if (buglyStrategy == null || buglyStrategy.isEnableANRCrashMonitor()) {
                    a2.mo19475h();
                } else {
                    C2014w.m2113a("[crash] Closed ANR monitor!", new Object[0]);
                    a2.mo19476i();
                }
                C1958d.m1828a(context);
                BuglyBroadcastRecevier instance = BuglyBroadcastRecevier.getInstance();
                instance.addFilter("android.net.conn.CONNECTIVITY_CHANGE");
                instance.regist(context);
                C1997m a3 = C1997m.m2013a();
                int i2 = f1089c - 1;
                f1089c = i2;
                a3.mo19590a(1004, i2);
            }
        }
    }

    /* renamed from: a */
    private synchronized void m1611a(Context context, BuglyStrategy buglyStrategy) {
        if (buglyStrategy != null) {
            String libBuglySOFilePath = buglyStrategy.getLibBuglySOFilePath();
            if (!TextUtils.isEmpty(libBuglySOFilePath)) {
                C1938a.m1667a(context).f1211m = libBuglySOFilePath;
                C2014w.m2113a("setted libBugly.so file path :%s", libBuglySOFilePath);
            }
            if (buglyStrategy.getCrashHandleCallback() != null) {
                this.f1093b = buglyStrategy.getCrashHandleCallback();
                C2014w.m2113a("setted CrashHanldeCallback", new Object[0]);
            }
            if (buglyStrategy.getAppReportDelay() > 0) {
                this.f1092a = buglyStrategy.getAppReportDelay();
                C2014w.m2113a("setted delay: %d", Long.valueOf(this.f1092a));
            }
        }
    }

    public void onServerStrategyChanged(StrategyBean strategyBean) {
        if (strategyBean != null) {
            C1955c a = C1955c.m1808a();
            if (a != null) {
                a.mo19466a(strategyBean);
            }
        }
    }

    public String[] getTables() {
        return new String[]{"t_cr"};
    }
}
