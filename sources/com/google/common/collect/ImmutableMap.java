package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableMap<K, V> implements Map<K, V>, Serializable {
    private static final Entry<?, ?>[] EMPTY_ENTRY_ARRAY = new Entry[0];
    private transient ImmutableSet<Entry<K, V>> entrySet;
    private transient ImmutableSet<K> keySet;
    private transient ImmutableSetMultimap<K, V> multimapView;
    private transient ImmutableCollection<V> values;

    public static class Builder<K, V> {
        TerminalEntry<K, V>[] entries;
        int size;

        public Builder() {
            this(4);
        }

        Builder(int initialCapacity) {
            this.entries = new TerminalEntry[initialCapacity];
            this.size = 0;
        }

        private void ensureCapacity(int minCapacity) {
            if (minCapacity > this.entries.length) {
                this.entries = (TerminalEntry[]) ObjectArrays.arraysCopyOf(this.entries, com.google.common.collect.ImmutableCollection.Builder.expandedCapacity(this.entries.length, minCapacity));
            }
        }

        public Builder<K, V> put(K key, V value) {
            ensureCapacity(this.size + 1);
            TerminalEntry<K, V> entry = ImmutableMap.entryOf(key, value);
            TerminalEntry<K, V>[] terminalEntryArr = this.entries;
            int i = this.size;
            this.size = i + 1;
            terminalEntryArr[i] = entry;
            return this;
        }

        public Builder<K, V> put(Entry<? extends K, ? extends V> entry) {
            return put(entry.getKey(), entry.getValue());
        }

        public Builder<K, V> putAll(Map<? extends K, ? extends V> map) {
            ensureCapacity(this.size + map.size());
            for (Entry<? extends K, ? extends V> entry : map.entrySet()) {
                put(entry);
            }
            return this;
        }

        public ImmutableMap<K, V> build() {
            switch (this.size) {
                case 0:
                    return ImmutableMap.m1530of();
                case 1:
                    return ImmutableMap.m1531of(this.entries[0].getKey(), this.entries[0].getValue());
                default:
                    return new RegularImmutableMap(this.size, this.entries);
            }
        }
    }

    private static final class MapViewOfValuesAsSingletonSets<K, V> extends ImmutableMap<K, ImmutableSet<V>> {
        /* access modifiers changed from: private */
        public final ImmutableMap<K, V> delegate;

        public /* bridge */ /* synthetic */ Set entrySet() {
            return ImmutableMap.super.entrySet();
        }

        public /* bridge */ /* synthetic */ Set keySet() {
            return ImmutableMap.super.keySet();
        }

        public /* bridge */ /* synthetic */ Collection values() {
            return ImmutableMap.super.values();
        }

        MapViewOfValuesAsSingletonSets(ImmutableMap<K, V> delegate2) {
            this.delegate = (ImmutableMap) Preconditions.checkNotNull(delegate2);
        }

        public int size() {
            return this.delegate.size();
        }

        public boolean containsKey(@Nullable Object key) {
            return this.delegate.containsKey(key);
        }

        public ImmutableSet<V> get(@Nullable Object key) {
            V outerValue = this.delegate.get(key);
            if (outerValue == null) {
                return null;
            }
            return ImmutableSet.m1550of(outerValue);
        }

        /* access modifiers changed from: 0000 */
        public boolean isPartialView() {
            return false;
        }

        /* access modifiers changed from: 0000 */
        public ImmutableSet<Entry<K, ImmutableSet<V>>> createEntrySet() {
            return new ImmutableMapEntrySet<K, ImmutableSet<V>>() {
                /* access modifiers changed from: 0000 */
                public ImmutableMap<K, ImmutableSet<V>> map() {
                    return MapViewOfValuesAsSingletonSets.this;
                }

                public UnmodifiableIterator<Entry<K, ImmutableSet<V>>> iterator() {
                    final Iterator<Entry<K, V>> backingIterator = MapViewOfValuesAsSingletonSets.this.delegate.entrySet().iterator();
                    return new UnmodifiableIterator<Entry<K, ImmutableSet<V>>>() {
                        public boolean hasNext() {
                            return backingIterator.hasNext();
                        }

                        public Entry<K, ImmutableSet<V>> next() {
                            final Entry<K, V> backingEntry = (Entry) backingIterator.next();
                            return new AbstractMapEntry<K, ImmutableSet<V>>() {
                                public K getKey() {
                                    return backingEntry.getKey();
                                }

                                public ImmutableSet<V> getValue() {
                                    return ImmutableSet.m1550of(backingEntry.getValue());
                                }
                            };
                        }
                    };
                }
            };
        }
    }

    static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        private final Object[] keys;
        private final Object[] values;

        SerializedForm(ImmutableMap<?, ?> map) {
            this.keys = new Object[map.size()];
            this.values = new Object[map.size()];
            int i = 0;
            Iterator i$ = map.entrySet().iterator();
            while (i$.hasNext()) {
                Entry<?, ?> entry = (Entry) i$.next();
                this.keys[i] = entry.getKey();
                this.values[i] = entry.getValue();
                i++;
            }
        }

        /* access modifiers changed from: 0000 */
        public Object readResolve() {
            return createMap(new Builder<>());
        }

        /* access modifiers changed from: 0000 */
        public Object createMap(Builder<Object, Object> builder) {
            for (int i = 0; i < this.keys.length; i++) {
                builder.put(this.keys[i], this.values[i]);
            }
            return builder.build();
        }
    }

    /* access modifiers changed from: 0000 */
    public abstract ImmutableSet<Entry<K, V>> createEntrySet();

    public abstract V get(@Nullable Object obj);

    /* access modifiers changed from: 0000 */
    public abstract boolean isPartialView();

    /* renamed from: of */
    public static <K, V> ImmutableMap<K, V> m1530of() {
        return ImmutableBiMap.m1505of();
    }

    /* renamed from: of */
    public static <K, V> ImmutableMap<K, V> m1531of(K k1, V v1) {
        return ImmutableBiMap.m1506of(k1, v1);
    }

    /* renamed from: of */
    public static <K, V> ImmutableMap<K, V> m1532of(K k1, V v1, K k2, V v2) {
        return new RegularImmutableMap((TerminalEntry<?, ?>[]) new TerminalEntry[]{entryOf(k1, v1), entryOf(k2, v2)});
    }

    /* renamed from: of */
    public static <K, V> ImmutableMap<K, V> m1533of(K k1, V v1, K k2, V v2, K k3, V v3) {
        return new RegularImmutableMap((TerminalEntry<?, ?>[]) new TerminalEntry[]{entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3)});
    }

    /* renamed from: of */
    public static <K, V> ImmutableMap<K, V> m1534of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        return new RegularImmutableMap((TerminalEntry<?, ?>[]) new TerminalEntry[]{entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4)});
    }

    /* renamed from: of */
    public static <K, V> ImmutableMap<K, V> m1535of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        return new RegularImmutableMap((TerminalEntry<?, ?>[]) new TerminalEntry[]{entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5)});
    }

    static <K, V> TerminalEntry<K, V> entryOf(K key, V value) {
        CollectPreconditions.checkEntryNotNull(key, value);
        return new TerminalEntry<>(key, value);
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    static void checkNoConflict(boolean safe, String conflictDescription, Entry<?, ?> entry1, Entry<?, ?> entry2) {
        if (!safe) {
            throw new IllegalArgumentException("Multiple entries with same " + conflictDescription + ": " + entry1 + " and " + entry2);
        }
    }

    public static <K, V> ImmutableMap<K, V> copyOf(Map<? extends K, ? extends V> map) {
        if ((map instanceof ImmutableMap) && !(map instanceof ImmutableSortedMap)) {
            ImmutableMap<K, V> kvMap = (ImmutableMap) map;
            if (!kvMap.isPartialView()) {
                return kvMap;
            }
        } else if (map instanceof EnumMap) {
            return copyOfEnumMapUnsafe(map);
        }
        Entry<K, V>[] entries = (Entry[]) map.entrySet().toArray(EMPTY_ENTRY_ARRAY);
        switch (entries.length) {
            case 0:
                return m1530of();
            case 1:
                Entry<K, V> onlyEntry = entries[0];
                return m1531of(onlyEntry.getKey(), onlyEntry.getValue());
            default:
                return new RegularImmutableMap<>((Entry<?, ?>[]) entries);
        }
    }

    private static <K, V> ImmutableMap<K, V> copyOfEnumMapUnsafe(Map<? extends K, ? extends V> map) {
        return copyOfEnumMap((EnumMap) map);
    }

    private static <K extends Enum<K>, V> ImmutableMap<K, V> copyOfEnumMap(Map<K, ? extends V> original) {
        EnumMap<K, V> copy = new EnumMap<>(original);
        for (Entry<?, ?> entry : copy.entrySet()) {
            CollectPreconditions.checkEntryNotNull(entry.getKey(), entry.getValue());
        }
        return ImmutableEnumMap.asImmutable(copy);
    }

    ImmutableMap() {
    }

    @Deprecated
    public final V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final V remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean containsKey(@Nullable Object key) {
        return get(key) != null;
    }

    public boolean containsValue(@Nullable Object value) {
        return values().contains(value);
    }

    public ImmutableSet<Entry<K, V>> entrySet() {
        ImmutableSet<Entry<K, V>> result = this.entrySet;
        if (result != null) {
            return result;
        }
        ImmutableSet<Entry<K, V>> result2 = createEntrySet();
        this.entrySet = result2;
        return result2;
    }

    public ImmutableSet<K> keySet() {
        ImmutableSet<K> result = this.keySet;
        if (result != null) {
            return result;
        }
        ImmutableSet<K> result2 = createKeySet();
        this.keySet = result2;
        return result2;
    }

    /* access modifiers changed from: 0000 */
    public ImmutableSet<K> createKeySet() {
        return new ImmutableMapKeySet(this);
    }

    public ImmutableCollection<V> values() {
        ImmutableCollection<V> result = this.values;
        if (result != null) {
            return result;
        }
        ImmutableCollection<V> result2 = new ImmutableMapValues<>(this);
        this.values = result2;
        return result2;
    }

    @Beta
    public ImmutableSetMultimap<K, V> asMultimap() {
        ImmutableSetMultimap<K, V> result = this.multimapView;
        if (result != null) {
            return result;
        }
        ImmutableSetMultimap<K, V> result2 = createMultimapView();
        this.multimapView = result2;
        return result2;
    }

    private ImmutableSetMultimap<K, V> createMultimapView() {
        ImmutableMap<K, ImmutableSet<V>> map = viewMapValuesAsSingletonSets();
        return new ImmutableSetMultimap<>(map, map.size(), null);
    }

    private ImmutableMap<K, ImmutableSet<V>> viewMapValuesAsSingletonSets() {
        return new MapViewOfValuesAsSingletonSets(this);
    }

    public boolean equals(@Nullable Object object) {
        return Maps.equalsImpl(this, object);
    }

    public int hashCode() {
        return entrySet().hashCode();
    }

    public String toString() {
        return Maps.toStringImpl(this);
    }

    /* access modifiers changed from: 0000 */
    public Object writeReplace() {
        return new SerializedForm(this);
    }
}
