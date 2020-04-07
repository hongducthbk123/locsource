package p004cn.jiguang.p015d.p017b;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import java.util.Deque;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import p004cn.jiguang.api.JAction;
import p004cn.jiguang.p015d.p021d.C0445b;
import p004cn.jiguang.p015d.p022e.p023a.p024a.C0468c;
import p004cn.jiguang.p029e.C0501d;

/* renamed from: cn.jiguang.d.b.g */
public final class C0420g {

    /* renamed from: f */
    private static C0420g f307f;

    /* renamed from: a */
    private Map<String, C0421h> f308a = new ConcurrentHashMap();

    /* renamed from: b */
    private Deque<C0421h> f309b = new LinkedBlockingDeque();

    /* renamed from: c */
    private Deque<C0421h> f310c = new LinkedBlockingDeque();

    /* renamed from: d */
    private Handler f311d;

    /* renamed from: e */
    private Context f312e;

    /* renamed from: a */
    public static C0420g m485a() {
        if (f307f == null) {
            f307f = new C0420g();
        }
        return f307f;
    }

    /* renamed from: a */
    private synchronized C0421h m486a(String str) {
        C0421h hVar;
        hVar = null;
        for (C0421h hVar2 : this.f310c) {
            if (str.equals(hVar2.mo6466a())) {
                this.f310c.remove(hVar2);
            } else {
                hVar2 = hVar;
            }
            hVar = hVar2;
        }
        return hVar;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static String m488b(long j, String str) {
        return j + "|" + str;
    }

    /* renamed from: c */
    private synchronized void m489c(C0421h hVar) {
        boolean z;
        if (hVar != null) {
            Iterator it = this.f310c.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (((C0421h) it.next()).mo6466a().equals(hVar.mo6466a())) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (!z) {
                this.f310c.offerLast(hVar);
            }
        }
    }

    /* renamed from: d */
    private void m490d(C0421h hVar) {
        if (hVar != null) {
            this.f308a.remove(hVar.mo6466a());
            this.f309b.remove(hVar);
            this.f311d.removeMessages(7403, hVar);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /* renamed from: e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m491e(p004cn.jiguang.p015d.p017b.C0421h r9) {
        /*
            r8 = this;
            r6 = 0
            if (r9 != 0) goto L_0x0005
        L_0x0004:
            return
        L_0x0005:
            int r0 = p004cn.jiguang.p015d.p016a.C0389d.m306a()     // Catch:{ Throwable -> 0x0066 }
            r1 = 0
            long r2 = p004cn.jiguang.p015d.p016a.C0389d.m331d(r1)     // Catch:{ Throwable -> 0x0066 }
            byte[] r1 = r9.f316d     // Catch:{ Throwable -> 0x0066 }
            cn.jiguang.api.utils.OutputDataUtil r4 = new cn.jiguang.api.utils.OutputDataUtil     // Catch:{ Throwable -> 0x0066 }
            r5 = 20480(0x5000, float:2.8699E-41)
            r4.<init>(r5)     // Catch:{ Throwable -> 0x0066 }
            r4.writeByteArray(r1)     // Catch:{ Throwable -> 0x0066 }
            long r0 = (long) r0     // Catch:{ Throwable -> 0x0066 }
            r5 = 12
            r4.writeU32At(r0, r5)     // Catch:{ Throwable -> 0x0066 }
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0029
            r0 = 16
            r4.writeU64At(r2, r0)     // Catch:{ Throwable -> 0x0066 }
        L_0x0029:
            byte[] r0 = r4.toByteArray()     // Catch:{ Throwable -> 0x0066 }
            r1 = 1
            byte[] r0 = p004cn.jiguang.p015d.p022e.p023a.p024a.C0467b.m735a(r0, r1)     // Catch:{ Throwable -> 0x0066 }
            if (r0 == 0) goto L_0x005e
            java.util.concurrent.atomic.AtomicLong r1 = p004cn.jiguang.p015d.p017b.C0419f.f300a     // Catch:{ Throwable -> 0x0066 }
            long r2 = r1.get()     // Catch:{ Throwable -> 0x0066 }
            int r1 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r1 == 0) goto L_0x005e
            cn.jiguang.d.f.d r1 = p004cn.jiguang.p015d.p025f.C0477d.m767a()     // Catch:{ Throwable -> 0x0066 }
            cn.jiguang.d.f.a r1 = r1.mo6616b()     // Catch:{ Throwable -> 0x0066 }
            r1.mo6609a(r0)     // Catch:{ Throwable -> 0x0066 }
        L_0x0049:
            r8.m489c(r9)
            if (r9 == 0) goto L_0x0004
            android.os.Handler r0 = r8.f311d
            r1 = 7404(0x1cec, float:1.0375E-41)
            android.os.Message r0 = android.os.Message.obtain(r0, r1, r9)
            android.os.Handler r1 = r8.f311d
            r2 = 9800(0x2648, double:4.842E-320)
            r1.sendMessageDelayed(r0, r2)
            goto L_0x0004
        L_0x005e:
            java.lang.String r0 = "RequestCacheManager"
            java.lang.String r1 = "sendCommandWithLoggedIn failed:sendData is null"
            p004cn.jiguang.p029e.C0501d.m907c(r0, r1)     // Catch:{ Throwable -> 0x0066 }
            goto L_0x0049
        L_0x0066:
            r0 = move-exception
            goto L_0x0049
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p015d.p017b.C0420g.m491e(cn.jiguang.d.b.h):void");
    }

    /* renamed from: a */
    public final void mo6457a(long j, String str, Object obj) {
        if (obj != null && (obj instanceof C0468c)) {
            C0468c cVar = (C0468c) obj;
            C0419f.f300a.get();
            C0421h a = m486a(m488b(cVar.mo6592b().longValue(), str));
            if (a != null) {
                if (a != null) {
                    this.f311d.removeMessages(7404, a);
                }
                C0421h hVar = (C0421h) this.f308a.get(a.mo6466a());
                if (hVar != null) {
                    m490d(hVar);
                }
            }
        }
    }

    /* renamed from: a */
    public final void mo6458a(Context context, Handler handler) {
        this.f312e = context;
        this.f311d = handler;
    }

    /* renamed from: a */
    public final void mo6459a(C0421h hVar) {
        if (hVar != null && m486a(hVar.mo6466a()) != null) {
            if (hVar.f313a > 0) {
                C0417d.m446a();
                if (C0417d.m457d()) {
                    hVar.f313a -= 10000;
                    hVar.f314b++;
                    m491e(hVar);
                } else {
                    this.f309b.offerFirst(hVar);
                }
                if (hVar.f314b >= 2) {
                    this.f311d.sendEmptyMessageDelayed(1005, 2000);
                    return;
                }
                return;
            }
            mo6463b(hVar);
        }
    }

    /* renamed from: a */
    public final void mo6460a(String str, Object obj) {
        Message obtain = Message.obtain(this.f311d, 7402, obj);
        Bundle bundle = new Bundle();
        bundle.putLong("connection", C0419f.f300a.get());
        bundle.putString("request_sdktype", str);
        obtain.setData(bundle);
        obtain.sendToTarget();
    }

    /* renamed from: a */
    public final void mo6461a(byte[] bArr, String str, int i) {
        if (bArr != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("request_timeout", i);
            bundle.putByteArray("request_data", bArr);
            bundle.putString("request_sdktype", str);
            Message obtain = Message.obtain(this.f311d, 7401);
            obtain.setData(bundle);
            obtain.sendToTarget();
        }
    }

    /* renamed from: b */
    public final void mo6462b() {
        this.f311d.removeMessages(7404);
        while (true) {
            C0421h hVar = (C0421h) this.f310c.pollLast();
            if (hVar != null) {
                this.f309b.offerFirst(hVar);
            } else {
                return;
            }
        }
    }

    /* renamed from: b */
    public final void mo6463b(C0421h hVar) {
        if (hVar != null) {
            m490d(hVar);
            C0445b.m618a();
            Context context = this.f312e;
            String str = hVar.f318f;
            long j = hVar.f315c;
            int i = hVar.f317e;
            if (TextUtils.isEmpty(str)) {
                C0445b.m626b(context, j, i);
                return;
            }
            JAction jAction = (JAction) C0445b.f396a.get(str);
            if (jAction == null) {
                C0445b.m626b(context, j, i);
            } else {
                jAction.dispatchTimeOutMessage(context, C0419f.f300a.get(), j, i);
            }
        }
    }

    /* renamed from: b */
    public final void mo6464b(byte[] bArr, String str, int i) {
        C0501d.m903a("RequestCacheManager", "Action - sendRequestInternal - connection:" + C0419f.f300a.get() + ", timeout:" + i + ",sdkType:" + str + ", threadId:" + Thread.currentThread().getId());
        if (bArr != null) {
            if (this.f308a.size() > 200) {
                C0501d.m907c("RequestCacheManager", "sendRequestInternal failed,cache is full");
                return;
            }
            C0421h hVar = new C0421h(bArr, str, i);
            this.f308a.put(hVar.mo6466a(), hVar);
            if (i > 10000) {
                this.f311d.sendMessageDelayed(Message.obtain(this.f311d, 7403, hVar), (long) hVar.f313a);
            }
            m491e(hVar);
        }
    }

    /* renamed from: c */
    public final void mo6465c() {
        while (true) {
            C0421h hVar = (C0421h) this.f309b.pollFirst();
            if (hVar == null) {
                return;
            }
            if (hVar.f317e == 2) {
                this.f309b.remove(hVar);
                this.f308a.remove(hVar.mo6466a());
            } else {
                m491e(hVar);
            }
        }
    }
}
