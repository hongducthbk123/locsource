package p004cn.jiguang.p015d.p020c;

import java.nio.ByteBuffer;

/* renamed from: cn.jiguang.d.c.d */
public final class C0427d {

    /* renamed from: a */
    private ByteBuffer f340a;

    /* renamed from: b */
    private int f341b = -1;

    /* renamed from: c */
    private int f342c = -1;

    public C0427d(byte[] bArr) {
        this.f340a = ByteBuffer.wrap(bArr);
    }

    /* renamed from: c */
    private void m517c(int i) {
        if (i > this.f340a.remaining()) {
            throw new C0443t("end of input");
        }
    }

    /* renamed from: a */
    public final int mo6484a() {
        return this.f340a.position();
    }

    /* renamed from: a */
    public final void mo6485a(int i) {
        if (i > this.f340a.capacity() - this.f340a.position()) {
            throw new IllegalArgumentException("cannot set active region past end of input");
        }
        this.f340a.limit(this.f340a.position() + i);
    }

    /* renamed from: a */
    public final void mo6486a(byte[] bArr, int i, int i2) {
        m517c(i2);
        this.f340a.get(bArr, 1, i2);
    }

    /* renamed from: b */
    public final int mo6487b() {
        return this.f340a.remaining();
    }

    /* renamed from: b */
    public final void mo6488b(int i) {
        if (i >= this.f340a.capacity()) {
            throw new IllegalArgumentException("cannot jump past end of input");
        }
        this.f340a.position(i);
        this.f340a.limit(this.f340a.capacity());
    }

    /* renamed from: c */
    public final void mo6489c() {
        this.f340a.limit(this.f340a.capacity());
    }

    /* renamed from: d */
    public final void mo6490d() {
        this.f341b = this.f340a.position();
        this.f342c = this.f340a.limit();
    }

    /* renamed from: e */
    public final void mo6491e() {
        if (this.f341b < 0) {
            throw new IllegalStateException("no previous state");
        }
        this.f340a.position(this.f341b);
        this.f340a.limit(this.f342c);
        this.f341b = -1;
        this.f342c = -1;
    }

    /* renamed from: f */
    public final int mo6492f() {
        m517c(1);
        return this.f340a.get() & 255;
    }

    /* renamed from: g */
    public final int mo6493g() {
        m517c(2);
        return this.f340a.getShort() & 65535;
    }

    /* renamed from: h */
    public final long mo6494h() {
        m517c(4);
        return ((long) this.f340a.getInt()) & 4294967295L;
    }
}
