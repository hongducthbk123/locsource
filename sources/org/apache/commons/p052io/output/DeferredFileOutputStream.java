package org.apache.commons.p052io.output;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.p052io.IOUtils;

/* renamed from: org.apache.commons.io.output.DeferredFileOutputStream */
public class DeferredFileOutputStream extends ThresholdingOutputStream {
    private boolean closed;
    private OutputStream currentOutputStream;
    private final File directory;
    private ByteArrayOutputStream memoryOutputStream;
    private File outputFile;
    private final String prefix;
    private final String suffix;

    public DeferredFileOutputStream(int threshold, File outputFile2) {
        this(threshold, outputFile2, null, null, null);
    }

    public DeferredFileOutputStream(int threshold, String prefix2, String suffix2, File directory2) {
        this(threshold, null, prefix2, suffix2, directory2);
        if (prefix2 == null) {
            throw new IllegalArgumentException("Temporary file prefix is missing");
        }
    }

    private DeferredFileOutputStream(int threshold, File outputFile2, String prefix2, String suffix2, File directory2) {
        super(threshold);
        this.closed = false;
        this.outputFile = outputFile2;
        this.memoryOutputStream = new ByteArrayOutputStream();
        this.currentOutputStream = this.memoryOutputStream;
        this.prefix = prefix2;
        this.suffix = suffix2;
        this.directory = directory2;
    }

    /* access modifiers changed from: protected */
    public OutputStream getStream() throws IOException {
        return this.currentOutputStream;
    }

    /* access modifiers changed from: protected */
    public void thresholdReached() throws IOException {
        if (this.prefix != null) {
            this.outputFile = File.createTempFile(this.prefix, this.suffix, this.directory);
        }
        FileOutputStream fos = new FileOutputStream(this.outputFile);
        try {
            this.memoryOutputStream.writeTo(fos);
            this.currentOutputStream = fos;
            this.memoryOutputStream = null;
        } catch (IOException e) {
            fos.close();
            throw e;
        }
    }

    public boolean isInMemory() {
        return !isThresholdExceeded();
    }

    public byte[] getData() {
        if (this.memoryOutputStream != null) {
            return this.memoryOutputStream.toByteArray();
        }
        return null;
    }

    public File getFile() {
        return this.outputFile;
    }

    public void close() throws IOException {
        super.close();
        this.closed = true;
    }

    public void writeTo(OutputStream out) throws IOException {
        if (!this.closed) {
            throw new IOException("Stream not closed");
        } else if (isInMemory()) {
            this.memoryOutputStream.writeTo(out);
        } else {
            FileInputStream fis = new FileInputStream(this.outputFile);
            try {
                IOUtils.copy((InputStream) fis, out);
            } finally {
                IOUtils.closeQuietly((InputStream) fis);
            }
        }
    }
}
