package com.sensorsdata.analytics.android.sdk.java_websocket.drafts;

import com.google.common.net.HttpHeaders;
import com.sensorsdata.analytics.android.sdk.java_websocket.WebSocket.Role;
import com.sensorsdata.analytics.android.sdk.java_websocket.exceptions.InvalidDataException;
import com.sensorsdata.analytics.android.sdk.java_websocket.exceptions.InvalidHandshakeException;
import com.sensorsdata.analytics.android.sdk.java_websocket.exceptions.LimitExedeedException;
import com.sensorsdata.analytics.android.sdk.java_websocket.framing.FrameBuilder;
import com.sensorsdata.analytics.android.sdk.java_websocket.framing.Framedata;
import com.sensorsdata.analytics.android.sdk.java_websocket.framing.Framedata.Opcode;
import com.sensorsdata.analytics.android.sdk.java_websocket.framing.FramedataImpl1;
import com.sensorsdata.analytics.android.sdk.java_websocket.handshake.ClientHandshake;
import com.sensorsdata.analytics.android.sdk.java_websocket.handshake.ClientHandshakeBuilder;
import com.sensorsdata.analytics.android.sdk.java_websocket.handshake.HandshakeBuilder;
import com.sensorsdata.analytics.android.sdk.java_websocket.handshake.Handshakedata;
import com.sensorsdata.analytics.android.sdk.java_websocket.handshake.ServerHandshake;
import com.sensorsdata.analytics.android.sdk.java_websocket.handshake.ServerHandshakeBuilder;
import com.sensorsdata.analytics.android.sdk.java_websocket.util.Charsetfunctions;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.commons.p052io.IOUtils;

public abstract class Draft {
    public static final byte[] FLASH_POLICY_REQUEST = Charsetfunctions.utf8Bytes("<policy-file-request/>\u0000");
    public static int INITIAL_FAMESIZE = 64;
    public static int MAX_FAME_SIZE = 1000;
    protected Opcode continuousFrameType = null;
    protected Role role = null;

    public enum CloseHandshakeType {
        NONE,
        ONEWAY,
        TWOWAY
    }

    public enum HandshakeState {
        MATCHED,
        NOT_MATCHED
    }

    public abstract HandshakeState acceptHandshakeAsClient(ClientHandshake clientHandshake, ServerHandshake serverHandshake) throws InvalidHandshakeException;

    public abstract HandshakeState acceptHandshakeAsServer(ClientHandshake clientHandshake) throws InvalidHandshakeException;

    public abstract Draft copyInstance();

    public abstract ByteBuffer createBinaryFrame(Framedata framedata);

    public abstract List<Framedata> createFrames(String str, boolean z);

    public abstract List<Framedata> createFrames(ByteBuffer byteBuffer, boolean z);

    public abstract CloseHandshakeType getCloseHandshakeType();

    public abstract ClientHandshakeBuilder postProcessHandshakeRequestAsClient(ClientHandshakeBuilder clientHandshakeBuilder) throws InvalidHandshakeException;

    public abstract HandshakeBuilder postProcessHandshakeResponseAsServer(ClientHandshake clientHandshake, ServerHandshakeBuilder serverHandshakeBuilder) throws InvalidHandshakeException;

    public abstract void reset();

    public abstract List<Framedata> translateFrame(ByteBuffer byteBuffer) throws InvalidDataException;

    public static ByteBuffer readLine(ByteBuffer buf) {
        ByteBuffer sbuf = ByteBuffer.allocate(buf.remaining());
        byte cur = 48;
        while (buf.hasRemaining()) {
            byte prev = cur;
            cur = buf.get();
            sbuf.put(cur);
            if (prev == 13 && cur == 10) {
                sbuf.limit(sbuf.position() - 2);
                sbuf.position(0);
                return sbuf;
            }
        }
        buf.position(buf.position() - sbuf.position());
        return null;
    }

    public static String readStringLine(ByteBuffer buf) {
        ByteBuffer b = readLine(buf);
        if (b == null) {
            return null;
        }
        return Charsetfunctions.stringAscii(b.array(), 0, b.limit());
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.sensorsdata.analytics.android.sdk.java_websocket.handshake.HandshakeImpl1Client, com.sensorsdata.analytics.android.sdk.java_websocket.handshake.ClientHandshakeBuilder] */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v1, types: [com.sensorsdata.analytics.android.sdk.java_websocket.handshake.HandshakeBuilder] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.sensorsdata.analytics.android.sdk.java_websocket.handshake.HandshakeBuilder translateHandshakeHttp(java.nio.ByteBuffer r12, com.sensorsdata.analytics.android.sdk.java_websocket.WebSocket.Role r13) throws com.sensorsdata.analytics.android.sdk.java_websocket.exceptions.InvalidHandshakeException, com.sensorsdata.analytics.android.sdk.java_websocket.exceptions.IncompleteHandshakeException {
        /*
            r7 = 3
            r11 = 2
            r10 = 1
            java.lang.String r3 = readStringLine(r12)
            if (r3 != 0) goto L_0x0015
            com.sensorsdata.analytics.android.sdk.java_websocket.exceptions.IncompleteHandshakeException r6 = new com.sensorsdata.analytics.android.sdk.java_websocket.exceptions.IncompleteHandshakeException
            int r7 = r12.capacity()
            int r7 = r7 + 128
            r6.<init>(r7)
            throw r6
        L_0x0015:
            java.lang.String r6 = " "
            java.lang.String[] r1 = r3.split(r6, r7)
            int r6 = r1.length
            if (r6 == r7) goto L_0x0024
            com.sensorsdata.analytics.android.sdk.java_websocket.exceptions.InvalidHandshakeException r6 = new com.sensorsdata.analytics.android.sdk.java_websocket.exceptions.InvalidHandshakeException
            r6.<init>()
            throw r6
        L_0x0024:
            com.sensorsdata.analytics.android.sdk.java_websocket.WebSocket$Role r6 = com.sensorsdata.analytics.android.sdk.java_websocket.WebSocket.Role.CLIENT
            if (r13 != r6) goto L_0x005b
            com.sensorsdata.analytics.android.sdk.java_websocket.handshake.HandshakeImpl1Server r2 = new com.sensorsdata.analytics.android.sdk.java_websocket.handshake.HandshakeImpl1Server
            r2.<init>()
            r5 = r2
            com.sensorsdata.analytics.android.sdk.java_websocket.handshake.ServerHandshakeBuilder r5 = (com.sensorsdata.analytics.android.sdk.java_websocket.handshake.ServerHandshakeBuilder) r5
            r6 = r1[r10]
            short r6 = java.lang.Short.parseShort(r6)
            r5.setHttpStatus(r6)
            r6 = r1[r11]
            r5.setHttpStatusMessage(r6)
        L_0x003e:
            java.lang.String r3 = readStringLine(r12)
        L_0x0042:
            if (r3 == 0) goto L_0x007c
            int r6 = r3.length()
            if (r6 <= 0) goto L_0x007c
            java.lang.String r6 = ":"
            java.lang.String[] r4 = r3.split(r6, r11)
            int r6 = r4.length
            if (r6 == r11) goto L_0x0067
            com.sensorsdata.analytics.android.sdk.java_websocket.exceptions.InvalidHandshakeException r6 = new com.sensorsdata.analytics.android.sdk.java_websocket.exceptions.InvalidHandshakeException
            java.lang.String r7 = "not an http header"
            r6.<init>(r7)
            throw r6
        L_0x005b:
            com.sensorsdata.analytics.android.sdk.java_websocket.handshake.HandshakeImpl1Client r0 = new com.sensorsdata.analytics.android.sdk.java_websocket.handshake.HandshakeImpl1Client
            r0.<init>()
            r6 = r1[r10]
            r0.setResourceDescriptor(r6)
            r2 = r0
            goto L_0x003e
        L_0x0067:
            r6 = 0
            r6 = r4[r6]
            r7 = r4[r10]
            java.lang.String r8 = "^ +"
            java.lang.String r9 = ""
            java.lang.String r7 = r7.replaceFirst(r8, r9)
            r2.put(r6, r7)
            java.lang.String r3 = readStringLine(r12)
            goto L_0x0042
        L_0x007c:
            if (r3 != 0) goto L_0x0084
            com.sensorsdata.analytics.android.sdk.java_websocket.exceptions.IncompleteHandshakeException r6 = new com.sensorsdata.analytics.android.sdk.java_websocket.exceptions.IncompleteHandshakeException
            r6.<init>()
            throw r6
        L_0x0084:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sensorsdata.analytics.android.sdk.java_websocket.drafts.Draft.translateHandshakeHttp(java.nio.ByteBuffer, com.sensorsdata.analytics.android.sdk.java_websocket.WebSocket$Role):com.sensorsdata.analytics.android.sdk.java_websocket.handshake.HandshakeBuilder");
    }

    /* access modifiers changed from: protected */
    public boolean basicAccept(Handshakedata handshakedata) {
        return handshakedata.getFieldValue(HttpHeaders.UPGRADE).equalsIgnoreCase("websocket") && handshakedata.getFieldValue("Connection").toLowerCase(Locale.ENGLISH).contains("upgrade");
    }

    public List<Framedata> continuousFrame(Opcode op, ByteBuffer buffer, boolean fin) {
        if (op == Opcode.BINARY || op == Opcode.TEXT || op == Opcode.TEXT) {
            if (this.continuousFrameType != null) {
                this.continuousFrameType = Opcode.CONTINUOUS;
            } else {
                this.continuousFrameType = op;
            }
            FrameBuilder bui = new FramedataImpl1(this.continuousFrameType);
            try {
                bui.setPayload(buffer);
                bui.setFin(fin);
                if (fin) {
                    this.continuousFrameType = null;
                } else {
                    this.continuousFrameType = op;
                }
                return Collections.singletonList(bui);
            } catch (InvalidDataException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalArgumentException("Only Opcode.BINARY or  Opcode.TEXT are allowed");
        }
    }

    public List<ByteBuffer> createHandshake(Handshakedata handshakedata, Role ownrole) {
        return createHandshake(handshakedata, ownrole, true);
    }

    public List<ByteBuffer> createHandshake(Handshakedata handshakedata, Role ownrole, boolean withcontent) {
        StringBuilder bui = new StringBuilder(100);
        if (handshakedata instanceof ClientHandshake) {
            bui.append("GET ");
            bui.append(((ClientHandshake) handshakedata).getResourceDescriptor());
            bui.append(" HTTP/1.1");
        } else if (handshakedata instanceof ServerHandshake) {
            bui.append("HTTP/1.1 101 " + ((ServerHandshake) handshakedata).getHttpStatusMessage());
        } else {
            throw new RuntimeException("unknow role");
        }
        bui.append(IOUtils.LINE_SEPARATOR_WINDOWS);
        Iterator<String> it = handshakedata.iterateHttpFields();
        while (it.hasNext()) {
            String fieldname = (String) it.next();
            String fieldvalue = handshakedata.getFieldValue(fieldname);
            bui.append(fieldname);
            bui.append(": ");
            bui.append(fieldvalue);
            bui.append(IOUtils.LINE_SEPARATOR_WINDOWS);
        }
        bui.append(IOUtils.LINE_SEPARATOR_WINDOWS);
        byte[] httpheader = Charsetfunctions.asciiBytes(bui.toString());
        byte[] content = withcontent ? handshakedata.getContent() : null;
        ByteBuffer bytebuffer = ByteBuffer.allocate((content == null ? 0 : content.length) + httpheader.length);
        bytebuffer.put(httpheader);
        if (content != null) {
            bytebuffer.put(content);
        }
        bytebuffer.flip();
        return Collections.singletonList(bytebuffer);
    }

    public Handshakedata translateHandshake(ByteBuffer buf) throws InvalidHandshakeException {
        return translateHandshakeHttp(buf, this.role);
    }

    public int checkAlloc(int bytecount) throws LimitExedeedException, InvalidDataException {
        if (bytecount >= 0) {
            return bytecount;
        }
        throw new InvalidDataException(1002, "Negative count");
    }

    public void setParseMode(Role role2) {
        this.role = role2;
    }

    public Role getRole() {
        return this.role;
    }
}
