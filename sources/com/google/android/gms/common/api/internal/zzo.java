package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import java.util.concurrent.atomic.AtomicReference;

public abstract class zzo extends LifecycleCallback implements OnCancelListener {
    protected volatile boolean zza;
    protected final AtomicReference<zzp> zzb;
    protected final GoogleApiAvailability zzc;
    private final Handler zze;

    protected zzo(zzcf zzcf) {
        this(zzcf, GoogleApiAvailability.getInstance());
    }

    private zzo(zzcf zzcf, GoogleApiAvailability googleApiAvailability) {
        super(zzcf);
        this.zzb = new AtomicReference<>(null);
        this.zze = new Handler(Looper.getMainLooper());
        this.zzc = googleApiAvailability;
    }

    private static int zza(@Nullable zzp zzp) {
        if (zzp == null) {
            return -1;
        }
        return zzp.zza();
    }

    public void onCancel(DialogInterface dialogInterface) {
        zza(new ConnectionResult(13, null), zza((zzp) this.zzb.get()));
        zzd();
    }

    public void zza() {
        super.zza();
        this.zza = true;
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(int r7, int r8, android.content.Intent r9) {
        /*
            r6 = this;
            r5 = 18
            r1 = 13
            r2 = 1
            r3 = 0
            java.util.concurrent.atomic.AtomicReference<com.google.android.gms.common.api.internal.zzp> r0 = r6.zzb
            java.lang.Object r0 = r0.get()
            com.google.android.gms.common.api.internal.zzp r0 = (com.google.android.gms.common.api.internal.zzp) r0
            switch(r7) {
                case 1: goto L_0x0034;
                case 2: goto L_0x0018;
                default: goto L_0x0011;
            }
        L_0x0011:
            r1 = r3
        L_0x0012:
            if (r1 == 0) goto L_0x005a
            r6.zzd()
        L_0x0017:
            return
        L_0x0018:
            com.google.android.gms.common.GoogleApiAvailability r1 = r6.zzc
            android.app.Activity r4 = r6.zzg()
            int r4 = r1.isGooglePlayServicesAvailable(r4)
            if (r4 != 0) goto L_0x0068
            r1 = r2
        L_0x0025:
            if (r0 == 0) goto L_0x0017
            com.google.android.gms.common.ConnectionResult r2 = r0.zzb()
            int r2 = r2.getErrorCode()
            if (r2 != r5) goto L_0x0012
            if (r4 != r5) goto L_0x0012
            goto L_0x0017
        L_0x0034:
            r4 = -1
            if (r8 != r4) goto L_0x0039
            r1 = r2
            goto L_0x0012
        L_0x0039:
            if (r8 != 0) goto L_0x0011
            if (r9 == 0) goto L_0x0043
            java.lang.String r2 = "<<ResolutionFailureErrorDetail>>"
            int r1 = r9.getIntExtra(r2, r1)
        L_0x0043:
            com.google.android.gms.common.api.internal.zzp r2 = new com.google.android.gms.common.api.internal.zzp
            com.google.android.gms.common.ConnectionResult r4 = new com.google.android.gms.common.ConnectionResult
            r5 = 0
            r4.<init>(r1, r5)
            int r0 = zza(r0)
            r2.<init>(r4, r0)
            java.util.concurrent.atomic.AtomicReference<com.google.android.gms.common.api.internal.zzp> r0 = r6.zzb
            r0.set(r2)
            r0 = r2
            r1 = r3
            goto L_0x0012
        L_0x005a:
            if (r0 == 0) goto L_0x0017
            com.google.android.gms.common.ConnectionResult r1 = r0.zzb()
            int r0 = r0.zza()
            r6.zza(r1, r0)
            goto L_0x0017
        L_0x0068:
            r1 = r3
            goto L_0x0025
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zzo.zza(int, int, android.content.Intent):void");
    }

    public final void zza(Bundle bundle) {
        super.zza(bundle);
        if (bundle != null) {
            this.zzb.set(bundle.getBoolean("resolving_error", false) ? new zzp(new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution")), bundle.getInt("failed_client_id", -1)) : null);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void zza(ConnectionResult connectionResult, int i);

    public void zzb() {
        super.zzb();
        this.zza = false;
    }

    public final void zzb(Bundle bundle) {
        super.zzb(bundle);
        zzp zzp = (zzp) this.zzb.get();
        if (zzp != null) {
            bundle.putBoolean("resolving_error", true);
            bundle.putInt("failed_client_id", zzp.zza());
            bundle.putInt("failed_status", zzp.zzb().getErrorCode());
            bundle.putParcelable("failed_resolution", zzp.zzb().getResolution());
        }
    }

    public final void zzb(ConnectionResult connectionResult, int i) {
        zzp zzp = new zzp(connectionResult, i);
        if (this.zzb.compareAndSet(null, zzp)) {
            this.zze.post(new zzq(this, zzp));
        }
    }

    /* access modifiers changed from: protected */
    public abstract void zzc();

    /* access modifiers changed from: protected */
    public final void zzd() {
        this.zzb.set(null);
        zzc();
    }
}
