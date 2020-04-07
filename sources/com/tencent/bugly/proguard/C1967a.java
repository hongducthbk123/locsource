package com.tencent.bugly.proguard;

import android.content.Context;
import com.adjust.sdk.Constants;
import com.tencent.bugly.C1911a;
import com.tencent.bugly.C1925b;
import com.tencent.bugly.crashreport.biz.UserInfoBean;
import com.tencent.bugly.crashreport.common.info.C1938a;
import com.tencent.bugly.crashreport.common.info.C1939b;
import com.tencent.bugly.crashreport.common.strategy.C1941a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import p004cn.jiguang.net.HttpUtils;

/* renamed from: com.tencent.bugly.proguard.a */
/* compiled from: BUGLY */
public class C1967a {

    /* renamed from: a */
    protected HashMap<String, HashMap<String, byte[]>> f1447a = new HashMap<>();

    /* renamed from: b */
    protected String f1448b;

    /* renamed from: c */
    C1991h f1449c;

    /* renamed from: d */
    private HashMap<String, Object> f1450d;

    /* renamed from: a */
    public static C1973af m1887a(int i) {
        if (i == 1) {
            return new C1972ae();
        }
        if (i == 3) {
            return new C1971ad();
        }
        return null;
    }

    C1967a() {
        new HashMap();
        this.f1450d = new HashMap<>();
        this.f1448b = "GBK";
        this.f1449c = new C1991h();
    }

    /* renamed from: a */
    public static C1983ap m1890a(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        C1983ap apVar = new C1983ap();
        apVar.f1550a = userInfoBean.f1108e;
        apVar.f1554e = userInfoBean.f1113j;
        apVar.f1553d = userInfoBean.f1106c;
        apVar.f1552c = userInfoBean.f1107d;
        apVar.f1556g = C1938a.m1668b().mo19409i();
        apVar.f1557h = userInfoBean.f1118o == 1;
        switch (userInfoBean.f1105b) {
            case 1:
                apVar.f1551b = 1;
                break;
            case 2:
                apVar.f1551b = 4;
                break;
            case 3:
                apVar.f1551b = 2;
                break;
            case 4:
                apVar.f1551b = 3;
                break;
            default:
                if (userInfoBean.f1105b >= 10 && userInfoBean.f1105b < 20) {
                    apVar.f1551b = (byte) userInfoBean.f1105b;
                    break;
                } else {
                    C2014w.m2119e("unknown uinfo type %d ", Integer.valueOf(userInfoBean.f1105b));
                    return null;
                }
                break;
        }
        apVar.f1555f = new HashMap();
        if (userInfoBean.f1119p >= 0) {
            apVar.f1555f.put("C01", userInfoBean.f1119p);
        }
        if (userInfoBean.f1120q >= 0) {
            apVar.f1555f.put("C02", userInfoBean.f1120q);
        }
        if (userInfoBean.f1121r != null && userInfoBean.f1121r.size() > 0) {
            for (Entry entry : userInfoBean.f1121r.entrySet()) {
                apVar.f1555f.put("C03_" + ((String) entry.getKey()), entry.getValue());
            }
        }
        if (userInfoBean.f1122s != null && userInfoBean.f1122s.size() > 0) {
            for (Entry entry2 : userInfoBean.f1122s.entrySet()) {
                apVar.f1555f.put("C04_" + ((String) entry2.getKey()), entry2.getValue());
            }
        }
        apVar.f1555f.put("A36", (!userInfoBean.f1115l));
        apVar.f1555f.put("F02", userInfoBean.f1110g);
        apVar.f1555f.put("F03", userInfoBean.f1111h);
        apVar.f1555f.put("F04", userInfoBean.f1113j);
        apVar.f1555f.put("F05", userInfoBean.f1112i);
        apVar.f1555f.put("F06", userInfoBean.f1116m);
        apVar.f1555f.put("F10", userInfoBean.f1114k);
        C2014w.m2117c("summary type %d vm:%d", Byte.valueOf(apVar.f1551b), Integer.valueOf(apVar.f1555f.size()));
        return apVar;
    }

    /* renamed from: a */
    public void mo19540a(String str) {
        this.f1448b = str;
    }

    /* renamed from: a */
    public static String m1893a(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayList.size(); i++) {
            String str = (String) arrayList.get(i);
            if (str.equals("java.lang.Integer") || str.equals("int")) {
                str = "int32";
            } else if (str.equals("java.lang.Boolean") || str.equals("boolean")) {
                str = "bool";
            } else if (str.equals("java.lang.Byte") || str.equals("byte")) {
                str = "char";
            } else if (str.equals("java.lang.Double") || str.equals("double")) {
                str = "double";
            } else if (str.equals("java.lang.Float") || str.equals("float")) {
                str = "float";
            } else if (str.equals("java.lang.Long") || str.equals(Constants.LONG)) {
                str = "int64";
            } else if (str.equals("java.lang.Short") || str.equals("short")) {
                str = "short";
            } else if (str.equals("java.lang.Character")) {
                throw new IllegalArgumentException("can not support java.lang.Character");
            } else if (str.equals("java.lang.String")) {
                str = "string";
            } else if (str.equals("java.util.List")) {
                str = "list";
            } else if (str.equals("java.util.Map")) {
                str = "map";
            }
            arrayList.set(i, str);
        }
        Collections.reverse(arrayList);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            String str2 = (String) arrayList.get(i2);
            if (str2.equals("list")) {
                arrayList.set(i2 - 1, "<" + ((String) arrayList.get(i2 - 1)));
                arrayList.set(0, ((String) arrayList.get(0)) + ">");
            } else if (str2.equals("map")) {
                arrayList.set(i2 - 1, "<" + ((String) arrayList.get(i2 - 1)) + ",");
                arrayList.set(0, ((String) arrayList.get(0)) + ">");
            } else if (str2.equals("Array")) {
                arrayList.set(i2 - 1, "<" + ((String) arrayList.get(i2 - 1)));
                arrayList.set(0, ((String) arrayList.get(0)) + ">");
            }
        }
        Collections.reverse(arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer.append((String) it.next());
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public <T> void mo19541a(String str, T t) {
        if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        } else if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        } else if (t instanceof Set) {
            throw new IllegalArgumentException("can not support Set");
        } else {
            C1993i iVar = new C1993i();
            iVar.mo19574a(this.f1448b);
            iVar.mo19580a((Object) t, 0);
            byte[] a = C1995k.m2012a(iVar.mo19575a());
            HashMap hashMap = new HashMap(1);
            ArrayList arrayList = new ArrayList(1);
            m1894a(arrayList, (Object) t);
            hashMap.put(m1893a(arrayList), a);
            this.f1450d.remove(str);
            this.f1447a.put(str, hashMap);
        }
    }

    /* renamed from: a */
    public static C1984aq m1891a(List<UserInfoBean> list, int i) {
        if (list == null || list.size() == 0) {
            return null;
        }
        C1938a b = C1938a.m1668b();
        if (b == null) {
            return null;
        }
        b.mo19420t();
        C1984aq aqVar = new C1984aq();
        aqVar.f1561b = b.f1202d;
        aqVar.f1562c = b.mo19408h();
        ArrayList<C1983ap> arrayList = new ArrayList<>();
        for (UserInfoBean a : list) {
            C1983ap a2 = m1890a(a);
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        aqVar.f1563d = arrayList;
        aqVar.f1564e = new HashMap();
        aqVar.f1564e.put("A7", b.f1204f);
        aqVar.f1564e.put("A6", b.mo19419s());
        aqVar.f1564e.put("A5", b.mo19418r());
        aqVar.f1564e.put("A2", b.mo19416p());
        aqVar.f1564e.put("A1", b.mo19416p());
        aqVar.f1564e.put("A24", b.f1206h);
        aqVar.f1564e.put("A17", b.mo19417q());
        aqVar.f1564e.put("A15", b.mo19423w());
        aqVar.f1564e.put("A13", b.mo19424x());
        aqVar.f1564e.put("F08", b.f1220v);
        aqVar.f1564e.put("F09", b.f1221w);
        Map E = b.mo19379E();
        if (E != null && E.size() > 0) {
            for (Entry entry : E.entrySet()) {
                aqVar.f1564e.put("C04_" + ((String) entry.getKey()), entry.getValue());
            }
        }
        switch (i) {
            case 1:
                aqVar.f1560a = 1;
                break;
            case 2:
                aqVar.f1560a = 2;
                break;
            default:
                C2014w.m2119e("unknown up type %d ", Integer.valueOf(i));
                return null;
        }
        return aqVar;
    }

    /* renamed from: a */
    public static <T extends C1994j> T m1892a(byte[] bArr, Class<T> cls) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        try {
            T t = (C1994j) cls.newInstance();
            C1991h hVar = new C1991h(bArr);
            hVar.mo19564a("utf-8");
            t.mo19549a(hVar);
            return t;
        } catch (Throwable th) {
            if (!C2014w.m2116b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    public static C1979al m1888a(Context context, int i, byte[] bArr) {
        C1938a b = C1938a.m1668b();
        StrategyBean c = C1941a.m1752a().mo19435c();
        if (b == null || c == null) {
            C2014w.m2119e("Can not create request pkg for parameters is invalid.", new Object[0]);
            return null;
        }
        try {
            C1979al alVar = new C1979al();
            synchronized (b) {
                alVar.f1498a = 1;
                alVar.f1499b = b.mo19404f();
                alVar.f1500c = b.f1201c;
                alVar.f1501d = b.f1208j;
                alVar.f1502e = b.f1210l;
                b.getClass();
                alVar.f1503f = "2.4.0";
                alVar.f1504g = i;
                alVar.f1505h = bArr == null ? "".getBytes() : bArr;
                alVar.f1506i = b.f1205g;
                alVar.f1507j = b.f1206h;
                alVar.f1508k = new HashMap();
                alVar.f1509l = b.mo19402e();
                alVar.f1510m = c.f1242p;
                alVar.f1512o = b.mo19408h();
                alVar.f1513p = C1939b.m1732e(context);
                alVar.f1514q = System.currentTimeMillis();
                alVar.f1515r = b.mo19411k();
                alVar.f1516s = b.mo19410j();
                alVar.f1517t = b.mo19413m();
                alVar.f1518u = b.mo19412l();
                alVar.f1519v = b.mo19414n();
                alVar.f1520w = alVar.f1513p;
                b.getClass();
                alVar.f1511n = "com.tencent.bugly";
                alVar.f1508k.put("A26", b.mo19425y());
                alVar.f1508k.put("F11", b.f1224z);
                alVar.f1508k.put("F12", b.f1223y);
                alVar.f1508k.put("G1", b.mo19421u());
                alVar.f1508k.put("G2", b.mo19384K());
                alVar.f1508k.put("G3", b.mo19385L());
                alVar.f1508k.put("G4", b.mo19386M());
                alVar.f1508k.put("G5", b.mo19387N());
                alVar.f1508k.put("G6", b.mo19388O());
                alVar.f1508k.put("G7", Long.toString(b.mo19389P()));
                alVar.f1508k.put("D3", b.f1209k);
                if (C1925b.f1096b != null) {
                    for (C1911a aVar : C1925b.f1096b) {
                        if (!(aVar.versionKey == null || aVar.version == null)) {
                            alVar.f1508k.put(aVar.versionKey, aVar.version);
                        }
                    }
                }
            }
            C2007t a = C2007t.m2069a();
            if (!(a == null || a.f1644a || bArr == null)) {
                alVar.f1505h = C2018y.m2164a(alVar.f1505h, 2, 1, c.f1247u);
                if (alVar.f1505h == null) {
                    C2014w.m2119e("reqPkg sbuffer error!", new Object[0]);
                    return null;
                }
            }
            Map D = b.mo19378D();
            if (D != null) {
                for (Entry entry : D.entrySet()) {
                    alVar.f1508k.put(entry.getKey(), entry.getValue());
                }
            }
            return alVar;
        } catch (Throwable th) {
            if (!C2014w.m2116b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    private void m1894a(ArrayList<String> arrayList, Object obj) {
        if (obj.getClass().isArray()) {
            if (!obj.getClass().getComponentType().toString().equals("byte")) {
                throw new IllegalArgumentException("only byte[] is supported");
            } else if (Array.getLength(obj) > 0) {
                arrayList.add("java.util.List");
                m1894a(arrayList, Array.get(obj, 0));
            } else {
                arrayList.add("Array");
                arrayList.add(HttpUtils.URL_AND_PARA_SEPARATOR);
            }
        } else if (obj instanceof Array) {
            throw new IllegalArgumentException("can not support Array, please use List");
        } else if (obj instanceof List) {
            arrayList.add("java.util.List");
            List list = (List) obj;
            if (list.size() > 0) {
                m1894a(arrayList, list.get(0));
            } else {
                arrayList.add(HttpUtils.URL_AND_PARA_SEPARATOR);
            }
        } else if (obj instanceof Map) {
            arrayList.add("java.util.Map");
            Map map = (Map) obj;
            if (map.size() > 0) {
                Object next = map.keySet().iterator().next();
                Object obj2 = map.get(next);
                arrayList.add(next.getClass().getName());
                m1894a(arrayList, obj2);
                return;
            }
            arrayList.add(HttpUtils.URL_AND_PARA_SEPARATOR);
            arrayList.add(HttpUtils.URL_AND_PARA_SEPARATOR);
        } else {
            arrayList.add(obj.getClass().getName());
        }
    }

    /* renamed from: a */
    public byte[] mo19543a() {
        C1993i iVar = new C1993i(0);
        iVar.mo19574a(this.f1448b);
        iVar.mo19583a((Map<K, V>) this.f1447a, 0);
        return C1995k.m2012a(iVar.mo19575a());
    }

    /* renamed from: a */
    public void mo19542a(byte[] bArr) {
        this.f1449c.mo19570a(bArr);
        this.f1449c.mo19564a(this.f1448b);
        HashMap hashMap = new HashMap(1);
        HashMap hashMap2 = new HashMap(1);
        hashMap2.put("", new byte[0]);
        hashMap.put("", hashMap2);
        this.f1447a = this.f1449c.mo19568a((Map<K, V>) hashMap, 0, false);
    }

    /* renamed from: a */
    public static byte[] m1895a(C1979al alVar) {
        try {
            C1987d dVar = new C1987d();
            dVar.mo19556b();
            dVar.mo19540a("utf-8");
            dVar.mo19557b(1);
            dVar.mo19558b("RqdServer");
            dVar.mo19559c("sync");
            dVar.mo19541a("detail", alVar);
            return dVar.mo19543a();
        } catch (Throwable th) {
            if (!C2014w.m2116b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    public static C1980am m1889a(byte[] bArr, boolean z) {
        C1980am amVar;
        if (bArr != null) {
            try {
                C1987d dVar = new C1987d();
                dVar.mo19556b();
                dVar.mo19540a("utf-8");
                dVar.mo19542a(bArr);
                Object b = dVar.mo19555b("detail", new C1980am());
                if (C1980am.class.isInstance(b)) {
                    amVar = (C1980am) C1980am.class.cast(b);
                } else {
                    amVar = null;
                }
                if (z || amVar == null || amVar.f1526c == null || amVar.f1526c.length <= 0) {
                    return amVar;
                }
                C2014w.m2117c("resp buf %d", Integer.valueOf(amVar.f1526c.length));
                amVar.f1526c = C2018y.m2176b(amVar.f1526c, 2, 1, StrategyBean.f1230d);
                if (amVar.f1526c != null) {
                    return amVar;
                }
                C2014w.m2119e("resp sbuffer error!", new Object[0]);
                return null;
            } catch (Throwable th) {
                if (!C2014w.m2116b(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    public static byte[] m1896a(C1994j jVar) {
        try {
            C1993i iVar = new C1993i();
            iVar.mo19574a("utf-8");
            jVar.mo19550a(iVar);
            return iVar.mo19587b();
        } catch (Throwable th) {
            if (!C2014w.m2116b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }
}
