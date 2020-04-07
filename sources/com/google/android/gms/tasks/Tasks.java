package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class Tasks {

    static final class zza implements zzb {
        private final CountDownLatch zza;

        private zza() {
            this.zza = new CountDownLatch(1);
        }

        /* synthetic */ zza(zzq zzq) {
            this();
        }

        public final void onFailure(@NonNull Exception exc) {
            this.zza.countDown();
        }

        public final void onSuccess(Object obj) {
            this.zza.countDown();
        }

        public final void zza() throws InterruptedException {
            this.zza.await();
        }

        public final boolean zza(long j, TimeUnit timeUnit) throws InterruptedException {
            return this.zza.await(j, timeUnit);
        }
    }

    interface zzb extends OnFailureListener, OnSuccessListener<Object> {
    }

    static final class zzc implements zzb {
        private final Object zza = new Object();
        private final int zzb;
        private final zzp<Void> zzc;
        private int zzd;
        private int zze;
        private Exception zzf;

        public zzc(int i, zzp<Void> zzp) {
            this.zzb = i;
            this.zzc = zzp;
        }

        private final void zza() {
            if (this.zzd + this.zze != this.zzb) {
                return;
            }
            if (this.zzf == null) {
                this.zzc.zza(null);
                return;
            }
            zzp<Void> zzp = this.zzc;
            int i = this.zze;
            zzp.zza((Exception) new ExecutionException(i + " out of " + this.zzb + " underlying tasks failed", this.zzf));
        }

        public final void onFailure(@NonNull Exception exc) {
            synchronized (this.zza) {
                this.zze++;
                this.zzf = exc;
                zza();
            }
        }

        public final void onSuccess(Object obj) {
            synchronized (this.zza) {
                this.zzd++;
                zza();
            }
        }
    }

    private Tasks() {
    }

    public static <TResult> TResult await(@NonNull Task<TResult> task) throws ExecutionException, InterruptedException {
        zzbq.zzc("Must not be called on the main application thread");
        zzbq.zza(task, (Object) "Task must not be null");
        if (task.isComplete()) {
            return zza(task);
        }
        zza zza2 = new zza(null);
        zza(task, zza2);
        zza2.zza();
        return zza(task);
    }

    public static <TResult> TResult await(@NonNull Task<TResult> task, long j, @NonNull TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        zzbq.zzc("Must not be called on the main application thread");
        zzbq.zza(task, (Object) "Task must not be null");
        zzbq.zza(timeUnit, (Object) "TimeUnit must not be null");
        if (task.isComplete()) {
            return zza(task);
        }
        zza zza2 = new zza(null);
        zza(task, zza2);
        if (zza2.zza(j, timeUnit)) {
            return zza(task);
        }
        throw new TimeoutException("Timed out waiting for Task");
    }

    public static <TResult> Task<TResult> call(@NonNull Callable<TResult> callable) {
        return call(TaskExecutors.MAIN_THREAD, callable);
    }

    public static <TResult> Task<TResult> call(@NonNull Executor executor, @NonNull Callable<TResult> callable) {
        zzbq.zza(executor, (Object) "Executor must not be null");
        zzbq.zza(callable, (Object) "Callback must not be null");
        zzp zzp = new zzp();
        executor.execute(new zzq(zzp, callable));
        return zzp;
    }

    public static <TResult> Task<TResult> forException(@NonNull Exception exc) {
        zzp zzp = new zzp();
        zzp.zza(exc);
        return zzp;
    }

    public static <TResult> Task<TResult> forResult(TResult tresult) {
        zzp zzp = new zzp();
        zzp.zza(tresult);
        return zzp;
    }

    public static Task<Void> whenAll(Collection<? extends Task<?>> collection) {
        if (collection.isEmpty()) {
            return forResult(null);
        }
        for (Task task : collection) {
            if (task == null) {
                throw new NullPointerException("null tasks are not accepted");
            }
        }
        zzp zzp = new zzp();
        zzc zzc2 = new zzc(collection.size(), zzp);
        for (Task zza2 : collection) {
            zza(zza2, zzc2);
        }
        return zzp;
    }

    public static Task<Void> whenAll(Task<?>... taskArr) {
        return taskArr.length == 0 ? forResult(null) : whenAll((Collection<? extends Task<?>>) Arrays.asList(taskArr));
    }

    public static Task<List<Task<?>>> whenAllComplete(Collection<? extends Task<?>> collection) {
        return whenAll(collection).continueWith(new zzs(collection));
    }

    public static Task<List<Task<?>>> whenAllComplete(Task<?>... taskArr) {
        return whenAllComplete((Collection<? extends Task<?>>) Arrays.asList(taskArr));
    }

    public static <TResult> Task<List<TResult>> whenAllSuccess(Collection<? extends Task<?>> collection) {
        return whenAll(collection).continueWith(new zzr(collection));
    }

    public static <TResult> Task<List<TResult>> whenAllSuccess(Task<?>... taskArr) {
        return whenAllSuccess((Collection<? extends Task<?>>) Arrays.asList(taskArr));
    }

    private static <TResult> TResult zza(Task<TResult> task) throws ExecutionException {
        if (task.isSuccessful()) {
            return task.getResult();
        }
        throw new ExecutionException(task.getException());
    }

    private static void zza(Task<?> task, zzb zzb2) {
        task.addOnSuccessListener(TaskExecutors.zza, (OnSuccessListener<? super TResult>) zzb2);
        task.addOnFailureListener(TaskExecutors.zza, (OnFailureListener) zzb2);
    }
}
