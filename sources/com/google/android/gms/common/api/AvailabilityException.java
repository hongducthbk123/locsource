package com.google.android.gms.common.api;

import android.support.p000v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.internal.zzh;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import java.util.ArrayList;

public class AvailabilityException extends Exception {
    private final ArrayMap<zzh<?>, ConnectionResult> zza;

    @Hide
    public AvailabilityException(ArrayMap<zzh<?>, ConnectionResult> arrayMap) {
        this.zza = arrayMap;
    }

    public ConnectionResult getConnectionResult(GoogleApi<? extends ApiOptions> googleApi) {
        zzh zzc = googleApi.zzc();
        zzbq.zzb(this.zza.get(zzc) != null, "The given API was not part of the availability request.");
        return (ConnectionResult) this.zza.get(zzc);
    }

    public String getMessage() {
        ArrayList arrayList = new ArrayList();
        boolean z = true;
        for (zzh zzh : this.zza.keySet()) {
            ConnectionResult connectionResult = (ConnectionResult) this.zza.get(zzh);
            if (connectionResult.isSuccess()) {
                z = false;
            }
            String zza2 = zzh.zza();
            String valueOf = String.valueOf(connectionResult);
            arrayList.add(new StringBuilder(String.valueOf(zza2).length() + 2 + String.valueOf(valueOf).length()).append(zza2).append(": ").append(valueOf).toString());
        }
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("None of the queried APIs are available. ");
        } else {
            sb.append("Some of the queried APIs are unavailable. ");
        }
        sb.append(TextUtils.join("; ", arrayList));
        return sb.toString();
    }

    @Hide
    public final ArrayMap<zzh<?>, ConnectionResult> zza() {
        return this.zza;
    }
}
