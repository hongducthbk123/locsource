package p004cn.jiguang.p031g.p033b;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import java.io.Serializable;
import java.util.Map.Entry;

/* renamed from: cn.jiguang.g.b.d */
public final class C0513d {
    /* renamed from: a */
    public static C0510a m1028a(ContentResolver contentResolver, String str, C0510a aVar) {
        Cursor cursor;
        try {
            int b = aVar.mo6679b();
            if (b == 0) {
                return aVar;
            }
            String[] strArr = new String[b];
            String[] strArr2 = new String[b];
            int i = 0;
            for (Entry entry : aVar.mo6677a()) {
                strArr[i] = (String) entry.getKey();
                strArr2[i] = String.valueOf(C0510a.m1007a((Serializable) entry.getValue()));
                i++;
            }
            cursor = contentResolver.query(Uri.parse(str), strArr, null, strArr2, null);
            try {
                C0510a aVar2 = new C0510a();
                if (!(cursor == null || aVar == null || aVar.mo6679b() == 0)) {
                    for (int i2 = 0; i2 < cursor.getColumnCount(); i2++) {
                        String columnName = cursor.getColumnName(i2);
                        aVar2.mo6675a(columnName, m1029a(cursor, i2, (T) aVar.mo6680b(columnName, null)));
                    }
                }
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                return aVar2;
            } catch (Throwable th) {
                th = th;
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
            cursor.close();
            throw th;
        }
    }

    /* renamed from: a */
    private static <T extends Serializable> T m1029a(Cursor cursor, int i, T t) {
        T t2 = null;
        try {
            cursor.moveToFirst();
            T string = cursor.getString(i);
            if (string != null) {
                switch (C0510a.m1007a(t)) {
                    case 0:
                        t2 = string;
                        break;
                    case 1:
                        t2 = string;
                        break;
                    case 2:
                        t2 = Integer.valueOf(cursor.getInt(i));
                        break;
                    case 3:
                        t2 = Boolean.valueOf(cursor.getInt(i) > 0);
                        break;
                    case 4:
                        t2 = Long.valueOf(cursor.getLong(i));
                        break;
                    case 5:
                        t2 = Float.valueOf(cursor.getFloat(i));
                        break;
                }
            }
        } catch (Throwable th) {
            Log.e("SpProviderHelper", "convert " + th.getMessage());
        }
        return t2 == null ? t : t2;
    }

    /* renamed from: a */
    public static <T extends Serializable> boolean m1030a(ContentResolver contentResolver, String str, String str2, T t) {
        return m1031b(contentResolver, str, new C0510a().mo6675a(str2, (Serializable) t));
    }

    /* renamed from: b */
    public static boolean m1031b(ContentResolver contentResolver, String str, C0510a aVar) {
        ContentValues contentValues = new ContentValues();
        for (Entry entry : aVar.mo6677a()) {
            switch (C0510a.m1007a((Serializable) entry.getValue())) {
                case 0:
                    contentValues.put((String) entry.getKey(), null);
                    break;
                case 1:
                    contentValues.put((String) entry.getKey(), (String) entry.getValue());
                    break;
                case 2:
                    contentValues.put((String) entry.getKey(), (Integer) entry.getValue());
                    break;
                case 3:
                    contentValues.put((String) entry.getKey(), (Boolean) entry.getValue());
                    break;
                case 4:
                    contentValues.put((String) entry.getKey(), (Long) entry.getValue());
                    break;
                case 5:
                    contentValues.put((String) entry.getKey(), (Float) entry.getValue());
                    break;
            }
        }
        return contentResolver.update(Uri.parse(str), contentValues, null, null) > 0;
    }
}
