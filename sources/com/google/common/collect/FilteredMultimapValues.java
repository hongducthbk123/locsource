package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import javax.annotation.Nullable;

@GwtCompatible
final class FilteredMultimapValues<K, V> extends AbstractCollection<V> {
    private final FilteredMultimap<K, V> multimap;

    FilteredMultimapValues(FilteredMultimap<K, V> multimap2) {
        this.multimap = (FilteredMultimap) Preconditions.checkNotNull(multimap2);
    }

    public Iterator<V> iterator() {
        return Maps.valueIterator(this.multimap.entries().iterator());
    }

    public boolean contains(@Nullable Object o) {
        return this.multimap.containsValue(o);
    }

    public int size() {
        return this.multimap.size();
    }

    public boolean remove(@Nullable Object o) {
        Predicate<? super Entry<K, V>> entryPredicate = this.multimap.entryPredicate();
        Iterator<Entry<K, V>> unfilteredItr = this.multimap.unfiltered().entries().iterator();
        while (unfilteredItr.hasNext()) {
            Entry<K, V> entry = (Entry) unfilteredItr.next();
            if (entryPredicate.apply(entry) && Objects.equal(entry.getValue(), o)) {
                unfilteredItr.remove();
                return true;
            }
        }
        return false;
    }

    public boolean removeAll(Collection<?> c) {
        return Iterables.removeIf(this.multimap.unfiltered().entries(), Predicates.and(this.multimap.entryPredicate(), Maps.valuePredicateOnEntries(Predicates.m1491in(c))));
    }

    public boolean retainAll(Collection<?> c) {
        return Iterables.removeIf(this.multimap.unfiltered().entries(), Predicates.and(this.multimap.entryPredicate(), Maps.valuePredicateOnEntries(Predicates.not(Predicates.m1491in(c)))));
    }

    public void clear() {
        this.multimap.clear();
    }
}