package p004cn.jpush.android.p037a;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import p004cn.jpush.android.api.JPushMessage;
import p004cn.jpush.android.service.C0607d;
import p004cn.jpush.android.service.JPushMessageReceiver;

/* renamed from: cn.jpush.android.a.a */
public class C0542a {

    /* renamed from: a */
    private static C0542a f656a;

    /* renamed from: b */
    private Handler f657b;

    /* renamed from: cn.jpush.android.a.a$a */
    private class C0543a implements Runnable {

        /* renamed from: b */
        private Context f659b;

        /* renamed from: c */
        private JPushMessageReceiver f660c;

        /* renamed from: d */
        private Intent f661d;

        public C0543a(Context context, JPushMessageReceiver jPushMessageReceiver, Intent intent) {
            this.f659b = context;
            this.f660c = jPushMessageReceiver;
            this.f661d = intent;
        }

        public final void run() {
            if (this.f661d != null) {
                if ("cn.jpush.android.intent.RECEIVE_MESSAGE".equals(this.f661d.getAction())) {
                    int intExtra = this.f661d.getIntExtra("message_type", -1);
                    JPushMessage jPushMessage = null;
                    if (1 == intExtra || 2 == intExtra) {
                        jPushMessage = C0607d.m1397a().mo6997a(this.f661d);
                    } else if (3 == intExtra) {
                        C0548f.m1129a();
                        jPushMessage = C0548f.m1130a(this.f661d);
                    }
                    if (jPushMessage == null) {
                        return;
                    }
                    if (intExtra == 1) {
                        if (jPushMessage.isTagCheckOperator()) {
                            this.f660c.onCheckTagOperatorResult(this.f659b, jPushMessage);
                        } else {
                            this.f660c.onTagOperatorResult(this.f659b, jPushMessage);
                        }
                    } else if (intExtra == 2) {
                        this.f660c.onAliasOperatorResult(this.f659b, jPushMessage);
                    } else if (intExtra == 3) {
                        this.f660c.onMobileNumberOperatorResult(this.f659b, jPushMessage);
                    }
                }
            }
        }
    }

    private C0542a() {
        try {
            HandlerThread handlerThread = new HandlerThread("MessageReceiver");
            handlerThread.start();
            this.f657b = new Handler(handlerThread.getLooper());
        } catch (Throwable th) {
            this.f657b = new Handler();
        }
    }

    /* renamed from: a */
    public static C0542a m1122a() {
        if (f656a == null) {
            synchronized (C0542a.class) {
                if (f656a == null) {
                    f656a = new C0542a();
                }
            }
        }
        return f656a;
    }

    /* renamed from: a */
    public final void mo6774a(Context context, JPushMessageReceiver jPushMessageReceiver, Intent intent) {
        this.f657b.post(new C0543a(context, jPushMessageReceiver, intent));
    }
}
