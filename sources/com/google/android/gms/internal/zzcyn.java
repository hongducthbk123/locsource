package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;

@Hide
public final class zzcyn implements Creator<zzcym> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        int zza = zzbgm.zza(parcel);
        Intent intent = null;
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
                    intent = (Intent) zzbgm.zza(parcel, readInt, Intent.CREATOR);
                    break;
                default:
                    zzbgm.zzb(parcel, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel, zza);
        return new zzcym(i2, i, intent);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzcym[i];
    }
}
