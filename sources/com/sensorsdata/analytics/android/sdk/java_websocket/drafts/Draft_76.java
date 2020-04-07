package com.sensorsdata.analytics.android.sdk.java_websocket.drafts;

import com.adjust.sdk.Constants;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.common.net.HttpHeaders;
import com.sensorsdata.analytics.android.sdk.java_websocket.WebSocket.Role;
import com.sensorsdata.analytics.android.sdk.java_websocket.drafts.Draft.CloseHandshakeType;
import com.sensorsdata.analytics.android.sdk.java_websocket.drafts.Draft.HandshakeState;
import com.sensorsdata.analytics.android.sdk.java_websocket.exceptions.IncompleteHandshakeException;
import com.sensorsdata.analytics.android.sdk.java_websocket.exceptions.InvalidDataException;
import com.sensorsdata.analytics.android.sdk.java_websocket.exceptions.InvalidFrameException;
import com.sensorsdata.analytics.android.sdk.java_websocket.exceptions.InvalidHandshakeException;
import com.sensorsdata.analytics.android.sdk.java_websocket.framing.CloseFrameBuilder;
import com.sensorsdata.analytics.android.sdk.java_websocket.framing.Framedata;
import com.sensorsdata.analytics.android.sdk.java_websocket.framing.Framedata.Opcode;
import com.sensorsdata.analytics.android.sdk.java_websocket.handshake.ClientHandshake;
import com.sensorsdata.analytics.android.sdk.java_websocket.handshake.ClientHandshakeBuilder;
import com.sensorsdata.analytics.android.sdk.java_websocket.handshake.HandshakeBuilder;
import com.sensorsdata.analytics.android.sdk.java_websocket.handshake.Handshakedata;
import com.sensorsdata.analytics.android.sdk.java_websocket.handshake.ServerHandshake;
import com.sensorsdata.analytics.android.sdk.java_websocket.handshake.ServerHandshakeBuilder;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Draft_76 extends Draft_75 {
    private static final byte[] closehandshake = {-1, 0};
    private boolean failed = false;
    private final Random reuseableRandom = new Random();

    public static byte[] createChallenge(String key1, String key2, byte[] key3) throws InvalidHandshakeException {
        byte[] part1 = getPart(key1);
        byte[] part2 = getPart(key2);
        try {
            return MessageDigest.getInstance(Constants.MD5).digest(new byte[]{part1[0], part1[1], part1[2], part1[3], part2[0], part2[1], part2[2], part2[3], key3[0], key3[1], key3[2], key3[3], key3[4], key3[5], key3[6], key3[7]});
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String generateKey() {
        Random r = new Random();
        long spaces = (long) (r.nextInt(12) + 1);
        Long l = new Long(4294967295L / spaces);
        String key = Long.toString(((long) (r.nextInt(Math.abs(l.intValue())) + 1)) * spaces);
        int numChars = r.nextInt(12) + 1;
        for (int i = 0; i < numChars; i++) {
            int position = Math.abs(r.nextInt(key.length()));
            char randChar = (char) (r.nextInt(95) + 33);
            if (randChar >= '0' && randChar <= '9') {
                randChar = (char) (randChar - 15);
            }
            StringBuilder sb = new StringBuilder(key);
            key = sb.insert(position, randChar).toString();
        }
        for (int i2 = 0; ((long) i2) < spaces; i2++) {
            int position2 = Math.abs(r.nextInt(key.length() - 1) + 1);
            StringBuilder sb2 = new StringBuilder(key);
            key = sb2.insert(position2, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).toString();
        }
        return key;
    }

    private static byte[] getPart(String key) throws InvalidHandshakeException {
        try {
            long keyNumber = Long.parseLong(key.replaceAll("[^0-9]", ""));
            long keySpace = (long) (key.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).length - 1);
            if (keySpace == 0) {
                throw new InvalidHandshakeException("invalid Sec-WebSocket-Key (/key2/)");
            }
            long part = new Long(keyNumber / keySpace).longValue();
            return new byte[]{(byte) ((int) (part >> 24)), (byte) ((int) ((part << 8) >> 24)), (byte) ((int) ((part << 16) >> 24)), (byte) ((int) ((part << 24) >> 24))};
        } catch (NumberFormatException e) {
            throw new InvalidHandshakeException("invalid Sec-WebSocket-Key (/key1/ or /key2/)");
        }
    }

    public HandshakeState acceptHandshakeAsClient(ClientHandshake request, ServerHandshake response) {
        if (this.failed) {
            return HandshakeState.NOT_MATCHED;
        }
        try {
            if (!response.getFieldValue("Sec-WebSocket-Origin").equals(request.getFieldValue(HttpHeaders.ORIGIN)) || !basicAccept(response)) {
                return HandshakeState.NOT_MATCHED;
            }
            byte[] content = response.getContent();
            if (content == null || content.length == 0) {
                throw new IncompleteHandshakeException();
            } else if (Arrays.equals(content, createChallenge(request.getFieldValue("Sec-WebSocket-Key1"), request.getFieldValue("Sec-WebSocket-Key2"), request.getContent()))) {
                return HandshakeState.MATCHED;
            } else {
                return HandshakeState.NOT_MATCHED;
            }
        } catch (InvalidHandshakeException e) {
            throw new RuntimeException("bad handshakerequest", e);
        }
    }

    public HandshakeState acceptHandshakeAsServer(ClientHandshake handshakedata) {
        if (!handshakedata.getFieldValue(HttpHeaders.UPGRADE).equals("WebSocket") || !handshakedata.getFieldValue("Connection").contains(HttpHeaders.UPGRADE) || handshakedata.getFieldValue("Sec-WebSocket-Key1").length() <= 0 || handshakedata.getFieldValue("Sec-WebSocket-Key2").isEmpty() || !handshakedata.hasFieldValue(HttpHeaders.ORIGIN)) {
            return HandshakeState.NOT_MATCHED;
        }
        return HandshakeState.MATCHED;
    }

    public ClientHandshakeBuilder postProcessHandshakeRequestAsClient(ClientHandshakeBuilder request) {
        request.put(HttpHeaders.UPGRADE, "WebSocket");
        request.put("Connection", HttpHeaders.UPGRADE);
        request.put("Sec-WebSocket-Key1", generateKey());
        request.put("Sec-WebSocket-Key2", generateKey());
        if (!request.hasFieldValue(HttpHeaders.ORIGIN)) {
            request.put(HttpHeaders.ORIGIN, "random" + this.reuseableRandom.nextInt());
        }
        byte[] key3 = new byte[8];
        this.reuseableRandom.nextBytes(key3);
        request.setContent(key3);
        return request;
    }

    public HandshakeBuilder postProcessHandshakeResponseAsServer(ClientHandshake request, ServerHandshakeBuilder response) throws InvalidHandshakeException {
        response.setHttpStatusMessage("WebSocket Protocol Handshake");
        response.put(HttpHeaders.UPGRADE, "WebSocket");
        response.put("Connection", request.getFieldValue("Connection"));
        response.put("Sec-WebSocket-Origin", request.getFieldValue(HttpHeaders.ORIGIN));
        response.put("Sec-WebSocket-Location", "ws://" + request.getFieldValue("Host") + request.getResourceDescriptor());
        String key1 = request.getFieldValue("Sec-WebSocket-Key1");
        String key2 = request.getFieldValue("Sec-WebSocket-Key2");
        byte[] key3 = request.getContent();
        if (key1 == null || key2 == null || key3 == null || key3.length != 8) {
            throw new InvalidHandshakeException("Bad keys");
        }
        response.setContent(createChallenge(key1, key2, key3));
        return response;
    }

    public Handshakedata translateHandshake(ByteBuffer buf) throws InvalidHandshakeException {
        HandshakeBuilder bui = translateHandshakeHttp(buf, this.role);
        if ((bui.hasFieldValue("Sec-WebSocket-Key1") || this.role == Role.CLIENT) && !bui.hasFieldValue("Sec-WebSocket-Version")) {
            byte[] key3 = new byte[(this.role == Role.SERVER ? 8 : 16)];
            try {
                buf.get(key3);
                bui.setContent(key3);
            } catch (BufferUnderflowException e) {
                throw new IncompleteHandshakeException(buf.capacity() + 16);
            }
        }
        return bui;
    }

    public List<Framedata> translateFrame(ByteBuffer buffer) throws InvalidDataException {
        buffer.mark();
        List<Framedata> frames = super.translateRegularFrame(buffer);
        if (frames != null) {
            return frames;
        }
        buffer.reset();
        List<Framedata> frames2 = this.readyframes;
        this.readingState = true;
        if (this.currentFrame == null) {
            this.currentFrame = ByteBuffer.allocate(2);
            if (buffer.remaining() > this.currentFrame.remaining()) {
                throw new InvalidFrameException();
            }
            this.currentFrame.put(buffer);
            if (this.currentFrame.hasRemaining()) {
                this.readyframes = new LinkedList();
                return frames2;
            } else if (Arrays.equals(this.currentFrame.array(), closehandshake)) {
                frames2.add(new CloseFrameBuilder(1000));
                return frames2;
            } else {
                throw new InvalidFrameException();
            }
        } else {
            throw new InvalidFrameException();
        }
    }

    public ByteBuffer createBinaryFrame(Framedata framedata) {
        if (framedata.getOpcode() == Opcode.CLOSING) {
            return ByteBuffer.wrap(closehandshake);
        }
        return super.createBinaryFrame(framedata);
    }

    public CloseHandshakeType getCloseHandshakeType() {
        return CloseHandshakeType.ONEWAY;
    }

    public Draft copyInstance() {
        return new Draft_76();
    }
}