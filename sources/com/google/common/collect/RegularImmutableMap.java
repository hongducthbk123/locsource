package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Map.Entry;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true, serializable = true)
final class RegularImmutableMap<K, V> extends ImmutableMap<K, V> {
    private static final double MAX_LOAD_FACTOR = 1.2d;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public final transient ImmutableMapEntry<K, V>[] entries;
    private final transient int mask;
    private final transient ImmutableMapEntry<K, V>[] table;

    private class EntrySet extends ImmutableMapEntrySet<K, V> {
        private EntrySet() {
        }

        /* access modifiers changed from: 0000 */
        public ImmutableMap<K, V> map() {
            return RegularImmutableMap.this;
        }

        public UnmodifiableIterator<Entry<K, V>> iterator() {
            return asList().iterator();
        }

        /* access modifiers changed from: 0000 */
        public ImmutableList<Entry<K, V>> createAsList() {
            return new RegularImmutableAsList((ImmutableCollection<E>) this, (Object[]) RegularImmutableMap.this.entries);
        }
    }

    private static final class NonTerminalMapEntry<K, V> extends ImmutableMapEntry<K, V> {
        private final ImmutableMapEntry<K, V> nextInKeyBucket;

        NonTerminalMapEntry(K key, V value, ImmutableMapEntry<K, V> nextInKeyBucket2) {
            super(key, value);
            this.nextInKeyBucket = nextInKeyBucket2;
        }

        NonTerminalMapEntry(ImmutableMapEntry<K, V> contents, ImmutableMapEntry<K, V> nextInKeyBucket2) {
            super(contents);
            this.nextInKeyBucket = nextInKeyBucket2;
        }

        /* access modifiers changed from: 0000 */
        public ImmutableMapEntry<K, V> getNextInKeyBucket() {
            return this.nextInKeyBucket;
        }

        /* access modifiers changed from: 0000 */
        @Nullable
        public ImmutableMapEntry<K, V> getNextInValueBucket() {
            return null;
        }
    }

    RegularImmutableMap(TerminalEntry<?, ?>... theEntries) {
        this(theEntries.length, theEntries);
    }

    RegularImmutableMap(int size, TerminalEntry<?, ?>[] theEntries) {
        this.entries = createEntryArray(size);
        int tableSize = Hashing.closedTableSize(size, MAX_LOAD_FACTOR);
        this.table = createEntryArray(tableSize);
        this.mask = tableSize - 1;
        for (int entryIndex = 0; entryIndex < size; entryIndex++) {
            TerminalEntry<K, V> entry = theEntries[entryIndex];
            K key = entry.getKey();
            int tableIndex = Hashing.smear(key.hashCode()) & this.mask;
            ImmutableMapEntry<K, V> existing = this.table[tableIndex];
            ImmutableMapEntry nonTerminalMapEntry = existing == null ? entry : new NonTerminalMapEntry(entry, existing);
            this.table[tableIndex] = nonTerminalMapEntry;
            this.entries[entryIndex] = nonTerminalMapEntry;
            checkNoConflictInBucket(key, nonTerminalMapEntry, existing);
        }
    }

    RegularImmutableMap(Entry<?, ?>[] theEntries) {
        int size = theEntries.length;
        this.entries = createEntryArray(size);
        int tableSize = Hashing.closedTableSize(size, MAX_LOAD_FACTOR);
        this.table = createEntryArray(tableSize);
        this.mask = tableSize - 1;
        for (int entryIndex = 0; entryIndex < size; entryIndex++) {
            Entry<K, V> entry = theEntries[entryIndex];
            K key = entry.getKey();
            V value = entry.getValue();
            CollectPreconditions.checkEntryNotNull(key, value);
            int tableIndex = Hashing.smear(key.hashCode()) & this.mask;
            ImmutableMapEntry<K, V> existing = this.table[tableIndex];
            ImmutableMapEntry<K, V> newEntry = existing == null ? new TerminalEntry<>(key, value) : new NonTerminalMapEntry<>(key, value, existing);
            this.table[tableIndex] = newEntry;
            this.entries[entryIndex] = newEntry;
            checkNoConflictInBucket(key, newEntry, existing);
        }
    }

    private void checkNoConflictInBucket(K key, ImmutableMapEntry<K, V> entry, ImmutableMapEntry<K, V> bucketHead) {
        while (bucketHead != null) {
            checkNoConflict(!key.equals(bucketHead.getKey()), "key", entry, bucketHead);
            bucketHead = bucketHead.getNextInKeyBucket();
        }
    }

    private ImmutableMapEntry<K, V>[] createEntryArray(int size) {
        return new ImmutableMapEntry[size];
    }

    public V get(@Nullable Object key) {
        if (key == null) {
            return null;
        }
        for (ImmutableMapEntry<K, V> entry = this.table[Hashing.smear(key.hashCode()) & this.mask]; entry != null; entry = entry.getNextInKeyBucket()) {
            if (key.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    public int size() {
        return this.entries.length;
    }

    /* access modifiers changed from: 0000 */
    public boolean isPartialView() {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public ImmutableSet<Entry<K, V>> createEntrySet() {
        return new EntrySet();
    }
}
