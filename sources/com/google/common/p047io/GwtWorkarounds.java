package com.google.common.p047io;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

@GwtCompatible(emulated = true)
/* renamed from: com.google.common.io.GwtWorkarounds */
final class GwtWorkarounds {

    /* renamed from: com.google.common.io.GwtWorkarounds$ByteInput */
    interface ByteInput {
        void close() throws IOException;

        int read() throws IOException;
    }

    /* renamed from: com.google.common.io.GwtWorkarounds$ByteOutput */
    interface ByteOutput {
        void close() throws IOException;

        void flush() throws IOException;

        void write(byte b) throws IOException;
    }

    /* renamed from: com.google.common.io.GwtWorkarounds$CharInput */
    interface CharInput {
        void close() throws IOException;

        int read() throws IOException;
    }

    /* renamed from: com.google.common.io.GwtWorkarounds$CharOutput */
    interface CharOutput {
        void close() throws IOException;

        void flush() throws IOException;

        void write(char c) throws IOException;
    }

    private GwtWorkarounds() {
    }

    @GwtIncompatible("Reader")
    static CharInput asCharInput(final Reader reader) {
        Preconditions.checkNotNull(reader);
        return new CharInput() {
            public int read() throws IOException {
                return reader.read();
            }

            public void close() throws IOException {
                reader.close();
            }
        };
    }

    static CharInput asCharInput(final CharSequence chars) {
        Preconditions.checkNotNull(chars);
        return new CharInput() {
            int index = 0;

            public int read() {
                if (this.index >= chars.length()) {
                    return -1;
                }
                CharSequence charSequence = chars;
                int i = this.index;
                this.index = i + 1;
                return charSequence.charAt(i);
            }

            public void close() {
                this.index = chars.length();
            }
        };
    }

    @GwtIncompatible("InputStream")
    static InputStream asInputStream(final ByteInput input) {
        Preconditions.checkNotNull(input);
        return new InputStream() {
            public int read() throws IOException {
                return input.read();
            }

            public int read(byte[] b, int off, int len) throws IOException {
                Preconditions.checkNotNull(b);
                Preconditions.checkPositionIndexes(off, off + len, b.length);
                if (len == 0) {
                    return 0;
                }
                int firstByte = read();
                if (firstByte == -1) {
                    return -1;
                }
                b[off] = (byte) firstByte;
                for (int dst = 1; dst < len; dst++) {
                    int readByte = read();
                    if (readByte == -1) {
                        return dst;
                    }
                    b[off + dst] = (byte) readByte;
                }
                return len;
            }

            public void close() throws IOException {
                input.close();
            }
        };
    }

    @GwtIncompatible("OutputStream")
    static OutputStream asOutputStream(final ByteOutput output) {
        Preconditions.checkNotNull(output);
        return new OutputStream() {
            public void write(int b) throws IOException {
                output.write((byte) b);
            }

            public void flush() throws IOException {
                output.flush();
            }

            public void close() throws IOException {
                output.close();
            }
        };
    }

    @GwtIncompatible("Writer")
    static CharOutput asCharOutput(final Writer writer) {
        Preconditions.checkNotNull(writer);
        return new CharOutput() {
            public void write(char c) throws IOException {
                writer.append(c);
            }

            public void flush() throws IOException {
                writer.flush();
            }

            public void close() throws IOException {
                writer.close();
            }
        };
    }

    static CharOutput stringBuilderOutput(int initialSize) {
        final StringBuilder builder = new StringBuilder(initialSize);
        return new CharOutput() {
            public void write(char c) {
                builder.append(c);
            }

            public void flush() {
            }

            public void close() {
            }

            public String toString() {
                return builder.toString();
            }
        };
    }
}
