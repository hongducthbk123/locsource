package p004cn.jpush.android.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import java.util.Iterator;
import p004cn.jiguang.api.JCoreInterface;
import p004cn.jiguang.p015d.C0385a;
import p004cn.jiguang.p015d.p028h.C0492c;
import p004cn.jiguang.p015d.p028h.C0495f;
import p004cn.jiguang.p029e.C0501d;

/* renamed from: cn.jpush.android.service.DaemonService */
public class DaemonService extends Service {
    private static final String TAG = "DaemonService";

    /* renamed from: cn.jpush.android.service.DaemonService$MyBinder */
    public class MyBinder extends Binder {
        public MyBinder() {
        }

        public DaemonService getService() {
            return DaemonService.this;
        }
    }

    private void init() {
        try {
            super.onCreate();
            if (JCoreInterface.init(getApplicationContext(), false)) {
                JCoreInterface.register(getApplicationContext());
            } else {
                stopSelf();
            }
        } catch (Throwable th) {
            C0501d.m907c(TAG, "DaemonService onCreate failed:" + th.getMessage());
        }
    }

    private void report(int i, boolean z, Bundle bundle) {
        if (C0492c.m835a((Context) this)) {
            if (bundle != null) {
                try {
                    Iterator it = bundle.keySet().iterator();
                    while (it.hasNext()) {
                        it.next();
                    }
                } catch (Throwable th) {
                    return;
                }
            }
            String str = "";
            String str2 = "";
            String str3 = "";
            if (bundle != null) {
                str = bundle.getString("from_package");
                str2 = bundle.getString("from_uid");
                str3 = bundle.getString("awake_sequence");
            }
            C0495f.m858a().mo6655b().mo6648a(this, i, z, str, str2, str3);
        }
    }

    public IBinder onBind(Intent intent) {
        report(2, C0385a.f205l, intent != null ? intent.getExtras() : null);
        init();
        return new MyBinder();
    }

    public void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        report(1, C0385a.f205l, intent != null ? intent.getExtras() : null);
        init();
        return super.onStartCommand(intent, i, i2);
    }
}
