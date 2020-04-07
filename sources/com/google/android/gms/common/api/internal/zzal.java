package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Result;

public final class zzal implements zzbh {
    /* access modifiers changed from: private */
    public final zzbi zza;
    private boolean zzb = false;

    public zzal(zzbi zzbi) {
        this.zza = zzbi;
    }

    public final <A extends zzb, R extends Result, T extends zzm<R, A>> T zza(T t) {
        return zzb(t);
    }

    public final void zza() {
    }

    public final void zza(int i) {
        this.zza.zza((ConnectionResult) null);
        this.zza.zze.zza(i, this.zzb);
    }

    public final void zza(Bundle bundle) {
    }

    public final void zza(ConnectionResult connectionResult, Api<?> api, boolean z) {
    }

    /* JADX WARNING: type inference failed for: r0v11, types: [com.google.android.gms.common.api.Api$zzg] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <A extends com.google.android.gms.common.api.Api.zzb, T extends com.google.android.gms.common.api.internal.zzm<? extends com.google.android.gms.common.api.Result, A>> T zzb(T r4) {
        /*
            r3 = this;
            com.google.android.gms.common.api.internal.zzbi r0 = r3.zza     // Catch:{ DeadObjectException -> 0x0049 }
            com.google.android.gms.common.api.internal.zzba r0 = r0.zzd     // Catch:{ DeadObjectException -> 0x0049 }
            com.google.android.gms.common.api.internal.zzdk r0 = r0.zze     // Catch:{ DeadObjectException -> 0x0049 }
            r0.zza(r4)     // Catch:{ DeadObjectException -> 0x0049 }
            com.google.android.gms.common.api.internal.zzbi r0 = r3.zza     // Catch:{ DeadObjectException -> 0x0049 }
            com.google.android.gms.common.api.internal.zzba r0 = r0.zzd     // Catch:{ DeadObjectException -> 0x0049 }
            com.google.android.gms.common.api.Api$zzc r1 = r4.zzc()     // Catch:{ DeadObjectException -> 0x0049 }
            java.util.Map<com.google.android.gms.common.api.Api$zzc<?>, com.google.android.gms.common.api.Api$zze> r0 = r0.zzb     // Catch:{ DeadObjectException -> 0x0049 }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ DeadObjectException -> 0x0049 }
            com.google.android.gms.common.api.Api$zze r0 = (com.google.android.gms.common.api.Api.zze) r0     // Catch:{ DeadObjectException -> 0x0049 }
            java.lang.String r1 = "Appropriate Api was not requested."
            com.google.android.gms.common.internal.zzbq.zza(r0, r1)     // Catch:{ DeadObjectException -> 0x0049 }
            boolean r1 = r0.zzs()     // Catch:{ DeadObjectException -> 0x0049 }
            if (r1 != 0) goto L_0x003d
            com.google.android.gms.common.api.internal.zzbi r1 = r3.zza     // Catch:{ DeadObjectException -> 0x0049 }
            java.util.Map<com.google.android.gms.common.api.Api$zzc<?>, com.google.android.gms.common.ConnectionResult> r1 = r1.zzb     // Catch:{ DeadObjectException -> 0x0049 }
            com.google.android.gms.common.api.Api$zzc r2 = r4.zzc()     // Catch:{ DeadObjectException -> 0x0049 }
            boolean r1 = r1.containsKey(r2)     // Catch:{ DeadObjectException -> 0x0049 }
            if (r1 == 0) goto L_0x003d
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ DeadObjectException -> 0x0049 }
            r1 = 17
            r0.<init>(r1)     // Catch:{ DeadObjectException -> 0x0049 }
            r4.zzc(r0)     // Catch:{ DeadObjectException -> 0x0049 }
        L_0x003c:
            return r4
        L_0x003d:
            boolean r1 = r0 instanceof com.google.android.gms.common.internal.zzbz     // Catch:{ DeadObjectException -> 0x0049 }
            if (r1 == 0) goto L_0x0045
            com.google.android.gms.common.api.Api$zzg r0 = com.google.android.gms.common.internal.zzbz.zzi()     // Catch:{ DeadObjectException -> 0x0049 }
        L_0x0045:
            r4.zzb(r0)     // Catch:{ DeadObjectException -> 0x0049 }
            goto L_0x003c
        L_0x0049:
            r0 = move-exception
            com.google.android.gms.common.api.internal.zzbi r0 = r3.zza
            com.google.android.gms.common.api.internal.zzam r1 = new com.google.android.gms.common.api.internal.zzam
            r1.<init>(r3, r3)
            r0.zza(r1)
            goto L_0x003c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zzal.zzb(com.google.android.gms.common.api.internal.zzm):com.google.android.gms.common.api.internal.zzm");
    }

    public final boolean zzb() {
        if (this.zzb) {
            return false;
        }
        if (this.zza.zzd.zzg()) {
            this.zzb = true;
            for (zzdh zza2 : this.zza.zzd.zzd) {
                zza2.zza();
            }
            return false;
        }
        this.zza.zza((ConnectionResult) null);
        return true;
    }

    public final void zzc() {
        if (this.zzb) {
            this.zzb = false;
            this.zza.zza((zzbj) new zzan(this, this));
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zzd() {
        if (this.zzb) {
            this.zzb = false;
            this.zza.zzd.zze.zza();
            zzb();
        }
    }
}
