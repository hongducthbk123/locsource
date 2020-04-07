package p004cn.jiguang.p015d.p022e.p023a;

import java.nio.ByteBuffer;
import p004cn.jiguang.api.JResponse;
import p004cn.jiguang.p015d.p022e.p023a.p024a.C0468c;

/* renamed from: cn.jiguang.d.e.a.f */
public final class C0473f extends JResponse {
    public C0473f(C0468c cVar, ByteBuffer byteBuffer) {
        super((Object) cVar, byteBuffer);
    }

    public final String getName() {
        return "UserCtrlResponse";
    }

    /* access modifiers changed from: protected */
    public final boolean isNeedParseeErrorMsg() {
        return true;
    }

    public final void parseBody() {
        super.parseBody();
    }

    public final void writeBody() {
        super.writeBody();
    }
}
