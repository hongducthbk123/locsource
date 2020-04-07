package p004cn.jiguang.p015d.p022e.p023a;

import java.nio.ByteBuffer;
import p004cn.jiguang.api.JResponse;
import p004cn.jiguang.api.utils.ByteBufferUtils;
import p004cn.jiguang.api.utils.ProtocolUtil;
import p004cn.jiguang.p014c.C0382a;
import p004cn.jiguang.p015d.p022e.p023a.p024a.C0468c;

/* renamed from: cn.jiguang.d.e.a.e */
public final class C0472e extends JResponse {

    /* renamed from: a */
    long f468a;

    /* renamed from: b */
    String f469b;

    /* renamed from: c */
    String f470c;

    /* renamed from: d */
    String f471d;

    /* renamed from: e */
    String f472e;

    /* renamed from: f */
    String f473f;

    public C0472e(C0468c cVar, ByteBuffer byteBuffer) {
        super((Object) cVar, byteBuffer);
    }

    /* renamed from: a */
    public final String mo6605a() {
        return this.f469b;
    }

    /* renamed from: b */
    public final String mo6606b() {
        return this.f470c;
    }

    /* renamed from: c */
    public final String mo6607c() {
        return this.f471d;
    }

    public final long getJuid() {
        return this.f468a;
    }

    public final String getName() {
        return "RegisterResponse";
    }

    /* access modifiers changed from: protected */
    public final boolean isNeedParseeErrorMsg() {
        return true;
    }

    /* access modifiers changed from: protected */
    public final void parseBody() {
        super.parseBody();
        ByteBuffer byteBuffer = this.body;
        if (this.code == 0) {
            this.f468a = ByteBufferUtils.getLong(byteBuffer, this);
            this.f469b = ProtocolUtil.getTlv2(byteBuffer, this);
            this.f470c = ProtocolUtil.getTlv2(byteBuffer, this);
        } else if (this.code == 1007) {
            this.f472e = ProtocolUtil.getTlv2(byteBuffer, this);
        } else if (this.code == 1012) {
            this.f473f = ProtocolUtil.getTlv2(byteBuffer, this);
            C0382a.m220a(this.f473f);
        }
    }

    public final String toString() {
        return "[RegisterResponse] - juid:" + this.f468a + ", password:" + this.f469b + ", regId:" + this.f470c + ", deviceId:" + this.f471d + ", connectInfo:" + this.f473f + " - " + super.toString();
    }

    /* access modifiers changed from: protected */
    public final void writeBody() {
        super.writeBody();
        writeLong8(this.f468a);
        writeTlv2(this.f469b);
        writeTlv2(this.f470c);
        writeTlv2(this.f471d);
    }
}
