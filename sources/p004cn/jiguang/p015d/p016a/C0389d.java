package p004cn.jiguang.p015d.p016a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import java.io.Serializable;
import java.util.Locale;
import p004cn.jiguang.p015d.C0385a;
import p004cn.jiguang.p015d.p017b.C0395a;
import p004cn.jiguang.p015d.p017b.C0417d;
import p004cn.jiguang.p015d.p021d.C0453j;
import p004cn.jiguang.p031g.C0530k;
import p004cn.jiguang.p031g.p032a.C0507a;
import p004cn.jiguang.p031g.p033b.C0510a;
import p004cn.jiguang.p031g.p033b.C0514e;

/* renamed from: cn.jiguang.d.a.d */
public final class C0389d {

    /* renamed from: a */
    private static volatile C0514e f217a;

    /* renamed from: a */
    public static int m306a() {
        return ((Integer) m345p(null).mo6688a("session_id", Integer.valueOf(0))).intValue();
    }

    /* renamed from: a */
    public static void m307a(Context context) {
        f217a = C0514e.m1032a(context, "cn.jpush.android.user.profile");
    }

    /* renamed from: a */
    public static void m308a(Context context, int i) {
        m345p(context).mo6692b("session_id", Integer.valueOf(i));
    }

    /* renamed from: a */
    public static void m309a(Context context, long j) {
        m345p(context).mo6692b("services_launched_time", Long.valueOf(j));
    }

    /* renamed from: a */
    public static void m310a(Context context, long j, String str) {
        m345p(context).mo6691b(new C0510a().mo6675a("device_uid", (Serializable) Long.valueOf(j)).mo6676a("device_password", str));
        C0386a.m282j(m338i(context));
    }

    /* renamed from: a */
    public static void m311a(Context context, long j, String str, String str2, String str3) {
        C0510a a = new C0510a().mo6675a("device_uid", (Serializable) Long.valueOf(j)).mo6676a("device_password", str).mo6676a("device_registration_id", str2);
        if (!C0530k.m1099a(str3)) {
            a.mo6676a("devcie_id_generated", str3);
        }
        m345p(context).mo6691b(a);
        C0386a.m282j(m338i(context));
    }

    /* renamed from: a */
    public static void m312a(Context context, C0510a aVar) {
        m345p(context).mo6691b(aVar);
    }

    /* renamed from: a */
    public static <T extends Serializable> void m313a(Context context, String str, T t) {
        m345p(context).mo6692b(str, t);
    }

    /* renamed from: a */
    public static void m314a(Context context, String str, String str2) {
        m345p(context).mo6692b("sdk_version_" + str, str2);
    }

    /* renamed from: a */
    public static void m315a(Context context, String str, String str2, String str3, String str4) {
        m345p(context).mo6691b(new C0510a().mo6675a("device_registration_id", (Serializable) str).mo6675a("devcie_id_generated", (Serializable) str2).mo6675a("device_appkey", (Serializable) str3).mo6675a("backup_report_addr", (Serializable) str4));
    }

    /* renamed from: a */
    public static void m316a(Context context, boolean z) {
        m345p(context).mo6692b("upload_crash", Boolean.valueOf(z));
    }

    /* renamed from: a */
    public static void m317a(String str) {
        m345p(null).mo6692b("backup_report_addr", str);
    }

    /* renamed from: a */
    public static boolean m318a(Context context, String str) {
        return m345p(context).mo6692b("devcie_id_generated", str);
    }

    /* renamed from: b */
    public static C0510a m319b(Context context, C0510a aVar) {
        return m345p(context).mo6686a(aVar);
    }

    /* renamed from: b */
    public static <T extends Serializable> T m320b(Context context, String str, T t) {
        return m345p(context).mo6688a(str, t);
    }

    /* renamed from: b */
    public static String m321b(Context context) {
        return (String) m345p(context).mo6688a("device_registration_id", "");
    }

    /* renamed from: b */
    public static String m322b(Context context, String str) {
        return (String) m345p(context).mo6688a("sdk_version_" + str, "");
    }

    /* renamed from: b */
    public static void m323b(Context context, long j) {
        m345p(context).mo6692b("whitelist_wakeup_time", Long.valueOf(j));
    }

    /* renamed from: b */
    public static void m324b(Context context, boolean z) {
        m345p(context).mo6692b("is_tcp_close", Boolean.valueOf(z));
    }

    /* renamed from: b */
    public static void m325b(String str) {
        m345p(null).mo6692b("last_location", str);
    }

    /* renamed from: b */
    public static boolean m326b() {
        return ((Boolean) m345p(null).mo6688a("upload_crash", Boolean.valueOf(true))).booleanValue();
    }

    /* renamed from: c */
    public static String m327c() {
        return (String) m345p(null).mo6688a("backup_report_addr", "");
    }

    /* renamed from: c */
    public static String m328c(Context context) {
        return (String) m345p(context).mo6688a("devcie_id_generated", "");
    }

    /* renamed from: c */
    public static void m329c(Context context, String str) {
        m345p(context).mo6692b("analytics_account_id", str);
    }

    /* renamed from: c */
    public static void m330c(Context context, boolean z) {
        m345p(context).mo6692b("tcp_loggedin", Boolean.valueOf(z));
    }

    /* renamed from: d */
    public static long m331d(Context context) {
        return ((Long) m345p(context).mo6688a("device_uid", Long.valueOf(0))).longValue();
    }

    /* renamed from: d */
    public static void m332d(Context context, boolean z) {
        m345p(context).mo6692b("power_save_is_enbale", Boolean.valueOf(z));
    }

    /* renamed from: d */
    public static boolean m333d() {
        if (!C0395a.m388c()) {
            C0417d.m446a();
            return C0417d.m457d();
        }
        if (C0507a.m1001c()) {
            try {
                return C0507a.m1000b().mo6341a();
            } catch (Throwable th) {
            }
        }
        if (C0385a.f198e != null) {
            C0453j.m665a().mo6569c(C0385a.f198e, null, null);
        }
        try {
            return ((Boolean) m345p(C0385a.f198e).mo6688a("tcp_loggedin", Boolean.valueOf(false))).booleanValue();
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: e */
    public static boolean m334e(Context context) {
        if (m331d(context) <= 0 || TextUtils.isEmpty(m321b(context))) {
            return false;
        }
        String i = m338i(context);
        if (TextUtils.isEmpty(i)) {
            return false;
        }
        String v = C0386a.m295v();
        return !TextUtils.isEmpty(v) && TextUtils.equals(i, v);
    }

    /* renamed from: f */
    public static String m335f(Context context) {
        return (String) m345p(context).mo6688a("device_password", "");
    }

    /* renamed from: g */
    public static void m336g(Context context) {
        m345p(context).mo6691b(new C0510a().mo6675a("device_uid", (Serializable) Long.valueOf(0)).mo6676a("device_password", "").mo6676a("device_registration_id", "").mo6676a("devcie_id_generated", ""));
        C0386a.m282j(null);
    }

    /* renamed from: h */
    public static void m337h(Context context) {
        m345p(null).mo6691b(new C0510a().mo6675a("device_uid", (Serializable) Long.valueOf(0)).mo6676a("device_password", "").mo6676a("device_registration_id", ""));
    }

    /* renamed from: i */
    public static String m338i(Context context) {
        if (TextUtils.isEmpty(C0385a.f200g)) {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                if (!(applicationInfo == null || applicationInfo.metaData == null)) {
                    String string = applicationInfo.metaData.getString("JPUSH_APPKEY");
                    C0385a.f200g = string;
                    C0385a.f200g = string.toLowerCase(Locale.getDefault());
                }
            } catch (Throwable th) {
            }
        }
        return C0385a.f200g;
    }

    /* renamed from: j */
    public static boolean m339j(Context context) {
        return ((Boolean) m345p(context).mo6688a("is_tcp_close", Boolean.valueOf(false))).booleanValue();
    }

    /* renamed from: k */
    public static long m340k(Context context) {
        return ((Long) m345p(context).mo6688a("services_launched_time", Long.valueOf(-1))).longValue();
    }

    /* renamed from: l */
    public static long m341l(Context context) {
        return ((Long) m345p(context).mo6688a("whitelist_wakeup_time", Long.valueOf(-1))).longValue();
    }

    /* renamed from: m */
    public static String m342m(Context context) {
        return (String) m345p(context).mo6688a("last_location", "");
    }

    /* renamed from: n */
    public static String m343n(Context context) {
        return (String) m345p(context).mo6688a("analytics_account_id", "");
    }

    /* renamed from: o */
    public static boolean m344o(Context context) {
        return ((Boolean) m345p(context).mo6688a("power_save_is_enbale", Boolean.valueOf(false))).booleanValue();
    }

    /* renamed from: p */
    private static C0514e m345p(Context context) {
        if (f217a == null) {
            m307a(context);
        }
        return f217a;
    }
}
