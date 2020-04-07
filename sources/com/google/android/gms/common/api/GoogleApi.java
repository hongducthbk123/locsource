package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.internal.zzah;
import com.google.android.gms.common.api.internal.zzbm;
import com.google.android.gms.common.api.internal.zzbo;
import com.google.android.gms.common.api.internal.zzbw;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzcm;
import com.google.android.gms.common.api.internal.zzcq;
import com.google.android.gms.common.api.internal.zzcv;
import com.google.android.gms.common.api.internal.zzda;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.common.api.internal.zzdo;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.api.internal.zzh;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

public class GoogleApi<O extends ApiOptions> {
    @Hide
    protected final zzbm zza;
    private final Context zzb;
    private final Api<O> zzc;
    private final O zzd;
    private final zzh<O> zze;
    private final Looper zzf;
    private final int zzg;
    private final GoogleApiClient zzh;
    private final zzda zzi;

    @Hide
    public static class zza {
        public static final zza zza = new zzd().zza();
        public final zzda zzb;
        public final Looper zzc;

        private zza(zzda zzda, Account account, Looper looper) {
            this.zzb = zzda;
            this.zzc = looper;
        }
    }

    @Hide
    @MainThread
    public GoogleApi(@NonNull Activity activity, Api<O> api, O o, zza zza2) {
        zzbq.zza(activity, (Object) "Null activity is not permitted.");
        zzbq.zza(api, (Object) "Api must not be null.");
        zzbq.zza(zza2, (Object) "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        this.zzb = activity.getApplicationContext();
        this.zzc = api;
        this.zzd = o;
        this.zzf = zza2.zzc;
        this.zze = zzh.zza(this.zzc, this.zzd);
        this.zzh = new zzbw(this);
        this.zza = zzbm.zza(this.zzb);
        this.zzg = this.zza.zzc();
        this.zzi = zza2.zzb;
        zzah.zza(activity, this.zza, this.zze);
        this.zza.zza(this);
    }

    @Hide
    @Deprecated
    public GoogleApi(@NonNull Activity activity, Api<O> api, O o, zzda zzda) {
        this(activity, api, o, new zzd().zza(zzda).zza(activity.getMainLooper()).zza());
    }

    @Hide
    protected GoogleApi(@NonNull Context context, Api<O> api, Looper looper) {
        zzbq.zza(context, (Object) "Null context is not permitted.");
        zzbq.zza(api, (Object) "Api must not be null.");
        zzbq.zza(looper, (Object) "Looper must not be null.");
        this.zzb = context.getApplicationContext();
        this.zzc = api;
        this.zzd = null;
        this.zzf = looper;
        this.zze = zzh.zza(api);
        this.zzh = new zzbw(this);
        this.zza = zzbm.zza(this.zzb);
        this.zzg = this.zza.zzc();
        this.zzi = new zzg();
    }

    @Hide
    @Deprecated
    public GoogleApi(@NonNull Context context, Api<O> api, O o, Looper looper, zzda zzda) {
        this(context, api, (O) null, new zzd().zza(looper).zza(zzda).zza());
    }

    @Hide
    public GoogleApi(@NonNull Context context, Api<O> api, O o, zza zza2) {
        zzbq.zza(context, (Object) "Null context is not permitted.");
        zzbq.zza(api, (Object) "Api must not be null.");
        zzbq.zza(zza2, (Object) "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        this.zzb = context.getApplicationContext();
        this.zzc = api;
        this.zzd = o;
        this.zzf = zza2.zzc;
        this.zze = zzh.zza(this.zzc, this.zzd);
        this.zzh = new zzbw(this);
        this.zza = zzbm.zza(this.zzb);
        this.zzg = this.zza.zzc();
        this.zzi = zza2.zzb;
        this.zza.zza(this);
    }

    @Hide
    @Deprecated
    public GoogleApi(@NonNull Context context, Api<O> api, O o, zzda zzda) {
        this(context, api, o, new zzd().zza(zzda).zza());
    }

    private final <A extends zzb, T extends zzm<? extends Result, A>> T zza(int i, @NonNull T t) {
        t.zzg();
        this.zza.zza(this, i, (zzm<? extends Result, zzb>) t);
        return t;
    }

    private final <TResult, A extends zzb> Task<TResult> zza(int i, @NonNull zzde<A, TResult> zzde) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zza.zza(this, i, zzde, taskCompletionSource, this.zzi);
        return taskCompletionSource.getTask();
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x0023  */
    @com.google.android.gms.common.internal.Hide
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.common.internal.zzs zzh() {
        /*
            r2 = this;
            com.google.android.gms.common.internal.zzs r1 = new com.google.android.gms.common.internal.zzs
            r1.<init>()
            O r0 = r2.zzd
            boolean r0 = r0 instanceof com.google.android.gms.common.api.Api.ApiOptions.HasGoogleSignInAccountOptions
            if (r0 == 0) goto L_0x0036
            O r0 = r2.zzd
            com.google.android.gms.common.api.Api$ApiOptions$HasGoogleSignInAccountOptions r0 = (com.google.android.gms.common.api.Api.ApiOptions.HasGoogleSignInAccountOptions) r0
            com.google.android.gms.auth.api.signin.GoogleSignInAccount r0 = r0.getGoogleSignInAccount()
            if (r0 == 0) goto L_0x0036
            android.accounts.Account r0 = r0.getAccount()
        L_0x0019:
            com.google.android.gms.common.internal.zzs r1 = r1.zza(r0)
            O r0 = r2.zzd
            boolean r0 = r0 instanceof com.google.android.gms.common.api.Api.ApiOptions.HasGoogleSignInAccountOptions
            if (r0 == 0) goto L_0x0047
            O r0 = r2.zzd
            com.google.android.gms.common.api.Api$ApiOptions$HasGoogleSignInAccountOptions r0 = (com.google.android.gms.common.api.Api.ApiOptions.HasGoogleSignInAccountOptions) r0
            com.google.android.gms.auth.api.signin.GoogleSignInAccount r0 = r0.getGoogleSignInAccount()
            if (r0 == 0) goto L_0x0047
            java.util.Set r0 = r0.zzd()
        L_0x0031:
            com.google.android.gms.common.internal.zzs r0 = r1.zza(r0)
            return r0
        L_0x0036:
            O r0 = r2.zzd
            boolean r0 = r0 instanceof com.google.android.gms.common.api.Api.ApiOptions.HasAccountOptions
            if (r0 == 0) goto L_0x0045
            O r0 = r2.zzd
            com.google.android.gms.common.api.Api$ApiOptions$HasAccountOptions r0 = (com.google.android.gms.common.api.Api.ApiOptions.HasAccountOptions) r0
            android.accounts.Account r0 = r0.getAccount()
            goto L_0x0019
        L_0x0045:
            r0 = 0
            goto L_0x0019
        L_0x0047:
            java.util.Set r0 = java.util.Collections.emptySet()
            goto L_0x0031
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.GoogleApi.zzh():com.google.android.gms.common.internal.zzs");
    }

    @WorkerThread
    @Hide
    public zze zza(Looper looper, zzbo<O> zzbo) {
        return this.zzc.zzb().zza(this.zzb, looper, zzh().zza(this.zzb.getPackageName()).zzb(this.zzb.getClass().getName()).zza(), this.zzd, zzbo, zzbo);
    }

    @Hide
    public final Api<O> zza() {
        return this.zzc;
    }

    @Hide
    public final <L> zzci<L> zza(@NonNull L l, String str) {
        return zzcm.zzb(l, this.zzf, str);
    }

    @Hide
    public zzcv zza(Context context, Handler handler) {
        return new zzcv(context, handler, zzh().zza());
    }

    @Hide
    public final <A extends zzb, T extends zzm<? extends Result, A>> T zza(@NonNull T t) {
        return zza(0, t);
    }

    @Hide
    public final Task<Boolean> zza(@NonNull zzck<?> zzck) {
        zzbq.zza(zzck, (Object) "Listener key cannot be null.");
        return this.zza.zza(this, zzck);
    }

    @Hide
    public final <A extends zzb, T extends zzcq<A, ?>, U extends zzdo<A, ?>> Task<Void> zza(@NonNull T t, U u) {
        zzbq.zza(t);
        zzbq.zza(u);
        zzbq.zza(t.zza(), (Object) "Listener has already been released.");
        zzbq.zza(u.zza(), (Object) "Listener has already been released.");
        zzbq.zzb(t.zza().equals(u.zza()), "Listener registration and unregistration methods must be constructed with the same ListenerHolder.");
        return this.zza.zza(this, (zzcq<zzb, ?>) t, (zzdo<zzb, ?>) u);
    }

    @Hide
    public final <TResult, A extends zzb> Task<TResult> zza(zzde<A, TResult> zzde) {
        return zza(0, zzde);
    }

    @Hide
    public final O zzb() {
        return this.zzd;
    }

    @Hide
    public final <A extends zzb, T extends zzm<? extends Result, A>> T zzb(@NonNull T t) {
        return zza(1, t);
    }

    @Hide
    public final <TResult, A extends zzb> Task<TResult> zzb(zzde<A, TResult> zzde) {
        return zza(1, zzde);
    }

    @Hide
    public final zzh<O> zzc() {
        return this.zze;
    }

    @Hide
    public final <A extends zzb, T extends zzm<? extends Result, A>> T zzc(@NonNull T t) {
        return zza(2, t);
    }

    @Hide
    public final int zzd() {
        return this.zzg;
    }

    @Hide
    public final GoogleApiClient zze() {
        return this.zzh;
    }

    @Hide
    public final Looper zzf() {
        return this.zzf;
    }

    @Hide
    public final Context zzg() {
        return this.zzb;
    }
}
