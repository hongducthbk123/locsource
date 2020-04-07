package p004cn.jpush.android.data;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.vending.expansion.downloader.Constants;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;
import p004cn.jpush.android.C0541a;
import p004cn.jpush.android.p037a.C0546d;
import p004cn.jpush.android.p037a.C0550g;
import p004cn.jpush.android.p038b.C0564a;
import p004cn.jpush.android.p040d.C0577a;
import p004cn.jpush.android.p040d.C0579c;
import p004cn.jpush.android.p040d.C0582e;

/* renamed from: cn.jpush.android.data.b */
public abstract class C0589b implements Serializable {
    private static final long serialVersionUID = 8653272927271926594L;

    /* renamed from: A */
    public int f794A;

    /* renamed from: B */
    public String f795B;

    /* renamed from: C */
    public boolean f796C = false;

    /* renamed from: D */
    public boolean f797D = false;

    /* renamed from: E */
    public boolean f798E = false;

    /* renamed from: F */
    public boolean f799F = false;

    /* renamed from: G */
    public int f800G = -1;

    /* renamed from: H */
    public ArrayList<String> f801H = null;

    /* renamed from: I */
    public String f802I;

    /* renamed from: a */
    private boolean f803a = false;

    /* renamed from: b */
    public int f804b;

    /* renamed from: c */
    public String f805c;

    /* renamed from: d */
    public String f806d;

    /* renamed from: e */
    public byte f807e = 0;

    /* renamed from: f */
    public boolean f808f;

    /* renamed from: g */
    public int f809g;

    /* renamed from: h */
    public int f810h = 0;

    /* renamed from: i */
    public boolean f811i;

    /* renamed from: j */
    public String f812j;

    /* renamed from: k */
    public String f813k;

    /* renamed from: l */
    public int f814l = -1;

    /* renamed from: m */
    public String f815m;

    /* renamed from: n */
    public String f816n;

    /* renamed from: o */
    public String f817o;

    /* renamed from: p */
    public String f818p;

    /* renamed from: q */
    public int f819q;

    /* renamed from: r */
    public boolean f820r;

    /* renamed from: s */
    public List<String> f821s = null;

    /* renamed from: t */
    public int f822t;

    /* renamed from: u */
    public String f823u;

    /* renamed from: v */
    public String f824v;

    /* renamed from: w */
    public int f825w;

    /* renamed from: x */
    public String f826x;

    /* renamed from: y */
    public String f827y;

    /* renamed from: z */
    public String f828z;

    /* renamed from: a */
    public abstract void mo6876a(Context context);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract boolean mo6877a(JSONObject jSONObject);

    /* renamed from: a */
    public final boolean mo6879a(Context context, JSONObject jSONObject) {
        this.f820r = jSONObject.optInt("full_screen", 0) > 0;
        this.f822t = jSONObject.optInt("n_flag", 0);
        this.f823u = jSONObject.optString("n_title", "");
        this.f824v = jSONObject.optString("n_content", "");
        this.f825w = jSONObject.optInt("n_style", 0);
        this.f826x = jSONObject.optString("n_big_text", "");
        this.f827y = jSONObject.optString("n_big_pic_path", "");
        this.f828z = jSONObject.optString("n_inbox", "");
        this.f816n = jSONObject.optString("n_extras", "");
        this.f794A = jSONObject.optInt("n_priority", 0);
        this.f795B = jSONObject.optString("n_category", "");
        this.f814l = jSONObject.optInt("n_alert_type", -1);
        if (TextUtils.isEmpty(this.f823u)) {
            if (!this.f811i) {
                C0546d.m1124a(this.f805c, 996, null, context);
                return false;
            }
            this.f823u = C0541a.f652d;
        }
        JSONObject a = C0550g.m1136a(context, this.f805c, jSONObject, "ad_content");
        if (a != null) {
            if (this.f811i && this.f808f) {
                this.f803a = true;
            }
            return mo6877a(a);
        } else if (!this.f811i || !this.f808f) {
            return false;
        } else {
            return true;
        }
    }

    /* renamed from: a */
    public final boolean mo6878a() {
        return this.f803a;
    }

    /* renamed from: a */
    static boolean m1324a(ArrayList<String> arrayList, Context context, String str, String str2, boolean z) {
        String str3;
        String str4;
        if (!C0550g.m1139a(str) || context == null || arrayList.size() <= 0 || TextUtils.isEmpty(str2)) {
            return true;
        }
        Iterator it = arrayList.iterator();
        boolean z2 = true;
        while (it.hasNext()) {
            String str5 = (String) it.next();
            if (str5 == null || str5.startsWith("http://")) {
                str3 = str5;
            } else {
                str3 = str + str5;
            }
            byte[] a = C0564a.m1213a(str3, 5, Constants.ACTIVE_THREAD_WATCHDOG, 4);
            if (a != null) {
                try {
                    if (str5.startsWith("http://")) {
                        str5 = C0579c.m1295a(str5);
                    }
                    if (!z) {
                        str4 = C0579c.m1294a(context, str2) + str5;
                    } else {
                        str4 = C0579c.m1299b(context, str2) + str5;
                    }
                    C0579c.m1298a(str4, a);
                } catch (Exception e) {
                    C0582e.m1305b("Entity", "Write storage error,  create img file fail.", e);
                    z2 = false;
                }
            } else {
                C0546d.m1124a(str2, 1020, C0577a.m1269a(context, str3), context);
                z2 = false;
            }
        }
        return z2;
    }

    /* renamed from: a */
    static String m1323a(Context context, String str, String str2) {
        if (!str.endsWith(".jpg") && !str.endsWith(".png")) {
            return "";
        }
        String str3 = C0579c.m1299b(context, str2) + (str2 + str.substring(str.lastIndexOf(".")));
        byte[] a = C0564a.m1213a(str, 5, Constants.ACTIVE_THREAD_WATCHDOG, 4);
        if (a == null) {
            return "";
        }
        try {
            C0579c.m1298a(str3, a);
            return str3;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
