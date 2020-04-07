package org.apache.commons.p052io.input;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: org.apache.commons.io.input.DemuxInputStream */
public class DemuxInputStream extends InputStream {
    private final InheritableThreadLocal<InputStream> m_streams = new InheritableThreadLocal<>();

    public InputStream bindStream(InputStream input) {
        InputStream oldValue = (InputStream) this.m_streams.get();
        this.m_streams.set(input);
        return oldValue;
    }

    public void close() throws IOException {
        InputStream input = (InputStream) this.m_streams.get();
        if (input != null) {
            input.close();
        }
    }

    public int read() throws IOException {
        InputStream input = (InputStream) this.m_streams.get();
        if (input != null) {
            return input.read();
        }
        return -1;
    }
}
