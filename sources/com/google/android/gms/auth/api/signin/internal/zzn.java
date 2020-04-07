package com.google.android.gms.auth.api.signin.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzbgm;

@Hide
public final class zzn implements Creator<zzo> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        int zza = zzbgm.zza(parcel);
        Bundle bundle = null;
        int i2 = 0;
        while (parcel.dataPosition() < zza) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i2 = zzbgm.zzg(parcel, readInt);
                    break;
                case 2:
                    i = zzbgm.zzg(parcel, readInt);
                    break;
                case 3:
                    bundle = zzbgm.zzs(parcel, readInt);
                    break;
                default:
                    zzbgm.zzb(parcel, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel, zza);
        return new zzo(i2, i, bundle);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzo[i];
    }
}
