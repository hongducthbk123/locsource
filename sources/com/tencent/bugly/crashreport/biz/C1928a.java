package com.tencent.bugly.crashreport.biz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcelable;
import com.android.vending.expansion.zipfile.APEZProvider;
import com.google.android.vending.expansion.downloader.Constants;
import com.tencent.bugly.crashreport.common.info.C1938a;
import com.tencent.bugly.crashreport.common.strategy.C1941a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.C1967a;
import com.tencent.bugly.proguard.C1979al;
import com.tencent.bugly.proguard.C1984aq;
import com.tencent.bugly.proguard.C1994j;
import com.tencent.bugly.proguard.C2000n;
import com.tencent.bugly.proguard.C2001o;
import com.tencent.bugly.proguard.C2006s;
import com.tencent.bugly.proguard.C2007t;
import com.tencent.bugly.proguard.C2012v;
import com.tencent.bugly.proguard.C2014w;
import com.tencent.bugly.proguard.C2018y;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.tencent.bugly.crashreport.biz.a */
/* compiled from: BUGLY */
public final class C1928a {

    /* renamed from: a */
    private Context f1123a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public long f1124b;

    /* renamed from: c */
    private int f1125c;

    /* renamed from: d */
    private boolean f1126d = true;

    /* renamed from: com.tencent.bugly.crashreport.biz.a$a */
    /* compiled from: BUGLY */
    class C1931a implements Runnable {

        /* renamed from: a */
        private boolean f1130a;

        /* renamed from: b */
        private UserInfoBean f1131b;

        public C1931a(UserInfoBean userInfoBean, boolean z) {
            this.f1131b = userInfoBean;
            this.f1130a = z;
        }

        public final void run() {
            try {
                if (this.f1131b != null) {
                    UserInfoBean userInfoBean = this.f1131b;
                    if (userInfoBean != null) {
                        C1938a b = C1938a.m1668b();
                        if (b != null) {
                            userInfoBean.f1113j = b.mo19402e();
                        }
                    }
                    C2014w.m2117c("[UserInfo] Record user info.", new Object[0]);
                    C1928a.m1626a(C1928a.this, this.f1131b, false);
                }
                if (this.f1130a) {
                    C1928a aVar = C1928a.this;
                    C2012v a = C2012v.m2106a();
                    if (a != null) {
                        a.mo19636a(new Runnable() {
                            public final void run() {
                                try {
                                    C1928a.this.m1629c();
                                } catch (Throwable th) {
                                    C2014w.m2114a(th);
                                }
                            }
                        });
                    }
                }
            } catch (Throwable th) {
                if (!C2014w.m2114a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* renamed from: com.tencent.bugly.crashreport.biz.a$b */
    /* compiled from: BUGLY */
    class C1932b implements Runnable {
        C1932b() {
        }

        public final void run() {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis < C1928a.this.f1124b) {
                C2012v.m2106a().mo19637a(new C1932b(), (C1928a.this.f1124b - currentTimeMillis) + Constants.ACTIVE_THREAD_WATCHDOG);
                return;
            }
            C1928a.this.mo19355a(3, false, 0);
            C1928a.this.mo19354a();
        }
    }

    /* renamed from: com.tencent.bugly.crashreport.biz.a$c */
    /* compiled from: BUGLY */
    class C1933c implements Runnable {

        /* renamed from: a */
        private long f1134a = 21600000;

        public C1933c(long j) {
            this.f1134a = j;
        }

        public final void run() {
            C1928a aVar = C1928a.this;
            C2012v a = C2012v.m2106a();
            if (a != null) {
                a.mo19636a(new Runnable() {
                    public final void run() {
                        try {
                            C1928a.this.m1629c();
                        } catch (Throwable th) {
                            C2014w.m2114a(th);
                        }
                    }
                });
            }
            C1928a aVar2 = C1928a.this;
            long j = this.f1134a;
            C2012v.m2106a().mo19637a(new C1933c(j), j);
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m1626a(C1928a aVar, UserInfoBean userInfoBean, boolean z) {
        if (userInfoBean != null) {
            if (!z && userInfoBean.f1105b != 1) {
                List a = aVar.mo19353a(C1938a.m1667a(aVar.f1123a).f1202d);
                if (a != null && a.size() >= 20) {
                    C2014w.m2113a("[UserInfo] There are too many user info in local: %d", Integer.valueOf(a.size()));
                    return;
                }
            }
            long a2 = C2001o.m2035a().mo19598a("t_ui", m1623a(userInfoBean), (C2000n) null, true);
            if (a2 >= 0) {
                C2014w.m2117c("[Database] insert %s success with ID: %d", "t_ui", Long.valueOf(a2));
                userInfoBean.f1104a = a2;
            }
        }
    }

    public C1928a(Context context, boolean z) {
        this.f1123a = context;
        this.f1126d = z;
    }

    /* renamed from: a */
    public final void mo19355a(int i, boolean z, long j) {
        int i2 = 1;
        C1941a a = C1941a.m1752a();
        if (a == null || a.mo19435c().f1234h || i == 1 || i == 3) {
            if (i == 1 || i == 3) {
                this.f1125c++;
            }
            C1938a a2 = C1938a.m1667a(this.f1123a);
            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.f1105b = i;
            userInfoBean.f1106c = a2.f1202d;
            userInfoBean.f1107d = a2.mo19406g();
            userInfoBean.f1108e = System.currentTimeMillis();
            userInfoBean.f1109f = -1;
            userInfoBean.f1117n = a2.f1208j;
            if (i != 1) {
                i2 = 0;
            }
            userInfoBean.f1118o = i2;
            userInfoBean.f1115l = a2.mo19394a();
            userInfoBean.f1116m = a2.f1214p;
            userInfoBean.f1110g = a2.f1215q;
            userInfoBean.f1111h = a2.f1216r;
            userInfoBean.f1112i = a2.f1217s;
            userInfoBean.f1114k = a2.f1218t;
            userInfoBean.f1121r = a2.mo19426z();
            userInfoBean.f1122s = a2.mo19379E();
            userInfoBean.f1119p = a2.mo19380F();
            userInfoBean.f1120q = a2.mo19381G();
            C2012v.m2106a().mo19637a(new C1931a(userInfoBean, z), 0);
            return;
        }
        C2014w.m2119e("UserInfo is disable", new Object[0]);
    }

    /* renamed from: a */
    public final void mo19354a() {
        this.f1124b = C2018y.m2166b() + 86400000;
        C2012v.m2106a().mo19637a(new C1932b(), (this.f1124b - System.currentTimeMillis()) + Constants.ACTIVE_THREAD_WATCHDOG);
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public synchronized void m1629c() {
        final List arrayList;
        boolean z;
        int i;
        boolean z2 = false;
        synchronized (this) {
            if (this.f1126d) {
                C2007t a = C2007t.m2069a();
                if (a != null) {
                    C1941a a2 = C1941a.m1752a();
                    if (a2 != null && (!a2.mo19434b() || a.mo19628b(1001))) {
                        String str = C1938a.m1667a(this.f1123a).f1202d;
                        ArrayList arrayList2 = new ArrayList();
                        List a3 = mo19353a(str);
                        if (a3 != null) {
                            int size = a3.size() - 20;
                            if (size > 0) {
                                for (int i2 = 0; i2 < a3.size() - 1; i2++) {
                                    for (int i3 = i2 + 1; i3 < a3.size(); i3++) {
                                        if (((UserInfoBean) a3.get(i2)).f1108e > ((UserInfoBean) a3.get(i3)).f1108e) {
                                            UserInfoBean userInfoBean = (UserInfoBean) a3.get(i2);
                                            a3.set(i2, a3.get(i3));
                                            a3.set(i3, userInfoBean);
                                        }
                                    }
                                }
                                for (int i4 = 0; i4 < size; i4++) {
                                    arrayList2.add(a3.get(i4));
                                }
                            }
                            Iterator it = a3.iterator();
                            int i5 = 0;
                            while (it.hasNext()) {
                                UserInfoBean userInfoBean2 = (UserInfoBean) it.next();
                                if (userInfoBean2.f1109f != -1) {
                                    it.remove();
                                    if (userInfoBean2.f1108e < C2018y.m2166b()) {
                                        arrayList2.add(userInfoBean2);
                                    }
                                }
                                if (userInfoBean2.f1108e <= System.currentTimeMillis() - 600000 || !(userInfoBean2.f1105b == 1 || userInfoBean2.f1105b == 4 || userInfoBean2.f1105b == 3)) {
                                    i = i5;
                                } else {
                                    i = i5 + 1;
                                }
                                i5 = i;
                            }
                            if (i5 > 15) {
                                C2014w.m2118d("[UserInfo] Upload user info too many times in 10 min: %d", Integer.valueOf(i5));
                                z = false;
                            } else {
                                z = true;
                            }
                            arrayList = a3;
                        } else {
                            arrayList = new ArrayList();
                            z = true;
                        }
                        if (arrayList2.size() > 0) {
                            m1627a((List<UserInfoBean>) arrayList2);
                        }
                        if (!z || arrayList.size() == 0) {
                            C2014w.m2117c("[UserInfo] There is no user info in local database.", new Object[0]);
                        } else {
                            C2014w.m2117c("[UserInfo] Upload user info(size: %d)", Integer.valueOf(arrayList.size()));
                            C1984aq a4 = C1967a.m1891a(arrayList, this.f1125c == 1 ? 1 : 2);
                            if (a4 == null) {
                                C2014w.m2118d("[UserInfo] Failed to create UserInfoPackage.", new Object[0]);
                            } else {
                                byte[] a5 = C1967a.m1896a((C1994j) a4);
                                if (a5 == null) {
                                    C2014w.m2118d("[UserInfo] Failed to encode data.", new Object[0]);
                                } else {
                                    C1979al a6 = C1967a.m1888a(this.f1123a, a.f1644a ? 840 : 640, a5);
                                    if (a6 == null) {
                                        C2014w.m2118d("[UserInfo] Request package is null.", new Object[0]);
                                    } else {
                                        C19291 r5 = new C2006s() {
                                            /* renamed from: a */
                                            public final void mo19357a(boolean z) {
                                                if (z) {
                                                    C2014w.m2117c("[UserInfo] Successfully uploaded user info.", new Object[0]);
                                                    long currentTimeMillis = System.currentTimeMillis();
                                                    for (UserInfoBean userInfoBean : arrayList) {
                                                        userInfoBean.f1109f = currentTimeMillis;
                                                        C1928a.m1626a(C1928a.this, userInfoBean, true);
                                                    }
                                                }
                                            }
                                        };
                                        StrategyBean c = C1941a.m1752a().mo19435c();
                                        String str2 = a.f1644a ? c.f1244r : c.f1246t;
                                        String str3 = a.f1644a ? StrategyBean.f1228b : StrategyBean.f1227a;
                                        C2007t a7 = C2007t.m2069a();
                                        if (this.f1125c == 1) {
                                            z2 = true;
                                        }
                                        a7.mo19621a(1001, a6, str2, str3, r5, z2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /* renamed from: b */
    public final void mo19356b() {
        C2012v a = C2012v.m2106a();
        if (a != null) {
            a.mo19636a(new Runnable() {
                public final void run() {
                    try {
                        C1928a.this.m1629c();
                    } catch (Throwable th) {
                        C2014w.m2114a(th);
                    }
                }
            });
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004f, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0050, code lost:
        r1 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0085, code lost:
        r0 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0085 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:12:0x0035] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0088  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.tencent.bugly.crashreport.biz.UserInfoBean> mo19353a(java.lang.String r10) {
        /*
            r9 = this;
            r7 = 0
            boolean r0 = com.tencent.bugly.proguard.C2018y.m2158a(r10)     // Catch:{ Throwable -> 0x00ca, all -> 0x00c4 }
            if (r0 == 0) goto L_0x001f
            r3 = r7
        L_0x0008:
            com.tencent.bugly.proguard.o r0 = com.tencent.bugly.proguard.C2001o.m2035a()     // Catch:{ Throwable -> 0x00ca, all -> 0x00c4 }
            java.lang.String r1 = "t_ui"
            r2 = 0
            r4 = 0
            r5 = 0
            r6 = 1
            android.database.Cursor r8 = r0.mo19599a(r1, r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x00ca, all -> 0x00c4 }
            if (r8 != 0) goto L_0x0035
            if (r8 == 0) goto L_0x001d
            r8.close()
        L_0x001d:
            r0 = r7
        L_0x001e:
            return r0
        L_0x001f:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00ca, all -> 0x00c4 }
            java.lang.String r1 = "_pc = '"
            r0.<init>(r1)     // Catch:{ Throwable -> 0x00ca, all -> 0x00c4 }
            java.lang.StringBuilder r0 = r0.append(r10)     // Catch:{ Throwable -> 0x00ca, all -> 0x00c4 }
            java.lang.String r1 = "'"
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ Throwable -> 0x00ca, all -> 0x00c4 }
            java.lang.String r3 = r0.toString()     // Catch:{ Throwable -> 0x00ca, all -> 0x00c4 }
            goto L_0x0008
        L_0x0035:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x004f, all -> 0x0085 }
            r0.<init>()     // Catch:{ Throwable -> 0x004f, all -> 0x0085 }
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ Throwable -> 0x004f, all -> 0x0085 }
            r6.<init>()     // Catch:{ Throwable -> 0x004f, all -> 0x0085 }
        L_0x003f:
            boolean r1 = r8.moveToNext()     // Catch:{ Throwable -> 0x004f, all -> 0x0085 }
            if (r1 == 0) goto L_0x008c
            com.tencent.bugly.crashreport.biz.UserInfoBean r1 = m1624a(r8)     // Catch:{ Throwable -> 0x004f, all -> 0x0085 }
            if (r1 == 0) goto L_0x0061
            r6.add(r1)     // Catch:{ Throwable -> 0x004f, all -> 0x0085 }
            goto L_0x003f
        L_0x004f:
            r0 = move-exception
            r1 = r8
        L_0x0051:
            boolean r2 = com.tencent.bugly.proguard.C2014w.m2114a(r0)     // Catch:{ all -> 0x00c7 }
            if (r2 != 0) goto L_0x005a
            r0.printStackTrace()     // Catch:{ all -> 0x00c7 }
        L_0x005a:
            if (r1 == 0) goto L_0x005f
            r1.close()
        L_0x005f:
            r0 = r7
            goto L_0x001e
        L_0x0061:
            java.lang.String r1 = "_id"
            int r1 = r8.getColumnIndex(r1)     // Catch:{ Throwable -> 0x007b, all -> 0x0085 }
            long r2 = r8.getLong(r1)     // Catch:{ Throwable -> 0x007b, all -> 0x0085 }
            java.lang.String r1 = " or _id"
            java.lang.StringBuilder r1 = r0.append(r1)     // Catch:{ Throwable -> 0x007b, all -> 0x0085 }
            java.lang.String r4 = " = "
            java.lang.StringBuilder r1 = r1.append(r4)     // Catch:{ Throwable -> 0x007b, all -> 0x0085 }
            r1.append(r2)     // Catch:{ Throwable -> 0x007b, all -> 0x0085 }
            goto L_0x003f
        L_0x007b:
            r1 = move-exception
            java.lang.String r1 = "[Database] unknown id."
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x004f, all -> 0x0085 }
            com.tencent.bugly.proguard.C2014w.m2118d(r1, r2)     // Catch:{ Throwable -> 0x004f, all -> 0x0085 }
            goto L_0x003f
        L_0x0085:
            r0 = move-exception
        L_0x0086:
            if (r8 == 0) goto L_0x008b
            r8.close()
        L_0x008b:
            throw r0
        L_0x008c:
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x004f, all -> 0x0085 }
            int r1 = r0.length()     // Catch:{ Throwable -> 0x004f, all -> 0x0085 }
            if (r1 <= 0) goto L_0x00bc
            r1 = 4
            java.lang.String r2 = r0.substring(r1)     // Catch:{ Throwable -> 0x004f, all -> 0x0085 }
            com.tencent.bugly.proguard.o r0 = com.tencent.bugly.proguard.C2001o.m2035a()     // Catch:{ Throwable -> 0x004f, all -> 0x0085 }
            java.lang.String r1 = "t_ui"
            r3 = 0
            r4 = 0
            r5 = 1
            int r0 = r0.mo19597a(r1, r2, r3, r4, r5)     // Catch:{ Throwable -> 0x004f, all -> 0x0085 }
            java.lang.String r1 = "[Database] deleted %s error data %d"
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x004f, all -> 0x0085 }
            r3 = 0
            java.lang.String r4 = "t_ui"
            r2[r3] = r4     // Catch:{ Throwable -> 0x004f, all -> 0x0085 }
            r3 = 1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x004f, all -> 0x0085 }
            r2[r3] = r0     // Catch:{ Throwable -> 0x004f, all -> 0x0085 }
            com.tencent.bugly.proguard.C2014w.m2118d(r1, r2)     // Catch:{ Throwable -> 0x004f, all -> 0x0085 }
        L_0x00bc:
            if (r8 == 0) goto L_0x00c1
            r8.close()
        L_0x00c1:
            r0 = r6
            goto L_0x001e
        L_0x00c4:
            r0 = move-exception
            r8 = r7
            goto L_0x0086
        L_0x00c7:
            r0 = move-exception
            r8 = r1
            goto L_0x0086
        L_0x00ca:
            r0 = move-exception
            r1 = r7
            goto L_0x0051
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.biz.C1928a.mo19353a(java.lang.String):java.util.List");
    }

    /* renamed from: a */
    private static void m1627a(List<UserInfoBean> list) {
        if (list != null && list.size() != 0) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (i < list.size() && i < 50) {
                sb.append(" or _id").append(" = ").append(((UserInfoBean) list.get(i)).f1104a);
                i++;
            }
            String sb2 = sb.toString();
            if (sb2.length() > 0) {
                sb2 = sb2.substring(4);
            }
            sb.setLength(0);
            try {
                C2014w.m2117c("[Database] deleted %s data %d", "t_ui", Integer.valueOf(C2001o.m2035a().mo19597a("t_ui", sb2, (String[]) null, (C2000n) null, true)));
            } catch (Throwable th) {
                if (!C2014w.m2114a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* renamed from: a */
    private static ContentValues m1623a(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (userInfoBean.f1104a > 0) {
                contentValues.put(APEZProvider.FILEID, Long.valueOf(userInfoBean.f1104a));
            }
            contentValues.put("_tm", Long.valueOf(userInfoBean.f1108e));
            contentValues.put("_ut", Long.valueOf(userInfoBean.f1109f));
            contentValues.put("_tp", Integer.valueOf(userInfoBean.f1105b));
            contentValues.put("_pc", userInfoBean.f1106c);
            contentValues.put("_dt", C2018y.m2161a((Parcelable) userInfoBean));
            return contentValues;
        } catch (Throwable th) {
            if (C2014w.m2114a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private static UserInfoBean m1624a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex(APEZProvider.FILEID));
            UserInfoBean userInfoBean = (UserInfoBean) C2018y.m2142a(blob, UserInfoBean.CREATOR);
            if (userInfoBean == null) {
                return userInfoBean;
            }
            userInfoBean.f1104a = j;
            return userInfoBean;
        } catch (Throwable th) {
            if (!C2014w.m2114a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }
}
