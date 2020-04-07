package p004cn.jpush.android.p039c;

import android.app.Activity;
import android.content.Context;
import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hms.api.HuaweiApiClient.Builder;
import com.huawei.hms.api.HuaweiApiClient.ConnectionCallbacks;
import com.huawei.hms.api.HuaweiApiClient.OnConnectionFailedListener;
import com.huawei.hms.support.api.client.ResultCallback;
import com.huawei.hms.support.api.push.HuaweiPush;
import com.huawei.hms.support.api.push.TokenResult;
import p004cn.jpush.android.p040d.C0582e;

/* renamed from: cn.jpush.android.c.a */
public final class C0565a implements ConnectionCallbacks, OnConnectionFailedListener {

    /* renamed from: a */
    protected HuaweiApiClient f742a;

    /* renamed from: b */
    private C0568c f743b;

    public C0565a(Context context, C0568c cVar) {
        this.f743b = cVar;
        try {
            this.f742a = new Builder(context).addApi(HuaweiPush.PUSH_API).addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    private Activity m1215a() {
        if (this.f743b != null) {
            return this.f743b.f746a;
        }
        return null;
    }

    public final void onConnected() {
        C0582e.m1304b("PluginHuaweiApiClientCallBack", "onConnected");
        m1216b();
    }

    public final void onConnectionSuspended(int i) {
        C0582e.m1304b("PluginHuaweiApiClientCallBack", "onConnected:" + i);
        try {
            this.f742a.connect();
        } catch (Throwable th) {
            C0582e.m1306c("PluginHuaweiApiClientCallBack", "onConnectionSuspended reconnect failed:" + th);
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onConnectionFailed(com.huawei.hms.api.ConnectionResult r5) {
        /*
            r4 = this;
            r0 = 0
            java.lang.String r1 = "PluginHuaweiApiClientCallBack"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "onConnectionFailed:"
            r2.<init>(r3)
            android.app.Activity r3 = r4.m1215a()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = ", errorCode:"
            java.lang.StringBuilder r2 = r2.append(r3)
            if (r5 == 0) goto L_0x0022
            int r0 = r5.getErrorCode()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
        L_0x0022:
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            p004cn.jpush.android.p040d.C0582e.m1304b(r1, r0)
            com.huawei.hms.api.HuaweiApiAvailability r0 = com.huawei.hms.api.HuaweiApiAvailability.getInstance()     // Catch:{ Throwable -> 0x006f }
            int r1 = r5.getErrorCode()     // Catch:{ Throwable -> 0x006f }
            boolean r0 = r0.isUserResolvableError(r1)     // Catch:{ Throwable -> 0x006f }
            java.lang.String r1 = "PluginHuaweiApiClientCallBack"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x006f }
            java.lang.String r3 = "is user Resolvable Error - "
            r2.<init>(r3)     // Catch:{ Throwable -> 0x006f }
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ Throwable -> 0x006f }
            java.lang.String r2 = r2.toString()     // Catch:{ Throwable -> 0x006f }
            p004cn.jpush.android.p040d.C0582e.m1304b(r1, r2)     // Catch:{ Throwable -> 0x006f }
            if (r0 == 0) goto L_0x0085
            android.app.Activity r0 = r4.m1215a()     // Catch:{ Throwable -> 0x006f }
            if (r0 == 0) goto L_0x0067
            com.huawei.hms.api.HuaweiApiAvailability r0 = com.huawei.hms.api.HuaweiApiAvailability.getInstance()     // Catch:{ Throwable -> 0x006f }
            android.app.Activity r1 = r4.m1215a()     // Catch:{ Throwable -> 0x006f }
            int r2 = r5.getErrorCode()     // Catch:{ Throwable -> 0x006f }
            r3 = 10001(0x2711, float:1.4014E-41)
            r0.resolveError(r1, r2, r3)     // Catch:{ Throwable -> 0x006f }
        L_0x0066:
            return
        L_0x0067:
            java.lang.String r0 = "PluginHuaweiApiClientCallBack"
            java.lang.String r1 = "onConnectionFailed activity was null"
            p004cn.jpush.android.p040d.C0582e.m1306c(r0, r1)     // Catch:{ Throwable -> 0x006f }
            goto L_0x0066
        L_0x006f:
            r0 = move-exception
            java.lang.String r1 = "PluginHuaweiApiClientCallBack"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "resolverError failed error:"
            r2.<init>(r3)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            p004cn.jpush.android.p040d.C0582e.m1306c(r1, r0)
            goto L_0x0066
        L_0x0085:
            cn.jpush.android.c.g r0 = p004cn.jpush.android.p039c.C0573g.m1238a()     // Catch:{ Throwable -> 0x0092 }
            android.app.Activity r1 = r4.m1215a()     // Catch:{ Throwable -> 0x0092 }
            r2 = 0
            r0.mo6847a(r1, r2)     // Catch:{ Throwable -> 0x0092 }
            goto L_0x0066
        L_0x0092:
            r0 = move-exception
            goto L_0x0066
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jpush.android.p039c.C0565a.onConnectionFailed(com.huawei.hms.api.ConnectionResult):void");
    }

    /* renamed from: b */
    private String m1216b() {
        try {
            if (m1217c()) {
                HuaweiPush.HuaweiPushApi.getToken(this.f742a).setResultCallback(new ResultCallback<TokenResult>() {
                    public final /* synthetic */ void onResult(Object obj) {
                        try {
                            C0582e.m1304b("PluginHuaweiApiClientCallBack", "invoke get token interface success,result:" + ((TokenResult) obj));
                        } catch (Throwable th) {
                        }
                    }
                });
            }
        } catch (Throwable th) {
            C0582e.m1304b("PluginHuaweiApiClientCallBack", "get RegID failed error:" + th);
        }
        return null;
    }

    /* renamed from: c */
    private boolean m1217c() {
        try {
            if (this.f742a != null && this.f742a.isConnected()) {
                return true;
            }
        } catch (Throwable th) {
            C0582e.m1304b("PluginHuaweiApiClientCallBack", "load connect state faile -  error:" + th);
        }
        return false;
    }
}
