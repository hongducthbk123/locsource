package org.apache.commons.p052io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: org.apache.commons.io.filefilter.AndFileFilter */
public class AndFileFilter extends AbstractFileFilter implements ConditionalFileFilter, Serializable {
    private static final long serialVersionUID = 7215974688563965257L;
    private final List<IOFileFilter> fileFilters;

    public AndFileFilter() {
        this.fileFilters = new ArrayList();
    }

    public AndFileFilter(List<IOFileFilter> fileFilters2) {
        if (fileFilters2 == null) {
            this.fileFilters = new ArrayList();
        } else {
            this.fileFilters = new ArrayList(fileFilters2);
        }
    }

    public AndFileFilter(IOFileFilter filter1, IOFileFilter filter2) {
        if (filter1 == null || filter2 == null) {
            throw new IllegalArgumentException("The filters must not be null");
        }
        this.fileFilters = new ArrayList(2);
        addFileFilter(filter1);
        addFileFilter(filter2);
    }

    public void addFileFilter(IOFileFilter ioFileFilter) {
        this.fileFilters.add(ioFileFilter);
    }

    public List<IOFileFilter> getFileFilters() {
        return Collections.unmodifiableList(this.fileFilters);
    }

    public boolean removeFileFilter(IOFileFilter ioFileFilter) {
        return this.fileFilters.remove(ioFileFilter);
    }

    public void setFileFilters(List<IOFileFilter> fileFilters2) {
        this.fileFilters.clear();
        this.fileFilters.addAll(fileFilters2);
    }

    public boolean accept(File file) {
        if (this.fileFilters.isEmpty()) {
            return false;
        }
        for (IOFileFilter fileFilter : this.fileFilters) {
            if (!fileFilter.accept(file)) {
                return false;
            }
        }
        return true;
    }

    public boolean accept(File file, String name) {
        if (this.fileFilters.isEmpty()) {
            return false;
        }
        for (IOFileFilter fileFilter : this.fileFilters) {
            if (!fileFilter.accept(file, name)) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        String obj;
        StringBuilder buffer = new StringBuilder();
        buffer.append(super.toString());
        buffer.append("(");
        if (this.fileFilters != null) {
            for (int i = 0; i < this.fileFilters.size(); i++) {
                if (i > 0) {
                    buffer.append(",");
                }
                Object filter = this.fileFilters.get(i);
                if (filter == null) {
                    obj = "null";
                } else {
                    obj = filter.toString();
                }
                buffer.append(obj);
            }
        }
        buffer.append(")");
        return buffer.toString();
    }
}
