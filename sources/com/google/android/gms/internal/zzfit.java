package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfit<K, V> {
    private final zzfiv<K, V> zza;
    private final K zzb;
    private final V zzc;

    private zzfit(zzfky zzfky, K k, zzfky zzfky2, V v) {
        this.zza = new zzfiv<>(zzfky, k, zzfky2, v);
        this.zzb = k;
        this.zzc = v;
    }

    static <K, V> int zza(zzfiv<K, V> zzfiv, K k, V v) {
        return zzfhq.zza(zzfiv.zza, 1, (Object) k) + zzfhq.zza(zzfiv.zzc, 2, (Object) v);
    }

    public static <K, V> zzfit<K, V> zza(zzfky zzfky, K k, zzfky zzfky2, V v) {
        return new zzfit<>(zzfky, k, zzfky2, v);
    }

    private static <T> T zza(zzfhb zzfhb, zzfhm zzfhm, zzfky zzfky, T t) throws IOException {
        switch (zzfiu.zza[zzfky.ordinal()]) {
            case 1:
                zzfjd zzv = ((zzfjc) t).zzv();
                zzfhb.zza(zzv, zzfhm);
                return zzv.zze();
            case 2:
                return Integer.valueOf(zzfhb.zzn());
            case 3:
                throw new RuntimeException("Groups are not allowed in maps.");
            default:
                return zzfhq.zza(zzfhb, zzfky, true);
        }
    }

    static <K, V> void zza(zzfhg zzfhg, zzfiv<K, V> zzfiv, K k, V v) throws IOException {
        zzfhq.zza(zzfhg, zzfiv.zza, 1, k);
        zzfhq.zza(zzfhg, zzfiv.zzc, 2, v);
    }

    public final int zza(int i, K k, V v) {
        return zzfhg.zzf(i) + zzfhg.zzm(zza(this.zza, k, v));
    }

    public final void zza(zzfhg zzfhg, int i, K k, V v) throws IOException {
        zzfhg.zza(i, 2);
        zzfhg.zzc(zza(this.zza, k, v));
        zza(zzfhg, this.zza, k, v);
    }

    public final void zza(zzfiw<K, V> zzfiw, zzfhb zzfhb, zzfhm zzfhm) throws IOException {
        int zzd = zzfhb.zzd(zzfhb.zzs());
        K k = this.zza.zzb;
        V v = this.zza.zzd;
        while (true) {
            int zza2 = zzfhb.zza();
            if (zza2 == 0) {
                break;
            } else if (zza2 == (this.zza.zza.zzb() | 8)) {
                k = zza(zzfhb, zzfhm, this.zza.zza, (T) k);
            } else if (zza2 == (this.zza.zzc.zzb() | 16)) {
                v = zza(zzfhb, zzfhm, this.zza.zzc, (T) v);
            } else if (!zzfhb.zzb(zza2)) {
                break;
            }
        }
        zzfhb.zza(0);
        zzfhb.zze(zzd);
        zzfiw.put(k, v);
    }
}
