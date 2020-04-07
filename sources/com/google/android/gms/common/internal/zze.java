package com.google.android.gms.common.internal;

import android.app.PendingIntent;
import android.os.Bundle;
import android.support.annotation.BinderThread;
import com.google.android.gms.common.ConnectionResult;

@Hide
abstract class zze extends zzi<Boolean> {
    private int zza;
    private Bundle zzb;
    private /* synthetic */ zzd zzc;

    @BinderThread
    protected zze(zzd zzd, int i, Bundle bundle) {
        this.zzc = zzd;
        super(zzd, Boolean.valueOf(true));
        this.zza = i;
        this.zzb = bundle;
    }

    /* access modifiers changed from: protected */
    public abstract void zza(ConnectionResult connectionResult);

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(Object obj) {
        PendingIntent pendingIntent = null;
        if (((Boolean) obj) == null) {
            this.zzc.zzb(1, null);
            return;
        }
        switch (this.zza) {
            case 0:
                if (!zza()) {
                    this.zzc.zzb(1, null);
                    zza(new ConnectionResult(8, null));
                    return;
                }
                return;
            case 10:
                this.zzc.zzb(1, null);
                throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
            default:
                this.zzc.zzb(1, null);
                if (this.zzb != null) {
                    pendingIntent = (PendingIntent) this.zzb.getParcelable("pendingIntent");
                }
                zza(new ConnectionResult(this.zza, pendingIntent));
                return;
        }
    }

    /* access modifiers changed from: protected */
    public abstract boolean zza();

    /* access modifiers changed from: protected */
    public final void zzb() {
    }
}
