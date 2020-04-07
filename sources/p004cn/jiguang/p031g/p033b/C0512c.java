package p004cn.jiguang.p031g.p033b;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map.Entry;
import p004cn.jiguang.p029e.C0501d;

/* renamed from: cn.jiguang.g.b.c */
public final class C0512c {
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004d, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r2 = r8.getLong(r9, 0);
        r4 = (int) r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0058, code lost:
        if (((long) r4) == r2) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005f, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        return java.lang.Integer.valueOf(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        return p004cn.jiguang.p031g.p033b.C0510a.m1008a(r8, r9);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:3:0x000b, B:9:0x0038, B:15:0x0044] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.io.Serializable m1022a(android.content.SharedPreferences r8, java.lang.String r9, int r10) {
        /*
            r0 = 0
            boolean r1 = r8.contains(r9)
            if (r1 != 0) goto L_0x0008
        L_0x0007:
            return r0
        L_0x0008:
            switch(r10) {
                case 0: goto L_0x0037;
                case 1: goto L_0x0077;
                case 2: goto L_0x0043;
                case 3: goto L_0x007d;
                case 4: goto L_0x0060;
                case 5: goto L_0x0087;
                case 6: goto L_0x0092;
                default: goto L_0x000b;
            }
        L_0x000b:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException     // Catch:{ Throwable -> 0x0013 }
            java.lang.String r2 = "[SpHelper], action - readInternal , unsupport type"
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0013 }
            throw r1     // Catch:{ Throwable -> 0x0013 }
        L_0x0013:
            r1 = move-exception
            java.lang.String r2 = "SpHelper"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "exception when get ["
            r3.<init>(r4)
            java.lang.StringBuilder r3 = r3.append(r9)
            java.lang.String r4 = "]"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = r1.getMessage()
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            p004cn.jiguang.p029e.C0501d.m908c(r2, r3, r1)
            goto L_0x0007
        L_0x0037:
            r1 = 0
            java.lang.String r0 = r8.getString(r9, r1)     // Catch:{ ClassCastException -> 0x003d }
            goto L_0x0007
        L_0x003d:
            r1 = move-exception
            java.util.HashSet r0 = p004cn.jiguang.p031g.p033b.C0510a.m1008a(r8, r9)     // Catch:{ Throwable -> 0x0013 }
            goto L_0x0007
        L_0x0043:
            r1 = 0
            int r1 = r8.getInt(r9, r1)     // Catch:{ ClassCastException -> 0x004d }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r1)     // Catch:{ ClassCastException -> 0x004d }
            goto L_0x0007
        L_0x004d:
            r1 = move-exception
            r2 = 0
            long r2 = r8.getLong(r9, r2)     // Catch:{ Throwable -> 0x0013 }
            int r4 = (int) r2     // Catch:{ Throwable -> 0x0013 }
            long r6 = (long) r4     // Catch:{ Throwable -> 0x0013 }
            int r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r2 != 0) goto L_0x005f
            java.lang.Integer r0 = java.lang.Integer.valueOf(r4)     // Catch:{ Throwable -> 0x0013 }
            goto L_0x0007
        L_0x005f:
            throw r1     // Catch:{ Throwable -> 0x0013 }
        L_0x0060:
            r2 = 0
            long r2 = r8.getLong(r9, r2)     // Catch:{ ClassCastException -> 0x006b }
            java.lang.Long r0 = java.lang.Long.valueOf(r2)     // Catch:{ ClassCastException -> 0x006b }
            goto L_0x0007
        L_0x006b:
            r1 = move-exception
            r1 = 0
            int r1 = r8.getInt(r9, r1)     // Catch:{ Throwable -> 0x0013 }
            long r2 = (long) r1     // Catch:{ Throwable -> 0x0013 }
            java.lang.Long r0 = java.lang.Long.valueOf(r2)     // Catch:{ Throwable -> 0x0013 }
            goto L_0x0007
        L_0x0077:
            r1 = 0
            java.lang.String r0 = r8.getString(r9, r1)     // Catch:{ Throwable -> 0x0013 }
            goto L_0x0007
        L_0x007d:
            r1 = 0
            boolean r1 = r8.getBoolean(r9, r1)     // Catch:{ Throwable -> 0x0013 }
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)     // Catch:{ Throwable -> 0x0013 }
            goto L_0x0007
        L_0x0087:
            r1 = 0
            float r1 = r8.getFloat(r9, r1)     // Catch:{ Throwable -> 0x0013 }
            java.lang.Float r0 = java.lang.Float.valueOf(r1)     // Catch:{ Throwable -> 0x0013 }
            goto L_0x0007
        L_0x0092:
            java.util.HashSet r0 = p004cn.jiguang.p031g.p033b.C0510a.m1008a(r8, r9)     // Catch:{ Throwable -> 0x0013 }
            goto L_0x0007
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p031g.p033b.C0512c.m1022a(android.content.SharedPreferences, java.lang.String, int):java.io.Serializable");
    }

    /* renamed from: a */
    public static <T extends Serializable> T m1023a(SharedPreferences sharedPreferences, String str, T t) {
        try {
            Serializable a = m1022a(sharedPreferences, str, C0510a.m1007a(t));
            return a == null ? t : a;
        } catch (Throwable th) {
            C0501d.m908c("SpHelper", th.getMessage(), th);
            return t;
        }
    }

    /* renamed from: a */
    private static <T extends Serializable> void m1024a(Editor editor, String str, T t) {
        if (t == null) {
            editor.remove(str);
        } else if (t instanceof Boolean) {
            editor.putBoolean(str, ((Boolean) t).booleanValue());
        } else if (t instanceof Integer) {
            editor.putInt(str, ((Integer) t).intValue());
        } else if (t instanceof Long) {
            editor.putLong(str, ((Long) t).longValue());
        } else if (t instanceof Float) {
            editor.putFloat(str, ((Float) t).floatValue());
        } else if (t instanceof String) {
            editor.putString(str, (String) t);
        } else if ((t instanceof HashSet) && VERSION.SDK_INT >= 11) {
            try {
                editor.putStringSet(str, (HashSet) t);
            } catch (ClassCastException e) {
            }
        }
    }

    /* renamed from: a */
    public static boolean m1025a(SharedPreferences sharedPreferences, ContentValues contentValues) {
        if (contentValues == null) {
            return true;
        }
        Editor edit = sharedPreferences.edit();
        for (Entry entry : contentValues.valueSet()) {
            m1024a(edit, (String) entry.getKey(), (T) (Serializable) entry.getValue());
        }
        return edit.commit();
    }

    /* renamed from: a */
    public static boolean m1026a(SharedPreferences sharedPreferences, C0510a aVar) {
        if (aVar == null) {
            return true;
        }
        Editor edit = sharedPreferences.edit();
        for (Entry entry : aVar.mo6677a()) {
            m1024a(edit, (String) entry.getKey(), (T) (Serializable) entry.getValue());
        }
        return edit.commit();
    }

    /* renamed from: b */
    public static <T extends Serializable> boolean m1027b(SharedPreferences sharedPreferences, String str, T t) {
        Editor edit = sharedPreferences.edit();
        m1024a(edit, str, t);
        return edit.commit();
    }
}
