package p004cn.jiguang.p015d.p021d;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import p004cn.jiguang.api.SdkType;
import p004cn.jiguang.api.utils.OutputDataUtil;
import p004cn.jiguang.p015d.p016a.C0386a;
import p004cn.jiguang.p015d.p016a.C0389d;
import p004cn.jiguang.p015d.p017b.C0417d;

/* renamed from: cn.jiguang.d.d.e */
public final class C0448e {

    /* renamed from: e */
    private static volatile C0448e f402e;

    /* renamed from: f */
    private static final Object f403f = new Object();

    /* renamed from: a */
    private boolean f404a;

    /* renamed from: b */
    private boolean f405b;

    /* renamed from: c */
    private boolean f406c;

    /* renamed from: d */
    private boolean f407d;

    /* renamed from: g */
    private Map<Long, String> f408g;

    private C0448e() {
        this.f404a = false;
        this.f405b = false;
        this.f406c = false;
        this.f407d = false;
        this.f408g = new HashMap();
        this.f404a = m645g();
        this.f405b = m646h();
        this.f406c = m647i();
        this.f407d = m648j();
    }

    /* renamed from: a */
    public static C0448e m641a() {
        if (f402e == null) {
            synchronized (f403f) {
                if (f402e == null) {
                    f402e = new C0448e();
                }
            }
        }
        return f402e;
    }

    /* renamed from: a */
    private static String m642a(Context context, String str) {
        C0445b.m618a();
        String d = C0445b.m629d(str, "");
        return (TextUtils.isEmpty(d) || d.equals(C0389d.m322b(context, str))) ? "" : d;
    }

    /* renamed from: a */
    private void m643a(short s, String str, String str2) {
        long d = C0389d.m331d(null);
        int a = C0389d.m306a();
        long h = C0386a.m277h();
        OutputDataUtil outputDataUtil = new OutputDataUtil(20480);
        outputDataUtil.writeU16(0);
        outputDataUtil.writeU8(0);
        outputDataUtil.writeU8(26);
        outputDataUtil.writeU64(h);
        outputDataUtil.writeU32((long) a);
        outputDataUtil.writeU64(d);
        outputDataUtil.writeU8(s);
        outputDataUtil.writeU8(1);
        outputDataUtil.writeByteArrayincludeLength(str.getBytes());
        outputDataUtil.writeU16At(outputDataUtil.current(), 0);
        byte[] byteArray = outputDataUtil.toByteArray();
        C0417d.m446a();
        C0417d.m450a(byteArray, SdkType.JCORE.name(), 0);
        this.f408g.put(Long.valueOf(h), str2);
    }

    /* renamed from: e */
    public static boolean m644e() {
        C0445b.m618a();
        return C0445b.m625a(0);
    }

    /* renamed from: g */
    private static boolean m645g() {
        try {
            Class.forName("cn.jpush.android.api.JPushInterface");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /* renamed from: h */
    private static boolean m646h() {
        try {
            Class.forName("cn.jpush.im.android.api.JMessageClient");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /* renamed from: i */
    private static boolean m647i() {
        try {
            Class.forName("cn.jiguang.analytics.android.api.JAnalyticsInterface");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /* renamed from: j */
    private static boolean m648j() {
        try {
            Class.forName("cn.jiguang.share.android.api.JShareInterface");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /* renamed from: a */
    public final void mo6556a(Context context) {
        if (context != null) {
            String a = m642a(context, SdkType.JPUSH.name());
            String a2 = m642a(context, SdkType.JMESSAGE.name());
            String a3 = m642a(context, SdkType.JANALYTICS.name());
            String a4 = m642a(context, SdkType.JSHARE.name());
            if (!TextUtils.isEmpty(a)) {
                m643a(1, a, SdkType.JPUSH.name());
            }
            if (!TextUtils.isEmpty(a2)) {
                m643a(2, a2, SdkType.JMESSAGE.name());
            }
            if (!TextUtils.isEmpty(a3)) {
                m643a(4, a3, SdkType.JANALYTICS.name());
            }
            if (!TextUtils.isEmpty(a4)) {
                m643a(5, a4, SdkType.JSHARE.name());
            }
        }
    }

    /* renamed from: a */
    public final void mo6557a(Context context, long j) {
        String str = (String) this.f408g.remove(Long.valueOf(j));
        if (!TextUtils.isEmpty(str)) {
            C0445b.m618a();
            TextUtils.isEmpty(C0445b.m629d(str, ""));
        }
        if ((this.f408g == null || this.f408g.isEmpty()) && !this.f405b && !this.f404a) {
            C0417d.m446a().mo6447c();
        }
    }

    /* renamed from: b */
    public final short mo6558b() {
        short s = 0;
        if (this.f404a) {
            s = 1;
        }
        return this.f405b ? (C0386a.m298y() >= 0 || C0386a.m297x()) ? (short) (s | 32) : (short) (s | 64) : s;
    }

    /* renamed from: b */
    public final void mo6559b(Context context, long j) {
        String str = (String) this.f408g.remove(Long.valueOf(j));
        if (!TextUtils.isEmpty(str)) {
            C0445b.m618a();
            String d = C0445b.m629d(str, "");
            if (!TextUtils.isEmpty(d)) {
                C0389d.m314a(context, str, d);
            }
        }
        if (!mo6560b(context)) {
            C0417d.m446a().mo6447c();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean mo6560b(android.content.Context r5) {
        /*
            r4 = this;
            r0 = 0
            r1 = 1
            boolean r2 = r4.f405b
            if (r2 != 0) goto L_0x0016
            boolean r2 = r4.f404a
            if (r2 != 0) goto L_0x0016
            if (r5 != 0) goto L_0x0018
            java.lang.String r2 = "JClientsHelper"
            java.lang.String r3 = "get isNeedUserCtrl failed,context is null"
            p004cn.jiguang.p029e.C0501d.m907c(r2, r3)
        L_0x0013:
            r2 = r0
        L_0x0014:
            if (r2 == 0) goto L_0x0017
        L_0x0016:
            r0 = r1
        L_0x0017:
            return r0
        L_0x0018:
            boolean r2 = r4.f406c
            if (r2 == 0) goto L_0x002e
            cn.jiguang.api.SdkType r2 = p004cn.jiguang.api.SdkType.JANALYTICS
            java.lang.String r2 = r2.name()
            java.lang.String r2 = m642a(r5, r2)
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L_0x002e
            r2 = r1
            goto L_0x0014
        L_0x002e:
            boolean r2 = r4.f407d
            if (r2 == 0) goto L_0x0044
            cn.jiguang.api.SdkType r2 = p004cn.jiguang.api.SdkType.JSHARE
            java.lang.String r2 = r2.name()
            java.lang.String r2 = m642a(r5, r2)
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L_0x0044
            r2 = r1
            goto L_0x0014
        L_0x0044:
            boolean r2 = r4.f404a
            if (r2 == 0) goto L_0x005a
            cn.jiguang.api.SdkType r2 = p004cn.jiguang.api.SdkType.JPUSH
            java.lang.String r2 = r2.name()
            java.lang.String r2 = m642a(r5, r2)
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L_0x005a
            r2 = r1
            goto L_0x0014
        L_0x005a:
            boolean r2 = r4.f405b
            if (r2 == 0) goto L_0x0013
            cn.jiguang.api.SdkType r2 = p004cn.jiguang.api.SdkType.JMESSAGE
            java.lang.String r2 = r2.name()
            java.lang.String r2 = m642a(r5, r2)
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L_0x0013
            r2 = r1
            goto L_0x0014
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p015d.p021d.C0448e.mo6560b(android.content.Context):boolean");
    }

    /* renamed from: c */
    public final short mo6561c() {
        short s = 0;
        if (this.f404a) {
            s = 1;
        }
        if (this.f406c) {
            s = (short) (s | 4);
        }
        if (this.f407d) {
            s = (short) (s | 8);
        }
        return this.f405b ? (short) (s | 32) : s;
    }

    /* renamed from: c */
    public final void mo6562c(Context context, long j) {
        this.f408g.remove(Long.valueOf(j));
        if ((this.f408g == null || this.f408g.isEmpty()) && !this.f405b && !this.f404a) {
            C0417d.m446a().mo6447c();
        }
    }

    /* renamed from: d */
    public final short mo6563d() {
        short s = 0;
        if (this.f404a) {
            s = 1;
        }
        if (this.f405b) {
            s = (C0386a.m298y() >= 0 || C0386a.m297x()) ? (short) (s | 32) : (short) (s | 64);
        }
        if (this.f406c) {
            s = (short) (s | 4);
        }
        return this.f407d ? (short) (s | 8) : s;
    }

    /* renamed from: f */
    public final boolean mo6564f() {
        return this.f405b || this.f404a;
    }
}
