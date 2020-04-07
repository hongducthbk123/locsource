package p004cn.jpush.android.service;

import android.content.Context;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
import p004cn.jiguang.api.JCoreInterface;
import p004cn.jiguang.api.MultiSpHelper;
import p004cn.jpush.android.C0541a;
import p004cn.jpush.android.C0563b;
import p004cn.jpush.android.api.C0559a;
import p004cn.jpush.android.api.C0560b;
import p004cn.jpush.android.api.DefaultPushNotificationBuilder;
import p004cn.jpush.android.p039c.C0573g;
import p004cn.jpush.android.p040d.C0582e;

/* renamed from: cn.jpush.android.service.ServiceInterface */
public class ServiceInterface {

    /* renamed from: a */
    private static boolean f878a = false;

    /* renamed from: a */
    public static void m1363a(Context context) {
        if (!m1373d(context)) {
            JCoreInterface.restart(context, C0541a.f649a, new Bundle(), false);
            C0573g.m1238a().mo6846a(context);
        }
    }

    /* renamed from: a */
    public static void m1364a(Context context, int i) {
        m1374e(context);
        MultiSpHelper.commitInt(context, "service_stoped", 1);
        Bundle bundle = new Bundle();
        C0606c.m1392a(context, bundle, "intent.STOPPUSH");
        bundle.putString("app", context.getPackageName());
        JCoreInterface.stop(context, C0541a.f649a, bundle);
    }

    /* renamed from: b */
    public static void m1370b(Context context, int i) {
        m1374e(context);
        MultiSpHelper.commitInt(context, "service_stoped", 0);
        Bundle bundle = new Bundle();
        C0606c.m1392a(context, bundle, "intent.RESTOREPUSH");
        bundle.putString("app", context.getPackageName());
        JCoreInterface.restart(context, C0541a.f649a, bundle, true);
    }

    /* renamed from: a */
    public static void m1367a(Context context, String str, Set<String> set, long j, C0559a aVar) {
        int i;
        int i2 = 0;
        Bundle bundle = new Bundle();
        C0606c.m1392a(context, bundle, "intent.ALIAS_TAGS");
        bundle.putString("alias", str);
        ArrayList arrayList = null;
        if (set != null) {
            arrayList = new ArrayList(set);
        }
        bundle.putStringArrayList("tags", arrayList);
        bundle.putLong("seq_id", j);
        String str2 = "proto_type";
        StringBuilder sb = new StringBuilder();
        if (aVar != null) {
            i = aVar.f733e;
        } else {
            i = 0;
        }
        bundle.putString(str2, sb.append(i).toString());
        String str3 = "protoaction_type";
        StringBuilder sb2 = new StringBuilder();
        if (aVar != null) {
            i2 = aVar.f734f;
        }
        bundle.putString(str3, sb2.append(i2).toString());
        JCoreInterface.sendAction(context, C0541a.f649a, bundle);
    }

    /* renamed from: a */
    public static void m1366a(Context context, String str) {
        if (context != null && !m1373d(context)) {
            C0563b.m1207a(context, str, false);
        }
    }

    /* renamed from: a */
    public static void m1365a(Context context, Integer num, DefaultPushNotificationBuilder defaultPushNotificationBuilder) {
        if (context == null) {
            C0582e.m1308d("ServiceInterface", "Null context, please init JPush!");
        } else {
            C0563b.m1206a(context, num, defaultPushNotificationBuilder.toString(), false);
        }
    }

    /* renamed from: c */
    public static void m1371c(Context context, int i) {
        if (context == null) {
            C0582e.m1306c("ServiceInterface", "setNotificationNumber - context is null!");
        } else {
            C0563b.m1205a(context, i, false);
        }
    }

    /* renamed from: b */
    public static void m1369b(Context context) {
        if (context == null) {
            C0582e.m1306c("ServiceInterface", "clearAllNotification - context is null!");
        } else {
            C0560b.m1195a(context.getApplicationContext(), false);
        }
    }

    /* renamed from: a */
    public static String m1362a() {
        return "3.1.2";
    }

    /* renamed from: a */
    public static boolean m1368a(Context context, int i, int i2, int i3, int i4) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("startHour", i);
            jSONObject.put("startMins", i2);
            jSONObject.put("endHour", i3);
            jSONObject.put("endtMins", i4);
            m1366a(context, jSONObject.toString());
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    /* renamed from: c */
    public static boolean m1372c(Context context) {
        return m1374e(context) > 0;
    }

    /* renamed from: d */
    public static boolean m1373d(Context context) {
        boolean c = m1372c(context);
        if (c) {
            C0582e.m1302a("ServiceInterface", "The service is stopped, it will give up all the actions until you call resumePush method to resume the service.");
        }
        return c;
    }

    /* renamed from: e */
    private static int m1374e(Context context) {
        return MultiSpHelper.getInt(context, "service_stoped", 0);
    }
}
