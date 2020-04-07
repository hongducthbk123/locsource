package p004cn.jiguang.p015d.p017b.p018a.p019a;

import android.content.Context;
import java.util.Iterator;
import java.util.LinkedList;
import p004cn.jiguang.p015d.p017b.C0419f;
import p004cn.jiguang.p015d.p017b.p018a.C0411d;

/* renamed from: cn.jiguang.d.b.a.a.f */
public final class C0402f {

    /* renamed from: a */
    private final LinkedList<C0406j> f246a = new LinkedList<>();
    /* access modifiers changed from: private */

    /* renamed from: b */
    public final C0411d f247b;

    public C0402f(Context context, C0419f fVar, long j) {
        this.f247b = new C0411d(context, fVar, j);
        this.f246a.add(new C0405i(this.f247b, true));
        this.f246a.add(new C0397a(this.f247b));
        this.f246a.add(new C0408l(this.f247b));
        this.f246a.add(new C0400d(this.f247b));
        this.f246a.add(new C0401e(this.f247b));
        this.f246a.add(new C0404h(this.f247b));
        this.f246a.add(new C0407k(this.f247b));
        this.f246a.add(new C0398b(this.f247b));
        this.f246a.add(new C0405i(this.f247b, false));
    }

    /* renamed from: a */
    public final void mo6403a() {
        try {
            new Thread(new C0403g(this)).start();
        } catch (Throwable th) {
        }
    }

    /* renamed from: b */
    public final int mo6404b() {
        Iterator it = this.f246a.iterator();
        while (it.hasNext()) {
            try {
                switch (((C0406j) it.next()).mo6402a()) {
                    case 0:
                        return 0;
                    case 2:
                        return 2;
                }
            } catch (Throwable th) {
            }
        }
        return -1;
    }
}
