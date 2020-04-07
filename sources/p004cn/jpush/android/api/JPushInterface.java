package p004cn.jpush.android.api;

import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import p004cn.jiguang.api.JCoreInterface;
import p004cn.jiguang.api.MultiSpHelper;
import p004cn.jpush.android.C0541a;
import p004cn.jpush.android.C0563b;
import p004cn.jpush.android.data.JPushLocalNotification;
import p004cn.jpush.android.p037a.C0544b;
import p004cn.jpush.android.p037a.C0545c;
import p004cn.jpush.android.p037a.C0546d;
import p004cn.jpush.android.p037a.C0548f;
import p004cn.jpush.android.p037a.C0555k;
import p004cn.jpush.android.p039c.C0573g;
import p004cn.jpush.android.p040d.C0577a;
import p004cn.jpush.android.p040d.C0582e;
import p004cn.jpush.android.service.C0602a;
import p004cn.jpush.android.service.C0606c;
import p004cn.jpush.android.service.ServiceInterface;

/* renamed from: cn.jpush.android.api.JPushInterface */
public class JPushInterface {
    public static final String ACTION_CONNECTION_CHANGE = "cn.jpush.android.intent.CONNECTION";
    public static final String ACTION_MESSAGE_RECEIVED = "cn.jpush.android.intent.MESSAGE_RECEIVED";
    public static final String ACTION_NOTIFICATION_CLICK_ACTION = "cn.jpush.android.intent.NOTIFICATION_CLICK_ACTION";
    public static final String ACTION_NOTIFICATION_OPENED = "cn.jpush.android.intent.NOTIFICATION_OPENED";
    public static final String ACTION_NOTIFICATION_RECEIVED = "cn.jpush.android.intent.NOTIFICATION_RECEIVED";
    public static final String ACTION_NOTIFICATION_RECEIVED_PROXY = "cn.jpush.android.intent.NOTIFICATION_RECEIVED_PROXY";
    public static final String ACTION_REGISTRATION_ID = "cn.jpush.android.intent.REGISTRATION";
    public static final String ACTION_RICHPUSH_CALLBACK = "cn.jpush.android.intent.ACTION_RICHPUSH_CALLBACK";
    public static final String EXTRA_ACTIVITY_PARAM = "cn.jpush.android.ACTIVITY_PARAM";
    public static final String EXTRA_ALERT = "cn.jpush.android.ALERT";
    public static final String EXTRA_ALERT_TYPE = "cn.jpush.android.ALERT_TYPE";
    public static final String EXTRA_APP_KEY = "cn.jpush.android.APPKEY";
    public static final String EXTRA_BIG_PIC_PATH = "cn.jpush.android.BIG_PIC_PATH";
    public static final String EXTRA_BIG_TEXT = "cn.jpush.android.BIG_TEXT";
    public static final String EXTRA_CONNECTION_CHANGE = "cn.jpush.android.CONNECTION_CHANGE";
    public static final String EXTRA_CONTENT_TYPE = "cn.jpush.android.CONTENT_TYPE";
    public static final String EXTRA_EXTRA = "cn.jpush.android.EXTRA";
    public static final String EXTRA_INBOX = "cn.jpush.android.INBOX";
    public static final String EXTRA_MESSAGE = "cn.jpush.android.MESSAGE";
    public static final String EXTRA_MSG_ID = "cn.jpush.android.MSG_ID";
    public static final String EXTRA_NOTIFICATION_ACTION_EXTRA = "cn.jpush.android.NOTIFIACATION_ACTION_EXTRA";
    public static final String EXTRA_NOTIFICATION_DEVELOPER_ARG0 = "cn.jpush.android.NOTIFICATION_DEVELOPER_ARG0";
    public static final String EXTRA_NOTIFICATION_ID = "cn.jpush.android.NOTIFICATION_ID";
    public static final String EXTRA_NOTIFICATION_TITLE = "cn.jpush.android.NOTIFICATION_CONTENT_TITLE";
    public static final String EXTRA_NOTI_CATEGORY = "cn.jpush.android.NOTI_CATEGORY";
    public static final String EXTRA_NOTI_PRIORITY = "cn.jpush.android.NOTI_PRIORITY";
    public static final String EXTRA_NOTI_TYPE = "cn.jpush.android.NOTIFICATION_TYPE";
    public static final String EXTRA_PUSH_ID = "cn.jpush.android.PUSH_ID";
    public static final String EXTRA_REGISTRATION_ID = "cn.jpush.android.REGISTRATION_ID";
    public static final String EXTRA_RICHPUSH_FILE_PATH = "cn.jpush.android.FILE_PATH";
    public static final String EXTRA_RICHPUSH_FILE_TYPE = "cn.jpush.android.FILE_TYPE";
    public static final String EXTRA_RICHPUSH_HTML_PATH = "cn.jpush.android.HTML_PATH";
    public static final String EXTRA_RICHPUSH_HTML_RES = "cn.jpush.android.HTML_RES";
    public static final String EXTRA_STATUS = "cn.jpush.android.STATUS";
    public static final String EXTRA_TITLE = "cn.jpush.android.TITLE";

    /* renamed from: a */
    public static int f693a = 5;

    /* renamed from: b */
    private static final Integer f694b = Integer.valueOf(0);

    /* renamed from: cn.jpush.android.api.JPushInterface$a */
    public static class C0558a {

        /* renamed from: a */
        public static int f695a = 0;

        /* renamed from: b */
        public static int f696b = 6001;

        /* renamed from: c */
        public static int f697c = 6002;

        /* renamed from: d */
        public static int f698d = 6003;

        /* renamed from: e */
        public static int f699e = 6004;

        /* renamed from: f */
        public static int f700f = 6005;

        /* renamed from: g */
        public static int f701g = 6006;

        /* renamed from: h */
        public static int f702h = 6007;

        /* renamed from: i */
        public static int f703i = 6008;

        /* renamed from: j */
        public static int f704j = 6009;

        /* renamed from: k */
        public static int f705k = 6010;

        /* renamed from: l */
        public static int f706l = 6011;

        /* renamed from: m */
        public static int f707m = 6012;

        /* renamed from: n */
        public static int f708n = 6013;

        /* renamed from: o */
        public static int f709o = 6014;

        /* renamed from: p */
        public static int f710p = 6015;

        /* renamed from: q */
        public static int f711q = 6016;

        /* renamed from: r */
        public static int f712r = 6017;

        /* renamed from: s */
        public static int f713s = 6018;

        /* renamed from: t */
        public static int f714t = 6019;

        /* renamed from: u */
        public static int f715u = 6020;

        /* renamed from: v */
        public static int f716v = 6021;

        /* renamed from: w */
        public static int f717w = 6022;

        /* renamed from: x */
        public static int f718x = 6023;

        /* renamed from: y */
        public static int f719y = 6024;

        /* renamed from: z */
        public static int f720z = 6025;
    }

    static {
        JCoreInterface.initActionExtra(C0541a.f649a, C0545c.class);
        JCoreInterface.initAction(C0541a.f649a, C0544b.class);
    }

    public static void init(Context context) {
        C0582e.m1302a("JPushInterface", "action:init - sdkVersion:" + ServiceInterface.m1362a() + ", buildId:401");
        m1178a(context);
        if (JCoreInterface.init(context, false) && C0541a.m1120a(context)) {
            if (JCoreInterface.getDebugMode() && !C0577a.m1278a(context)) {
                C0582e.m1302a("JPushInterface", "检测到当前没有网络。长连接将在有网络时自动继续建立。");
            }
            if (C0563b.m1202a(context) == -1) {
                setLatestNotificationNumber(context, f693a);
            }
            ServiceInterface.m1363a(context);
        }
    }

    public static void resumePush(Context context) {
        C0582e.m1302a("JPushInterface", "action:resumePush");
        m1178a(context);
        ServiceInterface.m1370b(context, 1);
        C0573g.m1238a().mo6848b(context);
    }

    public static void stopPush(Context context) {
        C0582e.m1302a("JPushInterface", "action:stopPush");
        m1178a(context);
        ServiceInterface.m1364a(context, 1);
        C0573g.m1238a().mo6850c(context);
    }

    public static boolean isPushStopped(Context context) {
        m1178a(context);
        return ServiceInterface.m1372c(context);
    }

    public static void setDebugMode(boolean z) {
        JCoreInterface.setDebugMode(z);
    }

    public static void setPushTime(Context context, Set<Integer> set, int i, int i2) {
        m1178a(context);
        if (JCoreInterface.getDebugMode() && !C0577a.m1278a(context)) {
            C0582e.m1302a("JPushInterface", "检测到当前没有网络。此动作将在有网络时自动继续执行。");
        }
        if (set == null) {
            m1179a(context, true, "0123456_0^23");
        } else if (set.size() == 0 || set.isEmpty()) {
            m1179a(context, false, "0123456_0^23");
        } else if (i > i2) {
            C0582e.m1308d("JPushInterface", "Invalid time format - startHour should less than endHour");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Integer num : set) {
                if (num.intValue() > 6 || num.intValue() < 0) {
                    C0582e.m1308d("JPushInterface", "Invalid day format - " + num);
                    return;
                }
                sb.append(num);
            }
            sb.append("_");
            sb.append(i);
            sb.append("^");
            sb.append(i2);
            m1179a(context, true, sb.toString());
        }
    }

    public static void setSilenceTime(Context context, int i, int i2, int i3, int i4) {
        m1178a(context);
        if (i < 0 || i2 < 0 || i3 < 0 || i4 < 0 || i2 > 59 || i4 > 59 || i3 > 23 || i > 23) {
            C0582e.m1308d("JPushInterface", "Invalid parameter format, startHour and endHour should between 0 ~ 23, startMins and endMins should between 0 ~ 59. ");
        } else if (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) {
            ServiceInterface.m1366a(context, "");
            C0582e.m1302a("JPushInterface", "Remove the silence time!");
        } else if (ServiceInterface.m1368a(context, i, i2, i3, i4)) {
            C0582e.m1302a("JPushInterface", "Set Silence PushTime - " + i + " : " + i2 + " -- " + i3 + " : " + i4);
        } else {
            C0582e.m1308d("JPushInterface", "Set Silence PushTime Failed");
        }
    }

    public static String getRegistrationID(Context context) {
        m1178a(context);
        return JCoreInterface.getRegistrationID(context);
    }

    /* renamed from: a */
    private static void m1178a(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("NULL context");
        }
    }

    public static String getUdid(Context context) {
        m1178a(context);
        return JCoreInterface.getDeviceId(context);
    }

    public static void setLatestNotificationNumber(Context context, int i) {
        m1178a(context);
        C0582e.m1302a("JPushInterface", "action:setLatestNotificationNumber : " + i);
        if (i <= 0) {
            C0582e.m1308d("JPushInterface", "maxNum should > 0, Give up action..");
        } else {
            ServiceInterface.m1371c(context, i);
        }
    }

    public static void setDefaultPushNotificationBuilder(DefaultPushNotificationBuilder defaultPushNotificationBuilder) {
        if (defaultPushNotificationBuilder == null) {
            throw new IllegalArgumentException("NULL notification");
        }
        ServiceInterface.m1365a(C0541a.f653e, f694b, defaultPushNotificationBuilder);
    }

    public static void clearAllNotifications(Context context) {
        m1178a(context);
        ServiceInterface.m1369b(context);
    }

    public static void clearNotificationById(Context context, int i) {
        m1178a(context);
        ((NotificationManager) context.getSystemService("notification")).cancel(i);
    }

    public static void setPushNotificationBuilder(Integer num, DefaultPushNotificationBuilder defaultPushNotificationBuilder) {
        if (defaultPushNotificationBuilder == null) {
            throw new IllegalArgumentException("NULL pushNotificationBuilder");
        } else if (num.intValue() <= 0) {
            C0582e.m1308d("JPushInterface", "id should be larger than 0");
        } else {
            ServiceInterface.m1365a(C0541a.f653e, num, defaultPushNotificationBuilder);
        }
    }

    /* renamed from: a */
    static boolean m1180a(int i) {
        if (i <= 0) {
            return false;
        }
        if (m1177a(i) != null) {
            return true;
        }
        C0582e.m1306c("JPushInterface", "The builder with id:" + i + " has not been set in your app, use default builder!");
        return false;
    }

    /* renamed from: b */
    static PushNotificationBuilder m1181b(int i) {
        if (i <= 0) {
            i = f694b.intValue();
        }
        PushNotificationBuilder pushNotificationBuilder = null;
        try {
            pushNotificationBuilder = m1177a(i);
        } catch (Exception e) {
        }
        if (pushNotificationBuilder == null) {
            return new DefaultPushNotificationBuilder();
        }
        return pushNotificationBuilder;
    }

    @Deprecated
    public void setAliasAndTags(Context context, String str, Set<String> set) {
        m1178a(context);
        C0555k.m1158a(context, str, set, null, 0, 0);
    }

    @Deprecated
    public static void setAliasAndTags(Context context, String str, Set<String> set, TagAliasCallback tagAliasCallback) {
        m1178a(context);
        C0555k.m1158a(context, str, set, tagAliasCallback, 0, 0);
    }

    @Deprecated
    public static void setTags(Context context, Set<String> set, TagAliasCallback tagAliasCallback) {
        m1178a(context);
        setAliasAndTags(context, null, set, tagAliasCallback);
    }

    @Deprecated
    public static void setAlias(Context context, String str, TagAliasCallback tagAliasCallback) {
        m1178a(context);
        setAliasAndTags(context, str, null, tagAliasCallback);
    }

    public static Set<String> filterValidTags(Set<String> set) {
        return C0555k.m1151a(set);
    }

    public static void setTags(Context context, int i, Set<String> set) {
        m1178a(context);
        C0555k.m1155a(context, i, set, 1, 2);
    }

    public static void addTags(Context context, int i, Set<String> set) {
        m1178a(context);
        C0555k.m1155a(context, i, set, 1, 1);
    }

    public static void deleteTags(Context context, int i, Set<String> set) {
        m1178a(context);
        C0555k.m1155a(context, i, set, 1, 3);
    }

    public static void cleanTags(Context context, int i) {
        m1178a(context);
        C0555k.m1155a(context, i, (Set<String>) new HashSet<String>(), 1, 4);
    }

    public static void getAllTags(Context context, int i) {
        m1178a(context);
        C0555k.m1155a(context, i, (Set<String>) new HashSet<String>(), 1, 5);
    }

    public static void checkTagBindState(Context context, int i, String str) {
        m1178a(context);
        HashSet hashSet = null;
        if (!TextUtils.isEmpty(str)) {
            hashSet = new HashSet();
            hashSet.add(str);
        }
        C0555k.m1155a(context, i, (Set<String>) hashSet, 1, 6);
    }

    public static void setAlias(Context context, int i, String str) {
        m1178a(context);
        C0555k.m1154a(context, i, str, 2, 2);
    }

    public static void deleteAlias(Context context, int i) {
        m1178a(context);
        C0555k.m1154a(context, i, (String) null, 2, 3);
    }

    public static void getAlias(Context context, int i) {
        m1178a(context);
        C0555k.m1154a(context, i, (String) null, 2, 5);
    }

    public static void setMobileNumber(Context context, int i, String str) {
        m1178a(context);
        C0548f.m1129a();
        C0582e.m1302a("MobileNumberHelper", "action - setMobileNubmer, sequence:" + i + ",mobileNumber:" + str);
        if (ServiceInterface.m1373d(context)) {
            C0548f.m1131a(context, i, C0558a.f707m, str);
            return;
        }
        if (!(context instanceof Application)) {
            context = context.getApplicationContext();
        }
        if (!C0541a.m1120a(context)) {
            C0548f.m1131a(context, i, C0558a.f704j, str);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(NativeProtocol.WEB_DIALOG_ACTION, "intent.MOBILE_NUMBER");
        bundle.putInt("sequence", i);
        bundle.putString("mobile_number", str);
        JCoreInterface.sendAction(context, C0541a.f649a, bundle);
    }

    public static void reportNotificationOpened(Context context, String str) {
        m1178a(context);
        if (TextUtils.isEmpty(str)) {
            C0582e.m1308d("JPushInterface", "The msgId is not valid - " + str);
        }
        C0546d.m1124a(str, 1028, null, context);
    }

    public static void reportNotificationOpened(Context context, String str, byte b) {
        m1178a(context);
        if (TextUtils.isEmpty(str)) {
            C0582e.m1308d("JPushInterface", "The msgId is not valid - " + str);
        }
        C0546d.m1125a(str, "", b, 1000, context);
    }

    public static boolean getConnectionState(Context context) {
        m1178a(context);
        return JCoreInterface.getConnectionState(context);
    }

    public static void onResume(Context context) {
        m1178a(context);
        JCoreInterface.onResume(context);
    }

    public static void onPause(Context context) {
        m1178a(context);
        JCoreInterface.onPause(context);
    }

    public static void onFragmentResume(Context context, String str) {
        m1178a(context);
        JCoreInterface.onFragmentResume(context, str);
    }

    public static void onFragmentPause(Context context, String str) {
        m1178a(context);
        JCoreInterface.onFragmentPause(context, str);
    }

    public static void onKillProcess(Context context) {
        JCoreInterface.onKillProcess(context);
    }

    public static void setStatisticsSessionTimeout(long j) {
        if (j < 10) {
            C0582e.m1306c("JPushInterface", "sesseion timeout less than 10s");
        } else if (j > 86400) {
            C0582e.m1306c("JPushInterface", "sesseion timeout larger than 1day");
        }
    }

    public static void setStatisticsEnable(boolean z) {
    }

    public static void initCrashHandler(Context context) {
        m1178a(context);
        JCoreInterface.initCrashHandler(context);
    }

    public static void stopCrashHandler(Context context) {
        m1178a(context);
        JCoreInterface.stopCrashHandler(context);
    }

    public static void addLocalNotification(Context context, JPushLocalNotification jPushLocalNotification) {
        m1178a(context);
        C0602a.m1376a(context).mo6989a(context, jPushLocalNotification, false);
    }

    public static void removeLocalNotification(Context context, long j) {
        m1178a(context);
        Bundle bundle = new Bundle();
        C0606c.m1392a(context, bundle, "intent.MULTI_PROCESS");
        bundle.putInt("multi_type", 7);
        bundle.putLong("local_notification_id", j);
        JCoreInterface.sendAction(context, C0541a.f649a, bundle);
    }

    public static void clearLocalNotifications(Context context) {
        m1178a(context);
        Bundle bundle = new Bundle();
        C0606c.m1392a(context, bundle, "intent.MULTI_PROCESS");
        bundle.putInt("multi_type", 8);
        JCoreInterface.sendAction(context, C0541a.f649a, bundle);
    }

    public static String getStringTags(Set<String> set) {
        return C0555k.m1162b(set);
    }

    /* renamed from: a */
    private static PushNotificationBuilder m1177a(String str) throws NumberFormatException {
        String string = MultiSpHelper.getString(C0541a.f653e, "jpush_save_custom_builder" + str, "");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        if (string.startsWith("basic") || string.startsWith("custom")) {
            return BasicPushNotificationBuilder.m1172a(string);
        }
        return MultiActionsNotificationBuilder.parseFromPreference(string);
    }

    public static void requestPermission(Context context) {
        if (context == null) {
            C0582e.m1306c("JPushInterface", "[requestPermission] unexcepted - context was null");
        } else {
            JCoreInterface.requestPermission(context);
        }
    }

    public static void setDaemonAction(String str) {
        JCoreInterface.setDaemonAction(str);
    }

    public static void setPowerSaveMode(Context context, boolean z) {
        JCoreInterface.setPowerSaveMode(context, z);
    }

    /* renamed from: a */
    private static void m1179a(Context context, boolean z, String str) {
        MultiSpHelper.commitBoolean(context, "notification_enabled", z);
        if (!z) {
            C0582e.m1302a("JPushInterface", "action:setPushTime - closed");
            return;
        }
        String str2 = "([0-9]|1[0-9]|2[0-3])\\^([0-9]|1[0-9]|2[0-3])";
        if (Pattern.compile("([0-6]{0,7})_((" + str2 + ")|(" + str2 + "-)+(" + str2 + "))").matcher(str).matches()) {
            String b = C0563b.m1208b(context);
            if (str.equals(b)) {
                C0582e.m1302a("JPushInterface", "Already SetPushTime, give up - " + b);
                return;
            }
            C0582e.m1302a("JPushInterface", "action:setPushTime - enabled:" + z + ", pushTime:" + str);
            C0563b.m1210b(context, str, false);
            return;
        }
        C0582e.m1308d("JPushInterface", "Invalid time format - " + str);
    }
}
