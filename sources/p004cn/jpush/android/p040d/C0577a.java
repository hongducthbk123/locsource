package p004cn.jpush.android.p040d;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.adjust.sdk.Constants;
import com.facebook.internal.AnalyticsEvents;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.common.base.Ascii;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;
import p004cn.jiguang.api.JCoreInterface;
import p004cn.jiguang.api.MultiSpHelper;
import p004cn.jpush.android.C0563b;
import p004cn.jpush.android.api.JPushInterface;
import p004cn.jpush.android.data.C0589b;
import p004cn.jpush.android.p037a.C0546d;
import p004cn.jpush.android.p043ui.PopWinActivity;
import p004cn.jpush.android.p043ui.PushActivity;

/* renamed from: cn.jpush.android.d.a */
public final class C0577a {
    /* renamed from: a */
    public static String m1269a(Context context, String str) {
        String str2 = VERSION.RELEASE + "," + Integer.toString(VERSION.SDK_INT);
        String str3 = Build.MODEL;
        String a = C0585h.m1317a(context, "gsm.version.baseband", "baseband");
        String str4 = Build.DEVICE;
        String channel = JCoreInterface.getChannel();
        if (TextUtils.isEmpty(channel)) {
            channel = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        }
        String f = m1290f(context);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("androidSdkVersion", str2);
            jSONObject.put("model", str3);
            jSONObject.put("baseband", a);
            jSONObject.put("device", str4);
            jSONObject.put("channel", channel);
            jSONObject.put("network", f);
            jSONObject.put("url", str);
        } catch (JSONException e) {
        }
        return jSONObject.toString();
    }

    /* renamed from: b */
    public static boolean m1283b(Context context, String str) {
        if (context != null) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    if (context.getPackageManager().checkPermission(str, context.getPackageName()) == 0) {
                        return true;
                    }
                    return false;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        throw new IllegalArgumentException("empty params");
    }

    /* renamed from: c */
    public static boolean m1285c(Context context, String str) {
        try {
            context.getPackageManager().getReceiverInfo(new ComponentName(context.getPackageName(), str), 128);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    /* renamed from: d */
    public static boolean m1287d(Context context, String str) {
        try {
            context.getPackageManager().getServiceInfo(new ComponentName(context.getPackageName(), str), 128);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    /* renamed from: a */
    private static boolean m1279a(Context context, Class<?> cls) {
        try {
            if (!context.getPackageManager().queryIntentActivities(new Intent(context, cls), 0).isEmpty()) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            C0582e.m1302a("AndroidUtil", "hasActivityResolves error:" + th.getMessage());
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m1280a(Context context, String str, boolean z) {
        try {
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent(str);
            intent.addCategory(context.getPackageName());
            if (!packageManager.queryBroadcastReceivers(intent, 0).isEmpty()) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            C0582e.m1302a("AndroidUtil", "hasReceiverIntentFilter error:" + th.getMessage());
            return false;
        }
    }

    /* renamed from: f */
    private static boolean m1291f(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent(str);
            intent.addCategory(context.getPackageName());
            if (packageManager.queryIntentActivities(intent, 0).isEmpty()) {
                return false;
            }
        } catch (Throwable th) {
            C0582e.m1302a("AndroidUtil", "hasActivityIntentFilter error:" + th.getMessage());
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m1278a(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m1277a() {
        boolean z = false;
        try {
            return Environment.getExternalStorageState().equals("mounted");
        } catch (Throwable th) {
            return z;
        }
    }

    /* renamed from: a */
    public static Intent m1268a(Context context, C0589b bVar, boolean z) {
        Intent intent = new Intent();
        intent.putExtra("isUpdateVersion", false);
        intent.putExtra("body", bVar);
        intent.setAction("cn.jpush.android.ui.PushActivity");
        intent.addCategory(context.getPackageName());
        intent.addFlags(536870912);
        if (!m1292g(context) && VERSION.SDK_INT >= 11) {
            intent.addFlags(32768);
        }
        m1272a(context, intent);
        return intent;
    }

    /* renamed from: f */
    private static String m1290f(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
            }
            String typeName = activeNetworkInfo.getTypeName();
            String subtypeName = activeNetworkInfo.getSubtypeName();
            if (typeName == null) {
                return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
            }
            if (!TextUtils.isEmpty(subtypeName)) {
                return typeName + "," + subtypeName;
            }
            return typeName;
        } catch (Exception e) {
            e.printStackTrace();
            return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        }
    }

    /* renamed from: b */
    public static void m1281b(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            String packageName = context.getApplicationContext().getPackageName();
            if (!packageName.isEmpty()) {
                Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(packageName);
                if (launchIntentForPackage == null) {
                    C0582e.m1306c("AndroidUtil", "Can't get launch intent for this package!");
                    return;
                }
                int i = 268435456;
                if (VERSION.SDK_INT >= 11) {
                    i = 268468224;
                }
                launchIntentForPackage.addFlags(i);
                context.startActivity(launchIntentForPackage);
                return;
            }
            C0582e.m1306c("AndroidUtil", "The package with the given name cannot be found!");
        } catch (Throwable th) {
            C0582e.m1302a("AndroidUtil", "startMainActivity error:" + th.getMessage());
        }
    }

    /* renamed from: g */
    private static boolean m1292g(Context context) {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.processName.equals(context.getPackageName()) && runningAppProcessInfo.importance == 100) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: c */
    public static boolean m1284c(Context context) {
        try {
            if (!MultiSpHelper.getBoolean(context, "notification_enabled", true)) {
                C0582e.m1304b("AndroidUtil", "Notification was disabled by JPushInterface.setPushTime !");
                return false;
            }
            String b = C0563b.m1208b(context);
            if (TextUtils.isEmpty(b)) {
                return true;
            }
            String[] split = b.split("_");
            String str = split[0];
            String str2 = split[1];
            char[] charArray = str.toCharArray();
            String[] split2 = str2.split("\\^");
            Calendar instance = Calendar.getInstance();
            int i = instance.get(7);
            int i2 = instance.get(11);
            for (char valueOf : charArray) {
                if (i == Integer.valueOf(String.valueOf(valueOf)).intValue() + 1) {
                    int intValue = Integer.valueOf(split2[0]).intValue();
                    int intValue2 = Integer.valueOf(split2[1]).intValue();
                    if (i2 >= intValue && i2 <= intValue2) {
                        return true;
                    }
                }
            }
            C0582e.m1304b("AndroidUtil", "Current time is out of the push time - " + b);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    /* renamed from: d */
    public static boolean m1286d(Context context) {
        String string = MultiSpHelper.getString(context, "setting_silence_push_time", "");
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(string);
            int optInt = jSONObject.optInt("startHour", -1);
            int optInt2 = jSONObject.optInt("startMins", -1);
            int optInt3 = jSONObject.optInt("endHour", -1);
            int optInt4 = jSONObject.optInt("endtMins", -1);
            if (optInt < 0 || optInt2 < 0 || optInt3 < 0 || optInt4 < 0 || optInt2 > 59 || optInt4 > 59 || optInt3 > 23 || optInt > 23) {
                return false;
            }
            Calendar instance = Calendar.getInstance();
            int i = instance.get(11);
            int i2 = instance.get(12);
            if (optInt < optInt3) {
                if ((i <= optInt || i >= optInt3) && ((i != optInt || i2 < optInt2) && (i != optInt3 || i2 > optInt4))) {
                    return false;
                }
            } else if (optInt == optInt3) {
                if (optInt2 >= optInt4) {
                    if (i == optInt && i2 > optInt4 && i2 < optInt2) {
                        return false;
                    }
                } else if (i != optInt || i2 < optInt2 || i2 > optInt4) {
                    return false;
                }
            } else if (optInt <= optInt3) {
                return false;
            } else {
                if ((i <= optInt || i > 23) && ((i < 0 || i >= optInt3) && ((i != optInt || i2 < optInt2) && (i != optInt3 || i2 > optInt4)))) {
                    return false;
                }
            }
            C0582e.m1304b("AndroidUtil", "Current time is in the range of silence time - " + optInt + ":" + optInt2 + " ~ " + optInt3 + ":" + optInt4);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    /* renamed from: a */
    public static void m1274a(Context context, String str, Bundle bundle) {
        if (bundle == null) {
            C0582e.m1308d("AndroidUtil", "Bundle should not be null for sendBroadcast.");
            return;
        }
        try {
            Intent intent = new Intent(str);
            bundle.putString(JPushInterface.EXTRA_APP_KEY, JCoreInterface.getAppKey());
            intent.putExtras(bundle);
            String packageName = context.getPackageName();
            intent.addCategory(packageName);
            intent.setPackage(packageName);
            context.sendBroadcast(intent, String.format(Locale.ENGLISH, "%s.permission.JPUSH_MESSAGE", new Object[]{packageName}));
        } catch (Exception e) {
            C0582e.m1306c("AndroidUtil", "sendBroadcast error:" + e.getMessage() + ",action:" + str);
        }
    }

    /* renamed from: a */
    private static void m1272a(Context context, Intent intent) {
        if (VERSION.SDK_INT < 21) {
            try {
                for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(intent, 0)) {
                    if (resolveInfo.activityInfo != null) {
                        String str = resolveInfo.activityInfo.name;
                        if (!TextUtils.isEmpty(str)) {
                            intent.setComponent(new ComponentName(context, str));
                            return;
                        }
                    }
                }
            } catch (Throwable th) {
            }
        }
    }

    /* renamed from: a */
    public static List<String> m1271a(Context context, Intent intent, String str) {
        boolean z;
        ArrayList arrayList = new ArrayList();
        try {
            List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
            PackageManager packageManager = context.getPackageManager();
            for (ResolveInfo resolveInfo : queryBroadcastReceivers) {
                if (resolveInfo.activityInfo != null) {
                    String str2 = resolveInfo.activityInfo.name;
                    if (!TextUtils.isEmpty(str2)) {
                        if (TextUtils.isEmpty(str) || packageManager.checkPermission(str, resolveInfo.activityInfo.packageName) == 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (z) {
                            arrayList.add(str2);
                        }
                    }
                }
            }
        } catch (Throwable th) {
        }
        return arrayList;
    }

    /* renamed from: b */
    public static void m1282b(Context context, Intent intent, String str) {
        String action = intent.getAction();
        if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(action) || JPushInterface.ACTION_NOTIFICATION_OPENED.equals(action)) {
            List<String> a = m1271a(context, intent, str);
            if (!a.isEmpty()) {
                for (String componentName : a) {
                    try {
                        Intent intent2 = (Intent) intent.clone();
                        intent2.setComponent(new ComponentName(context.getPackageName(), componentName));
                        if (TextUtils.isEmpty(str)) {
                            context.sendBroadcast(intent2);
                        } else {
                            context.sendBroadcast(intent2, str);
                        }
                    } catch (Exception e) {
                        C0582e.m1306c("AndroidUtil", "sendBroadcast failed again:" + e.getMessage() + ", action:" + intent.getAction());
                    }
                }
                return;
            }
            C0582e.m1306c("AndroidUtil", "sendBroadcast failed again: receiver not found, action:" + intent.getAction());
        }
    }

    /* renamed from: a */
    public static void m1273a(Context context, C0589b bVar) {
        try {
            Intent intent = new Intent(JPushInterface.ACTION_MESSAGE_RECEIVED);
            intent.putExtra(JPushInterface.EXTRA_APP_KEY, bVar.f818p);
            intent.putExtra(JPushInterface.EXTRA_MESSAGE, bVar.f812j);
            intent.putExtra(JPushInterface.EXTRA_CONTENT_TYPE, bVar.f813k);
            intent.putExtra(JPushInterface.EXTRA_TITLE, bVar.f815m);
            intent.putExtra(JPushInterface.EXTRA_EXTRA, bVar.f816n);
            intent.putExtra(JPushInterface.EXTRA_MSG_ID, bVar.f805c);
            if (bVar.mo6878a()) {
                intent.putExtra(JPushInterface.EXTRA_RICHPUSH_FILE_PATH, bVar.f802I);
            }
            intent.addCategory(bVar.f817o);
            intent.setPackage(context.getPackageName());
            context.sendBroadcast(intent, String.format(Locale.ENGLISH, "%s.permission.JPUSH_MESSAGE", new Object[]{bVar.f817o}));
            if (bVar.f807e != 0) {
                C0546d.m1125a(bVar.f805c, "", bVar.f807e, 1018, context);
            } else {
                C0546d.m1124a(bVar.f805c, 1018, null, context);
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: e */
    public static boolean m1289e(Context context) {
        C0582e.m1302a("AndroidUtil", "action:checkValidManifest");
        if (!m1279a(context, PushActivity.class)) {
            C0582e.m1308d("AndroidUtil", "AndroidManifest.xml missing required activity: " + PushActivity.class.getCanonicalName());
            return false;
        }
        if (!m1279a(context, PopWinActivity.class)) {
            C0582e.m1306c("AndroidUtil", "AndroidManifest.xml missing activity: " + PopWinActivity.class.getCanonicalName());
            C0582e.m1306c("AndroidUtil", "You will unable to use pop-window rich push type.");
        }
        if (m1291f(context, "cn.jpush.android.ui.PushActivity")) {
            return true;
        }
        C0582e.m1308d("AndroidUtil", "AndroidManifest.xml missing required intent filter for PushActivity: cn.jpush.android.ui.PushActivity");
        return false;
    }

    /* renamed from: a */
    public static void m1276a(WebView webView) {
        try {
            if (VERSION.SDK_INT >= 11) {
                webView.removeJavascriptInterface("searchBoxJavaBridge_");
                webView.removeJavascriptInterface("accessibility");
                webView.removeJavascriptInterface("accessibilityTraversal");
            }
            if (VERSION.SDK_INT >= 21) {
                webView.getSettings().setMixedContentMode(0);
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public static void m1275a(WebSettings webSettings) {
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        if (VERSION.SDK_INT >= 11) {
            webSettings.setDisplayZoomControls(false);
        }
        webSettings.setCacheMode(2);
        webSettings.setSaveFormData(false);
        webSettings.setSavePassword(false);
    }

    /* renamed from: a */
    public static String m1270a(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance(Constants.MD5);
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            if (digest == null) {
                return "";
            }
            StringBuffer stringBuffer = new StringBuffer(digest.length * 2);
            for (byte b : digest) {
                stringBuffer.append("0123456789ABCDEF".charAt((b >> 4) & 15)).append("0123456789ABCDEF".charAt(b & Ascii.f977SI));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /* renamed from: e */
    public static Object m1288e(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null || applicationInfo.metaData == null) {
                return null;
            }
            return applicationInfo.metaData.get(str);
        } catch (Throwable th) {
            C0582e.m1306c("AndroidUtil", "#get meta data error:" + th);
            return null;
        }
    }
}
