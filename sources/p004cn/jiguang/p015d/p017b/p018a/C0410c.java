package p004cn.jiguang.p015d.p017b.p018a;

import android.text.TextUtils;
import java.io.Serializable;

/* renamed from: cn.jiguang.d.b.a.c */
public final class C0410c implements Serializable {

    /* renamed from: a */
    public String f253a;

    /* renamed from: b */
    public final int f254b;

    public C0410c(String str, int i) {
        this.f253a = str;
        this.f254b = i;
    }

    /* renamed from: a */
    public static C0410c m413a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(":");
        if (split.length != 2) {
            return null;
        }
        try {
            return new C0410c(split[0], Integer.decode(split[1]).intValue());
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m414a(String str, int i) {
        return !TextUtils.isEmpty(str) && i > 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C0410c cVar = (C0410c) obj;
        if (this.f254b == cVar.f254b) {
            if (this.f253a != null) {
                if (this.f253a.equals(cVar.f253a)) {
                    return true;
                }
            } else if (cVar.f253a == null) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((this.f253a != null ? this.f253a.hashCode() : 0) * 31) + this.f254b;
    }

    public final String toString() {
        return this.f253a + ":" + this.f254b;
    }
}
