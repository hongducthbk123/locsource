package com.google.android.gms.auth.account;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.internal.zzavq;
import com.google.android.gms.internal.zzawa;

public class WorkAccount {
    public static final Api<NoOptions> API = new Api<>("WorkAccount.API", zzb, zza);
    @Deprecated
    public static final WorkAccountApi WorkAccountApi = new zzavq();
    private static final zzf<zzawa> zza = new zzf<>();
    private static final zza<zzawa, NoOptions> zzb = new zzf();

    private WorkAccount() {
    }

    public static WorkAccountClient getClient(@NonNull Activity activity) {
        return new WorkAccountClient(activity);
    }

    public static WorkAccountClient getClient(@NonNull Context context) {
        return new WorkAccountClient(context);
    }
}
