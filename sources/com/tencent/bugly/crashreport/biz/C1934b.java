package com.tencent.bugly.crashreport.biz;

import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Build.VERSION;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.info.C1938a;
import com.tencent.bugly.crashreport.common.strategy.C1941a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.C2012v;
import com.tencent.bugly.proguard.C2014w;
import com.tencent.bugly.proguard.C2018y;
import org.apache.commons.p052io.IOUtils;

/* renamed from: com.tencent.bugly.crashreport.biz.b */
/* compiled from: BUGLY */
public class C1934b {

    /* renamed from: a */
    public static C1928a f1136a;

    /* renamed from: b */
    private static boolean f1137b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public static int f1138c = 10;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public static long f1139d = 300000;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public static long f1140e = 30000;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public static long f1141f = 0;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public static int f1142g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public static long f1143h;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public static long f1144i;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public static long f1145j = 0;

    /* renamed from: k */
    private static ActivityLifecycleCallbacks f1146k = null;
    /* access modifiers changed from: private */

    /* renamed from: l */
    public static Class<?> f1147l = null;
    /* access modifiers changed from: private */

    /* renamed from: m */
    public static boolean f1148m = true;

    /* renamed from: a */
    static /* synthetic */ String m1635a(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(C2018y.m2143a());
        sb.append("  ");
        sb.append(str);
        sb.append("  ");
        sb.append(str2);
        sb.append(IOUtils.LINE_SEPARATOR_UNIX);
        return sb.toString();
    }

    /* renamed from: g */
    static /* synthetic */ int m1650g() {
        int i = f1142g;
        f1142g = i + 1;
        return i;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:73:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m1646c(android.content.Context r11, com.tencent.bugly.BuglyStrategy r12) {
        /*
            r1 = 1
            r0 = 0
            if (r12 == 0) goto L_0x0120
            boolean r0 = r12.recordUserInfoOnceADay()
            boolean r1 = r12.isEnableUserInfo()
            r10 = r0
            r0 = r1
            r1 = r10
        L_0x000f:
            if (r1 == 0) goto L_0x006f
            com.tencent.bugly.crashreport.common.info.a r2 = com.tencent.bugly.crashreport.common.info.C1938a.m1667a(r11)
            java.lang.String r0 = r2.f1202d
            com.tencent.bugly.crashreport.biz.a r1 = f1136a
            java.util.List r3 = r1.mo19353a(r0)
            if (r3 == 0) goto L_0x006c
            r0 = 0
            r1 = r0
        L_0x0021:
            int r0 = r3.size()
            if (r1 >= r0) goto L_0x006c
            java.lang.Object r0 = r3.get(r1)
            com.tencent.bugly.crashreport.biz.UserInfoBean r0 = (com.tencent.bugly.crashreport.biz.UserInfoBean) r0
            java.lang.String r4 = r0.f1117n
            java.lang.String r5 = r2.f1208j
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0068
            int r4 = r0.f1105b
            r5 = 1
            if (r4 != r5) goto L_0x0068
            long r4 = com.tencent.bugly.proguard.C2018y.m2166b()
            r6 = 0
            int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r6 <= 0) goto L_0x006c
            long r6 = r0.f1108e
            int r4 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r4 < 0) goto L_0x0068
            long r0 = r0.f1109f
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 > 0) goto L_0x0064
            com.tencent.bugly.crashreport.biz.a r0 = f1136a
            com.tencent.bugly.proguard.v r1 = com.tencent.bugly.proguard.C2012v.m2106a()
            if (r1 == 0) goto L_0x0064
            com.tencent.bugly.crashreport.biz.a$2 r2 = new com.tencent.bugly.crashreport.biz.a$2
            r2.<init>()
            r1.mo19636a(r2)
        L_0x0064:
            r0 = 0
        L_0x0065:
            if (r0 != 0) goto L_0x006e
        L_0x0067:
            return
        L_0x0068:
            int r0 = r1 + 1
            r1 = r0
            goto L_0x0021
        L_0x006c:
            r0 = 1
            goto L_0x0065
        L_0x006e:
            r0 = 0
        L_0x006f:
            com.tencent.bugly.crashreport.common.info.a r4 = com.tencent.bugly.crashreport.common.info.C1938a.m1668b()
            if (r4 == 0) goto L_0x00b2
            r3 = 0
            r2 = 0
            java.lang.Thread r1 = java.lang.Thread.currentThread()
            java.lang.StackTraceElement[] r5 = r1.getStackTrace()
            int r6 = r5.length
            r1 = 0
            r10 = r1
            r1 = r3
            r3 = r10
        L_0x0084:
            if (r3 >= r6) goto L_0x00a8
            r7 = r5[r3]
            java.lang.String r8 = r7.getMethodName()
            java.lang.String r9 = "onCreate"
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x0098
            java.lang.String r1 = r7.getClassName()
        L_0x0098:
            java.lang.String r7 = r7.getClassName()
            java.lang.String r8 = "android.app.Activity"
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x00a5
            r2 = 1
        L_0x00a5:
            int r3 = r3 + 1
            goto L_0x0084
        L_0x00a8:
            if (r1 == 0) goto L_0x0111
            if (r2 == 0) goto L_0x010e
            r2 = 1
            r4.mo19393a(r2)
        L_0x00b0:
            r4.f1214p = r1
        L_0x00b2:
            if (r0 == 0) goto L_0x00db
            r0 = 0
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 14
            if (r1 < r2) goto L_0x00db
            android.content.Context r1 = r11.getApplicationContext()
            boolean r1 = r1 instanceof android.app.Application
            if (r1 == 0) goto L_0x00c9
            android.content.Context r0 = r11.getApplicationContext()
            android.app.Application r0 = (android.app.Application) r0
        L_0x00c9:
            if (r0 == 0) goto L_0x00db
            android.app.Application$ActivityLifecycleCallbacks r1 = f1146k     // Catch:{ Exception -> 0x0115 }
            if (r1 != 0) goto L_0x00d6
            com.tencent.bugly.crashreport.biz.b$2 r1 = new com.tencent.bugly.crashreport.biz.b$2     // Catch:{ Exception -> 0x0115 }
            r1.<init>()     // Catch:{ Exception -> 0x0115 }
            f1146k = r1     // Catch:{ Exception -> 0x0115 }
        L_0x00d6:
            android.app.Application$ActivityLifecycleCallbacks r1 = f1146k     // Catch:{ Exception -> 0x0115 }
            r0.registerActivityLifecycleCallbacks(r1)     // Catch:{ Exception -> 0x0115 }
        L_0x00db:
            boolean r0 = f1148m
            if (r0 == 0) goto L_0x0067
            long r0 = java.lang.System.currentTimeMillis()
            f1144i = r0
            com.tencent.bugly.crashreport.biz.a r0 = f1136a
            r1 = 1
            r2 = 0
            r4 = 0
            r0.mo19355a(r1, r2, r4)
            java.lang.String r0 = "[session] launch app, new start"
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.C2014w.m2113a(r0, r1)
            com.tencent.bugly.crashreport.biz.a r0 = f1136a
            r0.mo19354a()
            com.tencent.bugly.crashreport.biz.a r0 = f1136a
            r2 = 21600000(0x1499700, double:1.0671818E-316)
            com.tencent.bugly.proguard.v r1 = com.tencent.bugly.proguard.C2012v.m2106a()
            com.tencent.bugly.crashreport.biz.a$c r4 = new com.tencent.bugly.crashreport.biz.a$c
            r4.<init>(r2)
            r1.mo19637a(r4, r2)
            goto L_0x0067
        L_0x010e:
            java.lang.String r1 = "background"
            goto L_0x00b0
        L_0x0111:
            java.lang.String r1 = "unknown"
            goto L_0x00b0
        L_0x0115:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C2014w.m2114a(r0)
            if (r1 != 0) goto L_0x00db
            r0.printStackTrace()
            goto L_0x00db
        L_0x0120:
            r10 = r0
            r0 = r1
            r1 = r10
            goto L_0x000f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.biz.C1934b.m1646c(android.content.Context, com.tencent.bugly.BuglyStrategy):void");
    }

    /* renamed from: a */
    public static void m1639a(final Context context, final BuglyStrategy buglyStrategy) {
        long j;
        if (!f1137b) {
            f1148m = C1938a.m1667a(context).f1203e;
            f1136a = new C1928a(context, f1148m);
            f1137b = true;
            if (buglyStrategy != null) {
                f1147l = buglyStrategy.getUserInfoActivity();
                j = buglyStrategy.getAppReportDelay();
            } else {
                j = 0;
            }
            if (j <= 0) {
                m1646c(context, buglyStrategy);
            } else {
                C2012v.m2106a().mo19637a(new Runnable() {
                    public final void run() {
                        C1934b.m1646c(context, buglyStrategy);
                    }
                }, j);
            }
        }
    }

    /* renamed from: a */
    public static void m1637a(long j) {
        if (j < 0) {
            j = C1941a.m1752a().mo19435c().f1243q;
        }
        f1141f = j;
    }

    /* renamed from: a */
    public static void m1640a(StrategyBean strategyBean, boolean z) {
        if (f1136a != null && !z) {
            C1928a aVar = f1136a;
            C2012v a = C2012v.m2106a();
            if (a != null) {
                a.mo19636a(new Runnable() {
                    public final void run() {
                        try {
                            C1928a.this.m1629c();
                        } catch (Throwable th) {
                            C2014w.m2114a(th);
                        }
                    }
                });
            }
        }
        if (strategyBean != null) {
            if (strategyBean.f1243q > 0) {
                f1140e = strategyBean.f1243q;
            }
            if (strategyBean.f1249w > 0) {
                f1138c = strategyBean.f1249w;
            }
            if (strategyBean.f1250x > 0) {
                f1139d = strategyBean.f1250x;
            }
        }
    }

    /* renamed from: a */
    public static void m1636a() {
        if (f1136a != null) {
            f1136a.mo19355a(2, false, 0);
        }
    }

    /* renamed from: a */
    public static void m1638a(Context context) {
        if (f1137b && context != null) {
            Application application = null;
            if (VERSION.SDK_INT >= 14) {
                if (context.getApplicationContext() instanceof Application) {
                    application = (Application) context.getApplicationContext();
                }
                if (application != null) {
                    try {
                        if (f1146k != null) {
                            application.unregisterActivityLifecycleCallbacks(f1146k);
                        }
                    } catch (Exception e) {
                        if (!C2014w.m2114a(e)) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            f1137b = false;
        }
    }
}
