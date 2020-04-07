package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.zzf;
import com.google.android.gms.internal.zzcyj;
import com.google.android.gms.internal.zzcyk;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

final class zzv implements zzcc {
    private final Context zza;
    private final zzba zzb;
    private final Looper zzc;
    /* access modifiers changed from: private */
    public final zzbi zzd;
    /* access modifiers changed from: private */
    public final zzbi zze;
    private final Map<zzc<?>, zzbi> zzf;
    private final Set<zzcu> zzg = Collections.newSetFromMap(new WeakHashMap());
    private final zze zzh;
    private Bundle zzi;
    /* access modifiers changed from: private */
    public ConnectionResult zzj = null;
    /* access modifiers changed from: private */
    public ConnectionResult zzk = null;
    /* access modifiers changed from: private */
    public boolean zzl = false;
    /* access modifiers changed from: private */
    public final Lock zzm;
    private int zzn = 0;

    private zzv(Context context, zzba zzba, Lock lock, Looper looper, zzf zzf2, Map<zzc<?>, zze> map, Map<zzc<?>, zze> map2, zzr zzr, zza<? extends zzcyj, zzcyk> zza2, zze zze2, ArrayList<zzt> arrayList, ArrayList<zzt> arrayList2, Map<Api<?>, Boolean> map3, Map<Api<?>, Boolean> map4) {
        this.zza = context;
        this.zzb = zzba;
        this.zzm = lock;
        this.zzc = looper;
        this.zzh = zze2;
        this.zzd = new zzbi(context, this.zzb, lock, looper, zzf2, map2, null, map4, null, arrayList2, new zzx(this, null));
        this.zze = new zzbi(context, this.zzb, lock, looper, zzf2, map, zzr, map3, zza2, arrayList, new zzy(this, null));
        ArrayMap arrayMap = new ArrayMap();
        for (zzc put : map2.keySet()) {
            arrayMap.put(put, this.zzd);
        }
        for (zzc put2 : map.keySet()) {
            arrayMap.put(put2, this.zze);
        }
        this.zzf = Collections.unmodifiableMap(arrayMap);
    }

    public static zzv zza(Context context, zzba zzba, Lock lock, Looper looper, zzf zzf2, Map<zzc<?>, zze> map, zzr zzr, Map<Api<?>, Boolean> map2, zza<? extends zzcyj, zzcyk> zza2, ArrayList<zzt> arrayList) {
        zze zze2 = null;
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        for (Entry entry : map.entrySet()) {
            zze zze3 = (zze) entry.getValue();
            if (zze3.zze()) {
                zze2 = zze3;
            }
            if (zze3.mo11215l_()) {
                arrayMap.put((zzc) entry.getKey(), zze3);
            } else {
                arrayMap2.put((zzc) entry.getKey(), zze3);
            }
        }
        zzbq.zza(!arrayMap.isEmpty(), (Object) "CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
        ArrayMap arrayMap3 = new ArrayMap();
        ArrayMap arrayMap4 = new ArrayMap();
        for (Api api : map2.keySet()) {
            zzc zzc2 = api.zzc();
            if (arrayMap.containsKey(zzc2)) {
                arrayMap3.put(api, (Boolean) map2.get(api));
            } else if (arrayMap2.containsKey(zzc2)) {
                arrayMap4.put(api, (Boolean) map2.get(api));
            } else {
                throw new IllegalStateException("Each API in the isOptionalMap must have a corresponding client in the clients map.");
            }
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = arrayList;
        int size = arrayList4.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList4.get(i);
            i++;
            zzt zzt = (zzt) obj;
            if (arrayMap3.containsKey(zzt.zza)) {
                arrayList2.add(zzt);
            } else if (arrayMap4.containsKey(zzt.zza)) {
                arrayList3.add(zzt);
            } else {
                throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the isOptionalMap");
            }
        }
        return new zzv(context, zzba, lock, looper, zzf2, arrayMap, arrayMap2, zzr, zza2, zze2, arrayList2, arrayList3, arrayMap3, arrayMap4);
    }

    /* access modifiers changed from: private */
    public final void zza(int i, boolean z) {
        this.zzb.zza(i, z);
        this.zzk = null;
        this.zzj = null;
    }

    /* access modifiers changed from: private */
    public final void zza(Bundle bundle) {
        if (this.zzi == null) {
            this.zzi = bundle;
        } else if (bundle != null) {
            this.zzi.putAll(bundle);
        }
    }

    private final void zza(ConnectionResult connectionResult) {
        switch (this.zzn) {
            case 1:
                break;
            case 2:
                this.zzb.zza(connectionResult);
                break;
            default:
                Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
                break;
        }
        zzi();
        this.zzn = 0;
    }

    private static boolean zzb(ConnectionResult connectionResult) {
        return connectionResult != null && connectionResult.isSuccess();
    }

    private final boolean zzc(zzm<? extends Result, ? extends zzb> zzm2) {
        zzc zzc2 = zzm2.zzc();
        zzbq.zzb(this.zzf.containsKey(zzc2), "GoogleApiClient is not configured to use the API required for this call.");
        return ((zzbi) this.zzf.get(zzc2)).equals(this.zze);
    }

    /* access modifiers changed from: private */
    public final void zzh() {
        if (zzb(this.zzj)) {
            if (zzb(this.zzk) || zzj()) {
                switch (this.zzn) {
                    case 1:
                        break;
                    case 2:
                        this.zzb.zza(this.zzi);
                        break;
                    default:
                        Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new AssertionError());
                        break;
                }
                zzi();
                this.zzn = 0;
            } else if (this.zzk == null) {
            } else {
                if (this.zzn == 1) {
                    zzi();
                    return;
                }
                zza(this.zzk);
                this.zzd.zzc();
            }
        } else if (this.zzj != null && zzb(this.zzk)) {
            this.zze.zzc();
            zza(this.zzj);
        } else if (this.zzj != null && this.zzk != null) {
            ConnectionResult connectionResult = this.zzj;
            if (this.zze.zzc < this.zzd.zzc) {
                connectionResult = this.zzk;
            }
            zza(connectionResult);
        }
    }

    private final void zzi() {
        for (zzcu zza2 : this.zzg) {
            zza2.zza();
        }
        this.zzg.clear();
    }

    private final boolean zzj() {
        return this.zzk != null && this.zzk.getErrorCode() == 4;
    }

    @Nullable
    private final PendingIntent zzk() {
        if (this.zzh == null) {
            return null;
        }
        return PendingIntent.getActivity(this.zza, System.identityHashCode(this.zzb), this.zzh.zzf(), 134217728);
    }

    public final ConnectionResult zza(long j, @NonNull TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    @Nullable
    public final ConnectionResult zza(@NonNull Api<?> api) {
        return ((zzbi) this.zzf.get(api.zzc())).equals(this.zze) ? zzj() ? new ConnectionResult(4, zzk()) : this.zze.zza(api) : this.zzd.zza(api);
    }

    public final <A extends zzb, R extends Result, T extends zzm<R, A>> T zza(@NonNull T t) {
        if (!zzc((zzm<? extends Result, ? extends zzb>) t)) {
            return this.zzd.zza(t);
        }
        if (!zzj()) {
            return this.zze.zza(t);
        }
        t.zzc(new Status(4, null, zzk()));
        return t;
    }

    public final void zza() {
        this.zzn = 2;
        this.zzl = false;
        this.zzk = null;
        this.zzj = null;
        this.zzd.zza();
        this.zze.zza();
    }

    public final void zza(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append(str).append("authClient").println(":");
        this.zze.zza(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
        printWriter.append(str).append("anonClient").println(":");
        this.zzd.zza(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
    }

    public final boolean zza(zzcu zzcu) {
        this.zzm.lock();
        try {
            if ((zze() || zzd()) && !this.zze.zzd()) {
                this.zzg.add(zzcu);
                if (this.zzn == 0) {
                    this.zzn = 1;
                }
                this.zzk = null;
                this.zze.zza();
                return true;
            }
            this.zzm.unlock();
            return false;
        } finally {
            this.zzm.unlock();
        }
    }

    public final ConnectionResult zzb() {
        throw new UnsupportedOperationException();
    }

    public final <A extends zzb, T extends zzm<? extends Result, A>> T zzb(@NonNull T t) {
        if (!zzc((zzm<? extends Result, ? extends zzb>) t)) {
            return this.zzd.zzb(t);
        }
        if (!zzj()) {
            return this.zze.zzb(t);
        }
        t.zzc(new Status(4, null, zzk()));
        return t;
    }

    public final void zzc() {
        this.zzk = null;
        this.zzj = null;
        this.zzn = 0;
        this.zzd.zzc();
        this.zze.zzc();
        zzi();
    }

    public final boolean zzd() {
        boolean z = true;
        this.zzm.lock();
        try {
            if (!this.zzd.zzd() || (!this.zze.zzd() && !zzj() && this.zzn != 1)) {
                z = false;
            }
            return z;
        } finally {
            this.zzm.unlock();
        }
    }

    public final boolean zze() {
        this.zzm.lock();
        try {
            return this.zzn == 2;
        } finally {
            this.zzm.unlock();
        }
    }

    public final void zzf() {
        this.zzd.zzf();
        this.zze.zzf();
    }

    public final void zzg() {
        this.zzm.lock();
        try {
            boolean zze2 = zze();
            this.zze.zzc();
            this.zzk = new ConnectionResult(4);
            if (zze2) {
                new Handler(this.zzc).post(new zzw(this));
            } else {
                zzi();
            }
        } finally {
            this.zzm.unlock();
        }
    }
}
