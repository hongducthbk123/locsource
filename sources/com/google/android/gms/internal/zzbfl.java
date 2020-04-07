package com.google.android.gms.internal;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.clearcut.zze;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.common.internal.Hide;

final class zzbfl extends zzm<Status, zzbfn> {
    private final zze zza;

    zzbfl(zze zze, GoogleApiClient googleApiClient) {
        super(ClearcutLogger.zza, googleApiClient);
        this.zza = zze;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result zza(Status status) {
        return status;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(zzb zzb) throws RemoteException {
        zzbfn zzbfn = (zzbfn) zzb;
        zzbfm zzbfm = new zzbfm(this);
        try {
            zze zze = this.zza;
            if (zze.zzd != null && zze.zzc.zzc.length == 0) {
                zze.zzc.zzc = zze.zzd.zza();
            }
            if (zze.zze != null && zze.zzc.zze.length == 0) {
                zze.zzc.zze = zze.zze.zza();
            }
            zze.zzb = zzfls.zza((zzfls) zze.zzc);
            ((zzbfr) zzbfn.zzaf()).zza(zzbfm, this.zza);
        } catch (RuntimeException e) {
            Log.e("ClearcutLoggerApiImpl", "derived ClearcutLogger.MessageProducer ", e);
            zzc(new Status(10, "MessageProducer"));
        }
    }

    @Hide
    public final /* bridge */ /* synthetic */ void zza(Object obj) {
        super.zza((Status) obj);
    }
}
