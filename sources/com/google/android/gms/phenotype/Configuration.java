package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

@KeepForSdk
public class Configuration extends zzbgl implements Comparable<Configuration> {
    @KeepForSdk
    public static final Creator<Configuration> CREATOR = new zzc();
    private int zza;
    private zzi[] zzb;
    private String[] zzc;
    private Map<String, zzi> zzd = new TreeMap();

    public Configuration(int i, zzi[] zziArr, String[] strArr) {
        this.zza = i;
        this.zzb = zziArr;
        for (zzi zzi : zziArr) {
            this.zzd.put(zzi.zza, zzi);
        }
        this.zzc = strArr;
        if (this.zzc != null) {
            Arrays.sort(this.zzc);
        }
    }

    public /* synthetic */ int compareTo(Object obj) {
        return this.zza - ((Configuration) obj).zza;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Configuration)) {
            return false;
        }
        Configuration configuration = (Configuration) obj;
        return this.zza == configuration.zza && zzn.zza(this.zzd, configuration.zzd) && Arrays.equals(this.zzc, configuration.zzc);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Configuration(");
        sb.append(this.zza);
        sb.append(", ");
        sb.append("(");
        for (zzi append : this.zzd.values()) {
            sb.append(append);
            sb.append(", ");
        }
        sb.append(")");
        sb.append(", ");
        sb.append("(");
        if (this.zzc != null) {
            for (String append2 : this.zzc) {
                sb.append(append2);
                sb.append(", ");
            }
        } else {
            sb.append("null");
        }
        sb.append(")");
        sb.append(")");
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zza2 = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 2, this.zza);
        zzbgo.zza(parcel, 3, (T[]) this.zzb, i, false);
        zzbgo.zza(parcel, 4, this.zzc, false);
        zzbgo.zza(parcel, zza2);
    }
}
