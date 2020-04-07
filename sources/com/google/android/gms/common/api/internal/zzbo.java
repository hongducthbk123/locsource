package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzbz;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.internal.zzcyj;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public final class zzbo<O extends ApiOptions> implements ConnectionCallbacks, OnConnectionFailedListener, zzu {
    final /* synthetic */ zzbm zza;
    private final Queue<zza> zzb = new LinkedList();
    /* access modifiers changed from: private */
    public final zze zzc;
    private final zzb zzd;
    private final zzh<O> zze;
    private final zzae zzf;
    private final Set<zzj> zzg = new HashSet();
    private final Map<zzck<?>, zzcr> zzh = new HashMap();
    private final int zzi;
    private final zzcv zzj;
    private boolean zzk;
    private int zzl = -1;
    private ConnectionResult zzm = null;

    @WorkerThread
    public zzbo(zzbm zzbm, GoogleApi<O> googleApi) {
        this.zza = zzbm;
        this.zzc = googleApi.zza(zzbm.zzq.getLooper(), this);
        if (this.zzc instanceof zzbz) {
            this.zzd = zzbz.zzi();
        } else {
            this.zzd = this.zzc;
        }
        this.zze = googleApi.zzc();
        this.zzf = new zzae();
        this.zzi = googleApi.zzd();
        if (this.zzc.mo11215l_()) {
            this.zzj = googleApi.zza(zzbm.zzh, zzbm.zzq);
        } else {
            this.zzj = null;
        }
    }

    @WorkerThread
    private final void zzb(ConnectionResult connectionResult) {
        for (zzj zzj2 : this.zzg) {
            String str = null;
            if (connectionResult == ConnectionResult.zza) {
                str = this.zzc.zzw();
            }
            zzj2.zza(this.zze, connectionResult, str);
        }
        this.zzg.clear();
    }

    @WorkerThread
    private final void zzb(zza zza2) {
        zza2.zza(this.zzf, zzk());
        try {
            zza2.zza(this);
        } catch (DeadObjectException e) {
            onConnectionSuspended(1);
            this.zzc.zzg();
        }
    }

    private final void zzn() {
        this.zzl = -1;
        this.zza.zzj = -1;
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzo() {
        zzd();
        zzb(ConnectionResult.zza);
        zzq();
        for (zzcr zzcr : this.zzh.values()) {
            try {
                zzcr.zza.zza(this.zzd, new TaskCompletionSource());
            } catch (DeadObjectException e) {
                onConnectionSuspended(1);
                this.zzc.zzg();
            } catch (RemoteException e2) {
            }
        }
        while (this.zzc.zzs() && !this.zzb.isEmpty()) {
            zzb((zza) this.zzb.remove());
        }
        zzr();
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzp() {
        zzd();
        this.zzk = true;
        this.zzf.zzc();
        this.zza.zzq.sendMessageDelayed(Message.obtain(this.zza.zzq, 9, this.zze), this.zza.zzc);
        this.zza.zzq.sendMessageDelayed(Message.obtain(this.zza.zzq, 11, this.zze), this.zza.zzd);
        zzn();
    }

    @WorkerThread
    private final void zzq() {
        if (this.zzk) {
            this.zza.zzq.removeMessages(11, this.zze);
            this.zza.zzq.removeMessages(9, this.zze);
            this.zzk = false;
        }
    }

    private final void zzr() {
        this.zza.zzq.removeMessages(12, this.zze);
        this.zza.zzq.sendMessageDelayed(this.zza.zzq.obtainMessage(12, this.zze), this.zza.zze);
    }

    public final void onConnected(@Nullable Bundle bundle) {
        if (Looper.myLooper() == this.zza.zzq.getLooper()) {
            zzo();
        } else {
            this.zza.zzq.post(new zzbp(this));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0069, code lost:
        if (r5.zza.zza(r6, r5.zzi) != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0071, code lost:
        if (r6.getErrorCode() != 18) goto L_0x0076;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0073, code lost:
        r5.zzk = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0078, code lost:
        if (r5.zzk == false) goto L_0x0098;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x007a, code lost:
        r5.zza.zzq.sendMessageDelayed(android.os.Message.obtain(r5.zza.zzq, 9, r5.zze), r5.zza.zzc);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0098, code lost:
        r2 = r5.zze.zza();
        zza(new com.google.android.gms.common.api.Status(17, new java.lang.StringBuilder(java.lang.String.valueOf(r2).length() + 38).append("API: ").append(r2).append(" is not available on this device.").toString()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        return;
     */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onConnectionFailed(@android.support.annotation.NonNull com.google.android.gms.common.ConnectionResult r6) {
        /*
            r5 = this;
            com.google.android.gms.common.api.internal.zzbm r0 = r5.zza
            android.os.Handler r0 = r0.zzq
            com.google.android.gms.common.internal.zzbq.zza(r0)
            com.google.android.gms.common.api.internal.zzcv r0 = r5.zzj
            if (r0 == 0) goto L_0x0012
            com.google.android.gms.common.api.internal.zzcv r0 = r5.zzj
            r0.zzb()
        L_0x0012:
            r5.zzd()
            r5.zzn()
            r5.zzb(r6)
            int r0 = r6.getErrorCode()
            r1 = 4
            if (r0 != r1) goto L_0x002a
            com.google.android.gms.common.api.Status r0 = com.google.android.gms.common.api.internal.zzbm.zzb
            r5.zza(r0)
        L_0x0029:
            return
        L_0x002a:
            java.util.Queue<com.google.android.gms.common.api.internal.zza> r0 = r5.zzb
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0035
            r5.zzm = r6
            goto L_0x0029
        L_0x0035:
            java.lang.Object r1 = com.google.android.gms.common.api.internal.zzbm.zzf
            monitor-enter(r1)
            com.google.android.gms.common.api.internal.zzbm r0 = r5.zza     // Catch:{ all -> 0x005d }
            com.google.android.gms.common.api.internal.zzah r0 = r0.zzn     // Catch:{ all -> 0x005d }
            if (r0 == 0) goto L_0x0060
            com.google.android.gms.common.api.internal.zzbm r0 = r5.zza     // Catch:{ all -> 0x005d }
            java.util.Set r0 = r0.zzo     // Catch:{ all -> 0x005d }
            com.google.android.gms.common.api.internal.zzh<O> r2 = r5.zze     // Catch:{ all -> 0x005d }
            boolean r0 = r0.contains(r2)     // Catch:{ all -> 0x005d }
            if (r0 == 0) goto L_0x0060
            com.google.android.gms.common.api.internal.zzbm r0 = r5.zza     // Catch:{ all -> 0x005d }
            com.google.android.gms.common.api.internal.zzah r0 = r0.zzn     // Catch:{ all -> 0x005d }
            int r2 = r5.zzi     // Catch:{ all -> 0x005d }
            r0.zzb(r6, r2)     // Catch:{ all -> 0x005d }
            monitor-exit(r1)     // Catch:{ all -> 0x005d }
            goto L_0x0029
        L_0x005d:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x005d }
            throw r0
        L_0x0060:
            monitor-exit(r1)     // Catch:{ all -> 0x005d }
            com.google.android.gms.common.api.internal.zzbm r0 = r5.zza
            int r1 = r5.zzi
            boolean r0 = r0.zza(r6, r1)
            if (r0 != 0) goto L_0x0029
            int r0 = r6.getErrorCode()
            r1 = 18
            if (r0 != r1) goto L_0x0076
            r0 = 1
            r5.zzk = r0
        L_0x0076:
            boolean r0 = r5.zzk
            if (r0 == 0) goto L_0x0098
            com.google.android.gms.common.api.internal.zzbm r0 = r5.zza
            android.os.Handler r0 = r0.zzq
            com.google.android.gms.common.api.internal.zzbm r1 = r5.zza
            android.os.Handler r1 = r1.zzq
            r2 = 9
            com.google.android.gms.common.api.internal.zzh<O> r3 = r5.zze
            android.os.Message r1 = android.os.Message.obtain(r1, r2, r3)
            com.google.android.gms.common.api.internal.zzbm r2 = r5.zza
            long r2 = r2.zzc
            r0.sendMessageDelayed(r1, r2)
            goto L_0x0029
        L_0x0098:
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status
            r1 = 17
            com.google.android.gms.common.api.internal.zzh<O> r2 = r5.zze
            java.lang.String r2 = r2.zza()
            java.lang.String r3 = java.lang.String.valueOf(r2)
            int r3 = r3.length()
            int r3 = r3 + 38
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            java.lang.String r3 = "API: "
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.StringBuilder r2 = r3.append(r2)
            java.lang.String r3 = " is not available on this device."
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.<init>(r1, r2)
            r5.zza(r0)
            goto L_0x0029
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zzbo.onConnectionFailed(com.google.android.gms.common.ConnectionResult):void");
    }

    public final void onConnectionSuspended(int i) {
        if (Looper.myLooper() == this.zza.zzq.getLooper()) {
            zzp();
        } else {
            this.zza.zzq.post(new zzbq(this));
        }
    }

    @WorkerThread
    public final void zza() {
        zzbq.zza(this.zza.zzq);
        zza(zzbm.zza);
        this.zzf.zzb();
        for (zzck zzf2 : (zzck[]) this.zzh.keySet().toArray(new zzck[this.zzh.size()])) {
            zza((zza) new zzf(zzf2, new TaskCompletionSource()));
        }
        zzb(new ConnectionResult(4));
        if (this.zzc.zzs()) {
            this.zzc.zza((zzp) new zzbs(this));
        }
    }

    @WorkerThread
    public final void zza(@NonNull ConnectionResult connectionResult) {
        zzbq.zza(this.zza.zzq);
        this.zzc.zzg();
        onConnectionFailed(connectionResult);
    }

    public final void zza(ConnectionResult connectionResult, Api<?> api, boolean z) {
        if (Looper.myLooper() == this.zza.zzq.getLooper()) {
            onConnectionFailed(connectionResult);
        } else {
            this.zza.zzq.post(new zzbr(this, connectionResult));
        }
    }

    @WorkerThread
    public final void zza(Status status) {
        zzbq.zza(this.zza.zzq);
        for (zza zza2 : this.zzb) {
            zza2.zza(status);
        }
        this.zzb.clear();
    }

    @WorkerThread
    public final void zza(zza zza2) {
        zzbq.zza(this.zza.zzq);
        if (this.zzc.zzs()) {
            zzb(zza2);
            zzr();
            return;
        }
        this.zzb.add(zza2);
        if (this.zzm == null || !this.zzm.hasResolution()) {
            zzi();
        } else {
            onConnectionFailed(this.zzm);
        }
    }

    @WorkerThread
    public final void zza(zzj zzj2) {
        zzbq.zza(this.zza.zzq);
        this.zzg.add(zzj2);
    }

    public final zze zzb() {
        return this.zzc;
    }

    public final Map<zzck<?>, zzcr> zzc() {
        return this.zzh;
    }

    @WorkerThread
    public final void zzd() {
        zzbq.zza(this.zza.zzq);
        this.zzm = null;
    }

    @WorkerThread
    public final ConnectionResult zze() {
        zzbq.zza(this.zza.zzq);
        return this.zzm;
    }

    @WorkerThread
    public final void zzf() {
        zzbq.zza(this.zza.zzq);
        if (this.zzk) {
            zzi();
        }
    }

    @WorkerThread
    public final void zzg() {
        zzbq.zza(this.zza.zzq);
        if (this.zzk) {
            zzq();
            zza(this.zza.zzi.isGooglePlayServicesAvailable(this.zza.zzh) == 18 ? new Status(8, "Connection timed out while waiting for Google Play services update to complete.") : new Status(8, "API failed to connect while resuming due to an unknown error."));
            this.zzc.zzg();
        }
    }

    @WorkerThread
    public final void zzh() {
        zzbq.zza(this.zza.zzq);
        if (this.zzc.zzs() && this.zzh.size() == 0) {
            if (this.zzf.zza()) {
                zzr();
            } else {
                this.zzc.zzg();
            }
        }
    }

    @WorkerThread
    public final void zzi() {
        zzbq.zza(this.zza.zzq);
        if (!this.zzc.zzs() && !this.zzc.zzt()) {
            if (this.zzc.zzu()) {
                this.zzc.zzx();
                if (this.zza.zzj != 0) {
                    this.zza.zzi;
                    int zza2 = GoogleApiAvailability.zza(this.zza.zzh, this.zzc.zzx());
                    this.zzc.zzx();
                    this.zza.zzj = zza2;
                    if (zza2 != 0) {
                        onConnectionFailed(new ConnectionResult(zza2, null));
                        return;
                    }
                }
            }
            zzbu zzbu = new zzbu(this.zza, this.zzc, this.zze);
            if (this.zzc.mo11215l_()) {
                this.zzj.zza((zzcy) zzbu);
            }
            this.zzc.zza((zzj) zzbu);
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzj() {
        return this.zzc.zzs();
    }

    public final boolean zzk() {
        return this.zzc.mo11215l_();
    }

    public final int zzl() {
        return this.zzi;
    }

    /* access modifiers changed from: 0000 */
    public final zzcyj zzm() {
        if (this.zzj == null) {
            return null;
        }
        return this.zzj.zza();
    }
}
