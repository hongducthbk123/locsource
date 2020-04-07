package com.tencent.bugly.proguard;

import android.util.Log;
import java.util.Locale;

/* renamed from: com.tencent.bugly.proguard.w */
/* compiled from: BUGLY */
public final class C2014w {

    /* renamed from: a */
    public static String f1692a = "CrashReport";

    /* renamed from: b */
    public static boolean f1693b = false;

    /* renamed from: c */
    private static String f1694c = "CrashReportInfo";

    /* renamed from: a */
    private static boolean m2111a(int i, String str, Object... objArr) {
        if (!f1693b) {
            return false;
        }
        if (str == null) {
            str = "null";
        } else if (!(objArr == null || objArr.length == 0)) {
            str = String.format(Locale.US, str, objArr);
        }
        switch (i) {
            case 0:
                Log.i(f1692a, str);
                return true;
            case 1:
                Log.d(f1692a, str);
                return true;
            case 2:
                Log.w(f1692a, str);
                return true;
            case 3:
                Log.e(f1692a, str);
                return true;
            case 5:
                Log.i(f1694c, str);
                return true;
            default:
                return false;
        }
    }

    /* renamed from: a */
    public static boolean m2113a(String str, Object... objArr) {
        return m2111a(0, str, objArr);
    }

    /* renamed from: b */
    public static boolean m2115b(String str, Object... objArr) {
        return m2111a(5, str, objArr);
    }

    /* renamed from: c */
    public static boolean m2117c(String str, Object... objArr) {
        return m2111a(1, str, objArr);
    }

    /* renamed from: a */
    public static boolean m2112a(Class cls, String str, Object... objArr) {
        return m2111a(1, String.format(Locale.US, "[%s] %s", new Object[]{cls.getSimpleName(), str}), objArr);
    }

    /* renamed from: d */
    public static boolean m2118d(String str, Object... objArr) {
        return m2111a(2, str, objArr);
    }

    /* renamed from: a */
    public static boolean m2114a(Throwable th) {
        if (!f1693b) {
            return false;
        }
        return m2111a(2, C2018y.m2147a(th), new Object[0]);
    }

    /* renamed from: e */
    public static boolean m2119e(String str, Object... objArr) {
        return m2111a(3, str, objArr);
    }

    /* renamed from: b */
    public static boolean m2116b(Throwable th) {
        if (!f1693b) {
            return false;
        }
        return m2111a(3, C2018y.m2147a(th), new Object[0]);
    }
}
