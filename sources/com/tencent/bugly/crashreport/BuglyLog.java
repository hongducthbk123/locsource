package com.tencent.bugly.crashreport;

import android.util.Log;
import com.tencent.bugly.C1925b;
import com.tencent.bugly.proguard.C2015x;

/* compiled from: BUGLY */
public class BuglyLog {
    /* renamed from: v */
    public static void m1621v(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (C1925b.f1097c) {
            Log.v(str, str2);
        }
        C2015x.m2124a("V", str, str2);
    }

    /* renamed from: d */
    public static void m1617d(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (C1925b.f1097c) {
            Log.d(str, str2);
        }
        C2015x.m2124a("D", str, str2);
    }

    /* renamed from: i */
    public static void m1620i(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (C1925b.f1097c) {
            Log.i(str, str2);
        }
        C2015x.m2124a("I", str, str2);
    }

    /* renamed from: w */
    public static void m1622w(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (C1925b.f1097c) {
            Log.w(str, str2);
        }
        C2015x.m2124a("W", str, str2);
    }

    /* renamed from: e */
    public static void m1618e(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (C1925b.f1097c) {
            Log.e(str, str2);
        }
        C2015x.m2124a("E", str, str2);
    }

    /* renamed from: e */
    public static void m1619e(String str, String str2, Throwable th) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (C1925b.f1097c) {
            Log.e(str, str2, th);
        }
        C2015x.m2125a("E", str, th);
    }

    public static void setCache(int i) {
        C2015x.m2122a(i);
    }
}
