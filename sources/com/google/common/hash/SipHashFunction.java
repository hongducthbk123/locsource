package com.google.common.hash;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

final class SipHashFunction extends AbstractStreamingHashFunction implements Serializable {
    private static final long serialVersionUID = 0;

    /* renamed from: c */
    private final int f1018c;

    /* renamed from: d */
    private final int f1019d;

    /* renamed from: k0 */
    private final long f1020k0;

    /* renamed from: k1 */
    private final long f1021k1;

    private static final class SipHasher extends AbstractStreamingHasher {
        private static final int CHUNK_SIZE = 8;

        /* renamed from: b */
        private long f1022b = 0;

        /* renamed from: c */
        private final int f1023c;

        /* renamed from: d */
        private final int f1024d;
        private long finalM = 0;

        /* renamed from: v0 */
        private long f1025v0 = 8317987319222330741L;

        /* renamed from: v1 */
        private long f1026v1 = 7237128888997146477L;

        /* renamed from: v2 */
        private long f1027v2 = 7816392313619706465L;

        /* renamed from: v3 */
        private long f1028v3 = 8387220255154660723L;

        SipHasher(int c, int d, long k0, long k1) {
            super(8);
            this.f1023c = c;
            this.f1024d = d;
            this.f1025v0 ^= k0;
            this.f1026v1 ^= k1;
            this.f1027v2 ^= k0;
            this.f1028v3 ^= k1;
        }

        /* access modifiers changed from: protected */
        public void process(ByteBuffer buffer) {
            this.f1022b += 8;
            processM(buffer.getLong());
        }

        /* access modifiers changed from: protected */
        public void processRemaining(ByteBuffer buffer) {
            this.f1022b += (long) buffer.remaining();
            int i = 0;
            while (buffer.hasRemaining()) {
                this.finalM ^= (((long) buffer.get()) & 255) << i;
                i += 8;
            }
        }

        public HashCode makeHash() {
            this.finalM ^= this.f1022b << 56;
            processM(this.finalM);
            this.f1027v2 ^= 255;
            sipRound(this.f1024d);
            return HashCode.fromLong(((this.f1025v0 ^ this.f1026v1) ^ this.f1027v2) ^ this.f1028v3);
        }

        private void processM(long m) {
            this.f1028v3 ^= m;
            sipRound(this.f1023c);
            this.f1025v0 ^= m;
        }

        private void sipRound(int iterations) {
            for (int i = 0; i < iterations; i++) {
                this.f1025v0 += this.f1026v1;
                this.f1027v2 += this.f1028v3;
                this.f1026v1 = Long.rotateLeft(this.f1026v1, 13);
                this.f1028v3 = Long.rotateLeft(this.f1028v3, 16);
                this.f1026v1 ^= this.f1025v0;
                this.f1028v3 ^= this.f1027v2;
                this.f1025v0 = Long.rotateLeft(this.f1025v0, 32);
                this.f1027v2 += this.f1026v1;
                this.f1025v0 += this.f1028v3;
                this.f1026v1 = Long.rotateLeft(this.f1026v1, 17);
                this.f1028v3 = Long.rotateLeft(this.f1028v3, 21);
                this.f1026v1 ^= this.f1027v2;
                this.f1028v3 ^= this.f1025v0;
                this.f1027v2 = Long.rotateLeft(this.f1027v2, 32);
            }
        }
    }

    SipHashFunction(int c, int d, long k0, long k1) {
        boolean z;
        Preconditions.checkArgument(c > 0, "The number of SipRound iterations (c=%s) during Compression must be positive.", Integer.valueOf(c));
        if (d > 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "The number of SipRound iterations (d=%s) during Finalization must be positive.", Integer.valueOf(d));
        this.f1018c = c;
        this.f1019d = d;
        this.f1020k0 = k0;
        this.f1021k1 = k1;
    }

    public int bits() {
        return 64;
    }

    public Hasher newHasher() {
        return new SipHasher(this.f1018c, this.f1019d, this.f1020k0, this.f1021k1);
    }

    public String toString() {
        return "Hashing.sipHash" + this.f1018c + "" + this.f1019d + "(" + this.f1020k0 + ", " + this.f1021k1 + ")";
    }

    public boolean equals(@Nullable Object object) {
        if (!(object instanceof SipHashFunction)) {
            return false;
        }
        SipHashFunction other = (SipHashFunction) object;
        if (this.f1018c == other.f1018c && this.f1019d == other.f1019d && this.f1020k0 == other.f1020k0 && this.f1021k1 == other.f1021k1) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (int) ((((long) ((getClass().hashCode() ^ this.f1018c) ^ this.f1019d)) ^ this.f1020k0) ^ this.f1021k1);
    }
}
