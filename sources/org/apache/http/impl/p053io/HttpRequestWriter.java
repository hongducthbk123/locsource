package org.apache.http.impl.p053io;

import java.io.IOException;
import org.apache.http.HttpMessage;
import org.apache.http.HttpRequest;
import org.apache.http.message.LineFormatter;
import org.apache.http.p054io.SessionOutputBuffer;
import org.apache.http.params.HttpParams;

/* renamed from: org.apache.http.impl.io.HttpRequestWriter */
public class HttpRequestWriter extends AbstractMessageWriter {
    public HttpRequestWriter(SessionOutputBuffer buffer, LineFormatter formatter, HttpParams params) {
        super(buffer, formatter, params);
    }

    /* access modifiers changed from: protected */
    public void writeHeadLine(HttpMessage message) throws IOException {
        this.lineFormatter.formatRequestLine(this.lineBuf, ((HttpRequest) message).getRequestLine());
        this.sessionBuffer.writeLine(this.lineBuf);
    }
}
