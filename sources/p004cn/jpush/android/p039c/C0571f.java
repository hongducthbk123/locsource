package p004cn.jpush.android.p039c;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import p004cn.jiguang.api.JCoreInterface;
import p004cn.jiguang.api.utils.ByteBufferUtils;
import p004cn.jiguang.api.utils.OutputDataUtil;
import p004cn.jpush.android.C0541a;
import p004cn.jpush.android.C0563b;
import p004cn.jpush.android.p040d.C0582e;

/* renamed from: cn.jpush.android.c.f */
public class C0571f {

    /* renamed from: b */
    private static volatile C0571f f750b;

    /* renamed from: a */
    private Map<Byte, C0572a> f751a = new HashMap();

    /* renamed from: cn.jpush.android.c.f$a */
    private class C0572a {

        /* renamed from: a */
        public byte f752a;

        /* renamed from: b */
        public String f753b;

        /* renamed from: c */
        public long f754c;

        /* renamed from: d */
        public byte[] f755d;

        /* renamed from: e */
        public int f756e = 0;

        public C0572a(byte b, String str, long j, byte[] bArr) {
            this.f752a = b;
            this.f753b = str;
            this.f754c = j;
            this.f755d = bArr;
        }

        public final String toString() {
            return "PluginPlatformRegIDBean{pluginPlatformType=" + this.f752a + ", regid='" + this.f753b + '\'' + ", rid=" + this.f754c + ", retryCount=" + this.f756e + '}';
        }
    }

    private C0571f() {
    }

    /* renamed from: a */
    public static C0571f m1232a() {
        if (f750b == null) {
            synchronized (C0571f.class) {
                if (f750b == null) {
                    f750b = new C0571f();
                }
            }
        }
        return f750b;
    }

    /* renamed from: a */
    public final synchronized void mo6843a(Context context, Bundle bundle) {
        byte byteValue = bundle.getByte("plugin.platform.type", 0).byteValue();
        if (byteValue != 0 && JCoreInterface.isTcpConnected()) {
            String string = bundle.getString("plugin.platform.regid ");
            if (!this.f751a.containsKey(Byte.valueOf(byteValue)) || !TextUtils.equals(((C0572a) this.f751a.get(Byte.valueOf(byteValue))).f753b, string)) {
                long nextRid = JCoreInterface.getNextRid();
                long uid = JCoreInterface.getUid();
                int sid = JCoreInterface.getSid();
                OutputDataUtil outputDataUtil = new OutputDataUtil(20480);
                outputDataUtil.writeU16(0);
                outputDataUtil.writeU8(1);
                outputDataUtil.writeU8(27);
                outputDataUtil.writeU64(nextRid);
                outputDataUtil.writeU32((long) sid);
                outputDataUtil.writeU64(uid);
                outputDataUtil.writeByteArrayincludeLength(TextUtils.isEmpty(string) ? new byte[0] : string.getBytes());
                outputDataUtil.writeU8(byteValue);
                outputDataUtil.writeU16At(outputDataUtil.current(), 0);
                C0572a aVar = new C0572a(byteValue, string, nextRid, outputDataUtil.toByteArray());
                this.f751a.put(Byte.valueOf(byteValue), aVar);
                m1233a(context, aVar);
            }
        }
    }

    /* renamed from: a */
    private synchronized void m1233a(Context context, C0572a aVar) {
        JCoreInterface.sendRequestData(context, C0541a.f649a, ByteBufferUtils.ERROR_CODE, aVar.f755d);
    }

    /* renamed from: a */
    public final void mo6842a(Context context, long j, int i) {
        C0572a a = m1231a(j);
        C0582e.m1304b("PluginPlatformRidUpdate", "onUpdateRidFailed rid:" + j + ",errorCode:" + i + " ,pluginPlatformRegIDBean:" + String.valueOf(a));
        if (a == null) {
            return;
        }
        if (a.f756e < 3) {
            a.f756e++;
            m1233a(context, a);
            return;
        }
        this.f751a.remove(Byte.valueOf(a.f752a));
    }

    /* renamed from: a */
    private C0572a m1231a(long j) {
        for (Entry entry : this.f751a.entrySet()) {
            if (((C0572a) entry.getValue()).f754c == j) {
                return (C0572a) entry.getValue();
            }
        }
        return null;
    }

    /* renamed from: a */
    public final void mo6841a(Context context, long j) {
        C0572a a = m1231a(j);
        C0582e.m1304b("PluginPlatformRidUpdate", "onUpdateRidSuccess rid:" + j + " ,pluginPlatformRegIDBean:" + String.valueOf(a));
        if (a != null) {
            C0563b.m1204a(context, (int) a.f752a, a.f753b);
            C0563b.m1209b(context, (int) a.f752a, true);
            this.f751a.remove(Byte.valueOf(a.f752a));
        }
    }

    /* renamed from: b */
    public final void mo6844b(Context context, long j) {
        C0572a a = m1231a(j);
        C0582e.m1304b("PluginPlatformRidUpdate", "onUpdateRidTimeout rid:" + j + " ,pluginPlatformRegIDBean:" + String.valueOf(a));
        if (a == null) {
            return;
        }
        if (a.f756e < 3) {
            a.f756e++;
            m1233a(context, a);
            return;
        }
        this.f751a.remove(Byte.valueOf(a.f752a));
    }
}
