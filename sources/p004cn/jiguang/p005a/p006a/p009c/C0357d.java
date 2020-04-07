package p004cn.jiguang.p005a.p006a.p009c;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: cn.jiguang.a.a.c.d */
public final class C0357d {
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x004e A[ExcHandler: IndexOutOfBoundsException (e java.lang.IndexOutOfBoundsException), Splitter:B:1:0x0006] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.ArrayList<p004cn.jiguang.p005a.p006a.p009c.C0361h> m105a(android.content.Context r7, boolean r8) {
        /*
            r0 = 0
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            android.content.pm.PackageManager r1 = r7.getPackageManager()     // Catch:{ IndexOutOfBoundsException -> 0x004e, Throwable -> 0x004c }
            r3 = 0
            java.util.List r3 = r1.getInstalledPackages(r3)     // Catch:{ IndexOutOfBoundsException -> 0x004e, Throwable -> 0x004c }
            r1 = r0
        L_0x0010:
            int r0 = r3.size()     // Catch:{ IndexOutOfBoundsException -> 0x004e, Throwable -> 0x004c }
            if (r1 >= r0) goto L_0x004d
            java.lang.Object r0 = r3.get(r1)     // Catch:{ Throwable -> 0x0050, IndexOutOfBoundsException -> 0x004e }
            android.content.pm.PackageInfo r0 = (android.content.pm.PackageInfo) r0     // Catch:{ Throwable -> 0x0050, IndexOutOfBoundsException -> 0x004e }
            cn.jiguang.a.a.c.h r4 = new cn.jiguang.a.a.c.h     // Catch:{ Throwable -> 0x0050, IndexOutOfBoundsException -> 0x004e }
            r4.<init>()     // Catch:{ Throwable -> 0x0050, IndexOutOfBoundsException -> 0x004e }
            android.content.pm.ApplicationInfo r5 = r0.applicationInfo     // Catch:{ Throwable -> 0x0050, IndexOutOfBoundsException -> 0x004e }
            android.content.pm.PackageManager r6 = r7.getPackageManager()     // Catch:{ Throwable -> 0x0050, IndexOutOfBoundsException -> 0x004e }
            java.lang.CharSequence r5 = r5.loadLabel(r6)     // Catch:{ Throwable -> 0x0050, IndexOutOfBoundsException -> 0x004e }
            java.lang.String r5 = r5.toString()     // Catch:{ Throwable -> 0x0050, IndexOutOfBoundsException -> 0x004e }
            r4.f121a = r5     // Catch:{ Throwable -> 0x0050, IndexOutOfBoundsException -> 0x004e }
            java.lang.String r5 = r0.packageName     // Catch:{ Throwable -> 0x0050, IndexOutOfBoundsException -> 0x004e }
            r4.f122b = r5     // Catch:{ Throwable -> 0x0050, IndexOutOfBoundsException -> 0x004e }
            java.lang.String r5 = r0.versionName     // Catch:{ Throwable -> 0x0050, IndexOutOfBoundsException -> 0x004e }
            r4.f123c = r5     // Catch:{ Throwable -> 0x0050, IndexOutOfBoundsException -> 0x004e }
            int r5 = r0.versionCode     // Catch:{ Throwable -> 0x0050, IndexOutOfBoundsException -> 0x004e }
            r4.f124d = r5     // Catch:{ Throwable -> 0x0050, IndexOutOfBoundsException -> 0x004e }
            android.content.pm.ApplicationInfo r0 = r0.applicationInfo     // Catch:{ Throwable -> 0x0050, IndexOutOfBoundsException -> 0x004e }
            int r0 = p004cn.jiguang.p031g.C0523d.m1066a(r0)     // Catch:{ Throwable -> 0x0050, IndexOutOfBoundsException -> 0x004e }
            r4.f125e = r0     // Catch:{ Throwable -> 0x0050, IndexOutOfBoundsException -> 0x004e }
            r2.add(r4)     // Catch:{ Throwable -> 0x0050, IndexOutOfBoundsException -> 0x004e }
        L_0x0048:
            int r0 = r1 + 1
            r1 = r0
            goto L_0x0010
        L_0x004c:
            r0 = move-exception
        L_0x004d:
            return r2
        L_0x004e:
            r0 = move-exception
            goto L_0x004d
        L_0x0050:
            r0 = move-exception
            goto L_0x0048
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p005a.p006a.p009c.C0357d.m105a(android.content.Context, boolean):java.util.ArrayList");
    }

    /* renamed from: a */
    public static String[] m106a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
        } catch (Throwable th) {
            return null;
        }
    }

    /* renamed from: b */
    public static JSONArray m107b(Context context) {
        ArrayList a = m105a(context, true);
        JSONArray jSONArray = new JSONArray();
        try {
            Iterator it = a.iterator();
            while (it.hasNext()) {
                C0361h hVar = (C0361h) it.next();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("name", hVar.f121a);
                jSONObject.put("pkg", hVar.f122b);
                jSONObject.put("ver_name", hVar.f123c);
                jSONObject.put("ver_code", hVar.f124d);
                jSONObject.put("install_type", hVar.f125e);
                jSONArray.put(jSONObject);
            }
        } catch (Throwable th) {
        }
        return jSONArray;
    }
}
