package p004cn.jiguang.p005a.p006a.p008b;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/* renamed from: cn.jiguang.a.a.b.d */
final class C0348d implements LocationListener {

    /* renamed from: a */
    final /* synthetic */ C0347c f76a;

    C0348d(C0347c cVar) {
        this.f76a = cVar;
    }

    public final void onLocationChanged(Location location) {
        if (location != null) {
            this.f76a.m56a(location, this.f76a.f73c, false);
        }
        this.f76a.m61d();
    }

    public final void onProviderDisabled(String str) {
        this.f76a.m61d();
    }

    public final void onProviderEnabled(String str) {
    }

    public final void onStatusChanged(String str, int i, Bundle bundle) {
        if (i == 0) {
            this.f76a.m61d();
        }
    }
}
