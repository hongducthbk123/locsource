package p004cn.jiguang.p015d.p020c;

import android.support.p000v4.internal.view.SupportMenu;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p004cn.jiguang.p029e.C0501d;

/* renamed from: cn.jiguang.d.c.f */
public final class C0429f {
    /* renamed from: a */
    public static List<C0439p> m540a(String str) {
        C0435l[] a;
        ArrayList arrayList = new ArrayList();
        try {
            String[] a2 = C0438o.m603b().mo6547a();
            byte[] b = C0431h.m547a(C0436m.m583a(C0433j.m558a(C0433j.m559a(str), C0433j.f363a), 33, 1)).mo6512b(SupportMenu.USER_MASK);
            int length = a2.length;
            int i = 0;
            while (i < length) {
                String str2 = a2[i];
                try {
                    C0431h hVar = new C0431h(C0441r.m617a(null, new InetSocketAddress(InetAddress.getByName(str2), 53), b, System.currentTimeMillis() + 1000));
                    C0436m a3 = hVar.mo6510a();
                    if (a3 == null) {
                        break;
                    }
                    for (C0435l lVar : hVar.mo6511a(1)) {
                        if (lVar.mo6529b().mo6541e() == a3.mo6541e()) {
                            int d = lVar.mo6529b().mo6540d();
                            C0433j b2 = lVar.mo6529b().mo6537b();
                            if (d == a3.mo6538c() && b2.equals(a3.mo6537b())) {
                                Iterator a4 = lVar.mo6527a();
                                while (a4.hasNext()) {
                                    C0439p pVar = (C0439p) a4.next();
                                    if (pVar.mo6548h() > 0) {
                                        arrayList.add(pVar);
                                    }
                                }
                            }
                        }
                    }
                    i++;
                } catch (UnknownHostException e) {
                    C0501d.m907c("DNSSrvUtils", "Get default ports error at " + str2 + ", with UnknownHostException:" + e.getMessage());
                } catch (IOException e2) {
                    C0501d.m907c("DNSSrvUtils", "Get default ports error at " + str2 + ", with IOException:" + e2.getMessage());
                }
            }
        } catch (C0442s e3) {
            C0501d.m907c("DNSSrvUtils", "Get default ports error with TextParseException");
        } catch (C0434k e4) {
            C0501d.m907c("DNSSrvUtils", "Get default ports error with NameTooLongException");
        } catch (Exception e5) {
            C0501d.m907c("DNSSrvUtils", "Get default ports error with Exception");
        }
        return arrayList;
    }
}
