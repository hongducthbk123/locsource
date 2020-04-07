package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzdb;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbhf;

public final class zzb implements Runnable {
    private static final zzbhf zza = new zzbhf("RevokeAccessOperation", new String[0]);
    private final String zzb;
    private final zzdb zzc = new zzdb((GoogleApiClient) null);

    private zzb(String str) {
        zzbq.zza(str);
        this.zzb = str;
    }

    public static PendingResult<Status> zza(String str) {
        if (str == null) {
            return PendingResults.zza(new Status(4), (GoogleApiClient) null);
        }
        zzb zzb2 = new zzb(str);
        new Thread(zzb2).start();
        return zzb2.zzc;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00ac  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r7 = this;
            r5 = 0
            com.google.android.gms.common.api.Status r1 = com.google.android.gms.common.api.Status.zzc
            java.net.URL r2 = new java.net.URL     // Catch:{ IOException -> 0x005e, Exception -> 0x008e }
            java.lang.String r0 = "https://accounts.google.com/o/oauth2/revoke?token="
            java.lang.String r3 = java.lang.String.valueOf(r0)     // Catch:{ IOException -> 0x005e, Exception -> 0x008e }
            java.lang.String r0 = r7.zzb     // Catch:{ IOException -> 0x005e, Exception -> 0x008e }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ IOException -> 0x005e, Exception -> 0x008e }
            int r4 = r0.length()     // Catch:{ IOException -> 0x005e, Exception -> 0x008e }
            if (r4 == 0) goto L_0x0058
            java.lang.String r0 = r3.concat(r0)     // Catch:{ IOException -> 0x005e, Exception -> 0x008e }
        L_0x001b:
            r2.<init>(r0)     // Catch:{ IOException -> 0x005e, Exception -> 0x008e }
            java.net.URLConnection r0 = r2.openConnection()     // Catch:{ IOException -> 0x005e, Exception -> 0x008e }
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ IOException -> 0x005e, Exception -> 0x008e }
            java.lang.String r2 = "Content-Type"
            java.lang.String r3 = "application/x-www-form-urlencoded"
            r0.setRequestProperty(r2, r3)     // Catch:{ IOException -> 0x005e, Exception -> 0x008e }
            int r2 = r0.getResponseCode()     // Catch:{ IOException -> 0x005e, Exception -> 0x008e }
            r0 = 200(0xc8, float:2.8E-43)
            if (r2 != r0) goto L_0x007c
            com.google.android.gms.common.api.Status r0 = com.google.android.gms.common.api.Status.zza     // Catch:{ IOException -> 0x005e, Exception -> 0x008e }
        L_0x0035:
            com.google.android.gms.internal.zzbhf r1 = zza     // Catch:{ IOException -> 0x00b7, Exception -> 0x00b2 }
            r3 = 26
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00b7, Exception -> 0x00b2 }
            r4.<init>(r3)     // Catch:{ IOException -> 0x00b7, Exception -> 0x00b2 }
            java.lang.String r3 = "Response Code: "
            java.lang.StringBuilder r3 = r4.append(r3)     // Catch:{ IOException -> 0x00b7, Exception -> 0x00b2 }
            java.lang.StringBuilder r2 = r3.append(r2)     // Catch:{ IOException -> 0x00b7, Exception -> 0x00b2 }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x00b7, Exception -> 0x00b2 }
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ IOException -> 0x00b7, Exception -> 0x00b2 }
            r1.zzb(r2, r3)     // Catch:{ IOException -> 0x00b7, Exception -> 0x00b2 }
        L_0x0052:
            com.google.android.gms.common.api.internal.zzdb r1 = r7.zzc
            r1.zza(r0)
            return
        L_0x0058:
            java.lang.String r0 = new java.lang.String     // Catch:{ IOException -> 0x005e, Exception -> 0x008e }
            r0.<init>(r3)     // Catch:{ IOException -> 0x005e, Exception -> 0x008e }
            goto L_0x001b
        L_0x005e:
            r0 = move-exception
        L_0x005f:
            com.google.android.gms.internal.zzbhf r2 = zza
            java.lang.String r3 = "IOException when revoking access: "
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r4 = r0.length()
            if (r4 == 0) goto L_0x0088
            java.lang.String r0 = r3.concat(r0)
        L_0x0075:
            java.lang.Object[] r3 = new java.lang.Object[r5]
            r2.zze(r0, r3)
            r0 = r1
            goto L_0x0052
        L_0x007c:
            com.google.android.gms.internal.zzbhf r0 = zza     // Catch:{ IOException -> 0x005e, Exception -> 0x008e }
            java.lang.String r3 = "Unable to revoke access!"
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ IOException -> 0x005e, Exception -> 0x008e }
            r0.zze(r3, r4)     // Catch:{ IOException -> 0x005e, Exception -> 0x008e }
            r0 = r1
            goto L_0x0035
        L_0x0088:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r3)
            goto L_0x0075
        L_0x008e:
            r0 = move-exception
        L_0x008f:
            com.google.android.gms.internal.zzbhf r2 = zza
            java.lang.String r3 = "Exception when revoking access: "
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r4 = r0.length()
            if (r4 == 0) goto L_0x00ac
            java.lang.String r0 = r3.concat(r0)
        L_0x00a5:
            java.lang.Object[] r3 = new java.lang.Object[r5]
            r2.zze(r0, r3)
            r0 = r1
            goto L_0x0052
        L_0x00ac:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r3)
            goto L_0x00a5
        L_0x00b2:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L_0x008f
        L_0x00b7:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L_0x005f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.signin.internal.zzb.run():void");
    }
}
