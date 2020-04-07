package p004cn.jiguang.p015d.p017b;

import android.app.Service;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.google.android.vending.expansion.downloader.Constants;
import java.lang.ref.WeakReference;
import org.cocos2dx.lib.GameControllerDelegate;
import p004cn.jiguang.api.JResponse;
import p004cn.jiguang.api.utils.OutputDataUtil;
import p004cn.jiguang.p005a.p012c.C0377c;
import p004cn.jiguang.p015d.C0385a;
import p004cn.jiguang.p015d.p016a.C0386a;
import p004cn.jiguang.p015d.p016a.C0389d;
import p004cn.jiguang.p015d.p021d.C0445b;
import p004cn.jiguang.p015d.p021d.C0446c;
import p004cn.jiguang.p015d.p021d.C0448e;
import p004cn.jiguang.p015d.p021d.C0450g;
import p004cn.jiguang.p015d.p022e.p023a.C0465a;
import p004cn.jiguang.p015d.p022e.p023a.p024a.C0467b;
import p004cn.jiguang.p015d.p025f.C0477d;
import p004cn.jiguang.p015d.p028h.C0495f;
import p004cn.jiguang.p029e.C0501d;
import p004cn.jiguang.p031g.C0506a;
import p004cn.jiguang.p031g.C0530k;
import p004cn.jpush.android.service.PushService;

/* renamed from: cn.jiguang.d.b.d */
public final class C0417d {

    /* renamed from: d */
    private static boolean f285d = false;

    /* renamed from: j */
    private static final Object f286j = new Object();

    /* renamed from: l */
    private static volatile C0417d f287l;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public C0419f f288a;

    /* renamed from: b */
    private C0418e f289b;

    /* renamed from: c */
    private HandlerThread f290c;

    /* renamed from: e */
    private int f291e = 0;

    /* renamed from: f */
    private int f292f = 0;

    /* renamed from: g */
    private long f293g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public Context f294h;

    /* renamed from: i */
    private boolean f295i = false;

    /* renamed from: k */
    private WeakReference<Service> f296k;

    /* renamed from: m */
    private int f297m;

    /* renamed from: n */
    private int f298n;

    private C0417d() {
    }

    /* renamed from: a */
    public static C0417d m446a() {
        if (f287l == null) {
            synchronized (f286j) {
                if (f287l == null) {
                    f287l = new C0417d();
                }
            }
        }
        return f287l;
    }

    /* renamed from: a */
    static /* synthetic */ void m447a(C0417d dVar, long j) {
        C0501d.m903a("JiguangTcpManager", "Action - onLoggedIn - connection:" + j);
        if (!f285d) {
            C0446c.m635a(dVar.f294h.getApplicationContext(), true);
        }
        m453b(true);
        dVar.f291e = 0;
        dVar.f292f = 0;
        dVar.f289b.sendEmptyMessageDelayed(1005, 15000);
        C0420g.m485a().mo6465c();
        dVar.f289b.sendEmptyMessageDelayed(1032, 2000);
        C0377c.m181a(dVar.f294h, 2);
        C0445b.m618a();
        C0445b.m620a(dVar.f294h, j, 1);
    }

    /* renamed from: a */
    static /* synthetic */ void m449a(C0417d dVar, boolean z) {
        if (z || System.currentTimeMillis() - dVar.f293g >= 18000) {
            C0501d.m903a("JiguangTcpManager", "Send heart beat");
            dVar.f289b.removeMessages(1005);
            if (!C0419f.f301b.get() && f285d) {
                Long valueOf = Long.valueOf(C0386a.m277h());
                int a = C0389d.m306a();
                long d = C0389d.m331d(dVar.f294h);
                short b = C0448e.m641a().mo6558b();
                long longValue = valueOf.longValue();
                OutputDataUtil outputDataUtil = new OutputDataUtil(20480);
                outputDataUtil.writeU16(0);
                outputDataUtil.writeU8(4);
                outputDataUtil.writeU8(2);
                outputDataUtil.writeU64(longValue);
                outputDataUtil.writeU32((long) a);
                outputDataUtil.writeU64(d);
                outputDataUtil.writeU8(b);
                outputDataUtil.writeU16At(outputDataUtil.current(), 0);
                byte[] a2 = C0467b.m735a(outputDataUtil.toByteArray(), 1);
                if (a2 != null) {
                    C0477d.m767a().mo6616b().mo6609a(a2);
                } else {
                    C0501d.m907c("JiguangTcpManager", "send hb failed:sendData is null");
                }
                if (!dVar.f289b.hasMessages(GameControllerDelegate.BUTTON_SELECT)) {
                    dVar.f289b.sendEmptyMessageDelayed(GameControllerDelegate.BUTTON_SELECT, 10000);
                }
            }
        }
    }

    /* renamed from: a */
    public static void m450a(byte[] bArr, String str, int i) {
        C0420g.m485a().mo6461a(bArr, str, i);
    }

    /* renamed from: b */
    static /* synthetic */ void m451b(C0417d dVar) {
        if (!(dVar.f296k == null || dVar.f296k.get() == null)) {
            ((Service) dVar.f296k.get()).stopSelf();
        }
        C0506a.m978k(dVar.f294h);
        C0450g.m659a();
        C0450g.m662c();
        dVar.mo6447c();
    }

    /* renamed from: b */
    static /* synthetic */ void m452b(C0417d dVar, long j) {
        C0501d.m903a("JiguangTcpManager", "Action - onDisconnected - connection:" + j);
        C0420g.m485a().mo6462b();
        if (C0419f.f300a.get() != 0 || !C0389d.m339j(dVar.f294h)) {
            C0445b.m618a();
            C0445b.m620a(dVar.f294h, j, -1);
            if (f285d) {
                C0446c.m635a(dVar.f294h.getApplicationContext(), false);
            }
            m453b(false);
            dVar.f292f = 0;
            if (dVar.f288a != null) {
                dVar.f288a.mo6453c();
            }
            if (C0506a.m957c(dVar.f294h.getApplicationContext())) {
                dVar.m461j();
            }
            dVar.f291e++;
        }
    }

    /* renamed from: b */
    private static void m453b(boolean z) {
        f285d = z;
        C0389d.m330c(C0385a.f198e, z);
    }

    /* renamed from: c */
    static /* synthetic */ void m455c(C0417d dVar, long j) {
        C0501d.m903a("JiguangTcpManager", "Action - onHeartbeatSucceed - connection:" + j);
        if (j == C0419f.f300a.get()) {
            if (dVar.f289b.hasMessages(GameControllerDelegate.BUTTON_SELECT)) {
                dVar.f289b.removeMessages(GameControllerDelegate.BUTTON_SELECT);
            }
            dVar.f293g = System.currentTimeMillis();
            dVar.f292f = 0;
            C0445b.m618a();
            C0445b.m620a(dVar.f294h, j, 19);
        }
    }

    /* renamed from: d */
    static /* synthetic */ void m456d(C0417d dVar) {
        if (C0448e.m641a().mo6564f()) {
            dVar.f292f++;
            C0501d.m903a("JiguangTcpManager", "Action - onHeartbeatTimeout - timeoutTimes:" + dVar.f292f);
            if (m460i()) {
                C0501d.m903a("JiguangTcpManager", "Is connecting now. Give up to retry.");
                dVar.f289b.sendEmptyMessageDelayed(1005, 10000);
            } else if (!f285d || dVar.m459h()) {
                if (dVar.f288a != null) {
                    dVar.f288a.mo6451a();
                }
                dVar.m461j();
            } else {
                C0501d.m903a("JiguangTcpManager", "Already logged in. Give up to retry.");
                dVar.f289b.sendEmptyMessageDelayed(1005, Constants.ACTIVE_THREAD_WATCHDOG);
            }
        }
    }

    /* renamed from: d */
    public static boolean m457d() {
        return f285d;
    }

    /* renamed from: g */
    public static long m458g() {
        return C0419f.f300a.get();
    }

    /* renamed from: h */
    private boolean m459h() {
        return this.f292f > 1;
    }

    /* renamed from: i */
    private static boolean m460i() {
        return C0419f.f300a.get() != 0 && !f285d;
    }

    /* renamed from: j */
    private void m461j() {
        C0501d.m903a("JiguangTcpManager", "Action - retryConnect - disconnectedTimes:" + this.f291e);
        if (!C0506a.m957c(this.f294h.getApplicationContext()) || C0386a.m285l()) {
            C0501d.m903a("JiguangTcpManager", "network is not connect or hb is one day(user stop service) ");
            return;
        }
        int e = C0506a.m962e(this.f294h.getApplicationContext());
        int pow = (int) (Math.pow(2.0d, (double) this.f291e) * 3.0d * 1000.0d);
        int i = C0386a.m279i();
        if (pow > (i * 1000) / 2) {
            pow = (i * 1000) / 2;
        }
        if ((this.f291e < 5 || e == 1) && !this.f289b.hasMessages(1011)) {
            C0501d.m903a("JiguangTcpManager", "onDisconnected and retry restart conn - delay:" + pow);
            this.f289b.sendEmptyMessageDelayed(1011, (long) pow);
        }
    }

    /* renamed from: a */
    public final void mo6435a(int i) {
        this.f297m = i;
    }

    /* renamed from: a */
    public final void mo6436a(Service service) {
        if (service != null) {
            this.f296k = new WeakReference<>(service);
        }
    }

    /* renamed from: a */
    public final void mo6437a(Context context) {
        if (!this.f295i && context != null) {
            this.f294h = context.getApplicationContext();
            if (!C0389d.m339j(context)) {
                C0506a.m976j(this.f294h);
            }
            try {
                if (this.f290c == null || !this.f290c.isAlive()) {
                    this.f290c = new HandlerThread("JCore");
                    this.f290c.start();
                }
                this.f289b = new C0418e(this, this.f290c.getLooper() == null ? Looper.getMainLooper() : this.f290c.getLooper());
            } catch (Exception e) {
                this.f289b = new C0418e(this, Looper.getMainLooper());
            }
            C0450g.m659a().mo6565a(this.f294h);
            C0420g.m485a().mo6458a(this.f294h, (Handler) this.f289b);
            C0495f.m858a().mo6653a(this.f294h);
            this.f295i = true;
        }
    }

    /* renamed from: a */
    public final void mo6438a(Bundle bundle) {
        if (C0389d.m339j(this.f294h) || !bundle.getBoolean("connection-state", false)) {
            return;
        }
        if (C0419f.f300a.get() == 0) {
            mo6448e();
        } else {
            this.f289b.sendEmptyMessage(1006);
        }
    }

    /* renamed from: a */
    public final void mo6439a(JResponse jResponse, long j) {
        int a = ((C0465a) jResponse).mo6587a();
        if (a == 2) {
            C0446c.m636a(Message.obtain(this.f289b, 7303), j);
        } else if (a == 10) {
            jResponse.getHead().mo6591a(Long.valueOf(-1));
        }
    }

    /* renamed from: a */
    public final void mo6440a(String str, Bundle bundle) {
        C0445b.m618a();
        C0445b.m622a(this.f294h, str, C0419f.f300a.get(), bundle, (Handler) this.f289b);
        if (!C0389d.m339j(this.f294h)) {
            C0501d.m903a("JiguangTcpManager", "jiguang service already started");
            return;
        }
        C0395a.m383a(this.f294h, true);
        C0389d.m324b(this.f294h, false);
        if (C0419f.f300a.get() == 0) {
            mo6449f();
        }
    }

    /* renamed from: a */
    public final void mo6441a(String str, Object obj) {
        if (this.f289b.hasMessages(GameControllerDelegate.BUTTON_SELECT)) {
            this.f289b.removeMessages(GameControllerDelegate.BUTTON_SELECT);
        }
        this.f293g = System.currentTimeMillis();
        this.f292f = 0;
        C0420g.m485a().mo6460a(str, obj);
    }

    /* renamed from: a */
    public final void mo6442a(boolean z) {
        C0389d.m332d(this.f294h, z);
        if (this.f296k != null && this.f296k.get() != null && (this.f296k.get() instanceof PushService)) {
            ((PushService) this.f296k.get()).setDozePowerReceiver();
        }
    }

    /* renamed from: b */
    public final Handler mo6443b() {
        return this.f289b;
    }

    /* renamed from: b */
    public final void mo6444b(int i) {
        this.f298n = i;
    }

    /* renamed from: b */
    public final void mo6445b(Bundle bundle) {
        if (C0389d.m339j(this.f294h)) {
            C0501d.m903a("JiguangTcpManager", "tcp has close by active");
        } else if (C0419f.f300a.get() == 0) {
            mo6448e();
        } else {
            int i = bundle.getInt("rtc_delay", 0);
            if (C0530k.m1099a(bundle.getString("rtc"))) {
                this.f289b.sendEmptyMessage(1005);
            } else if (i == 0) {
                this.f289b.removeMessages(1005);
                if (!this.f289b.hasMessages(1004)) {
                    this.f289b.sendEmptyMessage(1005);
                }
            } else {
                this.f289b.removeMessages(1005);
                this.f289b.removeMessages(1004);
                this.f289b.sendEmptyMessageDelayed(1004, (long) i);
            }
        }
    }

    /* renamed from: b */
    public final void mo6446b(String str, Bundle bundle) {
        C0445b.m618a();
        C0445b.m622a(this.f294h, str, C0419f.f300a.get(), bundle, (Handler) this.f289b);
        if (C0389d.m339j(this.f294h)) {
            C0501d.m903a("JiguangTcpManager", "jiguang service already stoped");
            return;
        }
        C0448e.m641a();
        if (C0448e.m644e()) {
            C0395a.m383a(this.f294h, false);
            C0389d.m324b(this.f294h, true);
            if (!(this.f296k == null || this.f296k.get() == null)) {
                ((Service) this.f296k.get()).stopSelf();
            }
            mo6447c();
        }
    }

    /* renamed from: c */
    public final void mo6447c() {
        if (f285d) {
            C0446c.m635a(this.f294h, false);
        }
        m453b(false);
        this.f291e = 0;
        this.f292f = 0;
        if (this.f288a != null) {
            this.f288a.mo6453c();
        }
    }

    /* renamed from: e */
    public final void mo6448e() {
        if (m460i()) {
            C0501d.m903a("JiguangTcpManager", "Is connecting now. Give up to restart.");
        } else if (!f285d || m459h()) {
            this.f289b.removeMessages(1011);
            this.f289b.removeMessages(1005);
            mo6449f();
        } else {
            C0501d.m903a("JiguangTcpManager", "Already logged in. Give up to restart.");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0073, code lost:
        if (r3.f288a.mo6455e() == false) goto L_0x0075;
     */
    /* renamed from: f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void mo6449f() {
        /*
            r3 = this;
            monitor-enter(r3)
            java.lang.String r0 = "JiguangTcpManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x003e }
            java.lang.String r2 = "Action - restartNetworkingClient, pid:"
            r1.<init>(r2)     // Catch:{ all -> 0x003e }
            int r2 = android.os.Process.myPid()     // Catch:{ all -> 0x003e }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x003e }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x003e }
            p004cn.jiguang.p029e.C0501d.m903a(r0, r1)     // Catch:{ all -> 0x003e }
            android.content.Context r0 = r3.f294h     // Catch:{ all -> 0x003e }
            android.content.Context r0 = r0.getApplicationContext()     // Catch:{ all -> 0x003e }
            boolean r0 = p004cn.jiguang.p031g.C0506a.m957c(r0)     // Catch:{ all -> 0x003e }
            if (r0 != 0) goto L_0x002e
            java.lang.String r0 = "JiguangTcpManager"
            java.lang.String r1 = "No network connection. Give up to start connection thread."
            p004cn.jiguang.p029e.C0501d.m905b(r0, r1)     // Catch:{ all -> 0x003e }
        L_0x002c:
            monitor-exit(r3)
            return
        L_0x002e:
            android.content.Context r0 = r3.f294h     // Catch:{ all -> 0x003e }
            boolean r0 = p004cn.jiguang.p015d.p016a.C0389d.m339j(r0)     // Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x0041
            java.lang.String r0 = "JiguangTcpManager"
            java.lang.String r1 = "tcp has close by active"
            p004cn.jiguang.p029e.C0501d.m903a(r0, r1)     // Catch:{ all -> 0x003e }
            goto L_0x002c
        L_0x003e:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        L_0x0041:
            int r0 = r3.f297m     // Catch:{ all -> 0x003e }
            r1 = 102(0x66, float:1.43E-43)
            if (r0 != r1) goto L_0x004f
            java.lang.String r0 = "JiguangTcpManager"
            java.lang.String r1 = "login failed:102,give up start connection thread.reset from next app start"
            p004cn.jiguang.p029e.C0501d.m907c(r0, r1)     // Catch:{ all -> 0x003e }
            goto L_0x002c
        L_0x004f:
            cn.jiguang.d.b.f r0 = r3.f288a     // Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x0075
            java.lang.String r0 = "JiguangTcpManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x003e }
            java.lang.String r2 = "isRunning:"
            r1.<init>(r2)     // Catch:{ all -> 0x003e }
            cn.jiguang.d.b.f r2 = r3.f288a     // Catch:{ all -> 0x003e }
            boolean r2 = r2.mo6455e()     // Catch:{ all -> 0x003e }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x003e }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x003e }
            p004cn.jiguang.p029e.C0501d.m903a(r0, r1)     // Catch:{ all -> 0x003e }
            cn.jiguang.d.b.f r0 = r3.f288a     // Catch:{ all -> 0x003e }
            boolean r0 = r0.mo6455e()     // Catch:{ all -> 0x003e }
            if (r0 != 0) goto L_0x002c
        L_0x0075:
            cn.jiguang.d.b.f r0 = r3.f288a     // Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x0081
            cn.jiguang.d.b.f r0 = r3.f288a     // Catch:{ all -> 0x003e }
            r0.mo6453c()     // Catch:{ all -> 0x003e }
            r0 = 0
            r3.f288a = r0     // Catch:{ all -> 0x003e }
        L_0x0081:
            cn.jiguang.d.b.f r0 = new cn.jiguang.d.b.f     // Catch:{ all -> 0x003e }
            android.content.Context r1 = r3.f294h     // Catch:{ all -> 0x003e }
            android.content.Context r1 = r1.getApplicationContext()     // Catch:{ all -> 0x003e }
            cn.jiguang.d.b.e r2 = r3.f289b     // Catch:{ all -> 0x003e }
            r0.<init>(r1, r2)     // Catch:{ all -> 0x003e }
            r3.f288a = r0     // Catch:{ all -> 0x003e }
            cn.jiguang.d.b.f r0 = r3.f288a     // Catch:{ all -> 0x003e }
            r0.mo6452b()     // Catch:{ all -> 0x003e }
            goto L_0x002c
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p015d.p017b.C0417d.mo6449f():void");
    }
}
