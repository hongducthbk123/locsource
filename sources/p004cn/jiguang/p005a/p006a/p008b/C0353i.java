package p004cn.jiguang.p005a.p006a.p008b;

import android.net.wifi.ScanResult;
import org.json.JSONObject;
import p004cn.jiguang.p015d.p016a.C0386a;
import p004cn.jiguang.p031g.C0530k;

/* renamed from: cn.jiguang.a.a.b.i */
public final class C0353i implements Comparable<C0353i> {

    /* renamed from: a */
    public final String f104a;

    /* renamed from: b */
    public final int f105b;

    /* renamed from: c */
    public final String f106c;

    /* renamed from: d */
    final /* synthetic */ C0352h f107d;

    public C0353i(C0352h hVar, ScanResult scanResult) {
        this.f107d = hVar;
        this.f104a = scanResult.BSSID;
        this.f105b = scanResult.level;
        this.f106c = C0530k.m1102c(scanResult.SSID);
    }

    public C0353i(C0352h hVar, String str, int i, String str2) {
        this.f107d = hVar;
        this.f104a = str;
        this.f105b = i;
        this.f106c = C0530k.m1102c(str2);
    }

    /* renamed from: a */
    public final JSONObject mo6228a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mac_address", this.f104a);
            jSONObject.put("signal_strength", this.f105b);
            jSONObject.put("ssid", this.f106c);
            jSONObject.put("age", 0);
            jSONObject.put("itime", C0386a.m293t());
        } catch (Exception e) {
        }
        return jSONObject;
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return ((C0353i) obj).f105b - this.f105b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C0353i) {
            C0353i iVar = (C0353i) obj;
            if (this.f106c != null && this.f106c.equals(iVar.f106c) && this.f104a != null && this.f104a.equals(iVar.f104a)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f106c.hashCode() ^ this.f104a.hashCode();
    }

    public final String toString() {
        return "WifiInfo{bssid='" + this.f104a + '\'' + ", dBm=" + this.f105b + ", ssid='" + this.f106c + '\'' + '}';
    }
}
