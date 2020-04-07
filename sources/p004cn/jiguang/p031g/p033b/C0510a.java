package p004cn.jiguang.p031g.p033b;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

/* renamed from: cn.jiguang.g.b.a */
public final class C0510a {

    /* renamed from: a */
    private HashMap<String, Serializable> f571a = new HashMap<>(5);

    /* renamed from: a */
    public static int m1007a(Serializable serializable) {
        if (serializable == null) {
            return 0;
        }
        if (serializable instanceof String) {
            return 1;
        }
        if (serializable instanceof Integer) {
            return 2;
        }
        if (serializable instanceof Long) {
            return 4;
        }
        if (serializable instanceof Boolean) {
            return 3;
        }
        if (serializable instanceof Float) {
            return 5;
        }
        return serializable instanceof HashSet ? 6 : 0;
    }

    /* renamed from: a */
    public static HashSet<String> m1008a(SharedPreferences sharedPreferences, String str) {
        if (VERSION.SDK_INT >= 11) {
            Set<String> stringSet = sharedPreferences.getStringSet(str, null);
            if (stringSet != null) {
                if (stringSet instanceof HashSet) {
                    return (HashSet) stringSet;
                }
                HashSet hashSet = new HashSet();
                for (String add : stringSet) {
                    hashSet.add(add);
                }
                return hashSet;
            }
        }
        return null;
    }

    /* renamed from: a */
    public final C0510a mo6673a(String str, int i) {
        this.f571a.put(str, Integer.valueOf(i));
        return this;
    }

    /* renamed from: a */
    public final C0510a mo6674a(String str, long j) {
        this.f571a.put(str, Long.valueOf(j));
        return this;
    }

    /* renamed from: a */
    public final C0510a mo6675a(String str, Serializable serializable) {
        this.f571a.put(str, serializable);
        return this;
    }

    /* renamed from: a */
    public final C0510a mo6676a(String str, String str2) {
        this.f571a.put(str, str2);
        return this;
    }

    /* renamed from: a */
    public final Set<Entry<String, Serializable>> mo6677a() {
        return this.f571a.entrySet();
    }

    /* renamed from: a */
    public final boolean mo6678a(SharedPreferences sharedPreferences, boolean z) {
        if (sharedPreferences == null) {
            return false;
        }
        Editor edit = sharedPreferences.edit();
        for (Entry entry : this.f571a.entrySet()) {
            String str = (String) entry.getKey();
            Serializable serializable = (Serializable) entry.getValue();
            if (serializable == null) {
                edit.remove(str);
            } else if (serializable instanceof String) {
                edit.putString(str, (String) serializable);
            } else if (serializable instanceof Integer) {
                edit.putInt(str, ((Integer) serializable).intValue());
            } else if (serializable instanceof Boolean) {
                edit.putBoolean(str, ((Boolean) serializable).booleanValue());
            } else if (serializable instanceof Long) {
                edit.putLong(str, ((Long) serializable).longValue());
            } else if (serializable instanceof Float) {
                edit.putFloat(str, ((Float) serializable).floatValue());
            } else if ((serializable instanceof HashSet) && VERSION.SDK_INT >= 11) {
                try {
                    edit.putStringSet(str, (HashSet) serializable);
                } catch (ClassCastException e) {
                }
            }
        }
        edit.apply();
        return true;
    }

    /* renamed from: b */
    public final int mo6679b() {
        return this.f571a.size();
    }

    /* renamed from: b */
    public final <T extends Serializable> T mo6680b(String str, T t) {
        try {
            Serializable serializable = (Serializable) this.f571a.get(str);
            return serializable == null ? t : serializable;
        } catch (Throwable th) {
            return t;
        }
    }

    public final String toString() {
        return "values=" + this.f571a;
    }
}
