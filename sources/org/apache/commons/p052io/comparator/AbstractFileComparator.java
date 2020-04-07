package org.apache.commons.p052io.comparator;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* renamed from: org.apache.commons.io.comparator.AbstractFileComparator */
abstract class AbstractFileComparator implements Comparator<File> {
    AbstractFileComparator() {
    }

    public File[] sort(File... files) {
        if (files != null) {
            Arrays.sort(files, this);
        }
        return files;
    }

    public List<File> sort(List<File> files) {
        if (files != null) {
            Collections.sort(files, this);
        }
        return files;
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
