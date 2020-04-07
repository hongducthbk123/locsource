package p004cn.jpush.android.p039c;

import android.content.Context;
import android.text.TextUtils;
import com.meizu.cloud.pushsdk.PushManager;
import p004cn.jpush.android.p040d.C0582e;

/* renamed from: cn.jpush.android.c.d */
public final class C0569d extends C0570e {
    public C0569d(Context context) {
        if (context == null) {
            C0582e.m1306c("PluginMeizuPlateformAction", "context was null");
        }
        this.f748a = C0575i.m1256a(context, "MEIZU_APPKEY");
        this.f749b = C0575i.m1256a(context, "MEIZU_APPID");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo6833a(Context context) {
        if (context != null) {
            try {
                if (TextUtils.isEmpty(this.f748a) || TextUtils.isEmpty(this.f749b)) {
                    C0582e.m1308d("PluginMeizuPlateformAction", "meizu sdk appkey or appid was empty,please check your manifest config");
                } else {
                    PushManager.register(context, this.f749b, this.f748a);
                }
            } catch (Throwable th) {
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo6834b(Context context) {
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public final void mo6838c(Context context) {
    }
}
