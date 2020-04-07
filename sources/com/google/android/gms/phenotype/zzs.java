package com.google.android.gms.phenotype;

import android.content.SharedPreferences;
import android.util.Log;
import com.google.android.gms.phenotype.PhenotypeFlag.Factory;

final class zzs extends PhenotypeFlag<String> {
    zzs(Factory factory, String str, String str2) {
        super(factory, str, str2, null);
    }

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public final String zza(SharedPreferences sharedPreferences) {
        try {
            return sharedPreferences.getString(this.zza, null);
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            String str = "PhenotypeFlag";
            String str2 = "Invalid string value in SharedPreferences for ";
            String valueOf = String.valueOf(this.zza);
            Log.e(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), classCastException);
            return null;
        }
    }

    public final /* synthetic */ Object zza(String str) {
        return str;
    }
}
