package p004cn.jiguang.p005a.p011b;

import android.content.Context;
import p004cn.jiguang.p015d.p016a.C0386a;
import p004cn.jiguang.p015d.p016a.C0389d;

/* renamed from: cn.jiguang.a.b.a */
public final class C0372a {
    /* renamed from: a */
    public static long m156a(Context context) {
        long longValue = ((Long) C0389d.m320b(context, "report_location_frequency", Long.valueOf(3600000))).longValue();
        if (longValue > 0) {
            return longValue;
        }
        return 3600000;
    }

    /* renamed from: a */
    public static void m157a(Context context, boolean z) {
        C0389d.m313a(context, "lbs_report_enable", Boolean.valueOf(z));
    }

    /* renamed from: b */
    public static String m158b(Context context) {
        return C0386a.m256b(context, "number_appsecret", "2b90de0f1f88eaf49593f1d827b19c63");
    }

    /* renamed from: b */
    public static void m159b(Context context, boolean z) {
        C0389d.m313a(context, "lbs_report_now", Boolean.valueOf(z));
    }

    /* renamed from: c */
    public static long m160c(Context context) {
        return ((Long) C0389d.m320b(context, "report_arpinfo_frequency", Long.valueOf(0))).longValue();
    }

    /* renamed from: c */
    public static void m161c(Context context, boolean z) {
        C0389d.m313a(context, "nb_upload", Boolean.valueOf(z));
    }
}
