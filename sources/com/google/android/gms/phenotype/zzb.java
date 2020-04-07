package com.google.android.gms.phenotype;

import android.database.ContentObserver;
import android.os.Handler;

final class zzb extends ContentObserver {
    private /* synthetic */ zza zza;

    zzb(zza zza2, Handler handler) {
        this.zza = zza2;
        super(null);
    }

    public final void onChange(boolean z) {
        this.zza.zzb();
    }
}
