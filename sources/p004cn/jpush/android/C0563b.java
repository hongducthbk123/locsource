package p004cn.jpush.android;

import android.content.Context;
import android.os.Bundle;
import p004cn.jiguang.api.JCoreInterface;
import p004cn.jiguang.api.MultiSpHelper;
import p004cn.jpush.android.api.C0560b;
import p004cn.jpush.android.api.JPushInterface;
import p004cn.jpush.android.p037a.C0553i;
import p004cn.jpush.android.service.C0606c;

/* renamed from: cn.jpush.android.b */
public final class C0563b {
    /* renamed from: a */
    public static void m1205a(Context context, int i, boolean z) {
        if (z) {
            int b = C0553i.m1144b();
            if (i < b) {
                C0560b.m1190a(context, b - i);
            }
            MultiSpHelper.commitInt(context, "notification_num", i);
            return;
        }
        Bundle bundle = new Bundle();
        C0606c.m1392a(context, bundle, "intent.MULTI_PROCESS");
        bundle.putInt("multi_type", 2);
        bundle.putInt("notification_maxnum", i);
        JCoreInterface.sendAction(context, C0541a.f649a, bundle);
    }

    /* renamed from: a */
    public static int m1202a(Context context) {
        return MultiSpHelper.getInt(context, "notification_num", JPushInterface.f693a);
    }

    /* renamed from: a */
    public static void m1207a(Context context, String str, boolean z) {
        if (z || JCoreInterface.canCallDirect()) {
            MultiSpHelper.commitString(context, "setting_silence_push_time", str);
            return;
        }
        Bundle bundle = new Bundle();
        C0606c.m1392a(context, bundle, "intent.MULTI_PROCESS");
        bundle.putInt("multi_type", 4);
        bundle.putString("silence_push_time", str);
        JCoreInterface.sendAction(context, C0541a.f649a, bundle);
    }

    /* renamed from: b */
    public static String m1208b(Context context) {
        return MultiSpHelper.getString(context, "setting_push_time", "");
    }

    /* renamed from: b */
    public static void m1210b(Context context, String str, boolean z) {
        if (z || JCoreInterface.canCallDirect()) {
            MultiSpHelper.commitString(context, "setting_push_time", str);
            return;
        }
        Bundle bundle = new Bundle();
        C0606c.m1392a(context, bundle, "intent.MULTI_PROCESS");
        bundle.putInt("multi_type", 3);
        bundle.putString("enable_push_time", str);
        JCoreInterface.sendAction(context, C0541a.f649a, bundle);
    }

    /* renamed from: a */
    public static void m1206a(Context context, String str, String str2, boolean z) {
        if (z || JCoreInterface.canCallDirect()) {
            MultiSpHelper.commitString(context, "jpush_save_custom_builder" + str, str2);
            return;
        }
        Bundle bundle = new Bundle();
        C0606c.m1392a(context, bundle, "intent.MULTI_PROCESS");
        bundle.putInt("multi_type", 1);
        bundle.putString("notification_buidler_id", str);
        bundle.putString("notification_buidler", str2);
        JCoreInterface.sendAction(context, C0541a.f649a, bundle);
    }

    /* renamed from: a */
    public static void m1204a(Context context, int i, String str) {
        MultiSpHelper.commitString(context, "pluginPlatformRegid" + i, str);
    }

    /* renamed from: a */
    public static String m1203a(Context context, int i) {
        return MultiSpHelper.getString(context, "pluginPlatformRegid" + i, null);
    }

    /* renamed from: b */
    public static void m1209b(Context context, int i, boolean z) {
        MultiSpHelper.commitBoolean(context, "pluginPlatformRegidupload" + i, z);
    }

    /* renamed from: b */
    public static boolean m1211b(Context context, int i) {
        return MultiSpHelper.getBoolean(context, "pluginPlatformRegidupload" + i, false);
    }
}
