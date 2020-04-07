package com.sensorsdata.analytics.android.sdk.java_websocket.drafts;

import com.sensorsdata.analytics.android.sdk.java_websocket.drafts.Draft.HandshakeState;
import com.sensorsdata.analytics.android.sdk.java_websocket.exceptions.InvalidHandshakeException;
import com.sensorsdata.analytics.android.sdk.java_websocket.handshake.ClientHandshake;
import com.sensorsdata.analytics.android.sdk.java_websocket.handshake.ClientHandshakeBuilder;

public class Draft_17 extends Draft_10 {
    public HandshakeState acceptHandshakeAsServer(ClientHandshake handshakedata) throws InvalidHandshakeException {
        if (readVersion(handshakedata) == 13) {
            return HandshakeState.MATCHED;
        }
        return HandshakeState.NOT_MATCHED;
    }

    public ClientHandshakeBuilder postProcessHandshakeRequestAsClient(ClientHandshakeBuilder request) {
        super.postProcessHandshakeRequestAsClient(request);
        request.put("Sec-WebSocket-Version", "13");
        return request;
    }

    public Draft copyInstance() {
        return new Draft_17();
    }
}
