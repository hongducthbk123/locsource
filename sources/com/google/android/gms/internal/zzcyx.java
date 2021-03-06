package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbt;

@Hide
public final class zzcyx implements Creator<zzcyw> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zza = zzbgm.zza(parcel);
        ConnectionResult connectionResult = null;
        int i = 0;
        zzbt zzbt = null;
        while (parcel.dataPosition() < zza) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = zzbgm.zzg(parcel, readInt);
                    break;
                case 2:
                    connectionResult = (ConnectionResult) zzbgm.zza(parcel, readInt, ConnectionResult.CREATOR);
                    break;
                case 3:
                    zzbt = (zzbt) zzbgm.zza(parcel, readInt, zzbt.CREATOR);
                    break;
                default:
                    zzbgm.zzb(parcel, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel, zza);
        return new zzcyw(i, connectionResult, zzbt);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzcyw[i];
    }
}
