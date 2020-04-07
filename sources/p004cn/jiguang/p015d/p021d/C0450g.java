package p004cn.jiguang.p015d.p021d;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import p004cn.jiguang.p015d.p016a.C0386a;
import p004cn.jiguang.p029e.C0501d;

/* renamed from: cn.jiguang.d.d.g */
public final class C0450g {

    /* renamed from: a */
    private static boolean f409a = false;

    /* renamed from: b */
    private static Handler f410b;

    /* renamed from: d */
    private static C0450g f411d;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public Context f412c;

    /* renamed from: a */
    public static C0450g m659a() {
        if (f411d == null) {
            f411d = new C0450g();
        }
        return f411d;
    }

    /* renamed from: b */
    public static void m660b() {
        if (f410b != null && !f410b.hasMessages(8000)) {
            f410b.sendEmptyMessageDelayed(8000, (long) (C0386a.m279i() * 1000));
        }
    }

    /* renamed from: b */
    public static void m661b(Context context) {
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("rtc_delay", 0);
            bundle.putString("rtc", "rtc");
            C0453j.m665a().mo6568b(context, "intent.RTC", bundle);
        } catch (Throwable th) {
            C0501d.m907c("HeartBeatHelper", "sendHeartBeat error:" + th.getMessage());
        }
    }

    /* renamed from: c */
    public static void m662c() {
        f409a = false;
        try {
            if (f410b != null) {
                f410b.removeCallbacksAndMessages(null);
                f410b.getLooper().quit();
            }
            f410b = null;
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public final void mo6565a(Context context) {
        if (!f409a) {
            if (context == null) {
                C0501d.m907c("HeartBeatHelper", "init failed,context is null ");
                return;
            }
            f409a = true;
            this.f412c = context;
            try {
                HandlerThread handlerThread = new HandlerThread("JHeartBeatHelper");
                handlerThread.start();
                f410b = new C0451h(this, handlerThread.getLooper());
                m660b();
            } catch (Throwable th) {
            }
        }
    }
}
