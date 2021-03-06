package com.google.android.gms.auth.api.signin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.google.android.gms.auth.api.signin.internal.zzx;

public final class RevocationBoundService extends Service {
    public final IBinder onBind(Intent intent) {
        if ("com.google.android.gms.auth.api.signin.RevocationBoundService.disconnect".equals(intent.getAction()) || "com.google.android.gms.auth.api.signin.RevocationBoundService.clearClientState".equals(intent.getAction())) {
            if (Log.isLoggable("RevocationService", 2)) {
                String str = "RevocationService";
                String str2 = "RevocationBoundService handling ";
                String valueOf = String.valueOf(intent.getAction());
                Log.v(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            }
            return new zzx(this);
        }
        String str3 = "RevocationService";
        String str4 = "Unknown action sent to RevocationBoundService: ";
        String valueOf2 = String.valueOf(intent.getAction());
        Log.w(str3, valueOf2.length() != 0 ? str4.concat(valueOf2) : new String(str4));
        return null;
    }
}
