package p004cn.jpush.android.p039c;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.MiPushClient;
import p004cn.jpush.android.p040d.C0582e;

/* renamed from: cn.jpush.android.c.j */
public final class C0576j extends C0570e {
    public C0576j(Context context) {
        this.f748a = C0575i.m1256a(context, "XIAOMI_APPKEY");
        this.f749b = C0575i.m1256a(context, "XIAOMI_APPID");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo6833a(Context context) {
        if (context != null) {
            try {
                if (TextUtils.isEmpty(this.f748a) || TextUtils.isEmpty(this.f749b)) {
                    C0582e.m1308d("PluginXiaomiPlatformAction", "xiaomi sdk appkey or appid was empty,please check your manifest config");
                } else {
                    MiPushClient.registerPush(context, this.f749b, this.f748a);
                }
            } catch (Throwable th) {
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public final void mo6838c(Context context) {
        if (context != null) {
            MiPushClient.disablePush(context);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo6834b(Context context) {
        if (context != null) {
            MiPushClient.enablePush(context);
        }
    }
}
