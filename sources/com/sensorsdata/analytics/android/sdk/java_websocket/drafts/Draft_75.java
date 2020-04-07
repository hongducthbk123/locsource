package com.sensorsdata.analytics.android.sdk.java_websocket.drafts;

import com.google.common.net.HttpHeaders;
import com.sensorsdata.analytics.android.sdk.java_websocket.drafts.Draft.CloseHandshakeType;
import com.sensorsdata.analytics.android.sdk.java_websocket.drafts.Draft.HandshakeState;
import com.sensorsdata.analytics.android.sdk.java_websocket.exceptions.InvalidDataException;
import com.sensorsdata.analytics.android.sdk.java_websocket.exceptions.InvalidFrameException;
import com.sensorsdata.analytics.android.sdk.java_websocket.exceptions.InvalidHandshakeException;
import com.sensorsdata.analytics.android.sdk.java_websocket.exceptions.LimitExedeedException;
import com.sensorsdata.analytics.android.sdk.java_websocket.exceptions.NotSendableException;
import com.sensorsdata.analytics.android.sdk.java_websocket.framing.FrameBuilder;
import com.sensorsdata.analytics.android.sdk.java_websocket.framing.Framedata;
import com.sensorsdata.analytics.android.sdk.java_websocket.framing.Framedata.Opcode;
import com.sensorsdata.analytics.android.sdk.java_websocket.framing.FramedataImpl1;
import com.sensorsdata.analytics.android.sdk.java_websocket.handshake.ClientHandshake;
import com.sensorsdata.analytics.android.sdk.java_websocket.handshake.ClientHandshakeBuilder;
import com.sensorsdata.analytics.android.sdk.java_websocket.handshake.HandshakeBuilder;
import com.sensorsdata.analytics.android.sdk.java_websocket.handshake.ServerHandshake;
import com.sensorsdata.analytics.android.sdk.java_websocket.handshake.ServerHandshakeBuilder;
import com.sensorsdata.analytics.android.sdk.java_websocket.util.Charsetfunctions;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Draft_75 extends Draft {

    /* renamed from: CR */
    public static final byte f1068CR = 13;
    public static final byte END_OF_FRAME = -1;

    /* renamed from: LF */
    public static final byte f1069LF = 10;
    public static final byte START_OF_FRAME = 0;
    protected ByteBuffer currentFrame;
    protected boolean readingState = false;
    protected List<Framedata> readyframes = new LinkedList();
    private final Random reuseableRandom = new Random();

    public HandshakeState acceptHandshakeAsClient(ClientHandshake request, ServerHandshake response) {
        return (!request.getFieldValue("WebSocket-Origin").equals(response.getFieldValue(HttpHeaders.ORIGIN)) || !basicAccept(response)) ? HandshakeState.NOT_MATCHED : HandshakeState.MATCHED;
    }

    public HandshakeState acceptHandshakeAsServer(ClientHandshake handshakedata) {
        if (!handshakedata.hasFieldValue(HttpHeaders.ORIGIN) || !basicAccept(handshakedata)) {
            return HandshakeState.NOT_MATCHED;
        }
        return HandshakeState.MATCHED;
    }

    public ByteBuffer createBinaryFrame(Framedata framedata) {
        if (framedata.getOpcode() != Opcode.TEXT) {
            throw new RuntimeException("only text frames supported");
        }
        ByteBuffer pay = framedata.getPayloadData();
        ByteBuffer b = ByteBuffer.allocate(pay.remaining() + 2);
        b.put(0);
        pay.mark();
        b.put(pay);
        pay.reset();
        b.put(-1);
        b.flip();
        return b;
    }

    public List<Framedata> createFrames(ByteBuffer binary, boolean mask) {
        throw new RuntimeException("not yet implemented");
    }

    public List<Framedata> createFrames(String text, boolean mask) {
        FrameBuilder frame = new FramedataImpl1();
        try {
            frame.setPayload(ByteBuffer.wrap(Charsetfunctions.utf8Bytes(text)));
            frame.setFin(true);
            frame.setOptcode(Opcode.TEXT);
            frame.setTransferemasked(mask);
            return Collections.singletonList(frame);
        } catch (InvalidDataException e) {
            throw new NotSendableException((Throwable) e);
        }
    }

    public ClientHandshakeBuilder postProcessHandshakeRequestAsClient(ClientHandshakeBuilder request) throws InvalidHandshakeException {
        request.put(HttpHeaders.UPGRADE, "WebSocket");
        request.put("Connection", HttpHeaders.UPGRADE);
        if (!request.hasFieldValue(HttpHeaders.ORIGIN)) {
            request.put(HttpHeaders.ORIGIN, "random" + this.reuseableRandom.nextInt());
        }
        return request;
    }

    public HandshakeBuilder postProcessHandshakeResponseAsServer(ClientHandshake request, ServerHandshakeBuilder response) throws InvalidHandshakeException {
        response.setHttpStatusMessage("Web Socket Protocol Handshake");
        response.put(HttpHeaders.UPGRADE, "WebSocket");
        response.put("Connection", request.getFieldValue("Connection"));
        response.put("WebSocket-Origin", request.getFieldValue(HttpHeaders.ORIGIN));
        response.put("WebSocket-Location", "ws://" + request.getFieldValue("Host") + request.getResourceDescriptor());
        return response;
    }

    /* access modifiers changed from: protected */
    public List<Framedata> translateRegularFrame(ByteBuffer buffer) throws InvalidDataException {
        while (buffer.hasRemaining()) {
            byte newestByte = buffer.get();
            if (newestByte == 0) {
                if (this.readingState) {
                    throw new InvalidFrameException("unexpected START_OF_FRAME");
                }
                this.readingState = true;
            } else if (newestByte == -1) {
                if (!this.readingState) {
                    throw new InvalidFrameException("unexpected END_OF_FRAME");
                }
                if (this.currentFrame != null) {
                    this.currentFrame.flip();
                    FramedataImpl1 curframe = new FramedataImpl1();
                    curframe.setPayload(this.currentFrame);
                    curframe.setFin(true);
                    curframe.setOptcode(Opcode.TEXT);
                    this.readyframes.add(curframe);
                    this.currentFrame = null;
                    buffer.mark();
                }
                this.readingState = false;
            } else if (!this.readingState) {
                return null;
            } else {
                if (this.currentFrame == null) {
                    this.currentFrame = createBuffer();
                } else if (!this.currentFrame.hasRemaining()) {
                    this.currentFrame = increaseBuffer(this.currentFrame);
                }
                this.currentFrame.put(newestByte);
            }
        }
        List<Framedata> frames = this.readyframes;
        this.readyframes = new LinkedList();
        return frames;
    }

    public List<Framedata> translateFrame(ByteBuffer buffer) throws InvalidDataException {
        List<Framedata> frames = translateRegularFrame(buffer);
        if (frames != null) {
            return frames;
        }
        throw new InvalidDataException(1002);
    }

    public void reset() {
        this.readingState = false;
        this.currentFrame = null;
    }

    public CloseHandshakeType getCloseHandshakeType() {
        return CloseHandshakeType.NONE;
    }

    public ByteBuffer createBuffer() {
        return ByteBuffer.allocate(INITIAL_FAMESIZE);
    }

    public ByteBuffer increaseBuffer(ByteBuffer full) throws LimitExedeedException, InvalidDataException {
        full.flip();
        ByteBuffer newbuffer = ByteBuffer.allocate(checkAlloc(full.capacity() * 2));
        newbuffer.put(full);
        return newbuffer;
    }

    public Draft copyInstance() {
        return new Draft_75();
    }
}
