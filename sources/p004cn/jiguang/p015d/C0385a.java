package p004cn.jiguang.p015d;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.internal.ServerProtocol;
import com.tencent.bugly.Bugly;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import p004cn.jiguang.api.BasePreferenceManager;
import p004cn.jiguang.api.SdkType;
import p004cn.jiguang.p005a.C0334a;
import p004cn.jiguang.p005a.p006a.p009c.C0358e;
import p004cn.jiguang.p014c.C0382a;
import p004cn.jiguang.p014c.C0383b;
import p004cn.jiguang.p015d.p016a.C0386a;
import p004cn.jiguang.p015d.p016a.C0389d;
import p004cn.jiguang.p015d.p016a.C0391f;
import p004cn.jiguang.p015d.p017b.C0395a;
import p004cn.jiguang.p015d.p021d.C0446c;
import p004cn.jiguang.p015d.p021d.C0448e;
import p004cn.jiguang.p015d.p021d.C0460q;
import p004cn.jiguang.p015d.p021d.C0464u;
import p004cn.jiguang.p029e.C0501d;
import p004cn.jiguang.p031g.C0506a;
import p004cn.jiguang.p031g.C0530k;
import p004cn.jiguang.p031g.p032a.C0507a;
import p004cn.jiguang.service.Protocol;
import p004cn.jpush.android.api.JPluginPlatformInterface;
import p004cn.jpush.android.service.PushService;

/* renamed from: cn.jiguang.d.a */
public final class C0385a {

    /* renamed from: a */
    public static final String f194a = SdkType.JCORE.name();

    /* renamed from: b */
    public static boolean f195b = false;

    /* renamed from: c */
    public static String f196c;

    /* renamed from: d */
    public static String f197d;

    /* renamed from: e */
    public static Context f198e;

    /* renamed from: f */
    public static final C0383b f199f = new C0382a();

    /* renamed from: g */
    public static String f200g;

    /* renamed from: h */
    public static int f201h;

    /* renamed from: i */
    public static String f202i;

    /* renamed from: j */
    public static boolean f203j = false;

    /* renamed from: k */
    public static boolean f204k = false;

    /* renamed from: l */
    public static boolean f205l = false;

    /* renamed from: m */
    private static AtomicBoolean f206m = new AtomicBoolean(false);

    /* renamed from: n */
    private static ServiceConnection f207n = new C0394b();

    /* renamed from: a */
    public static void m237a(Context context, boolean z) {
        if (!C0507a.m1002d()) {
            Intent intent = new Intent();
            intent.setClass(context, PushService.class);
            try {
                if (context.bindService(intent, f207n, 1)) {
                    C0507a.m1003e();
                }
            } catch (SecurityException e) {
                C0501d.m907c("JCoreGlobal", "Remote Service bind failed caused by SecurityException!");
            }
        }
    }

    /* renamed from: a */
    private static boolean m238a() {
        int i;
        try {
            i = Protocol.GetSdkVersion();
        } catch (UnsatisfiedLinkError e) {
            C0501d.m909d("JCoreGlobal", "Get sdk version fail![获取sdk版本失败!]");
            e.printStackTrace();
            i = 0;
        }
        return i >= 100;
    }

    /* renamed from: a */
    public static synchronized boolean m239a(Context context) {
        boolean z = true;
        synchronized (C0385a.class) {
            f205l = f204k;
            f204k = true;
            if (!f206m.get()) {
                f196c = context.getPackageName();
                Context applicationContext = context.getApplicationContext();
                f198e = applicationContext;
                C0386a.m244a(applicationContext);
                C0389d.m307a(f198e);
                C0391f.m350a(f198e);
                BasePreferenceManager.init(context.getApplicationContext());
                if (!m240b(context)) {
                    z = false;
                } else {
                    String v = C0386a.m295v();
                    if (C0530k.m1099a(v) || "null".equals(v) || !v.equalsIgnoreCase(C0389d.m338i(context))) {
                        C0501d.m903a("ServiceHelper", "We found the appKey is changed or register appkey is empty. Will re-register.");
                        C0389d.m336g(context);
                        C0460q.m724e(context);
                    }
                    C0334a.m7a(context);
                    if (!C0506a.m989q(f198e)) {
                        z = false;
                    } else {
                        C0448e.m641a();
                        C0358e.m109a().mo6234a(context.getApplicationContext());
                        C0464u.m728a(context);
                        C0501d.m903a("JCoreGlobal", "action:init - sdkVersion:1.2.0, buildId:181");
                        if (!m238a()) {
                            C0501d.m909d("JCoreGlobal", "JCore .so file do not match JCore .jar file in the project, Failed to init JCore");
                            z = false;
                        } else {
                            ApplicationInfo c = m241c(context);
                            if (c == null) {
                                C0501d.m909d("JCoreGlobal", "JCore cannot be initialized completely due to NULL appInfo.");
                                z = false;
                            } else {
                                try {
                                    f197d = context.getPackageManager().getApplicationLabel(c).toString();
                                } catch (Throwable th) {
                                    C0501d.m903a("JCoreGlobal", "get mApplicationName error:" + th.getMessage());
                                }
                                try {
                                    PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                                    f201h = packageInfo.versionCode;
                                    String str = packageInfo.versionName;
                                    f202i = str;
                                    if (str.length() > 30) {
                                        f202i = f202i.substring(0, 30);
                                    }
                                } catch (Throwable th2) {
                                    C0501d.m903a("JCoreGlobal", "NO versionCode or versionName defined in manifest.");
                                }
                                if (VERSION.SDK_INT == 8) {
                                    System.setProperty("java.net.preferIPv4Stack", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                                    System.setProperty("java.net.preferIPv6Addresses", Bugly.SDK_IS_DEV);
                                }
                                f206m.set(true);
                                C0395a.m385b();
                                if (C0506a.m988p(f198e)) {
                                    m237a(f198e, false);
                                }
                            }
                        }
                    }
                }
            }
        }
        return z;
    }

    /* renamed from: b */
    private static boolean m240b(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                Bundle bundle = applicationInfo.metaData;
                if (bundle != null) {
                    String string = bundle.getString("JPUSH_APPKEY");
                    if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(f200g) && f200g.equals(string)) {
                        return f200g.length() == 24;
                    }
                    f200g = string;
                    if (C0530k.m1099a(string)) {
                        C0501d.m909d("JCoreGlobal", "errorcode:10001,metadata: JCore appKey - not defined in manifest");
                        C0446c.m633a(f198e, JPluginPlatformInterface.JPLUGIN_REQUEST_CODE, false);
                        C0386a.m245a(context, (int) JPluginPlatformInterface.JPLUGIN_REQUEST_CODE);
                        return false;
                    } else if (f200g.length() != 24) {
                        C0501d.m909d("JCoreGlobal", "errorcode:1008,Invalid appKey : " + f200g + ", Please get your Appkey from JIGUANG web console!");
                        C0446c.m633a(f198e, 1008, false);
                        C0386a.m245a(context, 1008);
                        return false;
                    } else {
                        f200g = f200g.toLowerCase(Locale.getDefault());
                        C0501d.m903a("JCoreGlobal", "metadata: appKey - " + f200g);
                        String c = C0530k.m1102c(bundle.getString("JPUSH_CHANNEL"));
                        if (C0530k.m1099a(c)) {
                            C0501d.m903a("JCoreGlobal", "metadata: channel - not defined in manifest");
                            return true;
                        }
                        C0501d.m903a("JCoreGlobal", "metadata: channel - " + c);
                        C0386a.m278h(c);
                        return true;
                    }
                } else {
                    C0501d.m903a("JCoreGlobal", "NO meta data defined in manifest.");
                    return false;
                }
            } else {
                C0501d.m903a("JCoreGlobal", "metadata: Can not get metaData from ApplicationInfo");
                return false;
            }
        } catch (Throwable th) {
            C0501d.m904a("JCoreGlobal", "Unexpected: failed to get current application info", th);
            return false;
        }
    }

    /* renamed from: c */
    private static ApplicationInfo m241c(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
        } catch (Throwable th) {
            C0501d.m906b("JCoreGlobal", "Unexpected: failed to get current application info", th);
            return null;
        }
    }
}
