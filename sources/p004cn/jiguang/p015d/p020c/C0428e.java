package p004cn.jiguang.p015d.p020c;

/* renamed from: cn.jiguang.d.c.e */
public final class C0428e {

    /* renamed from: a */
    private byte[] f343a;

    /* renamed from: b */
    private int f344b;

    /* renamed from: c */
    private int f345c;

    public C0428e() {
        this(32);
    }

    private C0428e(int i) {
        this.f343a = new byte[32];
        this.f344b = 0;
        this.f345c = -1;
    }

    /* renamed from: a */
    private static void m529a(long j, int i) {
        long j2 = 1 << i;
        if (j < 0 || j > j2) {
            throw new IllegalArgumentException(j + " out of range for " + i + " bit value");
        }
    }

    /* renamed from: d */
    private void m530d(int i) {
        if (this.f343a.length - this.f344b < i) {
            int length = this.f343a.length * 2;
            if (length < this.f344b + i) {
                length = this.f344b + i;
            }
            byte[] bArr = new byte[length];
            System.arraycopy(this.f343a, 0, bArr, 0, this.f344b);
            this.f343a = bArr;
        }
    }

    /* renamed from: a */
    public final int mo6495a() {
        return this.f344b;
    }

    /* renamed from: a */
    public final void mo6496a(int i) {
        if (i > this.f344b) {
            throw new IllegalArgumentException("cannot jump past end of data");
        }
        this.f344b = i;
    }

    /* renamed from: a */
    public final void mo6497a(int i, int i2) {
        m529a((long) i, 16);
        if (i2 > this.f344b - 2) {
            throw new IllegalArgumentException("cannot write past end of data");
        }
        int i3 = i2 + 1;
        this.f343a[i2] = (byte) ((i >>> 8) & 255);
        this.f343a[i3] = (byte) (i & 255);
    }

    /* renamed from: a */
    public final void mo6498a(long j) {
        m529a(j, 32);
        m530d(4);
        byte[] bArr = this.f343a;
        int i = this.f344b;
        this.f344b = i + 1;
        bArr[i] = (byte) ((int) ((j >>> 24) & 255));
        byte[] bArr2 = this.f343a;
        int i2 = this.f344b;
        this.f344b = i2 + 1;
        bArr2[i2] = (byte) ((int) ((j >>> 16) & 255));
        byte[] bArr3 = this.f343a;
        int i3 = this.f344b;
        this.f344b = i3 + 1;
        bArr3[i3] = (byte) ((int) ((j >>> 8) & 255));
        byte[] bArr4 = this.f343a;
        int i4 = this.f344b;
        this.f344b = i4 + 1;
        bArr4[i4] = (byte) ((int) (j & 255));
    }

    /* renamed from: a */
    public final void mo6499a(byte[] bArr) {
        mo6500a(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public final void mo6500a(byte[] bArr, int i, int i2) {
        m530d(i2);
        System.arraycopy(bArr, i, this.f343a, this.f344b, i2);
        this.f344b += i2;
    }

    /* renamed from: b */
    public final void mo6501b(int i) {
        m529a(0, 8);
        m530d(1);
        byte[] bArr = this.f343a;
        int i2 = this.f344b;
        this.f344b = i2 + 1;
        bArr[i2] = 0;
    }

    /* renamed from: b */
    public final byte[] mo6502b() {
        byte[] bArr = new byte[this.f344b];
        System.arraycopy(this.f343a, 0, bArr, 0, this.f344b);
        return bArr;
    }

    /* renamed from: c */
    public final void mo6503c(int i) {
        m529a((long) i, 16);
        m530d(2);
        byte[] bArr = this.f343a;
        int i2 = this.f344b;
        this.f344b = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        byte[] bArr2 = this.f343a;
        int i3 = this.f344b;
        this.f344b = i3 + 1;
        bArr2[i3] = (byte) (i & 255);
    }
}
