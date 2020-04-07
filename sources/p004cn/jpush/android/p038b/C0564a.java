package p004cn.jpush.android.p038b;

import android.content.Context;
import com.google.android.vending.expansion.downloader.Constants;
import com.google.common.net.HttpHeaders;
import org.apache.http.protocol.HTTP;
import p004cn.jiguang.net.HttpRequest;
import p004cn.jiguang.net.HttpResponse;
import p004cn.jiguang.net.HttpUtils;

/* renamed from: cn.jpush.android.b.a */
public final class C0564a {
    /* renamed from: a */
    public static HttpResponse m1212a(String str, int i, long j) {
        HttpResponse httpResponse = null;
        if (j < 200 || j > Constants.WATCHDOG_WAKE_TIMER) {
            j = 2000;
        }
        int i2 = 0;
        while (true) {
            try {
                HttpRequest httpRequest = new HttpRequest(str);
                httpRequest.setRequestProperty("Connection", HTTP.CONN_CLOSE);
                httpRequest.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, HTTP.IDENTITY_CODING);
                httpResponse = HttpUtils.httpGet((Context) null, httpRequest);
                if (httpResponse.getResponseCode() == 200) {
                    break;
                }
            } catch (AssertionError | Exception e) {
            }
            if (i2 >= 5) {
                break;
            }
            i2++;
            try {
                Thread.sleep(j);
            } catch (InterruptedException e2) {
            }
        }
        return httpResponse;
    }

    /* renamed from: a */
    public static byte[] m1213a(String str, int i, long j, int i2) {
        byte[] bArr = null;
        for (int i3 = 0; i3 < 4; i3++) {
            bArr = m1214b(str, 5, Constants.ACTIVE_THREAD_WATCHDOG);
            if (bArr != null) {
                break;
            }
        }
        return bArr;
    }

    /* JADX WARNING: Removed duplicated region for block: B:104:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0103  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0069 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x007a A[SYNTHETIC, Splitter:B:41:0x007a] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0091 A[SYNTHETIC, Splitter:B:50:0x0091] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x009e A[SYNTHETIC, Splitter:B:56:0x009e] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00ad A[SYNTHETIC, Splitter:B:63:0x00ad] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x00cc  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] m1214b(java.lang.String r10, int r11, long r12) {
        /*
            r8 = 200(0xc8, float:2.8E-43)
            r2 = 0
            r1 = 0
            if (r11 <= 0) goto L_0x000a
            r0 = 10
            if (r11 <= r0) goto L_0x000b
        L_0x000a:
            r11 = 1
        L_0x000b:
            r4 = 200(0xc8, double:9.9E-322)
            int r0 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r0 < 0) goto L_0x0018
            r4 = 60000(0xea60, double:2.9644E-319)
            int r0 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x001a
        L_0x0018:
            r12 = 2000(0x7d0, double:9.88E-321)
        L_0x001a:
            r3 = -1
            r6 = r2
            r4 = r1
            r5 = r1
        L_0x001e:
            java.net.URL r0 = new java.net.URL     // Catch:{ SSLPeerUnverifiedException -> 0x00f3, SSLHandshakeException -> 0x00f0, Exception -> 0x00ed, all -> 0x00eb }
            r0.<init>(r10)     // Catch:{ SSLPeerUnverifiedException -> 0x00f3, SSLHandshakeException -> 0x00f0, Exception -> 0x00ed, all -> 0x00eb }
            java.net.URLConnection r0 = r0.openConnection()     // Catch:{ SSLPeerUnverifiedException -> 0x00f3, SSLHandshakeException -> 0x00f0, Exception -> 0x00ed, all -> 0x00eb }
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ SSLPeerUnverifiedException -> 0x00f3, SSLHandshakeException -> 0x00f0, Exception -> 0x00ed, all -> 0x00eb }
            java.lang.String r5 = "Accept-Encoding"
            java.lang.String r7 = "identity"
            r0.setRequestProperty(r5, r7)     // Catch:{ SSLPeerUnverifiedException -> 0x006b, SSLHandshakeException -> 0x0087, Exception -> 0x009b }
            java.lang.String r5 = "Connection"
            java.lang.String r7 = "Close"
            r0.addRequestProperty(r5, r7)     // Catch:{ SSLPeerUnverifiedException -> 0x006b, SSLHandshakeException -> 0x0087, Exception -> 0x009b }
            int r3 = r0.getResponseCode()     // Catch:{ SSLPeerUnverifiedException -> 0x006b, SSLHandshakeException -> 0x0087, Exception -> 0x009b }
            if (r3 != r8) goto L_0x005c
            int r2 = r0.getContentLength()     // Catch:{ SSLPeerUnverifiedException -> 0x006b, SSLHandshakeException -> 0x0087, Exception -> 0x009b }
            java.io.InputStream r4 = r0.getInputStream()     // Catch:{ SSLPeerUnverifiedException -> 0x006b, SSLHandshakeException -> 0x0087, Exception -> 0x009b }
            if (r4 == 0) goto L_0x00fd
            byte[] r5 = p004cn.jiguang.net.HttpUtils.readInputStream(r4)     // Catch:{ SSLPeerUnverifiedException -> 0x006b, SSLHandshakeException -> 0x0087, Exception -> 0x009b }
        L_0x004b:
            if (r4 == 0) goto L_0x0050
            r4.close()     // Catch:{ IOException -> 0x00da }
        L_0x0050:
            if (r0 == 0) goto L_0x0109
            r0.disconnect()
            r0 = r5
        L_0x0056:
            if (r8 != r3) goto L_0x00cc
            if (r2 != 0) goto L_0x00c4
            r0 = r1
        L_0x005b:
            return r0
        L_0x005c:
            if (r4 == 0) goto L_0x0061
            r4.close()     // Catch:{ IOException -> 0x00dd }
        L_0x0061:
            if (r0 == 0) goto L_0x00fa
            r0.disconnect()
            r5 = r0
        L_0x0067:
            if (r6 < r11) goto L_0x00b6
            r0 = r1
            goto L_0x005b
        L_0x006b:
            r5 = move-exception
            r9 = r2
            r2 = r3
            r3 = r4
            r4 = r0
            r0 = r9
        L_0x0071:
            java.lang.String r5 = "HttpManager"
            java.lang.String r7 = "Catch SSLPeerUnverifiedException, http client execute error!"
            p004cn.jpush.android.p040d.C0582e.m1308d(r5, r7)     // Catch:{ all -> 0x00e7 }
            if (r3 == 0) goto L_0x007d
            r3.close()     // Catch:{ IOException -> 0x00df }
        L_0x007d:
            if (r4 == 0) goto L_0x0103
            r4.disconnect()
            r5 = r4
            r4 = r3
            r3 = r2
            r2 = r0
            goto L_0x0067
        L_0x0087:
            r5 = move-exception
        L_0x0088:
            java.lang.String r5 = "HttpManager"
            java.lang.String r6 = "Catch SSLHandshakeException, http client execute error!"
            p004cn.jpush.android.p040d.C0582e.m1302a(r5, r6)     // Catch:{ all -> 0x00a8 }
            if (r4 == 0) goto L_0x0094
            r4.close()     // Catch:{ IOException -> 0x00e1 }
        L_0x0094:
            if (r0 == 0) goto L_0x0100
            r0.disconnect()
            r0 = r1
            goto L_0x0056
        L_0x009b:
            r5 = move-exception
        L_0x009c:
            if (r4 == 0) goto L_0x00a1
            r4.close()     // Catch:{ IOException -> 0x00e3 }
        L_0x00a1:
            if (r0 == 0) goto L_0x00fa
            r0.disconnect()
            r5 = r0
            goto L_0x0067
        L_0x00a8:
            r1 = move-exception
            r5 = r0
            r0 = r1
        L_0x00ab:
            if (r4 == 0) goto L_0x00b0
            r4.close()     // Catch:{ IOException -> 0x00e5 }
        L_0x00b0:
            if (r5 == 0) goto L_0x00b5
            r5.disconnect()
        L_0x00b5:
            throw r0
        L_0x00b6:
            int r0 = r6 + 1
            long r6 = (long) r0
            long r6 = r6 * r12
            java.lang.Thread.sleep(r6)     // Catch:{ InterruptedException -> 0x00c0 }
            r6 = r0
            goto L_0x001e
        L_0x00c0:
            r6 = move-exception
            r6 = r0
            goto L_0x001e
        L_0x00c4:
            int r3 = r0.length     // Catch:{ Exception -> 0x00c9 }
            if (r3 >= r2) goto L_0x005b
            r0 = r1
            goto L_0x005b
        L_0x00c9:
            r0 = move-exception
            r0 = r1
            goto L_0x005b
        L_0x00cc:
            r0 = 400(0x190, float:5.6E-43)
            if (r0 != r3) goto L_0x00d2
            r0 = r1
            goto L_0x005b
        L_0x00d2:
            r0 = 404(0x194, float:5.66E-43)
            if (r0 != r3) goto L_0x00d8
            r0 = r1
            goto L_0x005b
        L_0x00d8:
            r0 = r1
            goto L_0x005b
        L_0x00da:
            r4 = move-exception
            goto L_0x0050
        L_0x00dd:
            r5 = move-exception
            goto L_0x0061
        L_0x00df:
            r5 = move-exception
            goto L_0x007d
        L_0x00e1:
            r4 = move-exception
            goto L_0x0094
        L_0x00e3:
            r5 = move-exception
            goto L_0x00a1
        L_0x00e5:
            r1 = move-exception
            goto L_0x00b0
        L_0x00e7:
            r0 = move-exception
            r5 = r4
            r4 = r3
            goto L_0x00ab
        L_0x00eb:
            r0 = move-exception
            goto L_0x00ab
        L_0x00ed:
            r0 = move-exception
            r0 = r5
            goto L_0x009c
        L_0x00f0:
            r0 = move-exception
            r0 = r5
            goto L_0x0088
        L_0x00f3:
            r0 = move-exception
            r0 = r2
            r2 = r3
            r3 = r4
            r4 = r5
            goto L_0x0071
        L_0x00fa:
            r5 = r0
            goto L_0x0067
        L_0x00fd:
            r5 = r1
            goto L_0x004b
        L_0x0100:
            r0 = r1
            goto L_0x0056
        L_0x0103:
            r5 = r4
            r4 = r3
            r3 = r2
            r2 = r0
            goto L_0x0067
        L_0x0109:
            r0 = r5
            goto L_0x0056
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jpush.android.p038b.C0564a.m1214b(java.lang.String, int, long):byte[]");
    }
}
