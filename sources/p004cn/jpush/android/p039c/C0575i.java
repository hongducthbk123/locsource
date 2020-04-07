package p004cn.jpush.android.p039c;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.support.api.push.HuaweiPush;
import com.xiaomi.mipush.sdk.MiPushClient;
import p004cn.jiguang.api.JCoreInterface;
import p004cn.jpush.android.C0563b;
import p004cn.jpush.android.p040d.C0577a;
import p004cn.jpush.android.p040d.C0582e;

/* renamed from: cn.jpush.android.c.i */
public final class C0575i {

    /* renamed from: a */
    private static final String f763a = "Xiaomi".toLowerCase();

    /* renamed from: b */
    private static final String f764b = "huawei".toLowerCase();

    /* renamed from: c */
    private static final String f765c = "Meizu".toLowerCase();

    /* renamed from: a */
    public static byte m1255a(Context context) {
        String str;
        byte b;
        int i = 1;
        if (context == null) {
            C0582e.m1306c("PluginWhichPlatform", "context was null");
            return 0;
        }
        try {
            str = Build.MANUFACTURER;
        } catch (Throwable th) {
            C0582e.m1306c("PluginWhichPlatform", "get MANUFACTURER failed - error:" + th);
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            C0582e.m1306c("PluginWhichPlatform", "MANUFACTURER was empty");
            return 0;
        }
        if (TextUtils.equals(f763a, str.toLowerCase())) {
            b = m1263f(context) ? (byte) 1 : 0;
        } else if (TextUtils.equals(f764b, str.toLowerCase())) {
            if (m1264g(context)) {
                b = 2;
                i = 2;
            } else {
                b = 0;
                i = 2;
            }
        } else if (!TextUtils.equals(f765c, str.toLowerCase())) {
            b = 0;
            i = 0;
        } else if (m1262e(context)) {
            b = 3;
            i = 3;
        } else {
            b = 0;
            i = 3;
        }
        C0563b.m1203a(context, i);
        C0563b.m1211b(context, i);
        if (b == 0) {
            C0563b.m1204a(context, i, (String) null);
            C0563b.m1209b(context, i, false);
        }
        return b;
    }

    /* renamed from: b */
    public static boolean m1259b(Context context) {
        if (context != null) {
            return m1260c(context);
        }
        C0582e.m1306c("PluginWhichPlatform", "context was null");
        return false;
    }

    /* renamed from: c */
    private static boolean m1260c(Context context) {
        if (!C0577a.m1287d(context, "cn.jpush.android.service.PluginFCMMessagingService") || !C0577a.m1287d(context, "cn.jpush.android.service.PluginFCMInstanceIdService")) {
            return false;
        }
        if (VERSION.SDK_INT < 14) {
            C0582e.m1306c("PluginWhichPlatform", "Os version is lower 14 ,will not use fcm");
            return false;
        }
        try {
            Class.forName("com.google.firebase.iid.FirebaseInstanceId");
            if (m1261d(context)) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            C0582e.m1308d("PluginWhichPlatform", "Please check *.jar files your project depends on, can't load class - com.google.firebase.iid.FirebaseInstanceId \nerror:" + th);
            if (!JCoreInterface.getDebugMode()) {
                return false;
            }
            throw new RuntimeException("Please check *.jar files your project depends on.", th);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x004a A[ADDED_TO_REGION] */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean m1261d(android.content.Context r8) {
        /*
            r2 = 1
            r1 = 0
            java.lang.String r4 = "com.google.android.gms"
            android.content.pm.PackageManager r0 = r8.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0050, Throwable -> 0x005b }
            r3 = 0
            android.content.pm.ApplicationInfo r0 = r0.getApplicationInfo(r4, r3)     // Catch:{ NameNotFoundException -> 0x0050, Throwable -> 0x005b }
            int r0 = r0.flags     // Catch:{ NameNotFoundException -> 0x0050, Throwable -> 0x005b }
            r0 = r0 & 1
            if (r0 == 0) goto L_0x001f
            r3 = r2
        L_0x0014:
            if (r3 != 0) goto L_0x0021
            java.lang.String r0 = "PluginWhichPlatform"
            java.lang.String r4 = "google play services is not system app!"
            p004cn.jpush.android.p040d.C0582e.m1306c(r0, r4)     // Catch:{ NameNotFoundException -> 0x0074, Throwable -> 0x006a }
            r0 = r1
        L_0x001e:
            return r0
        L_0x001f:
            r3 = r1
            goto L_0x0014
        L_0x0021:
            java.lang.String r0 = "com.google.android.gms.version"
            java.lang.Object r0 = p004cn.jpush.android.p040d.C0577a.m1288e(r8, r0)     // Catch:{ NameNotFoundException -> 0x0074, Throwable -> 0x006a }
            if (r0 != 0) goto L_0x002b
            r0 = r1
            goto L_0x001e
        L_0x002b:
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ NameNotFoundException -> 0x0074, Throwable -> 0x006a }
            int r0 = r0.intValue()     // Catch:{ NameNotFoundException -> 0x0074, Throwable -> 0x006a }
            android.content.pm.PackageManager r5 = r8.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0074, Throwable -> 0x006a }
            r6 = 0
            android.content.pm.PackageInfo r4 = r5.getPackageInfo(r4, r6)     // Catch:{ NameNotFoundException -> 0x0074, Throwable -> 0x006a }
            int r4 = r4.versionCode     // Catch:{ NameNotFoundException -> 0x0074, Throwable -> 0x006a }
            if (r4 < r0) goto L_0x004e
            r0 = r2
        L_0x003f:
            if (r0 != 0) goto L_0x0048
            java.lang.String r4 = "PluginWhichPlatform"
            java.lang.String r5 = "google play services is out of date , please update."
            p004cn.jpush.android.p040d.C0582e.m1306c(r4, r5)     // Catch:{ NameNotFoundException -> 0x0077, Throwable -> 0x006e }
        L_0x0048:
            if (r3 == 0) goto L_0x0068
            if (r0 == 0) goto L_0x0068
            r0 = r2
            goto L_0x001e
        L_0x004e:
            r0 = r1
            goto L_0x003f
        L_0x0050:
            r0 = move-exception
            r0 = r1
            r3 = r1
        L_0x0053:
            java.lang.String r4 = "PluginWhichPlatform"
            java.lang.String r5 = "no google play services in the device!"
            p004cn.jpush.android.p040d.C0582e.m1302a(r4, r5)
            goto L_0x0048
        L_0x005b:
            r0 = move-exception
            r3 = r1
            r4 = r1
        L_0x005e:
            java.lang.String r5 = "PluginWhichPlatform"
            java.lang.String r6 = "get google play services error:"
            p004cn.jpush.android.p040d.C0582e.m1303a(r5, r6, r0)
            r0 = r3
            r3 = r4
            goto L_0x0048
        L_0x0068:
            r0 = r1
            goto L_0x001e
        L_0x006a:
            r0 = move-exception
            r4 = r3
            r3 = r1
            goto L_0x005e
        L_0x006e:
            r4 = move-exception
            r7 = r4
            r4 = r3
            r3 = r0
            r0 = r7
            goto L_0x005e
        L_0x0074:
            r0 = move-exception
            r0 = r1
            goto L_0x0053
        L_0x0077:
            r4 = move-exception
            goto L_0x0053
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jpush.android.p039c.C0575i.m1261d(android.content.Context):boolean");
    }

    /* renamed from: e */
    private static boolean m1262e(Context context) {
        boolean z;
        if (VERSION.SDK_INT >= 11 && C0577a.m1285c(context, "cn.jpush.android.service.PluginMeizuPlatformsReceiver")) {
            try {
                if (Class.forName("com.meizu.cloud.pushsdk.PushManager") != null) {
                    z = true;
                } else {
                    z = false;
                }
                if (z && m1257a()) {
                    return true;
                }
                C0582e.m1302a("PluginWhichPlatform", "flyme version < 5.1.11.1A , Should not use MeizuPush");
                return false;
            } catch (Throwable th) {
                C0582e.m1308d("PluginWhichPlatform", "Please check *.jar files your project depends on, can't load class - com.meizu.cloud.pushsdk.PushManager \nerror:" + th);
                if (JCoreInterface.getDebugMode()) {
                    throw new RuntimeException("Please check *.aar files your project depends on.", th);
                }
            }
        }
        return false;
    }

    /* renamed from: a */
    private static boolean m1257a() {
        try {
            Class[] clsArr = {String.class};
            Object[] objArr = {"ro.build.display.id"};
            Class cls = Class.forName("android.os.SystemProperties");
            String str = (String) cls.getDeclaredMethod("get", clsArr).invoke(cls, objArr);
            if (!TextUtils.isEmpty(str)) {
                return (!str.contains("OS") && str.compareToIgnoreCase("Flyme 5.1.11.1A") >= 0) || (str.contains("OS") && str.compareToIgnoreCase("Flyme OS 5.1.11.1A") >= 0);
            }
        } catch (Throwable th) {
        }
        return false;
    }

    /* renamed from: f */
    private static boolean m1263f(Context context) {
        boolean z;
        if (C0577a.m1285c(context, "cn.jpush.android.service.PluginXiaomiPlatformsReceiver")) {
            try {
                if (MiPushClient.shouldUseMIUIPush(context)) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    return true;
                }
                C0582e.m1302a("PluginWhichPlatform", "should not Use MIUIPush");
                return false;
            } catch (Throwable th) {
                C0582e.m1308d("PluginWhichPlatform", "Please check *.jar files your project depends on, can't load class - com.xiaomi.mipush.sdk.MiPushClient \nerror:" + th);
                if (JCoreInterface.getDebugMode()) {
                    throw new RuntimeException("Please check *.jar files your project depends on.", th);
                }
            }
        }
        return false;
    }

    /* renamed from: b */
    private static boolean m1258b() {
        try {
            Class[] clsArr = {String.class};
            Object[] objArr = {"ro.build.version.emui"};
            Class cls = Class.forName("android.os.SystemProperties");
            String str = (String) cls.getDeclaredMethod("get", clsArr).invoke(cls, objArr);
            if (!TextUtils.isEmpty(str)) {
                return str.compareToIgnoreCase("EmotionUI_4.1") >= 0;
            }
        } catch (Throwable th) {
        }
        return false;
    }

    /* renamed from: g */
    private static boolean m1264g(Context context) {
        boolean z;
        if (VERSION.SDK_INT >= 14 && C0577a.m1285c(context, "cn.jpush.android.service.PluginHuaweiPlatformsReceiver")) {
            try {
                if (m1258b()) {
                    if (HuaweiPush.HuaweiPushApi != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        return true;
                    }
                }
                C0582e.m1302a("PluginWhichPlatform", "emui version must large than 4.0");
                return false;
            } catch (Throwable th) {
                C0582e.m1308d("PluginWhichPlatform", "Please check *.jar files your project depends on, can't load class - com.huawei.hms.support.api.push.HuaweiPush \nerror:" + th);
                if (JCoreInterface.getDebugMode()) {
                    throw new RuntimeException("Please check *.jar files your project depends on.", th);
                }
            }
        }
        return false;
    }

    /* renamed from: a */
    public static String m1256a(Context context, String str) {
        if (context == null) {
            C0582e.m1306c("PluginWhichPlatform", "context was null");
            return null;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                Bundle bundle = applicationInfo.metaData;
                if (bundle != null) {
                    String string = bundle.getString(str);
                    if (!TextUtils.isEmpty(string) && string.length() > 3) {
                        return string.substring(3, string.length());
                    }
                    C0582e.m1306c("PluginWhichPlatform", "metadata: " + str + " - not defined in manifest");
                    return null;
                }
                C0582e.m1302a("PluginWhichPlatform", "NO meta data defined in manifest.");
                return null;
            }
            C0582e.m1306c("PluginWhichPlatform", "metadata: Can not get metaData from ApplicationInfo");
            return null;
        } catch (Throwable th) {
            C0582e.m1306c("PluginWhichPlatform", "load plugin sdk config info error:" + th);
            return null;
        }
    }
}
