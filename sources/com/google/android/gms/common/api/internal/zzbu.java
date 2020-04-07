package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzan;
import com.google.android.gms.common.internal.zzj;
import java.util.Set;

final class zzbu implements zzcy, zzj {
    final /* synthetic */ zzbm zza;
    /* access modifiers changed from: private */
    public final zze zzb;
    /* access modifiers changed from: private */
    public final zzh<?> zzc;
    private zzan zzd = null;
    private Set<Scope> zze = null;
    /* access modifiers changed from: private */
    public boolean zzf = false;

    public zzbu(zzbm zzbm, zze zze2, zzh<?> zzh) {
        this.zza = zzbm;
        this.zzb = zze2;
        this.zzc = zzh;
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zza() {
        if (this.zzf && this.zzd != null) {
            this.zzb.zza(this.zzd, this.zze);
        }
    }

    public final void zza(@NonNull ConnectionResult connectionResult) {
        this.zza.zzq.post(new zzbv(this, connectionResult));
    }

    @WorkerThread
    public final void zza(zzan zzan, Set<Scope> set) {
        if (zzan == null || set == null) {
            Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
            zzb(new ConnectionResult(4));
            return;
        }
        this.zzd = zzan;
        this.zze = set;
        zza();
    }

    @WorkerThread
    public final void zzb(ConnectionResult connectionResult) {
        ((zzbo) this.zza.zzm.get(this.zzc)).zza(connectionResult);
    }
}
