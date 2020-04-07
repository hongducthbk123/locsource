package com.sensorsdata.analytics.android.sdk;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.sensorsdata.analytics.android.sdk.DbAdapter.Table;
import com.sensorsdata.analytics.android.sdk.exceptions.ConnectErrorException;
import com.sensorsdata.analytics.android.sdk.exceptions.DebugModeException;
import com.sensorsdata.analytics.android.sdk.util.Base64Coder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class AnalyticsMessages {
    private static final int CHECK_CONFIGURE = 4;
    private static final int FLUSH_QUEUE = 3;
    private static final String TAG = "SA.AnalyticsMessages";
    private static final Map<Context, AnalyticsMessages> sInstances = new HashMap();
    private final Context mContext;
    private final DbAdapter mDbAdapter;
    private final Worker mWorker = new Worker();

    private class Worker {
        private Handler mHandler;
        private final Object mHandlerLock = new Object();

        private class AnalyticsMessageHandler extends Handler {
            public AnalyticsMessageHandler(Looper looper) {
                super(looper);
            }

            public void handleMessage(Message msg) {
                try {
                    if (msg.what == 3) {
                        AnalyticsMessages.this.sendData();
                    } else if (msg.what == 4) {
                        DecideMessages decideMessages = (DecideMessages) msg.obj;
                        try {
                            String configureResult = AnalyticsMessages.this.getCheckConfigure();
                            if (configureResult != null && !configureResult.isEmpty()) {
                                try {
                                    JSONObject configureJson = new JSONObject(configureResult);
                                    JSONObject eventBindings = configureJson.optJSONObject("event_bindings");
                                    if (eventBindings != null && eventBindings.has("events") && (eventBindings.get("events") instanceof JSONArray)) {
                                        decideMessages.setEventBindings(eventBindings.getJSONArray("events"));
                                    }
                                    decideMessages.setVTrackServer(configureJson.optString("vtrack_server_url"));
                                } catch (JSONException e) {
                                    SALog.m1608i(AnalyticsMessages.TAG, "Failed to load SDK configure with" + configureResult);
                                }
                            }
                        } catch (ConnectErrorException e2) {
                            SALog.m1609i(AnalyticsMessages.TAG, "Failed to get vtrack configure from SensorsAnalaytics.", e2);
                        }
                    } else {
                        SALog.m1608i(AnalyticsMessages.TAG, "Unexpected message received by SensorsData worker: " + msg);
                    }
                } catch (RuntimeException e3) {
                    SALog.m1609i(AnalyticsMessages.TAG, "Worker threw an unhandled exception", e3);
                }
            }
        }

        public Worker() {
            HandlerThread thread = new HandlerThread("com.sensorsdata.analytics.android.sdk.AnalyticsMessages.Worker", 1);
            thread.start();
            this.mHandler = new AnalyticsMessageHandler(thread.getLooper());
        }

        public void runMessage(Message msg) {
            synchronized (this.mHandlerLock) {
                if (this.mHandler == null) {
                    SALog.m1608i(AnalyticsMessages.TAG, "Dead worker dropping a message: " + msg.what);
                } else {
                    this.mHandler.sendMessage(msg);
                }
            }
        }

        public void runMessageOnce(Message msg, long delay) {
            synchronized (this.mHandlerLock) {
                if (this.mHandler == null) {
                    SALog.m1608i(AnalyticsMessages.TAG, "Dead worker dropping a message: " + msg.what);
                } else if (!this.mHandler.hasMessages(msg.what)) {
                    this.mHandler.sendMessageDelayed(msg, delay);
                }
            }
        }
    }

    AnalyticsMessages(Context context, String packageName) {
        this.mContext = context;
        this.mDbAdapter = new DbAdapter(this.mContext, packageName);
    }

    public static AnalyticsMessages getInstance(Context messageContext, String packageName) {
        AnalyticsMessages ret;
        synchronized (sInstances) {
            Context appContext = messageContext.getApplicationContext();
            if (!sInstances.containsKey(appContext)) {
                ret = new AnalyticsMessages(appContext, packageName);
                sInstances.put(appContext, ret);
            } else {
                ret = (AnalyticsMessages) sInstances.get(appContext);
            }
        }
        return ret;
    }

    public void enqueueEventMessage(String type, JSONObject eventJson) {
        SALog.m1608i(TAG, "enqueueEventMessage");
        try {
            synchronized (this.mDbAdapter) {
                int ret = this.mDbAdapter.addJSON(eventJson, Table.EVENTS);
                if (ret < 0) {
                    String error = "Failed to enqueue the event: " + eventJson;
                    if (SensorsDataAPI.sharedInstance(this.mContext).isDebugMode()) {
                        throw new DebugModeException(error);
                    }
                    SALog.m1608i(TAG, error);
                }
                Message m = Message.obtain();
                m.what = 3;
                if (SensorsDataAPI.sharedInstance(this.mContext).isDebugMode() || ret == -2) {
                    this.mWorker.runMessage(m);
                } else if (type.equals("track_signup") || ret > SensorsDataAPI.sharedInstance(this.mContext).getFlushBulkSize()) {
                    this.mWorker.runMessage(m);
                } else {
                    this.mWorker.runMessageOnce(m, (long) SensorsDataAPI.sharedInstance(this.mContext).getFlushInterval());
                }
            }
        } catch (Exception e) {
            SALog.m1608i(TAG, "enqueueEventMessage error:" + e);
        }
    }

    public void checkConfigure(DecideMessages check) {
        Message m = Message.obtain();
        m.what = 4;
        m.obj = check;
        this.mWorker.runMessage(m);
    }

    public void flush() {
        Message m = Message.obtain();
        m.what = 3;
        this.mWorker.runMessage(m);
    }

    private byte[] slurp(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[8192];
        while (true) {
            int nRead = inputStream.read(data, 0, data.length);
            if (nRead != -1) {
                buffer.write(data, 0, nRead);
            } else {
                buffer.flush();
                return buffer.toByteArray();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:109:?, code lost:
        r22.cancel();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x02ca, code lost:
        r7 = r32.mDbAdapter.cleanupEvents(r15, com.sensorsdata.analytics.android.sdk.DbAdapter.Table.EVENTS);
        com.sensorsdata.analytics.android.sdk.SALog.m1608i(TAG, java.lang.String.format(java.util.Locale.CHINA, "Events flushed. [left = %d]", new java.lang.Object[]{java.lang.Integer.valueOf(r7)}));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:?, code lost:
        r16.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:?, code lost:
        r13.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x0308, code lost:
        r6.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x030d, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:?, code lost:
        r10.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x0313, code lost:
        r10 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:?, code lost:
        r22.cancel();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x0375, code lost:
        r7 = r32.mDbAdapter.cleanupEvents(r15, com.sensorsdata.analytics.android.sdk.DbAdapter.Table.EVENTS);
        com.sensorsdata.analytics.android.sdk.SALog.m1608i(TAG, java.lang.String.format(java.util.Locale.CHINA, "Events flushed. [left = %d]", new java.lang.Object[]{java.lang.Integer.valueOf(r7)}));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:?, code lost:
        r16.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:?, code lost:
        r13.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x03b3, code lost:
        r6.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:0x054a, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x054b, code lost:
        r10.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x0550, code lost:
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x0553, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:0x0554, code lost:
        r10.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x0559, code lost:
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:226:0x055c, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x055d, code lost:
        r10.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x0562, code lost:
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x01b1, code lost:
        r10 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x01ba, code lost:
        r10 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:?, code lost:
        r22.cancel();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x021c, code lost:
        r7 = r32.mDbAdapter.cleanupEvents(r15, com.sensorsdata.analytics.android.sdk.DbAdapter.Table.EVENTS);
        com.sensorsdata.analytics.android.sdk.SALog.m1608i(TAG, java.lang.String.format(java.util.Locale.CHINA, "Events flushed. [left = %d]", new java.lang.Object[]{java.lang.Integer.valueOf(r7)}));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:?, code lost:
        r16.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:?, code lost:
        r13.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x025a, code lost:
        r6.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0268, code lost:
        r10 = e;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x02b2 A[SYNTHETIC, Splitter:B:108:0x02b2] */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x02ca  */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x02f9 A[SYNTHETIC, Splitter:B:114:0x02f9] */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x02fe A[SYNTHETIC, Splitter:B:117:0x02fe] */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x0303 A[SYNTHETIC, Splitter:B:120:0x0303] */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x0308  */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x0313 A[ExcHandler: ResponseErrorException (e com.sensorsdata.analytics.android.sdk.exceptions.ResponseErrorException), PHI: r3 r6 r13 r16 
      PHI: (r3v6 'bout' java.io.BufferedOutputStream) = (r3v0 'bout' java.io.BufferedOutputStream), (r3v11 'bout' java.io.BufferedOutputStream), (r3v0 'bout' java.io.BufferedOutputStream) binds: [B:91:0x0260, B:57:0x01b2, B:21:0x0089] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r6v6 'connection' java.net.HttpURLConnection) = (r6v0 'connection' java.net.HttpURLConnection), (r6v11 'connection' java.net.HttpURLConnection) binds: [B:91:0x0260, B:57:0x01b2] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r13v6 'in' java.io.InputStream) = (r13v0 'in' java.io.InputStream), (r13v11 'in' java.io.InputStream), (r13v0 'in' java.io.InputStream) binds: [B:91:0x0260, B:57:0x01b2, B:21:0x0089] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r16v6 'out' java.io.OutputStream) = (r16v0 'out' java.io.OutputStream), (r16v11 'out' java.io.OutputStream), (r16v0 'out' java.io.OutputStream) binds: [B:91:0x0260, B:57:0x01b2, B:21:0x0089] A[DONT_GENERATE, DONT_INLINE], Splitter:B:21:0x0089] */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x035d A[SYNTHETIC, Splitter:B:141:0x035d] */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x0375  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x03a4 A[SYNTHETIC, Splitter:B:147:0x03a4] */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x03a9 A[SYNTHETIC, Splitter:B:150:0x03a9] */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x03ae A[SYNTHETIC, Splitter:B:153:0x03ae] */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x03b3  */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x045c A[SYNTHETIC, Splitter:B:175:0x045c] */
    /* JADX WARNING: Removed duplicated region for block: B:179:0x0474  */
    /* JADX WARNING: Removed duplicated region for block: B:181:0x04a3 A[SYNTHETIC, Splitter:B:181:0x04a3] */
    /* JADX WARNING: Removed duplicated region for block: B:184:0x04a8 A[SYNTHETIC, Splitter:B:184:0x04a8] */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x04ad A[SYNTHETIC, Splitter:B:187:0x04ad] */
    /* JADX WARNING: Removed duplicated region for block: B:190:0x04b2  */
    /* JADX WARNING: Removed duplicated region for block: B:222:0x0550  */
    /* JADX WARNING: Removed duplicated region for block: B:225:0x0559  */
    /* JADX WARNING: Removed duplicated region for block: B:228:0x0562  */
    /* JADX WARNING: Removed duplicated region for block: B:231:0x056b  */
    /* JADX WARNING: Removed duplicated region for block: B:243:0x05a0 A[SYNTHETIC, Splitter:B:243:0x05a0] */
    /* JADX WARNING: Removed duplicated region for block: B:247:0x05b8  */
    /* JADX WARNING: Removed duplicated region for block: B:249:0x05e7 A[SYNTHETIC, Splitter:B:249:0x05e7] */
    /* JADX WARNING: Removed duplicated region for block: B:252:0x05ec A[SYNTHETIC, Splitter:B:252:0x05ec] */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x05f1 A[SYNTHETIC, Splitter:B:255:0x05f1] */
    /* JADX WARNING: Removed duplicated region for block: B:258:0x05f6  */
    /* JADX WARNING: Removed duplicated region for block: B:262:0x05ff  */
    /* JADX WARNING: Removed duplicated region for block: B:298:0x000b A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:300:0x000b A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:302:0x000b A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:304:0x000b A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x01b1 A[ExcHandler: IOException (e java.io.IOException), PHI: r3 r13 r16 
      PHI: (r3v12 'bout' java.io.BufferedOutputStream) = (r3v0 'bout' java.io.BufferedOutputStream), (r3v0 'bout' java.io.BufferedOutputStream), (r3v0 'bout' java.io.BufferedOutputStream), (r3v0 'bout' java.io.BufferedOutputStream), (r3v0 'bout' java.io.BufferedOutputStream), (r3v0 'bout' java.io.BufferedOutputStream), (r3v19 'bout' java.io.BufferedOutputStream), (r3v19 'bout' java.io.BufferedOutputStream), (r3v19 'bout' java.io.BufferedOutputStream), (r3v19 'bout' java.io.BufferedOutputStream) binds: [B:18:0x006b, B:19:?, B:21:0x0089, B:125:0x030e, B:126:?, B:27:0x00a4, B:41:0x0141, B:42:?, B:158:0x03b9, B:46:0x0150] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r13v12 'in' java.io.InputStream) = (r13v0 'in' java.io.InputStream), (r13v0 'in' java.io.InputStream), (r13v0 'in' java.io.InputStream), (r13v0 'in' java.io.InputStream), (r13v0 'in' java.io.InputStream), (r13v0 'in' java.io.InputStream), (r13v0 'in' java.io.InputStream), (r13v0 'in' java.io.InputStream), (r13v15 'in' java.io.InputStream), (r13v0 'in' java.io.InputStream) binds: [B:18:0x006b, B:19:?, B:21:0x0089, B:125:0x030e, B:126:?, B:27:0x00a4, B:41:0x0141, B:42:?, B:46:0x0150, B:158:0x03b9] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r16v12 'out' java.io.OutputStream) = (r16v0 'out' java.io.OutputStream), (r16v0 'out' java.io.OutputStream), (r16v0 'out' java.io.OutputStream), (r16v0 'out' java.io.OutputStream), (r16v0 'out' java.io.OutputStream), (r16v0 'out' java.io.OutputStream), (r16v13 'out' java.io.OutputStream), (r16v14 'out' java.io.OutputStream), (r16v14 'out' java.io.OutputStream), (r16v14 'out' java.io.OutputStream) binds: [B:18:0x006b, B:19:?, B:21:0x0089, B:125:0x030e, B:126:?, B:27:0x00a4, B:41:0x0141, B:42:?, B:158:0x03b9, B:46:0x0150] A[DONT_GENERATE, DONT_INLINE], Splitter:B:18:0x006b] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x01ba A[ExcHandler: ConnectErrorException (e com.sensorsdata.analytics.android.sdk.exceptions.ConnectErrorException), PHI: r3 r6 r13 r16 
      PHI: (r3v10 'bout' java.io.BufferedOutputStream) = (r3v0 'bout' java.io.BufferedOutputStream), (r3v11 'bout' java.io.BufferedOutputStream), (r3v0 'bout' java.io.BufferedOutputStream) binds: [B:91:0x0260, B:57:0x01b2, B:21:0x0089] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r6v10 'connection' java.net.HttpURLConnection) = (r6v0 'connection' java.net.HttpURLConnection), (r6v11 'connection' java.net.HttpURLConnection) binds: [B:91:0x0260, B:57:0x01b2] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r13v10 'in' java.io.InputStream) = (r13v0 'in' java.io.InputStream), (r13v11 'in' java.io.InputStream), (r13v0 'in' java.io.InputStream) binds: [B:91:0x0260, B:57:0x01b2, B:21:0x0089] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r16v10 'out' java.io.OutputStream) = (r16v0 'out' java.io.OutputStream), (r16v11 'out' java.io.OutputStream), (r16v0 'out' java.io.OutputStream) binds: [B:91:0x0260, B:57:0x01b2, B:21:0x0089] A[DONT_GENERATE, DONT_INLINE], Splitter:B:21:0x0089] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0204 A[SYNTHETIC, Splitter:B:74:0x0204] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x021c  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x024b A[SYNTHETIC, Splitter:B:80:0x024b] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0250 A[SYNTHETIC, Splitter:B:83:0x0250] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0255 A[SYNTHETIC, Splitter:B:86:0x0255] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x025a  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0268 A[ExcHandler: InvalidDataException (e com.sensorsdata.analytics.android.sdk.exceptions.InvalidDataException), PHI: r3 r6 r13 r16 
      PHI: (r3v8 'bout' java.io.BufferedOutputStream) = (r3v0 'bout' java.io.BufferedOutputStream), (r3v11 'bout' java.io.BufferedOutputStream), (r3v0 'bout' java.io.BufferedOutputStream) binds: [B:91:0x0260, B:57:0x01b2, B:21:0x0089] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r6v8 'connection' java.net.HttpURLConnection) = (r6v0 'connection' java.net.HttpURLConnection), (r6v11 'connection' java.net.HttpURLConnection) binds: [B:91:0x0260, B:57:0x01b2] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r13v8 'in' java.io.InputStream) = (r13v0 'in' java.io.InputStream), (r13v11 'in' java.io.InputStream), (r13v0 'in' java.io.InputStream) binds: [B:91:0x0260, B:57:0x01b2, B:21:0x0089] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r16v8 'out' java.io.OutputStream) = (r16v0 'out' java.io.OutputStream), (r16v11 'out' java.io.OutputStream), (r16v0 'out' java.io.OutputStream) binds: [B:91:0x0260, B:57:0x01b2, B:21:0x0089] A[DONT_GENERATE, DONT_INLINE], Splitter:B:21:0x0089] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void sendData() {
        /*
            r32 = this;
            java.lang.String r25 = "SA.AnalyticsMessages"
            java.lang.String r26 = "sendData"
            com.sensorsdata.analytics.android.sdk.SALog.m1608i(r25, r26)
            r7 = 100
            r22 = 0
        L_0x000b:
            if (r7 <= 0) goto L_0x0041
            r9 = 1
            r13 = 0
            r16 = 0
            r3 = 0
            r6 = 0
            r0 = r32
            com.sensorsdata.analytics.android.sdk.DbAdapter r0 = r0.mDbAdapter
            r26 = r0
            monitor-enter(r26)
            r0 = r32
            android.content.Context r0 = r0.mContext     // Catch:{ all -> 0x0057 }
            r25 = r0
            com.sensorsdata.analytics.android.sdk.SensorsDataAPI r25 = com.sensorsdata.analytics.android.sdk.SensorsDataAPI.sharedInstance(r25)     // Catch:{ all -> 0x0057 }
            boolean r25 = r25.isDebugMode()     // Catch:{ all -> 0x0057 }
            if (r25 == 0) goto L_0x0042
            r0 = r32
            com.sensorsdata.analytics.android.sdk.DbAdapter r0 = r0.mDbAdapter     // Catch:{ all -> 0x0057 }
            r25 = r0
            com.sensorsdata.analytics.android.sdk.DbAdapter$Table r27 = com.sensorsdata.analytics.android.sdk.DbAdapter.Table.EVENTS     // Catch:{ all -> 0x0057 }
            r28 = 1
            r0 = r25
            r1 = r27
            r2 = r28
            java.lang.String[] r12 = r0.generateDataString(r1, r2)     // Catch:{ all -> 0x0057 }
        L_0x003e:
            monitor-exit(r26)     // Catch:{ all -> 0x0057 }
            if (r12 != 0) goto L_0x005a
        L_0x0041:
            return
        L_0x0042:
            r0 = r32
            com.sensorsdata.analytics.android.sdk.DbAdapter r0 = r0.mDbAdapter     // Catch:{ all -> 0x0057 }
            r25 = r0
            com.sensorsdata.analytics.android.sdk.DbAdapter$Table r27 = com.sensorsdata.analytics.android.sdk.DbAdapter.Table.EVENTS     // Catch:{ all -> 0x0057 }
            r28 = 50
            r0 = r25
            r1 = r27
            r2 = r28
            java.lang.String[] r12 = r0.generateDataString(r1, r2)     // Catch:{ all -> 0x0057 }
            goto L_0x003e
        L_0x0057:
            r25 = move-exception
            monitor-exit(r26)     // Catch:{ all -> 0x0057 }
            throw r25
        L_0x005a:
            r25 = 0
            r15 = r12[r25]
            r25 = 1
            r18 = r12[r25]
            r11 = 0
            r0 = r32
            r1 = r18
            java.lang.String r8 = r0.encodeData(r1)     // Catch:{ IOException -> 0x025f }
            java.net.URL r24 = new java.net.URL     // Catch:{ IOException -> 0x01b1 }
            r0 = r32
            android.content.Context r0 = r0.mContext     // Catch:{ IOException -> 0x01b1 }
            r25 = r0
            com.sensorsdata.analytics.android.sdk.SensorsDataAPI r25 = com.sensorsdata.analytics.android.sdk.SensorsDataAPI.sharedInstance(r25)     // Catch:{ IOException -> 0x01b1 }
            java.lang.String r25 = r25.getServerUrl()     // Catch:{ IOException -> 0x01b1 }
            r24.<init>(r25)     // Catch:{ IOException -> 0x01b1 }
            java.net.URLConnection r25 = r24.openConnection()     // Catch:{ IOException -> 0x01b1 }
            r0 = r25
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ IOException -> 0x01b1 }
            r6 = r0
            r0 = r32
            android.content.Context r0 = r0.mContext     // Catch:{ Exception -> 0x030d, IOException -> 0x01b1, ConnectErrorException -> 0x01ba, InvalidDataException -> 0x0268, ResponseErrorException -> 0x0313 }
            r25 = r0
            java.lang.String r23 = com.sensorsdata.analytics.android.sdk.util.SensorsDataUtils.getUserAgent(r25)     // Catch:{ Exception -> 0x030d, IOException -> 0x01b1, ConnectErrorException -> 0x01ba, InvalidDataException -> 0x0268, ResponseErrorException -> 0x0313 }
            boolean r25 = android.text.TextUtils.isEmpty(r23)     // Catch:{ Exception -> 0x030d, IOException -> 0x01b1, ConnectErrorException -> 0x01ba, InvalidDataException -> 0x0268, ResponseErrorException -> 0x0313 }
            if (r25 == 0) goto L_0x0099
            java.lang.String r23 = "SensorsAnalytics Android SDK"
        L_0x0099:
            java.lang.String r25 = "User-Agent"
            r0 = r25
            r1 = r23
            r6.addRequestProperty(r0, r1)     // Catch:{ Exception -> 0x030d, IOException -> 0x01b1, ConnectErrorException -> 0x01ba, InvalidDataException -> 0x0268, ResponseErrorException -> 0x0313 }
        L_0x00a2:
            r0 = r32
            android.content.Context r0 = r0.mContext     // Catch:{ IOException -> 0x01b1 }
            r25 = r0
            com.sensorsdata.analytics.android.sdk.SensorsDataAPI r25 = com.sensorsdata.analytics.android.sdk.SensorsDataAPI.sharedInstance(r25)     // Catch:{ IOException -> 0x01b1 }
            boolean r25 = r25.isDebugMode()     // Catch:{ IOException -> 0x01b1 }
            if (r25 == 0) goto L_0x00ce
            r0 = r32
            android.content.Context r0 = r0.mContext     // Catch:{ IOException -> 0x01b1 }
            r25 = r0
            com.sensorsdata.analytics.android.sdk.SensorsDataAPI r25 = com.sensorsdata.analytics.android.sdk.SensorsDataAPI.sharedInstance(r25)     // Catch:{ IOException -> 0x01b1 }
            boolean r25 = r25.isDebugWriteData()     // Catch:{ IOException -> 0x01b1 }
            if (r25 != 0) goto L_0x00ce
            java.lang.String r25 = "Dry-Run"
            java.lang.String r26 = "true"
            r0 = r25
            r1 = r26
            r6.addRequestProperty(r0, r1)     // Catch:{ IOException -> 0x01b1 }
        L_0x00ce:
            android.net.Uri$Builder r5 = new android.net.Uri$Builder     // Catch:{ IOException -> 0x01b1 }
            r5.<init>()     // Catch:{ IOException -> 0x01b1 }
            java.lang.String r25 = "data_list"
            r0 = r25
            r5.appendQueryParameter(r0, r8)     // Catch:{ IOException -> 0x01b1 }
            java.lang.String r25 = "gzip"
            java.lang.String r26 = "1"
            r0 = r25
            r1 = r26
            r5.appendQueryParameter(r0, r1)     // Catch:{ IOException -> 0x01b1 }
            boolean r25 = android.text.TextUtils.isEmpty(r8)     // Catch:{ IOException -> 0x01b1 }
            if (r25 != 0) goto L_0x00fc
            java.lang.String r25 = "crc"
            int r26 = r8.hashCode()     // Catch:{ IOException -> 0x01b1 }
            java.lang.String r26 = java.lang.String.valueOf(r26)     // Catch:{ IOException -> 0x01b1 }
            r0 = r25
            r1 = r26
            r5.appendQueryParameter(r0, r1)     // Catch:{ IOException -> 0x01b1 }
        L_0x00fc:
            android.net.Uri r25 = r5.build()     // Catch:{ IOException -> 0x01b1 }
            java.lang.String r17 = r25.getEncodedQuery()     // Catch:{ IOException -> 0x01b1 }
            byte[] r25 = r17.getBytes()     // Catch:{ IOException -> 0x01b1 }
            r0 = r25
            int r0 = r0.length     // Catch:{ IOException -> 0x01b1 }
            r25 = r0
            r0 = r25
            r6.setFixedLengthStreamingMode(r0)     // Catch:{ IOException -> 0x01b1 }
            r25 = 1
            r0 = r25
            r6.setDoOutput(r0)     // Catch:{ IOException -> 0x01b1 }
            java.lang.String r25 = "POST"
            r0 = r25
            r6.setRequestMethod(r0)     // Catch:{ IOException -> 0x01b1 }
            java.io.OutputStream r16 = r6.getOutputStream()     // Catch:{ IOException -> 0x01b1 }
            java.io.BufferedOutputStream r4 = new java.io.BufferedOutputStream     // Catch:{ IOException -> 0x01b1 }
            r0 = r16
            r4.<init>(r0)     // Catch:{ IOException -> 0x01b1 }
            java.lang.String r25 = "UTF-8"
            r0 = r17
            r1 = r25
            byte[] r25 = r0.getBytes(r1)     // Catch:{ IOException -> 0x0648, ConnectErrorException -> 0x0644, InvalidDataException -> 0x0640, ResponseErrorException -> 0x063c, Exception -> 0x0638, all -> 0x0634 }
            r0 = r25
            r4.write(r0)     // Catch:{ IOException -> 0x0648, ConnectErrorException -> 0x0644, InvalidDataException -> 0x0640, ResponseErrorException -> 0x063c, Exception -> 0x0638, all -> 0x0634 }
            r4.flush()     // Catch:{ IOException -> 0x0648, ConnectErrorException -> 0x0644, InvalidDataException -> 0x0640, ResponseErrorException -> 0x063c, Exception -> 0x0638, all -> 0x0634 }
            r4.close()     // Catch:{ IOException -> 0x0648, ConnectErrorException -> 0x0644, InvalidDataException -> 0x0640, ResponseErrorException -> 0x063c, Exception -> 0x0638, all -> 0x0634 }
            r3 = 0
            r16.close()     // Catch:{ IOException -> 0x01b1 }
            r16 = 0
            int r21 = r6.getResponseCode()     // Catch:{ IOException -> 0x01b1 }
            java.io.InputStream r13 = r6.getInputStream()     // Catch:{ FileNotFoundException -> 0x03b8 }
        L_0x014e:
            r0 = r32
            byte[] r20 = r0.slurp(r13)     // Catch:{ IOException -> 0x01b1 }
            r13.close()     // Catch:{ IOException -> 0x01b1 }
            r13 = 0
            java.lang.String r19 = new java.lang.String     // Catch:{ IOException -> 0x01b1 }
            java.lang.String r25 = "UTF-8"
            r0 = r19
            r1 = r20
            r2 = r25
            r0.<init>(r1, r2)     // Catch:{ IOException -> 0x01b1 }
            r25 = 200(0xc8, float:2.8E-43)
            r0 = r21
            r1 = r25
            if (r0 != r1) goto L_0x03bf
            java.lang.String r25 = "SA.AnalyticsMessages"
            java.lang.String r26 = "valid message: \n%s"
            r27 = 1
            r0 = r27
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ IOException -> 0x01b1 }
            r27 = r0
            r28 = 0
            java.lang.String r29 = com.sensorsdata.analytics.android.sdk.util.JSONUtils.formatJson(r18)     // Catch:{ IOException -> 0x01b1 }
            r27[r28] = r29     // Catch:{ IOException -> 0x01b1 }
            java.lang.String r26 = java.lang.String.format(r26, r27)     // Catch:{ IOException -> 0x01b1 }
            com.sensorsdata.analytics.android.sdk.SALog.m1608i(r25, r26)     // Catch:{ IOException -> 0x01b1 }
        L_0x0189:
            r25 = 200(0xc8, float:2.8E-43)
            r0 = r21
            r1 = r25
            if (r0 < r1) goto L_0x0199
            r25 = 300(0x12c, float:4.2E-43)
            r0 = r21
            r1 = r25
            if (r0 < r1) goto L_0x04b7
        L_0x0199:
            com.sensorsdata.analytics.android.sdk.exceptions.ResponseErrorException r25 = new com.sensorsdata.analytics.android.sdk.exceptions.ResponseErrorException     // Catch:{ IOException -> 0x01b1 }
            java.lang.String r26 = "flush failure with response '%s'"
            r27 = 1
            r0 = r27
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ IOException -> 0x01b1 }
            r27 = r0
            r28 = 0
            r27[r28] = r19     // Catch:{ IOException -> 0x01b1 }
            java.lang.String r26 = java.lang.String.format(r26, r27)     // Catch:{ IOException -> 0x01b1 }
            r25.<init>(r26)     // Catch:{ IOException -> 0x01b1 }
            throw r25     // Catch:{ IOException -> 0x01b1 }
        L_0x01b1:
            r10 = move-exception
        L_0x01b2:
            com.sensorsdata.analytics.android.sdk.exceptions.ConnectErrorException r25 = new com.sensorsdata.analytics.android.sdk.exceptions.ConnectErrorException     // Catch:{ ConnectErrorException -> 0x01ba, InvalidDataException -> 0x0268, ResponseErrorException -> 0x0313, Exception -> 0x0412 }
            r0 = r25
            r0.<init>(r10)     // Catch:{ ConnectErrorException -> 0x01ba, InvalidDataException -> 0x0268, ResponseErrorException -> 0x0313, Exception -> 0x0412 }
            throw r25     // Catch:{ ConnectErrorException -> 0x01ba, InvalidDataException -> 0x0268, ResponseErrorException -> 0x0313, Exception -> 0x0412 }
        L_0x01ba:
            r10 = move-exception
        L_0x01bb:
            r9 = 0
            java.lang.StringBuilder r25 = new java.lang.StringBuilder     // Catch:{ all -> 0x056e }
            r25.<init>()     // Catch:{ all -> 0x056e }
            java.lang.String r26 = "Connection error: "
            java.lang.StringBuilder r25 = r25.append(r26)     // Catch:{ all -> 0x056e }
            java.lang.String r26 = r10.getMessage()     // Catch:{ all -> 0x056e }
            java.lang.StringBuilder r25 = r25.append(r26)     // Catch:{ all -> 0x056e }
            java.lang.String r11 = r25.toString()     // Catch:{ all -> 0x056e }
            r0 = r32
            android.content.Context r0 = r0.mContext
            r25 = r0
            com.sensorsdata.analytics.android.sdk.SensorsDataAPI r25 = com.sensorsdata.analytics.android.sdk.SensorsDataAPI.sharedInstance(r25)
            boolean r14 = r25.isDebugMode()
            boolean r25 = android.text.TextUtils.isEmpty(r11)
            if (r25 != 0) goto L_0x021a
            if (r14 != 0) goto L_0x01f1
            java.lang.Boolean r25 = com.sensorsdata.analytics.android.sdk.SensorsDataAPI.ENABLE_LOG
            boolean r25 = r25.booleanValue()
            if (r25 == 0) goto L_0x021a
        L_0x01f1:
            java.lang.String r25 = "SA.AnalyticsMessages"
            r0 = r25
            com.sensorsdata.analytics.android.sdk.SALog.m1608i(r0, r11)
            if (r14 == 0) goto L_0x021a
            java.lang.Boolean r25 = com.sensorsdata.analytics.android.sdk.SensorsDataAPI.SHOW_DEBUG_INFO_VIEW
            boolean r25 = r25.booleanValue()
            if (r25 == 0) goto L_0x021a
            if (r22 == 0) goto L_0x0207
            r22.cancel()     // Catch:{ Exception -> 0x054a }
        L_0x0207:
            r0 = r32
            android.content.Context r0 = r0.mContext     // Catch:{ Exception -> 0x054a }
            r25 = r0
            r26 = 0
            r0 = r25
            r1 = r26
            android.widget.Toast r22 = android.widget.Toast.makeText(r0, r11, r1)     // Catch:{ Exception -> 0x054a }
            r22.show()     // Catch:{ Exception -> 0x054a }
        L_0x021a:
            if (r9 == 0) goto L_0x0550
            r0 = r32
            com.sensorsdata.analytics.android.sdk.DbAdapter r0 = r0.mDbAdapter
            r25 = r0
            com.sensorsdata.analytics.android.sdk.DbAdapter$Table r26 = com.sensorsdata.analytics.android.sdk.DbAdapter.Table.EVENTS
            r0 = r25
            r1 = r26
            int r7 = r0.cleanupEvents(r15, r1)
            java.lang.String r25 = "SA.AnalyticsMessages"
            java.util.Locale r26 = java.util.Locale.CHINA
            java.lang.String r27 = "Events flushed. [left = %d]"
            r28 = 1
            r0 = r28
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r28 = r0
            r29 = 0
            java.lang.Integer r30 = java.lang.Integer.valueOf(r7)
            r28[r29] = r30
            java.lang.String r26 = java.lang.String.format(r26, r27, r28)
            com.sensorsdata.analytics.android.sdk.SALog.m1608i(r25, r26)
        L_0x0249:
            if (r3 == 0) goto L_0x024e
            r3.close()     // Catch:{ IOException -> 0x060a }
        L_0x024e:
            if (r16 == 0) goto L_0x0253
            r16.close()     // Catch:{ IOException -> 0x060d }
        L_0x0253:
            if (r13 == 0) goto L_0x0258
            r13.close()     // Catch:{ IOException -> 0x0610 }
        L_0x0258:
            if (r6 == 0) goto L_0x000b
            r6.disconnect()
            goto L_0x000b
        L_0x025f:
            r10 = move-exception
            com.sensorsdata.analytics.android.sdk.exceptions.InvalidDataException r25 = new com.sensorsdata.analytics.android.sdk.exceptions.InvalidDataException     // Catch:{ ConnectErrorException -> 0x01ba, InvalidDataException -> 0x0268, ResponseErrorException -> 0x0313, Exception -> 0x0412 }
            r0 = r25
            r0.<init>(r10)     // Catch:{ ConnectErrorException -> 0x01ba, InvalidDataException -> 0x0268, ResponseErrorException -> 0x0313, Exception -> 0x0412 }
            throw r25     // Catch:{ ConnectErrorException -> 0x01ba, InvalidDataException -> 0x0268, ResponseErrorException -> 0x0313, Exception -> 0x0412 }
        L_0x0268:
            r10 = move-exception
        L_0x0269:
            r9 = 1
            java.lang.StringBuilder r25 = new java.lang.StringBuilder     // Catch:{ all -> 0x056e }
            r25.<init>()     // Catch:{ all -> 0x056e }
            java.lang.String r26 = "Invalid data: "
            java.lang.StringBuilder r25 = r25.append(r26)     // Catch:{ all -> 0x056e }
            java.lang.String r26 = r10.getMessage()     // Catch:{ all -> 0x056e }
            java.lang.StringBuilder r25 = r25.append(r26)     // Catch:{ all -> 0x056e }
            java.lang.String r11 = r25.toString()     // Catch:{ all -> 0x056e }
            r0 = r32
            android.content.Context r0 = r0.mContext
            r25 = r0
            com.sensorsdata.analytics.android.sdk.SensorsDataAPI r25 = com.sensorsdata.analytics.android.sdk.SensorsDataAPI.sharedInstance(r25)
            boolean r14 = r25.isDebugMode()
            boolean r25 = android.text.TextUtils.isEmpty(r11)
            if (r25 != 0) goto L_0x02c8
            if (r14 != 0) goto L_0x029f
            java.lang.Boolean r25 = com.sensorsdata.analytics.android.sdk.SensorsDataAPI.ENABLE_LOG
            boolean r25 = r25.booleanValue()
            if (r25 == 0) goto L_0x02c8
        L_0x029f:
            java.lang.String r25 = "SA.AnalyticsMessages"
            r0 = r25
            com.sensorsdata.analytics.android.sdk.SALog.m1608i(r0, r11)
            if (r14 == 0) goto L_0x02c8
            java.lang.Boolean r25 = com.sensorsdata.analytics.android.sdk.SensorsDataAPI.SHOW_DEBUG_INFO_VIEW
            boolean r25 = r25.booleanValue()
            if (r25 == 0) goto L_0x02c8
            if (r22 == 0) goto L_0x02b5
            r22.cancel()     // Catch:{ Exception -> 0x0553 }
        L_0x02b5:
            r0 = r32
            android.content.Context r0 = r0.mContext     // Catch:{ Exception -> 0x0553 }
            r25 = r0
            r26 = 0
            r0 = r25
            r1 = r26
            android.widget.Toast r22 = android.widget.Toast.makeText(r0, r11, r1)     // Catch:{ Exception -> 0x0553 }
            r22.show()     // Catch:{ Exception -> 0x0553 }
        L_0x02c8:
            if (r9 == 0) goto L_0x0559
            r0 = r32
            com.sensorsdata.analytics.android.sdk.DbAdapter r0 = r0.mDbAdapter
            r25 = r0
            com.sensorsdata.analytics.android.sdk.DbAdapter$Table r26 = com.sensorsdata.analytics.android.sdk.DbAdapter.Table.EVENTS
            r0 = r25
            r1 = r26
            int r7 = r0.cleanupEvents(r15, r1)
            java.lang.String r25 = "SA.AnalyticsMessages"
            java.util.Locale r26 = java.util.Locale.CHINA
            java.lang.String r27 = "Events flushed. [left = %d]"
            r28 = 1
            r0 = r28
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r28 = r0
            r29 = 0
            java.lang.Integer r30 = java.lang.Integer.valueOf(r7)
            r28[r29] = r30
            java.lang.String r26 = java.lang.String.format(r26, r27, r28)
            com.sensorsdata.analytics.android.sdk.SALog.m1608i(r25, r26)
        L_0x02f7:
            if (r3 == 0) goto L_0x02fc
            r3.close()     // Catch:{ IOException -> 0x0613 }
        L_0x02fc:
            if (r16 == 0) goto L_0x0301
            r16.close()     // Catch:{ IOException -> 0x0616 }
        L_0x0301:
            if (r13 == 0) goto L_0x0306
            r13.close()     // Catch:{ IOException -> 0x0619 }
        L_0x0306:
            if (r6 == 0) goto L_0x000b
            r6.disconnect()
            goto L_0x000b
        L_0x030d:
            r10 = move-exception
            r10.printStackTrace()     // Catch:{ IOException -> 0x01b1 }
            goto L_0x00a2
        L_0x0313:
            r10 = move-exception
        L_0x0314:
            r9 = 1
            java.lang.StringBuilder r25 = new java.lang.StringBuilder     // Catch:{ all -> 0x056e }
            r25.<init>()     // Catch:{ all -> 0x056e }
            java.lang.String r26 = "ResponseErrorException: "
            java.lang.StringBuilder r25 = r25.append(r26)     // Catch:{ all -> 0x056e }
            java.lang.String r26 = r10.getMessage()     // Catch:{ all -> 0x056e }
            java.lang.StringBuilder r25 = r25.append(r26)     // Catch:{ all -> 0x056e }
            java.lang.String r11 = r25.toString()     // Catch:{ all -> 0x056e }
            r0 = r32
            android.content.Context r0 = r0.mContext
            r25 = r0
            com.sensorsdata.analytics.android.sdk.SensorsDataAPI r25 = com.sensorsdata.analytics.android.sdk.SensorsDataAPI.sharedInstance(r25)
            boolean r14 = r25.isDebugMode()
            boolean r25 = android.text.TextUtils.isEmpty(r11)
            if (r25 != 0) goto L_0x0373
            if (r14 != 0) goto L_0x034a
            java.lang.Boolean r25 = com.sensorsdata.analytics.android.sdk.SensorsDataAPI.ENABLE_LOG
            boolean r25 = r25.booleanValue()
            if (r25 == 0) goto L_0x0373
        L_0x034a:
            java.lang.String r25 = "SA.AnalyticsMessages"
            r0 = r25
            com.sensorsdata.analytics.android.sdk.SALog.m1608i(r0, r11)
            if (r14 == 0) goto L_0x0373
            java.lang.Boolean r25 = com.sensorsdata.analytics.android.sdk.SensorsDataAPI.SHOW_DEBUG_INFO_VIEW
            boolean r25 = r25.booleanValue()
            if (r25 == 0) goto L_0x0373
            if (r22 == 0) goto L_0x0360
            r22.cancel()     // Catch:{ Exception -> 0x055c }
        L_0x0360:
            r0 = r32
            android.content.Context r0 = r0.mContext     // Catch:{ Exception -> 0x055c }
            r25 = r0
            r26 = 0
            r0 = r25
            r1 = r26
            android.widget.Toast r22 = android.widget.Toast.makeText(r0, r11, r1)     // Catch:{ Exception -> 0x055c }
            r22.show()     // Catch:{ Exception -> 0x055c }
        L_0x0373:
            if (r9 == 0) goto L_0x0562
            r0 = r32
            com.sensorsdata.analytics.android.sdk.DbAdapter r0 = r0.mDbAdapter
            r25 = r0
            com.sensorsdata.analytics.android.sdk.DbAdapter$Table r26 = com.sensorsdata.analytics.android.sdk.DbAdapter.Table.EVENTS
            r0 = r25
            r1 = r26
            int r7 = r0.cleanupEvents(r15, r1)
            java.lang.String r25 = "SA.AnalyticsMessages"
            java.util.Locale r26 = java.util.Locale.CHINA
            java.lang.String r27 = "Events flushed. [left = %d]"
            r28 = 1
            r0 = r28
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r28 = r0
            r29 = 0
            java.lang.Integer r30 = java.lang.Integer.valueOf(r7)
            r28[r29] = r30
            java.lang.String r26 = java.lang.String.format(r26, r27, r28)
            com.sensorsdata.analytics.android.sdk.SALog.m1608i(r25, r26)
        L_0x03a2:
            if (r3 == 0) goto L_0x03a7
            r3.close()     // Catch:{ IOException -> 0x061c }
        L_0x03a7:
            if (r16 == 0) goto L_0x03ac
            r16.close()     // Catch:{ IOException -> 0x061f }
        L_0x03ac:
            if (r13 == 0) goto L_0x03b1
            r13.close()     // Catch:{ IOException -> 0x0622 }
        L_0x03b1:
            if (r6 == 0) goto L_0x000b
            r6.disconnect()
            goto L_0x000b
        L_0x03b8:
            r10 = move-exception
            java.io.InputStream r13 = r6.getErrorStream()     // Catch:{ IOException -> 0x01b1 }
            goto L_0x014e
        L_0x03bf:
            java.lang.String r25 = "SA.AnalyticsMessages"
            java.lang.String r26 = "invalid message: \n%s"
            r27 = 1
            r0 = r27
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ IOException -> 0x01b1 }
            r27 = r0
            r28 = 0
            java.lang.String r29 = com.sensorsdata.analytics.android.sdk.util.JSONUtils.formatJson(r18)     // Catch:{ IOException -> 0x01b1 }
            r27[r28] = r29     // Catch:{ IOException -> 0x01b1 }
            java.lang.String r26 = java.lang.String.format(r26, r27)     // Catch:{ IOException -> 0x01b1 }
            com.sensorsdata.analytics.android.sdk.SALog.m1608i(r25, r26)     // Catch:{ IOException -> 0x01b1 }
            java.lang.String r25 = "SA.AnalyticsMessages"
            java.util.Locale r26 = java.util.Locale.CHINA     // Catch:{ IOException -> 0x01b1 }
            java.lang.String r27 = "ret_code: %d"
            r28 = 1
            r0 = r28
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ IOException -> 0x01b1 }
            r28 = r0
            r29 = 0
            java.lang.Integer r30 = java.lang.Integer.valueOf(r21)     // Catch:{ IOException -> 0x01b1 }
            r28[r29] = r30     // Catch:{ IOException -> 0x01b1 }
            java.lang.String r26 = java.lang.String.format(r26, r27, r28)     // Catch:{ IOException -> 0x01b1 }
            com.sensorsdata.analytics.android.sdk.SALog.m1608i(r25, r26)     // Catch:{ IOException -> 0x01b1 }
            java.lang.String r25 = "SA.AnalyticsMessages"
            java.util.Locale r26 = java.util.Locale.CHINA     // Catch:{ IOException -> 0x01b1 }
            java.lang.String r27 = "ret_content: %s"
            r28 = 1
            r0 = r28
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ IOException -> 0x01b1 }
            r28 = r0
            r29 = 0
            r28[r29] = r19     // Catch:{ IOException -> 0x01b1 }
            java.lang.String r26 = java.lang.String.format(r26, r27, r28)     // Catch:{ IOException -> 0x01b1 }
            com.sensorsdata.analytics.android.sdk.SALog.m1608i(r25, r26)     // Catch:{ IOException -> 0x01b1 }
            goto L_0x0189
        L_0x0412:
            r10 = move-exception
        L_0x0413:
            r9 = 0
            java.lang.StringBuilder r25 = new java.lang.StringBuilder     // Catch:{ all -> 0x056e }
            r25.<init>()     // Catch:{ all -> 0x056e }
            java.lang.String r26 = "Exception: "
            java.lang.StringBuilder r25 = r25.append(r26)     // Catch:{ all -> 0x056e }
            java.lang.String r26 = r10.getMessage()     // Catch:{ all -> 0x056e }
            java.lang.StringBuilder r25 = r25.append(r26)     // Catch:{ all -> 0x056e }
            java.lang.String r11 = r25.toString()     // Catch:{ all -> 0x056e }
            r0 = r32
            android.content.Context r0 = r0.mContext
            r25 = r0
            com.sensorsdata.analytics.android.sdk.SensorsDataAPI r25 = com.sensorsdata.analytics.android.sdk.SensorsDataAPI.sharedInstance(r25)
            boolean r14 = r25.isDebugMode()
            boolean r25 = android.text.TextUtils.isEmpty(r11)
            if (r25 != 0) goto L_0x0472
            if (r14 != 0) goto L_0x0449
            java.lang.Boolean r25 = com.sensorsdata.analytics.android.sdk.SensorsDataAPI.ENABLE_LOG
            boolean r25 = r25.booleanValue()
            if (r25 == 0) goto L_0x0472
        L_0x0449:
            java.lang.String r25 = "SA.AnalyticsMessages"
            r0 = r25
            com.sensorsdata.analytics.android.sdk.SALog.m1608i(r0, r11)
            if (r14 == 0) goto L_0x0472
            java.lang.Boolean r25 = com.sensorsdata.analytics.android.sdk.SensorsDataAPI.SHOW_DEBUG_INFO_VIEW
            boolean r25 = r25.booleanValue()
            if (r25 == 0) goto L_0x0472
            if (r22 == 0) goto L_0x045f
            r22.cancel()     // Catch:{ Exception -> 0x0565 }
        L_0x045f:
            r0 = r32
            android.content.Context r0 = r0.mContext     // Catch:{ Exception -> 0x0565 }
            r25 = r0
            r26 = 0
            r0 = r25
            r1 = r26
            android.widget.Toast r22 = android.widget.Toast.makeText(r0, r11, r1)     // Catch:{ Exception -> 0x0565 }
            r22.show()     // Catch:{ Exception -> 0x0565 }
        L_0x0472:
            if (r9 == 0) goto L_0x056b
            r0 = r32
            com.sensorsdata.analytics.android.sdk.DbAdapter r0 = r0.mDbAdapter
            r25 = r0
            com.sensorsdata.analytics.android.sdk.DbAdapter$Table r26 = com.sensorsdata.analytics.android.sdk.DbAdapter.Table.EVENTS
            r0 = r25
            r1 = r26
            int r7 = r0.cleanupEvents(r15, r1)
            java.lang.String r25 = "SA.AnalyticsMessages"
            java.util.Locale r26 = java.util.Locale.CHINA
            java.lang.String r27 = "Events flushed. [left = %d]"
            r28 = 1
            r0 = r28
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r28 = r0
            r29 = 0
            java.lang.Integer r30 = java.lang.Integer.valueOf(r7)
            r28[r29] = r30
            java.lang.String r26 = java.lang.String.format(r26, r27, r28)
            com.sensorsdata.analytics.android.sdk.SALog.m1608i(r25, r26)
        L_0x04a1:
            if (r3 == 0) goto L_0x04a6
            r3.close()     // Catch:{ IOException -> 0x0625 }
        L_0x04a6:
            if (r16 == 0) goto L_0x04ab
            r16.close()     // Catch:{ IOException -> 0x0628 }
        L_0x04ab:
            if (r13 == 0) goto L_0x04b0
            r13.close()     // Catch:{ IOException -> 0x062b }
        L_0x04b0:
            if (r6 == 0) goto L_0x000b
            r6.disconnect()
            goto L_0x000b
        L_0x04b7:
            r0 = r32
            android.content.Context r0 = r0.mContext
            r25 = r0
            com.sensorsdata.analytics.android.sdk.SensorsDataAPI r25 = com.sensorsdata.analytics.android.sdk.SensorsDataAPI.sharedInstance(r25)
            boolean r14 = r25.isDebugMode()
            boolean r25 = android.text.TextUtils.isEmpty(r11)
            if (r25 != 0) goto L_0x04fe
            if (r14 != 0) goto L_0x04d5
            java.lang.Boolean r25 = com.sensorsdata.analytics.android.sdk.SensorsDataAPI.ENABLE_LOG
            boolean r25 = r25.booleanValue()
            if (r25 == 0) goto L_0x04fe
        L_0x04d5:
            java.lang.String r25 = "SA.AnalyticsMessages"
            r0 = r25
            com.sensorsdata.analytics.android.sdk.SALog.m1608i(r0, r11)
            if (r14 == 0) goto L_0x04fe
            java.lang.Boolean r25 = com.sensorsdata.analytics.android.sdk.SensorsDataAPI.SHOW_DEBUG_INFO_VIEW
            boolean r25 = r25.booleanValue()
            if (r25 == 0) goto L_0x04fe
            if (r22 == 0) goto L_0x04eb
            r22.cancel()     // Catch:{ Exception -> 0x0543 }
        L_0x04eb:
            r0 = r32
            android.content.Context r0 = r0.mContext     // Catch:{ Exception -> 0x0543 }
            r25 = r0
            r26 = 0
            r0 = r25
            r1 = r26
            android.widget.Toast r22 = android.widget.Toast.makeText(r0, r11, r1)     // Catch:{ Exception -> 0x0543 }
            r22.show()     // Catch:{ Exception -> 0x0543 }
        L_0x04fe:
            if (r9 == 0) goto L_0x0548
            r0 = r32
            com.sensorsdata.analytics.android.sdk.DbAdapter r0 = r0.mDbAdapter
            r25 = r0
            com.sensorsdata.analytics.android.sdk.DbAdapter$Table r26 = com.sensorsdata.analytics.android.sdk.DbAdapter.Table.EVENTS
            r0 = r25
            r1 = r26
            int r7 = r0.cleanupEvents(r15, r1)
            java.lang.String r25 = "SA.AnalyticsMessages"
            java.util.Locale r26 = java.util.Locale.CHINA
            java.lang.String r27 = "Events flushed. [left = %d]"
            r28 = 1
            r0 = r28
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r28 = r0
            r29 = 0
            java.lang.Integer r30 = java.lang.Integer.valueOf(r7)
            r28[r29] = r30
            java.lang.String r26 = java.lang.String.format(r26, r27, r28)
            com.sensorsdata.analytics.android.sdk.SALog.m1608i(r25, r26)
        L_0x052d:
            if (r3 == 0) goto L_0x0532
            r3.close()     // Catch:{ IOException -> 0x0601 }
        L_0x0532:
            if (r16 == 0) goto L_0x0537
            r16.close()     // Catch:{ IOException -> 0x0604 }
        L_0x0537:
            if (r13 == 0) goto L_0x053c
            r13.close()     // Catch:{ IOException -> 0x0607 }
        L_0x053c:
            if (r6 == 0) goto L_0x000b
            r6.disconnect()
            goto L_0x000b
        L_0x0543:
            r10 = move-exception
            r10.printStackTrace()
            goto L_0x04fe
        L_0x0548:
            r7 = 0
            goto L_0x052d
        L_0x054a:
            r10 = move-exception
            r10.printStackTrace()
            goto L_0x021a
        L_0x0550:
            r7 = 0
            goto L_0x0249
        L_0x0553:
            r10 = move-exception
            r10.printStackTrace()
            goto L_0x02c8
        L_0x0559:
            r7 = 0
            goto L_0x02f7
        L_0x055c:
            r10 = move-exception
            r10.printStackTrace()
            goto L_0x0373
        L_0x0562:
            r7 = 0
            goto L_0x03a2
        L_0x0565:
            r10 = move-exception
            r10.printStackTrace()
            goto L_0x0472
        L_0x056b:
            r7 = 0
            goto L_0x04a1
        L_0x056e:
            r25 = move-exception
        L_0x056f:
            r0 = r32
            android.content.Context r0 = r0.mContext
            r26 = r0
            com.sensorsdata.analytics.android.sdk.SensorsDataAPI r26 = com.sensorsdata.analytics.android.sdk.SensorsDataAPI.sharedInstance(r26)
            boolean r14 = r26.isDebugMode()
            boolean r26 = android.text.TextUtils.isEmpty(r11)
            if (r26 != 0) goto L_0x05b6
            if (r14 != 0) goto L_0x058d
            java.lang.Boolean r26 = com.sensorsdata.analytics.android.sdk.SensorsDataAPI.ENABLE_LOG
            boolean r26 = r26.booleanValue()
            if (r26 == 0) goto L_0x05b6
        L_0x058d:
            java.lang.String r26 = "SA.AnalyticsMessages"
            r0 = r26
            com.sensorsdata.analytics.android.sdk.SALog.m1608i(r0, r11)
            if (r14 == 0) goto L_0x05b6
            java.lang.Boolean r26 = com.sensorsdata.analytics.android.sdk.SensorsDataAPI.SHOW_DEBUG_INFO_VIEW
            boolean r26 = r26.booleanValue()
            if (r26 == 0) goto L_0x05b6
            if (r22 == 0) goto L_0x05a3
            r22.cancel()     // Catch:{ Exception -> 0x05fa }
        L_0x05a3:
            r0 = r32
            android.content.Context r0 = r0.mContext     // Catch:{ Exception -> 0x05fa }
            r26 = r0
            r27 = 0
            r0 = r26
            r1 = r27
            android.widget.Toast r22 = android.widget.Toast.makeText(r0, r11, r1)     // Catch:{ Exception -> 0x05fa }
            r22.show()     // Catch:{ Exception -> 0x05fa }
        L_0x05b6:
            if (r9 == 0) goto L_0x05ff
            r0 = r32
            com.sensorsdata.analytics.android.sdk.DbAdapter r0 = r0.mDbAdapter
            r26 = r0
            com.sensorsdata.analytics.android.sdk.DbAdapter$Table r27 = com.sensorsdata.analytics.android.sdk.DbAdapter.Table.EVENTS
            r0 = r26
            r1 = r27
            int r7 = r0.cleanupEvents(r15, r1)
            java.lang.String r26 = "SA.AnalyticsMessages"
            java.util.Locale r27 = java.util.Locale.CHINA
            java.lang.String r28 = "Events flushed. [left = %d]"
            r29 = 1
            r0 = r29
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r29 = r0
            r30 = 0
            java.lang.Integer r31 = java.lang.Integer.valueOf(r7)
            r29[r30] = r31
            java.lang.String r27 = java.lang.String.format(r27, r28, r29)
            com.sensorsdata.analytics.android.sdk.SALog.m1608i(r26, r27)
        L_0x05e5:
            if (r3 == 0) goto L_0x05ea
            r3.close()     // Catch:{ IOException -> 0x062e }
        L_0x05ea:
            if (r16 == 0) goto L_0x05ef
            r16.close()     // Catch:{ IOException -> 0x0630 }
        L_0x05ef:
            if (r13 == 0) goto L_0x05f4
            r13.close()     // Catch:{ IOException -> 0x0632 }
        L_0x05f4:
            if (r6 == 0) goto L_0x05f9
            r6.disconnect()
        L_0x05f9:
            throw r25
        L_0x05fa:
            r10 = move-exception
            r10.printStackTrace()
            goto L_0x05b6
        L_0x05ff:
            r7 = 0
            goto L_0x05e5
        L_0x0601:
            r25 = move-exception
            goto L_0x0532
        L_0x0604:
            r25 = move-exception
            goto L_0x0537
        L_0x0607:
            r25 = move-exception
            goto L_0x053c
        L_0x060a:
            r25 = move-exception
            goto L_0x024e
        L_0x060d:
            r25 = move-exception
            goto L_0x0253
        L_0x0610:
            r25 = move-exception
            goto L_0x0258
        L_0x0613:
            r25 = move-exception
            goto L_0x02fc
        L_0x0616:
            r25 = move-exception
            goto L_0x0301
        L_0x0619:
            r25 = move-exception
            goto L_0x0306
        L_0x061c:
            r25 = move-exception
            goto L_0x03a7
        L_0x061f:
            r25 = move-exception
            goto L_0x03ac
        L_0x0622:
            r25 = move-exception
            goto L_0x03b1
        L_0x0625:
            r25 = move-exception
            goto L_0x04a6
        L_0x0628:
            r25 = move-exception
            goto L_0x04ab
        L_0x062b:
            r25 = move-exception
            goto L_0x04b0
        L_0x062e:
            r26 = move-exception
            goto L_0x05ea
        L_0x0630:
            r26 = move-exception
            goto L_0x05ef
        L_0x0632:
            r26 = move-exception
            goto L_0x05f4
        L_0x0634:
            r25 = move-exception
            r3 = r4
            goto L_0x056f
        L_0x0638:
            r10 = move-exception
            r3 = r4
            goto L_0x0413
        L_0x063c:
            r10 = move-exception
            r3 = r4
            goto L_0x0314
        L_0x0640:
            r10 = move-exception
            r3 = r4
            goto L_0x0269
        L_0x0644:
            r10 = move-exception
            r3 = r4
            goto L_0x01bb
        L_0x0648:
            r10 = move-exception
            r3 = r4
            goto L_0x01b2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sensorsdata.analytics.android.sdk.AnalyticsMessages.sendData():void");
    }

    private String encodeData(String rawMessage) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream(rawMessage.getBytes().length);
        GZIPOutputStream gos = new GZIPOutputStream(os);
        gos.write(rawMessage.getBytes());
        gos.close();
        byte[] compressed = os.toByteArray();
        os.close();
        return new String(Base64Coder.encode(compressed));
    }

    /* access modifiers changed from: private */
    public String getCheckConfigure() throws ConnectErrorException {
        String str;
        SALog.m1608i(TAG, "getCheckConfigure");
        HttpURLConnection connection = null;
        InputStream in = null;
        try {
            String configureUrl = SensorsDataAPI.sharedInstance(this.mContext).getConfigureUrl();
            if (configureUrl == null || configureUrl.isEmpty()) {
                str = "";
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        SALog.m1608i(TAG, "getCheckConfigure close inputStream error:" + e.getMessage());
                    }
                }
                if (connection != null) {
                    connection.disconnect();
                }
            } else {
                HttpURLConnection connection2 = (HttpURLConnection) new URL(configureUrl).openConnection();
                connection2.setRequestMethod("GET");
                int responseCode = connection2.getResponseCode();
                InputStream in2 = connection2.getInputStream();
                byte[] responseBody = slurp(in2);
                in2.close();
                InputStream in3 = null;
                str = new String(responseBody, "UTF-8");
                if (responseCode != 200) {
                    throw new ConnectErrorException("Response error.");
                }
                if (in3 != null) {
                    try {
                        in3.close();
                    } catch (IOException e2) {
                        SALog.m1608i(TAG, "getCheckConfigure close inputStream error:" + e2.getMessage());
                    }
                }
                if (connection2 != null) {
                    connection2.disconnect();
                }
            }
            return str;
        } catch (IOException e3) {
            throw new ConnectErrorException((Throwable) e3);
        } catch (Throwable th) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e4) {
                    SALog.m1608i(TAG, "getCheckConfigure close inputStream error:" + e4.getMessage());
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
            throw th;
        }
    }
}
