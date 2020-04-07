package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class zzf implements zzt {
    private final Map<String, List<zzr<?>>> zza = new HashMap();
    private final zzd zzb;

    zzf(zzd zzd) {
        this.zzb = zzd;
    }

    /* access modifiers changed from: private */
    public final synchronized boolean zzb(zzr<?> zzr) {
        boolean z = false;
        synchronized (this) {
            String zzc = zzr.zzc();
            if (this.zza.containsKey(zzc)) {
                List list = (List) this.zza.get(zzc);
                if (list == null) {
                    list = new ArrayList();
                }
                zzr.zza("waiting-for-response");
                list.add(zzr);
                this.zza.put(zzc, list);
                if (zzaf.zza) {
                    zzaf.zzb("Request for cacheKey=%s is in flight, putting on hold.", zzc);
                }
                z = true;
            } else {
                this.zza.put(zzc, null);
                zzr.zza((zzt) this);
                if (zzaf.zza) {
                    zzaf.zzb("new request, sending to network %s", zzc);
                }
            }
        }
        return z;
    }

    public final synchronized void zza(zzr<?> zzr) {
        String zzc = zzr.zzc();
        List list = (List) this.zza.remove(zzc);
        if (list != null && !list.isEmpty()) {
            if (zzaf.zza) {
                zzaf.zza("%d waiting requests for cacheKey=%s; resend to network", Integer.valueOf(list.size()), zzc);
            }
            zzr zzr2 = (zzr) list.remove(0);
            this.zza.put(zzc, list);
            zzr2.zza((zzt) this);
            try {
                this.zzb.zzc.put(zzr2);
            } catch (InterruptedException e) {
                zzaf.zzc("Couldn't add request to queue. %s", e.toString());
                Thread.currentThread().interrupt();
                this.zzb.zza();
            }
        }
        return;
    }

    public final void zza(zzr<?> zzr, zzx<?> zzx) {
        List<zzr> list;
        if (zzx.zzb == null || zzx.zzb.zza()) {
            zza(zzr);
            return;
        }
        String zzc = zzr.zzc();
        synchronized (this) {
            list = (List) this.zza.remove(zzc);
        }
        if (list != null) {
            if (zzaf.zza) {
                zzaf.zza("Releasing %d waiting requests for cacheKey=%s.", Integer.valueOf(list.size()), zzc);
            }
            for (zzr zza2 : list) {
                this.zzb.zze.zza(zza2, zzx);
            }
        }
    }
}
