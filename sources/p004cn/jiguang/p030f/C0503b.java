package p004cn.jiguang.p030f;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/* renamed from: cn.jiguang.f.b */
public final class C0503b {

    /* renamed from: a */
    private static Map<String, Method> f557a = new HashMap();

    /* renamed from: a */
    public static Object m917a(Class cls, String str, Object... objArr) {
        Object[] b = C0504c.m926b(objArr);
        Class[] a = C0504c.m924a((Class<?>[]) C0504c.m925a(b));
        Object[] b2 = C0504c.m926b(b);
        Method c = m922c(cls, str, a);
        if (c != null) {
            return c.invoke(null, b2);
        }
        throw new NoSuchMethodException("No such accessible method: " + str + "() on object: " + cls.getName());
    }

    /* renamed from: a */
    public static <T> T m918a(Class<T> cls, Object[] objArr, Class<?>[] clsArr) {
        Object[] b = C0504c.m926b(objArr);
        Constructor a = m919a(cls, C0504c.m924a(clsArr));
        if (a != null) {
            return a.newInstance(b);
        }
        throw new NoSuchMethodException("No such accessible constructor on object: " + cls.getName());
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0067 A[SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <T> java.lang.reflect.Constructor<T> m919a(java.lang.Class<T> r11, java.lang.Class<?>... r12) {
        /*
            r5 = 0
            r1 = 1
            r2 = 0
            if (r11 == 0) goto L_0x0015
            r0 = r1
        L_0x0006:
            java.lang.String r3 = "class cannot be null"
            java.lang.Object[] r4 = new java.lang.Object[r2]
            p004cn.jiguang.p030f.C0505d.m927a(r0, r3, r4)
            java.lang.reflect.Constructor r0 = r11.getConstructor(r12)     // Catch:{ NoSuchMethodException -> 0x0017 }
            p004cn.jiguang.p030f.C0502a.m914a(r0)     // Catch:{ NoSuchMethodException -> 0x0017 }
        L_0x0014:
            return r0
        L_0x0015:
            r0 = r2
            goto L_0x0006
        L_0x0017:
            r0 = move-exception
            java.lang.reflect.Constructor[] r7 = r11.getConstructors()
            int r8 = r7.length
            r6 = r2
            r0 = r5
        L_0x001f:
            if (r6 >= r8) goto L_0x0014
            r4 = r7[r6]
            java.lang.Class[] r3 = r4.getParameterTypes()
            boolean r3 = p004cn.jiguang.p030f.C0502a.m916a((java.lang.Class<?>[]) r12, (java.lang.Class<?>[]) r3, r1)
            if (r3 == 0) goto L_0x0067
            if (r4 == 0) goto L_0x006b
            r3 = r1
        L_0x0030:
            java.lang.String r9 = "constructor cannot be null"
            java.lang.Object[] r10 = new java.lang.Object[r2]
            p004cn.jiguang.p030f.C0505d.m927a(r3, r9, r10)
            boolean r3 = p004cn.jiguang.p030f.C0502a.m915a(r4)
            if (r3 == 0) goto L_0x0074
            java.lang.Class r3 = r4.getDeclaringClass()
        L_0x0041:
            if (r3 == 0) goto L_0x0072
            int r9 = r3.getModifiers()
            boolean r9 = java.lang.reflect.Modifier.isPublic(r9)
            if (r9 != 0) goto L_0x006d
            r3 = r2
        L_0x004e:
            if (r3 == 0) goto L_0x0074
            r3 = r4
        L_0x0051:
            if (r3 == 0) goto L_0x0067
            p004cn.jiguang.p030f.C0502a.m914a(r3)
            if (r0 == 0) goto L_0x0066
            java.lang.Class[] r4 = r3.getParameterTypes()
            java.lang.Class[] r9 = r0.getParameterTypes()
            int r4 = p004cn.jiguang.p030f.C0502a.m911a((java.lang.Class<?>[]) r4, (java.lang.Class<?>[]) r9, (java.lang.Class<?>[]) r12)
            if (r4 >= 0) goto L_0x0067
        L_0x0066:
            r0 = r3
        L_0x0067:
            int r3 = r6 + 1
            r6 = r3
            goto L_0x001f
        L_0x006b:
            r3 = r2
            goto L_0x0030
        L_0x006d:
            java.lang.Class r3 = r3.getEnclosingClass()
            goto L_0x0041
        L_0x0072:
            r3 = r1
            goto L_0x004e
        L_0x0074:
            r3 = r5
            goto L_0x0051
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p030f.C0503b.m919a(java.lang.Class, java.lang.Class[]):java.lang.reflect.Constructor");
    }

    /* renamed from: a */
    private static Method m920a(Class<?> cls, String str, Class<?>... clsArr) {
        Method method = null;
        Class superclass = cls.getSuperclass();
        while (superclass != null) {
            if (Modifier.isPublic(superclass.getModifiers())) {
                try {
                    return superclass.getMethod(str, clsArr);
                } catch (NoSuchMethodException e) {
                    return method;
                }
            } else {
                superclass = superclass.getSuperclass();
            }
        }
        return method;
    }

    /* renamed from: b */
    private static Method m921b(Class<?> cls, String str, Class<?>... clsArr) {
        while (cls != null) {
            Class[] interfaces = cls.getInterfaces();
            int length = interfaces.length;
            int i = 0;
            while (i < length) {
                Class cls2 = interfaces[i];
                if (Modifier.isPublic(cls2.getModifiers())) {
                    try {
                        return cls2.getDeclaredMethod(str, clsArr);
                    } catch (NoSuchMethodException e) {
                        Method b = m921b(cls2, str, clsArr);
                        if (b != null) {
                            return b;
                        }
                    }
                } else {
                    i++;
                }
            }
            cls = cls.getSuperclass();
        }
        return null;
    }

    /* renamed from: c */
    private static Method m922c(Class<?> cls, String str, Class<?>... clsArr) {
        Method method;
        Method[] methods;
        StringBuilder sb = new StringBuilder();
        sb.append(cls.toString()).append("#").append(str);
        if (clsArr == null || clsArr.length <= 0) {
            sb.append(Void.class.toString());
        } else {
            for (Class<?> cls2 : clsArr) {
                sb.append(cls2.toString()).append("#");
            }
        }
        String sb2 = sb.toString();
        synchronized (f557a) {
            method = (Method) f557a.get(sb2);
        }
        if (method == null) {
            try {
                method = cls.getMethod(str, clsArr);
                C0502a.m914a((AccessibleObject) method);
                synchronized (f557a) {
                    f557a.put(sb2, method);
                }
            } catch (NoSuchMethodException e) {
                method = null;
                for (Method method2 : cls.getMethods()) {
                    if (method2.getName().equals(str) && C0502a.m916a(clsArr, (Class<?>[]) method2.getParameterTypes(), true)) {
                        if (!C0502a.m915a((Member) method2)) {
                            method2 = null;
                        } else {
                            Class declaringClass = method2.getDeclaringClass();
                            if (!Modifier.isPublic(declaringClass.getModifiers())) {
                                String name = method2.getName();
                                Class[] parameterTypes = method2.getParameterTypes();
                                method2 = m921b(declaringClass, name, parameterTypes);
                                if (method2 == null) {
                                    method2 = m920a(declaringClass, name, (Class<?>[]) parameterTypes);
                                }
                            }
                        }
                        if (method2 != null && (method == null || C0502a.m911a((Class<?>[]) method2.getParameterTypes(), (Class<?>[]) method.getParameterTypes(), clsArr) < 0)) {
                            method = method2;
                        }
                    }
                }
                if (method != null) {
                    C0502a.m914a((AccessibleObject) method);
                }
                synchronized (f557a) {
                    f557a.put(sb2, method);
                }
            }
        } else if (!method.isAccessible()) {
            method.setAccessible(true);
        }
        return method;
    }
}
