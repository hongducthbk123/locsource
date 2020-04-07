package p004cn.jiguang.p015d.p022e.p023a;

import java.nio.ByteBuffer;
import p004cn.jiguang.api.JResponse;
import p004cn.jiguang.api.utils.ByteBufferUtils;
import p004cn.jiguang.api.utils.ProtocolUtil;
import p004cn.jiguang.p014c.C0382a;
import p004cn.jiguang.p015d.p016a.C0386a;
import p004cn.jiguang.p015d.p022e.p023a.p024a.C0468c;

/* renamed from: cn.jiguang.d.e.a.d */
public final class C0471d extends JResponse {

    /* renamed from: a */
    int f462a;

    /* renamed from: b */
    int f463b;

    /* renamed from: c */
    String f464c;

    /* renamed from: d */
    int f465d;

    /* renamed from: e */
    int f466e;

    /* renamed from: f */
    String f467f;

    public C0471d(C0468c cVar, ByteBuffer byteBuffer) {
        super((Object) cVar, byteBuffer);
    }

    /* renamed from: a */
    public final int mo6604a() {
        return this.f465d;
    }

    public final String getName() {
        return "LoginResponse";
    }

    public final int getSid() {
        return this.f462a;
    }

    /* access modifiers changed from: protected */
    public final boolean isNeedParseeErrorMsg() {
        return true;
    }

    public final void parseBody() {
        super.parseBody();
        ByteBuffer byteBuffer = this.body;
        this.f466e = -1;
        if (this.code == 0) {
            this.f462a = ByteBufferUtils.getInt(byteBuffer, this);
            this.f463b = ByteBufferUtils.getShort(byteBuffer, this);
            this.f464c = ProtocolUtil.getTlv2(byteBuffer, this);
            this.f465d = ByteBufferUtils.getInt(byteBuffer, this);
            try {
                this.f466e = byteBuffer.get();
            } catch (Throwable th) {
            }
        } else if (this.code == 1012) {
            this.f467f = ProtocolUtil.getTlv2(byteBuffer, this);
            C0382a.m220a(this.f467f);
        }
        C0386a.m243a(this.f466e);
    }

    public final String toString() {
        return "[LoginResponse] - sid:" + this.f462a + ", serverVersion:" + this.f463b + ", sessionKey:" + this.f464c + ", serverTime:" + this.f465d + ", idc:" + this.f466e + ", connectInfo:" + this.f467f + " - " + super.toString();
    }

    public final void writeBody() {
        super.writeBody();
        writeInt4(this.f462a);
        writeInt2(this.f463b);
        writeTlv2(this.f464c);
        writeInt4(this.f465d);
    }
}
