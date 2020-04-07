package p004cn.jpush.android.p037a;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/* renamed from: cn.jpush.android.a.i */
public final class C0553i {

    /* renamed from: a */
    private static Queue<Integer> f674a = new ConcurrentLinkedQueue();

    /* renamed from: a */
    public static int m1142a() {
        if (f674a.size() > 0) {
            return ((Integer) f674a.poll()).intValue();
        }
        return 0;
    }

    /* renamed from: a */
    public static boolean m1143a(int i) {
        return f674a.offer(Integer.valueOf(i));
    }

    /* renamed from: b */
    public static boolean m1145b(int i) {
        return f674a.contains(Integer.valueOf(i));
    }

    /* renamed from: b */
    public static int m1144b() {
        return f674a.size();
    }
}
