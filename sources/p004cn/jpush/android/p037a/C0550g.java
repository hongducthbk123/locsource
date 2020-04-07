package p004cn.jpush.android.p037a;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.applinks.AppLinkData;
import com.facebook.share.internal.ShareConstants;
import org.json.JSONException;
import org.json.JSONObject;
import p004cn.jiguang.net.HttpResponse;
import p004cn.jpush.android.data.C0588a;
import p004cn.jpush.android.data.C0594g;
import p004cn.jpush.android.p038b.C0564a;
import p004cn.jpush.android.p040d.C0577a;

/* renamed from: cn.jpush.android.a.g */
public final class C0550g {
    /* renamed from: a */
    public static C0588a m1134a(Context context, String str, String str2, String str3, String str4) {
        int i;
        if (context == null) {
            throw new IllegalArgumentException("NULL context");
        } else if (TextUtils.isEmpty(str)) {
            C0546d.m1124a("NO MSGID", 996, null, context);
            return null;
        } else {
            JSONObject a = m1135a(context, "NO_MSGID", str);
            if (a == null) {
                return null;
            }
            String optString = a.optString("msg_id", "");
            if (TextUtils.isEmpty(optString)) {
                optString = a.optString("ad_id", "");
            }
            if (!TextUtils.isEmpty(optString)) {
                str4 = optString;
            }
            boolean z = a.optInt("n_only", 0) == 1;
            if (z) {
                i = a.optInt("n_builder_id", 0);
            } else {
                i = 0;
            }
            C0588a aVar = new C0588a();
            aVar.f805c = str4;
            aVar.f793a = a;
            aVar.f804b = a.optInt("show_type", 3);
            aVar.f808f = z;
            aVar.f809g = i;
            aVar.f810h = a.optInt("notificaion_type", 0);
            aVar.f812j = a.optString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, "");
            aVar.f813k = a.optString("content_type", "");
            aVar.f815m = a.optString("title", "");
            aVar.f816n = a.optString(AppLinkData.ARGUMENTS_EXTRAS_KEY, "");
            aVar.f817o = str2;
            aVar.f818p = str3;
            aVar.f806d = a.optString("override_msg_id", "");
            return aVar;
        }
    }

    /* renamed from: a */
    public static void m1137a(Context context, C0588a aVar) {
        if (context == null) {
            throw new IllegalArgumentException("NULL context");
        }
        int i = aVar.f804b;
        JSONObject jSONObject = aVar.f793a;
        String str = aVar.f805c;
        if (i == 3 || i == 4) {
            JSONObject a = m1136a(context, str, jSONObject, "m_content");
            if (a != null) {
                int optInt = a.optInt("ad_t", -1);
                if (optInt == 0) {
                    C0594g gVar = new C0594g();
                    gVar.f805c = str;
                    gVar.f804b = i;
                    gVar.f819q = optInt;
                    gVar.f811i = aVar.f811i;
                    gVar.f808f = aVar.f808f;
                    gVar.f809g = aVar.f809g;
                    gVar.f817o = aVar.f817o;
                    gVar.f806d = aVar.f806d;
                    gVar.f810h = aVar.f810h;
                    if (gVar.mo6879a(context, a)) {
                        gVar.mo6876a(context);
                        return;
                    }
                    return;
                }
                C0546d.m1124a(str, 996, null, context);
                return;
            }
            return;
        }
        C0546d.m1124a(str, 996, null, context);
    }

    /* renamed from: a */
    public static void m1138a(final Context context, String str) {
        JSONObject jSONObject;
        if (context == null) {
            throw new IllegalArgumentException("NULL context");
        } else if (!TextUtils.isEmpty(str)) {
            JSONObject a = m1135a(context, "NO MSGID", str);
            if (a != null) {
                final String optString = a.optString("msg_id", "");
                if (TextUtils.isEmpty(optString)) {
                    optString = a.optString("ad_id", "");
                }
                int optInt = a.optInt("show_type", -1);
                if (optInt == 2) {
                    final String trim = a.optString("m_content", "").trim();
                    if (!m1139a(trim)) {
                        C0546d.m1124a(optString, 996, null, context);
                    } else if (context == null) {
                        throw new IllegalArgumentException("NULL context");
                    } else {
                        new Thread() {
                            public final void run() {
                                String str;
                                boolean z = false;
                                int i = 0;
                                while (true) {
                                    if (i >= 4) {
                                        str = null;
                                        break;
                                    }
                                    i++;
                                    HttpResponse a = C0564a.m1212a(trim, 5, 8000);
                                    if (a != null && a.getResponseCode() == 200) {
                                        z = true;
                                        str = a.getResponseBody();
                                        break;
                                    }
                                }
                                if (!z || TextUtils.isEmpty(str)) {
                                    C0546d.m1124a(optString, 1021, C0577a.m1269a(context, trim), context);
                                    C0546d.m1124a(optString, 996, null, context);
                                    return;
                                }
                                C0550g.m1138a(context, str);
                            }
                        }.start();
                    }
                } else {
                    if (optInt == 1) {
                        jSONObject = m1136a(context, optString, a, "m_content");
                    } else {
                        jSONObject = null;
                    }
                    if (jSONObject != null) {
                        int optInt2 = jSONObject.optInt("ad_t", -1);
                        switch (optInt2) {
                            case 0:
                                C0594g gVar = new C0594g();
                                boolean a2 = gVar.mo6879a(context, jSONObject);
                                gVar.f805c = optString;
                                gVar.f804b = optInt;
                                gVar.f819q = optInt2;
                                if (a2) {
                                    gVar.mo6876a(context);
                                    return;
                                }
                                return;
                            default:
                                C0546d.m1124a(optString, 996, null, context);
                                return;
                        }
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private static JSONObject m1135a(Context context, String str, String str2) {
        try {
            return new JSONObject(str2);
        } catch (JSONException e) {
            C0546d.m1124a(str, 996, null, context);
            return null;
        }
    }

    /* renamed from: a */
    public static JSONObject m1136a(Context context, String str, JSONObject jSONObject, String str2) {
        if (jSONObject == null) {
            C0546d.m1124a(str, 996, null, context);
            return null;
        } else if (TextUtils.isEmpty(str2)) {
            return null;
        } else {
            try {
                if (!jSONObject.isNull(str2)) {
                    return jSONObject.getJSONObject(str2);
                }
                return null;
            } catch (JSONException e) {
                C0546d.m1124a(str, 996, null, context);
                return null;
            }
        }
    }

    /* renamed from: a */
    public static boolean m1139a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.trim().matches("^[http|https]+://.*");
    }
}
