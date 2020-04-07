package p004cn.jiguang.p015d.p021d;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import com.facebook.internal.NativeProtocol;
import java.nio.ByteBuffer;
import p004cn.jiguang.api.JAction;
import p004cn.jiguang.api.JResponse;
import p004cn.jiguang.p005a.p006a.p009c.C0358e;
import p004cn.jiguang.p005a.p012c.C0375a;
import p004cn.jiguang.p005a.p012c.C0376b;
import p004cn.jiguang.p015d.C0385a;
import p004cn.jiguang.p015d.p017b.C0417d;
import p004cn.jiguang.p015d.p022e.p023a.p024a.C0466a;
import p004cn.jiguang.p015d.p022e.p023a.p024a.C0468c;
import p004cn.jiguang.p029e.C0501d;

/* renamed from: cn.jiguang.d.d.f */
public class C0449f implements JAction {
    public long dispatchMessage(Context context, long j, int i, Object obj, ByteBuffer byteBuffer) {
        JResponse a = C0466a.m731a((C0468c) obj, byteBuffer);
        if (a != null) {
            if (a.code == 0) {
                switch (a.getCommand()) {
                    case 19:
                        C0417d.m446a().mo6439a(a, j);
                        break;
                    case 25:
                        C0375a.m171a(context, C0417d.m446a().mo6443b(), j, a);
                        break;
                    case 26:
                        if (a.code != 0) {
                            C0448e.m641a().mo6557a(context, a.getRid().longValue());
                            break;
                        } else {
                            C0448e.m641a().mo6559b(context, a.getRid().longValue());
                            break;
                        }
                }
            } else {
                C0501d.m907c("JCoreAction", "Received error response - code:" + a.code);
            }
        }
        if (a != null) {
            return a.getHead().mo6592b().longValue();
        }
        return -1;
    }

    public void dispatchTimeOutMessage(Context context, long j, long j2, int i) {
        if (i == 26) {
            C0448e.m641a().mo6562c(context, j2);
        }
    }

    public IBinder getBinderByType(String str) {
        return null;
    }

    public String getSdkVersion() {
        return "1.2.0";
    }

    public void handleMessage(Context context, long j, Object obj) {
    }

    public boolean isSupportedCMD(int i) {
        return i == 0 || i == 1 || i == 19 || i == 25 || i == 26;
    }

    public void onActionRun(Context context, long j, Bundle bundle, Object obj) {
        C0376b.m179a();
        C0501d.m903a("ARunAction", " pkg:" + C0385a.f196c);
        C0501d.m903a("ARunAction", new StringBuilder("bundle:").append(bundle).toString() != null ? bundle.toString() : "");
        if (bundle != null) {
            if ("cn.jpush.android.intent.REPORT".equals(bundle.getString(NativeProtocol.WEB_DIALOG_ACTION))) {
                String string = bundle.getString("report");
                bundle.getString("report.extra.info");
                C0358e.m113a(context, string);
            }
        }
    }

    public void onEvent(Context context, long j, int i) {
    }
}
