package org.apache.commons.p052io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.util.regex.Pattern;
import org.apache.commons.p052io.IOCase;

/* renamed from: org.apache.commons.io.filefilter.RegexFileFilter */
public class RegexFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = 4269646126155225062L;
    private final Pattern pattern;

    public RegexFileFilter(String pattern2) {
        if (pattern2 == null) {
            throw new IllegalArgumentException("Pattern is missing");
        }
        this.pattern = Pattern.compile(pattern2);
    }

    public RegexFileFilter(String pattern2, IOCase caseSensitivity) {
        if (pattern2 == null) {
            throw new IllegalArgumentException("Pattern is missing");
        }
        int flags = 0;
        if (caseSensitivity != null && !caseSensitivity.isCaseSensitive()) {
            flags = 2;
        }
        this.pattern = Pattern.compile(pattern2, flags);
    }

    public RegexFileFilter(String pattern2, int flags) {
        if (pattern2 == null) {
            throw new IllegalArgumentException("Pattern is missing");
        }
        this.pattern = Pattern.compile(pattern2, flags);
    }

    public RegexFileFilter(Pattern pattern2) {
        if (pattern2 == null) {
            throw new IllegalArgumentException("Pattern is missing");
        }
        this.pattern = pattern2;
    }

    public boolean accept(File dir, String name) {
        return this.pattern.matcher(name).matches();
    }
}
