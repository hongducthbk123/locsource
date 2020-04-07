package p004cn.jiguang.p031g;

import java.nio.ByteBuffer;

/* renamed from: cn.jiguang.g.h */
public final class C0527h {

    /* renamed from: a */
    private ByteBuffer f605a;

    /* renamed from: b */
    private int f606b = -1;

    /* renamed from: c */
    private int f607c = -1;

    public C0527h(byte[] bArr) {
        this.f605a = ByteBuffer.wrap(bArr);
    }

    /* renamed from: b */
    private void m1089b(int i) {
        if (i > this.f605a.remaining()) {
            throw new C0534o("end of input");
        }
    }

    /* renamed from: a */
    public final int mo6702a() {
        m1089b(2);
        return this.f605a.getShort() & 65535;
    }

    /* renamed from: a */
    public final byte[] mo6703a(int i) {
        m1089b(2);
        byte[] bArr = new byte[2];
        this.f605a.get(bArr, 0, 2);
        return bArr;
    }

    /* renamed from: b */
    public final long mo6704b() {
        m1089b(4);
        return ((long) this.f605a.getInt()) & 4294967295L;
    }

    /* renamed from: c */
    public final byte[] mo6705c() {
        int remaining = this.f605a.remaining();
        byte[] bArr = new byte[remaining];
        this.f605a.get(bArr, 0, remaining);
        return bArr;
    }
}
