package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;

@Hide
public final class zzbhu implements Creator<zzbhx> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        zzbhq zzbhq = null;
        int zza = zzbgm.zza(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < zza) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = zzbgm.zzg(parcel, readInt);
                    break;
                case 2:
                    str = zzbgm.zzq(parcel, readInt);
                    break;
                case 3:
                    zzbhq = (zzbhq) zzbgm.zza(parcel, readInt, (Creator<T>) zzbhq.CREATOR);
                    break;
                default:
                    zzbgm.zzb(parcel, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel, zza);
        return new zzbhx(i, str, zzbhq);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzbhx[i];
    }
}
