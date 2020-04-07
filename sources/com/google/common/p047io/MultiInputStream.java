package com.google.common.p047io;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import javax.annotation.Nullable;

/* renamed from: com.google.common.io.MultiInputStream */
final class MultiInputStream extends InputStream {

    /* renamed from: in */
    private InputStream f1029in;

    /* renamed from: it */
    private Iterator<? extends ByteSource> f1030it;

    public MultiInputStream(Iterator<? extends ByteSource> it) throws IOException {
        this.f1030it = (Iterator) Preconditions.checkNotNull(it);
        advance();
    }

    public void close() throws IOException {
        if (this.f1029in != null) {
            try {
                this.f1029in.close();
            } finally {
                this.f1029in = null;
            }
        }
    }

    private void advance() throws IOException {
        close();
        if (this.f1030it.hasNext()) {
            this.f1029in = ((ByteSource) this.f1030it.next()).openStream();
        }
    }

    public int available() throws IOException {
        if (this.f1029in == null) {
            return 0;
        }
        return this.f1029in.available();
    }

    public boolean markSupported() {
        return false;
    }

    public int read() throws IOException {
        if (this.f1029in == null) {
            return -1;
        }
        int result = this.f1029in.read();
        if (result != -1) {
            return result;
        }
        advance();
        return read();
    }

    public int read(@Nullable byte[] b, int off, int len) throws IOException {
        if (this.f1029in == null) {
            return -1;
        }
        int result = this.f1029in.read(b, off, len);
        if (result != -1) {
            return result;
        }
        advance();
        return read(b, off, len);
    }

    public long skip(long n) throws IOException {
        if (this.f1029in == null || n <= 0) {
            return 0;
        }
        long result = this.f1029in.skip(n);
        if (result != 0) {
            return result;
        }
        if (read() == -1) {
            return 0;
        }
        return 1 + this.f1029in.skip(n - 1);
    }
}
