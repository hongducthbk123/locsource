package com.google.android.gms.auth.account;

import android.accounts.Account;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;

public abstract class zzb extends zzew implements zza {
    public zzb() {
        attachInterface(this, "com.google.android.gms.auth.account.IWorkAccountCallback");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                zza((Account) zzex.zza(parcel, Account.CREATOR));
                break;
            case 2:
                zza(zzex.zza(parcel));
                break;
            default:
                return false;
        }
        return true;
    }
}
