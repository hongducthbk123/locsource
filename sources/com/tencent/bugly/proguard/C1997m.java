package com.tencent.bugly.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.tencent.bugly.crashreport.common.info.C1938a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.tencent.bugly.proguard.m */
/* compiled from: BUGLY */
public final class C1997m {

    /* renamed from: a */
    public static final long f1598a = System.currentTimeMillis();

    /* renamed from: b */
    private static C1997m f1599b = null;

    /* renamed from: c */
    private Context f1600c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public String f1601d = C1938a.m1668b().f1202d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public Map<Integer, Map<String, C1996l>> f1602e = new HashMap();
    /* access modifiers changed from: private */

    /* renamed from: f */
    public SharedPreferences f1603f;

    private C1997m(Context context) {
        this.f1600c = context;
        this.f1603f = context.getSharedPreferences("crashrecord", 0);
    }

    /* renamed from: a */
    public static synchronized C1997m m2014a(Context context) {
        C1997m mVar;
        synchronized (C1997m.class) {
            if (f1599b == null) {
                f1599b = new C1997m(context);
            }
            mVar = f1599b;
        }
        return mVar;
    }

    /* renamed from: a */
    public static synchronized C1997m m2013a() {
        C1997m mVar;
        synchronized (C1997m.class) {
            mVar = f1599b;
        }
        return mVar;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public synchronized boolean m2020b(int i) {
        boolean z;
        try {
            List<C1996l> c = m2023c(i);
            if (c == null) {
                z = false;
            } else {
                long currentTimeMillis = System.currentTimeMillis();
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                for (C1996l lVar : c) {
                    if (lVar.f1592b != null && lVar.f1592b.equalsIgnoreCase(this.f1601d) && lVar.f1594d > 0) {
                        arrayList.add(lVar);
                    }
                    if (lVar.f1593c + 86400000 < currentTimeMillis) {
                        arrayList2.add(lVar);
                    }
                }
                Collections.sort(arrayList);
                if (arrayList.size() < 2) {
                    c.removeAll(arrayList2);
                    m2017a(i, (T) c);
                    z = false;
                } else if (arrayList.size() <= 0 || ((C1996l) arrayList.get(arrayList.size() - 1)).f1593c + 86400000 >= currentTimeMillis) {
                    z = true;
                } else {
                    c.clear();
                    m2017a(i, (T) c);
                    z = false;
                }
            }
        } catch (Exception e) {
            C2014w.m2119e("isFrequentCrash failed", new Object[0]);
            z = false;
        }
        return z;
    }

    /* renamed from: a */
    public final synchronized void mo19590a(int i, final int i2) {
        C2012v.m2106a().mo19636a(new Runnable(1004) {
            public final void run() {
                List<C1996l> list;
                C1996l lVar;
                try {
                    if (!TextUtils.isEmpty(C1997m.this.f1601d)) {
                        List a = C1997m.this.m2023c(1004);
                        if (a == null) {
                            list = new ArrayList<>();
                        } else {
                            list = a;
                        }
                        if (C1997m.this.f1602e.get(Integer.valueOf(1004)) == null) {
                            C1997m.this.f1602e.put(Integer.valueOf(1004), new HashMap());
                        }
                        if (((Map) C1997m.this.f1602e.get(Integer.valueOf(1004))).get(C1997m.this.f1601d) == null) {
                            C1996l lVar2 = new C1996l();
                            lVar2.f1591a = (long) 1004;
                            lVar2.f1597g = C1997m.f1598a;
                            lVar2.f1592b = C1997m.this.f1601d;
                            lVar2.f1596f = C1938a.m1668b().f1208j;
                            C1938a.m1668b().getClass();
                            lVar2.f1595e = "2.4.0";
                            lVar2.f1593c = System.currentTimeMillis();
                            lVar2.f1594d = i2;
                            ((Map) C1997m.this.f1602e.get(Integer.valueOf(1004))).put(C1997m.this.f1601d, lVar2);
                            lVar = lVar2;
                        } else {
                            C1996l lVar3 = (C1996l) ((Map) C1997m.this.f1602e.get(Integer.valueOf(1004))).get(C1997m.this.f1601d);
                            lVar3.f1594d = i2;
                            lVar = lVar3;
                        }
                        ArrayList arrayList = new ArrayList();
                        boolean z = false;
                        for (C1996l lVar4 : list) {
                            if (lVar4.f1597g == lVar.f1597g && lVar4.f1592b != null && lVar4.f1592b.equalsIgnoreCase(lVar.f1592b)) {
                                z = true;
                                lVar4.f1594d = lVar.f1594d;
                            }
                            if ((lVar4.f1595e != null && !lVar4.f1595e.equalsIgnoreCase(lVar.f1595e)) || ((lVar4.f1596f != null && !lVar4.f1596f.equalsIgnoreCase(lVar.f1596f)) || lVar4.f1594d <= 0)) {
                                arrayList.add(lVar4);
                            }
                        }
                        list.removeAll(arrayList);
                        if (!z) {
                            list.add(lVar);
                        }
                        C1997m.this.m2017a(1004, list);
                    }
                } catch (Exception e) {
                    C2014w.m2119e("saveCrashRecord failed", new Object[0]);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0051 A[SYNTHETIC, Splitter:B:25:0x0051] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0064 A[SYNTHETIC, Splitter:B:36:0x0064] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x006c A[Catch:{ Exception -> 0x003a }] */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized <T extends java.util.List<?>> T m2023c(int r7) {
        /*
            r6 = this;
            r1 = 0
            monitor-enter(r6)
            java.io.File r0 = new java.io.File     // Catch:{ Exception -> 0x003a }
            android.content.Context r2 = r6.f1600c     // Catch:{ Exception -> 0x003a }
            java.lang.String r3 = "crashrecord"
            r4 = 0
            java.io.File r2 = r2.getDir(r3, r4)     // Catch:{ Exception -> 0x003a }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x003a }
            r3.<init>()     // Catch:{ Exception -> 0x003a }
            java.lang.StringBuilder r3 = r3.append(r7)     // Catch:{ Exception -> 0x003a }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x003a }
            r0.<init>(r2, r3)     // Catch:{ Exception -> 0x003a }
            boolean r2 = r0.exists()     // Catch:{ Exception -> 0x003a }
            if (r2 != 0) goto L_0x0026
            r0 = r1
        L_0x0024:
            monitor-exit(r6)
            return r0
        L_0x0026:
            java.io.ObjectInputStream r2 = new java.io.ObjectInputStream     // Catch:{ IOException -> 0x0045, ClassNotFoundException -> 0x0058, all -> 0x0068 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0045, ClassNotFoundException -> 0x0058, all -> 0x0068 }
            r3.<init>(r0)     // Catch:{ IOException -> 0x0045, ClassNotFoundException -> 0x0058, all -> 0x0068 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x0045, ClassNotFoundException -> 0x0058, all -> 0x0068 }
            java.lang.Object r0 = r2.readObject()     // Catch:{ IOException -> 0x0079, ClassNotFoundException -> 0x0077 }
            java.util.List r0 = (java.util.List) r0     // Catch:{ IOException -> 0x0079, ClassNotFoundException -> 0x0077 }
            r2.close()     // Catch:{ Exception -> 0x003a }
            goto L_0x0024
        L_0x003a:
            r0 = move-exception
            java.lang.String r0 = "readCrashRecord error"
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0055 }
            com.tencent.bugly.proguard.C2014w.m2119e(r0, r2)     // Catch:{ all -> 0x0055 }
        L_0x0043:
            r0 = r1
            goto L_0x0024
        L_0x0045:
            r0 = move-exception
            r0 = r1
        L_0x0047:
            java.lang.String r2 = "open record file error"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0072 }
            com.tencent.bugly.proguard.C2014w.m2113a(r2, r3)     // Catch:{ all -> 0x0072 }
            if (r0 == 0) goto L_0x0043
            r0.close()     // Catch:{ Exception -> 0x003a }
            goto L_0x0043
        L_0x0055:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        L_0x0058:
            r0 = move-exception
            r2 = r1
        L_0x005a:
            java.lang.String r0 = "get object error"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0070 }
            com.tencent.bugly.proguard.C2014w.m2113a(r0, r3)     // Catch:{ all -> 0x0070 }
            if (r2 == 0) goto L_0x0043
            r2.close()     // Catch:{ Exception -> 0x003a }
            goto L_0x0043
        L_0x0068:
            r0 = move-exception
            r2 = r1
        L_0x006a:
            if (r2 == 0) goto L_0x006f
            r2.close()     // Catch:{ Exception -> 0x003a }
        L_0x006f:
            throw r0     // Catch:{ Exception -> 0x003a }
        L_0x0070:
            r0 = move-exception
            goto L_0x006a
        L_0x0072:
            r2 = move-exception
            r5 = r2
            r2 = r0
            r0 = r5
            goto L_0x006a
        L_0x0077:
            r0 = move-exception
            goto L_0x005a
        L_0x0079:
            r0 = move-exception
            r0 = r2
            goto L_0x0047
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C1997m.m2023c(int):java.util.List");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004f A[SYNTHETIC, Splitter:B:25:0x004f] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0057 A[Catch:{ Exception -> 0x0032 }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized <T extends java.util.List<?>> void m2017a(int r5, T r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            if (r6 != 0) goto L_0x0005
        L_0x0003:
            monitor-exit(r4)
            return
        L_0x0005:
            java.io.File r0 = new java.io.File     // Catch:{ Exception -> 0x0032 }
            android.content.Context r1 = r4.f1600c     // Catch:{ Exception -> 0x0032 }
            java.lang.String r2 = "crashrecord"
            r3 = 0
            java.io.File r1 = r1.getDir(r2, r3)     // Catch:{ Exception -> 0x0032 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0032 }
            r2.<init>()     // Catch:{ Exception -> 0x0032 }
            java.lang.StringBuilder r2 = r2.append(r5)     // Catch:{ Exception -> 0x0032 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0032 }
            r0.<init>(r1, r2)     // Catch:{ Exception -> 0x0032 }
            r2 = 0
            java.io.ObjectOutputStream r1 = new java.io.ObjectOutputStream     // Catch:{ IOException -> 0x0040, all -> 0x0053 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0040, all -> 0x0053 }
            r3.<init>(r0)     // Catch:{ IOException -> 0x0040, all -> 0x0053 }
            r1.<init>(r3)     // Catch:{ IOException -> 0x0040, all -> 0x0053 }
            r1.writeObject(r6)     // Catch:{ IOException -> 0x005d }
            r1.close()     // Catch:{ Exception -> 0x0032 }
            goto L_0x0003
        L_0x0032:
            r0 = move-exception
            java.lang.String r0 = "writeCrashRecord error"
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x003d }
            com.tencent.bugly.proguard.C2014w.m2119e(r0, r1)     // Catch:{ all -> 0x003d }
            goto L_0x0003
        L_0x003d:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        L_0x0040:
            r0 = move-exception
            r1 = r2
        L_0x0042:
            r0.printStackTrace()     // Catch:{ all -> 0x005b }
            java.lang.String r0 = "open record file error"
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x005b }
            com.tencent.bugly.proguard.C2014w.m2113a(r0, r2)     // Catch:{ all -> 0x005b }
            if (r1 == 0) goto L_0x0003
            r1.close()     // Catch:{ Exception -> 0x0032 }
            goto L_0x0003
        L_0x0053:
            r0 = move-exception
            r1 = r2
        L_0x0055:
            if (r1 == 0) goto L_0x005a
            r1.close()     // Catch:{ Exception -> 0x0032 }
        L_0x005a:
            throw r0     // Catch:{ Exception -> 0x0032 }
        L_0x005b:
            r0 = move-exception
            goto L_0x0055
        L_0x005d:
            r0 = move-exception
            goto L_0x0042
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C1997m.m2017a(int, java.util.List):void");
    }

    /* renamed from: a */
    public final synchronized boolean mo19591a(final int i) {
        boolean z = true;
        synchronized (this) {
            try {
                z = this.f1603f.getBoolean(i + "_" + this.f1601d, true);
                C2012v.m2106a().mo19636a(new Runnable() {
                    public final void run() {
                        C1997m.this.f1603f.edit().putBoolean(i + "_" + C1997m.this.f1601d, !C1997m.this.m2020b(i)).commit();
                    }
                });
            } catch (Exception e) {
                C2014w.m2119e("canInit error", new Object[0]);
            }
        }
        return z;
    }
}
