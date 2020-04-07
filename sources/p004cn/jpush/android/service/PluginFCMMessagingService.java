package p004cn.jpush.android.service;

import android.app.NotificationManager;
import android.os.Bundle;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.firebase.messaging.RemoteMessage.Notification;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;
import p004cn.jiguang.api.JCoreInterface;
import p004cn.jpush.android.C0541a;
import p004cn.jpush.android.api.C0560b;
import p004cn.jpush.android.p039c.C0574h;
import p004cn.jpush.android.p040d.C0582e;

/* renamed from: cn.jpush.android.service.PluginFCMMessagingService */
public class PluginFCMMessagingService extends FirebaseMessagingService {
    /* JADX WARNING: type inference failed for: r6v0, types: [android.content.Context, cn.jpush.android.service.PluginFCMMessagingService] */
    public void onMessageReceived(RemoteMessage remoteMessage) {
        C0582e.m1302a("PluginFCMMessagingService", "onMessageReceived is called:" + remoteMessage);
        Notification notification = remoteMessage.getNotification();
        Map data = remoteMessage.getData();
        if (data == null || data.isEmpty()) {
            C0582e.m1306c("PluginFCMMessagingService", "data is null");
            return;
        }
        Bundle bundle = new Bundle();
        for (Entry entry : data.entrySet()) {
            bundle.putString((String) entry.getKey(), (String) entry.getValue());
        }
        if (notification != null) {
            try {
                String string = bundle.getString("JMessageExtra");
                int a = C0560b.m1185a(new JSONObject(string).optString("msg_id"), 0);
                ((NotificationManager) getSystemService("notification")).notify(a, m1360a(bundle, notification, a));
                C0574h.m1254a(this, string, "", a, 8, false);
            } catch (Throwable th) {
            }
        } else {
            C0606c.m1392a(this, bundle, "intent.plugin.platform.ON_MESSAGING");
            JCoreInterface.sendAction(this, C0541a.f649a, bundle);
        }
    }

    /* JADX WARNING: type inference failed for: r10v0, types: [android.content.Context, cn.jpush.android.service.PluginFCMMessagingService] */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0118  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.app.Notification m1360a(android.os.Bundle r11, com.google.firebase.messaging.RemoteMessage.Notification r12, int r13) {
        /*
            r10 = this;
            r9 = 11
            r8 = -1
            r2 = 0
            java.lang.String r0 = r12.getTitle()
            java.lang.String r4 = r12.getBody()
            java.lang.String r5 = r12.getClickAction()
            java.lang.String r1 = "PluginFCMMessagingService"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r6 = "收到通知 title:"
            r3.<init>(r6)
            java.lang.String r6 = java.lang.String.valueOf(r0)
            java.lang.StringBuilder r3 = r3.append(r6)
            java.lang.String r6 = ",content:"
            java.lang.StringBuilder r3 = r3.append(r6)
            java.lang.String r6 = java.lang.String.valueOf(r4)
            java.lang.StringBuilder r3 = r3.append(r6)
            java.lang.String r6 = ",clickAction:"
            java.lang.StringBuilder r3 = r3.append(r6)
            java.lang.String r6 = java.lang.String.valueOf(r5)
            java.lang.StringBuilder r3 = r3.append(r6)
            java.lang.String r3 = r3.toString()
            p004cn.jpush.android.p040d.C0582e.m1302a(r1, r3)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L_0x004d
            java.lang.String r0 = p004cn.jpush.android.C0541a.f652d
        L_0x004d:
            android.content.pm.PackageManager r1 = r10.getPackageManager()     // Catch:{ Throwable -> 0x00cf }
            java.lang.String r3 = r10.getPackageName()     // Catch:{ Throwable -> 0x00cf }
            r6 = 128(0x80, float:1.794E-43)
            android.content.pm.ApplicationInfo r1 = r1.getApplicationInfo(r3, r6)     // Catch:{ Throwable -> 0x00cf }
            if (r1 == 0) goto L_0x00e4
            android.os.Bundle r3 = r1.metaData     // Catch:{ Throwable -> 0x00cf }
            if (r3 == 0) goto L_0x00e4
            android.os.Bundle r1 = r1.metaData     // Catch:{ Throwable -> 0x00cf }
            java.lang.String r3 = "com.google.firebase.messaging.default_notification_icon"
            int r1 = r1.getInt(r3)     // Catch:{ Throwable -> 0x00cf }
        L_0x0069:
            if (r1 != 0) goto L_0x0071
            int r3 = p004cn.jpush.android.C0541a.f650b
            if (r3 == 0) goto L_0x00e6
            int r1 = p004cn.jpush.android.C0541a.f650b
        L_0x0071:
            r3 = 0
            int r6 = android.os.Build.VERSION.SDK_INT
            if (r6 < r9) goto L_0x010e
            android.app.Notification$Builder r6 = new android.app.Notification$Builder
            android.content.Context r7 = p004cn.jpush.android.C0541a.f653e
            r6.<init>(r7)
            android.app.Notification$Builder r0 = r6.setContentTitle(r0)
            android.app.Notification$Builder r0 = r0.setContentText(r4)
            android.app.Notification$Builder r0 = r0.setTicker(r4)
            android.app.Notification$Builder r0 = r0.setSmallIcon(r1)
            java.lang.String r1 = "JPush"
            java.lang.String r4 = "Notification"
            p004cn.jpush.android.api.C0560b.m1188a(r0, r1, r4, r2, r8)
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 16
            if (r1 < r2) goto L_0x0105
            android.app.Notification r0 = r0.build()
        L_0x009e:
            boolean r1 = android.text.TextUtils.isEmpty(r5)
            if (r1 != 0) goto L_0x0118
            android.content.Intent r1 = new android.content.Intent
            r1.<init>(r5)
            java.lang.String r2 = r10.getPackageName()
            r1.setPackage(r2)
            r1.putExtras(r11)
        L_0x00b3:
            r2 = 335544320(0x14000000, float:6.4623485E-27)
            r1.setFlags(r2)
            r2 = 134217728(0x8000000, float:3.85186E-34)
            android.app.PendingIntent r1 = android.app.PendingIntent.getActivity(r10, r13, r1, r2)
            r0.contentIntent = r1
            int r1 = r0.flags
            r1 = r1 | 16
            r0.flags = r1
            int r1 = r0.flags
            r1 = r1 | 1
            r0.flags = r1
            r0.defaults = r8
            return r0
        L_0x00cf:
            r1 = move-exception
            java.lang.String r3 = "PluginFCMMessagingService"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "get fcm icon res error"
            r6.<init>(r7)
            java.lang.StringBuilder r1 = r6.append(r1)
            java.lang.String r1 = r1.toString()
            p004cn.jpush.android.p040d.C0582e.m1306c(r3, r1)
        L_0x00e4:
            r1 = r2
            goto L_0x0069
        L_0x00e6:
            android.content.Context r3 = p004cn.jpush.android.C0541a.f653e     // Catch:{ Throwable -> 0x00fb }
            android.content.pm.PackageManager r3 = r3.getPackageManager()     // Catch:{ Throwable -> 0x00fb }
            android.content.Context r6 = p004cn.jpush.android.C0541a.f653e     // Catch:{ Throwable -> 0x00fb }
            java.lang.String r6 = r6.getPackageName()     // Catch:{ Throwable -> 0x00fb }
            r7 = 0
            android.content.pm.ApplicationInfo r3 = r3.getApplicationInfo(r6, r7)     // Catch:{ Throwable -> 0x00fb }
            int r1 = r3.icon     // Catch:{ Throwable -> 0x00fb }
            goto L_0x0071
        L_0x00fb:
            r3 = move-exception
            java.lang.String r6 = "PluginFCMMessagingService"
            java.lang.String r7 = "failed to get application info and icon."
            p004cn.jpush.android.p040d.C0582e.m1307c(r6, r7, r3)
            goto L_0x0071
        L_0x0105:
            int r1 = android.os.Build.VERSION.SDK_INT
            if (r1 < r9) goto L_0x012f
            android.app.Notification r0 = r0.getNotification()
            goto L_0x009e
        L_0x010e:
            android.app.Notification r0 = new android.app.Notification
            long r2 = java.lang.System.currentTimeMillis()
            r0.<init>(r1, r4, r2)
            goto L_0x009e
        L_0x0118:
            android.content.pm.PackageManager r1 = r10.getPackageManager()
            java.lang.String r2 = r10.getPackageName()
            android.content.Intent r1 = r1.getLaunchIntentForPackage(r2)
            if (r1 != 0) goto L_0x012b
            android.content.Intent r1 = new android.content.Intent
            r1.<init>()
        L_0x012b:
            r1.putExtras(r11)
            goto L_0x00b3
        L_0x012f:
            r0 = r3
            goto L_0x009e
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jpush.android.service.PluginFCMMessagingService.m1360a(android.os.Bundle, com.google.firebase.messaging.RemoteMessage$Notification, int):android.app.Notification");
    }

    public void onDeletedMessages() {
        C0582e.m1302a("PluginFCMMessagingService", "onDeletedMessages is called");
        PluginFCMMessagingService.super.onDeletedMessages();
    }

    public void onMessageSent(String str) {
        C0582e.m1302a("PluginFCMMessagingService", "onMessageSent is called " + String.valueOf(str));
        PluginFCMMessagingService.super.onMessageSent(str);
    }

    public void onSendError(String str, Exception exc) {
        C0582e.m1303a("PluginFCMMessagingService", "onSendError is called", exc);
        PluginFCMMessagingService.super.onSendError(str, exc);
    }
}
