package com.google.android.gms.dynamic;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.zzs;

public abstract class zzp<T> {
    private final String zza;
    private T zzb;

    protected zzp(String str) {
        this.zza = str;
    }

    /* access modifiers changed from: protected */
    public abstract T zza(IBinder iBinder);

    /* access modifiers changed from: protected */
    public final T zzb(Context context) throws zzq {
        if (this.zzb == null) {
            zzbq.zza(context);
            Context remoteContext = zzs.getRemoteContext(context);
            if (remoteContext == null) {
                throw new zzq("Could not get remote context.");
            }
            try {
                this.zzb = zza((IBinder) remoteContext.getClassLoader().loadClass(this.zza).newInstance());
            } catch (ClassNotFoundException e) {
                throw new zzq("Could not load creator class.", e);
            } catch (InstantiationException e2) {
                throw new zzq("Could not instantiate creator.", e2);
            } catch (IllegalAccessException e3) {
                throw new zzq("Could not access creator.", e3);
            }
        }
        return this.zzb;
    }
}