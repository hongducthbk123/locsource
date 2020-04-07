package com.google.android.gms.internal;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzfhg extends zzfgr {
    private static final Logger zzb = Logger.getLogger(zzfhg.class.getName());
    /* access modifiers changed from: private */
    public static final boolean zzc = zzfkq.zza();
    zzfhi zza = this;

    static abstract class zza extends zzfhg {
        final byte[] zzb;
        final int zzc;
        int zzd;
        int zze;

        zza(int i) {
            super();
            if (i < 0) {
                throw new IllegalArgumentException("bufferSize must be >= 0");
            }
            this.zzb = new byte[Math.max(i, 20)];
            this.zzc = this.zzb.length;
        }

        public final int zzb() {
            throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array or ByteBuffer.");
        }

        /* access modifiers changed from: 0000 */
        public final void zzb(byte b) {
            byte[] bArr = this.zzb;
            int i = this.zzd;
            this.zzd = i + 1;
            bArr[i] = b;
            this.zze++;
        }

        /* access modifiers changed from: 0000 */
        public final void zzi(int i, int i2) {
            zzo((i << 3) | i2);
        }

        /* access modifiers changed from: 0000 */
        public final void zzi(long j) {
            if (zzfhg.zzc) {
                long j2 = (long) this.zzd;
                while ((j & -128) != 0) {
                    byte[] bArr = this.zzb;
                    int i = this.zzd;
                    this.zzd = i + 1;
                    zzfkq.zza(bArr, (long) i, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
                byte[] bArr2 = this.zzb;
                int i2 = this.zzd;
                this.zzd = i2 + 1;
                zzfkq.zza(bArr2, (long) i2, (byte) ((int) j));
                this.zze = ((int) (((long) this.zzd) - j2)) + this.zze;
                return;
            }
            while ((j & -128) != 0) {
                byte[] bArr3 = this.zzb;
                int i3 = this.zzd;
                this.zzd = i3 + 1;
                bArr3[i3] = (byte) ((((int) j) & 127) | 128);
                this.zze++;
                j >>>= 7;
            }
            byte[] bArr4 = this.zzb;
            int i4 = this.zzd;
            this.zzd = i4 + 1;
            bArr4[i4] = (byte) ((int) j);
            this.zze++;
        }

        /* access modifiers changed from: 0000 */
        public final void zzj(long j) {
            byte[] bArr = this.zzb;
            int i = this.zzd;
            this.zzd = i + 1;
            bArr[i] = (byte) ((int) (j & 255));
            byte[] bArr2 = this.zzb;
            int i2 = this.zzd;
            this.zzd = i2 + 1;
            bArr2[i2] = (byte) ((int) ((j >> 8) & 255));
            byte[] bArr3 = this.zzb;
            int i3 = this.zzd;
            this.zzd = i3 + 1;
            bArr3[i3] = (byte) ((int) ((j >> 16) & 255));
            byte[] bArr4 = this.zzb;
            int i4 = this.zzd;
            this.zzd = i4 + 1;
            bArr4[i4] = (byte) ((int) ((j >> 24) & 255));
            byte[] bArr5 = this.zzb;
            int i5 = this.zzd;
            this.zzd = i5 + 1;
            bArr5[i5] = (byte) ((int) (j >> 32));
            byte[] bArr6 = this.zzb;
            int i6 = this.zzd;
            this.zzd = i6 + 1;
            bArr6[i6] = (byte) ((int) (j >> 40));
            byte[] bArr7 = this.zzb;
            int i7 = this.zzd;
            this.zzd = i7 + 1;
            bArr7[i7] = (byte) ((int) (j >> 48));
            byte[] bArr8 = this.zzb;
            int i8 = this.zzd;
            this.zzd = i8 + 1;
            bArr8[i8] = (byte) ((int) (j >> 56));
            this.zze += 8;
        }

        /* access modifiers changed from: 0000 */
        public final void zzo(int i) {
            if (zzfhg.zzc) {
                long j = (long) this.zzd;
                while ((i & -128) != 0) {
                    byte[] bArr = this.zzb;
                    int i2 = this.zzd;
                    this.zzd = i2 + 1;
                    zzfkq.zza(bArr, (long) i2, (byte) ((i & 127) | 128));
                    i >>>= 7;
                }
                byte[] bArr2 = this.zzb;
                int i3 = this.zzd;
                this.zzd = i3 + 1;
                zzfkq.zza(bArr2, (long) i3, (byte) i);
                this.zze = ((int) (((long) this.zzd) - j)) + this.zze;
                return;
            }
            while ((i & -128) != 0) {
                byte[] bArr3 = this.zzb;
                int i4 = this.zzd;
                this.zzd = i4 + 1;
                bArr3[i4] = (byte) ((i & 127) | 128);
                this.zze++;
                i >>>= 7;
            }
            byte[] bArr4 = this.zzb;
            int i5 = this.zzd;
            this.zzd = i5 + 1;
            bArr4[i5] = (byte) i;
            this.zze++;
        }

        /* JADX WARNING: type inference failed for: r0v3, types: [byte[]] */
        /* JADX WARNING: type inference failed for: r2v9, types: [int, byte] */
        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Incorrect type for immutable var: ssa=byte[], code=null, for r0v3, types: [byte[]] */
        /* JADX WARNING: Incorrect type for immutable var: ssa=int, code=null, for r2v9, types: [int, byte] */
        /* JADX WARNING: Unknown variable types count: 2 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void zzp(int r4) {
            /*
                r3 = this;
                byte[] r0 = r3.zzb
                int r1 = r3.zzd
                int r2 = r1 + 1
                r3.zzd = r2
                byte r2 = (byte) r4
                r0[r1] = r2
                byte[] r0 = r3.zzb
                int r1 = r3.zzd
                int r2 = r1 + 1
                r3.zzd = r2
                int r2 = r4 >> 8
                byte r2 = (byte) r2
                r0[r1] = r2
                byte[] r0 = r3.zzb
                int r1 = r3.zzd
                int r2 = r1 + 1
                r3.zzd = r2
                int r2 = r4 >> 16
                byte r2 = (byte) r2
                r0[r1] = r2
                byte[] r0 = r3.zzb
                int r1 = r3.zzd
                int r2 = r1 + 1
                r3.zzd = r2
                int r2 = r4 >> 24
                r0[r1] = r2
                int r0 = r3.zze
                int r0 = r0 + 4
                r3.zze = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfhg.zza.zzp(int):void");
        }
    }

    static class zzb extends zzfhg {
        private final byte[] zzb;
        private final int zzc;
        private final int zzd;
        private int zze;

        zzb(byte[] bArr, int i, int i2) {
            super();
            if (bArr == null) {
                throw new NullPointerException("buffer");
            } else if ((i | i2 | (bArr.length - (i + i2))) < 0) {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)}));
            } else {
                this.zzb = bArr;
                this.zzc = i;
                this.zze = i;
                this.zzd = i + i2;
            }
        }

        public void zza() {
        }

        public final void zza(byte b) throws IOException {
            try {
                byte[] bArr = this.zzb;
                int i = this.zze;
                this.zze = i + 1;
                bArr[i] = b;
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zze), Integer.valueOf(this.zzd), Integer.valueOf(1)}), e);
            }
        }

        public final void zza(int i, int i2) throws IOException {
            zzc((i << 3) | i2);
        }

        public final void zza(int i, long j) throws IOException {
            zza(i, 0);
            zza(j);
        }

        public final void zza(int i, zzfgs zzfgs) throws IOException {
            zza(i, 2);
            zza(zzfgs);
        }

        public final void zza(int i, zzfjc zzfjc) throws IOException {
            zza(i, 2);
            zza(zzfjc);
        }

        public final void zza(int i, String str) throws IOException {
            zza(i, 2);
            zza(str);
        }

        public final void zza(int i, boolean z) throws IOException {
            int i2 = 0;
            zza(i, 0);
            if (z) {
                i2 = 1;
            }
            zza((byte) i2);
        }

        public final void zza(long j) throws IOException {
            if (!zzfhg.zzc || zzb() < 10) {
                while ((j & -128) != 0) {
                    try {
                        byte[] bArr = this.zzb;
                        int i = this.zze;
                        this.zze = i + 1;
                        bArr[i] = (byte) ((((int) j) & 127) | 128);
                        j >>>= 7;
                    } catch (IndexOutOfBoundsException e) {
                        throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zze), Integer.valueOf(this.zzd), Integer.valueOf(1)}), e);
                    }
                }
                byte[] bArr2 = this.zzb;
                int i2 = this.zze;
                this.zze = i2 + 1;
                bArr2[i2] = (byte) ((int) j);
                return;
            }
            while ((j & -128) != 0) {
                byte[] bArr3 = this.zzb;
                int i3 = this.zze;
                this.zze = i3 + 1;
                zzfkq.zza(bArr3, (long) i3, (byte) ((((int) j) & 127) | 128));
                j >>>= 7;
            }
            byte[] bArr4 = this.zzb;
            int i4 = this.zze;
            this.zze = i4 + 1;
            zzfkq.zza(bArr4, (long) i4, (byte) ((int) j));
        }

        public final void zza(zzfgs zzfgs) throws IOException {
            zzc(zzfgs.zza());
            zzfgs.zza((zzfgr) this);
        }

        public final void zza(zzfjc zzfjc) throws IOException {
            zzc(zzfjc.zza());
            zzfjc.zza((zzfhg) this);
        }

        public final void zza(String str) throws IOException {
            int i = this.zze;
            try {
                int zzh = zzh(str.length() * 3);
                int zzh2 = zzh(str.length());
                if (zzh2 == zzh) {
                    this.zze = i + zzh2;
                    int zza = zzfks.zza(str, this.zzb, this.zze, zzb());
                    this.zze = i;
                    zzc((zza - i) - zzh2);
                    this.zze = zza;
                    return;
                }
                zzc(zzfks.zza((CharSequence) str));
                this.zze = zzfks.zza(str, this.zzb, this.zze, zzb());
            } catch (zzfkv e) {
                this.zze = i;
                zza(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new zzc(e2);
            }
        }

        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            zzc(bArr, i, i2);
        }

        public final int zzb() {
            return this.zzd - this.zze;
        }

        public final void zzb(int i) throws IOException {
            if (i >= 0) {
                zzc(i);
            } else {
                zza((long) i);
            }
        }

        public final void zzb(int i, int i2) throws IOException {
            zza(i, 0);
            zzb(i2);
        }

        public final void zzb(int i, long j) throws IOException {
            zza(i, 1);
            zzc(j);
        }

        public final void zzb(int i, zzfgs zzfgs) throws IOException {
            zza(1, 3);
            zzc(2, i);
            zza(3, zzfgs);
            zza(1, 4);
        }

        public final void zzb(int i, zzfjc zzfjc) throws IOException {
            zza(1, 3);
            zzc(2, i);
            zza(3, zzfjc);
            zza(1, 4);
        }

        public final void zzc(int i) throws IOException {
            if (!zzfhg.zzc || zzb() < 10) {
                while ((i & -128) != 0) {
                    try {
                        byte[] bArr = this.zzb;
                        int i2 = this.zze;
                        this.zze = i2 + 1;
                        bArr[i2] = (byte) ((i & 127) | 128);
                        i >>>= 7;
                    } catch (IndexOutOfBoundsException e) {
                        throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zze), Integer.valueOf(this.zzd), Integer.valueOf(1)}), e);
                    }
                }
                byte[] bArr2 = this.zzb;
                int i3 = this.zze;
                this.zze = i3 + 1;
                bArr2[i3] = (byte) i;
                return;
            }
            while ((i & -128) != 0) {
                byte[] bArr3 = this.zzb;
                int i4 = this.zze;
                this.zze = i4 + 1;
                zzfkq.zza(bArr3, (long) i4, (byte) ((i & 127) | 128));
                i >>>= 7;
            }
            byte[] bArr4 = this.zzb;
            int i5 = this.zze;
            this.zze = i5 + 1;
            zzfkq.zza(bArr4, (long) i5, (byte) i);
        }

        public final void zzc(int i, int i2) throws IOException {
            zza(i, 0);
            zzc(i2);
        }

        public final void zzc(long j) throws IOException {
            try {
                byte[] bArr = this.zzb;
                int i = this.zze;
                this.zze = i + 1;
                bArr[i] = (byte) ((int) j);
                byte[] bArr2 = this.zzb;
                int i2 = this.zze;
                this.zze = i2 + 1;
                bArr2[i2] = (byte) ((int) (j >> 8));
                byte[] bArr3 = this.zzb;
                int i3 = this.zze;
                this.zze = i3 + 1;
                bArr3[i3] = (byte) ((int) (j >> 16));
                byte[] bArr4 = this.zzb;
                int i4 = this.zze;
                this.zze = i4 + 1;
                bArr4[i4] = (byte) ((int) (j >> 24));
                byte[] bArr5 = this.zzb;
                int i5 = this.zze;
                this.zze = i5 + 1;
                bArr5[i5] = (byte) ((int) (j >> 32));
                byte[] bArr6 = this.zzb;
                int i6 = this.zze;
                this.zze = i6 + 1;
                bArr6[i6] = (byte) ((int) (j >> 40));
                byte[] bArr7 = this.zzb;
                int i7 = this.zze;
                this.zze = i7 + 1;
                bArr7[i7] = (byte) ((int) (j >> 48));
                byte[] bArr8 = this.zzb;
                int i8 = this.zze;
                this.zze = i8 + 1;
                bArr8[i8] = (byte) ((int) (j >> 56));
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zze), Integer.valueOf(this.zzd), Integer.valueOf(1)}), e);
            }
        }

        public final void zzc(byte[] bArr, int i, int i2) throws IOException {
            try {
                System.arraycopy(bArr, i, this.zzb, this.zze, i2);
                this.zze += i2;
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zze), Integer.valueOf(this.zzd), Integer.valueOf(i2)}), e);
            }
        }

        public final void zzd(int i, int i2) throws IOException {
            zza(i, 5);
            zze(i2);
        }

        public final void zzd(byte[] bArr, int i, int i2) throws IOException {
            zzc(i2);
            zzc(bArr, 0, i2);
        }

        /* JADX WARNING: type inference failed for: r0v4, types: [byte[]] */
        /* JADX WARNING: type inference failed for: r2v11, types: [int, byte] */
        /* JADX WARNING: Incorrect type for immutable var: ssa=byte[], code=null, for r0v4, types: [byte[]] */
        /* JADX WARNING: Incorrect type for immutable var: ssa=int, code=null, for r2v11, types: [int, byte] */
        /* JADX WARNING: Unknown variable types count: 2 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void zze(int r8) throws java.io.IOException {
            /*
                r7 = this;
                r6 = 1
                byte[] r0 = r7.zzb     // Catch:{ IndexOutOfBoundsException -> 0x0033 }
                int r1 = r7.zze     // Catch:{ IndexOutOfBoundsException -> 0x0033 }
                int r2 = r1 + 1
                r7.zze = r2     // Catch:{ IndexOutOfBoundsException -> 0x0033 }
                byte r2 = (byte) r8     // Catch:{ IndexOutOfBoundsException -> 0x0033 }
                r0[r1] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0033 }
                byte[] r0 = r7.zzb     // Catch:{ IndexOutOfBoundsException -> 0x0033 }
                int r1 = r7.zze     // Catch:{ IndexOutOfBoundsException -> 0x0033 }
                int r2 = r1 + 1
                r7.zze = r2     // Catch:{ IndexOutOfBoundsException -> 0x0033 }
                int r2 = r8 >> 8
                byte r2 = (byte) r2     // Catch:{ IndexOutOfBoundsException -> 0x0033 }
                r0[r1] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0033 }
                byte[] r0 = r7.zzb     // Catch:{ IndexOutOfBoundsException -> 0x0033 }
                int r1 = r7.zze     // Catch:{ IndexOutOfBoundsException -> 0x0033 }
                int r2 = r1 + 1
                r7.zze = r2     // Catch:{ IndexOutOfBoundsException -> 0x0033 }
                int r2 = r8 >> 16
                byte r2 = (byte) r2     // Catch:{ IndexOutOfBoundsException -> 0x0033 }
                r0[r1] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0033 }
                byte[] r0 = r7.zzb     // Catch:{ IndexOutOfBoundsException -> 0x0033 }
                int r1 = r7.zze     // Catch:{ IndexOutOfBoundsException -> 0x0033 }
                int r2 = r1 + 1
                r7.zze = r2     // Catch:{ IndexOutOfBoundsException -> 0x0033 }
                int r2 = r8 >> 24
                r0[r1] = r2     // Catch:{ IndexOutOfBoundsException -> 0x0033 }
                return
            L_0x0033:
                r0 = move-exception
                com.google.android.gms.internal.zzfhg$zzc r1 = new com.google.android.gms.internal.zzfhg$zzc
                java.lang.String r2 = "Pos: %d, limit: %d, len: %d"
                r3 = 3
                java.lang.Object[] r3 = new java.lang.Object[r3]
                r4 = 0
                int r5 = r7.zze
                java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                r3[r4] = r5
                int r4 = r7.zzd
                java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
                r3[r6] = r4
                r4 = 2
                java.lang.Integer r5 = java.lang.Integer.valueOf(r6)
                r3[r4] = r5
                java.lang.String r2 = java.lang.String.format(r2, r3)
                r1.<init>(r2, r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfhg.zzb.zze(int):void");
        }
    }

    public static class zzc extends IOException {
        zzc() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        zzc(String str, Throwable th) {
            String valueOf = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
            String valueOf2 = String.valueOf(str);
            super(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), th);
        }

        zzc(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }
    }

    static final class zzd extends zza {
        private final OutputStream zzf;

        zzd(OutputStream outputStream, int i) {
            super(i);
            if (outputStream == null) {
                throw new NullPointerException("out");
            }
            this.zzf = outputStream;
        }

        private final void zze() throws IOException {
            this.zzf.write(this.zzb, 0, this.zzd);
            this.zzd = 0;
        }

        private final void zzq(int i) throws IOException {
            if (this.zzc - this.zzd < i) {
                zze();
            }
        }

        public final void zza() throws IOException {
            if (this.zzd > 0) {
                zze();
            }
        }

        public final void zza(byte b) throws IOException {
            if (this.zzd == this.zzc) {
                zze();
            }
            zzb(b);
        }

        public final void zza(int i, int i2) throws IOException {
            zzc((i << 3) | i2);
        }

        public final void zza(int i, long j) throws IOException {
            zzq(20);
            zzi(i, 0);
            zzi(j);
        }

        public final void zza(int i, zzfgs zzfgs) throws IOException {
            zza(i, 2);
            zza(zzfgs);
        }

        public final void zza(int i, zzfjc zzfjc) throws IOException {
            zza(i, 2);
            zza(zzfjc);
        }

        public final void zza(int i, String str) throws IOException {
            zza(i, 2);
            zza(str);
        }

        public final void zza(int i, boolean z) throws IOException {
            int i2 = 0;
            zzq(11);
            zzi(i, 0);
            if (z) {
                i2 = 1;
            }
            zzb((byte) i2);
        }

        public final void zza(long j) throws IOException {
            zzq(10);
            zzi(j);
        }

        public final void zza(zzfgs zzfgs) throws IOException {
            zzc(zzfgs.zza());
            zzfgs.zza((zzfgr) this);
        }

        public final void zza(zzfjc zzfjc) throws IOException {
            zzc(zzfjc.zza());
            zzfjc.zza((zzfhg) this);
        }

        /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void zza(java.lang.String r7) throws java.io.IOException {
            /*
                r6 = this;
                int r0 = r7.length()     // Catch:{ zzfkv -> 0x0064 }
                int r0 = r0 * 3
                int r1 = zzh(r0)     // Catch:{ zzfkv -> 0x0064 }
                int r2 = r1 + r0
                int r3 = r6.zzc     // Catch:{ zzfkv -> 0x0064 }
                if (r2 <= r3) goto L_0x001f
                byte[] r1 = new byte[r0]     // Catch:{ zzfkv -> 0x0064 }
                r2 = 0
                int r0 = com.google.android.gms.internal.zzfks.zza(r7, r1, r2, r0)     // Catch:{ zzfkv -> 0x0064 }
                r6.zzc(r0)     // Catch:{ zzfkv -> 0x0064 }
                r2 = 0
                r6.zza(r1, r2, r0)     // Catch:{ zzfkv -> 0x0064 }
            L_0x001e:
                return
            L_0x001f:
                int r0 = r0 + r1
                int r2 = r6.zzc     // Catch:{ zzfkv -> 0x0064 }
                int r3 = r6.zzd     // Catch:{ zzfkv -> 0x0064 }
                int r2 = r2 - r3
                if (r0 <= r2) goto L_0x002a
                r6.zze()     // Catch:{ zzfkv -> 0x0064 }
            L_0x002a:
                int r0 = r7.length()     // Catch:{ zzfkv -> 0x0064 }
                int r0 = zzh(r0)     // Catch:{ zzfkv -> 0x0064 }
                int r2 = r6.zzd     // Catch:{ zzfkv -> 0x0064 }
                if (r0 != r1) goto L_0x0069
                int r1 = r2 + r0
                r6.zzd = r1     // Catch:{ zzfkv -> 0x0058, ArrayIndexOutOfBoundsException -> 0x007b }
                byte[] r1 = r6.zzb     // Catch:{ zzfkv -> 0x0058, ArrayIndexOutOfBoundsException -> 0x007b }
                int r3 = r6.zzd     // Catch:{ zzfkv -> 0x0058, ArrayIndexOutOfBoundsException -> 0x007b }
                int r4 = r6.zzc     // Catch:{ zzfkv -> 0x0058, ArrayIndexOutOfBoundsException -> 0x007b }
                int r5 = r6.zzd     // Catch:{ zzfkv -> 0x0058, ArrayIndexOutOfBoundsException -> 0x007b }
                int r4 = r4 - r5
                int r1 = com.google.android.gms.internal.zzfks.zza(r7, r1, r3, r4)     // Catch:{ zzfkv -> 0x0058, ArrayIndexOutOfBoundsException -> 0x007b }
                r6.zzd = r2     // Catch:{ zzfkv -> 0x0058, ArrayIndexOutOfBoundsException -> 0x007b }
                int r3 = r1 - r2
                int r0 = r3 - r0
                r6.zzo(r0)     // Catch:{ zzfkv -> 0x0058, ArrayIndexOutOfBoundsException -> 0x007b }
                r6.zzd = r1     // Catch:{ zzfkv -> 0x0058, ArrayIndexOutOfBoundsException -> 0x007b }
            L_0x0052:
                int r1 = r6.zze     // Catch:{ zzfkv -> 0x0058, ArrayIndexOutOfBoundsException -> 0x007b }
                int r0 = r0 + r1
                r6.zze = r0     // Catch:{ zzfkv -> 0x0058, ArrayIndexOutOfBoundsException -> 0x007b }
                goto L_0x001e
            L_0x0058:
                r0 = move-exception
                int r1 = r6.zze     // Catch:{ zzfkv -> 0x0064 }
                int r3 = r6.zzd     // Catch:{ zzfkv -> 0x0064 }
                int r3 = r3 - r2
                int r1 = r1 - r3
                r6.zze = r1     // Catch:{ zzfkv -> 0x0064 }
                r6.zzd = r2     // Catch:{ zzfkv -> 0x0064 }
                throw r0     // Catch:{ zzfkv -> 0x0064 }
            L_0x0064:
                r0 = move-exception
                r6.zza(r7, r0)
                goto L_0x001e
            L_0x0069:
                int r0 = com.google.android.gms.internal.zzfks.zza(r7)     // Catch:{ zzfkv -> 0x0058, ArrayIndexOutOfBoundsException -> 0x007b }
                r6.zzo(r0)     // Catch:{ zzfkv -> 0x0058, ArrayIndexOutOfBoundsException -> 0x007b }
                byte[] r1 = r6.zzb     // Catch:{ zzfkv -> 0x0058, ArrayIndexOutOfBoundsException -> 0x007b }
                int r3 = r6.zzd     // Catch:{ zzfkv -> 0x0058, ArrayIndexOutOfBoundsException -> 0x007b }
                int r1 = com.google.android.gms.internal.zzfks.zza(r7, r1, r3, r0)     // Catch:{ zzfkv -> 0x0058, ArrayIndexOutOfBoundsException -> 0x007b }
                r6.zzd = r1     // Catch:{ zzfkv -> 0x0058, ArrayIndexOutOfBoundsException -> 0x007b }
                goto L_0x0052
            L_0x007b:
                r0 = move-exception
                com.google.android.gms.internal.zzfhg$zzc r1 = new com.google.android.gms.internal.zzfhg$zzc     // Catch:{ zzfkv -> 0x0064 }
                r1.<init>(r0)     // Catch:{ zzfkv -> 0x0064 }
                throw r1     // Catch:{ zzfkv -> 0x0064 }
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfhg.zzd.zza(java.lang.String):void");
        }

        public final void zza(byte[] bArr, int i, int i2) throws IOException {
            zzc(bArr, i, i2);
        }

        public final void zzb(int i) throws IOException {
            if (i >= 0) {
                zzc(i);
            } else {
                zza((long) i);
            }
        }

        public final void zzb(int i, int i2) throws IOException {
            zzq(20);
            zzi(i, 0);
            if (i2 >= 0) {
                zzo(i2);
            } else {
                zzi((long) i2);
            }
        }

        public final void zzb(int i, long j) throws IOException {
            zzq(18);
            zzi(i, 1);
            zzj(j);
        }

        public final void zzb(int i, zzfgs zzfgs) throws IOException {
            zza(1, 3);
            zzc(2, i);
            zza(3, zzfgs);
            zza(1, 4);
        }

        public final void zzb(int i, zzfjc zzfjc) throws IOException {
            zza(1, 3);
            zzc(2, i);
            zza(3, zzfjc);
            zza(1, 4);
        }

        public final void zzc(int i) throws IOException {
            zzq(10);
            zzo(i);
        }

        public final void zzc(int i, int i2) throws IOException {
            zzq(20);
            zzi(i, 0);
            zzo(i2);
        }

        public final void zzc(long j) throws IOException {
            zzq(8);
            zzj(j);
        }

        public final void zzc(byte[] bArr, int i, int i2) throws IOException {
            if (this.zzc - this.zzd >= i2) {
                System.arraycopy(bArr, i, this.zzb, this.zzd, i2);
                this.zzd += i2;
            } else {
                int i3 = this.zzc - this.zzd;
                System.arraycopy(bArr, i, this.zzb, this.zzd, i3);
                int i4 = i + i3;
                i2 -= i3;
                this.zzd = this.zzc;
                this.zze = i3 + this.zze;
                zze();
                if (i2 <= this.zzc) {
                    System.arraycopy(bArr, i4, this.zzb, 0, i2);
                    this.zzd = i2;
                } else {
                    this.zzf.write(bArr, i4, i2);
                }
            }
            this.zze += i2;
        }

        public final void zzd(int i, int i2) throws IOException {
            zzq(14);
            zzi(i, 5);
            zzp(i2);
        }

        public final void zzd(byte[] bArr, int i, int i2) throws IOException {
            zzc(i2);
            zzc(bArr, 0, i2);
        }

        public final void zze(int i) throws IOException {
            zzq(4);
            zzp(i);
        }
    }

    private zzfhg() {
    }

    static int zza(int i) {
        if (i > 4096) {
            return 4096;
        }
        return i;
    }

    public static int zza(int i, zzfik zzfik) {
        int zzf = zzf(i);
        int zzb2 = zzfik.zzb();
        return zzf + zzb2 + zzh(zzb2);
    }

    public static int zza(zzfik zzfik) {
        int zzb2 = zzfik.zzb();
        return zzb2 + zzh(zzb2);
    }

    public static zzfhg zza(OutputStream outputStream, int i) {
        return new zzd(outputStream, i);
    }

    public static zzfhg zza(byte[] bArr) {
        return zzb(bArr, 0, bArr.length);
    }

    public static int zzb(double d) {
        return 8;
    }

    public static int zzb(float f) {
        return 4;
    }

    public static int zzb(int i, double d) {
        return zzf(i) + 8;
    }

    public static int zzb(int i, zzfik zzfik) {
        return (zzf(1) << 1) + zzf(2, i) + zza(3, zzfik);
    }

    public static int zzb(int i, String str) {
        return zzf(i) + zzb(str);
    }

    public static int zzb(int i, boolean z) {
        return zzf(i) + 1;
    }

    public static int zzb(zzfgs zzfgs) {
        int zza2 = zzfgs.zza();
        return zza2 + zzh(zza2);
    }

    public static int zzb(zzfjc zzfjc) {
        int zza2 = zzfjc.zza();
        return zza2 + zzh(zza2);
    }

    public static int zzb(String str) {
        int length;
        try {
            length = zzfks.zza((CharSequence) str);
        } catch (zzfkv e) {
            length = str.getBytes(zzfhz.zza).length;
        }
        return length + zzh(length);
    }

    public static int zzb(boolean z) {
        return 1;
    }

    public static int zzb(byte[] bArr) {
        int length = bArr.length;
        return length + zzh(length);
    }

    public static zzfhg zzb(byte[] bArr, int i, int i2) {
        return new zzb(bArr, i, i2);
    }

    public static int zzc(int i, long j) {
        return zzf(i) + zze(j);
    }

    public static int zzc(int i, zzfgs zzfgs) {
        int zzf = zzf(i);
        int zza2 = zzfgs.zza();
        return zzf + zza2 + zzh(zza2);
    }

    public static int zzc(int i, zzfjc zzfjc) {
        return zzf(i) + zzb(zzfjc);
    }

    @Deprecated
    public static int zzc(zzfjc zzfjc) {
        return zzfjc.zza();
    }

    public static int zzd(int i, long j) {
        return zzf(i) + zze(j);
    }

    public static int zzd(int i, zzfgs zzfgs) {
        return (zzf(1) << 1) + zzf(2, i) + zzc(3, zzfgs);
    }

    public static int zzd(int i, zzfjc zzfjc) {
        return (zzf(1) << 1) + zzf(2, i) + zzc(3, zzfjc);
    }

    public static int zzd(long j) {
        return zze(j);
    }

    public static int zze(int i, int i2) {
        return zzf(i) + zzg(i2);
    }

    public static int zze(int i, long j) {
        return zzf(i) + 8;
    }

    public static int zze(long j) {
        long j2;
        if ((-128 & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        int i = 2;
        if ((-34359738368L & j) != 0) {
            i = 6;
            j2 = j >>> 28;
        } else {
            j2 = j;
        }
        if ((-2097152 & j2) != 0) {
            i += 2;
            j2 >>>= 14;
        }
        return (j2 & -16384) != 0 ? i + 1 : i;
    }

    public static int zzf(int i) {
        return zzh(i << 3);
    }

    public static int zzf(int i, int i2) {
        return zzf(i) + zzh(i2);
    }

    public static int zzf(long j) {
        return zze(zzi(j));
    }

    public static int zzg(int i) {
        if (i >= 0) {
            return zzh(i);
        }
        return 10;
    }

    public static int zzg(int i, int i2) {
        return zzf(i) + 4;
    }

    public static int zzg(long j) {
        return 8;
    }

    public static int zzh(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        return (-268435456 & i) == 0 ? 4 : 5;
    }

    public static int zzh(int i, int i2) {
        return zzf(i) + zzg(i2);
    }

    public static int zzh(long j) {
        return 8;
    }

    public static int zzi(int i) {
        return zzh(zzo(i));
    }

    private static long zzi(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public static int zzj(int i) {
        return 4;
    }

    public static int zzk(int i) {
        return 4;
    }

    public static int zzl(int i) {
        return zzg(i);
    }

    static int zzm(int i) {
        return zzh(i) + i;
    }

    @Deprecated
    public static int zzn(int i) {
        return zzh(i);
    }

    private static int zzo(int i) {
        return (i << 1) ^ (i >> 31);
    }

    public abstract void zza() throws IOException;

    public abstract void zza(byte b) throws IOException;

    public final void zza(double d) throws IOException {
        zzc(Double.doubleToRawLongBits(d));
    }

    public final void zza(float f) throws IOException {
        zze(Float.floatToRawIntBits(f));
    }

    public final void zza(int i, double d) throws IOException {
        zzb(i, Double.doubleToRawLongBits(d));
    }

    public abstract void zza(int i, int i2) throws IOException;

    public abstract void zza(int i, long j) throws IOException;

    public abstract void zza(int i, zzfgs zzfgs) throws IOException;

    public abstract void zza(int i, zzfjc zzfjc) throws IOException;

    public abstract void zza(int i, String str) throws IOException;

    public abstract void zza(int i, boolean z) throws IOException;

    public abstract void zza(long j) throws IOException;

    public abstract void zza(zzfgs zzfgs) throws IOException;

    public abstract void zza(zzfjc zzfjc) throws IOException;

    public abstract void zza(String str) throws IOException;

    /* access modifiers changed from: 0000 */
    public final void zza(String str, zzfkv zzfkv) throws IOException {
        zzb.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzfkv);
        byte[] bytes = str.getBytes(zzfhz.zza);
        try {
            zzc(bytes.length);
            zza(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzc(e);
        } catch (zzc e2) {
            throw e2;
        }
    }

    public final void zza(boolean z) throws IOException {
        zza((byte) (z ? 1 : 0));
    }

    public abstract int zzb();

    public abstract void zzb(int i) throws IOException;

    public abstract void zzb(int i, int i2) throws IOException;

    public abstract void zzb(int i, long j) throws IOException;

    public abstract void zzb(int i, zzfgs zzfgs) throws IOException;

    public abstract void zzb(int i, zzfjc zzfjc) throws IOException;

    public final void zzb(long j) throws IOException {
        zza(zzi(j));
    }

    public final void zzc() {
        if (zzb() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public abstract void zzc(int i) throws IOException;

    public abstract void zzc(int i, int i2) throws IOException;

    public abstract void zzc(long j) throws IOException;

    public abstract void zzc(byte[] bArr, int i, int i2) throws IOException;

    public final void zzd(int i) throws IOException {
        zzc(zzo(i));
    }

    public abstract void zzd(int i, int i2) throws IOException;

    /* access modifiers changed from: 0000 */
    public abstract void zzd(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zze(int i) throws IOException;

    @Deprecated
    public final void zze(int i, zzfjc zzfjc) throws IOException {
        zza(i, 3);
        zzfjc.zza(this);
        zza(i, 4);
    }
}
