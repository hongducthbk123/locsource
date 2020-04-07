package com.google.android.gms.auth.api.signin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.app.Fragment;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.Builder;
import com.google.android.gms.auth.api.signin.internal.zzf;
import com.google.android.gms.auth.api.signin.internal.zzp;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Hide
public final class GoogleSignIn {
    private GoogleSignIn() {
    }

    @NonNull
    public static GoogleSignInAccount getAccountForExtension(@NonNull Context context, @NonNull GoogleSignInOptionsExtension googleSignInOptionsExtension) {
        zzbq.zza(context, (Object) "please provide a valid Context object");
        zzbq.zza(googleSignInOptionsExtension, (Object) "please provide valid GoogleSignInOptionsExtension");
        GoogleSignInAccount lastSignedInAccount = getLastSignedInAccount(context);
        if (lastSignedInAccount == null) {
            lastSignedInAccount = GoogleSignInAccount.zza();
        }
        return lastSignedInAccount.zza(zza(googleSignInOptionsExtension.getImpliedScopes()));
    }

    @NonNull
    public static GoogleSignInAccount getAccountForScopes(@NonNull Context context, @NonNull Scope scope, Scope... scopeArr) {
        zzbq.zza(context, (Object) "please provide a valid Context object");
        zzbq.zza(scope, (Object) "please provide at least one valid scope");
        GoogleSignInAccount lastSignedInAccount = getLastSignedInAccount(context);
        if (lastSignedInAccount == null) {
            lastSignedInAccount = GoogleSignInAccount.zza();
        }
        lastSignedInAccount.zza(scope);
        lastSignedInAccount.zza(scopeArr);
        return lastSignedInAccount;
    }

    public static GoogleSignInClient getClient(@NonNull Activity activity, @NonNull GoogleSignInOptions googleSignInOptions) {
        return new GoogleSignInClient(activity, (GoogleSignInOptions) zzbq.zza(googleSignInOptions));
    }

    public static GoogleSignInClient getClient(@NonNull Context context, @NonNull GoogleSignInOptions googleSignInOptions) {
        return new GoogleSignInClient(context, (GoogleSignInOptions) zzbq.zza(googleSignInOptions));
    }

    @Nullable
    public static GoogleSignInAccount getLastSignedInAccount(Context context) {
        return zzp.zza(context).zzb();
    }

    public static Task<GoogleSignInAccount> getSignedInAccountFromIntent(@Nullable Intent intent) {
        GoogleSignInResult zza = zzf.zza(intent);
        return zza == null ? Tasks.forException(zzb.zza(Status.zzc)) : (!zza.getStatus().isSuccess() || zza.getSignInAccount() == null) ? Tasks.forException(zzb.zza(zza.getStatus())) : Tasks.forResult(zza.getSignInAccount());
    }

    public static boolean hasPermissions(@Nullable GoogleSignInAccount googleSignInAccount, @NonNull GoogleSignInOptionsExtension googleSignInOptionsExtension) {
        zzbq.zza(googleSignInOptionsExtension, (Object) "Please provide a non-null GoogleSignInOptionsExtension");
        return hasPermissions(googleSignInAccount, zza(googleSignInOptionsExtension.getImpliedScopes()));
    }

    public static boolean hasPermissions(@Nullable GoogleSignInAccount googleSignInAccount, @NonNull Scope... scopeArr) {
        if (googleSignInAccount == null) {
            return false;
        }
        HashSet hashSet = new HashSet();
        Collections.addAll(hashSet, scopeArr);
        return googleSignInAccount.getGrantedScopes().containsAll(hashSet);
    }

    public static void requestPermissions(@NonNull Activity activity, int i, @Nullable GoogleSignInAccount googleSignInAccount, @NonNull GoogleSignInOptionsExtension googleSignInOptionsExtension) {
        zzbq.zza(activity, (Object) "Please provide a non-null Activity");
        zzbq.zza(googleSignInOptionsExtension, (Object) "Please provide a non-null GoogleSignInOptionsExtension");
        requestPermissions(activity, i, googleSignInAccount, zza(googleSignInOptionsExtension.getImpliedScopes()));
    }

    public static void requestPermissions(@NonNull Activity activity, int i, @Nullable GoogleSignInAccount googleSignInAccount, @NonNull Scope... scopeArr) {
        zzbq.zza(activity, (Object) "Please provide a non-null Activity");
        zzbq.zza(scopeArr, (Object) "Please provide at least one scope");
        activity.startActivityForResult(zza(activity, googleSignInAccount, scopeArr), i);
    }

    public static void requestPermissions(@NonNull Fragment fragment, int i, @Nullable GoogleSignInAccount googleSignInAccount, @NonNull GoogleSignInOptionsExtension googleSignInOptionsExtension) {
        zzbq.zza(fragment, (Object) "Please provide a non-null Fragment");
        zzbq.zza(googleSignInOptionsExtension, (Object) "Please provide a non-null GoogleSignInOptionsExtension");
        requestPermissions(fragment, i, googleSignInAccount, zza(googleSignInOptionsExtension.getImpliedScopes()));
    }

    public static void requestPermissions(@NonNull Fragment fragment, int i, @Nullable GoogleSignInAccount googleSignInAccount, @NonNull Scope... scopeArr) {
        zzbq.zza(fragment, (Object) "Please provide a non-null Fragment");
        zzbq.zza(scopeArr, (Object) "Please provide at least one scope");
        fragment.startActivityForResult(zza(fragment.getActivity(), googleSignInAccount, scopeArr), i);
    }

    @NonNull
    private static Intent zza(@NonNull Activity activity, @Nullable GoogleSignInAccount googleSignInAccount, @NonNull Scope... scopeArr) {
        Builder builder = new Builder();
        if (scopeArr.length > 0) {
            builder.requestScopes(scopeArr[0], scopeArr);
        }
        if (googleSignInAccount != null && !TextUtils.isEmpty(googleSignInAccount.getEmail())) {
            builder.setAccountName(googleSignInAccount.getEmail());
        }
        return new GoogleSignInClient(activity, builder.build()).getSignInIntent();
    }

    @NonNull
    private static Scope[] zza(@Nullable List<Scope> list) {
        return list == null ? new Scope[0] : (Scope[]) list.toArray(new Scope[list.size()]);
    }
}
