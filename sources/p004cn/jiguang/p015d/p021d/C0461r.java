package p004cn.jiguang.p015d.p021d;

import android.content.Context;
import com.adjust.sdk.Constants;
import org.json.JSONArray;

/* renamed from: cn.jiguang.d.d.r */
final class C0461r implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Context f441a;

    /* renamed from: b */
    final /* synthetic */ JSONArray f442b;

    /* renamed from: c */
    final /* synthetic */ boolean f443c;

    /* renamed from: d */
    final /* synthetic */ boolean f444d;

    C0461r(Context context, JSONArray jSONArray, boolean z, boolean z2) {
        this.f441a = context;
        this.f442b = jSONArray;
        this.f443c = z;
        this.f444d = z2;
    }

    public final void run() {
        C0460q.m712a(this.f441a, this.f442b, this.f443c ? "prior" : Constants.NORMAL, this.f444d);
    }
}
