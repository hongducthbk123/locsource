package com.google.android.gms.common.api.internal;

import android.content.DialogInterface.OnCancelListener;
import android.support.annotation.MainThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;

final class zzq implements Runnable {
    final /* synthetic */ zzo zza;
    private final zzp zzb;

    zzq(zzo zzo, zzp zzp) {
        this.zza = zzo;
        this.zzb = zzp;
    }

    @MainThread
    public final void run() {
        if (this.zza.zza) {
            ConnectionResult zzb2 = this.zzb.zzb();
            if (zzb2.hasResolution()) {
                this.zza.zzd.startActivityForResult(GoogleApiActivity.zza(this.zza.zzg(), zzb2.getResolution(), this.zzb.zza(), false), 1);
            } else if (this.zza.zzc.isUserResolvableError(zzb2.getErrorCode())) {
                this.zza.zzc.zza(this.zza.zzg(), this.zza.zzd, zzb2.getErrorCode(), 2, this.zza);
            } else if (zzb2.getErrorCode() == 18) {
                GoogleApiAvailability.zza(this.zza.zzg().getApplicationContext(), (zzby) new zzr(this, GoogleApiAvailability.zza(this.zza.zzg(), (OnCancelListener) this.zza)));
            } else {
                this.zza.zza(zzb2, this.zzb.zza());
            }
        }
    }
}
