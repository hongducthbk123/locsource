package com.sensorsdata.analytics.android.sdk;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.util.Log;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SuppressLint({"CommitPrefEdits"})
abstract class PersistentIdentity<T> {
    private static final String LOGTAG = "SA.PersistentIdentity";
    private T item;
    private final Future<SharedPreferences> loadStoredPreferences;
    private final String persistentKey;
    private final PersistentSerializer serializer;

    interface PersistentSerializer<T> {
        T create();

        T load(String str);

        String save(T t);
    }

    PersistentIdentity(Future<SharedPreferences> loadStoredPreferences2, String persistentKey2, PersistentSerializer<T> serializer2) {
        this.loadStoredPreferences = loadStoredPreferences2;
        this.serializer = serializer2;
        this.persistentKey = persistentKey2;
    }

    /* access modifiers changed from: 0000 */
    public T get() {
        T item2;
        if (this.item == null) {
            String data = null;
            synchronized (this.loadStoredPreferences) {
                try {
                    SharedPreferences sharedPreferences = (SharedPreferences) this.loadStoredPreferences.get();
                    if (sharedPreferences != null) {
                        data = sharedPreferences.getString(this.persistentKey, null);
                    }
                } catch (ExecutionException e) {
                    Log.e(LOGTAG, "Cannot read distinct ids from sharedPreferences.", e.getCause());
                } catch (InterruptedException e2) {
                    Log.e(LOGTAG, "Cannot read distinct ids from sharedPreferences.", e2);
                }
                if (data == null) {
                    item2 = this.serializer.create();
                } else {
                    item2 = this.serializer.load(data);
                }
                if (item2 != null) {
                    commit(item2);
                }
            }
        }
        return this.item;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void commit(T r9) {
        /*
            r8 = this;
            r8.item = r9
            java.util.concurrent.Future<android.content.SharedPreferences> r5 = r8.loadStoredPreferences
            monitor-enter(r5)
            r3 = 0
            java.util.concurrent.Future<android.content.SharedPreferences> r4 = r8.loadStoredPreferences     // Catch:{ ExecutionException -> 0x0014, InterruptedException -> 0x0024 }
            java.lang.Object r4 = r4.get()     // Catch:{ ExecutionException -> 0x0014, InterruptedException -> 0x0024 }
            r0 = r4
            android.content.SharedPreferences r0 = (android.content.SharedPreferences) r0     // Catch:{ ExecutionException -> 0x0014, InterruptedException -> 0x0024 }
            r3 = r0
        L_0x0010:
            if (r3 != 0) goto L_0x002d
            monitor-exit(r5)     // Catch:{ all -> 0x0021 }
        L_0x0013:
            return
        L_0x0014:
            r1 = move-exception
            java.lang.String r4 = "SA.PersistentIdentity"
            java.lang.String r6 = "Cannot read distinct ids from sharedPreferences."
            java.lang.Throwable r7 = r1.getCause()     // Catch:{ all -> 0x0021 }
            android.util.Log.e(r4, r6, r7)     // Catch:{ all -> 0x0021 }
            goto L_0x0010
        L_0x0021:
            r4 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0021 }
            throw r4
        L_0x0024:
            r1 = move-exception
            java.lang.String r4 = "SA.PersistentIdentity"
            java.lang.String r6 = "Cannot read distinct ids from sharedPreferences."
            android.util.Log.e(r4, r6, r1)     // Catch:{ all -> 0x0021 }
            goto L_0x0010
        L_0x002d:
            android.content.SharedPreferences$Editor r2 = r3.edit()     // Catch:{ all -> 0x0021 }
            java.lang.String r4 = r8.persistentKey     // Catch:{ all -> 0x0021 }
            com.sensorsdata.analytics.android.sdk.PersistentIdentity$PersistentSerializer r6 = r8.serializer     // Catch:{ all -> 0x0021 }
            T r7 = r8.item     // Catch:{ all -> 0x0021 }
            java.lang.String r6 = r6.save(r7)     // Catch:{ all -> 0x0021 }
            r2.putString(r4, r6)     // Catch:{ all -> 0x0021 }
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0021 }
            r6 = 9
            if (r4 < r6) goto L_0x0049
            r2.apply()     // Catch:{ all -> 0x0021 }
        L_0x0047:
            monitor-exit(r5)     // Catch:{ all -> 0x0021 }
            goto L_0x0013
        L_0x0049:
            r2.commit()     // Catch:{ all -> 0x0021 }
            goto L_0x0047
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sensorsdata.analytics.android.sdk.PersistentIdentity.commit(java.lang.Object):void");
    }
}
