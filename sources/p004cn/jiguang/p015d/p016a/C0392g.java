package p004cn.jiguang.p015d.p016a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.android.vending.expansion.zipfile.APEZProvider;

/* renamed from: cn.jiguang.d.a.g */
public final class C0392g extends C0390e {

    /* renamed from: a */
    private static final String[] f229a = {"st_sort_key", APEZProvider.FILEID, "st_net", "st_conn_ip", "st_local_dns", "st_source", "st_failed", "st_total", "st_count_1", "st_count_1_3", "st_count_3_10", "st_count_10"};

    /* renamed from: b */
    private static volatile C0392g f230b;

    /* renamed from: c */
    private static final Object f231c = new Object();

    private C0392g(Context context) {
        super(context, "jpush_statistics.db", null, 1);
    }

    /* renamed from: a */
    public static C0392g m352a(Context context) {
        if (f230b == null) {
            synchronized (f231c) {
                if (f230b == null) {
                    f230b = new C0392g(context);
                }
            }
        }
        return f230b;
    }

    /* renamed from: a */
    public static C0393h m353a(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0) {
            return null;
        }
        try {
            C0393h hVar = new C0393h();
            hVar.mo6372a(cursor.getString(1));
            hVar.mo6375b(cursor.getString(2));
            hVar.mo6378c(cursor.getString(3));
            hVar.mo6381d(cursor.getString(4));
            hVar.mo6384e(cursor.getString(5));
            hVar.mo6371a(cursor.getInt(6));
            hVar.mo6374b(cursor.getInt(7));
            hVar.mo6377c(cursor.getInt(8));
            hVar.mo6380d(cursor.getInt(9));
            hVar.mo6383e(cursor.getInt(10));
            hVar.mo6386f(cursor.getInt(11));
            return hVar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public final long mo6361a(String str, String str2, String str3, String str4, String str5, int i, int i2, int i3, int i4, int i5, int i6) {
        long j = 0;
        if (mo6356a(true)) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("st_sort_key", str);
            contentValues.put("st_net", str2);
            contentValues.put("st_conn_ip", str3);
            contentValues.put("st_local_dns", str4);
            contentValues.put("st_source", str5);
            contentValues.put("st_failed", Integer.valueOf(i));
            contentValues.put("st_total", Integer.valueOf(1));
            contentValues.put("st_count_1", Integer.valueOf(i3));
            contentValues.put("st_count_1_3", Integer.valueOf(i4));
            contentValues.put("st_count_3_10", Integer.valueOf(i5));
            contentValues.put("st_count_10", Integer.valueOf(0));
            try {
                j = getWritableDatabase().insert("jpush_statistics", null, contentValues);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                mo6357b(true);
            }
        }
        return j;
    }

    /* renamed from: a */
    public final Cursor mo6362a() {
        try {
            Cursor rawQuery = getReadableDatabase().rawQuery("select * from jpush_statistics where st_failed > 0  order by st_failed desc limit 3", null);
            if (rawQuery == null) {
                return rawQuery;
            }
            rawQuery.moveToFirst();
            return rawQuery;
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    public final C0393h mo6363a(String str) {
        Cursor cursor;
        if (mo6356a(false)) {
            try {
                cursor = getReadableDatabase().query(true, "jpush_statistics", f229a, "st_sort_key='" + str + "'", null, null, null, null, null);
                if (cursor != null) {
                    try {
                        cursor.moveToFirst();
                    } catch (Throwable th) {
                        th = th;
                        if (cursor != null) {
                            cursor.close();
                        }
                        mo6357b(false);
                        throw th;
                    }
                }
                C0393h a = m353a(cursor);
                if (cursor != null) {
                    cursor.close();
                }
                mo6357b(false);
                return a;
            } catch (Throwable th2) {
                th = th2;
                cursor = null;
            }
        } else {
            throw new Exception("database open failed");
        }
    }

    /* renamed from: b */
    public final long mo6364b(String str, String str2, String str3, String str4, String str5, int i, int i2, int i3, int i4, int i5, int i6) {
        long j = 0;
        if (!mo6356a(true)) {
            return j;
        }
        String str6 = "st_sort_key='" + str + "'";
        ContentValues contentValues = new ContentValues();
        contentValues.put("st_sort_key", str);
        contentValues.put("st_net", str2);
        contentValues.put("st_conn_ip", str3);
        contentValues.put("st_local_dns", str4);
        contentValues.put("st_source", str5);
        contentValues.put("st_failed", Integer.valueOf(i));
        contentValues.put("st_total", Integer.valueOf(i2));
        contentValues.put("st_count_1", Integer.valueOf(i3));
        contentValues.put("st_count_1_3", Integer.valueOf(i4));
        contentValues.put("st_count_3_10", Integer.valueOf(i5));
        contentValues.put("st_count_10", Integer.valueOf(i6));
        try {
            long update = (long) getWritableDatabase().update("jpush_statistics", contentValues, str6, null);
            return update;
        } catch (Exception e) {
            e.printStackTrace();
            return j;
        } finally {
            mo6357b(true);
        }
    }

    /* renamed from: b */
    public final Cursor mo6365b() {
        try {
            Cursor rawQuery = getReadableDatabase().rawQuery("select * from jpush_statistics where st_total > 0 and st_failed = 0  order by st_total desc limit 3", null);
            if (rawQuery == null) {
                return rawQuery;
            }
            rawQuery.moveToFirst();
            return rawQuery;
        } catch (Exception e) {
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0046, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0047, code lost:
        if (r2 != null) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0049, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004c, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004e, code lost:
        r0 = r2;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0046 A[ExcHandler: all (r0v5 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:4:0x001b] */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int mo6366c(boolean r6) {
        /*
            r5 = this;
            r2 = 0
            r1 = 0
            if (r6 == 0) goto L_0x0034
            java.lang.String r0 = "st_total"
        L_0x0006:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "select SUM("
            r3.<init>(r4)
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r3 = ") from jpush_statistics"
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r0 = r0.toString()
            android.database.sqlite.SQLiteDatabase r3 = r5.getReadableDatabase()     // Catch:{ Exception -> 0x003e, all -> 0x0046 }
            r4 = 0
            android.database.Cursor r2 = r3.rawQuery(r0, r4)     // Catch:{ Exception -> 0x003e, all -> 0x0046 }
            if (r2 == 0) goto L_0x0037
            r2.moveToFirst()     // Catch:{ Exception -> 0x004d, all -> 0x0046 }
            r0 = 0
            int r0 = r2.getInt(r0)     // Catch:{ Exception -> 0x004d, all -> 0x0046 }
            if (r2 == 0) goto L_0x0033
            r2.close()
        L_0x0033:
            return r0
        L_0x0034:
            java.lang.String r0 = "st_failed"
            goto L_0x0006
        L_0x0037:
            if (r2 == 0) goto L_0x003c
            r2.close()
        L_0x003c:
            r0 = r1
            goto L_0x0033
        L_0x003e:
            r0 = move-exception
            r0 = r2
        L_0x0040:
            if (r0 == 0) goto L_0x003c
            r0.close()
            goto L_0x003c
        L_0x0046:
            r0 = move-exception
            if (r2 == 0) goto L_0x004c
            r2.close()
        L_0x004c:
            throw r0
        L_0x004d:
            r0 = move-exception
            r0 = r2
            goto L_0x0040
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p015d.p016a.C0392g.mo6366c(boolean):int");
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE jpush_statistics (_id INTEGER PRIMARY KEY AUTOINCREMENT ,st_sort_key text not null,st_net text not null,st_conn_ip text not null,st_local_dns text,st_source integer not null,st_failed integer not null,st_total integer not null,st_count_1 integer,st_count_1_3 integer,st_count_3_10 integer,st_count_10 integer);");
        } catch (Exception e) {
        }
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS jpush_statistics");
        onCreate(sQLiteDatabase);
    }
}
