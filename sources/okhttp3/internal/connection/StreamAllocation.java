package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.ConnectionPool;
import okhttp3.EventListener;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Route;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RouteSelector.Selection;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;

public final class StreamAllocation {
    static final /* synthetic */ boolean $assertionsDisabled = (!StreamAllocation.class.desiredAssertionStatus());
    public final Address address;
    public final Call call;
    private final Object callStackTrace;
    private boolean canceled;
    private HttpCodec codec;
    private RealConnection connection;
    private final ConnectionPool connectionPool;
    public final EventListener eventListener;
    private int refusedStreamCount;
    private boolean released;
    private boolean reportedAcquired;
    private Route route;
    private Selection routeSelection;
    private final RouteSelector routeSelector;

    public static final class StreamAllocationReference extends WeakReference<StreamAllocation> {
        public final Object callStackTrace;

        StreamAllocationReference(StreamAllocation referent, Object callStackTrace2) {
            super(referent);
            this.callStackTrace = callStackTrace2;
        }
    }

    public StreamAllocation(ConnectionPool connectionPool2, Address address2, Call call2, EventListener eventListener2, Object callStackTrace2) {
        this.connectionPool = connectionPool2;
        this.address = address2;
        this.call = call2;
        this.eventListener = eventListener2;
        this.routeSelector = new RouteSelector(address2, routeDatabase(), call2, eventListener2);
        this.callStackTrace = callStackTrace2;
    }

    public HttpCodec newStream(OkHttpClient client, Chain chain, boolean doExtensiveHealthChecks) {
        try {
            HttpCodec resultCodec = findHealthyConnection(chain.connectTimeoutMillis(), chain.readTimeoutMillis(), chain.writeTimeoutMillis(), client.pingIntervalMillis(), client.retryOnConnectionFailure(), doExtensiveHealthChecks).newCodec(client, chain, this);
            synchronized (this.connectionPool) {
                this.codec = resultCodec;
            }
            return resultCodec;
        } catch (IOException e) {
            throw new RouteException(e);
        }
    }

    private RealConnection findHealthyConnection(int connectTimeout, int readTimeout, int writeTimeout, int pingIntervalMillis, boolean connectionRetryEnabled, boolean doExtensiveHealthChecks) throws IOException {
        RealConnection candidate;
        while (true) {
            candidate = findConnection(connectTimeout, readTimeout, writeTimeout, pingIntervalMillis, connectionRetryEnabled);
            synchronized (this.connectionPool) {
                if (candidate.successCount != 0) {
                    if (candidate.isHealthy(doExtensiveHealthChecks)) {
                        break;
                    }
                    noNewStreams();
                } else {
                    break;
                }
            }
        }
        return candidate;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:106:?, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:?, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x012f, code lost:
        if (r10 == false) goto L_0x0142;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0131, code lost:
        r21.eventListener.connectionAcquired(r21.call, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0142, code lost:
        r2.connect(r22, r23, r24, r25, r26, r21.call, r21.eventListener);
        routeDatabase().connected(r2.route());
        r19 = null;
        r4 = r21.connectionPool;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0168, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:?, code lost:
        r21.reportedAcquired = true;
        okhttp3.internal.Internal.instance.put(r21.connectionPool, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x017b, code lost:
        if (r2.isMultiplexed() == false) goto L_0x0191;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x017d, code lost:
        r19 = okhttp3.internal.Internal.instance.deduplicate(r21.connectionPool, r21.address, r21);
        r2 = r21.connection;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0191, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0192, code lost:
        okhttp3.internal.Util.closeQuietly(r19);
        r21.eventListener.connectionAcquired(r21.call, r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private okhttp3.internal.connection.RealConnection findConnection(int r22, int r23, int r24, int r25, boolean r26) throws java.io.IOException {
        /*
            r21 = this;
            r10 = 0
            r2 = 0
            r17 = 0
            r0 = r21
            okhttp3.ConnectionPool r4 = r0.connectionPool
            monitor-enter(r4)
            r0 = r21
            boolean r3 = r0.released     // Catch:{ all -> 0x0017 }
            if (r3 == 0) goto L_0x001a
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0017 }
            java.lang.String r5 = "released"
            r3.<init>(r5)     // Catch:{ all -> 0x0017 }
            throw r3     // Catch:{ all -> 0x0017 }
        L_0x0017:
            r3 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0017 }
            throw r3
        L_0x001a:
            r0 = r21
            okhttp3.internal.http.HttpCodec r3 = r0.codec     // Catch:{ all -> 0x0017 }
            if (r3 == 0) goto L_0x0028
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0017 }
            java.lang.String r5 = "codec != null"
            r3.<init>(r5)     // Catch:{ all -> 0x0017 }
            throw r3     // Catch:{ all -> 0x0017 }
        L_0x0028:
            r0 = r21
            boolean r3 = r0.canceled     // Catch:{ all -> 0x0017 }
            if (r3 == 0) goto L_0x0036
            java.io.IOException r3 = new java.io.IOException     // Catch:{ all -> 0x0017 }
            java.lang.String r5 = "Canceled"
            r3.<init>(r5)     // Catch:{ all -> 0x0017 }
            throw r3     // Catch:{ all -> 0x0017 }
        L_0x0036:
            r0 = r21
            okhttp3.internal.connection.RealConnection r13 = r0.connection     // Catch:{ all -> 0x0017 }
            java.net.Socket r20 = r21.releaseIfNoNewStreams()     // Catch:{ all -> 0x0017 }
            r0 = r21
            okhttp3.internal.connection.RealConnection r3 = r0.connection     // Catch:{ all -> 0x0017 }
            if (r3 == 0) goto L_0x0049
            r0 = r21
            okhttp3.internal.connection.RealConnection r2 = r0.connection     // Catch:{ all -> 0x0017 }
            r13 = 0
        L_0x0049:
            r0 = r21
            boolean r3 = r0.reportedAcquired     // Catch:{ all -> 0x0017 }
            if (r3 != 0) goto L_0x0050
            r13 = 0
        L_0x0050:
            if (r2 != 0) goto L_0x006d
            okhttp3.internal.Internal r3 = okhttp3.internal.Internal.instance     // Catch:{ all -> 0x0017 }
            r0 = r21
            okhttp3.ConnectionPool r5 = r0.connectionPool     // Catch:{ all -> 0x0017 }
            r0 = r21
            okhttp3.Address r6 = r0.address     // Catch:{ all -> 0x0017 }
            r7 = 0
            r0 = r21
            r3.get(r5, r6, r0, r7)     // Catch:{ all -> 0x0017 }
            r0 = r21
            okhttp3.internal.connection.RealConnection r3 = r0.connection     // Catch:{ all -> 0x0017 }
            if (r3 == 0) goto L_0x008f
            r10 = 1
            r0 = r21
            okhttp3.internal.connection.RealConnection r2 = r0.connection     // Catch:{ all -> 0x0017 }
        L_0x006d:
            monitor-exit(r4)     // Catch:{ all -> 0x0017 }
            okhttp3.internal.Util.closeQuietly(r20)
            if (r13 == 0) goto L_0x007e
            r0 = r21
            okhttp3.EventListener r3 = r0.eventListener
            r0 = r21
            okhttp3.Call r4 = r0.call
            r3.connectionReleased(r4, r13)
        L_0x007e:
            if (r10 == 0) goto L_0x008b
            r0 = r21
            okhttp3.EventListener r3 = r0.eventListener
            r0 = r21
            okhttp3.Call r4 = r0.call
            r3.connectionAcquired(r4, r2)
        L_0x008b:
            if (r2 == 0) goto L_0x0096
            r14 = r2
        L_0x008e:
            return r14
        L_0x008f:
            r0 = r21
            okhttp3.Route r0 = r0.route     // Catch:{ all -> 0x0017 }
            r17 = r0
            goto L_0x006d
        L_0x0096:
            r12 = 0
            if (r17 != 0) goto L_0x00b6
            r0 = r21
            okhttp3.internal.connection.RouteSelector$Selection r3 = r0.routeSelection
            if (r3 == 0) goto L_0x00a9
            r0 = r21
            okhttp3.internal.connection.RouteSelector$Selection r3 = r0.routeSelection
            boolean r3 = r3.hasNext()
            if (r3 != 0) goto L_0x00b6
        L_0x00a9:
            r12 = 1
            r0 = r21
            okhttp3.internal.connection.RouteSelector r3 = r0.routeSelector
            okhttp3.internal.connection.RouteSelector$Selection r3 = r3.next()
            r0 = r21
            r0.routeSelection = r3
        L_0x00b6:
            r0 = r21
            okhttp3.ConnectionPool r4 = r0.connectionPool
            monitor-enter(r4)
            r0 = r21
            boolean r3 = r0.canceled     // Catch:{ all -> 0x00c9 }
            if (r3 == 0) goto L_0x00cc
            java.io.IOException r3 = new java.io.IOException     // Catch:{ all -> 0x00c9 }
            java.lang.String r5 = "Canceled"
            r3.<init>(r5)     // Catch:{ all -> 0x00c9 }
            throw r3     // Catch:{ all -> 0x00c9 }
        L_0x00c9:
            r3 = move-exception
        L_0x00ca:
            monitor-exit(r4)     // Catch:{ all -> 0x00c9 }
            throw r3
        L_0x00cc:
            if (r12 == 0) goto L_0x01ac
            r0 = r21
            okhttp3.internal.connection.RouteSelector$Selection r3 = r0.routeSelection     // Catch:{ all -> 0x00c9 }
            java.util.List r16 = r3.getAll()     // Catch:{ all -> 0x00c9 }
            r11 = 0
            int r18 = r16.size()     // Catch:{ all -> 0x00c9 }
        L_0x00db:
            r0 = r18
            if (r11 >= r0) goto L_0x01ac
            r0 = r16
            java.lang.Object r15 = r0.get(r11)     // Catch:{ all -> 0x00c9 }
            okhttp3.Route r15 = (okhttp3.Route) r15     // Catch:{ all -> 0x00c9 }
            okhttp3.internal.Internal r3 = okhttp3.internal.Internal.instance     // Catch:{ all -> 0x00c9 }
            r0 = r21
            okhttp3.ConnectionPool r5 = r0.connectionPool     // Catch:{ all -> 0x00c9 }
            r0 = r21
            okhttp3.Address r6 = r0.address     // Catch:{ all -> 0x00c9 }
            r0 = r21
            r3.get(r5, r6, r0, r15)     // Catch:{ all -> 0x00c9 }
            r0 = r21
            okhttp3.internal.connection.RealConnection r3 = r0.connection     // Catch:{ all -> 0x00c9 }
            if (r3 == 0) goto L_0x013f
            r10 = 1
            r0 = r21
            okhttp3.internal.connection.RealConnection r2 = r0.connection     // Catch:{ all -> 0x00c9 }
            r0 = r21
            r0.route = r15     // Catch:{ all -> 0x00c9 }
            r14 = r2
        L_0x0106:
            if (r10 != 0) goto L_0x01aa
            if (r17 != 0) goto L_0x0112
            r0 = r21
            okhttp3.internal.connection.RouteSelector$Selection r3 = r0.routeSelection     // Catch:{ all -> 0x01a6 }
            okhttp3.Route r17 = r3.next()     // Catch:{ all -> 0x01a6 }
        L_0x0112:
            r0 = r17
            r1 = r21
            r1.route = r0     // Catch:{ all -> 0x01a6 }
            r3 = 0
            r0 = r21
            r0.refusedStreamCount = r3     // Catch:{ all -> 0x01a6 }
            okhttp3.internal.connection.RealConnection r2 = new okhttp3.internal.connection.RealConnection     // Catch:{ all -> 0x01a6 }
            r0 = r21
            okhttp3.ConnectionPool r3 = r0.connectionPool     // Catch:{ all -> 0x01a6 }
            r0 = r17
            r2.<init>(r3, r0)     // Catch:{ all -> 0x01a6 }
            r3 = 0
            r0 = r21
            r0.acquire(r2, r3)     // Catch:{ all -> 0x00c9 }
        L_0x012e:
            monitor-exit(r4)     // Catch:{ all -> 0x00c9 }
            if (r10 == 0) goto L_0x0142
            r0 = r21
            okhttp3.EventListener r3 = r0.eventListener
            r0 = r21
            okhttp3.Call r4 = r0.call
            r3.connectionAcquired(r4, r2)
            r14 = r2
            goto L_0x008e
        L_0x013f:
            int r11 = r11 + 1
            goto L_0x00db
        L_0x0142:
            r0 = r21
            okhttp3.Call r8 = r0.call
            r0 = r21
            okhttp3.EventListener r9 = r0.eventListener
            r3 = r22
            r4 = r23
            r5 = r24
            r6 = r25
            r7 = r26
            r2.connect(r3, r4, r5, r6, r7, r8, r9)
            okhttp3.internal.connection.RouteDatabase r3 = r21.routeDatabase()
            okhttp3.Route r4 = r2.route()
            r3.connected(r4)
            r19 = 0
            r0 = r21
            okhttp3.ConnectionPool r4 = r0.connectionPool
            monitor-enter(r4)
            r3 = 1
            r0 = r21
            r0.reportedAcquired = r3     // Catch:{ all -> 0x01a3 }
            okhttp3.internal.Internal r3 = okhttp3.internal.Internal.instance     // Catch:{ all -> 0x01a3 }
            r0 = r21
            okhttp3.ConnectionPool r5 = r0.connectionPool     // Catch:{ all -> 0x01a3 }
            r3.put(r5, r2)     // Catch:{ all -> 0x01a3 }
            boolean r3 = r2.isMultiplexed()     // Catch:{ all -> 0x01a3 }
            if (r3 == 0) goto L_0x0191
            okhttp3.internal.Internal r3 = okhttp3.internal.Internal.instance     // Catch:{ all -> 0x01a3 }
            r0 = r21
            okhttp3.ConnectionPool r5 = r0.connectionPool     // Catch:{ all -> 0x01a3 }
            r0 = r21
            okhttp3.Address r6 = r0.address     // Catch:{ all -> 0x01a3 }
            r0 = r21
            java.net.Socket r19 = r3.deduplicate(r5, r6, r0)     // Catch:{ all -> 0x01a3 }
            r0 = r21
            okhttp3.internal.connection.RealConnection r2 = r0.connection     // Catch:{ all -> 0x01a3 }
        L_0x0191:
            monitor-exit(r4)     // Catch:{ all -> 0x01a3 }
            okhttp3.internal.Util.closeQuietly(r19)
            r0 = r21
            okhttp3.EventListener r3 = r0.eventListener
            r0 = r21
            okhttp3.Call r4 = r0.call
            r3.connectionAcquired(r4, r2)
            r14 = r2
            goto L_0x008e
        L_0x01a3:
            r3 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x01a3 }
            throw r3
        L_0x01a6:
            r3 = move-exception
            r2 = r14
            goto L_0x00ca
        L_0x01aa:
            r2 = r14
            goto L_0x012e
        L_0x01ac:
            r14 = r2
            goto L_0x0106
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.StreamAllocation.findConnection(int, int, int, int, boolean):okhttp3.internal.connection.RealConnection");
    }

    private Socket releaseIfNoNewStreams() {
        if ($assertionsDisabled || Thread.holdsLock(this.connectionPool)) {
            RealConnection allocatedConnection = this.connection;
            if (allocatedConnection == null || !allocatedConnection.noNewStreams) {
                return null;
            }
            return deallocate(false, false, true);
        }
        throw new AssertionError();
    }

    public void streamFinished(boolean noNewStreams, HttpCodec codec2, long bytesRead, IOException e) {
        Connection releasedConnection;
        Socket socket;
        boolean callEnd;
        this.eventListener.responseBodyEnd(this.call, bytesRead);
        synchronized (this.connectionPool) {
            if (codec2 != null) {
                if (codec2 == this.codec) {
                    if (!noNewStreams) {
                        this.connection.successCount++;
                    }
                    releasedConnection = this.connection;
                    socket = deallocate(noNewStreams, false, true);
                    if (this.connection != null) {
                        releasedConnection = null;
                    }
                    callEnd = this.released;
                }
            }
            throw new IllegalStateException("expected " + this.codec + " but was " + codec2);
        }
        Util.closeQuietly(socket);
        if (releasedConnection != null) {
            this.eventListener.connectionReleased(this.call, releasedConnection);
        }
        if (e != null) {
            this.eventListener.callFailed(this.call, e);
        } else if (callEnd) {
            this.eventListener.callEnd(this.call);
        }
    }

    public HttpCodec codec() {
        HttpCodec httpCodec;
        synchronized (this.connectionPool) {
            httpCodec = this.codec;
        }
        return httpCodec;
    }

    private RouteDatabase routeDatabase() {
        return Internal.instance.routeDatabase(this.connectionPool);
    }

    public Route route() {
        return this.route;
    }

    public synchronized RealConnection connection() {
        return this.connection;
    }

    public void release() {
        Connection releasedConnection;
        Socket socket;
        synchronized (this.connectionPool) {
            releasedConnection = this.connection;
            socket = deallocate(false, true, false);
            if (this.connection != null) {
                releasedConnection = null;
            }
        }
        Util.closeQuietly(socket);
        if (releasedConnection != null) {
            this.eventListener.connectionReleased(this.call, releasedConnection);
        }
    }

    public void noNewStreams() {
        Connection releasedConnection;
        Socket socket;
        synchronized (this.connectionPool) {
            releasedConnection = this.connection;
            socket = deallocate(true, false, false);
            if (this.connection != null) {
                releasedConnection = null;
            }
        }
        Util.closeQuietly(socket);
        if (releasedConnection != null) {
            this.eventListener.connectionReleased(this.call, releasedConnection);
        }
    }

    private Socket deallocate(boolean noNewStreams, boolean released2, boolean streamFinished) {
        if ($assertionsDisabled || Thread.holdsLock(this.connectionPool)) {
            if (streamFinished) {
                this.codec = null;
            }
            if (released2) {
                this.released = true;
            }
            Socket socket = null;
            if (this.connection != null) {
                if (noNewStreams) {
                    this.connection.noNewStreams = true;
                }
                if (this.codec == null && (this.released || this.connection.noNewStreams)) {
                    release(this.connection);
                    if (this.connection.allocations.isEmpty()) {
                        this.connection.idleAtNanos = System.nanoTime();
                        if (Internal.instance.connectionBecameIdle(this.connectionPool, this.connection)) {
                            socket = this.connection.socket();
                        }
                    }
                    this.connection = null;
                }
            }
            return socket;
        }
        throw new AssertionError();
    }

    public void cancel() {
        HttpCodec codecToCancel;
        RealConnection connectionToCancel;
        synchronized (this.connectionPool) {
            this.canceled = true;
            codecToCancel = this.codec;
            connectionToCancel = this.connection;
        }
        if (codecToCancel != null) {
            codecToCancel.cancel();
        } else if (connectionToCancel != null) {
            connectionToCancel.cancel();
        }
    }

    public void streamFailed(IOException e) {
        Connection releasedConnection;
        Socket socket;
        boolean noNewStreams = false;
        synchronized (this.connectionPool) {
            if (e instanceof StreamResetException) {
                StreamResetException streamResetException = (StreamResetException) e;
                if (streamResetException.errorCode == ErrorCode.REFUSED_STREAM) {
                    this.refusedStreamCount++;
                }
                if (streamResetException.errorCode != ErrorCode.REFUSED_STREAM || this.refusedStreamCount > 1) {
                    noNewStreams = true;
                    this.route = null;
                }
            } else if (this.connection != null && (!this.connection.isMultiplexed() || (e instanceof ConnectionShutdownException))) {
                noNewStreams = true;
                if (this.connection.successCount == 0) {
                    if (!(this.route == null || e == null)) {
                        this.routeSelector.connectFailed(this.route, e);
                    }
                    this.route = null;
                }
            }
            releasedConnection = this.connection;
            socket = deallocate(noNewStreams, false, true);
            if (this.connection != null || !this.reportedAcquired) {
                releasedConnection = null;
            }
        }
        Util.closeQuietly(socket);
        if (releasedConnection != null) {
            this.eventListener.connectionReleased(this.call, releasedConnection);
        }
    }

    public void acquire(RealConnection connection2, boolean reportedAcquired2) {
        if (!$assertionsDisabled && !Thread.holdsLock(this.connectionPool)) {
            throw new AssertionError();
        } else if (this.connection != null) {
            throw new IllegalStateException();
        } else {
            this.connection = connection2;
            this.reportedAcquired = reportedAcquired2;
            connection2.allocations.add(new StreamAllocationReference(this, this.callStackTrace));
        }
    }

    private void release(RealConnection connection2) {
        int size = connection2.allocations.size();
        for (int i = 0; i < size; i++) {
            if (((Reference) connection2.allocations.get(i)).get() == this) {
                connection2.allocations.remove(i);
                return;
            }
        }
        throw new IllegalStateException();
    }

    public Socket releaseAndAcquire(RealConnection newConnection) {
        if (!$assertionsDisabled && !Thread.holdsLock(this.connectionPool)) {
            throw new AssertionError();
        } else if (this.codec == null && this.connection.allocations.size() == 1) {
            Reference<StreamAllocation> onlyAllocation = (Reference) this.connection.allocations.get(0);
            Socket socket = deallocate(true, false, false);
            this.connection = newConnection;
            newConnection.allocations.add(onlyAllocation);
            return socket;
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean hasMoreRoutes() {
        return this.route != null || (this.routeSelection != null && this.routeSelection.hasNext()) || this.routeSelector.hasNext();
    }

    public String toString() {
        RealConnection connection2 = connection();
        return connection2 != null ? connection2.toString() : this.address.toString();
    }
}
