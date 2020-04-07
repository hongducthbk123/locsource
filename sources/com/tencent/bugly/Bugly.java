package com.tencent.bugly;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.bugly.crashreport.common.info.C1938a;
import com.tencent.bugly.proguard.C2000n;
import com.tencent.bugly.proguard.C2001o;
import com.tencent.bugly.proguard.C2014w;
import com.tencent.bugly.proguard.C2018y;
import java.util.Map;

/* compiled from: BUGLY */
public class Bugly {
    public static final String SDK_IS_DEV = "false";

    /* renamed from: a */
    private static boolean f1071a;
    public static Context applicationContext = null;

    /* renamed from: b */
    private static String[] f1072b = {"BuglyCrashModule", "BuglyRqdModule", "BuglyBetaModule"};

    /* renamed from: c */
    private static String[] f1073c = {"BuglyRqdModule", "BuglyCrashModule", "BuglyBetaModule"};
    public static boolean enable = true;
    public static Boolean isDev;

    public static void init(Context context, String str, boolean z) {
        init(context, str, z, null);
    }

    public static synchronized void init(Context context, String str, boolean z, BuglyStrategy buglyStrategy) {
        String[] strArr;
        synchronized (Bugly.class) {
            if (!f1071a) {
                f1071a = true;
                Context a = C2018y.m2138a(context);
                applicationContext = a;
                if (a == null) {
                    Log.e(C2014w.f1692a, "init arg 'context' should not be null!");
                } else {
                    if (isDev()) {
                        f1072b = f1073c;
                    }
                    for (String str2 : f1072b) {
                        try {
                            if (str2.equals("BuglyCrashModule")) {
                                C1925b.m1615a((C1911a) CrashModule.getInstance());
                            } else if (!str2.equals("BuglyBetaModule") && !str2.equals("BuglyRqdModule")) {
                                str2.equals("BuglyFeedbackModule");
                            }
                        } catch (Throwable th) {
                            C2014w.m2116b(th);
                        }
                    }
                    C1925b.f1095a = enable;
                    C1925b.m1614a(applicationContext, str, z, buglyStrategy);
                }
            }
        }
    }

    public static synchronized String getAppChannel() {
        String str = null;
        synchronized (Bugly.class) {
            C1938a b = C1938a.m1668b();
            if (b != null) {
                if (TextUtils.isEmpty(b.f1210l)) {
                    C2001o a = C2001o.m2035a();
                    if (a == null) {
                        str = b.f1210l;
                    } else {
                        Map a2 = a.mo19601a(556, (C2000n) null, true);
                        if (a2 != null) {
                            byte[] bArr = (byte[]) a2.get("app_channel");
                            if (bArr != null) {
                                str = new String(bArr);
                            }
                        }
                    }
                }
                str = b.f1210l;
            }
        }
        return str;
    }

    public static boolean isDev() {
        if (isDev == null) {
            isDev = Boolean.valueOf(Boolean.parseBoolean(SDK_IS_DEV.replace("@", "")));
        }
        return isDev.booleanValue();
    }
}
