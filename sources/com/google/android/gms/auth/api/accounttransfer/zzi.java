package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.internal.zzawl;
import com.google.android.gms.internal.zzawn;
import com.google.android.gms.internal.zzawv;

final class zzi extends zzc {
    private /* synthetic */ zzawv zzb;

    zzi(AccountTransferClient accountTransferClient, zzawv zzawv) {
        this.zzb = zzawv;
        super(null);
    }

    /* access modifiers changed from: protected */
    public final void zza(zzawn zzawn) throws RemoteException {
        zzawn.zza((zzawl) this.zza, this.zzb);
    }
}
