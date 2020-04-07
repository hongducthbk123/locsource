package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.support.p000v4.util.ArraySet;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzbq;

public class zzah extends zzo {
    private final ArraySet<zzh<?>> zze = new ArraySet<>();
    private zzbm zzf;

    private zzah(zzcf zzcf) {
        super(zzcf);
        this.zzd.zza("ConnectionlessLifecycleHelper", (LifecycleCallback) this);
    }

    public static void zza(Activity activity, zzbm zzbm, zzh<?> zzh) {
        zzcf zza = zza(activity);
        zzah zzah = (zzah) zza.zza("ConnectionlessLifecycleHelper", zzah.class);
        if (zzah == null) {
            zzah = new zzah(zza);
        }
        zzah.zzf = zzbm;
        zzbq.zza(zzh, (Object) "ApiKey cannot be null");
        zzah.zze.add(zzh);
        zzbm.zza(zzah);
    }

    private final void zzi() {
        if (!this.zze.isEmpty()) {
            this.zzf.zza(this);
        }
    }

    public final void zza() {
        super.zza();
        zzi();
    }

    /* access modifiers changed from: protected */
    public final void zza(ConnectionResult connectionResult, int i) {
        this.zzf.zzb(connectionResult, i);
    }

    public final void zzb() {
        super.zzb();
        this.zzf.zzb(this);
    }

    /* access modifiers changed from: protected */
    public final void zzc() {
        this.zzf.zzd();
    }

    public final void zze() {
        super.zze();
        zzi();
    }

    /* access modifiers changed from: 0000 */
    public final ArraySet<zzh<?>> zzf() {
        return this.zze;
    }
}
