package p004cn.jiguang.p031g;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.support.p000v4.app.NotificationCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import com.adjust.sdk.Constants;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.places.model.PlaceFields;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Pattern;
import javax.security.auth.x500.X500Principal;
import org.json.JSONArray;
import org.json.JSONObject;
import p004cn.jiguang.api.JCoreInterface;
import p004cn.jiguang.p015d.C0385a;
import p004cn.jiguang.p015d.p016a.C0386a;
import p004cn.jiguang.p015d.p016a.C0388c;
import p004cn.jiguang.p015d.p016a.C0389d;
import p004cn.jiguang.p015d.p017b.C0395a;
import p004cn.jiguang.p015d.p021d.C0448e;
import p004cn.jiguang.p015d.p021d.C0460q;
import p004cn.jiguang.p015d.p026g.C0483d;
import p004cn.jiguang.p029e.C0501d;
import p004cn.jpush.android.api.JPushInterface;
import p004cn.jpush.android.service.AlarmReceiver;
import p004cn.jpush.android.service.DaemonService;
import p004cn.jpush.android.service.DataProvider;
import p004cn.jpush.android.service.PushReceiver;
import p004cn.jpush.android.service.PushService;

/* renamed from: cn.jiguang.g.a */
public final class C0506a {

    /* renamed from: a */
    public static int f560a = 1;

    /* renamed from: b */
    private static List<String> f561b;

    /* renamed from: c */
    private static final ArrayList<String> f562c = new ArrayList<>();

    /* renamed from: d */
    private static final ArrayList<String> f563d = new ArrayList<>();

    /* renamed from: e */
    private static final ArrayList<String> f564e;

    /* renamed from: f */
    private static PushReceiver f565f;

    static {
        ArrayList arrayList = new ArrayList();
        f561b = arrayList;
        arrayList.add("358673013795895");
        f561b.add("004999010640000");
        f561b.add("00000000000000");
        f561b.add("000000000000000");
        f562c.add("android.permission.INTERNET");
        f562c.add("android.permission.WAKE_LOCK");
        f562c.add("android.permission.ACCESS_NETWORK_STATE");
        f563d.add("android.permission.VIBRATE");
        f563d.add("android.permission.CHANGE_WIFI_STATE");
        ArrayList<String> arrayList2 = new ArrayList<>();
        f564e = arrayList2;
        arrayList2.add("android.permission.ACCESS_FINE_LOCATION");
        f564e.add("android.permission.ACCESS_COARSE_LOCATION");
        f564e.add("android.permission.ACCESS_LOCATION_EXTRA_COMMANDS");
        f564e.add("android.permission.ACCESS_WIFI_STATE");
    }

    /* renamed from: A */
    private static boolean m928A(Context context) {
        try {
            if (f565f == null) {
                f565f = new PushReceiver();
                context.registerReceiver(f565f, new IntentFilter("android.intent.action.USER_PRESENT"));
                context.registerReceiver(f565f, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
                intentFilter.addDataScheme("package");
                IntentFilter intentFilter2 = new IntentFilter("android.intent.action.PACKAGE_REMOVED");
                intentFilter2.addDataScheme("package");
                context.registerReceiver(f565f, intentFilter);
                context.registerReceiver(f565f, intentFilter2);
                IntentFilter intentFilter3 = new IntentFilter(JPushInterface.ACTION_NOTIFICATION_RECEIVED_PROXY);
                intentFilter3.setPriority(1000);
                intentFilter3.addCategory(context.getPackageName());
                context.registerReceiver(f565f, intentFilter3);
                return true;
            }
            C0501d.m903a("AndroidUtil", "has register in code");
            return true;
        } catch (Exception e) {
            C0501d.m907c("AndroidUtil", "Register PushReceiver in code  failed:" + e.getMessage());
            return false;
        }
    }

    /* renamed from: a */
    public static int m929a(byte b) {
        return b & 255;
    }

    /* renamed from: a */
    public static ComponentInfo m930a(Context context, String str, Class<?> cls) {
        ComponentInfo[] componentInfoArr;
        if (context == null || TextUtils.isEmpty(str) || cls == null) {
            return null;
        }
        try {
            int i = Service.class.isAssignableFrom(cls) ? 4 : BroadcastReceiver.class.isAssignableFrom(cls) ? 2 : Activity.class.isAssignableFrom(cls) ? 1 : ContentProvider.class.isAssignableFrom(cls) ? 8 : 0;
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, i);
            switch (i) {
                case 1:
                    componentInfoArr = packageInfo.activities;
                    break;
                case 2:
                    componentInfoArr = packageInfo.receivers;
                    break;
                case 4:
                    componentInfoArr = packageInfo.services;
                    break;
                case 8:
                    componentInfoArr = packageInfo.providers;
                    break;
                default:
                    componentInfoArr = null;
                    break;
            }
            if (componentInfoArr == null) {
                return null;
            }
            String name = cls.getName();
            for (ComponentInfo componentInfo : componentInfoArr) {
                if (name.equals(componentInfo.name)) {
                    return componentInfo;
                }
            }
            return null;
        } catch (Throwable th) {
            C0501d.m907c("AndroidUtil", "hasComponent error:" + th.getMessage());
            return null;
        }
    }

    /* renamed from: a */
    public static String m931a() {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            if (new File("/proc/cpuinfo").exists()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/proc/cpuinfo")));
                String str = null;
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    } else if (readLine.contains("Processor")) {
                        int indexOf = readLine.indexOf(":");
                        if (indexOf >= 0 && indexOf < readLine.length() - 1) {
                            str = readLine.substring(indexOf + 1).trim();
                        }
                        if (str != null && !stringBuffer.toString().contains(str)) {
                            stringBuffer.append(str);
                        }
                    }
                }
                bufferedReader.close();
            }
        } catch (Throwable th) {
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public static String m932a(Context context) {
        if (context == null || context.getResources() == null) {
            return "0*0";
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (displayMetrics == null) {
            return "0*0";
        }
        int i = displayMetrics.widthPixels;
        return i + "*" + displayMetrics.heightPixels;
    }

    /* renamed from: a */
    public static String m933a(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(Constants.MD5);
            char[] charArray = str.toCharArray();
            byte[] bArr = new byte[charArray.length];
            for (int i = 0; i < charArray.length; i++) {
                bArr[i] = (byte) charArray[i];
            }
            byte[] digest = instance.digest(bArr);
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                byte b2 = b & 255;
                if (b2 < 16) {
                    stringBuffer.append(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                }
                stringBuffer.append(Integer.toHexString(b2));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: a */
    public static String m934a(byte[] bArr) {
        try {
            byte[] digest = MessageDigest.getInstance(Constants.MD5).digest(bArr);
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                byte b2 = b & 255;
                if (b2 < 16) {
                    stringBuffer.append(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                }
                stringBuffer.append(Integer.toHexString(b2));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: a */
    public static ArrayList<String> m935a(String[] strArr) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(strArr).getInputStream()));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        arrayList.add(readLine);
                    } else {
                        try {
                            return arrayList;
                        } catch (IOException e) {
                            e.printStackTrace();
                            return arrayList;
                        }
                    }
                } catch (Exception e2) {
                    try {
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    return null;
                } finally {
                    try {
                        bufferedReader.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
            }
        } catch (Exception e5) {
            return null;
        }
    }

    /* renamed from: a */
    private static List<String> m936a(Context context, Intent intent, String str) {
        ArrayList arrayList = new ArrayList();
        try {
            List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
            PackageManager packageManager = context.getPackageManager();
            for (ResolveInfo resolveInfo : queryBroadcastReceivers) {
                if (resolveInfo.activityInfo != null) {
                    String str2 = resolveInfo.activityInfo.name;
                    if (!TextUtils.isEmpty(str2)) {
                        if (TextUtils.isEmpty(str) || packageManager.checkPermission(str, resolveInfo.activityInfo.packageName) == 0) {
                            arrayList.add(str2);
                        }
                    }
                }
            }
        } catch (Throwable th) {
        }
        return arrayList;
    }

    /* renamed from: a */
    public static List<String> m937a(Context context, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 4; i++) {
            String str = strArr[i];
            if (!m944a(context, str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static JSONObject m938a(String str, String str2) {
        if (str2 == null || str2.length() == 0) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("data", str2);
            C0460q.m707a((Context) null, jSONObject, str);
            return jSONObject;
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    public static JSONObject m939a(String str, JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("data", jSONArray);
            C0460q.m707a((Context) null, jSONObject, str);
            return jSONObject;
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    public static void m940a(Context context, String str, int i) {
        int i2;
        Notification notification;
        if (m994v(context)) {
            if (!m943a(context, PushReceiver.class)) {
                new Handler(Looper.getMainLooper()).post(new C0509b(context, str));
                return;
            }
            Intent intent = new Intent(context, PushReceiver.class);
            intent.setAction("cn.jpush.android.intent.NOTIFICATION_OPENED_PROXY");
            intent.addCategory(context.getPackageName());
            intent.putExtra("debug_notification", true);
            intent.putExtra("toastText", str);
            intent.putExtra("type", -1);
            PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, 134217728);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            try {
                i2 = context.getPackageManager().getApplicationInfo(context.getApplicationContext().getPackageName(), 0).icon;
            } catch (Throwable th) {
                C0501d.m906b("AndroidUtil", "failed to get application info and icon.", th);
                i2 = 17301586;
            }
            String str2 = "Jiguang提示：包名和AppKey不匹配";
            String str3 = "请到 Portal 上获取您的包名和AppKey并更新AndroidManifest相应字段";
            long currentTimeMillis = System.currentTimeMillis();
            if (VERSION.SDK_INT >= 11) {
                notification = new Builder(context.getApplicationContext()).setContentTitle(str2).setContentText(str3).setContentIntent(broadcast).setSmallIcon(i2).setTicker(str).setWhen(currentTimeMillis).getNotification();
                notification.flags = 34;
            } else {
                Notification notification2 = new Notification(i2, str, currentTimeMillis);
                notification2.flags = 34;
                try {
                    Class.forName("android.app.Notification").getDeclaredMethod("setLatestEventInfo", new Class[]{Context.class, CharSequence.class, CharSequence.class, PendingIntent.class}).invoke(notification2, new Object[]{context, str2, str3, broadcast});
                    notification = notification2;
                } catch (Exception e) {
                    notification = notification2;
                }
            }
            if (notification != null) {
                notificationManager.notify(str.hashCode(), notification);
            }
        }
    }

    /* renamed from: a */
    public static void m941a(Context context, String str, Bundle bundle) {
        if (bundle == null) {
            C0501d.m909d("AndroidUtil", "Bundle should not be null for sendBroadcast.");
            return;
        }
        Intent intent = new Intent(str);
        try {
            bundle.putString(JPushInterface.EXTRA_APP_KEY, C0389d.m338i(context));
            intent.putExtras(bundle);
            String packageName = context.getPackageName();
            intent.addCategory(packageName);
            intent.setPackage(packageName);
            context.sendBroadcast(intent);
        } catch (Throwable th) {
            C0501d.m907c("AndroidUtil", "sendBroadcast error:" + th.getMessage() + ",action:" + str);
            m951b(context, intent, null);
        }
    }

    /* renamed from: a */
    public static void m942a(Context context, String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        if (str2 != null) {
            bundle.putString(str2, str3);
        }
        m941a(context, str, bundle);
    }

    /* renamed from: a */
    private static boolean m943a(Context context, Class<?> cls) {
        try {
            boolean z = !context.getPackageManager().queryBroadcastReceivers(new Intent(context, cls), 0).isEmpty();
            if (z) {
                return z;
            }
            try {
                return m930a(context, context.getPackageName(), cls) != null;
            } catch (Throwable th) {
                return z;
            }
        } catch (Throwable th2) {
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m944a(Context context, String str) {
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

    /* renamed from: a */
    private static boolean m945a(Context context, String str, String str2) {
        try {
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent(str2);
            intent.setPackage(context.getPackageName());
            for (ResolveInfo resolveInfo : packageManager.queryBroadcastReceivers(intent, 0)) {
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                if (activityInfo != null && activityInfo.name.equals(str)) {
                    return true;
                }
            }
        } catch (Throwable th) {
            C0501d.m907c("AndroidUtil", "hasReceiverIntentFilterPackage error:" + th.getMessage());
        }
        return false;
    }

    /* renamed from: a */
    private static boolean m946a(Context context, String str, boolean z) {
        try {
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent(str);
            intent.addCategory(context.getPackageName());
            return !packageManager.queryIntentServices(intent, 0).isEmpty();
        } catch (Throwable th) {
            C0501d.m907c("AndroidUtil", "hasServiceIntentFilter error:" + th.getMessage());
            return false;
        }
    }

    /* renamed from: a */
    private static boolean m947a(String str, String str2, String str3, String str4) {
        return (C0530k.m1099a(str3) || C0530k.m1099a(str4)) ? str.equals(str2) : str.equals(str2) && str3.equalsIgnoreCase(str4);
    }

    /* renamed from: b */
    public static String m948b() {
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                while (true) {
                    if (inetAddresses.hasMoreElements()) {
                        InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                        if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                            return inetAddress.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /* renamed from: b */
    public static String m949b(Context context, String str) {
        String s = m991s(context);
        if (!C0530k.m1104e(s)) {
            s = m992t(context);
        }
        return !C0530k.m1104e(s) ? str : s;
    }

    /* renamed from: b */
    public static String m950b(String str) {
        try {
            byte[] digest = MessageDigest.getInstance(Constants.MD5).digest(str.getBytes("utf-8"));
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                byte b2 = b & 255;
                if (b2 < 16) {
                    stringBuffer.append(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                }
                stringBuffer.append(Integer.toHexString(b2));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: b */
    private static void m951b(Context context, Intent intent, String str) {
        String action = intent.getAction();
        if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(action)) {
            List<String> a = m936a(context, intent, (String) null);
            if (!a.isEmpty()) {
                for (String componentName : a) {
                    try {
                        Intent intent2 = (Intent) intent.clone();
                        intent2.setComponent(new ComponentName(context.getPackageName(), componentName));
                        if (TextUtils.isEmpty(null)) {
                            context.sendBroadcast(intent2);
                        } else {
                            context.sendBroadcast(intent2, null);
                        }
                    } catch (Exception e) {
                        C0501d.m907c("AndroidUtil", "sendBroadcast failed again:" + e.getMessage() + ", action:" + action);
                    }
                }
                return;
            }
            C0501d.m907c("AndroidUtil", "sendBroadcast failed again: receiver not found, action:" + action);
        }
    }

    /* renamed from: b */
    public static boolean m952b(Context context) {
        ProviderInfo providerInfo;
        try {
            ProviderInfo[] providerInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 8).providers;
            int length = providerInfoArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    providerInfo = null;
                    break;
                }
                providerInfo = providerInfoArr[i];
                if (providerInfo.name.equals(DataProvider.class.getName())) {
                    break;
                }
                i++;
            }
            return providerInfo != null;
        } catch (Throwable th) {
            return false;
        }
    }

    /* renamed from: b */
    private static boolean m953b(Context context, Class<?> cls) {
        try {
            boolean z = !context.getPackageManager().queryIntentServices(new Intent(context, cls), 0).isEmpty();
            if (z) {
                return z;
            }
            try {
                return m930a(context, context.getPackageName(), cls) != null;
            } catch (Throwable th) {
                return z;
            }
        } catch (Throwable th2) {
            return false;
        }
    }

    /* renamed from: c */
    public static int m954c(String str) {
        String[] split = str.split("\\.");
        return Integer.parseInt(split[2]) + (Integer.parseInt(split[0]) << 16) + (Integer.parseInt(split[1]) << 8);
    }

    /* renamed from: c */
    public static String m955c(Context context, String str) {
        try {
            return m944a(context, "android.permission.READ_PHONE_STATE") ? ((TelephonyManager) context.getSystemService(PlaceFields.PHONE)).getSimSerialNumber() : str;
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    /* renamed from: c */
    private static boolean m956c() {
        boolean z = false;
        try {
            return Environment.getExternalStorageState().equals("mounted");
        } catch (Throwable th) {
            return z;
        }
    }

    /* renamed from: c */
    public static boolean m957c(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: d */
    private static String m958d() {
        String str = null;
        try {
            str = Environment.getExternalStorageDirectory().getPath();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (Exception e2) {
        }
        return !C0530k.m1099a(str) ? str + "/data/" : str;
    }

    /* renamed from: d */
    public static String m959d(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
            }
            String typeName = activeNetworkInfo.getTypeName();
            String subtypeName = activeNetworkInfo.getSubtypeName();
            return typeName == null ? AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN : !C0530k.m1099a(subtypeName) ? typeName + "," + subtypeName : typeName;
        } catch (Exception e) {
            e.printStackTrace();
            return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        }
    }

    /* renamed from: d */
    public static String m960d(Context context, String str) {
        String str2;
        try {
            str2 = ((TelephonyManager) context.getSystemService(PlaceFields.PHONE)).getDeviceId();
        } catch (Exception e) {
            str2 = null;
        }
        return C0530k.m1103d(str2) ? str2 : str;
    }

    /* renamed from: d */
    private static boolean m961d(String str) {
        if (!C0530k.m1103d(str) || str.length() < 10) {
            return false;
        }
        for (int i = 0; i < f561b.size(); i++) {
            if (str.equals(f561b.get(i)) || str.startsWith((String) f561b.get(i))) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: e */
    public static int m962e(Context context) {
        Intent intent;
        Intent intent2 = null;
        if (context == null) {
            return -1;
        }
        try {
            intent = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        } catch (SecurityException e) {
            e.printStackTrace();
            intent = intent2;
        } catch (Exception e2) {
            e2.printStackTrace();
            intent = intent2;
        }
        if (intent == null) {
            return -1;
        }
        int intExtra = intent.getIntExtra("status", -1);
        if (intExtra == 2 || intExtra == 5) {
            return intent.getIntExtra("plugged", -1);
        }
        return -1;
    }

    /* renamed from: e */
    private static String m963e() {
        String d = m958d();
        if (d == null) {
            return null;
        }
        return d + ".push_deviceid";
    }

    /* renamed from: e */
    public static String m964e(Context context, String str) {
        try {
            return m944a(context, "android.permission.READ_PHONE_STATE") ? ((TelephonyManager) context.getSystemService(PlaceFields.PHONE)).getSubscriberId() : str;
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0073 A[SYNTHETIC, Splitter:B:31:0x0073] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x007e A[SYNTHETIC, Splitter:B:37:0x007e] */
    /* renamed from: e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String m965e(java.lang.String r5) {
        /*
            r0 = 0
            java.lang.String r1 = m958d()
            boolean r2 = p004cn.jiguang.p031g.C0530k.m1099a(r1)
            if (r2 == 0) goto L_0x000d
            r5 = r0
        L_0x000c:
            return r5
        L_0x000d:
            java.io.File r2 = new java.io.File
            r2.<init>(r1)
            boolean r3 = r2.exists()
            if (r3 != 0) goto L_0x001b
            r2.mkdir()     // Catch:{ Exception -> 0x0082 }
        L_0x001b:
            java.lang.String r2 = m963e()
            boolean r2 = p004cn.jiguang.p031g.C0530k.m1099a(r2)
            if (r2 == 0) goto L_0x002e
            java.lang.String r1 = "AndroidUtil"
            java.lang.String r2 = "get device id  sd card file path fail"
            p004cn.jiguang.p029e.C0501d.m909d(r1, r2)
            r5 = r0
            goto L_0x000c
        L_0x002e:
            java.io.File r2 = new java.io.File
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r3 = ".push_deviceid"
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.String r1 = r1.toString()
            r2.<init>(r1)
            boolean r1 = r2.exists()
            if (r1 == 0) goto L_0x004f
            r2.delete()     // Catch:{ SecurityException -> 0x0069 }
        L_0x004f:
            r2.createNewFile()     // Catch:{ IOException -> 0x006c }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x006f, all -> 0x0078 }
            r1.<init>(r2)     // Catch:{ IOException -> 0x006f, all -> 0x0078 }
            byte[] r2 = r5.getBytes()     // Catch:{ IOException -> 0x008a, all -> 0x0088 }
            r1.write(r2)     // Catch:{ IOException -> 0x008a, all -> 0x0088 }
            r1.flush()     // Catch:{ IOException -> 0x008a, all -> 0x0088 }
            if (r1 == 0) goto L_0x000c
            r1.close()     // Catch:{ IOException -> 0x0067 }
            goto L_0x000c
        L_0x0067:
            r0 = move-exception
            goto L_0x000c
        L_0x0069:
            r1 = move-exception
            r5 = r0
            goto L_0x000c
        L_0x006c:
            r1 = move-exception
            r5 = r0
            goto L_0x000c
        L_0x006f:
            r1 = move-exception
            r1 = r0
        L_0x0071:
            if (r1 == 0) goto L_0x0076
            r1.close()     // Catch:{ IOException -> 0x0084 }
        L_0x0076:
            r5 = r0
            goto L_0x000c
        L_0x0078:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L_0x007c:
            if (r1 == 0) goto L_0x0081
            r1.close()     // Catch:{ IOException -> 0x0086 }
        L_0x0081:
            throw r0
        L_0x0082:
            r2 = move-exception
            goto L_0x001b
        L_0x0084:
            r1 = move-exception
            goto L_0x0076
        L_0x0086:
            r1 = move-exception
            goto L_0x0081
        L_0x0088:
            r0 = move-exception
            goto L_0x007c
        L_0x008a:
            r2 = move-exception
            goto L_0x0071
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p031g.C0506a.m965e(java.lang.String):java.lang.String");
    }

    /* renamed from: f */
    private static int m966f(String str) {
        if (C0530k.m1099a(str) || Pattern.matches("[0]*", str)) {
            return 0;
        }
        if (Pattern.matches("[0-9]{15}", str)) {
            return 1;
        }
        return Pattern.matches("[a-f0-9A-F]{14}", str) ? 2 : 0;
    }

    /* renamed from: f */
    private static String m967f() {
        String e = m963e();
        if (C0530k.m1099a(e)) {
            C0501d.m909d("AndroidUtil", "get device id  sd card file path fail");
            return null;
        }
        File file = new File(e);
        if (file.exists()) {
            try {
                ArrayList a = C0525f.m1083a((InputStream) new FileInputStream(file));
                if (a.size() > 0) {
                    return (String) a.get(0);
                }
            } catch (Exception e2) {
                return null;
            }
        }
        return null;
    }

    /* renamed from: f */
    public static String m968f(Context context) {
        String c = C0386a.m262c(context);
        if (m961d(c)) {
            return c;
        }
        String w = m995w(context);
        C0386a.m259b(context, w);
        return w;
    }

    /* renamed from: f */
    public static void m969f(Context context, String str) {
        if (!C0530k.m1099a(str)) {
            m984n(context, str);
            m980l(context, str);
            C0389d.m318a(context, str);
        }
    }

    /* renamed from: g */
    public static ApplicationInfo m970g(Context context, String str) {
        try {
            return context.getPackageManager().getApplicationInfo(str, 128);
        } catch (Throwable th) {
            return null;
        }
    }

    /* renamed from: g */
    public static String m971g(Context context) {
        String str = "";
        try {
            str = Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception | SecurityException e) {
        }
        return C0530k.m1103d(str) ? str : "";
    }

    /* renamed from: h */
    public static String m972h(Context context) {
        String str = null;
        String c = C0389d.m328c(context);
        if (C0530k.m1103d(c)) {
            f560a = C0515c.f586d - 1;
            return c;
        }
        String m = m982m(context, c);
        if (C0530k.m1103d(m)) {
            f560a = C0515c.f584b - 1;
            m980l(context, m);
            C0389d.m318a(context, m);
            return m;
        }
        if (m956c() && m944a(context, "android.permission.WRITE_EXTERNAL_STORAGE") && (VERSION.SDK_INT < 23 || (m944a(context, "android.permission.WRITE_EXTERNAL_STORAGE") && m944a(context, "android.permission.READ_EXTERNAL_STORAGE")))) {
            str = m967f();
        }
        if (C0530k.m1103d(str)) {
            f560a = C0515c.f585c - 1;
            m984n(context, str);
            C0389d.m318a(context, str);
            return str;
        }
        String str2 = VERSION.SDK_INT < 23 ? m960d(context, str) : "";
        String g = m971g(context);
        String b = m949b(context, "");
        String uuid = UUID.randomUUID().toString();
        String a = m933a(str2 + g + b + uuid);
        if (C0530k.m1099a(a)) {
            a = uuid;
        }
        C0389d.m318a(context, a);
        f560a = C0515c.f583a - 1;
        m984n(context, a);
        m980l(context, a);
        return a;
    }

    /* renamed from: h */
    public static String m973h(Context context, String str) {
        try {
            return context.getPackageManager().getServiceInfo(new ComponentName(context.getPackageName(), str), 128).processName;
        } catch (Throwable th) {
            return "";
        }
    }

    /* renamed from: i */
    public static boolean m974i(Context context) {
        String str = context.getApplicationInfo().sourceDir;
        if (C0530k.m1099a(str)) {
            return false;
        }
        if (str.startsWith("/system/app/")) {
            return true;
        }
        if (str.startsWith("/data/app/")) {
        }
        return false;
    }

    /* renamed from: i */
    public static boolean m975i(Context context, String str) {
        if (C0530k.m1099a(str)) {
            return false;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 0) != null;
        } catch (Throwable th) {
            return false;
        }
    }

    /* renamed from: j */
    public static void m976j(Context context) {
        long i = (long) (C0386a.m279i() * 1000);
        long currentTimeMillis = System.currentTimeMillis() + i;
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, new Intent(context, AlarmReceiver.class), 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        try {
            if (VERSION.SDK_INT >= 19) {
                alarmManager.setWindow(0, currentTimeMillis, 0, broadcast);
            } else {
                alarmManager.setInexactRepeating(0, currentTimeMillis, i, broadcast);
            }
        } catch (Exception e) {
            C0501d.m907c("AndroidUtil", "can't trigger alarm cause by exception:" + e.getMessage());
        }
    }

    /* renamed from: j */
    private static boolean m977j(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("empty params");
        }
        try {
            context.getPackageManager().getPermissionInfo(str, 128);
            return true;
        } catch (Throwable th) {
            C0501d.m907c("AndroidUtil", "hasPermissionDefined error:" + th.getMessage());
            return false;
        }
    }

    /* renamed from: k */
    public static void m978k(Context context) {
        try {
            ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(PendingIntent.getBroadcast(context, 0, new Intent(context, AlarmReceiver.class), 0));
        } catch (Exception e) {
        }
    }

    /* renamed from: k */
    private static boolean m979k(Context context, String str) {
        try {
            context.getPackageManager().getReceiverInfo(new ComponentName(context.getPackageName(), str), 128);
            return true;
        } catch (Throwable th) {
            C0501d.m907c("AndroidUtil", "hasReceiver error:" + th.getMessage());
            return false;
        }
    }

    /* renamed from: l */
    private static String m980l(Context context, String str) {
        if (!m956c() || !m944a(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            return null;
        }
        if (VERSION.SDK_INT < 23) {
            return m965e(str);
        }
        if (!m944a(context, "android.permission.WRITE_EXTERNAL_STORAGE") || !m944a(context, "android.permission.READ_EXTERNAL_STORAGE")) {
            return null;
        }
        return m965e(str);
    }

    /* renamed from: l */
    public static void m981l(Context context) {
        boolean z = true;
        C0388c w = C0386a.m296w();
        if (!w.mo6355d()) {
            String a = w.mo6352a();
            String b = w.mo6353b();
            String c = w.mo6354c();
            String d = m960d(context, "");
            String g = m971g(context);
            if (C0530k.m1099a(g)) {
                g = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
            }
            String b2 = m949b(context, "");
            if (C0530k.m1099a(b2)) {
                b2 = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
            }
            int f = m966f(a);
            int f2 = m966f(d);
            if (f == 0 || f2 == 0) {
                z = m947a(g, b, b2, c);
            } else if (!((1 == f && 2 == f2) || (2 == f && 1 == f2))) {
                if (f != f2) {
                    z = false;
                } else if (d.equals(a)) {
                    z = d.equals(a) && g.equals(b);
                } else {
                    z = m947a(g, b, b2, c);
                }
            }
            if (!z) {
                m983m(context);
            }
        }
    }

    /* renamed from: m */
    private static String m982m(Context context, String str) {
        if (!m944a(context, "android.permission.WRITE_SETTINGS")) {
            return str;
        }
        try {
            return System.getString(context.getContentResolver(), "devcie_id_generated");
        } catch (Exception e) {
            return str;
        }
    }

    /* renamed from: m */
    public static void m983m(Context context) {
        C0389d.m336g(context);
        m984n(context, "");
        m980l(context, "");
        C0483d.m792d().mo6626b(context);
    }

    /* renamed from: n */
    private static String m984n(Context context, String str) {
        if (m944a(context, "android.permission.WRITE_SETTINGS")) {
            try {
                if (System.putString(context.getContentResolver(), "devcie_id_generated", str)) {
                    return str;
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    /* renamed from: n */
    public static boolean m985n(Context context) {
        String a = C0531l.m1105a(context, "ro.product.brand");
        String a2 = C0531l.m1105a(context, "ro.miui.ui.version.name");
        if (!TextUtils.isEmpty(a) && "Xiaomi".equals(a) && !TextUtils.isEmpty(a2)) {
            String a3 = C0531l.m1105a(context, "ro.build.version.incremental");
            if (!TextUtils.isEmpty(a3) && a3.startsWith("V7.1")) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: o */
    public static double m986o(Context context) {
        double pow;
        double pow2;
        Point point = new Point();
        if (context instanceof Activity) {
            Display defaultDisplay = ((Activity) context).getWindowManager().getDefaultDisplay();
            if (VERSION.SDK_INT >= 17) {
                defaultDisplay.getRealSize(point);
            } else if (VERSION.SDK_INT >= 13) {
                defaultDisplay.getSize(point);
            } else {
                point.x = defaultDisplay.getWidth();
                point.y = defaultDisplay.getHeight();
            }
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (context instanceof Activity) {
            pow = Math.pow((double) (((float) point.x) / displayMetrics.xdpi), 2.0d);
            pow2 = Math.pow((double) (((float) point.y) / displayMetrics.ydpi), 2.0d);
        } else {
            pow = Math.pow((double) (((float) displayMetrics.widthPixels) / displayMetrics.xdpi), 2.0d);
            pow2 = Math.pow((double) (((float) displayMetrics.heightPixels) / displayMetrics.ydpi), 2.0d);
        }
        return Math.sqrt(pow2 + pow);
    }

    /* renamed from: o */
    private static boolean m987o(Context context, String str) {
        try {
            if (context.getPackageManager().getServiceInfo(new ComponentName(context.getPackageName(), str), 128).processName.contains(context.getPackageName() + ":")) {
                return true;
            }
        } catch (NameNotFoundException | NullPointerException e) {
        }
        return false;
    }

    /* renamed from: p */
    public static boolean m988p(Context context) {
        return m953b(context, PushService.class);
    }

    /* renamed from: q */
    public static boolean m989q(Context context) {
        boolean z;
        C0501d.m903a("AndroidUtil", "action:checkValidManifest");
        if (C0448e.m641a().mo6564f()) {
            if (!m953b(context, PushService.class)) {
                C0501d.m909d("AndroidUtil", "AndroidManifest.xml missing required service: " + PushService.class.getCanonicalName());
                z = false;
            } else {
                z = true;
            }
            if (!z) {
                return false;
            }
            if (!m952b(context)) {
                C0501d.m909d("AndroidUtil", "AndroidManifest.xml missing required ContentProvider: " + DataProvider.class.getCanonicalName());
                return false;
            }
            if (!m953b(context, DaemonService.class)) {
                C0501d.m907c("AndroidUtil", "AndroidManifest.xml missing required service: " + DaemonService.class.getCanonicalName());
                JCoreInterface.setCanLaunchedStoppedService(false);
            } else if (!m946a(context, JCoreInterface.getDaemonAction(), true)) {
                C0501d.m907c("AndroidUtil", "AndroidManifest.xml missing intent filter for DaemonService: " + JCoreInterface.getDaemonAction());
                JCoreInterface.setCanLaunchedStoppedService(false);
            } else {
                JCoreInterface.setCanLaunchedStoppedService(true);
            }
            if (m987o(context, PushService.class.getCanonicalName())) {
                C0385a.f203j = true;
            } else {
                C0385a.f203j = false;
            }
            if (!C0389d.m339j(context)) {
                C0395a.m383a(context, true);
                if (!m943a(context, AlarmReceiver.class)) {
                    C0501d.m909d("AndroidUtil", "AndroidManifest.xml missing required receiver: " + AlarmReceiver.class.getCanonicalName());
                    return false;
                }
                if (!m943a(context, PushReceiver.class)) {
                    C0501d.m909d("AndroidUtil", "AndroidManifest.xml missing required receiver: " + PushReceiver.class.getCanonicalName());
                    if (!m928A(context)) {
                        return false;
                    }
                }
                if (m945a(context, PushReceiver.class.getCanonicalName(), "android.intent.action.BOOT_COMPLETED")) {
                    C0501d.m907c("AndroidUtil", "PushReceiver should not have intent filter -- android.intent.action.BOOT_COMPLETED, Please remove the intent filter in AndroidManifest.xml");
                }
            }
            String str = context.getPackageName() + ".permission.JPUSH_MESSAGE";
            if (!m977j(context, str)) {
                C0501d.m909d("AndroidUtil", "The permission should be defined - " + str);
                return false;
            }
            f562c.add(str);
        }
        Iterator it = f562c.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            if (!m944a(context.getApplicationContext(), str2)) {
                C0501d.m909d("AndroidUtil", "The permissoin is required - " + str2);
                return false;
            }
        }
        if (VERSION.SDK_INT < 23) {
            if (!m944a(context.getApplicationContext(), "android.permission.WRITE_EXTERNAL_STORAGE")) {
                C0501d.m909d("AndroidUtil", "The permissoin is required - android.permission.WRITE_EXTERNAL_STORAGE");
                return false;
            } else if (!m944a(context.getApplicationContext(), "android.permission.WRITE_SETTINGS")) {
                C0501d.m909d("AndroidUtil", "The permissoin is required - android.permission.WRITE_SETTINGS");
                return false;
            }
        }
        Iterator it2 = f563d.iterator();
        while (it2.hasNext()) {
            m944a(context.getApplicationContext(), (String) it2.next());
        }
        Iterator it3 = f564e.iterator();
        while (it3.hasNext()) {
            m944a(context.getApplicationContext(), (String) it3.next());
        }
        return true;
    }

    /* renamed from: r */
    public static void m990r(Context context) {
        if (f565f != null && !m979k(context, PushReceiver.class.getCanonicalName())) {
            try {
                context.unregisterReceiver(f565f);
                f565f = null;
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: s */
    private static String m991s(Context context) {
        String str = "";
        if (VERSION.SDK_INT < 23 && m944a(context, "android.permission.ACCESS_WIFI_STATE")) {
            try {
                return ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getMacAddress();
            } catch (Exception e) {
            }
        }
        return str;
    }

    /* renamed from: t */
    private static String m992t(Context context) {
        String str;
        boolean z = false;
        String str2 = "";
        boolean z2 = m944a(context, "android.permission.ACCESS_WIFI_STATE") ? ((WifiManager) context.getApplicationContext().getSystemService("wifi")).isWifiEnabled() : false;
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (true) {
                if (!networkInterfaces.hasMoreElements()) {
                    str = str2;
                    break;
                }
                NetworkInterface networkInterface = (NetworkInterface) networkInterfaces.nextElement();
                if ("wlan0".equalsIgnoreCase(networkInterface.getName())) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (!(hardwareAddress == null || hardwareAddress.length == 0)) {
                        StringBuilder sb = new StringBuilder();
                        for (byte valueOf : hardwareAddress) {
                            sb.append(String.format(Locale.ENGLISH, "%02x:", new Object[]{Byte.valueOf(valueOf)}));
                        }
                        if (sb.length() > 0) {
                            sb.deleteCharAt(sb.length() - 1);
                        }
                        str = sb.toString();
                    }
                }
            }
        } catch (Exception e) {
            str = str2;
        }
        if (z2) {
            return str;
        }
        String r = C0386a.m291r();
        if (!TextUtils.isEmpty(r) && !TextUtils.isEmpty(str)) {
            z = r.equals(m933a(str.toLowerCase() + Build.MODEL));
        }
        return !z ? "" : str;
    }

    /* renamed from: u */
    private static String m993u(Context context) {
        try {
            String b = m949b(context, "");
            if (b == null || b.equals("")) {
                return null;
            }
            return m933a(b.toLowerCase() + Build.MODEL);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: v */
    private static boolean m994v(Context context) {
        String str;
        boolean z = false;
        try {
            X500Principal x500Principal = new X500Principal("CN=Android Debug,O=Android,C=US");
            String[] strArr = {"CN=Android Debug", "O=Android", "C=US"};
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
            CertificateFactory instance = CertificateFactory.getInstance("X.509");
            for (Signature byteArray : signatureArr) {
                X509Certificate x509Certificate = (X509Certificate) instance.generateCertificate(new ByteArrayInputStream(byteArray.toByteArray()));
                z = x509Certificate.getSubjectX500Principal().equals(x500Principal);
                if (z) {
                    return z;
                }
                try {
                    str = x509Certificate.getSubjectX500Principal().getName();
                } catch (Exception e) {
                    str = null;
                }
                if (str != null) {
                    try {
                        if (str.contains(strArr[0]) && str.contains(strArr[1]) && str.contains(strArr[2])) {
                            return true;
                        }
                    } catch (NameNotFoundException e2) {
                        return z;
                    } catch (Throwable th) {
                        return z;
                    }
                }
            }
            return z;
        } catch (NameNotFoundException e3) {
            return false;
        } catch (Throwable th2) {
            return false;
        }
    }

    /* renamed from: w */
    private static String m995w(Context context) {
        try {
            String d = m960d(context, "");
            if (m961d(d)) {
                return d;
            }
            String g = m971g(context);
            if (m961d(g) && !"9774d56d682e549c".equals(g.toLowerCase(Locale.getDefault()))) {
                return g;
            }
            String u = m993u(context);
            if (!m961d(u)) {
                u = m996x(context);
                if (u == null) {
                    u = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
                }
            }
            return !m961d(u) ? "" : u;
        } catch (Exception e) {
            String x = m996x(context);
            return !m961d(x) ? "" : x;
        }
    }

    /* renamed from: x */
    private static String m996x(Context context) {
        String string = context.getSharedPreferences("PrefsFile", 0).getString("key", null);
        if (!C0530k.m1099a(string)) {
            return string;
        }
        if (!m956c()) {
            return m998z(context);
        }
        String b = C0386a.m255b(context);
        return TextUtils.isEmpty(b) ? (VERSION.SDK_INT < 23 || (m944a(context, "android.permission.WRITE_EXTERNAL_STORAGE") && m944a(context, "android.permission.READ_EXTERNAL_STORAGE"))) ? m997y(context) : m998z(context) : b;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0091 A[SYNTHETIC, Splitter:B:30:0x0091] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009b A[SYNTHETIC, Splitter:B:36:0x009b] */
    /* JADX WARNING: Removed duplicated region for block: B:48:? A[RETURN, SYNTHETIC] */
    /* renamed from: y */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String m997y(android.content.Context r6) {
        /*
            r1 = 0
            java.lang.String r0 = m958d()
            if (r0 != 0) goto L_0x0036
            r0 = r1
        L_0x0008:
            boolean r2 = p004cn.jiguang.p031g.C0530k.m1099a(r0)
            if (r2 != 0) goto L_0x00a8
            java.io.File r2 = new java.io.File
            r2.<init>(r0)
            r3 = r2
        L_0x0014:
            if (r3 == 0) goto L_0x004e
            boolean r0 = r3.exists()     // Catch:{ Exception -> 0x004a }
            if (r0 == 0) goto L_0x004e
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ Exception -> 0x004a }
            r0.<init>(r3)     // Catch:{ Exception -> 0x004a }
            java.util.ArrayList r0 = p004cn.jiguang.p031g.C0525f.m1083a(r0)     // Catch:{ Exception -> 0x004a }
            int r2 = r0.size()     // Catch:{ Exception -> 0x004a }
            if (r2 <= 0) goto L_0x004e
            r2 = 0
            java.lang.Object r0 = r0.get(r2)     // Catch:{ Exception -> 0x004a }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x004a }
            p004cn.jiguang.p015d.p016a.C0386a.m246a(r6, r0)     // Catch:{ Exception -> 0x004a }
        L_0x0035:
            return r0
        L_0x0036:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r2 = ".push_udid"
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r0 = r0.toString()
            goto L_0x0008
        L_0x004a:
            r0 = move-exception
            r0.printStackTrace()
        L_0x004e:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            long r4 = java.lang.System.currentTimeMillis()
            java.lang.StringBuilder r0 = r0.append(r4)
            java.lang.String r0 = r0.toString()
            byte[] r0 = r0.getBytes()
            java.util.UUID r0 = java.util.UUID.nameUUIDFromBytes(r0)
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = p004cn.jiguang.p031g.C0530k.m1101b(r0)
            p004cn.jiguang.p015d.p016a.C0386a.m246a(r6, r0)
            if (r3 == 0) goto L_0x0035
            r3.createNewFile()     // Catch:{ IOException -> 0x009f }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x008e, all -> 0x0097 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x008e, all -> 0x0097 }
            byte[] r1 = r0.getBytes()     // Catch:{ IOException -> 0x00a5, all -> 0x00a3 }
            r2.write(r1)     // Catch:{ IOException -> 0x00a5, all -> 0x00a3 }
            r2.flush()     // Catch:{ IOException -> 0x00a5, all -> 0x00a3 }
            if (r2 == 0) goto L_0x0035
            r2.close()     // Catch:{ IOException -> 0x008c }
            goto L_0x0035
        L_0x008c:
            r1 = move-exception
            goto L_0x0035
        L_0x008e:
            r2 = move-exception
        L_0x008f:
            if (r1 == 0) goto L_0x0035
            r1.close()     // Catch:{ IOException -> 0x0095 }
            goto L_0x0035
        L_0x0095:
            r1 = move-exception
            goto L_0x0035
        L_0x0097:
            r0 = move-exception
            r2 = r1
        L_0x0099:
            if (r2 == 0) goto L_0x009e
            r2.close()     // Catch:{ IOException -> 0x00a1 }
        L_0x009e:
            throw r0
        L_0x009f:
            r1 = move-exception
            goto L_0x0035
        L_0x00a1:
            r1 = move-exception
            goto L_0x009e
        L_0x00a3:
            r0 = move-exception
            goto L_0x0099
        L_0x00a5:
            r1 = move-exception
            r1 = r2
            goto L_0x008f
        L_0x00a8:
            r3 = r1
            goto L_0x0014
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p031g.C0506a.m997y(android.content.Context):java.lang.String");
    }

    /* renamed from: z */
    private static String m998z(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PrefsFile", 0);
        String string = sharedPreferences.getString("key", null);
        if (string != null) {
            return string;
        }
        String uuid = UUID.randomUUID().toString();
        Editor edit = sharedPreferences.edit();
        edit.putString("key", uuid);
        edit.commit();
        return uuid;
    }
}
