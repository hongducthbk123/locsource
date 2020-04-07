package org.apache.http.cookie;

import java.io.Serializable;
import java.util.Comparator;
import org.apache.commons.p052io.IOUtils;
import org.apache.http.annotation.Immutable;
import p004cn.jiguang.net.HttpUtils;

@Immutable
public class CookiePathComparator implements Serializable, Comparator<Cookie> {
    private static final long serialVersionUID = 7523645369616405818L;

    private String normalizePath(Cookie cookie) {
        String path = cookie.getPath();
        if (path == null) {
            path = HttpUtils.PATHS_SEPARATOR;
        }
        if (!path.endsWith(HttpUtils.PATHS_SEPARATOR)) {
            return path + IOUtils.DIR_SEPARATOR_UNIX;
        }
        return path;
    }

    public int compare(Cookie c1, Cookie c2) {
        String path1 = normalizePath(c1);
        String path2 = normalizePath(c2);
        if (path1.equals(path2)) {
            return 0;
        }
        if (path1.startsWith(path2)) {
            return -1;
        }
        if (path2.startsWith(path1)) {
            return 1;
        }
        return 0;
    }
}
