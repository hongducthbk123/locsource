package com.google.android.gms.auth.api.accounttransfer;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.p000v4.util.ArraySet;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzaym;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbhq;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Hide
public class zzt extends zzaym {
    public static final Creator<zzt> CREATOR = new zzu();
    private static final HashMap<String, zzbhq<?, ?>> zza;
    private Set<Integer> zzb;
    @Hide
    private int zzc;
    private String zzd;
    private int zze;
    private byte[] zzf;
    private PendingIntent zzg;
    private DeviceMetaData zzh;

    static {
        HashMap<String, zzbhq<?, ?>> hashMap = new HashMap<>();
        zza = hashMap;
        hashMap.put("accountType", zzbhq.zzc("accountType", 2));
        zza.put("status", zzbhq.zza("status", 3));
        zza.put("transferBytes", zzbhq.zze("transferBytes", 4));
    }

    @Hide
    public zzt() {
        this.zzb = new ArraySet(3);
        this.zzc = 1;
    }

    zzt(Set<Integer> set, int i, String str, int i2, byte[] bArr, PendingIntent pendingIntent, DeviceMetaData deviceMetaData) {
        this.zzb = set;
        this.zzc = i;
        this.zzd = str;
        this.zze = i2;
        this.zzf = bArr;
        this.zzg = pendingIntent;
        this.zzh = deviceMetaData;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zza2 = zzbgo.zza(parcel);
        Set<Integer> set = this.zzb;
        if (set.contains(Integer.valueOf(1))) {
            zzbgo.zza(parcel, 1, this.zzc);
        }
        if (set.contains(Integer.valueOf(2))) {
            zzbgo.zza(parcel, 2, this.zzd, true);
        }
        if (set.contains(Integer.valueOf(3))) {
            zzbgo.zza(parcel, 3, this.zze);
        }
        if (set.contains(Integer.valueOf(4))) {
            zzbgo.zza(parcel, 4, this.zzf, true);
        }
        if (set.contains(Integer.valueOf(5))) {
            zzbgo.zza(parcel, 5, (Parcelable) this.zzg, i, true);
        }
        if (set.contains(Integer.valueOf(6))) {
            zzbgo.zza(parcel, 6, (Parcelable) this.zzh, i, true);
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
            case 3:
                return Integer.valueOf(this.zze);
            case 4:
                return this.zzf;
            default:
                throw new IllegalStateException("Unknown SafeParcelable id=" + zzbhq.zza());
        }
    }
}
