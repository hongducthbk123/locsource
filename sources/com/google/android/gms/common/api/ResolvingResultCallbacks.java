package com.google.android.gms.common.api;

import android.app.Activity;
import android.content.IntentSender.SendIntentException;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;

public abstract class ResolvingResultCallbacks<R extends Result> extends ResultCallbacks<R> {
    private final Activity zza;
    private final int zzb;

    protected ResolvingResultCallbacks(@NonNull Activity activity, int i) {
        this.zza = (Activity) zzbq.zza(activity, (Object) "Activity must not be null");
        this.zzb = i;
    }

    @Hide
    @KeepForSdk
    public final void onFailure(@NonNull Status status) {
        if (status.hasResolution()) {
            try {
                status.startResolutionForResult(this.zza, this.zzb);
            } catch (SendIntentException e) {
                Log.e("ResolvingResultCallback", "Failed to start resolution", e);
                onUnresolvableFailure(new Status(8));
            }
        } else {
            onUnresolvableFailure(status);
        }
    }

    public abstract void onSuccess(@NonNull R r);

    public abstract void onUnresolvableFailure(@NonNull Status status);
}
