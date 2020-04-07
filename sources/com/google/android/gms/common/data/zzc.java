package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import java.util.Arrays;

@Hide
public class zzc {
    protected final DataHolder zza;
    protected int zzb;
    private int zzc;

    public zzc(DataHolder dataHolder, int i) {
        this.zza = (DataHolder) zzbq.zza(dataHolder);
        zza(i);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof zzc)) {
            return false;
        }
        zzc zzc2 = (zzc) obj;
        return zzbg.zza(Integer.valueOf(zzc2.zzb), Integer.valueOf(this.zzb)) && zzbg.zza(Integer.valueOf(zzc2.zzc), Integer.valueOf(this.zzc)) && zzc2.zza == this.zza;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzb), Integer.valueOf(this.zzc), this.zza});
    }

    public boolean isDataValid() {
        return !this.zza.zze();
    }

    /* access modifiers changed from: protected */
    public final void zza(int i) {
        zzbq.zza(i >= 0 && i < this.zza.zza);
        this.zzb = i;
        this.zzc = this.zza.zza(this.zzb);
    }

    /* access modifiers changed from: protected */
    public final void zza(String str, CharArrayBuffer charArrayBuffer) {
        this.zza.zza(str, this.zzb, this.zzc, charArrayBuffer);
    }

    public final boolean zza(String str) {
        return this.zza.zza(str);
    }

    /* access modifiers changed from: protected */
    public final long zzb(String str) {
        return this.zza.zza(str, this.zzb, this.zzc);
    }

    /* access modifiers changed from: protected */
    public final int zzc(String str) {
        return this.zza.zzb(str, this.zzb, this.zzc);
    }

    /* access modifiers changed from: protected */
    public final boolean zzd(String str) {
        return this.zza.zzd(str, this.zzb, this.zzc);
    }

    /* access modifiers changed from: protected */
    public final String zze(String str) {
        return this.zza.zzc(str, this.zzb, this.zzc);
    }

    /* access modifiers changed from: protected */
    public final float zzf(String str) {
        return this.zza.zze(str, this.zzb, this.zzc);
    }

    /* access modifiers changed from: protected */
    public final byte[] zzg(String str) {
        return this.zza.zzf(str, this.zzb, this.zzc);
    }

    /* access modifiers changed from: protected */
    public final Uri zzh(String str) {
        String zzc2 = this.zza.zzc(str, this.zzb, this.zzc);
        if (zzc2 == null) {
            return null;
        }
        return Uri.parse(zzc2);
    }

    /* access modifiers changed from: protected */
    public final boolean zzi(String str) {
        return this.zza.zzg(str, this.zzb, this.zzc);
    }
}
