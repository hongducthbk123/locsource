package p004cn.jpush.android;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import java.util.concurrent.atomic.AtomicBoolean;
import p004cn.jiguang.api.MultiSpHelper;
import p004cn.jiguang.api.SdkType;
import p004cn.jpush.android.p040d.C0577a;
import p004cn.jpush.android.p040d.C0582e;

/* renamed from: cn.jpush.android.a */
public final class C0541a {

    /* renamed from: a */
    public static final String f649a = SdkType.JPUSH.name();

    /* renamed from: b */
    public static int f650b;

    /* renamed from: c */
    public static String f651c;

    /* renamed from: d */
    public static String f652d;

    /* renamed from: e */
    public static Context f653e;

    /* renamed from: f */
    public static boolean f654f = false;

    /* renamed from: g */
    private static AtomicBoolean f655g = new AtomicBoolean(false);

    /* renamed from: a */
    public static synchronized boolean m1120a(Context context) {
        boolean z = true;
        synchronized (C0541a.class) {
            if (!f655g.get()) {
                if (!C0577a.m1289e(context)) {
                    z = false;
                } else {
                    f651c = context.getPackageName();
                    f653e = context.getApplicationContext();
                    String str = "3.1.2";
                    try {
                        String string = MultiSpHelper.getString(context, "jpush_sdk_version", "");
                        if (!str.equals(string)) {
                            TextUtils.isEmpty(string);
                        }
                        MultiSpHelper.commitString(context, "jpush_sdk_version", str);
                    } catch (Throwable th) {
                    }
                    ApplicationInfo b = m1121b(context);
                    if (b == null) {
                        C0582e.m1308d("JPushGlobal", "JPush cannot be initialized completely due to NULL appInfo.");
                        z = false;
                    } else {
                        f650b = b.icon;
                        if (f650b == 0) {
                            C0582e.m1308d("JPushGlobal", "metadata: Can not get Application icon, you will be not able to show notification due to the application icon is null.");
                        }
                        try {
                            f652d = context.getPackageManager().getApplicationLabel(b).toString();
                        } catch (Throwable th2) {
                            C0582e.m1302a("JPushGlobal", "get mApplicationName error:" + th2.getMessage());
                        }
                        f655g.set(true);
                    }
                }
            }
        }
        return z;
    }

    /* renamed from: b */
    private static ApplicationInfo m1121b(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
        } catch (Throwable th) {
            C0582e.m1307c("JPushGlobal", "Unexpected: failed to get current application info", th);
            return null;
        }
    }
}
