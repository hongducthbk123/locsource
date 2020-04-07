package p004cn.jpush.android.service;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import p004cn.jpush.android.api.JPushInterface;
import p004cn.jpush.android.p037a.C0546d;
import p004cn.jpush.android.p040d.C0577a;
import p004cn.jpush.android.p040d.C0582e;

/* renamed from: cn.jpush.android.service.b */
public final class C0605b {

    /* renamed from: a */
    private static C0605b f889a;

    private C0605b() {
    }

    /* renamed from: a */
    public static C0605b m1389a() {
        if (f889a == null) {
            f889a = new C0605b();
        }
        return f889a;
    }

    /* renamed from: a */
    public static void m1390a(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra(JPushInterface.EXTRA_MSG_ID);
        if (!TextUtils.isEmpty(stringExtra)) {
            String stringExtra2 = intent.getStringExtra(JPushInterface.EXTRA_NOTI_TYPE);
            boolean z = false;
            if (stringExtra2 != null && "1".equals(stringExtra2)) {
                z = true;
            }
            if (true != z) {
                C0546d.m1124a(stringExtra, 1000, null, context);
            }
        }
        if (!C0577a.m1280a(context, JPushInterface.ACTION_NOTIFICATION_OPENED, true)) {
            C0582e.m1302a("PushReceiverCore", "No ACTION_NOTIFICATION_OPENED defined in manifest, open the default main activity");
            C0577a.m1281b(context);
            return;
        }
        Intent intent2 = new Intent(JPushInterface.ACTION_NOTIFICATION_OPENED);
        String str = "";
        try {
            intent2.putExtras(intent.getExtras());
            String stringExtra3 = intent.getStringExtra("app");
            if (TextUtils.isEmpty(stringExtra3)) {
                stringExtra3 = context.getPackageName();
            }
            intent2.addCategory(stringExtra3);
            intent2.setPackage(context.getPackageName());
            context.sendBroadcast(intent2, stringExtra3 + ".permission.JPUSH_MESSAGE");
        } catch (Throwable th) {
            C0582e.m1306c("PushReceiverCore", "onNotificationOpen sendBrocat error:" + th.getMessage());
            C0577a.m1282b(context, intent2, str + ".permission.JPUSH_MESSAGE");
        }
    }
}
