package p004cn.jiguang.p015d.p028h;

import android.content.Context;
import java.util.concurrent.atomic.AtomicBoolean;
import p004cn.jiguang.api.JCoreInterface;
import p004cn.jiguang.api.SdkType;
import p004cn.jiguang.p015d.C0385a;
import p004cn.jiguang.p015d.p016a.C0389d;
import p004cn.jiguang.p015d.p021d.C0445b;
import p004cn.jpush.android.service.DownloadProvider;

/* renamed from: cn.jiguang.d.h.f */
public class C0495f {

    /* renamed from: d */
    private static C0495f f540d;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public C0494e f541a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public C0497h f542b;

    /* renamed from: c */
    private AtomicBoolean f543c;

    private C0495f() {
        this.f541a = null;
        this.f542b = null;
        this.f543c = new AtomicBoolean(false);
        this.f541a = new C0494e();
        this.f542b = new C0497h();
    }

    /* renamed from: a */
    public static C0495f m858a() {
        if (f540d == null) {
            synchronized (C0495f.class) {
                if (f540d == null) {
                    f540d = new C0495f();
                }
            }
        }
        return f540d;
    }

    /* renamed from: a */
    public final void mo6653a(Context context) {
        if (!this.f543c.get()) {
            String i = C0389d.m338i(context);
            if (i == null) {
                i = "";
            }
            this.f541a.mo6651c(JCoreInterface.getDaemonAction());
            this.f541a.mo6652d("cn.jpush.android.service.PushService");
            this.f541a.mo6649a(DownloadProvider.class);
            this.f541a.mo6630a(3600);
            this.f541a.mo6636b(C0389d.m331d(context));
            this.f541a.mo6633a(i);
            this.f542b.mo6630a(3600);
            C0497h hVar = this.f542b;
            C0445b.m618a();
            hVar.mo6638b(C0445b.m629d(SdkType.JPUSH.name(), ""));
            this.f542b.mo6633a(i);
            this.f542b.mo6660c("http://" + C0385a.f199f.mo6351g() + "/v1/push/sdk/postlist");
            this.f543c.set(true);
        }
    }

    /* renamed from: a */
    public final void mo6654a(Context context, boolean z) {
        try {
            new Thread(new C0496g(this, context, z)).start();
        } catch (Throwable th) {
        }
    }

    /* renamed from: b */
    public final C0494e mo6655b() {
        return this.f541a;
    }

    /* renamed from: c */
    public final C0497h mo6656c() {
        return this.f542b;
    }
}
