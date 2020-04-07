package p004cn.jiguang.p015d.p017b.p018a;

import android.text.TextUtils;
import java.util.LinkedList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: cn.jiguang.d.b.a.f */
public final class C0413f {

    /* renamed from: a */
    int f266a;

    /* renamed from: b */
    C0410c f267b;

    /* renamed from: c */
    long f268c;

    /* renamed from: d */
    long f269d;

    /* renamed from: e */
    long f270e;

    /* renamed from: f */
    int f271f;

    /* renamed from: g */
    double f272g;

    /* renamed from: h */
    double f273h;

    /* renamed from: i */
    long f274i;

    /* renamed from: j */
    int f275j;

    /* renamed from: a */
    private static C0413f m436a(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() == 0) {
            return null;
        }
        try {
            C0413f fVar = new C0413f();
            fVar.f266a = jSONObject.getInt("type");
            fVar.f267b = C0410c.m413a(jSONObject.getString("addr"));
            fVar.f269d = jSONObject.getLong("rtime");
            fVar.f270e = jSONObject.getLong("interval");
            fVar.f271f = jSONObject.getInt("net");
            fVar.f275j = jSONObject.getInt("code");
            fVar.f268c = (long) jSONObject.optInt("uid");
            fVar.f272g = jSONObject.optDouble("lat");
            fVar.f273h = jSONObject.optDouble("lng");
            fVar.f274i = jSONObject.optLong("ltime");
            return fVar;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static LinkedList<C0413f> m437a(String str) {
        LinkedList<C0413f> linkedList = new LinkedList<>();
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONArray jSONArray = new JSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    linkedList.add(m436a(jSONArray.getJSONObject(i)));
                }
            } catch (JSONException e) {
            }
        }
        return linkedList;
    }

    /* renamed from: a */
    public final JSONObject mo6426a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", this.f266a);
            jSONObject.put("addr", this.f267b.toString());
            jSONObject.put("rtime", this.f269d);
            jSONObject.put("interval", this.f270e);
            jSONObject.put("net", this.f271f);
            jSONObject.put("code", this.f275j);
            if (this.f268c != 0) {
                jSONObject.put("uid", this.f268c);
            }
            double d = this.f272g;
            double d2 = this.f273h;
            if (d > -90.0d && d < 90.0d && d2 > -180.0d && d2 < 180.0d) {
                jSONObject.put("lat", this.f272g);
                jSONObject.put("lng", this.f273h);
                jSONObject.put("ltime", this.f274i);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
