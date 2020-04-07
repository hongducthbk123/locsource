package com.adjust.sdk;

import android.content.Context;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InstallReferrer implements InvocationHandler {
    private static final String PACKAGE_BASE_NAME = "com.android.installreferrer.";
    private static final int STATUS_DEVELOPER_ERROR = 3;
    private static final int STATUS_FEATURE_NOT_SUPPORTED = 2;
    private static final int STATUS_OK = 0;
    private static final int STATUS_SERVICE_DISCONNECTED = -1;
    private static final int STATUS_SERVICE_UNAVAILABLE = 1;
    private WeakReference<IActivityHandler> activityHandlerWeakRef;
    private Context context;
    private final Object flagLock;
    private boolean hasInstallReferrerBeenRead;
    private ILogger logger = AdjustFactory.getLogger();
    private Object referrerClient;
    private int retries;
    private TimerOnce retryTimer;
    private int retryWaitTime = 3000;

    public InstallReferrer(Context context2, IActivityHandler activityHandler) {
        this.context = context2;
        this.flagLock = new Object();
        this.hasInstallReferrerBeenRead = false;
        this.retries = 0;
        this.retryTimer = new TimerOnce(new Runnable() {
            public void run() {
                InstallReferrer.this.startConnection();
            }
        }, "InstallReferrer");
        this.activityHandlerWeakRef = new WeakReference<>(activityHandler);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0023, code lost:
        if (r6.context == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0025, code lost:
        r6.referrerClient = createInstallReferrerClient(r6.context);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002f, code lost:
        if (r6.referrerClient == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0031, code lost:
        r0 = getInstallReferrerStateListenerClass();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0035, code lost:
        if (r0 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0037, code lost:
        r1 = createProxyInstallReferrerStateListener(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003b, code lost:
        if (r1 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003d, code lost:
        startConnection(r0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void startConnection() {
        /*
            r6 = this;
            boolean r2 = com.adjust.sdk.AdjustFactory.getTryInstallReferrer()
            if (r2 != 0) goto L_0x0007
        L_0x0006:
            return
        L_0x0007:
            r6.closeReferrerClient()
            java.lang.Object r3 = r6.flagLock
            monitor-enter(r3)
            boolean r2 = r6.hasInstallReferrerBeenRead     // Catch:{ all -> 0x001d }
            if (r2 == 0) goto L_0x0020
            com.adjust.sdk.ILogger r2 = r6.logger     // Catch:{ all -> 0x001d }
            java.lang.String r4 = "Install referrer has already been read"
            r5 = 0
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x001d }
            r2.debug(r4, r5)     // Catch:{ all -> 0x001d }
            monitor-exit(r3)     // Catch:{ all -> 0x001d }
            goto L_0x0006
        L_0x001d:
            r2 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x001d }
            throw r2
        L_0x0020:
            monitor-exit(r3)     // Catch:{ all -> 0x001d }
            android.content.Context r2 = r6.context
            if (r2 == 0) goto L_0x0006
            android.content.Context r2 = r6.context
            java.lang.Object r2 = r6.createInstallReferrerClient(r2)
            r6.referrerClient = r2
            java.lang.Object r2 = r6.referrerClient
            if (r2 == 0) goto L_0x0006
            java.lang.Class r0 = r6.getInstallReferrerStateListenerClass()
            if (r0 == 0) goto L_0x0006
            java.lang.Object r1 = r6.createProxyInstallReferrerStateListener(r0)
            if (r1 == 0) goto L_0x0006
            r6.startConnection(r0, r1)
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adjust.sdk.InstallReferrer.startConnection():void");
    }

    private Object createInstallReferrerClient(Context context2) {
        boolean z = false;
        try {
            return Reflection.invokeInstanceMethod(Reflection.invokeStaticMethod("com.android.installreferrer.api.InstallReferrerClient", "newBuilder", new Class[]{Context.class}, context2), "build", null, new Object[0]);
        } catch (ClassNotFoundException ex) {
            this.logger.warn("InstallReferrer not integrated in project (%s) thrown by (%s)", ex.getMessage(), ex.getClass().getCanonicalName());
            return z;
        } catch (Exception ex2) {
            this.logger.error("createInstallReferrerClient error (%s) from (%s)", ex2.getMessage(), ex2.getClass().getCanonicalName());
            return z;
        }
    }

    private Class getInstallReferrerStateListenerClass() {
        try {
            return Class.forName("com.android.installreferrer.api.InstallReferrerStateListener");
        } catch (Exception ex) {
            this.logger.error("getInstallReferrerStateListenerClass error (%s) from (%s)", ex.getMessage(), ex.getClass().getCanonicalName());
            return null;
        }
    }

    private Object createProxyInstallReferrerStateListener(Class installReferrerStateListenerClass) {
        Object proxyInstance = null;
        try {
            return Proxy.newProxyInstance(installReferrerStateListenerClass.getClassLoader(), new Class[]{installReferrerStateListenerClass}, this);
        } catch (IllegalArgumentException e) {
            this.logger.error("InstallReferrer proxy violating parameter restrictions", new Object[0]);
            return proxyInstance;
        } catch (NullPointerException e2) {
            this.logger.error("Null argument passed to InstallReferrer proxy", new Object[0]);
            return proxyInstance;
        }
    }

    private void startConnection(Class listenerClass, Object listenerProxy) {
        try {
            Reflection.invokeInstanceMethod(this.referrerClient, "startConnection", new Class[]{listenerClass}, listenerProxy);
        } catch (InvocationTargetException ex) {
            if (Util.hasRootCause(ex)) {
                this.logger.error("InstallReferrer encountered an InvocationTargetException %s", Util.getRootCause(ex));
            }
        } catch (Exception ex2) {
            this.logger.error("startConnection error (%s) thrown by (%s)", ex2.getMessage(), ex2.getClass().getCanonicalName());
        }
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method == null) {
            this.logger.error("InstallReferrer invoke method null", new Object[0]);
        } else {
            String methodName = method.getName();
            if (methodName == null) {
                this.logger.error("InstallReferrer invoke method name null", new Object[0]);
            } else {
                this.logger.debug("InstallReferrer invoke method name: %s", methodName);
                if (args == null) {
                    this.logger.warn("InstallReferrer invoke args null", new Object[0]);
                    args = new Object[0];
                }
                for (Object arg : args) {
                    this.logger.debug("InstallReferrer invoke arg: %s", arg);
                }
                if (methodName.equals("onInstallReferrerSetupFinished")) {
                    if (args.length != 1) {
                        this.logger.error("InstallReferrer invoke onInstallReferrerSetupFinished args lenght not 1: %d", Integer.valueOf(args.length));
                    } else {
                        Object arg2 = args[0];
                        if (!(arg2 instanceof Integer)) {
                            this.logger.error("InstallReferrer invoke onInstallReferrerSetupFinished arg not int", new Object[0]);
                        } else {
                            Integer responseCode = (Integer) arg2;
                            if (responseCode == null) {
                                this.logger.error("InstallReferrer invoke onInstallReferrerSetupFinished responseCode arg is null", new Object[0]);
                            } else {
                                onInstallReferrerSetupFinishedInt(responseCode.intValue());
                            }
                        }
                    }
                } else if (methodName.equals("onInstallReferrerServiceDisconnected")) {
                    this.logger.debug("InstallReferrer onInstallReferrerServiceDisconnected", new Object[0]);
                }
            }
        }
        return null;
    }

    private void onInstallReferrerSetupFinishedInt(int responseCode) {
        switch (responseCode) {
            case -1:
                this.logger.debug("Play Store service is not connected now. Retrying ...", new Object[0]);
                retry();
                return;
            case 0:
                try {
                    Object referrerDetails = getInstallReferrer();
                    String installReferrer = getStringInstallReferrer(referrerDetails);
                    long clickTime = getReferrerClickTimestampSeconds(referrerDetails);
                    long installBegin = getInstallBeginTimestampSeconds(referrerDetails);
                    this.logger.debug("installReferrer: %s, clickTime: %d, installBeginTime: %d", installReferrer, Long.valueOf(clickTime), Long.valueOf(installBegin));
                    IActivityHandler activityHandler = (IActivityHandler) this.activityHandlerWeakRef.get();
                    if (activityHandler != null) {
                        activityHandler.sendInstallReferrer(clickTime, installBegin, installReferrer);
                    }
                    synchronized (this.flagLock) {
                        this.hasInstallReferrerBeenRead = true;
                    }
                    closeReferrerClient();
                    return;
                } catch (Exception e) {
                    this.logger.warn("Couldn't get install referrer from client (%s). Retrying ...", e.getMessage());
                    retry();
                    return;
                }
            case 1:
                this.logger.debug("Could not initiate connection to the Install Referrer service. Retrying ...", new Object[0]);
                retry();
                return;
            case 2:
                this.logger.debug("Install referrer not available on the current Play Store app.", new Object[0]);
                return;
            case 3:
                this.logger.debug("Install referrer general errors caused by incorrect usage. Retrying ...", new Object[0]);
                retry();
                return;
            default:
                this.logger.debug("Unexpected response code of install referrer response: %d", Integer.valueOf(responseCode));
                closeReferrerClient();
                return;
        }
    }

    private Object getInstallReferrer() {
        Object obj = null;
        if (this.referrerClient == null) {
            return obj;
        }
        try {
            return Reflection.invokeInstanceMethod(this.referrerClient, "getInstallReferrer", null, new Object[0]);
        } catch (Exception e) {
            this.logger.error("getInstallReferrer error (%s) thrown by (%s)", e.getMessage(), e.getClass().getCanonicalName());
            return obj;
        }
    }

    private String getStringInstallReferrer(Object referrerDetails) {
        if (referrerDetails == null) {
            return null;
        }
        try {
            return (String) Reflection.invokeInstanceMethod(referrerDetails, "getInstallReferrer", null, new Object[0]);
        } catch (Exception e) {
            this.logger.error("getStringInstallReferrer error (%s) thrown by (%s)", e.getMessage(), e.getClass().getCanonicalName());
            return null;
        }
    }

    private long getReferrerClickTimestampSeconds(Object referrerDetails) {
        long j = -1;
        if (referrerDetails == null) {
            return j;
        }
        try {
            return ((Long) Reflection.invokeInstanceMethod(referrerDetails, "getReferrerClickTimestampSeconds", null, new Object[0])).longValue();
        } catch (Exception e) {
            this.logger.error("getReferrerClickTimestampSeconds error (%s) thrown by (%s)", e.getMessage(), e.getClass().getCanonicalName());
            return j;
        }
    }

    private long getInstallBeginTimestampSeconds(Object referrerDetails) {
        long j = -1;
        if (referrerDetails == null) {
            return j;
        }
        try {
            return ((Long) Reflection.invokeInstanceMethod(referrerDetails, "getInstallBeginTimestampSeconds", null, new Object[0])).longValue();
        } catch (Exception e) {
            this.logger.error("getInstallBeginTimestampSeconds error (%s) thrown by (%s)", e.getMessage(), e.getClass().getCanonicalName());
            return j;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0020, code lost:
        r7.logger.debug("Limit number of retry for install referrer surpassed", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002d, code lost:
        r0 = r7.retryTimer.getFireIn();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0037, code lost:
        if (r0 <= 0) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0039, code lost:
        r7.logger.debug("Already waiting to retry to read install referrer in %d milliseconds", java.lang.Long.valueOf(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004a, code lost:
        r7.retryTimer.startIn((long) r7.retryWaitTime);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0015, code lost:
        r7.retries++;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001e, code lost:
        if (r7.retries <= 2) goto L_0x002d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void retry() {
        /*
            r7 = this;
            r6 = 0
            java.lang.Object r3 = r7.flagLock
            monitor-enter(r3)
            boolean r2 = r7.hasInstallReferrerBeenRead     // Catch:{ all -> 0x002a }
            if (r2 == 0) goto L_0x0014
            com.adjust.sdk.ILogger r2 = r7.logger     // Catch:{ all -> 0x002a }
            java.lang.String r4 = "Install referrer has already been read"
            r5 = 0
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x002a }
            r2.debug(r4, r5)     // Catch:{ all -> 0x002a }
            monitor-exit(r3)     // Catch:{ all -> 0x002a }
        L_0x0013:
            return
        L_0x0014:
            monitor-exit(r3)     // Catch:{ all -> 0x002a }
            int r2 = r7.retries
            int r2 = r2 + 1
            r7.retries = r2
            int r2 = r7.retries
            r3 = 2
            if (r2 <= r3) goto L_0x002d
            com.adjust.sdk.ILogger r2 = r7.logger
            java.lang.String r3 = "Limit number of retry for install referrer surpassed"
            java.lang.Object[] r4 = new java.lang.Object[r6]
            r2.debug(r3, r4)
            goto L_0x0013
        L_0x002a:
            r2 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x002a }
            throw r2
        L_0x002d:
            com.adjust.sdk.TimerOnce r2 = r7.retryTimer
            long r0 = r2.getFireIn()
            r2 = 0
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 <= 0) goto L_0x004a
            com.adjust.sdk.ILogger r2 = r7.logger
            java.lang.String r3 = "Already waiting to retry to read install referrer in %d milliseconds"
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.Long r5 = java.lang.Long.valueOf(r0)
            r4[r6] = r5
            r2.debug(r3, r4)
            goto L_0x0013
        L_0x004a:
            com.adjust.sdk.TimerOnce r2 = r7.retryTimer
            int r3 = r7.retryWaitTime
            long r4 = (long) r3
            r2.startIn(r4)
            goto L_0x0013
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adjust.sdk.InstallReferrer.retry():void");
    }

    private void closeReferrerClient() {
        if (this.referrerClient != null) {
            try {
                Reflection.invokeInstanceMethod(this.referrerClient, "endConnection", null, new Object[0]);
            } catch (Exception e) {
                this.logger.error("closeReferrerClient error (%s) thrown by (%s)", e.getMessage(), e.getClass().getCanonicalName());
            }
            this.referrerClient = null;
        }
    }
}
