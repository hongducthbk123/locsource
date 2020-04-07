package p004cn.jiguang.p015d.p021d;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import p004cn.jiguang.p005a.p012c.C0377c;
import p004cn.jiguang.p015d.p016a.C0389d;
import p004cn.jiguang.p015d.p017b.C0395a;

/* renamed from: cn.jiguang.d.d.h */
final class C0451h extends Handler {

    /* renamed from: a */
    final /* synthetic */ C0450g f413a;

    C0451h(C0450g gVar, Looper looper) {
        this.f413a = gVar;
        super(looper);
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 8000:
                C0450g gVar = this.f413a;
                Context a = this.f413a.f412c;
                if (a != null) {
                    C0377c.m187c(a);
                    if (C0448e.m641a().mo6564f() && !C0389d.m339j(a) && !C0395a.m388c()) {
                        C0450g.m661b(a);
                    }
                }
                this.f413a.f412c.getApplicationContext();
                C0450g.m660b();
                return;
            default:
                return;
        }
    }
}
