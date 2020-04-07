package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import android.util.Base64;
import com.google.android.vending.expansion.downloader.Constants;
import com.tencent.bugly.C1925b;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/* renamed from: com.tencent.bugly.proguard.t */
/* compiled from: BUGLY */
public final class C2007t {

    /* renamed from: b */
    private static C2007t f1643b = null;

    /* renamed from: a */
    public boolean f1644a = true;

    /* renamed from: c */
    private final C2001o f1645c;

    /* renamed from: d */
    private final Context f1646d;

    /* renamed from: e */
    private Map<Integer, Long> f1647e = new HashMap();

    /* renamed from: f */
    private LinkedBlockingQueue<Runnable> f1648f = new LinkedBlockingQueue<>();

    /* renamed from: g */
    private LinkedBlockingQueue<Runnable> f1649g = new LinkedBlockingQueue<>();
    /* access modifiers changed from: private */

    /* renamed from: h */
    public final Object f1650h = new Object();

    /* renamed from: i */
    private String f1651i = null;

    /* renamed from: j */
    private byte[] f1652j = null;

    /* renamed from: k */
    private long f1653k = 0;
    /* access modifiers changed from: private */

    /* renamed from: l */
    public byte[] f1654l = null;

    /* renamed from: m */
    private long f1655m = 0;
    /* access modifiers changed from: private */

    /* renamed from: n */
    public String f1656n = null;

    /* renamed from: o */
    private long f1657o = 0;

    /* renamed from: p */
    private final Object f1658p = new Object();
    /* access modifiers changed from: private */

    /* renamed from: q */
    public boolean f1659q = false;
    /* access modifiers changed from: private */

    /* renamed from: r */
    public final Object f1660r = new Object();

    /* renamed from: s */
    private int f1661s = 0;

    /* renamed from: com.tencent.bugly.proguard.t$a */
    /* compiled from: BUGLY */
    class C2010a implements Runnable {

        /* renamed from: a */
        private final Context f1666a;

        /* renamed from: b */
        private final Runnable f1667b;

        /* renamed from: c */
        private final long f1668c;

        public C2010a(Context context) {
            this.f1666a = context;
            this.f1667b = null;
            this.f1668c = 0;
        }

        public C2010a(Context context, Runnable runnable, long j) {
            this.f1666a = context;
            this.f1667b = runnable;
            this.f1668c = j;
        }

        public final void run() {
            if (!C2018y.m2156a(this.f1666a, "security_info", 30000)) {
                C2014w.m2117c("[UploadManager] Sleep %d try to lock security file again (pid=%d | tid=%d)", Integer.valueOf(5000), Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                C2018y.m2170b((long) Constants.ACTIVE_THREAD_WATCHDOG);
                if (C2018y.m2150a((Runnable) this, "BUGLY_ASYNC_UPLOAD") == null) {
                    C2014w.m2118d("[UploadManager] Failed to start a thread to execute task of initializing security context, try to post it into thread pool.", new Object[0]);
                    C2012v a = C2012v.m2106a();
                    if (a != null) {
                        a.mo19636a(this);
                    } else {
                        C2014w.m2119e("[UploadManager] Asynchronous thread pool is unavailable now, try next time.", new Object[0]);
                    }
                }
            } else {
                if (!C2007t.this.m2086e()) {
                    C2014w.m2118d("[UploadManager] Failed to load security info from database", new Object[0]);
                    C2007t.this.mo19626b(false);
                }
                if (C2007t.this.f1656n != null) {
                    if (C2007t.this.mo19627b()) {
                        C2014w.m2117c("[UploadManager] Sucessfully got session ID, try to execute upload tasks now (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                        if (this.f1667b != null) {
                            C2007t.this.m2074a(this.f1667b, this.f1668c);
                        }
                        C2007t.this.m2080c(0);
                        C2018y.m2173b(this.f1666a, "security_info");
                        synchronized (C2007t.this.f1660r) {
                            C2007t.this.f1659q = false;
                        }
                        return;
                    }
                    C2014w.m2113a("[UploadManager] Session ID is expired, drop it.", new Object[0]);
                    C2007t.this.mo19626b(true);
                }
                byte[] a2 = C2018y.m2159a(128);
                if (a2 == null || (a2.length << 3) != 128) {
                    C2014w.m2118d("[UploadManager] Failed to create AES key (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                    C2007t.this.mo19626b(false);
                    C2018y.m2173b(this.f1666a, "security_info");
                    synchronized (C2007t.this.f1660r) {
                        C2007t.this.f1659q = false;
                    }
                    return;
                }
                C2007t.this.f1654l = a2;
                C2014w.m2117c("[UploadManager] Execute one upload task for requesting session ID (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                if (this.f1667b != null) {
                    C2007t.this.m2074a(this.f1667b, this.f1668c);
                } else {
                    C2007t.this.m2080c(1);
                }
            }
        }
    }

    /* renamed from: b */
    static /* synthetic */ int m2079b(C2007t tVar) {
        int i = tVar.f1661s - 1;
        tVar.f1661s = i;
        return i;
    }

    private C2007t(Context context) {
        this.f1646d = context;
        this.f1645c = C2001o.m2035a();
        try {
            Class.forName("android.util.Base64");
        } catch (ClassNotFoundException e) {
            C2014w.m2113a("[UploadManager] Error: Can not find Base64 class, will not use stronger security way to upload", new Object[0]);
            this.f1644a = false;
        }
        if (this.f1644a) {
            StringBuilder sb = new StringBuilder();
            sb.append("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDP9x32s5pPtZBXzJBz2GWM/sbTvVO2+RvW0PH01IdaBxc/").append("fB6fbHZocC9T3nl1+J5eAFjIRVuV8vHDky7Qo82Mnh0PVvcZIEQvMMVKU8dsMQopxgsOs2gkSHJwgWdinKNS8CmWobo6pFwPUW11lMv714jAUZRq2GBOqiO2vQI6iwIDAQAB");
            this.f1651i = sb.toString();
        }
    }

    /* renamed from: a */
    public static synchronized C2007t m2070a(Context context) {
        C2007t tVar;
        synchronized (C2007t.class) {
            if (f1643b == null) {
                f1643b = new C2007t(context);
            }
            tVar = f1643b;
        }
        return tVar;
    }

    /* renamed from: a */
    public static synchronized C2007t m2069a() {
        C2007t tVar;
        synchronized (C2007t.class) {
            tVar = f1643b;
        }
        return tVar;
    }

    /* renamed from: a */
    public final void mo19620a(int i, C1979al alVar, String str, String str2, C2006s sVar, long j, boolean z) {
        try {
            m2075a(new C2011u(this.f1646d, i, alVar.f1504g, C1967a.m1895a(alVar), str, str2, sVar, this.f1644a, z), true, true, j);
        } catch (Throwable th) {
            if (!C2014w.m2114a(th)) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public final void mo19618a(int i, int i2, byte[] bArr, String str, String str2, C2006s sVar, int i3, int i4, boolean z, Map<String, String> map) {
        try {
            m2075a(new C2011u(this.f1646d, i, i2, bArr, str, str2, sVar, this.f1644a, i3, i4, false, map), z, false, 0);
        } catch (Throwable th) {
            if (!C2014w.m2114a(th)) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public final void mo19621a(int i, C1979al alVar, String str, String str2, C2006s sVar, boolean z) {
        mo19618a(i, alVar.f1504g, C1967a.m1895a(alVar), str, str2, sVar, 0, 0, z, null);
    }

    /* renamed from: a */
    public final long mo19617a(boolean z) {
        long j;
        long j2 = 0;
        long b = C2018y.m2166b();
        List a = this.f1645c.mo19600a(z ? 5 : 3);
        if (a == null || a.size() <= 0) {
            j = 0;
        } else {
            try {
                C2004q qVar = (C2004q) a.get(0);
                if (qVar.f1637e >= b) {
                    j2 = C2018y.m2178c(qVar.f1639g);
                    a.remove(qVar);
                }
                j = j2;
            } catch (Throwable th) {
                Throwable th2 = th;
                j = 0;
                C2014w.m2114a(th2);
            }
            if (a.size() > 0) {
                this.f1645c.mo19602a(a);
            }
        }
        C2014w.m2117c("[UploadManager] Local network consume: %d KB", Long.valueOf(j / 1024));
        return j;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final synchronized void mo19623a(long j, boolean z) {
        int i = z ? 5 : 3;
        C2004q qVar = new C2004q();
        qVar.f1634b = i;
        qVar.f1637e = C2018y.m2166b();
        qVar.f1635c = "";
        qVar.f1636d = "";
        qVar.f1639g = C2018y.m2180c(j);
        this.f1645c.mo19606b(i);
        this.f1645c.mo19605a(qVar);
        C2014w.m2117c("[UploadManager] Network total consume: %d KB", Long.valueOf(j / 1024));
    }

    /* renamed from: a */
    public final synchronized void mo19619a(int i, long j) {
        if (i >= 0) {
            this.f1647e.put(Integer.valueOf(i), Long.valueOf(j));
            C2004q qVar = new C2004q();
            qVar.f1634b = i;
            qVar.f1637e = j;
            qVar.f1635c = "";
            qVar.f1636d = "";
            qVar.f1639g = new byte[0];
            this.f1645c.mo19606b(i);
            this.f1645c.mo19605a(qVar);
            C2014w.m2117c("[UploadManager] Uploading(ID:%d) time: %s", Integer.valueOf(i), C2018y.m2144a(j));
        } else {
            C2014w.m2119e("[UploadManager] Unknown uploading ID: %d", Integer.valueOf(i));
        }
    }

    /* renamed from: a */
    public final synchronized long mo19616a(int i) {
        long j;
        long j2;
        j = 0;
        if (i >= 0) {
            Long l = (Long) this.f1647e.get(Integer.valueOf(i));
            if (l != null) {
                j = l.longValue();
            } else {
                List<C2004q> a = this.f1645c.mo19600a(i);
                if (a != null && a.size() > 0) {
                    if (a.size() > 1) {
                        for (C2004q qVar : a) {
                            if (qVar.f1637e > j) {
                                j2 = qVar.f1637e;
                            } else {
                                j2 = j;
                            }
                            j = j2;
                        }
                        this.f1645c.mo19606b(i);
                    } else {
                        try {
                            j = ((C2004q) a.get(0)).f1637e;
                        } catch (Throwable th) {
                            C2014w.m2114a(th);
                        }
                    }
                }
            }
        } else {
            C2014w.m2119e("[UploadManager] Unknown upload ID: %d", Integer.valueOf(i));
        }
        return j;
    }

    /* renamed from: b */
    public final boolean mo19628b(int i) {
        if (C1925b.f1097c) {
            C2014w.m2117c("Uploading frequency will not be checked if SDK is in debug mode.", new Object[0]);
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis() - mo19616a(i);
        C2014w.m2117c("[UploadManager] Time interval is %d seconds since last uploading(ID: %d).", Long.valueOf(currentTimeMillis / 1000), Integer.valueOf(i));
        if (currentTimeMillis >= 30000) {
            return true;
        }
        C2014w.m2113a("[UploadManager] Data only be uploaded once in %d seconds.", Long.valueOf(30));
        return false;
    }

    /* renamed from: c */
    private static boolean m2081c() {
        C2014w.m2117c("[UploadManager] Drop security info of database (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            C2001o a = C2001o.m2035a();
            if (a != null) {
                return a.mo19603a(555, "security_info", (C2000n) null, true);
            }
            C2014w.m2118d("[UploadManager] Failed to get Database", new Object[0]);
            return false;
        } catch (Throwable th) {
            C2014w.m2114a(th);
            return false;
        }
    }

    /* renamed from: d */
    private boolean m2084d() {
        C2014w.m2117c("[UploadManager] Record security info to database (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            C2001o a = C2001o.m2035a();
            if (a == null) {
                C2014w.m2118d("[UploadManager] Failed to get database", new Object[0]);
                return false;
            }
            StringBuilder sb = new StringBuilder();
            if (this.f1654l != null) {
                sb.append(Base64.encodeToString(this.f1654l, 0));
                sb.append("#");
                if (this.f1655m != 0) {
                    sb.append(Long.toString(this.f1655m));
                } else {
                    sb.append("null");
                }
                sb.append("#");
                if (this.f1656n != null) {
                    sb.append(this.f1656n);
                } else {
                    sb.append("null");
                }
                sb.append("#");
                if (this.f1657o != 0) {
                    sb.append(Long.toString(this.f1657o));
                } else {
                    sb.append("null");
                }
                a.mo19604a(555, "security_info", sb.toString().getBytes(), (C2000n) null, true);
                return true;
            }
            C2014w.m2117c("[UploadManager] AES key is null, will not record", new Object[0]);
            return false;
        } catch (Throwable th) {
            C2014w.m2114a(th);
            m2081c();
            return false;
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean m2086e() {
        /*
            r8 = this;
            r3 = 2
            r1 = 0
            r2 = 1
            java.lang.String r0 = "[UploadManager] Load security info from database (pid=%d | tid=%d)"
            java.lang.Object[] r3 = new java.lang.Object[r3]
            int r4 = android.os.Process.myPid()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3[r1] = r4
            int r4 = android.os.Process.myTid()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3[r2] = r4
            com.tencent.bugly.proguard.C2014w.m2117c(r0, r3)
            com.tencent.bugly.proguard.o r0 = com.tencent.bugly.proguard.C2001o.m2035a()     // Catch:{ Throwable -> 0x00fe }
            if (r0 != 0) goto L_0x002e
            java.lang.String r0 = "[UploadManager] Failed to get database"
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x00fe }
            com.tencent.bugly.proguard.C2014w.m2118d(r0, r2)     // Catch:{ Throwable -> 0x00fe }
            r0 = r1
        L_0x002d:
            return r0
        L_0x002e:
            r3 = 555(0x22b, float:7.78E-43)
            r4 = 0
            r5 = 1
            java.util.Map r0 = r0.mo19601a(r3, r4, r5)     // Catch:{ Throwable -> 0x00fe }
            if (r0 == 0) goto L_0x00d4
            java.lang.String r3 = "security_info"
            boolean r3 = r0.containsKey(r3)     // Catch:{ Throwable -> 0x00fe }
            if (r3 == 0) goto L_0x00d4
            java.lang.String r3 = new java.lang.String     // Catch:{ Throwable -> 0x00fe }
            java.lang.String r4 = "security_info"
            java.lang.Object r0 = r0.get(r4)     // Catch:{ Throwable -> 0x00fe }
            byte[] r0 = (byte[]) r0     // Catch:{ Throwable -> 0x00fe }
            r3.<init>(r0)     // Catch:{ Throwable -> 0x00fe }
            java.lang.String r0 = "#"
            java.lang.String[] r4 = r3.split(r0)     // Catch:{ Throwable -> 0x00fe }
            int r0 = r4.length     // Catch:{ Throwable -> 0x00fe }
            r5 = 4
            if (r0 != r5) goto L_0x00e9
            r0 = 0
            r0 = r4[r0]     // Catch:{ Throwable -> 0x00fe }
            boolean r0 = r0.isEmpty()     // Catch:{ Throwable -> 0x00fe }
            if (r0 != 0) goto L_0x0105
            r0 = 0
            r0 = r4[r0]     // Catch:{ Throwable -> 0x00fe }
            java.lang.String r3 = "null"
            boolean r0 = r0.equals(r3)     // Catch:{ Throwable -> 0x00fe }
            if (r0 != 0) goto L_0x0105
            r0 = 0
            r0 = r4[r0]     // Catch:{ Throwable -> 0x00d7 }
            r3 = 0
            byte[] r0 = android.util.Base64.decode(r0, r3)     // Catch:{ Throwable -> 0x00d7 }
            r8.f1654l = r0     // Catch:{ Throwable -> 0x00d7 }
            r0 = r1
        L_0x0076:
            if (r0 != 0) goto L_0x0095
            r3 = 1
            r3 = r4[r3]     // Catch:{ Throwable -> 0x00fe }
            boolean r3 = r3.isEmpty()     // Catch:{ Throwable -> 0x00fe }
            if (r3 != 0) goto L_0x0095
            r3 = 1
            r3 = r4[r3]     // Catch:{ Throwable -> 0x00fe }
            java.lang.String r5 = "null"
            boolean r3 = r3.equals(r5)     // Catch:{ Throwable -> 0x00fe }
            if (r3 != 0) goto L_0x0095
            r3 = 1
            r3 = r4[r3]     // Catch:{ Throwable -> 0x00dd }
            long r6 = java.lang.Long.parseLong(r3)     // Catch:{ Throwable -> 0x00dd }
            r8.f1655m = r6     // Catch:{ Throwable -> 0x00dd }
        L_0x0095:
            if (r0 != 0) goto L_0x00b0
            r3 = 2
            r3 = r4[r3]     // Catch:{ Throwable -> 0x00fe }
            boolean r3 = r3.isEmpty()     // Catch:{ Throwable -> 0x00fe }
            if (r3 != 0) goto L_0x00b0
            r3 = 2
            r3 = r4[r3]     // Catch:{ Throwable -> 0x00fe }
            java.lang.String r5 = "null"
            boolean r3 = r3.equals(r5)     // Catch:{ Throwable -> 0x00fe }
            if (r3 != 0) goto L_0x00b0
            r3 = 2
            r3 = r4[r3]     // Catch:{ Throwable -> 0x00fe }
            r8.f1656n = r3     // Catch:{ Throwable -> 0x00fe }
        L_0x00b0:
            if (r0 != 0) goto L_0x00cf
            r3 = 3
            r3 = r4[r3]     // Catch:{ Throwable -> 0x00fe }
            boolean r3 = r3.isEmpty()     // Catch:{ Throwable -> 0x00fe }
            if (r3 != 0) goto L_0x00cf
            r3 = 3
            r3 = r4[r3]     // Catch:{ Throwable -> 0x00fe }
            java.lang.String r5 = "null"
            boolean r3 = r3.equals(r5)     // Catch:{ Throwable -> 0x00fe }
            if (r3 != 0) goto L_0x00cf
            r3 = 3
            r3 = r4[r3]     // Catch:{ Throwable -> 0x00e3 }
            long r4 = java.lang.Long.parseLong(r3)     // Catch:{ Throwable -> 0x00e3 }
            r8.f1657o = r4     // Catch:{ Throwable -> 0x00e3 }
        L_0x00cf:
            if (r0 == 0) goto L_0x00d4
            m2081c()     // Catch:{ Throwable -> 0x00fe }
        L_0x00d4:
            r0 = r2
            goto L_0x002d
        L_0x00d7:
            r0 = move-exception
            com.tencent.bugly.proguard.C2014w.m2114a(r0)     // Catch:{ Throwable -> 0x00fe }
            r0 = r2
            goto L_0x0076
        L_0x00dd:
            r0 = move-exception
            com.tencent.bugly.proguard.C2014w.m2114a(r0)     // Catch:{ Throwable -> 0x00fe }
            r0 = r2
            goto L_0x0095
        L_0x00e3:
            r0 = move-exception
            com.tencent.bugly.proguard.C2014w.m2114a(r0)     // Catch:{ Throwable -> 0x00fe }
            r0 = r2
            goto L_0x00cf
        L_0x00e9:
            java.lang.String r0 = "SecurityInfo = %s, Strings.length = %d"
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x00fe }
            r6 = 0
            r5[r6] = r3     // Catch:{ Throwable -> 0x00fe }
            r3 = 1
            int r4 = r4.length     // Catch:{ Throwable -> 0x00fe }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Throwable -> 0x00fe }
            r5[r3] = r4     // Catch:{ Throwable -> 0x00fe }
            com.tencent.bugly.proguard.C2014w.m2113a(r0, r5)     // Catch:{ Throwable -> 0x00fe }
            r0 = r2
            goto L_0x00cf
        L_0x00fe:
            r0 = move-exception
            com.tencent.bugly.proguard.C2014w.m2114a(r0)
            r0 = r1
            goto L_0x002d
        L_0x0105:
            r0 = r1
            goto L_0x0076
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2007t.m2086e():boolean");
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final boolean mo19627b() {
        if (this.f1656n == null || this.f1657o == 0) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis() + this.f1653k;
        if (this.f1657o >= currentTimeMillis) {
            return true;
        }
        C2014w.m2117c("[UploadManager] Session ID expired time from server is: %d(%s), but now is: %d(%s)", Long.valueOf(this.f1657o), new Date(this.f1657o).toString(), Long.valueOf(currentTimeMillis), new Date(currentTimeMillis).toString());
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo19626b(boolean z) {
        synchronized (this.f1658p) {
            C2014w.m2117c("[UploadManager] Clear security context (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            this.f1654l = null;
            this.f1656n = null;
            this.f1657o = 0;
        }
        if (z) {
            m2081c();
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:100:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00c5, code lost:
        if (r15 <= 0) goto L_0x00e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00c7, code lost:
        com.tencent.bugly.proguard.C2014w.m2117c("[UploadManager] Execute urgent upload tasks of queue which has %d tasks (pid=%d | tid=%d)", java.lang.Integer.valueOf(r15), java.lang.Integer.valueOf(android.os.Process.myPid()), java.lang.Integer.valueOf(android.os.Process.myTid()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00e8, code lost:
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00e9, code lost:
        if (r3 >= r15) goto L_0x0139;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00eb, code lost:
        r0 = (java.lang.Runnable) r5.poll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00f1, code lost:
        if (r0 == null) goto L_0x0139;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00f3, code lost:
        r7 = r14.f1650h;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00f5, code lost:
        monitor-enter(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00f8, code lost:
        if (r14.f1661s < 2) goto L_0x0104;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00fa, code lost:
        if (r4 == null) goto L_0x0104;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00fc, code lost:
        r4.mo19636a(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00ff, code lost:
        monitor-exit(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0100, code lost:
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0104, code lost:
        monitor-exit(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0105, code lost:
        com.tencent.bugly.proguard.C2014w.m2113a("[UploadManager] Create and start a new thread to execute a upload task: %s", "BUGLY_ASYNC_UPLOAD");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x011b, code lost:
        if (com.tencent.bugly.proguard.C2018y.m2150a((java.lang.Runnable) new com.tencent.bugly.proguard.C2007t.C20081(r14), "BUGLY_ASYNC_UPLOAD") == null) goto L_0x012e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x011d, code lost:
        r7 = r14.f1650h;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x011f, code lost:
        monitor-enter(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:?, code lost:
        r14.f1661s++;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0126, code lost:
        monitor-exit(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x012e, code lost:
        com.tencent.bugly.proguard.C2014w.m2118d("[UploadManager] Failed to start a thread to execute asynchronous upload task, will try again next time.", new java.lang.Object[0]);
        m2077a(r0, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0139, code lost:
        if (r1 <= 0) goto L_0x015c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x013b, code lost:
        com.tencent.bugly.proguard.C2014w.m2117c("[UploadManager] Execute upload tasks of queue which has %d tasks (pid=%d | tid=%d)", java.lang.Integer.valueOf(r1), java.lang.Integer.valueOf(android.os.Process.myPid()), java.lang.Integer.valueOf(android.os.Process.myTid()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x015c, code lost:
        if (r4 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x015e, code lost:
        r4.mo19636a(new com.tencent.bugly.proguard.C2007t.C20092(r14));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:?, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0061 A[SYNTHETIC, Splitter:B:19:0x0061] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006b A[Catch:{ Throwable -> 0x0089 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x009d A[Catch:{ Throwable -> 0x0089 }] */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m2080c(int r15) {
        /*
            r14 = this;
            r13 = 3
            r12 = 2
            r11 = 1
            r2 = 0
            if (r15 >= 0) goto L_0x000e
            java.lang.String r0 = "[UploadManager] Number of task to execute should >= 0"
            java.lang.Object[] r1 = new java.lang.Object[r2]
            com.tencent.bugly.proguard.C2014w.m2113a(r0, r1)
        L_0x000d:
            return
        L_0x000e:
            com.tencent.bugly.proguard.v r4 = com.tencent.bugly.proguard.C2012v.m2106a()
            java.util.concurrent.LinkedBlockingQueue r5 = new java.util.concurrent.LinkedBlockingQueue
            r5.<init>()
            java.util.concurrent.LinkedBlockingQueue r6 = new java.util.concurrent.LinkedBlockingQueue
            r6.<init>()
            java.lang.Object r7 = r14.f1650h
            monitor-enter(r7)
            java.lang.String r0 = "[UploadManager] Try to poll all upload task need and put them into temp queue (pid=%d | tid=%d)"
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0057 }
            r3 = 0
            int r8 = android.os.Process.myPid()     // Catch:{ all -> 0x0057 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x0057 }
            r1[r3] = r8     // Catch:{ all -> 0x0057 }
            r3 = 1
            int r8 = android.os.Process.myTid()     // Catch:{ all -> 0x0057 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x0057 }
            r1[r3] = r8     // Catch:{ all -> 0x0057 }
            com.tencent.bugly.proguard.C2014w.m2117c(r0, r1)     // Catch:{ all -> 0x0057 }
            java.util.concurrent.LinkedBlockingQueue<java.lang.Runnable> r0 = r14.f1648f     // Catch:{ all -> 0x0057 }
            int r1 = r0.size()     // Catch:{ all -> 0x0057 }
            java.util.concurrent.LinkedBlockingQueue<java.lang.Runnable> r0 = r14.f1649g     // Catch:{ all -> 0x0057 }
            int r0 = r0.size()     // Catch:{ all -> 0x0057 }
            if (r1 != 0) goto L_0x005a
            if (r0 != 0) goto L_0x005a
            java.lang.String r0 = "[UploadManager] There is no upload task in queue."
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0057 }
            com.tencent.bugly.proguard.C2014w.m2117c(r0, r1)     // Catch:{ all -> 0x0057 }
            monitor-exit(r7)     // Catch:{ all -> 0x0057 }
            goto L_0x000d
        L_0x0057:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        L_0x005a:
            if (r15 == 0) goto L_0x016b
            if (r15 >= r1) goto L_0x0081
            r0 = r2
        L_0x005f:
            if (r4 == 0) goto L_0x0067
            boolean r1 = r4.mo19639c()     // Catch:{ all -> 0x0057 }
            if (r1 != 0) goto L_0x0168
        L_0x0067:
            r1 = r2
        L_0x0068:
            r3 = r2
        L_0x0069:
            if (r3 >= r15) goto L_0x009a
            java.util.concurrent.LinkedBlockingQueue<java.lang.Runnable> r0 = r14.f1648f     // Catch:{ all -> 0x0057 }
            java.lang.Object r0 = r0.peek()     // Catch:{ all -> 0x0057 }
            java.lang.Runnable r0 = (java.lang.Runnable) r0     // Catch:{ all -> 0x0057 }
            if (r0 == 0) goto L_0x009a
            r5.put(r0)     // Catch:{ Throwable -> 0x0089 }
            java.util.concurrent.LinkedBlockingQueue<java.lang.Runnable> r0 = r14.f1648f     // Catch:{ Throwable -> 0x0089 }
            r0.poll()     // Catch:{ Throwable -> 0x0089 }
        L_0x007d:
            int r0 = r3 + 1
            r3 = r0
            goto L_0x0069
        L_0x0081:
            int r3 = r1 + r0
            if (r15 >= r3) goto L_0x016b
            int r0 = r15 - r1
            r15 = r1
            goto L_0x005f
        L_0x0089:
            r0 = move-exception
            java.lang.String r8 = "[UploadManager] Failed to add upload task to temp urgent queue: %s"
            r9 = 1
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch:{ all -> 0x0057 }
            r10 = 0
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0057 }
            r9[r10] = r0     // Catch:{ all -> 0x0057 }
            com.tencent.bugly.proguard.C2014w.m2119e(r8, r9)     // Catch:{ all -> 0x0057 }
            goto L_0x007d
        L_0x009a:
            r3 = r2
        L_0x009b:
            if (r3 >= r1) goto L_0x00c4
            java.util.concurrent.LinkedBlockingQueue<java.lang.Runnable> r0 = r14.f1649g     // Catch:{ all -> 0x0057 }
            java.lang.Object r0 = r0.peek()     // Catch:{ all -> 0x0057 }
            java.lang.Runnable r0 = (java.lang.Runnable) r0     // Catch:{ all -> 0x0057 }
            if (r0 == 0) goto L_0x00c4
            r6.put(r0)     // Catch:{ Throwable -> 0x00b3 }
            java.util.concurrent.LinkedBlockingQueue<java.lang.Runnable> r0 = r14.f1649g     // Catch:{ Throwable -> 0x00b3 }
            r0.poll()     // Catch:{ Throwable -> 0x00b3 }
        L_0x00af:
            int r0 = r3 + 1
            r3 = r0
            goto L_0x009b
        L_0x00b3:
            r0 = move-exception
            java.lang.String r8 = "[UploadManager] Failed to add upload task to temp urgent queue: %s"
            r9 = 1
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch:{ all -> 0x0057 }
            r10 = 0
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0057 }
            r9[r10] = r0     // Catch:{ all -> 0x0057 }
            com.tencent.bugly.proguard.C2014w.m2119e(r8, r9)     // Catch:{ all -> 0x0057 }
            goto L_0x00af
        L_0x00c4:
            monitor-exit(r7)     // Catch:{ all -> 0x0057 }
            if (r15 <= 0) goto L_0x00e8
            java.lang.String r0 = "[UploadManager] Execute urgent upload tasks of queue which has %d tasks (pid=%d | tid=%d)"
            java.lang.Object[] r3 = new java.lang.Object[r13]
            java.lang.Integer r7 = java.lang.Integer.valueOf(r15)
            r3[r2] = r7
            int r7 = android.os.Process.myPid()
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r3[r11] = r7
            int r7 = android.os.Process.myTid()
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r3[r12] = r7
            com.tencent.bugly.proguard.C2014w.m2117c(r0, r3)
        L_0x00e8:
            r3 = r2
        L_0x00e9:
            if (r3 >= r15) goto L_0x0139
            java.lang.Object r0 = r5.poll()
            java.lang.Runnable r0 = (java.lang.Runnable) r0
            if (r0 == 0) goto L_0x0139
            java.lang.Object r7 = r14.f1650h
            monitor-enter(r7)
            int r8 = r14.f1661s     // Catch:{ all -> 0x012b }
            if (r8 < r12) goto L_0x0104
            if (r4 == 0) goto L_0x0104
            r4.mo19636a(r0)     // Catch:{ all -> 0x012b }
            monitor-exit(r7)     // Catch:{ all -> 0x012b }
        L_0x0100:
            int r0 = r3 + 1
            r3 = r0
            goto L_0x00e9
        L_0x0104:
            monitor-exit(r7)
            java.lang.String r7 = "[UploadManager] Create and start a new thread to execute a upload task: %s"
            java.lang.Object[] r8 = new java.lang.Object[r11]
            java.lang.String r9 = "BUGLY_ASYNC_UPLOAD"
            r8[r2] = r9
            com.tencent.bugly.proguard.C2014w.m2113a(r7, r8)
            com.tencent.bugly.proguard.t$1 r7 = new com.tencent.bugly.proguard.t$1
            r7.<init>(r0)
            java.lang.String r8 = "BUGLY_ASYNC_UPLOAD"
            java.lang.Thread r7 = com.tencent.bugly.proguard.C2018y.m2150a(r7, r8)
            if (r7 == 0) goto L_0x012e
            java.lang.Object r7 = r14.f1650h
            monitor-enter(r7)
            int r0 = r14.f1661s     // Catch:{ all -> 0x0128 }
            int r0 = r0 + 1
            r14.f1661s = r0     // Catch:{ all -> 0x0128 }
            monitor-exit(r7)     // Catch:{ all -> 0x0128 }
            goto L_0x0100
        L_0x0128:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        L_0x012b:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        L_0x012e:
            java.lang.String r7 = "[UploadManager] Failed to start a thread to execute asynchronous upload task, will try again next time."
            java.lang.Object[] r8 = new java.lang.Object[r2]
            com.tencent.bugly.proguard.C2014w.m2118d(r7, r8)
            r14.m2077a(r0, r11)
            goto L_0x0100
        L_0x0139:
            if (r1 <= 0) goto L_0x015c
            java.lang.String r0 = "[UploadManager] Execute upload tasks of queue which has %d tasks (pid=%d | tid=%d)"
            java.lang.Object[] r3 = new java.lang.Object[r13]
            java.lang.Integer r5 = java.lang.Integer.valueOf(r1)
            r3[r2] = r5
            int r2 = android.os.Process.myPid()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r3[r11] = r2
            int r2 = android.os.Process.myTid()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r3[r12] = r2
            com.tencent.bugly.proguard.C2014w.m2117c(r0, r3)
        L_0x015c:
            if (r4 == 0) goto L_0x000d
            com.tencent.bugly.proguard.t$2 r0 = new com.tencent.bugly.proguard.t$2
            r0.<init>(r14, r1, r6)
            r4.mo19636a(r0)
            goto L_0x000d
        L_0x0168:
            r1 = r0
            goto L_0x0068
        L_0x016b:
            r15 = r1
            goto L_0x005f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2007t.m2080c(int):void");
    }

    /* renamed from: a */
    private boolean m2077a(Runnable runnable, boolean z) {
        if (runnable == null) {
            C2014w.m2113a("[UploadManager] Upload task should not be null", new Object[0]);
            return false;
        }
        try {
            C2014w.m2117c("[UploadManager] Add upload task to queue (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            synchronized (this.f1650h) {
                if (z) {
                    this.f1648f.put(runnable);
                } else {
                    this.f1649g.put(runnable);
                }
            }
            return true;
        } catch (Throwable th) {
            C2014w.m2119e("[UploadManager] Failed to add upload task to queue: %s", th.getMessage());
            return false;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2074a(Runnable runnable, long j) {
        if (runnable == null) {
            C2014w.m2118d("[UploadManager] Upload task should not be null", new Object[0]);
            return;
        }
        C2014w.m2117c("[UploadManager] Execute synchronized upload task (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        Thread a = C2018y.m2150a(runnable, "BUGLY_SYNC_UPLOAD");
        if (a == null) {
            C2014w.m2119e("[UploadManager] Failed to start a thread to execute synchronized upload task, add it to queue.", new Object[0]);
            m2077a(runnable, true);
            return;
        }
        try {
            a.join(j);
        } catch (Throwable th) {
            C2014w.m2119e("[UploadManager] Failed to join upload synchronized task with message: %s. Add it to queue.", th.getMessage());
            m2077a(runnable, true);
            m2080c(0);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x008a, code lost:
        com.tencent.bugly.proguard.C2014w.m2117c("[UploadManager] Initialize security context now (pid=%d | tid=%d)", java.lang.Integer.valueOf(android.os.Process.myPid()), java.lang.Integer.valueOf(android.os.Process.myTid()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00a5, code lost:
        if (r9 == false) goto L_0x00b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00a7, code lost:
        m2074a((java.lang.Runnable) new com.tencent.bugly.proguard.C2007t.C2010a(r6, r6.f1646d, r7, r10), 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00b7, code lost:
        m2077a(r7, r8);
        r0 = new com.tencent.bugly.proguard.C2007t.C2010a(r6, r6.f1646d);
        com.tencent.bugly.proguard.C2014w.m2113a("[UploadManager] Create and start a new thread to execute a task of initializing security context: %s", "BUGLY_ASYNC_UPLOAD");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00d2, code lost:
        if (com.tencent.bugly.proguard.C2018y.m2150a((java.lang.Runnable) r0, "BUGLY_ASYNC_UPLOAD") != null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00d4, code lost:
        com.tencent.bugly.proguard.C2014w.m2118d("[UploadManager] Failed to start a thread to execute task of initializing security context, try to post it into thread pool.", new java.lang.Object[0]);
        r1 = com.tencent.bugly.proguard.C2012v.m2106a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00df, code lost:
        if (r1 == null) goto L_0x00e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00e1, code lost:
        r1.mo19636a(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00e6, code lost:
        com.tencent.bugly.proguard.C2014w.m2119e("[UploadManager] Asynchronous thread pool is unavailable now, try next time.", new java.lang.Object[0]);
        r1 = r6.f1660r;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ef, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        r6.f1659q = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00f3, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        return;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m2075a(java.lang.Runnable r7, boolean r8, boolean r9, long r10) {
        /*
            r6 = this;
            r5 = 2
            r3 = 1
            r4 = 0
            if (r7 != 0) goto L_0x000c
            java.lang.String r0 = "[UploadManager] Upload task should not be null"
            java.lang.Object[] r1 = new java.lang.Object[r4]
            com.tencent.bugly.proguard.C2014w.m2118d(r0, r1)
        L_0x000c:
            java.lang.String r0 = "[UploadManager] Add upload task (pid=%d | tid=%d)"
            java.lang.Object[] r1 = new java.lang.Object[r5]
            int r2 = android.os.Process.myPid()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r1[r4] = r2
            int r2 = android.os.Process.myTid()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r1[r3] = r2
            com.tencent.bugly.proguard.C2014w.m2117c(r0, r1)
            java.lang.String r0 = r6.f1656n
            if (r0 == 0) goto L_0x0077
            boolean r0 = r6.mo19627b()
            if (r0 == 0) goto L_0x0059
            java.lang.String r0 = "[UploadManager] Sucessfully got session ID, try to execute upload task now (pid=%d | tid=%d)"
            java.lang.Object[] r1 = new java.lang.Object[r5]
            int r2 = android.os.Process.myPid()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r1[r4] = r2
            int r2 = android.os.Process.myTid()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r1[r3] = r2
            com.tencent.bugly.proguard.C2014w.m2117c(r0, r1)
            if (r9 == 0) goto L_0x0052
            r6.m2074a(r7, r10)
        L_0x0051:
            return
        L_0x0052:
            r6.m2077a(r7, r8)
            r6.m2080c(r4)
            goto L_0x0051
        L_0x0059:
            java.lang.String r0 = "[UploadManager] Session ID is expired, drop it (pid=%d | tid=%d)"
            java.lang.Object[] r1 = new java.lang.Object[r5]
            int r2 = android.os.Process.myPid()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r1[r4] = r2
            int r2 = android.os.Process.myTid()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r1[r3] = r2
            com.tencent.bugly.proguard.C2014w.m2113a(r0, r1)
            r6.mo19626b(r4)
        L_0x0077:
            java.lang.Object r1 = r6.f1660r
            monitor-enter(r1)
            boolean r0 = r6.f1659q     // Catch:{ all -> 0x0083 }
            if (r0 == 0) goto L_0x0086
            r6.m2077a(r7, r8)     // Catch:{ all -> 0x0083 }
            monitor-exit(r1)     // Catch:{ all -> 0x0083 }
            goto L_0x0051
        L_0x0083:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        L_0x0086:
            r0 = 1
            r6.f1659q = r0     // Catch:{ all -> 0x0083 }
            monitor-exit(r1)     // Catch:{ all -> 0x0083 }
            java.lang.String r0 = "[UploadManager] Initialize security context now (pid=%d | tid=%d)"
            java.lang.Object[] r1 = new java.lang.Object[r5]
            int r2 = android.os.Process.myPid()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r1[r4] = r2
            int r2 = android.os.Process.myTid()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r1[r3] = r2
            com.tencent.bugly.proguard.C2014w.m2117c(r0, r1)
            if (r9 == 0) goto L_0x00b7
            com.tencent.bugly.proguard.t$a r0 = new com.tencent.bugly.proguard.t$a
            android.content.Context r2 = r6.f1646d
            r1 = r6
            r3 = r7
            r4 = r10
            r0.<init>(r2, r3, r4)
            r2 = 0
            r6.m2074a(r0, r2)
            goto L_0x0051
        L_0x00b7:
            r6.m2077a(r7, r8)
            com.tencent.bugly.proguard.t$a r0 = new com.tencent.bugly.proguard.t$a
            android.content.Context r1 = r6.f1646d
            r0.<init>(r1)
            java.lang.String r1 = "[UploadManager] Create and start a new thread to execute a task of initializing security context: %s"
            java.lang.Object[] r2 = new java.lang.Object[r3]
            java.lang.String r3 = "BUGLY_ASYNC_UPLOAD"
            r2[r4] = r3
            com.tencent.bugly.proguard.C2014w.m2113a(r1, r2)
            java.lang.String r1 = "BUGLY_ASYNC_UPLOAD"
            java.lang.Thread r1 = com.tencent.bugly.proguard.C2018y.m2150a(r0, r1)
            if (r1 != 0) goto L_0x0051
            java.lang.String r1 = "[UploadManager] Failed to start a thread to execute task of initializing security context, try to post it into thread pool."
            java.lang.Object[] r2 = new java.lang.Object[r4]
            com.tencent.bugly.proguard.C2014w.m2118d(r1, r2)
            com.tencent.bugly.proguard.v r1 = com.tencent.bugly.proguard.C2012v.m2106a()
            if (r1 == 0) goto L_0x00e6
            r1.mo19636a(r0)
            goto L_0x0051
        L_0x00e6:
            java.lang.String r0 = "[UploadManager] Asynchronous thread pool is unavailable now, try next time."
            java.lang.Object[] r1 = new java.lang.Object[r4]
            com.tencent.bugly.proguard.C2014w.m2119e(r0, r1)
            java.lang.Object r1 = r6.f1660r
            monitor-enter(r1)
            r0 = 0
            r6.f1659q = r0     // Catch:{ all -> 0x00f6 }
            monitor-exit(r1)     // Catch:{ all -> 0x00f6 }
            goto L_0x0051
        L_0x00f6:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2007t.m2075a(java.lang.Runnable, boolean, boolean, long):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004b, code lost:
        if (r10 == null) goto L_0x012f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004d, code lost:
        com.tencent.bugly.proguard.C2014w.m2117c("[UploadManager] Record security context (pid=%d | tid=%d)", java.lang.Integer.valueOf(android.os.Process.myPid()), java.lang.Integer.valueOf(android.os.Process.myTid()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        r3 = r10.f1530g;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x006a, code lost:
        if (r3 == null) goto L_0x0102;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0072, code lost:
        if (r3.containsKey("S1") == false) goto L_0x0102;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x007a, code lost:
        if (r3.containsKey("S2") == false) goto L_0x0102;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x007c, code lost:
        r8.f1653k = r10.f1528e - java.lang.System.currentTimeMillis();
        com.tencent.bugly.proguard.C2014w.m2117c("[UploadManager] Time lag of server is: %d", java.lang.Long.valueOf(r8.f1653k));
        r8.f1656n = (java.lang.String) r3.get("S1");
        com.tencent.bugly.proguard.C2014w.m2117c("[UploadManager] Session ID from server is: %s", r8.f1656n);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00b3, code lost:
        if (r8.f1656n.length() <= 0) goto L_0x0126;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        r8.f1657o = java.lang.Long.parseLong((java.lang.String) r3.get("S2"));
        com.tencent.bugly.proguard.C2014w.m2117c("[UploadManager] Session expired time from server is: %d(%s)", java.lang.Long.valueOf(r8.f1657o), new java.util.Date(r8.f1657o).toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00e8, code lost:
        if (r8.f1657o >= 1000) goto L_0x00f7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00ea, code lost:
        com.tencent.bugly.proguard.C2014w.m2118d("[UploadManager] Session expired time from server is less than 1 second, will set to default value", new java.lang.Object[0]);
        r8.f1657o = 259200000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        com.tencent.bugly.proguard.C2014w.m2118d("[UploadManager] Session expired time is invalid, will set to default value", new java.lang.Object[0]);
        r8.f1657o = 259200000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0118, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0119, code lost:
        com.tencent.bugly.proguard.C2014w.m2114a(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0126, code lost:
        com.tencent.bugly.proguard.C2014w.m2117c("[UploadManager] Session ID from server is invalid, try next time", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x012f, code lost:
        com.tencent.bugly.proguard.C2014w.m2117c("[UploadManager] Fail to init security context and clear local info (pid=%d | tid=%d)", java.lang.Integer.valueOf(android.os.Process.myPid()), java.lang.Integer.valueOf(android.os.Process.myTid()));
        mo19626b(false);
     */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x002b A[SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo19622a(int r9, com.tencent.bugly.proguard.C1980am r10) {
        /*
            r8 = this;
            r4 = 2
            r1 = 1
            r2 = 0
            boolean r0 = r8.f1644a
            if (r0 != 0) goto L_0x0008
        L_0x0007:
            return
        L_0x0008:
            if (r9 != r4) goto L_0x003e
            java.lang.String r0 = "[UploadManager] Session ID is invalid, will clear security context (pid=%d | tid=%d)"
            java.lang.Object[] r3 = new java.lang.Object[r4]
            int r4 = android.os.Process.myPid()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3[r2] = r4
            int r2 = android.os.Process.myTid()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r3[r1] = r2
            com.tencent.bugly.proguard.C2014w.m2117c(r0, r3)
            r8.mo19626b(r1)
        L_0x0028:
            java.lang.Object r1 = r8.f1660r
            monitor-enter(r1)
            boolean r0 = r8.f1659q     // Catch:{ all -> 0x003b }
            if (r0 == 0) goto L_0x0039
            r0 = 0
            r8.f1659q = r0     // Catch:{ all -> 0x003b }
            android.content.Context r0 = r8.f1646d     // Catch:{ all -> 0x003b }
            java.lang.String r2 = "security_info"
            com.tencent.bugly.proguard.C2018y.m2173b(r0, r2)     // Catch:{ all -> 0x003b }
        L_0x0039:
            monitor-exit(r1)     // Catch:{ all -> 0x003b }
            goto L_0x0007
        L_0x003b:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        L_0x003e:
            java.lang.Object r3 = r8.f1660r
            monitor-enter(r3)
            boolean r0 = r8.f1659q     // Catch:{ all -> 0x0047 }
            if (r0 != 0) goto L_0x004a
            monitor-exit(r3)     // Catch:{ all -> 0x0047 }
            goto L_0x0007
        L_0x0047:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        L_0x004a:
            monitor-exit(r3)
            if (r10 == 0) goto L_0x012f
            java.lang.String r0 = "[UploadManager] Record security context (pid=%d | tid=%d)"
            java.lang.Object[] r3 = new java.lang.Object[r4]
            int r4 = android.os.Process.myPid()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3[r2] = r4
            int r4 = android.os.Process.myTid()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3[r1] = r4
            com.tencent.bugly.proguard.C2014w.m2117c(r0, r3)
            java.util.Map<java.lang.String, java.lang.String> r3 = r10.f1530g     // Catch:{ Throwable -> 0x0118 }
            if (r3 == 0) goto L_0x0102
            java.lang.String r0 = "S1"
            boolean r0 = r3.containsKey(r0)     // Catch:{ Throwable -> 0x0118 }
            if (r0 == 0) goto L_0x0102
            java.lang.String r0 = "S2"
            boolean r0 = r3.containsKey(r0)     // Catch:{ Throwable -> 0x0118 }
            if (r0 == 0) goto L_0x0102
            long r4 = r10.f1528e     // Catch:{ Throwable -> 0x0118 }
            long r6 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0118 }
            long r4 = r4 - r6
            r8.f1653k = r4     // Catch:{ Throwable -> 0x0118 }
            java.lang.String r0 = "[UploadManager] Time lag of server is: %d"
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x0118 }
            r5 = 0
            long r6 = r8.f1653k     // Catch:{ Throwable -> 0x0118 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ Throwable -> 0x0118 }
            r4[r5] = r6     // Catch:{ Throwable -> 0x0118 }
            com.tencent.bugly.proguard.C2014w.m2117c(r0, r4)     // Catch:{ Throwable -> 0x0118 }
            java.lang.String r0 = "S1"
            java.lang.Object r0 = r3.get(r0)     // Catch:{ Throwable -> 0x0118 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x0118 }
            r8.f1656n = r0     // Catch:{ Throwable -> 0x0118 }
            java.lang.String r0 = "[UploadManager] Session ID from server is: %s"
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x0118 }
            r5 = 0
            java.lang.String r6 = r8.f1656n     // Catch:{ Throwable -> 0x0118 }
            r4[r5] = r6     // Catch:{ Throwable -> 0x0118 }
            com.tencent.bugly.proguard.C2014w.m2117c(r0, r4)     // Catch:{ Throwable -> 0x0118 }
            java.lang.String r0 = r8.f1656n     // Catch:{ Throwable -> 0x0118 }
            int r0 = r0.length()     // Catch:{ Throwable -> 0x0118 }
            if (r0 <= 0) goto L_0x0126
            java.lang.String r0 = "S2"
            java.lang.Object r0 = r3.get(r0)     // Catch:{ NumberFormatException -> 0x0109 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ NumberFormatException -> 0x0109 }
            long r4 = java.lang.Long.parseLong(r0)     // Catch:{ NumberFormatException -> 0x0109 }
            r8.f1657o = r4     // Catch:{ NumberFormatException -> 0x0109 }
            java.lang.String r0 = "[UploadManager] Session expired time from server is: %d(%s)"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ NumberFormatException -> 0x0109 }
            r4 = 0
            long r6 = r8.f1657o     // Catch:{ NumberFormatException -> 0x0109 }
            java.lang.Long r5 = java.lang.Long.valueOf(r6)     // Catch:{ NumberFormatException -> 0x0109 }
            r3[r4] = r5     // Catch:{ NumberFormatException -> 0x0109 }
            r4 = 1
            java.util.Date r5 = new java.util.Date     // Catch:{ NumberFormatException -> 0x0109 }
            long r6 = r8.f1657o     // Catch:{ NumberFormatException -> 0x0109 }
            r5.<init>(r6)     // Catch:{ NumberFormatException -> 0x0109 }
            java.lang.String r5 = r5.toString()     // Catch:{ NumberFormatException -> 0x0109 }
            r3[r4] = r5     // Catch:{ NumberFormatException -> 0x0109 }
            com.tencent.bugly.proguard.C2014w.m2117c(r0, r3)     // Catch:{ NumberFormatException -> 0x0109 }
            long r4 = r8.f1657o     // Catch:{ NumberFormatException -> 0x0109 }
            r6 = 1000(0x3e8, double:4.94E-321)
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 >= 0) goto L_0x00f7
            java.lang.String r0 = "[UploadManager] Session expired time from server is less than 1 second, will set to default value"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ NumberFormatException -> 0x0109 }
            com.tencent.bugly.proguard.C2014w.m2118d(r0, r3)     // Catch:{ NumberFormatException -> 0x0109 }
            r4 = 259200000(0xf731400, double:1.280618154E-315)
            r8.f1657o = r4     // Catch:{ NumberFormatException -> 0x0109 }
        L_0x00f7:
            boolean r0 = r8.m2084d()     // Catch:{ Throwable -> 0x0118 }
            if (r0 == 0) goto L_0x011d
            r1 = r2
        L_0x00fe:
            r0 = 0
            r8.m2080c(r0)     // Catch:{ Throwable -> 0x0118 }
        L_0x0102:
            if (r1 == 0) goto L_0x0028
            r8.mo19626b(r2)
            goto L_0x0028
        L_0x0109:
            r0 = move-exception
            java.lang.String r0 = "[UploadManager] Session expired time is invalid, will set to default value"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0118 }
            com.tencent.bugly.proguard.C2014w.m2118d(r0, r3)     // Catch:{ Throwable -> 0x0118 }
            r4 = 259200000(0xf731400, double:1.280618154E-315)
            r8.f1657o = r4     // Catch:{ Throwable -> 0x0118 }
            goto L_0x00f7
        L_0x0118:
            r0 = move-exception
            com.tencent.bugly.proguard.C2014w.m2114a(r0)
            goto L_0x0102
        L_0x011d:
            java.lang.String r0 = "[UploadManager] Failed to record database"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0118 }
            com.tencent.bugly.proguard.C2014w.m2117c(r0, r3)     // Catch:{ Throwable -> 0x0118 }
            goto L_0x00fe
        L_0x0126:
            java.lang.String r0 = "[UploadManager] Session ID from server is invalid, try next time"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0118 }
            com.tencent.bugly.proguard.C2014w.m2117c(r0, r3)     // Catch:{ Throwable -> 0x0118 }
            goto L_0x0102
        L_0x012f:
            java.lang.String r0 = "[UploadManager] Fail to init security context and clear local info (pid=%d | tid=%d)"
            java.lang.Object[] r3 = new java.lang.Object[r4]
            int r4 = android.os.Process.myPid()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3[r2] = r4
            int r4 = android.os.Process.myTid()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3[r1] = r4
            com.tencent.bugly.proguard.C2014w.m2117c(r0, r3)
            r8.mo19626b(r2)
            goto L_0x0028
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2007t.mo19622a(int, com.tencent.bugly.proguard.am):void");
    }

    /* renamed from: a */
    public final byte[] mo19625a(byte[] bArr) {
        if (this.f1654l != null && (this.f1654l.length << 3) == 128) {
            return C2018y.m2160a(1, bArr, this.f1654l);
        }
        C2014w.m2118d("[UploadManager] AES key is invalid (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        return null;
    }

    /* renamed from: b */
    public final byte[] mo19629b(byte[] bArr) {
        if (this.f1654l != null && (this.f1654l.length << 3) == 128) {
            return C2018y.m2160a(2, bArr, this.f1654l);
        }
        C2014w.m2118d("[UploadManager] AES key is invalid (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        return null;
    }

    /* renamed from: a */
    public final boolean mo19624a(Map<String, String> map) {
        if (map == null) {
            return false;
        }
        C2014w.m2117c("[UploadManager] Integrate security to HTTP headers (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        if (this.f1656n != null) {
            map.put("secureSessionId", this.f1656n);
            return true;
        } else if (this.f1654l == null || (this.f1654l.length << 3) != 128) {
            C2014w.m2118d("[UploadManager] AES key is invalid", new Object[0]);
            return false;
        } else {
            if (this.f1652j == null) {
                this.f1652j = Base64.decode(this.f1651i, 0);
                if (this.f1652j == null) {
                    C2014w.m2118d("[UploadManager] Failed to decode RSA public key", new Object[0]);
                    return false;
                }
            }
            byte[] b = C2018y.m2174b(1, this.f1654l, this.f1652j);
            if (b == null) {
                C2014w.m2118d("[UploadManager] Failed to encrypt AES key", new Object[0]);
                return false;
            }
            String encodeToString = Base64.encodeToString(b, 0);
            if (encodeToString == null) {
                C2014w.m2118d("[UploadManager] Failed to encode AES key", new Object[0]);
                return false;
            }
            map.put("raKey", encodeToString);
            return true;
        }
    }
}
