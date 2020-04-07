package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.Collection;

/* renamed from: com.tencent.bugly.proguard.ah */
/* compiled from: BUGLY */
public final class C1975ah extends C1994j implements Cloneable {

    /* renamed from: c */
    private static ArrayList<String> f1458c;

    /* renamed from: a */
    private String f1459a = "";

    /* renamed from: b */
    private ArrayList<String> f1460b = null;

    /* renamed from: a */
    public final void mo19550a(C1993i iVar) {
        iVar.mo19581a(this.f1459a, 0);
        if (this.f1460b != null) {
            iVar.mo19582a((Collection<T>) this.f1460b, 1);
        }
    }

    /* renamed from: a */
    public final void mo19549a(C1991h hVar) {
        this.f1459a = hVar.mo19572b(0, true);
        if (f1458c == null) {
            f1458c = new ArrayList<>();
            f1458c.add("");
        }
        this.f1460b = (ArrayList) hVar.mo19567a(f1458c, 1, false);
    }

    /* renamed from: a */
    public final void mo19551a(StringBuilder sb, int i) {
    }
}
