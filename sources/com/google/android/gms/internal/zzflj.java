package com.google.android.gms.internal;

import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import java.io.IOException;

public final class zzflj {
    private final byte[] zza;
    private final int zzb;
    private final int zzc;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
    private int zzi;
    private int zzj = 64;
    private int zzk = 67108864;

    private zzflj(byte[] bArr, int i, int i2) {
        this.zza = bArr;
        this.zzb = i;
        int i3 = i + i2;
        this.zzd = i3;
        this.zzc = i3;
        this.zzf = i;
    }

    public static zzflj zza(byte[] bArr) {
        return zza(bArr, 0, bArr.length);
    }

    public static zzflj zza(byte[] bArr, int i, int i2) {
        return new zzflj(bArr, 0, i2);
    }

    private final void zzf(int i) throws IOException {
        if (i < 0) {
            throw zzflr.zzb();
        } else if (this.zzf + i > this.zzh) {
            zzf(this.zzh - this.zzf);
            throw zzflr.zza();
        } else if (i <= this.zzd - this.zzf) {
            this.zzf += i;
        } else {
            throw zzflr.zza();
        }
    }

    private final void zzn() {
        this.zzd += this.zze;
        int i = this.zzd;
        if (i > this.zzh) {
            this.zze = i - this.zzh;
            this.zzd -= this.zze;
            return;
        }
        this.zze = 0;
    }

    private final byte zzo() throws IOException {
        if (this.zzf == this.zzd) {
            throw zzflr.zza();
        }
        byte[] bArr = this.zza;
        int i = this.zzf;
        this.zzf = i + 1;
        return bArr[i];
    }

    public final int zza() throws IOException {
        if (this.zzf == this.zzd) {
            this.zzg = 0;
            return 0;
        }
        this.zzg = zzh();
        if (this.zzg != 0) {
            return this.zzg;
        }
        throw new zzflr("Protocol message contained an invalid tag (zero).");
    }

    public final void zza(int i) throws zzflr {
        if (this.zzg != i) {
            throw new zzflr("Protocol message end-group tag did not match expected tag.");
        }
    }

    public final void zza(zzfls zzfls) throws IOException {
        int zzh2 = zzh();
        if (this.zzi >= this.zzj) {
            throw zzflr.zzd();
        }
        int zzc2 = zzc(zzh2);
        this.zzi++;
        zzfls.zza(this);
        zza(0);
        this.zzi--;
        zzd(zzc2);
    }

    public final void zza(zzfls zzfls, int i) throws IOException {
        if (this.zzi >= this.zzj) {
            throw zzflr.zzd();
        }
        this.zzi++;
        zzfls.zza(this);
        zza((i << 3) | 4);
        this.zzi--;
    }

    public final byte[] zza(int i, int i2) {
        if (i2 == 0) {
            return zzflv.zzh;
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(this.zza, this.zzb + i, bArr, 0, i2);
        return bArr;
    }

    public final long zzb() throws IOException {
        return zzi();
    }

    /* access modifiers changed from: 0000 */
    public final void zzb(int i, int i2) {
        if (i > this.zzf - this.zzb) {
            throw new IllegalArgumentException("Position " + i + " is beyond current " + (this.zzf - this.zzb));
        } else if (i < 0) {
            throw new IllegalArgumentException("Bad position " + i);
        } else {
            this.zzf = this.zzb + i;
            this.zzg = i2;
        }
    }

    public final boolean zzb(int i) throws IOException {
        int zza2;
        switch (i & 7) {
            case 0:
                zzh();
                return true;
            case 1:
                zzk();
                return true;
            case 2:
                zzf(zzh());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                zzj();
                return true;
            default:
                throw new zzflr("Protocol message tag had invalid wire type.");
        }
        do {
            zza2 = zza();
            if (zza2 != 0) {
            }
            zza(((i >>> 3) << 3) | 4);
            return true;
        } while (zzb(zza2));
        zza(((i >>> 3) << 3) | 4);
        return true;
    }

    public final int zzc() throws IOException {
        return zzh();
    }

    public final int zzc(int i) throws zzflr {
        if (i < 0) {
            throw zzflr.zzb();
        }
        int i2 = this.zzf + i;
        int i3 = this.zzh;
        if (i2 > i3) {
            throw zzflr.zza();
        }
        this.zzh = i2;
        zzn();
        return i3;
    }

    public final void zzd(int i) {
        this.zzh = i;
        zzn();
    }

    public final boolean zzd() throws IOException {
        return zzh() != 0;
    }

    public final String zze() throws IOException {
        int zzh2 = zzh();
        if (zzh2 < 0) {
            throw zzflr.zzb();
        } else if (zzh2 > this.zzd - this.zzf) {
            throw zzflr.zza();
        } else {
            String str = new String(this.zza, this.zzf, zzh2, zzflq.zza);
            this.zzf = zzh2 + this.zzf;
            return str;
        }
    }

    public final void zze(int i) {
        zzb(i, this.zzg);
    }

    public final byte[] zzf() throws IOException {
        int zzh2 = zzh();
        if (zzh2 < 0) {
            throw zzflr.zzb();
        } else if (zzh2 == 0) {
            return zzflv.zzh;
        } else {
            if (zzh2 > this.zzd - this.zzf) {
                throw zzflr.zza();
            }
            byte[] bArr = new byte[zzh2];
            System.arraycopy(this.zza, this.zzf, bArr, 0, zzh2);
            this.zzf = zzh2 + this.zzf;
            return bArr;
        }
    }

    public final long zzg() throws IOException {
        long zzi2 = zzi();
        return (-(zzi2 & 1)) ^ (zzi2 >>> 1);
    }

    public final int zzh() throws IOException {
        byte zzo = zzo();
        if (zzo >= 0) {
            return zzo;
        }
        byte b = zzo & Ascii.DEL;
        byte zzo2 = zzo();
        if (zzo2 >= 0) {
            return b | (zzo2 << 7);
        }
        byte b2 = b | ((zzo2 & Ascii.DEL) << 7);
        byte zzo3 = zzo();
        if (zzo3 >= 0) {
            return b2 | (zzo3 << Ascii.f978SO);
        }
        byte b3 = b2 | ((zzo3 & Ascii.DEL) << Ascii.f978SO);
        byte zzo4 = zzo();
        if (zzo4 >= 0) {
            return b3 | (zzo4 << Ascii.NAK);
        }
        byte b4 = b3 | ((zzo4 & Ascii.DEL) << Ascii.NAK);
        byte zzo5 = zzo();
        byte b5 = b4 | (zzo5 << Ascii.f971FS);
        if (zzo5 >= 0) {
            return b5;
        }
        for (int i = 0; i < 5; i++) {
            if (zzo() >= 0) {
                return b5;
            }
        }
        throw zzflr.zzc();
    }

    public final long zzi() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zzo = zzo();
            j |= ((long) (zzo & Ascii.DEL)) << i;
            if ((zzo & UnsignedBytes.MAX_POWER_OF_TWO) == 0) {
                return j;
            }
        }
        throw zzflr.zzc();
    }

    public final int zzj() throws IOException {
        return (zzo() & 255) | ((zzo() & 255) << 8) | ((zzo() & 255) << Ascii.DLE) | ((zzo() & 255) << Ascii.CAN);
    }

    public final long zzk() throws IOException {
        byte zzo = zzo();
        byte zzo2 = zzo();
        return ((((long) zzo2) & 255) << 8) | (((long) zzo) & 255) | ((((long) zzo()) & 255) << 16) | ((((long) zzo()) & 255) << 24) | ((((long) zzo()) & 255) << 32) | ((((long) zzo()) & 255) << 40) | ((((long) zzo()) & 255) << 48) | ((((long) zzo()) & 255) << 56);
    }

    public final int zzl() {
        if (this.zzh == Integer.MAX_VALUE) {
            return -1;
        }
        return this.zzh - this.zzf;
    }

    public final int zzm() {
        return this.zzf - this.zzb;
    }
}
