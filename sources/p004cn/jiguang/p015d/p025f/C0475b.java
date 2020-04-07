package p004cn.jiguang.p015d.p025f;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import p004cn.jiguang.p029e.C0501d;

/* renamed from: cn.jiguang.d.f.b */
public final class C0475b extends Handler {

    /* renamed from: a */
    final /* synthetic */ C0474a f481a;

    public C0475b(C0474a aVar, Looper looper) {
        this.f481a = aVar;
        super(looper);
    }

    public final void handleMessage(Message message) {
        byte[] bArr;
        try {
            bArr = (byte[]) message.obj;
        } catch (Exception e) {
            C0501d.m907c("BaseSocket", "#unexcepted - get send data failed e:" + e.getMessage());
            bArr = null;
        }
        this.f481a.mo6609a(bArr);
    }
}
