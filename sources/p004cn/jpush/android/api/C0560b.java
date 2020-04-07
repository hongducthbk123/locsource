package p004cn.jpush.android.api;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import com.google.common.primitives.Ints;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.Adler32;
import p004cn.jiguang.api.JCoreInterface;
import p004cn.jpush.android.C0541a;
import p004cn.jpush.android.C0563b;
import p004cn.jpush.android.data.C0589b;
import p004cn.jpush.android.data.C0594g;
import p004cn.jpush.android.p037a.C0546d;
import p004cn.jpush.android.p037a.C0553i;
import p004cn.jpush.android.p040d.C0577a;
import p004cn.jpush.android.p040d.C0579c;
import p004cn.jpush.android.p040d.C0582e;
import p004cn.jpush.android.p040d.C0586i;
import p004cn.jpush.android.p043ui.PopWinActivity;
import p004cn.jpush.android.service.PushReceiver;

/* renamed from: cn.jpush.android.api.b */
public final class C0560b {

    /* renamed from: a */
    private static boolean f736a = false;

    /* renamed from: a */
    public static void m1195a(Context context, boolean z) {
        if (z) {
            while (true) {
                Integer valueOf = Integer.valueOf(C0553i.m1142a());
                if (valueOf.intValue() != 0) {
                    m1198b(context, valueOf.intValue());
                } else {
                    return;
                }
            }
        } else {
            Bundle bundle = new Bundle();
            bundle.putString(NativeProtocol.WEB_DIALOG_ACTION, "intent.MULTI_PROCESS");
            bundle.putInt("multi_type", 10);
            JCoreInterface.sendAction(context, C0541a.f649a, bundle);
        }
    }

    /* renamed from: a */
    public static void m1190a(Context context, int i) {
        if (i > 0) {
            for (int i2 = 0; i2 < i; i2++) {
                Integer valueOf = Integer.valueOf(C0553i.m1142a());
                if (valueOf.intValue() != 0) {
                    m1198b(context, valueOf.intValue());
                }
            }
        }
    }

    /* renamed from: b */
    private static void m1198b(Context context, int i) {
        if (context == null) {
            context = C0541a.f653e;
        }
        ((NotificationManager) context.getSystemService("notification")).cancel(i);
    }

    /* renamed from: a */
    public static void m1193a(Context context, C0589b bVar, int i) {
        if (context == null) {
            context = C0541a.f653e;
        }
        ((NotificationManager) context.getSystemService("notification")).cancel(m1184a(bVar, 0));
    }

    /* renamed from: a */
    public static void m1192a(final Context context, final C0589b bVar) {
        new Thread(new Runnable() {
            public final void run() {
                C0560b.m1199b(context, bVar);
            }
        }).start();
    }

    /* renamed from: b */
    public static void m1199b(Context context, C0589b bVar) {
        PendingIntent activity;
        int i;
        Intent intent;
        int a = m1184a(bVar, 0);
        if (bVar.f811i && bVar.f808f) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (bVar instanceof C0594g) {
                Map a2 = m1187a(bVar);
                String str = TextUtils.isEmpty(bVar.f817o) ? context.getPackageName() : bVar.f817o;
                if (TextUtils.isEmpty(bVar.f824v)) {
                    m1194a(context, a2, 0, "", str, bVar);
                    return;
                }
                PushNotificationBuilder b = JPushInterface.m1181b(bVar.f809g);
                String developerArg0 = b.getDeveloperArg0();
                Notification buildNotification = b.buildNotification(a2);
                if (buildNotification == null || TextUtils.isEmpty(bVar.f824v)) {
                    C0582e.m1306c("NotificationHelper", "Got NULL notification. Give up to show.");
                    return;
                }
                if (!bVar.mo6878a()) {
                    C0582e.m1302a("NotificationHelper", "running flag:" + JCoreInterface.getRuningFlag());
                    if (C0577a.m1285c(context, PushReceiver.class.getCanonicalName())) {
                        intent = new Intent("cn.jpush.android.intent.NOTIFICATION_OPENED_PROXY." + UUID.randomUUID().toString());
                        intent.putExtra(JPushInterface.EXTRA_NOTI_TYPE, bVar.f810h);
                        if (JCoreInterface.getRuningFlag()) {
                            intent.setClass(context, PopWinActivity.class);
                            intent.putExtra("isNotification", true);
                        } else {
                            intent.setClass(context, PushReceiver.class);
                        }
                    } else {
                        Intent intent2 = new Intent(JPushInterface.ACTION_NOTIFICATION_OPENED);
                        intent2.addCategory(str);
                        if ((VERSION.SDK_INT >= 25 || VERSION.SDK_INT < 21) && JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent2.getAction())) {
                            List a3 = C0577a.m1271a(context, intent2, (String) null);
                            if (!a3.isEmpty()) {
                                intent2.setComponent(new ComponentName(context, (String) a3.get(0)));
                            }
                        }
                        intent = intent2;
                    }
                    intent.putExtra("sdktype", C0541a.f649a);
                    m1196a(intent, a2, a);
                    intent.putExtra("app", str);
                    if (!TextUtils.isEmpty(developerArg0)) {
                        intent.putExtra(JPushInterface.EXTRA_NOTIFICATION_DEVELOPER_ARG0, developerArg0);
                    }
                    if (JCoreInterface.getRuningFlag()) {
                        activity = PendingIntent.getActivity(context, 0, intent, Ints.MAX_POWER_OF_TWO);
                    } else {
                        activity = PendingIntent.getBroadcast(context, 0, intent, Ints.MAX_POWER_OF_TWO);
                    }
                } else {
                    Intent c = m1200c(context, bVar);
                    if (c != null) {
                        activity = PendingIntent.getActivity(context, a, c, 134217728);
                    } else {
                        return;
                    }
                }
                buildNotification.contentIntent = activity;
                if (!JPushInterface.m1180a(bVar.f809g)) {
                    if (1 == bVar.f810h) {
                        bVar.f822t = 1;
                    }
                    switch (bVar.f822t) {
                        case 0:
                            i = 1;
                            break;
                        case 1:
                            i = 16;
                            break;
                        case 2:
                            i = 32;
                            break;
                        default:
                            i = 1;
                            break;
                    }
                    buildNotification.flags = i | 1;
                }
                if (C0577a.m1286d(context)) {
                    buildNotification.defaults = 0;
                }
                if (buildNotification != null) {
                    notificationManager.notify(a, buildNotification);
                }
                if (1 != bVar.f810h) {
                    m1191a(context, a, true);
                    C0546d.m1124a(bVar.f805c, 1018, null, context);
                }
                m1194a(context, a2, a, developerArg0, str, bVar);
            }
        }
    }

    /* renamed from: a */
    private static int m1184a(C0589b bVar, int i) {
        String str = bVar.f805c;
        if (!TextUtils.isEmpty(bVar.f806d)) {
            str = bVar.f806d;
        }
        return m1185a(str, i);
    }

    /* renamed from: a */
    public static Map<String, String> m1187a(C0589b bVar) {
        HashMap hashMap = new HashMap();
        if (bVar != null) {
            hashMap.put(JPushInterface.EXTRA_MSG_ID, bVar.f805c);
            hashMap.put(JPushInterface.EXTRA_ALERT, bVar.f824v);
            hashMap.put(JPushInterface.EXTRA_ALERT_TYPE, bVar.f814l);
            if (!TextUtils.isEmpty(bVar.f823u)) {
                hashMap.put(JPushInterface.EXTRA_NOTIFICATION_TITLE, bVar.f823u);
            }
            if (!TextUtils.isEmpty(bVar.f816n)) {
                hashMap.put(JPushInterface.EXTRA_EXTRA, bVar.f816n);
            }
            if (bVar.f825w == 1 && !TextUtils.isEmpty(bVar.f826x)) {
                hashMap.put(JPushInterface.EXTRA_BIG_TEXT, bVar.f826x);
            } else if (bVar.f825w == 2 && !TextUtils.isEmpty(bVar.f828z)) {
                hashMap.put(JPushInterface.EXTRA_INBOX, bVar.f828z);
            } else if (bVar.f825w == 3 && !TextUtils.isEmpty(bVar.f827y)) {
                hashMap.put(JPushInterface.EXTRA_BIG_PIC_PATH, bVar.f827y);
            }
            if (bVar.f794A != 0) {
                hashMap.put(JPushInterface.EXTRA_NOTI_PRIORITY, bVar.f794A);
            }
            if (!TextUtils.isEmpty(bVar.f795B)) {
                hashMap.put(JPushInterface.EXTRA_NOTI_CATEGORY, bVar.f795B);
            }
        }
        return hashMap;
    }

    /* renamed from: c */
    public static Intent m1200c(Context context, C0589b bVar) {
        if (context == null) {
            C0582e.m1306c("NotificationHelper", "context was null");
            return null;
        }
        if (bVar != null) {
            if (3 == ((C0594g) bVar).f853L || 4 == ((C0594g) bVar).f853L || ((C0594g) bVar).f853L == 0) {
                return C0577a.m1268a(context, bVar, false);
            }
            if (2 == ((C0594g) bVar).f853L) {
                Intent intent = new Intent(context, PopWinActivity.class);
                intent.putExtra("body", bVar);
                intent.addFlags(335544320);
                return intent;
            }
        }
        return C0577a.m1268a(context, bVar, false);
    }

    /* renamed from: a */
    private static boolean m1197a(String str, CharSequence charSequence, int i, int i2) {
        int i3 = 3;
        if (f736a) {
            return true;
        }
        if (VERSION.SDK_INT < 26) {
            return false;
        }
        if (C0541a.f653e == null) {
            C0582e.m1308d("NotificationHelper", "ApplicationContext is null!");
            return false;
        } else if (C0541a.f653e.getApplicationInfo().targetSdkVersion < 26) {
            return false;
        } else {
            NotificationManager notificationManager = (NotificationManager) C0541a.f653e.getSystemService("notification");
            if (notificationManager == null) {
                C0582e.m1308d("NotificationHelper", "NotificationManager is null!");
                return false;
            }
            switch (i) {
                case -2:
                    i3 = 1;
                    break;
                case -1:
                    i3 = 2;
                    break;
                case 1:
                    i3 = 4;
                    break;
                case 2:
                    i3 = 5;
                    break;
            }
            try {
                Class cls = Class.forName("android.app.NotificationChannel");
                try {
                    Constructor constructor = cls.getConstructor(new Class[]{String.class, CharSequence.class, Integer.TYPE});
                    constructor.setAccessible(true);
                    try {
                        Object newInstance = constructor.newInstance(new Object[]{str, charSequence, Integer.valueOf(i3)});
                        if (newInstance == null) {
                            C0582e.m1308d("NotificationHelper", "new NotificationChannel fail, return");
                            return false;
                        }
                        Boolean[] boolArr = {Boolean.valueOf(true)};
                        Class[] clsArr = {Boolean.TYPE};
                        if ((i2 & 4) != 0) {
                            try {
                                C0586i.m1318a(newInstance, "enableLights", clsArr, boolArr);
                            } catch (Exception e) {
                                C0582e.m1306c("NotificationHelper", "enableLights fail:" + e.toString());
                            }
                        }
                        if ((i2 & 2) != 0) {
                            try {
                                C0586i.m1318a(newInstance, "enableVibration", clsArr, boolArr);
                            } catch (Exception e2) {
                                C0582e.m1306c("NotificationHelper", "enableLights fail:" + e2.toString());
                            }
                        }
                        try {
                            C0586i.m1318a(notificationManager, "createNotificationChannel", new Class[]{cls}, new Object[]{newInstance});
                        } catch (Exception e3) {
                            C0582e.m1308d("NotificationHelper", "createNotificationChannel fail:" + e3.toString());
                        }
                        f736a = true;
                        return true;
                    } catch (InstantiationException e4) {
                        e4.printStackTrace();
                        return false;
                    } catch (IllegalAccessException e5) {
                        e5.printStackTrace();
                        return false;
                    }
                } catch (InvocationTargetException e6) {
                    C0582e.m1308d("NotificationHelper", "new NotificationChannel fail:" + e6.toString());
                }
            } catch (NoSuchMethodException e7) {
                C0582e.m1308d("NotificationHelper", "new NotificationChannel fail:" + e7.toString());
            } catch (Throwable th) {
                C0582e.m1308d("NotificationHelper", "new NotificationChannel fail:" + th.toString());
            }
        }
    }

    /* renamed from: a */
    public static void m1188a(Builder builder, String str, CharSequence charSequence, int i, int i2) {
        if (m1197a(str, charSequence, i, i2)) {
            try {
                C0586i.m1318a(builder, "setChannelId", new Class[]{String.class}, new String[]{str});
            } catch (Throwable th) {
                C0582e.m1306c("NotificationHelper", "setChannelId error" + th);
            }
        }
    }

    /* renamed from: a */
    public static void m1191a(Context context, int i, boolean z) {
        if (!C0553i.m1145b(i)) {
            C0553i.m1143a(i);
        }
        if (C0553i.m1144b() > C0563b.m1202a(context)) {
            int a = C0553i.m1142a();
            if (a != 0) {
                m1198b(context, a);
            }
        }
    }

    /* renamed from: a */
    public static void m1194a(Context context, Map<String, String> map, int i, String str, String str2, C0589b bVar) {
        Intent intent = new Intent(JPushInterface.ACTION_NOTIFICATION_RECEIVED);
        try {
            C0582e.m1302a("NotificationHelper", "Send push received broadcast to developer defined receiver");
            m1196a(intent, map, i);
            if (!TextUtils.isEmpty(str)) {
                intent.putExtra(JPushInterface.EXTRA_NOTIFICATION_DEVELOPER_ARG0, str);
            }
            if (bVar.mo6878a() && (bVar instanceof C0594g)) {
                C0594g gVar = (C0594g) bVar;
                if (!(gVar.f853L == 0 || gVar.f853L == 4)) {
                    if (gVar.f858Q != null && gVar.f858Q.startsWith("file://")) {
                        gVar.f858Q = gVar.f858Q.replaceFirst("file://", "");
                        intent.putExtra(JPushInterface.EXTRA_RICHPUSH_HTML_PATH, gVar.f858Q);
                    }
                    if (gVar.f855N != null && gVar.f855N.size() > 0) {
                        StringBuilder sb = new StringBuilder();
                        String b = C0579c.m1299b(context, bVar.f805c);
                        Iterator it = gVar.f855N.iterator();
                        while (it.hasNext()) {
                            String str3 = (String) it.next();
                            if (str3.startsWith("http://")) {
                                str3 = C0579c.m1295a(str3);
                            }
                            if (TextUtils.isEmpty(sb.toString())) {
                                sb.append(b).append(str3);
                            } else {
                                sb.append(",").append(b).append(str3);
                            }
                        }
                        intent.putExtra(JPushInterface.EXTRA_RICHPUSH_HTML_RES, sb.toString());
                    }
                }
            }
            intent.addCategory(str2);
            intent.setPackage(context.getPackageName());
            context.sendBroadcast(intent, str2 + ".permission.JPUSH_MESSAGE");
        } catch (Throwable th) {
            C0582e.m1306c("NotificationHelper", "sendNotificationReceivedBroadcast error:" + th.getMessage());
            C0577a.m1282b(context, intent, str2 + ".permission.JPUSH_MESSAGE");
        }
    }

    /* renamed from: a */
    public static void m1196a(Intent intent, Map<String, String> map, int i) {
        for (String str : map.keySet()) {
            intent.putExtra(str, (String) map.get(str));
        }
        if (i != 0) {
            intent.putExtra(JPushInterface.EXTRA_NOTIFICATION_ID, i);
        }
    }

    /* renamed from: a */
    public static int m1185a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return Integer.valueOf(str).intValue();
        } catch (Exception e) {
            Adler32 adler32 = new Adler32();
            adler32.update(str.getBytes());
            int value = (int) adler32.getValue();
            if (value < 0) {
                value = Math.abs(value);
            }
            int i2 = value + (13889152 * i);
            if (i2 < 0) {
                return Math.abs(i2);
            }
            return i2;
        }
    }

    /* renamed from: a */
    public static int m1183a(int i) {
        int i2;
        switch (i) {
            case -1:
                try {
                    i2 = ((Integer) m1186a("R$drawable", new String[]{"jpush_notification_icon"}).get("jpush_notification_icon")).intValue();
                } catch (Exception e) {
                    i2 = 0;
                }
                if (i2 <= 0) {
                    return 17301618;
                }
                return i2;
            case 0:
                return 17301647;
            case 2:
                return 17301618;
            case 3:
                return 17301567;
            default:
                return 17301586;
        }
    }

    /* renamed from: a */
    private static HashMap<String, Integer> m1186a(String str, String[] strArr) {
        if (TextUtils.isEmpty(str)) {
            throw new NullPointerException("parameter resType or fieldNames error.");
        }
        HashMap<String, Integer> hashMap = new HashMap<>();
        try {
            Class[] declaredClasses = Class.forName(C0541a.f653e.getPackageName() + ".R").getDeclaredClasses();
            int length = declaredClasses.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Class cls = declaredClasses[i];
                if (cls.getName().contains(str)) {
                    for (int i2 = 0; i2 <= 0; i2++) {
                        String str2 = strArr[0];
                        hashMap.put(str2, Integer.valueOf(cls.getDeclaredField(str2).getInt(str2)));
                    }
                } else {
                    i++;
                }
            }
        } catch (Exception e) {
        }
        return hashMap;
    }

    /* renamed from: a */
    public static void m1189a(Notification notification, Context context, String str, String str2, PendingIntent pendingIntent) {
        try {
            Class.forName("android.app.Notification").getDeclaredMethod("setLatestEventInfo", new Class[]{Context.class, CharSequence.class, CharSequence.class, PendingIntent.class}).invoke(notification, new Object[]{context, str, str2, null});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
