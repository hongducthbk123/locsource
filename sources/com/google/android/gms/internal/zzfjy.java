package com.google.android.gms.internal;

import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class zzfjy<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private final int zza;
    /* access modifiers changed from: private */
    public List<zzfkd> zzb;
    /* access modifiers changed from: private */
    public Map<K, V> zzc;
    private boolean zzd;
    private volatile zzfkf zze;
    private Map<K, V> zzf;

    private zzfjy(int i) {
        this.zza = i;
        this.zzb = Collections.emptyList();
        this.zzc = Collections.emptyMap();
        this.zzf = Collections.emptyMap();
    }

    /* synthetic */ zzfjy(int i, zzfjz zzfjz) {
        this(i);
    }

    private final int zza(K k) {
        int size = this.zzb.size() - 1;
        if (size >= 0) {
            int compareTo = k.compareTo((Comparable) ((zzfkd) this.zzb.get(size)).getKey());
            if (compareTo > 0) {
                return -(size + 2);
            }
            if (compareTo == 0) {
                return size;
            }
        }
        int i = 0;
        int i2 = size;
        while (i <= i2) {
            int i3 = (i + i2) / 2;
            int compareTo2 = k.compareTo((Comparable) ((zzfkd) this.zzb.get(i3)).getKey());
            if (compareTo2 < 0) {
                i2 = i3 - 1;
            } else if (compareTo2 <= 0) {
                return i3;
            } else {
                i = i3 + 1;
            }
        }
        return -(i + 1);
    }

    static <FieldDescriptorType extends zzfhs<FieldDescriptorType>> zzfjy<FieldDescriptorType, Object> zza(int i) {
        return new zzfjz(i);
    }

    /* access modifiers changed from: private */
    public final V zzc(int i) {
        zze();
        V value = ((zzfkd) this.zzb.remove(i)).getValue();
        if (!this.zzc.isEmpty()) {
            Iterator it = zzf().entrySet().iterator();
            this.zzb.add(new zzfkd(this, (Entry) it.next()));
            it.remove();
        }
        return value;
    }

    /* access modifiers changed from: private */
    public final void zze() {
        if (this.zzd) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap<K, V> zzf() {
        zze();
        if (this.zzc.isEmpty() && !(this.zzc instanceof TreeMap)) {
            this.zzc = new TreeMap();
            this.zzf = ((TreeMap) this.zzc).descendingMap();
        }
        return (SortedMap) this.zzc;
    }

    public void clear() {
        zze();
        if (!this.zzb.isEmpty()) {
            this.zzb.clear();
        }
        if (!this.zzc.isEmpty()) {
            this.zzc.clear();
        }
    }

    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza((K) comparable) >= 0 || this.zzc.containsKey(comparable);
    }

    public Set<Entry<K, V>> entrySet() {
        if (this.zze == null) {
            this.zze = new zzfkf(this, null);
        }
        return this.zze;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzfjy)) {
            return super.equals(obj);
        }
        zzfjy zzfjy = (zzfjy) obj;
        int size = size();
        if (size != zzfjy.size()) {
            return false;
        }
        int zzc2 = zzc();
        if (zzc2 != zzfjy.zzc()) {
            return entrySet().equals(zzfjy.entrySet());
        }
        for (int i = 0; i < zzc2; i++) {
            if (!zzb(i).equals(zzfjy.zzb(i))) {
                return false;
            }
        }
        if (zzc2 != size) {
            return this.zzc.equals(zzfjy.zzc);
        }
        return true;
    }

    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zza2 = zza((K) comparable);
        return zza2 >= 0 ? ((zzfkd) this.zzb.get(zza2)).getValue() : this.zzc.get(comparable);
    }

    public int hashCode() {
        int i = 0;
        for (int i2 = 0; i2 < zzc(); i2++) {
            i += ((zzfkd) this.zzb.get(i2)).hashCode();
        }
        return this.zzc.size() > 0 ? this.zzc.hashCode() + i : i;
    }

    public V remove(Object obj) {
        zze();
        Comparable comparable = (Comparable) obj;
        int zza2 = zza((K) comparable);
        if (zza2 >= 0) {
            return zzc(zza2);
        }
        if (this.zzc.isEmpty()) {
            return null;
        }
        return this.zzc.remove(comparable);
    }

    public int size() {
        return this.zzb.size() + this.zzc.size();
    }

    /* renamed from: zza */
    public final V put(K k, V v) {
        zze();
        int zza2 = zza(k);
        if (zza2 >= 0) {
            return ((zzfkd) this.zzb.get(zza2)).setValue(v);
        }
        zze();
        if (this.zzb.isEmpty() && !(this.zzb instanceof ArrayList)) {
            this.zzb = new ArrayList(this.zza);
        }
        int i = -(zza2 + 1);
        if (i >= this.zza) {
            return zzf().put(k, v);
        }
        if (this.zzb.size() == this.zza) {
            zzfkd zzfkd = (zzfkd) this.zzb.remove(this.zza - 1);
            zzf().put((Comparable) zzfkd.getKey(), zzfkd.getValue());
        }
        this.zzb.add(i, new zzfkd(this, k, v));
        return null;
    }

    public void zza() {
        if (!this.zzd) {
            this.zzc = this.zzc.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzc);
            this.zzf = this.zzf.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzf);
            this.zzd = true;
        }
    }

    public final Entry<K, V> zzb(int i) {
        return (Entry) this.zzb.get(i);
    }

    public final boolean zzb() {
        return this.zzd;
    }

    public final int zzc() {
        return this.zzb.size();
    }

    public final Iterable<Entry<K, V>> zzd() {
        return this.zzc.isEmpty() ? zzfka.zza() : this.zzc.entrySet();
    }
}
