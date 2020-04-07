package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentActivity;
import android.support.p000v4.util.ArrayMap;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

public final class zzdc extends Fragment implements zzcf {
    private static WeakHashMap<FragmentActivity, WeakReference<zzdc>> zza = new WeakHashMap<>();
    private Map<String, LifecycleCallback> zzb = new ArrayMap();
    /* access modifiers changed from: private */
    public int zzc = 0;
    /* access modifiers changed from: private */
    public Bundle zzd;

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0010, code lost:
        if (r0 != null) goto L_0x0012;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.common.api.internal.zzdc zza(android.support.p000v4.app.FragmentActivity r3) {
        /*
            java.util.WeakHashMap<android.support.v4.app.FragmentActivity, java.lang.ref.WeakReference<com.google.android.gms.common.api.internal.zzdc>> r0 = zza
            java.lang.Object r0 = r0.get(r3)
            java.lang.ref.WeakReference r0 = (java.lang.ref.WeakReference) r0
            if (r0 == 0) goto L_0x0013
            java.lang.Object r0 = r0.get()
            com.google.android.gms.common.api.internal.zzdc r0 = (com.google.android.gms.common.api.internal.zzdc) r0
            if (r0 == 0) goto L_0x0013
        L_0x0012:
            return r0
        L_0x0013:
            android.support.v4.app.FragmentManager r0 = r3.getSupportFragmentManager()     // Catch:{ ClassCastException -> 0x0048 }
            java.lang.String r1 = "SupportLifecycleFragmentImpl"
            android.support.v4.app.Fragment r0 = r0.findFragmentByTag(r1)     // Catch:{ ClassCastException -> 0x0048 }
            com.google.android.gms.common.api.internal.zzdc r0 = (com.google.android.gms.common.api.internal.zzdc) r0     // Catch:{ ClassCastException -> 0x0048 }
            if (r0 == 0) goto L_0x0027
            boolean r1 = r0.isRemoving()
            if (r1 == 0) goto L_0x003d
        L_0x0027:
            com.google.android.gms.common.api.internal.zzdc r0 = new com.google.android.gms.common.api.internal.zzdc
            r0.<init>()
            android.support.v4.app.FragmentManager r1 = r3.getSupportFragmentManager()
            android.support.v4.app.FragmentTransaction r1 = r1.beginTransaction()
            java.lang.String r2 = "SupportLifecycleFragmentImpl"
            android.support.v4.app.FragmentTransaction r1 = r1.add(r0, r2)
            r1.commitAllowingStateLoss()
        L_0x003d:
            java.util.WeakHashMap<android.support.v4.app.FragmentActivity, java.lang.ref.WeakReference<com.google.android.gms.common.api.internal.zzdc>> r1 = zza
            java.lang.ref.WeakReference r2 = new java.lang.ref.WeakReference
            r2.<init>(r0)
            r1.put(r3, r2)
            goto L_0x0012
        L_0x0048:
            r0 = move-exception
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "Fragment with tag SupportLifecycleFragmentImpl is not a SupportLifecycleFragmentImpl"
            r1.<init>(r2, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zzdc.zza(android.support.v4.app.FragmentActivity):com.google.android.gms.common.api.internal.zzdc");
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        for (LifecycleCallback zza2 : this.zzb.values()) {
            zza2.zza(str, fileDescriptor, printWriter, strArr);
        }
    }

    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        for (LifecycleCallback zza2 : this.zzb.values()) {
            zza2.zza(i, i2, intent);
        }
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzc = 1;
        this.zzd = bundle;
        for (Entry entry : this.zzb.entrySet()) {
            ((LifecycleCallback) entry.getValue()).zza(bundle != null ? bundle.getBundle((String) entry.getKey()) : null);
        }
    }

    public final void onDestroy() {
        super.onDestroy();
        this.zzc = 5;
        for (LifecycleCallback zzh : this.zzb.values()) {
            zzh.zzh();
        }
    }

    public final void onResume() {
        super.onResume();
        this.zzc = 3;
        for (LifecycleCallback zze : this.zzb.values()) {
            zze.zze();
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            for (Entry entry : this.zzb.entrySet()) {
                Bundle bundle2 = new Bundle();
                ((LifecycleCallback) entry.getValue()).zzb(bundle2);
                bundle.putBundle((String) entry.getKey(), bundle2);
            }
        }
    }

    public final void onStart() {
        super.onStart();
        this.zzc = 2;
        for (LifecycleCallback zza2 : this.zzb.values()) {
            zza2.zza();
        }
    }

    public final void onStop() {
        super.onStop();
        this.zzc = 4;
        for (LifecycleCallback zzb2 : this.zzb.values()) {
            zzb2.zzb();
        }
    }

    public final /* synthetic */ Activity zza() {
        return getActivity();
    }

    public final <T extends LifecycleCallback> T zza(String str, Class<T> cls) {
        return (LifecycleCallback) cls.cast(this.zzb.get(str));
    }

    public final void zza(String str, @NonNull LifecycleCallback lifecycleCallback) {
        if (!this.zzb.containsKey(str)) {
            this.zzb.put(str, lifecycleCallback);
            if (this.zzc > 0) {
                new Handler(Looper.getMainLooper()).post(new zzdd(this, lifecycleCallback, str));
                return;
            }
            return;
        }
        throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 59).append("LifecycleCallback with tag ").append(str).append(" already added to this fragment.").toString());
    }
}
