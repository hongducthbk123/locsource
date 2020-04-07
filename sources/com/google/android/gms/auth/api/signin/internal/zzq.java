package com.google.android.gms.auth.api.signin.internal;

public final class zzq {
    private static int zza = 31;
    private int zzb = 1;

    public final int zza() {
        return this.zzb;
    }

    public final zzq zza(Object obj) {
        this.zzb = (obj == null ? 0 : obj.hashCode()) + (this.zzb * zza);
        return this;
    }

    public final zzq zza(boolean z) {
        this.zzb = (z ? 1 : 0) + (this.zzb * zza);
        return this;
    }
}
