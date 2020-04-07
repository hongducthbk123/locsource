package com.google.android.gms.auth;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.zzayl;
import com.google.android.gms.internal.zzez;
import java.io.IOException;

final class zze implements zzj<TokenData> {
    private /* synthetic */ Account zza;
    private /* synthetic */ String zzb;
    private /* synthetic */ Bundle zzc;

    zze(Account account, String str, Bundle bundle) {
        this.zza = account;
        this.zzb = str;
        this.zzc = bundle;
    }

    public final /* synthetic */ Object zza(IBinder iBinder) throws RemoteException, IOException, GoogleAuthException {
        Bundle bundle = (Bundle) zzd.zzb(zzez.zza(iBinder).zza(this.zza, this.zzb, this.zzc));
        TokenData zza2 = TokenData.zza(bundle, "tokenDetails");
        if (zza2 != null) {
            return zza2;
        }
        String string = bundle.getString("Error");
        Intent intent = (Intent) bundle.getParcelable("userRecoveryIntent");
        zzayl zza3 = zzayl.zza(string);
        if (zzayl.zza(zza3)) {
            String valueOf = String.valueOf(zza3);
            zzd.zze.zzd("GoogleAuthUtil", new StringBuilder(String.valueOf(valueOf).length() + 31).append("isUserRecoverableError status: ").append(valueOf).toString());
            throw new UserRecoverableAuthException(string, intent);
        }
        if (zzayl.NETWORK_ERROR.equals(zza3) || zzayl.SERVICE_UNAVAILABLE.equals(zza3)) {
            throw new IOException(string);
        }
        throw new GoogleAuthException(string);
    }
}
