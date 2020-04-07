package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.zzc;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.flags.ModuleDescriptor;

@Hide
public final class zzccl {
    private boolean zza = false;
    private zzccm zzb = null;

    public final <T> T zza(zzcce<T> zzcce) {
        synchronized (this) {
            if (this.zza) {
                return zzcce.zza(this.zzb);
            }
            T zzb2 = zzcce.zzb();
            return zzb2;
        }
    }

    public final void zza(Context context) {
        synchronized (this) {
            if (!this.zza) {
                try {
                    this.zzb = zzccn.asInterface(DynamiteModule.zza(context, DynamiteModule.zze, ModuleDescriptor.MODULE_ID).zza("com.google.android.gms.flags.impl.FlagProviderImpl"));
                    this.zzb.init(zzn.zza(context));
                    this.zza = true;
                } catch (RemoteException | zzc e) {
                    Log.w("FlagValueProvider", "Failed to initialize flags module.", e);
                }
                return;
            }
            return;
        }
    }
}
