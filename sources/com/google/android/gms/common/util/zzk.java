package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.os.SystemClock;
import com.google.android.vending.expansion.downloader.Constants;

public final class zzk {
    private static IntentFilter zza = new IntentFilter("android.intent.action.BATTERY_CHANGED");
    private static long zzb;
    private static float zzc = Float.NaN;

    @TargetApi(20)
    public static int zza(Context context) {
        int i = 1;
        if (context == null || context.getApplicationContext() == null) {
            return -1;
        }
        Intent registerReceiver = context.getApplicationContext().registerReceiver(null, zza);
        boolean z = ((registerReceiver == null ? 0 : registerReceiver.getIntExtra("plugged", 0)) & 7) != 0;
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager == null) {
            return -1;
        }
        int i2 = (zzs.zzf() ? powerManager.isInteractive() : powerManager.isScreenOn() ? 1 : 0) << 1;
        if (!z) {
            i = 0;
        }
        return i2 | i;
    }

    public static synchronized float zzb(Context context) {
        float f;
        synchronized (zzk.class) {
            if (SystemClock.elapsedRealtime() - zzb >= Constants.WATCHDOG_WAKE_TIMER || Float.isNaN(zzc)) {
                Intent registerReceiver = context.getApplicationContext().registerReceiver(null, zza);
                if (registerReceiver != null) {
                    zzc = ((float) registerReceiver.getIntExtra("level", -1)) / ((float) registerReceiver.getIntExtra("scale", -1));
                }
                zzb = SystemClock.elapsedRealtime();
                f = zzc;
            } else {
                f = zzc;
            }
        }
        return f;
    }
}
