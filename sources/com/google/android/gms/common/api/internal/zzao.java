package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzan;
import com.google.android.gms.common.internal.zzbt;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.internal.zzt;
import com.google.android.gms.common.zzf;
import com.google.android.gms.internal.zzcyj;
import com.google.android.gms.internal.zzcyk;
import com.google.android.gms.internal.zzcyw;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

public final class zzao implements zzbh {
    /* access modifiers changed from: private */
    public final zzbi zza;
    /* access modifiers changed from: private */
    public final Lock zzb;
    /* access modifiers changed from: private */
    public final Context zzc;
    private final zzf zzd;
    private ConnectionResult zze;
    private int zzf;
    private int zzg = 0;
    private int zzh;
    private final Bundle zzi = new Bundle();
    private final Set<zzc> zzj = new HashSet();
    /* access modifiers changed from: private */
    public zzcyj zzk;
    private boolean zzl;
    /* access modifiers changed from: private */
    public boolean zzm;
    private boolean zzn;
    /* access modifiers changed from: private */
    public zzan zzo;
    private boolean zzp;
    private boolean zzq;
    private final zzr zzr;
    private final Map<Api<?>, Boolean> zzs;
    private final zza<? extends zzcyj, zzcyk> zzt;
    private ArrayList<Future<?>> zzu = new ArrayList<>();

    public zzao(zzbi zzbi, zzr zzr2, Map<Api<?>, Boolean> map, zzf zzf2, zza<? extends zzcyj, zzcyk> zza2, Lock lock, Context context) {
        this.zza = zzbi;
        this.zzr = zzr2;
        this.zzs = map;
        this.zzd = zzf2;
        this.zzt = zza2;
        this.zzb = lock;
        this.zzc = context;
    }

    /* access modifiers changed from: private */
    public final void zza(zzcyw zzcyw) {
        if (zzb(0)) {
            ConnectionResult zza2 = zzcyw.zza();
            if (zza2.isSuccess()) {
                zzbt zzb2 = zzcyw.zzb();
                ConnectionResult zzb3 = zzb2.zzb();
                if (!zzb3.isSuccess()) {
                    String valueOf = String.valueOf(zzb3);
                    Log.wtf("GoogleApiClientConnecting", new StringBuilder(String.valueOf(valueOf).length() + 48).append("Sign-in succeeded with resolve account failure: ").append(valueOf).toString(), new Exception());
                    zzb(zzb3);
                    return;
                }
                this.zzn = true;
                this.zzo = zzb2.zza();
                this.zzp = zzb2.zzc();
                this.zzq = zzb2.zzd();
                zze();
            } else if (zza(zza2)) {
                zzg();
                zze();
            } else {
                zzb(zza2);
            }
        }
    }

    private final void zza(boolean z) {
        if (this.zzk != null) {
            if (this.zzk.zzs() && z) {
                this.zzk.zzh();
            }
            this.zzk.zzg();
            this.zzo = null;
        }
    }

    /* access modifiers changed from: private */
    public final boolean zza(ConnectionResult connectionResult) {
        return this.zzl && !connectionResult.hasResolution();
    }

    /* access modifiers changed from: private */
    public final void zzb(ConnectionResult connectionResult) {
        zzh();
        zza(!connectionResult.hasResolution());
        this.zza.zza(connectionResult);
        this.zza.zze.zza(connectionResult);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003f, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0013, code lost:
        if (r2 != false) goto L_0x0015;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
        if (r3 >= r5.zzf) goto L_0x003f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(com.google.android.gms.common.ConnectionResult r6, com.google.android.gms.common.api.Api<?> r7, boolean r8) {
        /*
            r5 = this;
            r1 = 0
            r0 = 1
            com.google.android.gms.common.api.Api$zzd r2 = r7.zza()
            int r3 = r2.zza()
            if (r8 == 0) goto L_0x0015
            boolean r2 = r6.hasResolution()
            if (r2 == 0) goto L_0x002f
            r2 = r0
        L_0x0013:
            if (r2 == 0) goto L_0x003f
        L_0x0015:
            com.google.android.gms.common.ConnectionResult r2 = r5.zze
            if (r2 == 0) goto L_0x001d
            int r2 = r5.zzf
            if (r3 >= r2) goto L_0x003f
        L_0x001d:
            if (r0 == 0) goto L_0x0023
            r5.zze = r6
            r5.zzf = r3
        L_0x0023:
            com.google.android.gms.common.api.internal.zzbi r0 = r5.zza
            java.util.Map<com.google.android.gms.common.api.Api$zzc<?>, com.google.android.gms.common.ConnectionResult> r0 = r0.zzb
            com.google.android.gms.common.api.Api$zzc r1 = r7.zzc()
            r0.put(r1, r6)
            return
        L_0x002f:
            com.google.android.gms.common.zzf r2 = r5.zzd
            int r4 = r6.getErrorCode()
            android.content.Intent r2 = r2.zza(r4)
            if (r2 == 0) goto L_0x003d
            r2 = r0
            goto L_0x0013
        L_0x003d:
            r2 = r1
            goto L_0x0013
        L_0x003f:
            r0 = r1
            goto L_0x001d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zzao.zzb(com.google.android.gms.common.ConnectionResult, com.google.android.gms.common.api.Api, boolean):void");
    }

    /* access modifiers changed from: private */
    public final boolean zzb(int i) {
        if (this.zzg == i) {
            return true;
        }
        Log.w("GoogleApiClientConnecting", this.zza.zzd.zzh());
        String valueOf = String.valueOf(this);
        Log.w("GoogleApiClientConnecting", new StringBuilder(String.valueOf(valueOf).length() + 23).append("Unexpected callback in ").append(valueOf).toString());
        Log.w("GoogleApiClientConnecting", "mRemainingConnections=" + this.zzh);
        String zzc2 = zzc(this.zzg);
        String zzc3 = zzc(i);
        Log.wtf("GoogleApiClientConnecting", new StringBuilder(String.valueOf(zzc2).length() + 70 + String.valueOf(zzc3).length()).append("GoogleApiClient connecting is in step ").append(zzc2).append(" but received callback for step ").append(zzc3).toString(), new Exception());
        zzb(new ConnectionResult(8, null));
        return false;
    }

    private static String zzc(int i) {
        switch (i) {
            case 0:
                return "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
            case 1:
                return "STEP_GETTING_REMOTE_SERVICE";
            default:
                return "UNKNOWN";
        }
    }

    /* access modifiers changed from: private */
    public final boolean zzd() {
        this.zzh--;
        if (this.zzh > 0) {
            return false;
        }
        if (this.zzh < 0) {
            Log.w("GoogleApiClientConnecting", this.zza.zzd.zzh());
            Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
            zzb(new ConnectionResult(8, null));
            return false;
        } else if (this.zze == null) {
            return true;
        } else {
            this.zza.zzc = this.zzf;
            zzb(this.zze);
            return false;
        }
    }

    /* access modifiers changed from: private */
    public final void zze() {
        if (this.zzh == 0) {
            if (!this.zzm || this.zzn) {
                ArrayList arrayList = new ArrayList();
                this.zzg = 1;
                this.zzh = this.zza.zza.size();
                for (zzc zzc2 : this.zza.zza.keySet()) {
                    if (!this.zza.zzb.containsKey(zzc2)) {
                        arrayList.add((zze) this.zza.zza.get(zzc2));
                    } else if (zzd()) {
                        zzf();
                    }
                }
                if (!arrayList.isEmpty()) {
                    this.zzu.add(zzbl.zza().submit(new zzau(this, arrayList)));
                }
            }
        }
    }

    private final void zzf() {
        this.zza.zzi();
        zzbl.zza().execute(new zzap(this));
        if (this.zzk != null) {
            if (this.zzp) {
                this.zzk.zza(this.zzo, this.zzq);
            }
            zza(false);
        }
        for (zzc zzc2 : this.zza.zzb.keySet()) {
            ((zze) this.zza.zza.get(zzc2)).zzg();
        }
        this.zza.zze.zza(this.zzi.isEmpty() ? null : this.zzi);
    }

    /* access modifiers changed from: private */
    public final void zzg() {
        this.zzm = false;
        this.zza.zzd.zzc = Collections.emptySet();
        for (zzc zzc2 : this.zzj) {
            if (!this.zza.zzb.containsKey(zzc2)) {
                this.zza.zzb.put(zzc2, new ConnectionResult(17, null));
            }
        }
    }

    private final void zzh() {
        ArrayList arrayList = this.zzu;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((Future) obj).cancel(true);
        }
        this.zzu.clear();
    }

    /* access modifiers changed from: private */
    public final Set<Scope> zzi() {
        if (this.zzr == null) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet(this.zzr.zze());
        Map zzg2 = this.zzr.zzg();
        for (Api api : zzg2.keySet()) {
            if (!this.zza.zzb.containsKey(api.zzc())) {
                hashSet.addAll(((zzt) zzg2.get(api)).zza);
            }
        }
        return hashSet;
    }

    public final <A extends zzb, R extends Result, T extends zzm<R, A>> T zza(T t) {
        this.zza.zzd.zza.add(t);
        return t;
    }

    public final void zza() {
        this.zza.zzb.clear();
        this.zzm = false;
        this.zze = null;
        this.zzg = 0;
        this.zzl = true;
        this.zzn = false;
        this.zzp = false;
        HashMap hashMap = new HashMap();
        boolean z = false;
        for (Api api : this.zzs.keySet()) {
            zze zze2 = (zze) this.zza.zza.get(api.zzc());
            boolean z2 = (api.zza().zza() == 1) | z;
            boolean booleanValue = ((Boolean) this.zzs.get(api)).booleanValue();
            if (zze2.mo11215l_()) {
                this.zzm = true;
                if (booleanValue) {
                    this.zzj.add(api.zzc());
                } else {
                    this.zzl = false;
                }
            }
            hashMap.put(zze2, new zzaq(this, api, booleanValue));
            z = z2;
        }
        if (z) {
            this.zzm = false;
        }
        if (this.zzm) {
            this.zzr.zza(Integer.valueOf(System.identityHashCode(this.zza.zzd)));
            zzax zzax = new zzax(this, null);
            this.zzk = (zzcyj) this.zzt.zza(this.zzc, this.zza.zzd.zzc(), this.zzr, this.zzr.zzk(), zzax, zzax);
        }
        this.zzh = this.zza.zza.size();
        this.zzu.add(zzbl.zza().submit(new zzar(this, hashMap)));
    }

    public final void zza(int i) {
        zzb(new ConnectionResult(8, null));
    }

    public final void zza(Bundle bundle) {
        if (zzb(1)) {
            if (bundle != null) {
                this.zzi.putAll(bundle);
            }
            if (zzd()) {
                zzf();
            }
        }
    }

    public final void zza(ConnectionResult connectionResult, Api<?> api, boolean z) {
        if (zzb(1)) {
            zzb(connectionResult, api, z);
            if (zzd()) {
                zzf();
            }
        }
    }

    public final <A extends zzb, T extends zzm<? extends Result, A>> T zzb(T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    public final boolean zzb() {
        zzh();
        zza(true);
        this.zza.zza((ConnectionResult) null);
        return true;
    }

    public final void zzc() {
    }
}
