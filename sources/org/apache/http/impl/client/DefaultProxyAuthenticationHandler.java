package org.apache.http.impl.client;

import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.annotation.Immutable;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.protocol.HttpContext;

@Immutable
public class DefaultProxyAuthenticationHandler extends AbstractAuthenticationHandler {
    public boolean isAuthenticationRequested(HttpResponse response, HttpContext context) {
        if (response != null) {
            return response.getStatusLine().getStatusCode() == 407;
        }
        throw new IllegalArgumentException("HTTP response may not be null");
    }

    public Map<String, Header> getChallenges(HttpResponse response, HttpContext context) throws MalformedChallengeException {
        if (response != null) {
            return parseChallenges(response.getHeaders("Proxy-Authenticate"));
        }
        throw new IllegalArgumentException("HTTP response may not be null");
    }
}
