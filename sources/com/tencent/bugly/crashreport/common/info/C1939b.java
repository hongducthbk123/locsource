package com.tencent.bugly.crashreport.common.info;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.facebook.places.model.PlaceFields;
import com.tencent.bugly.proguard.C2014w;
import com.tencent.bugly.proguard.C2018y;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import org.apache.commons.p052io.IOUtils;
import p004cn.jiguang.net.HttpUtils;

/* renamed from: com.tencent.bugly.crashreport.common.info.b */
/* compiled from: BUGLY */
public final class C1939b {

    /* renamed from: a */
    private static String f1225a = null;

    /* renamed from: b */
    private static String f1226b = null;

    /* renamed from: a */
    public static String m1722a() {
        try {
            return Build.MODEL;
        } catch (Throwable th) {
            if (!C2014w.m2114a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    /* renamed from: b */
    public static String m1725b() {
        try {
            return VERSION.RELEASE;
        } catch (Throwable th) {
            if (!C2014w.m2114a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    /* renamed from: c */
    public static int m1727c() {
        try {
            return VERSION.SDK_INT;
        } catch (Throwable th) {
            if (!C2014w.m2114a(th)) {
                th.printStackTrace();
            }
            return -1;
        }
    }

    /* renamed from: a */
    public static String m1723a(Context context) {
        String str;
        if (context == null) {
            return null;
        }
        if (!AppInfo.m1660a(context, "android.permission.READ_PHONE_STATE")) {
            C2014w.m2118d("no READ_PHONE_STATE permission to get IMEI", new Object[0]);
            return null;
        }
        try {
            String deviceId = ((TelephonyManager) context.getSystemService(PlaceFields.PHONE)).getDeviceId();
            if (deviceId == null) {
                return deviceId;
            }
            try {
                return deviceId.toLowerCase();
            } catch (Throwable th) {
            }
        } catch (Throwable th2) {
            str = null;
        }
        C2014w.m2113a("Failed to get IMEI.", new Object[0]);
        return str;
    }

    /* renamed from: b */
    public static String m1726b(Context context) {
        String str;
        if (context == null) {
            return null;
        }
        if (!AppInfo.m1660a(context, "android.permission.READ_PHONE_STATE")) {
            C2014w.m2118d("no READ_PHONE_STATE permission to get IMSI", new Object[0]);
            return null;
        }
        try {
            String subscriberId = ((TelephonyManager) context.getSystemService(PlaceFields.PHONE)).getSubscriberId();
            if (subscriberId == null) {
                return subscriberId;
            }
            try {
                return subscriberId.toLowerCase();
            } catch (Throwable th) {
            }
        } catch (Throwable th2) {
            str = null;
        }
        C2014w.m2113a("Failed to get IMSI.", new Object[0]);
        return str;
    }

    /* renamed from: c */
    public static String m1728c(Context context) {
        String str = "fail";
        if (context == null) {
            return str;
        }
        try {
            String string = Secure.getString(context.getContentResolver(), "android_id");
            if (string == null) {
                return "null";
            }
            try {
                return string.toLowerCase();
            } catch (Throwable th) {
                Throwable th2 = th;
                str = string;
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
        }
        if (C2014w.m2114a(th)) {
            return str;
        }
        C2014w.m2113a("Failed to get Android ID.", new Object[0]);
        return str;
    }

    /* renamed from: d */
    public static String m1730d(Context context) {
        String str;
        String str2 = "fail";
        if (context == null) {
            return str2;
        }
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager != null) {
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null) {
                    String macAddress = connectionInfo.getMacAddress();
                    str = macAddress == null ? "null" : macAddress.toLowerCase();
                    return str;
                }
            }
            str = str2;
        } catch (Throwable th) {
            Throwable th2 = th;
            str = str2;
            Throwable th3 = th2;
            if (!C2014w.m2114a(th3)) {
                th3.printStackTrace();
            }
        }
        return str;
    }

    /* renamed from: o */
    private static boolean m1748o() {
        try {
            if (Environment.getExternalStorageState().equals("mounted")) {
                return true;
            }
        } catch (Throwable th) {
            if (!C2014w.m2114a(th)) {
                th.printStackTrace();
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x006e A[Catch:{ all -> 0x00ba }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0073 A[SYNTHETIC, Splitter:B:39:0x0073] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0078 A[SYNTHETIC, Splitter:B:42:0x0078] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0098 A[SYNTHETIC, Splitter:B:56:0x0098] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x009d A[SYNTHETIC, Splitter:B:59:0x009d] */
    /* renamed from: p */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String m1749p() {
        /*
            r6 = 2
            r1 = 0
            java.lang.String r0 = "/system/build.prop"
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ Throwable -> 0x0065, all -> 0x0093 }
            r3.<init>(r0)     // Catch:{ Throwable -> 0x0065, all -> 0x0093 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00bc, all -> 0x00b7 }
            r0 = 2048(0x800, float:2.87E-42)
            r2.<init>(r3, r0)     // Catch:{ Throwable -> 0x00bc, all -> 0x00b7 }
        L_0x0010:
            java.lang.String r0 = r2.readLine()     // Catch:{ Throwable -> 0x00bf }
            if (r0 == 0) goto L_0x00c1
            java.lang.String r4 = "="
            r5 = 2
            java.lang.String[] r0 = r0.split(r4, r5)     // Catch:{ Throwable -> 0x00bf }
            int r4 = r0.length     // Catch:{ Throwable -> 0x00bf }
            if (r4 != r6) goto L_0x0010
            r4 = 0
            r4 = r0[r4]     // Catch:{ Throwable -> 0x00bf }
            java.lang.String r5 = "ro.product.cpu.abilist"
            boolean r4 = r4.equals(r5)     // Catch:{ Throwable -> 0x00bf }
            if (r4 == 0) goto L_0x0040
            r4 = 1
            r0 = r0[r4]     // Catch:{ Throwable -> 0x00bf }
        L_0x002e:
            if (r0 == 0) goto L_0x0039
            java.lang.String r4 = ","
            java.lang.String[] r0 = r0.split(r4)     // Catch:{ Throwable -> 0x00bf }
            r4 = 0
            r0 = r0[r4]     // Catch:{ Throwable -> 0x00bf }
        L_0x0039:
            r2.close()     // Catch:{ IOException -> 0x004f }
        L_0x003c:
            r3.close()     // Catch:{ IOException -> 0x005a }
        L_0x003f:
            return r0
        L_0x0040:
            r4 = 0
            r4 = r0[r4]     // Catch:{ Throwable -> 0x00bf }
            java.lang.String r5 = "ro.product.cpu.abi"
            boolean r4 = r4.equals(r5)     // Catch:{ Throwable -> 0x00bf }
            if (r4 == 0) goto L_0x0010
            r4 = 1
            r0 = r0[r4]     // Catch:{ Throwable -> 0x00bf }
            goto L_0x002e
        L_0x004f:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.proguard.C2014w.m2114a(r1)
            if (r2 != 0) goto L_0x003c
            r1.printStackTrace()
            goto L_0x003c
        L_0x005a:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.proguard.C2014w.m2114a(r1)
            if (r2 != 0) goto L_0x003f
            r1.printStackTrace()
            goto L_0x003f
        L_0x0065:
            r0 = move-exception
            r2 = r1
            r3 = r1
        L_0x0068:
            boolean r4 = com.tencent.bugly.proguard.C2014w.m2114a(r0)     // Catch:{ all -> 0x00ba }
            if (r4 != 0) goto L_0x0071
            r0.printStackTrace()     // Catch:{ all -> 0x00ba }
        L_0x0071:
            if (r2 == 0) goto L_0x0076
            r2.close()     // Catch:{ IOException -> 0x007d }
        L_0x0076:
            if (r3 == 0) goto L_0x007b
            r3.close()     // Catch:{ IOException -> 0x0088 }
        L_0x007b:
            r0 = r1
            goto L_0x003f
        L_0x007d:
            r0 = move-exception
            boolean r2 = com.tencent.bugly.proguard.C2014w.m2114a(r0)
            if (r2 != 0) goto L_0x0076
            r0.printStackTrace()
            goto L_0x0076
        L_0x0088:
            r0 = move-exception
            boolean r2 = com.tencent.bugly.proguard.C2014w.m2114a(r0)
            if (r2 != 0) goto L_0x007b
            r0.printStackTrace()
            goto L_0x007b
        L_0x0093:
            r0 = move-exception
            r2 = r1
            r3 = r1
        L_0x0096:
            if (r2 == 0) goto L_0x009b
            r2.close()     // Catch:{ IOException -> 0x00a1 }
        L_0x009b:
            if (r3 == 0) goto L_0x00a0
            r3.close()     // Catch:{ IOException -> 0x00ac }
        L_0x00a0:
            throw r0
        L_0x00a1:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.proguard.C2014w.m2114a(r1)
            if (r2 != 0) goto L_0x009b
            r1.printStackTrace()
            goto L_0x009b
        L_0x00ac:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.proguard.C2014w.m2114a(r1)
            if (r2 != 0) goto L_0x00a0
            r1.printStackTrace()
            goto L_0x00a0
        L_0x00b7:
            r0 = move-exception
            r2 = r1
            goto L_0x0096
        L_0x00ba:
            r0 = move-exception
            goto L_0x0096
        L_0x00bc:
            r0 = move-exception
            r2 = r1
            goto L_0x0068
        L_0x00bf:
            r0 = move-exception
            goto L_0x0068
        L_0x00c1:
            r0 = r1
            goto L_0x002e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.C1939b.m1749p():java.lang.String");
    }

    /* renamed from: a */
    public static String m1724a(boolean z) {
        String str = null;
        if (z) {
            try {
                str = m1749p();
            } catch (Throwable th) {
                if (!C2014w.m2114a(th)) {
                    th.printStackTrace();
                }
                return "fail";
            }
        }
        if (str == null) {
            str = System.getProperty("os.arch");
        }
        return str;
    }

    /* renamed from: d */
    public static long m1729d() {
        long j = -1;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (C2014w.m2114a(th)) {
                return j;
            }
            th.printStackTrace();
            return j;
        }
    }

    /* renamed from: e */
    public static long m1731e() {
        long j = -1;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (C2014w.m2114a(th)) {
                return j;
            }
            th.printStackTrace();
            return j;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x007a A[Catch:{ all -> 0x00c9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x007f A[SYNTHETIC, Splitter:B:44:0x007f] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0084 A[SYNTHETIC, Splitter:B:47:0x0084] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00a5 A[SYNTHETIC, Splitter:B:61:0x00a5] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00aa A[SYNTHETIC, Splitter:B:64:0x00aa] */
    /* renamed from: f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long m1733f() {
        /*
            r1 = 0
            java.lang.String r0 = "/proc/meminfo"
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ Throwable -> 0x0072, all -> 0x00a0 }
            r3.<init>(r0)     // Catch:{ Throwable -> 0x0072, all -> 0x00a0 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00cd, all -> 0x00c4 }
            r0 = 2048(0x800, float:2.87E-42)
            r2.<init>(r3, r0)     // Catch:{ Throwable -> 0x00cd, all -> 0x00c4 }
            java.lang.String r0 = r2.readLine()     // Catch:{ Throwable -> 0x00d0, all -> 0x00c7 }
            if (r0 != 0) goto L_0x0034
            r2.close()     // Catch:{ IOException -> 0x001e }
        L_0x0018:
            r3.close()     // Catch:{ IOException -> 0x0029 }
        L_0x001b:
            r0 = -1
        L_0x001d:
            return r0
        L_0x001e:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C2014w.m2114a(r0)
            if (r1 != 0) goto L_0x0018
            r0.printStackTrace()
            goto L_0x0018
        L_0x0029:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C2014w.m2114a(r0)
            if (r1 != 0) goto L_0x001b
            r0.printStackTrace()
            goto L_0x001b
        L_0x0034:
            java.lang.String r1 = ":\\s+"
            r4 = 2
            java.lang.String[] r0 = r0.split(r1, r4)     // Catch:{ Throwable -> 0x00d0, all -> 0x00c7 }
            r1 = 1
            r0 = r0[r1]     // Catch:{ Throwable -> 0x00d0, all -> 0x00c7 }
            java.lang.String r0 = r0.toLowerCase()     // Catch:{ Throwable -> 0x00d0, all -> 0x00c7 }
            java.lang.String r1 = "kb"
            java.lang.String r4 = ""
            java.lang.String r0 = r0.replace(r1, r4)     // Catch:{ Throwable -> 0x00d0, all -> 0x00c7 }
            java.lang.String r0 = r0.trim()     // Catch:{ Throwable -> 0x00d0, all -> 0x00c7 }
            long r0 = java.lang.Long.parseLong(r0)     // Catch:{ Throwable -> 0x00d0, all -> 0x00c7 }
            r4 = 10
            long r0 = r0 << r4
            r2.close()     // Catch:{ IOException -> 0x0067 }
        L_0x0058:
            r3.close()     // Catch:{ IOException -> 0x005c }
            goto L_0x001d
        L_0x005c:
            r2 = move-exception
            boolean r3 = com.tencent.bugly.proguard.C2014w.m2114a(r2)
            if (r3 != 0) goto L_0x001d
            r2.printStackTrace()
            goto L_0x001d
        L_0x0067:
            r2 = move-exception
            boolean r4 = com.tencent.bugly.proguard.C2014w.m2114a(r2)
            if (r4 != 0) goto L_0x0058
            r2.printStackTrace()
            goto L_0x0058
        L_0x0072:
            r0 = move-exception
            r2 = r1
        L_0x0074:
            boolean r3 = com.tencent.bugly.proguard.C2014w.m2114a(r0)     // Catch:{ all -> 0x00c9 }
            if (r3 != 0) goto L_0x007d
            r0.printStackTrace()     // Catch:{ all -> 0x00c9 }
        L_0x007d:
            if (r1 == 0) goto L_0x0082
            r1.close()     // Catch:{ IOException -> 0x008a }
        L_0x0082:
            if (r2 == 0) goto L_0x0087
            r2.close()     // Catch:{ IOException -> 0x0095 }
        L_0x0087:
            r0 = -2
            goto L_0x001d
        L_0x008a:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C2014w.m2114a(r0)
            if (r1 != 0) goto L_0x0082
            r0.printStackTrace()
            goto L_0x0082
        L_0x0095:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C2014w.m2114a(r0)
            if (r1 != 0) goto L_0x0087
            r0.printStackTrace()
            goto L_0x0087
        L_0x00a0:
            r0 = move-exception
            r2 = r1
            r3 = r1
        L_0x00a3:
            if (r2 == 0) goto L_0x00a8
            r2.close()     // Catch:{ IOException -> 0x00ae }
        L_0x00a8:
            if (r3 == 0) goto L_0x00ad
            r3.close()     // Catch:{ IOException -> 0x00b9 }
        L_0x00ad:
            throw r0
        L_0x00ae:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.proguard.C2014w.m2114a(r1)
            if (r2 != 0) goto L_0x00a8
            r1.printStackTrace()
            goto L_0x00a8
        L_0x00b9:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.proguard.C2014w.m2114a(r1)
            if (r2 != 0) goto L_0x00ad
            r1.printStackTrace()
            goto L_0x00ad
        L_0x00c4:
            r0 = move-exception
            r2 = r1
            goto L_0x00a3
        L_0x00c7:
            r0 = move-exception
            goto L_0x00a3
        L_0x00c9:
            r0 = move-exception
            r3 = r2
            r2 = r1
            goto L_0x00a3
        L_0x00cd:
            r0 = move-exception
            r2 = r3
            goto L_0x0074
        L_0x00d0:
            r0 = move-exception
            r1 = r2
            r2 = r3
            goto L_0x0074
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.C1939b.m1733f():long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:73:0x010a A[Catch:{ all -> 0x015a }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x010f A[SYNTHETIC, Splitter:B:75:0x010f] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0114 A[SYNTHETIC, Splitter:B:78:0x0114] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0136 A[SYNTHETIC, Splitter:B:92:0x0136] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x013b A[SYNTHETIC, Splitter:B:95:0x013b] */
    /* renamed from: g */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long m1735g() {
        /*
            r3 = 0
            r0 = -1
            r10 = 10
            java.lang.String r2 = "/proc/meminfo"
            java.io.FileReader r4 = new java.io.FileReader     // Catch:{ Throwable -> 0x0102, all -> 0x0131 }
            r4.<init>(r2)     // Catch:{ Throwable -> 0x0102, all -> 0x0131 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x015e, all -> 0x0155 }
            r5 = 2048(0x800, float:2.87E-42)
            r2.<init>(r4, r5)     // Catch:{ Throwable -> 0x015e, all -> 0x0155 }
            r2.readLine()     // Catch:{ Throwable -> 0x0162, all -> 0x0158 }
            java.lang.String r3 = r2.readLine()     // Catch:{ Throwable -> 0x0162, all -> 0x0158 }
            if (r3 != 0) goto L_0x0039
            r2.close()     // Catch:{ IOException -> 0x0023 }
        L_0x001f:
            r4.close()     // Catch:{ IOException -> 0x002e }
        L_0x0022:
            return r0
        L_0x0023:
            r2 = move-exception
            boolean r3 = com.tencent.bugly.proguard.C2014w.m2114a(r2)
            if (r3 != 0) goto L_0x001f
            r2.printStackTrace()
            goto L_0x001f
        L_0x002e:
            r2 = move-exception
            boolean r3 = com.tencent.bugly.proguard.C2014w.m2114a(r2)
            if (r3 != 0) goto L_0x0022
            r2.printStackTrace()
            goto L_0x0022
        L_0x0039:
            java.lang.String r5 = ":\\s+"
            r6 = 2
            java.lang.String[] r3 = r3.split(r5, r6)     // Catch:{ Throwable -> 0x0162, all -> 0x0158 }
            r5 = 1
            r3 = r3[r5]     // Catch:{ Throwable -> 0x0162, all -> 0x0158 }
            java.lang.String r3 = r3.toLowerCase()     // Catch:{ Throwable -> 0x0162, all -> 0x0158 }
            java.lang.String r5 = "kb"
            java.lang.String r6 = ""
            java.lang.String r3 = r3.replace(r5, r6)     // Catch:{ Throwable -> 0x0162, all -> 0x0158 }
            java.lang.String r3 = r3.trim()     // Catch:{ Throwable -> 0x0162, all -> 0x0158 }
            r6 = 0
            long r8 = java.lang.Long.parseLong(r3)     // Catch:{ Throwable -> 0x0162, all -> 0x0158 }
            long r8 = r8 << r10
            long r6 = r6 + r8
            java.lang.String r3 = r2.readLine()     // Catch:{ Throwable -> 0x0162, all -> 0x0158 }
            if (r3 != 0) goto L_0x007e
            r2.close()     // Catch:{ IOException -> 0x0073 }
        L_0x0064:
            r4.close()     // Catch:{ IOException -> 0x0068 }
            goto L_0x0022
        L_0x0068:
            r2 = move-exception
            boolean r3 = com.tencent.bugly.proguard.C2014w.m2114a(r2)
            if (r3 != 0) goto L_0x0022
            r2.printStackTrace()
            goto L_0x0022
        L_0x0073:
            r2 = move-exception
            boolean r3 = com.tencent.bugly.proguard.C2014w.m2114a(r2)
            if (r3 != 0) goto L_0x0064
            r2.printStackTrace()
            goto L_0x0064
        L_0x007e:
            java.lang.String r5 = ":\\s+"
            r8 = 2
            java.lang.String[] r3 = r3.split(r5, r8)     // Catch:{ Throwable -> 0x0162, all -> 0x0158 }
            r5 = 1
            r3 = r3[r5]     // Catch:{ Throwable -> 0x0162, all -> 0x0158 }
            java.lang.String r3 = r3.toLowerCase()     // Catch:{ Throwable -> 0x0162, all -> 0x0158 }
            java.lang.String r5 = "kb"
            java.lang.String r8 = ""
            java.lang.String r3 = r3.replace(r5, r8)     // Catch:{ Throwable -> 0x0162, all -> 0x0158 }
            java.lang.String r3 = r3.trim()     // Catch:{ Throwable -> 0x0162, all -> 0x0158 }
            long r8 = java.lang.Long.parseLong(r3)     // Catch:{ Throwable -> 0x0162, all -> 0x0158 }
            long r8 = r8 << r10
            long r6 = r6 + r8
            java.lang.String r3 = r2.readLine()     // Catch:{ Throwable -> 0x0162, all -> 0x0158 }
            if (r3 != 0) goto L_0x00c3
            r2.close()     // Catch:{ IOException -> 0x00b8 }
        L_0x00a7:
            r4.close()     // Catch:{ IOException -> 0x00ac }
            goto L_0x0022
        L_0x00ac:
            r2 = move-exception
            boolean r3 = com.tencent.bugly.proguard.C2014w.m2114a(r2)
            if (r3 != 0) goto L_0x0022
            r2.printStackTrace()
            goto L_0x0022
        L_0x00b8:
            r2 = move-exception
            boolean r3 = com.tencent.bugly.proguard.C2014w.m2114a(r2)
            if (r3 != 0) goto L_0x00a7
            r2.printStackTrace()
            goto L_0x00a7
        L_0x00c3:
            java.lang.String r0 = ":\\s+"
            r1 = 2
            java.lang.String[] r0 = r3.split(r0, r1)     // Catch:{ Throwable -> 0x0162, all -> 0x0158 }
            r1 = 1
            r0 = r0[r1]     // Catch:{ Throwable -> 0x0162, all -> 0x0158 }
            java.lang.String r0 = r0.toLowerCase()     // Catch:{ Throwable -> 0x0162, all -> 0x0158 }
            java.lang.String r1 = "kb"
            java.lang.String r3 = ""
            java.lang.String r0 = r0.replace(r1, r3)     // Catch:{ Throwable -> 0x0162, all -> 0x0158 }
            java.lang.String r0 = r0.trim()     // Catch:{ Throwable -> 0x0162, all -> 0x0158 }
            long r0 = java.lang.Long.parseLong(r0)     // Catch:{ Throwable -> 0x0162, all -> 0x0158 }
            long r0 = r0 << r10
            long r0 = r0 + r6
            r2.close()     // Catch:{ IOException -> 0x00f7 }
        L_0x00e6:
            r4.close()     // Catch:{ IOException -> 0x00eb }
            goto L_0x0022
        L_0x00eb:
            r2 = move-exception
            boolean r3 = com.tencent.bugly.proguard.C2014w.m2114a(r2)
            if (r3 != 0) goto L_0x0022
            r2.printStackTrace()
            goto L_0x0022
        L_0x00f7:
            r2 = move-exception
            boolean r3 = com.tencent.bugly.proguard.C2014w.m2114a(r2)
            if (r3 != 0) goto L_0x00e6
            r2.printStackTrace()
            goto L_0x00e6
        L_0x0102:
            r0 = move-exception
            r1 = r3
        L_0x0104:
            boolean r2 = com.tencent.bugly.proguard.C2014w.m2114a(r0)     // Catch:{ all -> 0x015a }
            if (r2 != 0) goto L_0x010d
            r0.printStackTrace()     // Catch:{ all -> 0x015a }
        L_0x010d:
            if (r1 == 0) goto L_0x0112
            r1.close()     // Catch:{ IOException -> 0x011b }
        L_0x0112:
            if (r3 == 0) goto L_0x0117
            r3.close()     // Catch:{ IOException -> 0x0126 }
        L_0x0117:
            r0 = -2
            goto L_0x0022
        L_0x011b:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C2014w.m2114a(r0)
            if (r1 != 0) goto L_0x0112
            r0.printStackTrace()
            goto L_0x0112
        L_0x0126:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C2014w.m2114a(r0)
            if (r1 != 0) goto L_0x0117
            r0.printStackTrace()
            goto L_0x0117
        L_0x0131:
            r0 = move-exception
            r2 = r3
            r4 = r3
        L_0x0134:
            if (r2 == 0) goto L_0x0139
            r2.close()     // Catch:{ IOException -> 0x013f }
        L_0x0139:
            if (r4 == 0) goto L_0x013e
            r4.close()     // Catch:{ IOException -> 0x014a }
        L_0x013e:
            throw r0
        L_0x013f:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.proguard.C2014w.m2114a(r1)
            if (r2 != 0) goto L_0x0139
            r1.printStackTrace()
            goto L_0x0139
        L_0x014a:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.proguard.C2014w.m2114a(r1)
            if (r2 != 0) goto L_0x013e
            r1.printStackTrace()
            goto L_0x013e
        L_0x0155:
            r0 = move-exception
            r2 = r3
            goto L_0x0134
        L_0x0158:
            r0 = move-exception
            goto L_0x0134
        L_0x015a:
            r0 = move-exception
            r2 = r1
            r4 = r3
            goto L_0x0134
        L_0x015e:
            r0 = move-exception
            r1 = r3
            r3 = r4
            goto L_0x0104
        L_0x0162:
            r0 = move-exception
            r1 = r2
            r3 = r4
            goto L_0x0104
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.C1939b.m1735g():long");
    }

    /* renamed from: h */
    public static long m1737h() {
        if (!m1748o()) {
            return 0;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount());
        } catch (Throwable th) {
            if (!C2014w.m2114a(th)) {
                th.printStackTrace();
            }
            return -2;
        }
    }

    /* renamed from: i */
    public static long m1739i() {
        if (!m1748o()) {
            return 0;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks());
        } catch (Throwable th) {
            if (!C2014w.m2114a(th)) {
                th.printStackTrace();
            }
            return -2;
        }
    }

    /* renamed from: j */
    public static String m1741j() {
        String str = "fail";
        try {
            return Locale.getDefault().getCountry();
        } catch (Throwable th) {
            if (C2014w.m2114a(th)) {
                return str;
            }
            th.printStackTrace();
            return str;
        }
    }

    /* renamed from: k */
    public static String m1743k() {
        String str = "fail";
        try {
            return Build.BRAND;
        } catch (Throwable th) {
            if (C2014w.m2114a(th)) {
                return str;
            }
            th.printStackTrace();
            return str;
        }
    }

    /* renamed from: e */
    public static String m1732e(Context context) {
        String str;
        String str2 = "unknown";
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            if (activeNetworkInfo.getType() == 1) {
                return "WIFI";
            }
            if (activeNetworkInfo.getType() == 0) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(PlaceFields.PHONE);
                if (telephonyManager != null) {
                    int networkType = telephonyManager.getNetworkType();
                    switch (networkType) {
                        case 1:
                            return "GPRS";
                        case 2:
                            return "EDGE";
                        case 3:
                            return "UMTS";
                        case 4:
                            return "CDMA";
                        case 5:
                            return "EVDO_0";
                        case 6:
                            return "EVDO_A";
                        case 7:
                            return "1xRTT";
                        case 8:
                            return "HSDPA";
                        case 9:
                            return "HSUPA";
                        case 10:
                            return "HSPA";
                        case 11:
                            return "iDen";
                        case 12:
                            return "EVDO_B";
                        case 13:
                            return "LTE";
                        case 14:
                            return "eHRPD";
                        case 15:
                            return "HSPA+";
                        default:
                            str = "MOBILE(" + networkType + ")";
                            break;
                    }
                    return str;
                }
            }
            str = str2;
            return str;
        } catch (Exception e) {
            if (C2014w.m2114a(e)) {
                return str2;
            }
            e.printStackTrace();
            return str2;
        }
    }

    /* renamed from: f */
    public static String m1734f(Context context) {
        String a = C2018y.m2146a(context, "ro.miui.ui.version.name");
        if (!C2018y.m2158a(a) && !a.equals("fail")) {
            return "XiaoMi/MIUI/" + a;
        }
        String a2 = C2018y.m2146a(context, "ro.build.version.emui");
        if (!C2018y.m2158a(a2) && !a2.equals("fail")) {
            return "HuaWei/EMOTION/" + a2;
        }
        String a3 = C2018y.m2146a(context, "ro.lenovo.series");
        if (C2018y.m2158a(a3) || a3.equals("fail")) {
            String a4 = C2018y.m2146a(context, "ro.build.nubia.rom.name");
            if (!C2018y.m2158a(a4) && !a4.equals("fail")) {
                return "Zte/NUBIA/" + a4 + "_" + C2018y.m2146a(context, "ro.build.nubia.rom.code");
            }
            String a5 = C2018y.m2146a(context, "ro.meizu.product.model");
            if (!C2018y.m2158a(a5) && !a5.equals("fail")) {
                return "Meizu/FLYME/" + C2018y.m2146a(context, "ro.build.display.id");
            }
            String a6 = C2018y.m2146a(context, "ro.build.version.opporom");
            if (!C2018y.m2158a(a6) && !a6.equals("fail")) {
                return "Oppo/COLOROS/" + a6;
            }
            String a7 = C2018y.m2146a(context, "ro.vivo.os.build.display.id");
            if (!C2018y.m2158a(a7) && !a7.equals("fail")) {
                return "vivo/FUNTOUCH/" + a7;
            }
            String a8 = C2018y.m2146a(context, "ro.aa.romver");
            if (!C2018y.m2158a(a8) && !a8.equals("fail")) {
                return "htc/" + a8 + HttpUtils.PATHS_SEPARATOR + C2018y.m2146a(context, "ro.build.description");
            }
            String a9 = C2018y.m2146a(context, "ro.lewa.version");
            if (!C2018y.m2158a(a9) && !a9.equals("fail")) {
                return "tcl/" + a9 + HttpUtils.PATHS_SEPARATOR + C2018y.m2146a(context, "ro.build.display.id");
            }
            String a10 = C2018y.m2146a(context, "ro.gn.gnromvernumber");
            if (!C2018y.m2158a(a10) && !a10.equals("fail")) {
                return "amigo/" + a10 + HttpUtils.PATHS_SEPARATOR + C2018y.m2146a(context, "ro.build.display.id");
            }
            String a11 = C2018y.m2146a(context, "ro.build.tyd.kbstyle_version");
            if (C2018y.m2158a(a11) || a11.equals("fail")) {
                return C2018y.m2146a(context, "ro.build.fingerprint") + HttpUtils.PATHS_SEPARATOR + C2018y.m2146a(context, "ro.build.rom.id");
            }
            return "dido/" + a11;
        }
        return "Lenovo/VIBE/" + C2018y.m2146a(context, "ro.build.version.incremental");
    }

    /* renamed from: g */
    public static String m1736g(Context context) {
        return C2018y.m2146a(context, "ro.board.platform");
    }

    /* renamed from: h */
    public static boolean m1738h(Context context) {
        boolean z;
        boolean z2;
        Boolean bool;
        try {
            z = new File("/system/app/Superuser.apk").exists();
        } catch (Throwable th) {
            if (!C2014w.m2116b(th)) {
                th.printStackTrace();
            }
            z = false;
        }
        Boolean bool2 = null;
        ArrayList a = C2018y.m2151a(context, new String[]{"/system/bin/sh", "-c", "type su"});
        if (a != null && a.size() > 0) {
            Iterator it = a.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                C2014w.m2117c(str, new Object[0]);
                if (str.contains("not found")) {
                    bool = Boolean.valueOf(false);
                } else {
                    bool = bool2;
                }
                bool2 = bool;
            }
            if (bool2 == null) {
                bool2 = Boolean.valueOf(true);
            }
        }
        Boolean valueOf = Boolean.valueOf(bool2 == null ? false : bool2.booleanValue());
        if (Build.TAGS == null || !Build.TAGS.contains("test-keys")) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (z2 || z || valueOf.booleanValue()) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r2v0, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r2v1 */
    /* JADX WARNING: type inference failed for: r1v2, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r0v4, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r2v3 */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: type inference failed for: r1v11 */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: type inference failed for: r1v16 */
    /* JADX WARNING: type inference failed for: r1v19 */
    /* JADX WARNING: type inference failed for: r1v20, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: type inference failed for: r0v8, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r1v22, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r2v9 */
    /* JADX WARNING: type inference failed for: r1v23, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r2v11 */
    /* JADX WARNING: type inference failed for: r2v13 */
    /* JADX WARNING: type inference failed for: r1v24, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r2v15 */
    /* JADX WARNING: type inference failed for: r2v17 */
    /* JADX WARNING: type inference failed for: r1v25 */
    /* JADX WARNING: type inference failed for: r2v18 */
    /* JADX WARNING: type inference failed for: r2v19 */
    /* JADX WARNING: type inference failed for: r2v20 */
    /* JADX WARNING: type inference failed for: r1v26 */
    /* JADX WARNING: type inference failed for: r1v27 */
    /* JADX WARNING: type inference failed for: r1v28 */
    /* JADX WARNING: type inference failed for: r1v29 */
    /* JADX WARNING: type inference failed for: r1v30 */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00a8, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00a9, code lost:
        r2 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00ab, code lost:
        r0 = th;
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00b0, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00b1, code lost:
        r2 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00b3, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00b4, code lost:
        r2 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00b6, code lost:
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00bc, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v8
      assigns: []
      uses: []
      mth insns count: 85
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0091 A[SYNTHETIC, Splitter:B:42:0x0091] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x009f A[SYNTHETIC, Splitter:B:49:0x009f] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00ab A[ExcHandler: all (th java.lang.Throwable), PHI: r2 
      PHI: (r2v6 ?) = (r2v18 ?), (r2v20 ?) binds: [B:12:0x002e, B:23:0x0059] A[DONT_GENERATE, DONT_INLINE], Splitter:B:12:0x002e] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00b6 A[ExcHandler: Throwable (th java.lang.Throwable), PHI: r1 
      PHI: (r1v8 ?) = (r1v26 ?), (r1v27 ?), (r1v28 ?), (r1v29 ?), (r1v30 ?) binds: [B:31:0x007e, B:32:?, B:27:0x0075, B:16:0x004a, B:5:0x001f] A[DONT_GENERATE, DONT_INLINE], Splitter:B:5:0x001f] */
    /* JADX WARNING: Unknown variable types count: 10 */
    /* renamed from: l */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m1745l() {
        /*
            r0 = 0
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x008d, all -> 0x009a }
            r3.<init>()     // Catch:{ Throwable -> 0x008d, all -> 0x009a }
            java.io.File r1 = new java.io.File     // Catch:{ Throwable -> 0x008d, all -> 0x009a }
            java.lang.String r2 = "/sys/block/mmcblk0/device/type"
            r1.<init>(r2)     // Catch:{ Throwable -> 0x008d, all -> 0x009a }
            boolean r1 = r1.exists()     // Catch:{ Throwable -> 0x008d, all -> 0x009a }
            if (r1 == 0) goto L_0x00c0
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x008d, all -> 0x009a }
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ Throwable -> 0x008d, all -> 0x009a }
            java.lang.String r4 = "/sys/block/mmcblk0/device/type"
            r2.<init>(r4)     // Catch:{ Throwable -> 0x008d, all -> 0x009a }
            r1.<init>(r2)     // Catch:{ Throwable -> 0x008d, all -> 0x009a }
            java.lang.String r2 = r1.readLine()     // Catch:{ Throwable -> 0x00b6, all -> 0x00a8 }
            if (r2 == 0) goto L_0x0028
            r3.append(r2)     // Catch:{ Throwable -> 0x00b6, all -> 0x00a8 }
        L_0x0028:
            r1.close()     // Catch:{ Throwable -> 0x00b6, all -> 0x00a8 }
            r2 = r1
        L_0x002c:
            java.lang.String r1 = ","
            r3.append(r1)     // Catch:{ Throwable -> 0x00b8, all -> 0x00ab }
            java.io.File r1 = new java.io.File     // Catch:{ Throwable -> 0x00b8, all -> 0x00ab }
            java.lang.String r4 = "/sys/block/mmcblk0/device/name"
            r1.<init>(r4)     // Catch:{ Throwable -> 0x00b8, all -> 0x00ab }
            boolean r1 = r1.exists()     // Catch:{ Throwable -> 0x00b8, all -> 0x00ab }
            if (r1 == 0) goto L_0x0057
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00b8, all -> 0x00ab }
            java.io.FileReader r4 = new java.io.FileReader     // Catch:{ Throwable -> 0x00b8, all -> 0x00ab }
            java.lang.String r5 = "/sys/block/mmcblk0/device/name"
            r4.<init>(r5)     // Catch:{ Throwable -> 0x00b8, all -> 0x00ab }
            r1.<init>(r4)     // Catch:{ Throwable -> 0x00b8, all -> 0x00ab }
            java.lang.String r2 = r1.readLine()     // Catch:{ Throwable -> 0x00b6, all -> 0x00ad }
            if (r2 == 0) goto L_0x0053
            r3.append(r2)     // Catch:{ Throwable -> 0x00b6, all -> 0x00ad }
        L_0x0053:
            r1.close()     // Catch:{ Throwable -> 0x00b6, all -> 0x00ad }
            r2 = r1
        L_0x0057:
            java.lang.String r1 = ","
            r3.append(r1)     // Catch:{ Throwable -> 0x00bb, all -> 0x00ab }
            java.io.File r1 = new java.io.File     // Catch:{ Throwable -> 0x00bb, all -> 0x00ab }
            java.lang.String r4 = "/sys/block/mmcblk0/device/cid"
            r1.<init>(r4)     // Catch:{ Throwable -> 0x00bb, all -> 0x00ab }
            boolean r1 = r1.exists()     // Catch:{ Throwable -> 0x00bb, all -> 0x00ab }
            if (r1 == 0) goto L_0x00be
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00bb, all -> 0x00ab }
            java.io.FileReader r4 = new java.io.FileReader     // Catch:{ Throwable -> 0x00bb, all -> 0x00ab }
            java.lang.String r5 = "/sys/block/mmcblk0/device/cid"
            r4.<init>(r5)     // Catch:{ Throwable -> 0x00bb, all -> 0x00ab }
            r1.<init>(r4)     // Catch:{ Throwable -> 0x00bb, all -> 0x00ab }
            java.lang.String r2 = r1.readLine()     // Catch:{ Throwable -> 0x00b6, all -> 0x00b0 }
            if (r2 == 0) goto L_0x007e
            r3.append(r2)     // Catch:{ Throwable -> 0x00b6, all -> 0x00b0 }
        L_0x007e:
            java.lang.String r0 = r3.toString()     // Catch:{ Throwable -> 0x00b6, all -> 0x00b3 }
            if (r1 == 0) goto L_0x0087
            r1.close()     // Catch:{ IOException -> 0x0088 }
        L_0x0087:
            return r0
        L_0x0088:
            r1 = move-exception
            com.tencent.bugly.proguard.C2014w.m2114a(r1)
            goto L_0x0087
        L_0x008d:
            r1 = move-exception
            r1 = r0
        L_0x008f:
            if (r1 == 0) goto L_0x0087
            r1.close()     // Catch:{ IOException -> 0x0095 }
            goto L_0x0087
        L_0x0095:
            r1 = move-exception
            com.tencent.bugly.proguard.C2014w.m2114a(r1)
            goto L_0x0087
        L_0x009a:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x009d:
            if (r2 == 0) goto L_0x00a2
            r2.close()     // Catch:{ IOException -> 0x00a3 }
        L_0x00a2:
            throw r0
        L_0x00a3:
            r1 = move-exception
            com.tencent.bugly.proguard.C2014w.m2114a(r1)
            goto L_0x00a2
        L_0x00a8:
            r0 = move-exception
            r2 = r1
            goto L_0x009d
        L_0x00ab:
            r0 = move-exception
            goto L_0x009d
        L_0x00ad:
            r0 = move-exception
            r2 = r1
            goto L_0x009d
        L_0x00b0:
            r0 = move-exception
            r2 = r1
            goto L_0x009d
        L_0x00b3:
            r0 = move-exception
            r2 = r1
            goto L_0x009d
        L_0x00b6:
            r2 = move-exception
            goto L_0x008f
        L_0x00b8:
            r1 = move-exception
            r1 = r2
            goto L_0x008f
        L_0x00bb:
            r1 = move-exception
            r1 = r2
            goto L_0x008f
        L_0x00be:
            r1 = r2
            goto L_0x007e
        L_0x00c0:
            r2 = r0
            goto L_0x002c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.C1939b.m1745l():java.lang.String");
    }

    /* renamed from: i */
    public static String m1740i(Context context) {
        StringBuilder sb = new StringBuilder();
        String a = C2018y.m2146a(context, "ro.genymotion.version");
        if (a != null) {
            sb.append("ro.genymotion.version");
            sb.append("|");
            sb.append(a);
            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
        }
        String a2 = C2018y.m2146a(context, "androVM.vbox_dpi");
        if (a2 != null) {
            sb.append("androVM.vbox_dpi");
            sb.append("|");
            sb.append(a2);
            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
        }
        String a3 = C2018y.m2146a(context, "qemu.sf.fake_camera");
        if (a3 != null) {
            sb.append("qemu.sf.fake_camera");
            sb.append("|");
            sb.append(a3);
        }
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0098 A[SYNTHETIC, Splitter:B:32:0x0098] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00a9 A[SYNTHETIC, Splitter:B:40:0x00a9] */
    /* renamed from: j */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m1742j(android.content.Context r5) {
        /*
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r0 = f1225a
            if (r0 != 0) goto L_0x0011
            java.lang.String r0 = "ro.secure"
            java.lang.String r0 = com.tencent.bugly.proguard.C2018y.m2146a(r5, r0)
            f1225a = r0
        L_0x0011:
            java.lang.String r0 = f1225a
            if (r0 == 0) goto L_0x002a
            java.lang.String r0 = "ro.secure"
            r3.append(r0)
            java.lang.String r0 = "|"
            r3.append(r0)
            java.lang.String r0 = f1225a
            r3.append(r0)
            java.lang.String r0 = "\n"
            r3.append(r0)
        L_0x002a:
            java.lang.String r0 = f1226b
            if (r0 != 0) goto L_0x0036
            java.lang.String r0 = "ro.debuggable"
            java.lang.String r0 = com.tencent.bugly.proguard.C2018y.m2146a(r5, r0)
            f1226b = r0
        L_0x0036:
            java.lang.String r0 = f1226b
            if (r0 == 0) goto L_0x004f
            java.lang.String r0 = "ro.debuggable"
            r3.append(r0)
            java.lang.String r0 = "|"
            r3.append(r0)
            java.lang.String r0 = f1226b
            r3.append(r0)
            java.lang.String r0 = "\n"
            r3.append(r0)
        L_0x004f:
            r2 = 0
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0091, all -> 0x00a5 }
            java.io.FileReader r0 = new java.io.FileReader     // Catch:{ Throwable -> 0x0091, all -> 0x00a5 }
            java.lang.String r4 = "/proc/self/status"
            r0.<init>(r4)     // Catch:{ Throwable -> 0x0091, all -> 0x00a5 }
            r1.<init>(r0)     // Catch:{ Throwable -> 0x0091, all -> 0x00a5 }
        L_0x005c:
            java.lang.String r0 = r1.readLine()     // Catch:{ Throwable -> 0x00b4 }
            if (r0 == 0) goto L_0x006a
            java.lang.String r2 = "TracerPid:"
            boolean r2 = r0.startsWith(r2)     // Catch:{ Throwable -> 0x00b4 }
            if (r2 == 0) goto L_0x005c
        L_0x006a:
            if (r0 == 0) goto L_0x0084
            r2 = 10
            java.lang.String r0 = r0.substring(r2)     // Catch:{ Throwable -> 0x00b4 }
            java.lang.String r0 = r0.trim()     // Catch:{ Throwable -> 0x00b4 }
            java.lang.String r2 = "tracer_pid"
            r3.append(r2)     // Catch:{ Throwable -> 0x00b4 }
            java.lang.String r2 = "|"
            r3.append(r2)     // Catch:{ Throwable -> 0x00b4 }
            r3.append(r0)     // Catch:{ Throwable -> 0x00b4 }
        L_0x0084:
            java.lang.String r0 = r3.toString()     // Catch:{ Throwable -> 0x00b4 }
            r1.close()     // Catch:{ IOException -> 0x008c }
        L_0x008b:
            return r0
        L_0x008c:
            r1 = move-exception
            com.tencent.bugly.proguard.C2014w.m2114a(r1)
            goto L_0x008b
        L_0x0091:
            r0 = move-exception
            r1 = r2
        L_0x0093:
            com.tencent.bugly.proguard.C2014w.m2114a(r0)     // Catch:{ all -> 0x00b2 }
            if (r1 == 0) goto L_0x009b
            r1.close()     // Catch:{ IOException -> 0x00a0 }
        L_0x009b:
            java.lang.String r0 = r3.toString()
            goto L_0x008b
        L_0x00a0:
            r0 = move-exception
            com.tencent.bugly.proguard.C2014w.m2114a(r0)
            goto L_0x009b
        L_0x00a5:
            r0 = move-exception
            r1 = r2
        L_0x00a7:
            if (r1 == 0) goto L_0x00ac
            r1.close()     // Catch:{ IOException -> 0x00ad }
        L_0x00ac:
            throw r0
        L_0x00ad:
            r1 = move-exception
            com.tencent.bugly.proguard.C2014w.m2114a(r1)
            goto L_0x00ac
        L_0x00b2:
            r0 = move-exception
            goto L_0x00a7
        L_0x00b4:
            r0 = move-exception
            goto L_0x0093
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.C1939b.m1742j(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b3, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00ba, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00bb, code lost:
        com.tencent.bugly.proguard.C2014w.m2114a(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00bf, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00c6, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00c7, code lost:
        com.tencent.bugly.proguard.C2014w.m2114a(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00cb, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00cc, code lost:
        r5 = r1;
        r1 = r0;
        r0 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00d5, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00d6, code lost:
        r5 = r1;
        r1 = r0;
        r0 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00e0, code lost:
        r0 = r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b6 A[SYNTHETIC, Splitter:B:42:0x00b6] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00bf A[ExcHandler: all (th java.lang.Throwable), PHI: r1 
      PHI: (r1v3 java.io.BufferedReader) = (r1v0 java.io.BufferedReader), (r1v5 java.io.BufferedReader), (r1v6 java.io.BufferedReader) binds: [B:1:0x0006, B:12:0x0039, B:23:0x0070] A[DONT_GENERATE, DONT_INLINE], Splitter:B:1:0x0006] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00c2 A[SYNTHETIC, Splitter:B:48:0x00c2] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00da A[ExcHandler: Throwable (th java.lang.Throwable), PHI: r0 
      PHI: (r0v9 java.io.BufferedReader) = (r0v23 java.io.BufferedReader), (r0v25 java.io.BufferedReader), (r0v27 java.io.BufferedReader) binds: [B:27:0x008c, B:16:0x0055, B:5:0x001f] A[DONT_GENERATE, DONT_INLINE], Splitter:B:5:0x001f] */
    /* renamed from: m */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m1746m() {
        /*
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r1 = 0
            java.io.File r0 = new java.io.File     // Catch:{ Throwable -> 0x00b2, all -> 0x00bf }
            java.lang.String r3 = "/sys/class/power_supply/ac/online"
            r0.<init>(r3)     // Catch:{ Throwable -> 0x00b2, all -> 0x00bf }
            boolean r0 = r0.exists()     // Catch:{ Throwable -> 0x00b2, all -> 0x00bf }
            if (r0 == 0) goto L_0x0037
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00b2, all -> 0x00bf }
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ Throwable -> 0x00b2, all -> 0x00bf }
            java.lang.String r4 = "/sys/class/power_supply/ac/online"
            r3.<init>(r4)     // Catch:{ Throwable -> 0x00b2, all -> 0x00bf }
            r0.<init>(r3)     // Catch:{ Throwable -> 0x00b2, all -> 0x00bf }
            java.lang.String r1 = r0.readLine()     // Catch:{ Throwable -> 0x00da, all -> 0x00cb }
            if (r1 == 0) goto L_0x0033
            java.lang.String r3 = "ac_online"
            r2.append(r3)     // Catch:{ Throwable -> 0x00da, all -> 0x00cb }
            java.lang.String r3 = "|"
            r2.append(r3)     // Catch:{ Throwable -> 0x00da, all -> 0x00cb }
            r2.append(r1)     // Catch:{ Throwable -> 0x00da, all -> 0x00cb }
        L_0x0033:
            r0.close()     // Catch:{ Throwable -> 0x00da, all -> 0x00cb }
            r1 = r0
        L_0x0037:
            java.lang.String r0 = "\n"
            r2.append(r0)     // Catch:{ Throwable -> 0x00dc, all -> 0x00bf }
            java.io.File r0 = new java.io.File     // Catch:{ Throwable -> 0x00dc, all -> 0x00bf }
            java.lang.String r3 = "/sys/class/power_supply/usb/online"
            r0.<init>(r3)     // Catch:{ Throwable -> 0x00dc, all -> 0x00bf }
            boolean r0 = r0.exists()     // Catch:{ Throwable -> 0x00dc, all -> 0x00bf }
            if (r0 == 0) goto L_0x006e
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00dc, all -> 0x00bf }
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ Throwable -> 0x00dc, all -> 0x00bf }
            java.lang.String r4 = "/sys/class/power_supply/usb/online"
            r3.<init>(r4)     // Catch:{ Throwable -> 0x00dc, all -> 0x00bf }
            r0.<init>(r3)     // Catch:{ Throwable -> 0x00dc, all -> 0x00bf }
            java.lang.String r1 = r0.readLine()     // Catch:{ Throwable -> 0x00da, all -> 0x00d0 }
            if (r1 == 0) goto L_0x006a
            java.lang.String r3 = "usb_online"
            r2.append(r3)     // Catch:{ Throwable -> 0x00da, all -> 0x00d0 }
            java.lang.String r3 = "|"
            r2.append(r3)     // Catch:{ Throwable -> 0x00da, all -> 0x00d0 }
            r2.append(r1)     // Catch:{ Throwable -> 0x00da, all -> 0x00d0 }
        L_0x006a:
            r0.close()     // Catch:{ Throwable -> 0x00da, all -> 0x00d0 }
            r1 = r0
        L_0x006e:
            java.lang.String r0 = "\n"
            r2.append(r0)     // Catch:{ Throwable -> 0x00df, all -> 0x00bf }
            java.io.File r0 = new java.io.File     // Catch:{ Throwable -> 0x00df, all -> 0x00bf }
            java.lang.String r3 = "/sys/class/power_supply/battery/capacity"
            r0.<init>(r3)     // Catch:{ Throwable -> 0x00df, all -> 0x00bf }
            boolean r0 = r0.exists()     // Catch:{ Throwable -> 0x00df, all -> 0x00bf }
            if (r0 == 0) goto L_0x00e2
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00df, all -> 0x00bf }
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ Throwable -> 0x00df, all -> 0x00bf }
            java.lang.String r4 = "/sys/class/power_supply/battery/capacity"
            r3.<init>(r4)     // Catch:{ Throwable -> 0x00df, all -> 0x00bf }
            r0.<init>(r3)     // Catch:{ Throwable -> 0x00df, all -> 0x00bf }
            java.lang.String r1 = r0.readLine()     // Catch:{ Throwable -> 0x00da, all -> 0x00d5 }
            if (r1 == 0) goto L_0x00a0
            java.lang.String r3 = "battery_capacity"
            r2.append(r3)     // Catch:{ Throwable -> 0x00da, all -> 0x00d5 }
            java.lang.String r3 = "|"
            r2.append(r3)     // Catch:{ Throwable -> 0x00da, all -> 0x00d5 }
            r2.append(r1)     // Catch:{ Throwable -> 0x00da, all -> 0x00d5 }
        L_0x00a0:
            r0.close()     // Catch:{ Throwable -> 0x00da, all -> 0x00d5 }
        L_0x00a3:
            if (r0 == 0) goto L_0x00a8
            r0.close()     // Catch:{ IOException -> 0x00ad }
        L_0x00a8:
            java.lang.String r0 = r2.toString()
            return r0
        L_0x00ad:
            r0 = move-exception
            com.tencent.bugly.proguard.C2014w.m2114a(r0)
            goto L_0x00a8
        L_0x00b2:
            r0 = move-exception
            r0 = r1
        L_0x00b4:
            if (r0 == 0) goto L_0x00a8
            r0.close()     // Catch:{ IOException -> 0x00ba }
            goto L_0x00a8
        L_0x00ba:
            r0 = move-exception
            com.tencent.bugly.proguard.C2014w.m2114a(r0)
            goto L_0x00a8
        L_0x00bf:
            r0 = move-exception
        L_0x00c0:
            if (r1 == 0) goto L_0x00c5
            r1.close()     // Catch:{ IOException -> 0x00c6 }
        L_0x00c5:
            throw r0
        L_0x00c6:
            r1 = move-exception
            com.tencent.bugly.proguard.C2014w.m2114a(r1)
            goto L_0x00c5
        L_0x00cb:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x00c0
        L_0x00d0:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x00c0
        L_0x00d5:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x00c0
        L_0x00da:
            r1 = move-exception
            goto L_0x00b4
        L_0x00dc:
            r0 = move-exception
            r0 = r1
            goto L_0x00b4
        L_0x00df:
            r0 = move-exception
            r0 = r1
            goto L_0x00b4
        L_0x00e2:
            r0 = r1
            goto L_0x00a3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.C1939b.m1746m():java.lang.String");
    }

    /* renamed from: k */
    public static String m1744k(Context context) {
        StringBuilder sb = new StringBuilder();
        String a = C2018y.m2146a(context, "gsm.sim.state");
        if (a != null) {
            sb.append("gsm.sim.state");
            sb.append("|");
            sb.append(a);
        }
        sb.append(IOUtils.LINE_SEPARATOR_UNIX);
        String a2 = C2018y.m2146a(context, "gsm.sim.state2");
        if (a2 != null) {
            sb.append("gsm.sim.state2");
            sb.append("|");
            sb.append(a2);
        }
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x003c A[SYNTHETIC, Splitter:B:19:0x003c] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0049 A[SYNTHETIC, Splitter:B:26:0x0049] */
    /* renamed from: n */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long m1747n() {
        /*
            r0 = 0
            r3 = 0
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0035, all -> 0x0045 }
            java.io.FileReader r1 = new java.io.FileReader     // Catch:{ Throwable -> 0x0035, all -> 0x0045 }
            java.lang.String r4 = "/proc/uptime"
            r1.<init>(r4)     // Catch:{ Throwable -> 0x0035, all -> 0x0045 }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x0035, all -> 0x0045 }
            java.lang.String r1 = r2.readLine()     // Catch:{ Throwable -> 0x0054 }
            if (r1 == 0) goto L_0x002b
            java.lang.String r3 = " "
            java.lang.String[] r1 = r1.split(r3)     // Catch:{ Throwable -> 0x0054 }
            r3 = 0
            r1 = r1[r3]     // Catch:{ Throwable -> 0x0054 }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0054 }
            r6 = 1000(0x3e8, double:4.94E-321)
            long r4 = r4 / r6
            float r3 = (float) r4     // Catch:{ Throwable -> 0x0054 }
            float r0 = java.lang.Float.parseFloat(r1)     // Catch:{ Throwable -> 0x0054 }
            float r0 = r3 - r0
        L_0x002b:
            r2.close()     // Catch:{ IOException -> 0x0030 }
        L_0x002e:
            long r0 = (long) r0
            return r0
        L_0x0030:
            r1 = move-exception
            com.tencent.bugly.proguard.C2014w.m2114a(r1)
            goto L_0x002e
        L_0x0035:
            r1 = move-exception
            r2 = r3
        L_0x0037:
            com.tencent.bugly.proguard.C2014w.m2114a(r1)     // Catch:{ all -> 0x0052 }
            if (r2 == 0) goto L_0x002e
            r2.close()     // Catch:{ IOException -> 0x0040 }
            goto L_0x002e
        L_0x0040:
            r1 = move-exception
            com.tencent.bugly.proguard.C2014w.m2114a(r1)
            goto L_0x002e
        L_0x0045:
            r0 = move-exception
            r2 = r3
        L_0x0047:
            if (r2 == 0) goto L_0x004c
            r2.close()     // Catch:{ IOException -> 0x004d }
        L_0x004c:
            throw r0
        L_0x004d:
            r1 = move-exception
            com.tencent.bugly.proguard.C2014w.m2114a(r1)
            goto L_0x004c
        L_0x0052:
            r0 = move-exception
            goto L_0x0047
        L_0x0054:
            r1 = move-exception
            goto L_0x0037
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.C1939b.m1747n():long");
    }
}
