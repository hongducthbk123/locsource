package com.tencent.bugly.crashreport.common.info;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Process;
import com.facebook.internal.ServerProtocol;
import com.tencent.bugly.C1925b;
import com.tencent.bugly.crashreport.C1926a;
import com.tencent.bugly.proguard.C2014w;
import com.tencent.bugly.proguard.C2018y;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

/* renamed from: com.tencent.bugly.crashreport.common.info.a */
/* compiled from: BUGLY */
public final class C1938a {

    /* renamed from: Z */
    private static C1938a f1155Z = null;

    /* renamed from: A */
    public HashMap<String, String> f1156A = new HashMap<>();

    /* renamed from: B */
    public List<String> f1157B = new ArrayList();

    /* renamed from: C */
    public C1926a f1158C = null;

    /* renamed from: D */
    private final Context f1159D;

    /* renamed from: E */
    private String f1160E;

    /* renamed from: F */
    private String f1161F;

    /* renamed from: G */
    private String f1162G = "unknown";

    /* renamed from: H */
    private String f1163H = "unknown";

    /* renamed from: I */
    private String f1164I = "";

    /* renamed from: J */
    private String f1165J = null;

    /* renamed from: K */
    private String f1166K = null;

    /* renamed from: L */
    private String f1167L = null;

    /* renamed from: M */
    private String f1168M = null;

    /* renamed from: N */
    private long f1169N = -1;

    /* renamed from: O */
    private long f1170O = -1;

    /* renamed from: P */
    private long f1171P = -1;

    /* renamed from: Q */
    private String f1172Q = null;

    /* renamed from: R */
    private String f1173R = null;

    /* renamed from: S */
    private Map<String, PlugInBean> f1174S = null;

    /* renamed from: T */
    private boolean f1175T = true;

    /* renamed from: U */
    private String f1176U = null;

    /* renamed from: V */
    private String f1177V = null;

    /* renamed from: W */
    private Boolean f1178W = null;

    /* renamed from: X */
    private String f1179X = null;

    /* renamed from: Y */
    private Map<String, PlugInBean> f1180Y = null;

    /* renamed from: a */
    public final long f1181a = System.currentTimeMillis();

    /* renamed from: aa */
    private int f1182aa = -1;

    /* renamed from: ab */
    private int f1183ab = -1;

    /* renamed from: ac */
    private Map<String, String> f1184ac = new HashMap();

    /* renamed from: ad */
    private Map<String, String> f1185ad = new HashMap();

    /* renamed from: ae */
    private Map<String, String> f1186ae = new HashMap();

    /* renamed from: af */
    private boolean f1187af;

    /* renamed from: ag */
    private String f1188ag = null;

    /* renamed from: ah */
    private String f1189ah = null;

    /* renamed from: ai */
    private String f1190ai = null;

    /* renamed from: aj */
    private String f1191aj = null;

    /* renamed from: ak */
    private String f1192ak = null;

    /* renamed from: al */
    private final Object f1193al = new Object();

    /* renamed from: am */
    private final Object f1194am = new Object();

    /* renamed from: an */
    private final Object f1195an = new Object();

    /* renamed from: ao */
    private final Object f1196ao = new Object();

    /* renamed from: ap */
    private final Object f1197ap = new Object();

    /* renamed from: aq */
    private final Object f1198aq = new Object();

    /* renamed from: ar */
    private final Object f1199ar = new Object();

    /* renamed from: b */
    public final byte f1200b;

    /* renamed from: c */
    public String f1201c;

    /* renamed from: d */
    public final String f1202d;

    /* renamed from: e */
    public boolean f1203e = true;

    /* renamed from: f */
    public final String f1204f;

    /* renamed from: g */
    public final String f1205g;

    /* renamed from: h */
    public final String f1206h;

    /* renamed from: i */
    public long f1207i;

    /* renamed from: j */
    public String f1208j = null;

    /* renamed from: k */
    public String f1209k = null;

    /* renamed from: l */
    public String f1210l = null;

    /* renamed from: m */
    public String f1211m = null;

    /* renamed from: n */
    public String f1212n = null;

    /* renamed from: o */
    public List<String> f1213o = null;

    /* renamed from: p */
    public String f1214p = "unknown";

    /* renamed from: q */
    public long f1215q = 0;

    /* renamed from: r */
    public long f1216r = 0;

    /* renamed from: s */
    public long f1217s = 0;

    /* renamed from: t */
    public long f1218t = 0;

    /* renamed from: u */
    public boolean f1219u = false;

    /* renamed from: v */
    public String f1220v = null;

    /* renamed from: w */
    public String f1221w = null;

    /* renamed from: x */
    public String f1222x = null;

    /* renamed from: y */
    public boolean f1223y = false;

    /* renamed from: z */
    public boolean f1224z = false;

    private C1938a(Context context) {
        this.f1159D = C2018y.m2138a(context);
        this.f1200b = 1;
        PackageInfo b = AppInfo.m1661b(context);
        if (b != null) {
            try {
                this.f1208j = b.versionName;
                this.f1220v = this.f1208j;
                this.f1221w = Integer.toString(b.versionCode);
            } catch (Throwable th) {
                if (!C2014w.m2114a(th)) {
                    th.printStackTrace();
                }
            }
        }
        this.f1201c = AppInfo.m1657a(context);
        this.f1202d = AppInfo.m1656a(Process.myPid());
        this.f1204f = C1939b.m1743k();
        this.f1205g = C1939b.m1722a();
        this.f1209k = AppInfo.m1662c(context);
        this.f1206h = "Android " + C1939b.m1725b() + ",level " + C1939b.m1727c();
        this.f1205g + ";" + this.f1206h;
        Map d = AppInfo.m1663d(context);
        if (d != null) {
            try {
                this.f1213o = AppInfo.m1659a(d);
                String str = (String) d.get("BUGLY_APPID");
                if (str != null) {
                    this.f1177V = str;
                }
                String str2 = (String) d.get("BUGLY_APP_VERSION");
                if (str2 != null) {
                    this.f1208j = str2;
                }
                String str3 = (String) d.get("BUGLY_APP_CHANNEL");
                if (str3 != null) {
                    this.f1210l = str3;
                }
                String str4 = (String) d.get("BUGLY_ENABLE_DEBUG");
                if (str4 != null) {
                    this.f1219u = str4.equalsIgnoreCase(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                }
                String str5 = (String) d.get("com.tencent.rdm.uuid");
                if (str5 != null) {
                    this.f1222x = str5;
                }
            } catch (Throwable th2) {
                if (!C2014w.m2114a(th2)) {
                    th2.printStackTrace();
                }
            }
        }
        try {
            if (!context.getDatabasePath("bugly_db_").exists()) {
                this.f1224z = true;
                C2014w.m2117c("App is first time to be installed on the device.", new Object[0]);
            }
        } catch (Throwable th3) {
            if (C1925b.f1097c) {
                th3.printStackTrace();
            }
        }
        C2014w.m2117c("com info create end", new Object[0]);
    }

    /* renamed from: a */
    public final boolean mo19394a() {
        return this.f1187af;
    }

    /* renamed from: a */
    public final void mo19393a(boolean z) {
        this.f1187af = z;
        if (this.f1158C != null) {
            this.f1158C.setNativeIsAppForeground(z);
        }
    }

    /* renamed from: a */
    public static synchronized C1938a m1667a(Context context) {
        C1938a aVar;
        synchronized (C1938a.class) {
            if (f1155Z == null) {
                f1155Z = new C1938a(context);
            }
            aVar = f1155Z;
        }
        return aVar;
    }

    /* renamed from: b */
    public static synchronized C1938a m1668b() {
        C1938a aVar;
        synchronized (C1938a.class) {
            aVar = f1155Z;
        }
        return aVar;
    }

    /* renamed from: c */
    public static String m1669c() {
        return "2.4.0";
    }

    /* renamed from: d */
    public final void mo19400d() {
        synchronized (this.f1193al) {
            this.f1160E = UUID.randomUUID().toString();
        }
    }

    /* renamed from: e */
    public final String mo19402e() {
        if (this.f1160E == null) {
            synchronized (this.f1193al) {
                if (this.f1160E == null) {
                    this.f1160E = UUID.randomUUID().toString();
                }
            }
        }
        return this.f1160E;
    }

    /* renamed from: f */
    public final String mo19404f() {
        if (!C2018y.m2158a((String) null)) {
            return null;
        }
        return this.f1177V;
    }

    /* renamed from: a */
    public final void mo19391a(String str) {
        this.f1177V = str;
    }

    /* renamed from: g */
    public final String mo19406g() {
        String str;
        synchronized (this.f1198aq) {
            str = this.f1162G;
        }
        return str;
    }

    /* renamed from: b */
    public final void mo19396b(String str) {
        synchronized (this.f1198aq) {
            if (str == null) {
                str = "10000";
            }
            this.f1162G = str;
        }
    }

    /* renamed from: h */
    public final String mo19408h() {
        if (this.f1161F != null) {
            return this.f1161F;
        }
        this.f1161F = mo19411k() + "|" + mo19413m() + "|" + mo19414n();
        return this.f1161F;
    }

    /* renamed from: c */
    public final void mo19398c(String str) {
        this.f1161F = str;
    }

    /* renamed from: i */
    public final synchronized String mo19409i() {
        return this.f1163H;
    }

    /* renamed from: d */
    public final synchronized void mo19401d(String str) {
        this.f1163H = str;
    }

    /* renamed from: j */
    public final synchronized String mo19410j() {
        return this.f1164I;
    }

    /* renamed from: e */
    public final synchronized void mo19403e(String str) {
        this.f1164I = str;
    }

    /* renamed from: k */
    public final String mo19411k() {
        if (!this.f1175T) {
            return "";
        }
        if (this.f1165J == null) {
            this.f1165J = C1939b.m1723a(this.f1159D);
        }
        return this.f1165J;
    }

    /* renamed from: l */
    public final String mo19412l() {
        if (!this.f1175T) {
            return "";
        }
        if (this.f1166K == null) {
            this.f1166K = C1939b.m1730d(this.f1159D);
        }
        return this.f1166K;
    }

    /* renamed from: m */
    public final String mo19413m() {
        if (!this.f1175T) {
            return "";
        }
        if (this.f1167L == null) {
            this.f1167L = C1939b.m1726b(this.f1159D);
        }
        return this.f1167L;
    }

    /* renamed from: n */
    public final String mo19414n() {
        if (!this.f1175T) {
            return "";
        }
        if (this.f1168M == null) {
            this.f1168M = C1939b.m1728c(this.f1159D);
        }
        return this.f1168M;
    }

    /* renamed from: o */
    public final long mo19415o() {
        if (this.f1169N <= 0) {
            this.f1169N = C1939b.m1729d();
        }
        return this.f1169N;
    }

    /* renamed from: p */
    public final long mo19416p() {
        if (this.f1170O <= 0) {
            this.f1170O = C1939b.m1733f();
        }
        return this.f1170O;
    }

    /* renamed from: q */
    public final long mo19417q() {
        if (this.f1171P <= 0) {
            this.f1171P = C1939b.m1737h();
        }
        return this.f1171P;
    }

    /* renamed from: r */
    public final String mo19418r() {
        if (this.f1172Q == null) {
            this.f1172Q = C1939b.m1724a(true);
        }
        return this.f1172Q;
    }

    /* renamed from: s */
    public final String mo19419s() {
        if (this.f1173R == null) {
            this.f1173R = C1939b.m1736g(this.f1159D);
        }
        return this.f1173R;
    }

    /* renamed from: a */
    public final void mo19392a(String str, String str2) {
        if (str != null && str2 != null) {
            synchronized (this.f1194am) {
                this.f1156A.put(str, str2);
            }
        }
    }

    /* renamed from: t */
    public final String mo19420t() {
        try {
            Map all = this.f1159D.getSharedPreferences("BuglySdkInfos", 0).getAll();
            if (!all.isEmpty()) {
                synchronized (this.f1194am) {
                    for (Entry entry : all.entrySet()) {
                        try {
                            this.f1156A.put(entry.getKey(), entry.getValue().toString());
                        } catch (Throwable th) {
                            C2014w.m2114a(th);
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            C2014w.m2114a(th2);
        }
        if (this.f1156A.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Entry entry2 : this.f1156A.entrySet()) {
            sb.append("[");
            sb.append((String) entry2.getKey());
            sb.append(",");
            sb.append((String) entry2.getValue());
            sb.append("] ");
        }
        mo19399c("SDK_INFO", sb.toString());
        return sb.toString();
    }

    /* renamed from: u */
    public final String mo19421u() {
        if (this.f1192ak == null) {
            this.f1192ak = AppInfo.m1664e(this.f1159D);
        }
        return this.f1192ak;
    }

    /* renamed from: v */
    public final synchronized Map<String, PlugInBean> mo19422v() {
        return null;
    }

    /* renamed from: w */
    public final String mo19423w() {
        if (this.f1176U == null) {
            this.f1176U = C1939b.m1741j();
        }
        return this.f1176U;
    }

    /* renamed from: x */
    public final Boolean mo19424x() {
        if (this.f1178W == null) {
            this.f1178W = Boolean.valueOf(C1939b.m1738h(this.f1159D));
        }
        return this.f1178W;
    }

    /* renamed from: y */
    public final String mo19425y() {
        if (this.f1179X == null) {
            this.f1179X = C1939b.m1734f(this.f1159D);
            C2014w.m2113a("rom:%s", this.f1179X);
        }
        return this.f1179X;
    }

    /* renamed from: z */
    public final Map<String, String> mo19426z() {
        HashMap hashMap;
        synchronized (this.f1195an) {
            if (this.f1184ac.size() <= 0) {
                hashMap = null;
            } else {
                hashMap = new HashMap(this.f1184ac);
            }
        }
        return hashMap;
    }

    /* renamed from: f */
    public final String mo19405f(String str) {
        String str2;
        if (C2018y.m2158a(str)) {
            C2014w.m2118d("key should not be empty %s", str);
            return null;
        }
        synchronized (this.f1195an) {
            str2 = (String) this.f1184ac.remove(str);
        }
        return str2;
    }

    /* renamed from: A */
    public final void mo19375A() {
        synchronized (this.f1195an) {
            this.f1184ac.clear();
        }
    }

    /* renamed from: g */
    public final String mo19407g(String str) {
        String str2;
        if (C2018y.m2158a(str)) {
            C2014w.m2118d("key should not be empty %s", str);
            return null;
        }
        synchronized (this.f1195an) {
            str2 = (String) this.f1184ac.get(str);
        }
        return str2;
    }

    /* renamed from: b */
    public final void mo19397b(String str, String str2) {
        if (C2018y.m2158a(str) || C2018y.m2158a(str2)) {
            C2014w.m2118d("key&value should not be empty %s %s", str, str2);
            return;
        }
        synchronized (this.f1195an) {
            this.f1184ac.put(str, str2);
        }
    }

    /* renamed from: B */
    public final int mo19376B() {
        int size;
        synchronized (this.f1195an) {
            size = this.f1184ac.size();
        }
        return size;
    }

    /* renamed from: C */
    public final Set<String> mo19377C() {
        Set<String> keySet;
        synchronized (this.f1195an) {
            keySet = this.f1184ac.keySet();
        }
        return keySet;
    }

    /* renamed from: D */
    public final Map<String, String> mo19378D() {
        HashMap hashMap;
        synchronized (this.f1199ar) {
            if (this.f1185ad.size() <= 0) {
                hashMap = null;
            } else {
                hashMap = new HashMap(this.f1185ad);
            }
        }
        return hashMap;
    }

    /* renamed from: c */
    public final void mo19399c(String str, String str2) {
        if (C2018y.m2158a(str) || C2018y.m2158a(str2)) {
            C2014w.m2118d("server key&value should not be empty %s %s", str, str2);
            return;
        }
        synchronized (this.f1196ao) {
            this.f1186ae.put(str, str2);
        }
    }

    /* renamed from: E */
    public final Map<String, String> mo19379E() {
        HashMap hashMap;
        synchronized (this.f1196ao) {
            if (this.f1186ae.size() <= 0) {
                hashMap = null;
            } else {
                hashMap = new HashMap(this.f1186ae);
            }
        }
        return hashMap;
    }

    /* renamed from: a */
    public final void mo19390a(int i) {
        synchronized (this.f1197ap) {
            int i2 = this.f1182aa;
            if (i2 != i) {
                this.f1182aa = i;
                C2014w.m2113a("user scene tag %d changed to tag %d", Integer.valueOf(i2), Integer.valueOf(this.f1182aa));
            }
        }
    }

    /* renamed from: F */
    public final int mo19380F() {
        int i;
        synchronized (this.f1197ap) {
            i = this.f1182aa;
        }
        return i;
    }

    /* renamed from: b */
    public final void mo19395b(int i) {
        int i2 = this.f1183ab;
        if (i2 != 24096) {
            this.f1183ab = 24096;
            C2014w.m2113a("server scene tag %d changed to tag %d", Integer.valueOf(i2), Integer.valueOf(this.f1183ab));
        }
    }

    /* renamed from: G */
    public final int mo19381G() {
        return this.f1183ab;
    }

    /* renamed from: H */
    public final boolean mo19382H() {
        return AppInfo.m1665f(this.f1159D);
    }

    /* renamed from: I */
    public final synchronized Map<String, PlugInBean> mo19383I() {
        return null;
    }

    /* renamed from: J */
    public static int m1666J() {
        return C1939b.m1727c();
    }

    /* renamed from: K */
    public final String mo19384K() {
        if (this.f1188ag == null) {
            this.f1188ag = C1939b.m1745l();
        }
        return this.f1188ag;
    }

    /* renamed from: L */
    public final String mo19385L() {
        if (this.f1189ah == null) {
            this.f1189ah = C1939b.m1740i(this.f1159D);
        }
        return this.f1189ah;
    }

    /* renamed from: M */
    public final String mo19386M() {
        if (this.f1190ai == null) {
            this.f1190ai = C1939b.m1742j(this.f1159D);
        }
        return this.f1190ai;
    }

    /* renamed from: N */
    public final String mo19387N() {
        Context context = this.f1159D;
        return C1939b.m1746m();
    }

    /* renamed from: O */
    public final String mo19388O() {
        if (this.f1191aj == null) {
            this.f1191aj = C1939b.m1744k(this.f1159D);
        }
        return this.f1191aj;
    }

    /* renamed from: P */
    public final long mo19389P() {
        Context context = this.f1159D;
        return C1939b.m1747n();
    }
}
