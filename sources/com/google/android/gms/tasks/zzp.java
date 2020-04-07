package com.google.android.gms.tasks;

import android.app.Activity;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import com.google.android.gms.common.api.internal.zzcf;
import com.google.android.gms.common.internal.zzbq;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

final class zzp<TResult> extends Task<TResult> {
    private final Object zza = new Object();
    private final zzn<TResult> zzb = new zzn<>();
    private boolean zzc;
    private TResult zzd;
    private Exception zze;

    static class zza extends LifecycleCallback {
        private final List<WeakReference<zzm<?>>> zza = new ArrayList();

        private zza(zzcf zzcf) {
            super(zzcf);
            this.zzd.zza("TaskOnStopCallback", (LifecycleCallback) this);
        }

        public static zza zzb(Activity activity) {
            zzcf zza2 = zza(activity);
            zza zza3 = (zza) zza2.zza("TaskOnStopCallback", zza.class);
            return zza3 == null ? new zza(zza2) : zza3;
        }

        public final <T> void zza(zzm<T> zzm) {
            synchronized (this.zza) {
                this.zza.add(new WeakReference(zzm));
            }
        }

        @MainThread
        public final void zzb() {
            synchronized (this.zza) {
                for (WeakReference weakReference : this.zza) {
                    zzm zzm = (zzm) weakReference.get();
                    if (zzm != null) {
                        zzm.zza();
                    }
                }
                this.zza.clear();
            }
        }
    }

    zzp() {
    }

    private final void zza() {
        zzbq.zza(this.zzc, (Object) "Task is not yet complete");
    }

    private final void zzb() {
        zzbq.zza(!this.zzc, (Object) "Task is already complete");
    }

    private final void zzc() {
        synchronized (this.zza) {
            if (this.zzc) {
                this.zzb.zza((Task<TResult>) this);
            }
        }
    }

    @NonNull
    public final Task<TResult> addOnCompleteListener(@NonNull Activity activity, @NonNull OnCompleteListener<TResult> onCompleteListener) {
        zze zze2 = new zze(TaskExecutors.MAIN_THREAD, onCompleteListener);
        this.zzb.zza((zzm<TResult>) zze2);
        zza.zzb(activity).zza(zze2);
        zzc();
        return this;
    }

    @NonNull
    public final Task<TResult> addOnCompleteListener(@NonNull OnCompleteListener<TResult> onCompleteListener) {
        return addOnCompleteListener(TaskExecutors.MAIN_THREAD, onCompleteListener);
    }

    @NonNull
    public final Task<TResult> addOnCompleteListener(@NonNull Executor executor, @NonNull OnCompleteListener<TResult> onCompleteListener) {
        this.zzb.zza((zzm<TResult>) new zze<TResult>(executor, onCompleteListener));
        zzc();
        return this;
    }

    @NonNull
    public final Task<TResult> addOnFailureListener(@NonNull Activity activity, @NonNull OnFailureListener onFailureListener) {
        zzg zzg = new zzg(TaskExecutors.MAIN_THREAD, onFailureListener);
        this.zzb.zza((zzm<TResult>) zzg);
        zza.zzb(activity).zza(zzg);
        zzc();
        return this;
    }

    @NonNull
    public final Task<TResult> addOnFailureListener(@NonNull OnFailureListener onFailureListener) {
        return addOnFailureListener(TaskExecutors.MAIN_THREAD, onFailureListener);
    }

    @NonNull
    public final Task<TResult> addOnFailureListener(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
        this.zzb.zza((zzm<TResult>) new zzg<TResult>(executor, onFailureListener));
        zzc();
        return this;
    }

    @NonNull
    public final Task<TResult> addOnSuccessListener(@NonNull Activity activity, @NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        zzi zzi = new zzi(TaskExecutors.MAIN_THREAD, onSuccessListener);
        this.zzb.zza((zzm<TResult>) zzi);
        zza.zzb(activity).zza(zzi);
        zzc();
        return this;
    }

    @NonNull
    public final Task<TResult> addOnSuccessListener(@NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        return addOnSuccessListener(TaskExecutors.MAIN_THREAD, onSuccessListener);
    }

    @NonNull
    public final Task<TResult> addOnSuccessListener(@NonNull Executor executor, @NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        this.zzb.zza((zzm<TResult>) new zzi<TResult>(executor, onSuccessListener));
        zzc();
        return this;
    }

    @NonNull
    public final <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull Continuation<TResult, TContinuationResult> continuation) {
        return continueWith(TaskExecutors.MAIN_THREAD, continuation);
    }

    @NonNull
    public final <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull Executor executor, @NonNull Continuation<TResult, TContinuationResult> continuation) {
        zzp zzp = new zzp();
        this.zzb.zza((zzm<TResult>) new zza<TResult>(executor, continuation, zzp));
        zzc();
        return zzp;
    }

    @NonNull
    public final <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull Continuation<TResult, Task<TContinuationResult>> continuation) {
        return continueWithTask(TaskExecutors.MAIN_THREAD, continuation);
    }

    @NonNull
    public final <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull Executor executor, @NonNull Continuation<TResult, Task<TContinuationResult>> continuation) {
        zzp zzp = new zzp();
        this.zzb.zza((zzm<TResult>) new zzc<TResult>(executor, continuation, zzp));
        zzc();
        return zzp;
    }

    @Nullable
    public final Exception getException() {
        Exception exc;
        synchronized (this.zza) {
            exc = this.zze;
        }
        return exc;
    }

    public final TResult getResult() {
        TResult tresult;
        synchronized (this.zza) {
            zza();
            if (this.zze != null) {
                throw new RuntimeExecutionException(this.zze);
            }
            tresult = this.zzd;
        }
        return tresult;
    }

    public final <X extends Throwable> TResult getResult(@NonNull Class<X> cls) throws Throwable {
        TResult tresult;
        synchronized (this.zza) {
            zza();
            if (cls.isInstance(this.zze)) {
                throw ((Throwable) cls.cast(this.zze));
            } else if (this.zze != null) {
                throw new RuntimeExecutionException(this.zze);
            } else {
                tresult = this.zzd;
            }
        }
        return tresult;
    }

    public final boolean isComplete() {
        boolean z;
        synchronized (this.zza) {
            z = this.zzc;
        }
        return z;
    }

    public final boolean isSuccessful() {
        boolean z;
        synchronized (this.zza) {
            z = this.zzc && this.zze == null;
        }
        return z;
    }

    @NonNull
    public final <TContinuationResult> Task<TContinuationResult> onSuccessTask(@NonNull SuccessContinuation<TResult, TContinuationResult> successContinuation) {
        return onSuccessTask(TaskExecutors.MAIN_THREAD, successContinuation);
    }

    @NonNull
    public final <TContinuationResult> Task<TContinuationResult> onSuccessTask(Executor executor, SuccessContinuation<TResult, TContinuationResult> successContinuation) {
        zzp zzp = new zzp();
        this.zzb.zza((zzm<TResult>) new zzk<TResult>(executor, successContinuation, zzp));
        zzc();
        return zzp;
    }

    public final void zza(@NonNull Exception exc) {
        zzbq.zza(exc, (Object) "Exception must not be null");
        synchronized (this.zza) {
            zzb();
            this.zzc = true;
            this.zze = exc;
        }
        this.zzb.zza((Task<TResult>) this);
    }

    public final void zza(TResult tresult) {
        synchronized (this.zza) {
            zzb();
            this.zzc = true;
            this.zzd = tresult;
        }
        this.zzb.zza((Task<TResult>) this);
    }

    public final boolean zzb(@NonNull Exception exc) {
        boolean z = true;
        zzbq.zza(exc, (Object) "Exception must not be null");
        synchronized (this.zza) {
            if (this.zzc) {
                z = false;
            } else {
                this.zzc = true;
                this.zze = exc;
                this.zzb.zza((Task<TResult>) this);
            }
        }
        return z;
    }

    public final boolean zzb(TResult tresult) {
        boolean z = true;
        synchronized (this.zza) {
            if (this.zzc) {
                z = false;
            } else {
                this.zzc = true;
                this.zzd = tresult;
                this.zzb.zza((Task<TResult>) this);
            }
        }
        return z;
    }
}
