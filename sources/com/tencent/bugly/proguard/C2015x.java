package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.tencent.bugly.crashreport.common.info.C1938a;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/* renamed from: com.tencent.bugly.proguard.x */
/* compiled from: BUGLY */
public final class C2015x {

    /* renamed from: a */
    public static boolean f1695a = true;

    /* renamed from: b */
    private static SimpleDateFormat f1696b;

    /* renamed from: c */
    private static int f1697c = 5120;

    /* renamed from: d */
    private static StringBuilder f1698d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public static StringBuilder f1699e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public static boolean f1700f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public static C2017a f1701g;

    /* renamed from: h */
    private static String f1702h;

    /* renamed from: i */
    private static String f1703i;

    /* renamed from: j */
    private static Context f1704j;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public static String f1705k;

    /* renamed from: l */
    private static boolean f1706l;

    /* renamed from: m */
    private static int f1707m;
    /* access modifiers changed from: private */

    /* renamed from: n */
    public static Object f1708n = new Object();

    /* renamed from: com.tencent.bugly.proguard.x$a */
    /* compiled from: BUGLY */
    public static class C2017a {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public boolean f1710a;
        /* access modifiers changed from: private */

        /* renamed from: b */
        public File f1711b;

        /* renamed from: c */
        private String f1712c;

        /* renamed from: d */
        private long f1713d;
        /* access modifiers changed from: private */

        /* renamed from: e */
        public long f1714e = 30720;

        public C2017a(String str) {
            if (str != null && !str.equals("")) {
                this.f1712c = str;
                this.f1710a = m2133a();
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: a */
        public synchronized boolean m2133a() {
            boolean z = false;
            synchronized (this) {
                try {
                    this.f1711b = new File(this.f1712c);
                    if (!this.f1711b.exists() || this.f1711b.delete()) {
                        if (!this.f1711b.createNewFile()) {
                            this.f1710a = false;
                        }
                        z = true;
                    } else {
                        this.f1710a = false;
                    }
                } catch (Throwable th) {
                    this.f1710a = false;
                }
            }
            return z;
        }

        /* JADX WARNING: Removed duplicated region for block: B:22:0x0034 A[SYNTHETIC, Splitter:B:22:0x0034] */
        /* JADX WARNING: Removed duplicated region for block: B:28:0x003e A[SYNTHETIC, Splitter:B:28:0x003e] */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final synchronized boolean mo19642a(java.lang.String r9) {
            /*
                r8 = this;
                r1 = 1
                r0 = 0
                monitor-enter(r8)
                boolean r2 = r8.f1710a     // Catch:{ all -> 0x0042 }
                if (r2 != 0) goto L_0x0009
            L_0x0007:
                monitor-exit(r8)
                return r0
            L_0x0009:
                r3 = 0
                java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x002d, all -> 0x003a }
                java.io.File r4 = r8.f1711b     // Catch:{ Throwable -> 0x002d, all -> 0x003a }
                r5 = 1
                r2.<init>(r4, r5)     // Catch:{ Throwable -> 0x002d, all -> 0x003a }
                java.lang.String r3 = "UTF-8"
                byte[] r3 = r9.getBytes(r3)     // Catch:{ Throwable -> 0x004e, all -> 0x0049 }
                r2.write(r3)     // Catch:{ Throwable -> 0x004e, all -> 0x0049 }
                r2.flush()     // Catch:{ Throwable -> 0x004e, all -> 0x0049 }
                r2.close()     // Catch:{ Throwable -> 0x004e, all -> 0x0049 }
                long r4 = r8.f1713d     // Catch:{ Throwable -> 0x004e, all -> 0x0049 }
                int r3 = r3.length     // Catch:{ Throwable -> 0x004e, all -> 0x0049 }
                long r6 = (long) r3     // Catch:{ Throwable -> 0x004e, all -> 0x0049 }
                long r4 = r4 + r6
                r8.f1713d = r4     // Catch:{ Throwable -> 0x004e, all -> 0x0049 }
                r2.close()     // Catch:{ IOException -> 0x0045 }
            L_0x002b:
                r0 = r1
                goto L_0x0007
            L_0x002d:
                r1 = move-exception
                r1 = r3
            L_0x002f:
                r2 = 0
                r8.f1710a = r2     // Catch:{ all -> 0x004b }
                if (r1 == 0) goto L_0x0007
                r1.close()     // Catch:{ IOException -> 0x0038 }
                goto L_0x0007
            L_0x0038:
                r1 = move-exception
                goto L_0x0007
            L_0x003a:
                r0 = move-exception
                r2 = r3
            L_0x003c:
                if (r2 == 0) goto L_0x0041
                r2.close()     // Catch:{ IOException -> 0x0047 }
            L_0x0041:
                throw r0     // Catch:{ all -> 0x0042 }
            L_0x0042:
                r0 = move-exception
                monitor-exit(r8)
                throw r0
            L_0x0045:
                r0 = move-exception
                goto L_0x002b
            L_0x0047:
                r1 = move-exception
                goto L_0x0041
            L_0x0049:
                r0 = move-exception
                goto L_0x003c
            L_0x004b:
                r0 = move-exception
                r2 = r1
                goto L_0x003c
            L_0x004e:
                r1 = move-exception
                r1 = r2
                goto L_0x002f
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2015x.C2017a.mo19642a(java.lang.String):boolean");
        }
    }

    static {
        f1696b = null;
        try {
            f1696b = new SimpleDateFormat("MM-dd HH:mm:ss");
        } catch (Throwable th) {
        }
    }

    /* renamed from: b */
    private static boolean m2128b(String str, String str2, String str3) {
        try {
            C1938a b = C1938a.m1668b();
            if (!(b == null || b.f1158C == null)) {
                return b.f1158C.appendLogToNative(str, str2, str3);
            }
        } catch (Throwable th) {
            if (!C2014w.m2114a(th)) {
                th.printStackTrace();
            }
        }
        return false;
    }

    /* renamed from: a */
    public static synchronized void m2123a(Context context) {
        synchronized (C2015x.class) {
            if (!f1706l && context != null && f1695a) {
                try {
                    f1699e = new StringBuilder(0);
                    f1698d = new StringBuilder(0);
                    f1704j = context;
                    C1938a a = C1938a.m1667a(context);
                    f1702h = a.f1202d;
                    a.getClass();
                    f1703i = "";
                    f1705k = f1704j.getFilesDir().getPath() + "/buglylog_" + f1702h + "_" + f1703i + ".txt";
                    f1707m = Process.myPid();
                } catch (Throwable th) {
                }
                f1706l = true;
            }
        }
    }

    /* renamed from: a */
    public static void m2122a(int i) {
        synchronized (f1708n) {
            f1697c = i;
            if (i < 0) {
                f1697c = 0;
            } else if (i > 10240) {
                f1697c = 10240;
            }
        }
    }

    /* renamed from: a */
    public static void m2125a(String str, String str2, Throwable th) {
        if (th != null) {
            String message = th.getMessage();
            if (message == null) {
                message = "";
            }
            m2124a(str, str2, message + 10 + C2018y.m2167b(th));
        }
    }

    /* renamed from: a */
    public static synchronized void m2124a(String str, String str2, String str3) {
        synchronized (C2015x.class) {
            if (f1706l && f1695a) {
                m2128b(str, str2, str3);
                long myTid = (long) Process.myTid();
                f1698d.setLength(0);
                if (str3.length() > 30720) {
                    str3 = str3.substring(str3.length() - 30720, str3.length() - 1);
                }
                Date date = new Date();
                f1698d.append(f1696b != null ? f1696b.format(date) : date.toString()).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(f1707m).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(myTid).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(str).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(str2).append(": ").append(str3).append("\u0001\r\n");
                final String sb = f1698d.toString();
                synchronized (f1708n) {
                    f1699e.append(sb);
                    if (f1699e.length() > f1697c) {
                        if (!f1700f) {
                            f1700f = true;
                            C2012v.m2106a().mo19636a(new Runnable() {
                                public final void run() {
                                    synchronized (C2015x.f1708n) {
                                        try {
                                            if (C2015x.f1701g == null) {
                                                C2015x.f1701g = new C2017a(C2015x.f1705k);
                                            } else if (C2015x.f1701g.f1711b == null || C2015x.f1701g.f1711b.length() + ((long) C2015x.f1699e.length()) > C2015x.f1701g.f1714e) {
                                                C2015x.f1701g.m2133a();
                                            }
                                            if (C2015x.f1701g.f1710a) {
                                                C2015x.f1701g.mo19642a(C2015x.f1699e.toString());
                                                C2015x.f1699e.setLength(0);
                                            } else {
                                                C2015x.f1699e.setLength(0);
                                                C2015x.f1699e.append(sb);
                                            }
                                            C2015x.f1700f = false;
                                        } catch (Throwable th) {
                                        }
                                    }
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r1v3, types: [java.io.File] */
    /* JADX WARNING: type inference failed for: r1v8, types: [java.io.File] */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] m2126a(boolean r4) {
        /*
            r0 = 0
            boolean r1 = f1695a
            if (r1 != 0) goto L_0x0006
        L_0x0005:
            return r0
        L_0x0006:
            java.lang.Object r2 = f1708n
            monitor-enter(r2)
            if (r4 == 0) goto L_0x002c
            com.tencent.bugly.proguard.x$a r1 = f1701g     // Catch:{ Throwable -> 0x003a }
            if (r1 == 0) goto L_0x002c
            com.tencent.bugly.proguard.x$a r1 = f1701g     // Catch:{ Throwable -> 0x003a }
            boolean r1 = r1.f1710a     // Catch:{ Throwable -> 0x003a }
            if (r1 == 0) goto L_0x002c
            com.tencent.bugly.proguard.x$a r1 = f1701g     // Catch:{ Throwable -> 0x003a }
            java.io.File r1 = r1.f1711b     // Catch:{ Throwable -> 0x003a }
        L_0x001d:
            java.lang.StringBuilder r3 = f1699e     // Catch:{ Throwable -> 0x003a }
            int r3 = r3.length()     // Catch:{ Throwable -> 0x003a }
            if (r3 != 0) goto L_0x002e
            if (r1 != 0) goto L_0x002e
            monitor-exit(r2)     // Catch:{ all -> 0x0029 }
            goto L_0x0005
        L_0x0029:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        L_0x002c:
            r1 = r0
            goto L_0x001d
        L_0x002e:
            java.lang.StringBuilder r3 = f1699e     // Catch:{ Throwable -> 0x003a }
            java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x003a }
            byte[] r0 = com.tencent.bugly.proguard.C2018y.m2162a(r1, r3)     // Catch:{ Throwable -> 0x003a }
            monitor-exit(r2)     // Catch:{ all -> 0x0029 }
            goto L_0x0005
        L_0x003a:
            r1 = move-exception
            monitor-exit(r2)
            goto L_0x0005
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.C2015x.m2126a(boolean):byte[]");
    }
}
