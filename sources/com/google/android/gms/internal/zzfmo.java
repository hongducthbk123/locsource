package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfmo extends zzflm<zzfmo> implements Cloneable {
    private String[] zza;
    private String[] zzb;
    private int[] zzc;
    private long[] zzd;
    private long[] zze;

    public zzfmo() {
        this.zza = zzflv.zzf;
        this.zzb = zzflv.zzf;
        this.zzc = zzflv.zza;
        this.zzd = zzflv.zzb;
        this.zze = zzflv.zzb;
        this.zzax = null;
        this.zzay = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public zzfmo clone() {
        try {
            zzfmo zzfmo = (zzfmo) super.clone();
            if (this.zza != null && this.zza.length > 0) {
                zzfmo.zza = (String[]) this.zza.clone();
            }
            if (this.zzb != null && this.zzb.length > 0) {
                zzfmo.zzb = (String[]) this.zzb.clone();
            }
            if (this.zzc != null && this.zzc.length > 0) {
                zzfmo.zzc = (int[]) this.zzc.clone();
            }
            if (this.zzd != null && this.zzd.length > 0) {
                zzfmo.zzd = (long[]) this.zzd.clone();
            }
            if (this.zze != null && this.zze.length > 0) {
                zzfmo.zze = (long[]) this.zze.clone();
            }
            return zzfmo;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfmo)) {
            return false;
        }
        zzfmo zzfmo = (zzfmo) obj;
        if (!zzflq.zza((Object[]) this.zza, (Object[]) zzfmo.zza)) {
            return false;
        }
        if (!zzflq.zza((Object[]) this.zzb, (Object[]) zzfmo.zzb)) {
            return false;
        }
        if (!zzflq.zza(this.zzc, zzfmo.zzc)) {
            return false;
        }
        if (!zzflq.zza(this.zzd, zzfmo.zzd)) {
            return false;
        }
        if (!zzflq.zza(this.zze, zzfmo.zze)) {
            return false;
        }
        return (this.zzax == null || this.zzax.zzb()) ? zzfmo.zzax == null || zzfmo.zzax.zzb() : this.zzax.equals(zzfmo.zzax);
    }

    public final int hashCode() {
        return ((this.zzax == null || this.zzax.zzb()) ? 0 : this.zzax.hashCode()) + ((((((((((((getClass().getName().hashCode() + 527) * 31) + zzflq.zza((Object[]) this.zza)) * 31) + zzflq.zza((Object[]) this.zzb)) * 31) + zzflq.zza(this.zzc)) * 31) + zzflq.zza(this.zzd)) * 31) + zzflq.zza(this.zze)) * 31);
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        int i;
        int zza2 = super.zza();
        if (this.zza == null || this.zza.length <= 0) {
            i = zza2;
        } else {
            int i2 = 0;
            int i3 = 0;
            for (String str : this.zza) {
                if (str != null) {
                    i3++;
                    i2 += zzflk.zza(str);
                }
            }
            i = zza2 + i2 + (i3 * 1);
        }
        if (this.zzb != null && this.zzb.length > 0) {
            int i4 = 0;
            int i5 = 0;
            for (String str2 : this.zzb) {
                if (str2 != null) {
                    i5++;
                    i4 += zzflk.zza(str2);
                }
            }
            i = i + i4 + (i5 * 1);
        }
        if (this.zzc != null && this.zzc.length > 0) {
            int i6 = 0;
            for (int zza3 : this.zzc) {
                i6 += zzflk.zza(zza3);
            }
            i = i + i6 + (this.zzc.length * 1);
        }
        if (this.zzd != null && this.zzd.length > 0) {
            int i7 = 0;
            for (long zza4 : this.zzd) {
                i7 += zzflk.zza(zza4);
            }
            i = i + i7 + (this.zzd.length * 1);
        }
        if (this.zze == null || this.zze.length <= 0) {
            return i;
        }
        int i8 = 0;
        for (long zza5 : this.zze) {
            i8 += zzflk.zza(zza5);
        }
        return i + i8 + (this.zze.length * 1);
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zza2 = zzflj.zza();
            switch (zza2) {
                case 0:
                    break;
                case 10:
                    int zza3 = zzflv.zza(zzflj, 10);
                    int length = this.zza == null ? 0 : this.zza.length;
                    String[] strArr = new String[(zza3 + length)];
                    if (length != 0) {
                        System.arraycopy(this.zza, 0, strArr, 0, length);
                    }
                    while (length < strArr.length - 1) {
                        strArr[length] = zzflj.zze();
                        zzflj.zza();
                        length++;
                    }
                    strArr[length] = zzflj.zze();
                    this.zza = strArr;
                    continue;
                case 18:
                    int zza4 = zzflv.zza(zzflj, 18);
                    int length2 = this.zzb == null ? 0 : this.zzb.length;
                    String[] strArr2 = new String[(zza4 + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.zzb, 0, strArr2, 0, length2);
                    }
                    while (length2 < strArr2.length - 1) {
                        strArr2[length2] = zzflj.zze();
                        zzflj.zza();
                        length2++;
                    }
                    strArr2[length2] = zzflj.zze();
                    this.zzb = strArr2;
                    continue;
                case 24:
                    int zza5 = zzflv.zza(zzflj, 24);
                    int length3 = this.zzc == null ? 0 : this.zzc.length;
                    int[] iArr = new int[(zza5 + length3)];
                    if (length3 != 0) {
                        System.arraycopy(this.zzc, 0, iArr, 0, length3);
                    }
                    while (length3 < iArr.length - 1) {
                        iArr[length3] = zzflj.zzc();
                        zzflj.zza();
                        length3++;
                    }
                    iArr[length3] = zzflj.zzc();
                    this.zzc = iArr;
                    continue;
                case 26:
                    int zzc2 = zzflj.zzc(zzflj.zzh());
                    int zzm = zzflj.zzm();
                    int i = 0;
                    while (zzflj.zzl() > 0) {
                        zzflj.zzc();
                        i++;
                    }
                    zzflj.zze(zzm);
                    int length4 = this.zzc == null ? 0 : this.zzc.length;
                    int[] iArr2 = new int[(i + length4)];
                    if (length4 != 0) {
                        System.arraycopy(this.zzc, 0, iArr2, 0, length4);
                    }
                    while (length4 < iArr2.length) {
                        iArr2[length4] = zzflj.zzc();
                        length4++;
                    }
                    this.zzc = iArr2;
                    zzflj.zzd(zzc2);
                    continue;
                case 32:
                    int zza6 = zzflv.zza(zzflj, 32);
                    int length5 = this.zzd == null ? 0 : this.zzd.length;
                    long[] jArr = new long[(zza6 + length5)];
                    if (length5 != 0) {
                        System.arraycopy(this.zzd, 0, jArr, 0, length5);
                    }
                    while (length5 < jArr.length - 1) {
                        jArr[length5] = zzflj.zzb();
                        zzflj.zza();
                        length5++;
                    }
                    jArr[length5] = zzflj.zzb();
                    this.zzd = jArr;
                    continue;
                case 34:
                    int zzc3 = zzflj.zzc(zzflj.zzh());
                    int zzm2 = zzflj.zzm();
                    int i2 = 0;
                    while (zzflj.zzl() > 0) {
                        zzflj.zzb();
                        i2++;
                    }
                    zzflj.zze(zzm2);
                    int length6 = this.zzd == null ? 0 : this.zzd.length;
                    long[] jArr2 = new long[(i2 + length6)];
                    if (length6 != 0) {
                        System.arraycopy(this.zzd, 0, jArr2, 0, length6);
                    }
                    while (length6 < jArr2.length) {
                        jArr2[length6] = zzflj.zzb();
                        length6++;
                    }
                    this.zzd = jArr2;
                    zzflj.zzd(zzc3);
                    continue;
                case 40:
                    int zza7 = zzflv.zza(zzflj, 40);
                    int length7 = this.zze == null ? 0 : this.zze.length;
                    long[] jArr3 = new long[(zza7 + length7)];
                    if (length7 != 0) {
                        System.arraycopy(this.zze, 0, jArr3, 0, length7);
                    }
                    while (length7 < jArr3.length - 1) {
                        jArr3[length7] = zzflj.zzb();
                        zzflj.zza();
                        length7++;
                    }
                    jArr3[length7] = zzflj.zzb();
                    this.zze = jArr3;
                    continue;
                case 42:
                    int zzc4 = zzflj.zzc(zzflj.zzh());
                    int zzm3 = zzflj.zzm();
                    int i3 = 0;
                    while (zzflj.zzl() > 0) {
                        zzflj.zzb();
                        i3++;
                    }
                    zzflj.zze(zzm3);
                    int length8 = this.zze == null ? 0 : this.zze.length;
                    long[] jArr4 = new long[(i3 + length8)];
                    if (length8 != 0) {
                        System.arraycopy(this.zze, 0, jArr4, 0, length8);
                    }
                    while (length8 < jArr4.length) {
                        jArr4[length8] = zzflj.zzb();
                        length8++;
                    }
                    this.zze = jArr4;
                    zzflj.zzd(zzc4);
                    continue;
                default:
                    if (!super.zza(zzflj, zza2)) {
                        break;
                    } else {
                        continue;
                    }
            }
        }
        return this;
    }

    public final void zza(zzflk zzflk) throws IOException {
        if (this.zza != null && this.zza.length > 0) {
            for (String str : this.zza) {
                if (str != null) {
                    zzflk.zza(1, str);
                }
            }
        }
        if (this.zzb != null && this.zzb.length > 0) {
            for (String str2 : this.zzb) {
                if (str2 != null) {
                    zzflk.zza(2, str2);
                }
            }
        }
        if (this.zzc != null && this.zzc.length > 0) {
            for (int zza2 : this.zzc) {
                zzflk.zza(3, zza2);
            }
        }
        if (this.zzd != null && this.zzd.length > 0) {
            for (long zzb2 : this.zzd) {
                zzflk.zzb(4, zzb2);
            }
        }
        if (this.zze != null && this.zze.length > 0) {
            for (long zzb3 : this.zze) {
                zzflk.zzb(5, zzb3);
            }
        }
        super.zza(zzflk);
    }

    public final /* synthetic */ zzflm zzc() throws CloneNotSupportedException {
        return (zzfmo) clone();
    }

    public final /* synthetic */ zzfls zzd() throws CloneNotSupportedException {
        return (zzfmo) clone();
    }
}
