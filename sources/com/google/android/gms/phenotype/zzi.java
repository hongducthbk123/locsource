package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Base64;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.Comparator;

public final class zzi extends zzbgl implements Comparable<zzi> {
    public static final Creator<zzi> CREATOR = new zzk();
    private static Comparator<zzi> zzi = new zzj();
    public final String zza;
    public final int zzb;
    private long zzc;
    private boolean zzd;
    private double zze;
    private String zzf;
    private byte[] zzg;
    private int zzh;

    public zzi(String str, long j, boolean z, double d, String str2, byte[] bArr, int i, int i2) {
        this.zza = str;
        this.zzc = j;
        this.zzd = z;
        this.zze = d;
        this.zzf = str2;
        this.zzg = bArr;
        this.zzh = i;
        this.zzb = i2;
    }

    private static int zza(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    public final /* synthetic */ int compareTo(Object obj) {
        zzi zzi2 = (zzi) obj;
        int compareTo = this.zza.compareTo(zzi2.zza);
        if (compareTo != 0) {
            return compareTo;
        }
        int zza2 = zza(this.zzh, zzi2.zzh);
        if (zza2 != 0) {
            return zza2;
        }
        switch (this.zzh) {
            case 1:
                long j = this.zzc;
                long j2 = zzi2.zzc;
                if (j < j2) {
                    return -1;
                }
                return j != j2 ? 1 : 0;
            case 2:
                boolean z = this.zzd;
                if (z != zzi2.zzd) {
                    return z ? 1 : -1;
                }
                return 0;
            case 3:
                return Double.compare(this.zze, zzi2.zze);
            case 4:
                String str = this.zzf;
                String str2 = zzi2.zzf;
                if (str == str2) {
                    return 0;
                }
                if (str == null) {
                    return -1;
                }
                if (str2 == null) {
                    return 1;
                }
                return str.compareTo(str2);
            case 5:
                if (this.zzg == zzi2.zzg) {
                    return 0;
                }
                if (this.zzg == null) {
                    return -1;
                }
                if (zzi2.zzg == null) {
                    return 1;
                }
                for (int i = 0; i < Math.min(this.zzg.length, zzi2.zzg.length); i++) {
                    int i2 = this.zzg[i] - zzi2.zzg[i];
                    if (i2 != 0) {
                        return i2;
                    }
                }
                return zza(this.zzg.length, zzi2.zzg.length);
            default:
                throw new AssertionError("Invalid enum value: " + this.zzh);
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzi)) {
            return false;
        }
        zzi zzi2 = (zzi) obj;
        if (!zzn.zza(this.zza, zzi2.zza) || this.zzh != zzi2.zzh || this.zzb != zzi2.zzb) {
            return false;
        }
        switch (this.zzh) {
            case 1:
                return this.zzc == zzi2.zzc;
            case 2:
                return this.zzd == zzi2.zzd;
            case 3:
                return this.zze == zzi2.zze;
            case 4:
                return zzn.zza(this.zzf, zzi2.zzf);
            case 5:
                return Arrays.equals(this.zzg, zzi2.zzg);
            default:
                throw new AssertionError("Invalid enum value: " + this.zzh);
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Flag(");
        sb.append(this.zza);
        sb.append(", ");
        switch (this.zzh) {
            case 1:
                sb.append(this.zzc);
                break;
            case 2:
                sb.append(this.zzd);
                break;
            case 3:
                sb.append(this.zze);
                break;
            case 4:
                sb.append("'");
                sb.append(this.zzf);
                sb.append("'");
                break;
            case 5:
                if (this.zzg != null) {
                    sb.append("'");
                    sb.append(Base64.encodeToString(this.zzg, 3));
                    sb.append("'");
                    break;
                } else {
                    sb.append("null");
                    break;
                }
            default:
                String str = this.zza;
                throw new AssertionError(new StringBuilder(String.valueOf(str).length() + 27).append("Invalid type: ").append(str).append(", ").append(this.zzh).toString());
        }
        sb.append(", ");
        sb.append(this.zzh);
        sb.append(", ");
        sb.append(this.zzb);
        sb.append(")");
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zza2 = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 2, this.zza, false);
        zzbgo.zza(parcel, 3, this.zzc);
        zzbgo.zza(parcel, 4, this.zzd);
        zzbgo.zza(parcel, 5, this.zze);
        zzbgo.zza(parcel, 6, this.zzf, false);
        zzbgo.zza(parcel, 7, this.zzg, false);
        zzbgo.zza(parcel, 8, this.zzh);
        zzbgo.zza(parcel, 9, this.zzb);
        zzbgo.zza(parcel, zza2);
    }
}
