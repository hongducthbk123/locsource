package org.apache.http.impl.conn;

import java.io.IOException;
import org.apache.http.annotation.Immutable;
import org.apache.http.p054io.HttpTransportMetrics;
import org.apache.http.p054io.SessionInputBuffer;
import org.apache.http.util.CharArrayBuffer;

@Immutable
public class LoggingSessionInputBuffer implements SessionInputBuffer {

    /* renamed from: in */
    private final SessionInputBuffer f1727in;
    private final Wire wire;

    public LoggingSessionInputBuffer(SessionInputBuffer in, Wire wire2) {
        this.f1727in = in;
        this.wire = wire2;
    }

    public boolean isDataAvailable(int timeout) throws IOException {
        return this.f1727in.isDataAvailable(timeout);
    }

    public int read(byte[] b, int off, int len) throws IOException {
        int l = this.f1727in.read(b, off, len);
        if (this.wire.enabled() && l > 0) {
            this.wire.input(b, off, l);
        }
        return l;
    }

    public int read() throws IOException {
        int l = this.f1727in.read();
        if (this.wire.enabled() && l != -1) {
            this.wire.input(l);
        }
        return l;
    }

    public int read(byte[] b) throws IOException {
        int l = this.f1727in.read(b);
        if (this.wire.enabled() && l > 0) {
            this.wire.input(b, 0, l);
        }
        return l;
    }

    public String readLine() throws IOException {
        String s = this.f1727in.readLine();
        if (this.wire.enabled() && s != null) {
            this.wire.input(s + "[EOL]");
        }
        return s;
    }

    public int readLine(CharArrayBuffer buffer) throws IOException {
        int l = this.f1727in.readLine(buffer);
        if (this.wire.enabled() && l >= 0) {
            this.wire.input(new String(buffer.buffer(), buffer.length() - l, l) + "[EOL]");
        }
        return l;
    }

    public HttpTransportMetrics getMetrics() {
        return this.f1727in.getMetrics();
    }
}
