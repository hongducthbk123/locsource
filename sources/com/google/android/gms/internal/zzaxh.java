package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import com.google.android.gms.auth.api.Auth.AuthCredentialsOptions;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.auth.api.credentials.PasswordSpecification;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;

@Hide
public final class zzaxh {
    public static PendingIntent zza(Context context, @Nullable AuthCredentialsOptions authCredentialsOptions, HintRequest hintRequest) {
        zzbq.zza(context, (Object) "context must not be null");
        zzbq.zza(hintRequest, (Object) "request must not be null");
        if (authCredentialsOptions == null) {
        }
        PasswordSpecification passwordSpecification = (authCredentialsOptions == null || authCredentialsOptions.zza() == null) ? PasswordSpecification.zza : authCredentialsOptions.zza();
        Intent putExtra = new Intent("com.google.android.gms.auth.api.credentials.PICKER").putExtra("com.google.android.gms.credentials.hintRequestVersion", 2).putExtra("com.google.android.gms.credentials.RequestType", "Hints").putExtra("com.google.android.gms.credentials.ClaimedCallingPackage", null);
        zzbgq.zza(passwordSpecification, putExtra, "com.google.android.gms.credentials.PasswordSpecification");
        zzbgq.zza(hintRequest, putExtra, "com.google.android.gms.credentials.HintRequest");
        return PendingIntent.getActivity(context, 2000, putExtra, 134217728);
    }
}