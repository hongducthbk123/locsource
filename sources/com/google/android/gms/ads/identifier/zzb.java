package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.zzs;

@Hide
public final class zzb {
    private SharedPreferences zza;

    @Hide
    public zzb(Context context) {
        try {
            Context remoteContext = zzs.getRemoteContext(context);
            this.zza = remoteContext == null ? null : remoteContext.getSharedPreferences("google_ads_flags", 0);
        } catch (Throwable th) {
            Log.w("GmscoreFlag", "Error while getting SharedPreferences ", th);
            this.zza = null;
        }
    }

    /* access modifiers changed from: 0000 */
    public final float zza(String str, float f) {
        try {
            if (this.zza == null) {
                return 0.0f;
            }
            return this.zza.getFloat(str, 0.0f);
        } catch (Throwable th) {
            Log.w("GmscoreFlag", "Error while reading from SharedPreferences ", th);
            return 0.0f;
        }
    }

    /* access modifiers changed from: 0000 */
    public final String zza(String str, String str2) {
        try {
            return this.zza == null ? str2 : this.zza.getString(str, str2);
        } catch (Throwable th) {
            Log.w("GmscoreFlag", "Error while reading from SharedPreferences ", th);
            return str2;
        }
    }

    public final boolean zza(String str, boolean z) {
        try {
            if (this.zza == null) {
                return false;
            }
            return this.zza.getBoolean(str, false);
        } catch (Throwable th) {
            Log.w("GmscoreFlag", "Error while reading from SharedPreferences ", th);
            return false;
        }
    }
}
