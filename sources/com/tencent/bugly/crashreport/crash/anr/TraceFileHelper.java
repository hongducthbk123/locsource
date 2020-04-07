package com.tencent.bugly.crashreport.crash.anr;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.tencent.bugly.proguard.C2014w;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.p052io.IOUtils;

/* compiled from: BUGLY */
public class TraceFileHelper {

    /* renamed from: com.tencent.bugly.crashreport.crash.anr.TraceFileHelper$a */
    /* compiled from: BUGLY */
    public static class C1947a {

        /* renamed from: a */
        public long f1323a;

        /* renamed from: b */
        public String f1324b;

        /* renamed from: c */
        public long f1325c;

        /* renamed from: d */
        public Map<String, String[]> f1326d;
    }

    /* renamed from: com.tencent.bugly.crashreport.crash.anr.TraceFileHelper$b */
    /* compiled from: BUGLY */
    public interface C1948b {
        /* renamed from: a */
        boolean mo19448a(long j);

        /* renamed from: a */
        boolean mo19449a(long j, long j2, String str);

        /* renamed from: a */
        boolean mo19450a(String str, int i, String str2, String str3);
    }

    public static C1947a readTargetDumpInfo(String str, String str2, final boolean z) {
        if (str == null || str2 == null) {
            return null;
        }
        final C1947a aVar = new C1947a();
        readTraceFile(str2, new C1948b() {
            /* renamed from: a */
            public final boolean mo19450a(String str, int i, String str2, String str3) {
                C2014w.m2117c("new thread %s", str);
                if (aVar.f1323a > 0 && aVar.f1325c > 0 && aVar.f1324b != null) {
                    if (aVar.f1326d == null) {
                        aVar.f1326d = new HashMap();
                    }
                    aVar.f1326d.put(str, new String[]{str2, str3, i});
                }
                return true;
            }

            /* renamed from: a */
            public final boolean mo19449a(long j, long j2, String str) {
                C2014w.m2117c("new process %s", str);
                if (!str.equals(str)) {
                    return true;
                }
                aVar.f1323a = j;
                aVar.f1324b = str;
                aVar.f1325c = j2;
                if (!z) {
                    return false;
                }
                return true;
            }

            /* renamed from: a */
            public final boolean mo19448a(long j) {
                C2014w.m2117c("process end %d", Long.valueOf(j));
                if (aVar.f1323a <= 0 || aVar.f1325c <= 0 || aVar.f1324b == null) {
                    return true;
                }
                return false;
            }
        });
        if (aVar.f1323a <= 0 || aVar.f1325c <= 0 || aVar.f1324b == null) {
            return null;
        }
        return aVar;
    }

    public static C1947a readFirstDumpInfo(String str, final boolean z) {
        if (str == null) {
            C2014w.m2119e("path:%s", str);
            return null;
        }
        final C1947a aVar = new C1947a();
        readTraceFile(str, new C1948b() {
            /* renamed from: a */
            public final boolean mo19450a(String str, int i, String str2, String str3) {
                C2014w.m2117c("new thread %s", str);
                if (aVar.f1326d == null) {
                    aVar.f1326d = new HashMap();
                }
                aVar.f1326d.put(str, new String[]{str2, str3, i});
                return true;
            }

            /* renamed from: a */
            public final boolean mo19449a(long j, long j2, String str) {
                C2014w.m2117c("new process %s", str);
                aVar.f1323a = j;
                aVar.f1324b = str;
                aVar.f1325c = j2;
                if (!z) {
                    return false;
                }
                return true;
            }

            /* renamed from: a */
            public final boolean mo19448a(long j) {
                C2014w.m2117c("process end %d", Long.valueOf(j));
                return false;
            }
        });
        if (aVar.f1323a > 0 && aVar.f1325c > 0 && aVar.f1324b != null) {
            return aVar;
        }
        C2014w.m2119e("first dump error %s", aVar.f1323a + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + aVar.f1325c + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + aVar.f1324b);
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:102:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x019a, code lost:
        if (r13.mo19448a(java.lang.Long.parseLong(r1[1].toString().split("\\s")[2])) != false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x01a1, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x01a6, code lost:
        if (com.tencent.bugly.proguard.C2014w.m2114a(r0) == false) goto L_0x01a8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x01a8, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:?, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x01c2 A[SYNTHETIC, Splitter:B:70:0x01c2] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void readTraceFile(java.lang.String r12, com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.C1948b r13) {
        /*
            if (r12 == 0) goto L_0x0004
            if (r13 != 0) goto L_0x0005
        L_0x0004:
            return
        L_0x0005:
            java.io.File r0 = new java.io.File
            r0.<init>(r12)
            boolean r1 = r0.exists()
            if (r1 == 0) goto L_0x0004
            r0.lastModified()
            r0.length()
            r1 = 0
            java.io.BufferedReader r7 = new java.io.BufferedReader     // Catch:{ Exception -> 0x01d6, all -> 0x01be }
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ Exception -> 0x01d6, all -> 0x01be }
            r2.<init>(r0)     // Catch:{ Exception -> 0x01d6, all -> 0x01be }
            r7.<init>(r2)     // Catch:{ Exception -> 0x01d6, all -> 0x01be }
            java.lang.String r0 = "-{5}\\spid\\s\\d+\\sat\\s\\d+-\\d+-\\d+\\s\\d{2}:\\d{2}:\\d{2}\\s-{5}"
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.lang.String r1 = "-{5}\\send\\s\\d+\\s-{5}"
            java.util.regex.Pattern r8 = java.util.regex.Pattern.compile(r1)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.lang.String r1 = "Cmd\\sline:\\s(\\S+)"
            java.util.regex.Pattern r9 = java.util.regex.Pattern.compile(r1)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.lang.String r1 = "\".+\"\\s(daemon\\s){0,1}prio=\\d+\\stid=\\d+\\s.*"
            java.util.regex.Pattern r10 = java.util.regex.Pattern.compile(r1)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.text.SimpleDateFormat r11 = new java.text.SimpleDateFormat     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.lang.String r1 = "yyyy-MM-dd HH:mm:ss"
            java.util.Locale r2 = java.util.Locale.US     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            r11.<init>(r1, r2)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
        L_0x0043:
            r1 = 1
            java.util.regex.Pattern[] r1 = new java.util.regex.Pattern[r1]     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            r2 = 0
            r1[r2] = r0     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.lang.Object[] r1 = m1763a(r7, r1)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            if (r1 == 0) goto L_0x01ad
            r2 = 1
            r1 = r1[r2]     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.lang.String r2 = "\\s"
            java.lang.String[] r1 = r1.split(r2)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            r2 = 2
            r2 = r1[r2]     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            long r2 = java.lang.Long.parseLong(r2)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            r4.<init>()     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            r5 = 4
            r5 = r1[r5]     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.lang.String r5 = " "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            r5 = 5
            r1 = r1[r5]     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.lang.StringBuilder r1 = r4.append(r1)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.util.Date r1 = r11.parse(r1)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            long r4 = r1.getTime()     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            r1 = 1
            java.util.regex.Pattern[] r1 = new java.util.regex.Pattern[r1]     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            r6 = 0
            r1[r6] = r9     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.lang.Object[] r1 = m1763a(r7, r1)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            if (r1 != 0) goto L_0x00a5
            r7.close()     // Catch:{ IOException -> 0x0099 }
            goto L_0x0004
        L_0x0099:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C2014w.m2114a(r0)
            if (r1 != 0) goto L_0x0004
            r0.printStackTrace()
            goto L_0x0004
        L_0x00a5:
            r6 = 1
            r1 = r1[r6]     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.util.regex.Matcher r1 = r9.matcher(r1)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            r1.find()     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            r6 = 1
            r1.group(r6)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            r6 = 1
            java.lang.String r6 = r1.group(r6)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            r1 = r13
            boolean r1 = r1.mo19449a(r2, r4, r6)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            if (r1 != 0) goto L_0x00d4
            r7.close()     // Catch:{ IOException -> 0x00c8 }
            goto L_0x0004
        L_0x00c8:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C2014w.m2114a(r0)
            if (r1 != 0) goto L_0x0004
            r0.printStackTrace()
            goto L_0x0004
        L_0x00d4:
            r1 = 2
            java.util.regex.Pattern[] r1 = new java.util.regex.Pattern[r1]     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            r2 = 0
            r1[r2] = r10     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            r2 = 1
            r1[r2] = r8     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.lang.Object[] r1 = m1763a(r7, r1)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            if (r1 == 0) goto L_0x0043
            r2 = 0
            r2 = r1[r2]     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            if (r2 != r10) goto L_0x0182
            r2 = 1
            r1 = r1[r2]     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.lang.String r2 = "\".+\""
            java.util.regex.Pattern r2 = java.util.regex.Pattern.compile(r2)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.util.regex.Matcher r2 = r2.matcher(r1)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            r2.find()     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.lang.String r2 = r2.group()     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            r3 = 1
            int r4 = r2.length()     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            int r4 = r4 + -1
            java.lang.String r2 = r2.substring(r3, r4)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.lang.String r3 = "NATIVE"
            r1.contains(r3)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.lang.String r3 = "tid=\\d+"
            java.util.regex.Pattern r3 = java.util.regex.Pattern.compile(r3)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.util.regex.Matcher r1 = r3.matcher(r1)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            r1.find()     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.lang.String r1 = r1.group()     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.lang.String r3 = "="
            int r3 = r1.indexOf(r3)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            int r3 = r3 + 1
            java.lang.String r1 = r1.substring(r3)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.lang.String r3 = m1762a(r7)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.lang.String r4 = m1764b(r7)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            r13.mo19450a(r2, r1, r3, r4)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            goto L_0x00d4
        L_0x013d:
            r0 = move-exception
            r1 = r7
        L_0x013f:
            boolean r2 = com.tencent.bugly.proguard.C2014w.m2114a(r0)     // Catch:{ all -> 0x01d3 }
            if (r2 != 0) goto L_0x0148
            r0.printStackTrace()     // Catch:{ all -> 0x01d3 }
        L_0x0148:
            java.lang.String r2 = "trace open fail:%s : %s"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x01d3 }
            r4 = 0
            java.lang.Class r5 = r0.getClass()     // Catch:{ all -> 0x01d3 }
            java.lang.String r5 = r5.getName()     // Catch:{ all -> 0x01d3 }
            r3[r4] = r5     // Catch:{ all -> 0x01d3 }
            r4 = 1
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x01d3 }
            r5.<init>()     // Catch:{ all -> 0x01d3 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x01d3 }
            java.lang.StringBuilder r0 = r5.append(r0)     // Catch:{ all -> 0x01d3 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x01d3 }
            r3[r4] = r0     // Catch:{ all -> 0x01d3 }
            com.tencent.bugly.proguard.C2014w.m2118d(r2, r3)     // Catch:{ all -> 0x01d3 }
            if (r1 == 0) goto L_0x0004
            r1.close()     // Catch:{ IOException -> 0x0176 }
            goto L_0x0004
        L_0x0176:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C2014w.m2114a(r0)
            if (r1 != 0) goto L_0x0004
            r0.printStackTrace()
            goto L_0x0004
        L_0x0182:
            r2 = 1
            r1 = r1[r2]     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            java.lang.String r2 = "\\s"
            java.lang.String[] r1 = r1.split(r2)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            r2 = 2
            r1 = r1[r2]     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            long r2 = java.lang.Long.parseLong(r1)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            boolean r1 = r13.mo19448a(r2)     // Catch:{ Exception -> 0x013d, all -> 0x01d1 }
            if (r1 != 0) goto L_0x0043
            r7.close()     // Catch:{ IOException -> 0x01a1 }
            goto L_0x0004
        L_0x01a1:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C2014w.m2114a(r0)
            if (r1 != 0) goto L_0x0004
            r0.printStackTrace()
            goto L_0x0004
        L_0x01ad:
            r7.close()     // Catch:{ IOException -> 0x01b2 }
            goto L_0x0004
        L_0x01b2:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.C2014w.m2114a(r0)
            if (r1 != 0) goto L_0x0004
            r0.printStackTrace()
            goto L_0x0004
        L_0x01be:
            r0 = move-exception
            r7 = r1
        L_0x01c0:
            if (r7 == 0) goto L_0x01c5
            r7.close()     // Catch:{ IOException -> 0x01c6 }
        L_0x01c5:
            throw r0
        L_0x01c6:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.proguard.C2014w.m2114a(r1)
            if (r2 != 0) goto L_0x01c5
            r1.printStackTrace()
            goto L_0x01c5
        L_0x01d1:
            r0 = move-exception
            goto L_0x01c0
        L_0x01d3:
            r0 = move-exception
            r7 = r1
            goto L_0x01c0
        L_0x01d6:
            r0 = move-exception
            goto L_0x013f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.readTraceFile(java.lang.String, com.tencent.bugly.crashreport.crash.anr.TraceFileHelper$b):void");
    }

    /* renamed from: a */
    private static Object[] m1763a(BufferedReader bufferedReader, Pattern... patternArr) throws IOException {
        if (bufferedReader == null || patternArr == null) {
            return null;
        }
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return null;
            }
            int length = patternArr.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    Pattern pattern = patternArr[i];
                    if (pattern.matcher(readLine).matches()) {
                        return new Object[]{pattern, readLine};
                    }
                    i++;
                }
            }
        }
    }

    /* renamed from: a */
    private static String m1762a(BufferedReader bufferedReader) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 3; i++) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return null;
            }
            stringBuffer.append(readLine + IOUtils.LINE_SEPARATOR_UNIX);
        }
        return stringBuffer.toString();
    }

    /* renamed from: b */
    private static String m1764b(BufferedReader bufferedReader) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null && readLine.trim().length() > 0) {
                stringBuffer.append(readLine + IOUtils.LINE_SEPARATOR_UNIX);
            }
        }
        return stringBuffer.toString();
    }
}
