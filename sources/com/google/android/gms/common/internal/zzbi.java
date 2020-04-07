package com.google.android.gms.common.internal;

import java.util.ArrayList;
import java.util.List;
import p004cn.jiguang.net.HttpUtils;

public final class zzbi {
    private final List<String> zza;
    private final Object zzb;

    private zzbi(Object obj) {
        this.zzb = zzbq.zza(obj);
        this.zza = new ArrayList();
    }

    public final String toString() {
        StringBuilder append = new StringBuilder(100).append(this.zzb.getClass().getSimpleName()).append('{');
        int size = this.zza.size();
        for (int i = 0; i < size; i++) {
            append.append((String) this.zza.get(i));
            if (i < size - 1) {
                append.append(", ");
            }
        }
        return append.append('}').toString();
    }

    public final zzbi zza(String str, Object obj) {
        List<String> list = this.zza;
        String str2 = (String) zzbq.zza(str);
        String valueOf = String.valueOf(obj);
        list.add(new StringBuilder(String.valueOf(str2).length() + 1 + String.valueOf(valueOf).length()).append(str2).append(HttpUtils.EQUAL_SIGN).append(valueOf).toString());
        return this;
    }
}
