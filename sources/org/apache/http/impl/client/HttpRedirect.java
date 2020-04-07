package org.apache.http.impl.client;

import java.net.URI;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.client.methods.HttpRequestBase;

@NotThreadSafe
class HttpRedirect extends HttpRequestBase {
    private String method;

    public HttpRedirect(String method2, URI uri) {
        if (method2.equalsIgnoreCase("HEAD")) {
            this.method = "HEAD";
        } else {
            this.method = "GET";
        }
        setURI(uri);
    }

    public String getMethod() {
        return this.method;
    }
}
