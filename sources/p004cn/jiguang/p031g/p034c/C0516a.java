package p004cn.jiguang.p031g.p034c;

import android.text.TextUtils;
import org.json.JSONObject;

/* renamed from: cn.jiguang.g.c.a */
public final class C0516a {

    /* renamed from: a */
    public String f589a;

    /* renamed from: b */
    public String f590b;

    /* renamed from: c */
    public String f591c;

    /* renamed from: d */
    public int f592d;

    /* renamed from: e */
    public String f593e;

    /* renamed from: f */
    public String f594f;

    /* renamed from: g */
    public String f595g;

    /* renamed from: a */
    public final JSONObject mo6693a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("imei", TextUtils.isEmpty(this.f590b) ? "" : this.f590b);
            jSONObject.put("iccid", TextUtils.isEmpty(this.f593e) ? "" : this.f593e);
            jSONObject.put("imsi", TextUtils.isEmpty(this.f591c) ? "" : this.f591c);
            return jSONObject;
        } catch (Throwable th) {
            return null;
        }
    }

    public final String toString() {
        return "SimInfo{device_id='" + this.f589a + '\'' + ", imei='" + this.f590b + '\'' + ", imsi='" + this.f591c + '\'' + ", phoneType=" + this.f592d + ", iccid='" + this.f593e + '\'' + ", simOpertorName='" + this.f594f + '\'' + ", networkOperatorName='" + this.f595g + '\'' + '}';
    }
}
