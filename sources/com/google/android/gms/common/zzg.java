package com.google.android.gms.common;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzba;
import com.google.android.gms.common.internal.zzbb;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.zzc;

@Hide
final class zzg {
    private static volatile zzba zza;
    private static final Object zzb = new Object();
    private static Context zzc;

    static zzp zza(String str, zzh zzh, boolean z) {
        boolean z2 = true;
        try {
            if (zza == null) {
                zzbq.zza(zzc);
                synchronized (zzb) {
                    if (zza == null) {
                        zza = zzbb.zza(DynamiteModule.zza(zzc, DynamiteModule.zzc, "com.google.android.gms.googlecertificates").zza("com.google.android.gms.common.GoogleCertificatesImpl"));
                    }
                }
            }
            zzbq.zza(zzc);
            try {
                if (zza.zza(new zzn(str, zzh, z), zzn.zza(zzc.getPackageManager()))) {
                    return zzp.zza();
                }
                if (z || !zza(str, zzh, true).zza) {
                    z2 = false;
                }
                return zzp.zza(str, zzh, z, z2);
            } catch (RemoteException e) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
                return zzp.zza("module call", e);
            }
        } catch (zzc e2) {
            return zzp.zza("module init", e2);
        }
    }

    static synchronized void zza(Context context) {
        synchronized (zzg.class) {
            if (zzc != null) {
                Log.w("GoogleCertificates", "GoogleCertificates has been initialized already");
            } else if (context != null) {
                zzc = context.getApplicationContext();
            }
        }
    }
}
