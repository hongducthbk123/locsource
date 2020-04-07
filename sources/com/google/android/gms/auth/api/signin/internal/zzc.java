package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.support.p000v4.content.AsyncTaskLoader;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzcu;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public final class zzc extends AsyncTaskLoader<Void> implements zzcu {
    private Semaphore zza = new Semaphore(0);
    private Set<GoogleApiClient> zzb;

    public zzc(Context context, Set<GoogleApiClient> set) {
        super(context);
        this.zzb = set;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public final Void loadInBackground() {
        int i;
        int i2 = 0;
        Iterator it = this.zzb.iterator();
        while (true) {
            i = i2;
            if (it.hasNext()) {
                i2 = ((GoogleApiClient) it.next()).zza((zzcu) this) ? i + 1 : i;
            } else {
                try {
                    break;
                } catch (InterruptedException e) {
                    Log.i("GACSignInLoader", "Unexpected InterruptedException", e);
                    Thread.currentThread().interrupt();
                }
            }
        }
        this.zza.tryAcquire(i, 5, TimeUnit.SECONDS);
        return null;
    }

    /* access modifiers changed from: protected */
    public final void onStartLoading() {
        this.zza.drainPermits();
        forceLoad();
    }

    public final void zza() {
        this.zza.release();
    }
}
