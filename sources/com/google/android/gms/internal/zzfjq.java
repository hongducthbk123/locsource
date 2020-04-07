package com.google.android.gms.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

final class zzfjq extends zzfgs {
    /* access modifiers changed from: private */
    public static final int[] zzb;
    private final int zzc;
    /* access modifiers changed from: private */
    public final zzfgs zzd;
    /* access modifiers changed from: private */
    public final zzfgs zze;
    private final int zzf;
    private final int zzg;

    static {
        int i = 1;
        ArrayList arrayList = new ArrayList();
        int i2 = 1;
        while (i > 0) {
            arrayList.add(Integer.valueOf(i));
            int i3 = i2 + i;
            i2 = i;
            i = i3;
        }
        arrayList.add(Integer.valueOf(ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED));
        zzb = new int[arrayList.size()];
        int i4 = 0;
        while (true) {
            int i5 = i4;
            if (i5 < zzb.length) {
                zzb[i5] = ((Integer) arrayList.get(i5)).intValue();
                i4 = i5 + 1;
            } else {
                return;
            }
        }
    }

    private zzfjq(zzfgs zzfgs, zzfgs zzfgs2) {
        this.zzd = zzfgs;
        this.zze = zzfgs2;
        this.zzf = zzfgs.zza();
        this.zzc = this.zzf + zzfgs2.zza();
        this.zzg = Math.max(zzfgs.zze(), zzfgs2.zze()) + 1;
    }

    static zzfgs zza(zzfgs zzfgs, zzfgs zzfgs2) {
        if (zzfgs2.zza() == 0) {
            return zzfgs;
        }
        if (zzfgs.zza() == 0) {
            return zzfgs2;
        }
        int zza = zzfgs2.zza() + zzfgs.zza();
        if (zza < 128) {
            return zzb(zzfgs, zzfgs2);
        }
        if (zzfgs instanceof zzfjq) {
            zzfjq zzfjq = (zzfjq) zzfgs;
            if (zzfjq.zze.zza() + zzfgs2.zza() < 128) {
                return new zzfjq(zzfjq.zzd, zzb(zzfjq.zze, zzfgs2));
            } else if (zzfjq.zzd.zze() > zzfjq.zze.zze() && zzfjq.zze() > zzfgs2.zze()) {
                return new zzfjq(zzfjq.zzd, new zzfjq(zzfjq.zze, zzfgs2));
            }
        }
        return zza >= zzb[Math.max(zzfgs.zze(), zzfgs2.zze()) + 1] ? new zzfjq(zzfgs, zzfgs2) : new zzfjs().zza(zzfgs, zzfgs2);
    }

    private static zzfgs zzb(zzfgs zzfgs, zzfgs zzfgs2) {
        int zza = zzfgs.zza();
        int zza2 = zzfgs2.zza();
        byte[] bArr = new byte[(zza + zza2)];
        zzfgs.zza(bArr, 0, 0, zza);
        zzfgs2.zza(bArr, 0, zza, zza2);
        return zzfgs.zzb(bArr);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfgs)) {
            return false;
        }
        zzfgs zzfgs = (zzfgs) obj;
        if (this.zzc != zzfgs.zza()) {
            return false;
        }
        if (this.zzc == 0) {
            return true;
        }
        int zzg2 = zzg();
        int zzg3 = zzfgs.zzg();
        if (zzg2 != 0 && zzg3 != 0 && zzg2 != zzg3) {
            return false;
        }
        zzfjt zzfjt = new zzfjt(this);
        zzfgy zzfgy = (zzfgy) zzfjt.next();
        zzfjt zzfjt2 = new zzfjt(zzfgs);
        zzfgy zzfgy2 = (zzfgy) zzfjt2.next();
        int i = 0;
        zzfgy zzfgy3 = zzfgy;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int zza = zzfgy3.zza() - i2;
            int zza2 = zzfgy2.zza() - i;
            int min = Math.min(zza, zza2);
            if (!(i2 == 0 ? zzfgy3.zza(zzfgy2, i, min) : zzfgy2.zza(zzfgy3, i2, min))) {
                return false;
            }
            int i4 = i3 + min;
            if (i4 < this.zzc) {
                if (min == zza) {
                    zzfgy3 = (zzfgy) zzfjt.next();
                    i2 = 0;
                } else {
                    i2 += min;
                }
                if (min == zza2) {
                    zzfgy2 = (zzfgy) zzfjt2.next();
                    i = 0;
                    i3 = i4;
                } else {
                    i += min;
                    i3 = i4;
                }
            } else if (i4 == this.zzc) {
                return true;
            } else {
                throw new IllegalStateException();
            }
        }
    }

    public final byte zza(int i) {
        zzb(i, this.zzc);
        return i < this.zzf ? this.zzd.zza(i) : this.zze.zza(i - this.zzf);
    }

    public final int zza() {
        return this.zzc;
    }

    /* access modifiers changed from: protected */
    public final int zza(int i, int i2, int i3) {
        if (i2 + i3 <= this.zzf) {
            return this.zzd.zza(i, i2, i3);
        }
        if (i2 >= this.zzf) {
            return this.zze.zza(i, i2 - this.zzf, i3);
        }
        int i4 = this.zzf - i2;
        return this.zze.zza(this.zzd.zza(i, i2, i4), 0, i3 - i4);
    }

    public final zzfgs zza(int i, int i2) {
        int zzb2 = zzb(i, i2, this.zzc);
        if (zzb2 == 0) {
            return zzfgs.zza;
        }
        if (zzb2 == this.zzc) {
            return this;
        }
        if (i2 <= this.zzf) {
            return this.zzd.zza(i, i2);
        }
        if (i >= this.zzf) {
            return this.zze.zza(i - this.zzf, i2 - this.zzf);
        }
        zzfgs zzfgs = this.zzd;
        return new zzfjq(zzfgs.zza(i, zzfgs.zza()), this.zze.zza(0, i2 - this.zzf));
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzfgr zzfgr) throws IOException {
        this.zzd.zza(zzfgr);
        this.zze.zza(zzfgr);
    }

    /* access modifiers changed from: protected */
    public final void zzb(byte[] bArr, int i, int i2, int i3) {
        if (i + i3 <= this.zzf) {
            this.zzd.zzb(bArr, i, i2, i3);
        } else if (i >= this.zzf) {
            this.zze.zzb(bArr, i - this.zzf, i2, i3);
        } else {
            int i4 = this.zzf - i;
            this.zzd.zzb(bArr, i, i2, i4);
            this.zze.zzb(bArr, 0, i2 + i4, i3 - i4);
        }
    }

    public final zzfhb zzd() {
        return zzfhb.zza((InputStream) new zzfju(this));
    }

    /* access modifiers changed from: protected */
    public final int zze() {
        return this.zzg;
    }

    /* access modifiers changed from: protected */
    public final boolean zzf() {
        return this.zzc >= zzb[this.zzg];
    }
}
