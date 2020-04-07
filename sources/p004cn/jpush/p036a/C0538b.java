package p004cn.jpush.p036a;

import java.nio.ByteBuffer;
import p004cn.jiguang.api.JResponse;
import p004cn.jiguang.api.utils.ByteBufferUtils;
import p004cn.jiguang.api.utils.ProtocolUtil;

/* renamed from: cn.jpush.a.b */
public final class C0538b extends JResponse {

    /* renamed from: a */
    int f642a;

    /* renamed from: b */
    long f643b;

    /* renamed from: c */
    String f644c;

    public final void writeBody() {
        super.writeBody();
        writeInt1(this.f642a);
        writeLong8(this.f643b);
        writeTlv2(this.f644c);
    }

    public C0538b(Object obj, ByteBuffer byteBuffer) {
        super(obj, byteBuffer);
    }

    /* access modifiers changed from: protected */
    public final boolean isNeedParseeErrorMsg() {
        return false;
    }

    public final void parseBody() {
        super.parseBody();
        ByteBuffer byteBuffer = this.body;
        this.f642a = ByteBufferUtils.get(byteBuffer, this).byteValue();
        this.f643b = ByteBufferUtils.getLong(byteBuffer, this);
        this.f644c = ProtocolUtil.getTlv2(byteBuffer, this);
    }

    public final String getName() {
        return "MessagePush";
    }

    /* renamed from: a */
    public final int mo6769a() {
        return this.f642a;
    }

    /* renamed from: b */
    public final long mo6770b() {
        return this.f643b;
    }

    /* renamed from: c */
    public final String mo6771c() {
        return this.f644c;
    }

    public final String toString() {
        return "[MessagePush] - msgType:" + this.f642a + ", msgId:" + this.f643b + ", msgContent:" + this.f644c + " - " + super.toString();
    }
}
