package p004cn.jiguang.p005a.p006a.p009c;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.facebook.devicerequests.internal.DeviceRequestsHelper;
import com.google.android.vending.expansion.downloader.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p004cn.jiguang.api.C0378a;
import p004cn.jiguang.p015d.C0385a;
import p004cn.jiguang.p015d.p016a.C0386a;
import p004cn.jiguang.p015d.p016a.C0389d;
import p004cn.jiguang.p015d.p021d.C0460q;
import p004cn.jiguang.p015d.p026g.C0483d;
import p004cn.jiguang.p031g.C0506a;
import p004cn.jiguang.p031g.C0530k;
import p004cn.jiguang.p031g.p034c.C0516a;
import p004cn.jiguang.p031g.p034c.C0522b;

/* renamed from: cn.jiguang.a.a.c.b */
public class C0355b {

    /* renamed from: a */
    private static final String f112a = C0355b.class.getSimpleName();

    /* renamed from: a */
    public static JSONObject m97a(Context context) {
        try {
            JSONObject jSONObject = new JSONObject();
            String a = C0506a.m931a();
            String a2 = C0506a.m932a(context);
            String format = String.format(Locale.ENGLISH, "%.1f", new Object[]{Double.valueOf(C0506a.m986o(context))});
            String str = VERSION.RELEASE;
            String str2 = Build.MODEL;
            String locale = context.getResources().getConfiguration().locale.toString();
            String b = C0506a.m949b(C0385a.f198e, "");
            long rawOffset = (long) (TimeZone.getDefault().getRawOffset() / 3600000);
            r4 = rawOffset > 0 ? "+" + rawOffset : rawOffset < 0 ? new StringBuilder(Constants.FILENAME_SEQUENCE_SEPARATOR).append(rawOffset).toString() : rawOffset;
            String str3 = "cpu_info";
            if (C0530k.m1099a(a)) {
                a = "";
            }
            jSONObject.put(str3, a);
            String str4 = "resolution";
            if (C0530k.m1099a(a2)) {
                a2 = "";
            }
            jSONObject.put(str4, a2);
            String str5 = "os_version";
            if (C0530k.m1099a(str)) {
                str = "";
            }
            jSONObject.put(str5, str);
            String str6 = "language";
            if (C0530k.m1099a(locale)) {
                locale = "";
            }
            jSONObject.put(str6, locale);
            String str7 = "timezone";
            if (C0530k.m1099a(r4)) {
                r4 = "";
            }
            jSONObject.put(str7, r4);
            String str8 = "model";
            if (C0530k.m1099a(str2)) {
                str2 = "";
            }
            jSONObject.put(str8, str2);
            String str9 = "screensize";
            if (C0530k.m1099a(format)) {
                format = "";
            }
            jSONObject.put(str9, format);
            String str10 = "mac";
            if (C0530k.m1099a(b)) {
                b = "";
            }
            jSONObject.put(str10, b);
            ArrayList a3 = C0522b.m1064a().mo6697a(context.getApplicationContext());
            JSONArray jSONArray = new JSONArray();
            if (a3 != null) {
                Iterator it = a3.iterator();
                while (it.hasNext()) {
                    C0516a aVar = (C0516a) it.next();
                    if (aVar != null) {
                        JSONObject a4 = aVar.mo6693a();
                        if (a4 != null) {
                            jSONArray.put(a4);
                        }
                    }
                }
            }
            jSONObject.put("sim_slots", jSONArray);
            return jSONObject;
        } catch (Throwable th) {
            return null;
        }
    }

    /* renamed from: a */
    public static JSONObject m98a(Context context, C0483d dVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            ArrayList a = C0522b.m1064a().mo6697a(context.getApplicationContext());
            JSONArray jSONArray = new JSONArray();
            if (a != null) {
                Iterator it = a.iterator();
                while (it.hasNext()) {
                    C0516a aVar = (C0516a) it.next();
                    if (aVar != null) {
                        JSONObject a2 = aVar.mo6693a();
                        if (a2 != null) {
                            jSONArray.put(a2);
                        }
                    }
                }
            }
            jSONObject.put("sim_slots", jSONArray);
            jSONObject.put("pkgname", context.getPackageName());
            jSONObject.put("appkey", C0389d.m338i(context));
            jSONObject.put("platform", 0);
            jSONObject.put("apkversion", dVar.f499a);
            jSONObject.put("systemversion", dVar.f500b);
            jSONObject.put("modelnumber", dVar.f501c);
            jSONObject.put("basebandversion", dVar.f502d);
            jSONObject.put("buildnumber", dVar.f503e);
            jSONObject.put("channel", dVar.f508j);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("PushSDKVer", dVar.f504f);
            jSONObject2.put("StatisticSDKVer", dVar.f505g);
            jSONObject2.put("ShareSDKVer", dVar.f506h);
            jSONObject2.put("CoreSDKVer", dVar.f507i);
            jSONObject.put("sdkver", jSONObject2);
            jSONObject.put("installation", dVar.f509k);
            jSONObject.put("resolution", dVar.f510l);
            jSONObject.put("business", dVar.f511m);
            jSONObject.put("device_id_status", dVar.f512n);
            jSONObject.put("device_id", dVar.f513o);
            jSONObject.put("android_id", dVar.f514p);
            jSONObject.put("mac_address", dVar.f515q);
            jSONObject.put("serial_number", dVar.f516r);
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    /* renamed from: a */
    public static void m99a(Context context, String str) {
        Editor edit = context.getSharedPreferences("jpush_device_info", 0).edit();
        edit.putString("sdk_version", str);
        edit.commit();
    }

    /* renamed from: b */
    public static String m100b(Context context) {
        return context.getSharedPreferences("jpush_device_info", 0).getString("sdk_version", null);
    }

    /* renamed from: b */
    public static void m101b(Context context, C0483d dVar) {
        C0386a.m264c(context, m98a(context, dVar).toString());
    }

    /* renamed from: b */
    public static void m102b(Context context, String str) {
        Editor edit = context.getSharedPreferences("jpush_device_info", 0).edit();
        edit.putString("device_session", str);
        edit.commit();
    }

    /* renamed from: c */
    public static void m103c(Context context) {
        if (C0389d.m334e(context)) {
            JSONObject a = m97a(context);
            if (a != null) {
                String jSONObject = a.toString();
                if (!TextUtils.isEmpty(jSONObject)) {
                    StringBuilder append = new StringBuilder().append(jSONObject);
                    String g = C0386a.m275g("");
                    String i = C0389d.m338i(context);
                    String str = C0385a.f202i;
                    String str2 = C0385a.f201h;
                    String str3 = "1.2.0";
                    String str4 = "120";
                    String str5 = C0385a.f196c;
                    StringBuilder sb = new StringBuilder();
                    sb.append(g);
                    sb.append(",");
                    if (TextUtils.isEmpty(i)) {
                        i = "";
                    }
                    sb.append(i);
                    sb.append(",");
                    sb.append(TextUtils.isEmpty(str) ? "" : str);
                    sb.append(",");
                    sb.append(TextUtils.isEmpty(str2) ? "" : str2);
                    sb.append(",");
                    sb.append(TextUtils.isEmpty(str3) ? "" : str3);
                    sb.append(",");
                    sb.append(TextUtils.isEmpty(str4) ? "" : str4);
                    sb.append(",");
                    sb.append(TextUtils.isEmpty(str5) ? "" : str5);
                    String b = C0530k.m1101b(append.append(sb.toString()).toString());
                    if (b != null && !TextUtils.equals(b, context.getSharedPreferences("jpush_device_info", 0).getString("device_session", null))) {
                        try {
                            C0460q.m707a(context, a, DeviceRequestsHelper.DEVICE_INFO_PARAM);
                            C0460q.m715a(context, a, (C0378a) new C0356c(context, b));
                        } catch (Throwable th) {
                        }
                    }
                }
            }
        }
    }
}
