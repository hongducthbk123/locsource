package p004cn.jpush.android.service;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.facebook.share.internal.ShareConstants;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import p004cn.jiguang.api.JCoreInterface;
import p004cn.jpush.android.C0541a;
import p004cn.jpush.android.api.JPushInterface;
import p004cn.jpush.android.data.C0591d;
import p004cn.jpush.android.data.C0592e;
import p004cn.jpush.android.data.JPushLocalNotification;
import p004cn.jpush.android.p040d.C0582e;

/* renamed from: cn.jpush.android.service.a */
public final class C0602a {

    /* renamed from: a */
    private static volatile C0602a f879a = null;

    /* renamed from: b */
    private static ExecutorService f880b = Executors.newSingleThreadExecutor();

    /* renamed from: f */
    private static final Object f881f = new Object();

    /* renamed from: c */
    private Handler f882c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public Context f883d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public String f884e;

    private C0602a(Context context) {
        this.f882c = null;
        this.f883d = null;
        this.f884e = "";
        this.f882c = new Handler(Looper.getMainLooper());
        this.f883d = context;
        this.f884e = this.f883d.getPackageName();
    }

    /* renamed from: a */
    public static C0602a m1376a(Context context) {
        if (f879a == null) {
            synchronized (f881f) {
                if (f879a == null) {
                    f879a = new C0602a(context);
                }
            }
        }
        return f879a;
    }

    /* renamed from: b */
    public final synchronized void mo6990b(Context context) {
        C0582e.m1302a("JPushLocalNotificationCenter", "clear all local notification ");
        int[] a = C0591d.m1329a(context).mo6887a();
        if (a != null && a.length > 0) {
            for (int clearNotificationById : a) {
                JPushInterface.clearNotificationById(this.f883d, clearNotificationById);
            }
        }
    }

    /* renamed from: a */
    public final synchronized boolean mo6988a(Context context, long j) {
        boolean z = false;
        synchronized (this) {
            C0582e.m1302a("JPushLocalNotificationCenter", "remove LocalNotification ");
            long j2 = (long) ((int) j);
            try {
                C0591d a = C0591d.m1329a(context);
                if (a.mo6886a(j2, 0) != null) {
                    a.mo6882a(j2);
                    JPushInterface.clearNotificationById(this.f883d, (int) j2);
                    z = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return z;
    }

    /* renamed from: a */
    public final synchronized boolean mo6989a(Context context, JPushLocalNotification jPushLocalNotification, boolean z) {
        if (z) {
            m1381a(context, jPushLocalNotification);
        } else {
            Bundle bundle = new Bundle();
            C0606c.m1392a(context, bundle, "intent.MULTI_PROCESS");
            bundle.putInt("multi_type", 6);
            bundle.putSerializable("local_notification", jPushLocalNotification);
            JCoreInterface.sendAction(context, C0541a.f649a, bundle);
        }
        return true;
    }

    /* renamed from: a */
    private synchronized boolean m1381a(Context context, JPushLocalNotification jPushLocalNotification) {
        boolean z;
        C0582e.m1302a("JPushLocalNotificationCenter", "add LocalNotification");
        long currentTimeMillis = System.currentTimeMillis();
        long broadcastTime = jPushLocalNotification.getBroadcastTime() - currentTimeMillis;
        ServiceInterface.m1373d(context);
        long notificationId = jPushLocalNotification.getNotificationId();
        try {
            C0591d a = C0591d.m1329a(context);
            if (a.mo6886a(notificationId, 0) != null) {
                a.mo6888b(notificationId, 1, 0, 0, jPushLocalNotification.toJSON(), jPushLocalNotification.getBroadcastTime(), currentTimeMillis);
            } else {
                a.mo6883a(notificationId, 1, 0, 0, jPushLocalNotification.toJSON(), jPushLocalNotification.getBroadcastTime(), currentTimeMillis);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (broadcastTime < 300000) {
            m1377a(jPushLocalNotification.getNotificationId(), broadcastTime);
            z = true;
        } else {
            z = true;
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0042, code lost:
        if (r0 != null) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0047, code lost:
        r2.mo6906b(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x005d, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x005e, code lost:
        r10 = r1;
        r1 = r0;
        r0 = r10;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0041 A[ExcHandler: Exception (e java.lang.Exception), Splitter:B:5:0x000d] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0055 A[SYNTHETIC, Splitter:B:32:0x0055] */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void mo6991c(android.content.Context r12) {
        /*
            r11 = this;
            monitor-enter(r11)
            cn.jpush.android.data.d r2 = p004cn.jpush.android.data.C0591d.m1329a(r12)     // Catch:{ all -> 0x004c }
            r0 = 0
            boolean r0 = r2.mo6905a(r0)     // Catch:{ all -> 0x004c }
            if (r0 == 0) goto L_0x003f
            r0 = 0
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0041, all -> 0x004f }
            r6 = 300000(0x493e0, double:1.482197E-318)
            android.database.Cursor r0 = r2.mo6885a(r4, r6)     // Catch:{ Exception -> 0x0041, all -> 0x004f }
            boolean r1 = r0.moveToFirst()     // Catch:{ Exception -> 0x0041, all -> 0x005d }
            if (r1 == 0) goto L_0x0036
        L_0x001e:
            cn.jpush.android.data.e r1 = p004cn.jpush.android.data.C0591d.m1330a(r0)     // Catch:{ Exception -> 0x0041, all -> 0x005d }
            if (r1 == 0) goto L_0x0030
            long r6 = r1.mo6891a()     // Catch:{ Exception -> 0x0041, all -> 0x005d }
            long r8 = r1.mo6903f()     // Catch:{ Exception -> 0x0041, all -> 0x005d }
            long r8 = r8 - r4
            r11.m1377a(r6, r8)     // Catch:{ Exception -> 0x0041, all -> 0x005d }
        L_0x0030:
            boolean r1 = r0.moveToNext()     // Catch:{ Exception -> 0x0041, all -> 0x005d }
            if (r1 != 0) goto L_0x001e
        L_0x0036:
            if (r0 == 0) goto L_0x003b
            r0.close()     // Catch:{ all -> 0x004c }
        L_0x003b:
            r0 = 0
            r2.mo6906b(r0)     // Catch:{ all -> 0x004c }
        L_0x003f:
            monitor-exit(r11)
            return
        L_0x0041:
            r1 = move-exception
            if (r0 == 0) goto L_0x0047
            r0.close()     // Catch:{ all -> 0x004c }
        L_0x0047:
            r0 = 0
            r2.mo6906b(r0)     // Catch:{ all -> 0x004c }
            goto L_0x003f
        L_0x004c:
            r0 = move-exception
            monitor-exit(r11)
            throw r0
        L_0x004f:
            r1 = move-exception
            r10 = r1
            r1 = r0
            r0 = r10
        L_0x0053:
            if (r1 == 0) goto L_0x0058
            r1.close()     // Catch:{ all -> 0x004c }
        L_0x0058:
            r1 = 0
            r2.mo6906b(r1)     // Catch:{ all -> 0x004c }
            throw r0     // Catch:{ all -> 0x004c }
        L_0x005d:
            r1 = move-exception
            r10 = r1
            r1 = r0
            r0 = r10
            goto L_0x0053
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jpush.android.service.C0602a.mo6991c(android.content.Context):void");
    }

    /* renamed from: d */
    public final void mo6992d(final Context context) {
        f880b.execute(new Runnable() {
            public final void run() {
                C0602a.this.m1383e(context);
                C0602a.this.mo6991c(context);
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x007f A[SYNTHETIC, Splitter:B:35:0x007f] */
    /* renamed from: e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void m1383e(android.content.Context r14) {
        /*
            r13 = this;
            monitor-enter(r13)
            cn.jpush.android.data.d r1 = p004cn.jpush.android.data.C0591d.m1329a(r14)     // Catch:{ all -> 0x0078 }
            r0 = 0
            boolean r0 = r1.mo6905a(r0)     // Catch:{ all -> 0x0078 }
            if (r0 == 0) goto L_0x0052
            r2 = 0
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0054, all -> 0x007b }
            r0 = 1
            android.database.Cursor r12 = r1.mo6884a(r0, r4)     // Catch:{ Exception -> 0x0054, all -> 0x007b }
            boolean r0 = r12.moveToFirst()     // Catch:{ Exception -> 0x008c, all -> 0x0087 }
            if (r0 == 0) goto L_0x0049
        L_0x001c:
            cn.jpush.android.data.e r0 = p004cn.jpush.android.data.C0591d.m1330a(r12)     // Catch:{ Exception -> 0x008c, all -> 0x0087 }
            if (r0 == 0) goto L_0x0043
            java.lang.String r2 = r0.mo6901d()     // Catch:{ Exception -> 0x008c, all -> 0x0087 }
            java.lang.String r3 = r13.f884e     // Catch:{ Exception -> 0x008c, all -> 0x0087 }
            java.lang.String r4 = ""
            r13.m1378a(r14, r2, r3, r4)     // Catch:{ Exception -> 0x008c, all -> 0x0087 }
            long r2 = r0.mo6891a()     // Catch:{ Exception -> 0x008c, all -> 0x0087 }
            r4 = 0
            r5 = 0
            r6 = 0
            java.lang.String r7 = r0.mo6901d()     // Catch:{ Exception -> 0x008c, all -> 0x0087 }
            long r8 = r0.mo6903f()     // Catch:{ Exception -> 0x008c, all -> 0x0087 }
            long r10 = r0.mo6902e()     // Catch:{ Exception -> 0x008c, all -> 0x0087 }
            r1.mo6888b(r2, r4, r5, r6, r7, r8, r10)     // Catch:{ Exception -> 0x008c, all -> 0x0087 }
        L_0x0043:
            boolean r0 = r12.moveToNext()     // Catch:{ Exception -> 0x008c, all -> 0x0087 }
            if (r0 != 0) goto L_0x001c
        L_0x0049:
            if (r12 == 0) goto L_0x004e
            r12.close()     // Catch:{ all -> 0x0078 }
        L_0x004e:
            r0 = 0
            r1.mo6906b(r0)     // Catch:{ all -> 0x0078 }
        L_0x0052:
            monitor-exit(r13)
            return
        L_0x0054:
            r0 = move-exception
        L_0x0055:
            java.lang.String r3 = "JPushLocalNotificationCenter"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0089 }
            java.lang.String r5 = "triggerLNKillProcess: "
            r4.<init>(r5)     // Catch:{ all -> 0x0089 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0089 }
            java.lang.StringBuilder r0 = r4.append(r0)     // Catch:{ all -> 0x0089 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0089 }
            p004cn.jpush.android.p040d.C0582e.m1306c(r3, r0)     // Catch:{ all -> 0x0089 }
            if (r2 == 0) goto L_0x0073
            r2.close()     // Catch:{ all -> 0x0078 }
        L_0x0073:
            r0 = 0
            r1.mo6906b(r0)     // Catch:{ all -> 0x0078 }
            goto L_0x0052
        L_0x0078:
            r0 = move-exception
            monitor-exit(r13)
            throw r0
        L_0x007b:
            r0 = move-exception
            r12 = r2
        L_0x007d:
            if (r12 == 0) goto L_0x0082
            r12.close()     // Catch:{ all -> 0x0078 }
        L_0x0082:
            r2 = 0
            r1.mo6906b(r2)     // Catch:{ all -> 0x0078 }
            throw r0     // Catch:{ all -> 0x0078 }
        L_0x0087:
            r0 = move-exception
            goto L_0x007d
        L_0x0089:
            r0 = move-exception
            r12 = r2
            goto L_0x007d
        L_0x008c:
            r0 = move-exception
            r2 = r12
            goto L_0x0055
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jpush.android.service.C0602a.m1383e(android.content.Context):void");
    }

    /* renamed from: a */
    private synchronized void m1377a(final long j, long j2) {
        if (this.f882c != null) {
            C06042 r0 = new Runnable() {
                public final void run() {
                    try {
                        C0591d a = C0591d.m1329a(C0602a.this.f883d);
                        C0592e a2 = a.mo6886a(j, 0);
                        if (a2 == null) {
                            return;
                        }
                        if (a2.mo6898c() == 1) {
                            a.mo6888b(a2.mo6891a(), 0, 1, 0, a2.mo6901d(), a2.mo6903f(), a2.mo6902e());
                        } else if (a2.mo6895b() > 1) {
                            a.mo6888b(a2.mo6891a(), a2.mo6895b() - 1, 0, 0, a2.mo6901d(), a2.mo6903f(), a2.mo6902e());
                        } else if (a2.mo6895b() == 1) {
                            if (a2.mo6903f() <= System.currentTimeMillis()) {
                                C0602a.this.m1378a(C0602a.this.f883d, a2.mo6901d(), C0602a.this.f884e, "");
                                a.mo6888b(a2.mo6891a(), 0, 0, 0, a2.mo6901d(), a2.mo6903f(), a2.mo6902e());
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            if (j2 <= 0) {
                this.f882c.post(r0);
            } else {
                this.f882c.postDelayed(r0, j2);
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m1378a(Context context, String str, String str2, String str3) {
        Intent intent = new Intent(JPushInterface.ACTION_NOTIFICATION_RECEIVED_PROXY);
        intent.putExtra("senderId", str3);
        intent.putExtra("appId", str2);
        intent.putExtra(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, str);
        intent.putExtra("notificaion_type", 1);
        intent.addCategory(str2);
        context.sendOrderedBroadcast(intent, str2 + ".permission.JPUSH_MESSAGE");
    }
}
