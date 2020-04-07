package p004cn.jpush.android.api;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.Notification.Builder;
import android.content.Context;
import android.os.Build.VERSION;
import p004cn.jpush.android.C0541a;

/* renamed from: cn.jpush.android.api.BasicPushNotificationBuilder */
public class BasicPushNotificationBuilder extends DefaultPushNotificationBuilder {

    /* renamed from: a */
    protected Context f690a;
    public String developerArg0 = "developerArg0";
    public int notificationDefaults = -2;
    public int notificationFlags = 16;
    public int statusBarDrawable = C0541a.f650b;

    public BasicPushNotificationBuilder(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("NULL context");
        }
        this.f690a = context;
    }

    public String getDeveloperArg0() {
        return this.developerArg0;
    }

    /* access modifiers changed from: 0000 */
    @TargetApi(11)
    public Notification getNotification(Builder builder) {
        Notification notification;
        if (this.notificationDefaults != -2) {
            builder.setDefaults(this.notificationDefaults);
        }
        builder.setSmallIcon(this.statusBarDrawable);
        if (VERSION.SDK_INT >= 16) {
            notification = builder.build();
        } else {
            notification = builder.getNotification();
        }
        notification.flags = this.notificationFlags | 1;
        return notification;
    }

    /* access modifiers changed from: 0000 */
    public void resetNotificationParams(Notification notification) {
        notification.defaults = this.notificationDefaults;
        notification.flags = this.notificationFlags;
        notification.icon = this.statusBarDrawable;
    }

    public String toString() {
        return "basic_____" + mo6787a();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public String mo6787a() {
        return this.notificationDefaults + "_____" + this.notificationFlags + "_____" + this.statusBarDrawable + "_____" + this.developerArg0;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo6788a(String[] strArr) throws NumberFormatException {
        this.notificationDefaults = Integer.parseInt(strArr[1]);
        this.notificationFlags = Integer.parseInt(strArr[2]);
        this.statusBarDrawable = Integer.parseInt(strArr[3]);
        if (5 == strArr.length) {
            this.developerArg0 = strArr[4];
        }
    }

    /* renamed from: a */
    static PushNotificationBuilder m1172a(String str) {
        BasicPushNotificationBuilder basicPushNotificationBuilder;
        String[] split = str.split("_____");
        String str2 = split[0];
        if ("basic".equals(str2)) {
            basicPushNotificationBuilder = new BasicPushNotificationBuilder(C0541a.f653e);
        } else if ("custom".equals(str2)) {
            basicPushNotificationBuilder = new CustomPushNotificationBuilder(C0541a.f653e);
        } else {
            basicPushNotificationBuilder = new BasicPushNotificationBuilder(C0541a.f653e);
        }
        basicPushNotificationBuilder.mo6788a(split);
        return basicPushNotificationBuilder;
    }
}
