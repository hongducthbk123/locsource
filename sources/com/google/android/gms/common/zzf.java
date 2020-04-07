package com.google.android.gms.common;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzak;
import com.google.android.gms.common.util.zzj;
import com.google.android.gms.internal.zzbih;
import com.google.android.vending.expansion.downloader.Constants;

@Hide
public class zzf {
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = zzs.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    private static final zzf zza = new zzf();

    zzf() {
    }

    public static int zza(Context context, int i) {
        int zza2 = zzs.zza(context, i);
        if (zzs.zzc(context, zza2)) {
            return 18;
        }
        return zza2;
    }

    @Nullable
    @Hide
    public static Intent zza(Context context, int i, @Nullable String str) {
        switch (i) {
            case 1:
            case 2:
                return (context == null || !zzj.zzb(context)) ? zzak.zza("com.google.android.gms", zza(context, str)) : zzak.zza();
            case 3:
                return zzak.zza("com.google.android.gms");
            default:
                return null;
        }
    }

    public static zzf zza() {
        return zza;
    }

    private static String zza(@Nullable Context context, @Nullable String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("gcore_");
        sb.append(GOOGLE_PLAY_SERVICES_VERSION_CODE);
        sb.append(Constants.FILENAME_SEQUENCE_SEPARATOR);
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
        }
        sb.append(Constants.FILENAME_SEQUENCE_SEPARATOR);
        if (context != null) {
            sb.append(context.getPackageName());
        }
        sb.append(Constants.FILENAME_SEQUENCE_SEPARATOR);
        if (context != null) {
            try {
                sb.append(zzbih.zza(context).zzb(context.getPackageName(), 0).versionCode);
            } catch (NameNotFoundException e) {
            }
        }
        return sb.toString();
    }

    @Hide
    public static void zzb(Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        zzs.zza(context);
    }

    @Hide
    public static boolean zzb(Context context, int i) {
        return zzs.zzc(context, i);
    }

    @Hide
    public static void zzc(Context context) {
        zzs.zzc(context);
    }

    @Hide
    public static int zzd(Context context) {
        return zzs.zzd(context);
    }

    @Nullable
    public PendingIntent getErrorResolutionPendingIntent(Context context, int i, int i2) {
        return zza(context, i, i2, null);
    }

    public String getErrorString(int i) {
        return zzs.getErrorString(i);
    }

    public int isGooglePlayServicesAvailable(Context context) {
        return zza(context, -1);
    }

    public boolean isUserResolvableError(int i) {
        return zzs.isUserRecoverableError(i);
    }

    @Nullable
    @Hide
    public final PendingIntent zza(Context context, int i, int i2, @Nullable String str) {
        Intent zza2 = zza(context, i, str);
        if (zza2 == null) {
            return null;
        }
        return PendingIntent.getActivity(context, i2, zza2, 268435456);
    }

    @Nullable
    @Hide
    @Deprecated
    public final Intent zza(int i) {
        return zza(null, i, null);
    }
}
