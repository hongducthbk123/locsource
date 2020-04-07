package com.google.android.gms.internal;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

public final class zzflk {
    private final ByteBuffer zza;

    private zzflk(ByteBuffer byteBuffer) {
        this.zza = byteBuffer;
        this.zza.order(ByteOrder.LITTLE_ENDIAN);
    }

    private zzflk(byte[] bArr, int i, int i2) {
        this(ByteBuffer.wrap(bArr, i, i2));
    }

    public static int zza(int i) {
        if (i >= 0) {
            return zzd(i);
        }
        return 10;
    }

    public static int zza(long j) {
        if ((-128 & j) == 0) {
            return 1;
        }
        if ((-16384 & j) == 0) {
            return 2;
        }
        if ((-2097152 & j) == 0) {
            return 3;
        }
        if ((-268435456 & j) == 0) {
            return 4;
        }
        if ((-34359738368L & j) == 0) {
            return 5;
        }
        if ((-4398046511104L & j) == 0) {
            return 6;
        }
        if ((-562949953421312L & j) == 0) {
            return 7;
        }
        if ((-72057594037927936L & j) == 0) {
            return 8;
        }
        return (Long.MIN_VALUE & j) == 0 ? 9 : 10;
    }

    private static int zza(CharSequence charSequence) {
        int i;
        int i2 = 0;
        int length = charSequence.length();
        int i3 = 0;
        while (i3 < length && charSequence.charAt(i3) < 128) {
            i3++;
        }
        int i4 = length;
        while (true) {
            if (i3 >= length) {
                i = i4;
                break;
            }
            char charAt = charSequence.charAt(i3);
            if (charAt < 2048) {
                i4 += (127 - charAt) >>> 31;
                i3++;
            } else {
                int length2 = charSequence.length();
                while (i3 < length2) {
                    char charAt2 = charSequence.charAt(i3);
                    if (charAt2 < 2048) {
                        i2 += (127 - charAt2) >>> 31;
                    } else {
                        i2 += 2;
                        if (55296 <= charAt2 && charAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i3) < 65536) {
                                throw new IllegalArgumentException("Unpaired surrogate at index " + i3);
                            }
                            i3++;
                        }
                    }
                    i3++;
                }
                i = i4 + i2;
            }
        }
        if (i >= length) {
            return i;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) i) + 4294967296L));
    }

    private static int zza(CharSequence charSequence, byte[] bArr, int i, int i2) {
        int i3;
        int length = charSequence.length();
        int i4 = 0;
        int i5 = i + i2;
        while (i4 < length && i4 + i < i5) {
            char charAt = charSequence.charAt(i4);
            if (charAt >= 128) {
                break;
            }
            bArr[i + i4] = (byte) charAt;
            i4++;
        }
        if (i4 == length) {
            return i + length;
        }
        int i6 = i + i4;
        while (i4 < length) {
            char charAt2 = charSequence.charAt(i4);
            if (charAt2 < 128 && i6 < i5) {
                i3 = i6 + 1;
                bArr[i6] = (byte) charAt2;
            } else if (charAt2 < 2048 && i6 <= i5 - 2) {
                int i7 = i6 + 1;
                bArr[i6] = (byte) ((charAt2 >>> 6) | 960);
                i3 = i7 + 1;
                bArr[i7] = (byte) ((charAt2 & '?') | 128);
            } else if ((charAt2 < 55296 || 57343 < charAt2) && i6 <= i5 - 3) {
                int i8 = i6 + 1;
                bArr[i6] = (byte) ((charAt2 >>> 12) | 480);
                int i9 = i8 + 1;
                bArr[i8] = (byte) (((charAt2 >>> 6) & 63) | 128);
                i3 = i9 + 1;
                bArr[i9] = (byte) ((charAt2 & '?') | 128);
            } else if (i6 <= i5 - 4) {
                if (i4 + 1 != charSequence.length()) {
                    i4++;
                    char charAt3 = charSequence.charAt(i4);
                    if (Character.isSurrogatePair(charAt2, charAt3)) {
                        int codePoint = Character.toCodePoint(charAt2, charAt3);
                        int i10 = i6 + 1;
                        bArr[i6] = (byte) ((codePoint >>> 18) | 240);
                        int i11 = i10 + 1;
                        bArr[i10] = (byte) (((codePoint >>> 12) & 63) | 128);
                        int i12 = i11 + 1;
                        bArr[i11] = (byte) (((codePoint >>> 6) & 63) | 128);
                        i3 = i12 + 1;
                        bArr[i12] = (byte) ((codePoint & 63) | 128);
                    }
                }
                throw new IllegalArgumentException("Unpaired surrogate at index " + (i4 - 1));
            } else {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + i6);
            }
            i4++;
            i6 = i3;
        }
        return i6;
    }

    public static int zza(String str) {
        int zza2 = zza((CharSequence) str);
        return zza2 + zzd(zza2);
    }

    public static zzflk zza(byte[] bArr) {
        return zza(bArr, 0, bArr.length);
    }

    public static zzflk zza(byte[] bArr, int i, int i2) {
        return new zzflk(bArr, 0, i2);
    }

    private static void zza(CharSequence charSequence, ByteBuffer byteBuffer) {
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        } else if (byteBuffer.hasArray()) {
            try {
                byteBuffer.position(zza(charSequence, byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining()) - byteBuffer.arrayOffset());
            } catch (ArrayIndexOutOfBoundsException e) {
                BufferOverflowException bufferOverflowException = new BufferOverflowException();
                bufferOverflowException.initCause(e);
                throw bufferOverflowException;
            }
        } else {
            zzb(charSequence, byteBuffer);
        }
    }

    public static int zzb(int i) {
        return zzd(i << 3);
    }

    public static int zzb(int i, int i2) {
        return zzb(i) + zza(i2);
    }

    public static int zzb(int i, zzfls zzfls) {
        int zzb = zzb(i);
        int zzf = zzfls.zzf();
        return zzb + zzf + zzd(zzf);
    }

    public static int zzb(int i, String str) {
        return zzb(i) + zza(str);
    }

    public static int zzb(int i, byte[] bArr) {
        return zzb(i) + zzb(bArr);
    }

    public static int zzb(byte[] bArr) {
        return zzd(bArr.length) + bArr.length;
    }

    private final void zzb(long j) throws IOException {
        while ((-128 & j) != 0) {
            zzf((((int) j) & 127) | 128);
            j >>>= 7;
        }
        zzf((int) j);
    }

    private static void zzb(CharSequence charSequence, ByteBuffer byteBuffer) {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt < 128) {
                byteBuffer.put((byte) charAt);
            } else if (charAt < 2048) {
                byteBuffer.put((byte) ((charAt >>> 6) | 960));
                byteBuffer.put((byte) ((charAt & '?') | 128));
            } else if (charAt < 55296 || 57343 < charAt) {
                byteBuffer.put((byte) ((charAt >>> 12) | 480));
                byteBuffer.put((byte) (((charAt >>> 6) & 63) | 128));
                byteBuffer.put((byte) ((charAt & '?') | 128));
            } else {
                if (i + 1 != charSequence.length()) {
                    i++;
                    char charAt2 = charSequence.charAt(i);
                    if (Character.isSurrogatePair(charAt, charAt2)) {
                        int codePoint = Character.toCodePoint(charAt, charAt2);
                        byteBuffer.put((byte) ((codePoint >>> 18) | 240));
                        byteBuffer.put((byte) (((codePoint >>> 12) & 63) | 128));
                        byteBuffer.put((byte) (((codePoint >>> 6) & 63) | 128));
                        byteBuffer.put((byte) ((codePoint & 63) | 128));
                    }
                }
                throw new IllegalArgumentException("Unpaired surrogate at index " + (i - 1));
            }
            i++;
        }
    }

    private final void zzc(long j) throws IOException {
        if (this.zza.remaining() < 8) {
            throw new zzfll(this.zza.position(), this.zza.limit());
        }
        this.zza.putLong(j);
    }

    public static int zzd(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        return (-268435456 & i) == 0 ? 4 : 5;
    }

    private static long zzd(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public static int zze(int i) {
        return (i << 1) ^ (i >> 31);
    }

    public static int zze(int i, long j) {
        return zzb(i) + zza(j);
    }

    public static int zzf(int i, long j) {
        return zzb(i) + zza(zzd(j));
    }

    private final void zzf(int i) throws IOException {
        byte b = (byte) i;
        if (!this.zza.hasRemaining()) {
            throw new zzfll(this.zza.position(), this.zza.limit());
        }
        this.zza.put(b);
    }

    public final void zza() {
        if (this.zza.remaining() != 0) {
            throw new IllegalStateException(String.format("Did not write as much data as expected, %s bytes remaining.", new Object[]{Integer.valueOf(this.zza.remaining())}));
        }
    }

    public final void zza(int i, double d) throws IOException {
        zzc(i, 1);
        zzc(Double.doubleToLongBits(d));
    }

    public final void zza(int i, float f) throws IOException {
        zzc(i, 5);
        int floatToIntBits = Float.floatToIntBits(f);
        if (this.zza.remaining() < 4) {
            throw new zzfll(this.zza.position(), this.zza.limit());
        }
        this.zza.putInt(floatToIntBits);
    }

    public final void zza(int i, int i2) throws IOException {
        zzc(i, 0);
        if (i2 >= 0) {
            zzc(i2);
        } else {
            zzb((long) i2);
        }
    }

    public final void zza(int i, long j) throws IOException {
        zzc(i, 0);
        zzb(j);
    }

    public final void zza(int i, zzfls zzfls) throws IOException {
        zzc(i, 2);
        zza(zzfls);
    }

    public final void zza(int i, String str) throws IOException {
        zzc(i, 2);
        try {
            int zzd = zzd(str.length());
            if (zzd == zzd(str.length() * 3)) {
                int position = this.zza.position();
                if (this.zza.remaining() < zzd) {
                    throw new zzfll(zzd + position, this.zza.limit());
                }
                this.zza.position(position + zzd);
                zza((CharSequence) str, this.zza);
                int position2 = this.zza.position();
                this.zza.position(position);
                zzc((position2 - position) - zzd);
                this.zza.position(position2);
                return;
            }
            zzc(zza((CharSequence) str));
            zza((CharSequence) str, this.zza);
        } catch (BufferOverflowException e) {
            zzfll zzfll = new zzfll(this.zza.position(), this.zza.limit());
            zzfll.initCause(e);
            throw zzfll;
        }
    }

    public final void zza(int i, boolean z) throws IOException {
        int i2 = 0;
        zzc(i, 0);
        if (z) {
            i2 = 1;
        }
        byte b = (byte) i2;
        if (!this.zza.hasRemaining()) {
            throw new zzfll(this.zza.position(), this.zza.limit());
        }
        this.zza.put(b);
    }

    public final void zza(int i, byte[] bArr) throws IOException {
        zzc(i, 2);
        zzc(bArr.length);
        zzc(bArr);
    }

    public final void zza(zzfls zzfls) throws IOException {
        zzc(zzfls.zze());
        zzfls.zza(this);
    }

    public final void zzb(int i, long j) throws IOException {
        zzc(i, 0);
        zzb(j);
    }

    public final void zzc(int i) throws IOException {
        while ((i & -128) != 0) {
            zzf((i & 127) | 128);
            i >>>= 7;
        }
        zzf(i);
    }

    public final void zzc(int i, int i2) throws IOException {
        zzc((i << 3) | i2);
    }

    public final void zzc(int i, long j) throws IOException {
        zzc(i, 1);
        zzc(j);
    }

    public final void zzc(byte[] bArr) throws IOException {
        int length = bArr.length;
        if (this.zza.remaining() >= length) {
            this.zza.put(bArr, 0, length);
            return;
        }
        throw new zzfll(this.zza.position(), this.zza.limit());
    }

    public final void zzd(int i, long j) throws IOException {
        zzc(i, 0);
        zzb(zzd(j));
    }
}
