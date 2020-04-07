package p004cn.jiguang.p005a.p006a.p009c;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.p052io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import p004cn.jiguang.net.HttpUtils;
import p004cn.jiguang.p005a.p011b.C0372a;
import p004cn.jiguang.p005a.p012c.C0377c;
import p004cn.jiguang.p015d.p016a.C0386a;
import p004cn.jiguang.p015d.p021d.C0460q;
import p004cn.jiguang.p015d.p026g.C0482c;
import p004cn.jiguang.p015d.p026g.p027a.C0480a;
import p004cn.jiguang.p031g.C0506a;
import p004cn.jiguang.p031g.C0525f;
import p004cn.jiguang.p031g.C0526g;
import p004cn.jiguang.p031g.C0530k;

/* renamed from: cn.jiguang.a.a.c.i */
public final class C0362i extends Thread {

    /* renamed from: a */
    private static ExecutorService f126a = Executors.newSingleThreadExecutor();

    /* renamed from: b */
    private static final Object f127b = new Object();

    /* renamed from: c */
    private static AtomicInteger f128c = new AtomicInteger();

    /* renamed from: j */
    private static CookieManager f129j;

    /* renamed from: d */
    private String f130d;

    /* renamed from: e */
    private String f131e;

    /* renamed from: f */
    private String f132f;

    /* renamed from: g */
    private Context f133g;

    /* renamed from: h */
    private int f134h = 0;

    /* renamed from: i */
    private String f135i;

    private C0362i() {
    }

    private C0362i(Context context) {
        String d = C0506a.m960d(context, this.f130d);
        String c = C0506a.m955c(context, this.f131e);
        String e = C0506a.m964e(context, this.f132f);
        CookieManager cookieManager = new CookieManager();
        f129j = cookieManager;
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(f129j);
        this.f133g = context;
        this.f130d = d;
        this.f131e = c;
        this.f132f = e;
    }

    /* renamed from: a */
    public static int m125a(String str) {
        if (C0530k.m1099a(str)) {
            return -1;
        }
        if (str.equalsIgnoreCase("ChinaTelecom")) {
            return 2;
        }
        if (str.equalsIgnoreCase("ChinaMobile")) {
            return 0;
        }
        return str.equalsIgnoreCase("ChinaUnicom") ? 1 : -1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:103:?, code lost:
        r14.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0245, code lost:
        r15.disconnect();
        r4 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x024b, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x024c, code lost:
        r3.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0250, code lost:
        r2 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0251, code lost:
        r14 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:?, code lost:
        r14.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x0259, code lost:
        r15.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x025d, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x025e, code lost:
        r3.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x02b1, code lost:
        r2 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x02b9, code lost:
        r2 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x02bc, code lost:
        r2 = r12;
        r14 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x02c7, code lost:
        r2 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x02cb, code lost:
        r2 = r12;
        r14 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x02e0, code lost:
        r2 = r12;
        r3 = r13;
        r4 = r14;
        r5 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x02e7, code lost:
        r2 = r12;
        r4 = r3;
        r5 = r15;
        r3 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:?, code lost:
        r14.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x022f, code lost:
        r15.disconnect();
        r4 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0235, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0236, code lost:
        r3.printStackTrace();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0240 A[SYNTHETIC, Splitter:B:102:0x0240] */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0245  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0250 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:63:0x01d9] */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x0254 A[SYNTHETIC, Splitter:B:111:0x0254] */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x0259  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x02b1 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:11:0x0037] */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x014f A[ADDED_TO_REGION, EDGE_INSN: B:178:0x014f->B:34:0x014f ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0145  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x022a A[SYNTHETIC, Splitter:B:93:0x022a] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x022f  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private p004cn.jiguang.p005a.p006a.p009c.C0363j m126a(android.content.Context r23, java.lang.String r24, int r25, long r26, boolean r28, java.io.File r29, java.lang.String r30) {
        /*
            r22 = this;
            r2 = 200(0xc8, double:9.9E-322)
            int r2 = (r26 > r2 ? 1 : (r26 == r2 ? 0 : -1))
            if (r2 < 0) goto L_0x000d
            r2 = 60000(0xea60, double:2.9644E-319)
            int r2 = (r26 > r2 ? 1 : (r26 == r2 ? 0 : -1))
            if (r2 <= 0) goto L_0x000f
        L_0x000d:
            r26 = 2000(0x7d0, double:9.88E-321)
        L_0x000f:
            java.util.UUID r2 = java.util.UUID.randomUUID()
            java.lang.String r17 = r2.toString()
            java.lang.String r18 = "--"
            java.lang.String r19 = "\r\n"
            java.lang.String r20 = "multipart/form-data"
            r4 = 0
            r14 = 0
            r13 = 0
            r11 = 0
            r3 = 0
            r2 = -1
            java.net.CookieManager r5 = f129j
            if (r5 != 0) goto L_0x002e
            java.net.CookieManager r5 = new java.net.CookieManager
            r5.<init>()
            f129j = r5
        L_0x002e:
            r16 = r3
            r3 = r4
        L_0x0031:
            java.net.HttpURLConnection r15 = p004cn.jiguang.net.HttpUtils.getHttpURLConnectionWithProxy(r23, r24)     // Catch:{ SSLPeerUnverifiedException -> 0x02d9, Exception -> 0x02bf, AssertionError -> 0x02b3, all -> 0x02ae }
            r3 = 30000(0x7530, float:4.2039E-41)
            r15.setConnectTimeout(r3)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r3 = 30000(0x7530, float:4.2039E-41)
            r15.setReadTimeout(r3)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r3 = 1
            r15.setDoInput(r3)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r3 = 1
            r15.setDoOutput(r3)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r3 = 0
            r15.setUseCaches(r3)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            java.lang.String r3 = "POST"
            r15.setRequestMethod(r3)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            java.lang.String r3 = "Charset"
            java.lang.String r4 = "UTF-8"
            r15.setRequestProperty(r3, r4)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            java.lang.String r3 = "User-Agent"
            java.lang.String r4 = "Mozilla/5.0 (Linux; Android 5.1.1; Nexus 6 Build/LYZ28E) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.23 Mobile Safari/537.36"
            r15.addRequestProperty(r3, r4)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            java.net.CookieManager r3 = f129j     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            java.net.CookieStore r3 = r3.getCookieStore()     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            java.util.List r3 = r3.getCookies()     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            int r3 = r3.size()     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            if (r3 <= 0) goto L_0x0083
            java.lang.String r3 = "Cookie"
            java.lang.String r4 = ";"
            java.net.CookieManager r5 = f129j     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            java.net.CookieStore r5 = r5.getCookieStore()     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            java.util.List r5 = r5.getCookies()     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            java.lang.String r4 = android.text.TextUtils.join(r4, r5)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r15.setRequestProperty(r3, r4)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
        L_0x0083:
            r0 = r28
            r15.setInstanceFollowRedirects(r0)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            if (r29 == 0) goto L_0x0189
            java.lang.String r3 = "Content-Type"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r4.<init>()     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r0 = r20
            java.lang.StringBuilder r4 = r4.append(r0)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            java.lang.String r5 = ";boundary="
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r0 = r17
            java.lang.StringBuilder r4 = r4.append(r0)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            java.lang.String r4 = r4.toString()     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r15.setRequestProperty(r3, r4)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            java.io.OutputStream r3 = r15.getOutputStream()     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            java.io.DataOutputStream r4 = new java.io.DataOutputStream     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r4.<init>(r3)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            java.lang.StringBuffer r3 = new java.lang.StringBuffer     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r3.<init>()     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r0 = r18
            r3.append(r0)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r0 = r17
            r3.append(r0)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r0 = r19
            r3.append(r0)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            java.lang.String r6 = "Content-Disposition: form-data; name=\""
            r5.<init>(r6)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r0 = r30
            java.lang.StringBuilder r5 = r5.append(r0)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            java.lang.String r6 = "\"; filename=\""
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            java.lang.String r6 = r29.getName()     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            java.lang.String r6 = "\""
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r0 = r19
            java.lang.StringBuilder r5 = r5.append(r0)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            java.lang.String r5 = r5.toString()     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r3.append(r5)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            java.lang.String r6 = "Content-Type: application/octet-stream; charset=UTF-8"
            r5.<init>(r6)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r0 = r19
            java.lang.StringBuilder r5 = r5.append(r0)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            java.lang.String r5 = r5.toString()     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r3.append(r5)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r0 = r19
            r3.append(r0)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            java.lang.String r3 = r3.toString()     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            byte[] r3 = r3.getBytes()     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r4.write(r3)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r0 = r29
            r3.<init>(r0)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r5 = 1024(0x400, float:1.435E-42)
            byte[] r5 = new byte[r5]     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
        L_0x0124:
            int r6 = r3.read(r5)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r7 = -1
            if (r6 == r7) goto L_0x0151
            r7 = 0
            r4.write(r5, r7, r6)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            goto L_0x0124
        L_0x0130:
            r3 = move-exception
            r3 = r13
            r4 = r14
            r5 = r15
        L_0x0134:
            if (r4 == 0) goto L_0x0139
            r4.close()     // Catch:{ IOException -> 0x021e }
        L_0x0139:
            if (r5 == 0) goto L_0x02f7
            r5.disconnect()
            r13 = r3
            r14 = r4
            r4 = r5
        L_0x0141:
            r3 = 404(0x194, float:5.66E-43)
            if (r2 == r3) goto L_0x014f
            r3 = 200(0xc8, float:2.8E-43)
            if (r2 == r3) goto L_0x0262
            boolean r3 = p004cn.jiguang.p031g.C0506a.m957c(r23)
            if (r3 != 0) goto L_0x0262
        L_0x014f:
            r2 = 0
        L_0x0150:
            return r2
        L_0x0151:
            r3.close()     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            byte[] r3 = r19.getBytes()     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r4.write(r3)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r3.<init>()     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r0 = r18
            java.lang.StringBuilder r3 = r3.append(r0)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r0 = r17
            java.lang.StringBuilder r3 = r3.append(r0)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r0 = r18
            java.lang.StringBuilder r3 = r3.append(r0)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r0 = r19
            java.lang.StringBuilder r3 = r3.append(r0)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            java.lang.String r3 = r3.toString()     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            byte[] r3 = r3.getBytes()     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r4.write(r3)     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r4.flush()     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            r4.close()     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
        L_0x0189:
            int r12 = r15.getResponseCode()     // Catch:{ SSLPeerUnverifiedException -> 0x0130, Exception -> 0x02c3, AssertionError -> 0x02b6, all -> 0x02b1 }
            java.util.Map r2 = r15.getHeaderFields()     // Catch:{ SSLPeerUnverifiedException -> 0x02df, Exception -> 0x02c6, AssertionError -> 0x02b8, all -> 0x02b1 }
            m131a(r2)     // Catch:{ SSLPeerUnverifiedException -> 0x02df, Exception -> 0x02c6, AssertionError -> 0x02b8, all -> 0x02b1 }
            r2 = 302(0x12e, float:4.23E-43)
            if (r12 != r2) goto L_0x01d1
            java.lang.String r2 = "Location"
            java.lang.String r4 = r15.getHeaderField(r2)     // Catch:{ SSLPeerUnverifiedException -> 0x02df, Exception -> 0x02c6, AssertionError -> 0x02b8, all -> 0x02b1 }
            if (r25 < 0) goto L_0x01c0
            int r5 = r25 + -1
            r6 = 0
            r9 = 0
            r10 = 0
            r2 = r22
            r3 = r23
            r8 = r28
            cn.jiguang.a.a.c.j r2 = r2.m126a(r3, r4, r5, r6, r8, r9, r10)     // Catch:{ SSLPeerUnverifiedException -> 0x02df, Exception -> 0x02c6, AssertionError -> 0x02b8, all -> 0x02b1 }
            if (r14 == 0) goto L_0x01b5
            r14.close()     // Catch:{ IOException -> 0x01bb }
        L_0x01b5:
            if (r15 == 0) goto L_0x0150
            r15.disconnect()
            goto L_0x0150
        L_0x01bb:
            r3 = move-exception
            r3.printStackTrace()
            goto L_0x01b5
        L_0x01c0:
            if (r14 == 0) goto L_0x01c5
            r14.close()     // Catch:{ IOException -> 0x01cc }
        L_0x01c5:
            if (r15 == 0) goto L_0x01ca
            r15.disconnect()
        L_0x01ca:
            r2 = 0
            goto L_0x0150
        L_0x01cc:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x01c5
        L_0x01d1:
            r2 = 200(0xc8, float:2.8E-43)
            if (r12 != r2) goto L_0x020b
            java.io.InputStream r3 = r15.getInputStream()     // Catch:{ SSLPeerUnverifiedException -> 0x02df, Exception -> 0x02c6, AssertionError -> 0x02b8, all -> 0x02b1 }
            java.util.Map r4 = r15.getHeaderFields()     // Catch:{ SSLPeerUnverifiedException -> 0x02e6, Exception -> 0x02ca, AssertionError -> 0x02bb, all -> 0x0250 }
            java.lang.String r2 = new java.lang.String     // Catch:{ SSLPeerUnverifiedException -> 0x02cf, Exception -> 0x0224, AssertionError -> 0x023a, all -> 0x0250 }
            byte[] r5 = p004cn.jiguang.p031g.C0529j.m1097a(r3)     // Catch:{ SSLPeerUnverifiedException -> 0x02cf, Exception -> 0x0224, AssertionError -> 0x023a, all -> 0x0250 }
            java.lang.String r6 = "UTF-8"
            r2.<init>(r5, r6)     // Catch:{ SSLPeerUnverifiedException -> 0x02cf, Exception -> 0x0224, AssertionError -> 0x023a, all -> 0x0250 }
            if (r3 == 0) goto L_0x01ed
            r3.close()     // Catch:{ IOException -> 0x0206 }
        L_0x01ed:
            if (r15 == 0) goto L_0x01f2
            r15.disconnect()
        L_0x01f2:
            r3 = 200(0xc8, float:2.8E-43)
            if (r12 < r3) goto L_0x0281
            r3 = 300(0x12c, float:4.2E-43)
            if (r12 >= r3) goto L_0x0281
            if (r2 != 0) goto L_0x01fe
            java.lang.String r2 = "<<error>>"
        L_0x01fe:
            cn.jiguang.a.a.c.j r3 = new cn.jiguang.a.a.c.j
            r3.<init>(r12, r4, r2)
            r2 = r3
            goto L_0x0150
        L_0x0206:
            r3 = move-exception
            r3.printStackTrace()
            goto L_0x01ed
        L_0x020b:
            if (r14 == 0) goto L_0x0210
            r14.close()     // Catch:{ IOException -> 0x0219 }
        L_0x0210:
            if (r15 == 0) goto L_0x02f0
            r15.disconnect()
            r2 = r12
            r4 = r15
            goto L_0x0141
        L_0x0219:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0210
        L_0x021e:
            r6 = move-exception
            r6.printStackTrace()
            goto L_0x0139
        L_0x0224:
            r2 = move-exception
            r2 = r12
            r13 = r4
            r14 = r3
        L_0x0228:
            if (r14 == 0) goto L_0x022d
            r14.close()     // Catch:{ IOException -> 0x0235 }
        L_0x022d:
            if (r15 == 0) goto L_0x02f4
            r15.disconnect()
            r4 = r15
            goto L_0x0141
        L_0x0235:
            r3 = move-exception
            r3.printStackTrace()
            goto L_0x022d
        L_0x023a:
            r2 = move-exception
            r2 = r12
            r13 = r4
            r14 = r3
        L_0x023e:
            if (r14 == 0) goto L_0x0243
            r14.close()     // Catch:{ IOException -> 0x024b }
        L_0x0243:
            if (r15 == 0) goto L_0x02f4
            r15.disconnect()
            r4 = r15
            goto L_0x0141
        L_0x024b:
            r3 = move-exception
            r3.printStackTrace()
            goto L_0x0243
        L_0x0250:
            r2 = move-exception
            r14 = r3
        L_0x0252:
            if (r14 == 0) goto L_0x0257
            r14.close()     // Catch:{ IOException -> 0x025d }
        L_0x0257:
            if (r15 == 0) goto L_0x025c
            r15.disconnect()
        L_0x025c:
            throw r2
        L_0x025d:
            r3 = move-exception
            r3.printStackTrace()
            goto L_0x0257
        L_0x0262:
            r3 = 3
            r0 = r16
            if (r0 < r3) goto L_0x0271
            cn.jiguang.a.a.c.j r2 = new cn.jiguang.a.a.c.j
            r3 = -1
            java.lang.String r4 = "<<failed_with_retries>>"
            r2.<init>(r3, r13, r4)
            goto L_0x0150
        L_0x0271:
            int r3 = r16 + 1
            java.lang.Thread.sleep(r26)     // Catch:{ InterruptedException -> 0x027b }
            r16 = r3
            r3 = r4
            goto L_0x0031
        L_0x027b:
            r5 = move-exception
            r16 = r3
            r3 = r4
            goto L_0x0031
        L_0x0281:
            r2 = 400(0x190, float:5.6E-43)
            if (r12 < r2) goto L_0x02a9
            r2 = 500(0x1f4, float:7.0E-43)
            if (r12 >= r2) goto L_0x02a9
            r2 = 400(0x190, float:5.6E-43)
            if (r2 != r12) goto L_0x0291
            java.lang.String r2 = "server fail"
            goto L_0x01fe
        L_0x0291:
            r2 = 401(0x191, float:5.62E-43)
            if (r2 == r12) goto L_0x02a5
            r2 = 404(0x194, float:5.66E-43)
            if (r2 == r12) goto L_0x02a5
            r2 = 406(0x196, float:5.69E-43)
            if (r2 == r12) goto L_0x02a5
            r2 = 408(0x198, float:5.72E-43)
            if (r2 == r12) goto L_0x02a5
            r2 = 409(0x199, float:5.73E-43)
            if (r2 != r12) goto L_0x02ed
        L_0x02a5:
            java.lang.String r2 = "<<error>>"
            goto L_0x01fe
        L_0x02a9:
            r2 = 500(0x1f4, float:7.0E-43)
            if (r12 < r2) goto L_0x02a5
            goto L_0x02a5
        L_0x02ae:
            r2 = move-exception
            r15 = r3
            goto L_0x0252
        L_0x02b1:
            r2 = move-exception
            goto L_0x0252
        L_0x02b3:
            r4 = move-exception
            r15 = r3
            goto L_0x023e
        L_0x02b6:
            r3 = move-exception
            goto L_0x023e
        L_0x02b8:
            r2 = move-exception
            r2 = r12
            goto L_0x023e
        L_0x02bb:
            r2 = move-exception
            r2 = r12
            r14 = r3
            goto L_0x023e
        L_0x02bf:
            r4 = move-exception
            r15 = r3
            goto L_0x0228
        L_0x02c3:
            r3 = move-exception
            goto L_0x0228
        L_0x02c6:
            r2 = move-exception
            r2 = r12
            goto L_0x0228
        L_0x02ca:
            r2 = move-exception
            r2 = r12
            r14 = r3
            goto L_0x0228
        L_0x02cf:
            r2 = move-exception
            r2 = r12
            r5 = r15
            r21 = r3
            r3 = r4
            r4 = r21
            goto L_0x0134
        L_0x02d9:
            r4 = move-exception
            r4 = r14
            r5 = r3
            r3 = r13
            goto L_0x0134
        L_0x02df:
            r2 = move-exception
            r2 = r12
            r3 = r13
            r4 = r14
            r5 = r15
            goto L_0x0134
        L_0x02e6:
            r2 = move-exception
            r2 = r12
            r4 = r3
            r5 = r15
            r3 = r13
            goto L_0x0134
        L_0x02ed:
            r2 = r11
            goto L_0x01fe
        L_0x02f0:
            r2 = r12
            r4 = r15
            goto L_0x0141
        L_0x02f4:
            r4 = r15
            goto L_0x0141
        L_0x02f7:
            r13 = r3
            r14 = r4
            r4 = r5
            goto L_0x0141
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p005a.p006a.p009c.C0362i.m126a(android.content.Context, java.lang.String, int, long, boolean, java.io.File, java.lang.String):cn.jiguang.a.a.c.j");
    }

    /* renamed from: a */
    private String m127a(int i) {
        this.f135i = (i < 0 || i >= 3) ? "http://182.92.20.189:9099/" : C0386a.m256b(this.f133g, "number_url" + i, "http://182.92.20.189:9099/");
        return this.f135i;
    }

    /* renamed from: a */
    private String m128a(String str, C0363j jVar) {
        if (m132a(this.f133g, jVar)) {
            return m137d(str);
        }
        return null;
    }

    /* renamed from: a */
    private String m129a(TreeMap<String, String> treeMap) {
        if (treeMap.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Entry value : treeMap.entrySet()) {
            sb.append(value.getValue());
        }
        return C0506a.m933a(sb.toString() + C0372a.m158b(this.f133g)).toUpperCase();
    }

    /* renamed from: a */
    public static void m130a(Context context) {
        if (f128c.get() < 2) {
            f126a.execute(new C0362i(context));
        }
    }

    /* renamed from: a */
    private static void m131a(Map<String, List<String>> map) {
        List<String> list = (List) map.get("Set-Cookie");
        if (list != null) {
            for (String parse : list) {
                f129j.getCookieStore().add(null, (HttpCookie) HttpCookie.parse(parse).get(0));
            }
        }
    }

    /* renamed from: a */
    private static boolean m132a(Context context, C0363j jVar) {
        FileOutputStream fileOutputStream;
        boolean z;
        if (context == null || jVar == null) {
            return false;
        }
        FileOutputStream fileOutputStream2 = null;
        String str = "resp.raw";
        StringBuilder sb = new StringBuilder("");
        if (jVar.f138c != null && jVar.f138c.size() > 0) {
            for (Entry entry : jVar.f138c.entrySet()) {
                if (entry.getKey() != null) {
                    sb.append((String) entry.getKey()).append(": ");
                }
                Iterator it = ((List) entry.getValue()).iterator();
                if (it.hasNext()) {
                    sb.append((String) it.next());
                    while (it.hasNext()) {
                        sb.append(", ").append((String) it.next());
                    }
                }
                sb.append(IOUtils.LINE_SEPARATOR_UNIX);
            }
        }
        sb.append("\r\n\r\n");
        if (!TextUtils.isEmpty(jVar.f137b)) {
            sb.append(jVar.f137b);
        }
        try {
            context.deleteFile(str);
            fileOutputStream = context.openFileOutput(str, 0);
            try {
                fileOutputStream.write(sb.toString().getBytes("UTF-8"));
                C0526g.m1087a((Closeable) fileOutputStream);
                String str2 = "resp.zip";
                try {
                    context.deleteFile(str2);
                    String str3 = context.getFilesDir() + File.separator;
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new File(str3 + "resp.raw"));
                    C0525f.m1086a(arrayList, new File(str3 + str2));
                    z = true;
                } catch (IOException e) {
                    e.printStackTrace();
                    z = false;
                }
                return z;
            } catch (FileNotFoundException e2) {
                C0526g.m1087a((Closeable) fileOutputStream);
                return false;
            } catch (UnsupportedEncodingException e3) {
                C0526g.m1087a((Closeable) fileOutputStream);
                return false;
            } catch (IOException e4) {
                C0526g.m1087a((Closeable) fileOutputStream);
                return false;
            } catch (NullPointerException e5) {
                C0526g.m1087a((Closeable) fileOutputStream);
                return false;
            } catch (Throwable th) {
                fileOutputStream2 = fileOutputStream;
                th = th;
                C0526g.m1087a((Closeable) fileOutputStream2);
                throw th;
            }
        } catch (FileNotFoundException e6) {
            fileOutputStream = null;
        } catch (UnsupportedEncodingException e7) {
            fileOutputStream = null;
            C0526g.m1087a((Closeable) fileOutputStream);
            return false;
        } catch (IOException e8) {
            fileOutputStream = null;
            C0526g.m1087a((Closeable) fileOutputStream);
            return false;
        } catch (NullPointerException e9) {
            fileOutputStream = null;
            C0526g.m1087a((Closeable) fileOutputStream);
            return false;
        } catch (Throwable th2) {
            th = th2;
            C0526g.m1087a((Closeable) fileOutputStream2);
            throw th;
        }
    }

    /* renamed from: a */
    private boolean m133a(String str, String str2, String str3) {
        String str4;
        TreeMap treeMap = new TreeMap();
        if (!C0530k.m1099a(str)) {
            treeMap.put("imei", str);
        }
        if (!C0530k.m1099a(str2)) {
            treeMap.put("iccid", str2);
        }
        if (!C0530k.m1099a(str3)) {
            treeMap.put("imsi", str3);
        }
        treeMap.put("version", C0386a.m256b(this.f133g, "number_version", "1.3.0"));
        treeMap.put("app_id", C0386a.m256b(this.f133g, "number_appid", "7"));
        treeMap.put("req_time", C0482c.m789b());
        treeMap.put("sign", m129a(treeMap));
        String str5 = "";
        for (Entry entry : treeMap.entrySet()) {
            try {
                str5 = str5 + HttpUtils.PARAMETERS_SEPARATOR + entry.getKey() + HttpUtils.EQUAL_SIGN + URLEncoder.encode((String) entry.getValue(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
            }
        }
        try {
            C0363j a = m126a(this.f133g, this.f135i + "statistic/query?" + str5, 10, 30000, false, null, null);
            if (a.f136a != 200) {
                return false;
            }
            JSONObject c = m136c(a.f137b);
            if (c != null) {
                if (c.optInt("code", -1) != 200) {
                    return false;
                }
                str4 = m135b(c.optString("num"));
            } else if (a.f138c != null || !TextUtils.isEmpty(a.f137b)) {
                synchronized (f127b) {
                    this.f134h = 0;
                    try {
                        str4 = m128a(str5, a);
                    } catch (Throwable th) {
                        str4 = null;
                    }
                    this.f133g.deleteFile("resp.raw");
                    this.f133g.deleteFile("resp.zip");
                }
            } else {
                str4 = null;
            }
            if (TextUtils.isEmpty(str4)) {
                return false;
            }
            m138e(str4);
            return true;
        } catch (Throwable th2) {
            return false;
        }
    }

    /* renamed from: b */
    public static String m134b(Context context) {
        if (context == null) {
            return "";
        }
        String d = C0506a.m960d(context, "");
        String c = C0506a.m955c(context, "");
        return C0506a.m933a(d + c + C0506a.m964e(context, ""));
    }

    /* renamed from: b */
    private String m135b(String str) {
        String f = m139f(str);
        if (!C0530k.m1099a(f) && Patterns.PHONE.matcher(f).matches()) {
            return f;
        }
        return null;
    }

    /* renamed from: c */
    private static JSONObject m136c(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                return new JSONObject(str);
            }
        } catch (Exception e) {
        }
        return null;
    }

    /* renamed from: d */
    private String m137d(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            C0363j a = m126a(this.f133g, this.f135i + "statistic/query?" + str, 10, 30000, false, new File(this.f133g.getFilesDir() + File.separator + "resp.zip"), "resp_data");
            if (a.f136a != 200) {
                return null;
            }
            JSONObject c = m136c(a.f137b);
            if (c == null) {
                if (a.f138c != null || !TextUtils.isEmpty(a.f137b)) {
                    if (this.f134h > 4) {
                        return null;
                    }
                    this.f134h++;
                    try {
                        str2 = m128a(str, a);
                    } catch (Throwable th) {
                    }
                }
                str2 = null;
            } else if (c.optInt("code", -1) != 200) {
                return null;
            } else {
                str2 = m135b(c.optString("num"));
            }
            return str2;
        } catch (Throwable th2) {
            return null;
        }
    }

    /* renamed from: e */
    private void m138e(String str) {
        String str2;
        Context context = this.f133g;
        String b = m134b(context);
        if (C0530k.m1099a(b)) {
            b = "number_num";
        }
        C0386a.m247a(context, b, str);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("num", str);
            if (!C0530k.m1099a(this.f130d)) {
                jSONObject.put("imei", this.f130d);
            }
            if (!C0530k.m1099a(this.f132f)) {
                jSONObject.put("imsi", this.f132f);
            }
            if (!C0530k.m1099a(this.f131e)) {
                jSONObject.put("iccid", this.f131e);
            }
            try {
                str2 = C0480a.m776a(jSONObject.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!C0530k.m1099a(str2)) {
                JSONObject jSONObject2 = new JSONObject();
                C0460q.m707a(this.f133g, jSONObject2, "nb");
                jSONObject2.put("content", str2);
                C0377c.m183a(this.f133g, jSONObject2);
                C0372a.m161c(this.f133g, false);
            }
        } catch (JSONException e2) {
        }
    }

    /* renamed from: f */
    private String m139f(String str) {
        try {
            return C0480a.m777a(str, C0372a.m158b(this.f133g).substring(0, 16));
        } catch (Exception e) {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0089 A[Catch:{ Exception -> 0x0107 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00da A[Catch:{ Exception -> 0x0107 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r6 = this;
            r2 = -1
            r1 = 0
            java.util.concurrent.atomic.AtomicInteger r0 = f128c
            r0.incrementAndGet()
            java.lang.String r0 = r6.f130d     // Catch:{ Exception -> 0x0107 }
            boolean r0 = p004cn.jiguang.p031g.C0530k.m1099a(r0)     // Catch:{ Exception -> 0x0107 }
            if (r0 == 0) goto L_0x0020
            java.lang.String r0 = r6.f131e     // Catch:{ Exception -> 0x0107 }
            boolean r0 = p004cn.jiguang.p031g.C0530k.m1099a(r0)     // Catch:{ Exception -> 0x0107 }
            if (r0 == 0) goto L_0x0020
            java.lang.String r0 = r6.f132f     // Catch:{ Exception -> 0x0107 }
            boolean r0 = p004cn.jiguang.p031g.C0530k.m1099a(r0)     // Catch:{ Exception -> 0x0107 }
            if (r0 == 0) goto L_0x0020
        L_0x001f:
            return
        L_0x0020:
            android.content.Context r3 = r6.f133g     // Catch:{ Exception -> 0x0107 }
            java.lang.String r0 = m134b(r3)     // Catch:{ Exception -> 0x0107 }
            boolean r4 = p004cn.jiguang.p031g.C0530k.m1099a(r0)     // Catch:{ Exception -> 0x0107 }
            if (r4 == 0) goto L_0x002e
            java.lang.String r0 = "number_num"
        L_0x002e:
            java.lang.String r4 = ""
            java.lang.String r3 = p004cn.jiguang.p015d.p016a.C0386a.m256b(r3, r0, r4)     // Catch:{ Exception -> 0x0107 }
            boolean r0 = p004cn.jiguang.p031g.C0530k.m1099a(r3)     // Catch:{ Exception -> 0x0107 }
            if (r0 != 0) goto L_0x004f
            android.content.Context r0 = r6.f133g     // Catch:{ Exception -> 0x0107 }
            java.lang.String r4 = "nb_upload"
            r5 = 0
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch:{ Exception -> 0x0107 }
            java.io.Serializable r0 = p004cn.jiguang.p015d.p016a.C0389d.m320b(r0, r4, r5)     // Catch:{ Exception -> 0x0107 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ Exception -> 0x0107 }
            boolean r0 = r0.booleanValue()     // Catch:{ Exception -> 0x0107 }
            if (r0 == 0) goto L_0x0103
        L_0x004f:
            android.content.Context r0 = r6.f133g     // Catch:{ Exception -> 0x0107 }
            java.lang.String r3 = "nb_lasttime"
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0107 }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ Exception -> 0x0107 }
            p004cn.jiguang.p015d.p016a.C0389d.m313a(r0, r3, r4)     // Catch:{ Exception -> 0x0107 }
            java.lang.String r0 = r6.f132f     // Catch:{ Exception -> 0x0107 }
            boolean r3 = p004cn.jiguang.p031g.C0530k.m1099a(r0)     // Catch:{ Exception -> 0x0107 }
            if (r3 != 0) goto L_0x00d8
            java.lang.String r3 = "46000"
            boolean r3 = r0.startsWith(r3)     // Catch:{ Exception -> 0x0107 }
            if (r3 != 0) goto L_0x0086
            java.lang.String r3 = "46002"
            boolean r3 = r0.startsWith(r3)     // Catch:{ Exception -> 0x0107 }
            if (r3 != 0) goto L_0x0086
            java.lang.String r3 = "46007"
            boolean r3 = r0.startsWith(r3)     // Catch:{ Exception -> 0x0107 }
            if (r3 != 0) goto L_0x0086
            java.lang.String r3 = "46008"
            boolean r3 = r0.startsWith(r3)     // Catch:{ Exception -> 0x0107 }
            if (r3 == 0) goto L_0x00a4
        L_0x0086:
            r0 = r1
        L_0x0087:
            if (r0 == r2) goto L_0x00da
            r6.m127a(r0)     // Catch:{ Exception -> 0x0107 }
            java.lang.String r0 = r6.f135i     // Catch:{ Exception -> 0x0107 }
            boolean r0 = p004cn.jiguang.p031g.C0530k.m1099a(r0)     // Catch:{ Exception -> 0x0107 }
            if (r0 != 0) goto L_0x009d
            java.lang.String r0 = r6.f130d     // Catch:{ Exception -> 0x0107 }
            java.lang.String r1 = r6.f131e     // Catch:{ Exception -> 0x0107 }
            java.lang.String r2 = r6.f132f     // Catch:{ Exception -> 0x0107 }
            r6.m133a(r0, r1, r2)     // Catch:{ Exception -> 0x0107 }
        L_0x009d:
            java.util.concurrent.atomic.AtomicInteger r0 = f128c
            r0.decrementAndGet()
            goto L_0x001f
        L_0x00a4:
            java.lang.String r3 = "46001"
            boolean r3 = r0.startsWith(r3)     // Catch:{ Exception -> 0x0107 }
            if (r3 != 0) goto L_0x00bc
            java.lang.String r3 = "46006"
            boolean r3 = r0.startsWith(r3)     // Catch:{ Exception -> 0x0107 }
            if (r3 != 0) goto L_0x00bc
            java.lang.String r3 = "46009"
            boolean r3 = r0.startsWith(r3)     // Catch:{ Exception -> 0x0107 }
            if (r3 == 0) goto L_0x00be
        L_0x00bc:
            r0 = 1
            goto L_0x0087
        L_0x00be:
            java.lang.String r3 = "46003"
            boolean r3 = r0.startsWith(r3)     // Catch:{ Exception -> 0x0107 }
            if (r3 != 0) goto L_0x00d6
            java.lang.String r3 = "46005"
            boolean r3 = r0.startsWith(r3)     // Catch:{ Exception -> 0x0107 }
            if (r3 != 0) goto L_0x00d6
            java.lang.String r3 = "46011"
            boolean r0 = r0.startsWith(r3)     // Catch:{ Exception -> 0x0107 }
            if (r0 == 0) goto L_0x00d8
        L_0x00d6:
            r0 = 2
            goto L_0x0087
        L_0x00d8:
            r0 = r2
            goto L_0x0087
        L_0x00da:
            java.lang.String r0 = ""
        L_0x00dc:
            r2 = 3
            if (r1 >= r2) goto L_0x009d
            r6.m127a(r1)     // Catch:{ Exception -> 0x0107 }
            int r1 = r1 + 1
            java.lang.String r2 = r6.f135i     // Catch:{ Exception -> 0x0107 }
            boolean r2 = p004cn.jiguang.p031g.C0530k.m1099a(r2)     // Catch:{ Exception -> 0x0107 }
            if (r2 != 0) goto L_0x00dc
            java.lang.String r2 = r6.f135i     // Catch:{ Exception -> 0x0107 }
            boolean r2 = r0.equals(r2)     // Catch:{ Exception -> 0x0107 }
            if (r2 != 0) goto L_0x00dc
            java.lang.String r0 = r6.f135i     // Catch:{ Exception -> 0x0107 }
            java.lang.String r2 = r6.f130d     // Catch:{ Exception -> 0x0107 }
            java.lang.String r3 = r6.f131e     // Catch:{ Exception -> 0x0107 }
            java.lang.String r4 = r6.f132f     // Catch:{ Exception -> 0x0107 }
            boolean r2 = r6.m133a(r2, r3, r4)     // Catch:{ Exception -> 0x0107 }
            if (r2 == 0) goto L_0x00dc
            goto L_0x009d
        L_0x0103:
            r6.m138e(r3)     // Catch:{ Exception -> 0x0107 }
            goto L_0x009d
        L_0x0107:
            r0 = move-exception
            goto L_0x009d
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p005a.p006a.p009c.C0362i.run():void");
    }
}
