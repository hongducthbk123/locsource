package org.apache.http.impl.client;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.annotation.Immutable;

@Immutable
public class TunnelRefusedException extends HttpException {
    private static final long serialVersionUID = -8646722842745617323L;
    private final HttpResponse response;

    public TunnelRefusedException(String message, HttpResponse response2) {
        super(message);
        this.response = response2;
    }

    public HttpResponse getResponse() {
        return this.response;
    }
}
