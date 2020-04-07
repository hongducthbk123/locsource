package com.google.android.gms.internal;

import com.google.android.gms.internal.zzfhu.zzg;

final class zzfiq implements zzfjw {
    private static final zzfjb zzb = new zzfir();
    private final zzfjb zza;

    public zzfiq() {
        this(new zzfis(zzfht.zza(), zza()));
    }

    private zzfiq(zzfjb zzfjb) {
        this.zza = (zzfjb) zzfhz.zza(zzfjb, "messageInfoFactory");
    }

    private static zzfjb zza() {
        try {
            return (zzfjb) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            return zzb;
        }
    }

    private static boolean zza(zzfja zzfja) {
        return zzfja.zza() == zzg.zzk;
    }

    public final <T> zzfjv<T> zza(Class<T> cls) {
        zzfjx.zza(cls);
        zzfja zzb2 = this.zza.zzb(cls);
        if (zzb2.zzb()) {
            return zzfhu.class.isAssignableFrom(cls) ? zzfjh.zza(cls, zzfjx.zzc(), zzfhp.zza(), zzb2.zzc()) : zzfjh.zza(cls, zzfjx.zza(), zzfhp.zzb(), zzb2.zzc());
        }
        if (zzfhu.class.isAssignableFrom(cls)) {
            if (zza(zzb2)) {
                return zzfjg.zza(cls, zzb2, zzfjk.zzb(), zzfim.zzb(), zzfjx.zzc(), zzfhp.zza(), zzfiz.zzb());
            }
            return zzfjg.zza(cls, zzb2, zzfjk.zzb(), zzfim.zzb(), zzfjx.zzc(), null, zzfiz.zzb());
        } else if (zza(zzb2)) {
            return zzfjg.zza(cls, zzb2, zzfjk.zza(), zzfim.zza(), zzfjx.zza(), zzfhp.zzb(), zzfiz.zza());
        } else {
            return zzfjg.zza(cls, zzb2, zzfjk.zza(), zzfim.zza(), zzfjx.zzb(), null, zzfiz.zza());
        }
    }
}
