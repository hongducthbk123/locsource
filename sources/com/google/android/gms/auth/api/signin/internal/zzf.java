package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzbm;
import com.google.android.gms.internal.zzbhf;

public final class zzf {
    private static zzbhf zza = new zzbhf("GoogleSignInCommon", new String[0]);

    public static Intent zza(Context context, GoogleSignInOptions googleSignInOptions) {
        zza.zzb("getSignInIntent()", new Object[0]);
        SignInConfiguration signInConfiguration = new SignInConfiguration(context.getPackageName(), googleSignInOptions);
        Intent intent = new Intent("com.google.android.gms.auth.GOOGLE_SIGN_IN");
        intent.setPackage(context.getPackageName());
        intent.setClass(context, SignInHubActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("config", signInConfiguration);
        intent.putExtra("config", bundle);
        return intent;
    }

    @Nullable
    public static GoogleSignInResult zza(Intent intent) {
        if (intent == null || (!intent.hasExtra("googleSignInStatus") && !intent.hasExtra("googleSignInAccount"))) {
            return null;
        }
        GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) intent.getParcelableExtra("googleSignInAccount");
        Status status = (Status) intent.getParcelableExtra("googleSignInStatus");
        if (googleSignInAccount != null) {
            status = Status.zza;
        }
        return new GoogleSignInResult(googleSignInAccount, status);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0098  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.common.api.OptionalPendingResult<com.google.android.gms.auth.api.signin.GoogleSignInResult> zza(com.google.android.gms.common.api.GoogleApiClient r5, android.content.Context r6, com.google.android.gms.auth.api.signin.GoogleSignInOptions r7, boolean r8) {
        /*
            r1 = 0
            r2 = 0
            com.google.android.gms.internal.zzbhf r0 = zza
            java.lang.String r3 = "silentSignIn()"
            java.lang.Object[] r4 = new java.lang.Object[r2]
            r0.zzb(r3, r4)
            com.google.android.gms.internal.zzbhf r0 = zza
            java.lang.String r3 = "getEligibleSavedSignInResult()"
            java.lang.Object[] r4 = new java.lang.Object[r2]
            r0.zzb(r3, r4)
            com.google.android.gms.common.internal.zzbq.zza(r7)
            com.google.android.gms.auth.api.signin.internal.zzp r0 = com.google.android.gms.auth.api.signin.internal.zzp.zza(r6)
            com.google.android.gms.auth.api.signin.GoogleSignInOptions r3 = r0.zzc()
            if (r3 == 0) goto L_0x0096
            android.accounts.Account r0 = r3.zzb()
            android.accounts.Account r4 = r7.zzb()
            if (r0 != 0) goto L_0x0091
            if (r4 != 0) goto L_0x008f
            r0 = 1
        L_0x002e:
            if (r0 == 0) goto L_0x0096
            boolean r0 = r7.zzd()
            if (r0 != 0) goto L_0x0096
            boolean r0 = r7.zzc()
            if (r0 == 0) goto L_0x0050
            boolean r0 = r3.zzc()
            if (r0 == 0) goto L_0x0096
            java.lang.String r0 = r7.zze()
            java.lang.String r4 = r3.zze()
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x0096
        L_0x0050:
            java.util.HashSet r0 = new java.util.HashSet
            java.util.ArrayList r3 = r3.zza()
            r0.<init>(r3)
            java.util.HashSet r3 = new java.util.HashSet
            java.util.ArrayList r4 = r7.zza()
            r3.<init>(r4)
            boolean r0 = r0.containsAll(r3)
            if (r0 == 0) goto L_0x0096
            com.google.android.gms.auth.api.signin.internal.zzp r0 = com.google.android.gms.auth.api.signin.internal.zzp.zza(r6)
            com.google.android.gms.auth.api.signin.GoogleSignInAccount r3 = r0.zzb()
            if (r3 == 0) goto L_0x0096
            boolean r0 = r3.zzb()
            if (r0 != 0) goto L_0x0096
            com.google.android.gms.auth.api.signin.GoogleSignInResult r0 = new com.google.android.gms.auth.api.signin.GoogleSignInResult
            com.google.android.gms.common.api.Status r4 = com.google.android.gms.common.api.Status.zza
            r0.<init>(r3, r4)
        L_0x007f:
            if (r0 == 0) goto L_0x0098
            com.google.android.gms.internal.zzbhf r1 = zza
            java.lang.String r3 = "Eligible saved sign in result found"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r1.zzb(r3, r2)
            com.google.android.gms.common.api.OptionalPendingResult r0 = com.google.android.gms.common.api.PendingResults.zzb(r0, r5)
        L_0x008e:
            return r0
        L_0x008f:
            r0 = r2
            goto L_0x002e
        L_0x0091:
            boolean r0 = r0.equals(r4)
            goto L_0x002e
        L_0x0096:
            r0 = r1
            goto L_0x007f
        L_0x0098:
            if (r8 == 0) goto L_0x00aa
            com.google.android.gms.auth.api.signin.GoogleSignInResult r0 = new com.google.android.gms.auth.api.signin.GoogleSignInResult
            com.google.android.gms.common.api.Status r2 = new com.google.android.gms.common.api.Status
            r3 = 4
            r2.<init>(r3)
            r0.<init>(r1, r2)
            com.google.android.gms.common.api.OptionalPendingResult r0 = com.google.android.gms.common.api.PendingResults.zzb(r0, r5)
            goto L_0x008e
        L_0x00aa:
            com.google.android.gms.internal.zzbhf r0 = zza
            java.lang.String r1 = "trySilentSignIn()"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r0.zzb(r1, r2)
            com.google.android.gms.auth.api.signin.internal.zzg r0 = new com.google.android.gms.auth.api.signin.internal.zzg
            r0.<init>(r5, r6, r7)
            com.google.android.gms.common.api.internal.zzm r1 = r5.zza(r0)
            com.google.android.gms.common.api.internal.zzco r0 = new com.google.android.gms.common.api.internal.zzco
            r0.<init>(r1)
            goto L_0x008e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.signin.internal.zzf.zza(com.google.android.gms.common.api.GoogleApiClient, android.content.Context, com.google.android.gms.auth.api.signin.GoogleSignInOptions, boolean):com.google.android.gms.common.api.OptionalPendingResult");
    }

    public static PendingResult<Status> zza(GoogleApiClient googleApiClient, Context context, boolean z) {
        zza.zzb("Signing out", new Object[0]);
        zza(context);
        return z ? PendingResults.zza(Status.zza, googleApiClient) : googleApiClient.zzb(new zzi(googleApiClient));
    }

    private static void zza(Context context) {
        zzp.zza(context).zza();
        for (GoogleApiClient zzd : GoogleApiClient.zza()) {
            zzd.zzd();
        }
        zzbm.zzb();
    }

    public static Intent zzb(Context context, GoogleSignInOptions googleSignInOptions) {
        zza.zzb("getFallbackSignInIntent()", new Object[0]);
        Intent zza2 = zza(context, googleSignInOptions);
        zza2.setAction("com.google.android.gms.auth.APPAUTH_SIGN_IN");
        return zza2;
    }

    public static PendingResult<Status> zzb(GoogleApiClient googleApiClient, Context context, boolean z) {
        zza.zzb("Revoking access", new Object[0]);
        String zza2 = zzaa.zza(context).zza("refreshToken");
        zza(context);
        return z ? zzb.zza(zza2) : googleApiClient.zzb(new zzk(googleApiClient));
    }

    public static Intent zzc(Context context, GoogleSignInOptions googleSignInOptions) {
        zza.zzb("getNoImplementationSignInIntent()", new Object[0]);
        Intent zza2 = zza(context, googleSignInOptions);
        zza2.setAction("com.google.android.gms.auth.NO_IMPL");
        return zza2;
    }
}
