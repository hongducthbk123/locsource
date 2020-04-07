package com.google.android.gms.common.util;

import android.support.p000v4.util.ArrayMap;
import android.support.p000v4.util.ArraySet;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class zzf {
    private static <K, V> Map<K, V> zza(int i, boolean z, K[] kArr, V[] vArr) {
        Map<K, V> zzb = zzb(i, false);
        for (int i2 = 0; i2 < kArr.length; i2++) {
            zzb.put(kArr[i2], vArr[i2]);
        }
        return zzb;
    }

    public static <K, V> Map<K, V> zza(K k, V v, K k2, V v2, K k3, V v3) {
        Map zzb = zzb(3, false);
        zzb.put(k, v);
        zzb.put(k2, v2);
        zzb.put(k3, v3);
        return Collections.unmodifiableMap(zzb);
    }

    public static <K, V> Map<K, V> zza(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6) {
        Map zzb = zzb(6, false);
        zzb.put(k, v);
        zzb.put(k2, v2);
        zzb.put(k3, v3);
        zzb.put(k4, v4);
        zzb.put(k5, v5);
        zzb.put(k6, v6);
        return Collections.unmodifiableMap(zzb);
    }

    public static <K, V> Map<K, V> zza(K[] kArr, V[] vArr) {
        if (kArr.length != vArr.length) {
            int length = kArr.length;
            throw new IllegalArgumentException("Key and values array lengths not equal: " + length + " != " + vArr.length);
        }
        switch (kArr.length) {
            case 0:
                return Collections.emptyMap();
            case 1:
                return Collections.singletonMap(kArr[0], vArr[0]);
            default:
                return Collections.unmodifiableMap(zza(kArr.length, false, kArr, vArr));
        }
    }

    private static <T> Set<T> zza(int i, boolean z) {
        return i <= 256 ? new ArraySet(i) : new HashSet(i, 1.0f);
    }

    public static <T> Set<T> zza(T t, T t2, T t3) {
        Set zza = zza(3, false);
        zza.add(t);
        zza.add(t2);
        zza.add(t3);
        return Collections.unmodifiableSet(zza);
    }

    public static <T> Set<T> zza(T... tArr) {
        switch (tArr.length) {
            case 0:
                return Collections.emptySet();
            case 1:
                return Collections.singleton(tArr[0]);
            case 2:
                T t = tArr[0];
                T t2 = tArr[1];
                Set zza = zza(2, false);
                zza.add(t);
                zza.add(t2);
                return Collections.unmodifiableSet(zza);
            case 3:
                return zza(tArr[0], tArr[1], tArr[2]);
            case 4:
                T t3 = tArr[0];
                T t4 = tArr[1];
                T t5 = tArr[2];
                T t6 = tArr[3];
                Set zza2 = zza(4, false);
                zza2.add(t3);
                zza2.add(t4);
                zza2.add(t5);
                zza2.add(t6);
                return Collections.unmodifiableSet(zza2);
            default:
                Set zza3 = zza(tArr.length, false);
                Collections.addAll(zza3, tArr);
                return Collections.unmodifiableSet(zza3);
        }
    }

    private static <K, V> Map<K, V> zzb(int i, boolean z) {
        return i <= 256 ? new ArrayMap(i) : new HashMap(i, 1.0f);
    }
}
