package p004cn.jiguang.p015d.p017b.p018a;

import android.text.TextUtils;
import com.google.android.vending.expansion.downloader.Constants;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import p004cn.jiguang.p015d.p017b.C0422i;
import p004cn.jiguang.p015d.p020c.C0439p;
import p004cn.jiguang.p015d.p021d.C0446c;

/* renamed from: cn.jiguang.d.b.a.a */
public final class C0396a {

    /* renamed from: a */
    private LinkedHashMap<C0410c, String> f244a = new LinkedHashMap<>();

    /* renamed from: a */
    public static C0396a m390a(C0422i iVar) {
        C0396a aVar = new C0396a();
        if (iVar == null) {
            return aVar;
        }
        for (String a : iVar.mo6477f()) {
            C0410c a2 = C0410c.m413a(a);
            if (a2 != null) {
                aVar.mo6395a(a2.f253a, a2.f254b, "default");
            }
        }
        return aVar;
    }

    /* renamed from: a */
    public static C0396a m391a(String str) {
        String[] split;
        int i;
        C0396a aVar = new C0396a();
        if (!TextUtils.isEmpty(str)) {
            for (String str2 : str.split(Constants.FILENAME_SEQUENCE_SEPARATOR)) {
                if (!TextUtils.isEmpty(str2)) {
                    String[] split2 = str2.split(":");
                    if (split2.length > 2) {
                        try {
                            i = Integer.decode(split2[1]).intValue();
                        } catch (Exception e) {
                            i = 0;
                        }
                        aVar.mo6395a(split2[0], i, split2[2]);
                    }
                }
            }
        }
        return aVar;
    }

    /* renamed from: a */
    public static C0396a m392a(List<C0439p> list, boolean z) {
        InetAddress inetAddress;
        C0396a aVar = new C0396a();
        if (list == null || list.isEmpty()) {
            return aVar;
        }
        for (C0439p pVar : list) {
            String jVar = pVar.mo6549i().toString();
            if (!TextUtils.isEmpty(jVar) && jVar.endsWith(".")) {
                jVar = jVar.substring(0, jVar.length() - 1);
            }
            if (z) {
                try {
                    inetAddress = C0446c.m639b(jVar);
                } catch (Exception e) {
                    inetAddress = null;
                }
                if (inetAddress != null) {
                    jVar = inetAddress.getHostAddress();
                }
            }
            aVar.mo6395a(jVar, pVar.mo6548h(), "srv record");
        }
        return aVar;
    }

    /* renamed from: b */
    public static C0396a m393b(C0422i iVar) {
        C0396a aVar = new C0396a();
        if (iVar == null) {
            return aVar;
        }
        aVar.mo6395a(iVar.mo6470a(), iVar.mo6472b(), "main");
        List c = iVar.mo6474c();
        List d = iVar.mo6475d();
        if (c == null || d == null) {
            return aVar;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < c.size() && i2 < d.size()) {
                aVar.mo6395a((String) c.get(i2), ((Integer) d.get(i2)).intValue(), "option" + i2);
                i = i2 + 1;
            }
        }
        return aVar;
    }

    /* renamed from: a */
    public final void mo6395a(String str, int i, String str2) {
        if (C0410c.m414a(str, i)) {
            this.f244a.put(new C0410c(str, i), str2);
        }
    }

    /* renamed from: a */
    public final boolean mo6396a() {
        return this.f244a == null || this.f244a.isEmpty();
    }

    /* renamed from: a */
    public final boolean mo6397a(C0410c cVar) {
        return this.f244a.containsKey(cVar);
    }

    /* renamed from: b */
    public final Iterator<Entry<C0410c, String>> mo6398b() {
        return this.f244a.entrySet().iterator();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C0396a aVar = (C0396a) obj;
        return this.f244a != null ? this.f244a.equals(aVar.f244a) : aVar.f244a == null;
    }

    public final int hashCode() {
        if (this.f244a != null) {
            return this.f244a.hashCode();
        }
        return 0;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.f244a != null) {
            for (Entry entry : this.f244a.entrySet()) {
                sb.append(((C0410c) entry.getKey()).toString()).append(":").append((String) entry.getValue()).append(Constants.FILENAME_SEQUENCE_SEPARATOR);
            }
            if (!this.f244a.isEmpty()) {
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return sb.toString();
    }
}
