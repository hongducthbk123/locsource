package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzaxc extends zzaxg<Status> {
    private /* synthetic */ Credential zza;

    zzaxc(zzawz zzawz, GoogleApiClient googleApiClient, Credential credential) {
        this.zza = credential;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result zza(Status status) {
        return status;
    }

    /* access modifiers changed from: protected */
    public final void zza(Context context, zzaxn zzaxn) throws RemoteException {
        zzaxn.zza((zzaxl) new zzaxf(this), new zzaxp(this.zza));
    }
}
