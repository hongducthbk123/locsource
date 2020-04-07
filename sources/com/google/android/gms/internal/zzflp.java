package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

final class zzflp implements Cloneable {
    private zzfln<?, ?> zza;
    private Object zzb;
    private List<zzflu> zzc = new ArrayList();

    zzflp() {
    }

    private final byte[] zzb() throws IOException {
        byte[] bArr = new byte[zza()];
        zza(zzflk.zza(bArr));
        return bArr;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzc */
    public zzflp clone() {
        int i = 0;
        zzflp zzflp = new zzflp();
        try {
            zzflp.zza = this.zza;
            if (this.zzc == null) {
                zzflp.zzc = null;
            } else {
                zzflp.zzc.addAll(this.zzc);
            }
            if (this.zzb != null) {
                if (this.zzb instanceof zzfls) {
                    zzflp.zzb = (zzfls) ((zzfls) this.zzb).clone();
                } else if (this.zzb instanceof byte[]) {
                    zzflp.zzb = ((byte[]) this.zzb).clone();
                } else if (this.zzb instanceof byte[][]) {
                    byte[][] bArr = (byte[][]) this.zzb;
                    byte[][] bArr2 = new byte[bArr.length][];
                    zzflp.zzb = bArr2;
                    for (int i2 = 0; i2 < bArr.length; i2++) {
                        bArr2[i2] = (byte[]) bArr[i2].clone();
                    }
                } else if (this.zzb instanceof boolean[]) {
                    zzflp.zzb = ((boolean[]) this.zzb).clone();
                } else if (this.zzb instanceof int[]) {
                    zzflp.zzb = ((int[]) this.zzb).clone();
                } else if (this.zzb instanceof long[]) {
                    zzflp.zzb = ((long[]) this.zzb).clone();
                } else if (this.zzb instanceof float[]) {
                    zzflp.zzb = ((float[]) this.zzb).clone();
                } else if (this.zzb instanceof double[]) {
                    zzflp.zzb = ((double[]) this.zzb).clone();
                } else if (this.zzb instanceof zzfls[]) {
                    zzfls[] zzflsArr = (zzfls[]) this.zzb;
                    zzfls[] zzflsArr2 = new zzfls[zzflsArr.length];
                    zzflp.zzb = zzflsArr2;
                    while (true) {
                        int i3 = i;
                        if (i3 >= zzflsArr.length) {
                            break;
                        }
                        zzflsArr2[i3] = (zzfls) zzflsArr[i3].clone();
                        i = i3 + 1;
                    }
                }
            }
            return zzflp;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzflp)) {
            return false;
        }
        zzflp zzflp = (zzflp) obj;
        if (this.zzb == null || zzflp.zzb == null) {
            if (this.zzc != null && zzflp.zzc != null) {
                return this.zzc.equals(zzflp.zzc);
            }
            try {
                return Arrays.equals(zzb(), zzflp.zzb());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else if (this.zza == zzflp.zza) {
            return !this.zza.zza.isArray() ? this.zzb.equals(zzflp.zzb) : this.zzb instanceof byte[] ? Arrays.equals((byte[]) this.zzb, (byte[]) zzflp.zzb) : this.zzb instanceof int[] ? Arrays.equals((int[]) this.zzb, (int[]) zzflp.zzb) : this.zzb instanceof long[] ? Arrays.equals((long[]) this.zzb, (long[]) zzflp.zzb) : this.zzb instanceof float[] ? Arrays.equals((float[]) this.zzb, (float[]) zzflp.zzb) : this.zzb instanceof double[] ? Arrays.equals((double[]) this.zzb, (double[]) zzflp.zzb) : this.zzb instanceof boolean[] ? Arrays.equals((boolean[]) this.zzb, (boolean[]) zzflp.zzb) : Arrays.deepEquals((Object[]) this.zzb, (Object[]) zzflp.zzb);
        } else {
            return false;
        }
    }

    public final int hashCode() {
        try {
            return Arrays.hashCode(zzb()) + 527;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: 0000 */
    public final int zza() {
        int i = 0;
        if (this.zzb != null) {
            zzfln<?, ?> zzfln = this.zza;
            Object obj = this.zzb;
            if (!zzfln.zzc) {
                return zzfln.zza(obj);
            }
            int length = Array.getLength(obj);
            for (int i2 = 0; i2 < length; i2++) {
                if (Array.get(obj, i2) != null) {
                    i += zzfln.zza(Array.get(obj, i2));
                }
            }
            return i;
        }
        Iterator it = this.zzc.iterator();
        while (true) {
            int i3 = i;
            if (!it.hasNext()) {
                return i3;
            }
            zzflu zzflu = (zzflu) it.next();
            i = zzflu.zzb.length + zzflk.zzd(zzflu.zza) + 0 + i3;
        }
    }

    /* access modifiers changed from: 0000 */
    public final <T> T zza(zzfln<?, T> zzfln) {
        if (this.zzb == null) {
            this.zza = zzfln;
            this.zzb = zzfln.zza(this.zzc);
            this.zzc = null;
        } else if (!this.zza.equals(zzfln)) {
            throw new IllegalStateException("Tried to getExtension with a different Extension.");
        }
        return this.zzb;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzflk zzflk) throws IOException {
        if (this.zzb != null) {
            zzfln<?, ?> zzfln = this.zza;
            Object obj = this.zzb;
            if (zzfln.zzc) {
                int length = Array.getLength(obj);
                for (int i = 0; i < length; i++) {
                    Object obj2 = Array.get(obj, i);
                    if (obj2 != null) {
                        zzfln.zza(obj2, zzflk);
                    }
                }
                return;
            }
            zzfln.zza(obj, zzflk);
            return;
        }
        for (zzflu zzflu : this.zzc) {
            zzflk.zzc(zzflu.zza);
            zzflk.zzc(zzflu.zzb);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzflu zzflu) {
        this.zzc.add(zzflu);
    }
}
