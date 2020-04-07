package p004cn.jpush.android.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.os.IBinder;
import p004cn.jiguang.api.JCoreInterface;
import p004cn.jiguang.p013b.C0380b;
import p004cn.jiguang.p015d.p016a.C0389d;
import p004cn.jiguang.p015d.p017b.C0395a;
import p004cn.jiguang.p015d.p017b.C0417d;
import p004cn.jiguang.p015d.p028h.C0495f;
import p004cn.jiguang.p029e.C0501d;
import p004cn.jiguang.p031g.C0506a;
import p004cn.jiguang.p031g.C0533n;
import p004cn.jiguang.p031g.p032a.C0507a;

/* renamed from: cn.jpush.android.service.PushService */
public class PushService extends Service {
    private static final String TAG = "PushService";
    private static C0380b mBinder = null;
    private BroadcastReceiver mPowerBroadcastReceiver = null;
    private BroadcastReceiver receiver;

    public IBinder onBind(Intent intent) {
        C0395a.m382a(this);
        return mBinder;
    }

    public void onCreate() {
        C0533n nVar = new C0533n();
        if (!JCoreInterface.init(this, false)) {
            C0501d.m907c(TAG, "onCreate:JCoreInterface init failed");
            return;
        }
        C0395a.m382a(this);
        if (mBinder == null) {
            mBinder = new C0507a();
        }
        C0506a.m981l(getApplicationContext());
        C0417d.m446a().mo6437a((Context) this);
        C0417d.m446a().mo6436a((Service) this);
        try {
            C0495f.m858a().mo6654a(getApplicationContext(), true);
        } catch (OutOfMemoryError e) {
        }
        try {
            IntentFilter intentFilter = new IntentFilter();
            if (this.receiver == null) {
                this.receiver = new PushReceiver();
            }
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            registerReceiver(this.receiver, intentFilter);
        } catch (Throwable th) {
        }
        setDozePowerReceiver();
        nVar.mo6706a("PushService onCreate");
        super.onCreate();
    }

    public void onDestroy() {
        super.onDestroy();
        C0417d a = C0417d.m446a();
        getApplicationContext();
        a.mo6447c();
        try {
            if (this.receiver != null) {
                unregisterReceiver(this.receiver);
            }
            if (this.mPowerBroadcastReceiver != null) {
                unregisterReceiver(this.mPowerBroadcastReceiver);
            }
        } catch (Throwable th) {
        }
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int onStartCommand(android.content.Intent r8, int r9, int r10) {
        /*
            r7 = this;
            r1 = 0
            r6 = 1
            java.lang.String r0 = "PushService"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "onStartCommand - intent:"
            r2.<init>(r3)
            java.lang.StringBuilder r2 = r2.append(r8)
            java.lang.String r3 = ", pkg:"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = p004cn.jiguang.p015d.C0385a.f196c
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = ", connection:"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.util.concurrent.atomic.AtomicLong r3 = p004cn.jiguang.p015d.p017b.C0419f.f300a
            long r4 = r3.get()
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.String r2 = r2.toString()
            p004cn.jiguang.p029e.C0501d.m903a(r0, r2)
            p004cn.jiguang.p015d.p017b.C0395a.m382a(r7)
            r0 = 0
            boolean r0 = p004cn.jiguang.api.JCoreInterface.init(r7, r0)
            if (r0 != 0) goto L_0x0044
            java.lang.String r0 = "PushService"
            java.lang.String r1 = "onStartCommand:JCoreInterface init failed"
            p004cn.jiguang.p029e.C0501d.m907c(r0, r1)
        L_0x0043:
            return r6
        L_0x0044:
            if (r8 == 0) goto L_0x0079
            java.lang.String r0 = r8.getAction()     // Catch:{ Throwable -> 0x0074 }
            android.os.Bundle r1 = r8.getExtras()     // Catch:{ Throwable -> 0x0077 }
        L_0x004e:
            if (r0 == 0) goto L_0x0043
            if (r1 == 0) goto L_0x0043
            cn.jiguang.d.d.j r2 = p004cn.jiguang.p015d.p021d.C0453j.m665a()     // Catch:{ Throwable -> 0x005a }
            r2.mo6567a(r7, r0, r1)     // Catch:{ Throwable -> 0x005a }
            goto L_0x0043
        L_0x005a:
            r0 = move-exception
            java.lang.String r1 = "PushService"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "PushService onStartCommand error:"
            r2.<init>(r3)
            java.lang.String r0 = r0.getMessage()
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            p004cn.jiguang.p029e.C0501d.m907c(r1, r0)
            goto L_0x0043
        L_0x0074:
            r0 = move-exception
            r0 = r1
            goto L_0x004e
        L_0x0077:
            r2 = move-exception
            goto L_0x004e
        L_0x0079:
            r0 = r1
            goto L_0x004e
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jpush.android.service.PushService.onStartCommand(android.content.Intent, int, int):int");
    }

    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public void setDozePowerReceiver() {
        try {
            if (!C0389d.m344o(this)) {
                if (this.mPowerBroadcastReceiver == null) {
                    this.mPowerBroadcastReceiver = new PushReceiver();
                    IntentFilter intentFilter = new IntentFilter();
                    if (VERSION.SDK_INT >= 21) {
                        intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
                    }
                    if (VERSION.SDK_INT >= 23) {
                        intentFilter.addAction("android.os.action.DEVICE_IDLE_MODE_CHANGED");
                    }
                    registerReceiver(this.mPowerBroadcastReceiver, intentFilter);
                }
            } else if (this.mPowerBroadcastReceiver != null) {
                unregisterReceiver(this.mPowerBroadcastReceiver);
                this.mPowerBroadcastReceiver = null;
            }
        } catch (Throwable th) {
        }
    }
}
