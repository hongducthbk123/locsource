package p004cn.jiguang.p015d.p021d;

import p004cn.jiguang.net.SSLTrustManager;

/* renamed from: cn.jiguang.d.d.i */
public final class C0452i {

    /* renamed from: a */
    public static SSLTrustManager f414a;

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00ee, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0125, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x014b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        return new p004cn.jiguang.p015d.p021d.C0462s(-2, "Catch AssertionError to avoid http close crash - " + r0.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        return new p004cn.jiguang.p015d.p021d.C0462s(-2, "Exception - " + r0.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        return new p004cn.jiguang.p015d.p021d.C0462s(-2, "Exception - " + r0.getMessage());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x009c A[Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ee A[ExcHandler: AssertionError (r0v5 'e' java.lang.AssertionError A[CUSTOM_DECLARE]), Splitter:B:5:0x000b] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0125 A[ExcHandler: Exception (r0v3 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:5:0x000b] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static p004cn.jiguang.p015d.p021d.C0462s m664a(java.lang.String r7, java.lang.String r8, android.content.Context r9, boolean r10, int r11) {
        /*
            r6 = -2
            java.lang.String r0 = "UTF-8"
            byte[] r0 = r8.getBytes(r0)     // Catch:{ UnsupportedEncodingException -> 0x00d2 }
            byte[] r0 = p004cn.jiguang.p015d.p026g.C0489j.m820a(r0)     // Catch:{ IOException -> 0x0109 }
            cn.jiguang.net.HttpRequest r1 = new cn.jiguang.net.HttpRequest     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            r1.<init>(r7)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            r2 = 30000(0x7530, float:4.2039E-41)
            r1.setConnectTimeout(r2)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            r2 = 30000(0x7530, float:4.2039E-41)
            r1.setReadTimeout(r2)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            r2 = 1
            r1.setDoOutPut(r2)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            r2 = 1
            r1.setDoInPut(r2)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            r2 = 0
            r1.setUseCaches(r2)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            r1.setBody(r0)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            java.lang.String r2 = "Content-Length"
            int r3 = r0.length     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            r1.setRequestProperty(r2, r3)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            r2 = 0
            r1.setHaveRspData(r2)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            r2 = 1
            r1.setNeedRetryIfHttpsFailed(r2)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            cn.jiguang.net.SSLTrustManager r2 = f414a     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            if (r2 != 0) goto L_0x0050
            java.lang.String r2 = p004cn.jiguang.service.Protocol.getCerTificate()     // Catch:{ Throwable -> 0x0190, AssertionError -> 0x00ee, Exception -> 0x0125 }
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Throwable -> 0x0190, AssertionError -> 0x00ee, Exception -> 0x0125 }
            if (r3 != 0) goto L_0x0050
            cn.jiguang.net.SSLTrustManager r3 = new cn.jiguang.net.SSLTrustManager     // Catch:{ Throwable -> 0x0190, AssertionError -> 0x00ee, Exception -> 0x0125 }
            r3.<init>(r2)     // Catch:{ Throwable -> 0x0190, AssertionError -> 0x00ee, Exception -> 0x0125 }
            f414a = r3     // Catch:{ Throwable -> 0x0190, AssertionError -> 0x00ee, Exception -> 0x0125 }
        L_0x0050:
            cn.jiguang.net.SSLTrustManager r2 = f414a     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            if (r2 == 0) goto L_0x0059
            cn.jiguang.net.SSLTrustManager r2 = f414a     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            r1.setSslTrustManager(r2)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
        L_0x0059:
            java.lang.String r2 = "Accept"
            java.lang.String r3 = "application/jason"
            r1.setRequestProperty(r2, r3)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            java.lang.String r2 = "Accept-Encoding"
            java.lang.String r3 = "gzip"
            r1.setRequestProperty(r2, r3)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            java.lang.String r2 = "Content-Encoding"
            java.lang.String r3 = "gzip"
            r1.setRequestProperty(r2, r3)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            java.lang.String r2 = "X-App-Key"
            java.lang.String r3 = p004cn.jiguang.p015d.p016a.C0389d.m338i(r9)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            r1.setRequestProperty(r2, r3)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            java.lang.String r0 = p004cn.jiguang.p031g.C0506a.m934a(r0)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            java.lang.String r0 = p004cn.jiguang.p015d.p021d.C0460q.m719c(r0)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            java.lang.String r2 = "Authorization"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            java.lang.String r4 = "Basic "
            r3.<init>(r4)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            java.lang.StringBuilder r0 = r3.append(r0)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            java.lang.String r0 = r0.toString()     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            r1.setRequestProperty(r2, r0)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            java.lang.String r0 = "Charset"
            java.lang.String r2 = "UTF-8"
            r1.setRequestProperty(r0, r2)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
        L_0x009a:
            if (r11 <= 0) goto L_0x0187
            int r11 = r11 + -1
            cn.jiguang.net.HttpResponse r2 = p004cn.jiguang.net.HttpUtils.httpPost(r9, r1)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            int r0 = r2.getResponseCode()     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            java.lang.String r3 = "HttpHelper"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            java.lang.String r5 = "status code:"
            r4.<init>(r5)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            java.lang.StringBuilder r4 = r4.append(r0)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            java.lang.String r5 = " retry left:"
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            java.lang.StringBuilder r4 = r4.append(r11)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            java.lang.String r4 = r4.toString()     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            p004cn.jiguang.p029e.C0501d.m903a(r3, r4)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            switch(r0) {
                case 200: goto L_0x0140;
                case 401: goto L_0x0167;
                case 404: goto L_0x016f;
                case 410: goto L_0x016f;
                case 429: goto L_0x016f;
                case 503: goto L_0x0177;
                case 3005: goto L_0x009a;
                default: goto L_0x00c7;
            }     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
        L_0x00c7:
            r1 = 500(0x1f4, float:7.0E-43)
            if (r0 < r1) goto L_0x017f
            cn.jiguang.d.d.s r0 = new cn.jiguang.d.d.s     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            r1 = -1
            r0.<init>(r1)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
        L_0x00d1:
            return r0
        L_0x00d2:
            r0 = move-exception
            r1 = r0
            cn.jiguang.d.d.s r0 = new cn.jiguang.d.d.s     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            r2 = -2
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            java.lang.String r4 = "Exception - "
            r3.<init>(r4)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            java.lang.String r1 = r1.getMessage()     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            java.lang.StringBuilder r1 = r3.append(r1)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            java.lang.String r1 = r1.toString()     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            r0.<init>(r2, r1)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            goto L_0x00d1
        L_0x00ee:
            r0 = move-exception
            r1 = r0
            cn.jiguang.d.d.s r0 = new cn.jiguang.d.d.s
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Catch AssertionError to avoid http close crash - "
            r2.<init>(r3)
            java.lang.String r1 = r1.getMessage()
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.String r1 = r1.toString()
            r0.<init>(r6, r1)
            goto L_0x00d1
        L_0x0109:
            r1 = move-exception
            cn.jiguang.d.d.s r0 = new cn.jiguang.d.d.s     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            r2 = -2
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            java.lang.String r4 = "zip err:"
            r3.<init>(r4)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            java.lang.String r1 = r1.getMessage()     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            java.lang.StringBuilder r1 = r3.append(r1)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            java.lang.String r1 = r1.toString()     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            r0.<init>(r2, r1)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            goto L_0x00d1
        L_0x0125:
            r0 = move-exception
            r1 = r0
            cn.jiguang.d.d.s r0 = new cn.jiguang.d.d.s
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Exception - "
            r2.<init>(r3)
            java.lang.String r1 = r1.getMessage()
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.String r1 = r1.toString()
            r0.<init>(r6, r1)
            goto L_0x00d1
        L_0x0140:
            cn.jiguang.d.d.s r0 = new cn.jiguang.d.d.s     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            r1 = 0
            java.lang.String r2 = r2.getResponseBody()     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            r0.<init>(r1, r2)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            goto L_0x00d1
        L_0x014b:
            r0 = move-exception
            r1 = r0
            cn.jiguang.d.d.s r0 = new cn.jiguang.d.d.s
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Exception - "
            r2.<init>(r3)
            java.lang.String r1 = r1.getMessage()
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.String r1 = r1.toString()
            r0.<init>(r6, r1)
            goto L_0x00d1
        L_0x0167:
            cn.jiguang.d.d.s r0 = new cn.jiguang.d.d.s     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            r1 = -3
            r0.<init>(r1)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            goto L_0x00d1
        L_0x016f:
            cn.jiguang.d.d.s r0 = new cn.jiguang.d.d.s     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            r1 = -1
            r0.<init>(r1)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            goto L_0x00d1
        L_0x0177:
            cn.jiguang.d.d.s r0 = new cn.jiguang.d.d.s     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            r1 = -2
            r0.<init>(r1)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            goto L_0x00d1
        L_0x017f:
            cn.jiguang.d.d.s r0 = new cn.jiguang.d.d.s     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            r1 = -2
            r0.<init>(r1)     // Catch:{ AssertionError -> 0x00ee, Exception -> 0x0125, Throwable -> 0x014b }
            goto L_0x00d1
        L_0x0187:
            cn.jiguang.d.d.s r0 = new cn.jiguang.d.d.s
            java.lang.String r1 = "Failed - retry enough"
            r0.<init>(r6, r1)
            goto L_0x00d1
        L_0x0190:
            r2 = move-exception
            goto L_0x0050
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p015d.p021d.C0452i.m664a(java.lang.String, java.lang.String, android.content.Context, boolean, int):cn.jiguang.d.d.s");
    }
}
