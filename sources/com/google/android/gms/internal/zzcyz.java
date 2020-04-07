package com.google.android.gms.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.WorkSource;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzaa;
import com.google.android.gms.common.util.zzw;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

@Hide
public final class zzcyz {
    private static String zza = "WakeLock";
    private static String zzb = "*gcore*:";
    private static boolean zzc = false;
    private static ScheduledExecutorService zzo;
    private final WakeLock zzd;
    private WorkSource zze;
    private final int zzf;
    private final String zzg;
    private final String zzh;
    private final String zzi;
    private final Context zzj;
    private boolean zzk;
    private final Map<String, Integer[]> zzl;
    private int zzm;
    private AtomicInteger zzn;

    public zzcyz(Context context, int i, String str) {
        this(context, 1, str, null, context == null ? null : context.getPackageName());
    }

    @Hide
    @SuppressLint({"UnwrappedWakeLock"})
    private zzcyz(Context context, int i, String str, String str2, String str3) {
        this(context, 1, str, null, str3, null);
    }

    @Hide
    @SuppressLint({"UnwrappedWakeLock"})
    private zzcyz(Context context, int i, String str, String str2, String str3, String str4) {
        this.zzk = true;
        this.zzl = new HashMap();
        this.zzn = new AtomicInteger(0);
        zzbq.zza(str, (Object) "Wake lock name can NOT be empty");
        this.zzf = i;
        this.zzh = null;
        this.zzi = null;
        this.zzj = context.getApplicationContext();
        if (!"com.google.android.gms".equals(context.getPackageName())) {
            String valueOf = String.valueOf(zzb);
            String valueOf2 = String.valueOf(str);
            this.zzg = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        } else {
            this.zzg = str;
        }
        this.zzd = ((PowerManager) context.getSystemService("power")).newWakeLock(i, str);
        if (zzaa.zza(this.zzj)) {
            if (zzw.zza(str3)) {
                str3 = context.getPackageName();
            }
            this.zze = zzaa.zza(context, str3);
            WorkSource workSource = this.zze;
            if (workSource != null && zzaa.zza(this.zzj)) {
                if (this.zze != null) {
                    this.zze.add(workSource);
                } else {
                    this.zze = workSource;
                }
                try {
                    this.zzd.setWorkSource(this.zze);
                } catch (IllegalArgumentException e) {
                    Log.wtf(zza, e.toString());
                }
            }
        }
        if (zzo == null) {
            zzo = zzbhg.zza().zza();
        }
    }

    private final String zza(String str) {
        return this.zzk ? !TextUtils.isEmpty(str) ? str : this.zzh : this.zzh;
    }

    /* access modifiers changed from: private */
    public final void zza(int i) {
        if (this.zzd.isHeld()) {
            try {
                this.zzd.release();
            } catch (RuntimeException e) {
                if (e.getClass().equals(RuntimeException.class)) {
                    Log.e(zza, String.valueOf(this.zzg).concat("was already released!"), new IllegalStateException());
                    return;
                }
                throw e;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0026, code lost:
        if (r0 == false) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002e, code lost:
        if (r9.zzm == 1) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0030, code lost:
        com.google.android.gms.common.stats.zze.zza();
        com.google.android.gms.common.stats.zze.zza(r9.zzj, com.google.android.gms.common.stats.zzc.zza(r9.zzd, r4), 8, r9.zzg, r4, null, r9.zzf, com.google.android.gms.common.util.zzaa.zza(r9.zze));
        r9.zzm--;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza() {
        /*
            r9 = this;
            r3 = 0
            r1 = 1
            r8 = 0
            java.util.concurrent.atomic.AtomicInteger r0 = r9.zzn
            int r0 = r0.decrementAndGet()
            if (r0 >= 0) goto L_0x0012
            java.lang.String r0 = zza
            java.lang.String r2 = "release without a matched acquire!"
            android.util.Log.e(r0, r2)
        L_0x0012:
            java.lang.String r4 = r9.zza(r3)
            monitor-enter(r9)
            boolean r0 = r9.zzk     // Catch:{ all -> 0x0078 }
            if (r0 == 0) goto L_0x0028
            java.util.Map<java.lang.String, java.lang.Integer[]> r0 = r9.zzl     // Catch:{ all -> 0x0078 }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ all -> 0x0078 }
            java.lang.Integer[] r0 = (java.lang.Integer[]) r0     // Catch:{ all -> 0x0078 }
            if (r0 != 0) goto L_0x0056
            r0 = r8
        L_0x0026:
            if (r0 != 0) goto L_0x0030
        L_0x0028:
            boolean r0 = r9.zzk     // Catch:{ all -> 0x0078 }
            if (r0 != 0) goto L_0x0051
            int r0 = r9.zzm     // Catch:{ all -> 0x0078 }
            if (r0 != r1) goto L_0x0051
        L_0x0030:
            com.google.android.gms.common.stats.zze.zza()     // Catch:{ all -> 0x0078 }
            android.content.Context r0 = r9.zzj     // Catch:{ all -> 0x0078 }
            android.os.PowerManager$WakeLock r1 = r9.zzd     // Catch:{ all -> 0x0078 }
            java.lang.String r1 = com.google.android.gms.common.stats.zzc.zza(r1, r4)     // Catch:{ all -> 0x0078 }
            r2 = 8
            java.lang.String r3 = r9.zzg     // Catch:{ all -> 0x0078 }
            r5 = 0
            int r6 = r9.zzf     // Catch:{ all -> 0x0078 }
            android.os.WorkSource r7 = r9.zze     // Catch:{ all -> 0x0078 }
            java.util.List r7 = com.google.android.gms.common.util.zzaa.zza(r7)     // Catch:{ all -> 0x0078 }
            com.google.android.gms.common.stats.zze.zza(r0, r1, r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0078 }
            int r0 = r9.zzm     // Catch:{ all -> 0x0078 }
            int r0 = r0 + -1
            r9.zzm = r0     // Catch:{ all -> 0x0078 }
        L_0x0051:
            monitor-exit(r9)     // Catch:{ all -> 0x0078 }
            r9.zza(r8)
            return
        L_0x0056:
            r2 = 0
            r2 = r0[r2]     // Catch:{ all -> 0x0078 }
            int r2 = r2.intValue()     // Catch:{ all -> 0x0078 }
            if (r2 != r1) goto L_0x0066
            java.util.Map<java.lang.String, java.lang.Integer[]> r0 = r9.zzl     // Catch:{ all -> 0x0078 }
            r0.remove(r4)     // Catch:{ all -> 0x0078 }
            r0 = r1
            goto L_0x0026
        L_0x0066:
            r2 = 0
            r3 = 0
            r3 = r0[r3]     // Catch:{ all -> 0x0078 }
            int r3 = r3.intValue()     // Catch:{ all -> 0x0078 }
            int r3 = r3 + -1
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0078 }
            r0[r2] = r3     // Catch:{ all -> 0x0078 }
            r0 = r8
            goto L_0x0026
        L_0x0078:
            r0 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x0078 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcyz.zza():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004a, code lost:
        if (r0 == false) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0052, code lost:
        if (r12.zzm == 0) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0054, code lost:
        com.google.android.gms.common.stats.zze.zza();
        com.google.android.gms.common.stats.zze.zza(r12.zzj, com.google.android.gms.common.stats.zzc.zza(r12.zzd, r4), 7, r12.zzg, r4, null, r12.zzf, com.google.android.gms.common.util.zzaa.zza(r12.zze), 1000);
        r12.zzm++;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(long r13) {
        /*
            r12 = this;
            r3 = 0
            r10 = 1000(0x3e8, double:4.94E-321)
            r1 = 1
            r2 = 0
            java.util.concurrent.atomic.AtomicInteger r0 = r12.zzn
            r0.incrementAndGet()
            java.lang.String r4 = r12.zza(r3)
            monitor-enter(r12)
            java.util.Map<java.lang.String, java.lang.Integer[]> r0 = r12.zzl     // Catch:{ all -> 0x00a1 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x00a1 }
            if (r0 == 0) goto L_0x001b
            int r0 = r12.zzm     // Catch:{ all -> 0x00a1 }
            if (r0 <= 0) goto L_0x002b
        L_0x001b:
            android.os.PowerManager$WakeLock r0 = r12.zzd     // Catch:{ all -> 0x00a1 }
            boolean r0 = r0.isHeld()     // Catch:{ all -> 0x00a1 }
            if (r0 != 0) goto L_0x002b
            java.util.Map<java.lang.String, java.lang.Integer[]> r0 = r12.zzl     // Catch:{ all -> 0x00a1 }
            r0.clear()     // Catch:{ all -> 0x00a1 }
            r0 = 0
            r12.zzm = r0     // Catch:{ all -> 0x00a1 }
        L_0x002b:
            boolean r0 = r12.zzk     // Catch:{ all -> 0x00a1 }
            if (r0 == 0) goto L_0x004c
            java.util.Map<java.lang.String, java.lang.Integer[]> r0 = r12.zzl     // Catch:{ all -> 0x00a1 }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ all -> 0x00a1 }
            java.lang.Integer[] r0 = (java.lang.Integer[]) r0     // Catch:{ all -> 0x00a1 }
            if (r0 != 0) goto L_0x008f
            java.util.Map<java.lang.String, java.lang.Integer[]> r0 = r12.zzl     // Catch:{ all -> 0x00a1 }
            r2 = 1
            java.lang.Integer[] r2 = new java.lang.Integer[r2]     // Catch:{ all -> 0x00a1 }
            r3 = 0
            r5 = 1
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x00a1 }
            r2[r3] = r5     // Catch:{ all -> 0x00a1 }
            r0.put(r4, r2)     // Catch:{ all -> 0x00a1 }
            r0 = r1
        L_0x004a:
            if (r0 != 0) goto L_0x0054
        L_0x004c:
            boolean r0 = r12.zzk     // Catch:{ all -> 0x00a1 }
            if (r0 != 0) goto L_0x0076
            int r0 = r12.zzm     // Catch:{ all -> 0x00a1 }
            if (r0 != 0) goto L_0x0076
        L_0x0054:
            com.google.android.gms.common.stats.zze.zza()     // Catch:{ all -> 0x00a1 }
            android.content.Context r0 = r12.zzj     // Catch:{ all -> 0x00a1 }
            android.os.PowerManager$WakeLock r1 = r12.zzd     // Catch:{ all -> 0x00a1 }
            java.lang.String r1 = com.google.android.gms.common.stats.zzc.zza(r1, r4)     // Catch:{ all -> 0x00a1 }
            r2 = 7
            java.lang.String r3 = r12.zzg     // Catch:{ all -> 0x00a1 }
            r5 = 0
            int r6 = r12.zzf     // Catch:{ all -> 0x00a1 }
            android.os.WorkSource r7 = r12.zze     // Catch:{ all -> 0x00a1 }
            java.util.List r7 = com.google.android.gms.common.util.zzaa.zza(r7)     // Catch:{ all -> 0x00a1 }
            r8 = 1000(0x3e8, double:4.94E-321)
            com.google.android.gms.common.stats.zze.zza(r0, r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x00a1 }
            int r0 = r12.zzm     // Catch:{ all -> 0x00a1 }
            int r0 = r0 + 1
            r12.zzm = r0     // Catch:{ all -> 0x00a1 }
        L_0x0076:
            monitor-exit(r12)     // Catch:{ all -> 0x00a1 }
            android.os.PowerManager$WakeLock r0 = r12.zzd
            r0.acquire()
            r0 = 0
            int r0 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x008e
            java.util.concurrent.ScheduledExecutorService r0 = zzo
            com.google.android.gms.internal.zzcza r1 = new com.google.android.gms.internal.zzcza
            r1.<init>(r12)
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.MILLISECONDS
            r0.schedule(r1, r10, r2)
        L_0x008e:
            return
        L_0x008f:
            r1 = 0
            r3 = 0
            r3 = r0[r3]     // Catch:{ all -> 0x00a1 }
            int r3 = r3.intValue()     // Catch:{ all -> 0x00a1 }
            int r3 = r3 + 1
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x00a1 }
            r0[r1] = r3     // Catch:{ all -> 0x00a1 }
            r0 = r2
            goto L_0x004a
        L_0x00a1:
            r0 = move-exception
            monitor-exit(r12)     // Catch:{ all -> 0x00a1 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcyz.zza(long):void");
    }

    public final void zza(boolean z) {
        this.zzd.setReferenceCounted(false);
        this.zzk = false;
    }

    public final boolean zzb() {
        return this.zzd.isHeld();
    }
}
