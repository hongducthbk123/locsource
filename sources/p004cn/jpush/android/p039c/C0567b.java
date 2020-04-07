package p004cn.jpush.android.p039c;

import android.content.Context;
import p004cn.jpush.android.p040d.C0577a;
import p004cn.jpush.android.p040d.C0582e;

/* renamed from: cn.jpush.android.c.b */
public final class C0567b extends C0570e {

    /* renamed from: c */
    private C0565a f745c;

    protected C0567b(Context context) {
        if (context == null) {
            C0582e.m1306c("PluginHuaweiPlatformAction", "context was null");
        }
        this.f745c = new C0565a(context, null);
        this.f748a = "huawei_appid";
        Object e = C0577a.m1288e(context, "com.huawei.hms.client.appid");
        if (e != null) {
            this.f749b = e.toString();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo6833a(Context context) {
        try {
            this.f745c.f742a.connect();
        } catch (Throwable th) {
            C0582e.m1308d("PluginHuaweiPlatformAction", "register e:" + th);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo6834b(Context context) {
        try {
            this.f745c.f742a.connect();
        } catch (Throwable th) {
        }
    }
}
