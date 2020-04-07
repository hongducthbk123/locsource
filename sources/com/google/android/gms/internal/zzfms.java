package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfms extends zzflm<zzfms> implements Cloneable {
    private static volatile zzfms[] zza;
    private String zzb;
    private String zzc;

    public zzfms() {
        this.zzb = "";
        this.zzc = "";
        this.zzax = null;
        this.zzay = -1;
    }

    public static zzfms[] zzb() {
        if (zza == null) {
            synchronized (zzflq.zzb) {
                if (zza == null) {
                    zza = new zzfms[0];
                }
            }
        }
        return zza;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzg */
    public zzfms clone() {
        try {
            return (zzfms) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfms)) {
            return false;
        }
        zzfms zzfms = (zzfms) obj;
        if (this.zzb == null) {
            if (zzfms.zzb != null) {
                return false;
            }
        } else if (!this.zzb.equals(zzfms.zzb)) {
            return false;
        }
        if (this.zzc == null) {
            if (zzfms.zzc != null) {
                return false;
            }
        } else if (!this.zzc.equals(zzfms.zzc)) {
            return false;
        }
        return (this.zzax == null || this.zzax.zzb()) ? zzfms.zzax == null || zzfms.zzax.zzb() : this.zzax.equals(zzfms.zzax);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.zzc == null ? 0 : this.zzc.hashCode()) + (((this.zzb == null ? 0 : this.zzb.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31;
        if (this.zzax != null && !this.zzax.zzb()) {
            i = this.zzax.hashCode();
        }
        return hashCode + i;
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        int zza2 = super.zza();
        if (this.zzb != null && !this.zzb.equals("")) {
            zza2 += zzflk.zzb(1, this.zzb);
        }
        return (this.zzc == null || this.zzc.equals("")) ? zza2 : zza2 + zzflk.zzb(2, this.zzc);
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zza2 = zzflj.zza();
            switch (zza2) {
                case 0:
                    break;
                case 10:
                    this.zzb = zzflj.zze();
                    continue;
                case 18:
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
        if (this.zzb != null && !this.zzb.equals("")) {
            zzflk.zza(1, this.zzb);
        }
        if (this.zzc != null && !this.zzc.equals("")) {
            zzflk.zza(2, this.zzc);
        }
        super.zza(zzflk);
    }

    public final /* synthetic */ zzflm zzc() throws CloneNotSupportedException {
        return (zzfms) clone();
    }

    public final /* synthetic */ zzfls zzd() throws CloneNotSupportedException {
        return (zzfms) clone();
    }
}
