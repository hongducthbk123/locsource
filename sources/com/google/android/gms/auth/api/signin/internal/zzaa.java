package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.zzbq;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;

public final class zzaa {
    private static final Lock zza = new ReentrantLock();
    private static zzaa zzb;
    private final Lock zzc = new ReentrantLock();
    private final SharedPreferences zzd;

    private zzaa(Context context) {
        this.zzd = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }

    public static zzaa zza(Context context) {
        zzbq.zza(context);
        zza.lock();
        try {
            if (zzb == null) {
                zzb = new zzaa(context.getApplicationContext());
            }
            return zzb;
        } finally {
            zza.unlock();
        }
    }

    private final GoogleSignInAccount zzb(String str) {
        GoogleSignInAccount googleSignInAccount = null;
        if (TextUtils.isEmpty(str)) {
            return googleSignInAccount;
        }
        String zza2 = zza(zzb("googleSignInAccount", str));
        if (zza2 == null) {
            return googleSignInAccount;
        }
        try {
            return GoogleSignInAccount.zza(zza2);
        } catch (JSONException e) {
            return googleSignInAccount;
        }
    }

    private static String zzb(String str, String str2) {
        return new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(str2).length()).append(str).append(":").append(str2).toString();
    }

    private final GoogleSignInOptions zzc(String str) {
        GoogleSignInOptions googleSignInOptions = null;
        if (TextUtils.isEmpty(str)) {
            return googleSignInOptions;
        }
        String zza2 = zza(zzb("googleSignInOptions", str));
        if (zza2 == null) {
            return googleSignInOptions;
        }
        try {
            return GoogleSignInOptions.zza(zza2);
        } catch (JSONException e) {
            return googleSignInOptions;
        }
    }

    private final void zzd(String str) {
        this.zzc.lock();
        try {
            this.zzd.edit().remove(str).apply();
        } finally {
            this.zzc.unlock();
        }
    }

    public final GoogleSignInAccount zza() {
        return zzb(zza("defaultGoogleSignInAccount"));
    }

    /* access modifiers changed from: protected */
    public final String zza(String str) {
        this.zzc.lock();
        try {
            return this.zzd.getString(str, null);
        } finally {
            this.zzc.unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zza(GoogleSignInAccount googleSignInAccount, GoogleSignInOptions googleSignInOptions) {
        zzbq.zza(googleSignInAccount);
        zzbq.zza(googleSignInOptions);
        String zzc2 = googleSignInAccount.zzc();
        zza(zzb("googleSignInAccount", zzc2), googleSignInAccount.zze());
        zza(zzb("googleSignInOptions", zzc2), googleSignInOptions.zzf());
    }

    /* access modifiers changed from: protected */
    public final void zza(String str, String str2) {
        this.zzc.lock();
        try {
            this.zzd.edit().putString(str, str2).apply();
        } finally {
            this.zzc.unlock();
        }
    }

    public final GoogleSignInOptions zzb() {
        return zzc(zza("defaultGoogleSignInAccount"));
    }

    public final void zzc() {
        String zza2 = zza("defaultGoogleSignInAccount");
        zzd("defaultGoogleSignInAccount");
        if (!TextUtils.isEmpty(zza2)) {
            zzd(zzb("googleSignInAccount", zza2));
            zzd(zzb("googleSignInOptions", zza2));
        }
    }

    public final void zzd() {
        this.zzc.lock();
        try {
            this.zzd.edit().clear().apply();
        } finally {
            this.zzc.unlock();
        }
    }
}
