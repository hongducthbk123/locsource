package p004cn.jiguang.p005a.p011b;

import android.content.Context;
import android.content.SharedPreferences;

/* renamed from: cn.jiguang.a.b.c */
public class C0374c {

    /* renamed from: b */
    private static volatile C0374c f175b;

    /* renamed from: a */
    private SharedPreferences f176a = null;

    /* renamed from: a */
    private SharedPreferences m162a(Context context) {
        if (this.f176a == null) {
            this.f176a = context.getSharedPreferences("JPushSA_Config", 0);
        }
        return this.f176a;
    }

    /* renamed from: a */
    public static C0374c m163a() {
        if (f175b == null) {
            synchronized (C0374c.class) {
                if (f175b == null) {
                    f175b = new C0374c();
                }
            }
        }
        return f175b;
    }

    /* renamed from: a */
    public final long mo6258a(Context context, String str, long j) {
        return m162a(context).getLong(str, j);
    }

    /* renamed from: a */
    public final String mo6259a(Context context, String str, String str2) {
        return m162a(context).getString(str, null);
    }

    /* renamed from: b */
    public final void mo6260b(Context context, String str, long j) {
        m162a(context).edit().putLong(str, j).commit();
    }

    /* renamed from: b */
    public final void mo6261b(Context context, String str, String str2) {
        m162a(context).edit().putString(str, str2).commit();
    }
}
