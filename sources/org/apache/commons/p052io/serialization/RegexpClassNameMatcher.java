package org.apache.commons.p052io.serialization;

import java.util.regex.Pattern;

/* renamed from: org.apache.commons.io.serialization.RegexpClassNameMatcher */
final class RegexpClassNameMatcher implements ClassNameMatcher {
    private final Pattern pattern;

    public RegexpClassNameMatcher(String regex) {
        this(Pattern.compile(regex));
    }

    public RegexpClassNameMatcher(Pattern pattern2) {
        if (pattern2 == null) {
            throw new IllegalArgumentException("Null pattern");
        }
        this.pattern = pattern2;
    }

    public boolean matches(String className) {
        return this.pattern.matcher(className).matches();
    }
}
