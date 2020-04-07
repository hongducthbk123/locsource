package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

final class zzfjt implements Iterator<zzfgy> {
    private final Stack<zzfjq> zza;
    private zzfgy zzb;

    private zzfjt(zzfgs zzfgs) {
        this.zza = new Stack<>();
        this.zzb = zza(zzfgs);
    }

    private final zzfgy zza() {
        while (!this.zza.isEmpty()) {
            zzfgy zza2 = zza(((zzfjq) this.zza.pop()).zze);
            if (!zza2.zzb()) {
                return zza2;
            }
        }
        return null;
    }

    private final zzfgy zza(zzfgs zzfgs) {
        zzfgs zzfgs2 = zzfgs;
        while (zzfgs2 instanceof zzfjq) {
            zzfjq zzfjq = (zzfjq) zzfgs2;
            this.zza.push(zzfjq);
            zzfgs2 = zzfjq.zzd;
        }
        return (zzfgy) zzfgs2;
    }

    public final boolean hasNext() {
        return this.zzb != null;
    }

    public final /* synthetic */ Object next() {
        if (this.zzb == null) {
            throw new NoSuchElementException();
        }
        zzfgy zzfgy = this.zzb;
        this.zzb = zza();
        return zzfgy;
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
