package com.sensorsdata.analytics.android.sdk.java_websocket.client;

import com.sensorsdata.analytics.android.sdk.java_websocket.WebSocket;
import com.sensorsdata.analytics.android.sdk.java_websocket.WebSocket.READYSTATE;
import com.sensorsdata.analytics.android.sdk.java_websocket.WebSocketAdapter;
import com.sensorsdata.analytics.android.sdk.java_websocket.WebSocketImpl;
import com.sensorsdata.analytics.android.sdk.java_websocket.WebSocketListener;
import com.sensorsdata.analytics.android.sdk.java_websocket.drafts.Draft;
import com.sensorsdata.analytics.android.sdk.java_websocket.drafts.Draft_17;
import com.sensorsdata.analytics.android.sdk.java_websocket.exceptions.InvalidHandshakeException;
import com.sensorsdata.analytics.android.sdk.java_websocket.framing.Framedata;
import com.sensorsdata.analytics.android.sdk.java_websocket.framing.Framedata.Opcode;
import com.sensorsdata.analytics.android.sdk.java_websocket.handshake.HandshakeImpl1Client;
import com.sensorsdata.analytics.android.sdk.java_websocket.handshake.Handshakedata;
import com.sensorsdata.analytics.android.sdk.java_websocket.handshake.ServerHandshake;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.NotYetConnectedException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;
import p004cn.jiguang.net.HttpUtils;

public abstract class WebSocketClient extends WebSocketAdapter implements Runnable, WebSocket {
    static final /* synthetic */ boolean $assertionsDisabled = (!WebSocketClient.class.desiredAssertionStatus());
    private CountDownLatch closeLatch;
    private CountDownLatch connectLatch;
    private int connectTimeout;
    private Draft draft;
    /* access modifiers changed from: private */
    public WebSocketImpl engine;
    private Map<String, String> headers;
    private InputStream istream;
    /* access modifiers changed from: private */
    public OutputStream ostream;
    private Proxy proxy;
    private Socket socket;
    protected URI uri;
    private Thread writeThread;

    private class WebsocketWriteThread implements Runnable {
        private WebsocketWriteThread() {
        }

        public void run() {
            Thread.currentThread().setName("WebsocketWriteThread");
            while (!Thread.interrupted()) {
                try {
                    ByteBuffer buffer = (ByteBuffer) WebSocketClient.this.engine.outQueue.take();
                    WebSocketClient.this.ostream.write(buffer.array(), 0, buffer.limit());
                    WebSocketClient.this.ostream.flush();
                } catch (IOException e) {
                    WebSocketClient.this.engine.eot();
                    return;
                } catch (InterruptedException e2) {
                    return;
                }
            }
        }
    }

    public abstract void onClose(int i, String str, boolean z);

    public abstract void onError(Exception exc);

    public abstract void onMessage(String str);

    public abstract void onOpen(ServerHandshake serverHandshake);

    public WebSocketClient(URI serverURI) {
        this(serverURI, new Draft_17());
    }

    public WebSocketClient(URI serverUri, Draft draft2) {
        this(serverUri, draft2, null, 0);
    }

    public WebSocketClient(URI serverUri, Draft protocolDraft, Map<String, String> httpHeaders, int connectTimeout2) {
        this.uri = null;
        this.engine = null;
        this.socket = null;
        this.proxy = Proxy.NO_PROXY;
        this.connectLatch = new CountDownLatch(1);
        this.closeLatch = new CountDownLatch(1);
        this.connectTimeout = 0;
        if (serverUri == null) {
            throw new IllegalArgumentException();
        } else if (protocolDraft == null) {
            throw new IllegalArgumentException("null as draft is permitted for `WebSocketServer` only!");
        } else {
            this.uri = serverUri;
            this.draft = protocolDraft;
            this.headers = httpHeaders;
            this.connectTimeout = connectTimeout2;
            this.engine = new WebSocketImpl((WebSocketListener) this, protocolDraft);
        }
    }

    public URI getURI() {
        return this.uri;
    }

    public Draft getDraft() {
        return this.draft;
    }

    public void connect() {
        if (this.writeThread != null) {
            throw new IllegalStateException("WebSocketClient objects are not reuseable");
        }
        this.writeThread = new Thread(this);
        this.writeThread.start();
    }

    public boolean connectBlocking() throws InterruptedException {
        connect();
        this.connectLatch.await();
        return this.engine.isOpen();
    }

    public void close() {
        if (this.writeThread != null) {
            this.engine.close(1000);
        }
    }

    public void closeBlocking() throws InterruptedException {
        close();
        this.closeLatch.await();
    }

    public void send(String text) throws NotYetConnectedException {
        this.engine.send(text);
    }

    public void send(byte[] data) throws NotYetConnectedException {
        this.engine.send(data);
    }

    public void run() {
        try {
            if (this.socket == null) {
                this.socket = new Socket(this.proxy);
            } else if (this.socket.isClosed()) {
                throw new IOException();
            }
            if (!this.socket.isBound()) {
                this.socket.connect(new InetSocketAddress(this.uri.getHost(), getPort()), this.connectTimeout);
            }
            this.istream = this.socket.getInputStream();
            this.ostream = this.socket.getOutputStream();
            sendHandshake();
            this.writeThread = new Thread(new WebsocketWriteThread());
            this.writeThread.start();
            byte[] rawbuffer = new byte[WebSocketImpl.RCVBUF];
            while (!isClosed() && !isClosing()) {
                try {
                    int readBytes = this.istream.read(rawbuffer);
                    if (readBytes != -1) {
                        this.engine.decode(ByteBuffer.wrap(rawbuffer, 0, readBytes));
                    }
                } catch (IOException e) {
                    this.engine.eot();
                } catch (RuntimeException e2) {
                    onError(e2);
                    this.engine.closeConnection(1006, e2.getMessage());
                }
            }
            this.engine.eot();
            if (!$assertionsDisabled && !this.socket.isClosed()) {
                throw new AssertionError();
            }
        } catch (Exception e3) {
            onWebsocketError(this.engine, e3);
            this.engine.closeConnection(-1, e3.getMessage());
        }
    }

    private int getPort() {
        int port = this.uri.getPort();
        if (port != -1) {
            return port;
        }
        String scheme = this.uri.getScheme();
        if (scheme.equals("wss")) {
            return WebSocket.DEFAULT_WSS_PORT;
        }
        if (scheme.equals("ws")) {
            return 80;
        }
        throw new RuntimeException("unkonow scheme" + scheme);
    }

    private void sendHandshake() throws InvalidHandshakeException {
        String path;
        String part1 = this.uri.getPath();
        String part2 = this.uri.getQuery();
        if (part1 == null || part1.length() == 0) {
            path = HttpUtils.PATHS_SEPARATOR;
        } else {
            path = part1;
        }
        if (part2 != null) {
            path = path + HttpUtils.URL_AND_PARA_SEPARATOR + part2;
        }
        int port = getPort();
        String host = this.uri.getHost() + (port != 80 ? ":" + port : "");
        HandshakeImpl1Client handshake = new HandshakeImpl1Client();
        handshake.setResourceDescriptor(path);
        handshake.put("Host", host);
        if (this.headers != null) {
            for (Entry<String, String> kv : this.headers.entrySet()) {
                handshake.put((String) kv.getKey(), (String) kv.getValue());
            }
        }
        this.engine.startHandshake(handshake);
    }

    public READYSTATE getReadyState() {
        return this.engine.getReadyState();
    }

    public final void onWebsocketMessage(WebSocket conn, String message) {
        onMessage(message);
    }

    public final void onWebsocketMessage(WebSocket conn, ByteBuffer blob) {
        onMessage(blob);
    }

    public void onWebsocketMessageFragment(WebSocket conn, Framedata frame) {
        onFragment(frame);
    }

    public final void onWebsocketOpen(WebSocket conn, Handshakedata handshake) {
        this.connectLatch.countDown();
        onOpen((ServerHandshake) handshake);
    }

    public final void onWebsocketClose(WebSocket conn, int code, String reason, boolean remote) {
        this.connectLatch.countDown();
        this.closeLatch.countDown();
        if (this.writeThread != null) {
            this.writeThread.interrupt();
        }
        try {
            if (this.socket != null) {
                this.socket.close();
            }
        } catch (IOException e) {
            onWebsocketError(this, e);
        }
        onClose(code, reason, remote);
    }

    public final void onWebsocketError(WebSocket conn, Exception ex) {
        onError(ex);
    }

    public final void onWriteDemand(WebSocket conn) {
    }

    public void onWebsocketCloseInitiated(WebSocket conn, int code, String reason) {
        onCloseInitiated(code, reason);
    }

    public void onWebsocketClosing(WebSocket conn, int code, String reason, boolean remote) {
        onClosing(code, reason, remote);
    }

    public void onCloseInitiated(int code, String reason) {
    }

    public void onClosing(int code, String reason, boolean remote) {
    }

    public WebSocket getConnection() {
        return this.engine;
    }

    public InetSocketAddress getLocalSocketAddress(WebSocket conn) {
        if (this.socket != null) {
            return (InetSocketAddress) this.socket.getLocalSocketAddress();
        }
        return null;
    }

    public InetSocketAddress getRemoteSocketAddress(WebSocket conn) {
        if (this.socket != null) {
            return (InetSocketAddress) this.socket.getRemoteSocketAddress();
        }
        return null;
    }

    public void onMessage(ByteBuffer bytes) {
    }

    public void onFragment(Framedata frame) {
    }

    public void setProxy(Proxy proxy2) {
        if (proxy2 == null) {
            throw new IllegalArgumentException();
        }
        this.proxy = proxy2;
    }

    public void setSocket(Socket socket2) {
        if (this.socket != null) {
            throw new IllegalStateException("socket has already been set");
        }
        this.socket = socket2;
    }

    public void sendFragmentedFrame(Opcode op, ByteBuffer buffer, boolean fin) {
        this.engine.sendFragmentedFrame(op, buffer, fin);
    }

    public boolean isOpen() {
        return this.engine.isOpen();
    }

    public boolean isFlushAndClose() {
        return this.engine.isFlushAndClose();
    }

    public boolean isClosed() {
        return this.engine.isClosed();
    }

    public boolean isClosing() {
        return this.engine.isClosing();
    }

    public boolean isConnecting() {
        return this.engine.isConnecting();
    }

    public boolean hasBufferedData() {
        return this.engine.hasBufferedData();
    }

    public void close(int code) {
        this.engine.close();
    }

    public void close(int code, String message) {
        this.engine.close(code, message);
    }

    public void closeConnection(int code, String message) {
        this.engine.closeConnection(code, message);
    }

    public void send(ByteBuffer bytes) throws IllegalArgumentException, NotYetConnectedException {
        this.engine.send(bytes);
    }

    public void sendFrame(Framedata framedata) {
        this.engine.sendFrame(framedata);
    }

    public InetSocketAddress getLocalSocketAddress() {
        return this.engine.getLocalSocketAddress();
    }

    public InetSocketAddress getRemoteSocketAddress() {
        return this.engine.getRemoteSocketAddress();
    }

    public String getResourceDescriptor() {
        return this.uri.getPath();
    }
}
