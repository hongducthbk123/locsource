package com.google.android.gms.internal;

import java.lang.reflect.Field;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

final class zzfkq {
    private static final Logger zza = Logger.getLogger(zzfkq.class.getName());
    private static final Unsafe zzb = zzd();
    private static final Class<?> zzc = zzfgo.zzb();
    private static final boolean zzd = zzc(Long.TYPE);
    private static final boolean zze = zzc(Integer.TYPE);
    private static final zzd zzf;
    private static final boolean zzg = zzf();
    private static final boolean zzh = zze();
    private static final long zzi = ((long) zza(byte[].class));
    private static final long zzj = ((long) zza(boolean[].class));
    private static final long zzk = ((long) zzb(boolean[].class));
    private static final long zzl = ((long) zza(int[].class));
    private static final long zzm = ((long) zzb(int[].class));
    private static final long zzn = ((long) zza(long[].class));
    private static final long zzo = ((long) zzb(long[].class));
    private static final long zzp = ((long) zza(float[].class));
    private static final long zzq = ((long) zzb(float[].class));
    private static final long zzr = ((long) zza(double[].class));
    private static final long zzs = ((long) zzb(double[].class));
    private static final long zzt = ((long) zza(Object[].class));
    private static final long zzu = ((long) zzb(Object[].class));
    private static final long zzv;
    /* access modifiers changed from: private */
    public static final boolean zzw = (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN);

    static final class zza extends zzd {
        zza(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte zza(Object obj, long j) {
            return zzfkq.zzw ? zzfkq.zzd(obj, j) : zzfkq.zze(obj, j);
        }

        public final void zza(Object obj, long j, byte b) {
            if (zzfkq.zzw) {
                zzfkq.zzc(obj, j, b);
            } else {
                zzfkq.zzd(obj, j, b);
            }
        }
    }

    static final class zzb extends zzd {
        zzb(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte zza(Object obj, long j) {
            return zzfkq.zzw ? zzfkq.zzd(obj, j) : zzfkq.zze(obj, j);
        }

        public final void zza(Object obj, long j, byte b) {
            if (zzfkq.zzw) {
                zzfkq.zzc(obj, j, b);
            } else {
                zzfkq.zzd(obj, j, b);
            }
        }
    }

    static final class zzc extends zzd {
        zzc(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte zza(Object obj, long j) {
            return this.zza.getByte(obj, j);
        }

        public final void zza(Object obj, long j, byte b) {
            this.zza.putByte(obj, j, b);
        }
    }

    static abstract class zzd {
        Unsafe zza;

        zzd(Unsafe unsafe) {
            this.zza = unsafe;
        }

        public abstract byte zza(Object obj, long j);

        public abstract void zza(Object obj, long j, byte b);

        public final int zzb(Object obj, long j) {
            return this.zza.getInt(obj, j);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x00be, code lost:
        if (r0 != null) goto L_0x00c0;
     */
    static {
        /*
            r0 = 0
            java.lang.Class<com.google.android.gms.internal.zzfkq> r1 = com.google.android.gms.internal.zzfkq.class
            java.lang.String r1 = r1.getName()
            java.util.logging.Logger r1 = java.util.logging.Logger.getLogger(r1)
            zza = r1
            sun.misc.Unsafe r1 = zzd()
            zzb = r1
            java.lang.Class r1 = com.google.android.gms.internal.zzfgo.zzb()
            zzc = r1
            java.lang.Class r1 = java.lang.Long.TYPE
            boolean r1 = zzc(r1)
            zzd = r1
            java.lang.Class r1 = java.lang.Integer.TYPE
            boolean r1 = zzc(r1)
            zze = r1
            sun.misc.Unsafe r1 = zzb
            if (r1 != 0) goto L_0x00d6
        L_0x002d:
            zzf = r0
            boolean r0 = zzf()
            zzg = r0
            boolean r0 = zze()
            zzh = r0
            java.lang.Class<byte[]> r0 = byte[].class
            int r0 = zza(r0)
            long r0 = (long) r0
            zzi = r0
            java.lang.Class<boolean[]> r0 = boolean[].class
            int r0 = zza(r0)
            long r0 = (long) r0
            zzj = r0
            java.lang.Class<boolean[]> r0 = boolean[].class
            int r0 = zzb(r0)
            long r0 = (long) r0
            zzk = r0
            java.lang.Class<int[]> r0 = int[].class
            int r0 = zza(r0)
            long r0 = (long) r0
            zzl = r0
            java.lang.Class<int[]> r0 = int[].class
            int r0 = zzb(r0)
            long r0 = (long) r0
            zzm = r0
            java.lang.Class<long[]> r0 = long[].class
            int r0 = zza(r0)
            long r0 = (long) r0
            zzn = r0
            java.lang.Class<long[]> r0 = long[].class
            int r0 = zzb(r0)
            long r0 = (long) r0
            zzo = r0
            java.lang.Class<float[]> r0 = float[].class
            int r0 = zza(r0)
            long r0 = (long) r0
            zzp = r0
            java.lang.Class<float[]> r0 = float[].class
            int r0 = zzb(r0)
            long r0 = (long) r0
            zzq = r0
            java.lang.Class<double[]> r0 = double[].class
            int r0 = zza(r0)
            long r0 = (long) r0
            zzr = r0
            java.lang.Class<double[]> r0 = double[].class
            int r0 = zzb(r0)
            long r0 = (long) r0
            zzs = r0
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            int r0 = zza(r0)
            long r0 = (long) r0
            zzt = r0
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            int r0 = zzb(r0)
            long r0 = (long) r0
            zzu = r0
            boolean r0 = com.google.android.gms.internal.zzfgo.zza()
            if (r0 == 0) goto L_0x00ff
            java.lang.Class<java.nio.Buffer> r0 = java.nio.Buffer.class
            java.lang.String r1 = "effectiveDirectAddress"
            java.lang.reflect.Field r0 = zza(r0, r1)
            if (r0 == 0) goto L_0x00ff
        L_0x00c0:
            if (r0 == 0) goto L_0x00c6
            com.google.android.gms.internal.zzfkq$zzd r1 = zzf
            if (r1 != 0) goto L_0x0108
        L_0x00c6:
            r0 = -1
        L_0x00c8:
            zzv = r0
            java.nio.ByteOrder r0 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r1 = java.nio.ByteOrder.BIG_ENDIAN
            if (r0 != r1) goto L_0x0111
            r0 = 1
        L_0x00d3:
            zzw = r0
            return
        L_0x00d6:
            boolean r1 = com.google.android.gms.internal.zzfgo.zza()
            if (r1 == 0) goto L_0x00f6
            boolean r1 = zzd
            if (r1 == 0) goto L_0x00e9
            com.google.android.gms.internal.zzfkq$zzb r0 = new com.google.android.gms.internal.zzfkq$zzb
            sun.misc.Unsafe r1 = zzb
            r0.<init>(r1)
            goto L_0x002d
        L_0x00e9:
            boolean r1 = zze
            if (r1 == 0) goto L_0x002d
            com.google.android.gms.internal.zzfkq$zza r0 = new com.google.android.gms.internal.zzfkq$zza
            sun.misc.Unsafe r1 = zzb
            r0.<init>(r1)
            goto L_0x002d
        L_0x00f6:
            com.google.android.gms.internal.zzfkq$zzc r0 = new com.google.android.gms.internal.zzfkq$zzc
            sun.misc.Unsafe r1 = zzb
            r0.<init>(r1)
            goto L_0x002d
        L_0x00ff:
            java.lang.Class<java.nio.Buffer> r0 = java.nio.Buffer.class
            java.lang.String r1 = "address"
            java.lang.reflect.Field r0 = zza(r0, r1)
            goto L_0x00c0
        L_0x0108:
            com.google.android.gms.internal.zzfkq$zzd r1 = zzf
            sun.misc.Unsafe r1 = r1.zza
            long r0 = r1.objectFieldOffset(r0)
            goto L_0x00c8
        L_0x0111:
            r0 = 0
            goto L_0x00d3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfkq.<clinit>():void");
    }

    private zzfkq() {
    }

    static byte zza(byte[] bArr, long j) {
        return zzf.zza(bArr, zzi + j);
    }

    private static int zza(Class<?> cls) {
        if (zzh) {
            return zzf.zza.arrayBaseOffset(cls);
        }
        return -1;
    }

    static int zza(Object obj, long j) {
        return zzf.zzb(obj, j);
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable th) {
            return null;
        }
    }

    private static void zza(Object obj, long j, int i) {
        zzf.zza.putInt(obj, j, i);
    }

    static void zza(byte[] bArr, long j, byte b) {
        zzf.zza(bArr, zzi + j, b);
    }

    static boolean zza() {
        return zzh;
    }

    private static int zzb(Class<?> cls) {
        if (zzh) {
            return zzf.zza.arrayIndexScale(cls);
        }
        return -1;
    }

    static boolean zzb() {
        return zzg;
    }

    /* access modifiers changed from: private */
    public static void zzc(Object obj, long j, byte b) {
        int i = ((((int) j) ^ -1) & 3) << 3;
        zza(obj, j & -4, (zza(obj, j & -4) & ((255 << i) ^ -1)) | ((b & 255) << i));
    }

    private static boolean zzc(Class<?> cls) {
        if (!zzfgo.zza()) {
            return false;
        }
        try {
            Class<?> cls2 = zzc;
            cls2.getMethod("peekLong", new Class[]{cls, Boolean.TYPE});
            cls2.getMethod("pokeLong", new Class[]{cls, Long.TYPE, Boolean.TYPE});
            cls2.getMethod("pokeInt", new Class[]{cls, Integer.TYPE, Boolean.TYPE});
            cls2.getMethod("peekInt", new Class[]{cls, Boolean.TYPE});
            cls2.getMethod("pokeByte", new Class[]{cls, Byte.TYPE});
            cls2.getMethod("peekByte", new Class[]{cls});
            cls2.getMethod("pokeByteArray", new Class[]{cls, byte[].class, Integer.TYPE, Integer.TYPE});
            cls2.getMethod("peekByteArray", new Class[]{cls, byte[].class, Integer.TYPE, Integer.TYPE});
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static byte zzd(Object obj, long j) {
        return (byte) (zza(obj, -4 & j) >>> ((int) (((-1 ^ j) & 3) << 3)));
    }

    private static Unsafe zzd() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzfkr());
        } catch (Throwable th) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static void zzd(Object obj, long j, byte b) {
        int i = (((int) j) & 3) << 3;
        zza(obj, j & -4, (zza(obj, j & -4) & ((255 << i) ^ -1)) | ((b & 255) << i));
    }

    /* access modifiers changed from: private */
    public static byte zze(Object obj, long j) {
        return (byte) (zza(obj, -4 & j) >>> ((int) ((3 & j) << 3)));
    }

    private static boolean zze() {
        if (zzb == null) {
            return false;
        }
        try {
            Class cls = zzb.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("arrayBaseOffset", new Class[]{Class.class});
            cls.getMethod("arrayIndexScale", new Class[]{Class.class});
            cls.getMethod("getInt", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putInt", new Class[]{Object.class, Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putLong", new Class[]{Object.class, Long.TYPE, Long.TYPE});
            cls.getMethod("getObject", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putObject", new Class[]{Object.class, Long.TYPE, Object.class});
            if (zzfgo.zza()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putByte", new Class[]{Object.class, Long.TYPE, Byte.TYPE});
            cls.getMethod("getBoolean", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putBoolean", new Class[]{Object.class, Long.TYPE, Boolean.TYPE});
            cls.getMethod("getFloat", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putFloat", new Class[]{Object.class, Long.TYPE, Float.TYPE});
            cls.getMethod("getDouble", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putDouble", new Class[]{Object.class, Long.TYPE, Double.TYPE});
            return true;
        } catch (Throwable th) {
            String valueOf = String.valueOf(th);
            zza.logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", new StringBuilder(String.valueOf(valueOf).length() + 71).append("platform method missing - proto runtime falling back to safer methods: ").append(valueOf).toString());
            return false;
        }
    }

    private static boolean zzf() {
        if (zzb == null) {
            return false;
        }
        try {
            Class cls = zzb.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            if (zzfgo.zza()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Long.TYPE});
            cls.getMethod("putByte", new Class[]{Long.TYPE, Byte.TYPE});
            cls.getMethod("getInt", new Class[]{Long.TYPE});
            cls.getMethod("putInt", new Class[]{Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Long.TYPE});
            cls.getMethod("putLong", new Class[]{Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Long.TYPE, Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE});
            return true;
        } catch (Throwable th) {
            String valueOf = String.valueOf(th);
            zza.logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", new StringBuilder(String.valueOf(valueOf).length() + 71).append("platform method missing - proto runtime falling back to safer methods: ").append(valueOf).toString());
            return false;
        }
    }
}
