package p004cn.jiguang.p005a.p006a.p007a;

import android.os.Handler.Callback;
import android.os.Message;

/* renamed from: cn.jiguang.a.a.a.e */
final class C0339e implements Callback {

    /* renamed from: a */
    final /* synthetic */ C0338d f39a;

    C0339e(C0338d dVar) {
        this.f39a = dVar;
    }

    public final boolean handleMessage(Message message) {
        if (message != null && message.what == 1) {
            Thread thread = (Thread) message.obj;
            if (thread != null) {
                thread.interrupt();
            }
            if (message.getData() != null) {
                message.getData().getString("ip");
            }
        }
        return false;
    }
}
