package com.google.android.gms.internal;

import com.google.android.gms.common.internal.Hide;
import java.util.Iterator;

public abstract class zzbhs extends zzbhp implements zzbgp {
    @Hide
    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!getClass().isInstance(obj)) {
            return false;
        }
        zzbhp zzbhp = (zzbhp) obj;
        for (zzbhq zzbhq : zza().values()) {
            if (zza(zzbhq)) {
                if (!zzbhp.zza(zzbhq)) {
                    return false;
                }
                if (!zzb(zzbhq).equals(zzbhp.zzb(zzbhq))) {
                    return false;
                }
            } else if (zzbhp.zza(zzbhq)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        Iterator it = zza().values().iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            zzbhq zzbhq = (zzbhq) it.next();
            if (zza(zzbhq)) {
                i = zzb(zzbhq).hashCode() + (i2 * 31);
            } else {
                i = i2;
            }
        }
    }

    public Object zza(String str) {
        return null;
    }

    public boolean zzb(String str) {
        return false;
    }
}
