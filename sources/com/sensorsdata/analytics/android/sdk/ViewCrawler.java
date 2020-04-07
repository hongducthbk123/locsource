package com.sensorsdata.analytics.android.sdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.p000v4.app.NotificationCompat;
import android.util.DisplayMetrics;
import android.util.JsonWriter;
import android.util.Pair;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.sensorsdata.analytics.android.sdk.EditProtocol.BadInstructionsException;
import com.sensorsdata.analytics.android.sdk.EditProtocol.InapplicableInstructionsException;
import com.sensorsdata.analytics.android.sdk.EditorConnection.EditorConnectionException;
import com.sensorsdata.analytics.android.sdk.ResourceReader.Ids;
import com.sensorsdata.analytics.android.sdk.util.Base64Coder;
import com.sensorsdata.analytics.android.sdk.util.JSONUtils;
import com.sensorsdata.analytics.android.sdk.util.SensorsDataUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.zip.GZIPOutputStream;
import org.apache.http.cookie.ClientCookie;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p004cn.jiguang.net.HttpUtils;

@TargetApi(16)
public class ViewCrawler implements VTrack, DebugTracking {
    private static final int CLOSE_CODE_GOING_AWAY = 1001;
    private static final int CLOSE_CODE_NOCODE = 1005;
    private static final int CLOSE_CODE_NORMAL = 1000;
    private static final int CONNECT_RETRY_TIMES = 40;
    private static final int EMULATOR_CONNECT_ATTEMPT_INTERVAL_MILLIS = 30000;
    private static final int MESSAGE_CONNECT_TO_EDITOR = 1;
    private static final int MESSAGE_EVENT_BINDINGS_RECEIVED = 5;
    private static final int MESSAGE_HANDLE_DISCONNECT = 13;
    private static final int MESSAGE_HANDLE_EDITOR_BINDINGS_RECEIVED = 6;
    private static final int MESSAGE_HANDLE_EDITOR_CLOSED = 8;
    private static final int MESSAGE_INITIALIZE_CHANGES = 0;
    private static final int MESSAGE_SEND_DEVICE_INFO = 4;
    private static final int MESSAGE_SEND_EVENT_TRACKED = 7;
    private static final int MESSAGE_SEND_STATE_FOR_EDITING = 2;
    private static final long RETRY_TIME_INTERVAL = 30000;
    private static final String SHARED_PREF_BINDINGS_KEY = "sensorsdata.viewcrawler.bindings";
    private static final String SHARED_PREF_EDITS_FILE = "sensorsdata";
    private static final String TAG = "SA.ViewCrawler";
    /* access modifiers changed from: private */
    public static int mCurrentRetryTimes = 0;
    /* access modifiers changed from: private */
    public static boolean mIsRetryConnect = true;
    private final Context mContext;
    /* access modifiers changed from: private */
    public final HashSet<String> mDisabledActivity = new HashSet<>();
    /* access modifiers changed from: private */
    public final DynamicEventTracker mDynamicEventTracker;
    /* access modifiers changed from: private */
    public final EditState mEditState;
    /* access modifiers changed from: private */
    public final LifecycleCallbacks mLifecycleCallbacks;
    /* access modifiers changed from: private */
    public final ViewCrawlerHandler mMessageThreadHandler;
    /* access modifiers changed from: private */
    public final HashSet<Activity> mStartedActivities = new HashSet<>();
    /* access modifiers changed from: private */
    public String mVTrackServer = null;

    private class Editor implements com.sensorsdata.analytics.android.sdk.EditorConnection.Editor {
        private Editor() {
        }

        public void sendSnapshot(JSONObject message) {
            Message msg = ViewCrawler.this.mMessageThreadHandler.obtainMessage(2);
            msg.obj = message;
            ViewCrawler.this.mMessageThreadHandler.sendMessage(msg);
        }

        public void bindEvents(JSONObject message) {
            Message msg = ViewCrawler.this.mMessageThreadHandler.obtainMessage(6);
            msg.obj = message;
            ViewCrawler.this.mMessageThreadHandler.sendMessage(msg);
        }

        public void sendDeviceInfo(JSONObject message) {
            Message msg = ViewCrawler.this.mMessageThreadHandler.obtainMessage(4);
            msg.obj = message;
            ViewCrawler.this.mMessageThreadHandler.sendMessage(msg);
        }

        public void cleanup() {
            ViewCrawler.this.mMessageThreadHandler.sendMessage(ViewCrawler.this.mMessageThreadHandler.obtainMessage(8));
        }

        public void disconnect() {
            ViewCrawler.mIsRetryConnect = false;
            ViewCrawler.this.mMessageThreadHandler.sendMessage(ViewCrawler.this.mMessageThreadHandler.obtainMessage(13));
        }

        public void onWebSocketOpen() {
            SALog.m1608i(ViewCrawler.TAG, "onWebSocketOpen");
            ViewCrawler.mCurrentRetryTimes = 0;
            ViewCrawler.mIsRetryConnect = true;
        }

        public void onWebSocketClose(int code) {
            SALog.m1608i(ViewCrawler.TAG, "onWebSocketClose; mIsRetryConnect=" + ViewCrawler.mIsRetryConnect + ";mCurrentRetryTimes=" + ViewCrawler.mCurrentRetryTimes);
            if (code != 1005) {
                ViewCrawler.mIsRetryConnect = false;
                ViewCrawler.mCurrentRetryTimes = 0;
                return;
            }
            if (ViewCrawler.mCurrentRetryTimes >= 40) {
                ViewCrawler.mIsRetryConnect = false;
            }
            if (ViewCrawler.mIsRetryConnect) {
                ViewCrawler.this.mMessageThreadHandler.sendMessageDelayed(ViewCrawler.this.mMessageThreadHandler.obtainMessage(1), ViewCrawler.RETRY_TIME_INTERVAL);
                ViewCrawler.access$1308();
            }
        }
    }

    private class EmulatorConnector implements Runnable {
        private volatile boolean mStopped = true;

        public EmulatorConnector() {
        }

        public void run() {
            if (!this.mStopped) {
                if (ViewCrawler.this.mVTrackServer == null) {
                    ViewCrawler.this.mMessageThreadHandler.postDelayed(this, 3000);
                    return;
                }
                ViewCrawler.this.mMessageThreadHandler.sendMessage(ViewCrawler.this.mMessageThreadHandler.obtainMessage(1));
                ViewCrawler.this.mMessageThreadHandler.postDelayed(this, ViewCrawler.RETRY_TIME_INTERVAL);
            }
        }

        public void start() {
            if (this.mStopped) {
                this.mStopped = false;
                ViewCrawler.this.mMessageThreadHandler.post(this);
            }
        }

        public void stop() {
            this.mStopped = true;
            ViewCrawler.this.mMessageThreadHandler.removeCallbacks(this);
        }
    }

    private class LifecycleCallbacks implements ActivityLifecycleCallbacks {
        private final EmulatorConnector mEmulatorConnector = new EmulatorConnector();
        private boolean mEnableConnector = false;

        public LifecycleCallbacks() {
        }

        /* access modifiers changed from: 0000 */
        public void enableConnector() {
            this.mEnableConnector = true;
            this.mEmulatorConnector.start();
        }

        /* access modifiers changed from: 0000 */
        public void disableConnector() {
            this.mEnableConnector = false;
            this.mEmulatorConnector.stop();
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
            if (this.mEnableConnector) {
                this.mEmulatorConnector.start();
            }
            ViewCrawler.this.mStartedActivities.add(activity);
            Iterator it = ViewCrawler.this.mDisabledActivity.iterator();
            while (it.hasNext()) {
                if (((String) it.next()).equals(activity.getClass().getCanonicalName())) {
                    return;
                }
            }
            ViewCrawler.this.mEditState.add(activity);
        }

        public void onActivityPaused(Activity activity) {
            ViewCrawler.this.mStartedActivities.remove(activity);
            ViewCrawler.this.mEditState.remove(activity);
            if (ViewCrawler.this.mEditState.isEmpty()) {
                this.mEmulatorConnector.stop();
            }
        }

        public void onActivityStopped(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
        }
    }

    private class ViewCrawlerHandler extends Handler {
        /* access modifiers changed from: private */
        public final Context mContext;
        /* access modifiers changed from: private */
        public EditorConnection mEditorConnection;
        private final List<Pair<String, JSONObject>> mEditorEventBindings;
        private int mHasRetryCount = 0;
        private final List<Pair<String, JSONObject>> mPersistentEventBindings;
        private final EditProtocol mProtocol;
        private ViewSnapshot mSnapshot;
        private final Lock mStartLock;
        /* access modifiers changed from: private */
        public boolean mUseGzip;

        public ViewCrawlerHandler(Context context, Looper looper, String resourcePackageName) {
            super(looper);
            this.mContext = context;
            this.mSnapshot = null;
            this.mProtocol = new EditProtocol(new Ids(resourcePackageName, context));
            this.mEditorEventBindings = new ArrayList();
            this.mPersistentEventBindings = new ArrayList();
            this.mUseGzip = false;
            this.mStartLock = new ReentrantLock();
            this.mStartLock.lock();
        }

        public void start() {
            this.mStartLock.unlock();
        }

        public void handleMessage(Message msg) {
            this.mStartLock.lock();
            try {
                switch (msg.what) {
                    case 0:
                        initializeBindings();
                        break;
                    case 1:
                        connectToEditor();
                        break;
                    case 2:
                        sendSnapshot((JSONObject) msg.obj);
                        break;
                    case 4:
                        sendDeviceInfo((JSONObject) msg.obj);
                        break;
                    case 5:
                        handleEventBindingsReceived((JSONArray) msg.obj);
                        break;
                    case 6:
                        handleEditorBindingsReceived((JSONObject) msg.obj);
                        break;
                    case 7:
                        sendReportTrackToEditor((JSONObject) msg.obj);
                        break;
                    case 8:
                        handleEditorClosed();
                        break;
                    case 13:
                        handleDisconnect();
                        break;
                }
            } finally {
                this.mStartLock.unlock();
            }
        }

        private void initializeBindings() {
            SharedPreferences preferences = getSharedPreferences();
            String storedBindings = preferences.getString(ViewCrawler.SHARED_PREF_BINDINGS_KEY, null);
            if (storedBindings != null) {
                try {
                    SALog.m1608i(ViewCrawler.TAG, "Initialize event bindings: " + storedBindings);
                    JSONArray bindings = new JSONArray(storedBindings);
                    this.mPersistentEventBindings.clear();
                    for (int i = 0; i < bindings.length(); i++) {
                        JSONObject event = bindings.getJSONObject(i);
                        this.mPersistentEventBindings.add(new Pair(JSONUtils.optionalStringKey(event, "target_activity"), event));
                    }
                } catch (JSONException e) {
                    SALog.m1609i(ViewCrawler.TAG, "JSON error when initializing saved changes, clearing persistent memory", e);
                    android.content.SharedPreferences.Editor editor = preferences.edit();
                    editor.remove(ViewCrawler.SHARED_PREF_BINDINGS_KEY);
                    editor.apply();
                }
            }
            applyVariantsAndEventBindings();
        }

        private void retrySendDeviceInfo(JSONObject message) {
            if (this.mHasRetryCount < 3) {
                this.mHasRetryCount++;
                Message msg = ViewCrawler.this.mMessageThreadHandler.obtainMessage(4);
                msg.obj = message;
                ViewCrawler.this.mMessageThreadHandler.sendMessageDelayed(msg, 3000);
            }
        }

        private void connectToEditor() {
            if (this.mEditorConnection != null && this.mEditorConnection.isValid()) {
                SALog.m1608i(ViewCrawler.TAG, "The VTrack server has been connected.");
            } else if (ViewCrawler.this.mVTrackServer != null) {
                SALog.m1608i(ViewCrawler.TAG, "Connecting to the VTrack server with " + ViewCrawler.this.mVTrackServer);
                try {
                    this.mEditorConnection = new EditorConnection(new URI(ViewCrawler.this.mVTrackServer), new Editor());
                } catch (URISyntaxException e) {
                    SALog.m1609i(ViewCrawler.TAG, "Error parsing URI " + ViewCrawler.this.mVTrackServer + " for VTrack websocket", e);
                } catch (EditorConnectionException e2) {
                    SALog.m1609i(ViewCrawler.TAG, "Error connecting to URI " + ViewCrawler.this.mVTrackServer, e2);
                }
            }
        }

        private void sendDeviceInfo(final JSONObject message) {
            if (this.mEditorConnection != null && this.mEditorConnection.isValid()) {
                Iterator<Activity> activityIt = ViewCrawler.this.mStartedActivities.iterator();
                if (!activityIt.hasNext()) {
                    retrySendDeviceInfo(message);
                    return;
                }
                Activity activity = (Activity) activityIt.next();
                if (activity != null) {
                    try {
                        new Builder(activity).setMessage("正在连接到 Sensors Analytics 可视化埋点管理界面...").setTitle("Connecting to VTrack").setPositiveButton("继续", new OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                try {
                                    JSONObject payload = message.getJSONObject(MessengerShareContentUtility.ATTACHMENT_PAYLOAD);
                                    if (payload.has("support_gzip")) {
                                        ViewCrawlerHandler.this.mUseGzip = payload.getBoolean("support_gzip");
                                    }
                                } catch (JSONException e) {
                                }
                                PackageManager manager = ViewCrawlerHandler.this.mContext.getPackageManager();
                                DisplayMetrics displayMetrics = ViewCrawlerHandler.this.mContext.getResources().getDisplayMetrics();
                                try {
                                    JSONObject payload2 = new JSONObject();
                                    payload2.put("$lib", "Android");
                                    payload2.put("$lib_version", "1.8.8");
                                    payload2.put("$os", "Android");
                                    payload2.put("$os_version", VERSION.RELEASE == null ? "UNKNOWN" : VERSION.RELEASE);
                                    payload2.put("$screen_height", String.valueOf(displayMetrics.heightPixels));
                                    payload2.put("$screen_width", String.valueOf(displayMetrics.widthPixels));
                                    try {
                                        PackageInfo info = manager.getPackageInfo(ViewCrawlerHandler.this.mContext.getPackageName(), 0);
                                        payload2.put("$main_bundle_identifier", info.packageName);
                                        payload2.put("$app_version", info.versionName);
                                    } catch (NameNotFoundException e2) {
                                        payload2.put("$main_bundle_identifier", "");
                                        payload2.put("$app_version", "");
                                    }
                                    payload2.put("$device_name", Build.BRAND + HttpUtils.PATHS_SEPARATOR + Build.MODEL);
                                    payload2.put("$device_model", Build.MODEL == null ? "UNKNOWN" : Build.MODEL);
                                    payload2.put("$device_id", SensorsDataUtils.getDeviceID(ViewCrawlerHandler.this.mContext));
                                    if (ViewCrawlerHandler.this.mEditorConnection != null && ViewCrawlerHandler.this.mEditorConnection.isValid()) {
                                        ViewCrawlerHandler.this.mEditorConnection.sendMessage(ViewCrawlerHandler.this.setUpPayload("device_info_response", payload2).toString());
                                    }
                                } catch (JSONException e3) {
                                    SALog.m1609i(ViewCrawler.TAG, "Can't write the response for device information.", e3);
                                } catch (IOException e4) {
                                    SALog.m1609i(ViewCrawler.TAG, "Can't write the response for device information.", e4);
                                }
                            }
                        }).setNegativeButton("取消", new OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                if (ViewCrawlerHandler.this.mEditorConnection != null) {
                                    ViewCrawlerHandler.this.mEditorConnection.close(true);
                                }
                            }
                        }).show();
                    } catch (RuntimeException e) {
                        SALog.m1609i(ViewCrawler.TAG, "Failed to show dialog of VTrack connector", e);
                    }
                } else if (this.mEditorConnection != null) {
                    this.mEditorConnection.close(true);
                }
            }
        }

        private void sendSnapshot(JSONObject message) {
            if (this.mEditorConnection != null && this.mEditorConnection.isValid()) {
                long startSnapshot = System.currentTimeMillis();
                try {
                    JSONObject payload = message.getJSONObject(MessengerShareContentUtility.ATTACHMENT_PAYLOAD);
                    if (payload.has("config")) {
                        this.mSnapshot = this.mProtocol.readSnapshotConfig(payload);
                    }
                    if (this.mSnapshot == null) {
                        SALog.m1608i(ViewCrawler.TAG, "Snapshot should be initialize at first calling.");
                        return;
                    }
                    if (payload.has("last_image_hash")) {
                        this.mSnapshot.updateLastImageHashArray(payload.getString("last_image_hash"));
                    }
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(out);
                    try {
                        outputStreamWriter.write("{");
                        outputStreamWriter.write("\"type\": \"snapshot_response\",");
                        if (this.mUseGzip) {
                            ByteArrayOutputStream payload_out = new ByteArrayOutputStream();
                            OutputStreamWriter payload_writer = new OutputStreamWriter(payload_out);
                            payload_writer.write("{\"activities\":");
                            payload_writer.flush();
                            this.mSnapshot.snapshots(ViewCrawler.this.mEditState, payload_out);
                            long snapshotTime = System.currentTimeMillis() - startSnapshot;
                            payload_writer.write(",\"snapshot_time_millis\": ");
                            payload_writer.write(Long.toString(snapshotTime));
                            payload_writer.write("}");
                            payload_writer.flush();
                            payload_out.close();
                            byte[] payloadData = payload_out.toString().getBytes();
                            ByteArrayOutputStream os = new ByteArrayOutputStream(payloadData.length);
                            GZIPOutputStream gos = new GZIPOutputStream(os);
                            gos.write(payloadData);
                            gos.close();
                            byte[] compressed = os.toByteArray();
                            os.close();
                            outputStreamWriter.write("\"gzip_payload\": \"" + new String(Base64Coder.encode(compressed)) + "\"");
                        } else {
                            outputStreamWriter.write("\"payload\": {");
                            outputStreamWriter.write("\"activities\":");
                            outputStreamWriter.flush();
                            this.mSnapshot.snapshots(ViewCrawler.this.mEditState, out);
                            long snapshotTime2 = System.currentTimeMillis() - startSnapshot;
                            outputStreamWriter.write(",\"snapshot_time_millis\": ");
                            outputStreamWriter.write(Long.toString(snapshotTime2));
                            outputStreamWriter.write("}");
                        }
                        outputStreamWriter.write("}");
                        outputStreamWriter.flush();
                        try {
                        } catch (IOException e) {
                            SALog.m1609i(ViewCrawler.TAG, "Can't close writer.", e);
                        }
                    } catch (IOException e2) {
                        SALog.m1609i(ViewCrawler.TAG, "Can't write snapshot request to server", e2);
                        try {
                        } catch (IOException e3) {
                            SALog.m1609i(ViewCrawler.TAG, "Can't close writer.", e3);
                        }
                    } finally {
                        try {
                            outputStreamWriter.close();
                        } catch (IOException e4) {
                            SALog.m1609i(ViewCrawler.TAG, "Can't close writer.", e4);
                        }
                    }
                    if (this.mEditorConnection != null && this.mEditorConnection.isValid()) {
                        this.mEditorConnection.sendMessage(out.toString());
                    }
                } catch (JSONException e5) {
                    SALog.m1609i(ViewCrawler.TAG, "Payload with snapshot config required with snapshot request", e5);
                } catch (BadInstructionsException e6) {
                    SALog.m1609i(ViewCrawler.TAG, "VTrack server sent malformed message with snapshot request", e6);
                }
            }
        }

        private void sendEventBindingResponse(boolean result) {
            if (this.mEditorConnection != null && this.mEditorConnection.isValid()) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                JsonWriter j = new JsonWriter(new OutputStreamWriter(out));
                try {
                    j.beginObject();
                    j.name("type").value("event_binding_response");
                    j.name(MessengerShareContentUtility.ATTACHMENT_PAYLOAD).beginObject();
                    j.name("status").value("OK");
                    j.endObject();
                    j.endObject();
                    try {
                    } catch (IOException e) {
                        SALog.m1609i(ViewCrawler.TAG, "Can't close websocket writer", e);
                    }
                } catch (IOException e2) {
                    SALog.m1609i(ViewCrawler.TAG, "Can't write event_binding_response to server", e2);
                    try {
                    } catch (IOException e3) {
                        SALog.m1609i(ViewCrawler.TAG, "Can't close websocket writer", e3);
                    }
                } finally {
                    try {
                        j.close();
                    } catch (IOException e4) {
                        SALog.m1609i(ViewCrawler.TAG, "Can't close websocket writer", e4);
                    }
                }
                if (this.mEditorConnection != null && this.mEditorConnection.isValid()) {
                    this.mEditorConnection.sendMessage(out.toString());
                }
            }
        }

        private void sendReportTrackToEditor(JSONObject eventJson) {
            if (this.mEditorConnection != null && this.mEditorConnection.isValid() && eventJson != null) {
                JSONObject sendProperties = eventJson.optJSONObject("properties");
                if (sendProperties != null) {
                    SALog.m1608i(ViewCrawler.TAG, "Sending debug track to vtrack. original event: " + eventJson.toString());
                    if (sendProperties.optString("$from_vtrack", "").length() >= 1) {
                        OutputStreamWriter writer = new OutputStreamWriter(this.mEditorConnection.getBufferedOutputStream());
                        try {
                            JSONObject payload = new JSONObject();
                            payload.put("depolyed", sendProperties.getBoolean("$binding_depolyed"));
                            payload.put("trigger_id", sendProperties.getString("$binding_trigger_id"));
                            payload.put(ClientCookie.PATH_ATTR, sendProperties.getString("$binding_path"));
                            sendProperties.remove("$binding_path");
                            sendProperties.remove("$binding_depolyed");
                            sendProperties.remove("$binding_trigger_id");
                            eventJson.put("properties", sendProperties);
                            payload.put(NotificationCompat.CATEGORY_EVENT, eventJson);
                            JSONObject result = new JSONObject();
                            result.put("type", "debug_track");
                            result.put(MessengerShareContentUtility.ATTACHMENT_PAYLOAD, payload);
                            writer.write(result.toString());
                            writer.flush();
                            try {
                            } catch (IOException e) {
                                SALog.m1609i(ViewCrawler.TAG, "Can't close writer.", e);
                            }
                        } catch (JSONException e2) {
                            SALog.m1609i(ViewCrawler.TAG, "Invalied proprties", e2);
                            try {
                            } catch (IOException e3) {
                                SALog.m1609i(ViewCrawler.TAG, "Can't close writer.", e3);
                            }
                        } catch (IOException e4) {
                            SALog.m1609i(ViewCrawler.TAG, "Can't write track_message to server", e4);
                            try {
                            } catch (IOException e5) {
                                SALog.m1609i(ViewCrawler.TAG, "Can't close writer.", e5);
                            }
                        } finally {
                            try {
                                writer.close();
                            } catch (IOException e6) {
                                SALog.m1609i(ViewCrawler.TAG, "Can't close writer.", e6);
                            }
                        }
                    }
                }
            }
        }

        private void handleEventBindingsReceived(JSONArray eventBindings) {
            android.content.SharedPreferences.Editor editor = getSharedPreferences().edit();
            editor.putString(ViewCrawler.SHARED_PREF_BINDINGS_KEY, eventBindings.toString());
            editor.apply();
            initializeBindings();
        }

        private void handleEditorBindingsReceived(JSONObject message) {
            SALog.m1608i(ViewCrawler.TAG, String.format("Received event bindings from VTrack editor: %s", new Object[]{message.toString()}));
            sendEventBindingResponse(true);
            try {
                JSONArray eventBindings = message.getJSONObject(MessengerShareContentUtility.ATTACHMENT_PAYLOAD).getJSONArray("events");
                int eventCount = eventBindings.length();
                this.mEditorEventBindings.clear();
                for (int i = 0; i < eventCount; i++) {
                    try {
                        JSONObject event = eventBindings.getJSONObject(i);
                        this.mEditorEventBindings.add(new Pair(JSONUtils.optionalStringKey(event, "target_activity"), event));
                    } catch (JSONException e) {
                        SALog.m1609i(ViewCrawler.TAG, "Bad event binding received from VTrack server in " + eventBindings.toString(), e);
                    }
                }
                applyVariantsAndEventBindings();
            } catch (JSONException e2) {
                SALog.m1609i(ViewCrawler.TAG, "Bad event bindings received", e2);
            }
        }

        private void handleEditorClosed() {
            SALog.m1608i(ViewCrawler.TAG, "VTrack server connection closed.");
            this.mSnapshot = null;
            this.mEditorEventBindings.clear();
            applyVariantsAndEventBindings();
        }

        private void handleDisconnect() {
            if (this.mEditorConnection != null) {
                ViewCrawler.this.mLifecycleCallbacks.disableConnector();
                this.mEditorConnection.close(true);
            }
        }

        private void applyVariantsAndEventBindings() {
            List<ViewVisitor> mapElement;
            List<Pair<String, ViewVisitor>> newVisitors = new ArrayList<>();
            SALog.m1608i(ViewCrawler.TAG, String.format(Locale.CHINA, "Event bindings are loaded. %d events from VTrack editor ，%d events from VTrack configure", new Object[]{Integer.valueOf(this.mEditorEventBindings.size()), Integer.valueOf(this.mPersistentEventBindings.size())}));
            if (this.mEditorEventBindings.size() > 0) {
                for (Pair<String, JSONObject> changeInfo : this.mEditorEventBindings) {
                    try {
                        newVisitors.add(new Pair(changeInfo.first, this.mProtocol.readEventBinding((JSONObject) changeInfo.second, ViewCrawler.this.mDynamicEventTracker)));
                    } catch (InapplicableInstructionsException e) {
                        SALog.m1608i(ViewCrawler.TAG, e.getMessage());
                    } catch (BadInstructionsException e2) {
                        SALog.m1609i(ViewCrawler.TAG, "Bad editor event binding cannot be applied.", e2);
                    }
                }
            } else {
                for (Pair<String, JSONObject> changeInfo2 : this.mPersistentEventBindings) {
                    try {
                        newVisitors.add(new Pair(changeInfo2.first, this.mProtocol.readEventBinding((JSONObject) changeInfo2.second, ViewCrawler.this.mDynamicEventTracker)));
                    } catch (InapplicableInstructionsException e3) {
                        SALog.m1608i(ViewCrawler.TAG, e3.getMessage());
                    } catch (BadInstructionsException e4) {
                        SALog.m1609i(ViewCrawler.TAG, "Bad persistent event binding cannot be applied.", e4);
                    }
                }
            }
            Map<String, List<ViewVisitor>> editMap = new HashMap<>();
            int totalEdits = newVisitors.size();
            for (int i = 0; i < totalEdits; i++) {
                Pair<String, ViewVisitor> next = (Pair) newVisitors.get(i);
                if (editMap.containsKey(next.first)) {
                    mapElement = (List) editMap.get(next.first);
                } else {
                    mapElement = new ArrayList<>();
                    editMap.put(next.first, mapElement);
                }
                mapElement.add(next.second);
            }
            ViewCrawler.this.mEditState.setEdits(editMap);
        }

        /* access modifiers changed from: private */
        public JSONObject setUpPayload(String type, JSONObject payload) throws JSONException, IOException {
            JSONObject response = new JSONObject();
            response.put("type", type);
            if (this.mUseGzip) {
                byte[] payloadData = payload.toString().getBytes();
                ByteArrayOutputStream os = new ByteArrayOutputStream(payloadData.length);
                GZIPOutputStream gos = new GZIPOutputStream(os);
                gos.write(payloadData);
                gos.close();
                byte[] compressed = os.toByteArray();
                os.close();
                response.put("gzip_payload", new String(Base64Coder.encode(compressed)));
            } else {
                response.put(MessengerShareContentUtility.ATTACHMENT_PAYLOAD, payload);
            }
            return response;
        }

        private SharedPreferences getSharedPreferences() {
            String str = ViewCrawler.SHARED_PREF_EDITS_FILE;
            return this.mContext.getSharedPreferences(ViewCrawler.SHARED_PREF_EDITS_FILE, 0);
        }
    }

    static /* synthetic */ int access$1308() {
        int i = mCurrentRetryTimes;
        mCurrentRetryTimes = i + 1;
        return i;
    }

    public ViewCrawler(Context context, String resourcePackageName) {
        this.mContext = context;
        this.mEditState = new EditState();
        this.mLifecycleCallbacks = new LifecycleCallbacks();
        ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(this.mLifecycleCallbacks);
        HandlerThread thread = new HandlerThread(ViewCrawler.class.getCanonicalName(), 10);
        thread.start();
        this.mMessageThreadHandler = new ViewCrawlerHandler(context, thread.getLooper(), resourcePackageName);
        this.mDynamicEventTracker = new DynamicEventTracker(context, this.mMessageThreadHandler);
    }

    public void startUpdates() {
        this.mMessageThreadHandler.start();
        this.mMessageThreadHandler.sendMessage(this.mMessageThreadHandler.obtainMessage(0));
    }

    public void enableEditingVTrack() {
        this.mLifecycleCallbacks.enableConnector();
    }

    public void disableActivity(String canonicalName) {
        this.mDisabledActivity.add(canonicalName);
    }

    public void setEventBindings(JSONArray bindings) {
        Message msg = this.mMessageThreadHandler.obtainMessage(5);
        msg.obj = bindings;
        this.mMessageThreadHandler.sendMessage(msg);
    }

    public void setVTrackServer(String serverUrl) {
        if (this.mVTrackServer == null && serverUrl != null && serverUrl.length() > 0) {
            this.mVTrackServer = serverUrl;
            SALog.m1608i(TAG, "Gets VTrack server URL '" + this.mVTrackServer + "' from configure.");
        }
        if (this.mVTrackServer == null) {
            this.mVTrackServer = Uri.parse(SensorsDataAPI.sharedInstance(this.mContext).getConfigureUrl()).buildUpon().path("/api/ws").scheme("ws").build().toString();
            SALog.m1608i(TAG, "Generates VTrack server URL '" + this.mVTrackServer + "' with configure URL.");
        }
        if (this.mVTrackServer == null) {
            SALog.m1608i(TAG, "Unknown VTrack server URL.");
        }
    }

    public void reportTrack(JSONObject eventJson) {
        Message m = this.mMessageThreadHandler.obtainMessage();
        m.what = 7;
        m.obj = eventJson;
        this.mMessageThreadHandler.sendMessage(m);
    }
}
