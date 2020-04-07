package com.google.android.gms.auth;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.zzayl;
import com.google.android.gms.internal.zzez;
import java.io.IOException;

final class zzi implements zzj<Boolean> {
    private /* synthetic */ String zza;

    zzi(String str) {
        this.zza = str;
    }

    public final /* synthetic */ Object zza(IBinder iBinder) throws RemoteException, IOException, GoogleAuthException {
        Bundle bundle = (Bundle) zzd.zzb(zzez.zza(iBinder).zza(this.zza));
        String string = bundle.getString("Error");
        Intent intent = (Intent) bundle.getParcelable("userRecoveryIntent");
        zzayl zza2 = zzayl.zza(string);
        if (zzayl.SUCCESS.equals(zza2)) {
            return Boolean.valueOf(true);
        }
        if (zzayl.zza(zza2)) {
            String valueOf = String.valueOf(zza2);
            zzd.zze.zzd("GoogleAuthUtil", new StringBuilder(String.valueOf(valueOf).length() + 31).append("isUserRecoverableError status: ").append(valueOf).toString());
            throw new UserRecoverableAuthException(string, intent);
        }
        throw new GoogleAuthException(string);
    }
}
