package com.google.android.gms.internal;

import com.google.android.gms.internal.zzfgj;
import com.google.android.gms.internal.zzfgk;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class zzfgk<MessageType extends zzfgj<MessageType, BuilderType>, BuilderType extends zzfgk<MessageType, BuilderType>> implements zzfjd {
    protected static <T> void zza(Iterable<T> iterable, List<? super T> list) {
        zzfhz.zza(iterable);
        if (iterable instanceof zzfil) {
            List zza = ((zzfil) iterable).zza();
            zzfil zzfil = (zzfil) list;
            int size = list.size();
            for (Object next : zza) {
                if (next == null) {
                    String str = "Element at index " + (zzfil.size() - size) + " is null.";
                    for (int size2 = zzfil.size() - 1; size2 >= size; size2--) {
                        zzfil.remove(size2);
                    }
                    throw new NullPointerException(str);
                } else if (next instanceof zzfgs) {
                    zzfil.zza((zzfgs) next);
                } else {
                    zzfil.add((String) next);
                }
            }
        } else if (iterable instanceof zzfjm) {
            list.addAll((Collection) iterable);
        } else {
            zzb(iterable, list);
        }
    }

    private static <T> void zzb(Iterable<T> iterable, List<? super T> list) {
        if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
            ((ArrayList) list).ensureCapacity(((Collection) iterable).size() + list.size());
        }
        int size = list.size();
        for (Object next : iterable) {
            if (next == null) {
                String str = "Element at index " + (list.size() - size) + " is null.";
                for (int size2 = list.size() - 1; size2 >= size; size2--) {
                    list.remove(size2);
                }
                throw new NullPointerException(str);
            }
            list.add(next);
        }
    }

    /* renamed from: zza */
    public abstract BuilderType clone();

    /* access modifiers changed from: protected */
    public abstract BuilderType zza(MessageType messagetype);

    /* renamed from: zza */
    public abstract BuilderType zzb(zzfhb zzfhb, zzfhm zzfhm) throws IOException;

    public final /* synthetic */ zzfjd zza(zzfjc zzfjc) {
        if (zzw().getClass().isInstance(zzfjc)) {
            return zza((MessageType) (zzfgj) zzfjc);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
