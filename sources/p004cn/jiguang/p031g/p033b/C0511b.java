package p004cn.jiguang.p031g.p033b;

import android.content.ContentValues;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: cn.jiguang.g.b.b */
public final class C0511b {

    /* renamed from: a */
    private static final Map<String, C0511b> f572a = new ConcurrentHashMap();

    /* renamed from: d */
    private static final Object f573d = new Object();

    /* renamed from: b */
    private final HashMap<String, Serializable> f574b = new HashMap<>();

    /* renamed from: c */
    private final String f575c;

    private C0511b(String str) {
        this.f575c = str;
    }

    /* renamed from: a */
    public static C0511b m1017a(String str) {
        C0511b bVar = (C0511b) f572a.get(str);
        if (bVar == null) {
            synchronized (f573d) {
                bVar = (C0511b) f572a.get(str);
                if (bVar == null) {
                    bVar = new C0511b(str);
                    f572a.put(str, bVar);
                }
            }
        }
        return bVar;
    }

    /* renamed from: a */
    public final Serializable mo6682a(String str, int i) {
        Serializable serializable = (Serializable) this.f574b.get(str);
        if (C0510a.m1007a(serializable) == i) {
            return serializable;
        }
        return null;
    }

    /* renamed from: a */
    public final void mo6683a() {
        this.f574b.clear();
    }

    /* renamed from: a */
    public final void mo6684a(ContentValues contentValues) {
        for (Entry entry : contentValues.valueSet()) {
            mo6685a((String) entry.getKey(), entry.getValue());
        }
    }

    /* renamed from: a */
    public final void mo6685a(String str, Object obj) {
        if (obj == null) {
            this.f574b.remove(str);
        } else if (obj instanceof Serializable) {
            Serializable serializable = (Serializable) obj;
            boolean z = this.f574b.size() > 512 ? false : serializable instanceof String ? ((String) serializable).length() < 2048 : !(serializable instanceof HashSet);
            if (z) {
                this.f574b.put(str, (Serializable) obj);
            }
        }
    }
}
