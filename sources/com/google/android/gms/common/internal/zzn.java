package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.support.annotation.BinderThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;

@Hide
public final class zzn extends zze {
    private IBinder zza;
    private /* synthetic */ zzd zzb;

    @BinderThread
    public zzn(zzd zzd, int i, IBinder iBinder, Bundle bundle) {
        this.zzb = zzd;
        super(zzd, i, bundle);
        this.zza = iBinder;
    }

    /* access modifiers changed from: protected */
    public final void zza(ConnectionResult connectionResult) {
        if (this.zzb.zzv != null) {
            this.zzb.zzv.zza(connectionResult);
        }
        this.zzb.zza(connectionResult);
    }

    /* access modifiers changed from: protected */
    public final boolean zza() {
        try {
            String interfaceDescriptor = this.zza.getInterfaceDescriptor();
            if (!this.zzb.zzb().equals(interfaceDescriptor)) {
                String zzb2 = this.zzb.zzb();
                Log.e("GmsClient", new StringBuilder(String.valueOf(zzb2).length() + 34 + String.valueOf(interfaceDescriptor).length()).append("service descriptor mismatch: ").append(zzb2).append(" vs. ").append(interfaceDescriptor).toString());
                return false;
            }
            IInterface zza2 = this.zzb.zza(this.zza);
            if (zza2 == null) {
                return false;
            }
            if (!this.zzb.zza(2, 4, zza2) && !this.zzb.zza(3, 4, zza2)) {
                return false;
            }
            this.zzb.zzy = null;
            Bundle q_ = this.zzb.mo11780q_();
            if (this.zzb.zzu != null) {
                this.zzb.zzu.zza(q_);
            }
            return true;
        } catch (RemoteException e) {
            Log.w("GmsClient", "service probably died");
            return false;
        }
    }
}
