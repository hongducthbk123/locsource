package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.internal.zzawl;
import com.google.android.gms.internal.zzawn;
import com.google.android.gms.internal.zzawr;

final class zze extends zzb<byte[]> {
    private /* synthetic */ zzawr zza;

    zze(AccountTransferClient accountTransferClient, zzawr zzawr) {
        this.zza = zzawr;
        super(null);
    }

    /* access modifiers changed from: protected */
    public final void zza(zzawn zzawn) throws RemoteException {
        zzawn.zza((zzawl) new zzf(this, this), this.zza);
    }
}
