package p004cn.jiguang.p015d.p017b.p018a.p019a;

import android.text.TextUtils;
import p004cn.jiguang.p015d.p016a.C0386a;
import p004cn.jiguang.p015d.p017b.p018a.C0396a;
import p004cn.jiguang.p015d.p017b.p018a.C0411d;

/* renamed from: cn.jiguang.d.b.a.a.h */
public final class C0404h extends C0406j {
    public C0404h(C0411d dVar) {
        super(dVar);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final int mo6402a() {
        String str = "LastGoodConnPolicy";
        String d = C0386a.m266d();
        if (TextUtils.isEmpty(d)) {
            return -1;
        }
        C0396a aVar = new C0396a();
        aVar.mo6395a(d, C0386a.m269e(), str);
        return mo6407b(aVar);
    }
}
