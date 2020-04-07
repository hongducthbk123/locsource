package com.tencent.bugly.proguard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.android.vending.expansion.zipfile.APEZProvider;
import com.tencent.bugly.C1911a;
import java.util.List;
import java.util.Map;

/* renamed from: com.tencent.bugly.proguard.o */
/* compiled from: BUGLY */
public final class C2001o {

    /* renamed from: a */
    private static C2001o f1609a = null;

    /* renamed from: b */
    private static C2003p f1610b = null;

    /* renamed from: c */
    private static boolean f1611c = false;

    /* renamed from: com.tencent.bugly.proguard.o$a */
    /* compiled from: BUGLY */
    class C2002a extends Thread {

        /* renamed from: a */
        private int f1612a;

        /* renamed from: b */
        private C2000n f1613b;

        /* renamed from: c */
        private String f1614c;

        /* renamed from: d */
        private ContentValues f1615d;

        /* renamed from: e */
        private boolean f1616e;

        /* renamed from: f */
        private String[] f1617f;

        /* renamed from: g */
        private String f1618g;

        /* renamed from: h */
        private String[] f1619h;

        /* renamed from: i */
        private String f1620i;

        /* renamed from: j */
        private String f1621j;

        /* renamed from: k */
        private String f1622k;

        /* renamed from: l */
        private String f1623l;

        /* renamed from: m */
        private String f1624m;

        /* renamed from: n */
        private String[] f1625n;

        /* renamed from: o */
        private int f1626o;

        /* renamed from: p */
        private String f1627p;

        /* renamed from: q */
        private byte[] f1628q;

        public C2002a(int i, C2000n nVar) {
            this.f1612a = i;
            this.f1613b = nVar;
        }

        /* renamed from: a */
        public final void mo19608a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
            this.f1616e = z;
            this.f1614c = str;
            this.f1617f = strArr;
            this.f1618g = str2;
            this.f1619h = strArr2;
            this.f1620i = str3;
            this.f1621j = str4;
            this.f1622k = str5;
            this.f1623l = str6;
        }

        /* renamed from: a */
        public final void mo19607a(int i, String str, byte[] bArr) {
            this.f1626o = i;
            this.f1627p = str;
            this.f1628q = bArr;
        }

        public final void run() {
            switch (this.f1612a) {
                case 1:
                    C2001o.this.m2032a(this.f1614c, this.f1615d, this.f1613b);
                    return;
                case 2:
                    C2001o.this.m2030a(this.f1614c, this.f1624m, this.f1625n, this.f1613b);
                    return;
                case 3:
                    C2001o.this.m2034a(this.f1616e, this.f1614c, this.f1617f, this.f1618g, this.f1619h, this.f1620i, this.f1621j, this.f1622k, this.f1623l, this.f1613b);
                    return;
                case 4:
                    C2001o.this.m2041a(this.f1626o, this.f1627p, this.f1628q, this.f1613b);
                    return;
                case 5:
                    C2001o.this.m2038a(this.f1626o, this.f1613b);
                    return;
                case 6:
                    C2001o.this.m2040a(this.f1626o, this.f1627p, this.f1613b);
                    return;
                default:
                    return;
            }
        }
    }

    private C2001o(Context context, List<C1911a> list) {
        f1610b = new C2003p(context, list);
    }

    /* renamed from: a */
    public static synchronized C2001o m2036a(Context context, List<C1911a> list) {
        C2001o oVar;
        synchronized (C2001o.class) {
            if (f1609a == null) {
                f1609a = new C2001o(context, list);
            }
            oVar = f1609a;
        }
        return oVar;
    }

    /* renamed from: a */
    public static synchronized C2001o m2035a() {
        C2001o oVar;
        synchronized (C2001o.class) {
            oVar = f1609a;
        }
        return oVar;
    }

    /* renamed from: a */
    public final long mo19598a(String str, ContentValues contentValues, C2000n nVar, boolean z) {
        return m2032a(str, contentValues, (C2000n) null);
    }

    /* renamed from: a */
    public final Cursor mo19599a(String str, String[] strArr, String str2, String[] strArr2, C2000n nVar, boolean z) {
        return m2034a(false, str, strArr, str2, null, null, null, null, null, null);
    }

    /* renamed from: a */
    public final int mo19597a(String str, String str2, String[] strArr, C2000n nVar, boolean z) {
        return m2030a(str, str2, (String[]) null, (C2000n) null);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public synchronized long m2032a(String str, ContentValues contentValues, C2000n nVar) {
        long j = 0;
        synchronized (this) {
            try {
                SQLiteDatabase writableDatabase = f1610b.getWritableDatabase();
                if (!(writableDatabase == null || contentValues == null)) {
                    long replace = writableDatabase.replace(str, APEZProvider.FILEID, contentValues);
                    if (replace >= 0) {
                        C2014w.m2117c("[Database] insert %s success.", str);
                    } else {
                        C2014w.m2118d("[Database] replace %s error.", str);
                    }
                    j = replace;
                }
                if (nVar != null) {
                    Long.valueOf(j);
                }
            } catch (Throwable th) {
                if (nVar != null) {
                    Long.valueOf(0);
                }
                throw th;
            }
        }
        return j;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public synchronized Cursor m2034a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6, C2000n nVar) {
        Cursor cursor;
        try {
            SQLiteDatabase writableDatabase = f1610b.getWritableDatabase();
            if (writableDatabase != null) {
                cursor = writableDatabase.query(z, str, strArr, str2, strArr2, str3, str4, str5, str6);
            } else {
                cursor = null;
            }
            if (nVar != null) {
            }
        } catch (Throwable th) {
            if (!C2014w.m2114a(th)) {
                th.printStackTrace();
            }
            if (nVar != null) {
                cursor = null;
            } else {
                cursor = null;
            }
        }
        return cursor;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public synchronized int m2030a(String str, String str2, String[] strArr, C2000n nVar) {
        int i = 0;
        synchronized (this) {
            try {
                SQLiteDatabase writableDatabase = f1610b.getWritableDatabase();
                if (writableDatabase != null) {
                    i = writableDatabase.delete(str, str2, strArr);
                }
                if (nVar != null) {
                    Integer.valueOf(i);
                }
            } catch (Throwable th) {
                if (nVar != null) {
                    Integer.valueOf(0);
                }
                throw th;
            }
        }
        return i;
    }

    /* renamed from: a */
    public final boolean mo19604a(int i, String str, byte[] bArr, C2000n nVar, boolean z) {
        if (z) {
            return m2041a(i, str, bArr, (C2000n) null);
        }
        C2002a aVar = new C2002a(4, null);
        aVar.mo19607a(i, str, bArr);
        C2012v.m2106a().mo19636a(aVar);
        return true;
    }

    /* renamed from: a */
    public final Map<String, byte[]> mo19601a(int i, C2000n nVar, boolean z) {
        return m2038a(i, (C2000n) null);
    }

    /* renamed from: a */
    public final boolean mo19603a(int i, String str, C2000n nVar, boolean z) {
        return m2040a(555, str, (C2000n) null);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public boolean m2041a(int i, String str, byte[] bArr, C2000n nVar) {
        boolean z = false;
        try {
            C2004q qVar = new C2004q();
            qVar.f1633a = (long) i;
            qVar.f1638f = str;
            qVar.f1637e = System.currentTimeMillis();
            qVar.f1639g = bArr;
            z = m2045b(qVar);
            if (nVar != null) {
                Boolean.valueOf(z);
            }
        } catch (Throwable th) {
            if (nVar != null) {
                Boolean.valueOf(z);
            }
            throw th;
        }
        return z;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x002e  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Map<java.lang.String, byte[]> m2038a(int r6, com.tencent.bugly.proguard.C2000n r7) {
        /*
            r5 = this;
            r2 = 0
            java.util.List r0 = r5.m2047c(r6)     // Catch:{ Throwable -> 0x003a }
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ Throwable -> 0x003a }
            r1.<init>()     // Catch:{ Throwable -> 0x003a }
            java.util.Iterator r2 = r0.iterator()     // Catch:{ Throwable -> 0x0024 }
        L_0x000e:
            boolean r0 = r2.hasNext()     // Catch:{ Throwable -> 0x0024 }
            if (r0 == 0) goto L_0x0034
            java.lang.Object r0 = r2.next()     // Catch:{ Throwable -> 0x0024 }
            com.tencent.bugly.proguard.q r0 = (com.tencent.bugly.proguard.C2004q) r0     // Catch:{ Throwable -> 0x0024 }
            byte[] r3 = r0.f1639g     // Catch:{ Throwable -> 0x0024 }
            if (r3 == 0) goto L_0x000e
            java.lang.String r0 = r0.f1638f     // Catch:{ Throwable -> 0x0024 }
            r1.put(r0, r3)     // Catch:{ Throwable -> 0x0024 }
            goto L_0x000e
        L_0x0024:
            r0 = move-exception
            r4 = r0
            r0 = r1
            r1 = r4
        L_0x0028:
            boolean r2 = com.tencent.bugly.proguard.C2014w.m2114a(r1)     // Catch:{ all -> 0x0038 }
            if (r2 != 0) goto L_0x0031
            r1.printStackTrace()     // Catch:{ all -> 0x0038 }
        L_0x0031:
            if (r7 == 0) goto L_0x0033
        L_0x0033:
            return r0
        L_0x0034:
            if (r7 == 0) goto L_0x003e
            r0 = r1
            goto L_0x0033
        L_0x0038:
            r0 = move-exception
            throw r0
        L_0x003a:
            r0 = move-exception
            r1 = r0
            r0 = r2
            goto L_0x0028
        L_0x003e:
            r0 = r1
            goto L_0x0033
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2001o.m2038a(int, com.tencent.bugly.proguard.n):java.util.Map");
    }

    /* renamed from: a */
    public final synchronized boolean mo19605a(C2004q qVar) {
        boolean z = false;
        synchronized (this) {
            if (qVar != null) {
                try {
                    SQLiteDatabase writableDatabase = f1610b.getWritableDatabase();
                    if (writableDatabase != null) {
                        ContentValues c = m2046c(qVar);
                        if (c != null) {
                            long replace = writableDatabase.replace("t_lr", APEZProvider.FILEID, c);
                            if (replace >= 0) {
                                C2014w.m2117c("[Database] insert %s success.", "t_lr");
                                qVar.f1633a = replace;
                                z = true;
                            }
                        }
                    }
                } catch (Throwable th) {
                    if (!C2014w.m2114a(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
        return z;
    }

    /* renamed from: b */
    private synchronized boolean m2045b(C2004q qVar) {
        boolean z = false;
        synchronized (this) {
            if (qVar != null) {
                try {
                    SQLiteDatabase writableDatabase = f1610b.getWritableDatabase();
                    if (writableDatabase != null) {
                        ContentValues d = m2048d(qVar);
                        if (d != null) {
                            long replace = writableDatabase.replace("t_pf", APEZProvider.FILEID, d);
                            if (replace >= 0) {
                                C2014w.m2117c("[Database] insert %s success.", "t_pf");
                                qVar.f1633a = replace;
                                z = true;
                            }
                        }
                    }
                } catch (Throwable th) {
                    if (!C2014w.m2114a(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004c, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004d, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0082, code lost:
        r0 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0082 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:17:0x0032] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0085 A[SYNTHETIC, Splitter:B:43:0x0085] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized java.util.List<com.tencent.bugly.proguard.C2004q> mo19600a(int r10) {
        /*
            r9 = this;
            r8 = 0
            monitor-enter(r9)
            com.tencent.bugly.proguard.p r0 = f1610b     // Catch:{ all -> 0x0089 }
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()     // Catch:{ all -> 0x0089 }
            if (r0 == 0) goto L_0x005c
            if (r10 < 0) goto L_0x0030
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00c4, all -> 0x00be }
            java.lang.String r2 = "_tp = "
            r1.<init>(r2)     // Catch:{ Throwable -> 0x00c4, all -> 0x00be }
            java.lang.StringBuilder r1 = r1.append(r10)     // Catch:{ Throwable -> 0x00c4, all -> 0x00be }
            java.lang.String r3 = r1.toString()     // Catch:{ Throwable -> 0x00c4, all -> 0x00be }
        L_0x001b:
            java.lang.String r1 = "t_lr"
            r2 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r2 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ Throwable -> 0x00c4, all -> 0x00be }
            if (r2 != 0) goto L_0x0032
            if (r2 == 0) goto L_0x002d
            r2.close()     // Catch:{ all -> 0x0089 }
        L_0x002d:
            r0 = r8
        L_0x002e:
            monitor-exit(r9)
            return r0
        L_0x0030:
            r3 = r8
            goto L_0x001b
        L_0x0032:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x004c, all -> 0x0082 }
            r3.<init>()     // Catch:{ Throwable -> 0x004c, all -> 0x0082 }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ Throwable -> 0x004c, all -> 0x0082 }
            r1.<init>()     // Catch:{ Throwable -> 0x004c, all -> 0x0082 }
        L_0x003c:
            boolean r4 = r2.moveToNext()     // Catch:{ Throwable -> 0x004c, all -> 0x0082 }
            if (r4 == 0) goto L_0x008c
            com.tencent.bugly.proguard.q r4 = m2037a(r2)     // Catch:{ Throwable -> 0x004c, all -> 0x0082 }
            if (r4 == 0) goto L_0x005e
            r1.add(r4)     // Catch:{ Throwable -> 0x004c, all -> 0x0082 }
            goto L_0x003c
        L_0x004c:
            r0 = move-exception
            r1 = r2
        L_0x004e:
            boolean r2 = com.tencent.bugly.proguard.C2014w.m2114a(r0)     // Catch:{ all -> 0x00c1 }
            if (r2 != 0) goto L_0x0057
            r0.printStackTrace()     // Catch:{ all -> 0x00c1 }
        L_0x0057:
            if (r1 == 0) goto L_0x005c
            r1.close()     // Catch:{ all -> 0x0089 }
        L_0x005c:
            r0 = r8
            goto L_0x002e
        L_0x005e:
            java.lang.String r4 = "_id"
            int r4 = r2.getColumnIndex(r4)     // Catch:{ Throwable -> 0x0078, all -> 0x0082 }
            long r4 = r2.getLong(r4)     // Catch:{ Throwable -> 0x0078, all -> 0x0082 }
            java.lang.String r6 = " or _id"
            java.lang.StringBuilder r6 = r3.append(r6)     // Catch:{ Throwable -> 0x0078, all -> 0x0082 }
            java.lang.String r7 = " = "
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Throwable -> 0x0078, all -> 0x0082 }
            r6.append(r4)     // Catch:{ Throwable -> 0x0078, all -> 0x0082 }
            goto L_0x003c
        L_0x0078:
            r4 = move-exception
            java.lang.String r4 = "[Database] unknown id."
            r5 = 0
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x004c, all -> 0x0082 }
            com.tencent.bugly.proguard.C2014w.m2118d(r4, r5)     // Catch:{ Throwable -> 0x004c, all -> 0x0082 }
            goto L_0x003c
        L_0x0082:
            r0 = move-exception
        L_0x0083:
            if (r2 == 0) goto L_0x0088
            r2.close()     // Catch:{ all -> 0x0089 }
        L_0x0088:
            throw r0     // Catch:{ all -> 0x0089 }
        L_0x0089:
            r0 = move-exception
            monitor-exit(r9)
            throw r0
        L_0x008c:
            java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x004c, all -> 0x0082 }
            int r4 = r3.length()     // Catch:{ Throwable -> 0x004c, all -> 0x0082 }
            if (r4 <= 0) goto L_0x00b6
            r4 = 4
            java.lang.String r3 = r3.substring(r4)     // Catch:{ Throwable -> 0x004c, all -> 0x0082 }
            java.lang.String r4 = "t_lr"
            r5 = 0
            int r0 = r0.delete(r4, r3, r5)     // Catch:{ Throwable -> 0x004c, all -> 0x0082 }
            java.lang.String r3 = "[Database] deleted %s illegal data %d"
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x004c, all -> 0x0082 }
            r5 = 0
            java.lang.String r6 = "t_lr"
            r4[r5] = r6     // Catch:{ Throwable -> 0x004c, all -> 0x0082 }
            r5 = 1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x004c, all -> 0x0082 }
            r4[r5] = r0     // Catch:{ Throwable -> 0x004c, all -> 0x0082 }
            com.tencent.bugly.proguard.C2014w.m2118d(r3, r4)     // Catch:{ Throwable -> 0x004c, all -> 0x0082 }
        L_0x00b6:
            if (r2 == 0) goto L_0x00bb
            r2.close()     // Catch:{ all -> 0x0089 }
        L_0x00bb:
            r0 = r1
            goto L_0x002e
        L_0x00be:
            r0 = move-exception
            r2 = r8
            goto L_0x0083
        L_0x00c1:
            r0 = move-exception
            r2 = r1
            goto L_0x0083
        L_0x00c4:
            r0 = move-exception
            r1 = r8
            goto L_0x004e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2001o.mo19600a(int):java.util.List");
    }

    /* renamed from: a */
    public final synchronized void mo19602a(List<C2004q> list) {
        if (list != null) {
            if (list.size() != 0) {
                SQLiteDatabase writableDatabase = f1610b.getWritableDatabase();
                if (writableDatabase != null) {
                    StringBuilder sb = new StringBuilder();
                    for (C2004q qVar : list) {
                        sb.append(" or _id").append(" = ").append(qVar.f1633a);
                    }
                    String sb2 = sb.toString();
                    if (sb2.length() > 0) {
                        sb2 = sb2.substring(4);
                    }
                    sb.setLength(0);
                    try {
                        C2014w.m2117c("[Database] deleted %s data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", sb2, null)));
                    } catch (Throwable th) {
                        if (!C2014w.m2114a(th)) {
                            th.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /* renamed from: b */
    public final synchronized void mo19606b(int i) {
        String str = null;
        synchronized (this) {
            SQLiteDatabase writableDatabase = f1610b.getWritableDatabase();
            if (writableDatabase != null) {
                if (i >= 0) {
                    try {
                        str = "_tp = " + i;
                    } catch (Throwable th) {
                        if (!C2014w.m2114a(th)) {
                            th.printStackTrace();
                        }
                    }
                }
                C2014w.m2117c("[Database] deleted %s data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", str, null)));
            }
        }
    }

    /* renamed from: c */
    private static ContentValues m2046c(C2004q qVar) {
        if (qVar == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (qVar.f1633a > 0) {
                contentValues.put(APEZProvider.FILEID, Long.valueOf(qVar.f1633a));
            }
            contentValues.put("_tp", Integer.valueOf(qVar.f1634b));
            contentValues.put("_pc", qVar.f1635c);
            contentValues.put("_th", qVar.f1636d);
            contentValues.put("_tm", Long.valueOf(qVar.f1637e));
            if (qVar.f1639g != null) {
                contentValues.put("_dt", qVar.f1639g);
            }
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
    private static C2004q m2037a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            C2004q qVar = new C2004q();
            qVar.f1633a = cursor.getLong(cursor.getColumnIndex(APEZProvider.FILEID));
            qVar.f1634b = cursor.getInt(cursor.getColumnIndex("_tp"));
            qVar.f1635c = cursor.getString(cursor.getColumnIndex("_pc"));
            qVar.f1636d = cursor.getString(cursor.getColumnIndex("_th"));
            qVar.f1637e = cursor.getLong(cursor.getColumnIndex("_tm"));
            qVar.f1639g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return qVar;
        } catch (Throwable th) {
            if (C2014w.m2114a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0048, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0049, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x007e, code lost:
        r0 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x007e A[ExcHandler: all (th java.lang.Throwable), Splitter:B:13:0x002e] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0081 A[SYNTHETIC, Splitter:B:39:0x0081] */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized java.util.List<com.tencent.bugly.proguard.C2004q> m2047c(int r10) {
        /*
            r9 = this;
            r8 = 0
            monitor-enter(r9)
            com.tencent.bugly.proguard.p r0 = f1610b     // Catch:{ Throwable -> 0x00cb, all -> 0x00c5 }
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()     // Catch:{ Throwable -> 0x00cb, all -> 0x00c5 }
            if (r0 == 0) goto L_0x0058
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00cb, all -> 0x00c5 }
            java.lang.String r2 = "_id = "
            r1.<init>(r2)     // Catch:{ Throwable -> 0x00cb, all -> 0x00c5 }
            java.lang.StringBuilder r1 = r1.append(r10)     // Catch:{ Throwable -> 0x00cb, all -> 0x00c5 }
            java.lang.String r3 = r1.toString()     // Catch:{ Throwable -> 0x00cb, all -> 0x00c5 }
            java.lang.String r1 = "t_pf"
            r2 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r2 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ Throwable -> 0x00cb, all -> 0x00c5 }
            if (r2 != 0) goto L_0x002e
            if (r2 == 0) goto L_0x002b
            r2.close()     // Catch:{ all -> 0x0085 }
        L_0x002b:
            r0 = r8
        L_0x002c:
            monitor-exit(r9)
            return r0
        L_0x002e:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0048, all -> 0x007e }
            r4.<init>()     // Catch:{ Throwable -> 0x0048, all -> 0x007e }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0048, all -> 0x007e }
            r1.<init>()     // Catch:{ Throwable -> 0x0048, all -> 0x007e }
        L_0x0038:
            boolean r5 = r2.moveToNext()     // Catch:{ Throwable -> 0x0048, all -> 0x007e }
            if (r5 == 0) goto L_0x0088
            com.tencent.bugly.proguard.q r5 = m2044b(r2)     // Catch:{ Throwable -> 0x0048, all -> 0x007e }
            if (r5 == 0) goto L_0x005a
            r1.add(r5)     // Catch:{ Throwable -> 0x0048, all -> 0x007e }
            goto L_0x0038
        L_0x0048:
            r0 = move-exception
            r1 = r2
        L_0x004a:
            boolean r2 = com.tencent.bugly.proguard.C2014w.m2114a(r0)     // Catch:{ all -> 0x00c8 }
            if (r2 != 0) goto L_0x0053
            r0.printStackTrace()     // Catch:{ all -> 0x00c8 }
        L_0x0053:
            if (r1 == 0) goto L_0x0058
            r1.close()     // Catch:{ all -> 0x0085 }
        L_0x0058:
            r0 = r8
            goto L_0x002c
        L_0x005a:
            java.lang.String r5 = "_tp"
            int r5 = r2.getColumnIndex(r5)     // Catch:{ Throwable -> 0x0074, all -> 0x007e }
            java.lang.String r5 = r2.getString(r5)     // Catch:{ Throwable -> 0x0074, all -> 0x007e }
            java.lang.String r6 = " or _tp"
            java.lang.StringBuilder r6 = r4.append(r6)     // Catch:{ Throwable -> 0x0074, all -> 0x007e }
            java.lang.String r7 = " = "
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Throwable -> 0x0074, all -> 0x007e }
            r6.append(r5)     // Catch:{ Throwable -> 0x0074, all -> 0x007e }
            goto L_0x0038
        L_0x0074:
            r5 = move-exception
            java.lang.String r5 = "[Database] unknown id."
            r6 = 0
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ Throwable -> 0x0048, all -> 0x007e }
            com.tencent.bugly.proguard.C2014w.m2118d(r5, r6)     // Catch:{ Throwable -> 0x0048, all -> 0x007e }
            goto L_0x0038
        L_0x007e:
            r0 = move-exception
        L_0x007f:
            if (r2 == 0) goto L_0x0084
            r2.close()     // Catch:{ all -> 0x0085 }
        L_0x0084:
            throw r0     // Catch:{ all -> 0x0085 }
        L_0x0085:
            r0 = move-exception
            monitor-exit(r9)
            throw r0
        L_0x0088:
            int r5 = r4.length()     // Catch:{ Throwable -> 0x0048, all -> 0x007e }
            if (r5 <= 0) goto L_0x00bd
            java.lang.String r5 = " and _id"
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Throwable -> 0x0048, all -> 0x007e }
            java.lang.String r5 = " = "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Throwable -> 0x0048, all -> 0x007e }
            r4.append(r10)     // Catch:{ Throwable -> 0x0048, all -> 0x007e }
            r4 = 4
            java.lang.String r3 = r3.substring(r4)     // Catch:{ Throwable -> 0x0048, all -> 0x007e }
            java.lang.String r4 = "t_pf"
            r5 = 0
            int r0 = r0.delete(r4, r3, r5)     // Catch:{ Throwable -> 0x0048, all -> 0x007e }
            java.lang.String r3 = "[Database] deleted %s illegal data %d."
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x0048, all -> 0x007e }
            r5 = 0
            java.lang.String r6 = "t_pf"
            r4[r5] = r6     // Catch:{ Throwable -> 0x0048, all -> 0x007e }
            r5 = 1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x0048, all -> 0x007e }
            r4[r5] = r0     // Catch:{ Throwable -> 0x0048, all -> 0x007e }
            com.tencent.bugly.proguard.C2014w.m2118d(r3, r4)     // Catch:{ Throwable -> 0x0048, all -> 0x007e }
        L_0x00bd:
            if (r2 == 0) goto L_0x00c2
            r2.close()     // Catch:{ all -> 0x0085 }
        L_0x00c2:
            r0 = r1
            goto L_0x002c
        L_0x00c5:
            r0 = move-exception
            r2 = r8
            goto L_0x007f
        L_0x00c8:
            r0 = move-exception
            r2 = r1
            goto L_0x007f
        L_0x00cb:
            r0 = move-exception
            r1 = r8
            goto L_0x004a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2001o.m2047c(int):java.util.List");
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public synchronized boolean m2040a(int i, String str, C2000n nVar) {
        String str2;
        boolean z = true;
        boolean z2 = false;
        synchronized (this) {
            try {
                SQLiteDatabase writableDatabase = f1610b.getWritableDatabase();
                if (writableDatabase != null) {
                    if (C2018y.m2158a(str)) {
                        str2 = "_id = " + i;
                    } else {
                        str2 = "_id = " + i + " and _tp" + " = \"" + str + "\"";
                    }
                    int delete = writableDatabase.delete("t_pf", str2, null);
                    C2014w.m2117c("[Database] deleted %s data %d", "t_pf", Integer.valueOf(delete));
                    if (delete <= 0) {
                        z = false;
                    }
                    z2 = z;
                }
                if (nVar != null) {
                    Boolean.valueOf(z2);
                }
            } catch (Throwable th) {
                if (nVar != null) {
                    Boolean.valueOf(false);
                }
                throw th;
            }
        }
        return z2;
    }

    /* renamed from: d */
    private static ContentValues m2048d(C2004q qVar) {
        if (qVar == null || C2018y.m2158a(qVar.f1638f)) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (qVar.f1633a > 0) {
                contentValues.put(APEZProvider.FILEID, Long.valueOf(qVar.f1633a));
            }
            contentValues.put("_tp", qVar.f1638f);
            contentValues.put("_tm", Long.valueOf(qVar.f1637e));
            if (qVar.f1639g == null) {
                return contentValues;
            }
            contentValues.put("_dt", qVar.f1639g);
            return contentValues;
        } catch (Throwable th) {
            if (!C2014w.m2114a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: b */
    private static C2004q m2044b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            C2004q qVar = new C2004q();
            qVar.f1633a = cursor.getLong(cursor.getColumnIndex(APEZProvider.FILEID));
            qVar.f1637e = cursor.getLong(cursor.getColumnIndex("_tm"));
            qVar.f1638f = cursor.getString(cursor.getColumnIndex("_tp"));
            qVar.f1639g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return qVar;
        } catch (Throwable th) {
            if (C2014w.m2114a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }
}
