package com.google.android.gms.internal;

import com.btgame.onesdk.frame.constants.Constants;

public final class zzh implements zzab {
    private int zza;
    private int zzb;
    private final int zzc;
    private final float zzd;

    public zzh() {
        this(Constants.SPLASH_TIME, 1, 1.0f);
    }

    private zzh(int i, int i2, float f) {
        this.zza = Constants.SPLASH_TIME;
        this.zzc = 1;
        this.zzd = 1.0f;
    }

    public final int zza() {
        return this.zza;
    }

    public final void zza(zzae zzae) throws zzae {
        this.zzb++;
        this.zza = (int) (((float) this.zza) + (((float) this.zza) * this.zzd));
        if (!(this.zzb <= this.zzc)) {
            throw zzae;
        }
    }

    public final int zzb() {
        return this.zzb;
    }
}
