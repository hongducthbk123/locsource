package p004cn.jpush.android.p039c;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import org.json.JSONObject;
import p004cn.jpush.android.C0541a;
import p004cn.jpush.android.api.C0560b;
import p004cn.jpush.android.api.JPushInterface;
import p004cn.jpush.android.data.C0589b;
import p004cn.jpush.android.data.C0594g;
import p004cn.jpush.android.p037a.C0546d;
import p004cn.jpush.android.p040d.C0577a;
import p004cn.jpush.android.p040d.C0582e;

/* renamed from: cn.jpush.android.c.h */
public final class C0574h {
    /* renamed from: a */
    public static void m1254a(Context context, String str, String str2, int i, byte b, boolean z) {
        if (context == null) {
            C0582e.m1306c("PluginPlatformsNotificationHelper", "context was null");
        } else if (TextUtils.isEmpty(str)) {
            C0582e.m1306c("PluginPlatformsNotificationHelper", "content was null");
        } else {
            C0589b a = m1252a(context, str, str2);
            if (a == null) {
                C0582e.m1306c("PluginPlatformsNotificationHelper", "entity was null");
            } else if (TextUtils.isEmpty(a.f805c)) {
                C0582e.m1306c("PluginPlatformsNotificationHelper", "message id was empty");
            } else {
                a.f807e = b;
                if (z) {
                    if (!(a instanceof C0594g)) {
                        return;
                    }
                    if (((C0594g) a).f853L == -1) {
                        m1253a(context, a, str2, i);
                        return;
                    }
                    Intent c = C0560b.m1200c(context, a);
                    if (c != null) {
                        c.addFlags(268435456);
                        context.getApplicationContext().startActivity(c);
                    }
                } else if (a instanceof C0594g) {
                    C0560b.m1194a(context, C0560b.m1187a(a), i, null, context.getPackageName(), a);
                    C0546d.m1125a(a.f805c, str2, a.f807e, 1018, context);
                }
            }
        }
    }

    /* renamed from: a */
    public static C0589b m1252a(Context context, String str, String str2) {
        byte b = 0;
        C0594g gVar = new C0594g();
        try {
            JSONObject jSONObject = new JSONObject(str);
            gVar.f805c = jSONObject.optString("_jmsgid_");
            if (gVar.f805c.isEmpty()) {
                gVar.f805c = jSONObject.optString("msg_id");
            }
            gVar.f807e = (byte) jSONObject.optInt("rom_type");
            int optInt = jSONObject.optInt("show_type", -1);
            JSONObject optJSONObject = jSONObject.optJSONObject("m_content");
            if (optJSONObject != null) {
                gVar.f824v = optJSONObject.optString("n_content");
                gVar.f823u = optJSONObject.optString("n_title");
                gVar.f816n = optJSONObject.optString("n_extras");
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("rich_content");
                if (optJSONObject2 != null) {
                    gVar.mo6877a(optJSONObject2);
                    gVar.f804b = 3;
                } else {
                    gVar.f804b = 4;
                    gVar.f853L = -1;
                }
            } else {
                gVar.f824v = jSONObject.optString("n_content");
                gVar.f823u = jSONObject.optString("n_title");
                gVar.f816n = jSONObject.optString("n_extras");
                gVar.f807e = (byte) jSONObject.optInt("rom_type");
            }
            if (optInt != -1) {
                gVar.f804b = optInt;
            }
            gVar.f819q = 0;
            gVar.f820r = true;
            return gVar;
        } catch (Throwable th) {
            String str3 = "NO MSGID";
            if (!TextUtils.isEmpty(gVar.f805c)) {
                str3 = gVar.f805c;
                b = gVar.f807e;
            }
            C0546d.m1125a(str3, str2, b, 996, context);
            return null;
        }
    }

    /* renamed from: a */
    private static void m1253a(Context context, C0589b bVar, String str, int i) {
        if (!TextUtils.isEmpty(bVar.f805c)) {
            Intent intent = new Intent(JPushInterface.ACTION_NOTIFICATION_OPENED);
            try {
                C0560b.m1196a(intent, C0560b.m1187a(bVar), i);
                intent.putExtra("sdktype", C0541a.f649a);
                String str2 = TextUtils.isEmpty(bVar.f817o) ? context.getPackageName() : bVar.f817o;
                intent.addCategory(str2);
                intent.setPackage(context.getPackageName());
                context.sendBroadcast(intent, str2 + ".permission.JPUSH_MESSAGE");
                C0546d.m1125a(bVar.f805c, str, bVar.f807e, 1000, context);
            } catch (Throwable th) {
                C0582e.m1306c("PluginPlatformsNotificationHelper", "onNotificationOpen sendBrocat error:" + th.getMessage());
                C0577a.m1282b(context, intent, context.getPackageName() + ".permission.JPUSH_MESSAGE");
            }
        }
    }
}
