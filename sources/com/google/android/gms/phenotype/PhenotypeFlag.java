package com.google.android.gms.phenotype;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.UserManager;
import android.support.p000v4.content.PermissionChecker;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.zzdnm;
import com.google.android.gms.internal.zzdob;

@KeepForSdk
@Deprecated
public abstract class PhenotypeFlag<T> {
    private static final Object zzb = new Object();
    @SuppressLint({"StaticFieldLeak"})
    private static Context zzc = null;
    private static boolean zzd = false;
    private static Boolean zze = null;
    final String zza;
    private final Factory zzf;
    private final String zzg;
    private final T zzh;
    private T zzi;

    @KeepForSdk
    public static class Factory {
        /* access modifiers changed from: private */
        public final String zza;
        /* access modifiers changed from: private */
        public final Uri zzb;
        /* access modifiers changed from: private */
        public final String zzc;
        /* access modifiers changed from: private */
        public final String zzd;
        /* access modifiers changed from: private */
        public final boolean zze;
        /* access modifiers changed from: private */
        public final boolean zzf;

        @KeepForSdk
        public Factory(Uri uri) {
            this(null, uri, "", "", false, false);
        }

        private Factory(String str, Uri uri, String str2, String str3, boolean z, boolean z2) {
            this.zza = str;
            this.zzb = uri;
            this.zzc = str2;
            this.zzd = str3;
            this.zze = z;
            this.zzf = z2;
        }

        @KeepForSdk
        public PhenotypeFlag<String> createFlag(String str, String str2) {
            return PhenotypeFlag.zzb(this, str, str2);
        }

        @KeepForSdk
        public Factory withGservicePrefix(String str) {
            if (this.zze) {
                throw new IllegalStateException("Cannot set GServices prefix and skip GServices");
            }
            return new Factory(this.zza, this.zzb, str, this.zzd, this.zze, this.zzf);
        }

        @KeepForSdk
        public Factory withPhenotypePrefix(String str) {
            return new Factory(this.zza, this.zzb, this.zzc, str, this.zze, this.zzf);
        }
    }

    interface zza<V> {
        V zza();
    }

    private PhenotypeFlag(Factory factory, String str, T t) {
        this.zzi = null;
        if (factory.zza == null && factory.zzb == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        } else if (factory.zza == null || factory.zzb == null) {
            this.zzf = factory;
            String valueOf = String.valueOf(factory.zzc);
            String valueOf2 = String.valueOf(str);
            this.zzg = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
            String valueOf3 = String.valueOf(factory.zzd);
            String valueOf4 = String.valueOf(str);
            this.zza = valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3);
            this.zzh = t;
        } else {
            throw new IllegalArgumentException("Must pass one of SharedPreferences file name or ContentProvider URI");
        }
    }

    /* synthetic */ PhenotypeFlag(Factory factory, String str, Object obj, zzr zzr) {
        this(factory, str, obj);
    }

    @KeepForSdk
    public static void maybeInit(Context context) {
        zzdob.zzb(context);
        if (zzc == null) {
            zzdob.zza(context);
            synchronized (zzb) {
                if (VERSION.SDK_INT < 24 || !context.isDeviceProtectedStorage()) {
                    Context applicationContext = context.getApplicationContext();
                    if (applicationContext != null) {
                        context = applicationContext;
                    }
                }
                if (zzc != context) {
                    zze = null;
                }
                zzc = context;
            }
            zzd = false;
        }
    }

    private static <V> V zza(zza<V> zza2) {
        long clearCallingIdentity;
        try {
            return zza2.zza();
        } catch (SecurityException e) {
            clearCallingIdentity = Binder.clearCallingIdentity();
            V zza3 = zza2.zza();
            Binder.restoreCallingIdentity(clearCallingIdentity);
            return zza3;
        } catch (Throwable th) {
            Binder.restoreCallingIdentity(clearCallingIdentity);
            throw th;
        }
    }

    static boolean zza(String str, boolean z) {
        if (zzd()) {
            return ((Boolean) zza((zza<V>) new zzq<V>(str, false))).booleanValue();
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static PhenotypeFlag<String> zzb(Factory factory, String str, String str2) {
        return new zzs(factory, str, str2);
    }

    @TargetApi(24)
    private final T zzb() {
        if (zza("gms:phenotype:phenotype_flag:debug_bypass_phenotype", false)) {
            String str = "PhenotypeFlag";
            String str2 = "Bypass reading Phenotype values for flag: ";
            String valueOf = String.valueOf(this.zza);
            Log.w(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        } else if (this.zzf.zzb != null) {
            String str3 = (String) zza((zza<V>) new zzo<V>(this, zza.zza(zzc.getContentResolver(), this.zzf.zzb)));
            if (str3 != null) {
                return zza(str3);
            }
        } else if (this.zzf.zza != null) {
            if (VERSION.SDK_INT >= 24 && !zzc.isDeviceProtectedStorage() && !((UserManager) zzc.getSystemService(UserManager.class)).isUserUnlocked()) {
                return null;
            }
            SharedPreferences sharedPreferences = zzc.getSharedPreferences(this.zzf.zza, 0);
            if (sharedPreferences.contains(this.zza)) {
                return zza(sharedPreferences);
            }
        }
        return null;
    }

    private final T zzc() {
        if (!this.zzf.zze && zzd()) {
            String str = (String) zza((zza<V>) new zzp<V>(this));
            if (str != null) {
                return zza(str);
            }
        }
        return null;
    }

    private static boolean zzd() {
        boolean z = false;
        if (zze == null) {
            if (zzc == null) {
                return false;
            }
            if (PermissionChecker.checkCallingOrSelfPermission(zzc, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0) {
                z = true;
            }
            zze = Boolean.valueOf(z);
        }
        return zze.booleanValue();
    }

    @KeepForSdk
    public T get() {
        if (zzc == null) {
            throw new IllegalStateException("Must call PhenotypeFlag.init() first");
        }
        if (this.zzf.zzf) {
            T zzc2 = zzc();
            if (zzc2 != null) {
                return zzc2;
            }
            T zzb2 = zzb();
            if (zzb2 != null) {
                return zzb2;
            }
        } else {
            T zzb3 = zzb();
            if (zzb3 != null) {
                return zzb3;
            }
            T zzc3 = zzc();
            if (zzc3 != null) {
                return zzc3;
            }
        }
        return this.zzh;
    }

    public abstract T zza(SharedPreferences sharedPreferences);

    public abstract T zza(String str);

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ String zza() {
        return zzdnm.zza(zzc.getContentResolver(), this.zzg, (String) null);
    }
}
