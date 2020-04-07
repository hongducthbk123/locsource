package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.internal.zzawl;
import com.google.android.gms.internal.zzawn;
import com.google.android.gms.internal.zzawp;

final class zzj extends zzc {
    private /* synthetic */ zzawp zzb;

    zzj(AccountTransferClient accountTransferClient, zzawp zzawp) {
        this.zzb = zzawp;
        super(null);
    }

    /* access modifiers changed from: protected */
    public final void zza(zzawn zzawn) throws RemoteException {
        zzawn.zza((zzawl) this.zza, this.zzb);
    }
}
