package org.apache.commons.p052io.output;

import java.io.OutputStream;

/* renamed from: org.apache.commons.io.output.CountingOutputStream */
public class CountingOutputStream extends ProxyOutputStream {
    private long count = 0;

    public CountingOutputStream(OutputStream out) {
        super(out);
    }

    /* access modifiers changed from: protected */
    public synchronized void beforeWrite(int n) {
        this.count += (long) n;
    }

    public int getCount() {
        long result = getByteCount();
        if (result <= 2147483647L) {
            return (int) result;
        }
        throw new ArithmeticException("The byte count " + result + " is too large to be converted to an int");
    }

    public int resetCount() {
        long result = resetByteCount();
        if (result <= 2147483647L) {
            return (int) result;
        }
        throw new ArithmeticException("The byte count " + result + " is too large to be converted to an int");
    }

    public synchronized long getByteCount() {
        return this.count;
    }

    public synchronized long resetByteCount() {
        long tmp;
        tmp = this.count;
        this.count = 0;
        return tmp;
    }
}
