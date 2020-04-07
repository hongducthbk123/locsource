package org.apache.commons.p052io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.p052io.IOCase;

/* renamed from: org.apache.commons.io.filefilter.NameFileFilter */
public class NameFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = 176844364689077340L;
    private final IOCase caseSensitivity;
    private final String[] names;

    public NameFileFilter(String name) {
        this(name, (IOCase) null);
    }

    public NameFileFilter(String name, IOCase caseSensitivity2) {
        if (name == null) {
            throw new IllegalArgumentException("The wildcard must not be null");
        }
        this.names = new String[]{name};
        if (caseSensitivity2 == null) {
            caseSensitivity2 = IOCase.SENSITIVE;
        }
        this.caseSensitivity = caseSensitivity2;
    }

    public NameFileFilter(String[] names2) {
        this(names2, (IOCase) null);
    }

    public NameFileFilter(String[] names2, IOCase caseSensitivity2) {
        if (names2 == null) {
            throw new IllegalArgumentException("The array of names must not be null");
        }
        this.names = new String[names2.length];
        System.arraycopy(names2, 0, this.names, 0, names2.length);
        if (caseSensitivity2 == null) {
            caseSensitivity2 = IOCase.SENSITIVE;
        }
        this.caseSensitivity = caseSensitivity2;
    }

    public NameFileFilter(List<String> names2) {
        this(names2, (IOCase) null);
    }

    public NameFileFilter(List<String> names2, IOCase caseSensitivity2) {
        if (names2 == null) {
            throw new IllegalArgumentException("The list of names must not be null");
        }
        this.names = (String[]) names2.toArray(new String[names2.size()]);
        if (caseSensitivity2 == null) {
            caseSensitivity2 = IOCase.SENSITIVE;
        }
        this.caseSensitivity = caseSensitivity2;
    }

    public boolean accept(File file) {
        String name = file.getName();
        for (String name2 : this.names) {
            if (this.caseSensitivity.checkEquals(name, name2)) {
                return true;
            }
        }
        return false;
    }

    public boolean accept(File dir, String name) {
        for (String name2 : this.names) {
            if (this.caseSensitivity.checkEquals(name, name2)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(super.toString());
        buffer.append("(");
        if (this.names != null) {
            for (int i = 0; i < this.names.length; i++) {
                if (i > 0) {
                    buffer.append(",");
                }
                buffer.append(this.names[i]);
            }
        }
        buffer.append(")");
        return buffer.toString();
    }
}
