package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzfr extends zzev implements zzfp {
    zzfr(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
    }

    public final String zza() throws RemoteException {
        Parcel zza = zza(1, mo12203a_());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    public final boolean zza(boolean z) throws RemoteException {
        Parcel a_ = mo12203a_();
        zzex.zza(a_, true);
        Parcel zza = zza(2, a_);
        boolean zza2 = zzex.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final boolean zzb() throws RemoteException {
        Parcel zza = zza(6, mo12203a_());
        boolean zza2 = zzex.zza(zza);
        zza.recycle();
        return zza2;
    }
}
