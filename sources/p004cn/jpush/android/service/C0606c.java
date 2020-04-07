package p004cn.jpush.android.service;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.internal.NativeProtocol;
import p004cn.jiguang.api.MultiSpHelper;
import p004cn.jpush.android.C0541a;
import p004cn.jpush.android.C0563b;
import p004cn.jpush.android.api.C0560b;
import p004cn.jpush.android.data.JPushLocalNotification;
import p004cn.jpush.android.p037a.C0548f;
import p004cn.jpush.android.p037a.C0552h;
import p004cn.jpush.android.p037a.C0555k;
import p004cn.jpush.android.p039c.C0571f;
import p004cn.jpush.android.p040d.C0582e;

/* renamed from: cn.jpush.android.service.c */
public final class C0606c {

    /* renamed from: b */
    private static C0606c f890b;

    /* renamed from: a */
    private Context f891a;

    /* renamed from: a */
    public static C0606c m1391a(Context context) {
        if (f890b == null) {
            f890b = new C0606c(context);
        }
        return f890b;
    }

    private C0606c(Context context) {
        this.f891a = context;
    }

    /* renamed from: a */
    public final void mo6996a(Bundle bundle, Handler handler) {
        C0582e.m1302a("PushServiceCore", "bundle:" + (bundle != null ? bundle.toString() : ""));
        if (bundle != null) {
            String string = bundle.getString(NativeProtocol.WEB_DIALOG_ACTION);
            if (string != null) {
                C0582e.m1302a("PushServiceCore", "Action - handleServiceAction - action:" + string);
                String str = m1393b(this.f891a) + ".";
                if (string.startsWith(str)) {
                    string = string.substring(str.length());
                }
                if ("intent.MULTI_PROCESS".equals(string)) {
                    switch (bundle.getInt("multi_type")) {
                        case 1:
                            C0563b.m1206a(this.f891a, bundle.getString("notification_buidler_id"), bundle.getString("notification_buidler"), true);
                            return;
                        case 2:
                            C0563b.m1205a(this.f891a, bundle.getInt("notification_maxnum"), true);
                            return;
                        case 3:
                            C0563b.m1210b(this.f891a, bundle.getString("enable_push_time"), true);
                            return;
                        case 4:
                            C0563b.m1207a(this.f891a, bundle.getString("silence_push_time"), true);
                            return;
                        case 6:
                            C0602a.m1376a(this.f891a).mo6989a(this.f891a, (JPushLocalNotification) bundle.getSerializable("local_notification"), true);
                            return;
                        case 7:
                            C0602a.m1376a(this.f891a).mo6988a(this.f891a, bundle.getLong("local_notification_id"));
                            return;
                        case 8:
                            C0602a.m1376a(this.f891a).mo6990b(this.f891a);
                            return;
                        case 9:
                            C0560b.m1191a(this.f891a, bundle.getInt("notification_id"), true);
                            return;
                        case 10:
                            C0560b.m1195a(this.f891a, true);
                            return;
                        default:
                            return;
                    }
                } else if ("intent.STOPPUSH".equals(string)) {
                    MultiSpHelper.commitInt(this.f891a, "service_stoped", 1);
                } else if (string.equals("intent.RESTOREPUSH")) {
                    MultiSpHelper.commitInt(this.f891a, "service_stoped", 0);
                } else if ("intent.ALIAS_TAGS".equals(string)) {
                    C0555k.m1156a(this.f891a, bundle);
                } else if ("intent.plugin.platform.REFRESSH_REGID".equals(string)) {
                    C0571f.m1232a().mo6843a(this.f891a, bundle);
                } else if ("intent.plugin.platform.ON_MESSAGING".equals(string)) {
                    String string2 = bundle.getString("appId");
                    String string3 = bundle.getString("senderId");
                    String string4 = bundle.getString("JMessageExtra");
                    Log.d("PushServiceCore", "appId:" + String.valueOf(string2) + ",senderId:" + String.valueOf(string3) + ",JMessageExtra:" + String.valueOf(string4));
                    if (!TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string3) && !TextUtils.isEmpty(string4)) {
                        C0552h.m1141a(this.f891a, string2, string3, string4, 0, 8);
                    }
                } else if ("intent.MOBILE_NUMBER".equals(string)) {
                    C0548f.m1129a().mo6779a(this.f891a, bundle);
                }
            }
        }
    }

    /* renamed from: b */
    private static String m1393b(Context context) {
        String str = C0541a.f651c;
        if (TextUtils.isEmpty(str) && context != null) {
            str = context.getPackageName();
        }
        if (str == null) {
            return "";
        }
        return str;
    }

    /* renamed from: a */
    public static void m1392a(Context context, Bundle bundle, String str) {
        bundle.putString(NativeProtocol.WEB_DIALOG_ACTION, m1393b(context) + "." + str);
    }

    /* renamed from: a */
    public final void mo6995a() {
        C0602a.m1376a(this.f891a).mo6992d(this.f891a);
    }
}
