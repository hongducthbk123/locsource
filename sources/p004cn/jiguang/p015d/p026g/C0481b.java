package p004cn.jiguang.p015d.p026g;

import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: cn.jiguang.d.g.b */
public final class C0481b {

    /* renamed from: a */
    int f491a = -1;

    /* renamed from: b */
    int f492b = -1;

    /* renamed from: c */
    int f493c = -1;

    /* renamed from: d */
    int f494d = -1;

    /* renamed from: e */
    int f495e = -1;

    /* renamed from: a */
    public final JSONObject mo6621a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("level", this.f491a);
            jSONObject.put("scale", this.f492b);
            jSONObject.put("status", this.f493c);
            jSONObject.put("voltage", this.f494d);
            jSONObject.put("temperature", this.f495e);
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    public final String toString() {
        return "BatteryInfo{level=" + this.f491a + ", scale=" + this.f492b + ", status=" + this.f493c + ", voltage=" + this.f494d + ", temperature=" + this.f495e + '}';
    }
}
