package com.google.android.gms.internal;

import android.os.RemoteException;

public final class zzcci extends zzcce<Long> {
    public zzcci(int i, String str, Long l) {
        super(0, str, l);
    }

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public final Long zza(zzccm zzccm) {
        try {
            return Long.valueOf(zzccm.getLongFlagValue(zza(), ((Long) zzb()).longValue(), zzc()));
        } catch (RemoteException e) {
            return (Long) zzb();
        }
    }
}
