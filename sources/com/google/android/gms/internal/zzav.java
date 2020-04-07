package com.google.android.gms.internal;

import java.io.UnsupportedEncodingException;

public class zzav extends zzr<String> {
    private final Object zza = new Object();
    private zzz<String> zzb;

    public zzav(int i, String str, zzz<String> zzz, zzy zzy) {
        super(i, str, zzy);
        this.zzb = zzz;
    }

    /* access modifiers changed from: protected */
    public final zzx<String> zza(zzp zzp) {
        String str;
        try {
            str = new String(zzp.zzb, zzap.zza(zzp.zzc));
        } catch (UnsupportedEncodingException e) {
            str = new String(zzp.zzb);
        }
        return zzx.zza(str, zzap.zza(zzp));
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzc */
    public void zza(String str) {
        zzz<String> zzz;
        synchronized (this.zza) {
            zzz = this.zzb;
        }
        if (zzz != null) {
            zzz.zza(str);
        }
    }
}
