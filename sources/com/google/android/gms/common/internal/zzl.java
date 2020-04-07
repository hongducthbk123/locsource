package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;

public final class zzl implements ServiceConnection {
    private final int zza;
    private /* synthetic */ zzd zzb;

    public zzl(zzd zzd, int i) {
        this.zzb = zzd;
        this.zza = i;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        zzay zzaz;
        if (iBinder == null) {
            this.zzb.zzc(16);
            return;
        }
        synchronized (this.zzb.zzo) {
            zzd zzd = this.zzb;
            if (iBinder == null) {
                zzaz = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                zzaz = (queryLocalInterface == null || !(queryLocalInterface instanceof zzay)) ? new zzaz(iBinder) : (zzay) queryLocalInterface;
            }
            zzd.zzp = zzaz;
        }
        this.zzb.zza(0, (Bundle) null, this.zza);
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        synchronized (this.zzb.zzo) {
            this.zzb.zzp = null;
        }
        this.zzb.zza.sendMessage(this.zzb.zza.obtainMessage(6, this.zza, 1));
    }
}
