package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzfjh<T> implements zzfjv<T> {
    private final zzfjc zza;
    private final zzfkn<?, ?> zzb;
    private final boolean zzc;
    private final zzfhn<?> zzd;

    private zzfjh(Class<T> cls, zzfkn<?, ?> zzfkn, zzfhn<?> zzfhn, zzfjc zzfjc) {
        this.zzb = zzfkn;
        this.zzc = zzfhn.zza(cls);
        this.zzd = zzfhn;
        this.zza = zzfjc;
    }

    static <T> zzfjh<T> zza(Class<T> cls, zzfkn<?, ?> zzfkn, zzfhn<?> zzfhn, zzfjc zzfjc) {
        return new zzfjh<>(cls, zzfkn, zzfhn, zzfjc);
    }

    public final int zza(T t) {
        zzfkn<?, ?> zzfkn = this.zzb;
        int zzb2 = zzfkn.zzb(zzfkn.zza(t)) + 0;
        return this.zzc ? zzb2 + this.zzd.zza((Object) t).zzc() : zzb2;
    }

    public final void zza(T t, zzfli zzfli) {
        Iterator zzb2 = this.zzd.zza((Object) t).zzb();
        while (zzb2.hasNext()) {
            Entry entry = (Entry) zzb2.next();
            zzfhs zzfhs = (zzfhs) entry.getKey();
            if (zzfhs.zzc() != zzfld.MESSAGE || zzfhs.zzd() || zzfhs.zze()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (entry instanceof zzfii) {
                zzfli.zza(zzfhs.zza(), ((zzfii) entry).zza().zzc());
            } else {
                zzfli.zza(zzfhs.zza(), entry.getValue());
            }
        }
        zzfkn<?, ?> zzfkn = this.zzb;
        zzfkn.zza(zzfkn.zza(t), zzfli);
    }
}
