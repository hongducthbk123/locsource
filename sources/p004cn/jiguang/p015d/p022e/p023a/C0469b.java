package p004cn.jiguang.p015d.p022e.p023a;

import java.nio.ByteBuffer;
import p004cn.jiguang.api.JResponse;
import p004cn.jiguang.api.utils.ByteBufferUtils;
import p004cn.jiguang.api.utils.ProtocolUtil;
import p004cn.jiguang.p015d.p022e.p023a.p024a.C0468c;

/* renamed from: cn.jiguang.d.e.a.b */
public final class C0469b extends JResponse {

    /* renamed from: a */
    long f458a;

    /* renamed from: b */
    String f459b;

    public C0469b(C0468c cVar, ByteBuffer byteBuffer) {
        super((Object) cVar, byteBuffer);
    }

    /* renamed from: a */
    public final long mo6600a() {
        return this.f458a;
    }

    /* renamed from: b */
    public final String mo6601b() {
        return this.f459b;
    }

    public final String getName() {
        return "CtrlResponse";
    }

    /* access modifiers changed from: protected */
    public final boolean isNeedParseeErrorMsg() {
        return false;
    }

    public final void parseBody() {
        super.parseBody();
        ByteBuffer byteBuffer = this.body;
        this.f458a = ByteBufferUtils.getLong(byteBuffer, this);
        this.f459b = ProtocolUtil.getTlv2(byteBuffer, this);
    }

    public final String toString() {
        return "[CtrlResponse] - senderId:" + this.f458a + ", msgContent:" + this.f459b + " - " + super.toString();
    }

    public final void writeBody() {
        super.writeBody();
        writeLong8(this.f458a);
        writeTlv2(this.f459b);
    }
}
