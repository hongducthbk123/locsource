package com.google.android.gms.auth.api.signin.internal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.p000v4.app.FragmentActivity;
import android.support.p000v4.app.LoaderManager.LoaderCallbacks;
import android.support.p000v4.content.Loader;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

@KeepName
public class SignInHubActivity extends FragmentActivity {
    private static boolean zza = false;
    private boolean zzb = false;
    @VisibleForTesting
    private SignInConfiguration zzc;
    private boolean zzd;
    /* access modifiers changed from: private */
    public int zze;
    /* access modifiers changed from: private */
    public Intent zzf;

    class zza implements LoaderCallbacks<Void> {
        private zza() {
        }

        public final Loader<Void> onCreateLoader(int i, Bundle bundle) {
            return new zzc(SignInHubActivity.this, GoogleApiClient.zza());
        }

        public final /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
            SignInHubActivity.this.setResult(SignInHubActivity.this.zze, SignInHubActivity.this.zzf);
            SignInHubActivity.this.finish();
        }

        public final void onLoaderReset(Loader<Void> loader) {
        }
    }

    private final void zza() {
        getSupportLoaderManager().initLoader(0, null, new zza());
        zza = false;
    }

    private final void zza(int i) {
        Status status = new Status(i);
        Intent intent = new Intent();
        intent.putExtra("googleSignInStatus", status);
        setResult(0, intent);
        finish();
        zza = false;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (!this.zzb) {
            setResult(0);
            switch (i) {
                case 40962:
                    if (intent != null) {
                        SignInAccount signInAccount = (SignInAccount) intent.getParcelableExtra(GoogleSignInApi.EXTRA_SIGN_IN_ACCOUNT);
                        if (signInAccount != null && signInAccount.zza() != null) {
                            GoogleSignInAccount zza2 = signInAccount.zza();
                            zzp.zza(this).zza(this.zzc.zza(), zza2);
                            intent.removeExtra(GoogleSignInApi.EXTRA_SIGN_IN_ACCOUNT);
                            intent.putExtra("googleSignInAccount", zza2);
                            this.zzd = true;
                            this.zze = i2;
                            this.zzf = intent;
                            zza();
                            return;
                        } else if (intent.hasExtra("errorCode")) {
                            zza(intent.getIntExtra("errorCode", 8));
                            return;
                        }
                    }
                    zza(8);
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String action = intent.getAction();
        if ("com.google.android.gms.auth.NO_IMPL".equals(action)) {
            zza((int) GoogleSignInStatusCodes.SIGN_IN_FAILED);
        } else if (zza) {
            setResult(0);
            zza((int) GoogleSignInStatusCodes.SIGN_IN_CURRENTLY_IN_PROGRESS);
        } else {
            zza = true;
            if (action.equals("com.google.android.gms.auth.GOOGLE_SIGN_IN") || action.equals("com.google.android.gms.auth.APPAUTH_SIGN_IN")) {
                this.zzc = (SignInConfiguration) intent.getBundleExtra("config").getParcelable("config");
                if (this.zzc == null) {
                    Log.e("AuthSignInClient", "Activity started with invalid configuration.");
                    setResult(0);
                    finish();
                } else if (bundle == null) {
                    Intent intent2 = new Intent(action);
                    if (action.equals("com.google.android.gms.auth.GOOGLE_SIGN_IN")) {
                        intent2.setPackage("com.google.android.gms");
                    } else {
                        intent2.setPackage(getPackageName());
                    }
                    intent2.putExtra("config", this.zzc);
                    try {
                        startActivityForResult(intent2, 40962);
                    } catch (ActivityNotFoundException e) {
                        this.zzb = true;
                        Log.w("AuthSignInClient", "Could not launch sign in Intent. Google Play Service is probably being updated...");
                        zza(17);
                    }
                } else {
                    this.zzd = bundle.getBoolean("signingInGoogleApiClients");
                    if (this.zzd) {
                        this.zze = bundle.getInt("signInResultCode");
                        this.zzf = (Intent) bundle.getParcelable("signInResultData");
                        zza();
                    }
                }
            } else {
                String str = "AuthSignInClient";
                String str2 = "Unknown action: ";
                String valueOf = String.valueOf(intent.getAction());
                Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                finish();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("signingInGoogleApiClients", this.zzd);
        if (this.zzd) {
            bundle.putInt("signInResultCode", this.zze);
            bundle.putParcelable("signInResultData", this.zzf);
        }
    }
}
