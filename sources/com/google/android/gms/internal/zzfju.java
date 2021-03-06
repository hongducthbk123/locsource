package com.google.android.gms.internal;

import java.io.IOException;
import java.io.InputStream;

final class zzfju extends InputStream {
    private zzfjt zza;
    private zzfgy zzb;
    private int zzc;
    private int zzd;
    private int zze;
    private int zzf;
    private /* synthetic */ zzfjq zzg;

    public zzfju(zzfjq zzfjq) {
        this.zzg = zzfjq;
        zza();
    }

    private final int zza(byte[] bArr, int i, int i2) {
        int i3 = i2;
        int i4 = i;
        while (true) {
            if (i3 <= 0) {
                break;
            }
            zzb();
            if (this.zzb != null) {
                int min = Math.min(this.zzc - this.zzd, i3);
                if (bArr != null) {
                    this.zzb.zza(bArr, this.zzd, i4, min);
                    i4 += min;
                }
                this.zzd += min;
                i3 -= min;
            } else if (i3 == i2) {
                return -1;
            }
        }
        return i2 - i3;
    }

    private final void zza() {
        this.zza = new zzfjt(this.zzg);
        this.zzb = (zzfgy) this.zza.next();
        this.zzc = this.zzb.zza();
        this.zzd = 0;
        this.zze = 0;
    }

    private final void zzb() {
        if (this.zzb != null && this.zzd == this.zzc) {
            this.zze += this.zzc;
            this.zzd = 0;
            if (this.zza.hasNext()) {
                this.zzb = (zzfgy) this.zza.next();
                this.zzc = this.zzb.zza();
                return;
            }
            this.zzb = null;
            this.zzc = 0;
        }
    }

    public final int available() throws IOException {
        return this.zzg.zza() - (this.zze + this.zzd);
    }

    public final void mark(int i) {
        this.zzf = this.zze + this.zzd;
    }

    public final boolean markSupported() {
        return true;
    }

    public final int read() throws IOException {
        zzb();
        if (this.zzb == null) {
            return -1;
        }
        zzfgy zzfgy = this.zzb;
        int i = this.zzd;
        this.zzd = i + 1;
        return zzfgy.zza(i) & 255;
    }

    public final int read(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new NullPointerException();
        } else if (i >= 0 && i2 >= 0 && i2 <= bArr.length - i) {
            return zza(bArr, i, i2);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public final synchronized void reset() {
        zza();
        zza(null, 0, this.zzf);
    }

    public final long skip(long j) {
        if (j < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (j > 2147483647L) {
            j = 2147483647L;
        }
        return (long) zza(null, 0, (int) j);
    }
}
