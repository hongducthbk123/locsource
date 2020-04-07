package com.sensorsdata.analytics.android.sdk;

import android.util.Log;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.sensorsdata.analytics.android.sdk.java_websocket.client.WebSocketClient;
import com.sensorsdata.analytics.android.sdk.java_websocket.drafts.Draft_17;
import com.sensorsdata.analytics.android.sdk.java_websocket.exceptions.NotSendableException;
import com.sensorsdata.analytics.android.sdk.java_websocket.exceptions.WebsocketNotConnectedException;
import com.sensorsdata.analytics.android.sdk.java_websocket.framing.Framedata.Opcode;
import com.sensorsdata.analytics.android.sdk.java_websocket.handshake.ServerHandshake;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.ByteBuffer;
import org.json.JSONException;
import org.json.JSONObject;

public class EditorConnection {
    private static final int CONNECT_TIMEOUT = 1000;
    /* access modifiers changed from: private */
    public static final ByteBuffer EMPTY_BYTE_BUFFER = ByteBuffer.allocate(0);
    private static final String TAG = "SA.EditorConnection";
    /* access modifiers changed from: private */
    public final EditorClient mClient;
    /* access modifiers changed from: private */
    public final Editor mService;
    /* access modifiers changed from: private */
    public final URI mURI;

    public interface Editor {
        void bindEvents(JSONObject jSONObject);

        void cleanup();

        void disconnect();

        void onWebSocketClose(int i);

        void onWebSocketOpen();

        void sendDeviceInfo(JSONObject jSONObject);

        void sendSnapshot(JSONObject jSONObject);
    }

    private class EditorClient extends WebSocketClient {
        public EditorClient(URI uri, int connectTimeout) throws InterruptedException {
            super(uri, new Draft_17(), null, connectTimeout);
        }

        public void onOpen(ServerHandshake handshakedata) {
            if (SensorsDataAPI.ENABLE_LOG.booleanValue()) {
                SALog.m1608i(EditorConnection.TAG, "Websocket connected: " + handshakedata.getHttpStatus() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + handshakedata.getHttpStatusMessage());
            }
            EditorConnection.this.mService.onWebSocketOpen();
        }

        public void onMessage(String message) {
            try {
                JSONObject messageJson = new JSONObject(message);
                String type = messageJson.getString("type");
                if (type.equals("device_info_request")) {
                    EditorConnection.this.mService.sendDeviceInfo(messageJson);
                } else if (type.equals("snapshot_request")) {
                    EditorConnection.this.mService.sendSnapshot(messageJson);
                } else if (type.equals("event_binding_request")) {
                    EditorConnection.this.mService.bindEvents(messageJson);
                } else if (type.equals("disconnect")) {
                    EditorConnection.this.mService.disconnect();
                }
            } catch (JSONException e) {
                SALog.m1609i(EditorConnection.TAG, "Bad JSON received:" + message, e);
            }
        }

        public void onClose(int code, String reason, boolean remote) {
            Log.i(EditorConnection.TAG, "WebSocket closed. Code: " + code + ", reason: " + reason + "\nURI: " + EditorConnection.this.mURI);
            EditorConnection.this.mService.cleanup();
            EditorConnection.this.mService.onWebSocketClose(code);
        }

        public void onError(Exception ex) {
            if (ex == null || ex.getMessage() == null) {
                SALog.m1608i(EditorConnection.TAG, "Unknown websocket error occurred");
            } else {
                SALog.m1608i(EditorConnection.TAG, "Websocket Error: " + ex.getMessage());
            }
        }
    }

    public class EditorConnectionException extends IOException {
        private static final long serialVersionUID = -1884953175346045636L;

        public EditorConnectionException(Throwable cause) {
            super(cause.getMessage());
        }
    }

    private class WebSocketOutputStream extends OutputStream {
        private WebSocketOutputStream() {
        }

        public void write(int b) throws EditorConnectionException {
            write(new byte[]{(byte) b}, 0, 1);
        }

        public void write(byte[] b) throws EditorConnectionException {
            write(b, 0, b.length);
        }

        public void write(byte[] b, int off, int len) throws EditorConnectionException {
            try {
                EditorConnection.this.mClient.sendFragmentedFrame(Opcode.TEXT, ByteBuffer.wrap(b, off, len), false);
            } catch (WebsocketNotConnectedException e) {
                throw new EditorConnectionException(e);
            } catch (NotSendableException e2) {
                throw new EditorConnectionException(e2);
            }
        }

        public void close() throws EditorConnectionException {
            try {
                EditorConnection.this.mClient.sendFragmentedFrame(Opcode.TEXT, EditorConnection.EMPTY_BYTE_BUFFER, true);
            } catch (WebsocketNotConnectedException e) {
                throw new EditorConnectionException(e);
            } catch (NotSendableException e2) {
                throw new EditorConnectionException(e2);
            }
        }
    }

    public EditorConnection(URI uri, Editor service) throws EditorConnectionException {
        this.mService = service;
        this.mURI = uri;
        try {
            this.mClient = new EditorClient(uri, 1000);
            this.mClient.connectBlocking();
        } catch (InterruptedException e) {
            throw new EditorConnectionException(e);
        }
    }

    public boolean isValid() {
        return !this.mClient.isClosed() && !this.mClient.isClosing() && !this.mClient.isFlushAndClose();
    }

    public BufferedOutputStream getBufferedOutputStream() {
        return new BufferedOutputStream(new WebSocketOutputStream());
    }

    public void sendMessage(String message) {
        SALog.m1608i(TAG, "Sending message: " + message);
        try {
            this.mClient.send(message);
        } catch (Exception e) {
            SALog.m1609i(TAG, "sendMessage;error", e);
        }
    }

    public void close(boolean block) {
        if (this.mClient != null) {
            if (block) {
                try {
                    this.mClient.closeBlocking();
                } catch (Exception e) {
                    SALog.m1609i(TAG, "close;error", e);
                }
            } else {
                this.mClient.close();
            }
        }
    }
}
