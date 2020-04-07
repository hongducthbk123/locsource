package com.google.android.gms.clearcut;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.clearcut.ClearcutLogger.zzb;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbfv;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzfmr;
import com.google.android.gms.phenotype.ExperimentTokens;
import java.util.Arrays;

@Hide
public final class zze extends zzbgl {
    public static final Creator<zze> CREATOR = new zzf();
    public zzbfv zza;
    public byte[] zzb;
    public final zzfmr zzc;
    public final zzb zzd;
    public final zzb zze;
    private int[] zzf;
    private String[] zzg;
    private int[] zzh;
    private byte[][] zzi;
    private ExperimentTokens[] zzj;
    private boolean zzk;

    public zze(zzbfv zzbfv, zzfmr zzfmr, zzb zzb2, zzb zzb3, int[] iArr, String[] strArr, int[] iArr2, byte[][] bArr, ExperimentTokens[] experimentTokensArr, boolean z) {
        this.zza = zzbfv;
        this.zzc = zzfmr;
        this.zzd = zzb2;
        this.zze = null;
        this.zzf = iArr;
        this.zzg = null;
        this.zzh = iArr2;
        this.zzi = null;
        this.zzj = null;
        this.zzk = z;
    }

    zze(zzbfv zzbfv, byte[] bArr, int[] iArr, String[] strArr, int[] iArr2, byte[][] bArr2, boolean z, ExperimentTokens[] experimentTokensArr) {
        this.zza = zzbfv;
        this.zzb = bArr;
        this.zzf = iArr;
        this.zzg = strArr;
        this.zzc = null;
        this.zzd = null;
        this.zze = null;
        this.zzh = iArr2;
        this.zzi = bArr2;
        this.zzj = experimentTokensArr;
        this.zzk = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zze)) {
            return false;
        }
        zze zze2 = (zze) obj;
        return zzbg.zza(this.zza, zze2.zza) && Arrays.equals(this.zzb, zze2.zzb) && Arrays.equals(this.zzf, zze2.zzf) && Arrays.equals(this.zzg, zze2.zzg) && zzbg.zza(this.zzc, zze2.zzc) && zzbg.zza(this.zzd, zze2.zzd) && zzbg.zza(this.zze, zze2.zze) && Arrays.equals(this.zzh, zze2.zzh) && Arrays.deepEquals(this.zzi, zze2.zzi) && Arrays.equals(this.zzj, zze2.zzj) && this.zzk == zze2.zzk;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, this.zzb, this.zzf, this.zzg, this.zzc, this.zzd, this.zze, this.zzh, this.zzi, this.zzj, Boolean.valueOf(this.zzk)});
    }

    public final String toString() {
        return "LogEventParcelable[" + this.zza + ", LogEventBytes: " + (this.zzb == null ? null : new String(this.zzb)) + ", TestCodes: " + Arrays.toString(this.zzf) + ", MendelPackages: " + Arrays.toString(this.zzg) + ", LogEvent: " + this.zzc + ", ExtensionProducer: " + this.zzd + ", VeProducer: " + this.zze + ", ExperimentIDs: " + Arrays.toString(this.zzh) + ", ExperimentTokens: " + Arrays.toString(this.zzi) + ", ExperimentTokensParcelables: " + Arrays.toString(this.zzj) + ", AddPhenotypeExperimentTokens: " + this.zzk + "]";
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zza2 = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zza, i, false);
        zzbgo.zza(parcel, 3, this.zzb, false);
        zzbgo.zza(parcel, 4, this.zzf, false);
        zzbgo.zza(parcel, 5, this.zzg, false);
        zzbgo.zza(parcel, 6, this.zzh, false);
        zzbgo.zza(parcel, 7, this.zzi, false);
        zzbgo.zza(parcel, 8, this.zzk);
        zzbgo.zza(parcel, 9, (T[]) this.zzj, i, false);
        zzbgo.zza(parcel, zza2);
    }
}
