package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible
abstract class AbstractMultimap<K, V> implements Multimap<K, V> {
    private transient Map<K, Collection<V>> asMap;
    private transient Collection<Entry<K, V>> entries;
    private transient Set<K> keySet;
    private transient Multiset<K> keys;
    private transient Collection<V> values;

    private class Entries extends Entries<K, V> {
        private Entries() {
        }

        /* access modifiers changed from: 0000 */
        public Multimap<K, V> multimap() {
            return AbstractMultimap.this;
        }

        public Iterator<Entry<K, V>> iterator() {
            return AbstractMultimap.this.entryIterator();
        }
    }

    private class EntrySet extends Entries implements Set<Entry<K, V>> {
        private EntrySet() {
            super();
        }

        public int hashCode() {
            return Sets.hashCodeImpl(this);
        }

        public boolean equals(@Nullable Object obj) {
            return Sets.equalsImpl(this, obj);
        }
    }

    class Values extends AbstractCollection<V> {
        Values() {
        }

        public Iterator<V> iterator() {
            return AbstractMultimap.this.valueIterator();
        }

        public int size() {
            return AbstractMultimap.this.size();
        }

        public boolean contains(@Nullable Object o) {
            return AbstractMultimap.this.containsValue(o);
        }

        public void clear() {
            AbstractMultimap.this.clear();
        }
    }

    /* access modifiers changed from: 0000 */
    public abstract Map<K, Collection<V>> createAsMap();

    /* access modifiers changed from: 0000 */
    public abstract Iterator<Entry<K, V>> entryIterator();

    AbstractMultimap() {
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean containsValue(@Nullable Object value) {
        for (Collection<V> collection : asMap().values()) {
            if (collection.contains(value)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsEntry(@Nullable Object key, @Nullable Object value) {
        Collection<V> collection = (Collection) asMap().get(key);
        return collection != null && collection.contains(value);
    }

    public boolean remove(@Nullable Object key, @Nullable Object value) {
        Collection<V> collection = (Collection) asMap().get(key);
        return collection != null && collection.remove(value);
    }

    public boolean put(@Nullable K key, @Nullable V value) {
        return get(key).add(value);
    }

    public boolean putAll(@Nullable K key, Iterable<? extends V> values2) {
        Preconditions.checkNotNull(values2);
        if (values2 instanceof Collection) {
            Collection<? extends V> valueCollection = (Collection) values2;
            if (valueCollection.isEmpty() || !get(key).addAll(valueCollection)) {
                return false;
            }
            return true;
        }
        Iterator<? extends V> valueItr = values2.iterator();
        if (!valueItr.hasNext() || !Iterators.addAll(get(key), valueItr)) {
            return false;
        }
        return true;
    }

    public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
        boolean changed = false;
        for (Entry<? extends K, ? extends V> entry : multimap.entries()) {
            changed |= put(entry.getKey(), entry.getValue());
        }
        return changed;
    }

    public Collection<V> replaceValues(@Nullable K key, Iterable<? extends V> values2) {
        Preconditions.checkNotNull(values2);
        Collection<V> result = removeAll(key);
        putAll(key, values2);
        return result;
    }

    public Collection<Entry<K, V>> entries() {
        Collection<Entry<K, V>> result = this.entries;
        if (result != null) {
            return result;
        }
        Collection<Entry<K, V>> result2 = createEntries();
        this.entries = result2;
        return result2;
    }

    /* access modifiers changed from: 0000 */
    public Collection<Entry<K, V>> createEntries() {
        if (this instanceof SetMultimap) {
            return new EntrySet();
        }
        return new Entries();
    }

    public Set<K> keySet() {
        Set<K> result = this.keySet;
        if (result != null) {
            return result;
        }
        Set<K> result2 = createKeySet();
        this.keySet = result2;
        return result2;
    }

    /* access modifiers changed from: 0000 */
    public Set<K> createKeySet() {
        return new KeySet(asMap());
    }

    public Multiset<K> keys() {
        Multiset<K> result = this.keys;
        if (result != null) {
            return result;
        }
        Multiset<K> result2 = createKeys();
        this.keys = result2;
        return result2;
    }

    /* access modifiers changed from: 0000 */
    public Multiset<K> createKeys() {
        return new Keys(this);
    }

    public Collection<V> values() {
        Collection<V> result = this.values;
        if (result != null) {
            return result;
        }
        Collection<V> result2 = createValues();
        this.values = result2;
        return result2;
    }

    /* access modifiers changed from: 0000 */
    public Collection<V> createValues() {
        return new Values();
    }

    /* access modifiers changed from: 0000 */
    public Iterator<V> valueIterator() {
        return Maps.valueIterator(entries().iterator());
    }

    public Map<K, Collection<V>> asMap() {
        Map<K, Collection<V>> result = this.asMap;
        if (result != null) {
            return result;
        }
        Map<K, Collection<V>> result2 = createAsMap();
        this.asMap = result2;
        return result2;
    }

    public boolean equals(@Nullable Object object) {
        return Multimaps.equalsImpl(this, object);
    }

    public int hashCode() {
        return asMap().hashCode();
    }

    public String toString() {
        return asMap().toString();
    }
}
