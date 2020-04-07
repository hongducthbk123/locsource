package org.apache.commons.p052io.input;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import org.apache.commons.p052io.Charsets;
import org.apache.commons.p052io.IOUtils;

/* renamed from: org.apache.commons.io.input.ReversedLinesFileReader */
public class ReversedLinesFileReader implements Closeable {
    /* access modifiers changed from: private */
    public final int avoidNewlineSplitBufferSize;
    /* access modifiers changed from: private */
    public final int blockSize;
    /* access modifiers changed from: private */
    public final int byteDecrement;
    private FilePart currentFilePart;
    /* access modifiers changed from: private */
    public final Charset encoding;
    /* access modifiers changed from: private */
    public final byte[][] newLineSequences;
    /* access modifiers changed from: private */
    public final RandomAccessFile randomAccessFile;
    private final long totalBlockCount;
    private final long totalByteLength;
    private boolean trailingNewlineOfFileSkipped;

    /* renamed from: org.apache.commons.io.input.ReversedLinesFileReader$FilePart */
    private class FilePart {
        private int currentLastBytePos;
        private final byte[] data;
        private byte[] leftOver;

        /* renamed from: no */
        private final long f1723no;

        private FilePart(long no, int length, byte[] leftOverOfLastFilePart) throws IOException {
            int i;
            this.f1723no = no;
            if (leftOverOfLastFilePart != null) {
                i = leftOverOfLastFilePart.length;
            } else {
                i = 0;
            }
            this.data = new byte[(length + i)];
            long off = (no - 1) * ((long) ReversedLinesFileReader.this.blockSize);
            if (no > 0) {
                ReversedLinesFileReader.this.randomAccessFile.seek(off);
                if (ReversedLinesFileReader.this.randomAccessFile.read(this.data, 0, length) != length) {
                    throw new IllegalStateException("Count of requested bytes and actually read bytes don't match");
                }
            }
            if (leftOverOfLastFilePart != null) {
                System.arraycopy(leftOverOfLastFilePart, 0, this.data, length, leftOverOfLastFilePart.length);
            }
            this.currentLastBytePos = this.data.length - 1;
            this.leftOver = null;
        }

        /* access modifiers changed from: private */
        public FilePart rollOver() throws IOException {
            if (this.currentLastBytePos > -1) {
                throw new IllegalStateException("Current currentLastCharPos unexpectedly positive... last readLine() should have returned something! currentLastCharPos=" + this.currentLastBytePos);
            } else if (this.f1723no > 1) {
                return new FilePart(this.f1723no - 1, ReversedLinesFileReader.this.blockSize, this.leftOver);
            } else {
                if (this.leftOver == null) {
                    return null;
                }
                throw new IllegalStateException("Unexpected leftover of the last block: leftOverOfThisFilePart=" + new String(this.leftOver, ReversedLinesFileReader.this.encoding));
            }
        }

        /* access modifiers changed from: private */
        public String readLine() throws IOException {
            boolean isLastFilePart;
            String line = null;
            if (this.f1723no == 1) {
                isLastFilePart = true;
            } else {
                isLastFilePart = false;
            }
            int i = this.currentLastBytePos;
            while (true) {
                if (i > -1) {
                    if (!isLastFilePart && i < ReversedLinesFileReader.this.avoidNewlineSplitBufferSize) {
                        createLeftOver();
                        break;
                    }
                    int newLineMatchByteCount = getNewLineMatchByteCount(this.data, i);
                    if (newLineMatchByteCount <= 0) {
                        i -= ReversedLinesFileReader.this.byteDecrement;
                        if (i < 0) {
                            createLeftOver();
                            break;
                        }
                    } else {
                        int lineStart = i + 1;
                        int lineLengthBytes = (this.currentLastBytePos - lineStart) + 1;
                        if (lineLengthBytes < 0) {
                            throw new IllegalStateException("Unexpected negative line length=" + lineLengthBytes);
                        }
                        byte[] lineData = new byte[lineLengthBytes];
                        System.arraycopy(this.data, lineStart, lineData, 0, lineLengthBytes);
                        line = new String(lineData, ReversedLinesFileReader.this.encoding);
                        this.currentLastBytePos = i - newLineMatchByteCount;
                    }
                } else {
                    break;
                }
            }
            if (!isLastFilePart || this.leftOver == null) {
                return line;
            }
            String line2 = new String(this.leftOver, ReversedLinesFileReader.this.encoding);
            this.leftOver = null;
            return line2;
        }

        private void createLeftOver() {
            int lineLengthBytes = this.currentLastBytePos + 1;
            if (lineLengthBytes > 0) {
                this.leftOver = new byte[lineLengthBytes];
                System.arraycopy(this.data, 0, this.leftOver, 0, lineLengthBytes);
            } else {
                this.leftOver = null;
            }
            this.currentLastBytePos = -1;
        }

        private int getNewLineMatchByteCount(byte[] data2, int i) {
            byte[][] arr$;
            boolean z;
            for (byte[] newLineSequence : ReversedLinesFileReader.this.newLineSequences) {
                boolean match = true;
                for (int j = newLineSequence.length - 1; j >= 0; j--) {
                    int k = (i + j) - (newLineSequence.length - 1);
                    if (k < 0 || data2[k] != newLineSequence[j]) {
                        z = false;
                    } else {
                        z = true;
                    }
                    match &= z;
                }
                if (match) {
                    return newLineSequence.length;
                }
            }
            return 0;
        }
    }

    @Deprecated
    public ReversedLinesFileReader(File file) throws IOException {
        this(file, 4096, Charset.defaultCharset());
    }

    public ReversedLinesFileReader(File file, Charset charset) throws IOException {
        this(file, 4096, charset);
    }

    public ReversedLinesFileReader(File file, int blockSize2, Charset encoding2) throws IOException {
        this.trailingNewlineOfFileSkipped = false;
        this.blockSize = blockSize2;
        this.encoding = encoding2;
        Charset charset = Charsets.toCharset(encoding2);
        if (charset.newEncoder().maxBytesPerChar() == 1.0f) {
            this.byteDecrement = 1;
        } else if (charset == Charsets.UTF_8) {
            this.byteDecrement = 1;
        } else if (charset == Charset.forName("Shift_JIS") || charset == Charset.forName("windows-31j") || charset == Charset.forName("x-windows-949") || charset == Charset.forName("gbk") || charset == Charset.forName("x-windows-950")) {
            this.byteDecrement = 1;
        } else if (charset == Charsets.UTF_16BE || charset == Charsets.UTF_16LE) {
            this.byteDecrement = 2;
        } else if (charset == Charsets.UTF_16) {
            throw new UnsupportedEncodingException("For UTF-16, you need to specify the byte order (use UTF-16BE or UTF-16LE)");
        } else {
            throw new UnsupportedEncodingException("Encoding " + encoding2 + " is not supported yet (feel free to " + "submit a patch)");
        }
        this.newLineSequences = new byte[][]{IOUtils.LINE_SEPARATOR_WINDOWS.getBytes(encoding2), IOUtils.LINE_SEPARATOR_UNIX.getBytes(encoding2), "\r".getBytes(encoding2)};
        this.avoidNewlineSplitBufferSize = this.newLineSequences[0].length;
        this.randomAccessFile = new RandomAccessFile(file, "r");
        this.totalByteLength = this.randomAccessFile.length();
        int lastBlockLength = (int) (this.totalByteLength % ((long) blockSize2));
        if (lastBlockLength > 0) {
            this.totalBlockCount = (this.totalByteLength / ((long) blockSize2)) + 1;
        } else {
            this.totalBlockCount = this.totalByteLength / ((long) blockSize2);
            if (this.totalByteLength > 0) {
                lastBlockLength = blockSize2;
            }
        }
        this.currentFilePart = new FilePart(this.totalBlockCount, lastBlockLength, null);
    }

    public ReversedLinesFileReader(File file, int blockSize2, String encoding2) throws IOException {
        this(file, blockSize2, Charsets.toCharset(encoding2));
    }

    public String readLine() throws IOException {
        String line = this.currentFilePart.readLine();
        while (line == null) {
            this.currentFilePart = this.currentFilePart.rollOver();
            if (this.currentFilePart == null) {
                break;
            }
            line = this.currentFilePart.readLine();
        }
        if (!"".equals(line) || this.trailingNewlineOfFileSkipped) {
            return line;
        }
        this.trailingNewlineOfFileSkipped = true;
        return readLine();
    }

    public void close() throws IOException {
        this.randomAccessFile.close();
    }
}
