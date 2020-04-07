package com.sensorsdata.analytics.android.sdk.java_websocket.framing;

import com.sensorsdata.analytics.android.sdk.java_websocket.exceptions.InvalidFrameException;
import java.nio.ByteBuffer;

public interface Framedata {

    public enum Opcode {
        CONTINUOUS,
        TEXT,
        BINARY,
        PING,
        PONG,
        CLOSING
    }

    void append(Framedata framedata) throws InvalidFrameException;

    Opcode getOpcode();

    ByteBuffer getPayloadData();

    boolean getTransfereMasked();

    boolean isFin();
}
