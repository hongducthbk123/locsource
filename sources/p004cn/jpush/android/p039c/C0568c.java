package p004cn.jpush.android.p039c;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import p004cn.jpush.android.api.JPushInterface;
import p004cn.jpush.android.p040d.C0582e;

/* renamed from: cn.jpush.android.c.c */
public final class C0568c {

    /* renamed from: a */
    protected Activity f746a;

    /* renamed from: b */
    C0565a f747b;

    public C0568c(Context context) {
        if (context == null) {
            C0582e.m1306c("PluginHuaweiPushInterface", "context was null");
        } else if (C0575i.m1255a(context) == 2) {
            this.f747b = new C0565a(context, this);
        }
    }

    /* renamed from: a */
    public final void mo6835a(Activity activity) {
        if (activity == null) {
            C0582e.m1306c("PluginHuaweiPushInterface", "activity was null");
            return;
        }
        try {
            if (this.f747b.f742a != null) {
                this.f746a = activity;
                if (!JPushInterface.isPushStopped(activity.getApplicationContext())) {
                    this.f747b.f742a.connect();
                }
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: b */
    public final void mo6837b(Activity activity) {
        if (activity == null) {
            C0582e.m1306c("PluginHuaweiPushInterface", "activity was null");
        }
        this.f746a = null;
    }

    /* renamed from: a */
    public final void mo6836a(Context context, int i, Intent intent) {
        int i2;
        if (i != 10001) {
            return;
        }
        if (context != null) {
            if (intent != null) {
                try {
                    i2 = intent.getIntExtra("intent.extra.RESULT", 0);
                } catch (Throwable th) {
                    C0582e.m1306c("PluginHuaweiPushInterface", "onActivityResult error:" + th);
                    return;
                }
            } else {
                i2 = -1;
            }
            C0582e.m1304b("PluginHuaweiPushInterface", "onActivityResult,intent.extra.RESULT value" + i2);
            if (i2 != 0) {
                C0573g.m1238a().mo6847a(context, (String) null);
                if (i2 == 13) {
                    C0582e.m1304b("PluginHuaweiPushInterface", "user cancled");
                } else if (i2 == 8) {
                    C0582e.m1304b("PluginHuaweiPushInterface", "huawei sdk internal error");
                } else {
                    C0582e.m1304b("PluginHuaweiPushInterface", "unknow error:" + i2);
                }
            } else if (this.f747b.f742a == null || this.f747b.f742a.isConnecting() || this.f747b.f742a.isConnected()) {
                C0582e.m1304b("PluginHuaweiPushInterface", "onActivityResult call connect failed huaweiApiClient:" + this.f747b.f742a);
            } else {
                this.f747b.f742a.connect();
            }
        } else {
            C0582e.m1306c("PluginHuaweiPushInterface", "onActivityResult activity was null");
        }
    }
}
