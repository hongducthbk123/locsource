package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import java.util.Arrays;

@Hide
public final class zzbfv extends zzbgl {
    public static final Creator<zzbfv> CREATOR = new zzbfw();
    public final int zza;
    public final String zzb;
    private String zzc;
    private int zzd;
    private String zze;
    private String zzf;
    private boolean zzg;
    private boolean zzh;
    private int zzi;

    public zzbfv(String str, int i, int i2, String str2, String str3, String str4, boolean z, int i3) {
        this.zzc = (String) zzbq.zza(str);
        this.zzd = i;
        this.zza = i2;
        this.zzb = str2;
        this.zze = str3;
        this.zzf = str4;
        this.zzg = !z;
        this.zzh = z;
        this.zzi = i3;
    }

    public zzbfv(String str, int i, int i2, String str2, String str3, boolean z, String str4, boolean z2, int i3) {
        this.zzc = str;
        this.zzd = i;
        this.zza = i2;
        this.zze = str2;
        this.zzf = str3;
        this.zzg = z;
        this.zzb = str4;
        this.zzh = z2;
        this.zzi = i3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbfv)) {
            return false;
        }
        zzbfv zzbfv = (zzbfv) obj;
        return zzbg.zza(this.zzc, zzbfv.zzc) && this.zzd == zzbfv.zzd && this.zza == zzbfv.zza && zzbg.zza(this.zzb, zzbfv.zzb) && zzbg.zza(this.zze, zzbfv.zze) && zzbg.zza(this.zzf, zzbfv.zzf) && this.zzg == zzbfv.zzg && this.zzh == zzbfv.zzh && this.zzi == zzbfv.zzi;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzc, Integer.valueOf(this.zzd), Integer.valueOf(this.zza), this.zzb, this.zze, this.zzf, Boolean.valueOf(this.zzg), Boolean.valueOf(this.zzh), Integer.valueOf(this.zzi)});
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PlayLoggerContext[");
        sb.append("package=").append(this.zzc).append(',');
        sb.append("packageVersionCode=").append(this.zzd).append(',');
        sb.append("logSource=").append(this.zza).append(',');
        sb.append("logSourceName=").append(this.zzb).append(',');
        sb.append("uploadAccount=").append(this.zze).append(',');
        sb.append("loggingId=").append(this.zzf).append(',');
        sb.append("logAndroidId=").append(this.zzg).append(',');
        sb.append("isAnonymous=").append(this.zzh).append(',');
        sb.append("qosTier=").append(this.zzi);
        sb.append("]");
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zza2 = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 2, this.zzc, false);
        zzbgo.zza(parcel, 3, this.zzd);
        zzbgo.zza(parcel, 4, this.zza);
        zzbgo.zza(parcel, 5, this.zze, false);
        zzbgo.zza(parcel, 6, this.zzf, false);
        zzbgo.zza(parcel, 7, this.zzg);
        zzbgo.zza(parcel, 8, this.zzb, false);
        zzbgo.zza(parcel, 9, this.zzh);
        zzbgo.zza(parcel, 10, this.zzi);
        zzbgo.zza(parcel, zza2);
    }
}
