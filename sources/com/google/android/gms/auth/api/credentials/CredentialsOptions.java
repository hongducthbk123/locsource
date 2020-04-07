package com.google.android.gms.auth.api.credentials;

import com.google.android.gms.auth.api.Auth.AuthCredentialsOptions;

public final class CredentialsOptions extends AuthCredentialsOptions {
    public static final CredentialsOptions DEFAULT = ((CredentialsOptions) new Builder().zza());

    public static final class Builder extends com.google.android.gms.auth.api.Auth.AuthCredentialsOptions.Builder {
        /* renamed from: build */
        public final CredentialsOptions zza() {
            return new CredentialsOptions(this);
        }

        public final Builder forceEnableSaveDialog() {
            this.zzb = Boolean.valueOf(true);
            return this;
        }
    }

    private CredentialsOptions(Builder builder) {
        super(builder);
    }
}
