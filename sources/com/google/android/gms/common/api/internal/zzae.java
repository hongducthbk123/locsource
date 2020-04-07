package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

public final class zzae {
    /* access modifiers changed from: private */
    public final Map<BasePendingResult<?>, Boolean> zza = Collections.synchronizedMap(new WeakHashMap());
    /* access modifiers changed from: private */
    public final Map<TaskCompletionSource<?>, Boolean> zzb = Collections.synchronizedMap(new WeakHashMap());

    private final void zza(boolean z, Status status) {
        HashMap hashMap;
        HashMap hashMap2;
        synchronized (this.zza) {
            hashMap = new HashMap(this.zza);
        }
        synchronized (this.zzb) {
            hashMap2 = new HashMap(this.zzb);
        }
        for (Entry entry : hashMap.entrySet()) {
            if (z || ((Boolean) entry.getValue()).booleanValue()) {
                ((BasePendingResult) entry.getKey()).zzd(status);
            }
        }
        for (Entry entry2 : hashMap2.entrySet()) {
            if (z || ((Boolean) entry2.getValue()).booleanValue()) {
                ((TaskCompletionSource) entry2.getKey()).trySetException(new ApiException(status));
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zza(BasePendingResult<? extends Result> basePendingResult, boolean z) {
        this.zza.put(basePendingResult, Boolean.valueOf(z));
        basePendingResult.zza(new zzaf(this, basePendingResult));
    }

    /* access modifiers changed from: 0000 */
    public final <TResult> void zza(TaskCompletionSource<TResult> taskCompletionSource, boolean z) {
        this.zzb.put(taskCompletionSource, Boolean.valueOf(z));
        taskCompletionSource.getTask().addOnCompleteListener(new zzag(this, taskCompletionSource));
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza() {
        return !this.zza.isEmpty() || !this.zzb.isEmpty();
    }

    public final void zzb() {
        zza(false, zzbm.zza);
    }

    public final void zzc() {
        zza(true, zzdk.zza);
    }
}
