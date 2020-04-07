package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class IdToken extends zzbgl implements ReflectedParcelable {
    public static final Creator<IdToken> CREATOR = new zzi();
    @NonNull
    private final String zza;
    @NonNull
    private final String zzb;

    public IdToken(@NonNull String str, @NonNull String str2) {
        boolean z = true;
        zzbq.zzb(!TextUtils.isEmpty(str), "account type string cannot be null or empty");
        if (TextUtils.isEmpty(str2)) {
            z = false;
        }
        zzbq.zzb(z, "id token string cannot be null or empty");
        this.zza = str;
        this.zzb = str2;
    }

    @NonNull
    public final String getAccountType() {
        return this.zza;
    }

    @NonNull
    public final String getIdToken() {
        return this.zzb;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zza2 = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 1, getAccountType(), false);
        zzbgo.zza(parcel, 2, getIdToken(), false);
        zzbgo.zza(parcel, zza2);
    }
}
