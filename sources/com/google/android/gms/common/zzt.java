package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbih;

@Hide
public class zzt {
    private static zzt zza;
    private final Context zzb;

    private zzt(Context context) {
        this.zzb = context.getApplicationContext();
    }

    @Hide
    private static zzh zza(PackageInfo packageInfo, zzh... zzhArr) {
        if (packageInfo.signatures == null) {
            return null;
        }
        if (packageInfo.signatures.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return null;
        }
        zzi zzi = new zzi(packageInfo.signatures[0].toByteArray());
        for (int i = 0; i < zzhArr.length; i++) {
            if (zzhArr[i].equals(zzi)) {
                return zzhArr[i];
            }
        }
        return null;
    }

    private final zzp zza(String str) {
        try {
            PackageInfo zzb2 = zzbih.zza(this.zzb).zzb(str, 64);
            boolean zzb3 = zzs.zzb(this.zzb);
            if (zzb2 == null) {
                return zzp.zza("null pkg");
            }
            if (zzb2.signatures.length != 1) {
                return zzp.zza("single cert required");
            }
            zzi zzi = new zzi(zzb2.signatures[0].toByteArray());
            String str2 = zzb2.packageName;
            zzp zza2 = zzg.zza(str2, zzi, zzb3);
            return (!zza2.zza || zzb2.applicationInfo == null || (zzb2.applicationInfo.flags & 2) == 0) ? zza2 : (!zzb3 || zzg.zza(str2, zzi, false).zza) ? zzp.zza("debuggable release cert app rejected") : zza2;
        } catch (NameNotFoundException e) {
            String str3 = "no pkg ";
            String valueOf = String.valueOf(str);
            return zzp.zza(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        }
    }

    public static zzt zza(Context context) {
        zzbq.zza(context);
        synchronized (zzt.class) {
            if (zza == null) {
                zzg.zza(context);
                zza = new zzt(context);
            }
        }
        return zza;
    }

    @Hide
    public static boolean zza(PackageInfo packageInfo, boolean z) {
        zzh zza2;
        if (!(packageInfo == null || packageInfo.signatures == null)) {
            if (z) {
                zza2 = zza(packageInfo, zzk.zza);
            } else {
                zza2 = zza(packageInfo, zzk.zza[0]);
            }
            if (zza2 != null) {
                return true;
            }
        }
        return false;
    }

    @Hide
    public final boolean zza(int i) {
        zzp zzp;
        String[] zza2 = zzbih.zza(this.zzb).zza(i);
        if (zza2 != null && zza2.length != 0) {
            zzp = null;
            for (String zza3 : zza2) {
                zzp = zza(zza3);
                if (zzp.zza) {
                    break;
                }
            }
        } else {
            zzp = zzp.zza("no pkgs");
        }
        if (!zzp.zza) {
            if (zzp.zzb != null) {
                Log.d("GoogleCertificatesRslt", zzp.zzb(), zzp.zzb);
            } else {
                Log.d("GoogleCertificatesRslt", zzp.zzb());
            }
        }
        return zzp.zza;
    }

    @Hide
    public final boolean zza(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        if (zza(packageInfo, false)) {
            return true;
        }
        if (!zza(packageInfo, true)) {
            return false;
        }
        if (zzs.zzb(this.zzb)) {
            return true;
        }
        Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
        return false;
    }
}
