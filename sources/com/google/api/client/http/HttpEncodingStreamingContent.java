package com.google.api.client.http;

import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StreamingContent;
import java.io.IOException;
import java.io.OutputStream;

public final class HttpEncodingStreamingContent implements StreamingContent {
    private final StreamingContent content;
    private final HttpEncoding encoding;

    public HttpEncodingStreamingContent(StreamingContent content2, HttpEncoding encoding2) {
        this.content = (StreamingContent) Preconditions.checkNotNull(content2);
        this.encoding = (HttpEncoding) Preconditions.checkNotNull(encoding2);
    }

    public void writeTo(OutputStream out) throws IOException {
        this.encoding.encode(this.content, out);
    }

    public StreamingContent getContent() {
        return this.content;
    }

    public HttpEncoding getEncoding() {
        return this.encoding;
    }
}
