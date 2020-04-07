package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.auth.account.zzc;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.common.internal.Hide;

final class zzavr extends zzm<Result, zzawa> {
    private /* synthetic */ boolean zza;

    zzavr(zzavq zzavq, Api api, GoogleApiClient googleApiClient, boolean z) {
        this.zza = z;
        super(api, googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final Result zza(Status status) {
        return new zzavy(status);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(zzb zzb) throws RemoteException {
        ((zzc) ((zzawa) zzb).zzaf()).zza(this.zza);
        zza(new zzavy(Status.zza));
    }

    @Hide
    public final /* bridge */ /* synthetic */ void zza(Object obj) {
        super.zza((Result) obj);
    }
}
