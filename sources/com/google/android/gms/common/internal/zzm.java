package com.google.android.gms.common.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;

public final class zzm implements zzj {
    private /* synthetic */ zzd zza;

    public zzm(zzd zzd) {
        this.zza = zzd;
    }

    public final void zza(@NonNull ConnectionResult connectionResult) {
        if (connectionResult.isSuccess()) {
            this.zza.zza((zzan) null, this.zza.zzah());
        } else if (this.zza.zzv != null) {
            this.zza.zzv.zza(connectionResult);
        }
    }
}
