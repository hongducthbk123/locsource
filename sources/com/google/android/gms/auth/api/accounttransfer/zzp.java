package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;
import java.util.List;

@Hide
public final class zzp implements Creator<zzo> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        ArrayList arrayList = null;
        int zza = zzbgm.zza(parcel);
        int i = 0;
        List list = null;
        List list2 = null;
        List list3 = null;
        List list4 = null;
        while (parcel.dataPosition() < zza) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = zzbgm.zzg(parcel, readInt);
                    break;
                case 2:
                    list4 = zzbgm.zzac(parcel, readInt);
                    break;
                case 3:
                    list3 = zzbgm.zzac(parcel, readInt);
                    break;
                case 4:
                    list2 = zzbgm.zzac(parcel, readInt);
                    break;
                case 5:
                    list = zzbgm.zzac(parcel, readInt);
                    break;
                case 6:
                    arrayList = zzbgm.zzac(parcel, readInt);
                    break;
                default:
                    zzbgm.zzb(parcel, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel, zza);
        return new zzo(i, list4, list3, list2, list, arrayList);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzo[i];
    }
}
