package p004cn.jpush.android.p037a;

import android.content.Context;
import android.content.Intent;
import p004cn.jiguang.api.JCoreInterface;
import p004cn.jiguang.api.JRequest;
import p004cn.jiguang.api.utils.OutputDataUtil;
import p004cn.jpush.android.C0541a;
import p004cn.jpush.android.api.JPushInterface.C0558a;
import p004cn.jpush.android.p040d.C0582e;
import p004cn.jpush.p036a.C0539c;

/* renamed from: cn.jpush.android.a.e */
public final class C0547e {

    /* renamed from: b */
    private static C0547e f662b = null;

    /* renamed from: a */
    private Context f663a = null;

    private C0547e(Context context) {
        this.f663a = context;
    }

    /* renamed from: a */
    public static synchronized C0547e m1126a(Context context) {
        C0547e eVar;
        synchronized (C0547e.class) {
            if (f662b == null) {
                f662b = new C0547e(context);
            }
            eVar = f662b;
        }
        return eVar;
    }

    /* renamed from: a */
    public final void mo6777a(JRequest jRequest, int i) {
        if (jRequest != null) {
            C0582e.m1302a("JPushRequestHelper", "Action - sendJPushRequest, timeout:" + 20000 + ", threadId:" + Thread.currentThread().getId());
            Long rid = jRequest.getRid();
            int command = jRequest.getCommand();
            long uid = JCoreInterface.getUid();
            int sid = JCoreInterface.getSid();
            switch (command) {
                case 10:
                case 28:
                case 29:
                    long longValue = rid.longValue();
                    String appKey = JCoreInterface.getAppKey();
                    short version = (short) jRequest.getVersion();
                    short command2 = (short) jRequest.getCommand();
                    String a = ((C0539c) jRequest).mo6772a();
                    OutputDataUtil outputDataUtil = new OutputDataUtil(20480);
                    outputDataUtil.writeU16(0);
                    outputDataUtil.writeU8(version);
                    outputDataUtil.writeU8(command2);
                    outputDataUtil.writeU64(longValue);
                    outputDataUtil.writeU32((long) sid);
                    outputDataUtil.writeU64(uid);
                    if (command2 == 10) {
                        outputDataUtil.writeByteArrayincludeLength(appKey.getBytes());
                    }
                    outputDataUtil.writeByteArrayincludeLength(a.getBytes());
                    outputDataUtil.writeU16At(outputDataUtil.current(), 0);
                    JCoreInterface.sendRequestData(this.f663a, C0541a.f649a, 20000, outputDataUtil.toByteArray());
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    public final void mo6776a(long j) {
        try {
            int a = C0556l.m1165a().mo6782a(j);
            Intent intent = new Intent();
            intent.addCategory(C0541a.f651c);
            intent.setPackage(this.f663a.getPackageName());
            if (a == 0) {
                intent.setAction("cn.jpush.android.intent.TAG_ALIAS_TIMEOUT");
            } else {
                intent.setAction("cn.jpush.android.intent.RECEIVE_MESSAGE");
                if (a == 1) {
                    intent.putExtra("message_type", 1);
                } else {
                    intent.putExtra("message_type", 2);
                }
            }
            intent.putExtra("tagalias_errorcode", C0558a.f697c);
            intent.putExtra("tagalias_seqid", j);
            this.f663a.sendBroadcast(intent);
        } catch (Throwable th) {
            C0582e.m1306c("JPushRequestHelper", "onTagaliasTimeout error:" + th.getMessage());
        }
    }
}
