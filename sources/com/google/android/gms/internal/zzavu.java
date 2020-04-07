package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.RemoteException;
import com.google.android.gms.auth.account.zza;
import com.google.android.gms.auth.account.zzc;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.common.internal.Hide;

final class zzavu extends zzm<Result, zzawa> {
    private /* synthetic */ Account zza;

    zzavu(zzavq zzavq, Api api, GoogleApiClient googleApiClient, Account account) {
        this.zza = account;
        super(api, googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final Result zza(Status status) {
        return new zzavz(status);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(zzb zzb) throws RemoteException {
        ((zzc) ((zzawa) zzb).zzaf()).zza((zza) new zzavv(this), this.zza);
    }

    @Hide
    public final /* bridge */ /* synthetic */ void zza(Object obj) {
        super.zza((Result) obj);
    }
}
