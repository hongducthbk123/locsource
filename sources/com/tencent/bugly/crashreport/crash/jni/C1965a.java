package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import com.tencent.bugly.crashreport.common.info.AppInfo;
import com.tencent.bugly.crashreport.common.info.C1938a;
import com.tencent.bugly.crashreport.common.info.C1939b;
import com.tencent.bugly.crashreport.common.strategy.C1941a;
import com.tencent.bugly.crashreport.crash.C1953b;
import com.tencent.bugly.crashreport.crash.C1955c;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.C2014w;
import com.tencent.bugly.proguard.C2015x;
import com.tencent.bugly.proguard.C2018y;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.p052io.IOUtils;
import p004cn.jiguang.net.HttpUtils;

/* renamed from: com.tencent.bugly.crashreport.crash.jni.a */
/* compiled from: BUGLY */
public final class C1965a implements NativeExceptionHandler {

    /* renamed from: a */
    private final Context f1440a;

    /* renamed from: b */
    private final C1953b f1441b;

    /* renamed from: c */
    private final C1938a f1442c;

    /* renamed from: d */
    private final C1941a f1443d;

    /* renamed from: e */
    private final String f1444e;

    public C1965a(Context context, C1938a aVar, C1953b bVar, C1941a aVar2, String str) {
        this.f1440a = context;
        this.f1441b = bVar;
        this.f1442c = aVar;
        this.f1443d = aVar2;
        this.f1444e = str;
    }

    public final CrashDetailBean packageCrashDatas(String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, String str8, String str9, byte[] bArr, Map<String, String> map, boolean z) {
        boolean l = C1955c.m1808a().mo19479l();
        if (l) {
            C2014w.m2119e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
        }
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.f1288b = 1;
        crashDetailBean.f1291e = this.f1442c.mo19408h();
        crashDetailBean.f1292f = this.f1442c.f1208j;
        crashDetailBean.f1293g = this.f1442c.mo19423w();
        crashDetailBean.f1299m = this.f1442c.mo19406g();
        crashDetailBean.f1300n = str3;
        crashDetailBean.f1301o = l ? " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]" : "";
        crashDetailBean.f1302p = str4;
        if (str5 == null) {
            str5 = "";
        }
        crashDetailBean.f1303q = str5;
        crashDetailBean.f1304r = j;
        crashDetailBean.f1307u = C2018y.m2168b(crashDetailBean.f1303q.getBytes());
        crashDetailBean.f1312z = str;
        crashDetailBean.f1265A = str2;
        crashDetailBean.f1272H = this.f1442c.mo19425y();
        crashDetailBean.f1294h = this.f1442c.mo19422v();
        crashDetailBean.f1295i = this.f1442c.mo19383I();
        crashDetailBean.f1308v = str8;
        String a = C1966b.m1861a(this.f1444e, str8);
        if (!C2018y.m2158a(a)) {
            crashDetailBean.f1284T = a;
        }
        File file = new File(this.f1444e, "backup_record.txt");
        crashDetailBean.f1285U = file.exists() ? file.getAbsolutePath() : null;
        crashDetailBean.f1273I = str7;
        crashDetailBean.f1274J = str6;
        crashDetailBean.f1275K = str9;
        crashDetailBean.f1269E = this.f1442c.mo19416p();
        crashDetailBean.f1270F = this.f1442c.mo19415o();
        crashDetailBean.f1271G = this.f1442c.mo19417q();
        if (z) {
            crashDetailBean.f1266B = C1939b.m1735g();
            crashDetailBean.f1267C = C1939b.m1731e();
            crashDetailBean.f1268D = C1939b.m1739i();
            crashDetailBean.f1309w = C2018y.m2145a(this.f1440a, C1955c.f1358d, (String) null);
            crashDetailBean.f1310x = C2015x.m2126a(true);
            crashDetailBean.f1276L = this.f1442c.f1181a;
            crashDetailBean.f1277M = this.f1442c.mo19394a();
            crashDetailBean.f1279O = this.f1442c.mo19380F();
            crashDetailBean.f1280P = this.f1442c.mo19381G();
            crashDetailBean.f1281Q = this.f1442c.mo19426z();
            crashDetailBean.f1282R = this.f1442c.mo19379E();
            crashDetailBean.f1311y = C2018y.m2152a(C1955c.f1359e, false);
            String str10 = "java:\n";
            int indexOf = crashDetailBean.f1303q.indexOf(str10);
            if (indexOf > 0) {
                int length = indexOf + str10.length();
                String substring = crashDetailBean.f1303q.substring(length, crashDetailBean.f1303q.length() - 1);
                if (substring.length() > 0 && crashDetailBean.f1311y.containsKey(crashDetailBean.f1265A)) {
                    String str11 = (String) crashDetailBean.f1311y.get(crashDetailBean.f1265A);
                    int indexOf2 = str11.indexOf(substring);
                    if (indexOf2 > 0) {
                        String substring2 = str11.substring(indexOf2);
                        crashDetailBean.f1311y.put(crashDetailBean.f1265A, substring2);
                        crashDetailBean.f1303q = crashDetailBean.f1303q.substring(0, length);
                        crashDetailBean.f1303q += substring2;
                    }
                }
            }
            if (str == null) {
                crashDetailBean.f1312z = this.f1442c.f1202d;
            }
            this.f1441b.mo19463b(crashDetailBean);
        } else {
            crashDetailBean.f1266B = -1;
            crashDetailBean.f1267C = -1;
            crashDetailBean.f1268D = -1;
            crashDetailBean.f1309w = "this crash is occurred at last process! Log is miss, when get an terrible ABRT Native Exception etc.";
            crashDetailBean.f1276L = -1;
            crashDetailBean.f1279O = -1;
            crashDetailBean.f1280P = -1;
            crashDetailBean.f1281Q = map;
            crashDetailBean.f1282R = this.f1442c.mo19379E();
            crashDetailBean.f1311y = null;
            if (str == null) {
                crashDetailBean.f1312z = "unknown(record)";
            }
            if (bArr == null) {
                crashDetailBean.f1310x = "this crash is occurred at last process! Log is miss, when get an terrible ABRT Native Exception etc.".getBytes();
            } else {
                crashDetailBean.f1310x = bArr;
            }
        }
        return crashDetailBean;
    }

    public final void handleNativeException(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7) {
        C2014w.m2113a("Native Crash Happen v1", new Object[0]);
        handleNativeException2(i, i2, j, j2, str, str2, str3, str4, i3, str5, i4, i5, i6, str6, str7, null);
    }

    public final void handleNativeException2(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7, String[] strArr) {
        String str8;
        String str9;
        C2014w.m2113a("Native Crash Happen v2", new Object[0]);
        try {
            if (!this.f1443d.mo19434b()) {
                C2014w.m2119e("waiting for remote sync", new Object[0]);
                int i7 = 0;
                while (!this.f1443d.mo19434b()) {
                    C2018y.m2170b(500);
                    i7 += 500;
                    if (i7 >= 3000) {
                        break;
                    }
                }
            }
            String a = C1966b.m1860a(str3);
            String str10 = "UNKNOWN";
            if (i3 > 0) {
                str8 = "KERNEL";
                str9 = str + "(" + str5 + ")";
            } else {
                if (i4 > 0) {
                    Context context = this.f1440a;
                    str10 = AppInfo.m1656a(i4);
                }
                if (!str10.equals(String.valueOf(i4))) {
                    str10 = str10 + "(" + i4 + ")";
                    str8 = str5;
                    str9 = str;
                } else {
                    str8 = str5;
                    str9 = str;
                }
            }
            if (!this.f1443d.mo19434b()) {
                C2014w.m2118d("no remote but still store!", new Object[0]);
            }
            if (this.f1443d.mo19435c().f1233g || !this.f1443d.mo19434b()) {
                String str11 = null;
                String str12 = null;
                if (strArr != null) {
                    HashMap hashMap = new HashMap();
                    for (String str13 : strArr) {
                        String[] split = str13.split(HttpUtils.EQUAL_SIGN);
                        if (split.length == 2) {
                            hashMap.put(split[0], split[1]);
                        } else {
                            C2014w.m2118d("bad extraMsg %s", str13);
                        }
                    }
                    str12 = (String) hashMap.get("ExceptionThreadName");
                    str11 = (String) hashMap.get("ExceptionProcessName");
                } else {
                    C2014w.m2117c("not found extraMsg", new Object[0]);
                }
                if (str11 == null || str11.length() == 0) {
                    str11 = this.f1442c.f1202d;
                } else {
                    C2014w.m2117c("crash process name change to %s", str11);
                }
                if (str12 != null && str12.length() != 0) {
                    C2014w.m2117c("crash thread name change to %s", str12);
                    Iterator it = Thread.getAllStackTraces().keySet().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Thread thread = (Thread) it.next();
                        if (thread.getName().equals(str12)) {
                            str12 = str12 + "(" + thread.getId() + ")";
                            break;
                        }
                    }
                } else {
                    Thread currentThread = Thread.currentThread();
                    str12 = currentThread.getName() + "(" + currentThread.getId() + ")";
                }
                CrashDetailBean packageCrashDatas = packageCrashDatas(str11, str12, (j2 / 1000) + (1000 * j), str9, str2, a, str8, str10, str4, str7, null, null, true);
                if (packageCrashDatas == null) {
                    C2014w.m2119e("pkg crash datas fail!", new Object[0]);
                    return;
                }
                C1953b.m1792a("NATIVE_CRASH", C2018y.m2143a(), this.f1442c.f1202d, Thread.currentThread(), str9 + IOUtils.LINE_SEPARATOR_UNIX + str2 + IOUtils.LINE_SEPARATOR_UNIX + a, packageCrashDatas);
                if (!this.f1441b.mo19462a(packageCrashDatas, i3)) {
                    this.f1441b.mo19459a(packageCrashDatas, 3000, true);
                }
                C1966b.m1862b(this.f1444e);
                return;
            }
            C2014w.m2119e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
            C1953b.m1792a("NATIVE_CRASH", C2018y.m2143a(), this.f1442c.f1202d, Thread.currentThread(), str9 + IOUtils.LINE_SEPARATOR_UNIX + str2 + IOUtils.LINE_SEPARATOR_UNIX + a, null);
            C2018y.m2172b(str4);
        } catch (Throwable th) {
            if (!C2014w.m2114a(th)) {
                th.printStackTrace();
            }
        }
    }
}
