package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzg extends zzm<GoogleSignInResult> {
    final /* synthetic */ Context zza;
    final /* synthetic */ GoogleSignInOptions zzb;

    zzg(GoogleApiClient googleApiClient, Context context, GoogleSignInOptions googleSignInOptions) {
        this.zza = context;
        this.zzb = googleSignInOptions;
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result zza(Status status) {
        return new GoogleSignInResult(null, status);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(zzb zzb2) throws RemoteException {
        ((zzv) ((zze) zzb2).zzaf()).zza(new zzh(this), this.zzb);
    }
}
