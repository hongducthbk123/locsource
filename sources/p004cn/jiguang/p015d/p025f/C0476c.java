package p004cn.jiguang.p015d.p025f;

import com.btgame.googlepay.util.IabHelper;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import p004cn.jiguang.p015d.p022e.p023a.p024a.C0468c;
import p004cn.jiguang.p029e.C0501d;

/* renamed from: cn.jiguang.d.f.c */
public final class C0476c extends C0474a {

    /* renamed from: h */
    private ByteBuffer f482h = ByteBuffer.allocate(8192);

    /* renamed from: b */
    private boolean m762b(byte[] bArr) {
        try {
            if (!mo6613b() || bArr == null || bArr.length <= 0) {
                return false;
            }
            int write = this.f475b.write(ByteBuffer.wrap(bArr));
            return write > 0 || write >= 0;
        } catch (Exception e) {
            C0501d.m907c("NioSocketClient", "send data error:" + e.getMessage());
            mo6611a();
            return false;
        }
    }

    /* renamed from: a */
    public final synchronized int mo6608a(String str, int i) {
        int i2 = -991;
        synchronized (this) {
            super.mo6608a(str, i);
            try {
                this.f475b = SocketChannel.open();
                this.f477d = Selector.open();
                this.f475b.configureBlocking(false);
                this.f475b.connect(new InetSocketAddress(str, i));
                long currentTimeMillis = System.currentTimeMillis();
                while (true) {
                    if (!this.f475b.finishConnect()) {
                        if (this.f478e) {
                            if (System.currentTimeMillis() - currentTimeMillis > 3000) {
                                mo6611a();
                                i2 = -994;
                                break;
                            }
                        } else {
                            break;
                        }
                    } else if (this.f478e) {
                        this.f475b.register(this.f477d, 1);
                        i2 = 0;
                    }
                }
            } catch (Throwable th) {
                C0501d.m907c("NioSocketClient", "tcp connect has failed:" + th);
                i2 = th instanceof SocketTimeoutException ? -994 : -1000;
            }
        }
        return i2;
    }

    /* renamed from: a */
    public final int mo6609a(byte[] bArr) {
        return m762b(bArr) ? 0 : 103;
    }

    /* renamed from: a */
    public final C0478e mo6610a(int i) {
        int i2;
        if (!mo6613b()) {
            return new C0478e(-991, "recv error,the connect is invalid");
        }
        C0468c c = mo6614c();
        if (c != null) {
            ByteBuffer b = mo6612b(c.mo6597f());
            if (b != null) {
                return new C0478e(0, b);
            }
        }
        int i3 = 1048576;
        while (mo6613b() && this.f476c < i3) {
            try {
                if ((i > 0 ? this.f477d.select((long) i) : this.f477d.select()) != 0) {
                    Iterator it = this.f477d.selectedKeys().iterator();
                    while (it.hasNext()) {
                        SelectionKey selectionKey = (SelectionKey) it.next();
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        if (selectionKey.isReadable()) {
                            int read = socketChannel.read(this.f482h);
                            if (read < 0) {
                                return new C0478e(-996, "read len < 0:" + read);
                            }
                            this.f482h.flip();
                            int limit = this.f482h.limit();
                            if (this.f474a.remaining() < limit) {
                                return new C0478e(-996, "the total buf remaining less than readLen,remaining:" + this.f474a.remaining() + ",readLen:" + limit);
                            }
                            this.f474a.put(this.f482h);
                            this.f476c += limit;
                            this.f482h.compact();
                            if (this.f476c >= 20) {
                                C0468c c2 = mo6614c();
                                if (c2 != null) {
                                    i2 = c2.mo6597f();
                                    i3 = i2;
                                }
                            }
                            i2 = i3;
                            i3 = i2;
                        } else {
                            selectionKey.isWritable();
                        }
                        it.remove();
                    }
                    continue;
                } else if (i > 0) {
                    return new C0478e(-994, "recv time out");
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                C0478e eVar = new C0478e(-997, th2.getMessage());
                if (!(th2 instanceof SocketTimeoutException)) {
                    return eVar;
                }
                eVar.mo6618a(-994);
                return eVar;
            }
        }
        if (i3 == 1048576) {
            return new C0478e(-997, "recv empty data or tcp has close");
        }
        ByteBuffer b2 = mo6612b(i3);
        return b2 != null ? new C0478e(0, b2) : new C0478e((int) IabHelper.IABHELPER_REMOTE_EXCEPTION, "parse error");
    }

    /* renamed from: a */
    public final void mo6611a() {
        super.mo6611a();
        if (this.f477d != null) {
            try {
                this.f477d.close();
            } catch (IOException e) {
            }
        }
        if (this.f475b != null) {
            try {
                this.f475b.close();
            } catch (Exception e2) {
            }
        }
        this.f475b = null;
    }
}
