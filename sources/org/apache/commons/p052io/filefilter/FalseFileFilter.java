package org.apache.commons.p052io.filefilter;

import java.io.File;
import java.io.Serializable;

/* renamed from: org.apache.commons.io.filefilter.FalseFileFilter */
public class FalseFileFilter implements IOFileFilter, Serializable {
    public static final IOFileFilter FALSE = new FalseFileFilter();
    public static final IOFileFilter INSTANCE = FALSE;
    private static final long serialVersionUID = 6210271677940926200L;

    protected FalseFileFilter() {
    }

    public boolean accept(File file) {
        return false;
    }

    public boolean accept(File dir, String name) {
        return false;
    }
}
