package p004cn.jiguang.p015d.p020c;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import org.apache.http.cookie.ClientCookie;

/* renamed from: cn.jiguang.d.c.o */
public class C0438o {

    /* renamed from: d */
    private static C0438o f381d;

    /* renamed from: a */
    private String[] f382a = null;

    /* renamed from: b */
    private C0433j[] f383b = null;

    /* renamed from: c */
    private int f384c = -1;

    static {
        C0438o oVar = new C0438o();
        synchronized (C0438o.class) {
            f381d = oVar;
        }
    }

    public C0438o() {
        if (m606c() || m607d()) {
            return;
        }
        if (this.f382a == null || this.f383b == null) {
            String property = System.getProperty("os.name");
            String property2 = System.getProperty("java.vendor");
            if (property.indexOf("Windows") != -1) {
                if (property.indexOf("95") == -1 && property.indexOf("98") == -1 && property.indexOf("ME") == -1) {
                    try {
                        Process exec = Runtime.getRuntime().exec("ipconfig /all");
                        m599a(exec.getInputStream());
                        exec.destroy();
                    } catch (Exception e) {
                    }
                } else {
                    String str = "winipcfg.out";
                    try {
                        Runtime.getRuntime().exec("winipcfg /all /batch " + str).waitFor();
                        m599a((InputStream) new FileInputStream(new File(str)));
                        new File(str).delete();
                    } catch (Exception e2) {
                    }
                }
            } else if (property.indexOf("NetWare") != -1) {
                m604b("sys:/etc/resolv.cfg");
            } else if (property2.indexOf("Android") != -1) {
                m608e();
            } else {
                m604b("/etc/resolv.conf");
            }
        }
    }

    /* renamed from: a */
    private static int m598a(String str) {
        try {
            int parseInt = Integer.parseInt(str.substring(6));
            if (parseInt >= 0) {
                return parseInt;
            }
        } catch (NumberFormatException e) {
        }
        return -1;
    }

    /* renamed from: a */
    private void m599a(InputStream inputStream) {
        int intValue = Integer.getInteger("org.xbill.DNS.windows.parse.buffer", 8192).intValue();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, intValue);
        bufferedInputStream.mark(intValue);
        m600a((InputStream) bufferedInputStream, (Locale) null);
        if (this.f382a == null) {
            try {
                bufferedInputStream.reset();
                m600a((InputStream) bufferedInputStream, new Locale("", ""));
            } catch (IOException e) {
            }
        }
    }

    /* renamed from: a */
    private void m600a(InputStream inputStream, Locale locale) {
        boolean z;
        boolean z2;
        String str = C0438o.class.getPackage().getName() + ".windows.DNSServer";
        ResourceBundle bundle = locale != null ? ResourceBundle.getBundle(str, locale) : ResourceBundle.getBundle(str);
        String string = bundle.getString("host_name");
        String string2 = bundle.getString("primary_dns_suffix");
        String string3 = bundle.getString("dns_suffix");
        String string4 = bundle.getString("dns_servers");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            boolean z3 = false;
            boolean z4 = false;
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    StringTokenizer stringTokenizer = new StringTokenizer(readLine);
                    if (!stringTokenizer.hasMoreTokens()) {
                        z3 = false;
                        z4 = false;
                    } else {
                        String nextToken = stringTokenizer.nextToken();
                        if (readLine.indexOf(":") != -1) {
                            z = false;
                            z2 = false;
                        } else {
                            boolean z5 = z4;
                            z = z3;
                            z2 = z5;
                        }
                        if (readLine.indexOf(string) != -1) {
                            while (stringTokenizer.hasMoreTokens()) {
                                nextToken = stringTokenizer.nextToken();
                            }
                            try {
                                if (C0433j.m560a(nextToken, (C0433j) null).mo6522b() != 1) {
                                    m605b(nextToken, arrayList2);
                                    boolean z6 = z2;
                                    z3 = z;
                                    z4 = z6;
                                }
                            } catch (C0442s e) {
                                boolean z7 = z2;
                                z3 = z;
                                z4 = z7;
                            }
                        } else if (readLine.indexOf(string2) != -1) {
                            while (stringTokenizer.hasMoreTokens()) {
                                nextToken = stringTokenizer.nextToken();
                            }
                            if (!nextToken.equals(":")) {
                                m605b(nextToken, arrayList2);
                                z3 = z;
                                z4 = true;
                            }
                        } else if (z2 || readLine.indexOf(string3) != -1) {
                            while (stringTokenizer.hasMoreTokens()) {
                                nextToken = stringTokenizer.nextToken();
                            }
                            if (!nextToken.equals(":")) {
                                m605b(nextToken, arrayList2);
                                z3 = z;
                                z4 = true;
                            }
                        } else {
                            if (z || readLine.indexOf(string4) != -1) {
                                while (stringTokenizer.hasMoreTokens()) {
                                    nextToken = stringTokenizer.nextToken();
                                }
                                if (!nextToken.equals(":")) {
                                    m601a(nextToken, (List) arrayList);
                                    z = true;
                                }
                            }
                            boolean z8 = z2;
                            z3 = z;
                            z4 = z8;
                        }
                        boolean z9 = z2;
                        z3 = z;
                        z4 = z9;
                    }
                } else {
                    m602a((List) arrayList, (List) arrayList2);
                    return;
                }
            }
        } catch (IOException e2) {
        }
    }

    /* renamed from: a */
    private static void m601a(String str, List list) {
        if (!list.contains(str)) {
            list.add(str);
        }
    }

    /* renamed from: a */
    private void m602a(List list, List list2) {
        if (this.f382a == null && list.size() > 0) {
            this.f382a = (String[]) list.toArray(new String[0]);
        }
        if (this.f383b == null && list2.size() > 0) {
            this.f383b = (C0433j[]) list2.toArray(new C0433j[0]);
        }
    }

    /* renamed from: b */
    public static synchronized C0438o m603b() {
        C0438o oVar;
        synchronized (C0438o.class) {
            oVar = f381d;
        }
        return oVar;
    }

    /* renamed from: b */
    private void m604b(String str) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(str)));
            ArrayList arrayList = new ArrayList(0);
            ArrayList arrayList2 = new ArrayList(0);
            int i = -1;
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    } else if (readLine.startsWith("nameserver")) {
                        StringTokenizer stringTokenizer = new StringTokenizer(readLine);
                        stringTokenizer.nextToken();
                        m601a(stringTokenizer.nextToken(), (List) arrayList);
                    } else if (readLine.startsWith(ClientCookie.DOMAIN_ATTR)) {
                        StringTokenizer stringTokenizer2 = new StringTokenizer(readLine);
                        stringTokenizer2.nextToken();
                        if (stringTokenizer2.hasMoreTokens() && arrayList2.isEmpty()) {
                            m605b(stringTokenizer2.nextToken(), arrayList2);
                        }
                    } else if (readLine.startsWith("search")) {
                        if (!arrayList2.isEmpty()) {
                            arrayList2.clear();
                        }
                        StringTokenizer stringTokenizer3 = new StringTokenizer(readLine);
                        stringTokenizer3.nextToken();
                        while (stringTokenizer3.hasMoreTokens()) {
                            m605b(stringTokenizer3.nextToken(), arrayList2);
                        }
                    } else if (readLine.startsWith("options")) {
                        StringTokenizer stringTokenizer4 = new StringTokenizer(readLine);
                        stringTokenizer4.nextToken();
                        while (stringTokenizer4.hasMoreTokens()) {
                            String nextToken = stringTokenizer4.nextToken();
                            if (nextToken.startsWith("ndots:")) {
                                i = m598a(nextToken);
                            }
                        }
                    }
                } catch (IOException e) {
                }
            }
            bufferedReader.close();
            m602a((List) arrayList, (List) arrayList2);
            if (this.f384c < 0 && i > 0) {
                this.f384c = i;
            }
        } catch (FileNotFoundException e2) {
        }
    }

    /* renamed from: b */
    private static void m605b(String str, List list) {
        try {
            C0433j a = C0433j.m560a(str, C0433j.f363a);
            if (!list.contains(a)) {
                list.add(a);
            }
        } catch (C0442s e) {
        }
    }

    /* renamed from: c */
    private boolean m606c() {
        ArrayList arrayList = new ArrayList(0);
        ArrayList arrayList2 = new ArrayList(0);
        String property = System.getProperty("dns.server");
        if (property != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(property, ",");
            while (stringTokenizer.hasMoreTokens()) {
                m601a(stringTokenizer.nextToken(), (List) arrayList);
            }
        }
        String property2 = System.getProperty("dns.search");
        if (property2 != null) {
            StringTokenizer stringTokenizer2 = new StringTokenizer(property2, ",");
            while (stringTokenizer2.hasMoreTokens()) {
                m605b(stringTokenizer2.nextToken(), arrayList2);
            }
        }
        m602a((List) arrayList, (List) arrayList2);
        return (this.f382a == null || this.f383b == null) ? false : true;
    }

    /* renamed from: d */
    private boolean m607d() {
        ArrayList arrayList = new ArrayList(0);
        ArrayList arrayList2 = new ArrayList(0);
        try {
            Class[] clsArr = new Class[0];
            Object[] objArr = new Object[0];
            Class cls = Class.forName("sun.net.dns.ResolverConfiguration");
            Object invoke = cls.getDeclaredMethod("open", clsArr).invoke(null, objArr);
            List<String> list = (List) cls.getMethod("nameservers", clsArr).invoke(invoke, objArr);
            List<String> list2 = (List) cls.getMethod("searchlist", clsArr).invoke(invoke, objArr);
            if (list.size() == 0) {
                return false;
            }
            if (list.size() > 0) {
                for (String a : list) {
                    m601a(a, (List) arrayList);
                }
            }
            if (list2.size() > 0) {
                for (String b : list2) {
                    m605b(b, arrayList2);
                }
            }
            m602a((List) arrayList, (List) arrayList2);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: e */
    private void m608e() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        try {
            Method method = Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class});
            String[] strArr = {"net.dns1", "net.dns2", "net.dns3", "net.dns4"};
            for (int i = 0; i < 4; i++) {
                String str = (String) method.invoke(null, new Object[]{strArr[i]});
                if (str != null && ((str.matches("^\\d+(\\.\\d+){3}$") || str.matches("^[0-9a-f]+(:[0-9a-f]*)+:[0-9a-f]+$")) && !arrayList.contains(str))) {
                    arrayList.add(str);
                }
            }
        } catch (Exception e) {
        }
        m602a((List) arrayList, (List) arrayList2);
    }

    /* renamed from: a */
    public final String[] mo6547a() {
        return this.f382a;
    }
}
