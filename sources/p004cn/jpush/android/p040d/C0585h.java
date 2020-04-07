package p004cn.jpush.android.p040d;

import android.content.Context;

/* renamed from: cn.jpush.android.d.h */
public final class C0585h {
    /* renamed from: a */
    public static String m1317a(Context context, String str, String str2) throws IllegalArgumentException {
        try {
            Class loadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            return (String) loadClass.getMethod("get", new Class[]{String.class, String.class}).invoke(loadClass, new Object[]{new String(str), new String(str2)});
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e2) {
            return str2;
        }
    }
}
