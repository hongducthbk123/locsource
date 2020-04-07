package p004cn.jiguang.p015d.p017b;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import p004cn.jiguang.p015d.C0385a;
import p004cn.jiguang.p015d.p017b.p018a.p019a.C0402f;
import p004cn.jiguang.p015d.p021d.C0446c;
import p004cn.jiguang.p015d.p021d.C0448e;
import p004cn.jiguang.p015d.p022e.p023a.p024a.C0466a;
import p004cn.jiguang.p015d.p025f.C0477d;
import p004cn.jiguang.p015d.p025f.C0478e;
import p004cn.jiguang.p015d.p026g.C0483d;
import p004cn.jiguang.p015d.p026g.C0488i;
import p004cn.jiguang.p029e.C0501d;

/* renamed from: cn.jiguang.d.b.f */
public final class C0419f implements Runnable {

    /* renamed from: a */
    public static AtomicLong f300a = new AtomicLong(0);

    /* renamed from: b */
    public static AtomicBoolean f301b = new AtomicBoolean(false);

    /* renamed from: c */
    private Context f302c;

    /* renamed from: d */
    private Handler f303d;

    /* renamed from: e */
    private volatile boolean f304e = false;

    /* renamed from: f */
    private boolean f305f;

    /* renamed from: g */
    private ExecutorService f306g;

    public C0419f(Context context, Handler handler) {
        this.f302c = context;
        this.f303d = handler;
        this.f305f = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:117:0x00f0 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0201  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean m477a(int r31) {
        /*
            r30 = this;
        L_0x0000:
            r0 = r30
            boolean r2 = r0.f304e
            if (r2 == 0) goto L_0x0008
            r2 = 0
        L_0x0007:
            return r2
        L_0x0008:
            if (r31 > 0) goto L_0x0013
            java.lang.String r2 = "NetworkingClient"
            java.lang.String r3 = "login error,retry login too many times"
            p004cn.jiguang.p029e.C0501d.m903a(r2, r3)
            r2 = 0
            goto L_0x0007
        L_0x0013:
            r0 = r30
            android.content.Context r2 = r0.f302c
            boolean r2 = p004cn.jiguang.p015d.p016a.C0389d.m334e(r2)
            if (r2 == 0) goto L_0x002b
            r0 = r30
            android.content.Context r2 = r0.f302c
            java.lang.String r2 = p004cn.jiguang.p015d.p016a.C0389d.m335f(r2)
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 == 0) goto L_0x01fe
        L_0x002b:
            r0 = r30
            android.content.Context r3 = r0.f302c
            java.util.concurrent.atomic.AtomicLong r2 = f300a
            r2.get()
            cn.jiguang.d.g.d r2 = p004cn.jiguang.p015d.p026g.C0483d.m792d()
            java.lang.String r2 = r2.mo6623a()
            cn.jiguang.d.g.d r4 = p004cn.jiguang.p015d.p026g.C0483d.m792d()
            java.lang.String r4 = r4.f499a
            cn.jiguang.d.g.d r5 = p004cn.jiguang.p015d.p026g.C0483d.m792d()
            java.lang.String r5 = r5.mo6625b()
            cn.jiguang.d.g.d r6 = p004cn.jiguang.p015d.p026g.C0483d.m792d()
            java.lang.String r6 = r6.mo6627c()
            cn.jiguang.d.d.e r7 = p004cn.jiguang.p015d.p021d.C0448e.m641a()
            short r7 = r7.mo6561c()
            java.lang.String r8 = p004cn.jiguang.p015d.p016a.C0389d.m343n(r3)
            long r10 = p004cn.jiguang.p015d.p016a.C0386a.m277h()
            java.util.Random r9 = new java.util.Random
            r9.<init>()
            int r9 = r9.nextInt()
            int r9 = java.lang.Math.abs(r9)
            p004cn.jiguang.p015d.p026g.p027a.C0480a.m779a(r9)
            cn.jiguang.api.utils.OutputDataUtil r12 = new cn.jiguang.api.utils.OutputDataUtil
            r13 = 20480(0x5000, float:2.8699E-41)
            r12.<init>(r13)
            r13 = 0
            r12.writeU16(r13)
            r13 = 16
            r12.writeU8(r13)
            r13 = 0
            r12.writeU8(r13)
            r12.writeU64(r10)
            long r10 = (long) r9
            r12.writeU32(r10)
            r10 = 0
            r12.writeU64(r10)
            byte[] r2 = r2.getBytes()
            r12.writeByteArrayincludeLength(r2)
            byte[] r2 = r4.getBytes()
            r12.writeByteArrayincludeLength(r2)
            byte[] r2 = r5.getBytes()
            r12.writeByteArrayincludeLength(r2)
            r2 = 0
            r12.writeU8(r2)
            byte[] r2 = r6.getBytes()
            r12.writeByteArrayincludeLength(r2)
            cn.jiguang.d.d.e r2 = p004cn.jiguang.p015d.p021d.C0448e.m641a()
            r2.mo6563d()
            r12.writeU8(r7)
            byte[] r2 = r8.getBytes()
            r12.writeByteArrayincludeLength(r2)
            int r2 = r12.current()
            r4 = 0
            r12.writeU16At(r2, r4)
            byte[] r2 = r12.toByteArray()
            r4 = 0
            byte[] r2 = p004cn.jiguang.p015d.p022e.p023a.p024a.C0467b.m735a(r2, r4)
            if (r2 != 0) goto L_0x00f3
            r2 = 0
        L_0x00d7:
            if (r2 != 0) goto L_0x01cc
            boolean r2 = p004cn.jiguang.p015d.p016a.C0386a.m285l()
            if (r2 == 0) goto L_0x00ea
            r0 = r30
            android.os.Handler r2 = r0.f303d
            r3 = 1001(0x3e9, float:1.403E-42)
            r4 = 100
            r2.sendEmptyMessageDelayed(r3, r4)
        L_0x00ea:
            r30.m479f()
            r2 = 0
        L_0x00ee:
            if (r2 != 0) goto L_0x0201
            r2 = 0
            goto L_0x0007
        L_0x00f3:
            cn.jiguang.d.f.d r4 = p004cn.jiguang.p015d.p025f.C0477d.m767a()
            cn.jiguang.d.f.a r4 = r4.mo6616b()
            int r2 = r4.mo6609a(r2)
            if (r2 == 0) goto L_0x0103
            r2 = 0
            goto L_0x00d7
        L_0x0103:
            cn.jiguang.d.f.d r2 = p004cn.jiguang.p015d.p025f.C0477d.m767a()
            cn.jiguang.d.f.a r2 = r2.mo6616b()
            r4 = 20000(0x4e20, float:2.8026E-41)
            cn.jiguang.d.f.e r2 = r2.mo6610a(r4)
            int r4 = r2.mo6617a()
            if (r4 == 0) goto L_0x013f
            java.lang.String r3 = "ConnectingHelper"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Register failed - recv msg failed with error code:"
            r4.<init>(r5)
            int r5 = r2.mo6617a()
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r5 = ",msg:"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r2 = r2.mo6620c()
            java.lang.StringBuilder r2 = r4.append(r2)
            java.lang.String r2 = r2.toString()
            p004cn.jiguang.p029e.C0501d.m907c(r3, r2)
            r2 = 0
            goto L_0x00d7
        L_0x013f:
            java.nio.ByteBuffer r2 = r2.mo6619b()
            byte[] r2 = r2.array()
            cn.jiguang.api.JResponse r2 = p004cn.jiguang.p015d.p022e.p023a.p024a.C0466a.m732a(r2)
            if (r2 != 0) goto L_0x014f
            r2 = 0
            goto L_0x00d7
        L_0x014f:
            int r4 = r2.getCommand()
            if (r4 == 0) goto L_0x0157
            r2 = 0
            goto L_0x00d7
        L_0x0157:
            cn.jiguang.d.e.a.e r2 = (p004cn.jiguang.p015d.p022e.p023a.C0472e) r2
            int r4 = r2.code
            p004cn.jiguang.p015d.p016a.C0386a.m245a(r3, r4)
            cn.jiguang.d.b.d r5 = p004cn.jiguang.p015d.p017b.C0417d.m446a()
            r5.mo6444b(r4)
            if (r4 != 0) goto L_0x01c5
            long r4 = r2.getJuid()
            java.lang.String r6 = r2.mo6605a()
            java.lang.String r7 = r2.mo6606b()
            java.lang.String r8 = r2.mo6607c()
            java.lang.String r2 = "ConnectingHelper"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "Register succeed - juid:"
            r9.<init>(r10)
            java.lang.StringBuilder r9 = r9.append(r4)
            java.lang.String r10 = ", registrationId:"
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.StringBuilder r9 = r9.append(r7)
            java.lang.String r10 = ", deviceId:"
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.StringBuilder r9 = r9.append(r8)
            java.lang.String r9 = r9.toString()
            p004cn.jiguang.p029e.C0501d.m905b(r2, r9)
            boolean r2 = p004cn.jiguang.p031g.C0530k.m1099a(r7)
            if (r2 != 0) goto L_0x01ab
            r10 = 0
            int r2 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r2 != 0) goto L_0x01b5
        L_0x01ab:
            java.lang.String r2 = "ConnectingHelper"
            java.lang.String r3 = "Unexpected: registrationId/juid should not be empty. "
            p004cn.jiguang.p029e.C0501d.m909d(r2, r3)
            r2 = 0
            goto L_0x00d7
        L_0x01b5:
            p004cn.jiguang.p031g.C0506a.m969f(r3, r8)
            p004cn.jiguang.p015d.p016a.C0389d.m311a(r3, r4, r6, r7, r8)
            java.lang.String r2 = "cn.jpush.android.intent.REGISTRATION"
            java.lang.String r4 = "cn.jpush.android.REGISTRATION_ID"
            p004cn.jiguang.p031g.C0506a.m942a(r3, r2, r4, r7)
            r2 = 1
            goto L_0x00d7
        L_0x01c5:
            r2 = 1
            p004cn.jiguang.p015d.p021d.C0446c.m633a(r3, r4, r2)
            r2 = 0
            goto L_0x00d7
        L_0x01cc:
            r0 = r30
            android.content.Context r2 = r0.f302c
            cn.jiguang.d.g.d r3 = p004cn.jiguang.p015d.p026g.C0483d.m792d()
            p004cn.jiguang.p005a.p006a.p009c.C0355b.m101b(r2, r3)
            boolean r2 = p004cn.jiguang.p015d.p016a.C0386a.m285l()
            if (r2 == 0) goto L_0x01eb
            p004cn.jiguang.p015d.p016a.C0386a.m281j()
            r0 = r30
            android.os.Handler r2 = r0.f303d
            r3 = 1031(0x407, float:1.445E-42)
            r4 = 100
            r2.sendEmptyMessageDelayed(r3, r4)
        L_0x01eb:
            r0 = r30
            android.content.Context r2 = r0.f302c
            long r2 = p004cn.jiguang.p015d.p016a.C0389d.m331d(r2)
            cn.jiguang.d.h.f r4 = p004cn.jiguang.p015d.p028h.C0495f.m858a()
            cn.jiguang.d.h.e r4 = r4.mo6655b()
            r4.mo6636b(r2)
        L_0x01fe:
            r2 = 1
            goto L_0x00ee
        L_0x0201:
            r0 = r30
            android.content.Context r10 = r0.f302c
            java.util.concurrent.atomic.AtomicLong r2 = f300a
            r2.get()
            r9 = 0
            long r12 = p004cn.jiguang.p015d.p016a.C0389d.m331d(r10)
            java.lang.String r2 = p004cn.jiguang.p015d.p016a.C0389d.m335f(r10)
            java.lang.String r2 = p004cn.jiguang.p031g.C0530k.m1101b(r2)
            if (r2 != 0) goto L_0x021b
            java.lang.String r2 = ""
        L_0x021b:
            java.lang.String r11 = p004cn.jiguang.p015d.p016a.C0389d.m338i(r10)
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            p004cn.jiguang.p015d.p021d.C0445b.m618a()
            cn.jiguang.api.SdkType r7 = p004cn.jiguang.api.SdkType.JCORE
            java.lang.String r7 = r7.name()
            java.lang.String r8 = ""
            java.lang.String r7 = p004cn.jiguang.p015d.p021d.C0445b.m629d(r7, r8)
            boolean r8 = android.text.TextUtils.isEmpty(r7)
            if (r8 != 0) goto L_0x023c
            int r3 = p004cn.jiguang.p031g.C0506a.m954c(r7)
        L_0x023c:
            p004cn.jiguang.p015d.p021d.C0445b.m618a()
            cn.jiguang.api.SdkType r7 = p004cn.jiguang.api.SdkType.JANALYTICS
            java.lang.String r7 = r7.name()
            java.lang.String r8 = ""
            java.lang.String r7 = p004cn.jiguang.p015d.p021d.C0445b.m629d(r7, r8)
            boolean r8 = android.text.TextUtils.isEmpty(r7)
            if (r8 != 0) goto L_0x0255
            int r4 = p004cn.jiguang.p031g.C0506a.m954c(r7)
        L_0x0255:
            p004cn.jiguang.p015d.p021d.C0445b.m618a()
            cn.jiguang.api.SdkType r7 = p004cn.jiguang.api.SdkType.JSHARE
            java.lang.String r7 = r7.name()
            java.lang.String r8 = ""
            java.lang.String r7 = p004cn.jiguang.p015d.p021d.C0445b.m629d(r7, r8)
            boolean r8 = android.text.TextUtils.isEmpty(r7)
            if (r8 != 0) goto L_0x026e
            int r5 = p004cn.jiguang.p031g.C0506a.m954c(r7)
        L_0x026e:
            p004cn.jiguang.p015d.p021d.C0445b.m618a()
            cn.jiguang.api.SdkType r7 = p004cn.jiguang.api.SdkType.JPUSH
            java.lang.String r7 = r7.name()
            java.lang.String r8 = ""
            java.lang.String r7 = p004cn.jiguang.p015d.p021d.C0445b.m629d(r7, r8)
            boolean r8 = android.text.TextUtils.isEmpty(r7)
            if (r8 != 0) goto L_0x0287
            int r6 = p004cn.jiguang.p031g.C0506a.m954c(r7)
        L_0x0287:
            p004cn.jiguang.p015d.p026g.C0483d.m792d()
            byte r14 = p004cn.jiguang.p015d.p026g.C0483d.m791c(r10)
            java.lang.String r7 = "ConnectingHelper"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r15 = "Login with - juid:"
            r8.<init>(r15)
            java.lang.StringBuilder r8 = r8.append(r12)
            java.lang.String r15 = ", appKey:"
            java.lang.StringBuilder r8 = r8.append(r15)
            java.lang.StringBuilder r8 = r8.append(r11)
            java.lang.String r15 = ", sdkVersion:"
            java.lang.StringBuilder r8 = r8.append(r15)
            java.lang.StringBuilder r8 = r8.append(r3)
            java.lang.String r15 = ", pushVersion:"
            java.lang.StringBuilder r8 = r8.append(r15)
            java.lang.StringBuilder r8 = r8.append(r6)
            java.lang.String r15 = ", analyticsVersion:"
            java.lang.StringBuilder r8 = r8.append(r15)
            java.lang.StringBuilder r8 = r8.append(r4)
            java.lang.String r15 = " ,shareVersion:"
            java.lang.StringBuilder r8 = r8.append(r15)
            java.lang.StringBuilder r8 = r8.append(r5)
            java.lang.String r15 = ", pluginPlatformType:"
            java.lang.StringBuilder r8 = r8.append(r15)
            java.lang.StringBuilder r8 = r8.append(r14)
            java.lang.String r8 = r8.toString()
            p004cn.jiguang.p029e.C0501d.m905b(r7, r8)
            long r16 = java.lang.System.currentTimeMillis()
            cn.jiguang.d.d.e r7 = p004cn.jiguang.p015d.p021d.C0448e.m641a()
            short r15 = r7.mo6563d()
            int r18 = p004cn.jiguang.p031g.C0532m.m1107a(r10)
            java.lang.String r19 = p004cn.jiguang.p031g.C0506a.m972h(r10)
            cn.jiguang.a.a.b.e r7 = p004cn.jiguang.p005a.p006a.p008b.C0350f.m75a(r10)
            if (r7 == 0) goto L_0x03c3
            java.lang.String r7 = r7.mo6220e()
        L_0x02fc:
            java.lang.String r8 = p004cn.jiguang.p014c.C0384c.m235a(r10)
            java.util.Locale r20 = java.util.Locale.ENGLISH
            r21 = 0
            r0 = r21
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r21 = r0
            r0 = r20
            r1 = r21
            java.lang.String r8 = java.lang.String.format(r0, r8, r1)
            java.lang.String r8 = p004cn.jiguang.p031g.C0530k.m1102c(r8)
            if (r8 != 0) goto L_0x03c7
            java.lang.String r8 = ""
        L_0x031a:
            java.lang.String r20 = p004cn.jiguang.p015d.p016a.C0389d.m343n(r10)
            long r22 = p004cn.jiguang.p015d.p016a.C0386a.m277h()
            long r0 = (long) r3
            r24 = r0
            long r0 = (long) r6
            r26 = r0
            long r0 = (long) r4
            r28 = r0
            long r4 = (long) r5
            cn.jiguang.api.utils.OutputDataUtil r3 = new cn.jiguang.api.utils.OutputDataUtil
            r6 = 20480(0x5000, float:2.8699E-41)
            r3.<init>(r6)
            r6 = 0
            r3.writeU16(r6)
            r6 = 20
            r3.writeU8(r6)
            r6 = 1
            r3.writeU8(r6)
            r0 = r22
            r3.writeU64(r0)
            r22 = 0
            r0 = r22
            r3.writeU32(r0)
            r3.writeU64(r12)
            r6 = 97
            r3.writeU8(r6)
            r6 = 0
            r3.writeU8(r6)
            r6 = 0
            r3.writeU16(r6)
            byte[] r2 = r2.getBytes()
            r3.writeByteArrayincludeLength(r2)
            r0 = r26
            r3.writeU32(r0)
            r0 = r28
            r3.writeU32(r0)
            r3.writeU32(r4)
            r0 = r24
            r3.writeU32(r0)
            byte[] r2 = r11.getBytes()
            r3.writeByteArrayincludeLength(r2)
            r2 = 0
            r3.writeU8(r2)
            r3.writeU8(r15)
            r3.writeU8(r14)
            r0 = r18
            r3.writeU8(r0)
            byte[] r2 = r19.getBytes()
            r3.writeByteArrayincludeLength(r2)
            byte[] r2 = r7.getBytes()
            r3.writeByteArrayincludeLength(r2)
            byte[] r2 = r8.getBytes()
            r3.writeByteArrayincludeLength(r2)
            byte[] r2 = r20.getBytes()
            r3.writeByteArrayincludeLength(r2)
            int r2 = r3.current()
            r4 = 0
            r3.writeU16At(r2, r4)
            byte[] r2 = r3.toByteArray()
            r3 = 1
            byte[] r2 = p004cn.jiguang.p015d.p022e.p023a.p024a.C0467b.m735a(r2, r3)
            if (r2 == 0) goto L_0x03bd
            int r3 = r2.length
            if (r3 > 0) goto L_0x03cd
        L_0x03bd:
            r2 = -1
        L_0x03be:
            if (r2 >= 0) goto L_0x04d9
            r2 = 0
            goto L_0x0007
        L_0x03c3:
            java.lang.String r7 = ""
            goto L_0x02fc
        L_0x03c7:
            java.lang.String r8 = r8.toUpperCase()
            goto L_0x031a
        L_0x03cd:
            cn.jiguang.d.f.d r3 = p004cn.jiguang.p015d.p025f.C0477d.m767a()
            cn.jiguang.d.f.a r3 = r3.mo6616b()
            int r2 = r3.mo6609a(r2)
            if (r2 == 0) goto L_0x03dd
            r2 = -1
            goto L_0x03be
        L_0x03dd:
            cn.jiguang.d.f.d r2 = p004cn.jiguang.p015d.p025f.C0477d.m767a()
            cn.jiguang.d.f.a r2 = r2.mo6616b()
            r3 = 20000(0x4e20, float:2.8026E-41)
            cn.jiguang.d.f.e r2 = r2.mo6610a(r3)
            int r3 = r2.mo6617a()
            int r4 = r2.mo6617a()
            if (r4 == 0) goto L_0x0427
            java.lang.String r4 = "ConnectingHelper"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "Login failed - recv msg failed wit error code:"
            r5.<init>(r6)
            int r6 = r2.mo6617a()
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r6 = ",msg:"
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r2 = r2.mo6620c()
            java.lang.StringBuilder r2 = r5.append(r2)
            java.lang.String r2 = r2.toString()
            p004cn.jiguang.p029e.C0501d.m907c(r4, r2)
            long r4 = java.lang.System.currentTimeMillis()
            long r4 = r4 - r16
            r2 = 1
            p004cn.jiguang.p015d.p026g.C0486g.m809a(r10, r3, r4, r2)
            r2 = -1
            goto L_0x03be
        L_0x0427:
            long r4 = java.lang.System.currentTimeMillis()
            java.nio.ByteBuffer r2 = r2.mo6619b()
            byte[] r2 = r2.array()
            cn.jiguang.api.JResponse r2 = p004cn.jiguang.p015d.p022e.p023a.p024a.C0466a.m732a(r2)
            if (r2 != 0) goto L_0x0449
            java.lang.String r2 = "ConnectingHelper"
            java.lang.String r6 = "Login failed - unknown command"
            p004cn.jiguang.p029e.C0501d.m907c(r2, r6)
            long r4 = r4 - r16
            r2 = 1
            p004cn.jiguang.p015d.p026g.C0486g.m809a(r10, r3, r4, r2)
            r2 = -1
            goto L_0x03be
        L_0x0449:
            boolean r6 = r2 instanceof p004cn.jiguang.p015d.p022e.p023a.C0471d
            if (r6 != 0) goto L_0x045d
            java.lang.String r2 = "ConnectingHelper"
            java.lang.String r6 = "Login failed - it is not LoginResponse"
            p004cn.jiguang.p029e.C0501d.m907c(r2, r6)
            long r4 = r4 - r16
            r2 = 1
            p004cn.jiguang.p015d.p026g.C0486g.m809a(r10, r3, r4, r2)
            r2 = -1
            goto L_0x03be
        L_0x045d:
            cn.jiguang.d.e.a.d r2 = (p004cn.jiguang.p015d.p022e.p023a.C0471d) r2
            int r3 = r2.code
            cn.jiguang.d.b.d r6 = p004cn.jiguang.p015d.p017b.C0417d.m446a()
            r6.mo6435a(r3)
            if (r3 != 0) goto L_0x04a6
            int r6 = r2.getSid()
            int r2 = r2.mo6604a()
            long r12 = (long) r2
            r14 = 1000(0x3e8, double:4.94E-321)
            long r12 = r12 * r14
            p004cn.jiguang.p015d.p016a.C0389d.m308a(r10, r6)
            p004cn.jiguang.p015d.p016a.C0386a.m258b(r12)
            java.lang.String r2 = "ConnectingHelper"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "Login succeed - sid:"
            r7.<init>(r8)
            java.lang.StringBuilder r6 = r7.append(r6)
            java.lang.String r7 = ", serverTime;"
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.StringBuilder r6 = r6.append(r12)
            java.lang.String r6 = r6.toString()
            p004cn.jiguang.p029e.C0501d.m905b(r2, r6)
            p004cn.jiguang.p015d.p021d.C0446c.m634a(r10, r12)
            r2 = r9
        L_0x049e:
            long r4 = r4 - r16
            p004cn.jiguang.p015d.p026g.C0486g.m809a(r10, r3, r4, r2)
            r2 = r3
            goto L_0x03be
        L_0x04a6:
            r2 = 1
            r6 = 10000(0x2710, float:1.4013E-41)
            if (r3 != r6) goto L_0x04c0
            java.lang.String r6 = "ConnectingHelper"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "Login failed with Local error - code:"
            r7.<init>(r8)
            java.lang.StringBuilder r7 = r7.append(r3)
            java.lang.String r7 = r7.toString()
            p004cn.jiguang.p029e.C0501d.m907c(r6, r7)
            goto L_0x049e
        L_0x04c0:
            java.lang.String r6 = "ConnectingHelper"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "Login failed with server error - code:"
            r7.<init>(r8)
            java.lang.String r8 = p004cn.jiguang.p031g.C0528i.m1094a(r3)
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r7 = r7.toString()
            p004cn.jiguang.p029e.C0501d.m907c(r6, r7)
            goto L_0x049e
        L_0x04d9:
            if (r2 <= 0) goto L_0x051a
            java.util.concurrent.atomic.AtomicLong r3 = f300a
            long r4 = r3.get()
            r0 = r30
            android.os.Handler r3 = r0.f303d
            r6 = 7306(0x1c8a, float:1.0238E-41)
            android.os.Message r3 = android.os.Message.obtain(r3, r6)
            p004cn.jiguang.p015d.p021d.C0446c.m636a(r3, r4)
            r3 = 108(0x6c, float:1.51E-43)
            if (r2 != r3) goto L_0x04fd
            r0 = r30
            android.content.Context r2 = r0.f302c
            p004cn.jiguang.p031g.C0506a.m983m(r2)
            int r31 = r31 + -1
            goto L_0x0000
        L_0x04fd:
            r3 = 102(0x66, float:1.43E-43)
            if (r2 != r3) goto L_0x0512
            p004cn.jiguang.p015d.p016a.C0386a.m284k()
            r0 = r30
            android.os.Handler r2 = r0.f303d
            r3 = 1003(0x3eb, float:1.406E-42)
            r4 = 100
            r2.sendEmptyMessageDelayed(r3, r4)
        L_0x050f:
            r2 = 0
            goto L_0x0007
        L_0x0512:
            r3 = 1012(0x3f4, float:1.418E-42)
            if (r2 != r3) goto L_0x050f
            p004cn.jiguang.p015d.p016a.C0386a.m263c()
            goto L_0x050f
        L_0x051a:
            r2 = 0
            java.util.concurrent.atomic.AtomicLong r4 = f300a
            long r4 = r4.get()
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto L_0x0551
            java.util.concurrent.atomic.AtomicLong r2 = f300a
            long r2 = r2.get()
            r0 = r30
            android.os.Handler r4 = r0.f303d
            r5 = 7304(0x1c88, float:1.0235E-41)
            android.os.Message r4 = android.os.Message.obtain(r4, r5)
            p004cn.jiguang.p015d.p021d.C0446c.m636a(r4, r2)
            cn.jiguang.d.d.e r2 = p004cn.jiguang.p015d.p021d.C0448e.m641a()
            r0 = r30
            android.content.Context r3 = r0.f302c
            boolean r2 = r2.mo6560b(r3)
            if (r2 != 0) goto L_0x0554
            java.lang.String r2 = "NetworkingClient"
            java.lang.String r3 = "need not keep tcp connect,will close connection"
            p004cn.jiguang.p029e.C0501d.m903a(r2, r3)
            r2 = 0
            goto L_0x0007
        L_0x0551:
            r2 = 0
            goto L_0x0007
        L_0x0554:
            r2 = 1
            goto L_0x0007
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p015d.p017b.C0419f.m477a(int):boolean");
    }

    /* renamed from: a */
    private boolean m478a(Context context) {
        C0483d.m792d().mo6624a(context);
        try {
            f300a.set(Thread.currentThread().getId());
            C0402f fVar = new C0402f(context, this, f300a.get());
            if (fVar.mo6404b() != 0) {
                fVar.mo6403a();
                m479f();
                return false;
            } else if (!m477a(2)) {
                fVar.mo6403a();
                m479f();
                return false;
            } else {
                fVar.mo6403a();
                return true;
            }
        } catch (UnsatisfiedLinkError e) {
            C0501d.m906b("NetworkingClient", "长连接失败, jpush.so加载异常", e);
            return false;
        }
    }

    /* renamed from: f */
    private void m479f() {
        C0501d.m903a("NetworkingClient", "Action - closeConnection - connection:" + f300a.get());
        if (0 != f300a.get()) {
            try {
                f301b.set(true);
                f300a.set(0);
                C0477d.m767a().mo6616b().mo6611a();
                f301b.set(false);
            } catch (Exception e) {
            }
            this.f305f = false;
            if (C0448e.m641a().mo6564f()) {
                C0446c.m636a(Message.obtain(this.f303d, 7301), f300a.get());
            }
        }
    }

    /* renamed from: a */
    public final void mo6451a() {
        C0501d.m903a("NetworkingClient", "Action - tryStop - connection:" + f300a.get());
        this.f304e = true;
        this.f305f = false;
        if (f300a.get() != 0) {
            C0477d.m767a().mo6616b().mo6611a();
        }
    }

    /* renamed from: b */
    public final synchronized void mo6452b() {
        if (this.f305f) {
            this.f306g = Executors.newSingleThreadExecutor();
            try {
                this.f306g.execute(this);
            } catch (Throwable th) {
                C0501d.m907c("NetworkingClient", "execute networkingClient exception :" + th);
                mo6451a();
            }
        }
        return;
    }

    /* renamed from: c */
    public final synchronized void mo6453c() {
        mo6451a();
        C0488i.m817a();
        C0488i.m818a(this.f306g);
    }

    /* renamed from: d */
    public final boolean mo6454d() {
        return this.f304e;
    }

    /* renamed from: e */
    public final boolean mo6455e() {
        return this.f305f;
    }

    public final void run() {
        C0501d.m905b("NetworkingClient", "Begin to run in ConnectingThread - id:" + Thread.currentThread().getId());
        try {
            if (!m478a(this.f302c)) {
                C0501d.m903a("NetworkingClient", "prepare Push Channel failed , returned");
                return;
            }
            do {
                if (!this.f304e) {
                    C0501d.m903a("NetworkingClient", "Network listening...");
                    C0478e a = C0477d.m767a().mo6616b().mo6610a(0);
                    if (a != null) {
                        if (a.mo6617a() != 0) {
                            C0501d.m903a("NetworkingClient", " recv failed with error code:" + a.mo6617a() + ",msg:" + a.mo6620c() + ",No Break!!");
                        } else {
                            ByteBuffer b = a.mo6619b();
                            int length = b.array().length;
                            byte[] bArr = new byte[length];
                            System.arraycopy(b.array(), 0, bArr, 0, length);
                            C0466a.m733a(this.f302c, bArr);
                            C0501d.m903a("NetworkingClient", "Received bytes - len:" + b.array().length + ", connection:" + f300a.get() + ", pkg:" + C0385a.f196c);
                        }
                    }
                }
                if (this.f304e) {
                    C0501d.m903a("NetworkingClient", "Break receiving by wantStop - connection:" + f300a.get());
                }
                m479f();
                return;
            } while (0 != f300a.get());
            C0501d.m907c("NetworkingClient", "mConnection is reset to 0 when network listening. Break now.");
        } catch (Throwable th) {
            C0501d.m908c("NetworkingClient", "run exception", th);
        }
    }
}
