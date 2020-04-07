package com.google.android.gms.clearcut;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class zzc extends zzbgl {
    public static final Creator<zzc> CREATOR = new zzd();
    private boolean zza;
    private long zzb;
    private long zzc;

    public zzc(boolean z, long j, long j2) {
        this.zza = z;
        this.zzb = j;
        this.zzc = j2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzc)) {
            return false;
        }
        zzc zzc2 = (zzc) obj;
        return this.zza == zzc2.zza && this.zzb == zzc2.zzb && this.zzc == zzc2.zzc;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Boolean.valueOf(this.zza), Long.valueOf(this.zzb), Long.valueOf(this.zzc)});
    }

    public final String toString() {
        return "CollectForDebugParcelable[skipPersistentStorage: " + this.zza + ",collectForDebugStartTimeMillis: " + this.zzb + ",collectForDebugExpiryTimeMillis: " + this.zzc + "]";
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zza2 = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 1, this.zza);
        zzbgo.zza(parcel, 2, this.zzc);
        zzbgo.zza(parcel, 3, this.zzb);
        zzbgo.zza(parcel, zza2);
    }
}
