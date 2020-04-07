package p004cn.jiguang.p015d.p016a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;

/* renamed from: cn.jiguang.d.a.e */
public abstract class C0390e extends SQLiteOpenHelper {

    /* renamed from: a */
    private volatile int f218a = 0;

    /* renamed from: b */
    private volatile int f219b = 0;

    /* renamed from: c */
    private volatile SQLiteDatabase f220c;

    /* renamed from: d */
    private volatile SQLiteDatabase f221d;

    /* renamed from: e */
    private final Object f222e = new Object();

    /* renamed from: f */
    private final Object f223f = new Object();

    /* renamed from: g */
    private final Context f224g;

    /* renamed from: h */
    private final String f225h;

    /* renamed from: i */
    private final int f226i;

    /* renamed from: j */
    private final CursorFactory f227j;

    public C0390e(Context context, String str, CursorFactory cursorFactory, int i) {
        super(context, str, null, 1);
        this.f224g = context;
        this.f225h = str;
        this.f226i = 1;
        this.f227j = null;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean mo6356a(boolean r4) {
        /*
            r3 = this;
            r0 = 1
            if (r4 == 0) goto L_0x0017
            java.lang.Object r1 = r3.f222e     // Catch:{ Exception -> 0x0014 }
            monitor-enter(r1)     // Catch:{ Exception -> 0x0014 }
            r3.getWritableDatabase()     // Catch:{ all -> 0x0011 }
            int r2 = r3.f219b     // Catch:{ all -> 0x0011 }
            int r2 = r2 + 1
            r3.f219b = r2     // Catch:{ all -> 0x0011 }
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
            java.lang.Object r1 = r3.f223f     // Catch:{ Exception -> 0x0014 }
            monitor-enter(r1)     // Catch:{ Exception -> 0x0014 }
            r3.getReadableDatabase()     // Catch:{ all -> 0x0025 }
            int r2 = r3.f218a     // Catch:{ all -> 0x0025 }
            int r2 = r2 + 1
            r3.f218a = r2     // Catch:{ all -> 0x0025 }
            monitor-exit(r1)     // Catch:{ all -> 0x0025 }
            goto L_0x0010
        L_0x0025:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0025 }
            throw r0     // Catch:{ Exception -> 0x0014 }
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p015d.p016a.C0390e.mo6356a(boolean):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0019, code lost:
        if (r3 <= 0) goto L_0x001b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0047, code lost:
        if (r3 <= 0) goto L_0x0049;
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo6357b(boolean r5) {
        /*
            r4 = this;
            r1 = 1
            r0 = 0
            if (r5 == 0) goto L_0x0032
            java.lang.Object r2 = r4.f222e
            monitor-enter(r2)
            android.database.sqlite.SQLiteDatabase r3 = r4.f221d     // Catch:{ all -> 0x002f }
            if (r3 == 0) goto L_0x001b
            android.database.sqlite.SQLiteDatabase r3 = r4.f221d     // Catch:{ all -> 0x002f }
            boolean r3 = r3.isOpen()     // Catch:{ all -> 0x002f }
            if (r3 == 0) goto L_0x001b
            int r3 = r4.f219b     // Catch:{ all -> 0x002f }
            int r3 = r3 + -1
            r4.f219b = r3     // Catch:{ all -> 0x002f }
            if (r3 > 0) goto L_0x001c
        L_0x001b:
            r0 = r1
        L_0x001c:
            if (r0 == 0) goto L_0x002d
            r0 = 0
            r4.f219b = r0     // Catch:{ all -> 0x002f }
            android.database.sqlite.SQLiteDatabase r0 = r4.f221d     // Catch:{ all -> 0x002f }
            if (r0 == 0) goto L_0x002a
            android.database.sqlite.SQLiteDatabase r0 = r4.f221d     // Catch:{ all -> 0x002f }
            r0.close()     // Catch:{ all -> 0x002f }
        L_0x002a:
            r0 = 0
            r4.f221d = r0     // Catch:{ all -> 0x002f }
        L_0x002d:
            monitor-exit(r2)     // Catch:{ all -> 0x002f }
        L_0x002e:
            return
        L_0x002f:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x002f }
            throw r0
        L_0x0032:
            java.lang.Object r2 = r4.f223f
            monitor-enter(r2)
            android.database.sqlite.SQLiteDatabase r3 = r4.f220c     // Catch:{ all -> 0x005d }
            if (r3 == 0) goto L_0x0049
            android.database.sqlite.SQLiteDatabase r3 = r4.f220c     // Catch:{ all -> 0x005d }
            boolean r3 = r3.isOpen()     // Catch:{ all -> 0x005d }
            if (r3 == 0) goto L_0x0049
            int r3 = r4.f218a     // Catch:{ all -> 0x005d }
            int r3 = r3 + -1
            r4.f218a = r3     // Catch:{ all -> 0x005d }
            if (r3 > 0) goto L_0x004a
        L_0x0049:
            r0 = r1
        L_0x004a:
            if (r0 == 0) goto L_0x005b
            r0 = 0
            r4.f218a = r0     // Catch:{ all -> 0x005d }
            android.database.sqlite.SQLiteDatabase r0 = r4.f220c     // Catch:{ all -> 0x005d }
            if (r0 == 0) goto L_0x0058
            android.database.sqlite.SQLiteDatabase r0 = r4.f220c     // Catch:{ all -> 0x005d }
            r0.close()     // Catch:{ all -> 0x005d }
        L_0x0058:
            r0 = 0
            r4.f220c = r0     // Catch:{ all -> 0x005d }
        L_0x005b:
            monitor-exit(r2)     // Catch:{ all -> 0x005d }
            goto L_0x002e
        L_0x005d:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x005d }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p015d.p016a.C0390e.mo6357b(boolean):void");
    }

    @Deprecated
    public void close() {
    }

    public SQLiteDatabase getReadableDatabase() {
        if (this.f220c == null || !this.f220c.isOpen()) {
            synchronized (this.f223f) {
                if (this.f220c == null || !this.f220c.isOpen()) {
                    try {
                        getWritableDatabase();
                    } catch (SQLiteException e) {
                    }
                    String path = this.f224g.getDatabasePath(this.f225h).getPath();
                    this.f220c = SQLiteDatabase.openDatabase(path, this.f227j, 1);
                    if (this.f220c.getVersion() != this.f226i) {
                        throw new SQLiteException("Can't upgrade read-only database from version " + this.f220c.getVersion() + " to " + this.f226i + ": " + path);
                    }
                    this.f218a = 0;
                    onOpen(this.f220c);
                }
            }
        }
        return this.f220c;
    }

    public SQLiteDatabase getWritableDatabase() {
        if (this.f221d == null || !this.f221d.isOpen()) {
            synchronized (this.f222e) {
                if (this.f221d == null || !this.f221d.isOpen()) {
                    this.f219b = 0;
                    this.f221d = super.getWritableDatabase();
                    if (VERSION.SDK_INT >= 11) {
                        this.f221d.enableWriteAheadLogging();
                    }
                }
            }
        }
        return this.f221d;
    }
}
