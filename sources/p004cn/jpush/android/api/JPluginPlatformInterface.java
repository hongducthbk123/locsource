package p004cn.jpush.android.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import p004cn.jpush.android.p039c.C0568c;
import p004cn.jpush.android.p039c.C0573g;
import p004cn.jpush.android.p040d.C0582e;

/* renamed from: cn.jpush.android.api.JPluginPlatformInterface */
public class JPluginPlatformInterface {
    public static final int JPLUGIN_REQUEST_CODE = 10001;

    /* renamed from: a */
    private C0568c f692a;

    public JPluginPlatformInterface(Context context) {
        try {
            if (C0573g.m1238a().mo6853f(context) == 2) {
                this.f692a = new C0568c(context);
            }
        } catch (Throwable th) {
            C0582e.m1306c("JPluginPlatformInterface", "new JPluginPlatformInterface failed:" + th);
        }
    }

    public void onStart(Activity activity) {
        if (this.f692a != null) {
            this.f692a.mo6835a(activity);
        }
    }

    public void onStop(Activity activity) {
        if (this.f692a != null) {
            this.f692a.mo6837b(activity);
        }
    }

    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        if (this.f692a != null) {
            this.f692a.mo6836a(activity, i, intent);
        }
    }
}
