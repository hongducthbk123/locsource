package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResult.zza;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.Hide;
import java.util.concurrent.TimeUnit;

public final class zzco<R extends Result> extends OptionalPendingResult<R> {
    private final BasePendingResult<R> zza;

    public zzco(PendingResult<R> pendingResult) {
        if (!(pendingResult instanceof BasePendingResult)) {
            throw new IllegalArgumentException("OptionalPendingResult can only wrap PendingResults generated by an API call.");
        }
        this.zza = (BasePendingResult) pendingResult;
    }

    public final R await() {
        return this.zza.await();
    }

    public final R await(long j, TimeUnit timeUnit) {
        return this.zza.await(j, timeUnit);
    }

    public final void cancel() {
        this.zza.cancel();
    }

    public final R get() {
        if (isDone()) {
            return await(0, TimeUnit.MILLISECONDS);
        }
        throw new IllegalStateException("Result is not available. Check that isDone() returns true before calling get().");
    }

    public final boolean isCanceled() {
        return this.zza.isCanceled();
    }

    public final boolean isDone() {
        return this.zza.zze();
    }

    public final void setResultCallback(ResultCallback<? super R> resultCallback) {
        this.zza.setResultCallback(resultCallback);
    }

    public final void setResultCallback(ResultCallback<? super R> resultCallback, long j, TimeUnit timeUnit) {
        this.zza.setResultCallback(resultCallback, j, timeUnit);
    }

    @NonNull
    public final <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> resultTransform) {
        return this.zza.then(resultTransform);
    }

    @Hide
    public final void zza(zza zza2) {
        this.zza.zza(zza2);
    }

    @Hide
    public final Integer zzb() {
        return this.zza.zzb();
    }
}
