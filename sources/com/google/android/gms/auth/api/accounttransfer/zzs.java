package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzbgm;
import com.google.android.gms.internal.zzbgn;
import java.util.HashSet;

@Hide
public final class zzs implements Creator<zzr> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        String str = null;
        int zza = zzbgm.zza(parcel);
        HashSet hashSet = new HashSet();
        int i = 0;
        String str2 = null;
        zzt zzt = null;
        while (parcel.dataPosition() < zza) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = zzbgm.zzg(parcel, readInt);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case 2:
                    zzt zzt2 = (zzt) zzbgm.zza(parcel, readInt, zzt.CREATOR);
                    hashSet.add(Integer.valueOf(2));
                    zzt = zzt2;
                    break;
                case 3:
                    str2 = zzbgm.zzq(parcel, readInt);
                    hashSet.add(Integer.valueOf(3));
                    break;
                case 4:
                    str = zzbgm.zzq(parcel, readInt);
                    hashSet.add(Integer.valueOf(4));
                    break;
                default:
                    zzbgm.zzb(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == zza) {
            return new zzr(hashSet, i, zzt, str2, str);
        }
        throw new zzbgn("Overread allowed size end=" + zza, parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzr[i];
    }
}
