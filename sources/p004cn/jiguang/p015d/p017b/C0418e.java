package p004cn.jiguang.p015d.p017b;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import org.cocos2dx.lib.GameControllerDelegate;
import p004cn.jiguang.p005a.p012c.C0377c;
import p004cn.jiguang.p015d.C0385a;
import p004cn.jiguang.p015d.p021d.C0445b;
import p004cn.jiguang.p015d.p021d.C0446c;
import p004cn.jiguang.p015d.p021d.C0448e;
import p004cn.jiguang.p029e.C0501d;
import p004cn.jiguang.p031g.C0506a;
import p004cn.jiguang.p031g.C0530k;

/* renamed from: cn.jiguang.d.b.e */
final class C0418e extends Handler {

    /* renamed from: a */
    final /* synthetic */ C0417d f299a;

    public C0418e(C0417d dVar, Looper looper) {
        this.f299a = dVar;
        super(looper);
    }

    public final void handleMessage(Message message) {
        super.handleMessage(message);
        String str = "";
        try {
            Bundle data = message.getData();
            if (data != null) {
                str = data.getString("sdktype");
            }
            if (C0530k.m1099a(str) || str.equals(C0385a.f194a)) {
                switch (message.what) {
                    case 1001:
                        C0506a.m976j(this.f299a.f294h.getApplicationContext());
                        C0446c.m635a(this.f299a.f294h, false);
                        return;
                    case 1002:
                        C0377c.m184a(this.f299a.f294h, false);
                        return;
                    case 1003:
                        C0417d.m451b(this.f299a);
                        return;
                    case 1004:
                        C0417d.m449a(this.f299a, true);
                        return;
                    case 1005:
                        C0417d.m449a(this.f299a, false);
                        return;
                    case 1006:
                        removeMessages(1011);
                        removeMessages(1010);
                        sendEmptyMessageDelayed(1011, 3000);
                        return;
                    case 1007:
                        sendEmptyMessageDelayed(1010, 200);
                        return;
                    case 1010:
                        if (this.f299a.f288a != null) {
                            this.f299a.f288a.mo6453c();
                            return;
                        }
                        return;
                    case 1011:
                        this.f299a.mo6448e();
                        return;
                    case GameControllerDelegate.BUTTON_SELECT /*1022*/:
                        C0417d.m456d(this.f299a);
                        return;
                    case 1031:
                        C0506a.m976j(this.f299a.f294h.getApplicationContext());
                        return;
                    case 1032:
                        C0448e.m641a().mo6556a(this.f299a.f294h);
                        return;
                    case 7301:
                        C0417d.m452b(this.f299a, message.getData().getLong("connection"));
                        return;
                    case 7303:
                        C0417d.m455c(this.f299a, message.getData().getLong("connection"));
                        return;
                    case 7304:
                        C0417d.m447a(this.f299a, message.getData().getLong("connection"));
                        return;
                    case 7306:
                        C0501d.m903a("JiguangTcpManager", "onLoginFailed - connection:" + message.getData().getLong("connection") + ", respCode:" + message.arg2);
                        return;
                    case 7307:
                        C0419f.f301b.set(false);
                        return;
                    case 7401:
                        Bundle data2 = message.getData();
                        if (data2 == null) {
                            C0501d.m907c("JiguangTcpManager", "Unexpected - want to send null request.");
                            return;
                        }
                        int i = data2.getInt("request_timeout");
                        C0420g.m485a().mo6464b(data2.getByteArray("request_data"), data2.getString("request_sdktype"), i);
                        return;
                    case 7402:
                        Bundle data3 = message.getData();
                        if (data3 == null) {
                            C0501d.m907c("JiguangTcpManager", "Unexpected - msg response bundle is null.");
                            return;
                        }
                        C0420g.m485a().mo6457a(message.getData().getLong("connection"), data3.getString("request_sdktype"), message.obj);
                        return;
                    case 7403:
                        if (message.obj != null && (message.obj instanceof C0421h)) {
                            C0420g.m485a().mo6463b((C0421h) message.obj);
                            return;
                        }
                        return;
                    case 7404:
                        if (message.obj != null && (message.obj instanceof C0421h)) {
                            C0420g.m485a().mo6459a((C0421h) message.obj);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            } else {
                C0445b.m618a();
                C0445b.m623a(this.f299a.f294h, str, (Object) data);
            }
        } catch (Exception e) {
        }
    }
}
