package p004cn.jpush.p036a;

import java.nio.ByteBuffer;
import p004cn.jiguang.api.JRequest;
import p004cn.jiguang.api.utils.ProtocolUtil;

/* renamed from: cn.jpush.a.c */
public final class C0539c extends JRequest {

    /* renamed from: a */
    String f645a;

    /* renamed from: b */
    String f646b;

    public final void parseBody() {
        ByteBuffer byteBuffer = this.body;
        this.f645a = ProtocolUtil.getTlv2(byteBuffer);
        this.f646b = ProtocolUtil.getTlv2(byteBuffer);
    }

    /* access modifiers changed from: protected */
    public final boolean isNeedParseeErrorMsg() {
        return true;
    }

    public C0539c(int i, int i2, long j, String str, String str2) {
        super(i, i2, j);
        this.f645a = str;
        this.f646b = str2;
    }

    public final void writeBody() {
        writeTlv2(this.f645a);
        writeTlv2(this.f646b);
    }

    public final String getName() {
        return "TagaliasRequest";
    }

    /* renamed from: a */
    public final String mo6772a() {
        return this.f646b;
    }

    public final String toString() {
        return "[TagaliasRequest] - appKey:" + this.f645a + ", action:" + this.f646b + " - " + super.toString();
    }
}
