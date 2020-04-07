package p004cn.jpush.android.p039c;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.firebase.iid.FirebaseInstanceId;
import java.util.concurrent.atomic.AtomicBoolean;
import p004cn.jiguang.api.JCoreInterface;
import p004cn.jiguang.api.MultiSpHelper;
import p004cn.jiguang.api.SdkType;
import p004cn.jpush.android.C0541a;
import p004cn.jpush.android.C0563b;
import p004cn.jpush.android.p040d.C0582e;
import p004cn.jpush.android.service.C0606c;
import p004cn.jpush.android.service.PushReceiver;

/* renamed from: cn.jpush.android.c.g */
public class C0573g {

    /* renamed from: d */
    private static volatile C0573g f758d;

    /* renamed from: a */
    private AtomicBoolean f759a = new AtomicBoolean(false);

    /* renamed from: b */
    private byte f760b = 0;

    /* renamed from: c */
    private C0570e f761c;

    /* renamed from: e */
    private boolean f762e = false;

    private C0573g() {
    }

    /* renamed from: a */
    public static C0573g m1238a() {
        if (f758d == null) {
            synchronized (C0573g.class) {
                if (f758d == null) {
                    f758d = new C0573g();
                }
            }
        }
        return f758d;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0056 A[Catch:{ Throwable -> 0x00a9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0064 A[SYNTHETIC, Splitter:B:16:0x0064] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void mo6846a(android.content.Context r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            if (r5 != 0) goto L_0x000c
            java.lang.String r0 = "PluginPlatformsInterface"
            java.lang.String r1 = "context was null"
            p004cn.jpush.android.p040d.C0582e.m1306c(r0, r1)     // Catch:{ all -> 0x008d }
        L_0x000a:
            monitor-exit(r4)
            return
        L_0x000c:
            java.util.concurrent.atomic.AtomicBoolean r0 = r4.f759a     // Catch:{ all -> 0x008d }
            boolean r0 = r0.get()     // Catch:{ all -> 0x008d }
            if (r0 != 0) goto L_0x000a
            byte r0 = p004cn.jpush.android.p039c.C0575i.m1255a(r5)     // Catch:{ all -> 0x008d }
            r4.f760b = r0     // Catch:{ all -> 0x008d }
            byte r0 = r4.f760b     // Catch:{ all -> 0x008d }
            switch(r0) {
                case 1: goto L_0x0090;
                case 2: goto L_0x0098;
                case 3: goto L_0x00a0;
                default: goto L_0x001f;
            }     // Catch:{ all -> 0x008d }
        L_0x001f:
            java.lang.String r0 = "PluginPlatformsInterface"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x008d }
            java.lang.String r2 = "whichPlatform - "
            r1.<init>(r2)     // Catch:{ all -> 0x008d }
            byte r2 = r4.f760b     // Catch:{ all -> 0x008d }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x008d }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x008d }
            p004cn.jpush.android.p040d.C0582e.m1304b(r0, r1)     // Catch:{ all -> 0x008d }
            boolean r0 = p004cn.jpush.android.p039c.C0575i.m1259b(r5)     // Catch:{ all -> 0x008d }
            r4.f762e = r0     // Catch:{ all -> 0x008d }
            java.lang.String r0 = "PluginPlatformsInterface"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x008d }
            java.lang.String r2 = "isIntegrateFCM -"
            r1.<init>(r2)     // Catch:{ all -> 0x008d }
            boolean r2 = r4.f762e     // Catch:{ all -> 0x008d }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x008d }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x008d }
            p004cn.jpush.android.p040d.C0582e.m1304b(r0, r1)     // Catch:{ all -> 0x008d }
            boolean r0 = r4.f762e     // Catch:{ all -> 0x008d }
            if (r0 != 0) goto L_0x0062
            r0 = 8
            r1 = 0
            p004cn.jpush.android.C0563b.m1204a(r5, r0, r1)     // Catch:{ all -> 0x008d }
            r0 = 8
            r1 = 0
            p004cn.jpush.android.C0563b.m1209b(r5, r0, r1)     // Catch:{ all -> 0x008d }
        L_0x0062:
            if (r5 == 0) goto L_0x0085
            boolean r0 = r4.f762e     // Catch:{ Throwable -> 0x00a9 }
            if (r0 == 0) goto L_0x006b
            com.google.firebase.FirebaseApp.initializeApp(r5)     // Catch:{ Throwable -> 0x00a9 }
        L_0x006b:
            cn.jpush.android.c.e r0 = r4.f761c     // Catch:{ Throwable -> 0x00c7 }
            if (r0 == 0) goto L_0x0085
            android.content.Context r0 = r5.getApplicationContext()     // Catch:{ Throwable -> 0x00c7 }
            boolean r0 = p004cn.jpush.android.api.JPushInterface.isPushStopped(r0)     // Catch:{ Throwable -> 0x00c7 }
            if (r0 != 0) goto L_0x00bf
            cn.jpush.android.c.e r0 = r4.f761c     // Catch:{ Throwable -> 0x00c7 }
            r0.mo6833a(r5)     // Catch:{ Throwable -> 0x00c7 }
            java.lang.String r0 = "PluginPlatformsInterface"
            java.lang.String r1 = "plugin plateform register start"
            p004cn.jpush.android.p040d.C0582e.m1304b(r0, r1)     // Catch:{ Throwable -> 0x00c7 }
        L_0x0085:
            java.util.concurrent.atomic.AtomicBoolean r0 = r4.f759a     // Catch:{ all -> 0x008d }
            r1 = 1
            r0.set(r1)     // Catch:{ all -> 0x008d }
            goto L_0x000a
        L_0x008d:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        L_0x0090:
            cn.jpush.android.c.j r0 = new cn.jpush.android.c.j     // Catch:{ all -> 0x008d }
            r0.<init>(r5)     // Catch:{ all -> 0x008d }
            r4.f761c = r0     // Catch:{ all -> 0x008d }
            goto L_0x001f
        L_0x0098:
            cn.jpush.android.c.b r0 = new cn.jpush.android.c.b     // Catch:{ all -> 0x008d }
            r0.<init>(r5)     // Catch:{ all -> 0x008d }
            r4.f761c = r0     // Catch:{ all -> 0x008d }
            goto L_0x001f
        L_0x00a0:
            cn.jpush.android.c.d r0 = new cn.jpush.android.c.d     // Catch:{ all -> 0x008d }
            r0.<init>(r5)     // Catch:{ all -> 0x008d }
            r4.f761c = r0     // Catch:{ all -> 0x008d }
            goto L_0x001f
        L_0x00a9:
            r0 = move-exception
            java.lang.String r1 = "PluginPlatformsInterface"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x008d }
            java.lang.String r3 = "#FirebaseAPP init error:"
            r2.<init>(r3)     // Catch:{ all -> 0x008d }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ all -> 0x008d }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x008d }
            p004cn.jpush.android.p040d.C0582e.m1306c(r1, r0)     // Catch:{ all -> 0x008d }
            goto L_0x006b
        L_0x00bf:
            java.lang.String r0 = "PluginPlatformsInterface"
            java.lang.String r1 = "stopPush was called. will not init the third push"
            p004cn.jpush.android.p040d.C0582e.m1306c(r0, r1)     // Catch:{ Throwable -> 0x00c7 }
            goto L_0x0085
        L_0x00c7:
            r0 = move-exception
            java.lang.String r1 = "PluginPlatformsInterface"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x008d }
            r2.<init>()     // Catch:{ all -> 0x008d }
            byte r3 = r4.f760b     // Catch:{ all -> 0x008d }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x008d }
            java.lang.String r3 = " register error:"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x008d }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ all -> 0x008d }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x008d }
            p004cn.jpush.android.p040d.C0582e.m1306c(r1, r0)     // Catch:{ all -> 0x008d }
            goto L_0x0085
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jpush.android.p039c.C0573g.mo6846a(android.content.Context):void");
    }

    /* renamed from: b */
    public final void mo6848b(Context context) {
        if (context != null) {
            mo6846a(context);
            try {
                if (this.f761c != null) {
                    this.f761c.mo6834b(context);
                }
            } catch (Throwable th) {
                C0582e.m1306c("PluginPlatformsInterface", this.f760b + " resumePush error:" + th);
            }
        }
    }

    /* renamed from: c */
    public final void mo6850c(Context context) {
        if (context != null) {
            mo6846a(context);
            try {
                if (this.f761c != null) {
                    this.f761c.mo6838c(context);
                }
            } catch (Throwable th) {
                C0582e.m1306c("PluginPlatformsInterface", this.f760b + " stopPush error:" + th);
            }
        }
    }

    /* renamed from: d */
    public final void mo6851d(Context context) {
        if (context == null) {
            context = C0541a.f653e;
        }
        if (context != null) {
            mo6846a(context);
            if (this.f761c != null) {
                m1240a(context, (int) this.f760b);
                String a = C0563b.m1203a(context, this.f760b);
                if (m1241a(context, (int) this.f760b, a)) {
                    m1239a(context, this.f760b, a);
                }
            }
            if (this.f762e) {
                String b = m1242b();
                if (!TextUtils.isEmpty(b)) {
                    mo6849b(context, b);
                    return;
                }
                try {
                    Intent intent = new Intent(context, PushReceiver.class);
                    intent.setAction("cn.jpush.android.intent.plugin.platform.REFRESSH_REGID");
                    Bundle bundle = new Bundle();
                    bundle.putString("sdktype", SdkType.JPUSH.name());
                    intent.putExtras(bundle);
                    intent.setPackage(context.getPackageName());
                    context.sendBroadcast(intent);
                } catch (Throwable th) {
                    C0582e.m1306c("PluginPlatformsInterface", "send ACTION_PLUGIN_PALTFORM_REQ_REFRESSH_REGID failed:" + th);
                }
            }
        }
    }

    /* renamed from: a */
    public final void mo6847a(Context context, String str) {
        if (context == null) {
            context = C0541a.f653e;
        }
        if (context != null) {
            mo6846a(context);
            if (this.f761c != null) {
                m1240a(context, (int) this.f760b);
                if (m1241a(context, (int) this.f760b, str)) {
                    m1239a(context, this.f760b, str);
                }
            }
        }
    }

    /* renamed from: e */
    public final void mo6852e(Context context) {
        if (context == null) {
            context = C0541a.f653e;
        }
        if (context != null) {
            mo6846a(context);
            if (this.f762e) {
                mo6849b(context, m1242b());
            }
        }
    }

    /* renamed from: b */
    public final void mo6849b(Context context, String str) {
        boolean z;
        if (context == null) {
            context = C0541a.f653e;
        }
        if (context != null) {
            mo6846a(context);
            if (this.f762e) {
                String appKey = JCoreInterface.getAppKey();
                if (!TextUtils.equals(appKey, MultiSpHelper.getString(context, "flag_clear_fcm_rid", null))) {
                    MultiSpHelper.commitString(context, "flag_clear_fcm_rid", appKey);
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    C0582e.m1302a("PluginPlatformsInterface", "appkey changed,will clear fcm token");
                    C0563b.m1209b(context, 8, false);
                    C0563b.m1204a(context, 8, (String) null);
                } else {
                    C0582e.m1302a("PluginPlatformsInterface", "appkey not change,will not clear fcm token");
                }
                if (m1241a(context, 8, str)) {
                    m1239a(context, 8, str);
                }
            }
        }
    }

    /* renamed from: b */
    private static String m1242b() {
        String str = null;
        try {
            str = FirebaseInstanceId.getInstance().getToken();
        } catch (Throwable th) {
            C0582e.m1305b("PluginPlatformsInterface", "get fcm token error:", th);
        }
        C0582e.m1304b("PluginPlatformsInterface", "getFcmToken:" + String.valueOf(str));
        return str;
    }

    /* renamed from: a */
    private static boolean m1241a(Context context, int i, String str) {
        if (C0563b.m1211b(context, i) && TextUtils.equals(C0563b.m1203a(context, i), str)) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    private static void m1239a(Context context, byte b, String str) {
        C0563b.m1209b(context, (int) b, false);
        C0563b.m1204a(context, (int) b, str);
        Bundle bundle = new Bundle();
        C0606c.m1392a(context, bundle, "intent.plugin.platform.REFRESSH_REGID");
        bundle.putString("plugin.platform.regid ", str);
        bundle.putByte("plugin.platform.type", b);
        JCoreInterface.sendAction(context, C0541a.f649a, bundle);
    }

    /* renamed from: f */
    public final byte mo6853f(Context context) {
        mo6846a(context);
        return this.f760b;
    }

    /* renamed from: g */
    public final boolean mo6854g(Context context) {
        mo6846a(context);
        return this.f762e;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00a2  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m1240a(android.content.Context r10, int r11) {
        /*
            r9 = this;
            r8 = 0
            r0 = 1
            r1 = 0
            java.lang.String r2 = "PluginPlatformsInterface"
            java.lang.String r3 = "checkClearRegId"
            p004cn.jpush.android.p040d.C0582e.m1304b(r2, r3)
            cn.jpush.android.c.e r2 = r9.f761c
            java.lang.String r2 = r2.mo6839a()
            cn.jpush.android.c.e r3 = r9.f761c
            java.lang.String r3 = r3.mo6840b()
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 != 0) goto L_0x0022
            boolean r4 = android.text.TextUtils.isEmpty(r2)
            if (r4 == 0) goto L_0x003d
        L_0x0022:
            java.lang.String r0 = "PluginPlatformsInterface"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "platform:"
            r1.<init>(r2)
            java.lang.StringBuilder r1 = r1.append(r11)
            java.lang.String r2 = " appkey or appid is empty,need not clear plugin rid"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            p004cn.jpush.android.p040d.C0582e.m1304b(r0, r1)
        L_0x003c:
            return
        L_0x003d:
            java.lang.String r4 = p004cn.jiguang.api.JCoreInterface.getAppKey()
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 == 0) goto L_0x004f
            java.lang.String r0 = "PluginPlatformsInterface"
            java.lang.String r1 = "jpush appkey is empty,need not clear plugin rid"
            p004cn.jpush.android.p040d.C0582e.m1304b(r0, r1)
            goto L_0x003c
        L_0x004f:
            java.lang.String r5 = "3.1.2"
            java.lang.String r6 = "flag_clear_plugin_rid"
            java.lang.String r6 = p004cn.jiguang.api.MultiSpHelper.getString(r10, r6, r8)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.StringBuilder r2 = r7.append(r2)
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r4)
            java.lang.StringBuilder r2 = r2.append(r5)
            java.lang.String r2 = r2.toString()
            java.lang.String r2 = p004cn.jpush.android.p040d.C0577a.m1270a(r2)
            boolean r3 = android.text.TextUtils.isEmpty(r6)
            if (r3 == 0) goto L_0x0090
            java.lang.String r0 = "flag_clear_plugin_rid"
            p004cn.jiguang.api.MultiSpHelper.commitString(r10, r0, r2)
        L_0x007f:
            r0 = r1
        L_0x0080:
            if (r0 == 0) goto L_0x00a2
            java.lang.String r0 = "PluginPlatformsInterface"
            java.lang.String r2 = "app info changed , will clear plugin rid"
            p004cn.jpush.android.p040d.C0582e.m1304b(r0, r2)
            p004cn.jpush.android.C0563b.m1209b(r10, r11, r1)
            p004cn.jpush.android.C0563b.m1204a(r10, r11, r8)
            goto L_0x003c
        L_0x0090:
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L_0x0080
            boolean r3 = android.text.TextUtils.equals(r6, r2)
            if (r3 != 0) goto L_0x007f
            java.lang.String r3 = "flag_clear_plugin_rid"
            p004cn.jiguang.api.MultiSpHelper.commitString(r10, r3, r2)
            goto L_0x0080
        L_0x00a2:
            java.lang.String r0 = "PluginPlatformsInterface"
            java.lang.String r1 = "app info not change, will not clear plugin rid"
            p004cn.jpush.android.p040d.C0582e.m1304b(r0, r1)
            goto L_0x003c
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jpush.android.p039c.C0573g.m1240a(android.content.Context, int):void");
    }
}
