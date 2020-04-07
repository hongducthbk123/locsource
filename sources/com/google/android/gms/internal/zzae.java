package com.google.android.gms.internal;

public class zzae extends Exception {
    private zzp zza;
    private long zzb;

    public zzae() {
        this.zza = null;
    }

    public zzae(zzp zzp) {
        this.zza = zzp;
    }

    public zzae(String str) {
        super(str);
        this.zza = null;
    }

    public zzae(Throwable th) {
        super(th);
        this.zza = null;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(long j) {
        this.zzb = j;
    }
}
