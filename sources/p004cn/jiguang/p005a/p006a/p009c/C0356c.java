package p004cn.jiguang.p005a.p006a.p009c;

import android.content.Context;
import p004cn.jiguang.api.C0378a;
import p004cn.jiguang.p015d.p016a.C0389d;

/* renamed from: cn.jiguang.a.a.c.c */
final class C0356c implements C0378a {

    /* renamed from: a */
    final /* synthetic */ Context f113a;

    /* renamed from: b */
    final /* synthetic */ String f114b;

    C0356c(Context context, String str) {
        this.f113a = context;
        this.f114b = str;
    }

    /* renamed from: a */
    public final void mo6202a(int i) {
        if (i == 0) {
            C0355b.m102b(this.f113a, this.f114b);
            C0389d.m313a(this.f113a, "last_report_device_info", Long.valueOf(System.currentTimeMillis()));
        }
    }
}
