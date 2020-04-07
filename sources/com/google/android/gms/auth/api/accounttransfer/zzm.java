package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzbgm;
import com.google.android.gms.internal.zzbgn;
import java.util.ArrayList;
import java.util.HashSet;

@Hide
public final class zzm implements Creator<zzl> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        zzo zzo = null;
        int i = 0;
        int zza = zzbgm.zza(parcel);
        HashSet hashSet = new HashSet();
        ArrayList arrayList = null;
        int i2 = 0;
        while (parcel.dataPosition() < zza) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i2 = zzbgm.zzg(parcel, readInt);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case 2:
                    arrayList = zzbgm.zzc(parcel, readInt, zzr.CREATOR);
                    hashSet.add(Integer.valueOf(2));
                    break;
                case 3:
                    i = zzbgm.zzg(parcel, readInt);
                    hashSet.add(Integer.valueOf(3));
                    break;
                case 4:
                    zzo zzo2 = (zzo) zzbgm.zza(parcel, readInt, zzo.CREATOR);
                    hashSet.add(Integer.valueOf(4));
                    zzo = zzo2;
                    break;
                default:
                    zzbgm.zzb(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == zza) {
            return new zzl(hashSet, i2, arrayList, i, zzo);
        }
        throw new zzbgn("Overread allowed size end=" + zza, parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzl[i];
    }
}
