package p004cn.jiguang.p030f;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/* renamed from: cn.jiguang.f.a */
final class C0502a {

    /* renamed from: a */
    private static final Class<?>[] f554a = {Byte.TYPE, Short.TYPE, Character.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE};

    /* renamed from: b */
    private static final Map<Class<?>, Class<?>> f555b;

    /* renamed from: c */
    private static final Map<Class<?>, Class<?>> f556c = new HashMap();

    static {
        HashMap hashMap = new HashMap();
        f555b = hashMap;
        hashMap.put(Boolean.TYPE, Boolean.class);
        f555b.put(Byte.TYPE, Byte.class);
        f555b.put(Character.TYPE, Character.class);
        f555b.put(Short.TYPE, Short.class);
        f555b.put(Integer.TYPE, Integer.class);
        f555b.put(Long.TYPE, Long.class);
        f555b.put(Double.TYPE, Double.class);
        f555b.put(Float.TYPE, Float.class);
        f555b.put(Void.TYPE, Void.TYPE);
        for (Class cls : f555b.keySet()) {
            Class cls2 = (Class) f555b.get(cls);
            if (!cls.equals(cls2)) {
                f556c.put(cls2, cls);
            }
        }
    }

    /* renamed from: a */
    private static float m910a(Class<?>[] clsArr, Class<?>[] clsArr2) {
        float f;
        float f2 = 0.0f;
        for (int i = 0; i < clsArr.length; i++) {
            Class<?> cls = clsArr[i];
            Class<?> cls2 = clsArr2[i];
            if (cls2.isPrimitive()) {
                if (!cls.isPrimitive()) {
                    cls = m912a(cls);
                    f = 0.1f;
                } else {
                    f = 0.0f;
                }
                int i2 = 0;
                while (cls != cls2 && i2 < f554a.length) {
                    if (cls == f554a[i2]) {
                        f += 0.1f;
                        if (i2 < f554a.length - 1) {
                            cls = f554a[i2 + 1];
                        }
                    }
                    i2++;
                }
            } else {
                Class<?> cls3 = cls;
                float f3 = 0.0f;
                while (true) {
                    if (cls3 != null && !cls2.equals(cls3)) {
                        if (cls2.isInterface() && m913a(cls3, cls2, true)) {
                            f3 += 0.25f;
                            break;
                        }
                        f3 += 1.0f;
                        cls3 = cls3.getSuperclass();
                    } else {
                        break;
                    }
                }
                if (cls3 == null) {
                    f3 += 1.5f;
                }
                f = f3;
            }
            f2 += f;
        }
        return f2;
    }

    /* renamed from: a */
    static int m911a(Class<?>[] clsArr, Class<?>[] clsArr2, Class<?>[] clsArr3) {
        float a = m910a(clsArr3, clsArr);
        float a2 = m910a(clsArr3, clsArr2);
        if (a < a2) {
            return -1;
        }
        return a2 < a ? 1 : 0;
    }

    /* renamed from: a */
    private static Class<?> m912a(Class<?> cls) {
        return (Class) f556c.get(cls);
    }

    /* renamed from: a */
    private static boolean m913a(Class<?> cls, Class<?> cls2, boolean z) {
        if (cls2 == null) {
            return false;
        }
        if (cls == null) {
            return !cls2.isPrimitive();
        }
        if (z) {
            if (cls.isPrimitive() && !cls2.isPrimitive()) {
                if (cls != null && cls.isPrimitive()) {
                    cls = (Class) f555b.get(cls);
                }
                if (cls == null) {
                    return false;
                }
            }
            if (cls2.isPrimitive() && !cls.isPrimitive()) {
                cls = m912a(cls);
                if (cls == null) {
                    return false;
                }
            }
        }
        if (cls.equals(cls2)) {
            return true;
        }
        if (!cls.isPrimitive()) {
            return cls2.isAssignableFrom(cls);
        }
        if (!cls2.isPrimitive()) {
            return false;
        }
        if (Integer.TYPE.equals(cls)) {
            return Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
        }
        if (Long.TYPE.equals(cls)) {
            return Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
        }
        if (Boolean.TYPE.equals(cls)) {
            return false;
        }
        if (Double.TYPE.equals(cls)) {
            return false;
        }
        if (Float.TYPE.equals(cls)) {
            return Double.TYPE.equals(cls2);
        }
        if (Character.TYPE.equals(cls)) {
            return Integer.TYPE.equals(cls2) || Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
        }
        if (Short.TYPE.equals(cls)) {
            return Integer.TYPE.equals(cls2) || Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
        }
        if (Byte.TYPE.equals(cls)) {
            return Short.TYPE.equals(cls2) || Integer.TYPE.equals(cls2) || Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
        }
        return false;
    }

    /* renamed from: a */
    static boolean m914a(AccessibleObject accessibleObject) {
        if (accessibleObject == null || accessibleObject.isAccessible()) {
            return false;
        }
        Member member = (Member) accessibleObject;
        if (!accessibleObject.isAccessible() && Modifier.isPublic(member.getModifiers())) {
            if ((member.getDeclaringClass().getModifiers() & 7) == 0) {
                try {
                    accessibleObject.setAccessible(true);
                    return true;
                } catch (SecurityException e) {
                }
            }
        }
        return false;
    }

    /* renamed from: a */
    static boolean m915a(Member member) {
        return member != null && Modifier.isPublic(member.getModifiers()) && !member.isSynthetic();
    }

    /* renamed from: a */
    static boolean m916a(Class<?>[] clsArr, Class<?>[] clsArr2, boolean z) {
        if (!C0504c.m923a(clsArr, clsArr2)) {
            return false;
        }
        if (clsArr == null) {
            clsArr = C0504c.f559b;
        }
        if (clsArr2 == null) {
            clsArr2 = C0504c.f559b;
        }
        for (int i = 0; i < clsArr.length; i++) {
            if (!m913a(clsArr[i], clsArr2[i], true)) {
                return false;
            }
        }
        return true;
    }
}
