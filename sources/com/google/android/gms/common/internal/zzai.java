package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.stats.zza;
import com.google.android.vending.expansion.downloader.Constants;
import java.util.HashMap;

final class zzai extends zzag implements Callback {
    /* access modifiers changed from: private */
    public final HashMap<zzah, zzaj> zza = new HashMap<>();
    /* access modifiers changed from: private */
    public final Context zzb;
    /* access modifiers changed from: private */
    public final Handler zzc;
    /* access modifiers changed from: private */
    public final zza zzd;
    private final long zze;
    /* access modifiers changed from: private */
    public final long zzf;

    zzai(Context context) {
        this.zzb = context.getApplicationContext();
        this.zzc = new Handler(context.getMainLooper(), this);
        this.zzd = zza.zza();
        this.zze = Constants.ACTIVE_THREAD_WATCHDOG;
        this.zzf = 300000;
    }

    public final boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                synchronized (this.zza) {
                    zzah zzah = (zzah) message.obj;
                    zzaj zzaj = (zzaj) this.zza.get(zzah);
                    if (zzaj != null && zzaj.zzc()) {
                        if (zzaj.zza()) {
                            zzaj.zzb("GmsClientSupervisor");
                        }
                        this.zza.remove(zzah);
                    }
                }
                return true;
            case 1:
                synchronized (this.zza) {
                    zzah zzah2 = (zzah) message.obj;
                    zzaj zzaj2 = (zzaj) this.zza.get(zzah2);
                    if (zzaj2 != null && zzaj2.zzb() == 3) {
                        String valueOf = String.valueOf(zzah2);
                        Log.wtf("GmsClientSupervisor", new StringBuilder(String.valueOf(valueOf).length() + 47).append("Timeout waiting for ServiceConnection callback ").append(valueOf).toString(), new Exception());
                        ComponentName zze2 = zzaj2.zze();
                        if (zze2 == null) {
                            zze2 = zzah2.zzb();
                        }
                        zzaj2.onServiceDisconnected(zze2 == null ? new ComponentName(zzah2.zza(), "unknown") : zze2);
                    }
                }
                return true;
            default:
                return false;
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzah zzah, ServiceConnection serviceConnection, String str) {
        boolean zza2;
        zzbq.zza(serviceConnection, (Object) "ServiceConnection must not be null");
        synchronized (this.zza) {
            zzaj zzaj = (zzaj) this.zza.get(zzah);
            if (zzaj != null) {
                this.zzc.removeMessages(0, zzah);
                if (!zzaj.zza(serviceConnection)) {
                    zzaj.zza(serviceConnection, str);
                    switch (zzaj.zzb()) {
                        case 1:
                            serviceConnection.onServiceConnected(zzaj.zze(), zzaj.zzd());
                            break;
                        case 2:
                            zzaj.zza(str);
                            break;
                    }
                } else {
                    String valueOf = String.valueOf(zzah);
                    throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 81).append("Trying to bind a GmsServiceConnection that was already connected before.  config=").append(valueOf).toString());
                }
            } else {
                zzaj = new zzaj(this, zzah);
                zzaj.zza(serviceConnection, str);
                zzaj.zza(str);
                this.zza.put(zzah, zzaj);
            }
            zza2 = zzaj.zza();
        }
        return zza2;
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzah zzah, ServiceConnection serviceConnection, String str) {
        zzbq.zza(serviceConnection, (Object) "ServiceConnection must not be null");
        synchronized (this.zza) {
            zzaj zzaj = (zzaj) this.zza.get(zzah);
            if (zzaj == null) {
                String valueOf = String.valueOf(zzah);
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 50).append("Nonexistent connection status for service config: ").append(valueOf).toString());
            } else if (!zzaj.zza(serviceConnection)) {
                String valueOf2 = String.valueOf(zzah);
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf2).length() + 76).append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=").append(valueOf2).toString());
            } else {
                zzaj.zzb(serviceConnection, str);
                if (zzaj.zzc()) {
                    this.zzc.sendMessageDelayed(this.zzc.obtainMessage(0, zzah), this.zze);
                }
            }
        }
    }
}
