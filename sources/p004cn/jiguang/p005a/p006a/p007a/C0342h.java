package p004cn.jiguang.p005a.p006a.p007a;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p004cn.jiguang.api.C0378a;
import p004cn.jiguang.p015d.p021d.C0460q;
import p004cn.jiguang.p031g.C0506a;
import p004cn.jiguang.p031g.C0533n;

/* renamed from: cn.jiguang.a.a.a.h */
final class C0342h extends Thread {
    /* access modifiers changed from: private */

    /* renamed from: i */
    public static final Object f45i = new Object();

    /* renamed from: a */
    private int f46a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public Context f47b;

    /* renamed from: c */
    private WifiManager f48c;

    /* renamed from: d */
    private String f49d;

    /* renamed from: e */
    private String f50e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public String f51f;

    /* renamed from: g */
    private int f52g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public boolean[] f53h;

    private C0342h(WifiManager wifiManager, String str, String str2, String str3, Context context, int i, int i2) {
        this.f52g = 2;
        this.f48c = wifiManager;
        this.f46a = i;
        this.f47b = context;
        this.f52g = i2;
        this.f49d = str2;
        this.f50e = str3;
        this.f51f = str;
        if (i2 == 2) {
            this.f53h = new boolean[3];
        }
    }

    /* synthetic */ C0342h(WifiManager wifiManager, String str, String str2, String str3, Context context, int i, int i2, byte b) {
        this(wifiManager, str, str2, str3, context, 300, 2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x007b, code lost:
        r2.destroy();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0088, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0091, code lost:
        r0 = null;
        r1 = r2;
        r2 = r3;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0076 A[SYNTHETIC, Splitter:B:39:0x0076] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0088 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:9:0x001e] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.ArrayList<p004cn.jiguang.p005a.p006a.p007a.C0336b> m35a(java.lang.String r8) {
        /*
            r7 = this;
            r1 = 0
            java.lang.String r0 = "cat /proc/net/arp"
            java.lang.Process r2 = p004cn.jiguang.p005a.p006a.p007a.C0337c.m26b(r0)     // Catch:{ IOException -> 0x0063, all -> 0x0071 }
            if (r2 != 0) goto L_0x0010
            if (r2 == 0) goto L_0x000e
            r2.destroy()
        L_0x000e:
            r0 = r1
        L_0x000f:
            return r0
        L_0x0010:
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ IOException -> 0x008a, all -> 0x0085 }
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x008a, all -> 0x0085 }
            java.io.InputStream r4 = r2.getInputStream()     // Catch:{ IOException -> 0x008a, all -> 0x0085 }
            r0.<init>(r4)     // Catch:{ IOException -> 0x008a, all -> 0x0085 }
            r3.<init>(r0)     // Catch:{ IOException -> 0x008a, all -> 0x0085 }
            r3.readLine()     // Catch:{ IOException -> 0x0090, all -> 0x0088 }
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ IOException -> 0x0090, all -> 0x0088 }
            r0.<init>()     // Catch:{ IOException -> 0x0090, all -> 0x0088 }
        L_0x0026:
            java.lang.String r1 = r3.readLine()     // Catch:{ IOException -> 0x0095, all -> 0x0088 }
            if (r1 == 0) goto L_0x005a
            cn.jiguang.a.a.a.b r1 = m37b(r1)     // Catch:{ Exception -> 0x0058 }
            if (r1 == 0) goto L_0x0026
            java.lang.String r4 = r1.mo6191b()     // Catch:{ Exception -> 0x0058 }
            java.lang.String r5 = "0x2"
            boolean r4 = r4.equals(r5)     // Catch:{ Exception -> 0x0058 }
            if (r4 == 0) goto L_0x0026
            java.lang.String r4 = r1.mo6189a()     // Catch:{ Exception -> 0x0058 }
            boolean r4 = r8.equals(r4)     // Catch:{ Exception -> 0x0058 }
            if (r4 != 0) goto L_0x0026
            java.lang.String r4 = r1.mo6193c()     // Catch:{ Exception -> 0x0058 }
            java.lang.String r5 = "00:00:00:00:00:00"
            boolean r4 = r4.equals(r5)     // Catch:{ Exception -> 0x0058 }
            if (r4 != 0) goto L_0x0026
            r0.add(r1)     // Catch:{ Exception -> 0x0058 }
            goto L_0x0026
        L_0x0058:
            r1 = move-exception
            goto L_0x0026
        L_0x005a:
            r3.close()     // Catch:{ IOException -> 0x007f }
        L_0x005d:
            if (r2 == 0) goto L_0x000f
            r2.destroy()
            goto L_0x000f
        L_0x0063:
            r0 = move-exception
            r2 = r1
            r0 = r1
        L_0x0066:
            if (r2 == 0) goto L_0x006b
            r2.close()     // Catch:{ IOException -> 0x0081 }
        L_0x006b:
            if (r1 == 0) goto L_0x000f
            r1.destroy()
            goto L_0x000f
        L_0x0071:
            r0 = move-exception
            r2 = r1
            r3 = r1
        L_0x0074:
            if (r3 == 0) goto L_0x0079
            r3.close()     // Catch:{ IOException -> 0x0083 }
        L_0x0079:
            if (r2 == 0) goto L_0x007e
            r2.destroy()
        L_0x007e:
            throw r0
        L_0x007f:
            r1 = move-exception
            goto L_0x005d
        L_0x0081:
            r2 = move-exception
            goto L_0x006b
        L_0x0083:
            r1 = move-exception
            goto L_0x0079
        L_0x0085:
            r0 = move-exception
            r3 = r1
            goto L_0x0074
        L_0x0088:
            r0 = move-exception
            goto L_0x0074
        L_0x008a:
            r0 = move-exception
            r0 = r1
            r6 = r1
            r1 = r2
            r2 = r6
            goto L_0x0066
        L_0x0090:
            r0 = move-exception
            r0 = r1
            r1 = r2
            r2 = r3
            goto L_0x0066
        L_0x0095:
            r1 = move-exception
            r1 = r2
            r2 = r3
            goto L_0x0066
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p005a.p006a.p007a.C0342h.m35a(java.lang.String):java.util.ArrayList");
    }

    /* renamed from: a */
    private JSONObject m36a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, ArrayList<C0336b> arrayList) {
        JSONObject jSONObject = new JSONObject();
        try {
            C0460q.m707a(this.f47b, jSONObject, "mac_list");
            jSONObject.put("ssid", str);
            jSONObject.put("bssid", str2);
            jSONObject.put("local_ip", str3);
            jSONObject.put("local_mac", str4);
            jSONObject.put("netmask", str5);
            JSONArray jSONArray = new JSONArray();
            if (!TextUtils.isEmpty(str6)) {
                jSONArray.put(str6);
            }
            if (!TextUtils.isEmpty(str7)) {
                jSONArray.put(str7);
            }
            jSONObject.put("dns", jSONArray);
            jSONObject.put("gateway", str8);
            jSONObject.put("dhcp", str9);
            JSONArray jSONArray2 = new JSONArray();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                C0336b bVar = (C0336b) it.next();
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("ip", bVar.mo6189a());
                jSONObject2.put("mac", bVar.mo6193c());
                jSONArray2.put(jSONObject2);
            }
            jSONObject.put("data", jSONArray2);
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    /* renamed from: b */
    private static C0336b m37b(String str) {
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        byte[] bytes = str.getBytes();
        C0336b bVar = new C0336b();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i3 >= bytes.length - 1) {
                break;
            }
            i3++;
            if (bytes[i3] == 32) {
                if (i3 - i2 > 1) {
                    String str2 = new String(bytes, i2, i3 - i2);
                    if (i != 0) {
                        if (i != 1) {
                            if (i != 2) {
                                if (i == 3) {
                                    bVar.mo6195d(str2);
                                    break;
                                }
                            } else {
                                bVar.mo6194c(str2);
                            }
                        } else {
                            bVar.mo6192b(str2);
                        }
                    } else {
                        bVar.mo6190a(str2);
                    }
                    i++;
                }
                i2 = i3 + 1;
            }
        }
        return bVar;
    }

    /* renamed from: b */
    private boolean m39b() {
        for (boolean z : this.f53h) {
            if (!z) {
                return true;
            }
        }
        return false;
    }

    public final void run() {
        DhcpInfo dhcpInfo = this.f48c.getDhcpInfo();
        if (dhcpInfo != null) {
            byte[] a = C0337c.m25a((long) dhcpInfo.ipAddress);
            String a2 = C0337c.m22a(dhcpInfo.ipAddress);
            String str = TextUtils.equals(a2, "0.0.0.0") ? "" : a2;
            String b = C0506a.m949b(this.f47b, "");
            String a3 = C0337c.m22a(dhcpInfo.netmask);
            String str2 = TextUtils.equals(a3, "0.0.0.0") ? "" : a3;
            String a4 = C0337c.m22a(dhcpInfo.dns1);
            if (TextUtils.equals(a4, "0.0.0.0")) {
                a4 = "";
            }
            String a5 = C0337c.m22a(dhcpInfo.dns2);
            if (TextUtils.equals(a5, "0.0.0.0")) {
                a5 = "";
            }
            String a6 = C0337c.m22a(dhcpInfo.gateway);
            if (TextUtils.equals(a6, "0.0.0.0")) {
                a6 = "";
            }
            String a7 = C0337c.m22a(dhcpInfo.serverAddress);
            if (TextUtils.equals(a7, "0.0.0.0")) {
                a7 = "";
            }
            C0533n nVar = new C0533n();
            try {
                if (this.f52g == 2) {
                    for (int i = 0; i < 3; i++) {
                        this.f53h[i] = false;
                        int i2 = (i * 85) + 0;
                        Thread thread = new Thread(new C0340f(new C0338d(a7, this.f46a), a, i2, i2 + 85, new C0344j(this, new C0533n(), i)));
                        thread.start();
                    }
                    synchronized (f45i) {
                        while (m39b()) {
                            try {
                                f45i.wait();
                            } catch (InterruptedException e) {
                            }
                        }
                    }
                } else {
                    new C0338d(a7, this.f46a).mo6197a(a, 0, 255);
                }
            } catch (Exception e2) {
            }
            nVar.mo6706a("ping");
            ArrayList arrayList = null;
            try {
                arrayList = m35a(a7);
            } catch (Exception e3) {
            }
            if (arrayList != null && !arrayList.isEmpty()) {
                C0460q.m715a(this.f47b, m36a(this.f50e, this.f49d, str, b, str2, a4, a5, a6, a7, arrayList), (C0378a) new C0343i(this));
            }
            C0337c.m28c(2);
        }
    }
}
