package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

final class zzc<TResult, TContinuationResult> implements OnFailureListener, OnSuccessListener<TContinuationResult>, zzm<TResult> {
    private final Executor zza;
    /* access modifiers changed from: private */
    public final Continuation<TResult, Task<TContinuationResult>> zzb;
    /* access modifiers changed from: private */
    public final zzp<TContinuationResult> zzc;

    public zzc(@NonNull Executor executor, @NonNull Continuation<TResult, Task<TContinuationResult>> continuation, @NonNull zzp<TContinuationResult> zzp) {
        this.zza = executor;
        this.zzb = continuation;
        this.zzc = zzp;
    }

    public final void onFailure(@NonNull Exception exc) {
        this.zzc.zza(exc);
    }

    public final void onSuccess(TContinuationResult tcontinuationresult) {
        this.zzc.zza(tcontinuationresult);
    }

    public final void zza() {
        throw new UnsupportedOperationException();
    }

    public final void zza(@NonNull Task<TResult> task) {
        this.zza.execute(new zzd(this, task));
    }
}
