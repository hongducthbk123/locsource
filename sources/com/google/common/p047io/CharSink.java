package com.google.common.p047io;

import com.google.common.base.Preconditions;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

/* renamed from: com.google.common.io.CharSink */
public abstract class CharSink implements OutputSupplier<Writer> {
    public abstract Writer openStream() throws IOException;

    protected CharSink() {
    }

    @Deprecated
    public final Writer getOutput() throws IOException {
        return openStream();
    }

    public Writer openBufferedStream() throws IOException {
        Writer writer = openStream();
        return writer instanceof BufferedWriter ? (BufferedWriter) writer : new BufferedWriter(writer);
    }

    public void write(CharSequence charSequence) throws IOException {
        Preconditions.checkNotNull(charSequence);
        Closer closer = Closer.create();
        try {
            Writer out = (Writer) closer.register(openStream());
            out.append(charSequence);
            out.flush();
            closer.close();
        } catch (Throwable th) {
            closer.close();
            throw th;
        }
    }

    public void writeLines(Iterable<? extends CharSequence> lines) throws IOException {
        writeLines(lines, System.getProperty("line.separator"));
    }

    public void writeLines(Iterable<? extends CharSequence> lines, String lineSeparator) throws IOException {
        Preconditions.checkNotNull(lines);
        Preconditions.checkNotNull(lineSeparator);
        Closer closer = Closer.create();
        try {
            Writer out = (Writer) closer.register(openBufferedStream());
            for (CharSequence line : lines) {
                out.append(line).append(lineSeparator);
            }
            out.flush();
            closer.close();
        } catch (Throwable th) {
            closer.close();
            throw th;
        }
    }

    public long writeFrom(Readable readable) throws IOException {
        Preconditions.checkNotNull(readable);
        Closer closer = Closer.create();
        try {
            Writer out = (Writer) closer.register(openStream());
            long written = CharStreams.copy(readable, (Appendable) out);
            out.flush();
            closer.close();
            return written;
        } catch (Throwable th) {
            closer.close();
            throw th;
        }
    }
}
