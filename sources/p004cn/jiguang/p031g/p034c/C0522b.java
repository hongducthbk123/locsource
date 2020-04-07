package p004cn.jiguang.p031g.p034c;

import android.content.Context;
import android.telephony.TelephonyManager;
import java.util.ArrayList;
import p004cn.jiguang.p031g.p034c.p035a.C0517a;
import p004cn.jiguang.p031g.p034c.p035a.C0518b;
import p004cn.jiguang.p031g.p034c.p035a.C0519c;
import p004cn.jiguang.p031g.p034c.p035a.C0520d;
import p004cn.jiguang.p031g.p034c.p035a.C0521e;

/* renamed from: cn.jiguang.g.c.b */
public final class C0522b {

    /* renamed from: b */
    private static C0522b f596b;

    /* renamed from: a */
    private Class<?>[] f597a = {C0519c.class, C0520d.class, C0521e.class};

    /* renamed from: c */
    private C0517a f598c = null;

    private C0522b() {
    }

    /* renamed from: a */
    private static C0517a m1063a(Context context, Class<?>[] clsArr) {
        if (clsArr == null) {
            return null;
        }
        int length = clsArr.length;
        int i = 0;
        while (i < length) {
            try {
                C0517a aVar = (C0517a) clsArr[i].newInstance();
                if (aVar.mo6696b(context)) {
                    return aVar;
                }
                i++;
            } catch (Throwable th) {
            }
        }
        return null;
    }

    /* renamed from: a */
    public static C0522b m1064a() {
        if (f596b == null) {
            synchronized (TelephonyManager.class) {
                if (f596b == null) {
                    f596b = new C0522b();
                }
            }
        }
        return f596b;
    }

    /* renamed from: a */
    public final ArrayList<C0516a> mo6697a(Context context) {
        if (this.f598c == null) {
            C0518b bVar = new C0518b();
            if (bVar.mo6696b(context)) {
                this.f598c = bVar;
                return bVar.mo6695a(context);
            }
            C0517a a = m1063a(context, this.f597a);
            if (a != null) {
                this.f598c = a;
                return a.mo6695a(context);
            }
            this.f598c = bVar;
        }
        return this.f598c.mo6695a(context);
    }
}
