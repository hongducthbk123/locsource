package p004cn.jpush.android.service;

import android.content.Context;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;
import p004cn.jpush.android.p039c.C0573g;
import p004cn.jpush.android.p039c.C0574h;
import p004cn.jpush.android.p040d.C0582e;

/* renamed from: cn.jpush.android.service.PluginXiaomiPlatformsReceiver */
public class PluginXiaomiPlatformsReceiver extends PushMessageReceiver {
    public void onReceivePassThroughMessage(Context context, MiPushMessage miPushMessage) {
    }

    public void onNotificationMessageClicked(Context context, MiPushMessage miPushMessage) {
        if (miPushMessage != null) {
            C0574h.m1254a(context, miPushMessage.getContent(), miPushMessage.getMessageId(), miPushMessage.getNotifyId(), 1, true);
        }
    }

    public void onNotificationMessageArrived(Context context, MiPushMessage miPushMessage) {
        if (miPushMessage != null) {
            try {
                C0574h.m1254a(context, miPushMessage.getContent(), miPushMessage.getMessageId(), miPushMessage.getNotifyId(), 1, false);
            } catch (Throwable th) {
            }
        }
    }

    public void onCommandResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        if (miPushCommandMessage != null) {
            try {
                if ("register".equals(miPushCommandMessage.getCommand())) {
                    String str = null;
                    if (miPushCommandMessage.getResultCode() == 0) {
                        str = MiPushClient.getRegId(context);
                    }
                    C0573g.m1238a().mo6847a(context.getApplicationContext(), str);
                }
            } catch (Throwable th) {
            }
        }
    }

    public void onReceiveRegisterResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        if (miPushCommandMessage == null) {
            return;
        }
        if (miPushCommandMessage.getResultCode() == 0) {
            C0582e.m1302a("PluginXiaomiPlatformsReceiver", "xiao mi push register success");
        } else {
            C0582e.m1308d("PluginXiaomiPlatformsReceiver", "xiao mi push register failed - errorCode:" + miPushCommandMessage.getResultCode() + ",reason:" + miPushCommandMessage.getReason());
        }
    }
}
