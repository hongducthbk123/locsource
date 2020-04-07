package p004cn.jiguang.p005a;

import android.app.Application;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.facebook.internal.NativeProtocol;
import p004cn.jiguang.api.JAnalyticsAction;
import p004cn.jiguang.api.JCoreInterface;
import p004cn.jiguang.p005a.p006a.p010d.C0365a;
import p004cn.jiguang.p015d.C0385a;
import p004cn.jiguang.p015d.p017b.C0395a;

/* renamed from: cn.jiguang.a.a */
public final class C0334a {

    /* renamed from: a */
    public static JAnalyticsAction f23a;

    /* renamed from: b */
    public static boolean f24b = true;

    /* renamed from: c */
    private static boolean f25c;

    /* renamed from: a */
    public static void m7a(Context context) {
        if (!f25c && context != null) {
            try {
                Context applicationContext = context.getApplicationContext();
                if ((applicationContext instanceof Application) && VERSION.SDK_INT >= 14) {
                    String c = C0395a.m387c(context);
                    String packageName = context.getPackageName();
                    if (!(c == null || packageName == null || !context.getPackageName().equals(c))) {
                        f24b = false;
                        ((Application) applicationContext).registerActivityLifecycleCallbacks(new C0365a());
                    }
                }
            } catch (Throwable th) {
                f24b = true;
            }
            f25c = true;
        }
    }

    /* renamed from: a */
    public static void m8a(Context context, String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(NativeProtocol.WEB_DIALOG_ACTION, "cn.jpush.android.intent.REPORT");
        bundle.putString("report", str);
        bundle.putString("report.extra.info", str2);
        JCoreInterface.sendAction(context, C0385a.f194a, bundle);
    }
}
