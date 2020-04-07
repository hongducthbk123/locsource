package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.util.zzs;

public final class zzbif {
    private static Context zza;
    private static Boolean zzb;

    public static synchronized boolean zza(Context context) {
        boolean booleanValue;
        synchronized (zzbif.class) {
            Context applicationContext = context.getApplicationContext();
            if (zza == null || zzb == null || zza != applicationContext) {
                zzb = null;
                if (zzs.zzi()) {
                    zzb = Boolean.valueOf(applicationContext.getPackageManager().isInstantApp());
                } else {
                    try {
                        context.getClassLoader().loadClass("com.google.android.instantapps.supervisor.InstantAppsRuntime");
                        zzb = Boolean.valueOf(true);
                    } catch (ClassNotFoundException e) {
                        zzb = Boolean.valueOf(false);
                    }
                }
                zza = applicationContext;
                booleanValue = zzb.booleanValue();
            } else {
                booleanValue = zzb.booleanValue();
            }
        }
        return booleanValue;
    }
}
