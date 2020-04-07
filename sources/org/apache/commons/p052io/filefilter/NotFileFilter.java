package org.apache.commons.p052io.filefilter;

import java.io.File;
import java.io.Serializable;

/* renamed from: org.apache.commons.io.filefilter.NotFileFilter */
public class NotFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = 6131563330944994230L;
    private final IOFileFilter filter;

    public NotFileFilter(IOFileFilter filter2) {
        if (filter2 == null) {
            throw new IllegalArgumentException("The filter must not be null");
        }
        this.filter = filter2;
    }

    public boolean accept(File file) {
        return !this.filter.accept(file);
    }

    public boolean accept(File file, String name) {
        return !this.filter.accept(file, name);
    }

    public String toString() {
        return super.toString() + "(" + this.filter.toString() + ")";
    }
}
