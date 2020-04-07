package p004cn.jpush.android.data;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import org.json.JSONObject;
import p004cn.jpush.android.p037a.C0550g;
import p004cn.jpush.android.p040d.C0578b;

/* renamed from: cn.jpush.android.data.g */
public final class C0594g extends C0589b {
    private static final long serialVersionUID = 2748721849169550485L;

    /* renamed from: J */
    public String f851J;

    /* renamed from: K */
    public int f852K;

    /* renamed from: L */
    public int f853L;

    /* renamed from: M */
    public int f854M;

    /* renamed from: N */
    public ArrayList<String> f855N;

    /* renamed from: O */
    public String f856O;

    /* renamed from: P */
    public String f857P;

    /* renamed from: Q */
    public String f858Q;

    /* renamed from: a */
    public String f859a;

    public C0594g() {
        this.f855N = new ArrayList<>();
        this.f856O = "";
        this.f857P = "";
        this.f819q = 0;
    }

    /* renamed from: a */
    public final boolean mo6877a(JSONObject jSONObject) {
        this.f859a = jSONObject.optString("e_url", "").trim();
        this.f851J = jSONObject.optString("e_title", "").trim();
        if (!TextUtils.isEmpty(this.f859a) && !C0550g.m1139a(this.f859a)) {
            this.f859a = "http://" + this.f859a;
        }
        this.f853L = jSONObject.optInt("e_rich_type", 0);
        this.f852K = jSONObject.optInt("e_jump_mode", 0);
        this.f854M = jSONObject.optInt("e_show", 0);
        if (3 == this.f853L || 2 == this.f853L || 1 == this.f853L) {
            this.f855N = C0578b.m1293a(jSONObject.optJSONArray("e_eres"));
        }
        this.f856O = jSONObject.optString("from_num", "");
        this.f857P = jSONObject.optString("to_num", "");
        return true;
    }

    /* renamed from: a */
    public final void mo6876a(final Context context) {
        new Thread() {
            /* JADX WARNING: Removed duplicated region for block: B:45:0x0100  */
            /* JADX WARNING: Removed duplicated region for block: B:51:0x0150  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final void run() {
                /*
                    r11 = this;
                    r6 = 3
                    r10 = 995(0x3e3, float:1.394E-42)
                    r9 = 1014(0x3f6, float:1.421E-42)
                    r1 = 0
                    r3 = 0
                    cn.jpush.android.data.g r0 = p004cn.jpush.android.data.C0594g.this
                    int r0 = r0.f854M
                    if (r0 == 0) goto L_0x000e
                L_0x000d:
                    return
                L_0x000e:
                    cn.jpush.android.data.g r0 = r1
                    java.lang.String r4 = r0.f805c
                    cn.jpush.android.data.g r0 = r1
                    java.lang.String r5 = r0.f859a
                    cn.jpush.android.data.g r0 = r1
                    java.lang.String r0 = r0.f827y
                    android.content.Context r2 = r2
                    android.content.Context r2 = r2.getApplicationContext()
                    p004cn.jiguang.api.JCoreInterface.triggerSceneCheck(r2, r6)
                    cn.jpush.android.data.g r2 = r1
                    int r2 = r2.f853L
                    if (r2 != 0) goto L_0x00a7
                    cn.jpush.android.data.g r1 = r1
                    int r1 = r1.f825w
                    if (r1 != r6) goto L_0x005f
                    boolean r1 = android.text.TextUtils.isEmpty(r0)
                    if (r1 != 0) goto L_0x005f
                    java.lang.String r1 = "http://"
                    boolean r1 = r0.startsWith(r1)
                    if (r1 != 0) goto L_0x0045
                    java.lang.String r1 = "https://"
                    boolean r1 = r0.startsWith(r1)
                    if (r1 == 0) goto L_0x007c
                L_0x0045:
                    android.content.Context r1 = r2
                    java.lang.String r2 = "android.permission.WRITE_EXTERNAL_STORAGE"
                    boolean r1 = p004cn.jpush.android.p040d.C0577a.m1283b(r1, r2)
                    if (r1 == 0) goto L_0x0074
                    android.content.Context r1 = r2
                    java.lang.String r0 = p004cn.jpush.android.data.C0589b.m1323a(r1, r0, r4)
                    boolean r1 = android.text.TextUtils.isEmpty(r0)
                    if (r1 != 0) goto L_0x006c
                    cn.jpush.android.data.g r1 = r1
                    r1.f827y = r0
                L_0x005f:
                    android.content.Context r0 = r2
                    p004cn.jpush.android.p037a.C0546d.m1124a(r4, r10, r3, r0)
                    android.content.Context r0 = r2
                    cn.jpush.android.data.g r1 = r1
                    p004cn.jpush.android.api.C0560b.m1192a(r0, r1)
                    goto L_0x000d
                L_0x006c:
                    java.lang.String r0 = "ShowEntity"
                    java.lang.String r1 = "Get network picture failed, show basic notification only."
                    p004cn.jpush.android.p040d.C0582e.m1306c(r0, r1)
                    goto L_0x005f
                L_0x0074:
                    java.lang.String r0 = "ShowEntity"
                    java.lang.String r1 = "No permission to write resource to storage, show basic notification only."
                    p004cn.jpush.android.p040d.C0582e.m1306c(r0, r1)
                    goto L_0x005f
                L_0x007c:
                    android.content.Context r1 = r2
                    java.lang.String r2 = "android.permission.READ_EXTERNAL_STORAGE"
                    boolean r1 = p004cn.jpush.android.p040d.C0577a.m1283b(r1, r2)
                    if (r1 == 0) goto L_0x009f
                    android.content.Context r1 = r2
                    java.lang.String r0 = p004cn.jpush.android.p040d.C0579c.m1300c(r1, r0)
                    boolean r1 = android.text.TextUtils.isEmpty(r0)
                    if (r1 != 0) goto L_0x0097
                    cn.jpush.android.data.g r1 = r1
                    r1.f827y = r0
                    goto L_0x005f
                L_0x0097:
                    java.lang.String r0 = "ShowEntity"
                    java.lang.String r1 = "Get developer picture failed, show basic notification only."
                    p004cn.jpush.android.p040d.C0582e.m1306c(r0, r1)
                    goto L_0x005f
                L_0x009f:
                    java.lang.String r0 = "ShowEntity"
                    java.lang.String r1 = "No permission to read resource from storage, show basic notification only."
                    p004cn.jpush.android.p040d.C0582e.m1306c(r0, r1)
                    goto L_0x005f
                L_0x00a7:
                    r0 = 4
                    cn.jpush.android.data.g r2 = r1
                    int r2 = r2.f853L
                    if (r0 != r2) goto L_0x00c0
                    cn.jpush.android.data.g r0 = r1
                    r0.f858Q = r5
                    android.content.Context r0 = r2
                    p004cn.jpush.android.p037a.C0546d.m1124a(r4, r10, r3, r0)
                    android.content.Context r0 = r2
                    cn.jpush.android.data.g r1 = r1
                    p004cn.jpush.android.api.C0560b.m1192a(r0, r1)
                    goto L_0x000d
                L_0x00c0:
                    android.content.Context r0 = r2
                    java.lang.String r2 = "android.permission.WRITE_EXTERNAL_STORAGE"
                    boolean r0 = p004cn.jpush.android.p040d.C0577a.m1283b(r0, r2)
                    if (r0 != 0) goto L_0x00d8
                    java.lang.String r0 = "ShowEntity"
                    java.lang.String r1 = "Rich-push needs the permission of WRITE_EXTERNAL_STORAGE, please request it."
                    p004cn.jpush.android.p040d.C0582e.m1308d(r0, r1)
                    android.content.Context r0 = r2
                    p004cn.jpush.android.p037a.C0546d.m1124a(r4, r9, r3, r0)
                    goto L_0x000d
                L_0x00d8:
                    boolean r0 = android.text.TextUtils.isEmpty(r5)
                    if (r0 != 0) goto L_0x01c7
                    r0 = r1
                L_0x00df:
                    r2 = 4
                    if (r0 >= r2) goto L_0x01c7
                    r2 = 5
                    r6 = 5000(0x1388, double:2.4703E-320)
                    cn.jiguang.net.HttpResponse r2 = p004cn.jpush.android.p038b.C0564a.m1212a(r5, r2, r6)
                    if (r2 == 0) goto L_0x014d
                    int r6 = r2.getResponseCode()
                    r7 = 200(0xc8, float:2.8E-43)
                    if (r6 != r7) goto L_0x014d
                    r0 = 1
                    java.lang.String r2 = r2.getResponseBody()
                L_0x00f8:
                    android.content.Context r6 = r2
                    java.lang.String r6 = p004cn.jpush.android.p040d.C0579c.m1299b(r6, r4)
                    if (r0 == 0) goto L_0x0150
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    r0.<init>()
                    java.lang.StringBuilder r0 = r0.append(r6)
                    java.lang.StringBuilder r0 = r0.append(r4)
                    java.lang.String r7 = ".html"
                    java.lang.StringBuilder r0 = r0.append(r7)
                    java.lang.String r0 = r0.toString()
                    java.lang.String r7 = "/"
                    int r7 = r5.lastIndexOf(r7)
                    int r7 = r7 + 1
                    java.lang.String r1 = r5.substring(r1, r7)
                    cn.jpush.android.data.g r5 = r1
                    java.util.ArrayList<java.lang.String> r5 = r5.f855N
                    boolean r5 = r5.isEmpty()
                    if (r5 != 0) goto L_0x01b6
                    cn.jpush.android.data.g r5 = r1
                    java.util.ArrayList<java.lang.String> r5 = r5.f855N
                    android.content.Context r7 = r2
                    cn.jpush.android.data.g r8 = r1
                    boolean r8 = r8.mo6878a()
                    boolean r5 = p004cn.jpush.android.data.C0589b.m1324a(r5, r7, r1, r4, r8)
                    if (r5 != 0) goto L_0x0164
                    android.content.Context r0 = r2
                    p004cn.jpush.android.p037a.C0546d.m1124a(r4, r9, r3, r0)
                    android.content.Context r0 = r2
                    cn.jpush.android.data.g r1 = r1
                    p004cn.jpush.android.api.C0560b.m1192a(r0, r1)
                    goto L_0x000d
                L_0x014d:
                    int r0 = r0 + 1
                    goto L_0x00df
                L_0x0150:
                    android.content.Context r0 = r2
                    p004cn.jpush.android.p037a.C0546d.m1124a(r4, r9, r3, r0)
                    r0 = 1021(0x3fd, float:1.431E-42)
                    android.content.Context r1 = r2
                    java.lang.String r1 = p004cn.jpush.android.p040d.C0577a.m1269a(r1, r5)
                    android.content.Context r2 = r2
                    p004cn.jpush.android.p037a.C0546d.m1124a(r4, r0, r1, r2)
                    goto L_0x000d
                L_0x0164:
                    java.lang.StringBuilder r5 = new java.lang.StringBuilder
                    java.lang.String r7 = "img src=\""
                    r5.<init>(r7)
                    java.lang.StringBuilder r1 = r5.append(r1)
                    java.lang.String r1 = r1.toString()
                    java.lang.StringBuilder r5 = new java.lang.StringBuilder
                    java.lang.String r7 = "img src=\""
                    r5.<init>(r7)
                    java.lang.StringBuilder r5 = r5.append(r6)
                    java.lang.String r5 = r5.toString()
                    java.lang.String r1 = r2.replaceAll(r1, r5)
                    android.content.Context r2 = r2
                    boolean r1 = p004cn.jpush.android.p040d.C0579c.m1297a(r0, r1, r2)
                    if (r1 == 0) goto L_0x01af
                    cn.jpush.android.data.g r1 = r1
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder
                    java.lang.String r5 = "file://"
                    r2.<init>(r5)
                    java.lang.StringBuilder r0 = r2.append(r0)
                    java.lang.String r0 = r0.toString()
                    r1.f858Q = r0
                    android.content.Context r0 = r2
                    p004cn.jpush.android.p037a.C0546d.m1124a(r4, r10, r3, r0)
                    android.content.Context r0 = r2
                    cn.jpush.android.data.g r1 = r1
                    p004cn.jpush.android.api.C0560b.m1192a(r0, r1)
                    goto L_0x000d
                L_0x01af:
                    android.content.Context r0 = r2
                    p004cn.jpush.android.p037a.C0546d.m1124a(r4, r9, r3, r0)
                    goto L_0x000d
                L_0x01b6:
                    cn.jpush.android.data.g r0 = r1
                    cn.jpush.android.data.g r1 = r1
                    java.lang.String r1 = r1.f859a
                    r0.f858Q = r1
                    android.content.Context r0 = r2
                    cn.jpush.android.data.g r1 = r1
                    p004cn.jpush.android.api.C0560b.m1192a(r0, r1)
                    goto L_0x000d
                L_0x01c7:
                    r0 = r1
                    r2 = r3
                    goto L_0x00f8
                */
                throw new UnsupportedOperationException("Method not decompiled: p004cn.jpush.android.data.C0594g.C05951.run():void");
            }
        }.start();
    }
}
