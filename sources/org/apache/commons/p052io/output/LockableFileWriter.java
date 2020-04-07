package org.apache.commons.p052io.output;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import org.apache.commons.p052io.Charsets;
import org.apache.commons.p052io.FileUtils;

/* renamed from: org.apache.commons.io.output.LockableFileWriter */
public class LockableFileWriter extends Writer {
    private static final String LCK = ".lck";
    private final File lockFile;
    private final Writer out;

    public LockableFileWriter(String fileName) throws IOException {
        this(fileName, false, (String) null);
    }

    public LockableFileWriter(String fileName, boolean append) throws IOException {
        this(fileName, append, (String) null);
    }

    public LockableFileWriter(String fileName, boolean append, String lockDir) throws IOException {
        this(new File(fileName), append, lockDir);
    }

    public LockableFileWriter(File file) throws IOException {
        this(file, false, (String) null);
    }

    public LockableFileWriter(File file, boolean append) throws IOException {
        this(file, append, (String) null);
    }

    @Deprecated
    public LockableFileWriter(File file, boolean append, String lockDir) throws IOException {
        this(file, Charset.defaultCharset(), append, lockDir);
    }

    public LockableFileWriter(File file, Charset encoding) throws IOException {
        this(file, encoding, false, (String) null);
    }

    public LockableFileWriter(File file, String encoding) throws IOException {
        this(file, encoding, false, (String) null);
    }

    public LockableFileWriter(File file, Charset encoding, boolean append, String lockDir) throws IOException {
        File file2 = file.getAbsoluteFile();
        if (file2.getParentFile() != null) {
            FileUtils.forceMkdir(file2.getParentFile());
        }
        if (file2.isDirectory()) {
            throw new IOException("File specified is a directory");
        }
        if (lockDir == null) {
            lockDir = System.getProperty("java.io.tmpdir");
        }
        File lockDirFile = new File(lockDir);
        FileUtils.forceMkdir(lockDirFile);
        testLockDir(lockDirFile);
        this.lockFile = new File(lockDirFile, file2.getName() + LCK);
        createLock();
        this.out = initWriter(file2, encoding, append);
    }

    public LockableFileWriter(File file, String encoding, boolean append, String lockDir) throws IOException {
        this(file, Charsets.toCharset(encoding), append, lockDir);
    }

    private void testLockDir(File lockDir) throws IOException {
        if (!lockDir.exists()) {
            throw new IOException("Could not find lockDir: " + lockDir.getAbsolutePath());
        } else if (!lockDir.canWrite()) {
            throw new IOException("Could not write to lockDir: " + lockDir.getAbsolutePath());
        }
    }

    private void createLock() throws IOException {
        synchronized (LockableFileWriter.class) {
            if (!this.lockFile.createNewFile()) {
                throw new IOException("Can't write file, lock " + this.lockFile.getAbsolutePath() + " exists");
            }
            this.lockFile.deleteOnExit();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.io.Writer initWriter(java.io.File r8, java.nio.charset.Charset r9, boolean r10) throws java.io.IOException {
        /*
            r7 = this;
            boolean r1 = r8.exists()
            r2 = 0
            r4 = 0
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0019, RuntimeException -> 0x002b }
            java.lang.String r6 = r8.getAbsolutePath()     // Catch:{ IOException -> 0x0019, RuntimeException -> 0x002b }
            r3.<init>(r6, r10)     // Catch:{ IOException -> 0x0019, RuntimeException -> 0x002b }
            java.io.OutputStreamWriter r5 = new java.io.OutputStreamWriter     // Catch:{ IOException -> 0x0040, RuntimeException -> 0x003d }
            java.nio.charset.Charset r6 = org.apache.commons.p052io.Charsets.toCharset(r9)     // Catch:{ IOException -> 0x0040, RuntimeException -> 0x003d }
            r5.<init>(r3, r6)     // Catch:{ IOException -> 0x0040, RuntimeException -> 0x003d }
            return r5
        L_0x0019:
            r0 = move-exception
        L_0x001a:
            org.apache.commons.p052io.IOUtils.closeQuietly(r4)
            org.apache.commons.p052io.IOUtils.closeQuietly(r2)
            java.io.File r6 = r7.lockFile
            org.apache.commons.p052io.FileUtils.deleteQuietly(r6)
            if (r1 != 0) goto L_0x002a
            org.apache.commons.p052io.FileUtils.deleteQuietly(r8)
        L_0x002a:
            throw r0
        L_0x002b:
            r0 = move-exception
        L_0x002c:
            org.apache.commons.p052io.IOUtils.closeQuietly(r4)
            org.apache.commons.p052io.IOUtils.closeQuietly(r2)
            java.io.File r6 = r7.lockFile
            org.apache.commons.p052io.FileUtils.deleteQuietly(r6)
            if (r1 != 0) goto L_0x003c
            org.apache.commons.p052io.FileUtils.deleteQuietly(r8)
        L_0x003c:
            throw r0
        L_0x003d:
            r0 = move-exception
            r2 = r3
            goto L_0x002c
        L_0x0040:
            r0 = move-exception
            r2 = r3
            goto L_0x001a
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.p052io.output.LockableFileWriter.initWriter(java.io.File, java.nio.charset.Charset, boolean):java.io.Writer");
    }

    public void close() throws IOException {
        try {
            this.out.close();
        } finally {
            this.lockFile.delete();
        }
    }

    public void write(int idx) throws IOException {
        this.out.write(idx);
    }

    public void write(char[] chr) throws IOException {
        this.out.write(chr);
    }

    public void write(char[] chr, int st, int end) throws IOException {
        this.out.write(chr, st, end);
    }

    public void write(String str) throws IOException {
        this.out.write(str);
    }

    public void write(String str, int st, int end) throws IOException {
        this.out.write(str, st, end);
    }

    public void flush() throws IOException {
        this.out.flush();
    }
}
