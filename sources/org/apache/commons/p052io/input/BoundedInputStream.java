package org.apache.commons.p052io.input;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: org.apache.commons.io.input.BoundedInputStream */
public class BoundedInputStream extends InputStream {

    /* renamed from: in */
    private final InputStream f1725in;
    private long mark;
    private final long max;
    private long pos;
    private boolean propagateClose;

    public BoundedInputStream(InputStream in, long size) {
        this.pos = 0;
        this.mark = -1;
        this.propagateClose = true;
        this.max = size;
        this.f1725in = in;
    }

    public BoundedInputStream(InputStream in) {
        this(in, -1);
    }

    public int read() throws IOException {
        if (this.max >= 0 && this.pos >= this.max) {
            return -1;
        }
        int read = this.f1725in.read();
        this.pos++;
        return read;
    }

    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    public int read(byte[] b, int off, int len) throws IOException {
        if (this.max >= 0 && this.pos >= this.max) {
            return -1;
        }
        int bytesRead = this.f1725in.read(b, off, (int) (this.max >= 0 ? Math.min((long) len, this.max - this.pos) : (long) len));
        if (bytesRead == -1) {
            return -1;
        }
        this.pos += (long) bytesRead;
        return bytesRead;
    }

    public long skip(long n) throws IOException {
        long toSkip;
        if (this.max >= 0) {
            toSkip = Math.min(n, this.max - this.pos);
        } else {
            toSkip = n;
        }
        long skippedBytes = this.f1725in.skip(toSkip);
        this.pos += skippedBytes;
        return skippedBytes;
    }

    public int available() throws IOException {
        if (this.max < 0 || this.pos < this.max) {
            return this.f1725in.available();
        }
        return 0;
    }

    public String toString() {
        return this.f1725in.toString();
    }

    public void close() throws IOException {
        if (this.propagateClose) {
            this.f1725in.close();
        }
    }

    public synchronized void reset() throws IOException {
        this.f1725in.reset();
        this.pos = this.mark;
    }

    public synchronized void mark(int readlimit) {
        this.f1725in.mark(readlimit);
        this.mark = this.pos;
    }

    public boolean markSupported() {
        return this.f1725in.markSupported();
    }

    public boolean isPropagateClose() {
        return this.propagateClose;
    }

    public void setPropagateClose(boolean propagateClose2) {
        this.propagateClose = propagateClose2;
    }
}
