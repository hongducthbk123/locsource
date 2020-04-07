package p004cn.jiguang.api;

import java.nio.ByteBuffer;

/* renamed from: cn.jiguang.api.JRequest */
public abstract class JRequest extends JProtocol {
    public JRequest(int i, int i2, long j) {
        super(true, i, i2, j);
    }

    public JRequest(Object obj, ByteBuffer byteBuffer) {
        super(true, obj, byteBuffer);
    }

    public void setJuid(long j) {
        this.head.mo6590a(j);
    }

    public void setSid(int i) {
        this.head.mo6593b(i);
    }
}
