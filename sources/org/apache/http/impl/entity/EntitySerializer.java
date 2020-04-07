package org.apache.http.impl.entity;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.impl.p053io.ChunkedOutputStream;
import org.apache.http.impl.p053io.ContentLengthOutputStream;
import org.apache.http.impl.p053io.IdentityOutputStream;
import org.apache.http.p054io.SessionOutputBuffer;

public class EntitySerializer {
    private final ContentLengthStrategy lenStrategy;

    public EntitySerializer(ContentLengthStrategy lenStrategy2) {
        if (lenStrategy2 == null) {
            throw new IllegalArgumentException("Content length strategy may not be null");
        }
        this.lenStrategy = lenStrategy2;
    }

    /* access modifiers changed from: protected */
    public OutputStream doSerialize(SessionOutputBuffer outbuffer, HttpMessage message) throws HttpException, IOException {
        long len = this.lenStrategy.determineLength(message);
        if (len == -2) {
            return new ChunkedOutputStream(outbuffer);
        }
        if (len == -1) {
            return new IdentityOutputStream(outbuffer);
        }
        return new ContentLengthOutputStream(outbuffer, len);
    }

    public void serialize(SessionOutputBuffer outbuffer, HttpMessage message, HttpEntity entity) throws HttpException, IOException {
        if (outbuffer == null) {
            throw new IllegalArgumentException("Session output buffer may not be null");
        } else if (message == null) {
            throw new IllegalArgumentException("HTTP message may not be null");
        } else if (entity == null) {
            throw new IllegalArgumentException("HTTP entity may not be null");
        } else {
            OutputStream outstream = doSerialize(outbuffer, message);
            entity.writeTo(outstream);
            outstream.close();
        }
    }
}
