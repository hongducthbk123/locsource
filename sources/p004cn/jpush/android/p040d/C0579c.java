package p004cn.jpush.android.p040d;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import p004cn.jiguang.net.HttpUtils;

/* renamed from: cn.jpush.android.d.c */
public final class C0579c {

    /* renamed from: a */
    public static final String f766a = (File.separator + "rich" + File.separator);

    /* renamed from: d */
    private static String m1301d(Context context, String str) {
        String str2 = context.getFilesDir() + f766a + str;
        try {
            File file = new File(str2);
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2 + HttpUtils.PATHS_SEPARATOR;
    }

    /* renamed from: a */
    public static String m1294a(Context context, String str) {
        String str2 = context.getFilesDir() + HttpUtils.PATHS_SEPARATOR + str;
        try {
            File file = new File(str2);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2 + HttpUtils.PATHS_SEPARATOR;
    }

    /* renamed from: b */
    public static String m1299b(Context context, String str) {
        try {
            if (C0577a.m1277a()) {
                String str2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + context.getPackageName() + File.separator + str + File.separator;
                File file = new File(str2);
                if (file.exists()) {
                    return str2;
                }
                file.mkdirs();
                return str2;
            }
            File file2 = new File(context.getFilesDir() + f766a);
            if (file2.exists() && file2.isDirectory()) {
                File[] listFiles = file2.listFiles();
                if (listFiles != null && listFiles.length > 10) {
                    Arrays.sort(listFiles, new Comparator<File>() {
                        public final /* synthetic */ int compare(Object obj, Object obj2) {
                            File file = (File) obj;
                            File file2 = (File) obj2;
                            if (file.lastModified() > file2.lastModified()) {
                                return -1;
                            }
                            if (file.lastModified() < file2.lastModified()) {
                                return 1;
                            }
                            return 0;
                        }
                    });
                    m1296a(listFiles[listFiles.length - 1]);
                }
            }
            return m1301d(context, str);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: a */
    private static boolean m1296a(File file) {
        try {
            if (!file.exists()) {
                return false;
            }
            if (file.isFile()) {
                return file.delete();
            }
            String[] list = file.list();
            if (list != null) {
                for (String file2 : list) {
                    File file3 = new File(file, file2);
                    if (file3.isDirectory()) {
                        m1296a(file3);
                    } else {
                        file3.delete();
                    }
                }
            }
            return file.delete();
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: c */
    public static String m1300c(Context context, String str) {
        try {
            if (C0577a.m1277a()) {
                String str2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + context.getPackageName() + File.separator + str;
                if (new File(str2).exists()) {
                    return str2;
                }
                C0582e.m1306c("DirectoryUtils", "Can't find developer picture resource in SDCard.");
                return "";
            }
            C0582e.m1306c("DirectoryUtils", "No SDCard found.");
            return "";
        } catch (Exception e) {
            C0582e.m1306c("DirectoryUtils", "Get developer picture resource failed.");
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: a */
    public static String m1295a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str.substring(str.lastIndexOf(HttpUtils.PATHS_SEPARATOR) + 1, str.length());
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x003f A[Catch:{ IOException -> 0x0043 }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean m1297a(java.lang.String r3, java.lang.String r4, android.content.Context r5) {
        /*
            if (r5 != 0) goto L_0x000a
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "NULL context"
            r0.<init>(r1)
            throw r0
        L_0x000a:
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 != 0) goto L_0x0044
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 != 0) goto L_0x0044
            java.io.File r0 = new java.io.File     // Catch:{ IOException -> 0x0043 }
            r0.<init>(r3)     // Catch:{ IOException -> 0x0043 }
            boolean r1 = r0.exists()     // Catch:{ IOException -> 0x0043 }
            if (r1 != 0) goto L_0x0024
            r0.createNewFile()     // Catch:{ IOException -> 0x0043 }
        L_0x0024:
            r2 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x003b }
            r1.<init>(r0)     // Catch:{ all -> 0x003b }
            java.lang.String r0 = "UTF-8"
            byte[] r0 = r4.getBytes(r0)     // Catch:{ all -> 0x0046 }
            r1.write(r0)     // Catch:{ all -> 0x0046 }
            r1.flush()     // Catch:{ all -> 0x0046 }
            r1.close()     // Catch:{ IOException -> 0x0043 }
            r0 = 1
        L_0x003a:
            return r0
        L_0x003b:
            r0 = move-exception
            r1 = r2
        L_0x003d:
            if (r1 == 0) goto L_0x0042
            r1.close()     // Catch:{ IOException -> 0x0043 }
        L_0x0042:
            throw r0     // Catch:{ IOException -> 0x0043 }
        L_0x0043:
            r0 = move-exception
        L_0x0044:
            r0 = 0
            goto L_0x003a
        L_0x0046:
            r0 = move-exception
            goto L_0x003d
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jpush.android.p040d.C0579c.m1297a(java.lang.String, java.lang.String, android.content.Context):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x002c  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean m1298a(java.lang.String r3, byte[] r4) throws java.io.IOException {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 != 0) goto L_0x0030
            int r0 = r4.length
            if (r0 <= 0) goto L_0x0030
            java.io.File r0 = new java.io.File
            r0.<init>(r3)
            boolean r1 = r0.exists()
            if (r1 != 0) goto L_0x0017
            r0.createNewFile()
        L_0x0017:
            r2 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x0028 }
            r1.<init>(r0)     // Catch:{ all -> 0x0028 }
            r1.write(r4)     // Catch:{ all -> 0x0032 }
            r1.flush()     // Catch:{ all -> 0x0032 }
            r1.close()
            r0 = 1
        L_0x0027:
            return r0
        L_0x0028:
            r0 = move-exception
            r1 = r2
        L_0x002a:
            if (r1 == 0) goto L_0x002f
            r1.close()
        L_0x002f:
            throw r0
        L_0x0030:
            r0 = 0
            goto L_0x0027
        L_0x0032:
            r0 = move-exception
            goto L_0x002a
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jpush.android.p040d.C0579c.m1298a(java.lang.String, byte[]):boolean");
    }
}
