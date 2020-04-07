package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.api.GoogleApi.zza;
import com.google.android.gms.common.api.internal.zzda;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.internal.zzbq;

public final class zzd {
    private zzda zza;
    private Looper zzb;

    public final zza zza() {
        if (this.zza == null) {
            this.zza = new zzg();
        }
        if (this.zzb == null) {
            this.zzb = Looper.getMainLooper();
        }
        return new zza(this.zza, this.zzb);
    }

    public final zzd zza(Looper looper) {
        zzbq.zza(looper, (Object) "Looper must not be null.");
        this.zzb = looper;
        return this;
    }

    public final zzd zza(zzda zzda) {
        zzbq.zza(zzda, (Object) "StatusExceptionMapper must not be null.");
        this.zza = zzda;
        return this;
    }
}
