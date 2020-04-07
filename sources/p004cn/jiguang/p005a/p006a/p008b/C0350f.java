package p004cn.jiguang.p005a.p006a.p008b;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.facebook.places.model.PlaceFields;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p004cn.jiguang.p005a.p011b.C0372a;
import p004cn.jiguang.p015d.p016a.C0389d;
import p004cn.jiguang.p015d.p021d.C0460q;
import p004cn.jiguang.p029e.C0501d;
import p004cn.jiguang.p031g.C0506a;
import p004cn.jiguang.p031g.C0530k;

/* renamed from: cn.jiguang.a.a.b.f */
public final class C0350f {

    /* renamed from: c */
    private static volatile C0350f f86c;

    /* renamed from: d */
    private static final Object f87d = new Object();

    /* renamed from: a */
    public boolean f88a = false;

    /* renamed from: b */
    protected Handler f89b;

    /* renamed from: e */
    private Context f90e;

    /* renamed from: f */
    private String f91f = "all";

    /* renamed from: g */
    private boolean f92g = true;

    /* renamed from: h */
    private String f93h = null;

    /* renamed from: i */
    private String f94i = null;

    /* renamed from: j */
    private String f95j = null;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public boolean f96k = false;

    /* renamed from: l */
    private C0345a f97l;
    /* access modifiers changed from: private */

    /* renamed from: m */
    public C0347c f98m;

    /* renamed from: n */
    private C0352h f99n;

    private C0350f(Context context) {
        this.f90e = context;
        this.f97l = new C0345a(context, this);
        this.f99n = new C0352h(context);
        this.f98m = new C0347c(context, this);
    }

    /* renamed from: a */
    public static C0349e m75a(Context context) {
        C0349e eVar = null;
        if (f86c != null) {
            eVar = f86c.m84d();
        }
        if (eVar == null || !eVar.mo6216a()) {
            eVar = C0349e.m67a(C0389d.m342m(context));
        }
        return (eVar == null || !eVar.mo6216a()) ? new C0349e(200.0d, 200.0d, 0.0d, 0.0f, 0.0f, "", 0, false) : eVar;
    }

    /* renamed from: a */
    public static void m76a(Context context, boolean z) {
        if (context != null) {
            if (f86c == null) {
                synchronized (f87d) {
                    if (f86c == null) {
                        f86c = new C0350f(context);
                    }
                }
            }
            f86c.f88a = z;
            C0350f fVar = f86c;
            try {
                if (fVar.f89b == null) {
                    HandlerThread handlerThread = new HandlerThread(PlaceFields.LOCATION);
                    handlerThread.start();
                    fVar.f89b = new C0351g(fVar, handlerThread.getLooper());
                }
                fVar.f89b.sendEmptyMessage(1000);
            } catch (Throwable th) {
            }
        }
    }

    /* renamed from: a */
    private boolean m79a(JSONArray jSONArray, JSONArray jSONArray2, String str) {
        if (C0530k.m1099a(str)) {
            if (!C0530k.m1099a(this.f95j)) {
                return false;
            }
        } else if (!str.equals(this.f95j)) {
            return false;
        }
        if (C0530k.m1099a(this.f94i)) {
            if (!(jSONArray2 == null || jSONArray2.length() == 0)) {
                return false;
            }
        } else if (jSONArray2 == null) {
            return false;
        } else {
            if (jSONArray2.length() == 0) {
                return false;
            }
            if (!this.f94i.equals(jSONArray2.toString())) {
                return false;
            }
        }
        if (C0530k.m1099a(this.f93h)) {
            if (!(jSONArray == null || jSONArray.length() == 0)) {
                return false;
            }
        } else if (jSONArray == null) {
            return false;
        } else {
            if (jSONArray.length() == 0) {
                return false;
            }
            try {
                String optString = ((JSONObject) jSONArray.get(0)).optString("ssid");
                if (!C0530k.m1099a(optString) && !optString.equals(this.f93h)) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: b */
    static /* synthetic */ void m80b(C0350f fVar) {
        fVar.f97l.mo6205c();
        fVar.f99n.mo6225a();
        fVar.f98m.mo6211b();
    }

    /* renamed from: c */
    private void m81c() {
        boolean z;
        JSONArray jSONArray = null;
        if (C0389d.m334e(this.f90e)) {
            JSONArray c = this.f99n.mo6227c();
            JSONArray b = this.f97l.mo6204b();
            C0349e d = this.f88a ? null : m84d();
            JSONObject jSONObject = d != null ? d.mo6221f() : null;
            if (jSONObject != null || b != null || c != null) {
                String str = jSONObject != null ? jSONObject.toString() : "";
                if (!m79a(c, b, str)) {
                    if (jSONObject != null && jSONObject.length() > 0) {
                        JSONArray jSONArray2 = new JSONArray();
                        jSONArray2.put(jSONObject);
                        jSONArray = jSONArray2;
                    }
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        C0460q.m707a(this.f90e, jSONObject2, "loc_info");
                        jSONObject2.put("network_type", C0506a.m959d(this.f90e));
                        jSONObject2.put("local_dns", C0506a.m948b());
                        if (c != null && c.length() > 0) {
                            jSONObject2.put("wifi", c);
                            this.f93h = ((JSONObject) c.get(0)).optString("ssid");
                        }
                        if (b != null && b.length() > 0) {
                            jSONObject2.put("cell", b);
                            this.f94i = b.toString();
                        }
                        if (!this.f88a && jSONArray != null && jSONArray.length() > 0) {
                            jSONObject2.put("gps", jSONArray);
                            this.f95j = str;
                        }
                        Context context = this.f90e;
                        JSONObject a = C0460q.m706a(context, "jpush_lbs_info.json");
                        JSONObject jSONObject3 = a == null ? new JSONObject() : a;
                        JSONArray optJSONArray = jSONObject3.optJSONArray("content");
                        JSONArray jSONArray3 = optJSONArray == null ? new JSONArray() : optJSONArray;
                        try {
                            jSONArray3.put(jSONObject2);
                            long longValue = ((Long) C0389d.m320b(context, "last_report_location", Long.valueOf(0))).longValue();
                            long currentTimeMillis = System.currentTimeMillis();
                            if (currentTimeMillis - longValue > C0372a.m156a(context)) {
                                C0389d.m313a(context, "last_report_location", Long.valueOf(currentTimeMillis));
                                z = true;
                            } else {
                                z = false;
                            }
                            if (z || ((Boolean) C0389d.m320b(context, "lbs_report_now", Boolean.valueOf(false))).booleanValue()) {
                                C0372a.m159b(context, false);
                                C0389d.m313a(context, "last_report_location", Long.valueOf(System.currentTimeMillis()));
                                C0460q.m711a(context, jSONArray3);
                                if (C0460q.m716a(context, "jpush_lbs_info.json", (JSONObject) null) || context.deleteFile("jpush_lbs_info.json")) {
                                }
                                return;
                            }
                            jSONObject3.put("content", jSONArray3);
                            C0460q.m716a(context, "jpush_lbs_info.json", jSONObject3);
                        } catch (JSONException e) {
                            C0501d.m904a("MyLocationManager", "unexpected on lbs reportPrepare", e);
                        }
                    } catch (JSONException e2) {
                    }
                }
            }
        }
    }

    /* renamed from: c */
    static /* synthetic */ void m82c(C0350f fVar) {
        if (C0506a.m944a(fVar.f90e, "android.permission.ACCESS_COARSE_LOCATION")) {
            fVar.f97l.mo6203a();
        } else {
            fVar.mo6222a();
        }
    }

    /* renamed from: d */
    private C0349e m84d() {
        if (this.f98m != null) {
            return this.f98m.mo6208a();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo6222a() {
        this.f99n.mo6226b();
        this.f98m.mo6209a(this.f90e);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo6223b() {
        try {
            if (this.f92g) {
                if (this.f91f.equals("cell_towers")) {
                    if (C0389d.m334e(this.f90e)) {
                        JSONObject a = C0506a.m939a("loc_cell", this.f97l.mo6204b());
                        if (a != null && a.length() > 0) {
                            C0460q.m714a(this.f90e, a);
                        }
                    }
                } else if (this.f91f.equals("wifi_towers")) {
                    if (C0389d.m334e(this.f90e)) {
                        JSONObject a2 = C0506a.m939a("loc_wifi", this.f99n.mo6227c());
                        if (a2 != null && a2.length() > 0) {
                            C0460q.m714a(this.f90e, a2);
                        }
                    }
                } else if (this.f91f.equals("gps")) {
                    if (C0389d.m334e(this.f90e) && !this.f88a) {
                        C0349e d = m84d();
                        JSONObject jSONObject = d != null ? d.mo6221f() : null;
                        if (jSONObject != null && jSONObject.length() > 0) {
                            JSONArray jSONArray = new JSONArray();
                            jSONArray.put(jSONObject);
                            JSONObject a3 = C0506a.m939a("loc_gps", jSONArray);
                            if (a3 != null && a3.length() > 0) {
                                C0460q.m714a(this.f90e, a3);
                            }
                        }
                    }
                } else if (this.f91f.equals("all")) {
                    m81c();
                }
                this.f96k = false;
            }
        } catch (Exception e) {
        } finally {
            this.f96k = false;
        }
        if (this.f89b != null) {
            this.f89b.removeCallbacksAndMessages(null);
            try {
                this.f89b.getLooper().quit();
            } catch (Exception e2) {
            }
            this.f89b = null;
        }
    }
}
