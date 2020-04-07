package com.google.android.gms.common;

import com.adjust.sdk.Constants;
import com.google.android.gms.common.util.zza;
import com.google.android.gms.common.util.zzm;

final class zzr extends zzp {
    private final String zzc;
    private final zzh zzd;
    private final boolean zze;
    private final boolean zzf;

    private zzr(String str, zzh zzh, boolean z, boolean z2) {
        super(false, null, null);
        this.zzc = str;
        this.zzd = zzh;
        this.zze = z;
        this.zzf = z2;
    }

    /* access modifiers changed from: 0000 */
    public final String zzb() {
        String str = this.zzf ? "debug cert rejected" : "not whitelisted";
        String str2 = this.zzc;
        String zzb = zzm.zzb(zza.zza(Constants.SHA1).digest(this.zzd.zza()));
        return new StringBuilder(String.valueOf(str).length() + 44 + String.valueOf(str2).length() + String.valueOf(zzb).length()).append(str).append(": pkg=").append(str2).append(", sha1=").append(zzb).append(", atk=").append(this.zze).append(", ver=12210278.false").toString();
    }
}
