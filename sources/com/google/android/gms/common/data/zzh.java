package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.Hide;
import java.util.NoSuchElementException;

@Hide
public final class zzh<T> extends zzb<T> {
    private T zzc;

    public zzh(DataBuffer<T> dataBuffer) {
        super(dataBuffer);
    }

    public final T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Cannot advance the iterator beyond " + this.zzb);
        }
        this.zzb++;
        if (this.zzb == 0) {
            this.zzc = this.zza.get(0);
            if (!(this.zzc instanceof zzc)) {
                String valueOf = String.valueOf(this.zzc.getClass());
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 44).append("DataBuffer reference of type ").append(valueOf).append(" is not movable").toString());
            }
        } else {
            ((zzc) this.zzc).zza(this.zzb);
        }
        return this.zzc;
    }
}
