package com.google.android.gms.internal;

import com.google.android.gms.internal.zzfhu.zzg;
import java.io.IOException;
import java.util.Arrays;

public final class zzfko {
    private static final zzfko zza = new zzfko(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzfko() {
        this(0, new int[8], new Object[8], true);
    }

    private zzfko(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    public static zzfko zza() {
        return zza;
    }

    static zzfko zza(zzfko zzfko, zzfko zzfko2) {
        int i = zzfko.zzb + zzfko2.zzb;
        int[] copyOf = Arrays.copyOf(zzfko.zzc, i);
        System.arraycopy(zzfko2.zzc, 0, copyOf, zzfko.zzb, zzfko2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zzfko.zzd, i);
        System.arraycopy(zzfko2.zzd, 0, copyOf2, zzfko.zzb, zzfko2.zzb);
        return new zzfko(i, copyOf, copyOf2, true);
    }

    private void zza(int i, Object obj) {
        zzf();
        if (this.zzb == this.zzc.length) {
            int i2 = (this.zzb < 4 ? 8 : this.zzb >> 1) + this.zzb;
            this.zzc = Arrays.copyOf(this.zzc, i2);
            this.zzd = Arrays.copyOf(this.zzd, i2);
        }
        this.zzc[this.zzb] = i;
        this.zzd[this.zzb] = obj;
        this.zzb++;
    }

    static zzfko zzb() {
        return new zzfko();
    }

    private final void zzf() {
        if (!this.zzf) {
            throw new UnsupportedOperationException();
        }
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof zzfko)) {
            return false;
        }
        zzfko zzfko = (zzfko) obj;
        if (this.zzb == zzfko.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzfko.zzc;
            int i = this.zzb;
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
                Object[] objArr = this.zzd;
                Object[] objArr2 = zzfko.zzd;
                int i3 = this.zzb;
                int i4 = 0;
                while (true) {
                    if (i4 >= i3) {
                        z2 = true;
                        break;
                    } else if (!objArr[i4].equals(objArr2[i4])) {
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
        }
        return false;
    }

    public final int hashCode() {
        return ((((this.zzb + 527) * 31) + Arrays.hashCode(this.zzc)) * 31) + Arrays.deepHashCode(this.zzd);
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.zzb) {
                int i3 = this.zzc[i2];
                int i4 = i3 >>> 3;
                switch (i3 & 7) {
                    case 0:
                        zzfhg.zza(i4, ((Long) this.zzd[i2]).longValue());
                        break;
                    case 1:
                        zzfhg.zzb(i4, ((Long) this.zzd[i2]).longValue());
                        break;
                    case 2:
                        zzfhg.zza(i4, (zzfgs) this.zzd[i2]);
                        break;
                    case 3:
                        zzfhg.zza(i4, 3);
                        ((zzfko) this.zzd[i2]).zza(zzfhg);
                        zzfhg.zza(i4, 4);
                        break;
                    case 5:
                        zzfhg.zzd(i4, ((Integer) this.zzd[i2]).intValue());
                        break;
                    default:
                        throw zzfie.zzf();
                }
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzfli zzfli) {
        if (zzfli.zza() == zzg.zzm) {
            for (int i = this.zzb - 1; i >= 0; i--) {
                zzfli.zza(this.zzc[i] >>> 3, this.zzd[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzfli.zza(this.zzc[i2] >>> 3, this.zzd[i2]);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzfjf.zza(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza(int i, zzfhb zzfhb) throws IOException {
        int zza2;
        zzf();
        int i2 = i >>> 3;
        switch (i & 7) {
            case 0:
                zza(i, (Object) Long.valueOf(zzfhb.zze()));
                return true;
            case 1:
                zza(i, (Object) Long.valueOf(zzfhb.zzg()));
                return true;
            case 2:
                zza(i, (Object) zzfhb.zzl());
                return true;
            case 3:
                zzfko zzfko = new zzfko();
                do {
                    zza2 = zzfhb.zza();
                    if (zza2 != 0) {
                    }
                    zzfhb.zza((i2 << 3) | 4);
                    zza(i, (Object) zzfko);
                    return true;
                } while (zzfko.zza(zza2, zzfhb));
                zzfhb.zza((i2 << 3) | 4);
                zza(i, (Object) zzfko);
                return true;
            case 4:
                return false;
            case 5:
                zza(i, (Object) Integer.valueOf(zzfhb.zzh()));
                return true;
            default:
                throw zzfie.zzf();
        }
    }

    public final void zzc() {
        this.zzf = false;
    }

    public final int zzd() {
        int i = this.zze;
        if (i == -1) {
            i = 0;
            for (int i2 = 0; i2 < this.zzb; i2++) {
                i += zzfhg.zzd(this.zzc[i2] >>> 3, (zzfgs) this.zzd[i2]);
            }
            this.zze = i;
        }
        return i;
    }

    public final int zze() {
        int zze2;
        int i = this.zze;
        if (i == -1) {
            i = 0;
            for (int i2 = 0; i2 < this.zzb; i2++) {
                int i3 = this.zzc[i2];
                int i4 = i3 >>> 3;
                switch (i3 & 7) {
                    case 0:
                        zze2 = zzfhg.zzd(i4, ((Long) this.zzd[i2]).longValue());
                        break;
                    case 1:
                        zze2 = zzfhg.zze(i4, ((Long) this.zzd[i2]).longValue());
                        break;
                    case 2:
                        zze2 = zzfhg.zzc(i4, (zzfgs) this.zzd[i2]);
                        break;
                    case 3:
                        zze2 = ((zzfko) this.zzd[i2]).zze() + (zzfhg.zzf(i4) << 1);
                        break;
                    case 5:
                        zze2 = zzfhg.zzg(i4, ((Integer) this.zzd[i2]).intValue());
                        break;
                    default:
                        throw new IllegalStateException(zzfie.zzf());
                }
                i += zze2;
            }
            this.zze = i;
        }
        return i;
    }
}
