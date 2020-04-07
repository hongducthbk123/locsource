package com.google.android.gms.internal;

final class zzfgv extends zzfgz {
    private final int zzc;
    private final int zzd;

    zzfgv(byte[] bArr, int i, int i2) {
        super(bArr);
        zzb(i, i + i2, bArr.length);
        this.zzc = i;
        this.zzd = i2;
    }

    public final byte zza(int i) {
        zzb(i, zza());
        return this.zzb[this.zzc + i];
    }

    public final int zza() {
        return this.zzd;
    }

    /* access modifiers changed from: protected */
    public final void zzb(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zzb, zzh() + i, bArr, i2, i3);
    }

    /* access modifiers changed from: protected */
    public final int zzh() {
        return this.zzc;
    }
}
