package p004cn.jpush.android.service;

import android.content.Context;
import android.content.Intent;
import com.meizu.cloud.pushsdk.MzPushMessageReceiver;
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.platform.message.PushSwitchStatus;
import com.meizu.cloud.pushsdk.platform.message.RegisterStatus;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;
import com.meizu.cloud.pushsdk.platform.message.SubTagsStatus;
import com.meizu.cloud.pushsdk.platform.message.UnRegisterStatus;
import org.json.JSONObject;
import p004cn.jpush.android.C0541a;
import p004cn.jpush.android.p039c.C0573g;
import p004cn.jpush.android.p039c.C0574h;
import p004cn.jpush.android.p040d.C0582e;

/* renamed from: cn.jpush.android.service.PluginMeizuPlatformsReceiver */
public class PluginMeizuPlatformsReceiver extends MzPushMessageReceiver {

    /* renamed from: a */
    private Context f877a;

    public void onReceive(Context context, Intent intent) {
        try {
            this.f877a = context.getApplicationContext();
        } catch (Throwable th) {
        }
        PluginMeizuPlatformsReceiver.super.onReceive(context, intent);
    }

    public void onRegister(Context context, String str) {
        C0582e.m1302a("PluginMeizuPlatformsReceiver", "onRegister is called");
    }

    public void onMessage(Context context, String str) {
        C0582e.m1302a("PluginMeizuPlatformsReceiver", "onMessage is called");
    }

    public void onUnRegister(Context context, boolean z) {
        C0582e.m1304b("PluginMeizuPlatformsReceiver", "onUnRegister is called");
    }

    public void onPushStatus(Context context, PushSwitchStatus pushSwitchStatus) {
        C0582e.m1304b("PluginMeizuPlatformsReceiver", "onPushStatus is called");
    }

    public void onRegisterStatus(Context context, RegisterStatus registerStatus) {
        C0582e.m1304b("PluginMeizuPlatformsReceiver", "onRegisterStatus:" + String.valueOf(registerStatus));
        String str = null;
        if (registerStatus != null) {
            str = registerStatus.getPushId();
            C0582e.m1306c("PluginMeizuPlatformsReceiver", "PushId is " + String.valueOf(str));
        }
        try {
            C0573g.m1238a().mo6847a(context, str);
        } catch (Throwable th) {
            C0582e.m1306c("PluginMeizuPlatformsReceiver", "Update pushId unexpected error:" + th.getMessage());
        }
    }

    public void onUnRegisterStatus(Context context, UnRegisterStatus unRegisterStatus) {
        C0582e.m1302a("PluginMeizuPlatformsReceiver", "onUnRegisterStatus:" + String.valueOf(unRegisterStatus));
    }

    public void onSubTagsStatus(Context context, SubTagsStatus subTagsStatus) {
        C0582e.m1302a("PluginMeizuPlatformsReceiver", "onSubTagsStatus:" + String.valueOf(subTagsStatus));
    }

    public void onSubAliasStatus(Context context, SubAliasStatus subAliasStatus) {
        C0582e.m1304b("PluginMeizuPlatformsReceiver", "onSubAliasStatus:" + String.valueOf(subAliasStatus));
    }

    public void onNotificationArrived(Context context, String str, String str2, String str3) {
        C0582e.m1304b("PluginMeizuPlatformsReceiver", "onNotificationArrived:title:" + String.valueOf(str) + ",content:" + String.valueOf(str2) + ",extra:" + String.valueOf(str3));
        C0574h.m1254a(context, m1361a(str3), "", 0, 3, false);
    }

    public void onNotificationClicked(Context context, String str, String str2, String str3) {
        C0582e.m1304b("PluginMeizuPlatformsReceiver", "onNotificationClicked:title:" + String.valueOf(str) + ",content:" + String.valueOf(str2) + ",extra:" + String.valueOf(str3));
        C0574h.m1254a(context, m1361a(str3), "", 0, 3, true);
    }

    public void onUpdateNotificationBuilder(PushNotificationBuilder pushNotificationBuilder) {
        try {
            if (this.f877a == null) {
                this.f877a = C0541a.f653e;
            }
            int identifier = this.f877a.getResources().getIdentifier("mz_push_notification_small_icon", "drawable", this.f877a.getPackageName());
            if (identifier != 0) {
                pushNotificationBuilder.setmStatusbarIcon(identifier);
            }
        } catch (Throwable th) {
            C0582e.m1306c("PluginMeizuPlatformsReceiver", "set meizu statusbar icon error:" + th.toString());
        }
    }

    /* renamed from: a */
    private static String m1361a(String str) {
        try {
            return new JSONObject(str).optString("JMessageExtra");
        } catch (Throwable th) {
            C0582e.m1306c("PluginMeizuPlatformsReceiver", "parse extra error " + th);
            return null;
        }
    }
}
