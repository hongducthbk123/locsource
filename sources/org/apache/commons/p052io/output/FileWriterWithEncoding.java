package org.apache.commons.p052io.output;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/* renamed from: org.apache.commons.io.output.FileWriterWithEncoding */
public class FileWriterWithEncoding extends Writer {
    private final Writer out;

    public FileWriterWithEncoding(String filename, String encoding) throws IOException {
        this(new File(filename), encoding, false);
    }

    public FileWriterWithEncoding(String filename, String encoding, boolean append) throws IOException {
        this(new File(filename), encoding, append);
    }

    public FileWriterWithEncoding(String filename, Charset encoding) throws IOException {
        this(new File(filename), encoding, false);
    }

    public FileWriterWithEncoding(String filename, Charset encoding, boolean append) throws IOException {
        this(new File(filename), encoding, append);
    }

    public FileWriterWithEncoding(String filename, CharsetEncoder encoding) throws IOException {
        this(new File(filename), encoding, false);
    }

    public FileWriterWithEncoding(String filename, CharsetEncoder encoding, boolean append) throws IOException {
        this(new File(filename), encoding, append);
    }

    public FileWriterWithEncoding(File file, String encoding) throws IOException {
        this(file, encoding, false);
    }

    public FileWriterWithEncoding(File file, String encoding, boolean append) throws IOException {
        this.out = initWriter(file, encoding, append);
    }

    public FileWriterWithEncoding(File file, Charset encoding) throws IOException {
        this(file, encoding, false);
    }

    public FileWriterWithEncoding(File file, Charset encoding, boolean append) throws IOException {
        this.out = initWriter(file, encoding, append);
    }

    public FileWriterWithEncoding(File file, CharsetEncoder encoding) throws IOException {
        this(file, encoding, false);
    }

    public FileWriterWithEncoding(File file, CharsetEncoder encoding, boolean append) throws IOException {
        this.out = initWriter(file, encoding, append);
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0058  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.io.Writer initWriter(java.io.File r8, java.lang.Object r9, boolean r10) throws java.io.IOException {
        /*
            if (r8 != 0) goto L_0x000a
            java.lang.NullPointerException r6 = new java.lang.NullPointerException
            java.lang.String r7 = "File is missing"
            r6.<init>(r7)
            throw r6
        L_0x000a:
            if (r9 != 0) goto L_0x0014
            java.lang.NullPointerException r6 = new java.lang.NullPointerException
            java.lang.String r7 = "Encoding is missing"
            r6.<init>(r7)
            throw r6
        L_0x0014:
            boolean r1 = r8.exists()
            r2 = 0
            r4 = 0
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0042, RuntimeException -> 0x004f }
            r3.<init>(r8, r10)     // Catch:{ IOException -> 0x0042, RuntimeException -> 0x004f }
            boolean r6 = r9 instanceof java.nio.charset.Charset     // Catch:{ IOException -> 0x005f, RuntimeException -> 0x005c }
            if (r6 == 0) goto L_0x002c
            java.io.OutputStreamWriter r5 = new java.io.OutputStreamWriter     // Catch:{ IOException -> 0x005f, RuntimeException -> 0x005c }
            java.nio.charset.Charset r9 = (java.nio.charset.Charset) r9     // Catch:{ IOException -> 0x005f, RuntimeException -> 0x005c }
            r5.<init>(r3, r9)     // Catch:{ IOException -> 0x005f, RuntimeException -> 0x005c }
            r4 = r5
        L_0x002b:
            return r4
        L_0x002c:
            boolean r6 = r9 instanceof java.nio.charset.CharsetEncoder     // Catch:{ IOException -> 0x005f, RuntimeException -> 0x005c }
            if (r6 == 0) goto L_0x0039
            java.io.OutputStreamWriter r5 = new java.io.OutputStreamWriter     // Catch:{ IOException -> 0x005f, RuntimeException -> 0x005c }
            java.nio.charset.CharsetEncoder r9 = (java.nio.charset.CharsetEncoder) r9     // Catch:{ IOException -> 0x005f, RuntimeException -> 0x005c }
            r5.<init>(r3, r9)     // Catch:{ IOException -> 0x005f, RuntimeException -> 0x005c }
            r4 = r5
            goto L_0x002b
        L_0x0039:
            java.io.OutputStreamWriter r5 = new java.io.OutputStreamWriter     // Catch:{ IOException -> 0x005f, RuntimeException -> 0x005c }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ IOException -> 0x005f, RuntimeException -> 0x005c }
            r5.<init>(r3, r9)     // Catch:{ IOException -> 0x005f, RuntimeException -> 0x005c }
            r4 = r5
            goto L_0x002b
        L_0x0042:
            r0 = move-exception
        L_0x0043:
            org.apache.commons.p052io.IOUtils.closeQuietly(r4)
            org.apache.commons.p052io.IOUtils.closeQuietly(r2)
            if (r1 != 0) goto L_0x004e
            org.apache.commons.p052io.FileUtils.deleteQuietly(r8)
        L_0x004e:
            throw r0
        L_0x004f:
            r0 = move-exception
        L_0x0050:
            org.apache.commons.p052io.IOUtils.closeQuietly(r4)
            org.apache.commons.p052io.IOUtils.closeQuietly(r2)
            if (r1 != 0) goto L_0x005b
            org.apache.commons.p052io.FileUtils.deleteQuietly(r8)
        L_0x005b:
            throw r0
        L_0x005c:
            r0 = move-exception
            r2 = r3
            goto L_0x0050
        L_0x005f:
            r0 = move-exception
            r2 = r3
            goto L_0x0043
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.p052io.output.FileWriterWithEncoding.initWriter(java.io.File, java.lang.Object, boolean):java.io.Writer");
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

    public void close() throws IOException {
        this.out.close();
    }
}
