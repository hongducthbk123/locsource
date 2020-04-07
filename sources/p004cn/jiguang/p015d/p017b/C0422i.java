package p004cn.jiguang.p015d.p017b;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
import p004cn.jiguang.p015d.p016a.C0386a;
import p004cn.jiguang.p015d.p016a.C0389d;
import p004cn.jiguang.p015d.p021d.C0460q;
import p004cn.jiguang.p029e.C0501d;
import p004cn.jiguang.p031g.C0530k;

/* renamed from: cn.jiguang.d.b.i */
public final class C0422i {

    /* renamed from: l */
    private static C0422i f319l = null;

    /* renamed from: m */
    private static Pattern f320m = Pattern.compile("(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))");

    /* renamed from: a */
    List<String> f321a = new ArrayList();

    /* renamed from: b */
    List<String> f322b = new ArrayList();

    /* renamed from: c */
    List<String> f323c = new ArrayList();

    /* renamed from: d */
    boolean f324d = false;

    /* renamed from: e */
    String f325e;

    /* renamed from: f */
    String f326f;

    /* renamed from: g */
    int f327g;

    /* renamed from: h */
    List<String> f328h = new ArrayList();

    /* renamed from: i */
    List<Integer> f329i = new ArrayList();

    /* renamed from: j */
    String f330j;

    /* renamed from: k */
    boolean f331k = false;

    /* renamed from: c */
    public static boolean m502c(String str) {
        return f320m.matcher(str).matches();
    }

    /* renamed from: a */
    public final String mo6470a() {
        return this.f326f;
    }

    /* renamed from: a */
    public final void mo6471a(String str) {
        if (C0530k.m1099a(str)) {
            str = C0386a.m286m();
        }
        if (!C0530k.m1099a(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                JSONArray jSONArray = jSONObject.getJSONArray("ips");
                if (jSONArray != null && jSONArray.length() != 0) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        this.f321a.add(jSONArray.optString(i));
                    }
                    JSONArray jSONArray2 = jSONObject.getJSONArray("op_conns");
                    if (jSONArray2 != null) {
                        jSONArray2.length();
                    }
                    if (jSONArray2 != null) {
                        for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                            this.f322b.add(jSONArray2.optString(i2));
                        }
                    }
                    JSONArray optJSONArray = jSONObject.optJSONArray("sis_ips");
                    if (optJSONArray != null && optJSONArray.length() > 0) {
                        for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                            this.f323c.add(optJSONArray.optString(i3));
                        }
                    }
                    this.f324d = jSONObject.optBoolean("data_report");
                }
            } catch (Throwable th) {
            }
        }
    }

    /* renamed from: b */
    public final int mo6472b() {
        return this.f327g;
    }

    /* renamed from: b */
    public final void mo6473b(String str) {
        this.f330j = str;
        if (this.f321a == null) {
            C0501d.m909d("SisInfo", "Unexpected - Invalid sis - no ips key.");
            this.f331k = true;
        } else if (this.f321a.size() == 0) {
            C0501d.m909d("SisInfo", "Unexpected - invalid sis - ips array len is 0");
            this.f331k = true;
        } else {
            try {
                C0423j jVar = new C0423j((String) this.f321a.get(0));
                this.f326f = jVar.f332a;
                this.f327g = jVar.f333b;
                if (this.f322b != null) {
                    for (String jVar2 : this.f322b) {
                        try {
                            C0423j jVar3 = new C0423j(jVar2);
                            this.f328h.add(jVar3.f332a);
                            this.f329i.add(Integer.valueOf(jVar3.f333b));
                        } catch (Exception e) {
                        }
                    }
                }
            } catch (Exception e2) {
                C0501d.m904a("SisInfo", "Failed to parse ips-1 - main ip.", e2);
                this.f331k = true;
            }
        }
    }

    /* renamed from: c */
    public final List<String> mo6474c() {
        return this.f328h;
    }

    /* renamed from: d */
    public final List<Integer> mo6475d() {
        return this.f329i;
    }

    /* renamed from: e */
    public final boolean mo6476e() {
        return this.f331k;
    }

    /* renamed from: f */
    public final List<String> mo6477f() {
        return this.f323c;
    }

    /* renamed from: g */
    public final boolean mo6478g() {
        return this.f324d;
    }

    /* renamed from: h */
    public final void mo6479h() {
        int size = this.f321a.size();
        if (size != 0) {
            C0386a.m265c(this.f330j);
            if (size > 1) {
                try {
                    C0423j jVar = new C0423j((String) this.f321a.get(1));
                    C0386a.m261b(jVar.f332a, jVar.f333b);
                } catch (Exception e) {
                    C0501d.m904a("SisInfo", "Failed to parse ips-2 - default ip.", e);
                }
            } else {
                C0501d.m907c("SisInfo", "Only main ip in sis.");
            }
            if (size > 2) {
                C0460q.m704a((String) this.f321a.get(2));
                C0389d.m317a((String) this.f321a.get(2));
            } else {
                C0501d.m907c("SisInfo", "No report backup ip.");
            }
            if (this.f325e != null) {
                C0386a.m260b(this.f325e);
            }
        }
    }
}
