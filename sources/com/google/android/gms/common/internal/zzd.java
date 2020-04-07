package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Log;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.zzc;
import com.google.android.gms.common.zzf;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class zzd<T extends IInterface> {
    @Hide
    private static String[] zzaa = {"service_esmobile", "service_googleme"};
    final Handler zza;
    protected zzj zzb;
    protected AtomicInteger zzc;
    private int zzd;
    private long zze;
    private long zzf;
    private int zzg;
    private long zzh;
    private zzam zzi;
    private final Context zzj;
    private final Looper zzk;
    private final zzag zzl;
    private final zzf zzm;
    private final Object zzn;
    /* access modifiers changed from: private */
    public final Object zzo;
    /* access modifiers changed from: private */
    public zzay zzp;
    private T zzq;
    /* access modifiers changed from: private */
    public final ArrayList<zzi<?>> zzr;
    private zzl zzs;
    private int zzt;
    /* access modifiers changed from: private */
    public final zzf zzu;
    /* access modifiers changed from: private */
    public final zzg zzv;
    private final int zzw;
    private final String zzx;
    /* access modifiers changed from: private */
    public ConnectionResult zzy;
    /* access modifiers changed from: private */
    public boolean zzz;

    protected zzd(Context context, Looper looper, int i, zzf zzf2, zzg zzg2, String str) {
        this(context, looper, zzag.zza(context), zzf.zza(), i, (zzf) zzbq.zza(zzf2), (zzg) zzbq.zza(zzg2), null);
    }

    protected zzd(Context context, Looper looper, zzag zzag, zzf zzf2, int i, zzf zzf3, zzg zzg2, String str) {
        this.zzn = new Object();
        this.zzo = new Object();
        this.zzr = new ArrayList<>();
        this.zzt = 1;
        this.zzy = null;
        this.zzz = false;
        this.zzc = new AtomicInteger(0);
        this.zzj = (Context) zzbq.zza(context, (Object) "Context must not be null");
        this.zzk = (Looper) zzbq.zza(looper, (Object) "Looper must not be null");
        this.zzl = (zzag) zzbq.zza(zzag, (Object) "Supervisor must not be null");
        this.zzm = (zzf) zzbq.zza(zzf2, (Object) "API availability must not be null");
        this.zza = new zzh(this, looper);
        this.zzw = i;
        this.zzu = zzf3;
        this.zzv = zzg2;
        this.zzx = str;
    }

    /* access modifiers changed from: private */
    public final boolean zza(int i, int i2, T t) {
        boolean z;
        synchronized (this.zzn) {
            if (this.zzt != i) {
                z = false;
            } else {
                zzb(i2, t);
                z = true;
            }
        }
        return z;
    }

    /* access modifiers changed from: private */
    public final void zzb(int i, T t) {
        boolean z = true;
        if ((i == 4) != (t != null)) {
            z = false;
        }
        zzbq.zzb(z);
        synchronized (this.zzn) {
            this.zzt = i;
            this.zzq = t;
            zza(i, t);
            switch (i) {
                case 1:
                    if (this.zzs != null) {
                        this.zzl.zza(zza(), zzy(), 129, this.zzs, zzi());
                        this.zzs = null;
                        break;
                    }
                    break;
                case 2:
                case 3:
                    if (!(this.zzs == null || this.zzi == null)) {
                        String zza2 = this.zzi.zza();
                        String zzb2 = this.zzi.zzb();
                        Log.e("GmsClient", new StringBuilder(String.valueOf(zza2).length() + 70 + String.valueOf(zzb2).length()).append("Calling connect() while still connected, missing disconnect() for ").append(zza2).append(" on ").append(zzb2).toString());
                        this.zzl.zza(this.zzi.zza(), this.zzi.zzb(), this.zzi.zzc(), this.zzs, zzi());
                        this.zzc.incrementAndGet();
                    }
                    this.zzs = new zzl(this, this.zzc.get());
                    this.zzi = new zzam(zzy(), zza(), false, 129);
                    if (!this.zzl.zza(new zzah(this.zzi.zza(), this.zzi.zzb(), this.zzi.zzc()), (ServiceConnection) this.zzs, zzi())) {
                        String zza3 = this.zzi.zza();
                        String zzb3 = this.zzi.zzb();
                        Log.e("GmsClient", new StringBuilder(String.valueOf(zza3).length() + 34 + String.valueOf(zzb3).length()).append("unable to connect to service: ").append(zza3).append(" on ").append(zzb3).toString());
                        zza(16, (Bundle) null, this.zzc.get());
                        break;
                    }
                    break;
                case 4:
                    zza(t);
                    break;
            }
        }
    }

    /* access modifiers changed from: private */
    @Hide
    public final void zzc(int i) {
        int i2;
        if (zzj()) {
            i2 = 5;
            this.zzz = true;
        } else {
            i2 = 4;
        }
        this.zza.sendMessage(this.zza.obtainMessage(i2, this.zzc.get(), 16));
    }

    @Nullable
    @Hide
    private final String zzi() {
        return this.zzx == null ? this.zzj.getClass().getName() : this.zzx;
    }

    @Hide
    private final boolean zzj() {
        boolean z;
        synchronized (this.zzn) {
            z = this.zzt == 3;
        }
        return z;
    }

    /* access modifiers changed from: private */
    public final boolean zzk() {
        if (this.zzz || TextUtils.isEmpty(zzb()) || TextUtils.isEmpty(null)) {
            return false;
        }
        try {
            Class.forName(zzb());
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /* renamed from: l_ */
    public boolean mo11779l_() {
        return false;
    }

    /* renamed from: q_ */
    public Bundle mo11780q_() {
        return null;
    }

    /* access modifiers changed from: protected */
    @Nullable
    @Hide
    public abstract T zza(IBinder iBinder);

    /* access modifiers changed from: protected */
    @Hide
    @NonNull
    public abstract String zza();

    /* access modifiers changed from: protected */
    @CallSuper
    public void zza(int i) {
        this.zzd = i;
        this.zze = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    @Hide
    public final void zza(int i, @Nullable Bundle bundle, int i2) {
        this.zza.sendMessage(this.zza.obtainMessage(7, i2, -1, new zzo(this, i, null)));
    }

    /* access modifiers changed from: protected */
    public void zza(int i, IBinder iBinder, Bundle bundle, int i2) {
        this.zza.sendMessage(this.zza.obtainMessage(1, i2, -1, new zzn(this, i, iBinder, bundle)));
    }

    /* access modifiers changed from: 0000 */
    public void zza(int i, T t) {
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void zza(@NonNull T t) {
        this.zzf = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void zza(ConnectionResult connectionResult) {
        this.zzg = connectionResult.getErrorCode();
        this.zzh = System.currentTimeMillis();
    }

    @WorkerThread
    @Hide
    public final void zza(zzan zzan, Set<Scope> set) {
        Bundle zzc2 = zzc();
        zzz zzz2 = new zzz(this.zzw);
        zzz2.zza = this.zzj.getPackageName();
        zzz2.zzd = zzc2;
        if (set != null) {
            zzz2.zzc = (Scope[]) set.toArray(new Scope[set.size()]);
        }
        if (mo11779l_()) {
            zzz2.zze = zzac() != null ? zzac() : new Account("<<default account>>", "com.google");
            if (zzan != null) {
                zzz2.zzb = zzan.asBinder();
            }
        } else if (zzag()) {
            zzz2.zze = zzac();
        }
        zzz2.zzf = zzad();
        try {
            synchronized (this.zzo) {
                if (this.zzp != null) {
                    this.zzp.zza(new zzk(this, this.zzc.get()), zzz2);
                } else {
                    Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                }
            }
        } catch (DeadObjectException e) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            zzb(1);
        } catch (SecurityException e2) {
            throw e2;
        } catch (RemoteException | RuntimeException e3) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e3);
            zza(8, (IBinder) null, (Bundle) null, this.zzc.get());
        }
    }

    public void zza(@NonNull zzj zzj2) {
        this.zzb = (zzj) zzbq.zza(zzj2, (Object) "Connection progress callbacks cannot be null.");
        zzb(2, null);
    }

    /* access modifiers changed from: protected */
    public final void zza(@NonNull zzj zzj2, int i, @Nullable PendingIntent pendingIntent) {
        this.zzb = (zzj) zzbq.zza(zzj2, (Object) "Connection progress callbacks cannot be null.");
        this.zza.sendMessage(this.zza.obtainMessage(3, this.zzc.get(), i, pendingIntent));
    }

    public void zza(@NonNull zzp zzp2) {
        zzp2.zza();
    }

    public final void zza(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int i;
        T t;
        zzay zzay;
        synchronized (this.zzn) {
            i = this.zzt;
            t = this.zzq;
        }
        synchronized (this.zzo) {
            zzay = this.zzp;
        }
        printWriter.append(str).append("mConnectState=");
        switch (i) {
            case 1:
                printWriter.print("DISCONNECTED");
                break;
            case 2:
                printWriter.print("REMOTE_CONNECTING");
                break;
            case 3:
                printWriter.print("LOCAL_CONNECTING");
                break;
            case 4:
                printWriter.print("CONNECTED");
                break;
            case 5:
                printWriter.print("DISCONNECTING");
                break;
            default:
                printWriter.print("UNKNOWN");
                break;
        }
        printWriter.append(" mService=");
        if (t == null) {
            printWriter.append("null");
        } else {
            printWriter.append(zzb()).append("@").append(Integer.toHexString(System.identityHashCode(t.asBinder())));
        }
        printWriter.append(" mServiceBroker=");
        if (zzay == null) {
            printWriter.println("null");
        } else {
            printWriter.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(zzay.asBinder())));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (this.zzf > 0) {
            PrintWriter append = printWriter.append(str).append("lastConnectedTime=");
            long j = this.zzf;
            String format = simpleDateFormat.format(new Date(this.zzf));
            append.println(new StringBuilder(String.valueOf(format).length() + 21).append(j).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(format).toString());
        }
        if (this.zze > 0) {
            printWriter.append(str).append("lastSuspendedCause=");
            switch (this.zzd) {
                case 1:
                    printWriter.append("CAUSE_SERVICE_DISCONNECTED");
                    break;
                case 2:
                    printWriter.append("CAUSE_NETWORK_LOST");
                    break;
                default:
                    printWriter.append(String.valueOf(this.zzd));
                    break;
            }
            PrintWriter append2 = printWriter.append(" lastSuspendedTime=");
            long j2 = this.zze;
            String format2 = simpleDateFormat.format(new Date(this.zze));
            append2.println(new StringBuilder(String.valueOf(format2).length() + 21).append(j2).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(format2).toString());
        }
        if (this.zzh > 0) {
            printWriter.append(str).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.zzg));
            PrintWriter append3 = printWriter.append(" lastFailedTime=");
            long j3 = this.zzh;
            String format3 = simpleDateFormat.format(new Date(this.zzh));
            append3.println(new StringBuilder(String.valueOf(format3).length() + 21).append(j3).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(format3).toString());
        }
    }

    @Hide
    public final Context zzaa() {
        return this.zzj;
    }

    @Hide
    public final Looper zzab() {
        return this.zzk;
    }

    public Account zzac() {
        return null;
    }

    public zzc[] zzad() {
        return new zzc[0];
    }

    /* access modifiers changed from: protected */
    @Hide
    public final void zzae() {
        if (!zzs()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    @Hide
    public final T zzaf() throws DeadObjectException {
        T t;
        synchronized (this.zzn) {
            if (this.zzt == 5) {
                throw new DeadObjectException();
            }
            zzae();
            zzbq.zza(this.zzq != null, (Object) "Client is connected but service is null");
            t = this.zzq;
        }
        return t;
    }

    public boolean zzag() {
        return false;
    }

    /* access modifiers changed from: protected */
    public Set<Scope> zzah() {
        return Collections.EMPTY_SET;
    }

    /* access modifiers changed from: protected */
    @Hide
    @NonNull
    public abstract String zzb();

    @Hide
    public final void zzb(int i) {
        this.zza.sendMessage(this.zza.obtainMessage(6, this.zzc.get(), i));
    }

    /* access modifiers changed from: protected */
    @Hide
    public Bundle zzc() {
        return new Bundle();
    }

    public boolean zze() {
        return false;
    }

    public Intent zzf() {
        throw new UnsupportedOperationException("Not a sign in API");
    }

    public void zzg() {
        this.zzc.incrementAndGet();
        synchronized (this.zzr) {
            int size = this.zzr.size();
            for (int i = 0; i < size; i++) {
                ((zzi) this.zzr.get(i)).zze();
            }
            this.zzr.clear();
        }
        synchronized (this.zzo) {
            this.zzp = null;
        }
        zzb(1, null);
    }

    public final boolean zzs() {
        boolean z;
        synchronized (this.zzn) {
            z = this.zzt == 4;
        }
        return z;
    }

    public final boolean zzt() {
        boolean z;
        synchronized (this.zzn) {
            z = this.zzt == 2 || this.zzt == 3;
        }
        return z;
    }

    public boolean zzu() {
        return true;
    }

    @Nullable
    public final IBinder zzv() {
        IBinder asBinder;
        synchronized (this.zzo) {
            asBinder = this.zzp == null ? null : this.zzp.asBinder();
        }
        return asBinder;
    }

    @Hide
    public final String zzw() {
        if (zzs() && this.zzi != null) {
            return this.zzi.zzb();
        }
        throw new RuntimeException("Failed to connect when checking package");
    }

    /* access modifiers changed from: protected */
    @Hide
    public String zzy() {
        return "com.google.android.gms";
    }

    public final void zzz() {
        int isGooglePlayServicesAvailable = this.zzm.isGooglePlayServicesAvailable(this.zzj);
        if (isGooglePlayServicesAvailable != 0) {
            zzb(1, null);
            zza((zzj) new zzm(this), isGooglePlayServicesAvailable, (PendingIntent) null);
            return;
        }
        zza((zzj) new zzm(this));
    }
}
