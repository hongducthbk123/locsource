package com.google.api.client.util;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

final class DataMap extends AbstractMap<String, Object> {
    final ClassInfo classInfo;
    final Object object;

    final class Entry implements java.util.Map.Entry<String, Object> {
        private final FieldInfo fieldInfo;
        private Object fieldValue;

        Entry(FieldInfo fieldInfo2, Object fieldValue2) {
            this.fieldInfo = fieldInfo2;
            this.fieldValue = Preconditions.checkNotNull(fieldValue2);
        }

        public String getKey() {
            String result = this.fieldInfo.getName();
            if (DataMap.this.classInfo.getIgnoreCase()) {
                return result.toLowerCase();
            }
            return result;
        }

        public Object getValue() {
            return this.fieldValue;
        }

        public Object setValue(Object value) {
            Object oldValue = this.fieldValue;
            this.fieldValue = Preconditions.checkNotNull(value);
            this.fieldInfo.setValue(DataMap.this.object, value);
            return oldValue;
        }

        public int hashCode() {
            return getKey().hashCode() ^ getValue().hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof java.util.Map.Entry)) {
                return false;
            }
            java.util.Map.Entry<?, ?> other = (java.util.Map.Entry) obj;
            if (!getKey().equals(other.getKey()) || !getValue().equals(other.getValue())) {
                return false;
            }
            return true;
        }
    }

    final class EntryIterator implements Iterator<java.util.Map.Entry<String, Object>> {
        private FieldInfo currentFieldInfo;
        private boolean isComputed;
        private boolean isRemoved;
        private FieldInfo nextFieldInfo;
        private Object nextFieldValue;
        private int nextKeyIndex = -1;

        EntryIterator() {
        }

        public boolean hasNext() {
            if (!this.isComputed) {
                this.isComputed = true;
                this.nextFieldValue = null;
                while (this.nextFieldValue == null) {
                    int i = this.nextKeyIndex + 1;
                    this.nextKeyIndex = i;
                    if (i >= DataMap.this.classInfo.names.size()) {
                        break;
                    }
                    this.nextFieldInfo = DataMap.this.classInfo.getFieldInfo((String) DataMap.this.classInfo.names.get(this.nextKeyIndex));
                    this.nextFieldValue = this.nextFieldInfo.getValue(DataMap.this.object);
                }
            }
            return this.nextFieldValue != null;
        }

        public java.util.Map.Entry<String, Object> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            this.currentFieldInfo = this.nextFieldInfo;
            Object currentFieldValue = this.nextFieldValue;
            this.isComputed = false;
            this.isRemoved = false;
            this.nextFieldInfo = null;
            this.nextFieldValue = null;
            return new Entry(this.currentFieldInfo, currentFieldValue);
        }

        public void remove() {
            Preconditions.checkState(this.currentFieldInfo != null && !this.isRemoved);
            this.isRemoved = true;
            this.currentFieldInfo.setValue(DataMap.this.object, null);
        }
    }

    final class EntrySet extends AbstractSet<java.util.Map.Entry<String, Object>> {
        EntrySet() {
        }

        public EntryIterator iterator() {
            return new EntryIterator();
        }

        public int size() {
            int result = 0;
            for (String name : DataMap.this.classInfo.names) {
                if (DataMap.this.classInfo.getFieldInfo(name).getValue(DataMap.this.object) != null) {
                    result++;
                }
            }
            return result;
        }

        public void clear() {
            for (String name : DataMap.this.classInfo.names) {
                DataMap.this.classInfo.getFieldInfo(name).setValue(DataMap.this.object, null);
            }
        }

        public boolean isEmpty() {
            for (String name : DataMap.this.classInfo.names) {
                if (DataMap.this.classInfo.getFieldInfo(name).getValue(DataMap.this.object) != null) {
                    return false;
                }
            }
            return true;
        }
    }

    DataMap(Object object2, boolean ignoreCase) {
        this.object = object2;
        this.classInfo = ClassInfo.m1471of(object2.getClass(), ignoreCase);
        Preconditions.checkArgument(!this.classInfo.isEnum());
    }

    public EntrySet entrySet() {
        return new EntrySet();
    }

    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    public Object get(Object key) {
        if (!(key instanceof String)) {
            return null;
        }
        FieldInfo fieldInfo = this.classInfo.getFieldInfo((String) key);
        if (fieldInfo != null) {
            return fieldInfo.getValue(this.object);
        }
        return null;
    }

    public Object put(String key, Object value) {
        FieldInfo fieldInfo = this.classInfo.getFieldInfo(key);
        Preconditions.checkNotNull(fieldInfo, "no field of key " + key);
        Object oldValue = fieldInfo.getValue(this.object);
        fieldInfo.setValue(this.object, Preconditions.checkNotNull(value));
        return oldValue;
    }
}
