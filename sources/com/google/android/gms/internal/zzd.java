package com.google.android.gms.internal;

import android.os.Process;
import java.util.concurrent.BlockingQueue;

public final class zzd extends Thread {
    private static final boolean zza = zzaf.zza;
    private final BlockingQueue<zzr<?>> zzb;
    /* access modifiers changed from: private */
    public final BlockingQueue<zzr<?>> zzc;
    private final zzb zzd;
    /* access modifiers changed from: private */
    public final zzaa zze;
    private volatile boolean zzf = false;
    private final zzf zzg;

    public zzd(BlockingQueue<zzr<?>> blockingQueue, BlockingQueue<zzr<?>> blockingQueue2, zzb zzb2, zzaa zzaa) {
        this.zzb = blockingQueue;
        this.zzc = blockingQueue2;
        this.zzd = zzb2;
        this.zze = zzaa;
        this.zzg = new zzf(this);
    }

    private final void zzb() throws InterruptedException {
        zzr zzr = (zzr) this.zzb.take();
        zzr.zza("cache-queue-take");
        zzr.zze();
        zzc zza2 = this.zzd.zza(zzr.zzc());
        if (zza2 == null) {
            zzr.zza("cache-miss");
            if (!this.zzg.zzb(zzr)) {
                this.zzc.put(zzr);
            }
        } else if (zza2.zza()) {
            zzr.zza("cache-hit-expired");
            zzr.zza(zza2);
            if (!this.zzg.zzb(zzr)) {
                this.zzc.put(zzr);
            }
        } else {
            zzr.zza("cache-hit");
            zzx zza3 = zzr.zza(new zzp(zza2.zza, zza2.zzg));
            zzr.zza("cache-hit-parsed");
            if (zza2.zzf < System.currentTimeMillis()) {
                zzr.zza("cache-hit-refresh-needed");
                zzr.zza(zza2);
                zza3.zzd = true;
                if (!this.zzg.zzb(zzr)) {
                    this.zze.zza(zzr, zza3, new zze(this, zzr));
                    return;
                }
            }
            this.zze.zza(zzr, zza3);
        }
    }

    public final void run() {
        if (zza) {
            zzaf.zza("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        this.zzd.zza();
        while (true) {
            try {
                zzb();
            } catch (InterruptedException e) {
                if (this.zzf) {
                    return;
                }
            }
        }
    }

    public final void zza() {
        this.zzf = true;
        interrupt();
    }
}
