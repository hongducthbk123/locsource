package org.apache.http.impl.cookie;

import org.apache.http.annotation.Immutable;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieAttributeHandler;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;
import p004cn.jiguang.net.HttpUtils;

@Immutable
public class BasicPathHandler implements CookieAttributeHandler {
    public void parse(SetCookie cookie, String value) throws MalformedCookieException {
        if (cookie == null) {
            throw new IllegalArgumentException("Cookie may not be null");
        }
        if (value == null || value.trim().length() == 0) {
            value = HttpUtils.PATHS_SEPARATOR;
        }
        cookie.setPath(value);
    }

    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
        if (!match(cookie, origin)) {
            throw new MalformedCookieException("Illegal path attribute \"" + cookie.getPath() + "\". Path of origin: \"" + origin.getPath() + "\"");
        }
    }

    public boolean match(Cookie cookie, CookieOrigin origin) {
        if (cookie == null) {
            throw new IllegalArgumentException("Cookie may not be null");
        } else if (origin == null) {
            throw new IllegalArgumentException("Cookie origin may not be null");
        } else {
            String targetpath = origin.getPath();
            String topmostPath = cookie.getPath();
            if (topmostPath == null) {
                topmostPath = HttpUtils.PATHS_SEPARATOR;
            }
            if (topmostPath.length() > 1 && topmostPath.endsWith(HttpUtils.PATHS_SEPARATOR)) {
                topmostPath = topmostPath.substring(0, topmostPath.length() - 1);
            }
            boolean match = targetpath.startsWith(topmostPath);
            if (!match || targetpath.length() == topmostPath.length() || topmostPath.endsWith(HttpUtils.PATHS_SEPARATOR)) {
                return match;
            }
            if (targetpath.charAt(topmostPath.length()) == '/') {
                return true;
            }
            return false;
        }
    }
}
