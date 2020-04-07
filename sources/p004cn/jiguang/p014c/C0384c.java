package p004cn.jiguang.p014c;

import android.content.Context;
import android.text.TextUtils;

/* renamed from: cn.jiguang.c.c */
public final class C0384c {

    /* renamed from: a */
    private static String f193a = "";

    /* renamed from: a */
    public static String m235a(Context context) {
        String str = "";
        return !TextUtils.isEmpty(str) ? str : m236b(context);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:23:? A[ADDED_TO_REGION, ORIG_RETURN, RETURN, SYNTHETIC] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String m236b(android.content.Context r7) {
        /*
            r1 = 0
            r2 = -1
            if (r7 == 0) goto L_0x006c
            android.content.Context r0 = r7.getApplicationContext()     // Catch:{ Throwable -> 0x0063 }
            java.lang.String r3 = "country_detector"
            java.lang.Object r0 = r0.getSystemService(r3)     // Catch:{ Throwable -> 0x0063 }
            if (r0 == 0) goto L_0x006c
            java.lang.Class r3 = r0.getClass()     // Catch:{ Throwable -> 0x0063 }
            java.lang.String r4 = "detectCountry"
            r5 = 0
            java.lang.Class[] r5 = new java.lang.Class[r5]     // Catch:{ Throwable -> 0x0063 }
            java.lang.reflect.Method r3 = r3.getDeclaredMethod(r4, r5)     // Catch:{ Throwable -> 0x0063 }
            if (r3 == 0) goto L_0x006c
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x0063 }
            java.lang.Object r3 = r3.invoke(r0, r4)     // Catch:{ Throwable -> 0x0063 }
            if (r3 == 0) goto L_0x006c
            java.lang.Class r0 = r3.getClass()     // Catch:{ Throwable -> 0x0063 }
            java.lang.String r4 = "getCountryIso"
            r5 = 0
            java.lang.Class[] r5 = new java.lang.Class[r5]     // Catch:{ Throwable -> 0x0063 }
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r4, r5)     // Catch:{ Throwable -> 0x0063 }
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x0063 }
            java.lang.Object r0 = r0.invoke(r3, r4)     // Catch:{ Throwable -> 0x0063 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x0063 }
            java.lang.Class r1 = r3.getClass()     // Catch:{ Throwable -> 0x0069 }
            java.lang.String r4 = "getSource"
            r5 = 0
            java.lang.Class[] r5 = new java.lang.Class[r5]     // Catch:{ Throwable -> 0x0069 }
            java.lang.reflect.Method r1 = r1.getDeclaredMethod(r4, r5)     // Catch:{ Throwable -> 0x0069 }
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x0069 }
            java.lang.Object r1 = r1.invoke(r3, r4)     // Catch:{ Throwable -> 0x0069 }
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch:{ Throwable -> 0x0069 }
            int r1 = r1.intValue()     // Catch:{ Throwable -> 0x0069 }
            r6 = r1
            r1 = r0
            r0 = r6
        L_0x005b:
            r2 = r0
            r0 = r1
        L_0x005d:
            if (r2 == 0) goto L_0x0062
            r1 = 1
            if (r2 != r1) goto L_0x0066
        L_0x0062:
            return r0
        L_0x0063:
            r0 = move-exception
        L_0x0064:
            r0 = r1
            goto L_0x005d
        L_0x0066:
            java.lang.String r0 = ""
            goto L_0x0062
        L_0x0069:
            r1 = move-exception
            r1 = r0
            goto L_0x0064
        L_0x006c:
            r0 = r2
            goto L_0x005b
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p014c.C0384c.m236b(android.content.Context):java.lang.String");
    }
}
