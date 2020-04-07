package p004cn.jiguang.p015d.p026g;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import p004cn.jiguang.p031g.C0526g;

/* renamed from: cn.jiguang.d.g.e */
public final class C0484e {
    /* renamed from: a */
    public static File m799a(File[] fileArr) {
        File file = null;
        if (!(fileArr == null || fileArr.length == 0)) {
            int length = fileArr.length;
            int i = 0;
            while (i < length) {
                File file2 = fileArr[i];
                if (file2.lastModified() <= (file != null ? file.lastModified() : 0)) {
                    file2 = file;
                }
                i++;
                file = file2;
            }
        }
        return file;
    }

    /* renamed from: a */
    public static void m800a(File file) {
        if (file != null && file.exists()) {
            file.delete();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0020 A[SYNTHETIC, Splitter:B:15:0x0020] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m801a(java.io.File r4, java.lang.String r5) {
        /*
            if (r5 != 0) goto L_0x0003
        L_0x0002:
            return
        L_0x0003:
            java.lang.String r0 = "UTF-8"
            byte[] r2 = r5.getBytes(r0)     // Catch:{ Exception -> 0x001a }
            r1 = 0
            java.io.File r4 = m805c(r4)     // Catch:{ Exception -> 0x001c, all -> 0x0027 }
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x001c, all -> 0x0027 }
            r0.<init>(r4)     // Catch:{ Exception -> 0x001c, all -> 0x0027 }
            r0.write(r2)     // Catch:{ Exception -> 0x0031 }
            p004cn.jiguang.p031g.C0526g.m1087a(r0)     // Catch:{ Exception -> 0x001a }
            goto L_0x0002
        L_0x001a:
            r0 = move-exception
            goto L_0x0002
        L_0x001c:
            r0 = move-exception
            r0 = r1
        L_0x001e:
            if (r4 == 0) goto L_0x0023
            r4.getAbsolutePath()     // Catch:{ all -> 0x002c }
        L_0x0023:
            p004cn.jiguang.p031g.C0526g.m1087a(r0)     // Catch:{ Exception -> 0x001a }
            goto L_0x0002
        L_0x0027:
            r0 = move-exception
        L_0x0028:
            p004cn.jiguang.p031g.C0526g.m1087a(r1)     // Catch:{ Exception -> 0x001a }
            throw r0     // Catch:{ Exception -> 0x001a }
        L_0x002c:
            r1 = move-exception
            r3 = r1
            r1 = r0
            r0 = r3
            goto L_0x0028
        L_0x0031:
            r1 = move-exception
            goto L_0x001e
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p015d.p026g.C0484e.m801a(java.io.File, java.lang.String):void");
    }

    /* renamed from: a */
    public static File[] m802a(File file, boolean z) {
        if (file == null || !file.exists() || !file.isDirectory()) {
            return null;
        }
        return file.listFiles(new C0485f(z));
    }

    /* renamed from: a */
    public static File[] m803a(String str, boolean z) {
        try {
            return m802a(new File(str), z);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: b */
    public static String m804b(File file) {
        byte[] d = m806d(file);
        if (d == null) {
            return null;
        }
        try {
            return new String(d, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /* renamed from: c */
    private static File m805c(File file) {
        if (file != null && !file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
            } catch (Exception e) {
            }
        }
        return file;
    }

    /* renamed from: d */
    private static byte[] m806d(File file) {
        try {
            return C0526g.m1088a((InputStream) new FileInputStream(file));
        } catch (Exception e) {
            return null;
        }
    }
}
