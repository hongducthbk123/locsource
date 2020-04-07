package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.app.FragmentActivity;
import android.support.p000v4.util.ArrayMap;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.api.Api.ApiOptions.NotRequiredOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.internal.zzba;
import com.google.android.gms.common.api.internal.zzce;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzcu;
import com.google.android.gms.common.api.internal.zzdh;
import com.google.android.gms.common.api.internal.zzi;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.internal.zzt;
import com.google.android.gms.internal.zzcyg;
import com.google.android.gms.internal.zzcyj;
import com.google.android.gms.internal.zzcyk;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public abstract class GoogleApiClient {
    public static final int SIGN_IN_MODE_OPTIONAL = 2;
    public static final int SIGN_IN_MODE_REQUIRED = 1;
    /* access modifiers changed from: private */
    public static final Set<GoogleApiClient> zza = Collections.newSetFromMap(new WeakHashMap());

    public static final class Builder {
        private Account zza;
        private final Set<Scope> zzb;
        private final Set<Scope> zzc;
        private int zzd;
        private View zze;
        private String zzf;
        private String zzg;
        private final Map<Api<?>, zzt> zzh;
        private final Context zzi;
        private final Map<Api<?>, ApiOptions> zzj;
        private zzce zzk;
        private int zzl;
        private OnConnectionFailedListener zzm;
        private Looper zzn;
        private GoogleApiAvailability zzo;
        private zza<? extends zzcyj, zzcyk> zzp;
        private final ArrayList<ConnectionCallbacks> zzq;
        private final ArrayList<OnConnectionFailedListener> zzr;
        private boolean zzs;

        public Builder(@NonNull Context context) {
            this.zzb = new HashSet();
            this.zzc = new HashSet();
            this.zzh = new ArrayMap();
            this.zzj = new ArrayMap();
            this.zzl = -1;
            this.zzo = GoogleApiAvailability.getInstance();
            this.zzp = zzcyg.zza;
            this.zzq = new ArrayList<>();
            this.zzr = new ArrayList<>();
            this.zzs = false;
            this.zzi = context;
            this.zzn = context.getMainLooper();
            this.zzf = context.getPackageName();
            this.zzg = context.getClass().getName();
        }

        public Builder(@NonNull Context context, @NonNull ConnectionCallbacks connectionCallbacks, @NonNull OnConnectionFailedListener onConnectionFailedListener) {
            this(context);
            zzbq.zza(connectionCallbacks, (Object) "Must provide a connected listener");
            this.zzq.add(connectionCallbacks);
            zzbq.zza(onConnectionFailedListener, (Object) "Must provide a connection failed listener");
            this.zzr.add(onConnectionFailedListener);
        }

        private final <O extends ApiOptions> void zza(Api<O> api, O o, Scope... scopeArr) {
            HashSet hashSet = new HashSet(api.zza().zza(o));
            for (Scope add : scopeArr) {
                hashSet.add(add);
            }
            this.zzh.put(api, new zzt(hashSet));
        }

        public final Builder addApi(@NonNull Api<? extends NotRequiredOptions> api) {
            zzbq.zza(api, (Object) "Api must not be null");
            this.zzj.put(api, null);
            List zza2 = api.zza().zza(null);
            this.zzc.addAll(zza2);
            this.zzb.addAll(zza2);
            return this;
        }

        public final <O extends HasOptions> Builder addApi(@NonNull Api<O> api, @NonNull O o) {
            zzbq.zza(api, (Object) "Api must not be null");
            zzbq.zza(o, (Object) "Null options are not permitted for this Api");
            this.zzj.put(api, o);
            List zza2 = api.zza().zza(o);
            this.zzc.addAll(zza2);
            this.zzb.addAll(zza2);
            return this;
        }

        public final <O extends HasOptions> Builder addApiIfAvailable(@NonNull Api<O> api, @NonNull O o, Scope... scopeArr) {
            zzbq.zza(api, (Object) "Api must not be null");
            zzbq.zza(o, (Object) "Null options are not permitted for this Api");
            this.zzj.put(api, o);
            zza(api, o, scopeArr);
            return this;
        }

        public final Builder addApiIfAvailable(@NonNull Api<? extends NotRequiredOptions> api, Scope... scopeArr) {
            zzbq.zza(api, (Object) "Api must not be null");
            this.zzj.put(api, null);
            zza(api, null, scopeArr);
            return this;
        }

        public final Builder addConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks) {
            zzbq.zza(connectionCallbacks, (Object) "Listener must not be null");
            this.zzq.add(connectionCallbacks);
            return this;
        }

        public final Builder addOnConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener) {
            zzbq.zza(onConnectionFailedListener, (Object) "Listener must not be null");
            this.zzr.add(onConnectionFailedListener);
            return this;
        }

        public final Builder addScope(@NonNull Scope scope) {
            zzbq.zza(scope, (Object) "Scope must not be null");
            this.zzb.add(scope);
            return this;
        }

        public final GoogleApiClient build() {
            zzbq.zzb(!this.zzj.isEmpty(), "must call addApi() to add at least one API");
            zzr zza2 = zza();
            Api api = null;
            Map zzg2 = zza2.zzg();
            ArrayMap arrayMap = new ArrayMap();
            ArrayMap arrayMap2 = new ArrayMap();
            ArrayList arrayList = new ArrayList();
            boolean z = false;
            for (Api api2 : this.zzj.keySet()) {
                Object obj = this.zzj.get(api2);
                boolean z2 = zzg2.get(api2) != null;
                arrayMap.put(api2, Boolean.valueOf(z2));
                com.google.android.gms.common.api.internal.zzt zzt = new com.google.android.gms.common.api.internal.zzt(api2, z2);
                arrayList.add(zzt);
                zza zzb2 = api2.zzb();
                zze zza3 = zzb2.zza(this.zzi, this.zzn, zza2, obj, zzt, zzt);
                arrayMap2.put(api2.zzc(), zza3);
                boolean z3 = zzb2.zza() == 1 ? obj != null : z;
                if (!zza3.zze()) {
                    api2 = api;
                } else if (api != null) {
                    String zzd2 = api2.zzd();
                    String zzd3 = api.zzd();
                    throw new IllegalStateException(new StringBuilder(String.valueOf(zzd2).length() + 21 + String.valueOf(zzd3).length()).append(zzd2).append(" cannot be used with ").append(zzd3).toString());
                }
                z = z3;
                api = api2;
            }
            if (api != null) {
                if (z) {
                    String zzd4 = api.zzd();
                    throw new IllegalStateException(new StringBuilder(String.valueOf(zzd4).length() + 82).append("With using ").append(zzd4).append(", GamesOptions can only be specified within GoogleSignInOptions.Builder").toString());
                }
                zzbq.zza(this.zza == null, "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead", api.zzd());
                zzbq.zza(this.zzb.equals(this.zzc), "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead.", api.zzd());
            }
            zzba zzba = new zzba(this.zzi, new ReentrantLock(), this.zzn, zza2, this.zzo, this.zzp, arrayMap, this.zzq, this.zzr, arrayMap2, this.zzl, zzba.zza((Iterable<zze>) arrayMap2.values(), true), arrayList, false);
            synchronized (GoogleApiClient.zza) {
                GoogleApiClient.zza.add(zzba);
            }
            if (this.zzl >= 0) {
                zzi.zza(this.zzk).zza(this.zzl, zzba, this.zzm);
            }
            return zzba;
        }

        public final Builder enableAutoManage(@NonNull FragmentActivity fragmentActivity, int i, @Nullable OnConnectionFailedListener onConnectionFailedListener) {
            zzce zzce = new zzce(fragmentActivity);
            zzbq.zzb(i >= 0, "clientId must be non-negative");
            this.zzl = i;
            this.zzm = onConnectionFailedListener;
            this.zzk = zzce;
            return this;
        }

        public final Builder enableAutoManage(@NonNull FragmentActivity fragmentActivity, @Nullable OnConnectionFailedListener onConnectionFailedListener) {
            return enableAutoManage(fragmentActivity, 0, onConnectionFailedListener);
        }

        public final Builder setAccountName(String str) {
            this.zza = str == null ? null : new Account(str, "com.google");
            return this;
        }

        public final Builder setGravityForPopups(int i) {
            this.zzd = i;
            return this;
        }

        public final Builder setHandler(@NonNull Handler handler) {
            zzbq.zza(handler, (Object) "Handler must not be null");
            this.zzn = handler.getLooper();
            return this;
        }

        public final Builder setViewForPopups(@NonNull View view) {
            zzbq.zza(view, (Object) "View must not be null");
            this.zze = view;
            return this;
        }

        public final Builder useDefaultAccount() {
            return setAccountName("<<default account>>");
        }

        @Hide
        public final zzr zza() {
            zzcyk zzcyk = zzcyk.zza;
            if (this.zzj.containsKey(zzcyg.zzb)) {
                zzcyk = (zzcyk) this.zzj.get(zzcyg.zzb);
            }
            return new zzr(this.zza, this.zzb, this.zzh, this.zzd, this.zze, this.zzf, this.zzg, zzcyk);
        }
    }

    public interface ConnectionCallbacks {
        public static final int CAUSE_NETWORK_LOST = 2;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;

        void onConnected(@Nullable Bundle bundle);

        void onConnectionSuspended(int i);
    }

    public interface OnConnectionFailedListener {
        void onConnectionFailed(@NonNull ConnectionResult connectionResult);
    }

    public static void dumpAll(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        synchronized (zza) {
            String concat = String.valueOf(str).concat("  ");
            int i = 0;
            for (GoogleApiClient googleApiClient : zza) {
                int i2 = i + 1;
                printWriter.append(str).append("GoogleApiClient#").println(i);
                googleApiClient.dump(concat, fileDescriptor, printWriter, strArr);
                i = i2;
            }
        }
    }

    @Hide
    public static Set<GoogleApiClient> zza() {
        Set<GoogleApiClient> set;
        synchronized (zza) {
            set = zza;
        }
        return set;
    }

    public abstract ConnectionResult blockingConnect();

    public abstract ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit);

    public abstract PendingResult<Status> clearDefaultAccountAndReconnect();

    public abstract void connect();

    public void connect(int i) {
        throw new UnsupportedOperationException();
    }

    public abstract void disconnect();

    public abstract void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    @NonNull
    public abstract ConnectionResult getConnectionResult(@NonNull Api<?> api);

    public abstract boolean hasConnectedApi(@NonNull Api<?> api);

    public abstract boolean isConnected();

    public abstract boolean isConnecting();

    public abstract boolean isConnectionCallbacksRegistered(@NonNull ConnectionCallbacks connectionCallbacks);

    public abstract boolean isConnectionFailedListenerRegistered(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    public abstract void reconnect();

    public abstract void registerConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks);

    public abstract void registerConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    public abstract void stopAutoManage(@NonNull FragmentActivity fragmentActivity);

    public abstract void unregisterConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks);

    public abstract void unregisterConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    @Hide
    @NonNull
    public <C extends zze> C zza(@NonNull zzc<C> zzc) {
        throw new UnsupportedOperationException();
    }

    @Hide
    public <L> zzci<L> zza(@NonNull L l) {
        throw new UnsupportedOperationException();
    }

    @Hide
    public <A extends zzb, R extends Result, T extends zzm<R, A>> T zza(@NonNull T t) {
        throw new UnsupportedOperationException();
    }

    @Hide
    public void zza(zzdh zzdh) {
        throw new UnsupportedOperationException();
    }

    @Hide
    public boolean zza(@NonNull Api<?> api) {
        throw new UnsupportedOperationException();
    }

    @Hide
    public boolean zza(zzcu zzcu) {
        throw new UnsupportedOperationException();
    }

    @Hide
    public Context zzb() {
        throw new UnsupportedOperationException();
    }

    @Hide
    public <A extends zzb, T extends zzm<? extends Result, A>> T zzb(@NonNull T t) {
        throw new UnsupportedOperationException();
    }

    @Hide
    public void zzb(zzdh zzdh) {
        throw new UnsupportedOperationException();
    }

    @Hide
    public Looper zzc() {
        throw new UnsupportedOperationException();
    }

    @Hide
    public void zzd() {
        throw new UnsupportedOperationException();
    }
}
