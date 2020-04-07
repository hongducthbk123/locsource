package p004cn.jiguang.api;

import java.nio.ByteBuffer;
import p004cn.jiguang.api.utils.ByteBufferUtils;

/* renamed from: cn.jiguang.api.JResponse */
public abstract class JResponse extends JProtocol {
    public int code;

    public JResponse(int i, int i2, long j, long j2, int i3, String str) {
        super(false, i, i2, j, -1, j2);
        this.code = i3;
    }

    public JResponse(Object obj, ByteBuffer byteBuffer) {
        super(false, obj, byteBuffer);
    }

    public JResponse(ByteBuffer byteBuffer, byte[] bArr) {
        super(false, byteBuffer, bArr);
    }

    /* access modifiers changed from: protected */
    public void parseBody() {
        if (isNeedParseeErrorMsg()) {
            this.code = ByteBufferUtils.getShort(this.body, this);
        }
    }

    public String toString() {
        return "JResponse{code=" + this.code + '}';
    }

    /* access modifiers changed from: protected */
    public void writeBody() {
        if (this.code >= 0) {
            writeInt2(this.code);
        }
    }
}
