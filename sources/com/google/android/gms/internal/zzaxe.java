package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzaxe extends zzaxg<Status> {
    zzaxe(zzawz zzawz, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result zza(Status status) {
        return status;
    }

    /* access modifiers changed from: protected */
    public final void zza(Context context, zzaxn zzaxn) throws RemoteException {
        zzaxn.zza(new zzaxf(this));
    }
}
