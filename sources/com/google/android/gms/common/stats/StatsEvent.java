package com.google.android.gms.common.stats;

import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzbgl;

public abstract class StatsEvent extends zzbgl implements ReflectedParcelable {
    public String toString() {
        long zza = zza();
        int zzb = zzb();
        long zzc = zzc();
        String zzd = zzd();
        return new StringBuilder(String.valueOf(zzd).length() + 53).append(zza).append("\t").append(zzb).append("\t").append(zzc).append(zzd).toString();
    }

    public abstract long zza();

    public abstract int zzb();

    public abstract long zzc();

    public abstract String zzd();
}
