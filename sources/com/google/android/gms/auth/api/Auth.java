package com.google.android.gms.auth.api;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.auth.api.credentials.PasswordSpecification;
import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.internal.zzd;
import com.google.android.gms.auth.api.signin.internal.zze;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzawb;
import com.google.android.gms.internal.zzawc;
import com.google.android.gms.internal.zzawd;
import com.google.android.gms.internal.zzawz;
import com.google.android.gms.internal.zzaxi;
import com.google.android.gms.internal.zzayh;

public final class Auth {
    public static final Api<AuthCredentialsOptions> CREDENTIALS_API = new Api<>("Auth.CREDENTIALS_API", zzd, zza);
    public static final CredentialsApi CredentialsApi = new zzawz();
    public static final Api<GoogleSignInOptions> GOOGLE_SIGN_IN_API = new Api<>("Auth.GOOGLE_SIGN_IN_API", zzf, zzb);
    public static final GoogleSignInApi GoogleSignInApi = new zzd();
    @Hide
    @KeepForSdk
    public static final Api<zzf> PROXY_API = zzd.zza;
    @Hide
    @KeepForSdk
    public static final ProxyApi ProxyApi = new zzayh();
    @Hide
    public static final zzf<zzaxi> zza = new zzf<>();
    @Hide
    public static final zzf<zze> zzb = new zzf<>();
    @Hide
    private static zzf<zzawd> zzc = new zzf<>();
    private static final zza<zzaxi, AuthCredentialsOptions> zzd = new zza();
    private static final zza<zzawd, NoOptions> zze = new zzb();
    private static final zza<zze, GoogleSignInOptions> zzf = new zzc();
    @Hide
    private static Api<NoOptions> zzg = new Api<>("Auth.ACCOUNT_STATUS_API", zze, zzc);
    @Hide
    private static zzawb zzh = new zzawc();

    @Deprecated
    public static class AuthCredentialsOptions implements Optional {
        @Hide
        private static AuthCredentialsOptions zza = new Builder().zza();
        private final String zzb = null;
        private final PasswordSpecification zzc;
        private final boolean zzd;

        @Deprecated
        public static class Builder {
            @Hide
            @NonNull
            protected PasswordSpecification zza = PasswordSpecification.zza;
            @Hide
            protected Boolean zzb = Boolean.valueOf(false);

            public Builder forceEnableSaveDialog() {
                this.zzb = Boolean.valueOf(true);
                return this;
            }

            @Hide
            public AuthCredentialsOptions zza() {
                return new AuthCredentialsOptions(this);
            }
        }

        @Hide
        public AuthCredentialsOptions(Builder builder) {
            this.zzc = builder.zza;
            this.zzd = builder.zzb.booleanValue();
        }

        @Hide
        public final PasswordSpecification zza() {
            return this.zzc;
        }

        @Hide
        public final Bundle zzb() {
            Bundle bundle = new Bundle();
            bundle.putString("consumer_package", null);
            bundle.putParcelable("password_specification", this.zzc);
            bundle.putBoolean("force_save_dialog", this.zzd);
            return bundle;
        }
    }

    @Hide
    private Auth() {
    }
}
