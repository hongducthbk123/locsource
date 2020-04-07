package p004cn.jiguang.p005a.p012c;

import android.content.Context;
import android.text.TextUtils;
import java.io.Serializable;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p004cn.jiguang.p005a.p006a.p007a.C0337c;
import p004cn.jiguang.p005a.p006a.p008b.C0350f;
import p004cn.jiguang.p005a.p006a.p009c.C0354a;
import p004cn.jiguang.p005a.p006a.p009c.C0355b;
import p004cn.jiguang.p005a.p006a.p009c.C0357d;
import p004cn.jiguang.p005a.p006a.p009c.C0360g;
import p004cn.jiguang.p005a.p006a.p009c.C0362i;
import p004cn.jiguang.p005a.p011b.C0373b;
import p004cn.jiguang.p015d.p016a.C0386a;
import p004cn.jiguang.p015d.p016a.C0389d;
import p004cn.jiguang.p015d.p021d.C0460q;
import p004cn.jiguang.p015d.p026g.C0479a;
import p004cn.jiguang.p015d.p026g.C0481b;
import p004cn.jiguang.p015d.p026g.C0486g;
import p004cn.jiguang.p015d.p028h.C0495f;
import p004cn.jiguang.p029e.C0501d;
import p004cn.jiguang.p031g.C0506a;
import p004cn.jiguang.p031g.C0523d;
import p004cn.jiguang.p031g.p033b.C0510a;

/* renamed from: cn.jiguang.a.c.c */
public final class C0377c {

    /* renamed from: a */
    private static int f178a = 20480;

    /* renamed from: a */
    public static void m180a(Context context) {
        if (context != null) {
            JSONArray b = C0357d.m107b(context);
            if (b != null && b.length() != 0) {
                int length = b.length();
                if (b.toString().length() <= f178a) {
                    JSONObject a = C0506a.m939a("app_list", b);
                    if (a != null && a.length() > 0) {
                        C0460q.m714a(context, a);
                        return;
                    }
                    return;
                }
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < length; i++) {
                    try {
                        jSONArray.put(b.getJSONObject(i));
                    } catch (JSONException e) {
                    }
                    if (jSONArray.toString().length() > f178a || length - 1 == i) {
                        JSONObject a2 = C0506a.m939a("app_list", jSONArray);
                        if (a2 != null && a2.length() > 0) {
                            C0460q.m714a(context, a2);
                        }
                        jSONArray = new JSONArray();
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public static void m181a(Context context, int i) {
        C0460q.m720c(context);
        C0501d.m905b("ReportHelper", "periodTasks...");
        if (C0389d.m334e(context)) {
            C0510a b = C0389d.m319b(context, new C0510a().mo6675a("last_report_device_info", (Serializable) Long.valueOf(0)).mo6675a("lbs_report_enable", (Serializable) Boolean.valueOf(true)).mo6675a("last_collection_location", (Serializable) Long.valueOf(0)).mo6675a("location_report_delay", (Serializable) Long.valueOf(0)).mo6675a("location_collect_frequency", (Serializable) Long.valueOf(900000)).mo6675a("last_check_userapp_status", (Serializable) Long.valueOf(0)).mo6675a("nb_upload", (Serializable) Boolean.valueOf(false)).mo6675a("nb_lasttime", (Serializable) Long.valueOf(0)).mo6675a("app_running_collect_enable", (Serializable) Boolean.valueOf(true)).mo6675a("app_running_collect_interval", (Serializable) Long.valueOf(3600000)).mo6675a("app_running_last_collect_time", (Serializable) Long.valueOf(0)).mo6675a("app_running_collect_app_type", (Serializable) Integer.valueOf(1)).mo6675a("app_running_collect_process_type", (Serializable) Integer.valueOf(1)).mo6675a("battery_last_collect_time", (Serializable) Long.valueOf(0)));
            C0510a aVar = new C0510a();
            long currentTimeMillis = System.currentTimeMillis();
            boolean z = currentTimeMillis - ((Long) b.mo6680b("last_report_device_info", Long.valueOf(0))).longValue() > 86400000;
            boolean booleanValue = ((Boolean) b.mo6680b("lbs_report_enable", Boolean.valueOf(true))).booleanValue();
            boolean z2 = false;
            if (booleanValue) {
                long longValue = ((Long) b.mo6680b("last_collection_location", Long.valueOf(0))).longValue();
                long longValue2 = ((Long) b.mo6680b("location_collect_frequency", Long.valueOf(900000))).longValue();
                if (longValue <= 0) {
                    long longValue3 = ((Long) b.mo6680b("location_report_delay", Long.valueOf(0))).longValue();
                    if (longValue3 > 0) {
                        longValue = (currentTimeMillis + longValue3) - longValue2;
                        aVar.mo6675a("last_collection_location", (Serializable) Long.valueOf(longValue));
                    }
                }
                if (currentTimeMillis - longValue >= longValue2) {
                    aVar.mo6675a("last_collection_location", (Serializable) Long.valueOf(currentTimeMillis));
                    z2 = true;
                }
            }
            boolean z3 = false;
            if (currentTimeMillis - ((Long) b.mo6680b("last_check_userapp_status", Long.valueOf(0))).longValue() > 3600000) {
                aVar.mo6675a("last_check_userapp_status", (Serializable) Long.valueOf(currentTimeMillis));
                z3 = true;
            }
            boolean booleanValue2 = ((Boolean) b.mo6680b("nb_upload", Boolean.valueOf(false))).booleanValue();
            if (booleanValue2) {
                booleanValue2 = C0506a.m959d(context).toUpperCase().startsWith("WIFI");
                if (booleanValue2) {
                    booleanValue2 = currentTimeMillis - ((Long) b.mo6680b("nb_lasttime", Long.valueOf(0))).longValue() > 3600000;
                }
            }
            boolean booleanValue3 = ((Boolean) b.mo6680b("app_running_collect_enable", Boolean.valueOf(true))).booleanValue();
            int i2 = 1;
            int i3 = 1;
            if (booleanValue3) {
                long longValue4 = ((Long) b.mo6680b("app_running_last_collect_time", Long.valueOf(0))).longValue();
                if (longValue4 <= 0) {
                    booleanValue3 = false;
                    aVar.mo6675a("app_running_last_collect_time", (Serializable) Long.valueOf(currentTimeMillis));
                } else if (currentTimeMillis - longValue4 >= ((Long) b.mo6680b("app_running_collect_interval", Long.valueOf(3600000))).longValue()) {
                    i2 = ((Integer) b.mo6680b("app_running_collect_app_type", Integer.valueOf(1))).intValue();
                    i3 = ((Integer) b.mo6680b("app_running_collect_process_type", Integer.valueOf(1))).intValue();
                    booleanValue3 = (i2 == 0 && i3 == 0) ? false : true;
                    if (booleanValue3) {
                        aVar.mo6675a("app_running_last_collect_time", (Serializable) Long.valueOf(currentTimeMillis));
                    }
                } else {
                    booleanValue3 = false;
                }
            }
            boolean z4 = false;
            long longValue5 = ((Long) b.mo6680b("battery_last_collect_time", Long.valueOf(0))).longValue();
            if (longValue5 <= 0 || currentTimeMillis - longValue5 >= 3600000) {
                z4 = true;
                aVar.mo6675a("battery_last_collect_time", (Serializable) Long.valueOf(currentTimeMillis));
            }
            C0389d.m312a(context, aVar);
            C0373b bVar = new C0373b(z, booleanValue && z2, z3, booleanValue2, booleanValue3, i2, i3, z4);
            if (bVar.f167a) {
                C0355b.m103c(context);
            }
            if (C0386a.m252a()) {
                try {
                    C0486g.m808a(context);
                } catch (Throwable th) {
                }
            }
            if (bVar.f168b) {
                m184a(context, false);
            }
            if (bVar.f169c) {
                try {
                    m182a(context, (String) null);
                } catch (Throwable th2) {
                }
            }
            if (bVar.f170d) {
                try {
                    C0362i.m130a(context);
                } catch (Throwable th3) {
                }
            }
            if (C0506a.m957c(context) && C0506a.m959d(context).toUpperCase().startsWith("WIFI")) {
                try {
                    C0337c.m24a(context);
                } catch (Throwable th4) {
                }
            }
            if (bVar.f171e) {
                try {
                    C0523d.m1071a(context, bVar.f172f, bVar.f173g);
                } catch (Throwable th5) {
                }
            }
            if (bVar.f174h) {
                try {
                    C0481b a = C0479a.m773a(context);
                    if (a != null) {
                        JSONObject a2 = a.mo6621a();
                        C0460q.m707a(context, a2, "battery");
                        C0460q.m714a(context, a2);
                    }
                } catch (Throwable th6) {
                }
            }
            C0360g.m122a(context, i);
        }
    }

    /* renamed from: a */
    public static void m182a(Context context, String str) {
        if (C0389d.m334e(context)) {
            if (TextUtils.isEmpty(C0354a.m93a(context))) {
                m180a(context);
                C0354a.m94a(context, C0357d.m105a(context, true));
                return;
            }
            new C0354a(context, str).start();
        }
    }

    /* renamed from: a */
    public static void m183a(Context context, JSONObject jSONObject) {
        if (C0389d.m334e(context)) {
            if (context == null) {
                throw new IllegalArgumentException("NULL context");
            } else if (jSONObject != null && jSONObject.length() > 0) {
                C0460q.m714a(context, jSONObject);
            }
        }
    }

    /* renamed from: a */
    public static void m184a(Context context, boolean z) {
        try {
            C0350f.m76a(context, false);
        } catch (Throwable th) {
        }
    }

    /* renamed from: b */
    public static void m185b(Context context) {
        String[] a = C0357d.m106a(context);
        if (a != null && a.length != 0) {
            int length = a.length;
            String str = "[";
            String i = C0389d.m338i(context);
            long d = C0389d.m331d(context);
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            while (i2 < length) {
                String str2 = a[i2];
                str = i3 == 0 ? str + "\"" + str2 + "\"" : str + ",\"" + str2 + "\"";
                int i5 = i2 + 1;
                int i6 = i3 + 1;
                if (i6 >= 50 || str.length() > 1000 || i5 == length) {
                    C0460q.m714a(context, C0506a.m938a("android_permissions", String.format(Locale.ENGLISH, "{\"total\":%d,\"page\":%d,\"senderid\":\"%s\",\"uid\":%s,\"permission_list\":%s}", new Object[]{Integer.valueOf(length), Integer.valueOf(i4), i, Long.valueOf(d), str + "]"})));
                    str = "[";
                    i4++;
                    i6 = 0;
                }
                i3 = i6;
                i2 = i5;
            }
        }
    }

    /* renamed from: b */
    public static void m186b(Context context, int i) {
        C0360g.m122a(context, i);
    }

    /* renamed from: c */
    public static void m187c(Context context) {
        m181a(context, 0);
        try {
            C0495f.m858a().mo6654a(context, false);
        } catch (OutOfMemoryError e) {
        }
    }
}
