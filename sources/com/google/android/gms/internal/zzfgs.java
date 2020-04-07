package com.google.android.gms.internal;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

public abstract class zzfgs implements Serializable, Iterable<Byte> {
    public static final zzfgs zza = new zzfgz(zzfhz.zzb);
    private static final zzfgw zzb = (zzfgo.zza() ? new zzfha(null) : new zzfgu(null));
    private int zzc = 0;

    zzfgs() {
    }

    public static zzfgs zza(Iterable<zzfgs> iterable) {
        int size = ((Collection) iterable).size();
        return size == 0 ? zza : zza(iterable.iterator(), size);
    }

    public static zzfgs zza(String str) {
        return new zzfgz(str.getBytes(zzfhz.zza));
    }

    private static zzfgs zza(Iterator<zzfgs> it, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException(String.format("length (%s) must be >= 1", new Object[]{Integer.valueOf(i)}));
        } else if (i == 1) {
            return (zzfgs) it.next();
        } else {
            int i2 = i >>> 1;
            zzfgs zza2 = zza(it, i2);
            zzfgs zza3 = zza(it, i - i2);
            if (ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED - zza2.zza() >= zza3.zza()) {
                return zzfjq.zza(zza2, zza3);
            }
            int zza4 = zza2.zza();
            throw new IllegalArgumentException("ByteString would be too long: " + zza4 + "+" + zza3.zza());
        }
    }

    public static zzfgs zza(byte[] bArr) {
        return zza(bArr, 0, bArr.length);
    }

    public static zzfgs zza(byte[] bArr, int i, int i2) {
        return new zzfgz(zzb.zza(bArr, i, i2));
    }

    static int zzb(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((i | i2 | i4 | (i3 - i2)) >= 0) {
            return i4;
        }
        if (i < 0) {
            throw new IndexOutOfBoundsException("Beginning index: " + i + " < 0");
        } else if (i2 < i) {
            throw new IndexOutOfBoundsException("Beginning index larger than ending index: " + i + ", " + i2);
        } else {
            throw new IndexOutOfBoundsException("End index: " + i2 + " >= " + i3);
        }
    }

    static zzfgs zzb(byte[] bArr) {
        return new zzfgz(bArr);
    }

    static zzfgx zzb(int i) {
        return new zzfgx(i, null);
    }

    static void zzb(int i, int i2) {
        if (((i2 - (i + 1)) | i) >= 0) {
            return;
        }
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException("Index < 0: " + i);
        }
        throw new ArrayIndexOutOfBoundsException("Index > length: " + i + ", " + i2);
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int i = this.zzc;
        if (i == 0) {
            int zza2 = zza();
            i = zza(zza2, 0, zza2);
            if (i == 0) {
                i = 1;
            }
            this.zzc = i;
        }
        return i;
    }

    public /* synthetic */ Iterator iterator() {
        return new zzfgt(this);
    }

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", new Object[]{Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(zza())});
    }

    public abstract byte zza(int i);

    public abstract int zza();

    /* access modifiers changed from: protected */
    public abstract int zza(int i, int i2, int i3);

    public abstract zzfgs zza(int i, int i2);

    /* access modifiers changed from: 0000 */
    public abstract void zza(zzfgr zzfgr) throws IOException;

    public final void zza(byte[] bArr, int i, int i2, int i3) {
        zzb(i, i + i3, zza());
        zzb(i2, i2 + i3, bArr.length);
        if (i3 > 0) {
            zzb(bArr, i, i2, i3);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void zzb(byte[] bArr, int i, int i2, int i3);

    public final boolean zzb() {
        return zza() == 0;
    }

    public final byte[] zzc() {
        int zza2 = zza();
        if (zza2 == 0) {
            return zzfhz.zzb;
        }
        byte[] bArr = new byte[zza2];
        zzb(bArr, 0, 0, zza2);
        return bArr;
    }

    public abstract zzfhb zzd();

    /* access modifiers changed from: protected */
    public abstract int zze();

    /* access modifiers changed from: protected */
    public abstract boolean zzf();

    /* access modifiers changed from: protected */
    public final int zzg() {
        return this.zzc;
    }
}
