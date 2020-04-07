package com.google.android.gms.internal;

import com.google.android.gms.internal.zzfhu.zzg;
import java.io.IOException;

public final class zzfmg extends zzfhu<zzfmg, zza> implements zzfje {
    /* access modifiers changed from: private */
    public static final zzfmg zzh;
    private static volatile zzfjl<zzfmg> zzi;
    private int zzd;
    private int zze;
    private String zzf = "";
    private zzfid<zzfgp> zzg = zzu();

    public static final class zza extends com.google.android.gms.internal.zzfhu.zza<zzfmg, zza> implements zzfje {
        private zza() {
            super(zzfmg.zzh);
        }

        /* synthetic */ zza(zzfmh zzfmh) {
            this();
        }
    }

    static {
        zzfmg zzfmg = new zzfmg();
        zzh = zzfmg;
        zzfmg.zza(zzg.zzf, (Object) null, (Object) null);
        zzfmg.zzb.zzc();
    }

    private zzfmg() {
    }

    public static zzfmg zzd() {
        return zzh;
    }

    public final int zza() {
        int i = 0;
        int i2 = this.zzc;
        if (i2 != -1) {
            return i2;
        }
        int i3 = this.zze != 0 ? zzfhg.zze(1, this.zze) + 0 : 0;
        if (!this.zzf.isEmpty()) {
            i3 += zzfhg.zzb(2, this.zzf);
        }
        while (true) {
            int i4 = i3;
            if (i < this.zzg.size()) {
                i3 = zzfhg.zzc(3, (zzfjc) this.zzg.get(i)) + i4;
                i++;
            } else {
                int zze2 = this.zzb.zze() + i4;
                this.zzc = zze2;
                return zze2;
            }
        }
    }

    /* JADX WARNING: type inference failed for: r6v2, types: [java.lang.Byte] */
    /* JADX WARNING: type inference failed for: r6v3, types: [com.google.android.gms.internal.zzfjl<com.google.android.gms.internal.zzfmg>] */
    /* JADX WARNING: type inference failed for: r6v5, types: [com.google.android.gms.internal.zzfmg$zza] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: CFG modification limit reached, blocks count: 184 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object zza(int r7, java.lang.Object r8, java.lang.Object r9) {
        /*
            r6 = this;
            r0 = 0
            r2 = 0
            r1 = 1
            int[] r3 = com.google.android.gms.internal.zzfmh.zza
            int r4 = r7 + -1
            r3 = r3[r4]
            switch(r3) {
                case 1: goto L_0x0012;
                case 2: goto L_0x0018;
                case 3: goto L_0x001b;
                case 4: goto L_0x0022;
                case 5: goto L_0x0028;
                case 6: goto L_0x0079;
                case 7: goto L_0x00f2;
                case 8: goto L_0x00f6;
                case 9: goto L_0x0112;
                case 10: goto L_0x0118;
                default: goto L_0x000c;
            }
        L_0x000c:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>()
            throw r0
        L_0x0012:
            com.google.android.gms.internal.zzfmg r6 = new com.google.android.gms.internal.zzfmg
            r6.<init>()
        L_0x0017:
            return r6
        L_0x0018:
            com.google.android.gms.internal.zzfmg r6 = zzh
            goto L_0x0017
        L_0x001b:
            com.google.android.gms.internal.zzfid<com.google.android.gms.internal.zzfgp> r1 = r6.zzg
            r1.zzb()
            r6 = r0
            goto L_0x0017
        L_0x0022:
            com.google.android.gms.internal.zzfmg$zza r6 = new com.google.android.gms.internal.zzfmg$zza
            r6.<init>(r0)
            goto L_0x0017
        L_0x0028:
            com.google.android.gms.internal.zzfhu$zzh r8 = (com.google.android.gms.internal.zzfhu.zzh) r8
            com.google.android.gms.internal.zzfmg r9 = (com.google.android.gms.internal.zzfmg) r9
            int r0 = r6.zze
            if (r0 == 0) goto L_0x0071
            r0 = r1
        L_0x0031:
            int r4 = r6.zze
            int r3 = r9.zze
            if (r3 == 0) goto L_0x0073
            r3 = r1
        L_0x0038:
            int r5 = r9.zze
            int r0 = r8.zza(r0, r4, r3, r5)
            r6.zze = r0
            java.lang.String r0 = r6.zzf
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x0075
            r0 = r1
        L_0x0049:
            java.lang.String r3 = r6.zzf
            java.lang.String r4 = r9.zzf
            boolean r4 = r4.isEmpty()
            if (r4 != 0) goto L_0x0077
        L_0x0053:
            java.lang.String r2 = r9.zzf
            java.lang.String r0 = r8.zza(r0, r3, r1, r2)
            r6.zzf = r0
            com.google.android.gms.internal.zzfid<com.google.android.gms.internal.zzfgp> r0 = r6.zzg
            com.google.android.gms.internal.zzfid<com.google.android.gms.internal.zzfgp> r1 = r9.zzg
            com.google.android.gms.internal.zzfid r0 = r8.zza(r0, r1)
            r6.zzg = r0
            com.google.android.gms.internal.zzfhu$zzf r0 = com.google.android.gms.internal.zzfhu.zzf.zza
            if (r8 != r0) goto L_0x0017
            int r0 = r6.zzd
            int r1 = r9.zzd
            r0 = r0 | r1
            r6.zzd = r0
            goto L_0x0017
        L_0x0071:
            r0 = r2
            goto L_0x0031
        L_0x0073:
            r3 = r2
            goto L_0x0038
        L_0x0075:
            r0 = r2
            goto L_0x0049
        L_0x0077:
            r1 = r2
            goto L_0x0053
        L_0x0079:
            com.google.android.gms.internal.zzfhb r8 = (com.google.android.gms.internal.zzfhb) r8
            com.google.android.gms.internal.zzfhm r9 = (com.google.android.gms.internal.zzfhm) r9
            if (r9 != 0) goto L_0x0086
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            r0.<init>()
            throw r0
        L_0x0085:
            r2 = r1
        L_0x0086:
            if (r2 != 0) goto L_0x00f2
            int r0 = r8.zza()     // Catch:{ zzfie -> 0x009f, IOException -> 0x00b3 }
            switch(r0) {
                case 0: goto L_0x0085;
                case 8: goto L_0x0098;
                case 18: goto L_0x00ac;
                case 26: goto L_0x00c7;
                default: goto L_0x008f;
            }     // Catch:{ zzfie -> 0x009f, IOException -> 0x00b3 }
        L_0x008f:
            boolean r0 = r6.zza(r0, r8)     // Catch:{ zzfie -> 0x009f, IOException -> 0x00b3 }
            if (r0 != 0) goto L_0x011b
            r0 = r1
        L_0x0096:
            r2 = r0
            goto L_0x0086
        L_0x0098:
            int r0 = r8.zzf()     // Catch:{ zzfie -> 0x009f, IOException -> 0x00b3 }
            r6.zze = r0     // Catch:{ zzfie -> 0x009f, IOException -> 0x00b3 }
            goto L_0x0086
        L_0x009f:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ all -> 0x00aa }
            com.google.android.gms.internal.zzfie r0 = r0.zza(r6)     // Catch:{ all -> 0x00aa }
            r1.<init>(r0)     // Catch:{ all -> 0x00aa }
            throw r1     // Catch:{ all -> 0x00aa }
        L_0x00aa:
            r0 = move-exception
            throw r0
        L_0x00ac:
            java.lang.String r0 = r8.zzk()     // Catch:{ zzfie -> 0x009f, IOException -> 0x00b3 }
            r6.zzf = r0     // Catch:{ zzfie -> 0x009f, IOException -> 0x00b3 }
            goto L_0x0086
        L_0x00b3:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ all -> 0x00aa }
            com.google.android.gms.internal.zzfie r2 = new com.google.android.gms.internal.zzfie     // Catch:{ all -> 0x00aa }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x00aa }
            r2.<init>(r0)     // Catch:{ all -> 0x00aa }
            com.google.android.gms.internal.zzfie r0 = r2.zza(r6)     // Catch:{ all -> 0x00aa }
            r1.<init>(r0)     // Catch:{ all -> 0x00aa }
            throw r1     // Catch:{ all -> 0x00aa }
        L_0x00c7:
            com.google.android.gms.internal.zzfid<com.google.android.gms.internal.zzfgp> r0 = r6.zzg     // Catch:{ zzfie -> 0x009f, IOException -> 0x00b3 }
            boolean r0 = r0.zza()     // Catch:{ zzfie -> 0x009f, IOException -> 0x00b3 }
            if (r0 != 0) goto L_0x00df
            com.google.android.gms.internal.zzfid<com.google.android.gms.internal.zzfgp> r3 = r6.zzg     // Catch:{ zzfie -> 0x009f, IOException -> 0x00b3 }
            int r0 = r3.size()     // Catch:{ zzfie -> 0x009f, IOException -> 0x00b3 }
            if (r0 != 0) goto L_0x00ef
            r0 = 10
        L_0x00d9:
            com.google.android.gms.internal.zzfid r0 = r3.zzd(r0)     // Catch:{ zzfie -> 0x009f, IOException -> 0x00b3 }
            r6.zzg = r0     // Catch:{ zzfie -> 0x009f, IOException -> 0x00b3 }
        L_0x00df:
            com.google.android.gms.internal.zzfid<com.google.android.gms.internal.zzfgp> r3 = r6.zzg     // Catch:{ zzfie -> 0x009f, IOException -> 0x00b3 }
            com.google.android.gms.internal.zzfgp r0 = com.google.android.gms.internal.zzfgp.zzb()     // Catch:{ zzfie -> 0x009f, IOException -> 0x00b3 }
            com.google.android.gms.internal.zzfhu r0 = r8.zza(r0, r9)     // Catch:{ zzfie -> 0x009f, IOException -> 0x00b3 }
            com.google.android.gms.internal.zzfgp r0 = (com.google.android.gms.internal.zzfgp) r0     // Catch:{ zzfie -> 0x009f, IOException -> 0x00b3 }
            r3.add(r0)     // Catch:{ zzfie -> 0x009f, IOException -> 0x00b3 }
            goto L_0x0086
        L_0x00ef:
            int r0 = r0 << 1
            goto L_0x00d9
        L_0x00f2:
            com.google.android.gms.internal.zzfmg r6 = zzh
            goto L_0x0017
        L_0x00f6:
            com.google.android.gms.internal.zzfjl<com.google.android.gms.internal.zzfmg> r0 = zzi
            if (r0 != 0) goto L_0x010b
            java.lang.Class<com.google.android.gms.internal.zzfmg> r1 = com.google.android.gms.internal.zzfmg.class
            monitor-enter(r1)
            com.google.android.gms.internal.zzfjl<com.google.android.gms.internal.zzfmg> r0 = zzi     // Catch:{ all -> 0x010f }
            if (r0 != 0) goto L_0x010a
            com.google.android.gms.internal.zzfhu$zzb r0 = new com.google.android.gms.internal.zzfhu$zzb     // Catch:{ all -> 0x010f }
            com.google.android.gms.internal.zzfmg r2 = zzh     // Catch:{ all -> 0x010f }
            r0.<init>(r2)     // Catch:{ all -> 0x010f }
            zzi = r0     // Catch:{ all -> 0x010f }
        L_0x010a:
            monitor-exit(r1)     // Catch:{ all -> 0x010f }
        L_0x010b:
            com.google.android.gms.internal.zzfjl<com.google.android.gms.internal.zzfmg> r6 = zzi
            goto L_0x0017
        L_0x010f:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x010f }
            throw r0
        L_0x0112:
            java.lang.Byte r6 = java.lang.Byte.valueOf(r1)
            goto L_0x0017
        L_0x0118:
            r6 = r0
            goto L_0x0017
        L_0x011b:
            r0 = r2
            goto L_0x0096
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfmg.zza(int, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public final void zza(zzfhg zzfhg) throws IOException {
        if (this.zze != 0) {
            zzfhg.zzb(1, this.zze);
        }
        if (!this.zzf.isEmpty()) {
            zzfhg.zza(2, this.zzf);
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.zzg.size()) {
                zzfhg.zza(3, (zzfjc) this.zzg.get(i2));
                i = i2 + 1;
            } else {
                this.zzb.zza(zzfhg);
                return;
            }
        }
    }

    public final int zzb() {
        return this.zze;
    }

    public final String zzc() {
        return this.zzf;
    }
}
