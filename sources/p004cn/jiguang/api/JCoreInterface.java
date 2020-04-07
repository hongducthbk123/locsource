package p004cn.jiguang.api;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import p004cn.jiguang.net.HttpResponse;
import p004cn.jiguang.net.HttpUtils;
import p004cn.jiguang.p005a.C0334a;
import p004cn.jiguang.p005a.p006a.p009c.C0358e;
import p004cn.jiguang.p005a.p006a.p010d.C0366b;
import p004cn.jiguang.p005a.p011b.C0372a;
import p004cn.jiguang.p005a.p012c.C0375a;
import p004cn.jiguang.p015d.C0385a;
import p004cn.jiguang.p015d.p016a.C0386a;
import p004cn.jiguang.p015d.p016a.C0389d;
import p004cn.jiguang.p015d.p017b.C0395a;
import p004cn.jiguang.p015d.p021d.C0445b;
import p004cn.jiguang.p015d.p021d.C0453j;
import p004cn.jiguang.p015d.p021d.C0460q;
import p004cn.jiguang.p015d.p028h.C0495f;
import p004cn.jiguang.p029e.C0498a;
import p004cn.jiguang.p029e.C0501d;
import p004cn.jiguang.p031g.C0506a;
import p004cn.jiguang.p031g.p032a.C0507a;

/* renamed from: cn.jiguang.api.JCoreInterface */
public class JCoreInterface {

    /* renamed from: a */
    public static String f185a = "cn.jpush.android.intent.DaemonService";

    /* renamed from: b */
    private static boolean f186b = false;

    public static boolean canCallDirect() {
        try {
            return C0507a.m1001c() && C0507a.m1000b().asBinder().pingBinder();
        } catch (Throwable th) {
            return false;
        }
    }

    public static JSONObject fillBaseReport(JSONObject jSONObject, String str) {
        return C0460q.m707a(C0385a.f198e, jSONObject, str);
    }

    public static String getAccountId() {
        return C0389d.m343n(C0385a.f198e);
    }

    public static boolean getAesConfig() {
        return true;
    }

    public static String getAppKey() {
        return C0389d.m338i(null);
    }

    public static IBinder getBinderByType(String str, String str2) {
        if (C0507a.m1001c()) {
            try {
                return C0507a.m1000b().mo6339a(str, str2);
            } catch (Throwable th) {
            }
        }
        return null;
    }

    public static String getChannel() {
        if (!f186b) {
            return null;
        }
        return C0386a.m275g("");
    }

    public static String getCommonConfigAppkey() {
        return !f186b ? "" : C0389d.m338i(null);
    }

    public static boolean getConnectionState(Context context) {
        if (!init(context, false)) {
            return false;
        }
        return C0389d.m333d();
    }

    public static String getDaemonAction() {
        return f185a;
    }

    public static boolean getDebugMode() {
        return C0385a.f195b;
    }

    public static String getDeviceId(Context context) {
        return !C0385a.m239a(context) ? "" : C0506a.m972h(context);
    }

    public static String getHttpConfig(Context context, String str) {
        if (!C0385a.m239a(context)) {
            return "";
        }
        HttpResponse httpGet = HttpUtils.httpGet(context, C0460q.m717b(str));
        if (httpGet == null || httpGet.getResponseCode() != 200) {
            return null;
        }
        return httpGet.getResponseBody();
    }

    public static int getJCoreSDKVersionInt() {
        return 120;
    }

    public static long getNextRid() {
        if (!f186b) {
            return 0;
        }
        return C0386a.m277h();
    }

    public static String getRegistrationID(Context context) {
        return !init(context, false) ? "" : C0389d.m321b(context);
    }

    public static long getReportTime() {
        return !f186b ? System.currentTimeMillis() / 1000 : C0386a.m293t();
    }

    public static boolean getRuningFlag() {
        return C0395a.m384a();
    }

    public static int getSid() {
        if (!f186b) {
            return 0;
        }
        return C0389d.m306a();
    }

    public static boolean getTestConn() {
        return C0386a.f210c;
    }

    public static long getUid() {
        if (!f186b) {
            return 0;
        }
        return C0389d.m331d(null);
    }

    public static boolean init(Context context, boolean z) {
        if (f186b) {
            return true;
        }
        if (context == null) {
            C0501d.m907c("JCoreInterface", "unexcepted - context was null");
            return false;
        } else if (!C0385a.m239a(context)) {
            C0501d.m907c("JCoreInterface", "JCore init failed");
            return false;
        } else {
            f186b = true;
            C0460q.m709a(context);
            C0334a.m8a(context.getApplicationContext(), "crash_log", "");
            return true;
        }
    }

    public static void initAction(String str, Class<? extends JAction> cls) {
        C0445b.m624a(str, cls.getName());
    }

    public static void initActionExtra(String str, Class<? extends JActionExtra> cls) {
        C0445b.m627b(str, cls.getName());
    }

    public static void initCrashHandler(Context context) {
        if (C0385a.m239a(context)) {
            C0358e.m109a().mo6235b(context);
        }
    }

    public static boolean isServiceStoped(Context context) {
        return C0389d.m339j(context);
    }

    public static boolean isTcpConnected() {
        return C0389d.m333d();
    }

    public static boolean isValidRegistered() {
        if (!f186b) {
            return false;
        }
        return C0389d.m334e(null);
    }

    public static void onFragmentPause(Context context, String str) {
        if (C0385a.m239a(context)) {
            C0366b.m144a().mo6251b(context, str);
        }
    }

    public static void onFragmentResume(Context context, String str) {
        if (C0385a.m239a(context)) {
            C0366b.m144a().mo6249a(context, str);
        }
    }

    public static void onKillProcess(Context context) {
        if (C0385a.m239a(context)) {
            C0366b.m144a().mo6252c(context);
        }
    }

    public static void onPause(Context context) {
        boolean z = C0334a.f24b;
        if (C0385a.m239a(context) && z) {
            C0366b.m144a().mo6250b(context);
        }
    }

    public static void onResume(Context context) {
        boolean z = C0334a.f24b;
        if (C0385a.m239a(context) && z) {
            C0366b.m144a().mo6248a(context);
        }
    }

    public static void processCtrlReport(int i) {
        C0375a.m170a(i);
    }

    public static void register(Context context) {
        if (init(context, true)) {
            C0453j.m665a().mo6568b(context, "intent.INIT", new Bundle());
        }
    }

    public static void report(Context context, JSONObject jSONObject, boolean z) {
        C0460q.m713a(context, new JSONArray().put(jSONObject), z, false);
    }

    public static boolean reportHttpData(Context context, Object obj, String str) {
        if (obj == null) {
            C0460q.m709a(context);
        } else if (obj instanceof JSONObject) {
            C0460q.m714a(context, (JSONObject) obj);
        } else if (obj instanceof JSONArray) {
            C0460q.m711a(context, (JSONArray) obj);
        }
        return true;
    }

    public static void requestPermission(Context context) {
        if (context == null) {
            C0501d.m907c("JCoreInterface", "[requestPermission] context was null");
        } else if (!(context instanceof Activity)) {
            C0501d.m907c("JCoreInterface", "[requestPermission] context must instanceof Activity");
        } else if (VERSION.SDK_INT >= 23 && context.getApplicationInfo().targetSdkVersion >= 23) {
            try {
                List a = C0506a.m937a(context, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_PHONE_STATE"});
                if (a != null && !a.isEmpty()) {
                    Class.forName("android.app.Activity").getDeclaredMethod("requestPermissions", new Class[]{String[].class, Integer.TYPE}).invoke(context, new Object[]{a.toArray(new String[a.size()]), Integer.valueOf(1)});
                }
            } catch (Exception e) {
                C0501d.m907c("JCoreInterface", "#unexcepted - requestPermission e:" + e.getMessage());
            }
        }
    }

    public static void restart(Context context, String str, Bundle bundle, boolean z) {
        if (context == null) {
            C0501d.m907c("JCoreInterface", "unexcepted - context was null");
        } else if (init(context, false)) {
            try {
                bundle.putString("sdktype", str);
                C0453j.m665a().mo6568b(context, z ? "intent.RESTOREPUSH" : "intent.INIT", bundle);
            } catch (Throwable th) {
                C0501d.m908c("JCoreInterface", "restart failed", th);
            }
        }
    }

    public static void sendAction(Context context, String str, Bundle bundle) {
        if (C0385a.m239a(context)) {
            try {
                bundle.putString("sdktype", str);
                C0453j.m665a().mo6568b(context, "run.action", bundle);
            } catch (Throwable th) {
                C0501d.m908c("JCoreInterface", "sendAction failed", th);
            }
        }
    }

    public static void sendData(Context context, String str, int i, byte[] bArr) {
        if (init(context, false)) {
            try {
                Bundle bundle = new Bundle();
                bundle.putString("sdktype", str);
                bundle.putByteArray("datas", bArr);
                bundle.putInt("cmd", i);
                C0453j.m665a().mo6568b(context, "senddata.action", bundle);
            } catch (Throwable th) {
                C0501d.m908c("JCoreInterface", "sendData failed", th);
            }
        }
    }

    public static void sendRequestData(Context context, String str, int i, byte[] bArr) {
        if (init(context, false)) {
            try {
                Bundle bundle = new Bundle();
                bundle.putString("sdktype", str);
                bundle.putByteArray("datas", bArr);
                bundle.putInt("timeout", i);
                C0453j.m665a().mo6568b(context, "sendrequestdata.action", bundle);
            } catch (Throwable th) {
                C0501d.m908c("JCoreInterface", "sendRequestData failed", th);
            }
        }
    }

    public static void setAccountId(String str) {
        C0389d.m329c(C0385a.f198e, str);
    }

    public static void setAnalysisAction(JAnalyticsAction jAnalyticsAction) {
        if (jAnalyticsAction != null) {
            C0334a.f23a = jAnalyticsAction;
        }
    }

    public static void setCanLaunchedStoppedService(boolean z) {
        C0495f.m858a().mo6655b().mo6650a(z);
    }

    public static void setDaemonAction(String str) {
        f185a = str;
        C0495f.m858a().mo6655b().mo6651c(str);
    }

    public static void setDebugMode(boolean z) {
        C0385a.f195b = z;
    }

    public static void setImLBSEnable(Context context, boolean z) {
        if (C0385a.m239a(context)) {
            C0372a.m157a(context, z);
        }
    }

    public static void setLocationReportDelay(Context context, long j) {
        C0389d.m313a(context, "location_report_delay", Long.valueOf(j));
    }

    public static void setLogEnable(boolean z) {
        C0498a.f552a = z;
    }

    public static void setPowerSaveMode(Context context, boolean z) {
        if (f186b) {
            try {
                Bundle bundle = new Bundle();
                bundle.putString("sdktype", C0385a.f194a);
                bundle.putBoolean("key_power_save", z);
                C0453j.m665a().mo6568b(context, "intent.power.save", bundle);
            } catch (Throwable th) {
                C0501d.m908c("JCoreInterface", "setDozeAndPowerEnable to pushservice error", th);
            }
        } else if (context == null) {
            C0501d.m907c("JCoreInterface", "context is null,setDozeAndPowerEnable failed");
        } else {
            C0389d.m332d(context, z);
        }
    }

    public static void setTestConn(boolean z) {
        C0386a.f210c = z;
    }

    public static void setTestConnIPPort(String str, int i) {
        C0386a.f208a = str;
        C0386a.f209b = i;
    }

    public static void stop(Context context, String str, Bundle bundle) {
        if (init(context, false)) {
            try {
                bundle.putString("sdktype", str);
                C0453j.m665a().mo6568b(context, "intent.STOPPUSH", bundle);
            } catch (Throwable th) {
                C0501d.m908c("JCoreInterface", "stop failed", th);
            }
        }
    }

    public static void stopCrashHandler(Context context) {
        if (C0385a.m239a(context)) {
            C0358e.m109a().mo6236c(context);
        }
    }

    public static void testCountryCode(String str) {
    }

    public static void triggerSceneCheck(Context context, int i) {
        try {
            Bundle bundle = new Bundle();
            bundle.putString("sdktype", C0385a.f194a);
            bundle.putInt("key_trigger_scene", i);
            C0453j.m665a().mo6568b(context, "cn.jpush.android.intent.check.notification.state", bundle);
        } catch (Throwable th) {
            C0501d.m908c("JCoreInterface", "triggerSceneCheck to pushservice error", th);
        }
    }
}
