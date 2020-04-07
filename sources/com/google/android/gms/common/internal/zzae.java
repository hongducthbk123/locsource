package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzae implements Callback {
    private final zzaf zza;
    private final ArrayList<ConnectionCallbacks> zzb = new ArrayList<>();
    private ArrayList<ConnectionCallbacks> zzc = new ArrayList<>();
    private final ArrayList<OnConnectionFailedListener> zzd = new ArrayList<>();
    private volatile boolean zze = false;
    private final AtomicInteger zzf = new AtomicInteger(0);
    private boolean zzg = false;
    private final Handler zzh;
    private final Object zzi = new Object();

    public zzae(Looper looper, zzaf zzaf) {
        this.zza = zzaf;
        this.zzh = new Handler(looper, this);
    }

    public final boolean handleMessage(Message message) {
        if (message.what == 1) {
            ConnectionCallbacks connectionCallbacks = (ConnectionCallbacks) message.obj;
            synchronized (this.zzi) {
                if (this.zze && this.zza.zzs() && this.zzb.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(this.zza.mo11423q_());
                }
            }
            return true;
        }
        Log.wtf("GmsClientEvents", "Don't know how to handle message: " + message.what, new Exception());
        return false;
    }

    public final void zza() {
        this.zze = false;
        this.zzf.incrementAndGet();
    }

    public final void zza(int i) {
        int i2 = 0;
        zzbq.zza(Looper.myLooper() == this.zzh.getLooper(), (Object) "onUnintentionalDisconnection must only be called on the Handler thread");
        this.zzh.removeMessages(1);
        synchronized (this.zzi) {
            this.zzg = true;
            ArrayList arrayList = new ArrayList(this.zzb);
            int i3 = this.zzf.get();
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            while (i2 < size) {
                Object obj = arrayList2.get(i2);
                i2++;
                ConnectionCallbacks connectionCallbacks = (ConnectionCallbacks) obj;
                if (this.zze && this.zzf.get() == i3) {
                    if (this.zzb.contains(connectionCallbacks)) {
                        connectionCallbacks.onConnectionSuspended(i);
                    }
                }
            }
            this.zzc.clear();
            this.zzg = false;
        }
    }

    public final void zza(Bundle bundle) {
        boolean z = true;
        int i = 0;
        zzbq.zza(Looper.myLooper() == this.zzh.getLooper(), (Object) "onConnectionSuccess must only be called on the Handler thread");
        synchronized (this.zzi) {
            zzbq.zza(!this.zzg);
            this.zzh.removeMessages(1);
            this.zzg = true;
            if (this.zzc.size() != 0) {
                z = false;
            }
            zzbq.zza(z);
            ArrayList arrayList = new ArrayList(this.zzb);
            int i2 = this.zzf.get();
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            while (i < size) {
                Object obj = arrayList2.get(i);
                i++;
                ConnectionCallbacks connectionCallbacks = (ConnectionCallbacks) obj;
                if (this.zze && this.zza.zzs() && this.zzf.get() == i2) {
                    if (!this.zzc.contains(connectionCallbacks)) {
                        connectionCallbacks.onConnected(bundle);
                    }
                }
            }
            this.zzc.clear();
            this.zzg = false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.common.ConnectionResult r8) {
        /*
            r7 = this;
            r1 = 1
            r2 = 0
            android.os.Looper r0 = android.os.Looper.myLooper()
            android.os.Handler r3 = r7.zzh
            android.os.Looper r3 = r3.getLooper()
            if (r0 != r3) goto L_0x0047
            r0 = r1
        L_0x000f:
            java.lang.String r3 = "onConnectionFailure must only be called on the Handler thread"
            com.google.android.gms.common.internal.zzbq.zza(r0, r3)
            android.os.Handler r0 = r7.zzh
            r0.removeMessages(r1)
            java.lang.Object r3 = r7.zzi
            monitor-enter(r3)
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0055 }
            java.util.ArrayList<com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener> r1 = r7.zzd     // Catch:{ all -> 0x0055 }
            r0.<init>(r1)     // Catch:{ all -> 0x0055 }
            java.util.concurrent.atomic.AtomicInteger r1 = r7.zzf     // Catch:{ all -> 0x0055 }
            int r4 = r1.get()     // Catch:{ all -> 0x0055 }
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch:{ all -> 0x0055 }
            int r5 = r0.size()     // Catch:{ all -> 0x0055 }
        L_0x002f:
            if (r2 >= r5) goto L_0x0058
            java.lang.Object r1 = r0.get(r2)     // Catch:{ all -> 0x0055 }
            int r2 = r2 + 1
            com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener r1 = (com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener) r1     // Catch:{ all -> 0x0055 }
            boolean r6 = r7.zze     // Catch:{ all -> 0x0055 }
            if (r6 == 0) goto L_0x0045
            java.util.concurrent.atomic.AtomicInteger r6 = r7.zzf     // Catch:{ all -> 0x0055 }
            int r6 = r6.get()     // Catch:{ all -> 0x0055 }
            if (r6 == r4) goto L_0x0049
        L_0x0045:
            monitor-exit(r3)     // Catch:{ all -> 0x0055 }
        L_0x0046:
            return
        L_0x0047:
            r0 = r2
            goto L_0x000f
        L_0x0049:
            java.util.ArrayList<com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener> r6 = r7.zzd     // Catch:{ all -> 0x0055 }
            boolean r6 = r6.contains(r1)     // Catch:{ all -> 0x0055 }
            if (r6 == 0) goto L_0x002f
            r1.onConnectionFailed(r8)     // Catch:{ all -> 0x0055 }
            goto L_0x002f
        L_0x0055:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0055 }
            throw r0
        L_0x0058:
            monitor-exit(r3)     // Catch:{ all -> 0x0055 }
            goto L_0x0046
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zzae.zza(com.google.android.gms.common.ConnectionResult):void");
    }

    public final void zza(ConnectionCallbacks connectionCallbacks) {
        zzbq.zza(connectionCallbacks);
        synchronized (this.zzi) {
            if (this.zzb.contains(connectionCallbacks)) {
                String valueOf = String.valueOf(connectionCallbacks);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(valueOf).length() + 62).append("registerConnectionCallbacks(): listener ").append(valueOf).append(" is already registered").toString());
            } else {
                this.zzb.add(connectionCallbacks);
            }
        }
        if (this.zza.zzs()) {
            this.zzh.sendMessage(this.zzh.obtainMessage(1, connectionCallbacks));
        }
    }

    public final void zza(OnConnectionFailedListener onConnectionFailedListener) {
        zzbq.zza(onConnectionFailedListener);
        synchronized (this.zzi) {
            if (this.zzd.contains(onConnectionFailedListener)) {
                String valueOf = String.valueOf(onConnectionFailedListener);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(valueOf).length() + 67).append("registerConnectionFailedListener(): listener ").append(valueOf).append(" is already registered").toString());
            } else {
                this.zzd.add(onConnectionFailedListener);
            }
        }
    }

    public final void zzb() {
        this.zze = true;
    }

    public final boolean zzb(ConnectionCallbacks connectionCallbacks) {
        boolean contains;
        zzbq.zza(connectionCallbacks);
        synchronized (this.zzi) {
            contains = this.zzb.contains(connectionCallbacks);
        }
        return contains;
    }

    public final boolean zzb(OnConnectionFailedListener onConnectionFailedListener) {
        boolean contains;
        zzbq.zza(onConnectionFailedListener);
        synchronized (this.zzi) {
            contains = this.zzd.contains(onConnectionFailedListener);
        }
        return contains;
    }

    public final void zzc(ConnectionCallbacks connectionCallbacks) {
        zzbq.zza(connectionCallbacks);
        synchronized (this.zzi) {
            if (!this.zzb.remove(connectionCallbacks)) {
                String valueOf = String.valueOf(connectionCallbacks);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(valueOf).length() + 52).append("unregisterConnectionCallbacks(): listener ").append(valueOf).append(" not found").toString());
            } else if (this.zzg) {
                this.zzc.add(connectionCallbacks);
            }
        }
    }

    public final void zzc(OnConnectionFailedListener onConnectionFailedListener) {
        zzbq.zza(onConnectionFailedListener);
        synchronized (this.zzi) {
            if (!this.zzd.remove(onConnectionFailedListener)) {
                String valueOf = String.valueOf(onConnectionFailedListener);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(valueOf).length() + 57).append("unregisterConnectionFailedListener(): listener ").append(valueOf).append(" not found").toString());
            }
        }
    }
}
