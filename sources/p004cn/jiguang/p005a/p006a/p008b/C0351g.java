package p004cn.jiguang.p005a.p006a.p008b;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* renamed from: cn.jiguang.a.a.b.g */
final class C0351g extends Handler {

    /* renamed from: a */
    final /* synthetic */ C0350f f100a;

    public C0351g(C0350f fVar, Looper looper) {
        this.f100a = fVar;
        super(looper);
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1000:
                if (!this.f100a.f96k) {
                    this.f100a.f96k = true;
                    C0350f.m80b(this.f100a);
                    C0350f.m82c(this.f100a);
                    return;
                }
                return;
            case 1001:
            case 1003:
            case 1004:
            case 1005:
                if (this.f100a.f98m != null) {
                    this.f100a.f98m.mo6210a(message);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
