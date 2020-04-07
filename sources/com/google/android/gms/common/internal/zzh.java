package com.google.android.gms.common.internal;

import android.app.PendingIntent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;

@Hide
final class zzh extends Handler {
    private /* synthetic */ zzd zza;

    public zzh(zzd zzd, Looper looper) {
        this.zza = zzd;
        super(looper);
    }

    private static void zza(Message message) {
        zzi zzi = (zzi) message.obj;
        zzi.zzb();
        zzi.zzd();
    }

    private static boolean zzb(Message message) {
        return message.what == 2 || message.what == 1 || message.what == 7;
    }

    public final void handleMessage(Message message) {
        PendingIntent pendingIntent = null;
        if (this.zza.zzc.get() != message.arg1) {
            if (zzb(message)) {
                zza(message);
            }
        } else if ((message.what == 1 || message.what == 7 || message.what == 4 || message.what == 5) && !this.zza.zzt()) {
            zza(message);
        } else if (message.what == 4) {
            this.zza.zzy = new ConnectionResult(message.arg2);
            if (!this.zza.zzk() || this.zza.zzz) {
                ConnectionResult connectionResult = this.zza.zzy != null ? this.zza.zzy : new ConnectionResult(8);
                this.zza.zzb.zza(connectionResult);
                this.zza.zza(connectionResult);
                return;
            }
            this.zza.zzb(3, null);
        } else if (message.what == 5) {
            ConnectionResult connectionResult2 = this.zza.zzy != null ? this.zza.zzy : new ConnectionResult(8);
            this.zza.zzb.zza(connectionResult2);
            this.zza.zza(connectionResult2);
        } else if (message.what == 3) {
            if (message.obj instanceof PendingIntent) {
                pendingIntent = (PendingIntent) message.obj;
            }
            ConnectionResult connectionResult3 = new ConnectionResult(message.arg2, pendingIntent);
            this.zza.zzb.zza(connectionResult3);
            this.zza.zza(connectionResult3);
        } else if (message.what == 6) {
            this.zza.zzb(5, null);
            if (this.zza.zzu != null) {
                this.zza.zzu.zza(message.arg2);
            }
            this.zza.zza(message.arg2);
            this.zza.zza(5, 1, null);
        } else if (message.what == 2 && !this.zza.zzs()) {
            zza(message);
        } else if (zzb(message)) {
            ((zzi) message.obj).zzc();
        } else {
            Log.wtf("GmsClient", "Don't know how to handle message: " + message.what, new Exception());
        }
    }
}
