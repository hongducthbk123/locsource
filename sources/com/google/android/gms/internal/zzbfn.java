package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzr;

public final class zzbfn extends zzab<zzbfr> {
    public zzbfn(Context context, Looper looper, zzr zzr, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 40, zzr, connectionCallbacks, onConnectionFailedListener);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ IInterface zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
        return queryLocalInterface instanceof zzbfr ? (zzbfr) queryLocalInterface : new zzbfs(iBinder);
    }

    /* access modifiers changed from: protected */
    public final String zza() {
        return "com.google.android.gms.clearcut.service.START";
    }

    /* access modifiers changed from: protected */
    public final String zzb() {
        return "com.google.android.gms.clearcut.internal.IClearcutLoggerService";
    }
}
