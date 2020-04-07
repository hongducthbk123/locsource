package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.Collection;

/* renamed from: com.tencent.bugly.proguard.ak */
/* compiled from: BUGLY */
public final class C1978ak extends C1994j implements Cloneable {

    /* renamed from: b */
    private static ArrayList<C1977aj> f1494b;

    /* renamed from: a */
    public ArrayList<C1977aj> f1495a = null;

    /* renamed from: a */
    public final void mo19550a(C1993i iVar) {
        iVar.mo19582a((Collection<T>) this.f1495a, 0);
    }

    /* renamed from: a */
    public final void mo19549a(C1991h hVar) {
        if (f1494b == null) {
            f1494b = new ArrayList<>();
            f1494b.add(new C1977aj());
        }
        this.f1495a = (ArrayList) hVar.mo19567a(f1494b, 0, true);
    }

    /* renamed from: a */
    public final void mo19551a(StringBuilder sb, int i) {
    }
}
