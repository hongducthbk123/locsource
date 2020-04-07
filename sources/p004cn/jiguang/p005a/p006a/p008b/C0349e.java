package p004cn.jiguang.p005a.p006a.p008b;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: cn.jiguang.a.a.b.e */
public final class C0349e {

    /* renamed from: a */
    private double f77a;

    /* renamed from: b */
    private double f78b;

    /* renamed from: c */
    private double f79c;

    /* renamed from: d */
    private float f80d;

    /* renamed from: e */
    private float f81e;

    /* renamed from: f */
    private String f82f;

    /* renamed from: g */
    private long f83g;

    /* renamed from: h */
    private boolean f84h;

    /* renamed from: i */
    private String f85i;

    public C0349e(double d, double d2, double d3, float f, float f2, String str, long j, boolean z) {
        this.f77a = d;
        this.f78b = d2;
        this.f79c = d3;
        this.f80d = f;
        this.f81e = f2;
        this.f82f = str;
        this.f83g = j;
        this.f84h = z;
    }

    public C0349e(String str) {
        this.f85i = str;
    }

    /* renamed from: a */
    public static C0349e m67a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new C0349e(jSONObject.getDouble("lat"), jSONObject.getDouble("lng"), jSONObject.getDouble("alt"), (float) jSONObject.getDouble("bear"), (float) jSONObject.getDouble("acc"), jSONObject.getString("tag"), jSONObject.getLong("itime"), true);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    static /* synthetic */ boolean m68a(double d, double d2) {
        return d > -90.0d && d < 90.0d && d2 > -180.0d && d2 < 180.0d;
    }

    /* renamed from: a */
    public final boolean mo6216a() {
        return TextUtils.isEmpty(this.f85i);
    }

    /* renamed from: b */
    public final double mo6217b() {
        return this.f77a;
    }

    /* renamed from: c */
    public final double mo6218c() {
        return this.f78b;
    }

    /* renamed from: d */
    public final long mo6219d() {
        return this.f83g;
    }

    /* renamed from: e */
    public final String mo6220e() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("lat", this.f77a);
            jSONObject.put("lng", this.f78b);
            jSONObject.put("time", this.f83g);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    /* renamed from: f */
    public final JSONObject mo6221f() {
        if (TextUtils.isEmpty(this.f85i)) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("lat", this.f77a);
                jSONObject.put("lng", this.f78b);
                jSONObject.put("alt", this.f79c);
                jSONObject.put("bear", (double) this.f80d);
                jSONObject.put("acc", (double) this.f81e);
                jSONObject.put("tag", this.f82f);
                jSONObject.put("itime", this.f83g);
                return jSONObject;
            } catch (JSONException e) {
                this.f85i = "JSONException " + e.getMessage();
            }
        }
        return null;
    }
}
