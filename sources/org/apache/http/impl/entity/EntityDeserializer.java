package org.apache.http.impl.entity;

import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.impl.p053io.ChunkedInputStream;
import org.apache.http.impl.p053io.ContentLengthInputStream;
import org.apache.http.impl.p053io.IdentityInputStream;
import org.apache.http.p054io.SessionInputBuffer;

public class EntityDeserializer {
    private final ContentLengthStrategy lenStrategy;

    public EntityDeserializer(ContentLengthStrategy lenStrategy2) {
        if (lenStrategy2 == null) {
            throw new IllegalArgumentException("Content length strategy may not be null");
        }
        this.lenStrategy = lenStrategy2;
    }

    /* access modifiers changed from: protected */
    public BasicHttpEntity doDeserialize(SessionInputBuffer inbuffer, HttpMessage message) throws HttpException, IOException {
        BasicHttpEntity entity = new BasicHttpEntity();
        long len = this.lenStrategy.determineLength(message);
        if (len == -2) {
            entity.setChunked(true);
            entity.setContentLength(-1);
            entity.setContent(new ChunkedInputStream(inbuffer));
        } else if (len == -1) {
            entity.setChunked(false);
            entity.setContentLength(-1);
            entity.setContent(new IdentityInputStream(inbuffer));
        } else {
            entity.setChunked(false);
            entity.setContentLength(len);
            entity.setContent(new ContentLengthInputStream(inbuffer, len));
        }
        Header contentTypeHeader = message.getFirstHeader("Content-Type");
        if (contentTypeHeader != null) {
            entity.setContentType(contentTypeHeader);
        }
        Header contentEncodingHeader = message.getFirstHeader("Content-Encoding");
        if (contentEncodingHeader != null) {
            entity.setContentEncoding(contentEncodingHeader);
        }
        return entity;
    }

    public HttpEntity deserialize(SessionInputBuffer inbuffer, HttpMessage message) throws HttpException, IOException {
        if (inbuffer == null) {
            throw new IllegalArgumentException("Session input buffer may not be null");
        } else if (message != null) {
            return doDeserialize(inbuffer, message);
        } else {
            throw new IllegalArgumentException("HTTP message may not be null");
        }
    }
}
