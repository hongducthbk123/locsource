package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.gms.C1209R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzca;

@Deprecated
public final class zzbz {
    private static final Object zza = new Object();
    private static zzbz zzb;
    private final String zzc;
    private final Status zzd;
    private final boolean zze;
    private final boolean zzf;

    private zzbz(Context context) {
        boolean z = true;
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("google_app_measurement_enable", "integer", resources.getResourcePackageName(C1209R.string.common_google_play_services_unknown_issue));
        if (identifier != 0) {
            boolean z2 = resources.getInteger(identifier) != 0;
            if (z2) {
                z = false;
            }
            this.zzf = z;
            z = z2;
        } else {
            this.zzf = false;
        }
        this.zze = z;
        String zza2 = zzbf.zza(context);
        if (zza2 == null) {
            zza2 = new zzca(context).zza("google_app_id");
        }
        if (TextUtils.isEmpty(zza2)) {
            this.zzd = new Status(10, "Missing google app id value from from string resources with name google_app_id.");
            this.zzc = null;
            return;
        }
        this.zzc = zza2;
        this.zzd = Status.zza;
    }

    public static Status zza(Context context) {
        Status status;
        zzbq.zza(context, (Object) "Context must not be null.");
        synchronized (zza) {
            if (zzb == null) {
                zzb = new zzbz(context);
            }
            status = zzb.zzd;
        }
        return status;
    }

    private static zzbz zza(String str) {
        zzbz zzbz;
        synchronized (zza) {
            if (zzb == null) {
                throw new IllegalStateException(new StringBuilder(String.valueOf(str).length() + 34).append("Initialize must be called before ").append(str).append(".").toString());
            }
            zzbz = zzb;
        }
        return zzbz;
    }

    public static String zza() {
        return zza("getGoogleAppId").zzc;
    }

    public static boolean zzb() {
        return zza("isMeasurementExplicitlyDisabled").zzf;
    }
}
