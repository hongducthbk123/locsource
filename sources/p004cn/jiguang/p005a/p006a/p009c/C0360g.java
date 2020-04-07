package p004cn.jiguang.p005a.p006a.p009c;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.lang.reflect.InvocationTargetException;
import org.json.JSONObject;
import p004cn.jiguang.api.JCoreInterface;
import p004cn.jiguang.p015d.p016a.C0389d;
import p004cn.jiguang.p015d.p021d.C0460q;
import p004cn.jiguang.p031g.C0506a;

/* renamed from: cn.jiguang.a.a.c.g */
public final class C0360g {
    /* renamed from: a */
    public static void m122a(Context context, int i) {
        boolean z;
        boolean z2;
        String str = (String) C0389d.m320b(context, "report_notify_state", null);
        new C0360g();
        boolean b = VERSION.SDK_INT >= 24 ? m123a(context) : m124b(context);
        if (TextUtils.isEmpty(str)) {
            z2 = true;
        } else {
            if (TextUtils.equals("ON", str)) {
                z = true;
                z2 = false;
            } else if (TextUtils.equals("OFF", str)) {
                z = false;
                z2 = false;
            } else {
                z = false;
                z2 = true;
            }
            if (!z2) {
                z2 = z != b;
            }
        }
        if (z2) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("notification_state", b);
                jSONObject.put("imei", C0506a.m960d(context, C0506a.m960d(context, "")));
                jSONObject.put("device_id", C0506a.m972h(context));
                jSONObject.put("trigger_scene", i);
                C0460q.m707a((Context) null, jSONObject, "android_notification_state");
                JCoreInterface.report(context, jSONObject, false);
                C0389d.m313a(context, "report_notify_state", b ? "ON" : "OFF");
            } catch (Throwable th) {
            }
        }
    }

    @TargetApi(24)
    /* renamed from: a */
    private static boolean m123a(Context context) {
        try {
            return ((NotificationManager) context.getSystemService("notification")).areNotificationsEnabled();
        } catch (Throwable th) {
            return true;
        }
    }

    /* renamed from: b */
    private static boolean m124b(Context context) {
        try {
            AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            String packageName = context.getApplicationContext().getPackageName();
            int i = applicationInfo.uid;
            Class cls = Class.forName(AppOpsManager.class.getName());
            return ((Integer) cls.getMethod("checkOpNoThrow", new Class[]{Integer.TYPE, Integer.TYPE, String.class}).invoke(appOpsManager, new Object[]{Integer.valueOf(((Integer) cls.getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class)).intValue()), Integer.valueOf(i), packageName})).intValue() == 0;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException | RuntimeException | InvocationTargetException e) {
            return true;
        } catch (Throwable th) {
            return true;
        }
    }
}
