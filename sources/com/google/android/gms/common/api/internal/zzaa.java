package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.internal.zzt;
import com.google.android.gms.common.zzf;
import com.google.android.gms.internal.zzbic;
import com.google.android.gms.internal.zzcyj;
import com.google.android.gms.internal.zzcyk;
import com.google.android.gms.tasks.OnCompleteListener;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public final class zzaa implements zzcc {
    /* access modifiers changed from: private */
    public final Map<zzc<?>, zzz<?>> zza = new HashMap();
    /* access modifiers changed from: private */
    public final Map<zzc<?>, zzz<?>> zzb = new HashMap();
    private final Map<Api<?>, Boolean> zzc;
    private final zzbm zzd;
    /* access modifiers changed from: private */
    public final zzba zze;
    /* access modifiers changed from: private */
    public final Lock zzf;
    private final Looper zzg;
    private final zzf zzh;
    /* access modifiers changed from: private */
    public final Condition zzi;
    private final zzr zzj;
    private final boolean zzk;
    /* access modifiers changed from: private */
    public final boolean zzl;
    private final Queue<zzm<?, ?>> zzm = new LinkedList();
    /* access modifiers changed from: private */
    public boolean zzn;
    /* access modifiers changed from: private */
    public Map<zzh<?>, ConnectionResult> zzo;
    /* access modifiers changed from: private */
    public Map<zzh<?>, ConnectionResult> zzp;
    private zzad zzq;
    /* access modifiers changed from: private */
    public ConnectionResult zzr;

    public zzaa(Context context, Lock lock, Looper looper, zzf zzf2, Map<zzc<?>, zze> map, zzr zzr2, Map<Api<?>, Boolean> map2, zza<? extends zzcyj, zzcyk> zza2, ArrayList<zzt> arrayList, zzba zzba, boolean z) {
        boolean z2;
        boolean z3;
        boolean z4;
        this.zzf = lock;
        this.zzg = looper;
        this.zzi = lock.newCondition();
        this.zzh = zzf2;
        this.zze = zzba;
        this.zzc = map2;
        this.zzj = zzr2;
        this.zzk = z;
        HashMap hashMap = new HashMap();
        for (Api api : map2.keySet()) {
            hashMap.put(api.zzc(), api);
        }
        HashMap hashMap2 = new HashMap();
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            zzt zzt = (zzt) obj;
            hashMap2.put(zzt.zza, zzt);
        }
        boolean z5 = true;
        boolean z6 = false;
        boolean z7 = false;
        for (Entry entry : map.entrySet()) {
            Api api2 = (Api) hashMap.get(entry.getKey());
            zze zze2 = (zze) entry.getValue();
            if (zze2.zzu()) {
                z2 = true;
                if (!((Boolean) this.zzc.get(api2)).booleanValue()) {
                    z3 = z5;
                    z4 = true;
                } else {
                    z3 = z5;
                    z4 = z6;
                }
            } else {
                z2 = z7;
                z3 = false;
                z4 = z6;
            }
            zzz zzz = new zzz(context, api2, looper, zze2, (zzt) hashMap2.get(api2), zzr2, zza2);
            this.zza.put((zzc) entry.getKey(), zzz);
            if (zze2.mo11215l_()) {
                this.zzb.put((zzc) entry.getKey(), zzz);
            }
            z7 = z2;
            z5 = z3;
            z6 = z4;
        }
        this.zzl = z7 && !z5 && !z6;
        this.zzd = zzbm.zza();
    }

    @Nullable
    private final ConnectionResult zza(@NonNull zzc<?> zzc2) {
        this.zzf.lock();
        try {
            zzz zzz = (zzz) this.zza.get(zzc2);
            if (this.zzo != null && zzz != null) {
                return (ConnectionResult) this.zzo.get(zzz.zzc());
            }
            this.zzf.unlock();
            return null;
        } finally {
            this.zzf.unlock();
        }
    }

    /* access modifiers changed from: private */
    public final boolean zza(zzz<?> zzz, ConnectionResult connectionResult) {
        return !connectionResult.isSuccess() && !connectionResult.hasResolution() && ((Boolean) this.zzc.get(zzz.zza())).booleanValue() && zzz.zzh().zzu() && this.zzh.isUserResolvableError(connectionResult.getErrorCode());
    }

    private final <T extends zzm<? extends Result, ? extends zzb>> boolean zzc(@NonNull T t) {
        zzc zzc2 = t.zzc();
        ConnectionResult zza2 = zza(zzc2);
        if (zza2 == null || zza2.getErrorCode() != 4) {
            return false;
        }
        t.zzc(new Status(4, null, this.zzd.zza(((zzz) this.zza.get(zzc2)).zzc(), System.identityHashCode(this.zze))));
        return true;
    }

    /* JADX INFO: finally extract failed */
    private final boolean zzh() {
        this.zzf.lock();
        try {
            if (!this.zzn || !this.zzk) {
                this.zzf.unlock();
                return false;
            }
            for (zzc zza2 : this.zzb.keySet()) {
                ConnectionResult zza3 = zza(zza2);
                if (zza3 != null) {
                    if (!zza3.isSuccess()) {
                    }
                }
                this.zzf.unlock();
                return false;
            }
            this.zzf.unlock();
            return true;
        } catch (Throwable th) {
            this.zzf.unlock();
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public final void zzi() {
        if (this.zzj == null) {
            this.zze.zzc = Collections.emptySet();
            return;
        }
        HashSet hashSet = new HashSet(this.zzj.zze());
        Map zzg2 = this.zzj.zzg();
        for (Api api : zzg2.keySet()) {
            ConnectionResult zza2 = zza(api);
            if (zza2 != null && zza2.isSuccess()) {
                hashSet.addAll(((zzt) zzg2.get(api)).zza);
            }
        }
        this.zze.zzc = hashSet;
    }

    /* access modifiers changed from: private */
    public final void zzj() {
        while (!this.zzm.isEmpty()) {
            zzb((T) (zzm) this.zzm.remove());
        }
        this.zze.zza((Bundle) null);
    }

    /* access modifiers changed from: private */
    @Nullable
    public final ConnectionResult zzk() {
        ConnectionResult connectionResult;
        int i;
        int i2 = 0;
        ConnectionResult connectionResult2 = null;
        int i3 = 0;
        ConnectionResult connectionResult3 = null;
        for (zzz zzz : this.zza.values()) {
            Api zza2 = zzz.zza();
            ConnectionResult connectionResult4 = (ConnectionResult) this.zzo.get(zzz.zzc());
            if (!connectionResult4.isSuccess() && (!((Boolean) this.zzc.get(zza2)).booleanValue() || connectionResult4.hasResolution() || this.zzh.isUserResolvableError(connectionResult4.getErrorCode()))) {
                if (connectionResult4.getErrorCode() != 4 || !this.zzk) {
                    int zza3 = zza2.zza().zza();
                    if (connectionResult3 == null || i3 > zza3) {
                        int i4 = zza3;
                        connectionResult = connectionResult4;
                        i = i4;
                    } else {
                        i = i3;
                        connectionResult = connectionResult3;
                    }
                    i3 = i;
                    connectionResult3 = connectionResult;
                } else {
                    int zza4 = zza2.zza().zza();
                    if (connectionResult2 == null || i2 > zza4) {
                        i2 = zza4;
                        connectionResult2 = connectionResult4;
                    }
                }
            }
        }
        return (connectionResult3 == null || connectionResult2 == null || i3 <= i2) ? connectionResult3 : connectionResult2;
    }

    public final ConnectionResult zza(long j, TimeUnit timeUnit) {
        zza();
        long nanos = timeUnit.toNanos(j);
        while (zze()) {
            if (nanos <= 0) {
                try {
                    zzc();
                    return new ConnectionResult(14, null);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return new ConnectionResult(15, null);
                }
            } else {
                nanos = this.zzi.awaitNanos(nanos);
            }
        }
        return zzd() ? ConnectionResult.zza : this.zzr != null ? this.zzr : new ConnectionResult(13, null);
    }

    @Nullable
    public final ConnectionResult zza(@NonNull Api<?> api) {
        return zza(api.zzc());
    }

    public final <A extends zzb, R extends Result, T extends zzm<R, A>> T zza(@NonNull T t) {
        if (this.zzk && zzc(t)) {
            return t;
        }
        if (!zzd()) {
            this.zzm.add(t);
            return t;
        }
        this.zze.zze.zza(t);
        return ((zzz) this.zza.get(t.zzc())).zza(t);
    }

    public final void zza() {
        this.zzf.lock();
        try {
            if (!this.zzn) {
                this.zzn = true;
                this.zzo = null;
                this.zzp = null;
                this.zzq = null;
                this.zzr = null;
                this.zzd.zzd();
                this.zzd.zza((Iterable<? extends GoogleApi<?>>) this.zza.values()).addOnCompleteListener((Executor) new zzbic(this.zzg), (OnCompleteListener<TResult>) new zzac<TResult>(this));
                this.zzf.unlock();
            }
        } finally {
            this.zzf.unlock();
        }
    }

    public final void zza(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    /* JADX INFO: finally extract failed */
    public final boolean zza(zzcu zzcu) {
        this.zzf.lock();
        try {
            if (!this.zzn || zzh()) {
                this.zzf.unlock();
                return false;
            }
            this.zzd.zzd();
            this.zzq = new zzad(this, zzcu);
            this.zzd.zza((Iterable<? extends GoogleApi<?>>) this.zzb.values()).addOnCompleteListener((Executor) new zzbic(this.zzg), (OnCompleteListener<TResult>) this.zzq);
            this.zzf.unlock();
            return true;
        } catch (Throwable th) {
            this.zzf.unlock();
            throw th;
        }
    }

    public final ConnectionResult zzb() {
        zza();
        while (zze()) {
            try {
                this.zzi.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
        }
        return zzd() ? ConnectionResult.zza : this.zzr != null ? this.zzr : new ConnectionResult(13, null);
    }

    public final <A extends zzb, T extends zzm<? extends Result, A>> T zzb(@NonNull T t) {
        zzc zzc2 = t.zzc();
        if (this.zzk && zzc(t)) {
            return t;
        }
        this.zze.zze.zza(t);
        return ((zzz) this.zza.get(zzc2)).zzb(t);
    }

    public final void zzc() {
        this.zzf.lock();
        try {
            this.zzn = false;
            this.zzo = null;
            this.zzp = null;
            if (this.zzq != null) {
                this.zzq.zza();
                this.zzq = null;
            }
            this.zzr = null;
            while (!this.zzm.isEmpty()) {
                zzm zzm2 = (zzm) this.zzm.remove();
                zzm2.zza((zzdn) null);
                zzm2.cancel();
            }
            this.zzi.signalAll();
        } finally {
            this.zzf.unlock();
        }
    }

    public final boolean zzd() {
        this.zzf.lock();
        try {
            return this.zzo != null && this.zzr == null;
        } finally {
            this.zzf.unlock();
        }
    }

    public final boolean zze() {
        this.zzf.lock();
        try {
            return this.zzo == null && this.zzn;
        } finally {
            this.zzf.unlock();
        }
    }

    public final void zzf() {
    }

    public final void zzg() {
        this.zzf.lock();
        try {
            this.zzd.zze();
            if (this.zzq != null) {
                this.zzq.zza();
                this.zzq = null;
            }
            if (this.zzp == null) {
                this.zzp = new ArrayMap(this.zzb.size());
            }
            ConnectionResult connectionResult = new ConnectionResult(4);
            for (zzz zzc2 : this.zzb.values()) {
                this.zzp.put(zzc2.zzc(), connectionResult);
            }
            if (this.zzo != null) {
                this.zzo.putAll(this.zzp);
            }
        } finally {
            this.zzf.unlock();
        }
    }
}
