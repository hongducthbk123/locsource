package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

public final class zzfmq extends zzflm<zzfmq> implements Cloneable {
    private byte[] zza;
    private String zzb;
    private byte[][] zzc;
    private boolean zzd;

    public zzfmq() {
        this.zza = zzflv.zzh;
        this.zzb = "";
        this.zzc = zzflv.zzg;
        this.zzd = false;
        this.zzax = null;
        this.zzay = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public zzfmq clone() {
        try {
            zzfmq zzfmq = (zzfmq) super.clone();
            if (this.zzc != null && this.zzc.length > 0) {
                zzfmq.zzc = (byte[][]) this.zzc.clone();
            }
            return zzfmq;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfmq)) {
            return false;
        }
        zzfmq zzfmq = (zzfmq) obj;
        if (!Arrays.equals(this.zza, zzfmq.zza)) {
            return false;
        }
        if (this.zzb == null) {
            if (zzfmq.zzb != null) {
                return false;
            }
        } else if (!this.zzb.equals(zzfmq.zzb)) {
            return false;
        }
        if (!zzflq.zza(this.zzc, zzfmq.zzc)) {
            return false;
        }
        if (this.zzd != zzfmq.zzd) {
            return false;
        }
        return (this.zzax == null || this.zzax.zzb()) ? zzfmq.zzax == null || zzfmq.zzax.zzb() : this.zzax.equals(zzfmq.zzax);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.zzd ? 1231 : 1237) + (((((this.zzb == null ? 0 : this.zzb.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + Arrays.hashCode(this.zza)) * 31)) * 31) + zzflq.zza(this.zzc)) * 31)) * 31;
        if (this.zzax != null && !this.zzax.zzb()) {
            i = this.zzax.hashCode();
        }
        return hashCode + i;
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        int zza2 = super.zza();
        if (!Arrays.equals(this.zza, zzflv.zzh)) {
            zza2 += zzflk.zzb(1, this.zza);
        }
        if (this.zzc != null && this.zzc.length > 0) {
            int i = 0;
            int i2 = 0;
            for (byte[] bArr : this.zzc) {
                if (bArr != null) {
                    i2++;
                    i += zzflk.zzb(bArr);
                }
            }
            zza2 = zza2 + i + (i2 * 1);
        }
        if (this.zzd) {
            zza2 += zzflk.zzb(3) + 1;
        }
        return (this.zzb == null || this.zzb.equals("")) ? zza2 : zza2 + zzflk.zzb(4, this.zzb);
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zza2 = zzflj.zza();
            switch (zza2) {
                case 0:
                    break;
                case 10:
                    this.zza = zzflj.zzf();
                    continue;
                case 18:
                    int zza3 = zzflv.zza(zzflj, 18);
                    int length = this.zzc == null ? 0 : this.zzc.length;
                    byte[][] bArr = new byte[(zza3 + length)][];
                    if (length != 0) {
                        System.arraycopy(this.zzc, 0, bArr, 0, length);
                    }
                    while (length < bArr.length - 1) {
                        bArr[length] = zzflj.zzf();
                        zzflj.zza();
                        length++;
                    }
                    bArr[length] = zzflj.zzf();
                    this.zzc = bArr;
                    continue;
                case 24:
                    this.zzd = zzflj.zzd();
                    continue;
                case 34:
                    this.zzb = zzflj.zze();
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
        if (!Arrays.equals(this.zza, zzflv.zzh)) {
            zzflk.zza(1, this.zza);
        }
        if (this.zzc != null && this.zzc.length > 0) {
            for (byte[] bArr : this.zzc) {
                if (bArr != null) {
                    zzflk.zza(2, bArr);
                }
            }
        }
        if (this.zzd) {
            zzflk.zza(3, this.zzd);
        }
        if (this.zzb != null && !this.zzb.equals("")) {
            zzflk.zza(4, this.zzb);
        }
        super.zza(zzflk);
    }

    public final /* synthetic */ zzflm zzc() throws CloneNotSupportedException {
        return (zzfmq) clone();
    }

    public final /* synthetic */ zzfls zzd() throws CloneNotSupportedException {
        return (zzfmq) clone();
    }
}
