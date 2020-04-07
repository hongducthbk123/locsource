package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;

abstract class zzay implements Runnable {
    private /* synthetic */ zzao zza;

    private zzay(zzao zzao) {
        this.zza = zzao;
    }

    /* synthetic */ zzay(zzao zzao, zzap zzap) {
        this(zzao);
    }

    @WorkerThread
    public void run() {
        this.zza.zzb.lock();
        try {
            if (!Thread.interrupted()) {
                zza();
                this.zza.zzb.unlock();
            }
        } catch (RuntimeException e) {
            this.zza.zza.zza(e);
        } finally {
            this.zza.zzb.unlock();
        }
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public abstract void zza();
}
