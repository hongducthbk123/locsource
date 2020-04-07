package p004cn.jpush.android.p040d;

import java.lang.reflect.Method;

/* renamed from: cn.jpush.android.d.i */
public final class C0586i {
    /* renamed from: a */
    public static Object m1318a(Object obj, String str, Class[] clsArr, Object[] objArr) throws Exception {
        Object obj2;
        Throwable th = null;
        if (objArr.length != clsArr.length) {
            throw new IllegalArgumentException("argClasses' size is not equal to args' size");
        }
        Method method = obj.getClass().getMethod(str, clsArr);
        boolean isAccessible = method.isAccessible();
        if (!isAccessible) {
            method.setAccessible(true);
        }
        try {
            obj2 = method.invoke(obj, objArr);
        } catch (Exception e) {
            Throwable th2 = e;
            obj2 = null;
            th = th2;
        }
        if (!isAccessible) {
            method.setAccessible(false);
        }
        if (th == null) {
            return obj2;
        }
        throw th;
    }
}
