package com.google.android.gms.clearcut;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzbgm;

@Hide
public final class zzd implements Creator<zzc> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        long j = 0;
        int zza = zzbgm.zza(parcel);
        boolean z = false;
        long j2 = 0;
        while (parcel.dataPosition() < zza) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    z = zzbgm.zzc(parcel, readInt);
                    break;
                case 2:
                    j = zzbgm.zzi(parcel, readInt);
                    break;
                case 3:
                    j2 = zzbgm.zzi(parcel, readInt);
                    break;
                default:
                    zzbgm.zzb(parcel, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel, zza);
        return new zzc(z, j2, j);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzc[i];
    }
}
