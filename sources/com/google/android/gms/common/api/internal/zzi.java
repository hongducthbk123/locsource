package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzbq;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class zzi extends zzo {
    private final SparseArray<zza> zze = new SparseArray<>();

    class zza implements OnConnectionFailedListener {
        public final int zza;
        public final GoogleApiClient zzb;
        public final OnConnectionFailedListener zzc;

        public zza(int i, GoogleApiClient googleApiClient, OnConnectionFailedListener onConnectionFailedListener) {
            this.zza = i;
            this.zzb = googleApiClient;
            this.zzc = onConnectionFailedListener;
            googleApiClient.registerConnectionFailedListener(this);
        }

        public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            String valueOf = String.valueOf(connectionResult);
            Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf).length() + 27).append("beginFailureResolution for ").append(valueOf).toString());
            zzi.this.zzb(connectionResult, this.zza);
        }
    }

    private zzi(zzcf zzcf) {
        super(zzcf);
        this.zzd.zza("AutoManageHelper", (LifecycleCallback) this);
    }

    public static zzi zza(zzce zzce) {
        zzcf zzb = zzb(zzce);
        zzi zzi = (zzi) zzb.zza("AutoManageHelper", zzi.class);
        return zzi != null ? zzi : new zzi(zzb);
    }

    @Nullable
    private final zza zzb(int i) {
        if (this.zze.size() <= i) {
            return null;
        }
        return (zza) this.zze.get(this.zze.keyAt(i));
    }

    public final void zza() {
        super.zza();
        boolean z = this.zza;
        String valueOf = String.valueOf(this.zze);
        Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf).length() + 14).append("onStart ").append(z).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(valueOf).toString());
        if (this.zzb.get() == null) {
            for (int i = 0; i < this.zze.size(); i++) {
                zza zzb = zzb(i);
                if (zzb != null) {
                    zzb.zzb.connect();
                }
            }
        }
    }

    public final void zza(int i) {
        zza zza2 = (zza) this.zze.get(i);
        this.zze.remove(i);
        if (zza2 != null) {
            zza2.zzb.unregisterConnectionFailedListener(zza2);
            zza2.zzb.disconnect();
        }
    }

    public final void zza(int i, GoogleApiClient googleApiClient, OnConnectionFailedListener onConnectionFailedListener) {
        zzbq.zza(googleApiClient, (Object) "GoogleApiClient instance cannot be null");
        zzbq.zza(this.zze.indexOfKey(i) < 0, (Object) "Already managing a GoogleApiClient with id " + i);
        zzp zzp = (zzp) this.zzb.get();
        boolean z = this.zza;
        String valueOf = String.valueOf(zzp);
        Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf).length() + 49).append("starting AutoManage for client ").append(i).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(z).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(valueOf).toString());
        this.zze.put(i, new zza(i, googleApiClient, onConnectionFailedListener));
        if (this.zza && zzp == null) {
            String valueOf2 = String.valueOf(googleApiClient);
            Log.d("AutoManageHelper", new StringBuilder(String.valueOf(valueOf2).length() + 11).append("connecting ").append(valueOf2).toString());
            googleApiClient.connect();
        }
    }

    /* access modifiers changed from: protected */
    public final void zza(ConnectionResult connectionResult, int i) {
        Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if (i < 0) {
            Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
            return;
        }
        zza zza2 = (zza) this.zze.get(i);
        if (zza2 != null) {
            zza(i);
            OnConnectionFailedListener onConnectionFailedListener = zza2.zzc;
            if (onConnectionFailedListener != null) {
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }

    public final void zza(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        for (int i = 0; i < this.zze.size(); i++) {
            zza zzb = zzb(i);
            if (zzb != null) {
                printWriter.append(str).append("GoogleApiClient #").print(zzb.zza);
                printWriter.println(":");
                zzb.zzb.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
            }
        }
    }

    public final void zzb() {
        super.zzb();
        for (int i = 0; i < this.zze.size(); i++) {
            zza zzb = zzb(i);
            if (zzb != null) {
                zzb.zzb.disconnect();
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zzc() {
        for (int i = 0; i < this.zze.size(); i++) {
            zza zzb = zzb(i);
            if (zzb != null) {
                zzb.zzb.connect();
            }
        }
    }
}
