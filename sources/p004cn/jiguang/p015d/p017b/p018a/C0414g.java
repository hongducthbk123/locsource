package p004cn.jiguang.p015d.p017b.p018a;

import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: cn.jiguang.d.b.a.g */
public final class C0414g {

    /* renamed from: a */
    private int f276a;

    /* renamed from: b */
    private String f277b;

    /* renamed from: c */
    private String f278c;

    /* renamed from: d */
    private long f279d;

    /* renamed from: e */
    private String f280e;

    /* renamed from: f */
    private double f281f;

    /* renamed from: g */
    private double f282g;

    /* renamed from: h */
    private long f283h;

    public C0414g(int i, String str, String str2, long j, String str3, double d, double d2, long j2) {
        this.f276a = i;
        this.f277b = str;
        this.f278c = str2;
        this.f279d = j;
        this.f280e = str3;
        this.f281f = d;
        this.f282g = d2;
        this.f283h = j2;
    }

    /* renamed from: a */
    public final int mo6427a() {
        return this.f276a;
    }

    /* renamed from: b */
    public final long mo6428b() {
        return this.f279d;
    }

    /* renamed from: c */
    public final double mo6429c() {
        return this.f281f;
    }

    /* renamed from: d */
    public final double mo6430d() {
        return this.f282g;
    }

    /* renamed from: e */
    public final long mo6431e() {
        return this.f283h;
    }

    /* renamed from: f */
    public final JSONObject mo6432f() {
        boolean z = false;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", this.f276a);
            jSONObject.put("appkey", this.f277b);
            jSONObject.put("sdkver", this.f278c);
            jSONObject.put("platform", 0);
            if (this.f279d != 0) {
                jSONObject.put("uid", this.f279d);
            }
            if (this.f280e != null) {
                jSONObject.put("opera", this.f280e);
            }
            double d = this.f281f;
            double d2 = this.f282g;
            if (d > -90.0d && d < 90.0d && d2 > -180.0d && d2 < 180.0d) {
                z = true;
            }
            if (z) {
                jSONObject.put("lat", this.f281f);
                jSONObject.put("lng", this.f282g);
                jSONObject.put("time", this.f283h);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
