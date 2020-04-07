package p004cn.jpush.android.service;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.support.api.push.PushReceiver;
import com.huawei.hms.support.api.push.PushReceiver.Event;
import p004cn.jpush.android.p039c.C0573g;
import p004cn.jpush.android.p039c.C0574h;
import p004cn.jpush.android.p040d.C0582e;

/* renamed from: cn.jpush.android.service.PluginHuaweiPlatformsReceiver */
public class PluginHuaweiPlatformsReceiver extends PushReceiver {
    public void onToken(Context context, String str, Bundle bundle) {
        PluginHuaweiPlatformsReceiver.super.onToken(context, str, bundle);
        String str2 = null;
        if (bundle != null) {
            str2 = bundle.getString("belongId");
        }
        C0582e.m1304b("PluginHuaweiPlatformsReceiver", "token:" + str + ",belongId:" + str2);
        try {
            C0573g.m1238a().mo6847a(context.getApplicationContext(), str);
        } catch (Throwable th) {
        }
    }

    public void onEvent(Context context, Event event, Bundle bundle) {
        String str;
        int i = 0;
        if (event != null && Event.NOTIFICATION_OPENED.equals(event)) {
            if (bundle != null) {
                i = bundle.getInt("pushNotifyId", 0);
            }
            if (i != 0) {
                ((NotificationManager) context.getSystemService("notification")).cancel(i);
            }
            if (bundle != null) {
                try {
                    str = bundle.getString("pushMsg");
                } catch (Throwable th) {
                }
            } else {
                str = null;
            }
            if (!TextUtils.isEmpty(str)) {
                str = str.substring(1, str.length() - 1);
            }
            C0574h.m1254a(context.getApplicationContext(), str, "", i, 2, true);
        }
        PluginHuaweiPlatformsReceiver.super.onEvent(context, event, bundle);
    }

    public void onPushState(Context context, boolean z) {
        C0582e.m1302a("PluginHuaweiPlatformsReceiver", "onPushState:" + z);
        PluginHuaweiPlatformsReceiver.super.onPushState(context, z);
    }
}
