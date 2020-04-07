package com.google.android.gms.common;

import com.google.android.gms.common.internal.Hide;
import java.util.Arrays;

@Hide
final class zzi extends zzh {
    private final byte[] zza;

    zzi(byte[] bArr) {
        super(Arrays.copyOfRange(bArr, 0, 25));
        this.zza = bArr;
    }

    /* access modifiers changed from: 0000 */
    public final byte[] zza() {
        return this.zza;
    }
}
