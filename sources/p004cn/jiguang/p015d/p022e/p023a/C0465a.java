package p004cn.jiguang.p015d.p022e.p023a;

import java.nio.ByteBuffer;
import p004cn.jiguang.api.JResponse;
import p004cn.jiguang.api.utils.ByteBufferUtils;
import p004cn.jiguang.p015d.p022e.p023a.p024a.C0468c;

/* renamed from: cn.jiguang.d.e.a.a */
public final class C0465a extends JResponse {

    /* renamed from: a */
    int f447a;

    /* renamed from: b */
    int f448b;

    /* renamed from: c */
    int f449c;

    /* renamed from: d */
    long f450d;

    public C0465a(C0468c cVar, ByteBuffer byteBuffer) {
        super((Object) cVar, byteBuffer);
    }

    /* renamed from: a */
    public final int mo6587a() {
        return this.f447a;
    }

    public final String getName() {
        return "ACK Response";
    }

    /* access modifiers changed from: protected */
    public final boolean isNeedParseeErrorMsg() {
        return false;
    }

    public final void parseBody() {
        super.parseBody();
        ByteBuffer byteBuffer = this.body;
        this.f447a = ByteBufferUtils.get(byteBuffer, this).byteValue();
        this.f448b = ByteBufferUtils.get(byteBuffer, this).byteValue();
        this.f449c = ByteBufferUtils.get(byteBuffer, this).byteValue();
        this.f450d = ByteBufferUtils.getLong(byteBuffer, this);
    }

    public final String toString() {
        return "[AckNormal] - requestCommand:" + this.f447a + ", step:" + this.f448b + ", status:" + this.f449c + ", stime:" + this.f450d + " - " + super.toString();
    }

    public final void writeBody() {
        super.writeBody();
        writeInt1(this.f447a);
        writeInt1(this.f448b);
        writeInt1(this.f449c);
        writeLong8(this.f450d);
    }
}
