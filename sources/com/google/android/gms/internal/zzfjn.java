package com.google.android.gms.internal;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zzfjn {
    private static final zzfjn zza = new zzfjn();
    private final zzfjw zzb;
    private final ConcurrentMap<Class<?>, zzfjv<?>> zzc = new ConcurrentHashMap();

    private zzfjn() {
        zzfjw zzfjw = null;
        String[] strArr = {"com.google.protobuf.AndroidProto3SchemaFactory"};
        for (int i = 0; i <= 0; i++) {
            zzfjw = zza(strArr[0]);
            if (zzfjw != null) {
                break;
            }
        }
        if (zzfjw == null) {
            zzfjw = new zzfiq();
        }
        this.zzb = zzfjw;
    }

    public static zzfjn zza() {
        return zza;
    }

    private static zzfjw zza(String str) {
        try {
            return (zzfjw) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Throwable th) {
            return null;
        }
    }

    public final <T> zzfjv<T> zza(Class<T> cls) {
        zzfhz.zza(cls, "messageType");
        zzfjv<T> zzfjv = (zzfjv) this.zzc.get(cls);
        if (zzfjv != null) {
            return zzfjv;
        }
        zzfjv zza2 = this.zzb.zza(cls);
        zzfhz.zza(cls, "messageType");
        zzfhz.zza(zza2, "schema");
        zzfjv<T> zzfjv2 = (zzfjv) this.zzc.putIfAbsent(cls, zza2);
        return zzfjv2 != null ? zzfjv2 : zza2;
    }
}
