package com.google.android.gms.phenotype;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class zza {
    private static final ConcurrentHashMap<Uri, zza> zza = new ConcurrentHashMap<>();
    private static String[] zzg = {"key", "value"};
    private final ContentResolver zzb;
    private final Uri zzc;
    private final ContentObserver zzd;
    private final Object zze = new Object();
    private volatile Map<String, String> zzf;

    private zza(ContentResolver contentResolver, Uri uri) {
        this.zzb = contentResolver;
        this.zzc = uri;
        this.zzd = new zzb(this, null);
    }

    public static zza zza(ContentResolver contentResolver, Uri uri) {
        zza zza2 = (zza) zza.get(uri);
        if (zza2 != null) {
            return zza2;
        }
        zza zza3 = new zza(contentResolver, uri);
        zza zza4 = (zza) zza.putIfAbsent(uri, zza3);
        if (zza4 != null) {
            return zza4;
        }
        zza3.zzb.registerContentObserver(zza3.zzc, false, zza3.zzd);
        return zza3;
    }

    private final Map<String, String> zzc() {
        HashMap hashMap = new HashMap();
        Cursor query = this.zzb.query(this.zzc, zzg, null, null, null);
        if (query != null) {
            while (query.moveToNext()) {
                try {
                    hashMap.put(query.getString(0), query.getString(1));
                } finally {
                    query.close();
                }
            }
        }
        return hashMap;
    }

    public final Map<String, String> zza() {
        Map<String, String> zzc2 = PhenotypeFlag.zza("gms:phenotype:phenotype_flag:debug_disable_caching", false) ? zzc() : this.zzf;
        if (zzc2 == null) {
            synchronized (this.zze) {
                zzc2 = this.zzf;
                if (zzc2 == null) {
                    zzc2 = zzc();
                    this.zzf = zzc2;
                }
            }
        }
        return zzc2;
    }

    public final void zzb() {
        synchronized (this.zze) {
            this.zzf = null;
        }
    }
}
