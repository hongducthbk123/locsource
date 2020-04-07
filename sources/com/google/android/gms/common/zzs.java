package com.google.android.gms.common;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import com.google.android.gms.C1209R;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbf;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzj;
import com.google.android.gms.common.util.zzz;
import com.google.android.gms.internal.zzbih;
import java.util.concurrent.atomic.AtomicBoolean;

@Hide
public class zzs {
    @Deprecated
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 12210000;
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    static final AtomicBoolean zza = new AtomicBoolean();
    @Hide
    private static boolean zzb = false;
    @Hide
    private static boolean zzc = false;
    private static boolean zzd = false;
    private static boolean zze = false;
    private static final AtomicBoolean zzf = new AtomicBoolean();

    zzs() {
    }

    @Deprecated
    public static PendingIntent getErrorPendingIntent(int i, Context context, int i2) {
        return zzf.zza().getErrorResolutionPendingIntent(context, i, i2);
    }

    @Deprecated
    public static String getErrorString(int i) {
        return ConnectionResult.zza(i);
    }

    public static Context getRemoteContext(Context context) {
        try {
            return context.createPackageContext("com.google.android.gms", 3);
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    public static Resources getRemoteResource(Context context) {
        try {
            return context.getPackageManager().getResourcesForApplication("com.google.android.gms");
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    @Deprecated
    public static int isGooglePlayServicesAvailable(Context context) {
        return zza(context, -1);
    }

    @Deprecated
    public static boolean isUserRecoverableError(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 9:
                return true;
            default:
                return false;
        }
    }

    @Deprecated
    public static int zza(Context context, int i) {
        try {
            context.getResources().getString(C1209R.string.common_google_play_services_unknown_issue);
        } catch (Throwable th) {
            Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
        }
        if (!"com.google.android.gms".equals(context.getPackageName()) && !zzf.get()) {
            int zzb2 = zzbf.zzb(context);
            if (zzb2 == 0) {
                throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
            } else if (zzb2 != GOOGLE_PLAY_SERVICES_VERSION_CODE) {
                throw new IllegalStateException("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected " + GOOGLE_PLAY_SERVICES_VERSION_CODE + " but found " + zzb2 + ".  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
            }
        }
        return zza(context, !zzj.zzb(context) && !zzj.zzd(context), GOOGLE_PLAY_SERVICES_VERSION_CODE, i);
    }

    private static int zza(Context context, boolean z, int i, int i2) {
        zzbq.zzb(i2 == -1 || i2 >= 0);
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = null;
        if (z) {
            try {
                packageInfo = packageManager.getPackageInfo("com.android.vending", 8256);
            } catch (NameNotFoundException e) {
                Log.w("GooglePlayServicesUtil", "Google Play Store is missing.");
                return 9;
            }
        }
        try {
            PackageInfo packageInfo2 = packageManager.getPackageInfo("com.google.android.gms", 64);
            zzt.zza(context);
            if (!zzt.zza(packageInfo2, true)) {
                Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
                return 9;
            } else if (!z || (zzt.zza(packageInfo, true) && packageInfo.signatures[0].equals(packageInfo2.signatures[0]))) {
                int i3 = packageInfo2.versionCode / 1000;
                if (i3 >= i / 1000 || (i2 != -1 && i3 >= i2 / 1000)) {
                    ApplicationInfo applicationInfo = packageInfo2.applicationInfo;
                    if (applicationInfo == null) {
                        try {
                            applicationInfo = packageManager.getApplicationInfo("com.google.android.gms", 0);
                        } catch (NameNotFoundException e2) {
                            Log.wtf("GooglePlayServicesUtil", "Google Play services missing when getting application info.", e2);
                            return 1;
                        }
                    }
                    return !applicationInfo.enabled ? 3 : 0;
                }
                Log.w("GooglePlayServicesUtil", "Google Play services out of date.  Requires " + GOOGLE_PLAY_SERVICES_VERSION_CODE + " but found " + packageInfo2.versionCode);
                return 2;
            } else {
                Log.w("GooglePlayServicesUtil", "Google Play Store signature invalid.");
                return 9;
            }
        } catch (NameNotFoundException e3) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 1;
        }
    }

    @Hide
    @Deprecated
    public static void zza(Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        zzf.zza();
        int zza2 = zzf.zza(context, -1);
        if (zza2 != 0) {
            zzf.zza();
            Intent zza3 = zzf.zza(context, zza2, "e");
            Log.e("GooglePlayServicesUtil", "GooglePlayServices not available due to error " + zza2);
            if (zza3 == null) {
                throw new GooglePlayServicesNotAvailableException(zza2);
            }
            throw new GooglePlayServicesRepairableException(zza2, "Google Play Services not available", zza3);
        }
    }

    @Hide
    @TargetApi(19)
    @Deprecated
    public static boolean zza(Context context, int i, String str) {
        return zzz.zza(context, i, str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0077  */
    @android.annotation.TargetApi(21)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean zza(android.content.Context r5, java.lang.String r6) {
        /*
            r1 = 1
            r2 = 0
            java.lang.String r0 = "com.google.android.gms"
            boolean r3 = r6.equals(r0)
            boolean r0 = com.google.android.gms.common.util.zzs.zzg()
            if (r0 == 0) goto L_0x0039
            android.content.pm.PackageManager r0 = r5.getPackageManager()     // Catch:{ Exception -> 0x0036 }
            android.content.pm.PackageInstaller r0 = r0.getPackageInstaller()     // Catch:{ Exception -> 0x0036 }
            java.util.List r0 = r0.getAllSessions()     // Catch:{ Exception -> 0x0036 }
            java.util.Iterator r4 = r0.iterator()
        L_0x001e:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x0039
            java.lang.Object r0 = r4.next()
            android.content.pm.PackageInstaller$SessionInfo r0 = (android.content.pm.PackageInstaller.SessionInfo) r0
            java.lang.String r0 = r0.getAppPackageName()
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x001e
            r0 = r1
        L_0x0035:
            return r0
        L_0x0036:
            r0 = move-exception
            r0 = r2
            goto L_0x0035
        L_0x0039:
            android.content.pm.PackageManager r0 = r5.getPackageManager()
            r4 = 8192(0x2000, float:1.14794E-41)
            android.content.pm.ApplicationInfo r0 = r0.getApplicationInfo(r6, r4)     // Catch:{ NameNotFoundException -> 0x007d }
            if (r3 == 0) goto L_0x0048
            boolean r0 = r0.enabled     // Catch:{ NameNotFoundException -> 0x007d }
            goto L_0x0035
        L_0x0048:
            boolean r0 = r0.enabled     // Catch:{ NameNotFoundException -> 0x007d }
            if (r0 == 0) goto L_0x007b
            boolean r0 = com.google.android.gms.common.util.zzs.zzd()     // Catch:{ NameNotFoundException -> 0x007d }
            if (r0 == 0) goto L_0x0079
            java.lang.String r0 = "user"
            java.lang.Object r0 = r5.getSystemService(r0)     // Catch:{ NameNotFoundException -> 0x007d }
            android.os.UserManager r0 = (android.os.UserManager) r0     // Catch:{ NameNotFoundException -> 0x007d }
            java.lang.String r3 = r5.getPackageName()     // Catch:{ NameNotFoundException -> 0x007d }
            android.os.Bundle r0 = r0.getApplicationRestrictions(r3)     // Catch:{ NameNotFoundException -> 0x007d }
            if (r0 == 0) goto L_0x0079
            java.lang.String r3 = "true"
            java.lang.String r4 = "restricted_profile"
            java.lang.String r0 = r0.getString(r4)     // Catch:{ NameNotFoundException -> 0x007d }
            boolean r0 = r3.equals(r0)     // Catch:{ NameNotFoundException -> 0x007d }
            if (r0 == 0) goto L_0x0079
            r0 = r1
        L_0x0075:
            if (r0 != 0) goto L_0x007b
            r0 = r1
            goto L_0x0035
        L_0x0079:
            r0 = r2
            goto L_0x0075
        L_0x007b:
            r0 = r2
            goto L_0x0035
        L_0x007d:
            r0 = move-exception
            r0 = r2
            goto L_0x0035
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.zzs.zza(android.content.Context, java.lang.String):boolean");
    }

    @Hide
    public static boolean zzb(Context context) {
        if (!zze) {
            try {
                PackageInfo zzb2 = zzbih.zza(context).zzb("com.google.android.gms", 64);
                zzt.zza(context);
                if (zzb2 == null || zzt.zza(zzb2, false) || !zzt.zza(zzb2, true)) {
                    zzd = false;
                } else {
                    zzd = true;
                }
            } catch (NameNotFoundException e) {
                Log.w("GooglePlayServicesUtil", "Cannot find Google Play services package name.", e);
            } finally {
                zze = true;
            }
        }
        return zzd || !"user".equals(Build.TYPE);
    }

    @Hide
    @Deprecated
    public static boolean zzb(Context context, int i) {
        return zzz.zza(context, i);
    }

    @Hide
    @Deprecated
    public static void zzc(Context context) {
        if (!zza.getAndSet(true)) {
            try {
                NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                if (notificationManager != null) {
                    notificationManager.cancel(10436);
                }
            } catch (SecurityException e) {
            }
        }
    }

    @Hide
    @Deprecated
    public static boolean zzc(Context context, int i) {
        if (i == 18) {
            return true;
        }
        if (i == 1) {
            return zza(context, "com.google.android.gms");
        }
        return false;
    }

    @Hide
    @Deprecated
    public static int zzd(Context context) {
        boolean z = false;
        try {
            return context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        } catch (NameNotFoundException e) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return z;
        }
    }
}
