package p004cn.jpush.android.service;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.io.Serializable;
import p004cn.jiguang.p031g.p033b.C0511b;
import p004cn.jiguang.p031g.p033b.C0514e;

/* renamed from: cn.jpush.android.service.DataProvider */
public class DataProvider extends ContentProvider {
    private static final String TAG = "DataProvider";

    private String getName(Uri uri) {
        String str;
        if (uri != null) {
            try {
                str = uri.getPath();
            } catch (Throwable th) {
                return null;
            }
        } else {
            str = null;
        }
        if (str == null) {
            str = null;
        } else if (str.charAt(0) == '/') {
            str = str.substring(1);
        }
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        return null;
    }

    private Serializable getWithCache(C0514e eVar, C0511b bVar, String str, int i) {
        Serializable a = bVar.mo6682a(str, i);
        if (a != null || eVar == null) {
            return a;
        }
        Serializable a2 = eVar.mo6687a(str, i);
        bVar.mo6685a(str, (Object) a2);
        return a2;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        String name = getName(uri);
        if (name == null) {
            return 0;
        }
        C0511b.m1017a(name).mo6683a();
        C0514e a = C0514e.m1032a(getContext(), name);
        return (a == null || !a.mo6689a()) ? 0 : 1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARNING: Removed duplicated region for block: B:20:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getType(android.net.Uri r7) {
        /*
            r6 = this;
            r0 = 0
            java.lang.String r3 = r6.getName(r7)
            if (r3 != 0) goto L_0x0008
        L_0x0007:
            return r0
        L_0x0008:
            r2 = -1
            java.lang.String r1 = "key"
            java.lang.String r1 = r7.getQueryParameter(r1)     // Catch:{ Throwable -> 0x003d }
            java.lang.String r4 = "type"
            java.lang.String r4 = r7.getQueryParameter(r4)     // Catch:{ Throwable -> 0x0043 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Throwable -> 0x0043 }
            int r2 = r4.intValue()     // Catch:{ Throwable -> 0x0043 }
            r5 = r2
            r2 = r1
            r1 = r5
        L_0x0021:
            if (r1 < 0) goto L_0x0007
            r4 = 6
            if (r1 > r4) goto L_0x0007
            android.content.Context r4 = r6.getContext()
            cn.jiguang.g.b.e r4 = p004cn.jiguang.p031g.p033b.C0514e.m1032a(r4, r3)
            cn.jiguang.g.b.b r3 = p004cn.jiguang.p031g.p033b.C0511b.m1017a(r3)
            java.io.Serializable r1 = r6.getWithCache(r4, r3, r2, r1)
            if (r1 == 0) goto L_0x0007
            java.lang.String r0 = r1.toString()
            goto L_0x0007
        L_0x003d:
            r1 = move-exception
            r1 = r0
        L_0x003f:
            r5 = r2
            r2 = r1
            r1 = r5
            goto L_0x0021
        L_0x0043:
            r4 = move-exception
            goto L_0x003f
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jpush.android.service.DataProvider.getType(android.net.Uri):java.lang.String");
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return uri;
    }

    public boolean onCreate() {
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        if (strArr == null || strArr.length == 0 || strArr2 == null || strArr2.length == 0 || strArr.length != strArr2.length) {
            return null;
        }
        String name = getName(uri);
        if (name == null) {
            return null;
        }
        try {
            C0514e a = C0514e.m1032a(getContext(), name);
            C0511b a2 = C0511b.m1017a(name);
            int length = strArr.length;
            MatrixCursor matrixCursor = new MatrixCursor(strArr, 1);
            Object[] objArr = new Object[length];
            for (int i = 0; i < length; i++) {
                objArr[i] = getWithCache(a, a2, strArr[i], Integer.valueOf(strArr2[i]).intValue());
                if (objArr[i] != null) {
                    if (objArr[i].equals(Boolean.valueOf(false))) {
                        objArr[i] = Integer.valueOf(0);
                    } else if (objArr[i].equals(Boolean.valueOf(true))) {
                        objArr[i] = Integer.valueOf(1);
                    }
                }
            }
            matrixCursor.addRow(objArr);
            return matrixCursor;
        } catch (NumberFormatException e) {
            Log.e(TAG, "selectionArgs should be int");
        } catch (Throwable th) {
            Log.e(TAG, th.getMessage());
            return null;
        }
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        if (contentValues == null || contentValues.size() == 0) {
            return 0;
        }
        String name = getName(uri);
        if (name == null) {
            return 0;
        }
        C0511b.m1017a(name).mo6684a(contentValues);
        C0514e a = C0514e.m1032a(getContext(), name);
        if (a == null || !a.mo6690a(contentValues)) {
            return 0;
        }
        return contentValues.size();
    }
}
