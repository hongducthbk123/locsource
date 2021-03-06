package com.google.android.gms.auth.api.signin.internal;

import android.content.Intent;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

public final class zzd implements GoogleSignInApi {
    private static GoogleSignInOptions zza(GoogleApiClient googleApiClient) {
        return ((zze) googleApiClient.zza((zzc<C>) Auth.zzb)).mo11103m_();
    }

    public final Intent getSignInIntent(GoogleApiClient googleApiClient) {
        return zzf.zza(googleApiClient.zzb(), zza(googleApiClient));
    }

    public final GoogleSignInResult getSignInResultFromIntent(Intent intent) {
        return zzf.zza(intent);
    }

    public final PendingResult<Status> revokeAccess(GoogleApiClient googleApiClient) {
        return zzf.zzb(googleApiClient, googleApiClient.zzb(), false);
    }

    public final PendingResult<Status> signOut(GoogleApiClient googleApiClient) {
        return zzf.zza(googleApiClient, googleApiClient.zzb(), false);
    }

    public final OptionalPendingResult<GoogleSignInResult> silentSignIn(GoogleApiClient googleApiClient) {
        return zzf.zza(googleApiClient, googleApiClient.zzb(), zza(googleApiClient), false);
    }
}
