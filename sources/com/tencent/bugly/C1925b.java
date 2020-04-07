package com.tencent.bugly;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.bugly.crashreport.biz.C1934b;
import com.tencent.bugly.crashreport.common.info.C1938a;
import com.tencent.bugly.crashreport.common.strategy.C1941a;
import com.tencent.bugly.proguard.C1997m;
import com.tencent.bugly.proguard.C2000n;
import com.tencent.bugly.proguard.C2001o;
import com.tencent.bugly.proguard.C2007t;
import com.tencent.bugly.proguard.C2014w;
import com.tencent.bugly.proguard.C2015x;
import com.tencent.bugly.proguard.C2018y;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* renamed from: com.tencent.bugly.b */
/* compiled from: BUGLY */
public final class C1925b {

    /* renamed from: a */
    public static boolean f1095a = true;

    /* renamed from: b */
    public static List<C1911a> f1096b = new ArrayList();

    /* renamed from: c */
    public static boolean f1097c;

    /* renamed from: d */
    private static C2001o f1098d;

    /* renamed from: e */
    private static C1941a f1099e;

    /* renamed from: f */
    private static C1997m f1100f;

    /* renamed from: g */
    private static boolean f1101g;

    /* renamed from: a */
    private static boolean m1616a(C1938a aVar) {
        List<String> list = aVar.f1213o;
        aVar.getClass();
        String str = "bugly";
        if (list == null || !list.contains(str)) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public static synchronized void m1612a(Context context) {
        synchronized (C1925b.class) {
            m1613a(context, null);
        }
    }

    /* renamed from: a */
    public static synchronized void m1613a(Context context, BuglyStrategy buglyStrategy) {
        synchronized (C1925b.class) {
            if (f1101g) {
                C2014w.m2118d("[init] initial Multi-times, ignore this.", new Object[0]);
            } else if (context == null) {
                Log.w(C2014w.f1692a, "[init] context of init() is null, check it.");
            } else {
                C1938a a = C1938a.m1667a(context);
                if (m1616a(a)) {
                    f1095a = false;
                } else {
                    String f = a.mo19404f();
                    if (f == null) {
                        Log.e(C2014w.f1692a, "[init] meta data of BUGLY_APPID in AndroidManifest.xml should be set.");
                    } else {
                        m1614a(context, f, a.f1219u, buglyStrategy);
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m1614a(Context context, String str, boolean z, BuglyStrategy buglyStrategy) {
        String str2;
        String str3;
        String str4;
        String str5;
        synchronized (C1925b.class) {
            if (f1101g) {
                C2014w.m2118d("[init] initial Multi-times, ignore this.", new Object[0]);
            } else if (context == null) {
                Log.w(C2014w.f1692a, "[init] context is null, check it.");
            } else if (str == null) {
                Log.e(C2014w.f1692a, "init arg 'crashReportAppID' should not be null!");
            } else {
                f1101g = true;
                if (z) {
                    f1097c = true;
                    C2014w.f1693b = true;
                    C2014w.m2118d("Bugly debug模式开启，请在发布时把isDebug关闭。 -- Running in debug model for 'isDebug' is enabled. Please disable it when you release.", new Object[0]);
                    C2014w.m2119e("--------------------------------------------------------------------------------------------", new Object[0]);
                    C2014w.m2118d("Bugly debug模式将有以下行为特性 -- The following list shows the behaviour of debug model: ", new Object[0]);
                    C2014w.m2118d("[1] 输出详细的Bugly SDK的Log -- More detailed log of Bugly SDK will be output to logcat;", new Object[0]);
                    C2014w.m2118d("[2] 每一条Crash都会被立即上报 -- Every crash caught by Bugly will be uploaded immediately.", new Object[0]);
                    C2014w.m2118d("[3] 自定义日志将会在Logcat中输出 -- Custom log will be output to logcat.", new Object[0]);
                    C2014w.m2119e("--------------------------------------------------------------------------------------------", new Object[0]);
                    C2014w.m2115b("[init] Open debug mode of Bugly.", new Object[0]);
                }
                C2014w.m2113a("[init] Bugly version: v%s", "2.4.0");
                C2014w.m2113a(" crash report start initializing...", new Object[0]);
                C2014w.m2115b("[init] Bugly start initializing...", new Object[0]);
                C2014w.m2113a("[init] Bugly complete version: v%s", "2.4.0(1.2.1)");
                Context a = C2018y.m2138a(context);
                C1938a a2 = C1938a.m1667a(a);
                a2.mo19420t();
                C2015x.m2123a(a);
                f1098d = C2001o.m2036a(a, f1096b);
                C2007t.m2070a(a);
                f1099e = C1941a.m1753a(a, f1096b);
                f1100f = C1997m.m2014a(a);
                if (m1616a(a2)) {
                    f1095a = false;
                } else {
                    a2.mo19391a(str);
                    C2014w.m2113a("[param] Set APP ID:%s", str);
                    if (buglyStrategy != null) {
                        String appVersion = buglyStrategy.getAppVersion();
                        if (!TextUtils.isEmpty(appVersion)) {
                            if (appVersion.length() > 100) {
                                str5 = appVersion.substring(0, 100);
                                C2014w.m2118d("appVersion %s length is over limit %d substring to %s", appVersion, Integer.valueOf(100), str5);
                            } else {
                                str5 = appVersion;
                            }
                            a2.f1208j = str5;
                            C2014w.m2113a("[param] Set App version: %s", buglyStrategy.getAppVersion());
                        }
                        try {
                            if (buglyStrategy.isReplaceOldChannel()) {
                                String appChannel = buglyStrategy.getAppChannel();
                                if (!TextUtils.isEmpty(appChannel)) {
                                    if (appChannel.length() > 100) {
                                        String substring = appChannel.substring(0, 100);
                                        C2014w.m2118d("appChannel %s length is over limit %d substring to %s", appChannel, Integer.valueOf(100), substring);
                                        str4 = substring;
                                    } else {
                                        str4 = appChannel;
                                    }
                                    f1098d.mo19604a(556, "app_channel", str4.getBytes(), (C2000n) null, false);
                                    a2.f1210l = str4;
                                }
                            } else {
                                Map a3 = f1098d.mo19601a(556, (C2000n) null, true);
                                if (a3 != null) {
                                    byte[] bArr = (byte[]) a3.get("app_channel");
                                    if (bArr != null) {
                                        a2.f1210l = new String(bArr);
                                    }
                                }
                            }
                            C2014w.m2113a("[param] Set App channel: %s", a2.f1210l);
                        } catch (Exception e) {
                            if (f1097c) {
                                e.printStackTrace();
                            }
                        }
                        String appPackageName = buglyStrategy.getAppPackageName();
                        if (!TextUtils.isEmpty(appPackageName)) {
                            if (appPackageName.length() > 100) {
                                str3 = appPackageName.substring(0, 100);
                                C2014w.m2118d("appPackageName %s length is over limit %d substring to %s", appPackageName, Integer.valueOf(100), str3);
                            } else {
                                str3 = appPackageName;
                            }
                            a2.f1201c = str3;
                            C2014w.m2113a("[param] Set App package: %s", buglyStrategy.getAppPackageName());
                        }
                        String deviceID = buglyStrategy.getDeviceID();
                        if (deviceID != null) {
                            if (deviceID.length() > 100) {
                                str2 = deviceID.substring(0, 100);
                                C2014w.m2118d("deviceId %s length is over limit %d substring to %s", deviceID, Integer.valueOf(100), str2);
                            } else {
                                str2 = deviceID;
                            }
                            a2.mo19398c(str2);
                            C2014w.m2113a("s[param] Set device ID: %s", str2);
                        }
                        a2.f1203e = buglyStrategy.isUploadProcess();
                        C2015x.f1695a = buglyStrategy.isBuglyLogUpload();
                    }
                    C1934b.m1639a(a, buglyStrategy);
                    for (int i = 0; i < f1096b.size(); i++) {
                        try {
                            if (f1100f.mo19591a(((C1911a) f1096b.get(i)).f1094id)) {
                                ((C1911a) f1096b.get(i)).init(a, z, buglyStrategy);
                            }
                        } catch (Throwable th) {
                            if (!C2014w.m2114a(th)) {
                                th.printStackTrace();
                            }
                        }
                    }
                    f1099e.mo19431a(buglyStrategy != null ? buglyStrategy.getAppReportDelay() : 0);
                    C2014w.m2115b("[init] Bugly initialization finished.", new Object[0]);
                }
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m1615a(C1911a aVar) {
        synchronized (C1925b.class) {
            if (!f1096b.contains(aVar)) {
                f1096b.add(aVar);
            }
        }
    }
}
