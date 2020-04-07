package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Table.Cell;
import java.util.Map;

@GwtCompatible
class SingletonImmutableTable<R, C, V> extends ImmutableTable<R, C, V> {
    final C singleColumnKey;
    final R singleRowKey;
    final V singleValue;

    SingletonImmutableTable(R rowKey, C columnKey, V value) {
        this.singleRowKey = Preconditions.checkNotNull(rowKey);
        this.singleColumnKey = Preconditions.checkNotNull(columnKey);
        this.singleValue = Preconditions.checkNotNull(value);
    }

    SingletonImmutableTable(Cell<R, C, V> cell) {
        this(cell.getRowKey(), cell.getColumnKey(), cell.getValue());
    }

    public ImmutableMap<R, V> column(C columnKey) {
        Preconditions.checkNotNull(columnKey);
        return containsColumn(columnKey) ? ImmutableMap.m1531of(this.singleRowKey, this.singleValue) : ImmutableMap.m1530of();
    }

    public ImmutableMap<C, Map<R, V>> columnMap() {
        return ImmutableMap.m1531of(this.singleColumnKey, ImmutableMap.m1531of(this.singleRowKey, this.singleValue));
    }

    public ImmutableMap<R, Map<C, V>> rowMap() {
        return ImmutableMap.m1531of(this.singleRowKey, ImmutableMap.m1531of(this.singleColumnKey, this.singleValue));
    }

    public int size() {
        return 1;
    }

    /* access modifiers changed from: 0000 */
    public ImmutableSet<Cell<R, C, V>> createCellSet() {
        return ImmutableSet.m1550of(cellOf(this.singleRowKey, this.singleColumnKey, this.singleValue));
    }

    /* access modifiers changed from: 0000 */
    public ImmutableCollection<V> createValues() {
        return ImmutableSet.m1550of(this.singleValue);
    }
}
