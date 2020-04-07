package com.fasterxml.jackson.core.p046io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/* renamed from: com.fasterxml.jackson.core.io.BaseReader */
abstract class BaseReader extends Reader {
    protected static final int LAST_VALID_UNICODE_CHAR = 1114111;
    protected static final char NULL_BYTE = 0;
    protected static final char NULL_CHAR = 0;
    protected byte[] _buffer;
    protected final IOContext _context;
    protected InputStream _in;
    protected int _length;
    protected int _ptr;
    protected char[] _tmpBuf = null;

    protected BaseReader(IOContext context, InputStream in, byte[] buf, int ptr, int len) {
        this._context = context;
        this._in = in;
        this._buffer = buf;
        this._ptr = ptr;
        this._length = len;
    }

    public void close() throws IOException {
        InputStream in = this._in;
        if (in != null) {
            this._in = null;
            freeBuffers();
            in.close();
        }
    }

    public int read() throws IOException {
        if (this._tmpBuf == null) {
            this._tmpBuf = new char[1];
        }
        if (read(this._tmpBuf, 0, 1) < 1) {
            return -1;
        }
        return this._tmpBuf[0];
    }

    public final void freeBuffers() {
        byte[] buf = this._buffer;
        if (buf != null) {
            this._buffer = null;
            this._context.releaseReadIOBuffer(buf);
        }
    }

    /* access modifiers changed from: protected */
    public void reportBounds(char[] cbuf, int start, int len) throws IOException {
        throw new ArrayIndexOutOfBoundsException("read(buf," + start + "," + len + "), cbuf[" + cbuf.length + "]");
    }

    /* access modifiers changed from: protected */
    public void reportStrangeStream() throws IOException {
        throw new IOException("Strange I/O stream, returned 0 bytes on read");
    }
}
