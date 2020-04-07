package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class SignInConfiguration extends zzbgl implements ReflectedParcelable {
    public static final Creator<SignInConfiguration> CREATOR = new zzy();
    private final String zza;
    private GoogleSignInOptions zzb;

    public SignInConfiguration(String str, GoogleSignInOptions googleSignInOptions) {
        this.zza = zzbq.zza(str);
        this.zzb = googleSignInOptions;
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            SignInConfiguration signInConfiguration = (SignInConfiguration) obj;
            if (!this.zza.equals(signInConfiguration.zza)) {
                return false;
            }
            if (this.zzb == null) {
                if (signInConfiguration.zzb != null) {
                    return false;
                }
            } else if (!this.zzb.equals(signInConfiguration.zzb)) {
                return false;
            }
            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public final int hashCode() {
        return new zzq().zza((Object) this.zza).zza((Object) this.zzb).zza();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zza2 = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 2, this.zza, false);
        zzbgo.zza(parcel, 5, (Parcelable) this.zzb, i, false);
        zzbgo.zza(parcel, zza2);
    }

    public final GoogleSignInOptions zza() {
        return this.zzb;
    }
}
