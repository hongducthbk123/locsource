package p004cn.jiguang.p005a.p006a.p007a;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import p004cn.jiguang.p005a.p011b.C0372a;
import p004cn.jiguang.p031g.C0506a;

/* renamed from: cn.jiguang.a.a.a.a */
public final class C0335a {

    /* renamed from: b */
    private static C0335a f26b = null;

    /* renamed from: c */
    private static final Object f27c = new Object();

    /* renamed from: a */
    private HashMap<String, Long> f28a = null;

    /* renamed from: d */
    private File f29d = null;

    private C0335a() {
    }

    /* renamed from: a */
    public static long m9a(Context context) {
        return C0372a.m160c(context);
    }

    /* renamed from: a */
    public static C0335a m10a() {
        if (f26b == null) {
            synchronized (f27c) {
                f26b = new C0335a();
            }
        }
        return f26b;
    }

    /* renamed from: b */
    private void m11b(Context context) {
        if (this.f28a == null) {
            this.f28a = new HashMap<>();
        }
        if (this.f29d == null && context != null) {
            this.f29d = new File(context.getCacheDir(), "ArpCache");
            try {
                if (this.f29d != null && this.f28a != null && this.f29d.exists()) {
                    ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(this.f29d));
                    this.f28a = (HashMap) objectInputStream.readObject();
                    objectInputStream.close();
                }
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: a */
    public final void mo6187a(Context context, String str) {
        m11b(context);
        if (!TextUtils.isEmpty(str)) {
            this.f28a.put(C0506a.m933a(str), Long.valueOf(System.currentTimeMillis()));
            try {
                if (this.f29d != null && this.f28a != null) {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.f29d));
                    objectOutputStream.writeObject(this.f28a);
                    objectOutputStream.flush();
                    objectOutputStream.close();
                }
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: b */
    public final boolean mo6188b(Context context, String str) {
        m11b(context);
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        Long l = (Long) this.f28a.get(C0506a.m933a(str));
        if (l == null) {
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long longValue = l.longValue();
        long c = C0372a.m160c(context);
        return c != 0 && Math.abs(currentTimeMillis - longValue) > c;
    }
}
