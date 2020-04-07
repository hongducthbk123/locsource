package com.google.android.gms.auth;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzbgm;

@Hide
public final class zzb implements Creator<AccountChangeEventsRequest> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        Account account = null;
        int zza = zzbgm.zza(parcel);
        int i = 0;
        int i2 = 0;
        String str = null;
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
                    str = zzbgm.zzq(parcel, readInt);
                    break;
                case 4:
                    account = (Account) zzbgm.zza(parcel, readInt, Account.CREATOR);
                    break;
                default:
                    zzbgm.zzb(parcel, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel, zza);
        return new AccountChangeEventsRequest(i2, i, str, account);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new AccountChangeEventsRequest[i];
    }
}
