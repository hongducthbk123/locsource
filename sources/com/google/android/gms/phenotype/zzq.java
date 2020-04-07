package com.google.android.gms.phenotype;

import com.google.android.gms.internal.zzdnm;

final /* synthetic */ class zzq implements zza {
    private final String zza;
    private final boolean zzb = false;

    zzq(String str, boolean z) {
        this.zza = str;
    }

    public final Object zza() {
        return Boolean.valueOf(zzdnm.zza(PhenotypeFlag.zzc.getContentResolver(), this.zza, this.zzb));
    }
}
