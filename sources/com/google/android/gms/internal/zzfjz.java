package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

final class zzfjz extends zzfjy<FieldDescriptorType, Object> {
    zzfjz(int i) {
        super(i, null);
    }

    public final void zza() {
        if (!zzb()) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= zzc()) {
                    break;
                }
                Entry zzb = zzb(i2);
                if (((zzfhs) zzb.getKey()).zzd()) {
                    zzb.setValue(Collections.unmodifiableList((List) zzb.getValue()));
                }
                i = i2 + 1;
            }
            for (Entry entry : zzd()) {
                if (((zzfhs) entry.getKey()).zzd()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zza();
    }
}
