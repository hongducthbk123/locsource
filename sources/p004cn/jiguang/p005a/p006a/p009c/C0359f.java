package p004cn.jiguang.p005a.p006a.p009c;

import org.json.JSONObject;
import p004cn.jiguang.api.C0378a;
import p004cn.jiguang.p015d.p021d.C0460q;

/* renamed from: cn.jiguang.a.a.c.f */
final class C0359f extends Thread implements C0378a {

    /* renamed from: a */
    final /* synthetic */ C0358e f120a;

    C0359f(C0358e eVar) {
        this.f120a = eVar;
    }

    /* renamed from: a */
    public final void mo6202a(int i) {
        if (i == 0) {
            C0358e.m115d(this.f120a.f119e);
        }
    }

    public final void run() {
        if (this.f120a.f119e != null) {
            JSONObject e = C0358e.m116e(this.f120a.f119e);
            if (e != null) {
                C0460q.m715a(this.f120a.f119e, e, (C0378a) this);
            }
        }
    }
}
