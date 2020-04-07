package com.tencent.bugly.proguard;

import com.google.common.base.Ascii;

/* renamed from: com.tencent.bugly.proguard.e */
/* compiled from: BUGLY */
public final class C1988e {

    /* renamed from: a */
    private static final char[] f1571a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* renamed from: a */
    public static String m1958a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            cArr[(i * 2) + 1] = f1571a[b & Ascii.f977SI];
            cArr[i * 2] = f1571a[((byte) (b >>> 4)) & Ascii.f977SI];
        }
        return new String(cArr);
    }
}
