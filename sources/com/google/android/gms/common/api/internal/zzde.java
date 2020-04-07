package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class zzde<A extends zzb, TResult> {
    /* access modifiers changed from: protected */
    @Hide
    public abstract void zza(A a, TaskCompletionSource<TResult> taskCompletionSource) throws RemoteException;
}
