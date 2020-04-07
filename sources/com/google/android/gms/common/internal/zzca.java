package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.C1209R;

@Hide
public final class zzca {
    private final Resources zza;
    private final String zzb = this.zza.getResourcePackageName(C1209R.string.common_google_play_services_unknown_issue);

    public zzca(Context context) {
        zzbq.zza(context);
        this.zza = context.getResources();
    }

    public final String zza(String str) {
        int identifier = this.zza.getIdentifier(str, "string", this.zzb);
        if (identifier == 0) {
            return null;
        }
        return this.zza.getString(identifier);
    }
}
