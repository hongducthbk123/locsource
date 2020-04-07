package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzv {
    private final AtomicInteger zza;
    private final Set<zzr<?>> zzb;
    private final PriorityBlockingQueue<zzr<?>> zzc;
    private final PriorityBlockingQueue<zzr<?>> zzd;
    private final zzb zze;
    private final zzm zzf;
    private final zzaa zzg;
    private final zzn[] zzh;
    private zzd zzi;
    private final List<zzw> zzj;

    public zzv(zzb zzb2, zzm zzm) {
        this(zzb2, zzm, 4);
    }

    private zzv(zzb zzb2, zzm zzm, int i) {
        this(zzb2, zzm, 4, new zzi(new Handler(Looper.getMainLooper())));
    }

    private zzv(zzb zzb2, zzm zzm, int i, zzaa zzaa) {
        this.zza = new AtomicInteger();
        this.zzb = new HashSet();
        this.zzc = new PriorityBlockingQueue<>();
        this.zzd = new PriorityBlockingQueue<>();
        this.zzj = new ArrayList();
        this.zze = zzb2;
        this.zzf = zzm;
        this.zzh = new zzn[4];
        this.zzg = zzaa;
    }

    public final <T> zzr<T> zza(zzr<T> zzr) {
        zzr.zza(this);
        synchronized (this.zzb) {
            this.zzb.add(zzr);
        }
        zzr.zza(this.zza.incrementAndGet());
        zzr.zza("add-to-queue");
        if (!zzr.zzh()) {
            this.zzd.add(zzr);
        } else {
            this.zzc.add(zzr);
        }
        return zzr;
    }

    public final void zza() {
        zzn[] zznArr;
        if (this.zzi != null) {
            this.zzi.zza();
        }
        for (zzn zzn : this.zzh) {
            if (zzn != null) {
                zzn.zza();
            }
        }
        this.zzi = new zzd(this.zzc, this.zzd, this.zze, this.zzg);
        this.zzi.start();
        for (int i = 0; i < this.zzh.length; i++) {
            zzn zzn2 = new zzn(this.zzd, this.zzf, this.zze, this.zzg);
            this.zzh[i] = zzn2;
            zzn2.start();
        }
    }

    /* access modifiers changed from: 0000 */
    public final <T> void zzb(zzr<T> zzr) {
        synchronized (this.zzb) {
            this.zzb.remove(zzr);
        }
        synchronized (this.zzj) {
            for (zzw zza2 : this.zzj) {
                zza2.zza(zzr);
            }
        }
    }
}
