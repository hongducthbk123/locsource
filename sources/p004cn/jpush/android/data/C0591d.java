package p004cn.jpush.android.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.android.vending.expansion.zipfile.APEZProvider;

/* renamed from: cn.jpush.android.data.d */
public final class C0591d extends C0593f {

    /* renamed from: a */
    private static final String[] f831a = {APEZProvider.FILEID, "ln_id", "ln_count", "ln_remove", "ln_type", "ln_extra", "ln_trigger_time", "ln_add_time"};

    /* renamed from: b */
    private static volatile C0591d f832b;

    /* renamed from: c */
    private static final Object f833c = new Object();

    private C0591d(Context context) {
        super(context, "jpush_local_notification.db", null, 1);
    }

    /* renamed from: a */
    public static C0591d m1329a(Context context) {
        if (f832b == null) {
            synchronized (f833c) {
                if (f832b == null) {
                    f832b = new C0591d(context.getApplicationContext());
                }
            }
        }
        return f832b;
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE t_localnotification (_id INTEGER PRIMARY KEY AUTOINCREMENT ,ln_id long not null,ln_count integer not null,ln_remove integer not null,ln_type integer not null,ln_extra text ,ln_trigger_time long ,ln_add_time long );");
        } catch (Exception e) {
        }
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS t_localnotification");
        onCreate(sQLiteDatabase);
    }

    /* renamed from: a */
    public static C0592e m1330a(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0) {
            return null;
        }
        try {
            C0592e eVar = new C0592e();
            eVar.mo6893a(cursor.getLong(1));
            eVar.mo6892a(cursor.getInt(2));
            eVar.mo6896b(cursor.getInt(3));
            eVar.mo6899c(cursor.getInt(4));
            eVar.mo6894a(cursor.getString(5));
            eVar.mo6900c(cursor.getLong(6));
            eVar.mo6897b(cursor.getLong(7));
            return eVar;
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public final long mo6883a(long j, int i, int i2, int i3, String str, long j2, long j3) {
        long j4 = 0;
        if (mo6905a(true)) {
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("ln_id", Long.valueOf(j));
                contentValues.put("ln_count", Integer.valueOf(1));
                contentValues.put("ln_remove", Integer.valueOf(0));
                contentValues.put("ln_type", Integer.valueOf(0));
                contentValues.put("ln_extra", str);
                contentValues.put("ln_trigger_time", Long.valueOf(j2));
                contentValues.put("ln_add_time", Long.valueOf(j3));
                j4 = getWritableDatabase().insert("t_localnotification", null, contentValues);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                mo6906b(true);
            }
        }
        return j4;
    }

    /* renamed from: b */
    public final long mo6888b(long j, int i, int i2, int i3, String str, long j2, long j3) {
        long j4 = 0;
        if (!mo6905a(true)) {
            return j4;
        }
        try {
            String str2 = "ln_id=" + j;
            ContentValues contentValues = new ContentValues();
            contentValues.put("ln_id", Long.valueOf(j));
            contentValues.put("ln_count", Integer.valueOf(i));
            contentValues.put("ln_remove", Integer.valueOf(i2));
            contentValues.put("ln_type", Integer.valueOf(0));
            contentValues.put("ln_extra", str);
            contentValues.put("ln_trigger_time", Long.valueOf(j2));
            contentValues.put("ln_add_time", Long.valueOf(j3));
            long update = (long) getWritableDatabase().update("t_localnotification", contentValues, str2, null);
            return update;
        } catch (Exception e) {
            e.printStackTrace();
            return j4;
        } finally {
            mo6906b(true);
        }
    }

    /* renamed from: a */
    public final Cursor mo6885a(long j, long j2) {
        try {
            return getReadableDatabase().query(true, "t_localnotification", f831a, "ln_count>0 and ln_trigger_time<" + (300000 + j) + " and ln_trigger_time" + ">" + j, null, null, null, null, null);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    public final Cursor mo6884a(int i, long j) {
        try {
            return getReadableDatabase().query(true, "t_localnotification", f831a, "ln_count=" + 1 + " and ln_trigger_time" + "<" + j, null, null, null, null, null);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    public final C0592e mo6886a(long j, int i) throws Exception {
        Cursor cursor;
        if (mo6905a(false)) {
            try {
                cursor = getReadableDatabase().query(true, "t_localnotification", f831a, "ln_id=" + j + " and ln_type" + "=0", null, null, null, null, null);
                if (cursor != null) {
                    try {
                        cursor.moveToFirst();
                    } catch (Throwable th) {
                        th = th;
                        if (cursor != null) {
                            cursor.close();
                        }
                        mo6906b(false);
                        throw th;
                    }
                }
                C0592e a = m1330a(cursor);
                if (cursor != null) {
                    cursor.close();
                }
                mo6906b(false);
                return a;
            } catch (Throwable th2) {
                th = th2;
                cursor = null;
            }
        } else {
            throw new Exception("open database failed");
        }
    }

    /* renamed from: a */
    public final int mo6882a(long j) {
        if (mo6905a(true)) {
            try {
                return getWritableDatabase().delete("t_localnotification", "ln_id=" + j, null);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                mo6906b(true);
            }
        }
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:45:0x008a, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x008b, code lost:
        r1 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0094, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0095, code lost:
        r1 = r0;
        r0 = null;
        r10 = r2;
        r2 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00a0, code lost:
        r1 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00a1, code lost:
        r10 = r2;
        r2 = false;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x008a A[ExcHandler: all (th java.lang.Throwable), Splitter:B:6:0x0025] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:64:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int[] mo6887a() {
        /*
            r13 = this;
            r10 = 0
            r12 = 1
            r11 = 0
            java.lang.String r4 = "1"
            boolean r0 = r13.mo6905a(r11)
            if (r0 == 0) goto L_0x00ac
            r0 = 1
            java.lang.String[] r3 = new java.lang.String[r0]     // Catch:{ Exception -> 0x0064, all -> 0x0079 }
            r0 = 0
            java.lang.String r1 = "ln_id"
            r3[r0] = r1     // Catch:{ Exception -> 0x0064, all -> 0x0079 }
            android.database.sqlite.SQLiteDatabase r0 = r13.getReadableDatabase()     // Catch:{ Exception -> 0x0064, all -> 0x0079 }
            r1 = 1
            java.lang.String r2 = "t_localnotification"
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r2 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x0064, all -> 0x0079 }
            if (r2 == 0) goto L_0x00aa
            int r0 = r2.getCount()     // Catch:{ Exception -> 0x0094, all -> 0x008a }
            if (r0 <= 0) goto L_0x00aa
            int r0 = r2.getCount()     // Catch:{ Exception -> 0x0094, all -> 0x008a }
            int[] r10 = new int[r0]     // Catch:{ Exception -> 0x0094, all -> 0x008a }
            r2.moveToFirst()     // Catch:{ Exception -> 0x009a, all -> 0x008a }
            r0 = r11
        L_0x0035:
            r1 = 0
            int r1 = r2.getInt(r1)     // Catch:{ Exception -> 0x009a, all -> 0x008a }
            r10[r0] = r1     // Catch:{ Exception -> 0x009a, all -> 0x008a }
            int r0 = r0 + 1
            boolean r1 = r2.moveToNext()     // Catch:{ Exception -> 0x009a, all -> 0x008a }
            if (r1 != 0) goto L_0x0035
            r0 = r10
        L_0x0045:
            r1 = 1
            boolean r1 = r13.mo6905a(r1)     // Catch:{ Exception -> 0x00a0, all -> 0x008a }
            if (r1 == 0) goto L_0x00a8
            java.lang.String r1 = "delete from t_localnotification"
            android.database.sqlite.SQLiteDatabase r3 = r13.getWritableDatabase()     // Catch:{ Exception -> 0x00a4, all -> 0x008d }
            r3.execSQL(r1)     // Catch:{ Exception -> 0x00a4, all -> 0x008d }
            r1 = r12
        L_0x0056:
            if (r2 == 0) goto L_0x005b
            r2.close()
        L_0x005b:
            r13.mo6906b(r11)
            if (r1 == 0) goto L_0x0063
            r13.mo6906b(r12)
        L_0x0063:
            return r0
        L_0x0064:
            r0 = move-exception
            r1 = r0
            r2 = r11
            r0 = r10
        L_0x0068:
            r1.printStackTrace()     // Catch:{ all -> 0x0090 }
            if (r10 == 0) goto L_0x0070
            r10.close()
        L_0x0070:
            r13.mo6906b(r11)
            if (r2 == 0) goto L_0x0063
            r13.mo6906b(r12)
            goto L_0x0063
        L_0x0079:
            r0 = move-exception
            r2 = r10
            r1 = r11
        L_0x007c:
            if (r2 == 0) goto L_0x0081
            r2.close()
        L_0x0081:
            r13.mo6906b(r11)
            if (r1 == 0) goto L_0x0089
            r13.mo6906b(r12)
        L_0x0089:
            throw r0
        L_0x008a:
            r0 = move-exception
            r1 = r11
            goto L_0x007c
        L_0x008d:
            r0 = move-exception
            r1 = r12
            goto L_0x007c
        L_0x0090:
            r0 = move-exception
            r1 = r2
            r2 = r10
            goto L_0x007c
        L_0x0094:
            r0 = move-exception
            r1 = r0
            r0 = r10
            r10 = r2
            r2 = r11
            goto L_0x0068
        L_0x009a:
            r0 = move-exception
            r1 = r0
            r0 = r10
            r10 = r2
            r2 = r11
            goto L_0x0068
        L_0x00a0:
            r1 = move-exception
            r10 = r2
            r2 = r11
            goto L_0x0068
        L_0x00a4:
            r1 = move-exception
            r10 = r2
            r2 = r12
            goto L_0x0068
        L_0x00a8:
            r1 = r11
            goto L_0x0056
        L_0x00aa:
            r0 = r10
            goto L_0x0045
        L_0x00ac:
            r0 = r10
            goto L_0x0063
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jpush.android.data.C0591d.mo6887a():int[]");
    }
}
