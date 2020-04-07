package com.google.android.gms.phenotype;

import android.net.Uri;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.internal.zzcvy;
import com.google.android.gms.internal.zzcvz;

@KeepForSdk
public final class Phenotype {
    private static final zzf<zzcvz> zza = new zzf<>();
    private static final zza<zzcvz, NoOptions> zzb = new zzl();
    @Deprecated
    private static Api<NoOptions> zzc = new Api<>("Phenotype.API", zzb, zza);
    @Deprecated
    private static zzm zzd = new zzcvy();

    private Phenotype() {
    }

    @KeepForSdk
    public static Uri getContentProviderUri(String str) {
        String str2 = "content://com.google.android.gms.phenotype/";
        String valueOf = String.valueOf(Uri.encode(str));
        return Uri.parse(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
    }
}
