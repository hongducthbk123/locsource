package com.google.android.gms.common.util;

import com.google.common.base.Ascii;

public final class zzm {
    private static final char[] zza = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final char[] zzb = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String zza(byte[] bArr) {
        int length = bArr.length;
        StringBuilder sb = new StringBuilder(length << 1);
        for (int i = 0; i < length; i++) {
            sb.append(zza[(bArr[i] & 240) >>> 4]);
            sb.append(zza[bArr[i] & Ascii.f977SI]);
        }
        return sb.toString();
    }

    public static String zzb(byte[] bArr) {
        char[] cArr = new char[(bArr.length << 1)];
        int i = 0;
        for (byte b : bArr) {
            byte b2 = b & 255;
            int i2 = i + 1;
            cArr[i] = zzb[b2 >>> 4];
            i = i2 + 1;
            cArr[i2] = zzb[b2 & Ascii.f977SI];
        }
        return new String(cArr);
    }
}
