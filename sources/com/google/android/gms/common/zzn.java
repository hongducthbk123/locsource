package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzau;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

@Hide
public final class zzn extends zzbgl {
    public static final Creator<zzn> CREATOR = new zzo();
    private final String zza;
    private final zzh zzb;
    private final boolean zzc;

    zzn(String str, IBinder iBinder, boolean z) {
        this.zza = str;
        this.zzb = zza(iBinder);
        this.zzc = z;
    }

    zzn(String str, zzh zzh, boolean z) {
        this.zza = str;
        this.zzb = zzh;
        this.zzc = z;
    }

    private static zzh zza(IBinder iBinder) {
        zzi zzi;
        if (iBinder == null) {
            return null;
        }
        try {
            IObjectWrapper zzb2 = zzau.zza(iBinder).zzb();
            byte[] bArr = zzb2 == null ? null : (byte[]) com.google.android.gms.dynamic.zzn.zza(zzb2);
            if (bArr != null) {
                zzi = new zzi(bArr);
            } else {
                Log.e("GoogleCertificatesQuery", "Could not unwrap certificate");
                zzi = null;
            }
            return zzi;
        } catch (RemoteException e) {
            Log.e("GoogleCertificatesQuery", "Could not unwrap certificate", e);
            return null;
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        IBinder asBinder;
        int zza2 = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 1, this.zza, false);
        if (this.zzb == null) {
            Log.w("GoogleCertificatesQuery", "certificate binder is null");
            asBinder = null;
        } else {
            asBinder = this.zzb.asBinder();
        }
        zzbgo.zza(parcel, 2, asBinder, false);
        zzbgo.zza(parcel, 3, this.zzc);
        zzbgo.zza(parcel, zza2);
    }
}
