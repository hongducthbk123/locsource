package p004cn.jiguang.p015d.p017b.p018a.p019a;

import android.text.TextUtils;
import java.net.InetAddress;
import java.util.Collections;
import java.util.Iterator;
import p004cn.jiguang.p015d.C0385a;
import p004cn.jiguang.p015d.p017b.p018a.C0396a;
import p004cn.jiguang.p015d.p017b.p018a.C0411d;
import p004cn.jiguang.p015d.p021d.C0446c;

/* renamed from: cn.jiguang.d.b.a.a.b */
public final class C0398b extends C0406j {
    public C0398b(C0411d dVar) {
        super(dVar);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final int mo6402a() {
        String str = "DefaultConnPolicy";
        InetAddress b = C0446c.m639b(C0385a.f199f.mo6347c());
        if (b == null) {
            return -1;
        }
        String hostAddress = b.getHostAddress();
        if (TextUtils.isEmpty(hostAddress)) {
            return -1;
        }
        C0399c cVar = new C0399c(this);
        cVar.add(Integer.valueOf(7000));
        cVar.add(Integer.valueOf(7002));
        cVar.add(Integer.valueOf(7003));
        cVar.add(Integer.valueOf(7004));
        cVar.add(Integer.valueOf(7005));
        cVar.add(Integer.valueOf(7006));
        cVar.add(Integer.valueOf(7007));
        cVar.add(Integer.valueOf(7008));
        cVar.add(Integer.valueOf(7009));
        try {
            Collections.shuffle(cVar);
        } catch (Throwable th) {
        }
        C0396a aVar = new C0396a();
        Iterator it = cVar.iterator();
        while (it.hasNext()) {
            aVar.mo6395a(hostAddress, ((Integer) it.next()).intValue(), str);
        }
        return mo6407b(aVar);
    }
}
