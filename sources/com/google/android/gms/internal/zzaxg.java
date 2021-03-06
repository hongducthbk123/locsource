package com.google.android.gms.internal;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.common.internal.Hide;

abstract class zzaxg<R extends Result> extends zzm<R, zzaxi> {
    zzaxg(GoogleApiClient googleApiClient) {
        super(Auth.CREDENTIALS_API, googleApiClient);
    }

    /* access modifiers changed from: protected */
    public abstract void zza(Context context, zzaxn zzaxn) throws DeadObjectException, RemoteException;

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(zzb zzb) throws RemoteException {
        zzaxi zzaxi = (zzaxi) zzb;
        zza(zzaxi.zzaa(), (zzaxn) zzaxi.zzaf());
    }

    @Hide
    public final /* bridge */ /* synthetic */ void zza(Object obj) {
        super.zza((Result) obj);
    }
}
