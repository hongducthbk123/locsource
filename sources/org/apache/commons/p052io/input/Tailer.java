package org.apache.commons.p052io.input;

import java.io.File;
import java.nio.charset.Charset;

/* renamed from: org.apache.commons.io.input.Tailer */
public class Tailer implements Runnable {
    private static final int DEFAULT_BUFSIZE = 4096;
    private static final Charset DEFAULT_CHARSET = Charset.defaultCharset();
    private static final int DEFAULT_DELAY_MILLIS = 1000;
    private static final String RAF_MODE = "r";
    private final Charset cset;
    private final long delayMillis;
    private final boolean end;
    private final File file;
    private final byte[] inbuf;
    private final TailerListener listener;
    private final boolean reOpen;
    private volatile boolean run;

    public Tailer(File file2, TailerListener listener2) {
        this(file2, listener2, 1000);
    }

    public Tailer(File file2, TailerListener listener2, long delayMillis2) {
        this(file2, listener2, delayMillis2, false);
    }

    public Tailer(File file2, TailerListener listener2, long delayMillis2, boolean end2) {
        this(file2, listener2, delayMillis2, end2, 4096);
    }

    public Tailer(File file2, TailerListener listener2, long delayMillis2, boolean end2, boolean reOpen2) {
        this(file2, listener2, delayMillis2, end2, reOpen2, 4096);
    }

    public Tailer(File file2, TailerListener listener2, long delayMillis2, boolean end2, int bufSize) {
        this(file2, listener2, delayMillis2, end2, false, bufSize);
    }

    public Tailer(File file2, TailerListener listener2, long delayMillis2, boolean end2, boolean reOpen2, int bufSize) {
        this(file2, DEFAULT_CHARSET, listener2, delayMillis2, end2, reOpen2, bufSize);
    }

    public Tailer(File file2, Charset cset2, TailerListener listener2, long delayMillis2, boolean end2, boolean reOpen2, int bufSize) {
        this.run = true;
        this.file = file2;
        this.delayMillis = delayMillis2;
        this.end = end2;
        this.inbuf = new byte[bufSize];
        this.listener = listener2;
        listener2.init(this);
        this.reOpen = reOpen2;
        this.cset = cset2;
    }

    public static Tailer create(File file2, TailerListener listener2, long delayMillis2, boolean end2, int bufSize) {
        return create(file2, listener2, delayMillis2, end2, false, bufSize);
    }

    public static Tailer create(File file2, TailerListener listener2, long delayMillis2, boolean end2, boolean reOpen2, int bufSize) {
        return create(file2, DEFAULT_CHARSET, listener2, delayMillis2, end2, reOpen2, bufSize);
    }

    public static Tailer create(File file2, Charset charset, TailerListener listener2, long delayMillis2, boolean end2, boolean reOpen2, int bufSize) {
        Tailer tailer = new Tailer(file2, charset, listener2, delayMillis2, end2, reOpen2, bufSize);
        Thread thread = new Thread(tailer);
        thread.setDaemon(true);
        thread.start();
        return tailer;
    }

    public static Tailer create(File file2, TailerListener listener2, long delayMillis2, boolean end2) {
        return create(file2, listener2, delayMillis2, end2, 4096);
    }

    public static Tailer create(File file2, TailerListener listener2, long delayMillis2, boolean end2, boolean reOpen2) {
        return create(file2, listener2, delayMillis2, end2, reOpen2, 4096);
    }

    public static Tailer create(File file2, TailerListener listener2, long delayMillis2) {
        return create(file2, listener2, delayMillis2, false);
    }

    public static Tailer create(File file2, TailerListener listener2) {
        return create(file2, listener2, 1000, false);
    }

    public File getFile() {
        return this.file;
    }

    /* access modifiers changed from: protected */
    public boolean getRun() {
        return this.run;
    }

    public long getDelay() {
        return this.delayMillis;
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:64:0x00c9=Splitter:B:64:0x00c9, B:69:0x00d9=Splitter:B:69:0x00d9} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r14 = this;
            r7 = 0
            r2 = 0
            r8 = 0
            r10 = r7
        L_0x0006:
            boolean r12 = r14.getRun()     // Catch:{ InterruptedException -> 0x00c7, Exception -> 0x00d7, all -> 0x00e0 }
            if (r12 == 0) goto L_0x0040
            if (r10 != 0) goto L_0x0040
            java.io.RandomAccessFile r7 = new java.io.RandomAccessFile     // Catch:{ FileNotFoundException -> 0x0020 }
            java.io.File r12 = r14.file     // Catch:{ FileNotFoundException -> 0x0020 }
            java.lang.String r13 = "r"
            r7.<init>(r12, r13)     // Catch:{ FileNotFoundException -> 0x0020 }
        L_0x0017:
            if (r7 != 0) goto L_0x0028
            long r12 = r14.delayMillis     // Catch:{ InterruptedException -> 0x00ea, Exception -> 0x00e8 }
            java.lang.Thread.sleep(r12)     // Catch:{ InterruptedException -> 0x00ea, Exception -> 0x00e8 }
            r10 = r7
            goto L_0x0006
        L_0x0020:
            r0 = move-exception
            org.apache.commons.io.input.TailerListener r12 = r14.listener     // Catch:{ InterruptedException -> 0x00c7, Exception -> 0x00d7, all -> 0x00e0 }
            r12.fileNotFound()     // Catch:{ InterruptedException -> 0x00c7, Exception -> 0x00d7, all -> 0x00e0 }
            r7 = r10
            goto L_0x0017
        L_0x0028:
            boolean r12 = r14.end     // Catch:{ InterruptedException -> 0x00ea, Exception -> 0x00e8 }
            if (r12 == 0) goto L_0x003d
            java.io.File r12 = r14.file     // Catch:{ InterruptedException -> 0x00ea, Exception -> 0x00e8 }
            long r8 = r12.length()     // Catch:{ InterruptedException -> 0x00ea, Exception -> 0x00e8 }
        L_0x0032:
            java.io.File r12 = r14.file     // Catch:{ InterruptedException -> 0x00ea, Exception -> 0x00e8 }
            long r2 = r12.lastModified()     // Catch:{ InterruptedException -> 0x00ea, Exception -> 0x00e8 }
            r7.seek(r8)     // Catch:{ InterruptedException -> 0x00ea, Exception -> 0x00e8 }
            r10 = r7
            goto L_0x0006
        L_0x003d:
            r8 = 0
            goto L_0x0032
        L_0x0040:
            boolean r12 = r14.getRun()     // Catch:{ InterruptedException -> 0x00c7, Exception -> 0x00d7, all -> 0x00e0 }
            if (r12 == 0) goto L_0x00c2
            java.io.File r12 = r14.file     // Catch:{ InterruptedException -> 0x00c7, Exception -> 0x00d7, all -> 0x00e0 }
            boolean r6 = org.apache.commons.p052io.FileUtils.isFileNewer(r12, r2)     // Catch:{ InterruptedException -> 0x00c7, Exception -> 0x00d7, all -> 0x00e0 }
            java.io.File r12 = r14.file     // Catch:{ InterruptedException -> 0x00c7, Exception -> 0x00d7, all -> 0x00e0 }
            long r4 = r12.length()     // Catch:{ InterruptedException -> 0x00c7, Exception -> 0x00d7, all -> 0x00e0 }
            int r12 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r12 >= 0) goto L_0x007e
            org.apache.commons.io.input.TailerListener r12 = r14.listener     // Catch:{ InterruptedException -> 0x00c7, Exception -> 0x00d7, all -> 0x00e0 }
            r12.fileRotated()     // Catch:{ InterruptedException -> 0x00c7, Exception -> 0x00d7, all -> 0x00e0 }
            r11 = r10
            java.io.RandomAccessFile r7 = new java.io.RandomAccessFile     // Catch:{ FileNotFoundException -> 0x00ec }
            java.io.File r12 = r14.file     // Catch:{ FileNotFoundException -> 0x00ec }
            java.lang.String r13 = "r"
            r7.<init>(r12, r13)     // Catch:{ FileNotFoundException -> 0x00ec }
            r14.readLines(r11)     // Catch:{ IOException -> 0x006f }
        L_0x0068:
            r8 = 0
            org.apache.commons.p052io.IOUtils.closeQuietly(r11)     // Catch:{ FileNotFoundException -> 0x0076 }
            r10 = r7
            goto L_0x0040
        L_0x006f:
            r1 = move-exception
            org.apache.commons.io.input.TailerListener r12 = r14.listener     // Catch:{ FileNotFoundException -> 0x0076 }
            r12.handle(r1)     // Catch:{ FileNotFoundException -> 0x0076 }
            goto L_0x0068
        L_0x0076:
            r0 = move-exception
        L_0x0077:
            org.apache.commons.io.input.TailerListener r12 = r14.listener     // Catch:{ InterruptedException -> 0x00ea, Exception -> 0x00e8 }
            r12.fileNotFound()     // Catch:{ InterruptedException -> 0x00ea, Exception -> 0x00e8 }
            r10 = r7
            goto L_0x0040
        L_0x007e:
            int r12 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r12 <= 0) goto L_0x00b0
            long r8 = r14.readLines(r10)     // Catch:{ InterruptedException -> 0x00c7, Exception -> 0x00d7, all -> 0x00e0 }
            java.io.File r12 = r14.file     // Catch:{ InterruptedException -> 0x00c7, Exception -> 0x00d7, all -> 0x00e0 }
            long r2 = r12.lastModified()     // Catch:{ InterruptedException -> 0x00c7, Exception -> 0x00d7, all -> 0x00e0 }
        L_0x008c:
            boolean r12 = r14.reOpen     // Catch:{ InterruptedException -> 0x00c7, Exception -> 0x00d7, all -> 0x00e0 }
            if (r12 == 0) goto L_0x0093
            org.apache.commons.p052io.IOUtils.closeQuietly(r10)     // Catch:{ InterruptedException -> 0x00c7, Exception -> 0x00d7, all -> 0x00e0 }
        L_0x0093:
            long r12 = r14.delayMillis     // Catch:{ InterruptedException -> 0x00c7, Exception -> 0x00d7, all -> 0x00e0 }
            java.lang.Thread.sleep(r12)     // Catch:{ InterruptedException -> 0x00c7, Exception -> 0x00d7, all -> 0x00e0 }
            boolean r12 = r14.getRun()     // Catch:{ InterruptedException -> 0x00c7, Exception -> 0x00d7, all -> 0x00e0 }
            if (r12 == 0) goto L_0x00ef
            boolean r12 = r14.reOpen     // Catch:{ InterruptedException -> 0x00c7, Exception -> 0x00d7, all -> 0x00e0 }
            if (r12 == 0) goto L_0x00ef
            java.io.RandomAccessFile r7 = new java.io.RandomAccessFile     // Catch:{ InterruptedException -> 0x00c7, Exception -> 0x00d7, all -> 0x00e0 }
            java.io.File r12 = r14.file     // Catch:{ InterruptedException -> 0x00c7, Exception -> 0x00d7, all -> 0x00e0 }
            java.lang.String r13 = "r"
            r7.<init>(r12, r13)     // Catch:{ InterruptedException -> 0x00c7, Exception -> 0x00d7, all -> 0x00e0 }
            r7.seek(r8)     // Catch:{ InterruptedException -> 0x00ea, Exception -> 0x00e8 }
        L_0x00ae:
            r10 = r7
            goto L_0x0040
        L_0x00b0:
            if (r6 == 0) goto L_0x008c
            r8 = 0
            r10.seek(r8)     // Catch:{ InterruptedException -> 0x00c7, Exception -> 0x00d7, all -> 0x00e0 }
            long r8 = r14.readLines(r10)     // Catch:{ InterruptedException -> 0x00c7, Exception -> 0x00d7, all -> 0x00e0 }
            java.io.File r12 = r14.file     // Catch:{ InterruptedException -> 0x00c7, Exception -> 0x00d7, all -> 0x00e0 }
            long r2 = r12.lastModified()     // Catch:{ InterruptedException -> 0x00c7, Exception -> 0x00d7, all -> 0x00e0 }
            goto L_0x008c
        L_0x00c2:
            org.apache.commons.p052io.IOUtils.closeQuietly(r10)
            r7 = r10
        L_0x00c6:
            return
        L_0x00c7:
            r0 = move-exception
            r7 = r10
        L_0x00c9:
            java.lang.Thread r12 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x00e6 }
            r12.interrupt()     // Catch:{ all -> 0x00e6 }
            r14.stop(r0)     // Catch:{ all -> 0x00e6 }
            org.apache.commons.p052io.IOUtils.closeQuietly(r7)
            goto L_0x00c6
        L_0x00d7:
            r0 = move-exception
            r7 = r10
        L_0x00d9:
            r14.stop(r0)     // Catch:{ all -> 0x00e6 }
            org.apache.commons.p052io.IOUtils.closeQuietly(r7)
            goto L_0x00c6
        L_0x00e0:
            r12 = move-exception
            r7 = r10
        L_0x00e2:
            org.apache.commons.p052io.IOUtils.closeQuietly(r7)
            throw r12
        L_0x00e6:
            r12 = move-exception
            goto L_0x00e2
        L_0x00e8:
            r0 = move-exception
            goto L_0x00d9
        L_0x00ea:
            r0 = move-exception
            goto L_0x00c9
        L_0x00ec:
            r0 = move-exception
            r7 = r10
            goto L_0x0077
        L_0x00ef:
            r7 = r10
            goto L_0x00ae
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.p052io.input.Tailer.run():void");
    }

    private void stop(Exception e) {
        this.listener.handle(e);
        stop();
    }

    public void stop() {
        this.run = false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x007d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long readLines(java.io.RandomAccessFile r15) throws java.io.IOException {
        /*
            r14 = this;
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream
            r9 = 64
            r2.<init>(r9)
            long r4 = r15.getFilePointer()
            r6 = r4
            r8 = 0
        L_0x000d:
            boolean r9 = r14.getRun()
            if (r9 == 0) goto L_0x0071
            byte[] r9 = r14.inbuf
            int r3 = r15.read(r9)
            r9 = -1
            if (r3 == r9) goto L_0x0071
            r1 = 0
        L_0x001d:
            if (r1 >= r3) goto L_0x006c
            byte[] r9 = r14.inbuf
            byte r0 = r9[r1]
            switch(r0) {
                case 10: goto L_0x0048;
                case 11: goto L_0x0026;
                case 12: goto L_0x0026;
                case 13: goto L_0x0063;
                default: goto L_0x0026;
            }
        L_0x0026:
            if (r8 == 0) goto L_0x0042
            r8 = 0
            org.apache.commons.io.input.TailerListener r9 = r14.listener
            java.lang.String r10 = new java.lang.String
            byte[] r11 = r2.toByteArray()
            java.nio.charset.Charset r12 = r14.cset
            r10.<init>(r11, r12)
            r9.handle(r10)
            r2.reset()
            long r10 = (long) r1
            long r10 = r10 + r4
            r12 = 1
            long r6 = r10 + r12
        L_0x0042:
            r2.write(r0)
        L_0x0045:
            int r1 = r1 + 1
            goto L_0x001d
        L_0x0048:
            r8 = 0
            org.apache.commons.io.input.TailerListener r9 = r14.listener
            java.lang.String r10 = new java.lang.String
            byte[] r11 = r2.toByteArray()
            java.nio.charset.Charset r12 = r14.cset
            r10.<init>(r11, r12)
            r9.handle(r10)
            r2.reset()
            long r10 = (long) r1
            long r10 = r10 + r4
            r12 = 1
            long r6 = r10 + r12
            goto L_0x0045
        L_0x0063:
            if (r8 == 0) goto L_0x006a
            r9 = 13
            r2.write(r9)
        L_0x006a:
            r8 = 1
            goto L_0x0045
        L_0x006c:
            long r4 = r15.getFilePointer()
            goto L_0x000d
        L_0x0071:
            org.apache.commons.p052io.IOUtils.closeQuietly(r2)
            r15.seek(r6)
            org.apache.commons.io.input.TailerListener r9 = r14.listener
            boolean r9 = r9 instanceof org.apache.commons.p052io.input.TailerListenerAdapter
            if (r9 == 0) goto L_0x0084
            org.apache.commons.io.input.TailerListener r9 = r14.listener
            org.apache.commons.io.input.TailerListenerAdapter r9 = (org.apache.commons.p052io.input.TailerListenerAdapter) r9
            r9.endOfFileReached()
        L_0x0084:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.p052io.input.Tailer.readLines(java.io.RandomAccessFile):long");
    }
}
