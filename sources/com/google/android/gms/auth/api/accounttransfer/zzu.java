package com.google.android.gms.auth.api.accounttransfer;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzbgm;
import com.google.android.gms.internal.zzbgn;
import java.util.HashSet;

@Hide
public final class zzu implements Creator<zzt> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        DeviceMetaData deviceMetaData = null;
        int zza = zzbgm.zza(parcel);
        HashSet hashSet = new HashSet();
        PendingIntent pendingIntent = null;
        byte[] bArr = null;
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < zza) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i2 = zzbgm.zzg(parcel, readInt);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case 2:
                    str = zzbgm.zzq(parcel, readInt);
                    hashSet.add(Integer.valueOf(2));
                    break;
                case 3:
                    i = zzbgm.zzg(parcel, readInt);
                    hashSet.add(Integer.valueOf(3));
                    break;
                case 4:
                    bArr = zzbgm.zzt(parcel, readInt);
                    hashSet.add(Integer.valueOf(4));
                    break;
                case 5:
                    PendingIntent pendingIntent2 = (PendingIntent) zzbgm.zza(parcel, readInt, PendingIntent.CREATOR);
                    hashSet.add(Integer.valueOf(5));
                    pendingIntent = pendingIntent2;
                    break;
                case 6:
                    DeviceMetaData deviceMetaData2 = (DeviceMetaData) zzbgm.zza(parcel, readInt, DeviceMetaData.CREATOR);
                    hashSet.add(Integer.valueOf(6));
                    deviceMetaData = deviceMetaData2;
                    break;
                default:
                    zzbgm.zzb(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == zza) {
            return new zzt(hashSet, i2, str, i, bArr, pendingIntent, deviceMetaData);
        }
        throw new zzbgn("Overread allowed size end=" + zza, parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzt[i];
    }
}
