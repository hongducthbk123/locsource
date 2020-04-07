package p004cn.jpush.p036a;

import java.nio.ByteBuffer;
import p004cn.jiguang.api.JResponse;

/* renamed from: cn.jpush.a.a */
public final class C0537a extends JResponse {
    public final void writeBody() {
        super.writeBody();
    }

    public C0537a(Object obj, ByteBuffer byteBuffer) {
        super(obj, byteBuffer);
    }

    /* access modifiers changed from: protected */
    public final boolean isNeedParseeErrorMsg() {
        return true;
    }

    public final void parseBody() {
        super.parseBody();
    }

    public final String getName() {
        return "CommonResponse";
    }

    public final String toString() {
        return "[CommonResponse] - " + super.toString();
    }
}
