package p004cn.jiguang.p015d.p022e.p023a.p024a;

import java.nio.ByteBuffer;
import p004cn.jiguang.api.utils.ProtocolUtil;

/* renamed from: cn.jiguang.d.e.a.a.c */
public final class C0468c {

    /* renamed from: a */
    int f451a;

    /* renamed from: b */
    int f452b;

    /* renamed from: c */
    int f453c;

    /* renamed from: d */
    Long f454d;

    /* renamed from: e */
    int f455e;

    /* renamed from: f */
    long f456f;

    /* renamed from: g */
    private boolean f457g;

    public C0468c(boolean z, int i, int i2, int i3, long j, int i4, long j2) {
        this.f457g = false;
        this.f457g = z;
        this.f451a = 0;
        this.f452b = i2;
        this.f453c = i3;
        this.f454d = Long.valueOf(j);
        this.f455e = i4;
        this.f456f = j2;
    }

    public C0468c(boolean z, int i, int i2, long j) {
        this(z, 0, i, i2, j, 0, 0);
    }

    public C0468c(boolean z, byte[] bArr) {
        this.f457g = false;
        this.f457g = z;
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        this.f451a = wrap.getShort();
        this.f451a &= 32767;
        this.f452b = wrap.get();
        this.f453c = wrap.get();
        this.f454d = Long.valueOf(wrap.getLong());
        if (z) {
            this.f455e = wrap.getInt();
        }
        this.f456f = wrap.getLong();
    }

    /* renamed from: a */
    public final int mo6588a() {
        return this.f453c;
    }

    /* renamed from: a */
    public final void mo6589a(int i) {
        this.f451a = i;
    }

    /* renamed from: a */
    public final void mo6590a(long j) {
        this.f456f = j;
    }

    /* renamed from: a */
    public final void mo6591a(Long l) {
        this.f454d = l;
    }

    /* renamed from: b */
    public final Long mo6592b() {
        return this.f454d;
    }

    /* renamed from: b */
    public final void mo6593b(int i) {
        this.f455e = i;
    }

    /* renamed from: c */
    public final long mo6594c() {
        return this.f456f;
    }

    /* renamed from: d */
    public final int mo6595d() {
        return this.f455e;
    }

    /* renamed from: e */
    public final int mo6596e() {
        return this.f452b;
    }

    /* renamed from: f */
    public final int mo6597f() {
        return this.f451a;
    }

    /* renamed from: g */
    public final byte[] mo6598g() {
        if (this.f451a == 0) {
            throw new IllegalStateException("The head is not initialized yet.");
        }
        ByteBuffer allocate = ByteBuffer.allocate(24);
        allocate.putShort((short) this.f451a);
        allocate.put((byte) this.f452b);
        allocate.put((byte) this.f453c);
        allocate.putLong(this.f454d.longValue());
        if (this.f457g) {
            allocate.putInt(this.f455e);
        }
        allocate.putLong(this.f456f);
        allocate.flip();
        return ProtocolUtil.getBytesConsumed(allocate);
    }

    public final String toString() {
        return "[JHead] - len:" + this.f451a + ", version:" + this.f452b + ", command:" + this.f453c + ", rid:" + this.f454d + (this.f457g ? ", sid:" + this.f455e : "") + ", juid:" + this.f456f;
    }
}
