package com.google.android.gms.internal;

import com.google.common.base.Ascii;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class zzbfo {
    private static int zza(byte[] bArr, int i) {
        return (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << Ascii.DLE) | ((bArr[i + 3] & 255) << Ascii.CAN);
    }

    private static long zza(long j, long j2, long j3) {
        long j4 = (j ^ j2) * j3;
        long j5 = ((j4 ^ (j4 >>> 47)) ^ j2) * j3;
        return (j5 ^ (j5 >>> 47)) * j3;
    }

    public static long zza(byte[] bArr) {
        int length = bArr.length;
        if (length < 0 || length > bArr.length) {
            throw new IndexOutOfBoundsException("Out of bound index with offput: 0 and length: " + length);
        } else if (length <= 32) {
            if (length > 16) {
                long j = -7286425919675154353L + ((long) (length << 1));
                long zzb = -5435081209227447693L * zzb(bArr, 0);
                long zzb2 = zzb(bArr, 8);
                long zzb3 = zzb(bArr, (length + 0) - 8) * j;
                return zza((zzb(bArr, (length + 0) - 16) * -7286425919675154353L) + Long.rotateRight(zzb + zzb2, 43) + Long.rotateRight(zzb3, 30), zzb + Long.rotateRight(-7286425919675154353L + zzb2, 18) + zzb3, j);
            } else if (length >= 8) {
                long j2 = -7286425919675154353L + ((long) (length << 1));
                long zzb4 = -7286425919675154353L + zzb(bArr, 0);
                long zzb5 = zzb(bArr, (length + 0) - 8);
                return zza((Long.rotateRight(zzb5, 37) * j2) + zzb4, (Long.rotateRight(zzb4, 25) + zzb5) * j2, j2);
            } else if (length >= 4) {
                return zza(((((long) zza(bArr, 0)) & 4294967295L) << 3) + ((long) length), ((long) zza(bArr, (length + 0) - 4)) & 4294967295L, -7286425919675154353L + ((long) (length << 1)));
            } else if (length <= 0) {
                return -7286425919675154353L;
            } else {
                int i = (bArr[0] & 255) + ((bArr[(length >> 1) + 0] & 255) << 8);
                long j3 = (((long) (((bArr[(length - 1) + 0] & 255) << 2) + length)) * -4348849565147123417L) ^ (((long) i) * -7286425919675154353L);
                return (j3 ^ (j3 >>> 47)) * -7286425919675154353L;
            }
        } else if (length > 64) {
            return zza(bArr, 0, length);
        } else {
            long j4 = -7286425919675154353L + ((long) (length << 1));
            long zzb6 = zzb(bArr, 0) * -7286425919675154353L;
            long zzb7 = zzb(bArr, 8);
            long zzb8 = zzb(bArr, (length + 0) - 8) * j4;
            long zzb9 = (zzb(bArr, (length + 0) - 16) * -7286425919675154353L) + Long.rotateRight(zzb6 + zzb7, 43) + Long.rotateRight(zzb8, 30);
            long zza = zza(zzb9, Long.rotateRight(zzb7 - 7286425919675154353L, 18) + zzb6 + zzb8, j4);
            long zzb10 = zzb(bArr, 16) * j4;
            long zzb11 = zzb(bArr, 24);
            long zzb12 = (zzb9 + zzb(bArr, (length + 0) - 32)) * j4;
            return zza(((zzb(bArr, (length + 0) - 24) + zza) * j4) + Long.rotateRight(zzb10 + zzb11, 43) + Long.rotateRight(zzb12, 30), Long.rotateRight(zzb11 + zzb6, 18) + zzb10 + zzb12, j4);
        }
    }

    private static long zza(byte[] bArr, int i, int i2) {
        long[] jArr = new long[2];
        long[] jArr2 = new long[2];
        int i3 = (((i2 - 1) / 64) << 6) + 0;
        int i4 = (((i2 - 1) & 63) + i3) - 63;
        long zzb = 95310865018149119L + zzb(bArr, 0);
        long j = 2480279821605975764L;
        long j2 = 1390051526045402406L;
        int i5 = i;
        while (true) {
            long rotateRight = Long.rotateRight(zzb + j + jArr[0] + zzb(bArr, i5 + 8), 37) * -5435081209227447693L;
            long j3 = rotateRight ^ jArr2[1];
            long rotateRight2 = (Long.rotateRight(j + jArr[1] + zzb(bArr, i5 + 48), 42) * -5435081209227447693L) + jArr[0] + zzb(bArr, i5 + 40);
            long rotateRight3 = Long.rotateRight(j2 + jArr2[0], 33) * -5435081209227447693L;
            zza(bArr, i5, jArr[1] * -5435081209227447693L, jArr2[0] + j3, jArr);
            byte[] bArr2 = bArr;
            zza(bArr2, i5 + 32, rotateRight3 + jArr2[1], rotateRight2 + zzb(bArr, i5 + 16), jArr2);
            i5 += 64;
            if (i5 == i3) {
                long j4 = -5435081209227447693L + ((255 & j3) << 1);
                jArr2[0] = jArr2[0] + ((long) ((i2 - 1) & 63));
                jArr[0] = jArr[0] + jArr2[0];
                jArr2[0] = jArr2[0] + jArr[0];
                long rotateRight4 = Long.rotateRight(rotateRight3 + rotateRight2 + jArr[0] + zzb(bArr, i4 + 8), 37) * j4;
                long rotateRight5 = Long.rotateRight(jArr[1] + rotateRight2 + zzb(bArr, i4 + 48), 42) * j4;
                long j5 = rotateRight4 ^ (jArr2[1] * 9);
                long zzb2 = rotateRight5 + (jArr[0] * 9) + zzb(bArr, i4 + 40);
                long rotateRight6 = Long.rotateRight(jArr2[0] + j3, 33) * j4;
                zza(bArr, i4, jArr[1] * j4, j5 + jArr2[0], jArr);
                byte[] bArr3 = bArr;
                zza(bArr3, i4 + 32, rotateRight6 + jArr2[1], zzb2 + zzb(bArr, i4 + 16), jArr2);
                return zza(zza(jArr[0], jArr2[0], j4) + (((zzb2 >>> 47) ^ zzb2) * -4348849565147123417L) + j5, zza(jArr[1], jArr2[1], j4) + rotateRight6, j4);
            }
            j2 = j3;
            j = rotateRight2;
            zzb = rotateRight3;
        }
    }

    private static void zza(byte[] bArr, int i, long j, long j2, long[] jArr) {
        long zzb = zzb(bArr, i);
        long zzb2 = zzb(bArr, i + 8);
        long zzb3 = zzb(bArr, i + 16);
        long zzb4 = zzb(bArr, i + 24);
        long j3 = zzb + j;
        long j4 = zzb2 + j3 + zzb3;
        long rotateRight = Long.rotateRight(j4, 44) + Long.rotateRight(j2 + j3 + zzb4, 21);
        jArr[0] = j4 + zzb4;
        jArr[1] = j3 + rotateRight;
    }

    private static long zzb(byte[] bArr, int i) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr, i, 8);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        return wrap.getLong();
    }
}
