package com.tencent.bugly.crashreport.crash;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.tencent.bugly.crashreport.biz.C1934b;
import com.tencent.bugly.crashreport.common.info.C1938a;
import com.tencent.bugly.crashreport.common.info.C1939b;
import com.tencent.bugly.crashreport.common.strategy.C1941a;
import com.tencent.bugly.proguard.C2007t;
import com.tencent.bugly.proguard.C2014w;

/* compiled from: BUGLY */
public class BuglyBroadcastRecevier extends BroadcastReceiver {

    /* renamed from: d */
    private static BuglyBroadcastRecevier f1260d = null;

    /* renamed from: a */
    private IntentFilter f1261a = new IntentFilter();

    /* renamed from: b */
    private Context f1262b;

    /* renamed from: c */
    private String f1263c;

    /* renamed from: e */
    private boolean f1264e = true;

    public static synchronized BuglyBroadcastRecevier getInstance() {
        BuglyBroadcastRecevier buglyBroadcastRecevier;
        synchronized (BuglyBroadcastRecevier.class) {
            if (f1260d == null) {
                f1260d = new BuglyBroadcastRecevier();
            }
            buglyBroadcastRecevier = f1260d;
        }
        return buglyBroadcastRecevier;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        if (this.f1262b != null) {
            this.f1262b.unregisterReceiver(this);
        }
    }

    public synchronized void addFilter(String str) {
        if (!this.f1261a.hasAction(str)) {
            this.f1261a.addAction(str);
        }
        C2014w.m2117c("add action %s", str);
    }

    public synchronized void regist(Context context) {
        try {
            C2014w.m2113a("regis BC", new Object[0]);
            this.f1262b = context;
            context.registerReceiver(this, this.f1261a);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return;
    }

    public synchronized void unregist(Context context) {
        try {
            C2014w.m2113a("unregis BC", new Object[0]);
            context.unregisterReceiver(this);
            this.f1262b = context;
        } catch (Throwable th) {
            if (!C2014w.m2114a(th)) {
                th.printStackTrace();
            }
        }
    }

    public final void onReceive(Context context, Intent intent) {
        try {
            m1761a(context, intent);
        } catch (Throwable th) {
            if (!C2014w.m2114a(th)) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    private synchronized boolean m1761a(Context context, Intent intent) {
        boolean z = true;
        synchronized (this) {
            if (!(context == null || intent == null)) {
                if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    if (this.f1264e) {
                        this.f1264e = false;
                    } else {
                        String e = C1939b.m1732e(this.f1262b);
                        C2014w.m2117c("is Connect BC " + e, new Object[0]);
                        C2014w.m2113a("network %s changed to %s", this.f1263c, e);
                        if (e == null) {
                            this.f1263c = null;
                        } else {
                            String str = this.f1263c;
                            this.f1263c = e;
                            long currentTimeMillis = System.currentTimeMillis();
                            C1941a a = C1941a.m1752a();
                            C2007t a2 = C2007t.m2069a();
                            C1938a a3 = C1938a.m1667a(context);
                            if (a == null || a2 == null || a3 == null) {
                                C2014w.m2118d("not inited BC not work", new Object[0]);
                            } else if (!e.equals(str)) {
                                if (currentTimeMillis - a2.mo19616a(C1955c.f1355a) > 30000) {
                                    C2014w.m2113a("try to upload crash on network changed.", new Object[0]);
                                    C1955c a4 = C1955c.m1808a();
                                    if (a4 != null) {
                                        a4.mo19465a(0);
                                    }
                                }
                                if (currentTimeMillis - a2.mo19616a(1001) > 30000) {
                                    C2014w.m2113a("try to upload userinfo on network changed.", new Object[0]);
                                    C1934b.f1136a.mo19356b();
                                }
                            }
                        }
                    }
                }
            }
            z = false;
        }
        return z;
    }
}
