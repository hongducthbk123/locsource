package com.google.android.gms.internal;

import com.google.android.gms.internal.zzfhs;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

final class zzfhq<FieldDescriptorType extends zzfhs<FieldDescriptorType>> {
    private static final zzfhq zzd = new zzfhq(true);
    private final zzfjy<FieldDescriptorType, Object> zza = zzfjy.zza(16);
    private boolean zzb;
    private boolean zzc = false;

    private zzfhq() {
    }

    private zzfhq(boolean z) {
        if (!this.zzb) {
            this.zza.zza();
            this.zzb = true;
        }
    }

    static int zza(zzfky zzfky, int i, Object obj) {
        int i2;
        int zzf = zzfhg.zzf(i);
        if (zzfky == zzfky.GROUP) {
            zzfhz.zza((zzfjc) obj);
            i2 = zzf << 1;
        } else {
            i2 = zzf;
        }
        return i2 + zzb(zzfky, obj);
    }

    private static int zza(Entry<FieldDescriptorType, Object> entry) {
        zzfhs zzfhs = (zzfhs) entry.getKey();
        Object value = entry.getValue();
        return (zzfhs.zzc() != zzfld.MESSAGE || zzfhs.zzd() || zzfhs.zze()) ? zzb(zzfhs, value) : value instanceof zzfig ? zzfhg.zzb(((zzfhs) entry.getKey()).zza(), (zzfik) (zzfig) value) : zzfhg.zzd(((zzfhs) entry.getKey()).zza(), (zzfjc) value);
    }

    public static <T extends zzfhs<T>> zzfhq<T> zza() {
        return zzd;
    }

    public static Object zza(zzfhb zzfhb, zzfky zzfky, boolean z) throws IOException {
        zzfle zzfle = zzfle.STRICT;
        switch (zzfkx.zza[zzfky.ordinal()]) {
            case 1:
                return Double.valueOf(zzfhb.zzb());
            case 2:
                return Float.valueOf(zzfhb.zzc());
            case 3:
                return Long.valueOf(zzfhb.zze());
            case 4:
                return Long.valueOf(zzfhb.zzd());
            case 5:
                return Integer.valueOf(zzfhb.zzf());
            case 6:
                return Long.valueOf(zzfhb.zzg());
            case 7:
                return Integer.valueOf(zzfhb.zzh());
            case 8:
                return Boolean.valueOf(zzfhb.zzi());
            case 9:
                return zzfhb.zzl();
            case 10:
                return Integer.valueOf(zzfhb.zzm());
            case 11:
                return Integer.valueOf(zzfhb.zzo());
            case 12:
                return Long.valueOf(zzfhb.zzp());
            case 13:
                return Integer.valueOf(zzfhb.zzq());
            case 14:
                return Long.valueOf(zzfhb.zzr());
            case 15:
                return zzfle.zza(zzfhb);
            case 16:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle nested groups.");
            case 17:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle embedded messages.");
            case 18:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle enums.");
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    static void zza(zzfhg zzfhg, zzfky zzfky, int i, Object obj) throws IOException {
        if (zzfky == zzfky.GROUP) {
            zzfhz.zza((zzfjc) obj);
            zzfhg.zze(i, (zzfjc) obj);
            return;
        }
        zzfhg.zza(i, zzfky.zzb());
        switch (zzfhr.zzb[zzfky.ordinal()]) {
            case 1:
                zzfhg.zza(((Double) obj).doubleValue());
                return;
            case 2:
                zzfhg.zza(((Float) obj).floatValue());
                return;
            case 3:
                zzfhg.zza(((Long) obj).longValue());
                return;
            case 4:
                zzfhg.zza(((Long) obj).longValue());
                return;
            case 5:
                zzfhg.zzb(((Integer) obj).intValue());
                return;
            case 6:
                zzfhg.zzc(((Long) obj).longValue());
                return;
            case 7:
                zzfhg.zze(((Integer) obj).intValue());
                return;
            case 8:
                zzfhg.zza(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((zzfjc) obj).zza(zzfhg);
                return;
            case 10:
                zzfhg.zza((zzfjc) obj);
                return;
            case 11:
                if (obj instanceof zzfgs) {
                    zzfhg.zza((zzfgs) obj);
                    return;
                } else {
                    zzfhg.zza((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzfgs) {
                    zzfhg.zza((zzfgs) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzfhg.zzd(bArr, 0, bArr.length);
                return;
            case 13:
                zzfhg.zzc(((Integer) obj).intValue());
                return;
            case 14:
                zzfhg.zze(((Integer) obj).intValue());
                return;
            case 15:
                zzfhg.zzc(((Long) obj).longValue());
                return;
            case 16:
                zzfhg.zzd(((Integer) obj).intValue());
                return;
            case 17:
                zzfhg.zzb(((Long) obj).longValue());
                return;
            case 18:
                if (obj instanceof zzfia) {
                    zzfhg.zzb(((zzfia) obj).zza());
                    return;
                } else {
                    zzfhg.zzb(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r1v1 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zza(FieldDescriptorType r7, java.lang.Object r8) {
        /*
            r6 = this;
            boolean r0 = r7.zzd()
            if (r0 == 0) goto L_0x0034
            boolean r0 = r8 instanceof java.util.List
            if (r0 != 0) goto L_0x0012
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Wrong object type used with protocol message reflection."
            r0.<init>(r1)
            throw r0
        L_0x0012:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.List r8 = (java.util.List) r8
            r1.addAll(r8)
            r0 = r1
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            int r3 = r0.size()
            r2 = 0
        L_0x0024:
            if (r2 >= r3) goto L_0x003c
            java.lang.Object r4 = r0.get(r2)
            int r2 = r2 + 1
            com.google.android.gms.internal.zzfky r5 = r7.zzb()
            zza(r5, r4)
            goto L_0x0024
        L_0x0034:
            com.google.android.gms.internal.zzfky r0 = r7.zzb()
            zza(r0, r8)
            r1 = r8
        L_0x003c:
            boolean r0 = r1 instanceof com.google.android.gms.internal.zzfig
            if (r0 == 0) goto L_0x0043
            r0 = 1
            r6.zzc = r0
        L_0x0043:
            com.google.android.gms.internal.zzfjy<FieldDescriptorType, java.lang.Object> r0 = r6.zza
            r0.put(r7, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfhq.zza(com.google.android.gms.internal.zzfhs, java.lang.Object):void");
    }

    private static void zza(zzfky zzfky, Object obj) {
        boolean z = false;
        zzfhz.zza(obj);
        switch (zzfhr.zza[zzfky.zza().ordinal()]) {
            case 1:
                z = obj instanceof Integer;
                break;
            case 2:
                z = obj instanceof Long;
                break;
            case 3:
                z = obj instanceof Float;
                break;
            case 4:
                z = obj instanceof Double;
                break;
            case 5:
                z = obj instanceof Boolean;
                break;
            case 6:
                z = obj instanceof String;
                break;
            case 7:
                if ((obj instanceof zzfgs) || (obj instanceof byte[])) {
                    z = true;
                    break;
                }
            case 8:
                if ((obj instanceof Integer) || (obj instanceof zzfia)) {
                    z = true;
                    break;
                }
            case 9:
                if ((obj instanceof zzfjc) || (obj instanceof zzfig)) {
                    z = true;
                    break;
                }
        }
        if (!z) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }

    private static int zzb(zzfhs<?> zzfhs, Object obj) {
        int i = 0;
        zzfky zzb2 = zzfhs.zzb();
        int zza2 = zzfhs.zza();
        if (!zzfhs.zzd()) {
            return zza(zzb2, zza2, obj);
        }
        if (zzfhs.zze()) {
            for (Object zzb3 : (List) obj) {
                i += zzb(zzb2, zzb3);
            }
            return zzfhg.zzn(i) + zzfhg.zzf(zza2) + i;
        }
        for (Object zza3 : (List) obj) {
            i += zza(zzb2, zza2, zza3);
        }
        return i;
    }

    private static int zzb(zzfky zzfky, Object obj) {
        switch (zzfhr.zzb[zzfky.ordinal()]) {
            case 1:
                return zzfhg.zzb(((Double) obj).doubleValue());
            case 2:
                return zzfhg.zzb(((Float) obj).floatValue());
            case 3:
                return zzfhg.zzd(((Long) obj).longValue());
            case 4:
                return zzfhg.zze(((Long) obj).longValue());
            case 5:
                return zzfhg.zzg(((Integer) obj).intValue());
            case 6:
                return zzfhg.zzg(((Long) obj).longValue());
            case 7:
                return zzfhg.zzj(((Integer) obj).intValue());
            case 8:
                return zzfhg.zzb(((Boolean) obj).booleanValue());
            case 9:
                return zzfhg.zzc((zzfjc) obj);
            case 10:
                return obj instanceof zzfig ? zzfhg.zza((zzfik) (zzfig) obj) : zzfhg.zzb((zzfjc) obj);
            case 11:
                return obj instanceof zzfgs ? zzfhg.zzb((zzfgs) obj) : zzfhg.zzb((String) obj);
            case 12:
                return obj instanceof zzfgs ? zzfhg.zzb((zzfgs) obj) : zzfhg.zzb((byte[]) obj);
            case 13:
                return zzfhg.zzh(((Integer) obj).intValue());
            case 14:
                return zzfhg.zzk(((Integer) obj).intValue());
            case 15:
                return zzfhg.zzh(((Long) obj).longValue());
            case 16:
                return zzfhg.zzi(((Integer) obj).intValue());
            case 17:
                return zzfhg.zzf(((Long) obj).longValue());
            case 18:
                return obj instanceof zzfia ? zzfhg.zzl(((zzfia) obj).zza()) : zzfhg.zzl(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzfhq zzfhq = new zzfhq();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.zza.zzc()) {
                break;
            }
            Entry zzb2 = this.zza.zzb(i2);
            zzfhq.zza((FieldDescriptorType) (zzfhs) zzb2.getKey(), zzb2.getValue());
            i = i2 + 1;
        }
        for (Entry entry : this.zza.zzd()) {
            zzfhq.zza((FieldDescriptorType) (zzfhs) entry.getKey(), entry.getValue());
        }
        zzfhq.zzc = this.zzc;
        return zzfhq;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzfhq)) {
            return false;
        }
        return this.zza.equals(((zzfhq) obj).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final Iterator<Entry<FieldDescriptorType, Object>> zzb() {
        return this.zzc ? new zzfij(this.zza.entrySet().iterator()) : this.zza.entrySet().iterator();
    }

    public final int zzc() {
        int i = 0;
        for (int i2 = 0; i2 < this.zza.zzc(); i2++) {
            i += zza(this.zza.zzb(i2));
        }
        for (Entry zza2 : this.zza.zzd()) {
            i += zza(zza2);
        }
        return i;
    }
}
