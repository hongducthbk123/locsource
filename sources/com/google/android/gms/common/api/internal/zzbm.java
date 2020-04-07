package com.google.android.gms.common.api.internal;

import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.support.p000v4.util.ArraySet;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzcyj;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.vending.expansion.downloader.Constants;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Hide
public final class zzbm implements Callback {
    public static final Status zza = new Status(4, "Sign-out occurred while this API call was in progress.");
    /* access modifiers changed from: private */
    public static final Status zzb = new Status(4, "The user must be signed in to make this API call.");
    /* access modifiers changed from: private */
    public static final Object zzf = new Object();
    private static zzbm zzg;
    /* access modifiers changed from: private */
    public long zzc = Constants.ACTIVE_THREAD_WATCHDOG;
    /* access modifiers changed from: private */
    public long zzd = 120000;
    /* access modifiers changed from: private */
    public long zze = 10000;
    /* access modifiers changed from: private */
    public final Context zzh;
    /* access modifiers changed from: private */
    public final GoogleApiAvailability zzi;
    /* access modifiers changed from: private */
    public int zzj = -1;
    private final AtomicInteger zzk = new AtomicInteger(1);
    private final AtomicInteger zzl = new AtomicInteger(0);
    /* access modifiers changed from: private */
    public final Map<zzh<?>, zzbo<?>> zzm = new ConcurrentHashMap(5, 0.75f, 1);
    /* access modifiers changed from: private */
    public zzah zzn = null;
    /* access modifiers changed from: private */
    public final Set<zzh<?>> zzo = new ArraySet();
    private final Set<zzh<?>> zzp = new ArraySet();
    /* access modifiers changed from: private */
    public final Handler zzq;

    private zzbm(Context context, Looper looper, GoogleApiAvailability googleApiAvailability) {
        this.zzh = context;
        this.zzq = new Handler(looper, this);
        this.zzi = googleApiAvailability;
        this.zzq.sendMessage(this.zzq.obtainMessage(6));
    }

    public static zzbm zza() {
        zzbm zzbm;
        synchronized (zzf) {
            zzbq.zza(zzg, (Object) "Must guarantee manager is non-null before using getInstance");
            zzbm = zzg;
        }
        return zzbm;
    }

    public static zzbm zza(Context context) {
        zzbm zzbm;
        synchronized (zzf) {
            if (zzg == null) {
                HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
                handlerThread.start();
                zzg = new zzbm(context.getApplicationContext(), handlerThread.getLooper(), GoogleApiAvailability.getInstance());
            }
            zzbm = zzg;
        }
        return zzbm;
    }

    public static void zzb() {
        synchronized (zzf) {
            if (zzg != null) {
                zzbm zzbm = zzg;
                zzbm.zzl.incrementAndGet();
                zzbm.zzq.sendMessageAtFrontOfQueue(zzbm.zzq.obtainMessage(10));
            }
        }
    }

    @WorkerThread
    private final void zzb(GoogleApi<?> googleApi) {
        zzh zzc2 = googleApi.zzc();
        zzbo zzbo = (zzbo) this.zzm.get(zzc2);
        if (zzbo == null) {
            zzbo = new zzbo(this, googleApi);
            this.zzm.put(zzc2, zzbo);
        }
        if (zzbo.zzk()) {
            this.zzp.add(zzc2);
        }
        zzbo.zzi();
    }

    @WorkerThread
    private final void zzh() {
        for (zzh remove : this.zzp) {
            ((zzbo) this.zzm.remove(remove)).zza();
        }
        this.zzp.clear();
    }

    @WorkerThread
    public final boolean handleMessage(Message message) {
        zzbo zzbo;
        switch (message.what) {
            case 1:
                this.zze = ((Boolean) message.obj).booleanValue() ? 10000 : 300000;
                this.zzq.removeMessages(12);
                for (zzh obtainMessage : this.zzm.keySet()) {
                    this.zzq.sendMessageDelayed(this.zzq.obtainMessage(12, obtainMessage), this.zze);
                }
                break;
            case 2:
                zzj zzj2 = (zzj) message.obj;
                Iterator it = zzj2.zza().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    } else {
                        zzh zzh2 = (zzh) it.next();
                        zzbo zzbo2 = (zzbo) this.zzm.get(zzh2);
                        if (zzbo2 == null) {
                            zzj2.zza(zzh2, new ConnectionResult(13), null);
                            break;
                        } else if (zzbo2.zzj()) {
                            zzj2.zza(zzh2, ConnectionResult.zza, zzbo2.zzb().zzw());
                        } else if (zzbo2.zze() != null) {
                            zzj2.zza(zzh2, zzbo2.zze(), null);
                        } else {
                            zzbo2.zza(zzj2);
                        }
                    }
                }
            case 3:
                for (zzbo zzbo3 : this.zzm.values()) {
                    zzbo3.zzd();
                    zzbo3.zzi();
                }
                break;
            case 4:
            case 8:
            case 13:
                zzcp zzcp = (zzcp) message.obj;
                zzbo zzbo4 = (zzbo) this.zzm.get(zzcp.zzc.zzc());
                if (zzbo4 == null) {
                    zzb(zzcp.zzc);
                    zzbo4 = (zzbo) this.zzm.get(zzcp.zzc.zzc());
                }
                if (zzbo4.zzk() && this.zzl.get() != zzcp.zzb) {
                    zzcp.zza.zza(zza);
                    zzbo4.zza();
                    break;
                } else {
                    zzbo4.zza(zzcp.zza);
                    break;
                }
            case 5:
                int i = message.arg1;
                ConnectionResult connectionResult = (ConnectionResult) message.obj;
                Iterator it2 = this.zzm.values().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        zzbo = (zzbo) it2.next();
                        if (zzbo.zzl() == i) {
                        }
                    } else {
                        zzbo = null;
                    }
                }
                if (zzbo == null) {
                    Log.wtf("GoogleApiManager", "Could not find API instance " + i + " while trying to fail enqueued calls.", new Exception());
                    break;
                } else {
                    String errorString = this.zzi.getErrorString(connectionResult.getErrorCode());
                    String errorMessage = connectionResult.getErrorMessage();
                    zzbo.zza(new Status(17, new StringBuilder(String.valueOf(errorString).length() + 69 + String.valueOf(errorMessage).length()).append("Error resolution was canceled by the user, original error message: ").append(errorString).append(": ").append(errorMessage).toString()));
                    break;
                }
            case 6:
                if (this.zzh.getApplicationContext() instanceof Application) {
                    zzk.zza((Application) this.zzh.getApplicationContext());
                    zzk.zza().zza((zzl) new zzbn(this));
                    if (!zzk.zza().zza(true)) {
                        this.zze = 300000;
                        break;
                    }
                }
                break;
            case 7:
                zzb((GoogleApi) message.obj);
                break;
            case 9:
                if (this.zzm.containsKey(message.obj)) {
                    ((zzbo) this.zzm.get(message.obj)).zzf();
                    break;
                }
                break;
            case 10:
                zzh();
                break;
            case 11:
                if (this.zzm.containsKey(message.obj)) {
                    ((zzbo) this.zzm.get(message.obj)).zzg();
                    break;
                }
                break;
            case 12:
                if (this.zzm.containsKey(message.obj)) {
                    ((zzbo) this.zzm.get(message.obj)).zzh();
                    break;
                }
                break;
            default:
                Log.w("GoogleApiManager", "Unknown message id: " + message.what);
                return false;
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public final PendingIntent zza(zzh<?> zzh2, int i) {
        zzbo zzbo = (zzbo) this.zzm.get(zzh2);
        if (zzbo == null) {
            return null;
        }
        zzcyj zzm2 = zzbo.zzm();
        if (zzm2 == null) {
            return null;
        }
        return PendingIntent.getActivity(this.zzh, i, zzm2.zzf(), 134217728);
    }

    public final <O extends ApiOptions> Task<Boolean> zza(@NonNull GoogleApi<O> googleApi, @NonNull zzck<?> zzck) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzq.sendMessage(this.zzq.obtainMessage(13, new zzcp(new zzf(zzck, taskCompletionSource), this.zzl.get(), googleApi)));
        return taskCompletionSource.getTask();
    }

    public final <O extends ApiOptions> Task<Void> zza(@NonNull GoogleApi<O> googleApi, @NonNull zzcq<zzb, ?> zzcq, @NonNull zzdo<zzb, ?> zzdo) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzq.sendMessage(this.zzq.obtainMessage(8, new zzcp(new zzd(new zzcr(zzcq, zzdo), taskCompletionSource), this.zzl.get(), googleApi)));
        return taskCompletionSource.getTask();
    }

    public final Task<Map<zzh<?>, String>> zza(Iterable<? extends GoogleApi<?>> iterable) {
        zzj zzj2 = new zzj(iterable);
        for (GoogleApi googleApi : iterable) {
            zzbo zzbo = (zzbo) this.zzm.get(googleApi.zzc());
            if (zzbo == null || !zzbo.zzj()) {
                this.zzq.sendMessage(this.zzq.obtainMessage(2, zzj2));
                return zzj2.zzb();
            }
            zzj2.zza(googleApi.zzc(), ConnectionResult.zza, zzbo.zzb().zzw());
        }
        return zzj2.zzb();
    }

    public final void zza(GoogleApi<?> googleApi) {
        this.zzq.sendMessage(this.zzq.obtainMessage(7, googleApi));
    }

    public final <O extends ApiOptions, TResult> void zza(GoogleApi<O> googleApi, int i, zzde<zzb, TResult> zzde, TaskCompletionSource<TResult> taskCompletionSource, zzda zzda) {
        this.zzq.sendMessage(this.zzq.obtainMessage(4, new zzcp(new zze(i, zzde, taskCompletionSource, zzda), this.zzl.get(), googleApi)));
    }

    public final <O extends ApiOptions> void zza(GoogleApi<O> googleApi, int i, zzm<? extends Result, zzb> zzm2) {
        this.zzq.sendMessage(this.zzq.obtainMessage(4, new zzcp(new zzc(i, zzm2), this.zzl.get(), googleApi)));
    }

    public final void zza(@NonNull zzah zzah) {
        synchronized (zzf) {
            if (this.zzn != zzah) {
                this.zzn = zzah;
                this.zzo.clear();
                this.zzo.addAll(zzah.zzf());
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza(ConnectionResult connectionResult, int i) {
        return this.zzi.zza(this.zzh, connectionResult, i);
    }

    public final void zzb(ConnectionResult connectionResult, int i) {
        if (!zza(connectionResult, i)) {
            this.zzq.sendMessage(this.zzq.obtainMessage(5, i, 0, connectionResult));
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zzb(@NonNull zzah zzah) {
        synchronized (zzf) {
            if (this.zzn == zzah) {
                this.zzn = null;
                this.zzo.clear();
            }
        }
    }

    public final int zzc() {
        return this.zzk.getAndIncrement();
    }

    public final void zzd() {
        this.zzq.sendMessage(this.zzq.obtainMessage(3));
    }

    /* access modifiers changed from: 0000 */
    public final void zze() {
        this.zzl.incrementAndGet();
        this.zzq.sendMessage(this.zzq.obtainMessage(10));
    }
}
