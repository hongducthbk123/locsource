package org.apache.commons.p052io.filefilter;

import java.io.File;
import java.io.Serializable;

/* renamed from: org.apache.commons.io.filefilter.SizeFileFilter */
public class SizeFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = 7388077430788600069L;
    private final boolean acceptLarger;
    private final long size;

    public SizeFileFilter(long size2) {
        this(size2, true);
    }

    public SizeFileFilter(long size2, boolean acceptLarger2) {
        if (size2 < 0) {
            throw new IllegalArgumentException("The size must be non-negative");
        }
        this.size = size2;
        this.acceptLarger = acceptLarger2;
    }

    public boolean accept(File file) {
        boolean smaller;
        if (file.length() < this.size) {
            smaller = true;
        } else {
            smaller = false;
        }
        if (this.acceptLarger) {
            return !smaller;
        }
        return smaller;
    }

    public String toString() {
        return super.toString() + "(" + (this.acceptLarger ? ">=" : "<") + this.size + ")";
    }
}
