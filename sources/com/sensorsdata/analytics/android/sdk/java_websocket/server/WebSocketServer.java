package com.sensorsdata.analytics.android.sdk.java_websocket.server;

import com.sensorsdata.analytics.android.sdk.java_websocket.WebSocket;
import com.sensorsdata.analytics.android.sdk.java_websocket.WebSocketAdapter;
import com.sensorsdata.analytics.android.sdk.java_websocket.WebSocketFactory;
import com.sensorsdata.analytics.android.sdk.java_websocket.WebSocketImpl;
import com.sensorsdata.analytics.android.sdk.java_websocket.drafts.Draft;
import com.sensorsdata.analytics.android.sdk.java_websocket.exceptions.InvalidDataException;
import com.sensorsdata.analytics.android.sdk.java_websocket.framing.Framedata;
import com.sensorsdata.analytics.android.sdk.java_websocket.handshake.ClientHandshake;
import com.sensorsdata.analytics.android.sdk.java_websocket.handshake.Handshakedata;
import com.sensorsdata.analytics.android.sdk.java_websocket.handshake.ServerHandshakeBuilder;
import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class WebSocketServer extends WebSocketAdapter implements Runnable {
    static final /* synthetic */ boolean $assertionsDisabled = (!WebSocketServer.class.desiredAssertionStatus());
    public static int DECODERS = Runtime.getRuntime().availableProcessors();
    private final InetSocketAddress address;
    private BlockingQueue<ByteBuffer> buffers;
    private final Collection<WebSocket> connections;
    private List<WebSocketWorker> decoders;
    private List<Draft> drafts;
    private List<WebSocketImpl> iqueue;
    private final AtomicBoolean isclosed;
    private int queueinvokes;
    private final AtomicInteger queuesize;
    private Selector selector;
    private Thread selectorthread;
    private ServerSocketChannel server;
    private WebSocketServerFactory wsf;

    public interface WebSocketServerFactory extends WebSocketFactory {
        WebSocketImpl createWebSocket(WebSocketAdapter webSocketAdapter, Draft draft, Socket socket);

        WebSocketImpl createWebSocket(WebSocketAdapter webSocketAdapter, List<Draft> list, Socket socket);

        ByteChannel wrapChannel(SocketChannel socketChannel, SelectionKey selectionKey) throws IOException;
    }

    public class WebSocketWorker extends Thread {
        static final /* synthetic */ boolean $assertionsDisabled = (!WebSocketServer.class.desiredAssertionStatus());
        private BlockingQueue<WebSocketImpl> iqueue = new LinkedBlockingQueue();

        public WebSocketWorker() {
            setName("WebSocketWorker-" + getId());
            setUncaughtExceptionHandler(new UncaughtExceptionHandler(WebSocketServer.this) {
                public void uncaughtException(Thread t, Throwable e) {
                    Thread.getDefaultUncaughtExceptionHandler().uncaughtException(t, e);
                }
            });
        }

        public void put(WebSocketImpl ws) throws InterruptedException {
            this.iqueue.put(ws);
        }

        /* JADX INFO: finally extract failed */
        public void run() {
            WebSocketImpl ws = null;
            while (true) {
                try {
                    ws = (WebSocketImpl) this.iqueue.take();
                    ByteBuffer buf = (ByteBuffer) ws.inQueue.poll();
                    if ($assertionsDisabled || buf != null) {
                        try {
                            ws.decode(buf);
                            WebSocketServer.this.pushBuffer(buf);
                        } catch (Exception e) {
                            System.err.println("Error while reading from remote connection: " + e);
                            WebSocketServer.this.pushBuffer(buf);
                        } catch (Throwable th) {
                            WebSocketServer.this.pushBuffer(buf);
                            throw th;
                        }
                    } else {
                        throw new AssertionError();
                    }
                } catch (InterruptedException e2) {
                    return;
                } catch (RuntimeException e3) {
                    WebSocketServer.this.handleFatal(ws, e3);
                    return;
                }
            }
        }
    }

    public abstract void onClose(WebSocket webSocket, int i, String str, boolean z);

    public abstract void onError(WebSocket webSocket, Exception exc);

    public abstract void onMessage(WebSocket webSocket, String str);

    public abstract void onOpen(WebSocket webSocket, ClientHandshake clientHandshake);

    public WebSocketServer() throws UnknownHostException {
        this(new InetSocketAddress(80), DECODERS, null);
    }

    public WebSocketServer(InetSocketAddress address2) {
        this(address2, DECODERS, null);
    }

    public WebSocketServer(InetSocketAddress address2, int decoders2) {
        this(address2, decoders2, null);
    }

    public WebSocketServer(InetSocketAddress address2, List<Draft> drafts2) {
        this(address2, DECODERS, drafts2);
    }

    public WebSocketServer(InetSocketAddress address2, int decodercount, List<Draft> drafts2) {
        this(address2, decodercount, drafts2, new HashSet());
    }

    public WebSocketServer(InetSocketAddress address2, int decodercount, List<Draft> drafts2, Collection<WebSocket> connectionscontainer) {
        this.isclosed = new AtomicBoolean(false);
        this.queueinvokes = 0;
        this.queuesize = new AtomicInteger(0);
        this.wsf = new DefaultWebSocketServerFactory();
        if (address2 == null || decodercount < 1 || connectionscontainer == null) {
            throw new IllegalArgumentException("address and connectionscontainer must not be null and you need at least 1 decoder");
        }
        if (drafts2 == null) {
            this.drafts = Collections.emptyList();
        } else {
            this.drafts = drafts2;
        }
        this.address = address2;
        this.connections = connectionscontainer;
        this.iqueue = new LinkedList();
        this.decoders = new ArrayList(decodercount);
        this.buffers = new LinkedBlockingQueue();
        for (int i = 0; i < decodercount; i++) {
            WebSocketWorker ex = new WebSocketWorker();
            this.decoders.add(ex);
            ex.start();
        }
    }

    public void start() {
        if (this.selectorthread != null) {
            throw new IllegalStateException(getClass().getName() + " can only be started once.");
        }
        new Thread(this).start();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001f, code lost:
        if (r3.hasNext() == false) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0021, code lost:
        ((com.sensorsdata.analytics.android.sdk.java_websocket.WebSocket) r3.next()).close(1001);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0030, code lost:
        monitor-enter(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0033, code lost:
        if (r6.selectorthread == null) goto L_0x004d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003b, code lost:
        if (r6.selectorthread == java.lang.Thread.currentThread()) goto L_0x004d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003d, code lost:
        r6.selector.wakeup();
        r6.selectorthread.interrupt();
        r6.selectorthread.join((long) r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004d, code lost:
        monitor-exit(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0017, code lost:
        r3 = r1.iterator();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void stop(int r7) throws java.lang.InterruptedException {
        /*
            r6 = this;
            java.util.concurrent.atomic.AtomicBoolean r3 = r6.isclosed
            r4 = 0
            r5 = 1
            boolean r3 = r3.compareAndSet(r4, r5)
            if (r3 != 0) goto L_0x000b
        L_0x000a:
            return
        L_0x000b:
            r0 = 0
            java.util.Collection<com.sensorsdata.analytics.android.sdk.java_websocket.WebSocket> r4 = r6.connections
            monitor-enter(r4)
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x002d }
            java.util.Collection<com.sensorsdata.analytics.android.sdk.java_websocket.WebSocket> r3 = r6.connections     // Catch:{ all -> 0x002d }
            r1.<init>(r3)     // Catch:{ all -> 0x002d }
            monitor-exit(r4)     // Catch:{ all -> 0x0052 }
            java.util.Iterator r3 = r1.iterator()
        L_0x001b:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0030
            java.lang.Object r2 = r3.next()
            com.sensorsdata.analytics.android.sdk.java_websocket.WebSocket r2 = (com.sensorsdata.analytics.android.sdk.java_websocket.WebSocket) r2
            r4 = 1001(0x3e9, float:1.403E-42)
            r2.close(r4)
            goto L_0x001b
        L_0x002d:
            r3 = move-exception
        L_0x002e:
            monitor-exit(r4)     // Catch:{ all -> 0x002d }
            throw r3
        L_0x0030:
            monitor-enter(r6)
            java.lang.Thread r3 = r6.selectorthread     // Catch:{ all -> 0x004f }
            if (r3 == 0) goto L_0x004d
            java.lang.Thread r3 = r6.selectorthread     // Catch:{ all -> 0x004f }
            java.lang.Thread r4 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x004f }
            if (r3 == r4) goto L_0x004d
            java.nio.channels.Selector r3 = r6.selector     // Catch:{ all -> 0x004f }
            r3.wakeup()     // Catch:{ all -> 0x004f }
            java.lang.Thread r3 = r6.selectorthread     // Catch:{ all -> 0x004f }
            r3.interrupt()     // Catch:{ all -> 0x004f }
            java.lang.Thread r3 = r6.selectorthread     // Catch:{ all -> 0x004f }
            long r4 = (long) r7     // Catch:{ all -> 0x004f }
            r3.join(r4)     // Catch:{ all -> 0x004f }
        L_0x004d:
            monitor-exit(r6)     // Catch:{ all -> 0x004f }
            goto L_0x000a
        L_0x004f:
            r3 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x004f }
            throw r3
        L_0x0052:
            r3 = move-exception
            r0 = r1
            goto L_0x002e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sensorsdata.analytics.android.sdk.java_websocket.server.WebSocketServer.stop(int):void");
    }

    public void stop() throws IOException, InterruptedException {
        stop(0);
    }

    public Collection<WebSocket> connections() {
        return this.connections;
    }

    public InetSocketAddress getAddress() {
        return this.address;
    }

    public int getPort() {
        int port = getAddress().getPort();
        if (port != 0 || this.server == null) {
            return port;
        }
        return this.server.socket().getLocalPort();
    }

    public List<Draft> getDraft() {
        return Collections.unmodifiableList(this.drafts);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:101:?, code lost:
        pushBuffer(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x021b, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0220, code lost:
        if (r18.decoders != null) goto L_0x0222;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0222, code lost:
        r14 = r18.decoders.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x022e, code lost:
        if (r14.hasNext() != false) goto L_0x0230;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0230, code lost:
        ((com.sensorsdata.analytics.android.sdk.java_websocket.server.WebSocketServer.WebSocketWorker) r14.next()).interrupt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0242, code lost:
        if (r18.iqueue.isEmpty() != false) goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x0244, code lost:
        r5 = (com.sensorsdata.analytics.android.sdk.java_websocket.WebSocketImpl) r18.iqueue.remove(0);
        r3 = (com.sensorsdata.analytics.android.sdk.java_websocket.WrappedByteChannel) r5.channel;
        r2 = takeBuffer();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x025d, code lost:
        if (com.sensorsdata.analytics.android.sdk.java_websocket.SocketChannelIOHelper.readMore(r2, r5, r3) == false) goto L_0x0266;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x025f, code lost:
        r18.iqueue.add(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x026a, code lost:
        if (r2.hasRemaining() == false) goto L_0x027e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x026c, code lost:
        r5.inQueue.put(r2);
        queue(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0277, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:?, code lost:
        pushBuffer(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x027d, code lost:
        throw r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:?, code lost:
        pushBuffer(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0288, code lost:
        if (r18.server != null) goto L_0x028a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:?, code lost:
        r18.server.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x0293, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x0294, code lost:
        onError(null, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x02a0, code lost:
        if (r18.server != null) goto L_0x02a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:?, code lost:
        r18.server.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x02ab, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x02ac, code lost:
        onError(null, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x02b8, code lost:
        if (r18.decoders == null) goto L_0x02d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x02ba, code lost:
        r13 = r18.decoders.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x02c6, code lost:
        if (r13.hasNext() == false) goto L_0x02d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x02c8, code lost:
        ((com.sensorsdata.analytics.android.sdk.java_websocket.server.WebSocketServer.WebSocketWorker) r13.next()).interrupt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x02d6, code lost:
        if (r18.server == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:?, code lost:
        r18.server.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x02e1, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x02e2, code lost:
        onError(null, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x02ee, code lost:
        if (r18.server != null) goto L_0x02f0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0040, code lost:
        r18.selectorthread.setName("WebsocketSelector" + r18.selectorthread.getId());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:?, code lost:
        r18.server.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x02f9, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x02fa, code lost:
        onError(null, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x0306, code lost:
        if (r18.server != null) goto L_0x0308;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:?, code lost:
        r18.server.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x030f, code lost:
        throw r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x0310, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x0311, code lost:
        onError(null, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r18.server = java.nio.channels.ServerSocketChannel.open();
        r18.server.configureBlocking(false);
        r11 = r18.server.socket();
        r11.setReceiveBufferSize(com.sensorsdata.analytics.android.sdk.java_websocket.WebSocketImpl.RCVBUF);
        r11.bind(r18.address);
        r18.selector = java.nio.channels.Selector.open();
        r18.server.register(r18.selector, r18.server.validOps());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:206:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00ab, code lost:
        if (r18.selectorthread.isInterrupted() != false) goto L_0x02b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00ad, code lost:
        r9 = null;
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r18.selector.select();
        r8 = r18.selector.selectedKeys().iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00c6, code lost:
        if (r8.hasNext() == false) goto L_0x023a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00c8, code lost:
        r9 = (java.nio.channels.SelectionKey) r8.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00d4, code lost:
        if (r9.isValid() == false) goto L_0x00c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00da, code lost:
        if (r9.isAcceptable() == false) goto L_0x0150;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00e2, code lost:
        if (onConnect(r9) != false) goto L_0x00f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00e4, code lost:
        r9.cancel();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ea, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00eb, code lost:
        handleFatal(null, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        r4 = r18.server.accept();
        r4.configureBlocking(false);
        r12 = r18.wsf.createWebSocket((com.sensorsdata.analytics.android.sdk.java_websocket.WebSocketAdapter) r18, r18.drafts, r4.socket());
        r12.key = r4.register(r18.selector, 1, r12);
        r12.channel = r18.wsf.wrapChannel(r4, r12.key);
        r8.remove();
        allocateBuffers(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0136, code lost:
        if (r18.decoders != null) goto L_0x0138;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0138, code lost:
        r13 = r18.decoders.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0144, code lost:
        if (r13.hasNext() != false) goto L_0x0146;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0146, code lost:
        ((com.sensorsdata.analytics.android.sdk.java_websocket.server.WebSocketServer.WebSocketWorker) r13.next()).interrupt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0154, code lost:
        if (r9.isReadable() == false) goto L_0x0194;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0156, code lost:
        r5 = (com.sensorsdata.analytics.android.sdk.java_websocket.WebSocketImpl) r9.attachment();
        r2 = takeBuffer();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0168, code lost:
        if (com.sensorsdata.analytics.android.sdk.java_websocket.SocketChannelIOHelper.read(r2, r5, r5.channel) == false) goto L_0x0214;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x016e, code lost:
        if (r2.hasRemaining() == false) goto L_0x01e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0170, code lost:
        r5.inQueue.put(r2);
        queue(r5);
        r8.remove();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0181, code lost:
        if ((r5.channel instanceof com.sensorsdata.analytics.android.sdk.java_websocket.WrappedByteChannel) == false) goto L_0x0194;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x018b, code lost:
        if (((com.sensorsdata.analytics.android.sdk.java_websocket.WrappedByteChannel) r5.channel).isNeedRead() == false) goto L_0x0194;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x018d, code lost:
        r18.iqueue.add(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0198, code lost:
        if (r9.isWritable() == false) goto L_0x00c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x019a, code lost:
        r5 = (com.sensorsdata.analytics.android.sdk.java_websocket.WebSocketImpl) r9.attachment();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x01a8, code lost:
        if (com.sensorsdata.analytics.android.sdk.java_websocket.SocketChannelIOHelper.batch(r5, r5.channel) == false) goto L_0x00c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01ae, code lost:
        if (r9.isValid() == false) goto L_0x00c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01b0, code lost:
        r9.interestOps(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x01b6, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x01b7, code lost:
        if (r9 != null) goto L_0x01b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:?, code lost:
        r9.cancel();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01bc, code lost:
        handleIOException(r9, r5, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01c3, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:?, code lost:
        handleFatal(null, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01ce, code lost:
        if (r18.decoders != null) goto L_0x01d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01d0, code lost:
        r13 = r18.decoders.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01dc, code lost:
        if (r13.hasNext() != false) goto L_0x01de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01de, code lost:
        ((com.sensorsdata.analytics.android.sdk.java_websocket.server.WebSocketServer.WebSocketWorker) r13.next()).interrupt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:?, code lost:
        pushBuffer(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01ee, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:?, code lost:
        pushBuffer(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01f4, code lost:
        throw r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01fa, code lost:
        if (r18.decoders != null) goto L_0x01fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01fc, code lost:
        r13 = r18.decoders.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0208, code lost:
        if (r13.hasNext() != false) goto L_0x020a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x020a, code lost:
        ((com.sensorsdata.analytics.android.sdk.java_websocket.server.WebSocketServer.WebSocketWorker) r13.next()).interrupt();
     */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00e8 A[ExcHandler: CancelledKeyException (e java.nio.channels.CancelledKeyException), Splitter:B:23:0x00b1] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0131 A[ExcHandler: ClosedByInterruptException (e java.nio.channels.ClosedByInterruptException), Splitter:B:23:0x00b1] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01f5 A[ExcHandler: InterruptedException (e java.lang.InterruptedException), Splitter:B:23:0x00b1] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r18 = this;
            monitor-enter(r18)
            r0 = r18
            java.lang.Thread r13 = r0.selectorthread     // Catch:{ all -> 0x0028 }
            if (r13 == 0) goto L_0x002b
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0028 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ all -> 0x0028 }
            r14.<init>()     // Catch:{ all -> 0x0028 }
            java.lang.Class r15 = r18.getClass()     // Catch:{ all -> 0x0028 }
            java.lang.String r15 = r15.getName()     // Catch:{ all -> 0x0028 }
            java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ all -> 0x0028 }
            java.lang.String r15 = " can only be started once."
            java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ all -> 0x0028 }
            java.lang.String r14 = r14.toString()     // Catch:{ all -> 0x0028 }
            r13.<init>(r14)     // Catch:{ all -> 0x0028 }
            throw r13     // Catch:{ all -> 0x0028 }
        L_0x0028:
            r13 = move-exception
            monitor-exit(r18)     // Catch:{ all -> 0x0028 }
            throw r13
        L_0x002b:
            java.lang.Thread r13 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0028 }
            r0 = r18
            r0.selectorthread = r13     // Catch:{ all -> 0x0028 }
            r0 = r18
            java.util.concurrent.atomic.AtomicBoolean r13 = r0.isclosed     // Catch:{ all -> 0x0028 }
            boolean r13 = r13.get()     // Catch:{ all -> 0x0028 }
            if (r13 == 0) goto L_0x003f
            monitor-exit(r18)     // Catch:{ all -> 0x0028 }
        L_0x003e:
            return
        L_0x003f:
            monitor-exit(r18)     // Catch:{ all -> 0x0028 }
            r0 = r18
            java.lang.Thread r13 = r0.selectorthread
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = "WebsocketSelector"
            java.lang.StringBuilder r14 = r14.append(r15)
            r0 = r18
            java.lang.Thread r15 = r0.selectorthread
            long r16 = r15.getId()
            r0 = r16
            java.lang.StringBuilder r14 = r14.append(r0)
            java.lang.String r14 = r14.toString()
            r13.setName(r14)
            java.nio.channels.ServerSocketChannel r13 = java.nio.channels.ServerSocketChannel.open()     // Catch:{ IOException -> 0x00ea }
            r0 = r18
            r0.server = r13     // Catch:{ IOException -> 0x00ea }
            r0 = r18
            java.nio.channels.ServerSocketChannel r13 = r0.server     // Catch:{ IOException -> 0x00ea }
            r14 = 0
            r13.configureBlocking(r14)     // Catch:{ IOException -> 0x00ea }
            r0 = r18
            java.nio.channels.ServerSocketChannel r13 = r0.server     // Catch:{ IOException -> 0x00ea }
            java.net.ServerSocket r11 = r13.socket()     // Catch:{ IOException -> 0x00ea }
            int r13 = com.sensorsdata.analytics.android.sdk.java_websocket.WebSocketImpl.RCVBUF     // Catch:{ IOException -> 0x00ea }
            r11.setReceiveBufferSize(r13)     // Catch:{ IOException -> 0x00ea }
            r0 = r18
            java.net.InetSocketAddress r13 = r0.address     // Catch:{ IOException -> 0x00ea }
            r11.bind(r13)     // Catch:{ IOException -> 0x00ea }
            java.nio.channels.Selector r13 = java.nio.channels.Selector.open()     // Catch:{ IOException -> 0x00ea }
            r0 = r18
            r0.selector = r13     // Catch:{ IOException -> 0x00ea }
            r0 = r18
            java.nio.channels.ServerSocketChannel r13 = r0.server     // Catch:{ IOException -> 0x00ea }
            r0 = r18
            java.nio.channels.Selector r14 = r0.selector     // Catch:{ IOException -> 0x00ea }
            r0 = r18
            java.nio.channels.ServerSocketChannel r15 = r0.server     // Catch:{ IOException -> 0x00ea }
            int r15 = r15.validOps()     // Catch:{ IOException -> 0x00ea }
            r13.register(r14, r15)     // Catch:{ IOException -> 0x00ea }
        L_0x00a3:
            r0 = r18
            java.lang.Thread r13 = r0.selectorthread     // Catch:{ RuntimeException -> 0x01c3 }
            boolean r13 = r13.isInterrupted()     // Catch:{ RuntimeException -> 0x01c3 }
            if (r13 != 0) goto L_0x02b4
            r9 = 0
            r5 = 0
            r0 = r18
            java.nio.channels.Selector r13 = r0.selector     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            r13.select()     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            r0 = r18
            java.nio.channels.Selector r13 = r0.selector     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            java.util.Set r10 = r13.selectedKeys()     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            java.util.Iterator r8 = r10.iterator()     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
        L_0x00c2:
            boolean r13 = r8.hasNext()     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            if (r13 == 0) goto L_0x023a
            java.lang.Object r13 = r8.next()     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            r0 = r13
            java.nio.channels.SelectionKey r0 = (java.nio.channels.SelectionKey) r0     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            r9 = r0
            boolean r13 = r9.isValid()     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            if (r13 == 0) goto L_0x00c2
            boolean r13 = r9.isAcceptable()     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            if (r13 == 0) goto L_0x0150
            r0 = r18
            boolean r13 = r0.onConnect(r9)     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            if (r13 != 0) goto L_0x00f3
            r9.cancel()     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            goto L_0x00c2
        L_0x00e8:
            r13 = move-exception
            goto L_0x00a3
        L_0x00ea:
            r7 = move-exception
            r13 = 0
            r0 = r18
            r0.handleFatal(r13, r7)
            goto L_0x003e
        L_0x00f3:
            r0 = r18
            java.nio.channels.ServerSocketChannel r13 = r0.server     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            java.nio.channels.SocketChannel r4 = r13.accept()     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            r13 = 0
            r4.configureBlocking(r13)     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            r0 = r18
            com.sensorsdata.analytics.android.sdk.java_websocket.server.WebSocketServer$WebSocketServerFactory r13 = r0.wsf     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            r0 = r18
            java.util.List<com.sensorsdata.analytics.android.sdk.java_websocket.drafts.Draft> r14 = r0.drafts     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            java.net.Socket r15 = r4.socket()     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            r0 = r18
            com.sensorsdata.analytics.android.sdk.java_websocket.WebSocketImpl r12 = r13.createWebSocket(r0, r14, r15)     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            r0 = r18
            java.nio.channels.Selector r13 = r0.selector     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            r14 = 1
            java.nio.channels.SelectionKey r13 = r4.register(r13, r14, r12)     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            r12.key = r13     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            r0 = r18
            com.sensorsdata.analytics.android.sdk.java_websocket.server.WebSocketServer$WebSocketServerFactory r13 = r0.wsf     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            java.nio.channels.SelectionKey r14 = r12.key     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            java.nio.channels.ByteChannel r13 = r13.wrapChannel(r4, r14)     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            r12.channel = r13     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            r8.remove()     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            r0 = r18
            r0.allocateBuffers(r12)     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            goto L_0x00c2
        L_0x0131:
            r6 = move-exception
            r0 = r18
            java.util.List<com.sensorsdata.analytics.android.sdk.java_websocket.server.WebSocketServer$WebSocketWorker> r13 = r0.decoders
            if (r13 == 0) goto L_0x0284
            r0 = r18
            java.util.List<com.sensorsdata.analytics.android.sdk.java_websocket.server.WebSocketServer$WebSocketWorker> r13 = r0.decoders
            java.util.Iterator r13 = r13.iterator()
        L_0x0140:
            boolean r14 = r13.hasNext()
            if (r14 == 0) goto L_0x0284
            java.lang.Object r12 = r13.next()
            com.sensorsdata.analytics.android.sdk.java_websocket.server.WebSocketServer$WebSocketWorker r12 = (com.sensorsdata.analytics.android.sdk.java_websocket.server.WebSocketServer.WebSocketWorker) r12
            r12.interrupt()
            goto L_0x0140
        L_0x0150:
            boolean r13 = r9.isReadable()     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            if (r13 == 0) goto L_0x0194
            java.lang.Object r13 = r9.attachment()     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            r0 = r13
            com.sensorsdata.analytics.android.sdk.java_websocket.WebSocketImpl r0 = (com.sensorsdata.analytics.android.sdk.java_websocket.WebSocketImpl) r0     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            r5 = r0
            java.nio.ByteBuffer r2 = r18.takeBuffer()     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            java.nio.channels.ByteChannel r13 = r5.channel     // Catch:{ IOException -> 0x01ee, CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, InterruptedException -> 0x01f5 }
            boolean r13 = com.sensorsdata.analytics.android.sdk.java_websocket.SocketChannelIOHelper.read(r2, r5, r13)     // Catch:{ IOException -> 0x01ee, CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, InterruptedException -> 0x01f5 }
            if (r13 == 0) goto L_0x0214
            boolean r13 = r2.hasRemaining()     // Catch:{ IOException -> 0x01ee, CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, InterruptedException -> 0x01f5 }
            if (r13 == 0) goto L_0x01e8
            java.util.concurrent.BlockingQueue<java.nio.ByteBuffer> r13 = r5.inQueue     // Catch:{ IOException -> 0x01ee, CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, InterruptedException -> 0x01f5 }
            r13.put(r2)     // Catch:{ IOException -> 0x01ee, CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, InterruptedException -> 0x01f5 }
            r0 = r18
            r0.queue(r5)     // Catch:{ IOException -> 0x01ee, CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, InterruptedException -> 0x01f5 }
            r8.remove()     // Catch:{ IOException -> 0x01ee, CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, InterruptedException -> 0x01f5 }
            java.nio.channels.ByteChannel r13 = r5.channel     // Catch:{ IOException -> 0x01ee, CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, InterruptedException -> 0x01f5 }
            boolean r13 = r13 instanceof com.sensorsdata.analytics.android.sdk.java_websocket.WrappedByteChannel     // Catch:{ IOException -> 0x01ee, CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, InterruptedException -> 0x01f5 }
            if (r13 == 0) goto L_0x0194
            java.nio.channels.ByteChannel r13 = r5.channel     // Catch:{ IOException -> 0x01ee, CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, InterruptedException -> 0x01f5 }
            com.sensorsdata.analytics.android.sdk.java_websocket.WrappedByteChannel r13 = (com.sensorsdata.analytics.android.sdk.java_websocket.WrappedByteChannel) r13     // Catch:{ IOException -> 0x01ee, CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, InterruptedException -> 0x01f5 }
            boolean r13 = r13.isNeedRead()     // Catch:{ IOException -> 0x01ee, CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, InterruptedException -> 0x01f5 }
            if (r13 == 0) goto L_0x0194
            r0 = r18
            java.util.List<com.sensorsdata.analytics.android.sdk.java_websocket.WebSocketImpl> r13 = r0.iqueue     // Catch:{ IOException -> 0x01ee, CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, InterruptedException -> 0x01f5 }
            r13.add(r5)     // Catch:{ IOException -> 0x01ee, CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, InterruptedException -> 0x01f5 }
        L_0x0194:
            boolean r13 = r9.isWritable()     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            if (r13 == 0) goto L_0x00c2
            java.lang.Object r13 = r9.attachment()     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            r0 = r13
            com.sensorsdata.analytics.android.sdk.java_websocket.WebSocketImpl r0 = (com.sensorsdata.analytics.android.sdk.java_websocket.WebSocketImpl) r0     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            r5 = r0
            java.nio.channels.ByteChannel r13 = r5.channel     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            boolean r13 = com.sensorsdata.analytics.android.sdk.java_websocket.SocketChannelIOHelper.batch(r5, r13)     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            if (r13 == 0) goto L_0x00c2
            boolean r13 = r9.isValid()     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            if (r13 == 0) goto L_0x00c2
            r13 = 1
            r9.interestOps(r13)     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            goto L_0x00c2
        L_0x01b6:
            r7 = move-exception
            if (r9 == 0) goto L_0x01bc
            r9.cancel()     // Catch:{ RuntimeException -> 0x01c3 }
        L_0x01bc:
            r0 = r18
            r0.handleIOException(r9, r5, r7)     // Catch:{ RuntimeException -> 0x01c3 }
            goto L_0x00a3
        L_0x01c3:
            r6 = move-exception
            r13 = 0
            r0 = r18
            r0.handleFatal(r13, r6)     // Catch:{ all -> 0x021b }
            r0 = r18
            java.util.List<com.sensorsdata.analytics.android.sdk.java_websocket.server.WebSocketServer$WebSocketWorker> r13 = r0.decoders
            if (r13 == 0) goto L_0x02ea
            r0 = r18
            java.util.List<com.sensorsdata.analytics.android.sdk.java_websocket.server.WebSocketServer$WebSocketWorker> r13 = r0.decoders
            java.util.Iterator r13 = r13.iterator()
        L_0x01d8:
            boolean r14 = r13.hasNext()
            if (r14 == 0) goto L_0x02ea
            java.lang.Object r12 = r13.next()
            com.sensorsdata.analytics.android.sdk.java_websocket.server.WebSocketServer$WebSocketWorker r12 = (com.sensorsdata.analytics.android.sdk.java_websocket.server.WebSocketServer.WebSocketWorker) r12
            r12.interrupt()
            goto L_0x01d8
        L_0x01e8:
            r0 = r18
            r0.pushBuffer(r2)     // Catch:{ IOException -> 0x01ee, CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, InterruptedException -> 0x01f5 }
            goto L_0x0194
        L_0x01ee:
            r6 = move-exception
            r0 = r18
            r0.pushBuffer(r2)     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            throw r6     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
        L_0x01f5:
            r6 = move-exception
            r0 = r18
            java.util.List<com.sensorsdata.analytics.android.sdk.java_websocket.server.WebSocketServer$WebSocketWorker> r13 = r0.decoders
            if (r13 == 0) goto L_0x029c
            r0 = r18
            java.util.List<com.sensorsdata.analytics.android.sdk.java_websocket.server.WebSocketServer$WebSocketWorker> r13 = r0.decoders
            java.util.Iterator r13 = r13.iterator()
        L_0x0204:
            boolean r14 = r13.hasNext()
            if (r14 == 0) goto L_0x029c
            java.lang.Object r12 = r13.next()
            com.sensorsdata.analytics.android.sdk.java_websocket.server.WebSocketServer$WebSocketWorker r12 = (com.sensorsdata.analytics.android.sdk.java_websocket.server.WebSocketServer.WebSocketWorker) r12
            r12.interrupt()
            goto L_0x0204
        L_0x0214:
            r0 = r18
            r0.pushBuffer(r2)     // Catch:{ IOException -> 0x01ee, CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, InterruptedException -> 0x01f5 }
            goto L_0x0194
        L_0x021b:
            r13 = move-exception
            r0 = r18
            java.util.List<com.sensorsdata.analytics.android.sdk.java_websocket.server.WebSocketServer$WebSocketWorker> r14 = r0.decoders
            if (r14 == 0) goto L_0x0302
            r0 = r18
            java.util.List<com.sensorsdata.analytics.android.sdk.java_websocket.server.WebSocketServer$WebSocketWorker> r14 = r0.decoders
            java.util.Iterator r14 = r14.iterator()
        L_0x022a:
            boolean r15 = r14.hasNext()
            if (r15 == 0) goto L_0x0302
            java.lang.Object r12 = r14.next()
            com.sensorsdata.analytics.android.sdk.java_websocket.server.WebSocketServer$WebSocketWorker r12 = (com.sensorsdata.analytics.android.sdk.java_websocket.server.WebSocketServer.WebSocketWorker) r12
            r12.interrupt()
            goto L_0x022a
        L_0x023a:
            r0 = r18
            java.util.List<com.sensorsdata.analytics.android.sdk.java_websocket.WebSocketImpl> r13 = r0.iqueue     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            boolean r13 = r13.isEmpty()     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            if (r13 != 0) goto L_0x00a3
            r0 = r18
            java.util.List<com.sensorsdata.analytics.android.sdk.java_websocket.WebSocketImpl> r13 = r0.iqueue     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            r14 = 0
            java.lang.Object r13 = r13.remove(r14)     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            r0 = r13
            com.sensorsdata.analytics.android.sdk.java_websocket.WebSocketImpl r0 = (com.sensorsdata.analytics.android.sdk.java_websocket.WebSocketImpl) r0     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            r5 = r0
            java.nio.channels.ByteChannel r3 = r5.channel     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            com.sensorsdata.analytics.android.sdk.java_websocket.WrappedByteChannel r3 = (com.sensorsdata.analytics.android.sdk.java_websocket.WrappedByteChannel) r3     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            java.nio.ByteBuffer r2 = r18.takeBuffer()     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            boolean r13 = com.sensorsdata.analytics.android.sdk.java_websocket.SocketChannelIOHelper.readMore(r2, r5, r3)     // Catch:{ IOException -> 0x0277, CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, InterruptedException -> 0x01f5 }
            if (r13 == 0) goto L_0x0266
            r0 = r18
            java.util.List<com.sensorsdata.analytics.android.sdk.java_websocket.WebSocketImpl> r13 = r0.iqueue     // Catch:{ IOException -> 0x0277, CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, InterruptedException -> 0x01f5 }
            r13.add(r5)     // Catch:{ IOException -> 0x0277, CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, InterruptedException -> 0x01f5 }
        L_0x0266:
            boolean r13 = r2.hasRemaining()     // Catch:{ IOException -> 0x0277, CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, InterruptedException -> 0x01f5 }
            if (r13 == 0) goto L_0x027e
            java.util.concurrent.BlockingQueue<java.nio.ByteBuffer> r13 = r5.inQueue     // Catch:{ IOException -> 0x0277, CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, InterruptedException -> 0x01f5 }
            r13.put(r2)     // Catch:{ IOException -> 0x0277, CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, InterruptedException -> 0x01f5 }
            r0 = r18
            r0.queue(r5)     // Catch:{ IOException -> 0x0277, CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, InterruptedException -> 0x01f5 }
            goto L_0x023a
        L_0x0277:
            r6 = move-exception
            r0 = r18
            r0.pushBuffer(r2)     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
            throw r6     // Catch:{ CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, IOException -> 0x01b6, InterruptedException -> 0x01f5 }
        L_0x027e:
            r0 = r18
            r0.pushBuffer(r2)     // Catch:{ IOException -> 0x0277, CancelledKeyException -> 0x00e8, ClosedByInterruptException -> 0x0131, InterruptedException -> 0x01f5 }
            goto L_0x023a
        L_0x0284:
            r0 = r18
            java.nio.channels.ServerSocketChannel r13 = r0.server
            if (r13 == 0) goto L_0x003e
            r0 = r18
            java.nio.channels.ServerSocketChannel r13 = r0.server     // Catch:{ IOException -> 0x0293 }
            r13.close()     // Catch:{ IOException -> 0x0293 }
            goto L_0x003e
        L_0x0293:
            r6 = move-exception
            r13 = 0
            r0 = r18
            r0.onError(r13, r6)
            goto L_0x003e
        L_0x029c:
            r0 = r18
            java.nio.channels.ServerSocketChannel r13 = r0.server
            if (r13 == 0) goto L_0x003e
            r0 = r18
            java.nio.channels.ServerSocketChannel r13 = r0.server     // Catch:{ IOException -> 0x02ab }
            r13.close()     // Catch:{ IOException -> 0x02ab }
            goto L_0x003e
        L_0x02ab:
            r6 = move-exception
            r13 = 0
            r0 = r18
            r0.onError(r13, r6)
            goto L_0x003e
        L_0x02b4:
            r0 = r18
            java.util.List<com.sensorsdata.analytics.android.sdk.java_websocket.server.WebSocketServer$WebSocketWorker> r13 = r0.decoders
            if (r13 == 0) goto L_0x02d2
            r0 = r18
            java.util.List<com.sensorsdata.analytics.android.sdk.java_websocket.server.WebSocketServer$WebSocketWorker> r13 = r0.decoders
            java.util.Iterator r13 = r13.iterator()
        L_0x02c2:
            boolean r14 = r13.hasNext()
            if (r14 == 0) goto L_0x02d2
            java.lang.Object r12 = r13.next()
            com.sensorsdata.analytics.android.sdk.java_websocket.server.WebSocketServer$WebSocketWorker r12 = (com.sensorsdata.analytics.android.sdk.java_websocket.server.WebSocketServer.WebSocketWorker) r12
            r12.interrupt()
            goto L_0x02c2
        L_0x02d2:
            r0 = r18
            java.nio.channels.ServerSocketChannel r13 = r0.server
            if (r13 == 0) goto L_0x003e
            r0 = r18
            java.nio.channels.ServerSocketChannel r13 = r0.server     // Catch:{ IOException -> 0x02e1 }
            r13.close()     // Catch:{ IOException -> 0x02e1 }
            goto L_0x003e
        L_0x02e1:
            r6 = move-exception
            r13 = 0
            r0 = r18
            r0.onError(r13, r6)
            goto L_0x003e
        L_0x02ea:
            r0 = r18
            java.nio.channels.ServerSocketChannel r13 = r0.server
            if (r13 == 0) goto L_0x003e
            r0 = r18
            java.nio.channels.ServerSocketChannel r13 = r0.server     // Catch:{ IOException -> 0x02f9 }
            r13.close()     // Catch:{ IOException -> 0x02f9 }
            goto L_0x003e
        L_0x02f9:
            r6 = move-exception
            r13 = 0
            r0 = r18
            r0.onError(r13, r6)
            goto L_0x003e
        L_0x0302:
            r0 = r18
            java.nio.channels.ServerSocketChannel r14 = r0.server
            if (r14 == 0) goto L_0x030f
            r0 = r18
            java.nio.channels.ServerSocketChannel r14 = r0.server     // Catch:{ IOException -> 0x0310 }
            r14.close()     // Catch:{ IOException -> 0x0310 }
        L_0x030f:
            throw r13
        L_0x0310:
            r6 = move-exception
            r14 = 0
            r0 = r18
            r0.onError(r14, r6)
            goto L_0x030f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sensorsdata.analytics.android.sdk.java_websocket.server.WebSocketServer.run():void");
    }

    /* access modifiers changed from: protected */
    public void allocateBuffers(WebSocket c) throws InterruptedException {
        if (this.queuesize.get() < (this.decoders.size() * 2) + 1) {
            this.queuesize.incrementAndGet();
            this.buffers.put(createBuffer());
        }
    }

    /* access modifiers changed from: protected */
    public void releaseBuffers(WebSocket c) throws InterruptedException {
    }

    public ByteBuffer createBuffer() {
        return ByteBuffer.allocate(WebSocketImpl.RCVBUF);
    }

    private void queue(WebSocketImpl ws) throws InterruptedException {
        if (ws.workerThread == null) {
            ws.workerThread = (WebSocketWorker) this.decoders.get(this.queueinvokes % this.decoders.size());
            this.queueinvokes++;
        }
        ws.workerThread.put(ws);
    }

    private ByteBuffer takeBuffer() throws InterruptedException {
        return (ByteBuffer) this.buffers.take();
    }

    /* access modifiers changed from: private */
    public void pushBuffer(ByteBuffer buf) throws InterruptedException {
        if (this.buffers.size() <= this.queuesize.intValue()) {
            this.buffers.put(buf);
        }
    }

    private void handleIOException(SelectionKey key, WebSocket conn, IOException ex) {
        if (conn != null) {
            conn.closeConnection(1006, ex.getMessage());
        } else if (key != null) {
            SelectableChannel channel = key.channel();
            if (channel != null && channel.isOpen()) {
                try {
                    channel.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void handleFatal(WebSocket conn, Exception e) {
        onError(conn, e);
        try {
            stop();
        } catch (IOException e1) {
            onError(null, e1);
        } catch (InterruptedException e12) {
            Thread.currentThread().interrupt();
            onError(null, e12);
        }
    }

    /* access modifiers changed from: protected */
    public String getFlashSecurityPolicy() {
        return "<cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"" + getPort() + "\" /></cross-domain-policy>";
    }

    public final void onWebsocketMessage(WebSocket conn, String message) {
        onMessage(conn, message);
    }

    @Deprecated
    public void onWebsocketMessageFragment(WebSocket conn, Framedata frame) {
        onFragment(conn, frame);
    }

    public final void onWebsocketMessage(WebSocket conn, ByteBuffer blob) {
        onMessage(conn, blob);
    }

    public final void onWebsocketOpen(WebSocket conn, Handshakedata handshake) {
        if (addConnection(conn)) {
            onOpen(conn, (ClientHandshake) handshake);
        }
    }

    public final void onWebsocketClose(WebSocket conn, int code, String reason, boolean remote) {
        this.selector.wakeup();
        try {
            if (removeConnection(conn)) {
                onClose(conn, code, reason, remote);
            }
            try {
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } finally {
            try {
                releaseBuffers(conn);
            } catch (InterruptedException e2) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean removeConnection(WebSocket ws) {
        boolean removed;
        synchronized (this.connections) {
            removed = this.connections.remove(ws);
            if (!$assertionsDisabled && !removed) {
                throw new AssertionError();
            }
        }
        if (this.isclosed.get() && this.connections.size() == 0) {
            this.selectorthread.interrupt();
        }
        return removed;
    }

    public ServerHandshakeBuilder onWebsocketHandshakeReceivedAsServer(WebSocket conn, Draft draft, ClientHandshake request) throws InvalidDataException {
        return super.onWebsocketHandshakeReceivedAsServer(conn, draft, request);
    }

    /* access modifiers changed from: protected */
    public boolean addConnection(WebSocket ws) {
        boolean succ;
        if (!this.isclosed.get()) {
            synchronized (this.connections) {
                succ = this.connections.add(ws);
                if (!$assertionsDisabled && !succ) {
                    throw new AssertionError();
                }
            }
            return succ;
        }
        ws.close(1001);
        return true;
    }

    public final void onWebsocketError(WebSocket conn, Exception ex) {
        onError(conn, ex);
    }

    public final void onWriteDemand(WebSocket w) {
        WebSocketImpl conn = (WebSocketImpl) w;
        try {
            conn.key.interestOps(5);
        } catch (CancelledKeyException e) {
            conn.outQueue.clear();
        }
        this.selector.wakeup();
    }

    public void onWebsocketCloseInitiated(WebSocket conn, int code, String reason) {
        onCloseInitiated(conn, code, reason);
    }

    public void onWebsocketClosing(WebSocket conn, int code, String reason, boolean remote) {
        onClosing(conn, code, reason, remote);
    }

    public void onCloseInitiated(WebSocket conn, int code, String reason) {
    }

    public void onClosing(WebSocket conn, int code, String reason, boolean remote) {
    }

    public final void setWebSocketFactory(WebSocketServerFactory wsf2) {
        this.wsf = wsf2;
    }

    public final WebSocketFactory getWebSocketFactory() {
        return this.wsf;
    }

    /* access modifiers changed from: protected */
    public boolean onConnect(SelectionKey key) {
        return true;
    }

    private Socket getSocket(WebSocket conn) {
        return ((SocketChannel) ((WebSocketImpl) conn).key.channel()).socket();
    }

    public InetSocketAddress getLocalSocketAddress(WebSocket conn) {
        return (InetSocketAddress) getSocket(conn).getLocalSocketAddress();
    }

    public InetSocketAddress getRemoteSocketAddress(WebSocket conn) {
        return (InetSocketAddress) getSocket(conn).getRemoteSocketAddress();
    }

    public void onMessage(WebSocket conn, ByteBuffer message) {
    }

    public void onFragment(WebSocket conn, Framedata fragment) {
    }
}
