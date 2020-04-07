package p004cn.jiguang.p015d.p021d;

import android.content.Context;
import p004cn.jiguang.p005a.p006a.p009c.C0355b;
import p004cn.jiguang.p015d.p016a.C0386a;
import p004cn.jiguang.p031g.C0530k;

/* renamed from: cn.jiguang.d.d.u */
public final class C0464u {
    /* renamed from: a */
    public static void m728a(Context context) {
        String str = "1.2.0";
        String b = C0355b.m100b(context);
        if (C0530k.m1099a(b)) {
            b = C0386a.m292s();
        }
        if (!C0530k.m1099a(b) && !str.equals(b) && !str.startsWith("1.") && b.startsWith("1.")) {
            C0355b.m99a(context, str);
            m729b(context);
            C0386a.m267d(context);
        }
        C0386a.m280i(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0049, code lost:
        r2 = 0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0048 A[ExcHandler: FileNotFoundException (e java.io.FileNotFoundException), Splitter:B:9:0x0018] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized boolean m729b(android.content.Context r13) {
        /*
            r4 = 0
            r12 = 8
            r0 = 0
            java.lang.Class<cn.jiguang.d.d.u> r7 = p004cn.jiguang.p015d.p021d.C0464u.class
            monitor-enter(r7)
            java.lang.String r1 = ""
            java.lang.String r2 = p004cn.jiguang.p015d.p016a.C0389d.m321b(r13)     // Catch:{ all -> 0x005b }
            boolean r2 = p004cn.jiguang.p031g.C0530k.m1099a(r2)     // Catch:{ all -> 0x005b }
            if (r2 == 0) goto L_0x0016
        L_0x0014:
            monitor-exit(r7)
            return r0
        L_0x0016:
            r2 = 8
            byte[] r10 = new byte[r2]     // Catch:{ FileNotFoundException -> 0x0048, IOException -> 0x005e }
            java.lang.String r2 = "PrefsFile"
            java.io.FileInputStream r11 = r13.openFileInput(r2)     // Catch:{ FileNotFoundException -> 0x0048, IOException -> 0x005e }
            r2 = 0
            r3 = 8
            r11.read(r10, r2, r3)     // Catch:{ FileNotFoundException -> 0x0048, IOException -> 0x005e }
            r6 = r0
            r2 = r4
        L_0x0028:
            if (r6 >= r12) goto L_0x0037
            long r8 = r2 << r12
            byte r2 = r10[r6]     // Catch:{ FileNotFoundException -> 0x0048, IOException -> 0x0061 }
            r2 = r2 & 255(0xff, float:3.57E-43)
            long r2 = (long) r2     // Catch:{ FileNotFoundException -> 0x0048, IOException -> 0x0061 }
            long r8 = r8 + r2
            int r2 = r6 + 1
            r6 = r2
            r2 = r8
            goto L_0x0028
        L_0x0037:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0048, IOException -> 0x0061 }
            r6.<init>()     // Catch:{ FileNotFoundException -> 0x0048, IOException -> 0x0061 }
        L_0x003c:
            int r8 = r11.read()     // Catch:{ FileNotFoundException -> 0x0048, IOException -> 0x0061 }
            r9 = -1
            if (r8 == r9) goto L_0x0053
            char r8 = (char) r8     // Catch:{ FileNotFoundException -> 0x0048, IOException -> 0x0061 }
            r6.append(r8)     // Catch:{ FileNotFoundException -> 0x0048, IOException -> 0x0061 }
            goto L_0x003c
        L_0x0048:
            r2 = move-exception
            r2 = r4
        L_0x004a:
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 == 0) goto L_0x0014
            p004cn.jiguang.p015d.p016a.C0389d.m310a(r13, r2, r1)     // Catch:{ all -> 0x005b }
            r0 = 1
            goto L_0x0014
        L_0x0053:
            r11.close()     // Catch:{ FileNotFoundException -> 0x0048, IOException -> 0x0061 }
            java.lang.String r1 = r6.toString()     // Catch:{ FileNotFoundException -> 0x0048, IOException -> 0x0061 }
            goto L_0x004a
        L_0x005b:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        L_0x005e:
            r2 = move-exception
            r2 = r4
            goto L_0x004a
        L_0x0061:
            r6 = move-exception
            goto L_0x004a
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p015d.p021d.C0464u.m729b(android.content.Context):boolean");
    }
}
