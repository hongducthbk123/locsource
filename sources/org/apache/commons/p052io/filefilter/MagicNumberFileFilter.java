package org.apache.commons.p052io.filefilter;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Arrays;
import org.apache.commons.p052io.IOUtils;

/* renamed from: org.apache.commons.io.filefilter.MagicNumberFileFilter */
public class MagicNumberFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = -547733176983104172L;
    private final long byteOffset;
    private final byte[] magicNumbers;

    public MagicNumberFileFilter(byte[] magicNumber) {
        this(magicNumber, 0);
    }

    public MagicNumberFileFilter(String magicNumber) {
        this(magicNumber, 0);
    }

    public MagicNumberFileFilter(String magicNumber, long offset) {
        if (magicNumber == null) {
            throw new IllegalArgumentException("The magic number cannot be null");
        } else if (magicNumber.isEmpty()) {
            throw new IllegalArgumentException("The magic number must contain at least one byte");
        } else if (offset < 0) {
            throw new IllegalArgumentException("The offset cannot be negative");
        } else {
            this.magicNumbers = magicNumber.getBytes(Charset.defaultCharset());
            this.byteOffset = offset;
        }
    }

    public MagicNumberFileFilter(byte[] magicNumber, long offset) {
        if (magicNumber == null) {
            throw new IllegalArgumentException("The magic number cannot be null");
        } else if (magicNumber.length == 0) {
            throw new IllegalArgumentException("The magic number must contain at least one byte");
        } else if (offset < 0) {
            throw new IllegalArgumentException("The offset cannot be negative");
        } else {
            this.magicNumbers = new byte[magicNumber.length];
            System.arraycopy(magicNumber, 0, this.magicNumbers, 0, magicNumber.length);
            this.byteOffset = offset;
        }
    }

    public boolean accept(File file) {
        boolean z = false;
        if (file != null && file.isFile() && file.canRead()) {
            RandomAccessFile randomAccessFile = null;
            try {
                byte[] fileBytes = new byte[this.magicNumbers.length];
                RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "r");
                try {
                    randomAccessFile2.seek(this.byteOffset);
                    if (randomAccessFile2.read(fileBytes) != this.magicNumbers.length) {
                        IOUtils.closeQuietly((Closeable) randomAccessFile2);
                    } else {
                        z = Arrays.equals(this.magicNumbers, fileBytes);
                        IOUtils.closeQuietly((Closeable) randomAccessFile2);
                    }
                } catch (IOException e) {
                    randomAccessFile = randomAccessFile2;
                    IOUtils.closeQuietly((Closeable) randomAccessFile);
                    return z;
                } catch (Throwable th) {
                    th = th;
                    randomAccessFile = randomAccessFile2;
                    IOUtils.closeQuietly((Closeable) randomAccessFile);
                    throw th;
                }
            } catch (IOException e2) {
                IOUtils.closeQuietly((Closeable) randomAccessFile);
                return z;
            } catch (Throwable th2) {
                th = th2;
                IOUtils.closeQuietly((Closeable) randomAccessFile);
                throw th;
            }
        }
        return z;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        builder.append("(");
        builder.append(new String(this.magicNumbers, Charset.defaultCharset()));
        builder.append(",");
        builder.append(this.byteOffset);
        builder.append(")");
        return builder.toString();
    }
}
