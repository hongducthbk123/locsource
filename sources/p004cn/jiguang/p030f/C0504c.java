package p004cn.jiguang.p030f;

/* renamed from: cn.jiguang.f.c */
public final class C0504c {

    /* renamed from: a */
    static final Object[] f558a = new Object[0];

    /* renamed from: b */
    static final Class<?>[] f559b = new Class[0];

    /* renamed from: a */
    static boolean m923a(Object[] objArr, Object[] objArr2) {
        return (objArr != null || objArr2 == null || objArr2.length <= 0) && (objArr2 != null || objArr == null || objArr.length <= 0) && (objArr == null || objArr2 == null || objArr.length == objArr2.length);
    }

    /* renamed from: a */
    static Class<?>[] m924a(Class<?>[] clsArr) {
        return (clsArr == null || clsArr.length == 0) ? f559b : clsArr;
    }

    /* renamed from: a */
    static Class<?>[] m925a(Object... objArr) {
        if (objArr == null) {
            return null;
        }
        if (objArr.length == 0) {
            return f559b;
        }
        Class[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            clsArr[i] = objArr[i] == null ? null : objArr[i].getClass();
        }
        return clsArr;
    }

    /* renamed from: b */
    static Object[] m926b(Object[] objArr) {
        return (objArr == null || objArr.length == 0) ? f558a : objArr;
    }
}
