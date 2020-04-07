package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.tencent.bugly.proguard.aq */
/* compiled from: BUGLY */
public final class C1984aq extends C1994j implements Cloneable {

    /* renamed from: f */
    private static ArrayList<C1983ap> f1558f;

    /* renamed from: g */
    private static Map<String, String> f1559g;

    /* renamed from: a */
    public byte f1560a = 0;

    /* renamed from: b */
    public String f1561b = "";

    /* renamed from: c */
    public String f1562c = "";

    /* renamed from: d */
    public ArrayList<C1983ap> f1563d = null;

    /* renamed from: e */
    public Map<String, String> f1564e = null;

    /* renamed from: a */
    public final void mo19550a(C1993i iVar) {
        iVar.mo19576a(this.f1560a, 0);
        if (this.f1561b != null) {
            iVar.mo19581a(this.f1561b, 1);
        }
        if (this.f1562c != null) {
            iVar.mo19581a(this.f1562c, 2);
        }
        if (this.f1563d != null) {
            iVar.mo19582a((Collection<T>) this.f1563d, 3);
        }
        if (this.f1564e != null) {
            iVar.mo19583a(this.f1564e, 4);
        }
    }

    /* renamed from: a */
    public final void mo19549a(C1991h hVar) {
        this.f1560a = hVar.mo19562a(this.f1560a, 0, true);
        this.f1561b = hVar.mo19572b(1, false);
        this.f1562c = hVar.mo19572b(2, false);
        if (f1558f == null) {
            f1558f = new ArrayList<>();
            f1558f.add(new C1983ap());
        }
        this.f1563d = (ArrayList) hVar.mo19567a(f1558f, 3, false);
        if (f1559g == null) {
            f1559g = new HashMap();
            f1559g.put("", "");
        }
        this.f1564e = (Map) hVar.mo19567a(f1559g, 4, false);
    }

    /* renamed from: a */
    public final void mo19551a(StringBuilder sb, int i) {
    }
}
