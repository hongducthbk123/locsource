package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzaym;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbhq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Hide
public final class zzl extends zzaym {
    public static final Creator<zzl> CREATOR = new zzm();
    private static final HashMap<String, zzbhq<?, ?>> zza;
    private Set<Integer> zzb;
    @Hide
    private int zzc;
    private ArrayList<zzr> zzd;
    private int zze;
    private zzo zzf;

    static {
        HashMap<String, zzbhq<?, ?>> hashMap = new HashMap<>();
        zza = hashMap;
        hashMap.put("authenticatorData", zzbhq.zzb("authenticatorData", 2, zzr.class));
        zza.put("progress", zzbhq.zza("progress", 4, zzo.class));
    }

    @Hide
    public zzl() {
        this.zzb = new HashSet(1);
        this.zzc = 1;
    }

    zzl(Set<Integer> set, int i, ArrayList<zzr> arrayList, int i2, zzo zzo) {
        this.zzb = set;
        this.zzc = i;
        this.zzd = arrayList;
        this.zze = i2;
        this.zzf = zzo;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zza2 = zzbgo.zza(parcel);
        Set<Integer> set = this.zzb;
        if (set.contains(Integer.valueOf(1))) {
            zzbgo.zza(parcel, 1, this.zzc);
        }
        if (set.contains(Integer.valueOf(2))) {
            zzbgo.zzc(parcel, 2, this.zzd, true);
        }
        if (set.contains(Integer.valueOf(3))) {
            zzbgo.zza(parcel, 3, this.zze);
        }
        if (set.contains(Integer.valueOf(4))) {
            zzbgo.zza(parcel, 4, (Parcelable) this.zzf, i, true);
        }
        zzbgo.zza(parcel, zza2);
    }

    public final /* synthetic */ Map zza() {
        return zza;
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzbhq zzbhq) {
        return this.zzb.contains(Integer.valueOf(zzbhq.zza()));
    }

    /* access modifiers changed from: protected */
    public final Object zzb(zzbhq zzbhq) {
        switch (zzbhq.zza()) {
            case 1:
                return Integer.valueOf(this.zzc);
            case 2:
                return this.zzd;
            case 4:
                return this.zzf;
            default:
                throw new IllegalStateException("Unknown SafeParcelable id=" + zzbhq.zza());
        }
    }
}
