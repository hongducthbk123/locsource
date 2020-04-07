package p004cn.jiguang.p015d.p021d;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import p004cn.jiguang.api.JCoreInterface;
import p004cn.jiguang.p005a.p012c.C0377c;
import p004cn.jiguang.p015d.C0385a;
import p004cn.jiguang.p015d.p016a.C0389d;
import p004cn.jiguang.p015d.p017b.C0395a;
import p004cn.jiguang.p015d.p017b.C0417d;
import p004cn.jiguang.p015d.p017b.C0419f;
import p004cn.jiguang.p015d.p022e.p023a.p024a.C0467b;
import p004cn.jiguang.p015d.p025f.C0477d;
import p004cn.jiguang.p015d.p026g.C0488i;
import p004cn.jiguang.p029e.C0501d;
import p004cn.jiguang.p031g.C0506a;
import p004cn.jiguang.p031g.p032a.C0507a;
import p004cn.jpush.android.service.PushService;

/* renamed from: cn.jiguang.d.d.j */
public final class C0453j {

    /* renamed from: c */
    private static final Object f415c = new Object();

    /* renamed from: d */
    private static volatile C0453j f416d;

    /* renamed from: a */
    private boolean f417a;

    /* renamed from: b */
    private Context f418b;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public boolean f419e;

    /* renamed from: a */
    public static C0453j m665a() {
        if (f416d == null) {
            synchronized (f415c) {
                if (f416d == null) {
                    f416d = new C0453j();
                }
            }
        }
        return f416d;
    }

    /* renamed from: a */
    private void m666a(Context context) {
        if (!this.f417a) {
            if (context == null) {
                C0501d.m909d("JServiceCommandHelper", "init failed");
                return;
            }
            this.f418b = context.getApplicationContext();
            if (!C0506a.m988p(C0385a.f198e)) {
                C0395a.f243a = 0;
            }
            this.f419e = C0395a.m386b(this.f418b);
            this.f417a = true;
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m667a(C0453j jVar, String str, Bundle bundle) {
        try {
            C0417d.m446a().mo6437a(jVar.f418b);
            String str2 = m670b(jVar.f418b) + ".";
            if (str.startsWith(str2)) {
                str = str.substring(str2.length());
            }
            if (str.equals("intent.INIT")) {
                if (C0419f.f300a.get() == 0) {
                    C0417d.m446a().mo6449f();
                }
            } else if (str.equals("senddata.action")) {
                if (C0419f.f300a.get() == 0) {
                    C0417d.m446a().mo6449f();
                    return;
                }
                byte[] byteArray = bundle.getByteArray("datas");
                if (byteArray != null) {
                    byte[] a = C0467b.m735a(byteArray, 1);
                    bundle.getInt("cmd");
                    C0477d.m767a().mo6616b().mo6609a(a);
                }
            } else if ("intent.RTC".equals(str)) {
                C0417d.m446a().mo6445b(bundle);
            } else if ("intent.CONNECTIVITY_CHANGE".equals(str)) {
                C0417d.m446a().mo6438a(bundle);
                if (bundle.getBoolean("connection-state", false)) {
                    C0460q.m709a(jVar.f418b);
                }
            } else if (str.equals("run.action")) {
                String string = bundle.getString("sdktype");
                C0445b.m618a();
                C0445b.m622a(jVar.f418b, string, C0419f.f300a.get(), bundle, C0417d.m446a().mo6443b());
            } else if (str.equals("intent.STOPPUSH")) {
                String string2 = bundle.getString("sdktype");
                C0417d a2 = C0417d.m446a();
                C0389d.m338i(jVar.f418b);
                a2.mo6446b(string2, bundle);
            } else if (str.equals("intent.RESTOREPUSH")) {
                String string3 = bundle.getString("sdktype");
                C0417d a3 = C0417d.m446a();
                C0389d.m338i(jVar.f418b);
                a3.mo6440a(string3, bundle);
            } else if (str.equals("sendrequestdata.action")) {
                byte[] byteArray2 = bundle.getByteArray("datas");
                int i = bundle.getInt("timeout");
                String string4 = bundle.getString("sdktype");
                C0417d.m446a();
                C0417d.m450a(byteArray2, string4, i);
            } else if (str.equals("intent.power.save")) {
                C0417d.m446a().mo6442a(bundle.getBoolean("key_power_save"));
            } else if (str.equals("cn.jpush.android.intent.check.notification.state")) {
                C0377c.m186b(jVar.f418b, bundle.getInt("key_trigger_scene"));
            } else if (str.equals("report_history")) {
                C0460q.m709a(jVar.f418b);
            }
        } catch (Throwable th) {
            C0501d.m908c("JServiceCommandHelper", "handleAction failed", th);
        }
    }

    /* renamed from: a */
    private static boolean m669a(String str, Bundle bundle) {
        try {
            if (!C0507a.m1001c()) {
                return false;
            }
            C0507a.m1000b().mo6340a(str, bundle);
            return true;
        } catch (Throwable th) {
            C0501d.m907c("JServiceCommandHelper", "throwable , remote call failed, error:" + th);
            return false;
        }
    }

    /* renamed from: b */
    private static String m670b(Context context) {
        String str = C0385a.f196c;
        if (TextUtils.isEmpty(str) && context != null) {
            str = context.getPackageName();
        }
        return str == null ? "" : str;
    }

    /* renamed from: a */
    public final void mo6567a(Context context, String str, Bundle bundle) {
        try {
            m666a(context);
            if (this.f418b == null) {
                this.f418b = context.getApplicationContext();
            }
            C0488i.m817a().mo6629a((Runnable) new C0454k(this, context, str, bundle, 1));
        } catch (Throwable th) {
            C0501d.m908c("JServiceCommandHelper", "callAction failed", th);
        }
    }

    /* renamed from: b */
    public final void mo6568b(Context context, String str, Bundle bundle) {
        try {
            if (JCoreInterface.init(context, false)) {
                m666a(context);
                if (context != null) {
                    String str2 = m670b(context) + "." + str;
                    if (!C0395a.m388c()) {
                        C0488i.m817a().mo6629a((Runnable) new C0454k(this, context, str2, bundle, 0));
                    } else {
                        mo6569c(context, str2, bundle);
                    }
                }
            }
        } catch (Throwable th) {
            C0501d.m908c("JServiceCommandHelper", "onAction failed", th);
        }
    }

    /* renamed from: c */
    public final boolean mo6569c(Context context, String str, Bundle bundle) {
        if (!m669a(str, bundle)) {
            try {
                Intent intent = new Intent(context, PushService.class);
                intent.setAction(str);
                if (bundle != null) {
                    intent.putExtras(bundle);
                }
                context.startService(intent);
            } catch (Throwable th) {
                C0501d.m907c("JServiceCommandHelper", "throwable ,cant start service" + th.getMessage() + ", will use aidl to do action");
                return false;
            }
        }
        return true;
    }
}
