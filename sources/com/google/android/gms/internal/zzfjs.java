package com.google.android.gms.internal;

import java.util.Arrays;
import java.util.Stack;

final class zzfjs {
    private final Stack<zzfgs> zza;

    private zzfjs() {
        this.zza = new Stack<>();
    }

    private static int zza(int i) {
        int binarySearch = Arrays.binarySearch(zzfjq.zzb, i);
        return binarySearch < 0 ? (-(binarySearch + 1)) - 1 : binarySearch;
    }

    /* access modifiers changed from: private */
    public final zzfgs zza(zzfgs zzfgs, zzfgs zzfgs2) {
        zza(zzfgs);
        zza(zzfgs2);
        zzfgs zzfgs3 = (zzfgs) this.zza.pop();
        while (!this.zza.isEmpty()) {
            zzfgs3 = new zzfjq((zzfgs) this.zza.pop(), zzfgs3);
        }
        return zzfgs3;
    }

    private final void zza(zzfgs zzfgs) {
        zzfgs zzfgs2 = zzfgs;
        while (!zzfgs2.zzf()) {
            if (zzfgs2 instanceof zzfjq) {
                zzfjq zzfjq = (zzfjq) zzfgs2;
                zza(zzfjq.zzd);
                zzfgs2 = zzfjq.zze;
            } else {
                String valueOf = String.valueOf(zzfgs2.getClass());
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 49).append("Has a new type of ByteString been created? Found ").append(valueOf).toString());
            }
        }
        int zza2 = zza(zzfgs2.zza());
        int i = zzfjq.zzb[zza2 + 1];
        if (this.zza.isEmpty() || ((zzfgs) this.zza.peek()).zza() >= i) {
            this.zza.push(zzfgs2);
            return;
        }
        int i2 = zzfjq.zzb[zza2];
        zzfgs zzfgs3 = (zzfgs) this.zza.pop();
        while (!this.zza.isEmpty() && ((zzfgs) this.zza.peek()).zza() < i2) {
            zzfgs3 = new zzfjq((zzfgs) this.zza.pop(), zzfgs3);
        }
        zzfjq zzfjq2 = new zzfjq(zzfgs3, zzfgs2);
        while (!this.zza.isEmpty()) {
            if (((zzfgs) this.zza.peek()).zza() >= zzfjq.zzb[zza(zzfjq2.zza()) + 1]) {
                break;
            }
            zzfjq2 = new zzfjq((zzfgs) this.zza.pop(), zzfjq2);
        }
        this.zza.push(zzfjq2);
    }
}
