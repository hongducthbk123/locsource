package com.google.android.gms.internal;

import android.os.RemoteException;

public final class zzcch extends zzcce<Integer> {
    public zzcch(int i, String str, Integer num) {
        super(0, str, num);
    }

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public final Integer zza(zzccm zzccm) {
        try {
            return Integer.valueOf(zzccm.getIntFlagValue(zza(), ((Integer) zzb()).intValue(), zzc()));
        } catch (RemoteException e) {
            return (Integer) zzb();
        }
    }
}
