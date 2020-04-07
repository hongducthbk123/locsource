package p004cn.jpush.android.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;

/* renamed from: cn.jpush.android.data.f */
public abstract class C0593f extends SQLiteOpenHelper {

    /* renamed from: a */
    private volatile int f841a = 0;

    /* renamed from: b */
    private volatile int f842b = 0;

    /* renamed from: c */
    private volatile SQLiteDatabase f843c;

    /* renamed from: d */
    private volatile SQLiteDatabase f844d;

    /* renamed from: e */
    private final Object f845e = new Object();

    /* renamed from: f */
    private final Object f846f = new Object();

    /* renamed from: g */
    private final Context f847g;

    /* renamed from: h */
    private final String f848h;

    /* renamed from: i */
    private final int f849i;

    /* renamed from: j */
    private final CursorFactory f850j;

    public C0593f(Context context, String str, CursorFactory cursorFactory, int i) {
        super(context, str, null, 1);
        this.f847g = context;
        this.f848h = str;
        this.f849i = 1;
        this.f850j = null;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean mo6905a(boolean r4) {
        /*
            r3 = this;
            r0 = 1
            if (r4 == 0) goto L_0x0017
            java.lang.Object r1 = r3.f845e     // Catch:{ Exception -> 0x0014 }
            monitor-enter(r1)     // Catch:{ Exception -> 0x0014 }
            r3.getWritableDatabase()     // Catch:{ all -> 0x0011 }
            int r2 = r3.f842b     // Catch:{ all -> 0x0011 }
            int r2 = r2 + 1
            r3.f842b = r2     // Catch:{ all -> 0x0011 }
            monitor-exit(r1)     // Catch:{ all -> 0x0011 }
        L_0x0010:
            return r0
        L_0x0011:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0011 }
            throw r0     // Catch:{ Exception -> 0x0014 }
        L_0x0014:
            r0 = move-exception
            r0 = 0
            goto L_0x0010
        L_0x0017:
            java.lang.Object r1 = r3.f846f     // Catch:{ Exception -> 0x0014 }
            monitor-enter(r1)     // Catch:{ Exception -> 0x0014 }
            r3.getReadableDatabase()     // Catch:{ all -> 0x0025 }
            int r2 = r3.f841a     // Catch:{ all -> 0x0025 }
            int r2 = r2 + 1
            r3.f841a = r2     // Catch:{ all -> 0x0025 }
            monitor-exit(r1)     // Catch:{ all -> 0x0025 }
            goto L_0x0010
        L_0x0025:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0025 }
            throw r0     // Catch:{ Exception -> 0x0014 }
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jpush.android.data.C0593f.mo6905a(boolean):boolean");
    }

    public SQLiteDatabase getReadableDatabase() {
        if (this.f843c == null || !this.f843c.isOpen()) {
            synchronized (this.f846f) {
                if (this.f843c == null || !this.f843c.isOpen()) {
                    try {
                        getWritableDatabase();
                    } catch (SQLiteException e) {
                    }
                    String path = this.f847g.getDatabasePath(this.f848h).getPath();
                    this.f843c = SQLiteDatabase.openDatabase(path, this.f850j, 1);
                    if (this.f843c.getVersion() != this.f849i) {
                        throw new SQLiteException("Can't upgrade read-only database from version " + this.f843c.getVersion() + " to " + this.f849i + ": " + path);
                    }
                    this.f841a = 0;
                    onOpen(this.f843c);
                }
            }
        }
        return this.f843c;
    }

    public SQLiteDatabase getWritableDatabase() {
        if (this.f844d == null || !this.f844d.isOpen()) {
            synchronized (this.f845e) {
                if (this.f844d == null || !this.f844d.isOpen()) {
                    this.f842b = 0;
                    this.f844d = super.getWritableDatabase();
                    if (VERSION.SDK_INT >= 11) {
                        this.f844d.enableWriteAheadLogging();
                    }
                }
            }
        }
        return this.f844d;
    }

    @Deprecated
    public void close() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0019, code lost:
        if (r3 <= 0) goto L_0x001b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0047, code lost:
        if (r3 <= 0) goto L_0x0049;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo6906b(boolean r5) {
        /*
            r4 = this;
            r1 = 1
            r0 = 0
            if (r5 == 0) goto L_0x0032
            java.lang.Object r2 = r4.f845e
            monitor-enter(r2)
            android.database.sqlite.SQLiteDatabase r3 = r4.f844d     // Catch:{ all -> 0x002f }
            if (r3 == 0) goto L_0x001b
            android.database.sqlite.SQLiteDatabase r3 = r4.f844d     // Catch:{ all -> 0x002f }
            boolean r3 = r3.isOpen()     // Catch:{ all -> 0x002f }
            if (r3 == 0) goto L_0x001b
            int r3 = r4.f842b     // Catch:{ all -> 0x002f }
            int r3 = r3 + -1
            r4.f842b = r3     // Catch:{ all -> 0x002f }
            if (r3 > 0) goto L_0x001c
        L_0x001b:
            r0 = r1
        L_0x001c:
            if (r0 == 0) goto L_0x002d
            r0 = 0
            r4.f842b = r0     // Catch:{ all -> 0x002f }
            android.database.sqlite.SQLiteDatabase r0 = r4.f844d     // Catch:{ all -> 0x002f }
            if (r0 == 0) goto L_0x002a
            android.database.sqlite.SQLiteDatabase r0 = r4.f844d     // Catch:{ all -> 0x002f }
            r0.close()     // Catch:{ all -> 0x002f }
        L_0x002a:
            r0 = 0
            r4.f844d = r0     // Catch:{ all -> 0x002f }
        L_0x002d:
            monitor-exit(r2)     // Catch:{ all -> 0x002f }
        L_0x002e:
            return
        L_0x002f:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x002f }
            throw r0
        L_0x0032:
            java.lang.Object r2 = r4.f846f
            monitor-enter(r2)
            android.database.sqlite.SQLiteDatabase r3 = r4.f843c     // Catch:{ all -> 0x005d }
            if (r3 == 0) goto L_0x0049
            android.database.sqlite.SQLiteDatabase r3 = r4.f843c     // Catch:{ all -> 0x005d }
            boolean r3 = r3.isOpen()     // Catch:{ all -> 0x005d }
            if (r3 == 0) goto L_0x0049
            int r3 = r4.f841a     // Catch:{ all -> 0x005d }
            int r3 = r3 + -1
            r4.f841a = r3     // Catch:{ all -> 0x005d }
            if (r3 > 0) goto L_0x004a
        L_0x0049:
            r0 = r1
        L_0x004a:
            if (r0 == 0) goto L_0x005b
            r0 = 0
            r4.f841a = r0     // Catch:{ all -> 0x005d }
            android.database.sqlite.SQLiteDatabase r0 = r4.f843c     // Catch:{ all -> 0x005d }
            if (r0 == 0) goto L_0x0058
            android.database.sqlite.SQLiteDatabase r0 = r4.f843c     // Catch:{ all -> 0x005d }
            r0.close()     // Catch:{ all -> 0x005d }
        L_0x0058:
            r0 = 0
            r4.f843c = r0     // Catch:{ all -> 0x005d }
        L_0x005b:
            monitor-exit(r2)     // Catch:{ all -> 0x005d }
            goto L_0x002e
        L_0x005d:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x005d }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jpush.android.data.C0593f.mo6906b(boolean):void");
    }
}
