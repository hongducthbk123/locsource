package com.tencent.bugly.crashreport.crash.jni;

import android.annotation.SuppressLint;
import android.content.Context;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.C1925b;
import com.tencent.bugly.crashreport.C1926a;
import com.tencent.bugly.crashreport.common.info.C1938a;
import com.tencent.bugly.crashreport.common.info.C1939b;
import com.tencent.bugly.crashreport.common.strategy.C1941a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.C1953b;
import com.tencent.bugly.crashreport.crash.C1955c;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.C2012v;
import com.tencent.bugly.proguard.C2014w;
import com.tencent.bugly.proguard.C2018y;
import java.io.File;

/* compiled from: BUGLY */
public class NativeCrashHandler implements C1926a {

    /* renamed from: a */
    private static NativeCrashHandler f1425a;

    /* renamed from: l */
    private static boolean f1426l = false;

    /* renamed from: m */
    private static boolean f1427m = false;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public final Context f1428b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public final C1938a f1429c;

    /* renamed from: d */
    private final C2012v f1430d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public NativeExceptionHandler f1431e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public String f1432f;

    /* renamed from: g */
    private final boolean f1433g;

    /* renamed from: h */
    private boolean f1434h = false;

    /* renamed from: i */
    private boolean f1435i = false;

    /* renamed from: j */
    private boolean f1436j = false;

    /* renamed from: k */
    private boolean f1437k = false;
    /* access modifiers changed from: private */

    /* renamed from: n */
    public C1953b f1438n;

    /* access modifiers changed from: protected */
    public native boolean appendNativeLog(String str, String str2, String str3);

    /* access modifiers changed from: protected */
    public native boolean appendWholeNativeLog(String str);

    /* access modifiers changed from: protected */
    public native String getNativeKeyValueList();

    /* access modifiers changed from: protected */
    public native String getNativeLog();

    /* access modifiers changed from: protected */
    public native boolean putNativeKeyValue(String str, String str2);

    /* access modifiers changed from: protected */
    public native String regist(String str, boolean z, int i);

    /* access modifiers changed from: protected */
    public native String removeNativeKeyValue(String str);

    /* access modifiers changed from: protected */
    public native void setNativeInfo(int i, String str);

    /* access modifiers changed from: protected */
    public native void testCrash();

    /* access modifiers changed from: protected */
    public native String unregist();

    @SuppressLint({"SdCardPath"})
    private NativeCrashHandler(Context context, C1938a aVar, C1953b bVar, C2012v vVar, boolean z, String str) {
        this.f1428b = C2018y.m2138a(context);
        try {
            if (C2018y.m2158a(str)) {
                str = context.getDir("bugly", 0).getAbsolutePath();
            }
        } catch (Throwable th) {
            str = "/data/data/" + C1938a.m1667a(context).f1201c + "/app_bugly";
        }
        this.f1438n = bVar;
        this.f1432f = str;
        this.f1429c = aVar;
        this.f1430d = vVar;
        this.f1433g = z;
    }

    public static synchronized NativeCrashHandler getInstance(Context context, C1938a aVar, C1953b bVar, C1941a aVar2, C2012v vVar, boolean z, String str) {
        NativeCrashHandler nativeCrashHandler;
        synchronized (NativeCrashHandler.class) {
            if (f1425a == null) {
                f1425a = new NativeCrashHandler(context, aVar, bVar, vVar, z, str);
            }
            nativeCrashHandler = f1425a;
        }
        return nativeCrashHandler;
    }

    public static synchronized NativeCrashHandler getInstance() {
        NativeCrashHandler nativeCrashHandler;
        synchronized (NativeCrashHandler.class) {
            nativeCrashHandler = f1425a;
        }
        return nativeCrashHandler;
    }

    public synchronized String getDumpFilePath() {
        return this.f1432f;
    }

    public synchronized void setDumpFilePath(String str) {
        this.f1432f = str;
    }

    /* renamed from: a */
    private synchronized void m1846a(boolean z) {
        if (this.f1436j) {
            C2014w.m2118d("[Native] Native crash report has already registered.", new Object[0]);
        } else {
            this.f1431e = new C1965a(this.f1428b, this.f1429c, this.f1438n, C1941a.m1752a(), this.f1432f);
            if (this.f1435i) {
                try {
                    String regist = regist(this.f1432f, z, 1);
                    if (regist != null) {
                        C2014w.m2113a("[Native] Native Crash Report enable.", new Object[0]);
                        C2014w.m2117c("[Native] Check extra jni for Bugly NDK v%s", regist);
                        String replace = "2.1.1".replace(".", "");
                        String replace2 = "2.3.0".replace(".", "");
                        String replace3 = regist.replace(".", "");
                        if (replace3.length() == 2) {
                            replace3 = replace3 + AppEventsConstants.EVENT_PARAM_VALUE_NO;
                        } else if (replace3.length() == 1) {
                            replace3 = replace3 + "00";
                        }
                        try {
                            if (Integer.parseInt(replace3) >= Integer.parseInt(replace)) {
                                f1426l = true;
                            }
                            if (Integer.parseInt(replace3) >= Integer.parseInt(replace2)) {
                                f1427m = true;
                            }
                        } catch (Throwable th) {
                        }
                        if (f1427m) {
                            C2014w.m2113a("[Native] Info setting jni can be accessed.", new Object[0]);
                        } else {
                            C2014w.m2118d("[Native] Info setting jni can not be accessed.", new Object[0]);
                        }
                        if (f1426l) {
                            C2014w.m2113a("[Native] Extra jni can be accessed.", new Object[0]);
                        } else {
                            C2014w.m2118d("[Native] Extra jni can not be accessed.", new Object[0]);
                        }
                        this.f1429c.f1212n = regist;
                        this.f1436j = true;
                    }
                } catch (Throwable th2) {
                    C2014w.m2117c("[Native] Failed to load Bugly SO file.", new Object[0]);
                }
            } else if (this.f1434h) {
                String str = "com.tencent.feedback.eup.jni.NativeExceptionUpload";
                String str2 = "registNativeExceptionHandler2";
                try {
                    Class[] clsArr = {String.class, String.class, Integer.TYPE, Integer.TYPE};
                    C1938a.m1668b();
                    String str3 = (String) C2018y.m2141a(str, str2, null, clsArr, new Object[]{this.f1432f, C1939b.m1724a(false), Integer.valueOf(C1938a.m1666J()), Integer.valueOf(1)});
                    if (str3 == null) {
                        Class[] clsArr2 = {String.class, String.class, Integer.TYPE};
                        C1938a.m1668b();
                        str3 = (String) C2018y.m2141a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "registNativeExceptionHandler", null, clsArr2, new Object[]{this.f1432f, C1939b.m1724a(false), Integer.valueOf(C1938a.m1666J())});
                    }
                    if (str3 != null) {
                        this.f1436j = true;
                        C1938a.m1668b().f1212n = str3;
                        C2018y.m2141a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "enableHandler", null, new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(true)});
                        C2018y.m2141a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "setLogMode", null, new Class[]{Integer.TYPE}, new Object[]{Integer.valueOf(C1925b.f1097c ? 3 : 5)});
                    }
                } catch (Throwable th3) {
                }
            }
            this.f1435i = false;
            this.f1434h = false;
        }
    }

    public synchronized void startNativeMonitor() {
        if (this.f1435i || this.f1434h) {
            m1846a(this.f1433g);
        } else {
            if (!C2018y.m2158a(this.f1429c.f1211m)) {
                String str = this.f1429c.f1211m;
            }
            String str2 = "Bugly";
            this.f1429c.getClass();
            this.f1435i = m1848a(C2018y.m2158a(this.f1429c.f1211m) ? str2 : this.f1429c.f1211m, !C2018y.m2158a(this.f1429c.f1211m));
            if (this.f1435i || this.f1434h) {
                m1846a(this.f1433g);
                this.f1430d.mo19636a(new Runnable() {
                    public final void run() {
                        if (!C2018y.m2156a(NativeCrashHandler.this.f1428b, "native_record_lock", 10000)) {
                            C2014w.m2113a("[Native] Failed to lock file for handling native crash record.", new Object[0]);
                            return;
                        }
                        try {
                            NativeCrashHandler.this.setNativeAppVersion(NativeCrashHandler.this.f1429c.f1208j);
                            NativeCrashHandler.this.setNativeAppChannel(NativeCrashHandler.this.f1429c.f1210l);
                            NativeCrashHandler.this.setNativeAppPackage(NativeCrashHandler.this.f1429c.f1201c);
                            NativeCrashHandler.this.setNativeUserId(NativeCrashHandler.this.f1429c.mo19406g());
                            NativeCrashHandler.this.setNativeIsAppForeground(NativeCrashHandler.this.f1429c.mo19394a());
                            NativeCrashHandler.this.setNativeLaunchTime(NativeCrashHandler.this.f1429c.f1181a);
                        } catch (Throwable th) {
                            if (!C2014w.m2114a(th)) {
                                th.printStackTrace();
                            }
                        }
                        CrashDetailBean a = C1966b.m1857a(NativeCrashHandler.this.f1428b, NativeCrashHandler.this.f1432f, NativeCrashHandler.this.f1431e);
                        if (a != null) {
                            C2014w.m2113a("[Native] Get crash from native record.", new Object[0]);
                            if (!NativeCrashHandler.this.f1438n.mo19461a(a)) {
                                NativeCrashHandler.this.f1438n.mo19459a(a, 3000, false);
                            }
                            C1966b.m1862b(NativeCrashHandler.this.f1432f);
                        }
                        NativeCrashHandler.this.mo19491a();
                        C2018y.m2173b(NativeCrashHandler.this.f1428b, "native_record_lock");
                    }
                });
            }
        }
    }

    /* renamed from: a */
    private static boolean m1848a(String str, boolean z) {
        Throwable th;
        boolean z2;
        try {
            C2014w.m2113a("[Native] Trying to load so: %s", str);
            if (z) {
                System.load(str);
            } else {
                System.loadLibrary(str);
            }
            try {
                C2014w.m2113a("[Native] Successfully loaded SO: %s", str);
                return true;
            } catch (Throwable th2) {
                th = th2;
                z2 = true;
            }
        } catch (Throwable th3) {
            th = th3;
            z2 = false;
        }
        C2014w.m2118d(th.getMessage(), new Object[0]);
        C2014w.m2118d("[Native] Failed to load so: %s", str);
        return z2;
    }

    /* renamed from: b */
    private synchronized void m1850b() {
        if (!this.f1436j) {
            C2014w.m2118d("[Native] Native crash report has already unregistered.", new Object[0]);
        } else {
            try {
                if (unregist() != null) {
                    C2014w.m2113a("[Native] Successfully closed native crash report.", new Object[0]);
                    this.f1436j = false;
                }
            } catch (Throwable th) {
                C2014w.m2117c("[Native] Failed to close native crash report.", new Object[0]);
            }
            try {
                C2018y.m2141a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "enableHandler", null, new Class[]{Boolean.TYPE}, new Object[]{Boolean.valueOf(false)});
                this.f1436j = false;
                C2014w.m2113a("[Native] Successfully closed native crash report.", new Object[0]);
            } catch (Throwable th2) {
                C2014w.m2117c("[Native] Failed to close native crash report.", new Object[0]);
                this.f1435i = false;
                this.f1434h = false;
            }
        }
        return;
    }

    public void testNativeCrash() {
        if (!this.f1435i) {
            C2014w.m2118d("[Native] Bugly SO file has not been load.", new Object[0]);
        } else {
            testCrash();
        }
    }

    public NativeExceptionHandler getNativeExceptionHandler() {
        return this.f1431e;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo19491a() {
        long b = C2018y.m2166b() - C1955c.f1360f;
        File file = new File(this.f1432f);
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                String str = "tomb_";
                String str2 = ".txt";
                int length = str.length();
                int i = 0;
                for (File file2 : listFiles) {
                    String name = file2.getName();
                    if (name.startsWith(str)) {
                        try {
                            int indexOf = name.indexOf(str2);
                            if (indexOf > 0 && Long.parseLong(name.substring(length, indexOf)) >= b) {
                            }
                        } catch (Throwable th) {
                            C2014w.m2119e("[Native] Tomb file format error, delete %s", name);
                        }
                        if (file2.delete()) {
                            i++;
                        }
                    }
                }
                C2014w.m2117c("[Native] Clean tombs %d", Integer.valueOf(i));
            }
        }
    }

    /* renamed from: b */
    private synchronized void m1851b(boolean z) {
        if (z) {
            startNativeMonitor();
        } else {
            m1850b();
        }
    }

    public synchronized boolean isUserOpened() {
        return this.f1437k;
    }

    /* renamed from: c */
    private synchronized void m1853c(boolean z) {
        if (this.f1437k != z) {
            C2014w.m2113a("user change native %b", Boolean.valueOf(z));
            this.f1437k = z;
        }
    }

    public synchronized void setUserOpened(boolean z) {
        boolean z2 = true;
        synchronized (this) {
            m1853c(z);
            boolean isUserOpened = isUserOpened();
            C1941a a = C1941a.m1752a();
            if (a == null) {
                z2 = isUserOpened;
            } else if (!isUserOpened || !a.mo19435c().f1233g) {
                z2 = false;
            }
            if (z2 != this.f1436j) {
                C2014w.m2113a("native changed to %b", Boolean.valueOf(z2));
                m1851b(z2);
            }
        }
    }

    public synchronized void onStrategyChanged(StrategyBean strategyBean) {
        boolean z = true;
        synchronized (this) {
            if (strategyBean != null) {
                if (strategyBean.f1233g != this.f1436j) {
                    C2014w.m2118d("server native changed to %b", Boolean.valueOf(strategyBean.f1233g));
                }
            }
            if (!C1941a.m1752a().mo19435c().f1233g || !this.f1437k) {
                z = false;
            }
            if (z != this.f1436j) {
                C2014w.m2113a("native changed to %b", Boolean.valueOf(z));
                m1851b(z);
            }
        }
    }

    public boolean appendLogToNative(String str, String str2, String str3) {
        boolean z = false;
        if (!this.f1435i || !f1426l || str == null || str2 == null || str3 == null) {
            return z;
        }
        try {
            return appendNativeLog(str, str2, str3);
        } catch (UnsatisfiedLinkError e) {
            f1426l = z;
            return z;
        } catch (Throwable th) {
            if (C2014w.m2114a(th)) {
                return z;
            }
            th.printStackTrace();
            return z;
        }
    }

    public boolean putKeyValueToNative(String str, String str2) {
        boolean z = false;
        if (!this.f1435i || !f1426l || str == null || str2 == null) {
            return z;
        }
        try {
            return putNativeKeyValue(str, str2);
        } catch (UnsatisfiedLinkError e) {
            f1426l = z;
            return z;
        } catch (Throwable th) {
            if (C2014w.m2114a(th)) {
                return z;
            }
            th.printStackTrace();
            return z;
        }
    }

    /* renamed from: a */
    private boolean m1847a(int i, String str) {
        if (!this.f1435i || !f1427m) {
            return false;
        }
        try {
            setNativeInfo(i, str);
            return true;
        } catch (UnsatisfiedLinkError e) {
            f1427m = false;
            return false;
        } catch (Throwable th) {
            if (C2014w.m2114a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    public boolean setNativeAppVersion(String str) {
        return m1847a(10, str);
    }

    public boolean setNativeAppChannel(String str) {
        return m1847a(12, str);
    }

    public boolean setNativeAppPackage(String str) {
        return m1847a(13, str);
    }

    public boolean setNativeUserId(String str) {
        return m1847a(11, str);
    }

    public boolean setNativeIsAppForeground(boolean z) {
        return m1847a(14, z ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : Bugly.SDK_IS_DEV);
    }

    public boolean setNativeLaunchTime(long j) {
        try {
            return m1847a(15, String.valueOf(j));
        } catch (NumberFormatException e) {
            if (!C2014w.m2114a(e)) {
                e.printStackTrace();
            }
            return false;
        }
    }
}
