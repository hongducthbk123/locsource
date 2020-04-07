package p004cn.jiguang.p031g.p033b;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import p004cn.jiguang.p015d.C0385a;
import p004cn.jiguang.p031g.C0506a;

/* renamed from: cn.jiguang.g.b.e */
public final class C0514e {

    /* renamed from: a */
    public static Boolean f576a;

    /* renamed from: f */
    private static Map<String, C0514e> f577f = new HashMap();

    /* renamed from: g */
    private static final Object f578g = new Object();

    /* renamed from: b */
    private final String f579b;

    /* renamed from: c */
    private String f580c;

    /* renamed from: d */
    private SharedPreferences f581d;

    /* renamed from: e */
    private ContentResolver f582e;

    private C0514e(Context context, String str) {
        this.f579b = str;
        if (f576a == null) {
            f576a = Boolean.valueOf(C0506a.m952b(context));
        }
        Context context2 = context != null ? context.getApplicationContext() : C0385a.f198e;
        if (context2 != null) {
            this.f580c = "content://" + context2.getPackageName() + ".DataProvider/" + str;
            this.f581d = context2.getSharedPreferences(str, 0);
            this.f582e = context2.getContentResolver();
        }
    }

    /* renamed from: a */
    public static C0514e m1032a(Context context, String str) {
        C0514e eVar;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        C0514e eVar2 = (C0514e) f577f.get(str);
        if (eVar2 != null) {
            return eVar2;
        }
        synchronized (f578g) {
            eVar = (C0514e) f577f.get(str);
            if (eVar == null) {
                eVar = new C0514e(context, str);
                f577f.put(str, eVar);
            }
        }
        return eVar;
    }

    /* renamed from: b */
    private String m1033b() {
        if (this.f580c == null && C0385a.f198e != null) {
            this.f580c = "content://" + C0385a.f198e.getPackageName() + ".DataProvider/" + this.f579b;
        }
        return this.f580c;
    }

    /* renamed from: c */
    private SharedPreferences m1034c() {
        if (this.f581d == null && C0385a.f198e != null) {
            this.f581d = C0385a.f198e.getSharedPreferences(this.f579b, 0);
        }
        return this.f581d;
    }

    /* renamed from: c */
    private C0510a m1035c(C0510a aVar) {
        if (aVar == null || aVar.mo6679b() == 0) {
            return new C0510a();
        }
        SharedPreferences c = m1034c();
        if (c == null) {
            return aVar;
        }
        C0510a aVar2 = new C0510a();
        if (aVar != null) {
            for (Entry entry : aVar.mo6677a()) {
                String str = (String) entry.getKey();
                Serializable a = C0512c.m1022a(c, str, C0510a.m1007a((Serializable) entry.getValue()));
                if (a != null) {
                    aVar2.mo6675a(str, a);
                }
            }
        }
        return aVar2;
    }

    /* renamed from: c */
    private <T extends Serializable> T m1036c(String str, T t) {
        SharedPreferences c = m1034c();
        return c != null ? C0512c.m1023a(c, str, t) : t;
    }

    /* renamed from: d */
    private ContentResolver m1037d() {
        if (this.f582e == null && C0385a.f198e != null) {
            this.f582e = C0385a.f198e.getContentResolver();
        }
        return this.f582e;
    }

    /* renamed from: d */
    private boolean m1038d(C0510a aVar) {
        SharedPreferences c = m1034c();
        if (c != null) {
            return C0512c.m1026a(c, aVar);
        }
        return false;
    }

    /* renamed from: d */
    private <T extends Serializable> boolean m1039d(String str, T t) {
        SharedPreferences c = m1034c();
        if (c != null) {
            return C0512c.m1027b(c, str, t);
        }
        return false;
    }

    /* renamed from: a */
    public final C0510a mo6686a(C0510a aVar) {
        if (aVar == null || aVar.mo6679b() == 0) {
            return new C0510a();
        }
        if (!f576a.booleanValue()) {
            return m1035c(aVar);
        }
        ContentResolver d = m1037d();
        String b = m1033b();
        if (d == null || b == null) {
            return m1035c(aVar);
        }
        try {
            return C0513d.m1028a(d, b, aVar);
        } catch (Throwable th) {
            return m1035c(aVar);
        }
    }

    /* renamed from: a */
    public final Serializable mo6687a(String str, int i) {
        SharedPreferences c = m1034c();
        if (c != null) {
            return C0512c.m1022a(c, str, i);
        }
        return null;
    }

    /* renamed from: a */
    public final <T extends Serializable> T mo6688a(String str, T t) {
        if (!f576a.booleanValue()) {
            return m1036c(str, t);
        }
        ContentResolver d = m1037d();
        String b = m1033b();
        if (d == null || b == null) {
            return m1036c(str, t);
        }
        try {
            String type = d.getType(Uri.parse(b).buildUpon().appendQueryParameter("key", str).appendQueryParameter("type", String.valueOf(C0510a.m1007a(t))).build());
            return type != null ? t == null ? type : t instanceof Integer ? Integer.valueOf(type) : t instanceof Boolean ? Boolean.valueOf(type) : t instanceof Long ? Long.valueOf(type) : t instanceof Float ? Float.valueOf(type) : t instanceof String ? type : t instanceof HashSet ? null : null : t;
        } catch (Throwable th) {
            return m1036c(str, t);
        }
    }

    /* renamed from: a */
    public final boolean mo6689a() {
        SharedPreferences c = m1034c();
        if (c != null) {
            return c.edit().clear().commit();
        }
        return false;
    }

    /* renamed from: a */
    public final boolean mo6690a(ContentValues contentValues) {
        SharedPreferences c = m1034c();
        if (c != null) {
            return C0512c.m1025a(c, contentValues);
        }
        return false;
    }

    /* renamed from: b */
    public final boolean mo6691b(C0510a aVar) {
        if (!f576a.booleanValue()) {
            return m1038d(aVar);
        }
        ContentResolver d = m1037d();
        String b = m1033b();
        if (d == null || b == null) {
            return m1038d(aVar);
        }
        try {
            return C0513d.m1031b(d, b, aVar);
        } catch (Throwable th) {
            return m1038d(aVar);
        }
    }

    /* renamed from: b */
    public final <T extends Serializable> boolean mo6692b(String str, T t) {
        if (!f576a.booleanValue()) {
            return m1039d(str, t);
        }
        ContentResolver d = m1037d();
        String b = m1033b();
        if (d == null || b == null) {
            return m1039d(str, t);
        }
        try {
            return C0513d.m1030a(d, b, str, t);
        } catch (Throwable th) {
            return m1039d(str, t);
        }
    }
}
