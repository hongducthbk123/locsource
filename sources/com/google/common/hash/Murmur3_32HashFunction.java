package com.google.common.hash;

import com.google.common.primitives.UnsignedBytes;
import java.io.Serializable;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

final class Murmur3_32HashFunction extends AbstractStreamingHashFunction implements Serializable {

    /* renamed from: C1 */
    private static final int f1015C1 = -862048943;

    /* renamed from: C2 */
    private static final int f1016C2 = 461845907;
    private static final long serialVersionUID = 0;
    private final int seed;

    private static final class Murmur3_32Hasher extends AbstractStreamingHasher {
        private static final int CHUNK_SIZE = 4;

        /* renamed from: h1 */
        private int f1017h1;
        private int length = 0;

        Murmur3_32Hasher(int seed) {
            super(4);
            this.f1017h1 = seed;
        }

        /* access modifiers changed from: protected */
        public void process(ByteBuffer bb) {
            this.f1017h1 = Murmur3_32HashFunction.mixH1(this.f1017h1, Murmur3_32HashFunction.mixK1(bb.getInt()));
            this.length += 4;
        }

        /* access modifiers changed from: protected */
        public void processRemaining(ByteBuffer bb) {
            this.length += bb.remaining();
            int k1 = 0;
            int i = 0;
            while (bb.hasRemaining()) {
                k1 ^= UnsignedBytes.toInt(bb.get()) << i;
                i += 8;
            }
            this.f1017h1 ^= Murmur3_32HashFunction.mixK1(k1);
        }

        public HashCode makeHash() {
            return Murmur3_32HashFunction.fmix(this.f1017h1, this.length);
        }
    }

    Murmur3_32HashFunction(int seed2) {
        this.seed = seed2;
    }

    public int bits() {
        return 32;
    }

    public Hasher newHasher() {
        return new Murmur3_32Hasher(this.seed);
    }

    public String toString() {
        return "Hashing.murmur3_32(" + this.seed + ")";
    }

    public boolean equals(@Nullable Object object) {
        if (!(object instanceof Murmur3_32HashFunction)) {
            return false;
        }
        if (this.seed == ((Murmur3_32HashFunction) object).seed) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return getClass().hashCode() ^ this.seed;
    }

    public HashCode hashInt(int input) {
        return fmix(mixH1(this.seed, mixK1(input)), 4);
    }

    public HashCode hashLong(long input) {
        int high = (int) (input >>> 32);
        return fmix(mixH1(mixH1(this.seed, mixK1((int) input)), mixK1(high)), 8);
    }

    public HashCode hashUnencodedChars(CharSequence input) {
        int h1 = this.seed;
        for (int i = 1; i < input.length(); i += 2) {
            h1 = mixH1(h1, mixK1(input.charAt(i - 1) | (input.charAt(i) << 16)));
        }
        if ((input.length() & 1) == 1) {
            h1 ^= mixK1(input.charAt(input.length() - 1));
        }
        return fmix(h1, input.length() * 2);
    }

    /* access modifiers changed from: private */
    public static int mixK1(int k1) {
        return Integer.rotateLeft(k1 * f1015C1, 15) * f1016C2;
    }

    /* access modifiers changed from: private */
    public static int mixH1(int h1, int k1) {
        return (Integer.rotateLeft(h1 ^ k1, 13) * 5) - 430675100;
    }

    /* access modifiers changed from: private */
    public static HashCode fmix(int h1, int length) {
        int h12 = h1 ^ length;
        int h13 = (h12 ^ (h12 >>> 16)) * -2048144789;
        int h14 = (h13 ^ (h13 >>> 13)) * -1028477387;
        return HashCode.fromInt(h14 ^ (h14 >>> 16));
    }
}
