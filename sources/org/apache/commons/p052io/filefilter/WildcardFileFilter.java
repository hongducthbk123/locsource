package org.apache.commons.p052io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.p052io.FilenameUtils;
import org.apache.commons.p052io.IOCase;

/* renamed from: org.apache.commons.io.filefilter.WildcardFileFilter */
public class WildcardFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = -7426486598995782105L;
    private final IOCase caseSensitivity;
    private final String[] wildcards;

    public WildcardFileFilter(String wildcard) {
        this(wildcard, IOCase.SENSITIVE);
    }

    public WildcardFileFilter(String wildcard, IOCase caseSensitivity2) {
        if (wildcard == null) {
            throw new IllegalArgumentException("The wildcard must not be null");
        }
        this.wildcards = new String[]{wildcard};
        if (caseSensitivity2 == null) {
            caseSensitivity2 = IOCase.SENSITIVE;
        }
        this.caseSensitivity = caseSensitivity2;
    }

    public WildcardFileFilter(String[] wildcards2) {
        this(wildcards2, IOCase.SENSITIVE);
    }

    public WildcardFileFilter(String[] wildcards2, IOCase caseSensitivity2) {
        if (wildcards2 == null) {
            throw new IllegalArgumentException("The wildcard array must not be null");
        }
        this.wildcards = new String[wildcards2.length];
        System.arraycopy(wildcards2, 0, this.wildcards, 0, wildcards2.length);
        if (caseSensitivity2 == null) {
            caseSensitivity2 = IOCase.SENSITIVE;
        }
        this.caseSensitivity = caseSensitivity2;
    }

    public WildcardFileFilter(List<String> wildcards2) {
        this(wildcards2, IOCase.SENSITIVE);
    }

    public WildcardFileFilter(List<String> wildcards2, IOCase caseSensitivity2) {
        if (wildcards2 == null) {
            throw new IllegalArgumentException("The wildcard list must not be null");
        }
        this.wildcards = (String[]) wildcards2.toArray(new String[wildcards2.size()]);
        if (caseSensitivity2 == null) {
            caseSensitivity2 = IOCase.SENSITIVE;
        }
        this.caseSensitivity = caseSensitivity2;
    }

    public boolean accept(File dir, String name) {
        for (String wildcard : this.wildcards) {
            if (FilenameUtils.wildcardMatch(name, wildcard, this.caseSensitivity)) {
                return true;
            }
        }
        return false;
    }

    public boolean accept(File file) {
        String name = file.getName();
        for (String wildcard : this.wildcards) {
            if (FilenameUtils.wildcardMatch(name, wildcard, this.caseSensitivity)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(super.toString());
        buffer.append("(");
        if (this.wildcards != null) {
            for (int i = 0; i < this.wildcards.length; i++) {
                if (i > 0) {
                    buffer.append(",");
                }
                buffer.append(this.wildcards[i]);
            }
        }
        buffer.append(")");
        return buffer.toString();
    }
}
