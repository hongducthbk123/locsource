package p004cn.jiguang.p031g;

import com.google.zxing.common.StringUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* renamed from: cn.jiguang.g.f */
public final class C0525f {
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002d A[SYNTHETIC, Splitter:B:12:0x002d] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0045 A[SYNTHETIC, Splitter:B:25:0x0045] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.ArrayList<java.lang.String> m1083a(java.io.InputStream r6) {
        /*
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r1 = 0
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0053, all -> 0x0042 }
            java.lang.String r3 = "UTF-8"
            r0.<init>(r6, r3)     // Catch:{ Exception -> 0x0053, all -> 0x0042 }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Exception -> 0x002a, all -> 0x004e }
            r3 = 2048(0x800, float:2.87E-42)
            r1.<init>(r0, r3)     // Catch:{ Exception -> 0x002a, all -> 0x004e }
        L_0x0014:
            java.lang.String r3 = r1.readLine()     // Catch:{ Exception -> 0x002a, all -> 0x004e }
            if (r3 == 0) goto L_0x0031
            java.lang.String r3 = r3.trim()     // Catch:{ Exception -> 0x002a, all -> 0x004e }
            java.lang.String r4 = ""
            boolean r4 = r4.equals(r3)     // Catch:{ Exception -> 0x002a, all -> 0x004e }
            if (r4 != 0) goto L_0x0014
            r2.add(r3)     // Catch:{ Exception -> 0x002a, all -> 0x004e }
            goto L_0x0014
        L_0x002a:
            r1 = move-exception
        L_0x002b:
            if (r0 == 0) goto L_0x0030
            r0.close()     // Catch:{ IOException -> 0x003d }
        L_0x0030:
            return r2
        L_0x0031:
            r1.close()     // Catch:{ Exception -> 0x002a, all -> 0x004e }
            r0.close()     // Catch:{ IOException -> 0x0038 }
            goto L_0x0030
        L_0x0038:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0030
        L_0x003d:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0030
        L_0x0042:
            r0 = move-exception
        L_0x0043:
            if (r1 == 0) goto L_0x0048
            r1.close()     // Catch:{ IOException -> 0x0049 }
        L_0x0048:
            throw r0
        L_0x0049:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0048
        L_0x004e:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x0043
        L_0x0053:
            r0 = move-exception
            r0 = r1
            goto L_0x002b
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p031g.C0525f.m1083a(java.io.InputStream):java.util.ArrayList");
    }

    /* renamed from: a */
    private static void m1084a(File file, ZipOutputStream zipOutputStream, String str) {
        String str2 = new String((str + (str.trim().length() == 0 ? "" : File.separator) + file.getName()).getBytes("8859_1"), StringUtils.GB2312);
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File a : listFiles) {
                    m1084a(a, zipOutputStream, str2);
                }
                return;
            }
            return;
        }
        byte[] bArr = new byte[1048576];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file), 1048576);
        zipOutputStream.putNextEntry(new ZipEntry(str2));
        while (true) {
            int read = bufferedInputStream.read(bArr);
            if (read != -1) {
                zipOutputStream.write(bArr, 0, read);
            } else {
                bufferedInputStream.close();
                zipOutputStream.flush();
                zipOutputStream.closeEntry();
                return;
            }
        }
    }

    /* renamed from: a */
    public static void m1085a(String str) {
        File file = new File(str);
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    for (File file2 : listFiles) {
                        m1085a(file2.getAbsolutePath());
                        file2.delete();
                    }
                }
            }
            file.delete();
        }
    }

    /* renamed from: a */
    public static void m1086a(Collection<File> collection, File file) {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file), 1048576));
        for (File a : collection) {
            m1084a(a, zipOutputStream, "");
        }
        zipOutputStream.close();
    }
}
