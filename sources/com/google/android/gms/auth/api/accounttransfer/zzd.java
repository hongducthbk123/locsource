package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.internal.zzawl;
import com.google.android.gms.internal.zzawn;
import com.google.android.gms.internal.zzawt;

final class zzd extends zzc {
    private /* synthetic */ zzawt zzb;

    zzd(AccountTransferClient accountTransferClient, zzawt zzawt) {
        this.zzb = zzawt;
        super(null);
    }

    /* access modifiers changed from: protected */
    public final void zza(zzawn zzawn) throws RemoteException {
        zzawn.zza((zzawl) this.zza, this.zzb);
    }
}
