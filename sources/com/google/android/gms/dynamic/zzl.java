package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;

public abstract class zzl extends zzew implements zzk {
    public zzl() {
        attachInterface(this, "com.google.android.gms.dynamic.IFragmentWrapper");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        IObjectWrapper iObjectWrapper = null;
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 2:
                IObjectWrapper zza = zza();
                parcel2.writeNoException();
                zzex.zza(parcel2, (IInterface) zza);
                break;
            case 3:
                Bundle zzb = zzb();
                parcel2.writeNoException();
                zzex.zzb(parcel2, zzb);
                break;
            case 4:
                int zzc = zzc();
                parcel2.writeNoException();
                parcel2.writeInt(zzc);
                break;
            case 5:
                zzk zzd = zzd();
                parcel2.writeNoException();
                zzex.zza(parcel2, (IInterface) zzd);
                break;
            case 6:
                IObjectWrapper zze = zze();
                parcel2.writeNoException();
                zzex.zza(parcel2, (IInterface) zze);
                break;
            case 7:
                boolean zzf = zzf();
                parcel2.writeNoException();
                zzex.zza(parcel2, zzf);
                break;
            case 8:
                String zzg = zzg();
                parcel2.writeNoException();
                parcel2.writeString(zzg);
                break;
            case 9:
                zzk zzh = zzh();
                parcel2.writeNoException();
                zzex.zza(parcel2, (IInterface) zzh);
                break;
            case 10:
                int zzi = zzi();
                parcel2.writeNoException();
                parcel2.writeInt(zzi);
                break;
            case 11:
                boolean zzj = zzj();
                parcel2.writeNoException();
                zzex.zza(parcel2, zzj);
                break;
            case 12:
                IObjectWrapper zzk = zzk();
                parcel2.writeNoException();
                zzex.zza(parcel2, (IInterface) zzk);
                break;
            case 13:
                boolean zzl = zzl();
                parcel2.writeNoException();
                zzex.zza(parcel2, zzl);
                break;
            case 14:
                boolean zzm = zzm();
                parcel2.writeNoException();
                zzex.zza(parcel2, zzm);
                break;
            case 15:
                boolean zzn = zzn();
                parcel2.writeNoException();
                zzex.zza(parcel2, zzn);
                break;
            case 16:
                boolean zzo = zzo();
                parcel2.writeNoException();
                zzex.zza(parcel2, zzo);
                break;
            case 17:
                boolean zzp = zzp();
                parcel2.writeNoException();
                zzex.zza(parcel2, zzp);
                break;
            case 18:
                boolean zzq = zzq();
                parcel2.writeNoException();
                zzex.zza(parcel2, zzq);
                break;
            case 19:
                boolean zzr = zzr();
                parcel2.writeNoException();
                zzex.zza(parcel2, zzr);
                break;
            case 20:
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
                    iObjectWrapper = queryLocalInterface instanceof IObjectWrapper ? (IObjectWrapper) queryLocalInterface : new zzm(readStrongBinder);
                }
                zza(iObjectWrapper);
                parcel2.writeNoException();
                break;
            case 21:
                zza(zzex.zza(parcel));
                parcel2.writeNoException();
                break;
            case 22:
                zzb(zzex.zza(parcel));
                parcel2.writeNoException();
                break;
            case 23:
                zzc(zzex.zza(parcel));
                parcel2.writeNoException();
                break;
            case 24:
                zzd(zzex.zza(parcel));
                parcel2.writeNoException();
                break;
            case 25:
                zza((Intent) zzex.zza(parcel, Intent.CREATOR));
                parcel2.writeNoException();
                break;
            case 26:
                zza((Intent) zzex.zza(parcel, Intent.CREATOR), parcel.readInt());
                parcel2.writeNoException();
                break;
            case 27:
                IBinder readStrongBinder2 = parcel.readStrongBinder();
                if (readStrongBinder2 != null) {
                    IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
                    iObjectWrapper = queryLocalInterface2 instanceof IObjectWrapper ? (IObjectWrapper) queryLocalInterface2 : new zzm(readStrongBinder2);
                }
                zzb(iObjectWrapper);
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
