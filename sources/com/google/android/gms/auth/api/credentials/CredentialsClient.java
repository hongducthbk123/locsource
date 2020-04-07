package com.google.android.gms.auth.api.credentials;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.Auth.AuthCredentialsOptions;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzda;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbj;
import com.google.android.gms.internal.zzaxh;
import com.google.android.gms.tasks.Task;

@Hide
public class CredentialsClient extends GoogleApi<AuthCredentialsOptions> {
    @Hide
    CredentialsClient(@NonNull Activity activity, @NonNull AuthCredentialsOptions authCredentialsOptions) {
        super(activity, Auth.CREDENTIALS_API, authCredentialsOptions, (zzda) new zzg());
    }

    @Hide
    CredentialsClient(@NonNull Context context, @NonNull AuthCredentialsOptions authCredentialsOptions) {
        super(context, Auth.CREDENTIALS_API, authCredentialsOptions, (zzda) new zzg());
    }

    public Task<Void> delete(@NonNull Credential credential) {
        return zzbj.zza(Auth.CredentialsApi.delete(zze(), credential));
    }

    public Task<Void> disableAutoSignIn() {
        return zzbj.zza(Auth.CredentialsApi.disableAutoSignIn(zze()));
    }

    public PendingIntent getHintPickerIntent(@NonNull HintRequest hintRequest) {
        return zzaxh.zza(zzg(), (AuthCredentialsOptions) zzb(), hintRequest);
    }

    public Task<CredentialRequestResponse> request(@NonNull CredentialRequest credentialRequest) {
        return zzbj.zza(Auth.CredentialsApi.request(zze(), credentialRequest), new CredentialRequestResponse());
    }

    public Task<Void> save(@NonNull Credential credential) {
        return zzbj.zza(Auth.CredentialsApi.save(zze(), credential));
    }
}
