package org.apache.commons.p052io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.p052io.IOCase;

/* renamed from: org.apache.commons.io.filefilter.PrefixFileFilter */
public class PrefixFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = 8533897440809599867L;
    private final IOCase caseSensitivity;
    private final String[] prefixes;

    public PrefixFileFilter(String prefix) {
        this(prefix, IOCase.SENSITIVE);
    }

    public PrefixFileFilter(String prefix, IOCase caseSensitivity2) {
        if (prefix == null) {
            throw new IllegalArgumentException("The prefix must not be null");
        }
        this.prefixes = new String[]{prefix};
        if (caseSensitivity2 == null) {
            caseSensitivity2 = IOCase.SENSITIVE;
        }
        this.caseSensitivity = caseSensitivity2;
    }

    public PrefixFileFilter(String[] prefixes2) {
        this(prefixes2, IOCase.SENSITIVE);
    }

    public PrefixFileFilter(String[] prefixes2, IOCase caseSensitivity2) {
        if (prefixes2 == null) {
            throw new IllegalArgumentException("The array of prefixes must not be null");
        }
        this.prefixes = new String[prefixes2.length];
        System.arraycopy(prefixes2, 0, this.prefixes, 0, prefixes2.length);
        if (caseSensitivity2 == null) {
            caseSensitivity2 = IOCase.SENSITIVE;
        }
        this.caseSensitivity = caseSensitivity2;
    }

    public PrefixFileFilter(List<String> prefixes2) {
        this(prefixes2, IOCase.SENSITIVE);
    }

    public PrefixFileFilter(List<String> prefixes2, IOCase caseSensitivity2) {
        if (prefixes2 == null) {
            throw new IllegalArgumentException("The list of prefixes must not be null");
        }
        this.prefixes = (String[]) prefixes2.toArray(new String[prefixes2.size()]);
        if (caseSensitivity2 == null) {
            caseSensitivity2 = IOCase.SENSITIVE;
        }
        this.caseSensitivity = caseSensitivity2;
    }

    public boolean accept(File file) {
        String name = file.getName();
        for (String prefix : this.prefixes) {
            if (this.caseSensitivity.checkStartsWith(name, prefix)) {
                return true;
            }
        }
        return false;
    }

    public boolean accept(File file, String name) {
        for (String prefix : this.prefixes) {
            if (this.caseSensitivity.checkStartsWith(name, prefix)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(super.toString());
        buffer.append("(");
        if (this.prefixes != null) {
            for (int i = 0; i < this.prefixes.length; i++) {
                if (i > 0) {
                    buffer.append(",");
                }
                buffer.append(this.prefixes[i]);
            }
        }
        buffer.append(")");
        return buffer.toString();
    }
}
