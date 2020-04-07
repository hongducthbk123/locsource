package p004cn.jiguang.p015d.p028h;

import android.content.ComponentName;
import java.util.HashMap;

/* renamed from: cn.jiguang.d.h.d */
public final class C0493d {

    /* renamed from: a */
    private ComponentName f534a;

    /* renamed from: b */
    private HashMap<Integer, Boolean> f535b = new HashMap<>();

    /* renamed from: a */
    public final ComponentName mo6643a() {
        return this.f534a;
    }

    /* renamed from: a */
    public final void mo6644a(int i, boolean z) {
        this.f535b.put(Integer.valueOf(i), Boolean.valueOf(z));
    }

    /* renamed from: a */
    public final void mo6645a(ComponentName componentName) {
        this.f534a = componentName;
    }

    /* renamed from: b */
    public final HashMap<Integer, Boolean> mo6646b() {
        return this.f535b;
    }
}
