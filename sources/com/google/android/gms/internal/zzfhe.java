package com.google.android.gms.internal;

import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class zzfhe extends zzfhb {
    private final InputStream zzd;
    private final byte[] zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private zzfhf zzl;

    private zzfhe(InputStream inputStream, int i) {
        super();
        this.zzk = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.zzl = null;
        zzfhz.zza(inputStream, "input");
        this.zzd = inputStream;
        this.zze = new byte[i];
        this.zzf = 0;
        this.zzh = 0;
        this.zzj = 0;
    }

    private final void zzaa() {
        this.zzf += this.zzg;
        int i = this.zzj + this.zzf;
        if (i > this.zzk) {
            this.zzg = i - this.zzk;
            this.zzf -= this.zzg;
            return;
        }
        this.zzg = 0;
    }

    private final byte zzab() throws IOException {
        if (this.zzh == this.zzf) {
            zzh(1);
        }
        byte[] bArr = this.zze;
        int i = this.zzh;
        this.zzh = i + 1;
        return bArr[i];
    }

    private final void zzh(int i) throws IOException {
        if (zzi(i)) {
            return;
        }
        if (i > (this.zzc - this.zzj) - this.zzh) {
            throw zzfie.zzh();
        }
        throw zzfie.zza();
    }

    private final boolean zzi(int i) throws IOException {
        while (this.zzh + i > this.zzf) {
            if (i > (this.zzc - this.zzj) - this.zzh || this.zzj + this.zzh + i > this.zzk) {
                return false;
            }
            int i2 = this.zzh;
            if (i2 > 0) {
                if (this.zzf > i2) {
                    System.arraycopy(this.zze, i2, this.zze, 0, this.zzf - i2);
                }
                this.zzj += i2;
                this.zzf -= i2;
                this.zzh = 0;
            }
            int read = this.zzd.read(this.zze, this.zzf, Math.min(this.zze.length - this.zzf, (this.zzc - this.zzj) - this.zzf));
            if (read == 0 || read < -1 || read > this.zze.length) {
                throw new IllegalStateException("InputStream#read(byte[]) returned invalid result: " + read + "\nThe InputStream implementation is buggy.");
            } else if (read <= 0) {
                return false;
            } else {
                this.zzf = read + this.zzf;
                zzaa();
                if (this.zzf >= i) {
                    return true;
                }
            }
        }
        throw new IllegalStateException("refillBuffer() called when " + i + " bytes were already available in buffer");
    }

    private final byte[] zzj(int i) throws IOException {
        byte[] zzk2 = zzk(i);
        if (zzk2 != null) {
            return zzk2;
        }
        int i2 = this.zzh;
        int i3 = this.zzf - this.zzh;
        this.zzj += this.zzf;
        this.zzh = 0;
        this.zzf = 0;
        List zzl2 = zzl(i - i3);
        byte[] bArr = new byte[i];
        System.arraycopy(this.zze, i2, bArr, 0, i3);
        Iterator it = zzl2.iterator();
        while (true) {
            int i4 = i3;
            if (!it.hasNext()) {
                return bArr;
            }
            byte[] bArr2 = (byte[]) it.next();
            System.arraycopy(bArr2, 0, bArr, i4, bArr2.length);
            i3 = bArr2.length + i4;
        }
    }

    private final byte[] zzk(int i) throws IOException {
        if (i == 0) {
            return zzfhz.zzb;
        }
        if (i < 0) {
            throw zzfie.zzb();
        }
        int i2 = this.zzj + this.zzh + i;
        if (i2 - this.zzc > 0) {
            throw zzfie.zzh();
        } else if (i2 > this.zzk) {
            zzf((this.zzk - this.zzj) - this.zzh);
            throw zzfie.zza();
        } else {
            int i3 = this.zzf - this.zzh;
            int i4 = i - i3;
            if (i4 >= 4096 && i4 > this.zzd.available()) {
                return null;
            }
            byte[] bArr = new byte[i];
            System.arraycopy(this.zze, this.zzh, bArr, 0, i3);
            this.zzj += this.zzf;
            this.zzh = 0;
            this.zzf = 0;
            while (i3 < bArr.length) {
                int read = this.zzd.read(bArr, i3, i - i3);
                if (read == -1) {
                    throw zzfie.zza();
                }
                this.zzj += read;
                i3 += read;
            }
            return bArr;
        }
    }

    private final List<byte[]> zzl(int i) throws IOException {
        ArrayList arrayList = new ArrayList();
        while (i > 0) {
            byte[] bArr = new byte[Math.min(i, 4096)];
            int i2 = 0;
            while (i2 < bArr.length) {
                int read = this.zzd.read(bArr, i2, bArr.length - i2);
                if (read == -1) {
                    throw zzfie.zza();
                }
                this.zzj += read;
                i2 += read;
            }
            i -= bArr.length;
            arrayList.add(bArr);
        }
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00b2, code lost:
        if (((long) r4[r3]) < 0) goto L_0x00b4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final long zzx() throws java.io.IOException {
        /*
            r10 = this;
            r8 = 0
            int r0 = r10.zzh
            int r1 = r10.zzf
            if (r1 == r0) goto L_0x00b4
            byte[] r4 = r10.zze
            int r1 = r0 + 1
            byte r0 = r4[r0]
            if (r0 < 0) goto L_0x0014
            r10.zzh = r1
            long r0 = (long) r0
        L_0x0013:
            return r0
        L_0x0014:
            int r2 = r10.zzf
            int r2 = r2 - r1
            r3 = 9
            if (r2 < r3) goto L_0x00b4
            int r2 = r1 + 1
            byte r1 = r4[r1]
            int r1 = r1 << 7
            r0 = r0 ^ r1
            if (r0 >= 0) goto L_0x002a
            r0 = r0 ^ -128(0xffffffffffffff80, float:NaN)
            long r0 = (long) r0
        L_0x0027:
            r10.zzh = r2
            goto L_0x0013
        L_0x002a:
            int r3 = r2 + 1
            byte r1 = r4[r2]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L_0x0038
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            long r0 = (long) r0
            r2 = r3
            goto L_0x0027
        L_0x0038:
            int r2 = r3 + 1
            byte r1 = r4[r3]
            int r1 = r1 << 21
            r0 = r0 ^ r1
            if (r0 >= 0) goto L_0x0047
            r1 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r1
            long r0 = (long) r0
            goto L_0x0027
        L_0x0047:
            long r0 = (long) r0
            int r3 = r2 + 1
            byte r2 = r4[r2]
            long r6 = (long) r2
            r2 = 28
            long r6 = r6 << r2
            long r0 = r0 ^ r6
            int r2 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r2 < 0) goto L_0x005b
            r4 = 266354560(0xfe03f80, double:1.315966377E-315)
            long r0 = r0 ^ r4
            r2 = r3
            goto L_0x0027
        L_0x005b:
            int r2 = r3 + 1
            byte r3 = r4[r3]
            long r6 = (long) r3
            r3 = 35
            long r6 = r6 << r3
            long r0 = r0 ^ r6
            int r3 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r3 >= 0) goto L_0x006f
            r4 = -34093383808(0xfffffff80fe03f80, double:NaN)
            long r0 = r0 ^ r4
            goto L_0x0027
        L_0x006f:
            int r3 = r2 + 1
            byte r2 = r4[r2]
            long r6 = (long) r2
            r2 = 42
            long r6 = r6 << r2
            long r0 = r0 ^ r6
            int r2 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r2 < 0) goto L_0x0084
            r4 = 4363953127296(0x3f80fe03f80, double:2.1560793202584E-311)
            long r0 = r0 ^ r4
            r2 = r3
            goto L_0x0027
        L_0x0084:
            int r2 = r3 + 1
            byte r3 = r4[r3]
            long r6 = (long) r3
            r3 = 49
            long r6 = r6 << r3
            long r0 = r0 ^ r6
            int r3 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r3 >= 0) goto L_0x0098
            r4 = -558586000294016(0xfffe03f80fe03f80, double:NaN)
            long r0 = r0 ^ r4
            goto L_0x0027
        L_0x0098:
            int r3 = r2 + 1
            byte r2 = r4[r2]
            long r6 = (long) r2
            r2 = 56
            long r6 = r6 << r2
            long r0 = r0 ^ r6
            r6 = 71499008037633920(0xfe03f80fe03f80, double:6.838959413692434E-304)
            long r0 = r0 ^ r6
            int r2 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r2 >= 0) goto L_0x00ba
            int r2 = r3 + 1
            byte r3 = r4[r3]
            long r4 = (long) r3
            int r3 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r3 >= 0) goto L_0x0027
        L_0x00b4:
            long r0 = r10.zzt()
            goto L_0x0013
        L_0x00ba:
            r2 = r3
            goto L_0x0027
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfhe.zzx():long");
    }

    private final int zzy() throws IOException {
        int i = this.zzh;
        if (this.zzf - i < 4) {
            zzh(4);
            i = this.zzh;
        }
        byte[] bArr = this.zze;
        this.zzh = i + 4;
        return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << Ascii.DLE);
    }

    private final long zzz() throws IOException {
        int i = this.zzh;
        if (this.zzf - i < 8) {
            zzh(8);
            i = this.zzh;
        }
        byte[] bArr = this.zze;
        this.zzh = i + 8;
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    public final int zza() throws IOException {
        if (zzv()) {
            this.zzi = 0;
            return 0;
        }
        this.zzi = zzs();
        if ((this.zzi >>> 3) != 0) {
            return this.zzi;
        }
        throw zzfie.zzd();
    }

    public final <T extends zzfhu<T, ?>> T zza(T t, zzfhm zzfhm) throws IOException {
        int zzs = zzs();
        if (this.zza >= this.zzb) {
            throw zzfie.zzg();
        }
        int zzd2 = zzd(zzs);
        this.zza++;
        T zza = zzfhu.zza(t, (zzfhb) this, zzfhm);
        zza(0);
        this.zza--;
        zze(zzd2);
        return zza;
    }

    public final void zza(int i) throws zzfie {
        if (this.zzi != i) {
            throw zzfie.zze();
        }
    }

    public final void zza(zzfjd zzfjd, zzfhm zzfhm) throws IOException {
        int zzs = zzs();
        if (this.zza >= this.zzb) {
            throw zzfie.zzg();
        }
        int zzd2 = zzd(zzs);
        this.zza++;
        zzfjd.zzb(this, zzfhm);
        zza(0);
        this.zza--;
        zze(zzd2);
    }

    public final double zzb() throws IOException {
        return Double.longBitsToDouble(zzz());
    }

    public final boolean zzb(int i) throws IOException {
        int zza;
        int i2 = 0;
        switch (i & 7) {
            case 0:
                if (this.zzf - this.zzh >= 10) {
                    while (i2 < 10) {
                        byte[] bArr = this.zze;
                        int i3 = this.zzh;
                        this.zzh = i3 + 1;
                        if (bArr[i3] >= 0) {
                            return true;
                        }
                        i2++;
                    }
                    throw zzfie.zzc();
                }
                while (i2 < 10) {
                    if (zzab() >= 0) {
                        return true;
                    }
                    i2++;
                }
                throw zzfie.zzc();
            case 1:
                zzf(8);
                return true;
            case 2:
                zzf(zzs());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                zzf(4);
                return true;
            default:
                throw zzfie.zzf();
        }
        do {
            zza = zza();
            if (zza != 0) {
            }
            zza(((i >>> 3) << 3) | 4);
            return true;
        } while (zzb(zza));
        zza(((i >>> 3) << 3) | 4);
        return true;
    }

    public final float zzc() throws IOException {
        return Float.intBitsToFloat(zzy());
    }

    public final int zzd(int i) throws zzfie {
        if (i < 0) {
            throw zzfie.zzb();
        }
        int i2 = this.zzj + this.zzh + i;
        int i3 = this.zzk;
        if (i2 > i3) {
            throw zzfie.zza();
        }
        this.zzk = i2;
        zzaa();
        return i3;
    }

    public final long zzd() throws IOException {
        return zzx();
    }

    public final long zze() throws IOException {
        return zzx();
    }

    public final void zze(int i) {
        this.zzk = i;
        zzaa();
    }

    public final int zzf() throws IOException {
        return zzs();
    }

    public final void zzf(int i) throws IOException {
        if (i <= this.zzf - this.zzh && i >= 0) {
            this.zzh += i;
        } else if (i < 0) {
            throw zzfie.zzb();
        } else if (this.zzj + this.zzh + i > this.zzk) {
            zzf((this.zzk - this.zzj) - this.zzh);
            throw zzfie.zza();
        } else {
            int i2 = this.zzf - this.zzh;
            this.zzh = this.zzf;
            zzh(1);
            while (i - i2 > this.zzf) {
                i2 += this.zzf;
                this.zzh = this.zzf;
                zzh(1);
            }
            this.zzh = i - i2;
        }
    }

    public final long zzg() throws IOException {
        return zzz();
    }

    public final int zzh() throws IOException {
        return zzy();
    }

    public final boolean zzi() throws IOException {
        return zzx() != 0;
    }

    public final String zzj() throws IOException {
        int zzs = zzs();
        if (zzs > 0 && zzs <= this.zzf - this.zzh) {
            String str = new String(this.zze, this.zzh, zzs, zzfhz.zza);
            this.zzh = zzs + this.zzh;
            return str;
        } else if (zzs == 0) {
            return "";
        } else {
            if (zzs > this.zzf) {
                return new String(zzj(zzs), zzfhz.zza);
            }
            zzh(zzs);
            String str2 = new String(this.zze, this.zzh, zzs, zzfhz.zza);
            this.zzh = zzs + this.zzh;
            return str2;
        }
    }

    public final String zzk() throws IOException {
        byte[] zzj2;
        int zzs = zzs();
        int i = this.zzh;
        if (zzs <= this.zzf - i && zzs > 0) {
            zzj2 = this.zze;
            this.zzh = i + zzs;
        } else if (zzs == 0) {
            return "";
        } else {
            if (zzs <= this.zzf) {
                zzh(zzs);
                byte[] bArr = this.zze;
                this.zzh = zzs;
                zzj2 = bArr;
                i = 0;
            } else {
                zzj2 = zzj(zzs);
                i = 0;
            }
        }
        if (zzfks.zza(zzj2, i, i + zzs)) {
            return new String(zzj2, i, zzs, zzfhz.zza);
        }
        throw zzfie.zzi();
    }

    public final zzfgs zzl() throws IOException {
        int zzs = zzs();
        if (zzs <= this.zzf - this.zzh && zzs > 0) {
            zzfgs zza = zzfgs.zza(this.zze, this.zzh, zzs);
            this.zzh = zzs + this.zzh;
            return zza;
        } else if (zzs == 0) {
            return zzfgs.zza;
        } else {
            byte[] zzk2 = zzk(zzs);
            if (zzk2 != null) {
                return zzfgs.zzb(zzk2);
            }
            int i = this.zzh;
            int i2 = this.zzf - this.zzh;
            this.zzj += this.zzf;
            this.zzh = 0;
            this.zzf = 0;
            List<byte[]> zzl2 = zzl(zzs - i2);
            ArrayList arrayList = new ArrayList(zzl2.size() + 1);
            arrayList.add(zzfgs.zza(this.zze, i, i2));
            for (byte[] zzb : zzl2) {
                arrayList.add(zzfgs.zzb(zzb));
            }
            return zzfgs.zza((Iterable<zzfgs>) arrayList);
        }
    }

    public final int zzm() throws IOException {
        return zzs();
    }

    public final int zzn() throws IOException {
        return zzs();
    }

    public final int zzo() throws IOException {
        return zzy();
    }

    public final long zzp() throws IOException {
        return zzz();
    }

    public final int zzq() throws IOException {
        return zzg(zzs());
    }

    public final long zzr() throws IOException {
        return zza(zzx());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x006a, code lost:
        if (r3[r2] < 0) goto L_0x006c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzs() throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.zzh
            int r1 = r5.zzf
            if (r1 == r0) goto L_0x006c
            byte[] r3 = r5.zze
            int r2 = r0 + 1
            byte r0 = r3[r0]
            if (r0 < 0) goto L_0x0011
            r5.zzh = r2
        L_0x0010:
            return r0
        L_0x0011:
            int r1 = r5.zzf
            int r1 = r1 - r2
            r4 = 9
            if (r1 < r4) goto L_0x006c
            int r1 = r2 + 1
            byte r2 = r3[r2]
            int r2 = r2 << 7
            r0 = r0 ^ r2
            if (r0 >= 0) goto L_0x0026
            r0 = r0 ^ -128(0xffffffffffffff80, float:NaN)
        L_0x0023:
            r5.zzh = r1
            goto L_0x0010
        L_0x0026:
            int r2 = r1 + 1
            byte r1 = r3[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L_0x0033
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            r1 = r2
            goto L_0x0023
        L_0x0033:
            int r1 = r2 + 1
            byte r2 = r3[r2]
            int r2 = r2 << 21
            r0 = r0 ^ r2
            if (r0 >= 0) goto L_0x0041
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L_0x0023
        L_0x0041:
            int r2 = r1 + 1
            byte r1 = r3[r1]
            int r4 = r1 << 28
            r0 = r0 ^ r4
            r4 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r4
            if (r1 >= 0) goto L_0x0072
            int r1 = r2 + 1
            byte r2 = r3[r2]
            if (r2 >= 0) goto L_0x0023
            int r2 = r1 + 1
            byte r1 = r3[r1]
            if (r1 >= 0) goto L_0x0072
            int r1 = r2 + 1
            byte r2 = r3[r2]
            if (r2 >= 0) goto L_0x0023
            int r2 = r1 + 1
            byte r1 = r3[r1]
            if (r1 >= 0) goto L_0x0072
            int r1 = r2 + 1
            byte r2 = r3[r2]
            if (r2 >= 0) goto L_0x0023
        L_0x006c:
            long r0 = r5.zzt()
            int r0 = (int) r0
            goto L_0x0010
        L_0x0072:
            r1 = r2
            goto L_0x0023
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfhe.zzs():int");
    }

    /* access modifiers changed from: 0000 */
    public final long zzt() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zzab = zzab();
            j |= ((long) (zzab & Ascii.DEL)) << i;
            if ((zzab & UnsignedBytes.MAX_POWER_OF_TWO) == 0) {
                return j;
            }
        }
        throw zzfie.zzc();
    }

    public final int zzu() {
        if (this.zzk == Integer.MAX_VALUE) {
            return -1;
        }
        return this.zzk - (this.zzj + this.zzh);
    }

    public final boolean zzv() throws IOException {
        return this.zzh == this.zzf && !zzi(1);
    }

    public final int zzw() {
        return this.zzj + this.zzh;
    }
}
