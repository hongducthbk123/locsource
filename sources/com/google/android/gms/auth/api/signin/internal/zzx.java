package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.os.Binder;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;

public final class zzx extends zzs {
    private final Context zza;

    public zzx(Context context) {
        this.zza = context;
    }

    private final void zzc() {
        if (!GooglePlayServicesUtil.zzb(this.zza, Binder.getCallingUid())) {
            throw new SecurityException("Calling UID " + Binder.getCallingUid() + " is not Google Play services.");
        }
    }

    public final void zza() {
        zzc();
        zzaa zza2 = zzaa.zza(this.zza);
        GoogleSignInAccount zza3 = zza2.zza();
        GoogleSignInOptions googleSignInOptions = GoogleSignInOptions.DEFAULT_SIGN_IN;
        if (zza3 != null) {
            googleSignInOptions = zza2.zzb();
        }
        GoogleApiClient build = new Builder(this.zza).addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions).build();
        try {
            if (build.blockingConnect().isSuccess()) {
                if (zza3 != null) {
                    Auth.GoogleSignInApi.revokeAccess(build);
                } else {
                    build.clearDefaultAccountAndReconnect();
                }
            }
        } finally {
            build.disconnect();
        }
    }

    public final void zzb() {
        zzc();
        zzp.zza(this.zza).zza();
    }
}
