package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableSortedMap<K, V> extends ImmutableSortedMapFauxverideShim<K, V> implements SortedMap<K, V> {
    private static final ImmutableSortedMap<Comparable, Object> NATURAL_EMPTY_MAP = new EmptyImmutableSortedMap(NATURAL_ORDER);
    private static final Comparator<Comparable> NATURAL_ORDER = Ordering.natural();
    private static final long serialVersionUID = 0;
    private transient ImmutableSortedMap<K, V> descendingMap;

    public static class Builder<K, V> extends com.google.common.collect.ImmutableMap.Builder<K, V> {
        private final Comparator<? super K> comparator;

        public Builder(Comparator<? super K> comparator2) {
            this.comparator = (Comparator) Preconditions.checkNotNull(comparator2);
        }

        public Builder<K, V> put(K key, V value) {
            super.put(key, value);
            return this;
        }

        public Builder<K, V> put(Entry<? extends K, ? extends V> entry) {
            super.put(entry);
            return this;
        }

        public Builder<K, V> putAll(Map<? extends K, ? extends V> map) {
            super.putAll(map);
            return this;
        }

        public ImmutableSortedMap<K, V> build() {
            return ImmutableSortedMap.fromEntries(this.comparator, false, this.size, this.entries);
        }
    }

    private static class SerializedForm extends SerializedForm {
        private static final long serialVersionUID = 0;
        private final Comparator<Object> comparator;

        SerializedForm(ImmutableSortedMap<?, ?> sortedMap) {
            super(sortedMap);
            this.comparator = sortedMap.comparator();
        }

        /* access modifiers changed from: 0000 */
        public Object readResolve() {
            return createMap(new Builder<>(this.comparator));
        }
    }

    /* access modifiers changed from: 0000 */
    public abstract ImmutableSortedMap<K, V> createDescendingMap();

    public abstract ImmutableSortedMap<K, V> headMap(K k, boolean z);

    public abstract ImmutableSortedSet<K> keySet();

    public abstract ImmutableSortedMap<K, V> tailMap(K k, boolean z);

    public abstract ImmutableCollection<V> values();

    static <K, V> ImmutableSortedMap<K, V> emptyMap(Comparator<? super K> comparator) {
        if (Ordering.natural().equals(comparator)) {
            return m1562of();
        }
        return new EmptyImmutableSortedMap(comparator);
    }

    static <K, V> ImmutableSortedMap<K, V> fromSortedEntries(Comparator<? super K> comparator, int size, Entry<K, V>[] entries) {
        if (size == 0) {
            return emptyMap(comparator);
        }
        com.google.common.collect.ImmutableList.Builder<K> keyBuilder = ImmutableList.builder();
        com.google.common.collect.ImmutableList.Builder<V> valueBuilder = ImmutableList.builder();
        for (int i = 0; i < size; i++) {
            Entry<K, V> entry = entries[i];
            keyBuilder.add(entry.getKey());
            valueBuilder.add(entry.getValue());
        }
        return new RegularImmutableSortedMap(new RegularImmutableSortedSet(keyBuilder.build(), comparator), valueBuilder.build());
    }

    static <K, V> ImmutableSortedMap<K, V> from(ImmutableSortedSet<K> keySet, ImmutableList<V> valueList) {
        if (keySet.isEmpty()) {
            return emptyMap(keySet.comparator());
        }
        return new RegularImmutableSortedMap((RegularImmutableSortedSet) keySet, valueList);
    }

    /* renamed from: of */
    public static <K, V> ImmutableSortedMap<K, V> m1562of() {
        return NATURAL_EMPTY_MAP;
    }

    /* renamed from: of */
    public static <K extends Comparable<? super K>, V> ImmutableSortedMap<K, V> m1563of(K k1, V v1) {
        return from(ImmutableSortedSet.m1587of(k1), ImmutableList.m1512of(v1));
    }

    /* renamed from: of */
    public static <K extends Comparable<? super K>, V> ImmutableSortedMap<K, V> m1564of(K k1, V v1, K k2, V v2) {
        return fromEntries(Ordering.natural(), false, 2, entryOf(k1, v1), entryOf(k2, v2));
    }

    /* renamed from: of */
    public static <K extends Comparable<? super K>, V> ImmutableSortedMap<K, V> m1565of(K k1, V v1, K k2, V v2, K k3, V v3) {
        return fromEntries(Ordering.natural(), false, 3, entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3));
    }

    /* renamed from: of */
    public static <K extends Comparable<? super K>, V> ImmutableSortedMap<K, V> m1566of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        return fromEntries(Ordering.natural(), false, 4, entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4));
    }

    /* renamed from: of */
    public static <K extends Comparable<? super K>, V> ImmutableSortedMap<K, V> m1567of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        return fromEntries(Ordering.natural(), false, 5, entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5));
    }

    public static <K, V> ImmutableSortedMap<K, V> copyOf(Map<? extends K, ? extends V> map) {
        return copyOfInternal(map, Ordering.natural());
    }

    public static <K, V> ImmutableSortedMap<K, V> copyOf(Map<? extends K, ? extends V> map, Comparator<? super K> comparator) {
        return copyOfInternal(map, (Comparator) Preconditions.checkNotNull(comparator));
    }

    public static <K, V> ImmutableSortedMap<K, V> copyOfSorted(SortedMap<K, ? extends V> map) {
        Comparator<Comparable> comparator = map.comparator();
        if (comparator == null) {
            comparator = NATURAL_ORDER;
        }
        return copyOfInternal(map, comparator);
    }

    private static <K, V> ImmutableSortedMap<K, V> copyOfInternal(Map<? extends K, ? extends V> map, Comparator<? super K> comparator) {
        boolean sameComparator = false;
        if (map instanceof SortedMap) {
            Comparator<?> comparator2 = ((SortedMap) map).comparator();
            if (comparator2 != null) {
                sameComparator = comparator.equals(comparator2);
            } else if (comparator == NATURAL_ORDER) {
                sameComparator = true;
            } else {
                sameComparator = false;
            }
        }
        if (sameComparator && (map instanceof ImmutableSortedMap)) {
            ImmutableSortedMap<K, V> kvMap = (ImmutableSortedMap) map;
            if (!kvMap.isPartialView()) {
                return kvMap;
            }
        }
        Entry<K, V>[] entries = (Entry[]) map.entrySet().toArray(new Entry[0]);
        return fromEntries(comparator, sameComparator, entries.length, entries);
    }

    static <K, V> ImmutableSortedMap<K, V> fromEntries(Comparator<? super K> comparator, boolean sameComparator, int size, Entry<K, V>... entries) {
        for (int i = 0; i < size; i++) {
            Entry<K, V> entry = entries[i];
            entries[i] = entryOf(entry.getKey(), entry.getValue());
        }
        if (!sameComparator) {
            sortEntries(comparator, size, entries);
            validateEntries(size, entries, comparator);
        }
        return fromSortedEntries(comparator, size, entries);
    }

    private static <K, V> void sortEntries(Comparator<? super K> comparator, int size, Entry<K, V>[] entries) {
        Arrays.sort(entries, 0, size, Ordering.from(comparator).onKeys());
    }

    private static <K, V> void validateEntries(int size, Entry<K, V>[] entries, Comparator<? super K> comparator) {
        for (int i = 1; i < size; i++) {
            checkNoConflict(comparator.compare(entries[i + -1].getKey(), entries[i].getKey()) != 0, "key", entries[i - 1], entries[i]);
        }
    }

    public static <K extends Comparable<?>, V> Builder<K, V> naturalOrder() {
        return new Builder<>(Ordering.natural());
    }

    public static <K, V> Builder<K, V> orderedBy(Comparator<K> comparator) {
        return new Builder<>(comparator);
    }

    public static <K extends Comparable<?>, V> Builder<K, V> reverseOrder() {
        return new Builder<>(Ordering.natural().reverse());
    }

    ImmutableSortedMap() {
    }

    ImmutableSortedMap(ImmutableSortedMap<K, V> descendingMap2) {
        this.descendingMap = descendingMap2;
    }

    public int size() {
        return values().size();
    }

    public boolean containsValue(@Nullable Object value) {
        return values().contains(value);
    }

    /* access modifiers changed from: 0000 */
    public boolean isPartialView() {
        return keySet().isPartialView() || values().isPartialView();
    }

    public ImmutableSet<Entry<K, V>> entrySet() {
        return super.entrySet();
    }

    public Comparator<? super K> comparator() {
        return keySet().comparator();
    }

    public K firstKey() {
        return keySet().first();
    }

    public K lastKey() {
        return keySet().last();
    }

    public ImmutableSortedMap<K, V> headMap(K toKey) {
        return headMap(toKey, false);
    }

    public ImmutableSortedMap<K, V> subMap(K fromKey, K toKey) {
        return subMap(fromKey, true, toKey, false);
    }

    public ImmutableSortedMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
        boolean z;
        Preconditions.checkNotNull(fromKey);
        Preconditions.checkNotNull(toKey);
        if (comparator().compare(fromKey, toKey) <= 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "expected fromKey <= toKey but %s > %s", fromKey, toKey);
        return headMap(toKey, toInclusive).tailMap(fromKey, fromInclusive);
    }

    public ImmutableSortedMap<K, V> tailMap(K fromKey) {
        return tailMap(fromKey, true);
    }

    public Entry<K, V> lowerEntry(K key) {
        return headMap(key, false).lastEntry();
    }

    public K lowerKey(K key) {
        return Maps.keyOrNull(lowerEntry(key));
    }

    public Entry<K, V> floorEntry(K key) {
        return headMap(key, true).lastEntry();
    }

    public K floorKey(K key) {
        return Maps.keyOrNull(floorEntry(key));
    }

    public Entry<K, V> ceilingEntry(K key) {
        return tailMap(key, true).firstEntry();
    }

    public K ceilingKey(K key) {
        return Maps.keyOrNull(ceilingEntry(key));
    }

    public Entry<K, V> higherEntry(K key) {
        return tailMap(key, false).firstEntry();
    }

    public K higherKey(K key) {
        return Maps.keyOrNull(higherEntry(key));
    }

    public Entry<K, V> firstEntry() {
        if (isEmpty()) {
            return null;
        }
        return (Entry) entrySet().asList().get(0);
    }

    public Entry<K, V> lastEntry() {
        if (isEmpty()) {
            return null;
        }
        return (Entry) entrySet().asList().get(size() - 1);
    }

    @Deprecated
    public final Entry<K, V> pollFirstEntry() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final Entry<K, V> pollLastEntry() {
        throw new UnsupportedOperationException();
    }

    public ImmutableSortedMap<K, V> descendingMap() {
        ImmutableSortedMap<K, V> result = this.descendingMap;
        if (result != null) {
            return result;
        }
        ImmutableSortedMap<K, V> result2 = createDescendingMap();
        this.descendingMap = result2;
        return result2;
    }

    public ImmutableSortedSet<K> navigableKeySet() {
        return keySet();
    }

    public ImmutableSortedSet<K> descendingKeySet() {
        return keySet().descendingSet();
    }

    /* access modifiers changed from: 0000 */
    public Object writeReplace() {
        return new SerializedForm(this);
    }
}
