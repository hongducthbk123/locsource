package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Base64;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import p004cn.jiguang.net.HttpUtils;

@KeepForSdk
public class ExperimentTokens extends zzbgl {
    @KeepForSdk
    public static final Creator<ExperimentTokens> CREATOR = new zzh();
    private static byte[][] zza = new byte[0][];
    private static ExperimentTokens zzb = new ExperimentTokens("", null, zza, zza, zza, zza, null, null);
    private static final zza zzk = new zzd();
    private static final zza zzl = new zze();
    private static final zza zzm = new zzf();
    private static final zza zzn = new zzg();
    private String zzc;
    private byte[] zzd;
    private byte[][] zze;
    private byte[][] zzf;
    private byte[][] zzg;
    private byte[][] zzh;
    private int[] zzi;
    private byte[][] zzj;

    interface zza {
    }

    public ExperimentTokens(String str, byte[] bArr, byte[][] bArr2, byte[][] bArr3, byte[][] bArr4, byte[][] bArr5, int[] iArr, byte[][] bArr6) {
        this.zzc = str;
        this.zzd = bArr;
        this.zze = bArr2;
        this.zzf = bArr3;
        this.zzg = bArr4;
        this.zzh = bArr5;
        this.zzi = iArr;
        this.zzj = bArr6;
    }

    private static List<Integer> zza(int[] iArr) {
        if (iArr == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int valueOf : iArr) {
            arrayList.add(Integer.valueOf(valueOf));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    private static List<String> zza(byte[][] bArr) {
        if (bArr == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(bArr.length);
        for (byte[] encodeToString : bArr) {
            arrayList.add(Base64.encodeToString(encodeToString, 3));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    private static void zza(StringBuilder sb, String str, int[] iArr) {
        sb.append(str);
        sb.append(HttpUtils.EQUAL_SIGN);
        if (iArr == null) {
            sb.append("null");
            return;
        }
        sb.append("(");
        int length = iArr.length;
        boolean z = true;
        int i = 0;
        while (i < length) {
            int i2 = iArr[i];
            if (!z) {
                sb.append(", ");
            }
            sb.append(i2);
            i++;
            z = false;
        }
        sb.append(")");
    }

    private static void zza(StringBuilder sb, String str, byte[][] bArr) {
        sb.append(str);
        sb.append(HttpUtils.EQUAL_SIGN);
        if (bArr == null) {
            sb.append("null");
            return;
        }
        sb.append("(");
        int length = bArr.length;
        boolean z = true;
        int i = 0;
        while (i < length) {
            byte[] bArr2 = bArr[i];
            if (!z) {
                sb.append(", ");
            }
            sb.append("'");
            sb.append(Base64.encodeToString(bArr2, 3));
            sb.append("'");
            i++;
            z = false;
        }
        sb.append(")");
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ExperimentTokens)) {
            return false;
        }
        ExperimentTokens experimentTokens = (ExperimentTokens) obj;
        return zzn.zza(this.zzc, experimentTokens.zzc) && Arrays.equals(this.zzd, experimentTokens.zzd) && zzn.zza(zza(this.zze), zza(experimentTokens.zze)) && zzn.zza(zza(this.zzf), zza(experimentTokens.zzf)) && zzn.zza(zza(this.zzg), zza(experimentTokens.zzg)) && zzn.zza(zza(this.zzh), zza(experimentTokens.zzh)) && zzn.zza(zza(this.zzi), zza(experimentTokens.zzi)) && zzn.zza(zza(this.zzj), zza(experimentTokens.zzj));
    }

    public String toString() {
        String sb;
        StringBuilder sb2 = new StringBuilder("ExperimentTokens");
        sb2.append("(");
        if (this.zzc == null) {
            sb = "null";
        } else {
            String str = this.zzc;
            sb = new StringBuilder(String.valueOf(str).length() + 2).append("'").append(str).append("'").toString();
        }
        sb2.append(sb);
        sb2.append(", ");
        byte[] bArr = this.zzd;
        sb2.append("direct");
        sb2.append(HttpUtils.EQUAL_SIGN);
        if (bArr == null) {
            sb2.append("null");
        } else {
            sb2.append("'");
            sb2.append(Base64.encodeToString(bArr, 3));
            sb2.append("'");
        }
        sb2.append(", ");
        zza(sb2, "GAIA", this.zze);
        sb2.append(", ");
        zza(sb2, "PSEUDO", this.zzf);
        sb2.append(", ");
        zza(sb2, "ALWAYS", this.zzg);
        sb2.append(", ");
        zza(sb2, "OTHER", this.zzh);
        sb2.append(", ");
        zza(sb2, "weak", this.zzi);
        sb2.append(", ");
        zza(sb2, "directs", this.zzj);
        sb2.append(")");
        return sb2.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zza2 = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 2, this.zzc, false);
        zzbgo.zza(parcel, 3, this.zzd, false);
        zzbgo.zza(parcel, 4, this.zze, false);
        zzbgo.zza(parcel, 5, this.zzf, false);
        zzbgo.zza(parcel, 6, this.zzg, false);
        zzbgo.zza(parcel, 7, this.zzh, false);
        zzbgo.zza(parcel, 8, this.zzi, false);
        zzbgo.zza(parcel, 9, this.zzj, false);
        zzbgo.zza(parcel, zza2);
    }
}
