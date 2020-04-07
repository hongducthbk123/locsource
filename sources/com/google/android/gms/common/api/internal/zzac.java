package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.p000v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.Collections;
import java.util.Map;

final class zzac implements OnCompleteListener<Map<zzh<?>, String>> {
    private /* synthetic */ zzaa zza;

    private zzac(zzaa zzaa) {
        this.zza = zzaa;
    }

    public final void onComplete(@NonNull Task<Map<zzh<?>, String>> task) {
        this.zza.zzf.lock();
        try {
            if (this.zza.zzn) {
                if (task.isSuccessful()) {
                    this.zza.zzo = new ArrayMap(this.zza.zza.size());
                    for (zzz zzc : this.zza.zza.values()) {
                        this.zza.zzo.put(zzc.zzc(), ConnectionResult.zza);
                    }
                } else if (task.getException() instanceof AvailabilityException) {
                    AvailabilityException availabilityException = (AvailabilityException) task.getException();
                    if (this.zza.zzl) {
                        this.zza.zzo = new ArrayMap(this.zza.zza.size());
                        for (zzz zzz : this.zza.zza.values()) {
                            zzh zzc2 = zzz.zzc();
                            ConnectionResult connectionResult = availabilityException.getConnectionResult(zzz);
                            if (this.zza.zza(zzz, connectionResult)) {
                                this.zza.zzo.put(zzc2, new ConnectionResult(16));
                            } else {
                                this.zza.zzo.put(zzc2, connectionResult);
                            }
                        }
                    } else {
                        this.zza.zzo = availabilityException.zza();
                    }
                    this.zza.zzr = this.zza.zzk();
                } else {
                    Log.e("ConnectionlessGAC", "Unexpected availability exception", task.getException());
                    this.zza.zzo = Collections.emptyMap();
                    this.zza.zzr = new ConnectionResult(8);
                }
                if (this.zza.zzp != null) {
                    this.zza.zzo.putAll(this.zza.zzp);
                    this.zza.zzr = this.zza.zzk();
                }
                if (this.zza.zzr == null) {
                    this.zza.zzi();
                    this.zza.zzj();
                } else {
                    this.zza.zzn = false;
                    this.zza.zze.zza(this.zza.zzr);
                }
                this.zza.zzi.signalAll();
                this.zza.zzf.unlock();
            }
        } finally {
            this.zza.zzf.unlock();
        }
    }
}
