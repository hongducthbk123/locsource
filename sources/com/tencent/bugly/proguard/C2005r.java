package com.tencent.bugly.proguard;

import android.content.Context;
import com.tencent.bugly.BuglyStrategy.C1910a;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import p004cn.jiguang.api.utils.ByteBufferUtils;

/* renamed from: com.tencent.bugly.proguard.r */
/* compiled from: BUGLY */
public final class C2005r {

    /* renamed from: b */
    private static C2005r f1640b;

    /* renamed from: a */
    public Map<String, String> f1641a = null;

    /* renamed from: c */
    private Context f1642c;

    private C2005r(Context context) {
        this.f1642c = context;
    }

    /* renamed from: a */
    public static C2005r m2062a(Context context) {
        if (f1640b == null) {
            f1640b = new C2005r(context);
        }
        return f1640b;
    }

    /* JADX WARNING: Removed duplicated region for block: B:80:0x0178 A[Catch:{ all -> 0x018a, Throwable -> 0x018f }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] mo19615a(java.lang.String r19, byte[] r20, com.tencent.bugly.proguard.C2011u r21, java.util.Map<java.lang.String, java.lang.String> r22) {
        /*
            r18 = this;
            if (r19 != 0) goto L_0x000c
            java.lang.String r4 = "Failed for no URL."
            r5 = 0
            java.lang.Object[] r5 = new java.lang.Object[r5]
            com.tencent.bugly.proguard.C2014w.m2119e(r4, r5)
            r4 = 0
        L_0x000b:
            return r4
        L_0x000c:
            r7 = 0
            r8 = 0
            if (r20 != 0) goto L_0x0057
            r4 = 0
        L_0x0012:
            java.lang.String r6 = "request: %s, send: %d (pid=%d | tid=%d)"
            r9 = 4
            java.lang.Object[] r9 = new java.lang.Object[r9]
            r10 = 0
            r9[r10] = r19
            r10 = 1
            java.lang.Long r11 = java.lang.Long.valueOf(r4)
            r9[r10] = r11
            r10 = 2
            int r11 = android.os.Process.myPid()
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            r9[r10] = r11
            r10 = 3
            int r11 = android.os.Process.myTid()
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            r9[r10] = r11
            com.tencent.bugly.proguard.C2014w.m2117c(r6, r9)
            r6 = 0
            r9 = r19
        L_0x003d:
            if (r7 > 0) goto L_0x01af
            if (r8 > 0) goto L_0x01af
            if (r6 == 0) goto L_0x005c
            r6 = 0
        L_0x0044:
            r0 = r18
            android.content.Context r10 = r0.f1642c
            java.lang.String r10 = com.tencent.bugly.crashreport.common.info.C1939b.m1732e(r10)
            if (r10 != 0) goto L_0x008e
            java.lang.String r10 = "Failed to request for network not avail"
            r11 = 0
            java.lang.Object[] r11 = new java.lang.Object[r11]
            com.tencent.bugly.proguard.C2014w.m2118d(r10, r11)
            goto L_0x003d
        L_0x0057:
            r0 = r20
            int r4 = r0.length
            long r4 = (long) r4
            goto L_0x0012
        L_0x005c:
            int r7 = r7 + 1
            r10 = 1
            if (r7 <= r10) goto L_0x0044
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "try time: "
            r10.<init>(r11)
            java.lang.StringBuilder r10 = r10.append(r7)
            java.lang.String r10 = r10.toString()
            r11 = 0
            java.lang.Object[] r11 = new java.lang.Object[r11]
            com.tencent.bugly.proguard.C2014w.m2117c(r10, r11)
            java.util.Random r10 = new java.util.Random
            long r12 = java.lang.System.currentTimeMillis()
            r10.<init>(r12)
            r11 = 10000(0x2710, float:1.4013E-41)
            int r10 = r10.nextInt(r11)
            long r10 = (long) r10
            r12 = 10000(0x2710, double:4.9407E-320)
            long r10 = r10 + r12
            android.os.SystemClock.sleep(r10)
            goto L_0x0044
        L_0x008e:
            r0 = r21
            r0.mo19633a(r4)
            r0 = r18
            r1 = r20
            r2 = r22
            java.net.HttpURLConnection r14 = r0.m2064a(r9, r1, r10, r2)
            if (r14 == 0) goto L_0x019a
            int r12 = r14.getResponseCode()     // Catch:{ IOException -> 0x016c }
            r10 = 200(0xc8, float:2.8E-43)
            if (r12 != r10) goto L_0x00d0
            java.util.Map r10 = m2065a(r14)     // Catch:{ IOException -> 0x016c }
            r0 = r18
            r0.f1641a = r10     // Catch:{ IOException -> 0x016c }
            byte[] r10 = m2066b(r14)     // Catch:{ IOException -> 0x016c }
            if (r10 != 0) goto L_0x00c2
            r12 = 0
        L_0x00b7:
            r0 = r21
            r0.mo19634b(r12)     // Catch:{ IOException -> 0x016c }
            r14.disconnect()     // Catch:{ Throwable -> 0x00c5 }
        L_0x00bf:
            r4 = r10
            goto L_0x000b
        L_0x00c2:
            int r11 = r10.length     // Catch:{ IOException -> 0x016c }
            long r12 = (long) r11
            goto L_0x00b7
        L_0x00c5:
            r4 = move-exception
            boolean r5 = com.tencent.bugly.proguard.C2014w.m2114a(r4)
            if (r5 != 0) goto L_0x00bf
            r4.printStackTrace()
            goto L_0x00bf
        L_0x00d0:
            r10 = 301(0x12d, float:4.22E-43)
            if (r12 == r10) goto L_0x00e0
            r10 = 302(0x12e, float:4.23E-43)
            if (r12 == r10) goto L_0x00e0
            r10 = 303(0x12f, float:4.25E-43)
            if (r12 == r10) goto L_0x00e0
            r10 = 307(0x133, float:4.3E-43)
            if (r12 != r10) goto L_0x0107
        L_0x00e0:
            r10 = 1
        L_0x00e1:
            if (r10 == 0) goto L_0x01cd
            r10 = 1
            java.lang.String r6 = "Location"
            java.lang.String r11 = r14.getHeaderField(r6)     // Catch:{ IOException -> 0x01b2 }
            if (r11 != 0) goto L_0x0114
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01b2 }
            java.lang.String r11 = "Failed to redirect: %d"
            r6.<init>(r11)     // Catch:{ IOException -> 0x01b2 }
            java.lang.StringBuilder r6 = r6.append(r12)     // Catch:{ IOException -> 0x01b2 }
            java.lang.String r6 = r6.toString()     // Catch:{ IOException -> 0x01b2 }
            r11 = 0
            java.lang.Object[] r11 = new java.lang.Object[r11]     // Catch:{ IOException -> 0x01b2 }
            com.tencent.bugly.proguard.C2014w.m2119e(r6, r11)     // Catch:{ IOException -> 0x01b2 }
            r14.disconnect()     // Catch:{ Throwable -> 0x0109 }
        L_0x0104:
            r4 = 0
            goto L_0x000b
        L_0x0107:
            r10 = 0
            goto L_0x00e1
        L_0x0109:
            r4 = move-exception
            boolean r5 = com.tencent.bugly.proguard.C2014w.m2114a(r4)
            if (r5 != 0) goto L_0x0104
            r4.printStackTrace()
            goto L_0x0104
        L_0x0114:
            int r8 = r8 + 1
            r7 = 0
            java.lang.String r6 = "redirect code: %d ,to:%s"
            r9 = 2
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch:{ IOException -> 0x01be }
            r13 = 0
            java.lang.Integer r15 = java.lang.Integer.valueOf(r12)     // Catch:{ IOException -> 0x01be }
            r9[r13] = r15     // Catch:{ IOException -> 0x01be }
            r13 = 1
            r9[r13] = r11     // Catch:{ IOException -> 0x01be }
            com.tencent.bugly.proguard.C2014w.m2117c(r6, r9)     // Catch:{ IOException -> 0x01be }
            r6 = r10
            r9 = r11
            r16 = r7
            r7 = r8
            r8 = r16
        L_0x0130:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01cb }
            java.lang.String r11 = "response code "
            r10.<init>(r11)     // Catch:{ IOException -> 0x01cb }
            java.lang.StringBuilder r10 = r10.append(r12)     // Catch:{ IOException -> 0x01cb }
            java.lang.String r10 = r10.toString()     // Catch:{ IOException -> 0x01cb }
            r11 = 0
            java.lang.Object[] r11 = new java.lang.Object[r11]     // Catch:{ IOException -> 0x01cb }
            com.tencent.bugly.proguard.C2014w.m2118d(r10, r11)     // Catch:{ IOException -> 0x01cb }
            int r10 = r14.getContentLength()     // Catch:{ IOException -> 0x01cb }
            long r10 = (long) r10     // Catch:{ IOException -> 0x01cb }
            r12 = 0
            int r12 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r12 >= 0) goto L_0x0152
            r10 = 0
        L_0x0152:
            r0 = r21
            r0.mo19634b(r10)     // Catch:{ IOException -> 0x01cb }
            r14.disconnect()     // Catch:{ Throwable -> 0x0161 }
        L_0x015a:
            r16 = r7
            r7 = r8
            r8 = r16
            goto L_0x003d
        L_0x0161:
            r10 = move-exception
            boolean r11 = com.tencent.bugly.proguard.C2014w.m2114a(r10)
            if (r11 != 0) goto L_0x015a
            r10.printStackTrace()
            goto L_0x015a
        L_0x016c:
            r10 = move-exception
            r16 = r8
            r8 = r7
            r7 = r16
        L_0x0172:
            boolean r11 = com.tencent.bugly.proguard.C2014w.m2114a(r10)     // Catch:{ all -> 0x018a }
            if (r11 != 0) goto L_0x017b
            r10.printStackTrace()     // Catch:{ all -> 0x018a }
        L_0x017b:
            r14.disconnect()     // Catch:{ Throwable -> 0x017f }
            goto L_0x015a
        L_0x017f:
            r10 = move-exception
            boolean r11 = com.tencent.bugly.proguard.C2014w.m2114a(r10)
            if (r11 != 0) goto L_0x015a
            r10.printStackTrace()
            goto L_0x015a
        L_0x018a:
            r4 = move-exception
            r14.disconnect()     // Catch:{ Throwable -> 0x018f }
        L_0x018e:
            throw r4
        L_0x018f:
            r5 = move-exception
            boolean r6 = com.tencent.bugly.proguard.C2014w.m2114a(r5)
            if (r6 != 0) goto L_0x018e
            r5.printStackTrace()
            goto L_0x018e
        L_0x019a:
            java.lang.String r10 = "Failed to execute post."
            r11 = 0
            java.lang.Object[] r11 = new java.lang.Object[r11]
            com.tencent.bugly.proguard.C2014w.m2117c(r10, r11)
            r10 = 0
            r0 = r21
            r0.mo19634b(r10)
            r16 = r8
            r8 = r7
            r7 = r16
            goto L_0x015a
        L_0x01af:
            r4 = 0
            goto L_0x000b
        L_0x01b2:
            r6 = move-exception
            r16 = r6
            r6 = r10
            r10 = r16
            r17 = r8
            r8 = r7
            r7 = r17
            goto L_0x0172
        L_0x01be:
            r6 = move-exception
            r9 = r11
            r16 = r10
            r10 = r6
            r6 = r16
            r17 = r7
            r7 = r8
            r8 = r17
            goto L_0x0172
        L_0x01cb:
            r10 = move-exception
            goto L_0x0172
        L_0x01cd:
            r16 = r8
            r8 = r7
            r7 = r16
            goto L_0x0130
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2005r.mo19615a(java.lang.String, byte[], com.tencent.bugly.proguard.u, java.util.Map):byte[]");
    }

    /* renamed from: a */
    private static Map<String, String> m2065a(HttpURLConnection httpURLConnection) {
        HashMap hashMap = new HashMap();
        Map headerFields = httpURLConnection.getHeaderFields();
        if (headerFields == null || headerFields.size() == 0) {
            return null;
        }
        for (String str : headerFields.keySet()) {
            List list = (List) headerFields.get(str);
            if (list.size() > 0) {
                hashMap.put(str, list.get(0));
            }
        }
        return hashMap;
    }

    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v1, types: [java.io.BufferedInputStream] */
    /* JADX WARNING: type inference failed for: r2v2 */
    /* JADX WARNING: type inference failed for: r2v3, types: [java.io.BufferedInputStream] */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x004b A[SYNTHETIC, Splitter:B:29:0x004b] */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] m2066b(java.net.HttpURLConnection r6) {
        /*
            r0 = 0
            if (r6 != 0) goto L_0x0004
        L_0x0003:
            return r0
        L_0x0004:
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x0056, all -> 0x0046 }
            java.io.InputStream r1 = r6.getInputStream()     // Catch:{ Throwable -> 0x0056, all -> 0x0046 }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x0056, all -> 0x0046 }
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ Throwable -> 0x0021 }
            r1.<init>()     // Catch:{ Throwable -> 0x0021 }
            r3 = 1024(0x400, float:1.435E-42)
            byte[] r3 = new byte[r3]     // Catch:{ Throwable -> 0x0021 }
        L_0x0016:
            int r4 = r2.read(r3)     // Catch:{ Throwable -> 0x0021 }
            if (r4 <= 0) goto L_0x0036
            r5 = 0
            r1.write(r3, r5, r4)     // Catch:{ Throwable -> 0x0021 }
            goto L_0x0016
        L_0x0021:
            r1 = move-exception
        L_0x0022:
            boolean r3 = com.tencent.bugly.proguard.C2014w.m2114a(r1)     // Catch:{ all -> 0x0054 }
            if (r3 != 0) goto L_0x002b
            r1.printStackTrace()     // Catch:{ all -> 0x0054 }
        L_0x002b:
            if (r2 == 0) goto L_0x0003
            r2.close()     // Catch:{ Throwable -> 0x0031 }
            goto L_0x0003
        L_0x0031:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0003
        L_0x0036:
            r1.flush()     // Catch:{ Throwable -> 0x0021 }
            byte[] r0 = r1.toByteArray()     // Catch:{ Throwable -> 0x0021 }
            r2.close()     // Catch:{ Throwable -> 0x0041 }
            goto L_0x0003
        L_0x0041:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0003
        L_0x0046:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x0049:
            if (r2 == 0) goto L_0x004e
            r2.close()     // Catch:{ Throwable -> 0x004f }
        L_0x004e:
            throw r0
        L_0x004f:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x004e
        L_0x0054:
            r0 = move-exception
            goto L_0x0049
        L_0x0056:
            r1 = move-exception
            r2 = r0
            goto L_0x0022
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2005r.m2066b(java.net.HttpURLConnection):byte[]");
    }

    /* renamed from: a */
    private HttpURLConnection m2064a(String str, byte[] bArr, String str2, Map<String, String> map) {
        if (str == null) {
            C2014w.m2119e("destUrl is null.", new Object[0]);
            return null;
        }
        HttpURLConnection a = m2063a(str2, str);
        if (a == null) {
            C2014w.m2119e("Failed to get HttpURLConnection object.", new Object[0]);
            return null;
        }
        try {
            a.setRequestProperty("wup_version", "3.0");
            if (map != null && map.size() > 0) {
                for (Entry entry : map.entrySet()) {
                    a.setRequestProperty((String) entry.getKey(), URLEncoder.encode((String) entry.getValue(), "utf-8"));
                }
            }
            a.setRequestProperty("A37", URLEncoder.encode(str2, "utf-8"));
            a.setRequestProperty("A38", URLEncoder.encode(str2, "utf-8"));
            a.connect();
            OutputStream outputStream = a.getOutputStream();
            if (bArr == null) {
                outputStream.write(0);
            } else {
                outputStream.write(bArr);
            }
            return a;
        } catch (Throwable th) {
            if (!C2014w.m2114a(th)) {
                th.printStackTrace();
            }
            C2014w.m2119e("Failed to upload crash, please check your network.", new Object[0]);
            return null;
        }
    }

    /* renamed from: a */
    private static HttpURLConnection m2063a(String str, String str2) {
        HttpURLConnection httpURLConnection;
        try {
            URL url = new URL(str2);
            if (str == null || !str.toLowerCase(Locale.US).contains("wap")) {
                httpURLConnection = (HttpURLConnection) url.openConnection();
            } else {
                httpURLConnection = (HttpURLConnection) url.openConnection(new Proxy(Type.HTTP, new InetSocketAddress(System.getProperty("http.proxyHost"), Integer.parseInt(System.getProperty("http.proxyPort")))));
            }
            httpURLConnection.setConnectTimeout(C1910a.MAX_USERDATA_VALUE_LENGTH);
            httpURLConnection.setReadTimeout(ByteBufferUtils.ERROR_CODE);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setInstanceFollowRedirects(false);
            return httpURLConnection;
        } catch (Throwable th) {
            if (!C2014w.m2114a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }
}
