package com.google.android.gms.internal;

import android.net.TrafficStats;
import android.os.Process;
import android.os.SystemClock;
import java.util.concurrent.BlockingQueue;

public final class zzn extends Thread {
    private final BlockingQueue<zzr<?>> zza;
    private final zzm zzb;
    private final zzb zzc;
    private final zzaa zzd;
    private volatile boolean zze = false;

    public zzn(BlockingQueue<zzr<?>> blockingQueue, zzm zzm, zzb zzb2, zzaa zzaa) {
        this.zza = blockingQueue;
        this.zzb = zzm;
        this.zzc = zzb2;
        this.zzd = zzaa;
    }

    private final void zzb() throws InterruptedException {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        zzr zzr = (zzr) this.zza.take();
        try {
            zzr.zza("network-queue-take");
            zzr.zze();
            TrafficStats.setThreadStatsTag(zzr.zzb());
            zzp zza2 = this.zzb.zza(zzr);
            zzr.zza("network-http-complete");
            if (!zza2.zze || !zzr.zzl()) {
                zzx zza3 = zzr.zza(zza2);
                zzr.zza("network-parse-complete");
                if (zzr.zzh() && zza3.zzb != null) {
                    this.zzc.zza(zzr.zzc(), zza3.zzb);
                    zzr.zza("network-cache-written");
                }
                zzr.zzk();
                this.zzd.zza(zzr, zza3);
                zzr.zza(zza3);
                return;
            }
            zzr.zzb("not-modified");
            zzr.zzm();
        } catch (zzae e) {
            e.zza(SystemClock.elapsedRealtime() - elapsedRealtime);
            this.zzd.zza(zzr, e);
            zzr.zzm();
        } catch (Exception e2) {
            zzaf.zza(e2, "Unhandled exception %s", e2.toString());
            zzae zzae = new zzae((Throwable) e2);
            zzae.zza(SystemClock.elapsedRealtime() - elapsedRealtime);
            this.zzd.zza(zzr, zzae);
            zzr.zzm();
        }
    }

    public final void run() {
        Process.setThreadPriority(10);
        while (true) {
            try {
                zzb();
            } catch (InterruptedException e) {
                if (this.zze) {
                    return;
                }
            }
        }
    }

    public final void zza() {
        this.zze = true;
        interrupt();
    }
}
