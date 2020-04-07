package com.google.android.gms.internal;

import com.google.android.gms.internal.zzfhu;
import com.google.android.gms.internal.zzfhu.zza;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzfhu<MessageType extends zzfhu<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzfgj<MessageType, BuilderType> {
    private static Map<Object, zzfhu<?, ?>> zzd = new ConcurrentHashMap();
    protected zzfko zzb = zzfko.zza();
    protected int zzc = -1;

    public static abstract class zza<MessageType extends zzfhu<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzfgk<MessageType, BuilderType> {
        protected MessageType zza;
        private final MessageType zzb;
        private boolean zzc = false;

        protected zza(MessageType messagetype) {
            this.zzb = messagetype;
            this.zza = (zzfhu) messagetype.zza(zzg.zzg, (Object) null, (Object) null);
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zzf zzf = zzf.zza;
            messagetype.zza(zzg.zzb, (Object) zzf, (Object) messagetype2);
            messagetype.zzb = zzf.zza(messagetype.zzb, messagetype2.zzb);
        }

        /* access modifiers changed from: private */
        /* renamed from: zzc */
        public final BuilderType zzb(zzfhb zzfhb, zzfhm zzfhm) throws IOException {
            zzb();
            try {
                this.zza.zza(zzg.zze, (Object) zzfhb, (Object) zzfhm);
                return this;
            } catch (RuntimeException e) {
                if (e.getCause() instanceof IOException) {
                    throw ((IOException) e.getCause());
                }
                throw e;
            }
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            MessageType messagetype;
            zza zza2 = (zza) ((zzfhu) this.zzb).zza(zzg.zzh, (Object) null, (Object) null);
            if (this.zzc) {
                messagetype = this.zza;
            } else {
                MessageType messagetype2 = this.zza;
                messagetype2.zza(zzg.zzf, (Object) null, (Object) null);
                messagetype2.zzb.zzc();
                this.zzc = true;
                messagetype = this.zza;
            }
            zza2.zza((MessageType) (zzfhu) messagetype);
            return zza2;
        }

        public final /* synthetic */ zzfgk zza() {
            return (zza) clone();
        }

        public final /* synthetic */ zzfgk zza(zzfhb zzfhb, zzfhm zzfhm) throws IOException {
            return (zza) zzb(zzfhb, zzfhm);
        }

        public final BuilderType zza(MessageType messagetype) {
            zzb();
            zza(this.zza, messagetype);
            return this;
        }

        /* access modifiers changed from: protected */
        public final void zzb() {
            if (this.zzc) {
                MessageType messagetype = (zzfhu) this.zza.zza(zzg.zzg, (Object) null, (Object) null);
                zza(messagetype, this.zza);
                this.zza = messagetype;
                this.zzc = false;
            }
        }

        public final MessageType zzc() {
            if (this.zzc) {
                return this.zza;
            }
            MessageType messagetype = this.zza;
            messagetype.zza(zzg.zzf, (Object) null, (Object) null);
            messagetype.zzb.zzc();
            this.zzc = true;
            return this.zza;
        }

        public final MessageType zzd() {
            MessageType messagetype;
            boolean z;
            boolean z2 = true;
            if (this.zzc) {
                messagetype = this.zza;
            } else {
                MessageType messagetype2 = this.zza;
                messagetype2.zza(zzg.zzf, (Object) null, (Object) null);
                messagetype2.zzb.zzc();
                this.zzc = true;
                messagetype = this.zza;
            }
            MessageType messagetype3 = (zzfhu) messagetype;
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) messagetype3.zza(zzg.zzc, (Object) null, (Object) null)).byteValue();
            if (byteValue == 1) {
                z = true;
            } else if (byteValue == 0) {
                z = false;
            } else {
                if (messagetype3.zza(zzg.zza, (Object) Boolean.FALSE, (Object) null) == null) {
                    z2 = false;
                }
                if (booleanValue) {
                    messagetype3.zza(zzg.zzd, (Object) z2 ? messagetype3 : null, (Object) null);
                }
                z = z2;
            }
            if (z) {
                return messagetype3;
            }
            throw new zzfkm(messagetype3);
        }

        public final /* synthetic */ zzfjc zze() {
            if (this.zzc) {
                return this.zza;
            }
            MessageType messagetype = this.zza;
            messagetype.zza(zzg.zzf, (Object) null, (Object) null);
            messagetype.zzb.zzc();
            this.zzc = true;
            return this.zza;
        }

        public final /* synthetic */ zzfjc zzf() {
            MessageType messagetype;
            boolean z;
            boolean z2 = true;
            if (this.zzc) {
                messagetype = this.zza;
            } else {
                MessageType messagetype2 = this.zza;
                messagetype2.zza(zzg.zzf, (Object) null, (Object) null);
                messagetype2.zzb.zzc();
                this.zzc = true;
                messagetype = this.zza;
            }
            zzfhu zzfhu = (zzfhu) messagetype;
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) zzfhu.zza(zzg.zzc, (Object) null, (Object) null)).byteValue();
            if (byteValue == 1) {
                z = true;
            } else if (byteValue == 0) {
                z = false;
            } else {
                if (zzfhu.zza(zzg.zza, (Object) Boolean.FALSE, (Object) null) == null) {
                    z2 = false;
                }
                if (booleanValue) {
                    zzfhu.zza(zzg.zzd, (Object) z2 ? zzfhu : null, (Object) null);
                }
                z = z2;
            }
            if (z) {
                return zzfhu;
            }
            throw new zzfkm(zzfhu);
        }

        public final boolean zzs() {
            return zzfhu.zza(this.zza, false);
        }

        public final /* synthetic */ zzfjc zzw() {
            return this.zzb;
        }
    }

    public static class zzb<T extends zzfhu<T, ?>> extends zzfgm<T> {
        private T zza;

        public zzb(T t) {
            this.zza = t;
        }

        public final /* synthetic */ Object zzb(zzfhb zzfhb, zzfhm zzfhm) throws zzfie {
            return zzfhu.zza(this.zza, zzfhb, zzfhm);
        }
    }

    static class zzc implements zzh {
        static final zzc zza = new zzc();
        private static zzfhv zzb = new zzfhv();

        private zzc() {
        }

        public final double zza(boolean z, double d, boolean z2, double d2) {
            if (z == z2 && d == d2) {
                return d;
            }
            throw zzb;
        }

        public final int zza(boolean z, int i, boolean z2, int i2) {
            if (z == z2 && i == i2) {
                return i;
            }
            throw zzb;
        }

        public final long zza(boolean z, long j, boolean z2, long j2) {
            if (z == z2 && j == j2) {
                return j;
            }
            throw zzb;
        }

        public final zzfgs zza(boolean z, zzfgs zzfgs, boolean z2, zzfgs zzfgs2) {
            if (z == z2 && zzfgs.equals(zzfgs2)) {
                return zzfgs;
            }
            throw zzb;
        }

        public final zzfic zza(zzfic zzfic, zzfic zzfic2) {
            if (zzfic.equals(zzfic2)) {
                return zzfic;
            }
            throw zzb;
        }

        public final <T> zzfid<T> zza(zzfid<T> zzfid, zzfid<T> zzfid2) {
            if (zzfid.equals(zzfid2)) {
                return zzfid;
            }
            throw zzb;
        }

        public final <K, V> zzfiw<K, V> zza(zzfiw<K, V> zzfiw, zzfiw<K, V> zzfiw2) {
            if (zzfiw.equals(zzfiw2)) {
                return zzfiw;
            }
            throw zzb;
        }

        public final <T extends zzfjc> T zza(T t, T t2) {
            if (t == null && t2 == null) {
                return null;
            }
            if (t == null || t2 == null) {
                throw zzb;
            }
            T t3 = (zzfhu) t;
            if (t3 == t2 || !((zzfhu) t3.zza(zzg.zzi, (Object) null, (Object) null)).getClass().isInstance(t2)) {
                return t;
            }
            zzfhu zzfhu = (zzfhu) t2;
            t3.zza(zzg.zzb, (Object) this, (Object) zzfhu);
            t3.zzb = zza(t3.zzb, zzfhu.zzb);
            return t;
        }

        public final zzfko zza(zzfko zzfko, zzfko zzfko2) {
            if (zzfko.equals(zzfko2)) {
                return zzfko;
            }
            throw zzb;
        }

        public final Object zza(boolean z, Object obj, Object obj2) {
            if (z && obj.equals(obj2)) {
                return obj;
            }
            throw zzb;
        }

        public final String zza(boolean z, String str, boolean z2, String str2) {
            if (z == z2 && str.equals(str2)) {
                return str;
            }
            throw zzb;
        }

        public final void zza(boolean z) {
            if (z) {
                throw zzb;
            }
        }

        public final boolean zza(boolean z, boolean z2, boolean z3, boolean z4) {
            if (z == z3 && z2 == z4) {
                return z2;
            }
            throw zzb;
        }

        public final Object zzb(boolean z, Object obj, Object obj2) {
            if (z && obj.equals(obj2)) {
                return obj;
            }
            throw zzb;
        }

        public final Object zzc(boolean z, Object obj, Object obj2) {
            if (z && obj.equals(obj2)) {
                return obj;
            }
            throw zzb;
        }

        public final Object zzd(boolean z, Object obj, Object obj2) {
            if (z && obj.equals(obj2)) {
                return obj;
            }
            throw zzb;
        }

        public final Object zze(boolean z, Object obj, Object obj2) {
            if (z && obj.equals(obj2)) {
                return obj;
            }
            throw zzb;
        }

        public final Object zzf(boolean z, Object obj, Object obj2) {
            if (z && obj.equals(obj2)) {
                return obj;
            }
            throw zzb;
        }

        public final Object zzg(boolean z, Object obj, Object obj2) {
            boolean z2;
            if (z) {
                zzfhu zzfhu = (zzfhu) obj;
                zzfjc zzfjc = (zzfjc) obj2;
                if (zzfhu == zzfjc) {
                    z2 = true;
                } else if (!((zzfhu) zzfhu.zza(zzg.zzi, (Object) null, (Object) null)).getClass().isInstance(zzfjc)) {
                    z2 = false;
                } else {
                    zzfhu zzfhu2 = (zzfhu) zzfjc;
                    zzfhu.zza(zzg.zzb, (Object) this, (Object) zzfhu2);
                    zzfhu.zzb = zza(zzfhu.zzb, zzfhu2.zzb);
                    z2 = true;
                }
                if (z2) {
                    return obj;
                }
            }
            throw zzb;
        }
    }

    public static abstract class zzd<MessageType extends zzd<MessageType, BuilderType>, BuilderType> extends zzfhu<MessageType, BuilderType> implements zzfje {
        protected zzfhq<Object> zzd = zzfhq.zza();
    }

    static class zze implements zzh {
        int zza = 0;

        zze() {
        }

        public final double zza(boolean z, double d, boolean z2, double d2) {
            this.zza = (this.zza * 53) + zzfhz.zza(Double.doubleToLongBits(d));
            return d;
        }

        public final int zza(boolean z, int i, boolean z2, int i2) {
            this.zza = (this.zza * 53) + i;
            return i;
        }

        public final long zza(boolean z, long j, boolean z2, long j2) {
            this.zza = (this.zza * 53) + zzfhz.zza(j);
            return j;
        }

        public final zzfgs zza(boolean z, zzfgs zzfgs, boolean z2, zzfgs zzfgs2) {
            this.zza = (this.zza * 53) + zzfgs.hashCode();
            return zzfgs;
        }

        public final zzfic zza(zzfic zzfic, zzfic zzfic2) {
            this.zza = (this.zza * 53) + zzfic.hashCode();
            return zzfic;
        }

        public final <T> zzfid<T> zza(zzfid<T> zzfid, zzfid<T> zzfid2) {
            this.zza = (this.zza * 53) + zzfid.hashCode();
            return zzfid;
        }

        public final <K, V> zzfiw<K, V> zza(zzfiw<K, V> zzfiw, zzfiw<K, V> zzfiw2) {
            this.zza = (this.zza * 53) + zzfiw.hashCode();
            return zzfiw;
        }

        public final <T extends zzfjc> T zza(T t, T t2) {
            int i;
            if (t == null) {
                i = 37;
            } else if (t instanceof zzfhu) {
                zzfhu zzfhu = (zzfhu) t;
                if (zzfhu.zza == 0) {
                    int i2 = this.zza;
                    this.zza = 0;
                    zzfhu.zza(zzg.zzb, (Object) this, (Object) zzfhu);
                    zzfhu.zzb = zza(zzfhu.zzb, zzfhu.zzb);
                    zzfhu.zza = this.zza;
                    this.zza = i2;
                }
                i = zzfhu.zza;
            } else {
                i = t.hashCode();
            }
            this.zza = i + (this.zza * 53);
            return t;
        }

        public final zzfko zza(zzfko zzfko, zzfko zzfko2) {
            this.zza = (this.zza * 53) + zzfko.hashCode();
            return zzfko;
        }

        public final Object zza(boolean z, Object obj, Object obj2) {
            this.zza = zzfhz.zza(((Boolean) obj).booleanValue()) + (this.zza * 53);
            return obj;
        }

        public final String zza(boolean z, String str, boolean z2, String str2) {
            this.zza = (this.zza * 53) + str.hashCode();
            return str;
        }

        public final void zza(boolean z) {
            if (z) {
                throw new IllegalStateException();
            }
        }

        public final boolean zza(boolean z, boolean z2, boolean z3, boolean z4) {
            this.zza = (this.zza * 53) + zzfhz.zza(z2);
            return z2;
        }

        public final Object zzb(boolean z, Object obj, Object obj2) {
            this.zza = ((Integer) obj).intValue() + (this.zza * 53);
            return obj;
        }

        public final Object zzc(boolean z, Object obj, Object obj2) {
            this.zza = zzfhz.zza(Double.doubleToLongBits(((Double) obj).doubleValue())) + (this.zza * 53);
            return obj;
        }

        public final Object zzd(boolean z, Object obj, Object obj2) {
            this.zza = zzfhz.zza(((Long) obj).longValue()) + (this.zza * 53);
            return obj;
        }

        public final Object zze(boolean z, Object obj, Object obj2) {
            this.zza = (this.zza * 53) + obj.hashCode();
            return obj;
        }

        public final Object zzf(boolean z, Object obj, Object obj2) {
            this.zza = (this.zza * 53) + obj.hashCode();
            return obj;
        }

        public final Object zzg(boolean z, Object obj, Object obj2) {
            return zza((T) (zzfjc) obj, (T) (zzfjc) obj2);
        }
    }

    public static class zzf implements zzh {
        public static final zzf zza = new zzf();

        private zzf() {
        }

        public final double zza(boolean z, double d, boolean z2, double d2) {
            return z2 ? d2 : d;
        }

        public final int zza(boolean z, int i, boolean z2, int i2) {
            return z2 ? i2 : i;
        }

        public final long zza(boolean z, long j, boolean z2, long j2) {
            return z2 ? j2 : j;
        }

        public final zzfgs zza(boolean z, zzfgs zzfgs, boolean z2, zzfgs zzfgs2) {
            return z2 ? zzfgs2 : zzfgs;
        }

        public final zzfic zza(zzfic zzfic, zzfic zzfic2) {
            int size = zzfic.size();
            int size2 = zzfic2.size();
            if (size > 0 && size2 > 0) {
                if (!zzfic.zza()) {
                    zzfic = zzfic.zza(size2 + size);
                }
                zzfic.addAll(zzfic2);
            }
            return size > 0 ? zzfic : zzfic2;
        }

        public final <T> zzfid<T> zza(zzfid<T> zzfid, zzfid<T> zzfid2) {
            int size = zzfid.size();
            int size2 = zzfid2.size();
            if (size > 0 && size2 > 0) {
                if (!zzfid.zza()) {
                    zzfid = zzfid.zzd(size2 + size);
                }
                zzfid.addAll(zzfid2);
            }
            return size > 0 ? zzfid : zzfid2;
        }

        public final <K, V> zzfiw<K, V> zza(zzfiw<K, V> zzfiw, zzfiw<K, V> zzfiw2) {
            if (!zzfiw2.isEmpty()) {
                if (!zzfiw.zzd()) {
                    zzfiw = zzfiw.zzb();
                }
                zzfiw.zza(zzfiw2);
            }
            return zzfiw;
        }

        public final <T extends zzfjc> T zza(T t, T t2) {
            return (t == null || t2 == null) ? t == null ? t2 : t : t.zzv().zza(t2).zzf();
        }

        public final zzfko zza(zzfko zzfko, zzfko zzfko2) {
            return zzfko2 == zzfko.zza() ? zzfko : zzfko.zza(zzfko, zzfko2);
        }

        public final Object zza(boolean z, Object obj, Object obj2) {
            return obj2;
        }

        public final String zza(boolean z, String str, boolean z2, String str2) {
            return z2 ? str2 : str;
        }

        public final void zza(boolean z) {
        }

        public final boolean zza(boolean z, boolean z2, boolean z3, boolean z4) {
            return z3 ? z4 : z2;
        }

        public final Object zzb(boolean z, Object obj, Object obj2) {
            return obj2;
        }

        public final Object zzc(boolean z, Object obj, Object obj2) {
            return obj2;
        }

        public final Object zzd(boolean z, Object obj, Object obj2) {
            return obj2;
        }

        public final Object zze(boolean z, Object obj, Object obj2) {
            return obj2;
        }

        public final Object zzf(boolean z, Object obj, Object obj2) {
            return obj2;
        }

        public final Object zzg(boolean z, Object obj, Object obj2) {
            return z ? zza((T) (zzfjc) obj, (T) (zzfjc) obj2) : obj2;
        }
    }

    /* 'enum' access flag removed */
    public static final class zzg {
        public static final int zza = 1;
        public static final int zzb = 2;
        public static final int zzc = 3;
        public static final int zzd = 4;
        public static final int zze = 5;
        public static final int zzf = 6;
        public static final int zzg = 7;
        public static final int zzh = 8;
        public static final int zzi = 9;
        public static final int zzj = 10;
        public static final int zzk = 1;
        public static final int zzl = 1;
        public static final int zzm = 2;
        private static final /* synthetic */ int[] zzn = {zza, zzb, zzc, zzd, zze, zzf, zzg, zzh, zzi, zzj};
        private static int zzo = 2;
        private static final /* synthetic */ int[] zzp = {zzk, zzo};
        private static final /* synthetic */ int[] zzq = {zzl, zzm};

        /* renamed from: values$50KLMJ33DTMIUPRFDTJMOP9FE1P6UT3FC9QMCBQ7CLN6ASJ1EHIM8JB5EDPM2PR59HKN8P949LIN8Q3FCHA6UIBEEPNMMP9R0 */
        public static int[] m1446x126d66cb() {
            return (int[]) zzn.clone();
        }
    }

    public interface zzh {
        double zza(boolean z, double d, boolean z2, double d2);

        int zza(boolean z, int i, boolean z2, int i2);

        long zza(boolean z, long j, boolean z2, long j2);

        zzfgs zza(boolean z, zzfgs zzfgs, boolean z2, zzfgs zzfgs2);

        zzfic zza(zzfic zzfic, zzfic zzfic2);

        <T> zzfid<T> zza(zzfid<T> zzfid, zzfid<T> zzfid2);

        <K, V> zzfiw<K, V> zza(zzfiw<K, V> zzfiw, zzfiw<K, V> zzfiw2);

        <T extends zzfjc> T zza(T t, T t2);

        zzfko zza(zzfko zzfko, zzfko zzfko2);

        Object zza(boolean z, Object obj, Object obj2);

        String zza(boolean z, String str, boolean z2, String str2);

        void zza(boolean z);

        boolean zza(boolean z, boolean z2, boolean z3, boolean z4);

        Object zzb(boolean z, Object obj, Object obj2);

        Object zzc(boolean z, Object obj, Object obj2);

        Object zzd(boolean z, Object obj, Object obj2);

        Object zze(boolean z, Object obj, Object obj2);

        Object zzf(boolean z, Object obj, Object obj2);

        Object zzg(boolean z, Object obj, Object obj2);
    }

    protected static <T extends zzfhu<T, ?>> T zza(T t, zzfgs zzfgs) throws zzfie {
        boolean z;
        boolean z2;
        boolean z3 = true;
        T zza2 = zza(t, zzfgs, zzfhm.zza());
        if (zza2 != null) {
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) zza2.zza(zzg.zzc, (Object) null, (Object) null)).byteValue();
            if (byteValue == 1) {
                z2 = true;
            } else if (byteValue == 0) {
                z2 = false;
            } else {
                boolean z4 = zza2.zza(zzg.zza, (Object) Boolean.FALSE, (Object) null) != null;
                if (booleanValue) {
                    zza2.zza(zzg.zzd, (Object) z4 ? zza2 : null, (Object) null);
                }
                z2 = z4;
            }
            if (!z2) {
                throw new zzfkm(zza2).zza().zza(zza2);
            }
        }
        if (zza2 != null) {
            boolean booleanValue2 = Boolean.TRUE.booleanValue();
            byte byteValue2 = ((Byte) zza2.zza(zzg.zzc, (Object) null, (Object) null)).byteValue();
            if (byteValue2 == 1) {
                z = true;
            } else if (byteValue2 == 0) {
                z = false;
            } else {
                if (zza2.zza(zzg.zza, (Object) Boolean.FALSE, (Object) null) == null) {
                    z3 = false;
                }
                if (booleanValue2) {
                    zza2.zza(zzg.zzd, (Object) z3 ? zza2 : null, (Object) null);
                }
                z = z3;
            }
            if (!z) {
                throw new zzfkm(zza2).zza().zza(zza2);
            }
        }
        return zza2;
    }

    private static <T extends zzfhu<T, ?>> T zza(T t, zzfgs zzfgs, zzfhm zzfhm) throws zzfie {
        T zza2;
        try {
            zzfhb zzd2 = zzfgs.zzd();
            zza2 = zza(t, zzd2, zzfhm);
            zzd2.zza(0);
            return zza2;
        } catch (zzfie e) {
            throw e.zza(zza2);
        } catch (zzfie e2) {
            throw e2;
        }
    }

    static <T extends zzfhu<T, ?>> T zza(T t, zzfhb zzfhb, zzfhm zzfhm) throws zzfie {
        T t2 = (zzfhu) t.zza(zzg.zzg, (Object) null, (Object) null);
        try {
            t2.zza(zzg.zze, (Object) zzfhb, (Object) zzfhm);
            t2.zza(zzg.zzf, (Object) null, (Object) null);
            t2.zzb.zzc();
            return t2;
        } catch (RuntimeException e) {
            if (e.getCause() instanceof zzfie) {
                throw ((zzfie) e.getCause());
            }
            throw e;
        }
    }

    protected static <T extends zzfhu<T, ?>> T zza(T t, byte[] bArr) throws zzfie {
        boolean z;
        boolean z2 = true;
        T zza2 = zza(t, bArr, zzfhm.zza());
        if (zza2 != null) {
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) zza2.zza(zzg.zzc, (Object) null, (Object) null)).byteValue();
            if (byteValue == 1) {
                z = true;
            } else if (byteValue == 0) {
                z = false;
            } else {
                if (zza2.zza(zzg.zza, (Object) Boolean.FALSE, (Object) null) == null) {
                    z2 = false;
                }
                if (booleanValue) {
                    zza2.zza(zzg.zzd, (Object) z2 ? zza2 : null, (Object) null);
                }
                z = z2;
            }
            if (!z) {
                throw new zzfkm(zza2).zza().zza(zza2);
            }
        }
        return zza2;
    }

    private static <T extends zzfhu<T, ?>> T zza(T t, byte[] bArr, zzfhm zzfhm) throws zzfie {
        T zza2;
        try {
            zzfhb zza3 = zzfhb.zza(bArr);
            zza2 = zza(t, zza3, zzfhm);
            zza3.zza(0);
            return zza2;
        } catch (zzfie e) {
            throw e.zza(zza2);
        } catch (zzfie e2) {
            throw e2;
        }
    }

    static Object zza(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    protected static final <T extends zzfhu<T, ?>> boolean zza(T t, boolean z) {
        byte byteValue = ((Byte) t.zza(zzg.zzc, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        return t.zza(zzg.zza, (Object) Boolean.FALSE, (Object) null) != null;
    }

    protected static zzfic zzt() {
        return zzfhy.zzd();
    }

    protected static <E> zzfid<E> zzu() {
        return zzfjo.zzd();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!((zzfhu) zza(zzg.zzi, (Object) null, (Object) null)).getClass().isInstance(obj)) {
            return false;
        }
        try {
            zzc zzc2 = zzc.zza;
            zzfhu zzfhu = (zzfhu) obj;
            zza(zzg.zzb, (Object) zzc2, (Object) zzfhu);
            this.zzb = zzc2.zza(this.zzb, zzfhu.zzb);
            return true;
        } catch (zzfhv e) {
            return false;
        }
    }

    public int hashCode() {
        if (this.zza != 0) {
            return this.zza;
        }
        zze zze2 = new zze();
        zza(zzg.zzb, (Object) zze2, (Object) this);
        this.zzb = zze2.zza(this.zzb, this.zzb);
        this.zza = zze2.zza;
        return this.zza;
    }

    public String toString() {
        return zzfjf.zza(this, super.toString());
    }

    public int zza() {
        if (this.zzc == -1) {
            this.zzc = zzfjn.zza().zza(getClass()).zza(this);
        }
        return this.zzc;
    }

    /* access modifiers changed from: protected */
    public abstract Object zza(int i, Object obj, Object obj2);

    public void zza(zzfhg zzfhg) throws IOException {
        zzfjn.zza().zza(getClass()).zza(this, zzfhi.zza(zzfhg));
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, zzfhb zzfhb) throws IOException {
        if ((i & 7) == 4) {
            return false;
        }
        if (this.zzb == zzfko.zza()) {
            this.zzb = zzfko.zzb();
        }
        return this.zzb.zza(i, zzfhb);
    }

    public final zzfjl<MessageType> zzr() {
        return (zzfjl) zza(zzg.zzj, (Object) null, (Object) null);
    }

    public final boolean zzs() {
        boolean z = true;
        boolean booleanValue = Boolean.TRUE.booleanValue();
        byte byteValue = ((Byte) zza(zzg.zzc, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        if (zza(zzg.zza, (Object) Boolean.FALSE, (Object) null) == null) {
            z = false;
        }
        if (booleanValue) {
            zza(zzg.zzd, (Object) z ? this : null, (Object) null);
        }
        return z;
    }

    public final /* synthetic */ zzfjd zzv() {
        zza zza2 = (zza) zza(zzg.zzh, (Object) null, (Object) null);
        zza2.zza(this);
        return zza2;
    }

    public final /* synthetic */ zzfjc zzw() {
        return (zzfhu) zza(zzg.zzi, (Object) null, (Object) null);
    }
}
