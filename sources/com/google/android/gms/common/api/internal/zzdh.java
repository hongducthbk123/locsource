package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.zzbq;
import java.lang.ref.WeakReference;

public final class zzdh<R extends Result> extends TransformedResult<R> implements ResultCallback<R> {
    /* access modifiers changed from: private */
    public ResultTransform<? super R, ? extends Result> zza = null;
    /* access modifiers changed from: private */
    public zzdh<? extends Result> zzb = null;
    private volatile ResultCallbacks<? super R> zzc = null;
    private PendingResult<R> zzd = null;
    /* access modifiers changed from: private */
    public final Object zze = new Object();
    private Status zzf = null;
    /* access modifiers changed from: private */
    public final WeakReference<GoogleApiClient> zzg;
    /* access modifiers changed from: private */
    public final zzdj zzh;
    private boolean zzi = false;

    public zzdh(WeakReference<GoogleApiClient> weakReference) {
        zzbq.zza(weakReference, (Object) "GoogleApiClient reference must not be null");
        this.zzg = weakReference;
        GoogleApiClient googleApiClient = (GoogleApiClient) this.zzg.get();
        this.zzh = new zzdj(this, googleApiClient != null ? googleApiClient.zzc() : Looper.getMainLooper());
    }

    /* access modifiers changed from: private */
    public static void zza(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e) {
                String valueOf = String.valueOf(result);
                Log.w("TransformedResultImpl", new StringBuilder(String.valueOf(valueOf).length() + 18).append("Unable to release ").append(valueOf).toString(), e);
            }
        }
    }

    /* access modifiers changed from: private */
    public final void zza(Status status) {
        synchronized (this.zze) {
            this.zzf = status;
            zzb(this.zzf);
        }
    }

    private final void zzb() {
        if (this.zza != null || this.zzc != null) {
            GoogleApiClient googleApiClient = (GoogleApiClient) this.zzg.get();
            if (!(this.zzi || this.zza == null || googleApiClient == null)) {
                googleApiClient.zza(this);
                this.zzi = true;
            }
            if (this.zzf != null) {
                zzb(this.zzf);
            } else if (this.zzd != null) {
                this.zzd.setResultCallback(this);
            }
        }
    }

    private final void zzb(Status status) {
        synchronized (this.zze) {
            if (this.zza != null) {
                Status onFailure = this.zza.onFailure(status);
                zzbq.zza(onFailure, (Object) "onFailure must not return null");
                this.zzb.zza(onFailure);
            } else if (zzc()) {
                this.zzc.onFailure(status);
            }
        }
    }

    private final boolean zzc() {
        return (this.zzc == null || ((GoogleApiClient) this.zzg.get()) == null) ? false : true;
    }

    public final void andFinally(@NonNull ResultCallbacks<? super R> resultCallbacks) {
        boolean z = true;
        synchronized (this.zze) {
            zzbq.zza(this.zzc == null, (Object) "Cannot call andFinally() twice.");
            if (this.zza != null) {
                z = false;
            }
            zzbq.zza(z, (Object) "Cannot call then() and andFinally() on the same TransformedResult.");
            this.zzc = resultCallbacks;
            zzb();
        }
    }

    public final void onResult(R r) {
        synchronized (this.zze) {
            if (!r.getStatus().isSuccess()) {
                zza(r.getStatus());
                zza((Result) r);
            } else if (this.zza != null) {
                zzcs.zza().submit(new zzdi(this, r));
            } else if (zzc()) {
                this.zzc.onSuccess(r);
            }
        }
    }

    @NonNull
    public final <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> resultTransform) {
        zzdh<? extends Result> zzdh;
        boolean z = true;
        synchronized (this.zze) {
            zzbq.zza(this.zza == null, (Object) "Cannot call then() twice.");
            if (this.zzc != null) {
                z = false;
            }
            zzbq.zza(z, (Object) "Cannot call then() and andFinally() on the same TransformedResult.");
            this.zza = resultTransform;
            zzdh = new zzdh<>(this.zzg);
            this.zzb = zzdh;
            zzb();
        }
        return zzdh;
    }

    /* access modifiers changed from: 0000 */
    public final void zza() {
        this.zzc = null;
    }

    public final void zza(PendingResult<?> pendingResult) {
        synchronized (this.zze) {
            this.zzd = pendingResult;
            zzb();
        }
    }
}
