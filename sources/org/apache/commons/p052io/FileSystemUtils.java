package org.apache.commons.p052io;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.vending.expansion.downloader.Constants;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

/* renamed from: org.apache.commons.io.FileSystemUtils */
public class FileSystemUtils {

    /* renamed from: DF */
    private static final String f1721DF;
    private static final int INIT_PROBLEM = -1;
    private static final FileSystemUtils INSTANCE = new FileSystemUtils();

    /* renamed from: OS */
    private static final int f1722OS;
    private static final int OTHER = 0;
    private static final int POSIX_UNIX = 3;
    private static final int UNIX = 2;
    private static final int WINDOWS = 1;

    static {
        int os;
        String dfPath = "df";
        try {
            String osName = System.getProperty("os.name");
            if (osName == null) {
                throw new IOException("os.name not found");
            }
            String osName2 = osName.toLowerCase(Locale.ENGLISH);
            if (osName2.contains("windows")) {
                os = 1;
            } else if (osName2.contains("linux") || osName2.contains("mpe/ix") || osName2.contains("freebsd") || osName2.contains("irix") || osName2.contains("digital unix") || osName2.contains("unix") || osName2.contains("mac os x")) {
                os = 2;
            } else if (osName2.contains("sun os") || osName2.contains("sunos") || osName2.contains("solaris")) {
                os = 3;
                dfPath = "/usr/xpg4/bin/df";
            } else if (osName2.contains("hp-ux") || osName2.contains("aix")) {
                os = 3;
            } else {
                os = 0;
            }
            f1722OS = os;
            f1721DF = dfPath;
        } catch (Exception e) {
            os = -1;
        }
    }

    @Deprecated
    public static long freeSpace(String path) throws IOException {
        return INSTANCE.freeSpaceOS(path, f1722OS, false, -1);
    }

    public static long freeSpaceKb(String path) throws IOException {
        return freeSpaceKb(path, -1);
    }

    public static long freeSpaceKb(String path, long timeout) throws IOException {
        return INSTANCE.freeSpaceOS(path, f1722OS, true, timeout);
    }

    public static long freeSpaceKb() throws IOException {
        return freeSpaceKb(-1);
    }

    public static long freeSpaceKb(long timeout) throws IOException {
        return freeSpaceKb(new File(".").getAbsolutePath(), timeout);
    }

    /* access modifiers changed from: 0000 */
    public long freeSpaceOS(String path, int os, boolean kb, long timeout) throws IOException {
        if (path == null) {
            throw new IllegalArgumentException("Path must not be null");
        }
        switch (os) {
            case 0:
                throw new IllegalStateException("Unsupported operating system");
            case 1:
                return kb ? freeSpaceWindows(path, timeout) / 1024 : freeSpaceWindows(path, timeout);
            case 2:
                return freeSpaceUnix(path, kb, false, timeout);
            case 3:
                return freeSpaceUnix(path, kb, true, timeout);
            default:
                throw new IllegalStateException("Exception caught when determining operating system");
        }
    }

    /* access modifiers changed from: 0000 */
    public long freeSpaceWindows(String path, long timeout) throws IOException {
        String path2 = FilenameUtils.normalize(path, false);
        if (path2.length() > 0 && path2.charAt(0) != '\"') {
            path2 = "\"" + path2 + "\"";
        }
        List<String> lines = performCommand(new String[]{"cmd.exe", "/C", "dir /a /-c " + path2}, ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, timeout);
        for (int i = lines.size() - 1; i >= 0; i--) {
            String line = (String) lines.get(i);
            if (line.length() > 0) {
                return parseDir(line, path2);
            }
        }
        throw new IOException("Command line 'dir /-c' did not return any info for path '" + path2 + "'");
    }

    /* access modifiers changed from: 0000 */
    public long parseDir(String line, String path) throws IOException {
        int bytesStart = 0;
        int bytesEnd = 0;
        int j = line.length() - 1;
        while (true) {
            if (j < 0) {
                break;
            } else if (Character.isDigit(line.charAt(j))) {
                bytesEnd = j + 1;
                break;
            } else {
                j--;
            }
        }
        while (true) {
            if (j < 0) {
                break;
            }
            char c = line.charAt(j);
            if (!Character.isDigit(c) && c != ',' && c != '.') {
                bytesStart = j + 1;
                break;
            }
            j--;
        }
        if (j < 0) {
            throw new IOException("Command line 'dir /-c' did not return valid info for path '" + path + "'");
        }
        StringBuilder buf = new StringBuilder(line.substring(bytesStart, bytesEnd));
        int k = 0;
        while (k < buf.length()) {
            if (buf.charAt(k) == ',' || buf.charAt(k) == '.') {
                int k2 = k - 1;
                buf.deleteCharAt(k);
                k = k2;
            }
            k++;
        }
        return parseBytes(buf.toString(), path);
    }

    /* access modifiers changed from: 0000 */
    public long freeSpaceUnix(String path, boolean kb, boolean posix, long timeout) throws IOException {
        if (path.isEmpty()) {
            throw new IllegalArgumentException("Path must not be empty");
        }
        String flags = Constants.FILENAME_SEQUENCE_SEPARATOR;
        if (kb) {
            flags = flags + "k";
        }
        if (posix) {
            flags = flags + "P";
        }
        List<String> lines = performCommand(flags.length() > 1 ? new String[]{f1721DF, flags, path} : new String[]{f1721DF, path}, 3, timeout);
        if (lines.size() < 2) {
            throw new IOException("Command line '" + f1721DF + "' did not return info as expected " + "for path '" + path + "'- response was " + lines);
        }
        StringTokenizer tok = new StringTokenizer((String) lines.get(1), MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        if (tok.countTokens() >= 4) {
            tok.nextToken();
        } else if (tok.countTokens() != 1 || lines.size() < 3) {
            throw new IOException("Command line '" + f1721DF + "' did not return data as expected " + "for path '" + path + "'- check path is valid");
        } else {
            tok = new StringTokenizer((String) lines.get(2), MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        }
        tok.nextToken();
        tok.nextToken();
        return parseBytes(tok.nextToken(), path);
    }

    /* access modifiers changed from: 0000 */
    public long parseBytes(String freeSpace, String path) throws IOException {
        try {
            long bytes = Long.parseLong(freeSpace);
            if (bytes >= 0) {
                return bytes;
            }
            throw new IOException("Command line '" + f1721DF + "' did not find free space in response " + "for path '" + path + "'- check path is valid");
        } catch (NumberFormatException ex) {
            throw new IOException("Command line '" + f1721DF + "' did not return numeric data as expected " + "for path '" + path + "'- check path is valid", ex);
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00bf  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<java.lang.String> performCommand(java.lang.String[] r16, int r17, long r18) throws java.io.IOException {
        /*
            r15 = this;
            java.util.ArrayList r8 = new java.util.ArrayList
            r12 = 20
            r8.<init>(r12)
            r11 = 0
            r4 = 0
            r10 = 0
            r2 = 0
            r5 = 0
            java.lang.Thread r9 = org.apache.commons.p052io.ThreadMonitor.start(r18)     // Catch:{ InterruptedException -> 0x00fb }
            java.lang.Process r11 = r15.openProcess(r16)     // Catch:{ InterruptedException -> 0x00fb }
            java.io.InputStream r4 = r11.getInputStream()     // Catch:{ InterruptedException -> 0x00fb }
            java.io.OutputStream r10 = r11.getOutputStream()     // Catch:{ InterruptedException -> 0x00fb }
            java.io.InputStream r2 = r11.getErrorStream()     // Catch:{ InterruptedException -> 0x00fb }
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch:{ InterruptedException -> 0x00fb }
            java.io.InputStreamReader r12 = new java.io.InputStreamReader     // Catch:{ InterruptedException -> 0x00fb }
            java.nio.charset.Charset r13 = java.nio.charset.Charset.defaultCharset()     // Catch:{ InterruptedException -> 0x00fb }
            r12.<init>(r4, r13)     // Catch:{ InterruptedException -> 0x00fb }
            r6.<init>(r12)     // Catch:{ InterruptedException -> 0x00fb }
            java.lang.String r7 = r6.readLine()     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
        L_0x0032:
            if (r7 == 0) goto L_0x004e
            int r12 = r8.size()     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
            r0 = r17
            if (r12 >= r0) goto L_0x004e
            java.util.Locale r12 = java.util.Locale.ENGLISH     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
            java.lang.String r12 = r7.toLowerCase(r12)     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
            java.lang.String r7 = r12.trim()     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
            r8.add(r7)     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
            java.lang.String r7 = r6.readLine()     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
            goto L_0x0032
        L_0x004e:
            r11.waitFor()     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
            org.apache.commons.p052io.ThreadMonitor.stop(r9)     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
            int r12 = r11.exitValue()     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
            if (r12 == 0) goto L_0x00c3
            java.io.IOException r12 = new java.io.IOException     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
            r13.<init>()     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
            java.lang.String r14 = "Command line returned OS error code '"
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
            int r14 = r11.exitValue()     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
            java.lang.String r14 = "' for command "
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
            java.util.List r14 = java.util.Arrays.asList(r16)     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
            java.lang.String r13 = r13.toString()     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
            r12.<init>(r13)     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
            throw r12     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
        L_0x0085:
            r3 = move-exception
            r5 = r6
        L_0x0087:
            java.io.IOException r12 = new java.io.IOException     // Catch:{ all -> 0x00b0 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b0 }
            r13.<init>()     // Catch:{ all -> 0x00b0 }
            java.lang.String r14 = "Command line threw an InterruptedException for command "
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch:{ all -> 0x00b0 }
            java.util.List r14 = java.util.Arrays.asList(r16)     // Catch:{ all -> 0x00b0 }
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch:{ all -> 0x00b0 }
            java.lang.String r14 = " timeout="
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch:{ all -> 0x00b0 }
            r0 = r18
            java.lang.StringBuilder r13 = r13.append(r0)     // Catch:{ all -> 0x00b0 }
            java.lang.String r13 = r13.toString()     // Catch:{ all -> 0x00b0 }
            r12.<init>(r13, r3)     // Catch:{ all -> 0x00b0 }
            throw r12     // Catch:{ all -> 0x00b0 }
        L_0x00b0:
            r12 = move-exception
        L_0x00b1:
            org.apache.commons.p052io.IOUtils.closeQuietly(r4)
            org.apache.commons.p052io.IOUtils.closeQuietly(r10)
            org.apache.commons.p052io.IOUtils.closeQuietly(r2)
            org.apache.commons.p052io.IOUtils.closeQuietly(r5)
            if (r11 == 0) goto L_0x00c2
            r11.destroy()
        L_0x00c2:
            throw r12
        L_0x00c3:
            boolean r12 = r8.isEmpty()     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
            if (r12 == 0) goto L_0x00e9
            java.io.IOException r12 = new java.io.IOException     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
            r13.<init>()     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
            java.lang.String r14 = "Command line did not return any info for command "
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
            java.util.List r14 = java.util.Arrays.asList(r16)     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
            java.lang.String r13 = r13.toString()     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
            r12.<init>(r13)     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
            throw r12     // Catch:{ InterruptedException -> 0x0085, all -> 0x00e6 }
        L_0x00e6:
            r12 = move-exception
            r5 = r6
            goto L_0x00b1
        L_0x00e9:
            org.apache.commons.p052io.IOUtils.closeQuietly(r4)
            org.apache.commons.p052io.IOUtils.closeQuietly(r10)
            org.apache.commons.p052io.IOUtils.closeQuietly(r2)
            org.apache.commons.p052io.IOUtils.closeQuietly(r6)
            if (r11 == 0) goto L_0x00fa
            r11.destroy()
        L_0x00fa:
            return r8
        L_0x00fb:
            r3 = move-exception
            goto L_0x0087
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.p052io.FileSystemUtils.performCommand(java.lang.String[], int, long):java.util.List");
    }

    /* access modifiers changed from: 0000 */
    public Process openProcess(String[] cmdAttribs) throws IOException {
        return Runtime.getRuntime().exec(cmdAttribs);
    }
}
