package org.apache.commons.p052io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.p052io.FileUtils;

/* renamed from: org.apache.commons.io.comparator.SizeFileComparator */
public class SizeFileComparator extends AbstractFileComparator implements Serializable {
    public static final Comparator<File> SIZE_COMPARATOR = new SizeFileComparator();
    public static final Comparator<File> SIZE_REVERSE = new ReverseComparator(SIZE_COMPARATOR);
    public static final Comparator<File> SIZE_SUMDIR_COMPARATOR = new SizeFileComparator(true);
    public static final Comparator<File> SIZE_SUMDIR_REVERSE = new ReverseComparator(SIZE_SUMDIR_COMPARATOR);
    private static final long serialVersionUID = -1201561106411416190L;
    private final boolean sumDirectoryContents;

    public /* bridge */ /* synthetic */ List sort(List list) {
        return super.sort(list);
    }

    public /* bridge */ /* synthetic */ File[] sort(File[] fileArr) {
        return super.sort(fileArr);
    }

    public SizeFileComparator() {
        this.sumDirectoryContents = false;
    }

    public SizeFileComparator(boolean sumDirectoryContents2) {
        this.sumDirectoryContents = sumDirectoryContents2;
    }

    public int compare(File file1, File file2) {
        long size1;
        long size2;
        if (!file1.isDirectory()) {
            size1 = file1.length();
        } else if (!this.sumDirectoryContents || !file1.exists()) {
            size1 = 0;
        } else {
            size1 = FileUtils.sizeOfDirectory(file1);
        }
        if (!file2.isDirectory()) {
            size2 = file2.length();
        } else if (!this.sumDirectoryContents || !file2.exists()) {
            size2 = 0;
        } else {
            size2 = FileUtils.sizeOfDirectory(file2);
        }
        long result = size1 - size2;
        if (result < 0) {
            return -1;
        }
        if (result > 0) {
            return 1;
        }
        return 0;
    }

    public String toString() {
        return super.toString() + "[sumDirectoryContents=" + this.sumDirectoryContents + "]";
    }
}
