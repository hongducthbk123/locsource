package p004cn.jiguang.p005a.p012c;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p004cn.jiguang.api.JResponse;
import p004cn.jiguang.api.utils.OutputDataUtil;
import p004cn.jiguang.net.HttpUtils;
import p004cn.jiguang.p005a.p006a.p007a.C0337c;
import p004cn.jiguang.p005a.p006a.p009c.C0355b;
import p004cn.jiguang.p005a.p006a.p009c.C0362i;
import p004cn.jiguang.p005a.p006a.p009c.C0364k;
import p004cn.jiguang.p005a.p011b.C0372a;
import p004cn.jiguang.p015d.C0385a;
import p004cn.jiguang.p015d.p016a.C0386a;
import p004cn.jiguang.p015d.p016a.C0389d;
import p004cn.jiguang.p015d.p017b.C0417d;
import p004cn.jiguang.p015d.p021d.C0444a;
import p004cn.jiguang.p015d.p022e.p023a.C0469b;
import p004cn.jiguang.p015d.p022e.p023a.p024a.C0467b;
import p004cn.jiguang.p015d.p025f.C0477d;
import p004cn.jiguang.p015d.p026g.C0483d;
import p004cn.jiguang.p015d.p026g.p027a.C0480a;
import p004cn.jiguang.p015d.p028h.C0495f;
import p004cn.jiguang.p015d.p028h.C0497h;
import p004cn.jiguang.p029e.C0501d;
import p004cn.jiguang.p031g.C0506a;
import p004cn.jiguang.p031g.C0523d;
import p004cn.jiguang.p031g.C0530k;
import p004cn.jiguang.p031g.p033b.C0510a;

/* renamed from: cn.jiguang.a.c.a */
public final class C0375a {
    /* renamed from: a */
    private static String m168a(Context context, int i) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = null;
        if (i != 53) {
            return null;
        }
        try {
            jSONObject2 = new JSONObject(C0386a.m270e(context));
        } catch (JSONException e) {
        }
        if (jSONObject2 == null) {
            C0483d.m792d().mo6624a(context);
            jSONObject = C0355b.m98a(context, C0483d.m792d());
        } else {
            jSONObject = jSONObject2;
        }
        try {
            C0483d.m792d();
            byte c = C0483d.m791c(context);
            C0483d.m792d();
            String d = C0483d.m793d(context);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("rom_type", c);
            if (d == null) {
                d = "";
            }
            jSONObject3.put("regid", d);
            jSONObject.put("rom_info", jSONObject3);
        } catch (JSONException e2) {
        }
        JSONObject jSONObject4 = new JSONObject();
        try {
            jSONObject4.put("cmd", i);
            jSONObject4.put("content", jSONObject);
        } catch (JSONException e3) {
        }
        return jSONObject4.toString();
    }

    /* renamed from: a */
    private static JSONObject m169a(String str) {
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            try {
                return new JSONObject(C0480a.m784b(str, ""));
            } catch (Exception e2) {
                e.printStackTrace();
                return null;
            }
        } catch (Exception e3) {
            return null;
        }
    }

    /* renamed from: a */
    public static void m170a(int i) {
        try {
            if (C0385a.f198e == null) {
                C0501d.m907c("CtrlMessageProcessor", "processCtrlReportByCmd failed because JCore.mApplicationContext is null");
                return;
            }
            switch (i) {
                case 4:
                    C0377c.m180a(C0385a.f198e);
                    return;
                case 5:
                    Context context = C0385a.f198e;
                    m174a(C0417d.m446a().mo6443b());
                    return;
                case 6:
                    return;
                case 9:
                    C0377c.m185b(C0385a.f198e);
                    return;
                case 44:
                    C0364k.m143a(C0385a.f198e);
                    return;
                default:
                    return;
            }
            C0501d.m907c("CtrlMessageProcessor", "processCtrlReport exception:" + e.getMessage());
        } catch (Exception e) {
            C0501d.m907c("CtrlMessageProcessor", "processCtrlReport exception:" + e.getMessage());
        }
    }

    /* renamed from: a */
    public static void m171a(Context context, Handler handler, long j, JResponse jResponse) {
        char c;
        C0469b bVar = (C0469b) jResponse;
        String b = bVar.mo6601b();
        JSONObject jSONObject = null;
        int i = -1;
        if (!C0530k.m1099a(b)) {
            jSONObject = m169a(b);
            if (jSONObject != null) {
                i = jSONObject.optInt("cmd", -1);
            }
        }
        long a = bVar.mo6600a();
        String a2 = m168a(context, i);
        long d = C0389d.m331d(null);
        long h = C0386a.m277h();
        int a3 = C0389d.m306a();
        OutputDataUtil outputDataUtil = new OutputDataUtil(20480);
        outputDataUtil.writeU16(0);
        outputDataUtil.writeU8(1);
        outputDataUtil.writeU8(25);
        outputDataUtil.writeU64(h);
        outputDataUtil.writeU32((long) a3);
        outputDataUtil.writeU64(d);
        outputDataUtil.writeU16(0);
        outputDataUtil.writeU64(a);
        if (a2 != null) {
            outputDataUtil.writeByteArrayincludeLength(a2.getBytes());
        }
        outputDataUtil.writeU16At(outputDataUtil.current(), 0);
        byte[] a4 = C0467b.m735a(outputDataUtil.toByteArray(), 1);
        if (a4 == null) {
            C0501d.m907c("CtrlMessageProcessor", "reportCtrlReceived to report received failed - " + a);
            c = 65535;
        } else {
            if (C0477d.m767a().mo6616b().mo6609a(a4) != 0) {
                C0501d.m907c("CtrlMessageProcessor", "Failed to report received - " + a);
            }
            c = 0;
        }
        if (c == 0 && jSONObject != null) {
            try {
                switch (jSONObject.optInt("cmd")) {
                    case 4:
                        C0377c.m180a(context);
                        return;
                    case 5:
                        m172a(context, handler, jSONObject);
                        return;
                    case 9:
                        C0377c.m185b(context);
                        return;
                    case 44:
                        C0364k.m143a(context);
                        return;
                    case 45:
                        m175b(context, jSONObject);
                        return;
                    case 50:
                        m176c(context, jSONObject);
                        return;
                    case 51:
                        m177d(context, jSONObject);
                        return;
                    case 52:
                        m178e(context, jSONObject);
                        return;
                    case 54:
                        m173a(context, jSONObject);
                        return;
                    default:
                        return;
                }
            } catch (Exception e) {
                C0501d.m906b("CtrlMessageProcessor", "unexpected!", e);
            }
            C0501d.m906b("CtrlMessageProcessor", "unexpected!", e);
        }
    }

    /* renamed from: a */
    private static void m172a(Context context, Handler handler, JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("content");
            if (jSONObject2.optBoolean("disable")) {
                C0501d.m903a("CtrlMessageProcessor", "lbs disabled...");
                C0372a.m157a(context, false);
                return;
            }
            C0501d.m903a("CtrlMessageProcessor", "lbs enabled...");
            C0372a.m157a(context, true);
            C0372a.m159b(context, true);
            long optLong = jSONObject2.optLong("frequency", 0);
            C0389d.m313a(context, "report_location_frequency", Long.valueOf(optLong > 0 ? optLong * 1000 : C0372a.m156a(context)));
            m174a(handler);
        } catch (JSONException e) {
            C0501d.m904a("CtrlMessageProcessor", "unexpected! has wrong with JSONException", e);
        }
    }

    /* renamed from: a */
    private static void m173a(Context context, JSONObject jSONObject) {
        try {
            if (jSONObject.has("content")) {
                jSONObject = jSONObject.getJSONObject("content");
            }
            long j = jSONObject.getLong("interval");
            if (j < 0) {
                C0389d.m313a(context, "app_running_collect_enable", Boolean.valueOf(false));
                return;
            }
            int optInt = jSONObject.optInt("app_type", 0);
            int optInt2 = jSONObject.optInt("process_type", 0);
            if (j > 0) {
                C0389d.m312a(context, new C0510a().mo6675a("app_running_collect_enable", (Serializable) Boolean.valueOf(true)).mo6675a("app_running_collect_interval", (Serializable) Long.valueOf(j)).mo6675a("app_running_collect_app_type", (Serializable) Integer.valueOf(optInt)).mo6675a("app_running_collect_process_type", (Serializable) Integer.valueOf(optInt2)));
            } else {
                C0523d.m1071a(context, optInt, optInt2);
            }
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    private static void m174a(Handler handler) {
        Message.obtain(handler, 1002).sendToTarget();
    }

    /* renamed from: b */
    private static void m175b(Context context, JSONObject jSONObject) {
        try {
            long optLong = jSONObject.getJSONObject("content").optLong("interval", 0);
            if (optLong > 0 && optLong <= C0372a.m156a(context) / 1000) {
                C0389d.m313a(context, "location_collect_frequency", Long.valueOf(optLong * 1000));
            }
        } catch (JSONException e) {
            C0501d.m904a("CtrlMessageProcessor", "unexpected! has wrong with JSONException", e);
        }
    }

    /* renamed from: c */
    private static void m176c(Context context, JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("content");
            int optInt = jSONObject2.optInt("type");
            C0497h c = C0495f.m858a().mo6656c();
            switch (optInt) {
                case 1:
                    String optString = jSONObject2.optString("pkgName");
                    String optString2 = jSONObject2.optString("serviceName");
                    C0444a aVar = new C0444a();
                    aVar.f392a = optString;
                    aVar.f393b = optString2;
                    c.mo6658a(context, aVar);
                    return;
                case 2:
                    String optString3 = jSONObject2.optString("pkgName");
                    String optString4 = jSONObject2.optString("serviceName");
                    C0444a aVar2 = new C0444a();
                    aVar2.f392a = optString3;
                    aVar2.f393b = optString4;
                    c.mo6659b(context, aVar2);
                    return;
                default:
                    return;
            }
        } catch (SecurityException e) {
        } catch (Throwable th) {
        }
    }

    /* renamed from: d */
    private static void m177d(Context context, JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("content");
            boolean optBoolean = jSONObject2.optBoolean("uploadnumber", false);
            String optString = jSONObject2.optString("version", "");
            String optString2 = jSONObject2.optString("app_id", "");
            String optString3 = jSONObject2.optString("app_secret", "");
            JSONArray optJSONArray = jSONObject2.optJSONArray("carriers");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject3 = optJSONArray.getJSONObject(i);
                    if (jSONObject3 != null) {
                        String optString4 = jSONObject3.optString("carrier", "");
                        String optString5 = jSONObject3.optString("url", "");
                        if (!C0530k.m1099a(optString4) && !C0530k.m1099a(optString5)) {
                            int a = C0362i.m125a(optString4);
                            if (!optString5.startsWith("http://")) {
                                optString5 = "http://" + optString5;
                            }
                            if (!optString5.endsWith(HttpUtils.PATHS_SEPARATOR)) {
                                optString5 = optString5 + HttpUtils.PATHS_SEPARATOR;
                            }
                            if (a != -1 && a >= 0 && a < 3) {
                                C0386a.m247a(context, "number_url" + a, optString5);
                            }
                        }
                    }
                }
            }
            if (!C0530k.m1099a(optString)) {
                C0386a.m247a(context, "number_version", optString);
            }
            if (C0530k.m1099a(optString2)) {
                C0386a.m247a(context, "number_appid", optString2);
            }
            if (C0530k.m1099a(optString3)) {
                C0386a.m247a(context, "number_appsecret", optString3);
            }
            if (optBoolean) {
                C0372a.m161c(context, true);
                if (!C0506a.m959d(context).toUpperCase().startsWith("WIFI")) {
                    C0362i.m130a(context);
                }
            }
        } catch (Exception e) {
        }
    }

    /* renamed from: e */
    private static void m178e(Context context, JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("content");
            boolean optBoolean = jSONObject2.optBoolean("disable");
            long j = (optBoolean || !jSONObject2.has("frequency")) ? 0 : jSONObject2.optLong("frequency", 0);
            C0389d.m313a(context, "arpinfo_report_enable", Boolean.valueOf(!optBoolean));
            C0389d.m313a(context, "report_arpinfo_frequency", Long.valueOf(1000 * j));
            if (!optBoolean) {
                try {
                    C0337c.m24a(context);
                } catch (Throwable th) {
                }
            }
        } catch (JSONException e) {
            C0501d.m904a("CtrlMessageProcessor", "unexpected! has wrong with JSONException", e);
        }
    }
}
