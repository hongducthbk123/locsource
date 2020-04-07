package com.google.android.gms.common.internal;

import android.util.Log;

@Hide
public abstract class zzi<TListener> {
    private TListener zza;
    private boolean zzb = false;
    private /* synthetic */ zzd zzc;

    public zzi(zzd zzd, TListener tlistener) {
        this.zzc = zzd;
        this.zza = tlistener;
    }

    /* access modifiers changed from: protected */
    public abstract void zza(TListener tlistener);

    /* access modifiers changed from: protected */
    public abstract void zzb();

    public final void zzc() {
        TListener tlistener;
        synchronized (this) {
            tlistener = this.zza;
            if (this.zzb) {
                String valueOf = String.valueOf(this);
                Log.w("GmsClient", new StringBuilder(String.valueOf(valueOf).length() + 47).append("Callback proxy ").append(valueOf).append(" being reused. This is not safe.").toString());
            }
        }
        if (tlistener != null) {
            try {
                zza(tlistener);
            } catch (RuntimeException e) {
                zzb();
                throw e;
            }
        } else {
            zzb();
        }
        synchronized (this) {
            this.zzb = true;
        }
        zzd();
    }

    public final void zzd() {
        zze();
        synchronized (this.zzc.zzr) {
            this.zzc.zzr.remove(this);
        }
    }

    public final void zze() {
        synchronized (this) {
            this.zza = null;
        }
    }
}
