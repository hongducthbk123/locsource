package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

final class zzbf extends Handler {
    private /* synthetic */ zzba zza;

    zzbf(zzba zzba, Looper looper) {
        this.zza = zzba;
        super(looper);
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                this.zza.zzk();
                return;
            case 2:
                this.zza.zzj();
                return;
            default:
                Log.w("GoogleApiClientImpl", "Unknown message id: " + message.what);
                return;
        }
    }
}
