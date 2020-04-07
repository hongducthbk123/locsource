package com.google.android.gms.internal;

public final class zzflo implements Cloneable {
    private static final zzflp zza = new zzflp();
    private boolean zzb;
    private int[] zzc;
    private zzflp[] zzd;
    private int zze;

    zzflo() {
        this(10);
    }

    private zzflo(int i) {
        this.zzb = false;
        int zzc2 = zzc(i);
        this.zzc = new int[zzc2];
        this.zzd = new zzflp[zzc2];
        this.zze = 0;
    }

    private static int zzc(int i) {
        int i2 = i << 2;
        int i3 = 4;
        while (true) {
            if (i3 >= 32) {
                break;
            } else if (i2 <= (1 << i3) - 12) {
                i2 = (1 << i3) - 12;
                break;
            } else {
                i3++;
            }
        }
        return i2 / 4;
    }

    private final int zzd(int i) {
        int i2 = 0;
        int i3 = this.zze - 1;
        while (i2 <= i3) {
            int i4 = (i2 + i3) >>> 1;
            int i5 = this.zzc[i4];
            if (i5 < i) {
                i2 = i4 + 1;
            } else if (i5 <= i) {
                return i4;
            } else {
                i3 = i4 - 1;
            }
        }
        return i2 ^ -1;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        int i = this.zze;
        zzflo zzflo = new zzflo(i);
        System.arraycopy(this.zzc, 0, zzflo.zzc, 0, i);
        for (int i2 = 0; i2 < i; i2++) {
            if (this.zzd[i2] != null) {
                zzflo.zzd[i2] = (zzflp) this.zzd[i2].clone();
            }
        }
        zzflo.zze = i;
        return zzflo;
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzflo)) {
            return false;
        }
        zzflo zzflo = (zzflo) obj;
        if (this.zze != zzflo.zze) {
            return false;
        }
        int[] iArr = this.zzc;
        int[] iArr2 = zzflo.zzc;
        int i = this.zze;
        int i2 = 0;
        while (true) {
            if (i2 >= i) {
                z = true;
                break;
            } else if (iArr[i2] != iArr2[i2]) {
                z = false;
                break;
            } else {
                i2++;
            }
        }
        if (z) {
            zzflp[] zzflpArr = this.zzd;
            zzflp[] zzflpArr2 = zzflo.zzd;
            int i3 = this.zze;
            int i4 = 0;
            while (true) {
                if (i4 >= i3) {
                    z2 = true;
                    break;
                } else if (!zzflpArr[i4].equals(zzflpArr2[i4])) {
                    z2 = false;
                    break;
                } else {
                    i4++;
                }
            }
            if (z2) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = 17;
        for (int i2 = 0; i2 < this.zze; i2++) {
            i = (((i * 31) + this.zzc[i2]) * 31) + this.zzd[i2].hashCode();
        }
        return i;
    }

    /* access modifiers changed from: 0000 */
    public final int zza() {
        return this.zze;
    }

    /* access modifiers changed from: 0000 */
    public final zzflp zza(int i) {
        int zzd2 = zzd(i);
        if (zzd2 < 0 || this.zzd[zzd2] == zza) {
            return null;
        }
        return this.zzd[zzd2];
    }

    /* access modifiers changed from: 0000 */
    public final void zza(int i, zzflp zzflp) {
        int zzd2 = zzd(i);
        if (zzd2 >= 0) {
            this.zzd[zzd2] = zzflp;
            return;
        }
        int i2 = zzd2 ^ -1;
        if (i2 >= this.zze || this.zzd[i2] != zza) {
            if (this.zze >= this.zzc.length) {
                int zzc2 = zzc(this.zze + 1);
                int[] iArr = new int[zzc2];
                zzflp[] zzflpArr = new zzflp[zzc2];
                System.arraycopy(this.zzc, 0, iArr, 0, this.zzc.length);
                System.arraycopy(this.zzd, 0, zzflpArr, 0, this.zzd.length);
                this.zzc = iArr;
                this.zzd = zzflpArr;
            }
            if (this.zze - i2 != 0) {
                System.arraycopy(this.zzc, i2, this.zzc, i2 + 1, this.zze - i2);
                System.arraycopy(this.zzd, i2, this.zzd, i2 + 1, this.zze - i2);
            }
            this.zzc[i2] = i;
            this.zzd[i2] = zzflp;
            this.zze++;
            return;
        }
        this.zzc[i2] = i;
        this.zzd[i2] = zzflp;
    }

    /* access modifiers changed from: 0000 */
    public final zzflp zzb(int i) {
        return this.zzd[i];
    }

    public final boolean zzb() {
        return this.zze == 0;
    }
}
