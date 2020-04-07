package p004cn.jiguang.p015d.p016a;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.vending.expansion.downloader.Constants;
import java.io.Serializable;
import p004cn.jiguang.api.BasePreferenceManager;
import p004cn.jiguang.api.MultiSpHelper;
import p004cn.jiguang.p015d.C0385a;
import p004cn.jiguang.p015d.p026g.p027a.C0480a;
import p004cn.jiguang.p031g.C0530k;
import p004cn.jiguang.p031g.p033b.C0510a;
import p004cn.jiguang.p031g.p033b.C0514e;
import p004cn.jpush.android.api.JPushInterface;

/* renamed from: cn.jiguang.d.a.a */
public final class C0386a {

    /* renamed from: a */
    public static String f208a = "";

    /* renamed from: b */
    public static int f209b = -1;

    /* renamed from: c */
    public static boolean f210c = false;

    /* renamed from: d */
    private static C0514e f211d;

    /* renamed from: a */
    public static long m242a(long j) {
        return (m294u() + j) / 1000;
    }

    /* renamed from: a */
    public static void m243a(int i) {
        MultiSpHelper.commitInt(C0385a.f198e, "idc", i);
    }

    /* renamed from: a */
    public static void m244a(Context context) {
        f211d = C0514e.m1032a(context, BasePreferenceManager.JPUSH_PREF);
    }

    /* renamed from: a */
    public static void m245a(Context context, int i) {
        C0389d.m313a(context, "jpush_register_code", Integer.valueOf(i));
    }

    /* renamed from: a */
    public static void m246a(Context context, String str) {
        m272f(context).mo6692b("push_udid", str);
    }

    /* renamed from: a */
    public static void m247a(Context context, String str, String str2) {
        C0389d.m313a(context, str, C0480a.m776a(str2));
    }

    /* renamed from: a */
    public static void m248a(String str, int i) {
        m272f((Context) null).mo6691b(new C0510a().mo6676a("last_good_conn_ip", str).mo6673a("last_good_conn_port", i));
    }

    /* renamed from: a */
    public static void m249a(String str, long j) {
        m272f((Context) null).mo6692b(str, Long.valueOf(j));
    }

    /* renamed from: a */
    public static void m250a(String str, String str2, String str3) {
        m272f((Context) null).mo6691b(new C0510a().mo6675a("device_main_imei", (Serializable) str).mo6675a("device_main_android_id", (Serializable) str2).mo6675a("device_main_mac", (Serializable) str3));
    }

    /* renamed from: a */
    public static void m251a(boolean z) {
        m272f((Context) null).mo6692b("sis_report_switch", Boolean.valueOf(z));
    }

    /* renamed from: a */
    public static synchronized boolean m252a() {
        boolean z;
        synchronized (C0386a.class) {
            long longValue = ((Long) m272f((Context) null).mo6688a("last_report_index", Long.valueOf(0))).longValue();
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - longValue > 86400000) {
                m272f((Context) null).mo6692b("last_report_index", Long.valueOf(currentTimeMillis));
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    /* renamed from: a */
    public static boolean m253a(String str) {
        if (str == null) {
            return true;
        }
        if (str.equalsIgnoreCase((String) m272f((Context) null).mo6688a("last_connection_type", null))) {
            return false;
        }
        m272f((Context) null).mo6692b("last_connection_type", str);
        return true;
    }

    /* renamed from: b */
    public static long m254b() {
        return ((Long) m272f((Context) null).mo6688a("last_report_index", Long.valueOf(0))).longValue();
    }

    /* renamed from: b */
    public static String m255b(Context context) {
        return (String) m272f(context).mo6688a("push_udid", "");
    }

    /* renamed from: b */
    public static String m256b(Context context, String str, String str2) {
        String str3 = (String) C0389d.m320b(context, str, "");
        return C0530k.m1099a(str3) ? str2 : C0480a.m784b(str3, str2);
    }

    /* renamed from: b */
    private static void m257b(int i) {
        m272f((Context) null).mo6692b("heartbeat_interval", Integer.valueOf(i));
    }

    /* renamed from: b */
    public static void m258b(long j) {
        if (j > 0) {
            long currentTimeMillis = System.currentTimeMillis();
            C0387b.m299a(j, currentTimeMillis);
            m272f((Context) null).mo6691b(new C0510a().mo6674a("login_server_time", j).mo6674a("login_local_time", currentTimeMillis));
        }
    }

    /* renamed from: b */
    public static void m259b(Context context, String str) {
        m272f(context).mo6692b("imei", str);
    }

    /* renamed from: b */
    public static void m260b(String str) {
        m272f((Context) null).mo6692b("backup_user_addr", str);
    }

    /* renamed from: b */
    public static void m261b(String str, int i) {
        m272f((Context) null).mo6691b(new C0510a().mo6676a("default_conn_ip", str).mo6673a("default_conn_port", i));
    }

    /* renamed from: c */
    public static String m262c(Context context) {
        return (String) m272f(context).mo6688a("imei", "");
    }

    /* renamed from: c */
    public static void m263c() {
        m272f((Context) null).mo6691b(new C0510a().mo6675a("last_good_sis", (Serializable) null).mo6675a("last_good_sis_address", (Serializable) null).mo6675a("default_sis", (Serializable) null).mo6675a("last_good_conn_ip", (Serializable) null).mo6675a("last_good_conn_port", (Serializable) null).mo6675a("last_sis_request_time", (Serializable) Long.valueOf(0)).mo6675a("device_registered_appkey", (Serializable) null));
        C0389d.m337h(null);
    }

    /* renamed from: c */
    public static void m264c(Context context, String str) {
        m272f(context).mo6692b("udp_report_device_info", str);
    }

    /* renamed from: c */
    public static void m265c(String str) {
        m272f((Context) null).mo6692b("last_good_sis", str);
    }

    /* renamed from: d */
    public static String m266d() {
        return (String) m272f((Context) null).mo6688a("last_good_conn_ip", null);
    }

    /* renamed from: d */
    public static void m267d(Context context) {
        m244a(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences("cn.jpush.serverconfig", 0);
        m272f(context).mo6691b(new C0510a().mo6676a("device_main_ids", sharedPreferences.getString("register_device_info", "")).mo6676a("device_main_imei", sharedPreferences.getString("register_device_imei", "")).mo6676a("device_main_android_id", sharedPreferences.getString("register_device_android_id", "")).mo6676a("device_main_mac", sharedPreferences.getString("register_device_mac", "")).mo6676a("last_connection_type", sharedPreferences.getString("jpush_sis_nettype", "")).mo6676a("last_good_sis", sharedPreferences.getString("jpush_sis_receiver_string", "")).mo6676a("default_conn_ip", sharedPreferences.getString("mIP", "")).mo6676a("last_good_conn_ip", sharedPreferences.getString("jpush_conn_mip", "")).mo6676a("push_udid", sharedPreferences.getString("push_udid", "")).mo6676a("imei", sharedPreferences.getString("imei", "")).mo6673a("default_conn_port", sharedPreferences.getInt("mPort", 0)).mo6673a("last_good_conn_port", sharedPreferences.getInt("jpush_conn_mport", 0)).mo6674a("login_local_time", sharedPreferences.getLong("lctime", 0)).mo6674a("login_server_time", sharedPreferences.getLong("login_server_time", 0)));
        C0389d.m312a(context, new C0510a().mo6674a("last_report_device_info", sharedPreferences.getLong("dev_info_rep_time", 0)).mo6674a("last_report_location", sharedPreferences.getLong("login_report_time", 0)).mo6676a("setting_silence_push_time", sharedPreferences.getString("silencePushTime", "")).mo6676a("setting_push_time", sharedPreferences.getString("pushtime", "")).mo6673a("notification_num", sharedPreferences.getInt("notifaction_num", 5)).mo6673a("service_stoped", sharedPreferences.getInt("service_stoped", 0)));
        C0389d.m315a(context, sharedPreferences.getString("registration_id", ""), sharedPreferences.getString("JPush_DeviceId", ""), sharedPreferences.getString(JPushInterface.EXTRA_APP_KEY, ""), sharedPreferences.getString("http_report_sis_url", ""));
    }

    /* renamed from: d */
    public static void m268d(String str) {
        m272f((Context) null).mo6692b("default_sis", str);
    }

    /* renamed from: e */
    public static int m269e() {
        return ((Integer) m272f((Context) null).mo6688a("last_good_conn_port", Integer.valueOf(0))).intValue();
    }

    /* renamed from: e */
    public static String m270e(Context context) {
        return (String) m272f(context).mo6688a("udp_report_device_info", "");
    }

    /* renamed from: e */
    public static void m271e(String str) {
        m272f((Context) null).mo6692b("sis_report_history", str);
    }

    /* renamed from: f */
    private static C0514e m272f(Context context) {
        if (f211d == null) {
            m244a(context);
        }
        return f211d;
    }

    /* renamed from: f */
    public static void m273f(String str) {
        m272f((Context) null).mo6692b("last_good_sis_address", str);
    }

    /* renamed from: f */
    public static boolean m274f() {
        return System.currentTimeMillis() - ((Long) m272f((Context) null).mo6688a("last_sis_request_time", Long.valueOf(0))).longValue() > 180000;
    }

    /* renamed from: g */
    public static String m275g(String str) {
        return (String) m272f((Context) null).mo6688a("device_channel", str);
    }

    /* renamed from: g */
    public static void m276g() {
        m272f((Context) null).mo6692b("last_sis_request_time", Long.valueOf(System.currentTimeMillis()));
    }

    /* renamed from: h */
    public static synchronized long m277h() {
        long a;
        synchronized (C0386a.class) {
            a = C0391f.m348a();
        }
        return a;
    }

    /* renamed from: h */
    public static void m278h(String str) {
        m272f((Context) null).mo6692b("device_channel", str);
    }

    /* renamed from: i */
    public static int m279i() {
        return ((Integer) m272f((Context) null).mo6688a("heartbeat_interval", Integer.valueOf(290))).intValue();
    }

    /* renamed from: i */
    public static void m280i(String str) {
        m272f((Context) null).mo6692b("sdk_version", str);
    }

    /* renamed from: j */
    public static void m281j() {
        m257b(290);
    }

    /* renamed from: j */
    public static void m282j(String str) {
        m272f((Context) null).mo6692b("device_registered_appkey", str);
    }

    /* renamed from: k */
    public static long m283k(String str) {
        return ((Long) m272f((Context) null).mo6688a(str, Long.valueOf(0))).longValue();
    }

    /* renamed from: k */
    public static void m284k() {
        m257b((int) Constants.MAX_RETRY_AFTER);
    }

    /* renamed from: l */
    public static boolean m285l() {
        return m279i() == 86400;
    }

    /* renamed from: m */
    public static String m286m() {
        return (String) m272f((Context) null).mo6688a("last_good_sis", null);
    }

    /* renamed from: n */
    public static String m287n() {
        return (String) m272f((Context) null).mo6688a("default_sis", null);
    }

    /* renamed from: o */
    public static String m288o() {
        return (String) m272f((Context) null).mo6688a("sis_report_history", "");
    }

    /* renamed from: p */
    public static String m289p() {
        return (String) m272f((Context) null).mo6688a("last_good_sis_address", null);
    }

    /* renamed from: q */
    public static boolean m290q() {
        return ((Boolean) m272f((Context) null).mo6688a("sis_report_switch", Boolean.valueOf(false))).booleanValue();
    }

    /* renamed from: r */
    public static String m291r() {
        return (String) m272f((Context) null).mo6688a("foo001", null);
    }

    /* renamed from: s */
    public static String m292s() {
        return (String) m272f((Context) null).mo6688a("sdk_version", "");
    }

    /* renamed from: t */
    public static long m293t() {
        return m242a(System.currentTimeMillis());
    }

    /* renamed from: u */
    public static long m294u() {
        if (C0387b.m300a()) {
            return C0387b.m301b();
        }
        long longValue = ((Long) m272f((Context) null).mo6688a("login_local_time", Long.valueOf(0))).longValue();
        long longValue2 = ((Long) m272f((Context) null).mo6688a("login_server_time", Long.valueOf(0))).longValue();
        if (longValue == 0 || longValue2 == 0) {
            return 0;
        }
        C0387b.m299a(longValue2, longValue);
        return longValue2 - longValue;
    }

    /* renamed from: v */
    public static String m295v() {
        return (String) m272f((Context) null).mo6688a("device_registered_appkey", null);
    }

    /* renamed from: w */
    public static C0388c m296w() {
        return new C0388c((String) m272f((Context) null).mo6688a("device_main_imei", ""), (String) m272f((Context) null).mo6688a("device_main_android_id", ""), (String) m272f((Context) null).mo6688a("device_main_mac", ""));
    }

    /* renamed from: x */
    public static boolean m297x() {
        return ((Boolean) C0389d.m320b(null, "is_im_logged_in", Boolean.valueOf(false))).booleanValue();
    }

    /* renamed from: y */
    public static int m298y() {
        return ((Integer) C0389d.m320b(null, "im_login_count", Integer.valueOf(-1))).intValue();
    }
}
