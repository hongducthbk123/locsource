package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.IInterface;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.zzc;
import java.util.Set;

public abstract class zzab<T extends IInterface> extends zzd<T> implements zze, zzaf {
    private final zzr zzd;
    private final Set<Scope> zze;
    private final Account zzf;

    protected zzab(Context context, Looper looper, int i, zzr zzr, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, zzag.zza(context), GoogleApiAvailability.getInstance(), i, zzr, (ConnectionCallbacks) zzbq.zza(connectionCallbacks), (OnConnectionFailedListener) zzbq.zza(onConnectionFailedListener));
    }

    private zzab(Context context, Looper looper, zzag zzag, GoogleApiAvailability googleApiAvailability, int i, zzr zzr, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, zzag, googleApiAvailability, i, connectionCallbacks == null ? null : new zzac(connectionCallbacks), onConnectionFailedListener == null ? null : new zzad(onConnectionFailedListener), zzr.zzi());
        this.zzd = zzr;
        this.zzf = zzr.zzb();
        Set zzf2 = zzr.zzf();
        Set<Scope> zza = zza(zzf2);
        for (Scope contains : zza) {
            if (!zzf2.contains(contains)) {
                throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
            }
        }
        this.zze = zza;
    }

    /* access modifiers changed from: protected */
    @Hide
    @NonNull
    public Set<Scope> zza(@NonNull Set<Scope> set) {
        return set;
    }

    public final Account zzac() {
        return this.zzf;
    }

    public zzc[] zzad() {
        return new zzc[0];
    }

    /* access modifiers changed from: protected */
    public final Set<Scope> zzah() {
        return this.zze;
    }

    /* access modifiers changed from: protected */
    public final zzr zzai() {
        return this.zzd;
    }

    public final int zzx() {
        return -1;
    }
}
