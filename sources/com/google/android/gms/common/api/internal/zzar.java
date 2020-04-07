package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.common.zzf;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

final class zzar extends zzay {
    final /* synthetic */ zzao zza;
    private final Map<zze, zzaq> zzb;

    public zzar(zzao zzao, Map<zze, zzaq> map) {
        this.zza = zzao;
        super(zzao, null);
        this.zzb = map;
    }

    private final int zza(@NonNull zze zze, @NonNull Map<zze, Integer> map) {
        int i;
        zzbq.zza(zze);
        zzbq.zza(map);
        if (!zze.zzu()) {
            return 0;
        }
        if (map.containsKey(zze)) {
            return ((Integer) map.get(zze)).intValue();
        }
        Iterator it = map.keySet().iterator();
        if (it.hasNext()) {
            zze zze2 = (zze) it.next();
            zze2.zzx();
            zze.zzx();
            i = ((Integer) map.get(zze2)).intValue();
        } else {
            i = -1;
        }
        if (i == -1) {
            i = zzf.zza(this.zza.zzc, zze.zzx());
        }
        map.put(zze, Integer.valueOf(i));
        return i;
    }

    @WorkerThread
    public final void zza() {
        int i = 0;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (zze zze : this.zzb.keySet()) {
            if (!zze.zzu() || ((zzaq) this.zzb.get(zze)).zzc) {
                arrayList2.add(zze);
            } else {
                arrayList.add(zze);
            }
        }
        HashMap hashMap = new HashMap(this.zzb.size());
        int i2 = -1;
        if (!arrayList.isEmpty()) {
            ArrayList arrayList3 = arrayList;
            int size = arrayList3.size();
            int i3 = 0;
            while (i3 < size) {
                Object obj = arrayList3.get(i3);
                i3++;
                i2 = zza((zze) obj, hashMap);
                if (i2 != 0) {
                    break;
                }
            }
        } else {
            ArrayList arrayList4 = arrayList2;
            int size2 = arrayList4.size();
            while (i < size2) {
                Object obj2 = arrayList4.get(i);
                i++;
                i2 = zza((zze) obj2, hashMap);
                if (i2 == 0) {
                    break;
                }
            }
        }
        int i4 = i2;
        if (i4 != 0) {
            this.zza.zza.zza((zzbj) new zzas(this, this.zza, new ConnectionResult(i4, null)));
            return;
        }
        if (this.zza.zzm) {
            this.zza.zzk.zzi();
        }
        for (zze zze2 : this.zzb.keySet()) {
            zzj zzj = (zzj) this.zzb.get(zze2);
            if (!zze2.zzu() || zza(zze2, hashMap) == 0) {
                zze2.zza(zzj);
            } else {
                this.zza.zza.zza((zzbj) new zzat(this, this.zza, zzj));
            }
        }
    }
}
