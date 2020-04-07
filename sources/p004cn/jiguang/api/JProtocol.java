package p004cn.jiguang.api;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import p004cn.jiguang.api.utils.ProtocolUtil;
import p004cn.jiguang.p015d.p022e.p023a.p024a.C0468c;

/* renamed from: cn.jiguang.api.JProtocol */
public abstract class JProtocol {
    public static final int DEFAULT_RESP_CODE = 0;
    public static final int DEFAULT_RID = 0;
    public static final int DEFAULT_SID = 0;
    public static final int NO_JUID = -1;
    public static final int NO_RESP_CODE = -1;
    public static final int NO_SID = -1;
    private static final int PACKET_SIZE = 7168;
    private static final String TAG = "JProtocol";
    protected ByteBuffer body;
    protected C0468c head;
    private boolean isRequest;

    public JProtocol(boolean z, int i, int i2, long j) {
        this.isRequest = z;
        this.head = new C0468c(z, i, i2, j);
        this.body = ByteBuffer.allocate(PACKET_SIZE);
    }

    public JProtocol(boolean z, int i, int i2, long j, int i3, long j2) {
        this.isRequest = z;
        this.head = new C0468c(z, 0, i, i2, j, i3, j2);
        this.body = ByteBuffer.allocate(PACKET_SIZE);
    }

    public JProtocol(boolean z, Object obj, ByteBuffer byteBuffer) {
        this.isRequest = z;
        this.head = (C0468c) obj;
        if (byteBuffer != null) {
            this.body = byteBuffer;
            parseBody();
        }
    }

    public JProtocol(boolean z, ByteBuffer byteBuffer, byte[] bArr) {
        this.isRequest = z;
        try {
            this.head = new C0468c(z, bArr);
        } catch (Exception e) {
        }
        if (byteBuffer != null) {
            this.body = byteBuffer;
            parseBody();
        }
    }

    public static byte[] parseHead(Object obj) {
        if (obj != null && (obj instanceof C0468c)) {
            return ((C0468c) obj).mo6598g();
        }
        return null;
    }

    private final byte[] toBytes() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bytes = ProtocolUtil.getBytes(this.body);
        if (bytes == null) {
            return null;
        }
        this.head.mo6589a((this.isRequest ? 24 : 20) + bytes.length);
        try {
            byteArrayOutputStream.write(this.head.mo6598g());
            byteArrayOutputStream.write(bytes);
        } catch (Exception e) {
        }
        return byteArrayOutputStream.toByteArray();
    }

    public ByteBuffer getBody() {
        return this.body;
    }

    public int getCommand() {
        return this.head.mo6588a();
    }

    public C0468c getHead() {
        return this.head;
    }

    public long getJuid() {
        return this.head.mo6594c();
    }

    public abstract String getName();

    public Long getRid() {
        return this.head.mo6592b();
    }

    public int getSid() {
        return this.head.mo6595d();
    }

    public int getVersion() {
        return this.head.mo6596e();
    }

    /* access modifiers changed from: protected */
    public abstract boolean isNeedParseeErrorMsg();

    /* access modifiers changed from: protected */
    public abstract void parseBody();

    public String toString() {
        return (this.isRequest ? "[Request]" : "[Response]") + " - " + this.head.toString();
    }

    /* access modifiers changed from: protected */
    public abstract void writeBody();

    public final byte[] writeBodyAndToBytes() {
        this.body.clear();
        writeBody();
        this.body.flip();
        return toBytes();
    }

    /* access modifiers changed from: protected */
    public void writeBytes(byte[] bArr) {
        this.body.put(bArr);
    }

    /* access modifiers changed from: protected */
    public void writeInt1(int i) {
        this.body.put((byte) i);
    }

    /* access modifiers changed from: protected */
    public void writeInt2(int i) {
        this.body.putShort((short) i);
    }

    /* access modifiers changed from: protected */
    public void writeInt4(int i) {
        this.body.putInt(i);
    }

    /* access modifiers changed from: protected */
    public void writeLong8(long j) {
        this.body.putLong(j);
    }

    /* access modifiers changed from: protected */
    public void writeTlv2(String str) {
        this.body.put(ProtocolUtil.tlv2ToByteArray(str));
    }
}
