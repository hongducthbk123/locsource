package p004cn.jiguang.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.adjust.sdk.Constants;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.HttpHost;
import p004cn.jiguang.p029e.C0501d;

/* renamed from: cn.jiguang.net.HttpUtils */
public class HttpUtils {
    public static final String ENCODING_UTF_8 = "UTF-8";
    public static final String EQUAL_SIGN = "=";
    public static final String HTTP_DEFUALT_PROXY = "10.0.0.172";
    public static final String PARAMETERS_SEPARATOR = "&";
    public static final String PATHS_SEPARATOR = "/";
    public static final String URL_AND_PARA_SEPARATOR = "?";

    /* renamed from: a */
    private static final SimpleDateFormat f636a = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH);

    /* renamed from: cn.jiguang.net.HttpUtils$HttpListener */
    public abstract class HttpListener {
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x01c1  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x021d  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x011b A[Catch:{ all -> 0x01b8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x012e  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x017b  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x01a7 A[SYNTHETIC, Splitter:B:93:0x01a7] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static p004cn.jiguang.net.HttpResponse m1113a(android.content.Context r9, p004cn.jiguang.net.HttpRequest r10, boolean r11) {
        /*
            r3 = 0
            if (r10 != 0) goto L_0x0005
            r1 = r3
        L_0x0004:
            return r1
        L_0x0005:
            cn.jiguang.net.HttpResponse r4 = new cn.jiguang.net.HttpResponse
            java.lang.String r1 = r10.getUrl()
            r4.<init>(r1)
            java.lang.String r1 = r10.getUrl()     // Catch:{ MalformedURLException -> 0x024c, IOException -> 0x0240, Exception -> 0x0234, StackOverflowError -> 0x0208, all -> 0x0222 }
            java.net.HttpURLConnection r2 = getHttpURLConnectionWithProxy(r9, r1)     // Catch:{ MalformedURLException -> 0x024c, IOException -> 0x0240, Exception -> 0x0234, StackOverflowError -> 0x0208, all -> 0x0222 }
            boolean r1 = r2 instanceof javax.net.ssl.HttpsURLConnection     // Catch:{ MalformedURLException -> 0x00e3, IOException -> 0x0109, Exception -> 0x0155, StackOverflowError -> 0x022f, all -> 0x0226 }
            if (r1 == 0) goto L_0x0057
            java.lang.String r1 = "TLS"
            javax.net.ssl.SSLContext r5 = javax.net.ssl.SSLContext.getInstance(r1)     // Catch:{ Throwable -> 0x00c8 }
            r1 = 1
            javax.net.ssl.TrustManager[] r1 = new javax.net.ssl.TrustManager[r1]     // Catch:{ Throwable -> 0x00c8 }
            cn.jiguang.net.SSLTrustManager r6 = r10.getSslTrustManager()     // Catch:{ Throwable -> 0x00c8 }
            if (r6 == 0) goto L_0x00be
            r6 = 0
            cn.jiguang.net.SSLTrustManager r7 = r10.getSslTrustManager()     // Catch:{ Throwable -> 0x00c8 }
            r1[r6] = r7     // Catch:{ Throwable -> 0x00c8 }
        L_0x0030:
            r6 = 0
            java.security.SecureRandom r7 = new java.security.SecureRandom     // Catch:{ Throwable -> 0x00c8 }
            r7.<init>()     // Catch:{ Throwable -> 0x00c8 }
            r5.init(r6, r1, r7)     // Catch:{ Throwable -> 0x00c8 }
            if (r5 == 0) goto L_0x0046
            r0 = r2
            javax.net.ssl.HttpsURLConnection r0 = (javax.net.ssl.HttpsURLConnection) r0     // Catch:{ Throwable -> 0x00c8 }
            r1 = r0
            javax.net.ssl.SSLSocketFactory r5 = r5.getSocketFactory()     // Catch:{ Throwable -> 0x00c8 }
            r1.setSSLSocketFactory(r5)     // Catch:{ Throwable -> 0x00c8 }
        L_0x0046:
            javax.net.ssl.HostnameVerifier r1 = r10.getHostnameVerifier()     // Catch:{ Throwable -> 0x00c8 }
            if (r1 == 0) goto L_0x00fb
            r0 = r2
            javax.net.ssl.HttpsURLConnection r0 = (javax.net.ssl.HttpsURLConnection) r0     // Catch:{ Throwable -> 0x00c8 }
            r1 = r0
            javax.net.ssl.HostnameVerifier r5 = r10.getHostnameVerifier()     // Catch:{ Throwable -> 0x00c8 }
            r1.setHostnameVerifier(r5)     // Catch:{ Throwable -> 0x00c8 }
        L_0x0057:
            if (r10 == 0) goto L_0x005b
            if (r2 != 0) goto L_0x0132
        L_0x005b:
            if (r11 == 0) goto L_0x0077
            java.lang.String r1 = "POST"
            r2.setRequestMethod(r1)     // Catch:{ MalformedURLException -> 0x00e3, IOException -> 0x0109, Exception -> 0x0155, StackOverflowError -> 0x022f, all -> 0x0226 }
            r1 = 1
            r2.setDoOutput(r1)     // Catch:{ MalformedURLException -> 0x00e3, IOException -> 0x0109, Exception -> 0x0155, StackOverflowError -> 0x022f, all -> 0x0226 }
            r1 = 1
            r2.setDoInput(r1)     // Catch:{ MalformedURLException -> 0x00e3, IOException -> 0x0109, Exception -> 0x0155, StackOverflowError -> 0x022f, all -> 0x0226 }
            byte[] r1 = r10.getParas()     // Catch:{ MalformedURLException -> 0x00e3, IOException -> 0x0109, Exception -> 0x0155, StackOverflowError -> 0x022f, all -> 0x0226 }
            if (r1 == 0) goto L_0x0077
            java.io.OutputStream r5 = r2.getOutputStream()     // Catch:{ MalformedURLException -> 0x00e3, IOException -> 0x0109, Exception -> 0x0155, StackOverflowError -> 0x022f, all -> 0x0226 }
            r5.write(r1)     // Catch:{ MalformedURLException -> 0x00e3, IOException -> 0x0109, Exception -> 0x0155, StackOverflowError -> 0x022f, all -> 0x0226 }
        L_0x0077:
            java.io.InputStream r1 = r2.getInputStream()     // Catch:{ MalformedURLException -> 0x00e3, IOException -> 0x0109, Exception -> 0x0155, StackOverflowError -> 0x022f, all -> 0x0226 }
            boolean r5 = r10.isHaveRspData()     // Catch:{ MalformedURLException -> 0x01a4, IOException -> 0x0245, Exception -> 0x0239, StackOverflowError -> 0x0232 }
            if (r5 == 0) goto L_0x0180
            byte[] r5 = readInputStream(r1)     // Catch:{ MalformedURLException -> 0x01a4, IOException -> 0x0245, Exception -> 0x0239, StackOverflowError -> 0x0232 }
            if (r5 == 0) goto L_0x0091
            java.lang.String r6 = new java.lang.String     // Catch:{ MalformedURLException -> 0x01a4, IOException -> 0x0245, Exception -> 0x0239, StackOverflowError -> 0x0232 }
            java.lang.String r7 = "UTF-8"
            r6.<init>(r5, r7)     // Catch:{ MalformedURLException -> 0x01a4, IOException -> 0x0245, Exception -> 0x0239, StackOverflowError -> 0x0232 }
            r4.setResponseBody(r6)     // Catch:{ MalformedURLException -> 0x01a4, IOException -> 0x0245, Exception -> 0x0239, StackOverflowError -> 0x0232 }
        L_0x0091:
            if (r2 == 0) goto L_0x00b0
            int r5 = r2.getResponseCode()     // Catch:{ MalformedURLException -> 0x01a4, IOException -> 0x0245, Exception -> 0x0239, StackOverflowError -> 0x0232 }
            r4.setResponseCode(r5)     // Catch:{ MalformedURLException -> 0x01a4, IOException -> 0x0245, Exception -> 0x0239, StackOverflowError -> 0x0232 }
            java.lang.String r5 = "expires"
            java.lang.String r6 = "Expires"
            java.lang.String r6 = r2.getHeaderField(r6)     // Catch:{ MalformedURLException -> 0x01a4, IOException -> 0x0245, Exception -> 0x0239, StackOverflowError -> 0x0232 }
            r4.setResponseHeader(r5, r6)     // Catch:{ MalformedURLException -> 0x01a4, IOException -> 0x0245, Exception -> 0x0239, StackOverflowError -> 0x0232 }
            java.lang.String r5 = "cache-control"
            java.lang.String r6 = "Cache-Control"
            java.lang.String r6 = r2.getHeaderField(r6)     // Catch:{ MalformedURLException -> 0x01a4, IOException -> 0x0245, Exception -> 0x0239, StackOverflowError -> 0x0232 }
            r4.setResponseHeader(r5, r6)     // Catch:{ MalformedURLException -> 0x01a4, IOException -> 0x0245, Exception -> 0x0239, StackOverflowError -> 0x0232 }
        L_0x00b0:
            p004cn.jiguang.p031g.C0526g.m1087a(r1)
            p004cn.jiguang.p031g.C0526g.m1087a(r3)
            if (r2 == 0) goto L_0x00bb
            r2.disconnect()
        L_0x00bb:
            r1 = r4
            goto L_0x0004
        L_0x00be:
            r6 = 0
            cn.jiguang.net.DefaultTrustManager r7 = new cn.jiguang.net.DefaultTrustManager     // Catch:{ Throwable -> 0x00c8 }
            r7.<init>()     // Catch:{ Throwable -> 0x00c8 }
            r1[r6] = r7     // Catch:{ Throwable -> 0x00c8 }
            goto L_0x0030
        L_0x00c8:
            r1 = move-exception
            java.lang.String r5 = "HttpUtils"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x00e3, IOException -> 0x0109, Exception -> 0x0155, StackOverflowError -> 0x022f, all -> 0x0226 }
            java.lang.String r7 = "set ssl config error:"
            r6.<init>(r7)     // Catch:{ MalformedURLException -> 0x00e3, IOException -> 0x0109, Exception -> 0x0155, StackOverflowError -> 0x022f, all -> 0x0226 }
            java.lang.String r1 = r1.getMessage()     // Catch:{ MalformedURLException -> 0x00e3, IOException -> 0x0109, Exception -> 0x0155, StackOverflowError -> 0x022f, all -> 0x0226 }
            java.lang.StringBuilder r1 = r6.append(r1)     // Catch:{ MalformedURLException -> 0x00e3, IOException -> 0x0109, Exception -> 0x0155, StackOverflowError -> 0x022f, all -> 0x0226 }
            java.lang.String r1 = r1.toString()     // Catch:{ MalformedURLException -> 0x00e3, IOException -> 0x0109, Exception -> 0x0155, StackOverflowError -> 0x022f, all -> 0x0226 }
            p004cn.jiguang.p029e.C0501d.m907c(r5, r1)     // Catch:{ MalformedURLException -> 0x00e3, IOException -> 0x0109, Exception -> 0x0155, StackOverflowError -> 0x022f, all -> 0x0226 }
            goto L_0x0057
        L_0x00e3:
            r1 = move-exception
            r1 = r3
        L_0x00e5:
            r5 = 3004(0xbbc, float:4.21E-42)
            r4.setResponseCode(r5)     // Catch:{ all -> 0x022a }
            java.lang.String r5 = "MalformedURLException"
            r4.setResponseBody(r5)     // Catch:{ all -> 0x022a }
            p004cn.jiguang.p031g.C0526g.m1087a(r1)
            p004cn.jiguang.p031g.C0526g.m1087a(r3)
            if (r2 == 0) goto L_0x00bb
            r2.disconnect()
            goto L_0x00bb
        L_0x00fb:
            r0 = r2
            javax.net.ssl.HttpsURLConnection r0 = (javax.net.ssl.HttpsURLConnection) r0     // Catch:{ Throwable -> 0x00c8 }
            r1 = r0
            cn.jiguang.net.DefaultHostVerifier r5 = new cn.jiguang.net.DefaultHostVerifier     // Catch:{ Throwable -> 0x00c8 }
            r5.<init>()     // Catch:{ Throwable -> 0x00c8 }
            r1.setHostnameVerifier(r5)     // Catch:{ Throwable -> 0x00c8 }
            goto L_0x0057
        L_0x0109:
            r1 = move-exception
            r5 = r2
            r2 = r3
        L_0x010c:
            r6 = 2998(0xbb6, float:4.201E-42)
            r4.setResponseCode(r6)     // Catch:{ all -> 0x01b8 }
            java.lang.String r6 = "网络错误"
            r4.setResponseBody(r6)     // Catch:{ all -> 0x01b8 }
            boolean r6 = r1 instanceof java.net.SocketTimeoutException     // Catch:{ all -> 0x01b8 }
            if (r6 == 0) goto L_0x01a7
            r1 = 3001(0xbb9, float:4.205E-42)
            r4.setResponseCode(r1)     // Catch:{ all -> 0x01b8 }
            java.lang.String r1 = "请求超时"
            r4.setResponseBody(r1)     // Catch:{ all -> 0x01b8 }
        L_0x0126:
            p004cn.jiguang.p031g.C0526g.m1087a(r2)
            p004cn.jiguang.p031g.C0526g.m1087a(r3)
            if (r5 == 0) goto L_0x00bb
            r5.disconnect()
            goto L_0x00bb
        L_0x0132:
            java.util.Map r1 = r10.getRequestProperties()     // Catch:{ MalformedURLException -> 0x00e3, IOException -> 0x0109, Exception -> 0x0155, StackOverflowError -> 0x022f, all -> 0x0226 }
            setURLConnection(r1, r2)     // Catch:{ MalformedURLException -> 0x00e3, IOException -> 0x0109, Exception -> 0x0155, StackOverflowError -> 0x022f, all -> 0x0226 }
            int r1 = r10.getConnectTimeout()     // Catch:{ MalformedURLException -> 0x00e3, IOException -> 0x0109, Exception -> 0x0155, StackOverflowError -> 0x022f, all -> 0x0226 }
            if (r1 < 0) goto L_0x0146
            int r1 = r10.getConnectTimeout()     // Catch:{ MalformedURLException -> 0x00e3, IOException -> 0x0109, Exception -> 0x0155, StackOverflowError -> 0x022f, all -> 0x0226 }
            r2.setConnectTimeout(r1)     // Catch:{ MalformedURLException -> 0x00e3, IOException -> 0x0109, Exception -> 0x0155, StackOverflowError -> 0x022f, all -> 0x0226 }
        L_0x0146:
            int r1 = r10.getReadTimeout()     // Catch:{ MalformedURLException -> 0x00e3, IOException -> 0x0109, Exception -> 0x0155, StackOverflowError -> 0x022f, all -> 0x0226 }
            if (r1 < 0) goto L_0x005b
            int r1 = r10.getReadTimeout()     // Catch:{ MalformedURLException -> 0x00e3, IOException -> 0x0109, Exception -> 0x0155, StackOverflowError -> 0x022f, all -> 0x0226 }
            r2.setReadTimeout(r1)     // Catch:{ MalformedURLException -> 0x00e3, IOException -> 0x0109, Exception -> 0x0155, StackOverflowError -> 0x022f, all -> 0x0226 }
            goto L_0x005b
        L_0x0155:
            r1 = move-exception
            r5 = r2
            r2 = r3
        L_0x0158:
            r6 = 3006(0xbbe, float:4.212E-42)
            r4.setResponseCode(r6)     // Catch:{ all -> 0x01b8 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x01b8 }
            java.lang.String r7 = "UNKnow execption"
            r6.<init>(r7)     // Catch:{ all -> 0x01b8 }
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x01b8 }
            java.lang.StringBuilder r1 = r6.append(r1)     // Catch:{ all -> 0x01b8 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x01b8 }
            r4.setResponseBody(r1)     // Catch:{ all -> 0x01b8 }
            p004cn.jiguang.p031g.C0526g.m1087a(r2)
            p004cn.jiguang.p031g.C0526g.m1087a(r3)
            if (r5 == 0) goto L_0x00bb
            r5.disconnect()
            goto L_0x00bb
        L_0x0180:
            int r5 = r2.getResponseCode()     // Catch:{ MalformedURLException -> 0x01a4, IOException -> 0x0245, Exception -> 0x0239, StackOverflowError -> 0x0232 }
            r6 = 200(0xc8, float:2.8E-43)
            if (r5 == r6) goto L_0x0091
            boolean r5 = r10.isNeedErrorInput()     // Catch:{ MalformedURLException -> 0x01a4, IOException -> 0x0245, Exception -> 0x0239, StackOverflowError -> 0x0232 }
            if (r5 == 0) goto L_0x0091
            java.io.InputStream r3 = r2.getErrorStream()     // Catch:{ MalformedURLException -> 0x01a4, IOException -> 0x0245, Exception -> 0x0239, StackOverflowError -> 0x0232 }
            byte[] r5 = readInputStream(r3)     // Catch:{ MalformedURLException -> 0x01a4, IOException -> 0x0245, Exception -> 0x0239, StackOverflowError -> 0x0232 }
            if (r5 == 0) goto L_0x0091
            java.lang.String r6 = new java.lang.String     // Catch:{ MalformedURLException -> 0x01a4, IOException -> 0x0245, Exception -> 0x0239, StackOverflowError -> 0x0232 }
            java.lang.String r7 = "UTF-8"
            r6.<init>(r5, r7)     // Catch:{ MalformedURLException -> 0x01a4, IOException -> 0x0245, Exception -> 0x0239, StackOverflowError -> 0x0232 }
            r4.setResponseBody(r6)     // Catch:{ MalformedURLException -> 0x01a4, IOException -> 0x0245, Exception -> 0x0239, StackOverflowError -> 0x0232 }
            goto L_0x0091
        L_0x01a4:
            r5 = move-exception
            goto L_0x00e5
        L_0x01a7:
            boolean r6 = r1 instanceof java.net.UnknownHostException     // Catch:{ all -> 0x01b8 }
            if (r6 == 0) goto L_0x01c5
            r1 = 3003(0xbbb, float:4.208E-42)
            r4.setResponseCode(r1)     // Catch:{ all -> 0x01b8 }
            java.lang.String r1 = "域名无效"
            r4.setResponseBody(r1)     // Catch:{ all -> 0x01b8 }
            goto L_0x0126
        L_0x01b8:
            r1 = move-exception
        L_0x01b9:
            p004cn.jiguang.p031g.C0526g.m1087a(r2)
            p004cn.jiguang.p031g.C0526g.m1087a(r3)
            if (r5 == 0) goto L_0x01c4
            r5.disconnect()
        L_0x01c4:
            throw r1
        L_0x01c5:
            boolean r1 = r1 instanceof javax.net.ssl.SSLHandshakeException     // Catch:{ all -> 0x01b8 }
            if (r1 == 0) goto L_0x0126
            r1 = 3005(0xbbd, float:4.211E-42)
            r4.setResponseCode(r1)     // Catch:{ all -> 0x01b8 }
            java.lang.String r1 = "SSL失败"
            r4.setResponseBody(r1)     // Catch:{ all -> 0x01b8 }
            boolean r1 = r10.isNeedRetryIfHttpsFailed()     // Catch:{ all -> 0x01b8 }
            if (r1 == 0) goto L_0x0126
            java.lang.String r1 = r10.getUrl()     // Catch:{ all -> 0x01b8 }
            java.lang.String r6 = "https"
            boolean r1 = r1.startsWith(r6)     // Catch:{ all -> 0x01b8 }
            if (r1 == 0) goto L_0x0126
            java.lang.String r1 = "HttpUtils"
            java.lang.String r4 = "default ssl failed,will use http post once"
            p004cn.jiguang.p029e.C0501d.m903a(r1, r4)     // Catch:{ all -> 0x01b8 }
            java.lang.String r1 = r10.getUrl()     // Catch:{ all -> 0x01b8 }
            java.lang.String r1 = m1114a(r1)     // Catch:{ all -> 0x01b8 }
            r10.setUrl(r1)     // Catch:{ all -> 0x01b8 }
            cn.jiguang.net.HttpResponse r1 = httpPost(r9, r10)     // Catch:{ all -> 0x01b8 }
            p004cn.jiguang.p031g.C0526g.m1087a(r2)
            p004cn.jiguang.p031g.C0526g.m1087a(r3)
            if (r5 == 0) goto L_0x0004
            r5.disconnect()
            goto L_0x0004
        L_0x0208:
            r1 = move-exception
            r1 = r3
            r2 = r3
        L_0x020b:
            r5 = 3007(0xbbf, float:4.214E-42)
            r4.setResponseCode(r5)     // Catch:{ all -> 0x022a }
            java.lang.String r5 = "StackOverflowError"
            r4.setResponseBody(r5)     // Catch:{ all -> 0x022a }
            p004cn.jiguang.p031g.C0526g.m1087a(r1)
            p004cn.jiguang.p031g.C0526g.m1087a(r3)
            if (r2 == 0) goto L_0x00bb
            r2.disconnect()
            goto L_0x00bb
        L_0x0222:
            r1 = move-exception
            r2 = r3
            r5 = r3
            goto L_0x01b9
        L_0x0226:
            r1 = move-exception
            r5 = r2
            r2 = r3
            goto L_0x01b9
        L_0x022a:
            r4 = move-exception
            r5 = r2
            r2 = r1
            r1 = r4
            goto L_0x01b9
        L_0x022f:
            r1 = move-exception
            r1 = r3
            goto L_0x020b
        L_0x0232:
            r5 = move-exception
            goto L_0x020b
        L_0x0234:
            r1 = move-exception
            r2 = r3
            r5 = r3
            goto L_0x0158
        L_0x0239:
            r5 = move-exception
            r8 = r5
            r5 = r2
            r2 = r1
            r1 = r8
            goto L_0x0158
        L_0x0240:
            r1 = move-exception
            r2 = r3
            r5 = r3
            goto L_0x010c
        L_0x0245:
            r5 = move-exception
            r8 = r5
            r5 = r2
            r2 = r1
            r1 = r8
            goto L_0x010c
        L_0x024c:
            r1 = move-exception
            r1 = r3
            r2 = r3
            goto L_0x00e5
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.net.HttpUtils.m1113a(android.content.Context, cn.jiguang.net.HttpRequest, boolean):cn.jiguang.net.HttpResponse");
    }

    /* renamed from: a */
    private static String m1114a(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            return str.startsWith(Constants.SCHEME) ? new StringBuilder(HttpHost.DEFAULT_SCHEME_NAME).append(str.substring(5)).toString() : str;
        } catch (Exception e) {
            C0501d.m903a("HttpUtils", "fiflerHttpsToHttp error:" + e.getMessage());
            return str;
        }
    }

    public static String appendParaToUrl(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        if (!str.contains(URL_AND_PARA_SEPARATOR)) {
            sb.append(URL_AND_PARA_SEPARATOR);
        } else {
            sb.append(PARAMETERS_SEPARATOR);
        }
        return sb.append(str2).append(EQUAL_SIGN).append(str3).toString();
    }

    public static HttpURLConnection getHttpURLConnectionWithProxy(Context context, String str) {
        URL url = new URL(str);
        if (context != null) {
            try {
                if (context.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", context.getPackageName()) == 0) {
                    NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                    if (!(activeNetworkInfo == null || activeNetworkInfo.getType() == 1)) {
                        String extraInfo = activeNetworkInfo.getExtraInfo();
                        if (extraInfo != null && (extraInfo.equals("cmwap") || extraInfo.equals("3gwap") || extraInfo.equals("uniwap"))) {
                            return (HttpURLConnection) url.openConnection(new Proxy(Type.HTTP, new InetSocketAddress(HTTP_DEFUALT_PROXY, 80)));
                        }
                    }
                }
            } catch (Throwable th) {
            }
        }
        return (HttpURLConnection) url.openConnection();
    }

    public static String getUrlWithParas(String str, Map<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        StringBuilder sb = new StringBuilder(str);
        String joinParas = joinParas(map);
        if (!TextUtils.isEmpty(joinParas)) {
            sb.append(URL_AND_PARA_SEPARATOR).append(joinParas);
        }
        return sb.toString();
    }

    public static String getUrlWithValueEncodeParas(String str, Map<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        StringBuilder sb = new StringBuilder(str);
        String joinParasWithEncodedValue = joinParasWithEncodedValue(map);
        if (!TextUtils.isEmpty(joinParasWithEncodedValue)) {
            sb.append(URL_AND_PARA_SEPARATOR).append(joinParasWithEncodedValue);
        }
        return sb.toString();
    }

    public static HttpResponse httpGet(Context context, HttpRequest httpRequest) {
        return m1113a(context, httpRequest, false);
    }

    public static HttpResponse httpGet(Context context, String str) {
        return httpGet(context, new HttpRequest(str));
    }

    public static void httpGet(Context context, HttpRequest httpRequest, HttpListener httpListener) {
        new C0535a(context, httpListener).execute(new HttpRequest[]{httpRequest});
    }

    public static void httpGet(Context context, String str, HttpListener httpListener) {
        new C0536b(httpListener, context).execute(new String[]{str});
    }

    public static String httpGetString(Context context, HttpRequest httpRequest) {
        HttpResponse httpGet = httpGet(context, httpRequest);
        if (httpGet == null) {
            return null;
        }
        return httpGet.getResponseBody();
    }

    public static String httpGetString(Context context, String str) {
        HttpResponse httpGet = httpGet(context, new HttpRequest(str));
        if (httpGet == null) {
            return null;
        }
        return httpGet.getResponseBody();
    }

    public static HttpResponse httpPost(Context context, HttpRequest httpRequest) {
        return m1113a(context, httpRequest, true);
    }

    public static HttpResponse httpPost(Context context, String str) {
        return httpPost(context, new HttpRequest(str));
    }

    public static String httpPostString(Context context, String str) {
        HttpResponse httpPost = httpPost(context, new HttpRequest(str));
        if (httpPost == null) {
            return null;
        }
        return httpPost.getResponseBody();
    }

    public static String httpPostString(Context context, String str, Map<String, String> map) {
        HttpResponse httpPost = httpPost(context, new HttpRequest(str, map));
        if (httpPost == null) {
            return null;
        }
        return httpPost.getResponseBody();
    }

    public static String joinParas(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            sb.append((String) entry.getKey()).append(EQUAL_SIGN).append((String) entry.getValue());
            if (it.hasNext()) {
                sb.append(PARAMETERS_SEPARATOR);
            }
        }
        return sb.toString();
    }

    public static String joinParasWithEncodedValue(Map<String, String> map) {
        StringBuilder sb = new StringBuilder("");
        if (map != null && map.size() > 0) {
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                try {
                    Entry entry = (Entry) it.next();
                    sb.append((String) entry.getKey()).append(EQUAL_SIGN).append(URLEncoder.encode((String) entry.getValue(), "UTF-8"));
                    if (it.hasNext()) {
                        sb.append(PARAMETERS_SEPARATOR);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    public static long parseGmtTime(String str) {
        try {
            return f636a.parse(str).getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static byte[] readInputStream(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    public static void setURLConnection(Map<String, String> map, HttpURLConnection httpURLConnection) {
        if (map != null && map.size() != 0 && httpURLConnection != null) {
            for (Entry entry : map.entrySet()) {
                if (!TextUtils.isEmpty((CharSequence) entry.getKey())) {
                    httpURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
                }
            }
        }
    }
}
