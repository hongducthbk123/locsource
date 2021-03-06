package com.google.android.gms.internal;

import com.google.android.gms.internal.zzflm;
import java.io.IOException;

public abstract class zzflm<M extends zzflm<M>> extends zzfls {
    protected zzflo zzax;

    /* access modifiers changed from: protected */
    public int zza() {
        if (this.zzax == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.zzax.zza(); i2++) {
            i += this.zzax.zzb(i2).zza();
        }
        return i;
    }

    public final <T> T zza(zzfln<M, T> zzfln) {
        if (this.zzax == null) {
            return null;
        }
        zzflp zza = this.zzax.zza(zzfln.zzb >>> 3);
        if (zza != null) {
            return zza.zza(zzfln);
        }
        return null;
    }

    public void zza(zzflk zzflk) throws IOException {
        if (this.zzax != null) {
            for (int i = 0; i < this.zzax.zza(); i++) {
                this.zzax.zzb(i).zza(zzflk);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzflj zzflj, int i) throws IOException {
        int zzm = zzflj.zzm();
        if (!zzflj.zzb(i)) {
            return false;
        }
        int i2 = i >>> 3;
        zzflu zzflu = new zzflu(i, zzflj.zza(zzm, zzflj.zzm() - zzm));
        zzflp zzflp = null;
        if (this.zzax == null) {
            this.zzax = new zzflo();
        } else {
            zzflp = this.zzax.zza(i2);
        }
        if (zzflp == null) {
            zzflp = new zzflp();
            this.zzax.zza(i2, zzflp);
        }
        zzflp.zza(zzflu);
        return true;
    }

    /* renamed from: zzc */
    public M clone() throws CloneNotSupportedException {
        M m = (zzflm) super.clone();
        zzflq.zza(this, (zzflm) m);
        return m;
    }

    public /* synthetic */ zzfls zzd() throws CloneNotSupportedException {
        return (zzflm) clone();
    }
}
