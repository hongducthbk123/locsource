package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfmp extends zzflm<zzfmp> implements Cloneable {
    private int zza;
    private String zzb;
    private String zzc;

    public zzfmp() {
        this.zza = 0;
        this.zzb = "";
        this.zzc = "";
        this.zzax = null;
        this.zzay = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public zzfmp clone() {
        try {
            return (zzfmp) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfmp)) {
            return false;
        }
        zzfmp zzfmp = (zzfmp) obj;
        if (this.zza != zzfmp.zza) {
            return false;
        }
        if (this.zzb == null) {
            if (zzfmp.zzb != null) {
                return false;
            }
        } else if (!this.zzb.equals(zzfmp.zzb)) {
            return false;
        }
        if (this.zzc == null) {
            if (zzfmp.zzc != null) {
                return false;
            }
        } else if (!this.zzc.equals(zzfmp.zzc)) {
            return false;
        }
        return (this.zzax == null || this.zzax.zzb()) ? zzfmp.zzax == null || zzfmp.zzax.zzb() : this.zzax.equals(zzfmp.zzax);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.zzc == null ? 0 : this.zzc.hashCode()) + (((this.zzb == null ? 0 : this.zzb.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + this.zza) * 31)) * 31)) * 31;
        if (this.zzax != null && !this.zzax.zzb()) {
            i = this.zzax.hashCode();
        }
        return hashCode + i;
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        int zza2 = super.zza();
        if (this.zza != 0) {
            zza2 += zzflk.zzb(1, this.zza);
        }
        if (this.zzb != null && !this.zzb.equals("")) {
            zza2 += zzflk.zzb(2, this.zzb);
        }
        return (this.zzc == null || this.zzc.equals("")) ? zza2 : zza2 + zzflk.zzb(3, this.zzc);
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zza2 = zzflj.zza();
            switch (zza2) {
                case 0:
                    break;
                case 8:
                    this.zza = zzflj.zzc();
                    continue;
                case 18:
                    this.zzb = zzflj.zze();
                    continue;
                case 26:
                    this.zzc = zzflj.zze();
                    continue;
                default:
                    if (!super.zza(zzflj, zza2)) {
                        break;
                    } else {
                        continue;
                    }
            }
        }
        return this;
    }

    public final void zza(zzflk zzflk) throws IOException {
        if (this.zza != 0) {
            zzflk.zza(1, this.zza);
        }
        if (this.zzb != null && !this.zzb.equals("")) {
            zzflk.zza(2, this.zzb);
        }
        if (this.zzc != null && !this.zzc.equals("")) {
            zzflk.zza(3, this.zzc);
        }
        super.zza(zzflk);
    }

    public final /* synthetic */ zzflm zzc() throws CloneNotSupportedException {
        return (zzfmp) clone();
    }

    public final /* synthetic */ zzfls zzd() throws CloneNotSupportedException {
        return (zzfmp) clone();
    }
}
