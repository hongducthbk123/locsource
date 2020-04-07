package p004cn.jiguang.p005a.p006a.p010d;

import android.app.Application;
import android.content.Context;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;
import p004cn.jiguang.p005a.p011b.C0374c;
import p004cn.jiguang.p015d.p016a.C0386a;
import p004cn.jiguang.p015d.p016a.C0389d;
import p004cn.jiguang.p015d.p021d.C0460q;
import p004cn.jiguang.p015d.p026g.C0482c;
import p004cn.jiguang.p029e.C0501d;
import p004cn.jiguang.p031g.C0506a;
import p004cn.jiguang.p031g.C0530k;

/* renamed from: cn.jiguang.a.a.d.b */
public class C0366b {

    /* renamed from: a */
    public static boolean f142a = false;

    /* renamed from: b */
    public static boolean f143b = false;

    /* renamed from: c */
    private static volatile C0366b f144c = null;

    /* renamed from: d */
    private ExecutorService f145d = Executors.newSingleThreadExecutor();

    /* renamed from: e */
    private String f146e = null;

    /* renamed from: f */
    private String f147f = null;

    /* renamed from: g */
    private long f148g = 30;

    /* renamed from: h */
    private long f149h = 0;

    /* renamed from: i */
    private long f150i = 0;

    /* renamed from: j */
    private boolean f151j = true;

    /* renamed from: k */
    private boolean f152k = false;

    /* renamed from: l */
    private boolean f153l = true;

    /* renamed from: m */
    private long f154m = 0;

    /* renamed from: n */
    private JSONObject f155n = null;

    /* renamed from: o */
    private final Object f156o = new Object();

    private C0366b() {
    }

    /* renamed from: a */
    public static C0366b m144a() {
        if (f144c == null) {
            synchronized (C0366b.class) {
                f144c = new C0366b();
            }
        }
        return f144c;
    }

    /* renamed from: a */
    private JSONObject m145a(Context context, long j) {
        C0374c.m163a().mo6260b(context, "cur_session_start", this.f149h);
        StringBuilder sb = new StringBuilder();
        String i = C0389d.m338i(context);
        if (!C0530k.m1099a(i)) {
            sb.append(i);
        }
        String h = C0506a.m972h(context);
        if (!C0530k.m1099a(h)) {
            sb.append(h);
        }
        sb.append(j);
        this.f147f = C0506a.m933a(sb.toString());
        C0374c.m163a().mo6261b(context, "session_id", this.f147f);
        JSONObject jSONObject = new JSONObject();
        try {
            m147a(jSONObject);
            C0460q.m707a(context, jSONObject, "active_launch");
            jSONObject.put("session_id", this.f147f);
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x006f, code lost:
        if ((r10.f149h - r10.f150i) > (r10.f148g * 1000)) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0024, code lost:
        if ((r10.f149h - r2) <= (r10.f148g * 1000)) goto L_0x0026;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void m146a(p004cn.jiguang.p005a.p006a.p010d.C0366b r10, android.content.Context r11) {
        /*
            r8 = 1000(0x3e8, double:4.94E-321)
            r4 = -1
            r6 = 0
            r0 = 0
            r1 = 1
            boolean r2 = r10.f151j
            if (r2 == 0) goto L_0x0065
            r10.f151j = r0
            cn.jiguang.a.b.c r2 = p004cn.jiguang.p005a.p011b.C0374c.m163a()
            java.lang.String r3 = "last_pause"
            long r2 = r2.mo6258a(r11, r3, r4)
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 == 0) goto L_0x0071
            long r4 = r10.f149h
            long r2 = r4 - r2
            long r4 = r10.f148g
            long r4 = r4 * r8
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 > 0) goto L_0x0071
        L_0x0026:
            if (r0 == 0) goto L_0x0076
            org.json.JSONArray r0 = new org.json.JSONArray
            r0.<init>()
            long r2 = r10.f149h
            org.json.JSONObject r1 = r10.m145a(r11, r2)
            if (r1 == 0) goto L_0x0038
            r0.put(r1)
        L_0x0038:
            java.lang.Object r1 = r10.f156o
            monitor-enter(r1)
            org.json.JSONObject r2 = r10.m150d(r11)     // Catch:{ all -> 0x0073 }
            if (r2 == 0) goto L_0x0055
            int r3 = r2.length()     // Catch:{ all -> 0x0073 }
            if (r3 <= 0) goto L_0x0055
            java.lang.String r3 = "active_terminate"
            p004cn.jiguang.p015d.p021d.C0460q.m707a(r11, r2, r3)     // Catch:{ Exception -> 0x0083 }
        L_0x004c:
            java.lang.String r3 = "jpush_stat_cache.json"
            r4 = 0
            p004cn.jiguang.p015d.p021d.C0460q.m716a(r11, r3, r4)     // Catch:{ all -> 0x0073 }
            r3 = 0
            r10.f155n = r3     // Catch:{ all -> 0x0073 }
        L_0x0055:
            monitor-exit(r1)     // Catch:{ all -> 0x0073 }
            if (r2 == 0) goto L_0x0061
            int r1 = r2.length()
            if (r1 <= 0) goto L_0x0061
            r0.put(r2)
        L_0x0061:
            p004cn.jiguang.p015d.p021d.C0460q.m711a(r11, r0)
        L_0x0064:
            return
        L_0x0065:
            long r2 = r10.f149h
            long r4 = r10.f150i
            long r2 = r2 - r4
            long r4 = r10.f148g
            long r4 = r4 * r8
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 <= 0) goto L_0x0026
        L_0x0071:
            r0 = r1
            goto L_0x0026
        L_0x0073:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0073 }
            throw r0
        L_0x0076:
            cn.jiguang.a.b.c r0 = p004cn.jiguang.p005a.p011b.C0374c.m163a()
            java.lang.String r1 = "session_id"
            java.lang.String r0 = r0.mo6259a(r11, r1, r6)
            r10.f147f = r0
            goto L_0x0064
        L_0x0083:
            r3 = move-exception
            goto L_0x004c
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p005a.p006a.p010d.C0366b.m146a(cn.jiguang.a.a.d.b, android.content.Context):void");
    }

    /* renamed from: a */
    private static void m147a(JSONObject jSONObject) {
        String a = C0482c.m788a();
        String str = a.split("_")[0];
        String str2 = a.split("_")[1];
        jSONObject.put("date", str);
        jSONObject.put("time", str2);
    }

    /* renamed from: b */
    static /* synthetic */ void m148b(C0366b bVar, Context context) {
        if (context != null) {
            synchronized (bVar.f156o) {
                C0374c.m163a().mo6260b(context, "last_pause", bVar.f150i);
                C0374c.m163a().mo6260b(context, "cur_seesion_end", bVar.f150i);
                JSONObject d = bVar.m150d(context);
                JSONObject jSONObject = d == null ? new JSONObject() : d;
                try {
                    long a = C0374c.m163a().mo6258a(context, "cur_session_start", 0);
                    long j = 10;
                    if (a == 0) {
                        long j2 = bVar.f150i - bVar.f154m;
                        if (j2 > 0) {
                            j = j2 / 1000;
                        }
                        C0374c.m163a().mo6260b(context, "cur_session_start", bVar.f154m);
                    } else {
                        j = (bVar.f150i - a) / 1000;
                    }
                    jSONObject.put("duration", j);
                    jSONObject.put("itime", C0386a.m293t());
                    jSONObject.put("session_id", bVar.f147f);
                    m147a(jSONObject);
                } catch (Exception e) {
                }
                bVar.f155n = jSONObject;
                if (bVar.f155n != null) {
                    try {
                        C0460q.m710a(context, bVar.f155n.toString().getBytes("utf-8").length);
                    } catch (UnsupportedEncodingException | Exception e2) {
                    }
                }
                C0460q.m716a(context, "jpush_stat_cache.json", jSONObject);
            }
        }
    }

    /* renamed from: c */
    private boolean m149c(Context context, String str) {
        if (!this.f153l || context == null) {
            return false;
        }
        if (!(context instanceof Application)) {
            return true;
        }
        C0501d.m909d("JPushSA", "Context should be an Activity on this method : " + str);
        return false;
    }

    /* renamed from: d */
    private JSONObject m150d(Context context) {
        if (this.f155n == null) {
            this.f155n = C0460q.m706a(context, "jpush_stat_cache.json");
        }
        return this.f155n;
    }

    /* renamed from: a */
    public final void mo6248a(Context context) {
        if (m149c(context, "onResume")) {
            f142a = true;
            try {
                this.f152k = false;
            } catch (ClassCastException | Exception e) {
            }
            if (!this.f152k) {
                this.f152k = true;
                this.f149h = System.currentTimeMillis();
                this.f146e = context.getClass().getName();
                try {
                    this.f145d.execute(new C0369e(this, context.getApplicationContext()));
                } catch (Throwable th) {
                }
            }
        }
    }

    /* renamed from: a */
    public final void mo6249a(Context context, String str) {
        if (!this.f152k) {
            this.f152k = true;
            this.f146e = str;
            this.f149h = System.currentTimeMillis();
            try {
                this.f145d.execute(new C0367c(this, context.getApplicationContext()));
            } catch (Throwable th) {
            }
        }
    }

    /* renamed from: b */
    public final void mo6250b(Context context) {
        if (m149c(context, "onPause")) {
            f143b = true;
            try {
                this.f152k = true;
            } catch (ClassCastException e) {
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (this.f152k) {
                this.f152k = false;
                if (this.f146e != null && this.f146e.equals(context.getClass().getName())) {
                    this.f150i = System.currentTimeMillis();
                    this.f154m = this.f149h;
                    try {
                        this.f145d.execute(new C0370f(this, context.getApplicationContext()));
                    } catch (Throwable th) {
                    }
                }
            }
        }
    }

    /* renamed from: b */
    public final void mo6251b(Context context, String str) {
        if (this.f152k) {
            this.f152k = false;
            if (this.f146e == null || !this.f146e.equals(str)) {
                C0501d.m909d("JPushSA", "page name didn't match the last one passed by onResume");
                return;
            }
            this.f150i = System.currentTimeMillis();
            try {
                this.f145d.execute(new C0368d(this, context.getApplicationContext()));
            } catch (Throwable th) {
            }
        }
    }

    /* renamed from: c */
    public final void mo6252c(Context context) {
        try {
            if (this.f146e != null && this.f152k) {
                this.f150i = System.currentTimeMillis();
                try {
                    this.f145d.execute(new C0371g(this, context.getApplicationContext()));
                } catch (Throwable th) {
                }
            }
        } catch (Exception e) {
        }
    }
}
