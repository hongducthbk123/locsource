package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Map;
import java.util.Map.Entry;

@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableBiMap<K, V> extends ImmutableMap<K, V> implements BiMap<K, V> {
    private static final Entry<?, ?>[] EMPTY_ENTRY_ARRAY = new Entry[0];

    public static final class Builder<K, V> extends com.google.common.collect.ImmutableMap.Builder<K, V> {
        public Builder<K, V> put(K key, V value) {
            super.put(key, value);
            return this;
        }

        public Builder<K, V> putAll(Map<? extends K, ? extends V> map) {
            super.putAll(map);
            return this;
        }

        public ImmutableBiMap<K, V> build() {
            switch (this.size) {
                case 0:
                    return ImmutableBiMap.m1505of();
                case 1:
                    return ImmutableBiMap.m1506of(this.entries[0].getKey(), this.entries[0].getValue());
                default:
                    return new RegularImmutableBiMap(this.size, this.entries);
            }
        }
    }

    private static class SerializedForm extends SerializedForm {
        private static final long serialVersionUID = 0;

        SerializedForm(ImmutableBiMap<?, ?> bimap) {
            super(bimap);
        }

        /* access modifiers changed from: 0000 */
        public Object readResolve() {
            return createMap(new Builder<>());
        }
    }

    public abstract ImmutableBiMap<V, K> inverse();

    /* renamed from: of */
    public static <K, V> ImmutableBiMap<K, V> m1505of() {
        return EmptyImmutableBiMap.INSTANCE;
    }

    /* renamed from: of */
    public static <K, V> ImmutableBiMap<K, V> m1506of(K k1, V v1) {
        return new SingletonImmutableBiMap(k1, v1);
    }

    /* renamed from: of */
    public static <K, V> ImmutableBiMap<K, V> m1507of(K k1, V v1, K k2, V v2) {
        return new RegularImmutableBiMap((TerminalEntry<?, ?>[]) new TerminalEntry[]{entryOf(k1, v1), entryOf(k2, v2)});
    }

    /* renamed from: of */
    public static <K, V> ImmutableBiMap<K, V> m1508of(K k1, V v1, K k2, V v2, K k3, V v3) {
        return new RegularImmutableBiMap((TerminalEntry<?, ?>[]) new TerminalEntry[]{entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3)});
    }

    /* renamed from: of */
    public static <K, V> ImmutableBiMap<K, V> m1509of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        return new RegularImmutableBiMap((TerminalEntry<?, ?>[]) new TerminalEntry[]{entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4)});
    }

    /* renamed from: of */
    public static <K, V> ImmutableBiMap<K, V> m1510of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        return new RegularImmutableBiMap((TerminalEntry<?, ?>[]) new TerminalEntry[]{entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5)});
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    public static <K, V> ImmutableBiMap<K, V> copyOf(Map<? extends K, ? extends V> map) {
        if (map instanceof ImmutableBiMap) {
            ImmutableBiMap<K, V> bimap = (ImmutableBiMap) map;
            if (!bimap.isPartialView()) {
                return bimap;
            }
        }
        Entry<K, V>[] entries = (Entry[]) map.entrySet().toArray(EMPTY_ENTRY_ARRAY);
        switch (entries.length) {
            case 0:
                return m1505of();
            case 1:
                Entry<K, V> entry = entries[0];
                return m1506of(entry.getKey(), entry.getValue());
            default:
                return new RegularImmutableBiMap<>((Entry<?, ?>[]) entries);
        }
    }

    ImmutableBiMap() {
    }

    public ImmutableSet<V> values() {
        return inverse().keySet();
    }

    @Deprecated
    public V forcePut(K k, V v) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: 0000 */
    public Object writeReplace() {
        return new SerializedForm(this);
    }
}
