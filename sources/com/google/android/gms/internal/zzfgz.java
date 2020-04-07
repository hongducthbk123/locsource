package com.google.android.gms.internal;

import java.io.IOException;

class zzfgz extends zzfgy {
    protected final byte[] zzb;

    zzfgz(byte[] bArr) {
        this.zzb = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfgs)) {
            return false;
        }
        if (zza() != ((zzfgs) obj).zza()) {
            return false;
        }
        if (zza() == 0) {
            return true;
        }
        if (!(obj instanceof zzfgz)) {
            return obj.equals(this);
        }
        zzfgz zzfgz = (zzfgz) obj;
        int zzg = zzg();
        int zzg2 = zzfgz.zzg();
        if (zzg == 0 || zzg2 == 0 || zzg == zzg2) {
            return zza((zzfgz) obj, 0, zza());
        }
        return false;
    }

    public byte zza(int i) {
        return this.zzb[i];
    }

    public int zza() {
        return this.zzb.length;
    }

    /* access modifiers changed from: protected */
    public final int zza(int i, int i2, int i3) {
        return zzfhz.zza(i, this.zzb, zzh() + i2, i3);
    }

    public final zzfgs zza(int i, int i2) {
        int zzb2 = zzb(i, i2, zza());
        return zzb2 == 0 ? zzfgs.zza : new zzfgv(this.zzb, zzh() + i, zzb2);
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzfgr zzfgr) throws IOException {
        zzfgr.zza(this.zzb, zzh(), zza());
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza(zzfgs zzfgs, int i, int i2) {
        if (i2 > zzfgs.zza()) {
            throw new IllegalArgumentException("Length too large: " + i2 + zza());
        } else if (i + i2 > zzfgs.zza()) {
            throw new IllegalArgumentException("Ran off end of other: " + i + ", " + i2 + ", " + zzfgs.zza());
        } else if (!(zzfgs instanceof zzfgz)) {
            return zzfgs.zza(i, i + i2).equals(zza(0, i2));
        } else {
            zzfgz zzfgz = (zzfgz) zzfgs;
            byte[] bArr = this.zzb;
            byte[] bArr2 = zzfgz.zzb;
            int zzh = zzh() + i2;
            int zzh2 = zzh();
            int zzh3 = zzfgz.zzh() + i;
            while (zzh2 < zzh) {
                if (bArr[zzh2] != bArr2[zzh3]) {
                    return false;
                }
                zzh2++;
                zzh3++;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public void zzb(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zzb, i, bArr, i2, i3);
    }

    public final zzfhb zzd() {
        return zzfhb.zza(this.zzb, zzh(), zza(), true);
    }

    /* access modifiers changed from: protected */
    public int zzh() {
        return 0;
    }
}
