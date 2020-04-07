package p004cn.jiguang.p014c;

import android.text.TextUtils;
import java.util.LinkedHashMap;
import org.json.JSONObject;
import p004cn.jiguang.api.MultiSpHelper;
import p004cn.jiguang.p015d.C0385a;

/* renamed from: cn.jiguang.c.a */
public final class C0382a implements C0383b {

    /* renamed from: a */
    private static final LinkedHashMap<String, Integer> f192a;

    static {
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        f192a = linkedHashMap;
        linkedHashMap.put("s.jpush.cn", Integer.valueOf(19000));
        f192a.put("sis.jpush.io", Integer.valueOf(19000));
        f192a.put("easytomessage.com", Integer.valueOf(19000));
    }

    /* renamed from: a */
    public static void m220a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String optString = jSONObject.optString("srv");
                if (!TextUtils.isEmpty(optString)) {
                    MultiSpHelper.commitString(C0385a.f198e, "srv", optString);
                }
                String optString2 = jSONObject.optString("conn");
                if (!TextUtils.isEmpty(optString2)) {
                    MultiSpHelper.commitString(C0385a.f198e, "conn", optString2);
                }
            } catch (Throwable th) {
            }
        }
    }

    /* renamed from: a */
    public final String mo6345a() {
        return "CN";
    }

    /* renamed from: b */
    public final LinkedHashMap<String, Integer> mo6346b() {
        return f192a;
    }

    /* renamed from: c */
    public final String mo6347c() {
        String string = MultiSpHelper.getString(C0385a.f198e, "conn", "im64.jpush.cn");
        return TextUtils.isEmpty(string) ? "im64.jpush.cn" : string;
    }

    /* renamed from: d */
    public final String mo6348d() {
        String string = MultiSpHelper.getString(C0385a.f198e, "srv", "_im64._tcp.jpush.cn");
        return TextUtils.isEmpty(string) ? "_im64._tcp.jpush.cn" : string;
    }

    /* renamed from: e */
    public final String mo6349e() {
        return "_psis._udp.jpush.cn";
    }

    /* renamed from: f */
    public final String mo6350f() {
        return "stats.jpush.cn";
    }

    /* renamed from: g */
    public final String mo6351g() {
        return "update.sdk.jiguang.cn";
    }
}
