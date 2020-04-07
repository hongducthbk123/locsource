package com.google.android.gms.internal;

import com.google.android.gms.internal.zzfhu.zzb;
import com.google.android.gms.internal.zzfhu.zzg;
import com.google.android.gms.internal.zzfhu.zzh;
import java.io.IOException;

public final class zzfhw extends zzfhu<zzfhw, zza> implements zzfje {
    /* access modifiers changed from: private */
    public static final zzfhw zze;
    private static volatile zzfjl<zzfhw> zzf;
    private int zzd;

    public static final class zza extends com.google.android.gms.internal.zzfhu.zza<zzfhw, zza> implements zzfje {
        private zza() {
            super(zzfhw.zze);
        }

        /* synthetic */ zza(zzfhx zzfhx) {
            this();
        }

        public final zza zza(int i) {
            zzb();
            ((zzfhw) this.zza).zza(i);
            return this;
        }
    }

    static {
        zzfhw zzfhw = new zzfhw();
        zze = zzfhw;
        zzfhw.zza(zzg.zzf, (Object) null, (Object) null);
        zzfhw.zzb.zzc();
    }

    private zzfhw() {
    }

    /* access modifiers changed from: private */
    public final void zza(int i) {
        this.zzd = i;
    }

    public static zza zzc() {
        return (zza) ((com.google.android.gms.internal.zzfhu.zza) zze.zza(zzg.zzh, (Object) null, (Object) null));
    }

    public static zzfhw zzd() {
        return zze;
    }

    public final int zza() {
        int i = this.zzc;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (this.zzd != 0) {
            i2 = zzfhg.zze(1, this.zzd) + 0;
        }
        int zze2 = i2 + this.zzb.zze();
        this.zzc = zze2;
        return zze2;
    }

    /* access modifiers changed from: protected */
    public final Object zza(int i, Object obj, Object obj2) {
        boolean zza2;
        boolean z = true;
        switch (zzfhx.zza[i - 1]) {
            case 1:
                return new zzfhw();
            case 2:
                return zze;
            case 3:
                return null;
            case 4:
                return new zza(null);
            case 5:
                zzh zzh = (zzh) obj;
                zzfhw zzfhw = (zzfhw) obj2;
                boolean z2 = this.zzd != 0;
                int i2 = this.zzd;
                if (zzfhw.zzd == 0) {
                    z = false;
                }
                this.zzd = zzh.zza(z2, i2, z, zzfhw.zzd);
                return this;
            case 6:
                zzfhb zzfhb = (zzfhb) obj;
                if (((zzfhm) obj2) != null) {
                    boolean z3 = false;
                    while (!z3) {
                        try {
                            int zza3 = zzfhb.zza();
                            switch (zza3) {
                                case 0:
                                    z3 = true;
                                    break;
                                case 8:
                                    this.zzd = zzfhb.zzf();
                                    break;
                                default:
                                    if ((zza3 & 7) == 4) {
                                        zza2 = false;
                                    } else {
                                        if (this.zzb == zzfko.zza()) {
                                            this.zzb = zzfko.zzb();
                                        }
                                        zza2 = this.zzb.zza(zza3, zzfhb);
                                    }
                                    if (zza2) {
                                        break;
                                    } else {
                                        z3 = true;
                                        break;
                                    }
                            }
                        } catch (zzfie e) {
                            throw new RuntimeException(e.zza(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new zzfie(e2.getMessage()).zza(this));
                        }
                    }
                    break;
                } else {
                    throw new NullPointerException();
                }
            case 7:
                break;
            case 8:
                if (zzf == null) {
                    synchronized (zzfhw.class) {
                        if (zzf == null) {
                            zzf = new zzb(zze);
                        }
                    }
                }
                return zzf;
            case 9:
                return Byte.valueOf(1);
            case 10:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
        return zze;
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        if (this.zzd != 0) {
            zzfhg.zzb(1, this.zzd);
        }
        this.zzb.zza(zzfhg);
    }

    public final int zzb() {
        return this.zzd;
    }
}
