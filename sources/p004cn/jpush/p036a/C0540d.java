package p004cn.jpush.p036a;

import java.nio.ByteBuffer;
import p004cn.jiguang.api.JResponse;
import p004cn.jiguang.api.utils.ProtocolUtil;

/* renamed from: cn.jpush.a.d */
public final class C0540d extends JResponse {

    /* renamed from: a */
    String f647a;

    /* renamed from: b */
    long f648b = -1;

    public final void writeBody() {
        super.writeBody();
    }

    public C0540d(Object obj, ByteBuffer byteBuffer) {
        super(obj, byteBuffer);
    }

    /* access modifiers changed from: protected */
    public final boolean isNeedParseeErrorMsg() {
        return getCommand() == 10;
    }

    public final void parseBody() {
        super.parseBody();
        if (this.code <= 0) {
            this.f647a = ProtocolUtil.getTlv2(this.body, this);
        }
    }

    public final String getName() {
        return "TagaliasResponse";
    }

    /* renamed from: a */
    public final String mo6773a() {
        return this.f647a;
    }

    public final String toString() {
        return "[TagaliasResponse] - action:" + this.f647a + " - " + super.toString();
    }
}
