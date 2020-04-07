package p004cn.jpush.android.service;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import p004cn.jpush.android.p039c.C0573g;
import p004cn.jpush.android.p040d.C0582e;

/* renamed from: cn.jpush.android.service.PluginFCMInstanceIdService */
public class PluginFCMInstanceIdService extends FirebaseInstanceIdService {
    /* JADX WARNING: type inference failed for: r4v0, types: [android.content.Context, cn.jpush.android.service.PluginFCMInstanceIdService] */
    public void onTokenRefresh() {
        String str = null;
        try {
            str = FirebaseInstanceId.getInstance().getToken();
        } catch (Throwable th) {
            C0582e.m1305b("PluginFCMInstanceIdService", "get fcm token error:", th);
        }
        C0582e.m1304b("PluginFCMInstanceIdService", "fcm token is " + String.valueOf(str));
        C0573g.m1238a().mo6849b(this, str);
    }
}
