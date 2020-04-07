package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.Process;
import com.adjust.sdk.Constants;
import com.facebook.appevents.AppEventsConstants;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.p052io.IOUtils;
import org.apache.http.HttpHost;

/* renamed from: com.tencent.bugly.proguard.y */
/* compiled from: BUGLY */
public class C2018y {

    /* renamed from: a */
    private static Map<String, String> f1715a = null;

    /* renamed from: a */
    public static String m2147a(Throwable th) {
        if (th == null) {
            return "";
        }
        try {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            return stringWriter.getBuffer().toString();
        } catch (Throwable th2) {
            if (!C2014w.m2114a(th2)) {
                th2.printStackTrace();
            }
            return "fail";
        }
    }

    /* renamed from: a */
    public static String m2143a() {
        return m2144a(System.currentTimeMillis());
    }

    /* renamed from: a */
    public static String m2144a(long j) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date(j));
        } catch (Exception e) {
            return new Date().toString();
        }
    }

    /* renamed from: a */
    public static String m2148a(Date date) {
        if (date == null) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(date);
        } catch (Exception e) {
            return new Date().toString();
        }
    }

    /* renamed from: a */
    private static byte[] m2165a(byte[] bArr, int i, String str) {
        if (bArr == null || i == -1) {
            return bArr;
        }
        C2014w.m2117c("rqdp{  enD:} %d %d", Integer.valueOf(bArr.length), Integer.valueOf(i));
        try {
            C1973af a = C1967a.m1887a(i);
            if (a == null) {
                return null;
            }
            a.mo19546a(str);
            return a.mo19548b(bArr);
        } catch (Throwable th) {
            if (!C2014w.m2114a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: b */
    private static byte[] m2177b(byte[] bArr, int i, String str) {
        if (bArr == null || i == -1) {
            return bArr;
        }
        try {
            C1973af a = C1967a.m1887a(i);
            if (a == null) {
                return null;
            }
            a.mo19546a(str);
            return a.mo19547a(bArr);
        } catch (Throwable th) {
            if (!C2014w.m2114a(th)) {
                th.printStackTrace();
            }
            C2014w.m2118d("encrytype %d %s", Integer.valueOf(i), str);
            return null;
        }
    }

    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v1, types: [java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r2v1, types: [java.util.zip.ZipOutputStream] */
    /* JADX WARNING: type inference failed for: r3v2, types: [java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r2v3, types: [java.util.zip.ZipOutputStream] */
    /* JADX WARNING: type inference failed for: r3v3, types: [java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: type inference failed for: r2v11 */
    /* JADX WARNING: type inference failed for: r3v4 */
    /* JADX WARNING: type inference failed for: r2v12 */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: type inference failed for: r2v15, types: [java.io.FileInputStream] */
    /* JADX WARNING: type inference failed for: r3v6 */
    /* JADX WARNING: type inference failed for: r2v16 */
    /* JADX WARNING: type inference failed for: r3v7 */
    /* JADX WARNING: type inference failed for: r2v17 */
    /* JADX WARNING: type inference failed for: r3v8 */
    /* JADX WARNING: type inference failed for: r3v9 */
    /* JADX WARNING: type inference failed for: r2v18 */
    /* JADX WARNING: type inference failed for: r3v10 */
    /* JADX WARNING: type inference failed for: r3v11 */
    /* JADX WARNING: type inference failed for: r3v12 */
    /* JADX WARNING: type inference failed for: r2v19 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004b A[Catch:{ Throwable -> 0x0056 }, LOOP:0: B:18:0x004b->B:20:0x0051, LOOP_START] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005d A[Catch:{ all -> 0x007d }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0062 A[SYNTHETIC, Splitter:B:27:0x0062] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0067 A[SYNTHETIC, Splitter:B:30:0x0067] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0078 A[Catch:{ Throwable -> 0x0056 }, LOOP:1: B:34:0x0072->B:37:0x0078, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0080 A[SYNTHETIC, Splitter:B:40:0x0080] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0085 A[SYNTHETIC, Splitter:B:43:0x0085] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0090 A[EDGE_INSN: B:47:0x0090->B:48:? ?: BREAK  
    EDGE_INSN: B:47:0x0090->B:48:? ?: BREAK  
    EDGE_INSN: B:47:0x0090->B:48:? ?: BREAK  , SYNTHETIC, Splitter:B:47:0x0090] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0090 A[EDGE_INSN: B:47:0x0090->B:48:? ?: BREAK  
    EDGE_INSN: B:47:0x0090->B:48:? ?: BREAK  , SYNTHETIC, Splitter:B:47:0x0090] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x009f A[SYNTHETIC, Splitter:B:50:0x009f] */
    /* JADX WARNING: Unknown variable types count: 8 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] m2162a(java.io.File r9, java.lang.String r10) {
        /*
            r0 = 0
            r8 = 0
            java.lang.String r1 = "rqdp{  ZF start}"
            java.lang.Object[] r2 = new java.lang.Object[r8]
            com.tencent.bugly.proguard.C2014w.m2117c(r1, r2)
            java.lang.String r1 = "buglyCacheLog.txt"
            if (r9 == 0) goto L_0x00e7
            boolean r2 = r9.exists()     // Catch:{ Throwable -> 0x00d9, all -> 0x00cb }
            if (r2 == 0) goto L_0x00e7
            boolean r2 = r9.canRead()     // Catch:{ Throwable -> 0x00d9, all -> 0x00cb }
            if (r2 == 0) goto L_0x00e7
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x00d9, all -> 0x00cb }
            r2.<init>(r9)     // Catch:{ Throwable -> 0x00d9, all -> 0x00cb }
            java.lang.String r1 = r9.getName()     // Catch:{ Throwable -> 0x00de, all -> 0x00d0 }
            r3 = r2
        L_0x0023:
            java.lang.String r2 = "UTF-8"
            byte[] r2 = r10.getBytes(r2)     // Catch:{ Throwable -> 0x00e3, all -> 0x00d5 }
            java.io.ByteArrayInputStream r4 = new java.io.ByteArrayInputStream     // Catch:{ Throwable -> 0x00e3, all -> 0x00d5 }
            r4.<init>(r2)     // Catch:{ Throwable -> 0x00e3, all -> 0x00d5 }
            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream     // Catch:{ Throwable -> 0x00e3, all -> 0x00d5 }
            r5.<init>()     // Catch:{ Throwable -> 0x00e3, all -> 0x00d5 }
            java.util.zip.ZipOutputStream r2 = new java.util.zip.ZipOutputStream     // Catch:{ Throwable -> 0x00e3, all -> 0x00d5 }
            r2.<init>(r5)     // Catch:{ Throwable -> 0x00e3, all -> 0x00d5 }
            r6 = 8
            r2.setMethod(r6)     // Catch:{ Throwable -> 0x0056 }
            java.util.zip.ZipEntry r6 = new java.util.zip.ZipEntry     // Catch:{ Throwable -> 0x0056 }
            r6.<init>(r1)     // Catch:{ Throwable -> 0x0056 }
            r2.putNextEntry(r6)     // Catch:{ Throwable -> 0x0056 }
            r1 = 1024(0x400, float:1.435E-42)
            byte[] r1 = new byte[r1]     // Catch:{ Throwable -> 0x0056 }
            if (r3 == 0) goto L_0x0072
        L_0x004b:
            int r6 = r3.read(r1)     // Catch:{ Throwable -> 0x0056 }
            if (r6 <= 0) goto L_0x0072
            r7 = 0
            r2.write(r1, r7, r6)     // Catch:{ Throwable -> 0x0056 }
            goto L_0x004b
        L_0x0056:
            r1 = move-exception
        L_0x0057:
            boolean r4 = com.tencent.bugly.proguard.C2014w.m2114a(r1)     // Catch:{ all -> 0x007d }
            if (r4 != 0) goto L_0x0060
            r1.printStackTrace()     // Catch:{ all -> 0x007d }
        L_0x0060:
            if (r3 == 0) goto L_0x0065
            r3.close()     // Catch:{ IOException -> 0x00b7 }
        L_0x0065:
            if (r2 == 0) goto L_0x006a
            r2.close()     // Catch:{ IOException -> 0x00bc }
        L_0x006a:
            java.lang.String r1 = "rqdp{  ZF end}"
            java.lang.Object[] r2 = new java.lang.Object[r8]
            com.tencent.bugly.proguard.C2014w.m2117c(r1, r2)
        L_0x0071:
            return r0
        L_0x0072:
            int r6 = r4.read(r1)     // Catch:{ Throwable -> 0x0056 }
            if (r6 <= 0) goto L_0x0090
            r7 = 0
            r2.write(r1, r7, r6)     // Catch:{ Throwable -> 0x0056 }
            goto L_0x0072
        L_0x007d:
            r0 = move-exception
        L_0x007e:
            if (r3 == 0) goto L_0x0083
            r3.close()     // Catch:{ IOException -> 0x00c1 }
        L_0x0083:
            if (r2 == 0) goto L_0x0088
            r2.close()     // Catch:{ IOException -> 0x00c6 }
        L_0x0088:
            java.lang.String r1 = "rqdp{  ZF end}"
            java.lang.Object[] r2 = new java.lang.Object[r8]
            com.tencent.bugly.proguard.C2014w.m2117c(r1, r2)
            throw r0
        L_0x0090:
            r2.closeEntry()     // Catch:{ Throwable -> 0x0056 }
            r2.flush()     // Catch:{ Throwable -> 0x0056 }
            r2.finish()     // Catch:{ Throwable -> 0x0056 }
            byte[] r0 = r5.toByteArray()     // Catch:{ Throwable -> 0x0056 }
            if (r3 == 0) goto L_0x00a2
            r3.close()     // Catch:{ IOException -> 0x00ad }
        L_0x00a2:
            r2.close()     // Catch:{ IOException -> 0x00b2 }
        L_0x00a5:
            java.lang.String r1 = "rqdp{  ZF end}"
            java.lang.Object[] r2 = new java.lang.Object[r8]
            com.tencent.bugly.proguard.C2014w.m2117c(r1, r2)
            goto L_0x0071
        L_0x00ad:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x00a2
        L_0x00b2:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x00a5
        L_0x00b7:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0065
        L_0x00bc:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x006a
        L_0x00c1:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0083
        L_0x00c6:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0088
        L_0x00cb:
            r1 = move-exception
            r2 = r0
            r3 = r0
            r0 = r1
            goto L_0x007e
        L_0x00d0:
            r1 = move-exception
            r3 = r2
            r2 = r0
            r0 = r1
            goto L_0x007e
        L_0x00d5:
            r1 = move-exception
            r2 = r0
            r0 = r1
            goto L_0x007e
        L_0x00d9:
            r1 = move-exception
            r2 = r0
            r3 = r0
            goto L_0x0057
        L_0x00de:
            r1 = move-exception
            r3 = r2
            r2 = r0
            goto L_0x0057
        L_0x00e3:
            r1 = move-exception
            r2 = r0
            goto L_0x0057
        L_0x00e7:
            r3 = r0
            goto L_0x0023
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2018y.m2162a(java.io.File, java.lang.String):byte[]");
    }

    /* renamed from: a */
    public static byte[] m2163a(byte[] bArr, int i) {
        if (bArr == null || i == -1) {
            return bArr;
        }
        String str = "[Util] Zip %d bytes data with type %s";
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(bArr.length);
        objArr[1] = i == 2 ? "Gzip" : "zip";
        C2014w.m2117c(str, objArr);
        try {
            C1968aa a = C2019z.m2181a(i);
            if (a == null) {
                return null;
            }
            return a.mo19544a(bArr);
        } catch (Throwable th) {
            if (!C2014w.m2114a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: b */
    public static byte[] m2175b(byte[] bArr, int i) {
        if (bArr == null || i == -1) {
            return bArr;
        }
        String str = "[Util] Unzip %d bytes data with type %s";
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(bArr.length);
        objArr[1] = i == 2 ? "Gzip" : "zip";
        C2014w.m2117c(str, objArr);
        try {
            C1968aa a = C2019z.m2181a(i);
            if (a == null) {
                return null;
            }
            return a.mo19545b(bArr);
        } catch (Throwable th) {
            if (!C2014w.m2114a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    public static byte[] m2164a(byte[] bArr, int i, int i2, String str) {
        byte[] bArr2 = null;
        if (bArr == null) {
            return bArr2;
        }
        try {
            return m2165a(m2163a(bArr, 2), 1, str);
        } catch (Throwable th) {
            if (C2014w.m2114a(th)) {
                return bArr2;
            }
            th.printStackTrace();
            return bArr2;
        }
    }

    /* renamed from: b */
    public static byte[] m2176b(byte[] bArr, int i, int i2, String str) {
        try {
            return m2175b(m2177b(bArr, 1, str), 2);
        } catch (Exception e) {
            if (!C2014w.m2114a(e)) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: b */
    public static long m2166b() {
        try {
            return (((System.currentTimeMillis() + ((long) TimeZone.getDefault().getRawOffset())) / 86400000) * 86400000) - ((long) TimeZone.getDefault().getRawOffset());
        } catch (Throwable th) {
            if (!C2014w.m2114a(th)) {
                th.printStackTrace();
            }
            return -1;
        }
    }

    /* renamed from: a */
    public static String m2149a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                stringBuffer.append(AppEventsConstants.EVENT_PARAM_VALUE_NO);
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString().toUpperCase();
    }

    /* renamed from: b */
    public static String m2168b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "NULL";
        }
        try {
            MessageDigest instance = MessageDigest.getInstance(Constants.SHA1);
            instance.update(bArr);
            return m2149a(instance.digest());
        } catch (Throwable th) {
            if (!C2014w.m2114a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0097 A[Catch:{ all -> 0x0106 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x009c A[SYNTHETIC, Splitter:B:41:0x009c] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00a1 A[SYNTHETIC, Splitter:B:44:0x00a1] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00e7 A[SYNTHETIC, Splitter:B:69:0x00e7] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00ec A[SYNTHETIC, Splitter:B:72:0x00ec] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean m2157a(java.io.File r6, java.io.File r7, int r8) {
        /*
            r3 = 0
            r0 = 0
            java.lang.String r1 = "rqdp{  ZF start}"
            java.lang.Object[] r2 = new java.lang.Object[r0]
            com.tencent.bugly.proguard.C2014w.m2117c(r1, r2)
            if (r6 == 0) goto L_0x0013
            if (r7 == 0) goto L_0x0013
            boolean r1 = r6.equals(r7)
            if (r1 == 0) goto L_0x001b
        L_0x0013:
            java.lang.String r1 = "rqdp{  err ZF 1R!}"
            java.lang.Object[] r2 = new java.lang.Object[r0]
            com.tencent.bugly.proguard.C2014w.m2118d(r1, r2)
        L_0x001a:
            return r0
        L_0x001b:
            boolean r1 = r6.exists()
            if (r1 == 0) goto L_0x0027
            boolean r1 = r6.canRead()
            if (r1 != 0) goto L_0x002f
        L_0x0027:
            java.lang.String r1 = "rqdp{  !sFile.exists() || !sFile.canRead(),pls check ,return!}"
            java.lang.Object[] r2 = new java.lang.Object[r0]
            com.tencent.bugly.proguard.C2014w.m2118d(r1, r2)
            goto L_0x001a
        L_0x002f:
            java.io.File r1 = r7.getParentFile()     // Catch:{ Throwable -> 0x00ad }
            if (r1 == 0) goto L_0x0046
            java.io.File r1 = r7.getParentFile()     // Catch:{ Throwable -> 0x00ad }
            boolean r1 = r1.exists()     // Catch:{ Throwable -> 0x00ad }
            if (r1 != 0) goto L_0x0046
            java.io.File r1 = r7.getParentFile()     // Catch:{ Throwable -> 0x00ad }
            r1.mkdirs()     // Catch:{ Throwable -> 0x00ad }
        L_0x0046:
            boolean r1 = r7.exists()     // Catch:{ Throwable -> 0x00ad }
            if (r1 != 0) goto L_0x004f
            r7.createNewFile()     // Catch:{ Throwable -> 0x00ad }
        L_0x004f:
            boolean r1 = r7.exists()
            if (r1 == 0) goto L_0x001a
            boolean r1 = r7.canRead()
            if (r1 == 0) goto L_0x001a
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0109, all -> 0x00e2 }
            r4.<init>(r6)     // Catch:{ Throwable -> 0x0109, all -> 0x00e2 }
            java.util.zip.ZipOutputStream r2 = new java.util.zip.ZipOutputStream     // Catch:{ Throwable -> 0x010c, all -> 0x0101 }
            java.io.BufferedOutputStream r1 = new java.io.BufferedOutputStream     // Catch:{ Throwable -> 0x010c, all -> 0x0101 }
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x010c, all -> 0x0101 }
            r5.<init>(r7)     // Catch:{ Throwable -> 0x010c, all -> 0x0101 }
            r1.<init>(r5)     // Catch:{ Throwable -> 0x010c, all -> 0x0101 }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x010c, all -> 0x0101 }
            r1 = 8
            r2.setMethod(r1)     // Catch:{ Throwable -> 0x008f, all -> 0x0104 }
            java.util.zip.ZipEntry r1 = new java.util.zip.ZipEntry     // Catch:{ Throwable -> 0x008f, all -> 0x0104 }
            java.lang.String r3 = r6.getName()     // Catch:{ Throwable -> 0x008f, all -> 0x0104 }
            r1.<init>(r3)     // Catch:{ Throwable -> 0x008f, all -> 0x0104 }
            r2.putNextEntry(r1)     // Catch:{ Throwable -> 0x008f, all -> 0x0104 }
            r1 = 5000(0x1388, float:7.006E-42)
            byte[] r1 = new byte[r1]     // Catch:{ Throwable -> 0x008f, all -> 0x0104 }
        L_0x0084:
            int r3 = r4.read(r1)     // Catch:{ Throwable -> 0x008f, all -> 0x0104 }
            if (r3 <= 0) goto L_0x00b8
            r5 = 0
            r2.write(r1, r5, r3)     // Catch:{ Throwable -> 0x008f, all -> 0x0104 }
            goto L_0x0084
        L_0x008f:
            r1 = move-exception
            r3 = r4
        L_0x0091:
            boolean r4 = com.tencent.bugly.proguard.C2014w.m2114a(r1)     // Catch:{ all -> 0x0106 }
            if (r4 != 0) goto L_0x009a
            r1.printStackTrace()     // Catch:{ all -> 0x0106 }
        L_0x009a:
            if (r3 == 0) goto L_0x009f
            r3.close()     // Catch:{ IOException -> 0x00d8 }
        L_0x009f:
            if (r2 == 0) goto L_0x00a4
            r2.close()     // Catch:{ IOException -> 0x00dd }
        L_0x00a4:
            java.lang.String r1 = "rqdp{  ZF end}"
            java.lang.Object[] r2 = new java.lang.Object[r0]
            com.tencent.bugly.proguard.C2014w.m2117c(r1, r2)
            goto L_0x001a
        L_0x00ad:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.proguard.C2014w.m2114a(r1)
            if (r2 != 0) goto L_0x004f
            r1.printStackTrace()
            goto L_0x004f
        L_0x00b8:
            r2.flush()     // Catch:{ Throwable -> 0x008f, all -> 0x0104 }
            r2.closeEntry()     // Catch:{ Throwable -> 0x008f, all -> 0x0104 }
            r4.close()     // Catch:{ IOException -> 0x00ce }
        L_0x00c1:
            r2.close()     // Catch:{ IOException -> 0x00d3 }
        L_0x00c4:
            java.lang.String r1 = "rqdp{  ZF end}"
            java.lang.Object[] r0 = new java.lang.Object[r0]
            com.tencent.bugly.proguard.C2014w.m2117c(r1, r0)
            r0 = 1
            goto L_0x001a
        L_0x00ce:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x00c1
        L_0x00d3:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x00c4
        L_0x00d8:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x009f
        L_0x00dd:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x00a4
        L_0x00e2:
            r1 = move-exception
            r2 = r3
            r4 = r3
        L_0x00e5:
            if (r4 == 0) goto L_0x00ea
            r4.close()     // Catch:{ IOException -> 0x00f7 }
        L_0x00ea:
            if (r2 == 0) goto L_0x00ef
            r2.close()     // Catch:{ IOException -> 0x00fc }
        L_0x00ef:
            java.lang.String r2 = "rqdp{  ZF end}"
            java.lang.Object[] r0 = new java.lang.Object[r0]
            com.tencent.bugly.proguard.C2014w.m2117c(r2, r0)
            throw r1
        L_0x00f7:
            r3 = move-exception
            r3.printStackTrace()
            goto L_0x00ea
        L_0x00fc:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x00ef
        L_0x0101:
            r1 = move-exception
            r2 = r3
            goto L_0x00e5
        L_0x0104:
            r1 = move-exception
            goto L_0x00e5
        L_0x0106:
            r1 = move-exception
            r4 = r3
            goto L_0x00e5
        L_0x0109:
            r1 = move-exception
            r2 = r3
            goto L_0x0091
        L_0x010c:
            r1 = move-exception
            r2 = r3
            r3 = r4
            goto L_0x0091
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2018y.m2157a(java.io.File, java.io.File, int):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0049 A[Catch:{ all -> 0x00a6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x004e A[SYNTHETIC, Splitter:B:16:0x004e] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0053 A[SYNTHETIC, Splitter:B:19:0x0053] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0091 A[SYNTHETIC, Splitter:B:44:0x0091] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0096 A[SYNTHETIC, Splitter:B:47:0x0096] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.ArrayList<java.lang.String> m2151a(android.content.Context r6, java.lang.String[] r7) {
        /*
            r1 = 0
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            com.tencent.bugly.crashreport.common.info.a r2 = com.tencent.bugly.crashreport.common.info.C1938a.m1667a(r6)
            boolean r2 = r2.mo19382H()
            if (r2 == 0) goto L_0x0021
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.String r1 = new java.lang.String
            java.lang.String r2 = "unknown(low memory)"
            r1.<init>(r2)
            r0.add(r1)
        L_0x0020:
            return r0
        L_0x0021:
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch:{ Throwable -> 0x00a9, all -> 0x008d }
            java.lang.Process r4 = r2.exec(r7)     // Catch:{ Throwable -> 0x00a9, all -> 0x008d }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00a9, all -> 0x008d }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x00a9, all -> 0x008d }
            java.io.InputStream r5 = r4.getInputStream()     // Catch:{ Throwable -> 0x00a9, all -> 0x008d }
            r2.<init>(r5)     // Catch:{ Throwable -> 0x00a9, all -> 0x008d }
            r3.<init>(r2)     // Catch:{ Throwable -> 0x00a9, all -> 0x008d }
        L_0x0037:
            java.lang.String r2 = r3.readLine()     // Catch:{ Throwable -> 0x0041, all -> 0x00a4 }
            if (r2 == 0) goto L_0x0058
            r0.add(r2)     // Catch:{ Throwable -> 0x0041, all -> 0x00a4 }
            goto L_0x0037
        L_0x0041:
            r0 = move-exception
            r2 = r1
        L_0x0043:
            boolean r4 = com.tencent.bugly.proguard.C2014w.m2114a(r0)     // Catch:{ all -> 0x00a6 }
            if (r4 != 0) goto L_0x004c
            r0.printStackTrace()     // Catch:{ all -> 0x00a6 }
        L_0x004c:
            if (r3 == 0) goto L_0x0051
            r3.close()     // Catch:{ IOException -> 0x0083 }
        L_0x0051:
            if (r2 == 0) goto L_0x0056
            r2.close()     // Catch:{ IOException -> 0x0088 }
        L_0x0056:
            r0 = r1
            goto L_0x0020
        L_0x0058:
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0041, all -> 0x00a4 }
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x0041, all -> 0x00a4 }
            java.io.InputStream r4 = r4.getErrorStream()     // Catch:{ Throwable -> 0x0041, all -> 0x00a4 }
            r5.<init>(r4)     // Catch:{ Throwable -> 0x0041, all -> 0x00a4 }
            r2.<init>(r5)     // Catch:{ Throwable -> 0x0041, all -> 0x00a4 }
        L_0x0066:
            java.lang.String r4 = r2.readLine()     // Catch:{ Throwable -> 0x0070 }
            if (r4 == 0) goto L_0x0072
            r0.add(r4)     // Catch:{ Throwable -> 0x0070 }
            goto L_0x0066
        L_0x0070:
            r0 = move-exception
            goto L_0x0043
        L_0x0072:
            r3.close()     // Catch:{ IOException -> 0x007e }
        L_0x0075:
            r2.close()     // Catch:{ IOException -> 0x0079 }
            goto L_0x0020
        L_0x0079:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0020
        L_0x007e:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0075
        L_0x0083:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0051
        L_0x0088:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0056
        L_0x008d:
            r0 = move-exception
            r3 = r1
        L_0x008f:
            if (r3 == 0) goto L_0x0094
            r3.close()     // Catch:{ IOException -> 0x009a }
        L_0x0094:
            if (r1 == 0) goto L_0x0099
            r1.close()     // Catch:{ IOException -> 0x009f }
        L_0x0099:
            throw r0
        L_0x009a:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0094
        L_0x009f:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0099
        L_0x00a4:
            r0 = move-exception
            goto L_0x008f
        L_0x00a6:
            r0 = move-exception
            r1 = r2
            goto L_0x008f
        L_0x00a9:
            r0 = move-exception
            r2 = r1
            r3 = r1
            goto L_0x0043
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2018y.m2151a(android.content.Context, java.lang.String[]):java.util.ArrayList");
    }

    /* renamed from: a */
    public static String m2146a(Context context, String str) {
        if (str == null || str.trim().equals("")) {
            return "";
        }
        if (f1715a == null) {
            f1715a = new HashMap();
            ArrayList<String> a = m2151a(context, new String[]{"/system/bin/sh", "-c", "getprop"});
            if (a != null && a.size() > 0) {
                C2014w.m2112a(C2018y.class, "Successfully get 'getprop' list.", new Object[0]);
                Pattern compile = Pattern.compile("\\[(.+)\\]: \\[(.*)\\]");
                for (String matcher : a) {
                    Matcher matcher2 = compile.matcher(matcher);
                    if (matcher2.find()) {
                        f1715a.put(matcher2.group(1), matcher2.group(2));
                    }
                }
                C2014w.m2112a(C2018y.class, "System properties number: %dffffdsfsdfff.", Integer.valueOf(f1715a.size()));
            }
        }
        if (f1715a.containsKey(str)) {
            return (String) f1715a.get(str);
        }
        return "fail";
    }

    /* renamed from: b */
    public static void m2170b(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static boolean m2158a(String str) {
        if (str == null || str.trim().length() <= 0) {
            return true;
        }
        return false;
    }

    /* renamed from: b */
    public static void m2172b(String str) {
        if (str != null) {
            File file = new File(str);
            if (file.isFile() && file.exists() && file.canWrite()) {
                file.delete();
            }
        }
    }

    /* renamed from: c */
    public static byte[] m2180c(long j) {
        try {
            return (j).getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    public static long m2178c(byte[] bArr) {
        long j = -1;
        if (bArr == null) {
            return j;
        }
        try {
            return Long.parseLong(new String(bArr, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return j;
        }
    }

    /* renamed from: a */
    public static Context m2138a(Context context) {
        if (context == null) {
            return context;
        }
        Context applicationContext = context.getApplicationContext();
        return applicationContext != null ? applicationContext : context;
    }

    /* renamed from: b */
    public static String m2167b(Throwable th) {
        if (th == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.flush();
        return stringWriter.toString();
    }

    /* renamed from: a */
    public static void m2155a(Class<?> cls, String str, Object obj, Object obj2) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(null, obj);
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    public static Object m2141a(String str, String str2, Object obj, Class<?>[] clsArr, Object[] objArr) {
        boolean z = false;
        try {
            Method declaredMethod = Class.forName(str).getDeclaredMethod(str2, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(null, objArr);
        } catch (Exception e) {
            return z;
        }
    }

    /* renamed from: a */
    public static void m2154a(Parcel parcel, Map<String, PlugInBean> map) {
        if (map == null || map.size() <= 0) {
            parcel.writeBundle(null);
            return;
        }
        int size = map.size();
        ArrayList arrayList = new ArrayList(size);
        ArrayList arrayList2 = new ArrayList(size);
        for (Entry entry : map.entrySet()) {
            arrayList.add(entry.getKey());
            arrayList2.add(entry.getValue());
        }
        Bundle bundle = new Bundle();
        bundle.putInt("pluginNum", arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            bundle.putString("pluginKey" + i, (String) arrayList.get(i));
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            bundle.putString("pluginVal" + i2 + "plugInId", ((PlugInBean) arrayList2.get(i2)).f1152a);
            bundle.putString("pluginVal" + i2 + "plugInUUID", ((PlugInBean) arrayList2.get(i2)).f1154c);
            bundle.putString("pluginVal" + i2 + "plugInVersion", ((PlugInBean) arrayList2.get(i2)).f1153b);
        }
        parcel.writeBundle(bundle);
    }

    /* renamed from: a */
    public static Map<String, PlugInBean> m2153a(Parcel parcel) {
        HashMap hashMap;
        Bundle readBundle = parcel.readBundle();
        if (readBundle == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int intValue = ((Integer) readBundle.get("pluginNum")).intValue();
        for (int i = 0; i < intValue; i++) {
            arrayList.add(readBundle.getString("pluginKey" + i));
        }
        for (int i2 = 0; i2 < intValue; i2++) {
            arrayList2.add(new PlugInBean(readBundle.getString("pluginVal" + i2 + "plugInId"), readBundle.getString("pluginVal" + i2 + "plugInVersion"), readBundle.getString("pluginVal" + i2 + "plugInUUID")));
        }
        if (arrayList.size() == arrayList2.size()) {
            HashMap hashMap2 = new HashMap(arrayList.size());
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                hashMap2.put(arrayList.get(i3), PlugInBean.class.cast(arrayList2.get(i3)));
            }
            hashMap = hashMap2;
        } else {
            C2014w.m2119e("map plugin parcel error!", new Object[0]);
            hashMap = null;
        }
        return hashMap;
    }

    /* renamed from: b */
    public static void m2171b(Parcel parcel, Map<String, String> map) {
        if (map == null || map.size() <= 0) {
            parcel.writeBundle(null);
            return;
        }
        int size = map.size();
        ArrayList arrayList = new ArrayList(size);
        ArrayList arrayList2 = new ArrayList(size);
        for (Entry entry : map.entrySet()) {
            arrayList.add(entry.getKey());
            arrayList2.add(entry.getValue());
        }
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("keys", arrayList);
        bundle.putStringArrayList("values", arrayList2);
        parcel.writeBundle(bundle);
    }

    /* renamed from: b */
    public static Map<String, String> m2169b(Parcel parcel) {
        HashMap hashMap;
        Bundle readBundle = parcel.readBundle();
        if (readBundle == null) {
            return null;
        }
        ArrayList stringArrayList = readBundle.getStringArrayList("keys");
        ArrayList stringArrayList2 = readBundle.getStringArrayList("values");
        if (stringArrayList == null || stringArrayList2 == null || stringArrayList.size() != stringArrayList2.size()) {
            C2014w.m2119e("map parcel error!", new Object[0]);
            hashMap = null;
        } else {
            HashMap hashMap2 = new HashMap(stringArrayList.size());
            for (int i = 0; i < stringArrayList.size(); i++) {
                hashMap2.put(stringArrayList.get(i), stringArrayList2.get(i));
            }
            hashMap = hashMap2;
        }
        return hashMap;
    }

    /* renamed from: a */
    public static byte[] m2161a(Parcelable parcelable) {
        Parcel obtain = Parcel.obtain();
        parcelable.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }

    /* renamed from: a */
    public static <T> T m2142a(byte[] bArr, Creator<T> creator) {
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        try {
            T createFromParcel = creator.createFromParcel(obtain);
            if (obtain == null) {
                return createFromParcel;
            }
            obtain.recycle();
            return createFromParcel;
        } catch (Throwable th) {
            if (obtain != null) {
                obtain.recycle();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x0108 A[SYNTHETIC, Splitter:B:55:0x0108] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m2145a(android.content.Context r7, int r8, java.lang.String r9) {
        /*
            r6 = 4
            r5 = 3
            r4 = 2
            r3 = 1
            r2 = 0
            java.lang.String r0 = "android.permission.READ_LOGS"
            boolean r0 = com.tencent.bugly.crashreport.common.info.AppInfo.m1660a(r7, r0)
            if (r0 != 0) goto L_0x0016
            java.lang.String r0 = "no read_log permission!"
            java.lang.Object[] r1 = new java.lang.Object[r2]
            com.tencent.bugly.proguard.C2014w.m2118d(r0, r1)
            r0 = 0
        L_0x0015:
            return r0
        L_0x0016:
            if (r9 != 0) goto L_0x00b2
            java.lang.String[] r0 = new java.lang.String[r6]
            java.lang.String r1 = "logcat"
            r0[r2] = r1
            java.lang.String r1 = "-d"
            r0[r3] = r1
            java.lang.String r1 = "-v"
            r0[r4] = r1
            java.lang.String r1 = "threadtime"
            r0[r5] = r1
        L_0x002a:
            r1 = 0
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch:{ Throwable -> 0x0130 }
            java.lang.Process r2 = r2.exec(r0)     // Catch:{ Throwable -> 0x0130 }
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0067, all -> 0x012d }
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x0067, all -> 0x012d }
            java.io.InputStream r4 = r2.getInputStream()     // Catch:{ Throwable -> 0x0067, all -> 0x012d }
            r1.<init>(r4)     // Catch:{ Throwable -> 0x0067, all -> 0x012d }
            r0.<init>(r1)     // Catch:{ Throwable -> 0x0067, all -> 0x012d }
        L_0x0046:
            java.lang.String r1 = r0.readLine()     // Catch:{ Throwable -> 0x0067, all -> 0x012d }
            if (r1 == 0) goto L_0x00ce
            java.lang.StringBuilder r1 = r3.append(r1)     // Catch:{ Throwable -> 0x0067, all -> 0x012d }
            java.lang.String r4 = "\n"
            r1.append(r4)     // Catch:{ Throwable -> 0x0067, all -> 0x012d }
            if (r8 <= 0) goto L_0x0046
            int r1 = r3.length()     // Catch:{ Throwable -> 0x0067, all -> 0x012d }
            if (r1 <= r8) goto L_0x0046
            r1 = 0
            int r4 = r3.length()     // Catch:{ Throwable -> 0x0067, all -> 0x012d }
            int r4 = r4 - r8
            r3.delete(r1, r4)     // Catch:{ Throwable -> 0x0067, all -> 0x012d }
            goto L_0x0046
        L_0x0067:
            r0 = move-exception
            r1 = r2
        L_0x0069:
            boolean r2 = com.tencent.bugly.proguard.C2014w.m2114a(r0)     // Catch:{ all -> 0x0105 }
            if (r2 != 0) goto L_0x0072
            r0.printStackTrace()     // Catch:{ all -> 0x0105 }
        L_0x0072:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0105 }
            java.lang.String r4 = "\n[error:"
            r2.<init>(r4)     // Catch:{ all -> 0x0105 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0105 }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ all -> 0x0105 }
            java.lang.String r2 = "]"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x0105 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0105 }
            java.lang.StringBuilder r0 = r3.append(r0)     // Catch:{ all -> 0x0105 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0105 }
            if (r1 == 0) goto L_0x0015
            java.io.OutputStream r2 = r1.getOutputStream()     // Catch:{ IOException -> 0x00fb }
            r2.close()     // Catch:{ IOException -> 0x00fb }
        L_0x009c:
            java.io.InputStream r2 = r1.getInputStream()     // Catch:{ IOException -> 0x0100 }
            r2.close()     // Catch:{ IOException -> 0x0100 }
        L_0x00a3:
            java.io.InputStream r1 = r1.getErrorStream()     // Catch:{ IOException -> 0x00ac }
            r1.close()     // Catch:{ IOException -> 0x00ac }
            goto L_0x0015
        L_0x00ac:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0015
        L_0x00b2:
            r0 = 6
            java.lang.String[] r0 = new java.lang.String[r0]
            java.lang.String r1 = "logcat"
            r0[r2] = r1
            java.lang.String r1 = "-d"
            r0[r3] = r1
            java.lang.String r1 = "-v"
            r0[r4] = r1
            java.lang.String r1 = "threadtime"
            r0[r5] = r1
            java.lang.String r1 = "-s"
            r0[r6] = r1
            r1 = 5
            r0[r1] = r9
            goto L_0x002a
        L_0x00ce:
            java.lang.String r0 = r3.toString()     // Catch:{ Throwable -> 0x0067, all -> 0x012d }
            if (r2 == 0) goto L_0x0015
            java.io.OutputStream r1 = r2.getOutputStream()     // Catch:{ IOException -> 0x00f1 }
            r1.close()     // Catch:{ IOException -> 0x00f1 }
        L_0x00db:
            java.io.InputStream r1 = r2.getInputStream()     // Catch:{ IOException -> 0x00f6 }
            r1.close()     // Catch:{ IOException -> 0x00f6 }
        L_0x00e2:
            java.io.InputStream r1 = r2.getErrorStream()     // Catch:{ IOException -> 0x00eb }
            r1.close()     // Catch:{ IOException -> 0x00eb }
            goto L_0x0015
        L_0x00eb:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0015
        L_0x00f1:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x00db
        L_0x00f6:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x00e2
        L_0x00fb:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x009c
        L_0x0100:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x00a3
        L_0x0105:
            r0 = move-exception
        L_0x0106:
            if (r1 == 0) goto L_0x011d
            java.io.OutputStream r2 = r1.getOutputStream()     // Catch:{ IOException -> 0x011e }
            r2.close()     // Catch:{ IOException -> 0x011e }
        L_0x010f:
            java.io.InputStream r2 = r1.getInputStream()     // Catch:{ IOException -> 0x0123 }
            r2.close()     // Catch:{ IOException -> 0x0123 }
        L_0x0116:
            java.io.InputStream r1 = r1.getErrorStream()     // Catch:{ IOException -> 0x0128 }
            r1.close()     // Catch:{ IOException -> 0x0128 }
        L_0x011d:
            throw r0
        L_0x011e:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x010f
        L_0x0123:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0116
        L_0x0128:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x011d
        L_0x012d:
            r0 = move-exception
            r1 = r2
            goto L_0x0106
        L_0x0130:
            r0 = move-exception
            goto L_0x0069
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2018y.m2145a(android.content.Context, int, java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    public static Map<String, String> m2152a(int i, boolean z) {
        HashMap hashMap = new HashMap(12);
        Map allStackTraces = Thread.getAllStackTraces();
        if (allStackTraces == null) {
            return null;
        }
        Thread.currentThread().getId();
        StringBuilder sb = new StringBuilder();
        for (Entry entry : allStackTraces.entrySet()) {
            sb.setLength(0);
            if (!(entry.getValue() == null || ((StackTraceElement[]) entry.getValue()).length == 0)) {
                StackTraceElement[] stackTraceElementArr = (StackTraceElement[]) entry.getValue();
                int length = stackTraceElementArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    StackTraceElement stackTraceElement = stackTraceElementArr[i2];
                    if (i > 0 && sb.length() >= i) {
                        sb.append("\n[Stack over limit size :" + i + " , has been cutted !]");
                        break;
                    }
                    sb.append(stackTraceElement.toString()).append(IOUtils.LINE_SEPARATOR_UNIX);
                    i2++;
                }
                hashMap.put(((Thread) entry.getKey()).getName() + "(" + ((Thread) entry.getKey()).getId() + ")", sb.toString());
            }
        }
        return hashMap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0030 A[SYNTHETIC, Splitter:B:17:0x0030] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0050 A[Catch:{ Exception -> 0x0054 }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized byte[] m2159a(int r7) {
        /*
            r1 = 0
            java.lang.Class<com.tencent.bugly.proguard.y> r3 = com.tencent.bugly.proguard.C2018y.class
            monitor-enter(r3)
            r0 = 16
            byte[] r0 = new byte[r0]     // Catch:{ Exception -> 0x0021, all -> 0x004c }
            java.io.DataInputStream r2 = new java.io.DataInputStream     // Catch:{ Exception -> 0x0021, all -> 0x004c }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0021, all -> 0x004c }
            java.io.File r5 = new java.io.File     // Catch:{ Exception -> 0x0021, all -> 0x004c }
            java.lang.String r6 = "/dev/urandom"
            r5.<init>(r6)     // Catch:{ Exception -> 0x0021, all -> 0x004c }
            r4.<init>(r5)     // Catch:{ Exception -> 0x0021, all -> 0x004c }
            r2.<init>(r4)     // Catch:{ Exception -> 0x0021, all -> 0x004c }
            r2.readFully(r0)     // Catch:{ Exception -> 0x0065 }
            r2.close()     // Catch:{ Exception -> 0x0054 }
        L_0x001f:
            monitor-exit(r3)
            return r0
        L_0x0021:
            r0 = move-exception
            r2 = r1
        L_0x0023:
            java.lang.String r4 = "Failed to read from /dev/urandom : %s"
            r5 = 1
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x0063 }
            r6 = 0
            r5[r6] = r0     // Catch:{ all -> 0x0063 }
            com.tencent.bugly.proguard.C2014w.m2119e(r4, r5)     // Catch:{ all -> 0x0063 }
            if (r2 == 0) goto L_0x0033
            r2.close()     // Catch:{ Exception -> 0x0054 }
        L_0x0033:
            java.lang.String r0 = "AES"
            javax.crypto.KeyGenerator r0 = javax.crypto.KeyGenerator.getInstance(r0)     // Catch:{ Exception -> 0x0054 }
            r2 = 128(0x80, float:1.794E-43)
            java.security.SecureRandom r4 = new java.security.SecureRandom     // Catch:{ Exception -> 0x0054 }
            r4.<init>()     // Catch:{ Exception -> 0x0054 }
            r0.init(r2, r4)     // Catch:{ Exception -> 0x0054 }
            javax.crypto.SecretKey r0 = r0.generateKey()     // Catch:{ Exception -> 0x0054 }
            byte[] r0 = r0.getEncoded()     // Catch:{ Exception -> 0x0054 }
            goto L_0x001f
        L_0x004c:
            r0 = move-exception
            r2 = r1
        L_0x004e:
            if (r2 == 0) goto L_0x0053
            r2.close()     // Catch:{ Exception -> 0x0054 }
        L_0x0053:
            throw r0     // Catch:{ Exception -> 0x0054 }
        L_0x0054:
            r0 = move-exception
            boolean r2 = com.tencent.bugly.proguard.C2014w.m2116b(r0)     // Catch:{ all -> 0x0060 }
            if (r2 != 0) goto L_0x005e
            r0.printStackTrace()     // Catch:{ all -> 0x0060 }
        L_0x005e:
            r0 = r1
            goto L_0x001f
        L_0x0060:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        L_0x0063:
            r0 = move-exception
            goto L_0x004e
        L_0x0065:
            r0 = move-exception
            goto L_0x0023
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2018y.m2159a(int):byte[]");
    }

    /* renamed from: a */
    public static byte[] m2160a(int i, byte[] bArr, byte[] bArr2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
            Cipher instance = Cipher.getInstance("AES/GCM/NoPadding");
            instance.init(i, secretKeySpec, new IvParameterSpec(bArr2));
            return instance.doFinal(bArr);
        } catch (Exception e) {
            if (!C2014w.m2116b(e)) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: b */
    public static byte[] m2174b(int i, byte[] bArr, byte[] bArr2) {
        try {
            PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bArr2));
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(1, generatePublic);
            return instance.doFinal(bArr);
        } catch (Exception e) {
            if (!C2014w.m2116b(e)) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m2156a(Context context, String str, long j) {
        C2014w.m2117c("[Util] try to lock file:%s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            File file = new File(context.getFilesDir() + File.separator + str);
            if (file.exists()) {
                if (System.currentTimeMillis() - file.lastModified() < j) {
                    return false;
                }
                C2014w.m2117c("[Util] lock file(%s) is expired, unlock it", str);
                m2173b(context, str);
            }
            if (file.createNewFile()) {
                C2014w.m2117c("[Util] successfully locked file:%s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                return true;
            }
            C2014w.m2117c("[Util] Failed to locked file:%s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            return false;
        } catch (Throwable th) {
            C2014w.m2114a(th);
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m2173b(Context context, String str) {
        C2014w.m2117c("[Util] try to unlock file:%s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            File file = new File(context.getFilesDir() + File.separator + str);
            if (!file.exists()) {
                return true;
            }
            if (!file.delete()) {
                return false;
            }
            C2014w.m2117c("[Util] successfully unlocked file:%s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            return true;
        } catch (Throwable th) {
            C2014w.m2114a(th);
            return false;
        }
    }

    /* renamed from: a */
    private static BufferedReader m2139a(File file) {
        if (file == null || !file.exists() || !file.canRead()) {
            return null;
        }
        try {
            return new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
        } catch (Throwable th) {
            C2014w.m2114a(th);
            return null;
        }
    }

    /* renamed from: a */
    public static BufferedReader m2140a(String str, String str2) {
        if (str == null) {
            return null;
        }
        try {
            File file = new File(str, str2);
            if (!file.exists() || !file.canRead()) {
                return null;
            }
            return m2139a(file);
        } catch (NullPointerException e) {
            C2014w.m2114a(e);
            return null;
        }
    }

    /* renamed from: a */
    public static Thread m2150a(Runnable runnable, String str) {
        try {
            Thread thread = new Thread(runnable);
            thread.setName(str);
            thread.start();
            return thread;
        } catch (Throwable th) {
            C2014w.m2119e("[Util] Failed to start a thread to execute task with message: %s", th.getMessage());
            return null;
        }
    }

    /* renamed from: c */
    public static boolean m2179c(String str) {
        boolean z;
        if (str == null || str.trim().length() <= 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return false;
        }
        if (str.length() > 255) {
            C2014w.m2113a("URL's length is larger than 255.", new Object[0]);
            return false;
        } else if (!str.toLowerCase().startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
            C2014w.m2113a("URL is not start with \"http\".", new Object[0]);
            return false;
        } else if (str.toLowerCase().contains("qq.com")) {
            return true;
        } else {
            C2014w.m2113a("URL does not contain \"qq.com\".", new Object[0]);
            return false;
        }
    }
}
