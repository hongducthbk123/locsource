package p004cn.jiguang.p015d.p028h;

import android.content.Context;
import java.util.ArrayList;
import org.json.JSONObject;
import p004cn.jiguang.p015d.p021d.C0444a;
import p004cn.jiguang.p015d.p021d.C0460q;

/* renamed from: cn.jiguang.d.h.a */
public abstract class C0490a<T> {

    /* renamed from: a */
    protected long f526a;

    /* renamed from: b */
    protected String f527b;

    /* renamed from: c */
    protected long f528c;

    /* renamed from: d */
    protected String f529d = "";

    /* renamed from: a */
    public final void mo6630a(long j) {
        this.f526a = 3600;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo6631a(Context context, String str, JSONObject jSONObject) {
        if (jSONObject != null) {
            C0460q.m714a(context, C0460q.m707a(context, jSONObject, str));
        }
    }

    /* renamed from: a */
    public final void mo6632a(Context context, boolean z) {
        if (context == null || !mo6634a()) {
            return;
        }
        if (z || mo6635a(context)) {
            mo6637b(context);
            mo6640d(context);
        }
    }

    /* renamed from: a */
    public final void mo6633a(String str) {
        this.f527b = str;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo6634a() {
        return true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract boolean mo6635a(Context context);

    /* renamed from: b */
    public final void mo6636b(long j) {
        this.f528c = j;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract void mo6637b(Context context);

    /* renamed from: b */
    public final void mo6638b(String str) {
        this.f529d = str;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public abstract ArrayList<C0444a> mo6639c(Context context);

    /* renamed from: d */
    public abstract void mo6640d(Context context);
}
