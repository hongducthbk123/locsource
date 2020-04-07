package com.tencent.bugly.crashreport;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.BuglyStrategy.C1910a;
import com.tencent.bugly.C1911a;
import com.tencent.bugly.C1925b;
import com.tencent.bugly.CrashModule;
import com.tencent.bugly.crashreport.biz.C1934b;
import com.tencent.bugly.crashreport.common.info.C1938a;
import com.tencent.bugly.crashreport.crash.BuglyBroadcastRecevier;
import com.tencent.bugly.crashreport.crash.C1955c;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.crashreport.crash.p049h5.C1963b;
import com.tencent.bugly.crashreport.crash.p049h5.H5JavaScriptInterface;
import com.tencent.bugly.proguard.C2012v;
import com.tencent.bugly.proguard.C2014w;
import com.tencent.bugly.proguard.C2018y;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: BUGLY */
public class CrashReport {

    /* renamed from: a */
    private static Context f1102a;

    /* compiled from: BUGLY */
    public static class CrashHandleCallback extends C1910a {
    }

    /* compiled from: BUGLY */
    public static class UserStrategy extends BuglyStrategy {

        /* renamed from: a */
        private CrashHandleCallback f1103a;

        public UserStrategy(Context context) {
        }

        public synchronized CrashHandleCallback getCrashHandleCallback() {
            return this.f1103a;
        }

        public synchronized void setCrashHandleCallback(CrashHandleCallback crashHandleCallback) {
            this.f1103a = crashHandleCallback;
        }
    }

    public static void enableBugly(boolean z) {
        C1925b.f1095a = z;
    }

    public static void initCrashReport(Context context) {
        f1102a = context;
        C1925b.m1615a((C1911a) CrashModule.getInstance());
        C1925b.m1612a(context);
    }

    public static void initCrashReport(Context context, UserStrategy userStrategy) {
        f1102a = context;
        C1925b.m1615a((C1911a) CrashModule.getInstance());
        C1925b.m1613a(context, userStrategy);
    }

    public static void initCrashReport(Context context, String str, boolean z) {
        if (context != null) {
            f1102a = context;
            C1925b.m1615a((C1911a) CrashModule.getInstance());
            C1925b.m1614a(context, str, z, null);
        }
    }

    public static void initCrashReport(Context context, String str, boolean z, UserStrategy userStrategy) {
        if (context != null) {
            f1102a = context;
            C1925b.m1615a((C1911a) CrashModule.getInstance());
            C1925b.m1614a(context, str, z, userStrategy);
        }
    }

    public static String getBuglyVersion(Context context) {
        if (context == null) {
            C2014w.m2118d("Please call with context.", new Object[0]);
            return "unknown";
        }
        C1938a.m1667a(context);
        return C1938a.m1669c();
    }

    public static void testJavaCrash() {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not test Java crash because bugly is disable.");
        } else if (!CrashModule.hasInitialized()) {
            Log.e(C2014w.f1692a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C1938a b = C1938a.m1668b();
            if (b != null) {
                b.mo19395b(24096);
            }
            throw new RuntimeException("This Crash create for Test! You can go to Bugly see more detail!");
        }
    }

    public static void testNativeCrash() {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not test native crash because bugly is disable.");
        } else if (!CrashModule.hasInitialized()) {
            Log.e(C2014w.f1692a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C2014w.m2113a("start to create a native crash for test!", new Object[0]);
            C1955c.m1808a().mo19477j();
        }
    }

    public static void testANRCrash() {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not test ANR crash because bugly is disable.");
        } else if (!CrashModule.hasInitialized()) {
            Log.e(C2014w.f1692a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C2014w.m2113a("start to create a anr crash for test!", new Object[0]);
            C1955c.m1808a().mo19478k();
        }
    }

    public static void postCatchedException(Throwable th) {
        postCatchedException(th, Thread.currentThread(), false);
    }

    public static void postCatchedException(Throwable th, Thread thread) {
        postCatchedException(th, thread, false);
    }

    public static void postCatchedException(Throwable th, Thread thread, boolean z) {
        Thread thread2;
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not post crash caught because bugly is disable.");
        } else if (!CrashModule.hasInitialized()) {
            Log.e(C2014w.f1692a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else if (th == null) {
            C2014w.m2118d("throwable is null, just return", new Object[0]);
        } else {
            if (thread == null) {
                thread2 = Thread.currentThread();
            } else {
                thread2 = thread;
            }
            C1955c.m1808a().mo19468a(thread2, th, false, (String) null, (byte[]) null, z);
        }
    }

    public static void closeNativeReport() {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not close native report because bugly is disable.");
        } else if (!CrashModule.hasInitialized()) {
            Log.e(C2014w.f1692a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C1955c.m1808a().mo19473f();
        }
    }

    public static void startCrashReport() {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not start crash report because bugly is disable.");
        } else if (!CrashModule.hasInitialized()) {
            Log.w(C2014w.f1692a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C1955c.m1808a().mo19470c();
        }
    }

    public static void closeCrashReport() {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not close crash report because bugly is disable.");
        } else if (!CrashModule.hasInitialized()) {
            Log.w(C2014w.f1692a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            C1955c.m1808a().mo19471d();
        }
    }

    public static void closeBugly() {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not close bugly because bugly is disable.");
        } else if (!CrashModule.hasInitialized()) {
            Log.w(C2014w.f1692a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else if (f1102a != null) {
            BuglyBroadcastRecevier instance = BuglyBroadcastRecevier.getInstance();
            if (instance != null) {
                instance.unregist(f1102a);
            }
            closeCrashReport();
            C1934b.m1638a(f1102a);
            C2012v a = C2012v.m2106a();
            if (a != null) {
                a.mo19638b();
            }
        }
    }

    public static void setUserSceneTag(Context context, int i) {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not set tag caught because bugly is disable.");
        } else if (context == null) {
            Log.e(C2014w.f1692a, "setTag args context should not be null");
        } else {
            if (i <= 0) {
                C2014w.m2118d("setTag args tagId should > 0", new Object[0]);
            }
            C1938a.m1667a(context).mo19390a(i);
            C2014w.m2115b("[param] set user scene tag: %d", Integer.valueOf(i));
        }
    }

    public static int getUserSceneTagId(Context context) {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not get user scene tag because bugly is disable.");
            return -1;
        } else if (context != null) {
            return C1938a.m1667a(context).mo19380F();
        } else {
            Log.e(C2014w.f1692a, "getUserSceneTagId args context should not be null");
            return -1;
        }
    }

    public static String getUserData(Context context, String str) {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not get user data because bugly is disable.");
            return "unknown";
        } else if (context == null) {
            Log.e(C2014w.f1692a, "getUserDataValue args context should not be null");
            return "unknown";
        } else if (C2018y.m2158a(str)) {
            return null;
        } else {
            return C1938a.m1667a(context).mo19407g(str);
        }
    }

    public static void putUserData(Context context, String str, String str2) {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not put user data because bugly is disable.");
        } else if (context == null) {
            Log.w(C2014w.f1692a, "putUserData args context should not be null");
        } else if (str == null) {
            str;
            C2014w.m2118d("putUserData args key should not be null or empty", new Object[0]);
        } else if (str2 == null) {
            str2;
            C2014w.m2118d("putUserData args value should not be null", new Object[0]);
        } else if (!str.matches("[a-zA-Z[0-9]]+")) {
            C2014w.m2118d("putUserData args key should match [a-zA-Z[0-9]]+  {" + str + "}", new Object[0]);
        } else {
            if (str2.length() > 200) {
                C2014w.m2118d("user data value length over limit %d, it will be cutted!", Integer.valueOf(200));
                str2 = str2.substring(0, 200);
            }
            C1938a a = C1938a.m1667a(context);
            if (a.mo19377C().contains(str)) {
                NativeCrashHandler instance = NativeCrashHandler.getInstance();
                if (instance != null) {
                    instance.putKeyValueToNative(str, str2);
                }
                C1938a.m1667a(context).mo19397b(str, str2);
                C2014w.m2117c("replace KV %s %s", str, str2);
            } else if (a.mo19376B() >= 10) {
                C2014w.m2118d("user data size is over limit %d, it will be cutted!", Integer.valueOf(10));
            } else {
                if (str.length() > 50) {
                    C2014w.m2118d("user data key length over limit %d , will drop this new key %s", Integer.valueOf(50), str);
                    str = str.substring(0, 50);
                }
                NativeCrashHandler instance2 = NativeCrashHandler.getInstance();
                if (instance2 != null) {
                    instance2.putKeyValueToNative(str, str2);
                }
                C1938a.m1667a(context).mo19397b(str, str2);
                C2014w.m2115b("[param] set user data: %s - %s", str, str2);
            }
        }
    }

    public static String removeUserData(Context context, String str) {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not remove user data because bugly is disable.");
            return "unknown";
        } else if (context == null) {
            Log.e(C2014w.f1692a, "removeUserData args context should not be null");
            return "unknown";
        } else if (C2018y.m2158a(str)) {
            return null;
        } else {
            C2014w.m2115b("[param] remove user data: %s", str);
            return C1938a.m1667a(context).mo19405f(str);
        }
    }

    public static Set<String> getAllUserDataKeys(Context context) {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not get all keys of user data because bugly is disable.");
            return new HashSet();
        } else if (context != null) {
            return C1938a.m1667a(context).mo19377C();
        } else {
            Log.e(C2014w.f1692a, "getAllUserDataKeys args context should not be null");
            return new HashSet();
        }
    }

    public static int getUserDatasSize(Context context) {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not get size of user data because bugly is disable.");
            return -1;
        } else if (context != null) {
            return C1938a.m1667a(context).mo19376B();
        } else {
            Log.e(C2014w.f1692a, "getUserDatasSize args context should not be null");
            return -1;
        }
    }

    public static String getAppID() {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not get App ID because bugly is disable.");
            return "unknown";
        } else if (CrashModule.hasInitialized()) {
            return C1938a.m1667a(f1102a).mo19404f();
        } else {
            Log.e(C2014w.f1692a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return "unknown";
        }
    }

    public static void setUserId(String str) {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not set user ID because bugly is disable.");
        } else if (!CrashModule.hasInitialized()) {
            Log.e(C2014w.f1692a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            setUserId(f1102a, str);
        }
    }

    public static void setUserId(Context context, String str) {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not set user ID because bugly is disable.");
        } else if (context == null) {
            Log.e(C2014w.f1692a, "Context should not be null when bugly has not been initialed!");
        } else if (str == null) {
            C2014w.m2118d("userId should not be null", new Object[0]);
        } else {
            if (str.length() > 100) {
                String substring = str.substring(0, 100);
                C2014w.m2118d("userId %s length is over limit %d substring to %s", str, Integer.valueOf(100), substring);
                str = substring;
            }
            if (!str.equals(C1938a.m1667a(context).mo19406g())) {
                C1938a.m1667a(context).mo19396b(str);
                C2014w.m2115b("[user] set userId : %s", str);
                NativeCrashHandler instance = NativeCrashHandler.getInstance();
                if (instance != null) {
                    instance.setNativeUserId(str);
                }
                if (CrashModule.hasInitialized()) {
                    C1934b.m1636a();
                }
            }
        }
    }

    public static String getUserId() {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not get user ID because bugly is disable.");
            return "unknown";
        } else if (CrashModule.hasInitialized()) {
            return C1938a.m1667a(f1102a).mo19406g();
        } else {
            Log.e(C2014w.f1692a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return "unknown";
        }
    }

    public static String getAppVer() {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not get app version because bugly is disable.");
            return "unknown";
        } else if (CrashModule.hasInitialized()) {
            return C1938a.m1667a(f1102a).f1208j;
        } else {
            Log.e(C2014w.f1692a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return "unknown";
        }
    }

    public static String getAppChannel() {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not get App channel because bugly is disable.");
            return "unknown";
        } else if (CrashModule.hasInitialized()) {
            return C1938a.m1667a(f1102a).f1210l;
        } else {
            Log.e(C2014w.f1692a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return "unknown";
        }
    }

    public static void setContext(Context context) {
        f1102a = context;
    }

    public static boolean isLastSessionCrash() {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "The info 'isLastSessionCrash' is not accurate because bugly is disable.");
            return false;
        } else if (CrashModule.hasInitialized()) {
            return C1955c.m1808a().mo19469b();
        } else {
            Log.e(C2014w.f1692a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return false;
        }
    }

    public static void setSdkExtraData(Context context, String str, String str2) {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not put SDK extra data because bugly is disable.");
        } else if (context != null && !C2018y.m2158a(str) && !C2018y.m2158a(str2)) {
            C1938a.m1667a(context).mo19392a(str, str2);
        }
    }

    public static Map<String, String> getSdkExtraData() {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not get SDK extra data because bugly is disable.");
            return new HashMap();
        } else if (CrashModule.hasInitialized()) {
            return C1938a.m1667a(f1102a).f1156A;
        } else {
            Log.e(C2014w.f1692a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return null;
        }
    }

    public static Map<String, String> getSdkExtraData(Context context) {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not get SDK extra data because bugly is disable.");
            return new HashMap();
        } else if (context != null) {
            return C1938a.m1667a(context).f1156A;
        } else {
            C2014w.m2118d("Context should not be null.", new Object[0]);
            return null;
        }
    }

    private static void putSdkData(Context context, String str, String str2) {
        if (context != null && !C2018y.m2158a(str) && !C2018y.m2158a(str2)) {
            String replace = str.replace("[a-zA-Z[0-9]]+", "");
            if (replace.length() > 100) {
                Log.w(C2014w.f1692a, String.format("putSdkData key length over limit %d, will be cutted.", new Object[]{Integer.valueOf(50)}));
                replace = replace.substring(0, 50);
            }
            if (str2.length() > 500) {
                Log.w(C2014w.f1692a, String.format("putSdkData value length over limit %d, will be cutted!", new Object[]{Integer.valueOf(200)}));
                str2 = str2.substring(0, 200);
            }
            C1938a.m1667a(context).mo19399c(replace, str2);
            C2014w.m2115b(String.format("[param] putSdkData data: %s - %s", new Object[]{replace, str2}), new Object[0]);
        }
    }

    public static void setIsAppForeground(Context context, boolean z) {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not set 'isAppForeground' because bugly is disable.");
        } else if (context == null) {
            C2014w.m2118d("Context should not be null.", new Object[0]);
        } else {
            if (z) {
                C2014w.m2117c("App is in foreground.", new Object[0]);
            } else {
                C2014w.m2117c("App is in background.", new Object[0]);
            }
            C1938a.m1667a(context).mo19393a(z);
        }
    }

    public static void setIsDevelopmentDevice(Context context, boolean z) {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not set 'isDevelopmentDevice' because bugly is disable.");
        } else if (context == null) {
            C2014w.m2118d("Context should not be null.", new Object[0]);
        } else {
            if (z) {
                C2014w.m2117c("This is a development device.", new Object[0]);
            } else {
                C2014w.m2117c("This is not a development device.", new Object[0]);
            }
            C1938a.m1667a(context).f1223y = z;
        }
    }

    public static void setSessionIntervalMills(long j) {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not set 'SessionIntervalMills' because bugly is disable.");
        } else {
            C1934b.m1637a(j);
        }
    }

    public static void setAppVersion(Context context, String str) {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not set App version because bugly is disable.");
        } else if (context == null) {
            Log.w(C2014w.f1692a, "setAppVersion args context should not be null");
        } else if (str == null) {
            Log.w(C2014w.f1692a, "App version is null, will not set");
        } else {
            C1938a.m1667a(context).f1208j = str;
            NativeCrashHandler instance = NativeCrashHandler.getInstance();
            if (instance != null) {
                instance.setNativeAppVersion(str);
            }
        }
    }

    public static void setAppChannel(Context context, String str) {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not set App channel because Bugly is disable.");
        } else if (context == null) {
            Log.w(C2014w.f1692a, "setAppChannel args context should not be null");
        } else if (str == null) {
            Log.w(C2014w.f1692a, "App channel is null, will not set");
        } else {
            C1938a.m1667a(context).f1210l = str;
            NativeCrashHandler instance = NativeCrashHandler.getInstance();
            if (instance != null) {
                instance.setNativeAppChannel(str);
            }
        }
    }

    public static void setAppPackage(Context context, String str) {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not set App package because bugly is disable.");
        } else if (context == null) {
            Log.w(C2014w.f1692a, "setAppPackage args context should not be null");
        } else if (str == null) {
            Log.w(C2014w.f1692a, "App package is null, will not set");
        } else {
            C1938a.m1667a(context).f1201c = str;
            NativeCrashHandler instance = NativeCrashHandler.getInstance();
            if (instance != null) {
                instance.setNativeAppPackage(str);
            }
        }
    }

    public static void setCrashFilter(String str) {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not set App package because bugly is disable.");
            return;
        }
        Log.w(C2014w.f1692a, "Set crash stack filter: " + str);
        C1955c.f1366l = str;
    }

    public static void setCrashRegularFilter(String str) {
        if (!C1925b.f1095a) {
            Log.w(C2014w.f1692a, "Can not set App package because bugly is disable.");
            return;
        }
        Log.w(C2014w.f1692a, "Set crash stack filter: " + str);
        C1955c.f1367m = str;
    }

    public static boolean setJavascriptMonitor(WebView webView, boolean z) {
        return setJavascriptMonitor(webView, z, false);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public static boolean setJavascriptMonitor(WebView webView, boolean z, boolean z2) {
        if (webView == null) {
            Log.w(C2014w.f1692a, "Webview is null.");
            return false;
        } else if (!CrashModule.hasInitialized()) {
            C2014w.m2119e("CrashReport has not been initialed! please to call method 'initCrashReport' first!", new Object[0]);
            return false;
        } else {
            C2014w.m2113a("Set Javascript exception monitor of webview.", new Object[0]);
            if (!C1925b.f1095a) {
                Log.w(C2014w.f1692a, "Can not set JavaScript monitor because bugly is disable.");
                return false;
            }
            C2014w.m2117c("URL of webview is %s", webView.getUrl());
            if (webView.getUrl() == null) {
                return false;
            }
            if (z2 || VERSION.SDK_INT >= 19) {
                WebSettings settings = webView.getSettings();
                if (!settings.getJavaScriptEnabled()) {
                    C2014w.m2113a("Enable the javascript needed by webview monitor.", new Object[0]);
                    settings.setJavaScriptEnabled(true);
                }
                H5JavaScriptInterface instance = H5JavaScriptInterface.getInstance(webView);
                if (instance != null) {
                    C2014w.m2113a("Add a secure javascript interface to the webview.", new Object[0]);
                    webView.addJavascriptInterface(instance, "exceptionUploader");
                }
                if (z) {
                    C2014w.m2113a("Inject bugly.js(v%s) to the webview.", C1963b.m1844b());
                    String a = C1963b.m1843a();
                    if (a == null) {
                        C2014w.m2119e("Failed to inject Bugly.js.", C1963b.m1844b());
                        return false;
                    }
                    webView.loadUrl("javascript:" + a);
                }
                return true;
            }
            C2014w.m2119e("This interface is only available for Android 4.4 or later.", new Object[0]);
            return false;
        }
    }
}
