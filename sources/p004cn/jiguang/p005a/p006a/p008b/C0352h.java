package p004cn.jiguang.p005a.p006a.p008b;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import p004cn.jiguang.p031g.C0506a;

/* renamed from: cn.jiguang.a.a.b.h */
public final class C0352h {

    /* renamed from: a */
    private WifiManager f101a;

    /* renamed from: b */
    private Context f102b = null;

    /* renamed from: c */
    private JSONArray f103c;

    public C0352h(Context context) {
        this.f101a = (WifiManager) context.getApplicationContext().getSystemService("wifi");
        this.f102b = context;
    }

    /* renamed from: a */
    private List<C0353i> m87a(JSONArray jSONArray) {
        ArrayList arrayList;
        C0353i iVar;
        int i;
        if (!m88d()) {
            return null;
        }
        WifiInfo connectionInfo = this.f101a.getConnectionInfo();
        C0353i iVar2 = connectionInfo != null ? new C0353i(this, connectionInfo.getBSSID(), connectionInfo.getRssi(), connectionInfo.getSSID()) : null;
        ArrayList arrayList2 = new ArrayList();
        if (iVar2 != null) {
            JSONObject a = iVar2.mo6228a();
            a.put("tag", "connect");
            jSONArray.put(a);
        }
        List<ScanResult> scanResults = VERSION.SDK_INT < 23 ? this.f101a.getScanResults() : (this.f102b == null || !C0506a.m944a(this.f102b, "android.permission.ACCESS_COARSE_LOCATION")) ? null : this.f101a.getScanResults();
        if (scanResults != null && scanResults.size() > 0) {
            int i2 = -200;
            C0353i iVar3 = null;
            for (ScanResult iVar4 : scanResults) {
                C0353i iVar5 = new C0353i(this, iVar4);
                if (iVar2 != null && !iVar2.equals(iVar5)) {
                    arrayList2.add(iVar5);
                    if (!iVar5.f106c.equals(iVar2.f106c) && iVar5.f105b > i2) {
                        C0353i iVar6 = iVar5;
                        i = iVar5.f105b;
                        iVar = iVar6;
                        iVar3 = iVar;
                        i2 = i;
                    }
                }
                iVar = iVar3;
                i = i2;
                iVar3 = iVar;
                i2 = i;
            }
            Collections.sort(arrayList2);
            int i3 = 10;
            if (iVar3 != null) {
                JSONObject a2 = iVar3.mo6228a();
                a2.put("tag", "strongest");
                jSONArray.put(a2);
                arrayList2.remove(iVar3);
                i3 = 9;
            }
            if (iVar2 != null) {
                arrayList2.remove(iVar2);
                i3--;
            }
            if (arrayList2.size() > i3) {
                List subList = arrayList2.subList(0, i3);
                arrayList = new ArrayList();
                arrayList.addAll(subList);
                return arrayList;
            }
        }
        arrayList = arrayList2;
        return arrayList;
    }

    /* renamed from: d */
    private boolean m88d() {
        try {
            return this.f101a.isWifiEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: a */
    public final void mo6225a() {
        this.f103c = null;
    }

    /* renamed from: b */
    public final void mo6226b() {
        if (C0506a.m944a(this.f102b, "android.permission.ACCESS_WIFI_STATE")) {
            Context context = this.f102b;
            if (((C0506a.m944a(context, "android.permission.ACCESS_COARSE_LOCATION") && C0506a.m944a(context, "android.permission.ACCESS_WIFI_STATE") && C0506a.m944a(context, "android.permission.CHANGE_WIFI_STATE") && C0506a.m944a(context, "android.permission.ACCESS_FINE_LOCATION")) || C0506a.m985n(this.f102b)) && this.f101a.isWifiEnabled()) {
                JSONArray jSONArray = new JSONArray();
                try {
                    List<C0353i> a = m87a(jSONArray);
                    if (a != null) {
                        a.size();
                    }
                    if (a != null) {
                        for (C0353i a2 : a) {
                            jSONArray.put(a2.mo6228a());
                        }
                    }
                } catch (Throwable th) {
                }
                this.f103c = jSONArray;
            }
        }
    }

    /* renamed from: c */
    public final JSONArray mo6227c() {
        return this.f103c;
    }
}
