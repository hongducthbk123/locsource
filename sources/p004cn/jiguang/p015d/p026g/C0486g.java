package p004cn.jiguang.p015d.p026g;

import android.content.Context;
import com.facebook.share.internal.ShareConstants;
import org.json.JSONException;
import org.json.JSONObject;
import p004cn.jiguang.p015d.p016a.C0386a;
import p004cn.jiguang.p015d.p016a.C0392g;
import p004cn.jiguang.p015d.p016a.C0393h;
import p004cn.jiguang.p031g.C0506a;
import p004cn.jiguang.p031g.C0532m;

/* renamed from: cn.jiguang.d.g.g */
public final class C0486g {
    /* renamed from: a */
    private static JSONObject m807a(C0393h hVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("net", hVar.mo6373b());
            jSONObject.put("conn_ip", hVar.mo6376c());
            jSONObject.put("local_dns", hVar.mo6379d());
            jSONObject.put(ShareConstants.FEED_SOURCE_PARAM, hVar.mo6382e());
            jSONObject.put("times", hVar.mo6387g());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("count_0_1", hVar.mo6388h());
            jSONObject2.put("count_1_3", hVar.mo6389i());
            jSONObject2.put("count_3_", hVar.mo6390j());
            jSONObject.put("success_details", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    /* renamed from: a */
    public static void m808a(Context context) {
        if (context != null) {
            m811b(context);
            m812c(context);
        }
    }

    /* renamed from: a */
    public static synchronized void m809a(Context context, int i, long j, int i2) {
        synchronized (C0486g.class) {
            String c = C0532m.m1110c(context);
            String d = C0386a.m266d();
            if (d == null) {
                d = "";
            }
            String str = c + "_" + i + "_" + d + "_" + C0506a.m948b();
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            if (i2 == 0) {
                if (j <= 1000) {
                    i3 = 1;
                }
                if (j > 1000 && j <= 3000) {
                    i4 = 1;
                }
                if (j > 3000) {
                    i5 = 1;
                }
            }
            try {
                C0392g a = C0392g.m352a(context);
                C0393h a2 = a.mo6363a(str);
                if (a2 != null) {
                    a.mo6364b(str, c, d, C0506a.m948b(), i, a2.mo6385f() + i2, a2.mo6387g() + 1, i3 + a2.mo6388h(), i4 + a2.mo6389i(), i5 + a2.mo6390j(), a2.mo6391k() + 0);
                } else {
                    a.mo6361a(str, c, d, C0506a.m948b(), i, i2, 1, i3, i4, i5, 0);
                }
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: b */
    private static JSONObject m810b(C0393h hVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("net", hVar.mo6373b());
            jSONObject.put("conn_ip", hVar.mo6376c());
            jSONObject.put("local_dns", hVar.mo6379d());
            jSONObject.put(ShareConstants.FEED_SOURCE_PARAM, hVar.mo6382e());
            jSONObject.put("times", hVar.mo6387g());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00c3, code lost:
        if (r0 != null) goto L_0x00c5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00c8, code lost:
        r3.mo6357b(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00d4, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00dc, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00dd, code lost:
        r10 = r1;
        r1 = r0;
        r0 = r10;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c2 A[ExcHandler: Exception (e java.lang.Exception), Splitter:B:11:0x0017] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00d4 A[Catch:{ Exception -> 0x00c2, all -> 0x00dc }] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized void m811b(android.content.Context r11) {
        /*
            java.lang.Class<cn.jiguang.d.g.g> r2 = p004cn.jiguang.p015d.p026g.C0486g.class
            monitor-enter(r2)
            boolean r0 = p004cn.jiguang.p015d.p016a.C0389d.m334e(r11)     // Catch:{ all -> 0x00bf }
            if (r0 != 0) goto L_0x000b
        L_0x0009:
            monitor-exit(r2)
            return
        L_0x000b:
            cn.jiguang.d.a.g r3 = p004cn.jiguang.p015d.p016a.C0392g.m352a(r11)     // Catch:{ all -> 0x00bf }
            r0 = 0
            boolean r0 = r3.mo6356a(r0)     // Catch:{ all -> 0x00bf }
            if (r0 == 0) goto L_0x0009
            r0 = 0
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Exception -> 0x00c2, all -> 0x00ce }
            r1.<init>()     // Catch:{ Exception -> 0x00c2, all -> 0x00ce }
            java.lang.String r4 = "sdk_index"
            p004cn.jiguang.p015d.p021d.C0460q.m707a(r11, r1, r4)     // Catch:{ Exception -> 0x00c2, all -> 0x00ce }
            java.lang.String r4 = "date"
            java.text.SimpleDateFormat r5 = new java.text.SimpleDateFormat     // Catch:{ Exception -> 0x00c2, all -> 0x00ce }
            java.lang.String r6 = "yyyy-MM-dd"
            java.util.Locale r7 = java.util.Locale.ENGLISH     // Catch:{ Exception -> 0x00c2, all -> 0x00ce }
            r5.<init>(r6, r7)     // Catch:{ Exception -> 0x00c2, all -> 0x00ce }
            java.util.Date r6 = new java.util.Date     // Catch:{ Exception -> 0x00c2, all -> 0x00ce }
            long r8 = p004cn.jiguang.p015d.p016a.C0386a.m254b()     // Catch:{ Exception -> 0x00c2, all -> 0x00ce }
            r6.<init>(r8)     // Catch:{ Exception -> 0x00c2, all -> 0x00ce }
            java.lang.String r5 = r5.format(r6)     // Catch:{ Exception -> 0x00c2, all -> 0x00ce }
            r1.put(r4, r5)     // Catch:{ Exception -> 0x00c2, all -> 0x00ce }
            java.lang.String r4 = "login_total"
            r5 = 1
            int r5 = r3.mo6366c(r5)     // Catch:{ Exception -> 0x00c2, all -> 0x00ce }
            r1.put(r4, r5)     // Catch:{ Exception -> 0x00c2, all -> 0x00ce }
            java.lang.String r4 = "login_failed"
            r5 = 0
            int r5 = r3.mo6366c(r5)     // Catch:{ Exception -> 0x00c2, all -> 0x00ce }
            r1.put(r4, r5)     // Catch:{ Exception -> 0x00c2, all -> 0x00ce }
            org.json.JSONArray r4 = new org.json.JSONArray     // Catch:{ Exception -> 0x00c2, all -> 0x00ce }
            r4.<init>()     // Catch:{ Exception -> 0x00c2, all -> 0x00ce }
            android.database.Cursor r0 = r3.mo6362a()     // Catch:{ Exception -> 0x00c2, all -> 0x00ce }
            if (r0 == 0) goto L_0x007c
        L_0x005c:
            cn.jiguang.d.a.h r5 = p004cn.jiguang.p015d.p016a.C0392g.m353a(r0)     // Catch:{ Exception -> 0x00c2, all -> 0x00dc }
            if (r5 == 0) goto L_0x0073
            java.lang.String r6 = r5.mo6370a()     // Catch:{ Exception -> 0x00c2, all -> 0x00dc }
            boolean r6 = p004cn.jiguang.p031g.C0530k.m1099a(r6)     // Catch:{ Exception -> 0x00c2, all -> 0x00dc }
            if (r6 != 0) goto L_0x0073
            org.json.JSONObject r5 = m810b(r5)     // Catch:{ Exception -> 0x00c2, all -> 0x00dc }
            r4.put(r5)     // Catch:{ Exception -> 0x00c2, all -> 0x00dc }
        L_0x0073:
            boolean r5 = r0.moveToNext()     // Catch:{ Exception -> 0x00c2, all -> 0x00dc }
            if (r5 != 0) goto L_0x005c
            r0.close()     // Catch:{ Exception -> 0x00c2, all -> 0x00dc }
        L_0x007c:
            java.lang.String r5 = "failed_top"
            r1.put(r5, r4)     // Catch:{ Exception -> 0x00c2, all -> 0x00dc }
            org.json.JSONArray r4 = new org.json.JSONArray     // Catch:{ Exception -> 0x00c2, all -> 0x00dc }
            r4.<init>()     // Catch:{ Exception -> 0x00c2, all -> 0x00dc }
            android.database.Cursor r0 = r3.mo6365b()     // Catch:{ Exception -> 0x00c2, all -> 0x00dc }
            if (r0 == 0) goto L_0x00ac
        L_0x008c:
            cn.jiguang.d.a.h r5 = p004cn.jiguang.p015d.p016a.C0392g.m353a(r0)     // Catch:{ Exception -> 0x00c2, all -> 0x00dc }
            if (r5 == 0) goto L_0x00a3
            java.lang.String r6 = r5.mo6370a()     // Catch:{ Exception -> 0x00c2, all -> 0x00dc }
            boolean r6 = p004cn.jiguang.p031g.C0530k.m1099a(r6)     // Catch:{ Exception -> 0x00c2, all -> 0x00dc }
            if (r6 != 0) goto L_0x00a3
            org.json.JSONObject r5 = m807a(r5)     // Catch:{ Exception -> 0x00c2, all -> 0x00dc }
            r4.put(r5)     // Catch:{ Exception -> 0x00c2, all -> 0x00dc }
        L_0x00a3:
            boolean r5 = r0.moveToNext()     // Catch:{ Exception -> 0x00c2, all -> 0x00dc }
            if (r5 != 0) goto L_0x008c
            r0.close()     // Catch:{ Exception -> 0x00c2, all -> 0x00dc }
        L_0x00ac:
            java.lang.String r5 = "succeed_top"
            r1.put(r5, r4)     // Catch:{ Exception -> 0x00c2, all -> 0x00dc }
            p004cn.jiguang.p015d.p021d.C0460q.m714a(r11, r1)     // Catch:{ Exception -> 0x00c2, all -> 0x00dc }
            if (r0 == 0) goto L_0x00b9
            r0.close()     // Catch:{ all -> 0x00bf }
        L_0x00b9:
            r0 = 0
            r3.mo6357b(r0)     // Catch:{ all -> 0x00bf }
            goto L_0x0009
        L_0x00bf:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        L_0x00c2:
            r1 = move-exception
            if (r0 == 0) goto L_0x00c8
            r0.close()     // Catch:{ all -> 0x00bf }
        L_0x00c8:
            r0 = 0
            r3.mo6357b(r0)     // Catch:{ all -> 0x00bf }
            goto L_0x0009
        L_0x00ce:
            r1 = move-exception
            r10 = r1
            r1 = r0
            r0 = r10
        L_0x00d2:
            if (r1 == 0) goto L_0x00d7
            r1.close()     // Catch:{ all -> 0x00bf }
        L_0x00d7:
            r1 = 0
            r3.mo6357b(r1)     // Catch:{ all -> 0x00bf }
            throw r0     // Catch:{ all -> 0x00bf }
        L_0x00dc:
            r1 = move-exception
            r10 = r1
            r1 = r0
            r0 = r10
            goto L_0x00d2
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p015d.p026g.C0486g.m811b(android.content.Context):void");
    }

    /* renamed from: c */
    private static synchronized void m812c(Context context) {
        synchronized (C0486g.class) {
            C0392g a = C0392g.m352a(context);
            if (a.mo6356a(true)) {
                try {
                    a.getWritableDatabase().execSQL("delete from jpush_statistics");
                    a.mo6357b(true);
                } catch (Exception e) {
                    a.mo6357b(true);
                } catch (Throwable th) {
                    a.mo6357b(true);
                    throw th;
                }
            }
        }
        return;
    }
}
