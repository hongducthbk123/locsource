package p004cn.jiguang.api;

import android.content.Context;
import p004cn.jiguang.p015d.p016a.C0389d;

/* renamed from: cn.jiguang.api.MultiSpHelper */
public class MultiSpHelper {
    public static void commitBoolean(Context context, String str, boolean z) {
        C0389d.m313a(context, str, Boolean.valueOf(z));
    }

    public static void commitInt(Context context, String str, int i) {
        C0389d.m313a(context, str, Integer.valueOf(i));
    }

    public static void commitLong(Context context, String str, long j) {
        C0389d.m313a(context, str, Long.valueOf(j));
    }

    public static void commitString(Context context, String str, String str2) {
        C0389d.m313a(context, str, str2);
    }

    public static boolean getBoolean(Context context, String str, boolean z) {
        return ((Boolean) C0389d.m320b(context, str, Boolean.valueOf(z))).booleanValue();
    }

    public static int getInt(Context context, String str, int i) {
        return ((Integer) C0389d.m320b(context, str, Integer.valueOf(i))).intValue();
    }

    public static long getLong(Context context, String str, long j) {
        return ((Long) C0389d.m320b(context, str, Long.valueOf(j))).longValue();
    }

    public static String getString(Context context, String str, String str2) {
        return (String) C0389d.m320b(context, str, str2);
    }
}
