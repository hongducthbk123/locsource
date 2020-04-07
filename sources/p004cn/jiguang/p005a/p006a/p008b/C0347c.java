package p004cn.jiguang.p005a.p006a.p008b;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Message;
import com.facebook.places.model.PlaceFields;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.json.JSONObject;
import p004cn.jiguang.p015d.p016a.C0386a;
import p004cn.jiguang.p015d.p016a.C0389d;
import p004cn.jiguang.p029e.C0501d;
import p004cn.jiguang.p031g.C0506a;

/* renamed from: cn.jiguang.a.a.b.c */
public final class C0347c {

    /* renamed from: e */
    private static final SimpleDateFormat f70e = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

    /* renamed from: a */
    private LocationManager f71a;

    /* renamed from: b */
    private C0349e f72b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public String f73c = "";

    /* renamed from: d */
    private C0350f f74d;

    /* renamed from: f */
    private final LocationListener f75f = new C0348d(this);

    public C0347c(Context context, C0350f fVar) {
        this.f71a = (LocationManager) context.getSystemService(PlaceFields.LOCATION);
        this.f74d = fVar;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m56a(Location location, String str, boolean z) {
        if (location != null) {
            try {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                if (C0349e.m68a(latitude, longitude)) {
                    this.f72b = new C0349e(latitude, longitude, location.getAltitude(), location.getBearing(), location.getAccuracy(), str, C0386a.m242a(location.getTime()), z);
                    if (this.f72b != null) {
                        JSONObject f = this.f72b.mo6221f();
                        if (f != null) {
                            C0389d.m325b(f.toString());
                            return;
                        }
                        return;
                    }
                    return;
                }
                m58a("latitude(" + latitude + ") or longitude(" + longitude + ") is invalid");
            } catch (Exception e) {
                m58a("update exception" + e.getMessage());
            }
        } else {
            m58a("update location is null");
        }
    }

    /* renamed from: a */
    private void m58a(String str) {
        this.f72b = new C0349e(str);
    }

    /* renamed from: c */
    private boolean m60c() {
        try {
            if (this.f71a != null) {
                return this.f71a.isProviderEnabled("gps") || this.f71a.isProviderEnabled("network") || this.f71a.isProviderEnabled("passive");
            }
            return false;
        } catch (Exception | IllegalArgumentException | SecurityException e) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public void m61d() {
        m62e();
        if (this.f74d != null) {
            if (this.f74d.f89b != null) {
                if (this.f74d.f89b.hasMessages(1004)) {
                    this.f74d.f89b.removeMessages(1004);
                }
                if (this.f74d.f89b.hasMessages(1003)) {
                    this.f74d.f89b.removeMessages(1003);
                }
                if (this.f74d.f89b.hasMessages(1001)) {
                    this.f74d.f89b.removeMessages(1001);
                }
                if (this.f74d.f89b.hasMessages(1005)) {
                    this.f74d.f89b.removeMessages(1005);
                }
            }
            this.f74d.mo6223b();
            return;
        }
        C0501d.m907c("GpsInfoManager", "cellLocationManager is null,please check it");
    }

    /* renamed from: e */
    private void m62e() {
        try {
            if (this.f75f != null && this.f71a != null) {
                this.f71a.removeUpdates(this.f75f);
            }
        } catch (Throwable th) {
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final C0349e mo6208a() {
        return this.f72b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo6209a(Context context) {
        long j = 0;
        if (this.f74d.f88a) {
            m58a("skip gps collect");
            this.f74d.mo6223b();
        } else if (!C0506a.m944a(context, "android.permission.ACCESS_FINE_LOCATION")) {
            m58a("no permission");
            this.f74d.mo6223b();
        } else if (m60c()) {
            try {
                if (this.f71a != null) {
                    Location lastKnownLocation = this.f71a.getLastKnownLocation("gps");
                    Location lastKnownLocation2 = this.f71a.getLastKnownLocation("network");
                    Location lastKnownLocation3 = this.f71a.getLastKnownLocation("passive");
                    long time = lastKnownLocation == null ? 0 : lastKnownLocation.getTime();
                    long time2 = lastKnownLocation2 == null ? 0 : lastKnownLocation2.getTime();
                    long time3 = lastKnownLocation3 == null ? 0 : lastKnownLocation3.getTime();
                    if (time > time2) {
                        if (time > time3) {
                            lastKnownLocation3 = lastKnownLocation;
                        }
                    } else if (time2 > time3) {
                        lastKnownLocation3 = lastKnownLocation2;
                    }
                    m56a(lastKnownLocation3, lastKnownLocation3 != null ? lastKnownLocation3.getProvider() : "", true);
                    long currentTimeMillis = System.currentTimeMillis();
                    if (lastKnownLocation3 != null) {
                        j = lastKnownLocation3.getTime();
                    }
                    if (currentTimeMillis - j < 30000) {
                        m61d();
                    } else if (this.f71a.isProviderEnabled("network")) {
                        this.f73c = "network";
                        this.f74d.f89b.sendEmptyMessage(1003);
                    } else if (this.f71a.isProviderEnabled("gps")) {
                        this.f73c = "gps";
                        this.f74d.f89b.sendEmptyMessage(1003);
                    } else {
                        this.f73c = "network";
                        this.f74d.f89b.sendEmptyMessage(1004);
                    }
                } else {
                    m61d();
                }
            } catch (SecurityException e) {
                m61d();
            } catch (Exception e2) {
                m61d();
            }
        } else {
            m58a("no enabled provider");
            this.f74d.mo6223b();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo6210a(Message message) {
        int i;
        switch (message.what) {
            case 1001:
                try {
                    if (this.f73c == null || !this.f73c.equals("network")) {
                        m61d();
                        return;
                    }
                    this.f73c = "gps";
                    m62e();
                    this.f71a.requestLocationUpdates(this.f73c, 2000, 0.0f, this.f75f);
                    this.f74d.f89b.sendEmptyMessageDelayed(1001, 10000);
                    return;
                } catch (Throwable th) {
                    m61d();
                    return;
                }
            case 1003:
                i = 1001;
                break;
            case 1004:
                i = 1005;
                break;
            case 1005:
                m61d();
                return;
            default:
                return;
        }
        try {
            this.f71a.requestLocationUpdates(this.f73c, 2000, 0.0f, this.f75f);
            this.f74d.f89b.sendEmptyMessageDelayed(i, 20000);
        } catch (SecurityException e) {
            m61d();
        } catch (Throwable th2) {
            m61d();
        }
    }

    /* renamed from: b */
    public final void mo6211b() {
        this.f72b = null;
    }
}
