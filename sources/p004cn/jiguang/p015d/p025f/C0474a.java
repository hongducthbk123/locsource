package p004cn.jiguang.p015d.p025f;

import android.os.Handler;
import android.os.HandlerThread;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import p004cn.jiguang.p015d.p022e.p023a.p024a.C0468c;

/* renamed from: cn.jiguang.d.f.a */
public abstract class C0474a {

    /* renamed from: a */
    protected ByteBuffer f474a = ByteBuffer.allocate(20480);

    /* renamed from: b */
    protected SocketChannel f475b;

    /* renamed from: c */
    protected int f476c;

    /* renamed from: d */
    protected Selector f477d;

    /* renamed from: e */
    protected boolean f478e = false;

    /* renamed from: f */
    protected SimpleDateFormat f479f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /* renamed from: g */
    protected Handler f480g;

    /* renamed from: a */
    public int mo6608a(String str, int i) {
        if (this.f480g == null) {
            HandlerThread handlerThread = new HandlerThread("socketSendHandler");
            handlerThread.start();
            this.f480g = new C0475b(this, handlerThread.getLooper());
        }
        if (this.f474a == null) {
            this.f474a = ByteBuffer.allocate(20480);
        }
        this.f474a.clear();
        this.f476c = 0;
        this.f478e = true;
        return 0;
    }

    /* renamed from: a */
    public abstract int mo6609a(byte[] bArr);

    /* renamed from: a */
    public abstract C0478e mo6610a(int i);

    /* renamed from: a */
    public void mo6611a() {
        mo6613b();
        if (this.f480g != null) {
            this.f480g.removeCallbacksAndMessages(null);
            try {
                this.f480g.getLooper().quit();
            } catch (Exception e) {
            }
            this.f480g = null;
        }
        this.f478e = false;
        if (this.f474a != null) {
            this.f474a.clear();
        }
        this.f476c = 0;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final ByteBuffer mo6612b(int i) {
        if (this.f476c < i) {
            return null;
        }
        this.f476c -= i;
        byte[] bArr = new byte[i];
        this.f474a.flip();
        this.f474a.get(bArr, 0, i);
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        this.f474a.compact();
        return wrap;
    }

    /* renamed from: b */
    public final boolean mo6613b() {
        return this.f478e && this.f475b != null && this.f475b.isConnected();
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public final C0468c mo6614c() {
        if (this.f476c < 20) {
            return null;
        }
        ByteBuffer allocate = ByteBuffer.allocate(20);
        allocate.put(this.f474a.array(), 0, 20);
        return new C0468c(false, allocate.array());
    }
}
