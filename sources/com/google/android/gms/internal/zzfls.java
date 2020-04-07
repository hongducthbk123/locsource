package com.google.android.gms.internal;

import java.io.IOException;

public abstract class zzfls {
    protected volatile int zzay = -1;

    public static final <T extends zzfls> T zza(T t, byte[] bArr) throws zzflr {
        return zza(t, bArr, 0, bArr.length);
    }

    private static <T extends zzfls> T zza(T t, byte[] bArr, int i, int i2) throws zzflr {
        try {
            zzflj zza = zzflj.zza(bArr, 0, i2);
            t.zza(zza);
            zza.zza(0);
            return t;
        } catch (zzflr e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e2);
        }
    }

    public static final byte[] zza(zzfls zzfls) {
        byte[] bArr = new byte[zzfls.zzf()];
        try {
            zzflk zza = zzflk.zza(bArr, 0, bArr.length);
            zzfls.zza(zza);
            zza.zza();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public String toString() {
        return zzflt.zza(this);
    }

    /* access modifiers changed from: protected */
    public int zza() {
        return 0;
    }

    public abstract zzfls zza(zzflj zzflj) throws IOException;

    public void zza(zzflk zzflk) throws IOException {
    }

    /* renamed from: zzd */
    public zzfls clone() throws CloneNotSupportedException {
        return (zzfls) super.clone();
    }

    public final int zze() {
        if (this.zzay < 0) {
            zzf();
        }
        return this.zzay;
    }

    public final int zzf() {
        int zza = zza();
        this.zzay = zza;
        return zza;
    }
}
