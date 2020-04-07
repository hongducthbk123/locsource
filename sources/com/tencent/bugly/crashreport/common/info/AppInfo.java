package com.tencent.bugly.crashreport.common.info;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import com.adjust.sdk.Constants;
import com.tencent.bugly.proguard.C2014w;
import com.tencent.bugly.proguard.C2018y;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.Principal;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.p052io.IOUtils;

/* compiled from: BUGLY */
public class AppInfo {

    /* renamed from: a */
    private static ActivityManager f1151a;

    static {
        "@buglyAllChannel@".split(",");
        "@buglyAllChannelPriority@".split(",");
    }

    /* renamed from: a */
    public static String m1657a(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return context.getPackageName();
        } catch (Throwable th) {
            if (!C2014w.m2114a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    /* renamed from: b */
    public static PackageInfo m1661b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(m1657a(context), 0);
        } catch (Throwable th) {
            if (!C2014w.m2114a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m1660a(Context context, String str) {
        if (context == null || str == null || str.trim().length() <= 0) {
            return false;
        }
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            if (strArr == null) {
                return false;
            }
            for (String equals : strArr) {
                if (str.equals(equals)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            if (C2014w.m2114a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0043 A[Catch:{ all -> 0x005e }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004c A[SYNTHETIC, Splitter:B:23:0x004c] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0056 A[SYNTHETIC, Splitter:B:29:0x0056] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m1656a(int r5) {
        /*
            r0 = 0
            r2 = 0
            java.io.FileReader r1 = new java.io.FileReader     // Catch:{ Throwable -> 0x003b, all -> 0x0052 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x003b, all -> 0x0052 }
            java.lang.String r4 = "/proc/"
            r3.<init>(r4)     // Catch:{ Throwable -> 0x003b, all -> 0x0052 }
            java.lang.StringBuilder r3 = r3.append(r5)     // Catch:{ Throwable -> 0x003b, all -> 0x0052 }
            java.lang.String r4 = "/cmdline"
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Throwable -> 0x003b, all -> 0x0052 }
            java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x003b, all -> 0x0052 }
            r1.<init>(r3)     // Catch:{ Throwable -> 0x003b, all -> 0x0052 }
            r2 = 512(0x200, float:7.175E-43)
            char[] r2 = new char[r2]     // Catch:{ Throwable -> 0x0060 }
            r1.read(r2)     // Catch:{ Throwable -> 0x0060 }
        L_0x0023:
            int r3 = r2.length     // Catch:{ Throwable -> 0x0060 }
            if (r0 >= r3) goto L_0x002d
            char r3 = r2[r0]     // Catch:{ Throwable -> 0x0060 }
            if (r3 == 0) goto L_0x002d
            int r0 = r0 + 1
            goto L_0x0023
        L_0x002d:
            java.lang.String r3 = new java.lang.String     // Catch:{ Throwable -> 0x0060 }
            r3.<init>(r2)     // Catch:{ Throwable -> 0x0060 }
            r2 = 0
            java.lang.String r0 = r3.substring(r2, r0)     // Catch:{ Throwable -> 0x0060 }
            r1.close()     // Catch:{ Throwable -> 0x005a }
        L_0x003a:
            return r0
        L_0x003b:
            r0 = move-exception
            r1 = r2
        L_0x003d:
            boolean r2 = com.tencent.bugly.proguard.C2014w.m2114a(r0)     // Catch:{ all -> 0x005e }
            if (r2 != 0) goto L_0x0046
            r0.printStackTrace()     // Catch:{ all -> 0x005e }
        L_0x0046:
            java.lang.String r0 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x005e }
            if (r1 == 0) goto L_0x003a
            r1.close()     // Catch:{ Throwable -> 0x0050 }
            goto L_0x003a
        L_0x0050:
            r1 = move-exception
            goto L_0x003a
        L_0x0052:
            r0 = move-exception
            r1 = r2
        L_0x0054:
            if (r1 == 0) goto L_0x0059
            r1.close()     // Catch:{ Throwable -> 0x005c }
        L_0x0059:
            throw r0
        L_0x005a:
            r1 = move-exception
            goto L_0x003a
        L_0x005c:
            r1 = move-exception
            goto L_0x0059
        L_0x005e:
            r0 = move-exception
            goto L_0x0054
        L_0x0060:
            r0 = move-exception
            goto L_0x003d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.AppInfo.m1656a(int):java.lang.String");
    }

    /* renamed from: c */
    public static String m1662c(Context context) {
        if (context == null) {
            return null;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (packageManager == null || applicationInfo == null) {
                return null;
            }
            return packageManager.getApplicationLabel(applicationInfo).toString();
        } catch (Throwable th) {
            if (C2014w.m2114a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: d */
    public static Map<String, String> m1663d(Context context) {
        HashMap hashMap;
        if (context == null) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData != null) {
                hashMap = new HashMap();
                Object obj = applicationInfo.metaData.get("BUGLY_DISABLE");
                if (obj != null) {
                    hashMap.put("BUGLY_DISABLE", obj.toString());
                }
                Object obj2 = applicationInfo.metaData.get("BUGLY_APPID");
                if (obj2 != null) {
                    hashMap.put("BUGLY_APPID", obj2.toString());
                }
                Object obj3 = applicationInfo.metaData.get("BUGLY_APP_CHANNEL");
                if (obj3 != null) {
                    hashMap.put("BUGLY_APP_CHANNEL", obj3.toString());
                }
                Object obj4 = applicationInfo.metaData.get("BUGLY_APP_VERSION");
                if (obj4 != null) {
                    hashMap.put("BUGLY_APP_VERSION", obj4.toString());
                }
                Object obj5 = applicationInfo.metaData.get("BUGLY_ENABLE_DEBUG");
                if (obj5 != null) {
                    hashMap.put("BUGLY_ENABLE_DEBUG", obj5.toString());
                }
                Object obj6 = applicationInfo.metaData.get("com.tencent.rdm.uuid");
                if (obj6 != null) {
                    hashMap.put("com.tencent.rdm.uuid", obj6.toString());
                }
            } else {
                hashMap = null;
            }
            return hashMap;
        } catch (Throwable th) {
            if (C2014w.m2114a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static List<String> m1659a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        try {
            String str = (String) map.get("BUGLY_DISABLE");
            if (str == null || str.length() == 0) {
                return null;
            }
            String[] split = str.split(",");
            for (int i = 0; i < split.length; i++) {
                split[i] = split[i].trim();
            }
            return Arrays.asList(split);
        } catch (Throwable th) {
            if (!C2014w.m2114a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    private static String m1658a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr != null && bArr.length > 0) {
            try {
                CertificateFactory instance = CertificateFactory.getInstance("X.509");
                if (instance == null) {
                    return null;
                }
                X509Certificate x509Certificate = (X509Certificate) instance.generateCertificate(new ByteArrayInputStream(bArr));
                if (x509Certificate == null) {
                    return null;
                }
                sb.append("Issuer|");
                Principal issuerDN = x509Certificate.getIssuerDN();
                if (issuerDN != null) {
                    sb.append(issuerDN.toString());
                } else {
                    sb.append("unknown");
                }
                sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                sb.append("SerialNumber|");
                BigInteger serialNumber = x509Certificate.getSerialNumber();
                if (issuerDN != null) {
                    sb.append(serialNumber.toString(16));
                } else {
                    sb.append("unknown");
                }
                sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                sb.append("NotBefore|");
                Date notBefore = x509Certificate.getNotBefore();
                if (issuerDN != null) {
                    sb.append(notBefore.toString());
                } else {
                    sb.append("unknown");
                }
                sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                sb.append("NotAfter|");
                Date notAfter = x509Certificate.getNotAfter();
                if (issuerDN != null) {
                    sb.append(notAfter.toString());
                } else {
                    sb.append("unknown");
                }
                sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                sb.append("SHA1|");
                String a = C2018y.m2149a(MessageDigest.getInstance("SHA1").digest(x509Certificate.getEncoded()));
                if (a == null || a.length() <= 0) {
                    sb.append("unknown");
                } else {
                    sb.append(a.toString());
                }
                sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                sb.append("MD5|");
                String a2 = C2018y.m2149a(MessageDigest.getInstance(Constants.MD5).digest(x509Certificate.getEncoded()));
                if (a2 == null || a2.length() <= 0) {
                    sb.append("unknown");
                } else {
                    sb.append(a2.toString());
                }
            } catch (CertificateException e) {
                if (!C2014w.m2114a(e)) {
                    e.printStackTrace();
                }
            } catch (Throwable th) {
                if (!C2014w.m2114a(th)) {
                    th.printStackTrace();
                }
            }
        }
        if (sb.length() == 0) {
            return "unknown";
        }
        return sb.toString();
    }

    /* renamed from: e */
    public static String m1664e(Context context) {
        String a = m1657a(context);
        if (a == null) {
            return null;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(a, 64);
            if (packageInfo == null) {
                return null;
            }
            Signature[] signatureArr = packageInfo.signatures;
            if (signatureArr == null || signatureArr.length == 0) {
                return null;
            }
            return m1658a(packageInfo.signatures[0].toByteArray());
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    /* renamed from: f */
    public static boolean m1665f(Context context) {
        if (context == null) {
            return false;
        }
        if (f1151a == null) {
            f1151a = (ActivityManager) context.getSystemService("activity");
        }
        try {
            MemoryInfo memoryInfo = new MemoryInfo();
            f1151a.getMemoryInfo(memoryInfo);
            if (!memoryInfo.lowMemory) {
                return false;
            }
            C2014w.m2117c("Memory is low.", new Object[0]);
            return true;
        } catch (Throwable th) {
            if (!C2014w.m2114a(th)) {
                th.printStackTrace();
            }
            return false;
        }
    }
}
