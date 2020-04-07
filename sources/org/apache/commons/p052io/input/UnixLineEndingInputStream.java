package org.apache.commons.p052io.input;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: org.apache.commons.io.input.UnixLineEndingInputStream */
public class UnixLineEndingInputStream extends InputStream {
    private final boolean ensureLineFeedAtEndOfFile;
    private boolean eofSeen = false;
    private boolean slashNSeen = false;
    private boolean slashRSeen = false;
    private final InputStream target;

    public UnixLineEndingInputStream(InputStream in, boolean ensureLineFeedAtEndOfFile2) {
        this.target = in;
        this.ensureLineFeedAtEndOfFile = ensureLineFeedAtEndOfFile2;
    }

    private int readWithUpdate() throws IOException {
        boolean z;
        boolean z2 = true;
        int target2 = this.target.read();
        this.eofSeen = target2 == -1;
        if (!this.eofSeen) {
            if (target2 == 10) {
                z = true;
            } else {
                z = false;
            }
            this.slashNSeen = z;
            if (target2 != 13) {
                z2 = false;
            }
            this.slashRSeen = z2;
        }
        return target2;
    }

    public int read() throws IOException {
        boolean previousWasSlashR = this.slashRSeen;
        if (this.eofSeen) {
            return eofGame(previousWasSlashR);
        }
        int readWithUpdate = readWithUpdate();
        if (this.eofSeen) {
            return eofGame(previousWasSlashR);
        }
        if (this.slashRSeen) {
            return 10;
        }
        if (!previousWasSlashR || !this.slashNSeen) {
            return readWithUpdate;
        }
        return read();
    }

    private int eofGame(boolean previousWasSlashR) {
        if (previousWasSlashR || !this.ensureLineFeedAtEndOfFile || this.slashNSeen) {
            return -1;
        }
        this.slashNSeen = true;
        return 10;
    }

    public void close() throws IOException {
        super.close();
        this.target.close();
    }

    public synchronized void mark(int readlimit) {
        throw new UnsupportedOperationException("Mark notsupported");
    }
}
