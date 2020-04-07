package org.apache.http.impl.client;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLHandshakeException;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.annotation.Immutable;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;

@Immutable
public class DefaultHttpRequestRetryHandler implements HttpRequestRetryHandler {
    private final boolean requestSentRetryEnabled;
    private final int retryCount;

    public DefaultHttpRequestRetryHandler(int retryCount2, boolean requestSentRetryEnabled2) {
        this.retryCount = retryCount2;
        this.requestSentRetryEnabled = requestSentRetryEnabled2;
    }

    public DefaultHttpRequestRetryHandler() {
        this(3, false);
    }

    public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
        boolean idempotent;
        boolean sent;
        if (exception == null) {
            throw new IllegalArgumentException("Exception parameter may not be null");
        } else if (context == null) {
            throw new IllegalArgumentException("HTTP context may not be null");
        } else if (executionCount > this.retryCount) {
            return false;
        } else {
            if (exception instanceof NoHttpResponseException) {
                return true;
            }
            if ((exception instanceof InterruptedIOException) || (exception instanceof UnknownHostException) || (exception instanceof ConnectException) || (exception instanceof SSLHandshakeException)) {
                return false;
            }
            if (!(((HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST)) instanceof HttpEntityEnclosingRequest)) {
                idempotent = true;
            } else {
                idempotent = false;
            }
            if (idempotent) {
                return true;
            }
            Boolean b = (Boolean) context.getAttribute(ExecutionContext.HTTP_REQ_SENT);
            if (b == null || !b.booleanValue()) {
                sent = false;
            } else {
                sent = true;
            }
            if (!sent || this.requestSentRetryEnabled) {
                return true;
            }
            return false;
        }
    }

    public boolean isRequestSentRetryEnabled() {
        return this.requestSentRetryEnabled;
    }

    public int getRetryCount() {
        return this.retryCount;
    }
}
