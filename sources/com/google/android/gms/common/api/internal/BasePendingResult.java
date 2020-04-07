package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzaq;
import com.google.android.gms.common.internal.zzbq;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@KeepName
@Hide
public abstract class BasePendingResult<R extends Result> extends PendingResult<R> {
    static final ThreadLocal<Boolean> zzc = new zzs();
    @KeepName
    private zzb mResultGuardian;
    private final Object zza;
    @Hide
    private zza<R> zzb;
    private WeakReference<GoogleApiClient> zzd;
    private final CountDownLatch zze;
    private final ArrayList<com.google.android.gms.common.api.PendingResult.zza> zzf;
    private ResultCallback<? super R> zzg;
    private final AtomicReference<zzdn> zzh;
    /* access modifiers changed from: private */
    public R zzi;
    private Status zzj;
    private volatile boolean zzk;
    private boolean zzl;
    private boolean zzm;
    private zzaq zzn;
    private volatile zzdh<R> zzo;
    private boolean zzp;

    @Hide
    public static class zza<R extends Result> extends Handler {
        public zza() {
            this(Looper.getMainLooper());
        }

        public zza(Looper looper) {
            super(looper);
        }

        public final void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Pair pair = (Pair) message.obj;
                    ResultCallback resultCallback = (ResultCallback) pair.first;
                    Result result = (Result) pair.second;
                    try {
                        resultCallback.onResult(result);
                        return;
                    } catch (RuntimeException e) {
                        BasePendingResult.zzb(result);
                        throw e;
                    }
                case 2:
                    ((BasePendingResult) message.obj).zzd(Status.zzd);
                    return;
                default:
                    Log.wtf("BasePendingResult", "Don't know how to handle message: " + message.what, new Exception());
                    return;
            }
        }

        public final void zza(ResultCallback<? super R> resultCallback, R r) {
            sendMessage(obtainMessage(1, new Pair(resultCallback, r)));
        }
    }

    final class zzb {
        private zzb() {
        }

        /* synthetic */ zzb(BasePendingResult basePendingResult, zzs zzs) {
            this();
        }

        /* access modifiers changed from: protected */
        public final void finalize() throws Throwable {
            BasePendingResult.zzb(BasePendingResult.this.zzi);
            super.finalize();
        }
    }

    @Deprecated
    BasePendingResult() {
        this.zza = new Object();
        this.zze = new CountDownLatch(1);
        this.zzf = new ArrayList<>();
        this.zzh = new AtomicReference<>();
        this.zzp = false;
        this.zzb = new zza<>(Looper.getMainLooper());
        this.zzd = new WeakReference<>(null);
    }

    @Deprecated
    protected BasePendingResult(Looper looper) {
        this.zza = new Object();
        this.zze = new CountDownLatch(1);
        this.zzf = new ArrayList<>();
        this.zzh = new AtomicReference<>();
        this.zzp = false;
        this.zzb = new zza<>(looper);
        this.zzd = new WeakReference<>(null);
    }

    protected BasePendingResult(GoogleApiClient googleApiClient) {
        this.zza = new Object();
        this.zze = new CountDownLatch(1);
        this.zzf = new ArrayList<>();
        this.zzh = new AtomicReference<>();
        this.zzp = false;
        this.zzb = new zza<>(googleApiClient != null ? googleApiClient.zzc() : Looper.getMainLooper());
        this.zzd = new WeakReference<>(googleApiClient);
    }

    private final R zza() {
        R r;
        boolean z = true;
        synchronized (this.zza) {
            if (this.zzk) {
                z = false;
            }
            zzbq.zza(z, (Object) "Result has already been consumed.");
            zzbq.zza(zze(), (Object) "Result is not ready.");
            r = this.zzi;
            this.zzi = null;
            this.zzg = null;
            this.zzk = true;
        }
        zzdn zzdn = (zzdn) this.zzh.getAndSet(null);
        if (zzdn != null) {
            zzdn.zza(this);
        }
        return r;
    }

    @Hide
    public static void zzb(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e) {
                String valueOf = String.valueOf(result);
                Log.w("BasePendingResult", new StringBuilder(String.valueOf(valueOf).length() + 18).append("Unable to release ").append(valueOf).toString(), e);
            }
        }
    }

    private final void zzc(R r) {
        this.zzi = r;
        this.zzn = null;
        this.zze.countDown();
        this.zzj = this.zzi.getStatus();
        if (this.zzl) {
            this.zzg = null;
        } else if (this.zzg != null) {
            this.zzb.removeMessages(2);
            this.zzb.zza(this.zzg, zza());
        } else if (this.zzi instanceof Releasable) {
            this.mResultGuardian = new zzb(this, null);
        }
        ArrayList arrayList = this.zzf;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((com.google.android.gms.common.api.PendingResult.zza) obj).zza(this.zzj);
        }
        this.zzf.clear();
    }

    public final R await() {
        boolean z = true;
        zzbq.zzc("await must not be called on the UI thread");
        zzbq.zza(!this.zzk, (Object) "Result has already been consumed");
        if (this.zzo != null) {
            z = false;
        }
        zzbq.zza(z, (Object) "Cannot await if then() has been called.");
        try {
            this.zze.await();
        } catch (InterruptedException e) {
            zzd(Status.zzb);
        }
        zzbq.zza(zze(), (Object) "Result is not ready.");
        return zza();
    }

    public final R await(long j, TimeUnit timeUnit) {
        boolean z = true;
        if (j > 0) {
            zzbq.zzc("await must not be called on the UI thread when time is greater than zero.");
        }
        zzbq.zza(!this.zzk, (Object) "Result has already been consumed.");
        if (this.zzo != null) {
            z = false;
        }
        zzbq.zza(z, (Object) "Cannot await if then() has been called.");
        try {
            if (!this.zze.await(j, timeUnit)) {
                zzd(Status.zzd);
            }
        } catch (InterruptedException e) {
            zzd(Status.zzb);
        }
        zzbq.zza(zze(), (Object) "Result is not ready.");
        return zza();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void cancel() {
        /*
            r2 = this;
            java.lang.Object r1 = r2.zza
            monitor-enter(r1)
            boolean r0 = r2.zzl     // Catch:{ all -> 0x0029 }
            if (r0 != 0) goto L_0x000b
            boolean r0 = r2.zzk     // Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x000d
        L_0x000b:
            monitor-exit(r1)     // Catch:{ all -> 0x0029 }
        L_0x000c:
            return
        L_0x000d:
            com.google.android.gms.common.internal.zzaq r0 = r2.zzn     // Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x0016
            com.google.android.gms.common.internal.zzaq r0 = r2.zzn     // Catch:{ RemoteException -> 0x002c }
            r0.zza()     // Catch:{ RemoteException -> 0x002c }
        L_0x0016:
            R r0 = r2.zzi     // Catch:{ all -> 0x0029 }
            zzb(r0)     // Catch:{ all -> 0x0029 }
            r0 = 1
            r2.zzl = r0     // Catch:{ all -> 0x0029 }
            com.google.android.gms.common.api.Status r0 = com.google.android.gms.common.api.Status.zze     // Catch:{ all -> 0x0029 }
            com.google.android.gms.common.api.Result r0 = r2.zza(r0)     // Catch:{ all -> 0x0029 }
            r2.zzc(r0)     // Catch:{ all -> 0x0029 }
            monitor-exit(r1)     // Catch:{ all -> 0x0029 }
            goto L_0x000c
        L_0x0029:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0029 }
            throw r0
        L_0x002c:
            r0 = move-exception
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.BasePendingResult.cancel():void");
    }

    public boolean isCanceled() {
        boolean z;
        synchronized (this.zza) {
            z = this.zzl;
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setResultCallback(com.google.android.gms.common.api.ResultCallback<? super R> r6) {
        /*
            r5 = this;
            r0 = 1
            r1 = 0
            java.lang.Object r3 = r5.zza
            monitor-enter(r3)
            if (r6 != 0) goto L_0x000c
            r0 = 0
            r5.zzg = r0     // Catch:{ all -> 0x0027 }
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
        L_0x000b:
            return
        L_0x000c:
            boolean r2 = r5.zzk     // Catch:{ all -> 0x0027 }
            if (r2 != 0) goto L_0x002a
            r2 = r0
        L_0x0011:
            java.lang.String r4 = "Result has already been consumed."
            com.google.android.gms.common.internal.zzbq.zza(r2, r4)     // Catch:{ all -> 0x0027 }
            com.google.android.gms.common.api.internal.zzdh<R> r2 = r5.zzo     // Catch:{ all -> 0x0027 }
            if (r2 != 0) goto L_0x002c
        L_0x001a:
            java.lang.String r1 = "Cannot set callbacks if then() has been called."
            com.google.android.gms.common.internal.zzbq.zza(r0, r1)     // Catch:{ all -> 0x0027 }
            boolean r0 = r5.isCanceled()     // Catch:{ all -> 0x0027 }
            if (r0 == 0) goto L_0x002e
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            goto L_0x000b
        L_0x0027:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            throw r0
        L_0x002a:
            r2 = r1
            goto L_0x0011
        L_0x002c:
            r0 = r1
            goto L_0x001a
        L_0x002e:
            boolean r0 = r5.zze()     // Catch:{ all -> 0x0027 }
            if (r0 == 0) goto L_0x003f
            com.google.android.gms.common.api.internal.BasePendingResult$zza<R> r0 = r5.zzb     // Catch:{ all -> 0x0027 }
            com.google.android.gms.common.api.Result r1 = r5.zza()     // Catch:{ all -> 0x0027 }
            r0.zza(r6, r1)     // Catch:{ all -> 0x0027 }
        L_0x003d:
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            goto L_0x000b
        L_0x003f:
            r5.zzg = r6     // Catch:{ all -> 0x0027 }
            goto L_0x003d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.BasePendingResult.setResultCallback(com.google.android.gms.common.api.ResultCallback):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setResultCallback(com.google.android.gms.common.api.ResultCallback<? super R> r7, long r8, java.util.concurrent.TimeUnit r10) {
        /*
            r6 = this;
            r0 = 1
            r1 = 0
            java.lang.Object r3 = r6.zza
            monitor-enter(r3)
            if (r7 != 0) goto L_0x000c
            r0 = 0
            r6.zzg = r0     // Catch:{ all -> 0x0027 }
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
        L_0x000b:
            return
        L_0x000c:
            boolean r2 = r6.zzk     // Catch:{ all -> 0x0027 }
            if (r2 != 0) goto L_0x002a
            r2 = r0
        L_0x0011:
            java.lang.String r4 = "Result has already been consumed."
            com.google.android.gms.common.internal.zzbq.zza(r2, r4)     // Catch:{ all -> 0x0027 }
            com.google.android.gms.common.api.internal.zzdh<R> r2 = r6.zzo     // Catch:{ all -> 0x0027 }
            if (r2 != 0) goto L_0x002c
        L_0x001a:
            java.lang.String r1 = "Cannot set callbacks if then() has been called."
            com.google.android.gms.common.internal.zzbq.zza(r0, r1)     // Catch:{ all -> 0x0027 }
            boolean r0 = r6.isCanceled()     // Catch:{ all -> 0x0027 }
            if (r0 == 0) goto L_0x002e
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            goto L_0x000b
        L_0x0027:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            throw r0
        L_0x002a:
            r2 = r1
            goto L_0x0011
        L_0x002c:
            r0 = r1
            goto L_0x001a
        L_0x002e:
            boolean r0 = r6.zze()     // Catch:{ all -> 0x0027 }
            if (r0 == 0) goto L_0x003f
            com.google.android.gms.common.api.internal.BasePendingResult$zza<R> r0 = r6.zzb     // Catch:{ all -> 0x0027 }
            com.google.android.gms.common.api.Result r1 = r6.zza()     // Catch:{ all -> 0x0027 }
            r0.zza(r7, r1)     // Catch:{ all -> 0x0027 }
        L_0x003d:
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            goto L_0x000b
        L_0x003f:
            r6.zzg = r7     // Catch:{ all -> 0x0027 }
            com.google.android.gms.common.api.internal.BasePendingResult$zza<R> r0 = r6.zzb     // Catch:{ all -> 0x0027 }
            long r4 = r10.toMillis(r8)     // Catch:{ all -> 0x0027 }
            r1 = 2
            android.os.Message r1 = r0.obtainMessage(r1, r6)     // Catch:{ all -> 0x0027 }
            r0.sendMessageDelayed(r1, r4)     // Catch:{ all -> 0x0027 }
            goto L_0x003d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.BasePendingResult.setResultCallback(com.google.android.gms.common.api.ResultCallback, long, java.util.concurrent.TimeUnit):void");
    }

    @Hide
    public <S extends Result> TransformedResult<S> then(ResultTransform<? super R, ? extends S> resultTransform) {
        TransformedResult<S> then;
        boolean z = true;
        zzbq.zza(!this.zzk, (Object) "Result has already been consumed.");
        synchronized (this.zza) {
            zzbq.zza(this.zzo == null, (Object) "Cannot call then() twice.");
            zzbq.zza(this.zzg == null, (Object) "Cannot call then() if callbacks are set.");
            if (this.zzl) {
                z = false;
            }
            zzbq.zza(z, (Object) "Cannot call then() if result was canceled.");
            this.zzp = true;
            this.zzo = new zzdh<>(this.zzd);
            then = this.zzo.then(resultTransform);
            if (zze()) {
                this.zzb.zza(this.zzo, zza());
            } else {
                this.zzg = this.zzo;
            }
        }
        return then;
    }

    /* access modifiers changed from: protected */
    @Hide
    @NonNull
    public abstract R zza(Status status);

    @Hide
    public final void zza(com.google.android.gms.common.api.PendingResult.zza zza2) {
        zzbq.zzb(zza2 != null, "Callback cannot be null.");
        synchronized (this.zza) {
            if (zze()) {
                zza2.zza(this.zzj);
            } else {
                this.zzf.add(zza2);
            }
        }
    }

    @Hide
    public final void zza(R r) {
        boolean z = true;
        synchronized (this.zza) {
            if (this.zzm || this.zzl) {
                zzb(r);
                return;
            }
            if (zze()) {
            }
            zzbq.zza(!zze(), (Object) "Results have already been set");
            if (this.zzk) {
                z = false;
            }
            zzbq.zza(z, (Object) "Result has already been consumed");
            zzc(r);
        }
    }

    @Hide
    public final void zza(zzdn zzdn) {
        this.zzh.set(zzdn);
    }

    /* access modifiers changed from: protected */
    @Hide
    public final void zza(zzaq zzaq) {
        synchronized (this.zza) {
            this.zzn = zzaq;
        }
    }

    @Hide
    public final Integer zzb() {
        return null;
    }

    @Hide
    public final void zzd(Status status) {
        synchronized (this.zza) {
            if (!zze()) {
                zza((R) zza(status));
                this.zzm = true;
            }
        }
    }

    @Hide
    public final boolean zze() {
        return this.zze.getCount() == 0;
    }

    @Hide
    public final boolean zzf() {
        boolean isCanceled;
        synchronized (this.zza) {
            if (((GoogleApiClient) this.zzd.get()) == null || !this.zzp) {
                cancel();
            }
            isCanceled = isCanceled();
        }
        return isCanceled;
    }

    @Hide
    public final void zzg() {
        this.zzp = this.zzp || ((Boolean) zzc.get()).booleanValue();
    }
}
