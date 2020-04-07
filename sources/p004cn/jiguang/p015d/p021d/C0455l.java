package p004cn.jiguang.p015d.p021d;

import java.io.File;
import org.json.JSONObject;

/* renamed from: cn.jiguang.d.d.l */
public final class C0455l {

    /* renamed from: a */
    private File f425a;

    /* renamed from: b */
    private JSONObject f426b;

    /* renamed from: c */
    private long f427c;

    /* renamed from: d */
    private JSONObject f428d;

    /* renamed from: e */
    private boolean f429e;

    /* renamed from: f */
    private boolean f430f;

    public C0455l(File file, JSONObject jSONObject) {
        this.f425a = file;
        this.f426b = jSONObject;
        this.f427c = file.length();
    }

    /* renamed from: a */
    public final long mo6571a() {
        return this.f427c;
    }

    /* renamed from: a */
    public final void mo6572a(File file) {
        this.f425a = file;
        mo6575b();
    }

    /* renamed from: a */
    public final void mo6573a(JSONObject jSONObject) {
        this.f426b = null;
    }

    /* renamed from: a */
    public final void mo6574a(boolean z) {
        this.f429e = z;
    }

    /* renamed from: b */
    public final void mo6575b() {
        this.f427c = this.f425a.length();
    }

    /* renamed from: b */
    public final void mo6576b(JSONObject jSONObject) {
        this.f428d = jSONObject;
    }

    /* renamed from: b */
    public final void mo6577b(boolean z) {
        this.f430f = z;
    }

    /* renamed from: c */
    public final File mo6578c() {
        return this.f425a;
    }

    /* renamed from: d */
    public final JSONObject mo6579d() {
        return this.f426b;
    }

    /* renamed from: e */
    public final JSONObject mo6580e() {
        return this.f428d;
    }

    /* renamed from: f */
    public final boolean mo6581f() {
        return this.f429e;
    }

    /* renamed from: g */
    public final boolean mo6582g() {
        return this.f430f;
    }
}
