package p004cn.jiguang.api.utils;

import java.math.BigInteger;

/* renamed from: cn.jiguang.api.utils.OutputDataUtil */
public class OutputDataUtil {

    /* renamed from: d */
    private static final BigInteger f187d = BigInteger.ONE.shiftLeft(64);

    /* renamed from: a */
    private byte[] f188a;

    /* renamed from: b */
    private int f189b;

    /* renamed from: c */
    private int f190c;

    public OutputDataUtil() {
        this(32);
    }

    public OutputDataUtil(int i) {
        this.f188a = new byte[i];
        this.f189b = 0;
        this.f190c = -1;
    }

    /* renamed from: a */
    private void m212a(int i) {
        if (this.f188a.length - this.f189b < i) {
            int length = this.f188a.length * 2;
            if (length < this.f189b + i) {
                length = this.f189b + i;
            }
            byte[] bArr = new byte[length];
            System.arraycopy(this.f188a, 0, bArr, 0, this.f189b);
            this.f188a = bArr;
        }
    }

    public static int encodeZigZag32(int i) {
        return (i << 1) ^ (i >> 31);
    }

    public static long encodeZigZag64(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public int current() {
        return this.f189b;
    }

    public void jump(int i) {
        if (i > this.f189b) {
            throw new IllegalArgumentException("cannot jump past end of data");
        }
        this.f189b = i;
    }

    public void restore() {
        if (this.f190c < 0) {
            throw new IllegalStateException("no previous state");
        }
        this.f189b = this.f190c;
        this.f190c = -1;
    }

    public void save() {
        this.f190c = this.f189b;
    }

    public byte[] toByteArray() {
        byte[] bArr = new byte[this.f189b];
        System.arraycopy(this.f188a, 0, bArr, 0, this.f189b);
        return bArr;
    }

    public void writeByteArray(byte[] bArr) {
        writeByteArray(bArr, 0, bArr.length);
    }

    public void writeByteArray(byte[] bArr, int i, int i2) {
        m212a(i2);
        System.arraycopy(bArr, i, this.f188a, this.f189b, i2);
        this.f189b += i2;
    }

    public void writeByteArrayincludeLength(byte[] bArr) {
        writeU16(bArr.length);
        writeByteArray(bArr, 0, bArr.length);
    }

    public void writeCountedString(byte[] bArr) {
        if (bArr.length > 255) {
            throw new IllegalArgumentException("Invalid counted string");
        }
        m212a(bArr.length + 1);
        byte[] bArr2 = this.f188a;
        int i = this.f189b;
        this.f189b = i + 1;
        bArr2[i] = (byte) (bArr.length & 255);
        writeByteArray(bArr, 0, bArr.length);
    }

    public void writeRawLittleEndian16(int i) {
        byte[] bArr = this.f188a;
        int i2 = this.f189b;
        this.f189b = i2 + 1;
        bArr[i2] = (byte) (i & 255);
        byte[] bArr2 = this.f188a;
        int i3 = this.f189b;
        this.f189b = i3 + 1;
        bArr2[i3] = (byte) ((i >> 8) & 255);
    }

    public void writeRawLittleEndian32(int i) {
        byte[] bArr = this.f188a;
        int i2 = this.f189b;
        this.f189b = i2 + 1;
        bArr[i2] = (byte) (i & 255);
        byte[] bArr2 = this.f188a;
        int i3 = this.f189b;
        this.f189b = i3 + 1;
        bArr2[i3] = (byte) ((i >> 8) & 255);
        byte[] bArr3 = this.f188a;
        int i4 = this.f189b;
        this.f189b = i4 + 1;
        bArr3[i4] = (byte) ((i >> 16) & 255);
        byte[] bArr4 = this.f188a;
        int i5 = this.f189b;
        this.f189b = i5 + 1;
        bArr4[i5] = (byte) ((i >> 24) & 255);
    }

    public void writeRawLittleEndian64(long j) {
        byte[] bArr = this.f188a;
        int i = this.f189b;
        this.f189b = i + 1;
        bArr[i] = (byte) (((int) j) & 255);
        byte[] bArr2 = this.f188a;
        int i2 = this.f189b;
        this.f189b = i2 + 1;
        bArr2[i2] = (byte) (((int) (j >> 8)) & 255);
        byte[] bArr3 = this.f188a;
        int i3 = this.f189b;
        this.f189b = i3 + 1;
        bArr3[i3] = (byte) (((int) (j >> 16)) & 255);
        byte[] bArr4 = this.f188a;
        int i4 = this.f189b;
        this.f189b = i4 + 1;
        bArr4[i4] = (byte) (((int) (j >> 24)) & 255);
        byte[] bArr5 = this.f188a;
        int i5 = this.f189b;
        this.f189b = i5 + 1;
        bArr5[i5] = (byte) (((int) (j >> 32)) & 255);
        byte[] bArr6 = this.f188a;
        int i6 = this.f189b;
        this.f189b = i6 + 1;
        bArr6[i6] = (byte) (((int) (j >> 40)) & 255);
        byte[] bArr7 = this.f188a;
        int i7 = this.f189b;
        this.f189b = i7 + 1;
        bArr7[i7] = (byte) (((int) (j >> 48)) & 255);
        byte[] bArr8 = this.f188a;
        int i8 = this.f189b;
        this.f189b = i8 + 1;
        bArr8[i8] = (byte) (((int) (j >> 56)) & 255);
    }

    public void writeU16(int i) {
        m212a(2);
        byte[] bArr = this.f188a;
        int i2 = this.f189b;
        this.f189b = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        byte[] bArr2 = this.f188a;
        int i3 = this.f189b;
        this.f189b = i3 + 1;
        bArr2[i3] = (byte) (i & 255);
    }

    public void writeU16At(int i, int i2) {
        if (i2 > this.f189b - 2) {
            throw new IllegalArgumentException("cannot write past end of data");
        }
        int i3 = i2 + 1;
        this.f188a[i2] = (byte) ((i >>> 8) & 255);
        this.f188a[i3] = (byte) (i & 255);
    }

    public void writeU32(long j) {
        m212a(4);
        byte[] bArr = this.f188a;
        int i = this.f189b;
        this.f189b = i + 1;
        bArr[i] = (byte) ((int) ((j >>> 24) & 255));
        byte[] bArr2 = this.f188a;
        int i2 = this.f189b;
        this.f189b = i2 + 1;
        bArr2[i2] = (byte) ((int) ((j >>> 16) & 255));
        byte[] bArr3 = this.f188a;
        int i3 = this.f189b;
        this.f189b = i3 + 1;
        bArr3[i3] = (byte) ((int) ((j >>> 8) & 255));
        byte[] bArr4 = this.f188a;
        int i4 = this.f189b;
        this.f189b = i4 + 1;
        bArr4[i4] = (byte) ((int) (j & 255));
    }

    public void writeU32At(long j, int i) {
        if (i > this.f189b - 4) {
            throw new IllegalArgumentException("cannot write past end of data");
        }
        int i2 = i + 1;
        this.f188a[i] = (byte) ((int) ((j >>> 24) & 255));
        int i3 = i2 + 1;
        this.f188a[i2] = (byte) ((int) ((j >>> 16) & 255));
        int i4 = i3 + 1;
        this.f188a[i3] = (byte) ((int) ((j >>> 8) & 255));
        this.f188a[i4] = (byte) ((int) (j & 255));
    }

    public void writeU64(long j) {
        m212a(8);
        byte[] bArr = this.f188a;
        int i = this.f189b;
        this.f189b = i + 1;
        bArr[i] = (byte) ((int) ((j >>> 56) & 255));
        byte[] bArr2 = this.f188a;
        int i2 = this.f189b;
        this.f189b = i2 + 1;
        bArr2[i2] = (byte) ((int) ((j >>> 48) & 255));
        byte[] bArr3 = this.f188a;
        int i3 = this.f189b;
        this.f189b = i3 + 1;
        bArr3[i3] = (byte) ((int) ((j >>> 40) & 255));
        byte[] bArr4 = this.f188a;
        int i4 = this.f189b;
        this.f189b = i4 + 1;
        bArr4[i4] = (byte) ((int) ((j >>> 32) & 255));
        byte[] bArr5 = this.f188a;
        int i5 = this.f189b;
        this.f189b = i5 + 1;
        bArr5[i5] = (byte) ((int) ((j >>> 24) & 255));
        byte[] bArr6 = this.f188a;
        int i6 = this.f189b;
        this.f189b = i6 + 1;
        bArr6[i6] = (byte) ((int) ((j >>> 16) & 255));
        byte[] bArr7 = this.f188a;
        int i7 = this.f189b;
        this.f189b = i7 + 1;
        bArr7[i7] = (byte) ((int) ((j >>> 8) & 255));
        byte[] bArr8 = this.f188a;
        int i8 = this.f189b;
        this.f189b = i8 + 1;
        bArr8[i8] = (byte) ((int) (j & 255));
    }

    public void writeU64At(long j, int i) {
        int i2 = i + 1;
        this.f188a[i] = (byte) ((int) ((j >>> 56) & 255));
        int i3 = i2 + 1;
        this.f188a[i2] = (byte) ((int) ((j >>> 48) & 255));
        int i4 = i3 + 1;
        this.f188a[i3] = (byte) ((int) ((j >>> 40) & 255));
        int i5 = i4 + 1;
        this.f188a[i4] = (byte) ((int) ((j >>> 32) & 255));
        int i6 = i5 + 1;
        this.f188a[i5] = (byte) ((int) ((j >>> 24) & 255));
        int i7 = i6 + 1;
        this.f188a[i6] = (byte) ((int) ((j >>> 16) & 255));
        int i8 = i7 + 1;
        this.f188a[i7] = (byte) ((int) ((j >>> 8) & 255));
        this.f188a[i8] = (byte) ((int) (j & 255));
    }

    public void writeU8(int i) {
        m212a(1);
        byte[] bArr = this.f188a;
        int i2 = this.f189b;
        this.f189b = i2 + 1;
        bArr[i2] = (byte) (i & 255);
    }

    public void writeU8At(int i, int i2) {
        if (i2 > this.f189b - 1) {
            throw new IllegalArgumentException("cannot write past end of data");
        }
        this.f188a[i2] = (byte) (i & 255);
    }
}
