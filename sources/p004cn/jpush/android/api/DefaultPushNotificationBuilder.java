package p004cn.jpush.android.api;

import android.app.Notification;
import android.app.Notification.BigPictureStyle;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Builder;
import android.app.Notification.InboxStyle;
import android.app.PendingIntent;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.widget.RemoteViews;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONObject;
import p004cn.jpush.android.C0541a;
import p004cn.jpush.android.p040d.C0582e;

/* renamed from: cn.jpush.android.api.DefaultPushNotificationBuilder */
public class DefaultPushNotificationBuilder implements PushNotificationBuilder {
    private static final String DEFAULT_NOTIFICATION_CHANNEL_ID = "JPush";
    private static final String DEFAULT_NOTIFICATION_CHANNEL_NAME = "Notification";
    private static final String TAG = "DefaultPushNotificationBuilder";
    private static boolean hasCreateNotificationChannel = false;

    public String getDeveloperArg0() {
        return null;
    }

    /* access modifiers changed from: 0000 */
    public RemoteViews buildContentView(String str, String str2) {
        return null;
    }

    /* access modifiers changed from: 0000 */
    public void resetNotificationParams(Notification notification) {
    }

    /* access modifiers changed from: 0000 */
    public Notification getNotification(Builder builder) {
        try {
            if (VERSION.SDK_INT >= 16) {
                return builder.build();
            }
            return builder.getNotification();
        } catch (Throwable th) {
            C0582e.m1305b(TAG, "Build notification error:", th);
            return null;
        }
    }

    public Notification buildNotification(Map<String, String> map) {
        String str;
        int i;
        int i2;
        String str2 = C0541a.f652d;
        String str3 = "";
        String str4 = "";
        String str5 = "";
        int i3 = 0;
        String str6 = "";
        String str7 = "";
        if (map.containsKey(JPushInterface.EXTRA_ALERT)) {
            str = (String) map.get(JPushInterface.EXTRA_ALERT);
        } else {
            str = str3;
        }
        if (TextUtils.isEmpty(str)) {
            C0582e.m1306c(TAG, "No notification content to show. Give up.");
            return null;
        }
        if (map.containsKey(JPushInterface.EXTRA_NOTIFICATION_TITLE)) {
            str2 = (String) map.get(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        }
        if (map.containsKey(JPushInterface.EXTRA_BIG_TEXT)) {
            str4 = (String) map.get(JPushInterface.EXTRA_BIG_TEXT);
        }
        if (map.containsKey(JPushInterface.EXTRA_INBOX)) {
            str5 = (String) map.get(JPushInterface.EXTRA_INBOX);
        }
        if (map.containsKey(JPushInterface.EXTRA_NOTI_PRIORITY)) {
            i3 = Integer.parseInt((String) map.get(JPushInterface.EXTRA_NOTI_PRIORITY));
        }
        if (map.containsKey(JPushInterface.EXTRA_NOTI_CATEGORY)) {
            str6 = (String) map.get(JPushInterface.EXTRA_NOTI_CATEGORY);
        }
        if (map.containsKey(JPushInterface.EXTRA_BIG_PIC_PATH)) {
            str7 = (String) map.get(JPushInterface.EXTRA_BIG_PIC_PATH);
        }
        if (map.containsKey(JPushInterface.EXTRA_ALERT_TYPE)) {
            i = Integer.parseInt((String) map.get(JPushInterface.EXTRA_ALERT_TYPE));
        } else {
            i = -1;
        }
        if (i < -1 || i > 7) {
            i2 = -1;
        } else {
            i2 = i;
        }
        if (C0541a.f653e == null) {
            return null;
        }
        int identifier = C0541a.f653e.getResources().getIdentifier("jpush_notification_icon", "drawable", C0541a.f653e.getPackageName());
        if (identifier == 0) {
            if (C0541a.f650b != 0) {
                identifier = C0541a.f650b;
            } else {
                try {
                    identifier = C0541a.f653e.getPackageManager().getApplicationInfo(C0541a.f653e.getPackageName(), 0).icon;
                } catch (Throwable th) {
                    C0582e.m1307c(TAG, "failed to get application info and icon.", th);
                    return null;
                }
            }
        }
        RemoteViews buildContentView = buildContentView(str, str2);
        if (VERSION.SDK_INT >= 11) {
            Builder builder = new Builder(C0541a.f653e);
            builder.setContentTitle(str2).setContentText(str).setTicker(str).setSmallIcon(identifier);
            C0560b.m1188a(builder, DEFAULT_NOTIFICATION_CHANNEL_ID, (CharSequence) DEFAULT_NOTIFICATION_CHANNEL_NAME, i3, i2);
            if (VERSION.SDK_INT >= 16) {
                if (!TextUtils.isEmpty(str4)) {
                    BigTextStyle bigTextStyle = new BigTextStyle();
                    bigTextStyle.bigText(str4);
                    builder.setStyle(bigTextStyle);
                }
                if (!TextUtils.isEmpty(str5)) {
                    InboxStyle inboxStyle = new InboxStyle();
                    try {
                        TreeMap treeMap = new TreeMap();
                        JSONObject jSONObject = new JSONObject(str5);
                        Iterator keys = jSONObject.keys();
                        while (keys.hasNext()) {
                            String str8 = (String) keys.next();
                            treeMap.put(str8, jSONObject.optString(str8));
                        }
                        for (String addLine : treeMap.values()) {
                            inboxStyle.addLine(addLine);
                        }
                        inboxStyle.setSummaryText(" + " + jSONObject.length() + " new messages");
                    } catch (Throwable th2) {
                        C0582e.m1308d(TAG, "Set inbox style error: " + th2.getMessage());
                    }
                    builder.setStyle(inboxStyle);
                }
                if (!TextUtils.isEmpty(str7)) {
                    try {
                        BigPictureStyle bigPictureStyle = new BigPictureStyle();
                        bigPictureStyle.bigPicture(BitmapFactory.decodeFile(str7));
                        builder.setStyle(bigPictureStyle);
                    } catch (OutOfMemoryError e) {
                        C0582e.m1306c(TAG, "Create bitmap failed caused by OutOfMemoryError.");
                        e.printStackTrace();
                    } catch (Exception e2) {
                        C0582e.m1306c(TAG, "Create big picture style failed.");
                        e2.printStackTrace();
                    }
                }
                if (i3 != 0) {
                    if (i3 == 1) {
                        builder.setPriority(1);
                    } else if (i3 == 2) {
                        builder.setPriority(2);
                    } else if (i3 == -1) {
                        builder.setPriority(-1);
                    } else if (i3 == -2) {
                        builder.setPriority(-2);
                    } else {
                        builder.setPriority(0);
                    }
                }
                if (!TextUtils.isEmpty(str6)) {
                    if (VERSION.SDK_INT >= 21) {
                        try {
                            Class.forName("android.app.Notification$Builder").getDeclaredMethod("setCategory", new Class[]{String.class}).invoke(builder, new Object[]{str6});
                        } catch (ClassNotFoundException e3) {
                            e3.printStackTrace();
                        } catch (NoSuchMethodException e4) {
                            e4.printStackTrace();
                        } catch (Exception e5) {
                            e5.printStackTrace();
                        }
                    } else {
                        C0582e.m1306c(TAG, "Device rom SDK < 21, can not set notification category!");
                    }
                }
            }
            if (buildContentView != null) {
                builder.setContent(buildContentView);
            }
            builder.setDefaults(i2);
            return getNotification(builder);
        }
        Notification notification = new Notification(identifier, str, System.currentTimeMillis());
        resetNotificationParams(notification);
        notification.defaults = i2;
        if (str2 == null) {
            str2 = C0541a.f652d;
        }
        if (buildContentView != null) {
            notification.contentView = buildContentView;
        } else {
            C0560b.m1189a(notification, C0541a.f653e, str2, str, (PendingIntent) null);
        }
        return notification;
    }

    public String toString() {
        return "";
    }
}
