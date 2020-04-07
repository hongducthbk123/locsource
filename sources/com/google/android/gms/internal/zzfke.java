package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzfke implements Iterator<Entry<K, V>> {
    private int zza;
    private boolean zzb;
    private Iterator<Entry<K, V>> zzc;
    private /* synthetic */ zzfjy zzd;

    private zzfke(zzfjy zzfjy) {
        this.zzd = zzfjy;
        this.zza = -1;
    }

    /* synthetic */ zzfke(zzfjy zzfjy, zzfjz zzfjz) {
        this(zzfjy);
    }

    private final Iterator<Entry<K, V>> zza() {
        if (this.zzc == null) {
            this.zzc = this.zzd.zzc.entrySet().iterator();
        }
        return this.zzc;
    }

    public final boolean hasNext() {
        return this.zza + 1 < this.zzd.zzb.size() || (!this.zzd.zzc.isEmpty() && zza().hasNext());
    }

    public final /* synthetic */ Object next() {
        this.zzb = true;
        int i = this.zza + 1;
        this.zza = i;
        return i < this.zzd.zzb.size() ? (Entry) this.zzd.zzb.get(this.zza) : (Entry) zza().next();
    }

    public final void remove() {
        if (!this.zzb) {
            throw new IllegalStateException("remove() was called before next()");
        }
        this.zzb = false;
        this.zzd.zze();
        if (this.zza < this.zzd.zzb.size()) {
            zzfjy zzfjy = this.zzd;
            int i = this.zza;
            this.zza = i - 1;
            zzfjy.zzc(i);
            return;
        }
        zza().remove();
    }
}
