package p004cn.jpush.android.p037a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import p004cn.jiguang.api.JCoreInterface;
import p004cn.jiguang.api.MultiSpHelper;
import p004cn.jiguang.api.utils.OutputDataUtil;
import p004cn.jpush.android.C0541a;
import p004cn.jpush.android.api.JPushInterface.C0558a;
import p004cn.jpush.android.api.JPushMessage;
import p004cn.jpush.android.p040d.C0582e;
import p004cn.jpush.android.p040d.C0584g;

/* renamed from: cn.jpush.android.a.f */
public final class C0548f {

    /* renamed from: a */
    private static ConcurrentLinkedQueue<Long> f664a = new ConcurrentLinkedQueue<>();

    /* renamed from: c */
    private static final Object f665c = new Object();

    /* renamed from: d */
    private static C0548f f666d;

    /* renamed from: b */
    private ConcurrentHashMap<Long, C0549a> f667b = new ConcurrentHashMap<>();

    /* renamed from: cn.jpush.android.a.f$a */
    private class C0549a {

        /* renamed from: a */
        public int f668a;

        /* renamed from: b */
        public String f669b;

        public C0549a(int i, String str) {
            this.f668a = i;
            this.f669b = str;
        }

        public final String toString() {
            return "MobileBean{sequence=" + this.f668a + ", mobileNumber='" + this.f669b + '\'' + '}';
        }
    }

    /* renamed from: a */
    public static synchronized C0548f m1129a() {
        C0548f fVar;
        synchronized (C0548f.class) {
            if (f666d == null) {
                synchronized (f665c) {
                    if (f666d == null) {
                        f666d = new C0548f();
                    }
                }
            }
            fVar = f666d;
        }
        return fVar;
    }

    /* renamed from: a */
    public final void mo6779a(Context context, Bundle bundle) {
        char c;
        int i;
        int i2 = bundle.getInt("sequence", 0);
        String string = bundle.getString("mobile_number");
        String string2 = MultiSpHelper.getString(context, "mobile_number", null);
        if (string2 == null || !TextUtils.equals(string, string2)) {
            if (string2 != null) {
                MultiSpHelper.commitString(context, "mobile_number", null);
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (f664a.size() < 3) {
                f664a.offer(Long.valueOf(currentTimeMillis));
                c = 0;
            } else {
                long longValue = currentTimeMillis - ((Long) f664a.element()).longValue();
                if (longValue < 0) {
                    f664a.clear();
                    c = 2;
                } else if (longValue > 10000) {
                    while (f664a.size() >= 3) {
                        f664a.poll();
                    }
                    f664a.offer(Long.valueOf(currentTimeMillis));
                    c = 0;
                } else {
                    c = 1;
                }
            }
            if (c != 0) {
                if (c == 1) {
                    i = C0558a.f706l;
                } else {
                    i = C0558a.f708n;
                }
                m1131a(context, i2, i, string);
                return;
            }
            int c2 = C0584g.m1316c(string);
            if (c2 != 0) {
                C0582e.m1302a("MobileNumberHelper", "Invalid mobile number: " + string + ", will not set mobile number this time.");
                m1131a(context, i2, c2, string);
                return;
            }
            long nextRid = JCoreInterface.getNextRid();
            long uid = JCoreInterface.getUid();
            int sid = JCoreInterface.getSid();
            OutputDataUtil outputDataUtil = new OutputDataUtil(20480);
            outputDataUtil.writeU16(0);
            outputDataUtil.writeU8(1);
            outputDataUtil.writeU8(26);
            outputDataUtil.writeU64(nextRid);
            outputDataUtil.writeU32((long) sid);
            outputDataUtil.writeU64(uid);
            outputDataUtil.writeU8(7);
            outputDataUtil.writeU8(1);
            outputDataUtil.writeByteArrayincludeLength(string != null ? string.getBytes() : new byte[0]);
            outputDataUtil.writeU16At(outputDataUtil.current(), 0);
            byte[] byteArray = outputDataUtil.toByteArray();
            this.f667b.put(Long.valueOf(nextRid), new C0549a(i2, string));
            JCoreInterface.sendRequestData(context, C0541a.f649a, 20000, byteArray);
            return;
        }
        C0582e.m1302a("MobileNumberHelper", "already set this mobile number");
        m1131a(context, i2, C0558a.f695a, string);
    }

    /* renamed from: a */
    public static void m1131a(Context context, int i, int i2, String str) {
        try {
            Intent intent = new Intent();
            intent.addCategory(C0541a.f651c);
            intent.setAction("cn.jpush.android.intent.RECEIVE_MESSAGE");
            intent.setPackage(context.getPackageName());
            intent.putExtra("message_type", 3);
            intent.putExtra("sequence", i);
            intent.putExtra("code", i2);
            intent.putExtra("mobile_number", str);
            context.sendBroadcast(intent);
        } catch (Throwable th) {
            C0582e.m1306c("MobileNumberHelper", "onResult error:" + th);
        }
    }

    /* renamed from: a */
    public final void mo6778a(Context context, long j, int i) {
        if (this.f667b.size() != 0) {
            C0549a aVar = (C0549a) this.f667b.remove(Long.valueOf(j));
            if (aVar != null) {
                if (i == 0) {
                    MultiSpHelper.commitString(context, "mobile_number", aVar.f669b);
                } else if (i == 11) {
                    i = C0558a.f719y;
                } else if (i == 10) {
                    i = C0558a.f718x;
                }
                m1131a(context, aVar.f668a, i, aVar.f669b);
            }
        }
    }

    /* renamed from: a */
    public static JPushMessage m1130a(Intent intent) {
        if (intent == null) {
            return null;
        }
        try {
            int intExtra = intent.getIntExtra("sequence", -1);
            int intExtra2 = intent.getIntExtra("code", -1);
            String stringExtra = intent.getStringExtra("mobile_number");
            JPushMessage jPushMessage = new JPushMessage();
            try {
                jPushMessage.setSequence(intExtra);
                jPushMessage.setErrorCode(intExtra2);
                jPushMessage.setMobileNumber(stringExtra);
                return jPushMessage;
            } catch (Throwable th) {
                return jPushMessage;
            }
        } catch (Throwable th2) {
            return null;
        }
    }
}
