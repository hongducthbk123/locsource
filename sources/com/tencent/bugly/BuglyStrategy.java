package com.tencent.bugly;

import com.tencent.bugly.crashreport.common.info.C1938a;
import java.util.Map;

/* compiled from: BUGLY */
public class BuglyStrategy {

    /* renamed from: a */
    private String f1074a;

    /* renamed from: b */
    private String f1075b;

    /* renamed from: c */
    private String f1076c;

    /* renamed from: d */
    private long f1077d;

    /* renamed from: e */
    private String f1078e;

    /* renamed from: f */
    private String f1079f;

    /* renamed from: g */
    private boolean f1080g = true;

    /* renamed from: h */
    private boolean f1081h = true;

    /* renamed from: i */
    private boolean f1082i = true;

    /* renamed from: j */
    private Class<?> f1083j = null;

    /* renamed from: k */
    private boolean f1084k = true;

    /* renamed from: l */
    private boolean f1085l = true;

    /* renamed from: m */
    private boolean f1086m = true;

    /* renamed from: n */
    private boolean f1087n = false;

    /* renamed from: o */
    private C1910a f1088o;

    /* renamed from: com.tencent.bugly.BuglyStrategy$a */
    /* compiled from: BUGLY */
    public static class C1910a {
        public static final int CRASHTYPE_ANR = 4;
        public static final int CRASHTYPE_BLOCK = 7;
        public static final int CRASHTYPE_COCOS2DX_JS = 5;
        public static final int CRASHTYPE_COCOS2DX_LUA = 6;
        public static final int CRASHTYPE_JAVA_CATCH = 1;
        public static final int CRASHTYPE_JAVA_CRASH = 0;
        public static final int CRASHTYPE_NATIVE = 2;
        public static final int CRASHTYPE_U3D = 3;
        public static final int MAX_USERDATA_KEY_LENGTH = 100;
        public static final int MAX_USERDATA_VALUE_LENGTH = 30000;

        public synchronized Map<String, String> onCrashHandleStart(int i, String str, String str2, String str3) {
            return null;
        }

        public synchronized byte[] onCrashHandleStart2GetExtraDatas(int i, String str, String str2, String str3) {
            return null;
        }
    }

    public synchronized BuglyStrategy setBuglyLogUpload(boolean z) {
        this.f1084k = z;
        return this;
    }

    public synchronized BuglyStrategy setRecordUserInfoOnceADay(boolean z) {
        this.f1087n = z;
        return this;
    }

    public synchronized BuglyStrategy setUploadProcess(boolean z) {
        this.f1086m = z;
        return this;
    }

    public synchronized boolean isUploadProcess() {
        return this.f1086m;
    }

    public synchronized boolean isBuglyLogUpload() {
        return this.f1084k;
    }

    public synchronized boolean recordUserInfoOnceADay() {
        return this.f1087n;
    }

    public boolean isReplaceOldChannel() {
        return this.f1085l;
    }

    public void setReplaceOldChannel(boolean z) {
        this.f1085l = z;
    }

    public synchronized String getAppVersion() {
        return this.f1074a == null ? C1938a.m1668b().f1208j : this.f1074a;
    }

    public synchronized BuglyStrategy setAppVersion(String str) {
        this.f1074a = str;
        return this;
    }

    public synchronized BuglyStrategy setUserInfoActivity(Class<?> cls) {
        this.f1083j = cls;
        return this;
    }

    public synchronized Class<?> getUserInfoActivity() {
        return this.f1083j;
    }

    public synchronized String getAppChannel() {
        return this.f1075b == null ? C1938a.m1668b().f1210l : this.f1075b;
    }

    public synchronized BuglyStrategy setAppChannel(String str) {
        this.f1075b = str;
        return this;
    }

    public synchronized String getAppPackageName() {
        return this.f1076c == null ? C1938a.m1668b().f1201c : this.f1076c;
    }

    public synchronized BuglyStrategy setAppPackageName(String str) {
        this.f1076c = str;
        return this;
    }

    public synchronized long getAppReportDelay() {
        return this.f1077d;
    }

    public synchronized BuglyStrategy setAppReportDelay(long j) {
        this.f1077d = j;
        return this;
    }

    public synchronized String getLibBuglySOFilePath() {
        return this.f1078e;
    }

    public synchronized BuglyStrategy setLibBuglySOFilePath(String str) {
        this.f1078e = str;
        return this;
    }

    public synchronized String getDeviceID() {
        return this.f1079f;
    }

    public synchronized BuglyStrategy setDeviceID(String str) {
        this.f1079f = str;
        return this;
    }

    public synchronized boolean isEnableNativeCrashMonitor() {
        return this.f1080g;
    }

    public synchronized BuglyStrategy setEnableNativeCrashMonitor(boolean z) {
        this.f1080g = z;
        return this;
    }

    public synchronized BuglyStrategy setEnableUserInfo(boolean z) {
        this.f1082i = z;
        return this;
    }

    public synchronized boolean isEnableUserInfo() {
        return this.f1082i;
    }

    public synchronized boolean isEnableANRCrashMonitor() {
        return this.f1081h;
    }

    public synchronized BuglyStrategy setEnableANRCrashMonitor(boolean z) {
        this.f1081h = z;
        return this;
    }

    public synchronized C1910a getCrashHandleCallback() {
        return this.f1088o;
    }

    public synchronized BuglyStrategy setCrashHandleCallback(C1910a aVar) {
        this.f1088o = aVar;
        return this;
    }
}
