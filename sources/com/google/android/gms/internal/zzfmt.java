package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfmt extends zzflm<zzfmt> implements Cloneable {
    private int zza;
    private int zzb;

    public zzfmt() {
        this.zza = -1;
        this.zzb = 0;
        this.zzax = null;
        this.zzay = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public zzfmt clone() {
        try {
            return (zzfmt) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzfmt zza(com.google.android.gms.internal.zzflj r7) throws java.io.IOException {
        /*
            r6 = this;
        L_0x0000:
            int r0 = r7.zza()
            switch(r0) {
                case 0: goto L_0x000d;
                case 8: goto L_0x000e;
                case 16: goto L_0x003f;
                default: goto L_0x0007;
            }
        L_0x0007:
            boolean r0 = super.zza(r7, r0)
            if (r0 != 0) goto L_0x0000
        L_0x000d:
            return r6
        L_0x000e:
            int r1 = r7.zzm()
            int r2 = r7.zzc()     // Catch:{ IllegalArgumentException -> 0x0034 }
            switch(r2) {
                case -1: goto L_0x003c;
                case 0: goto L_0x003c;
                case 1: goto L_0x003c;
                case 2: goto L_0x003c;
                case 3: goto L_0x003c;
                case 4: goto L_0x003c;
                case 5: goto L_0x003c;
                case 6: goto L_0x003c;
                case 7: goto L_0x003c;
                case 8: goto L_0x003c;
                case 9: goto L_0x003c;
                case 10: goto L_0x003c;
                case 11: goto L_0x003c;
                case 12: goto L_0x003c;
                case 13: goto L_0x003c;
                case 14: goto L_0x003c;
                case 15: goto L_0x003c;
                case 16: goto L_0x003c;
                case 17: goto L_0x003c;
                default: goto L_0x0019;
            }     // Catch:{ IllegalArgumentException -> 0x0034 }
        L_0x0019:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x0034 }
            r4 = 43
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0034 }
            r5.<init>(r4)     // Catch:{ IllegalArgumentException -> 0x0034 }
            java.lang.StringBuilder r2 = r5.append(r2)     // Catch:{ IllegalArgumentException -> 0x0034 }
            java.lang.String r4 = " is not a valid enum NetworkType"
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch:{ IllegalArgumentException -> 0x0034 }
            java.lang.String r2 = r2.toString()     // Catch:{ IllegalArgumentException -> 0x0034 }
            r3.<init>(r2)     // Catch:{ IllegalArgumentException -> 0x0034 }
            throw r3     // Catch:{ IllegalArgumentException -> 0x0034 }
        L_0x0034:
            r2 = move-exception
            r7.zze(r1)
            r6.zza(r7, r0)
            goto L_0x0000
        L_0x003c:
            r6.zza = r2     // Catch:{ IllegalArgumentException -> 0x0034 }
            goto L_0x0000
        L_0x003f:
            int r1 = r7.zzm()
            int r2 = r7.zzc()     // Catch:{ IllegalArgumentException -> 0x0065 }
            switch(r2) {
                case 0: goto L_0x006d;
                case 1: goto L_0x006d;
                case 2: goto L_0x006d;
                case 3: goto L_0x006d;
                case 4: goto L_0x006d;
                case 5: goto L_0x006d;
                case 6: goto L_0x006d;
                case 7: goto L_0x006d;
                case 8: goto L_0x006d;
                case 9: goto L_0x006d;
                case 10: goto L_0x006d;
                case 11: goto L_0x006d;
                case 12: goto L_0x006d;
                case 13: goto L_0x006d;
                case 14: goto L_0x006d;
                case 15: goto L_0x006d;
                case 16: goto L_0x006d;
                case 100: goto L_0x006d;
                default: goto L_0x004a;
            }     // Catch:{ IllegalArgumentException -> 0x0065 }
        L_0x004a:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x0065 }
            r4 = 45
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0065 }
            r5.<init>(r4)     // Catch:{ IllegalArgumentException -> 0x0065 }
            java.lang.StringBuilder r2 = r5.append(r2)     // Catch:{ IllegalArgumentException -> 0x0065 }
            java.lang.String r4 = " is not a valid enum MobileSubtype"
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch:{ IllegalArgumentException -> 0x0065 }
            java.lang.String r2 = r2.toString()     // Catch:{ IllegalArgumentException -> 0x0065 }
            r3.<init>(r2)     // Catch:{ IllegalArgumentException -> 0x0065 }
            throw r3     // Catch:{ IllegalArgumentException -> 0x0065 }
        L_0x0065:
            r2 = move-exception
            r7.zze(r1)
            r6.zza(r7, r0)
            goto L_0x0000
        L_0x006d:
            r6.zzb = r2     // Catch:{ IllegalArgumentException -> 0x0065 }
            goto L_0x0000
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfmt.zza(com.google.android.gms.internal.zzflj):com.google.android.gms.internal.zzfmt");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfmt)) {
            return false;
        }
        zzfmt zzfmt = (zzfmt) obj;
        if (this.zza != zzfmt.zza) {
            return false;
        }
        if (this.zzb != zzfmt.zzb) {
            return false;
        }
        return (this.zzax == null || this.zzax.zzb()) ? zzfmt.zzax == null || zzfmt.zzax.zzb() : this.zzax.equals(zzfmt.zzax);
    }

    public final int hashCode() {
        return ((this.zzax == null || this.zzax.zzb()) ? 0 : this.zzax.hashCode()) + ((((((getClass().getName().hashCode() + 527) * 31) + this.zza) * 31) + this.zzb) * 31);
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        int zza2 = super.zza();
        if (this.zza != -1) {
            zza2 += zzflk.zzb(1, this.zza);
        }
        return this.zzb != 0 ? zza2 + zzflk.zzb(2, this.zzb) : zza2;
    }

    public final void zza(zzflk zzflk) throws IOException {
        if (this.zza != -1) {
            zzflk.zza(1, this.zza);
        }
        if (this.zzb != 0) {
            zzflk.zza(2, this.zzb);
        }
        super.zza(zzflk);
    }

    public final /* synthetic */ zzflm zzc() throws CloneNotSupportedException {
        return (zzfmt) clone();
    }

    public final /* synthetic */ zzfls zzd() throws CloneNotSupportedException {
        return (zzfmt) clone();
    }
}
