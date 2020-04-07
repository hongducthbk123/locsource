package p004cn.jiguang.p015d.p020c;

import java.net.SocketTimeoutException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

/* renamed from: cn.jiguang.d.c.a */
class C0424a {

    /* renamed from: a */
    protected long f334a;

    /* renamed from: b */
    protected SelectionKey f335b;

    protected C0424a(SelectableChannel selectableChannel, long j) {
        Selector selector = null;
        this.f334a = j;
        try {
            selector = Selector.open();
            selectableChannel.configureBlocking(false);
            this.f335b = selectableChannel.register(selector, 1);
        } catch (Throwable th) {
            if (selector != null) {
                selector.close();
            }
            selectableChannel.close();
            throw th;
        }
    }

    /* renamed from: a */
    protected static void m513a(SelectionKey selectionKey, long j) {
        long currentTimeMillis = j - System.currentTimeMillis();
        int i = 0;
        if (currentTimeMillis > 0) {
            i = selectionKey.selector().select(currentTimeMillis);
        } else if (currentTimeMillis == 0) {
            i = selectionKey.selector().selectNow();
        }
        if (i == 0) {
            throw new SocketTimeoutException();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo6481a() {
        this.f335b.selector().close();
        this.f335b.channel().close();
    }
}
