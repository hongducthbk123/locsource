package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

public final class zzfmr extends zzflm<zzfmr> implements Cloneable {
    public long zza;
    public long zzb;
    public byte[] zzc;
    public long zzd;
    public byte[] zze;
    private long zzf;
    private String zzg;
    private int zzh;
    private int zzi;
    private boolean zzj;
    private zzfms[] zzk;
    private byte[] zzl;
    private zzfmp zzm;
    private String zzn;
    private String zzo;
    private zzfmo zzp;
    private String zzq;
    private zzfmq zzr;
    private String zzs;
    private int zzt;
    private int[] zzu;
    private long zzv;
    private zzfmt zzw;
    private boolean zzx;

    public zzfmr() {
        this.zza = 0;
        this.zzb = 0;
        this.zzf = 0;
        this.zzg = "";
        this.zzh = 0;
        this.zzi = 0;
        this.zzj = false;
        this.zzk = zzfms.zzb();
        this.zzl = zzflv.zzh;
        this.zzm = null;
        this.zzc = zzflv.zzh;
        this.zzn = "";
        this.zzo = "";
        this.zzp = null;
        this.zzq = "";
        this.zzd = 180000;
        this.zzr = null;
        this.zze = zzflv.zzh;
        this.zzs = "";
        this.zzt = 0;
        this.zzu = zzflv.zza;
        this.zzv = 0;
        this.zzw = null;
        this.zzx = false;
        this.zzax = null;
        this.zzay = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public final zzfmr clone() {
        try {
            zzfmr zzfmr = (zzfmr) super.clone();
            if (this.zzk != null && this.zzk.length > 0) {
                zzfmr.zzk = new zzfms[this.zzk.length];
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= this.zzk.length) {
                        break;
                    }
                    if (this.zzk[i2] != null) {
                        zzfmr.zzk[i2] = (zzfms) this.zzk[i2].clone();
                    }
                    i = i2 + 1;
                }
            }
            if (this.zzm != null) {
                zzfmr.zzm = (zzfmp) this.zzm.clone();
            }
            if (this.zzp != null) {
                zzfmr.zzp = (zzfmo) this.zzp.clone();
            }
            if (this.zzr != null) {
                zzfmr.zzr = (zzfmq) this.zzr.clone();
            }
            if (this.zzu != null && this.zzu.length > 0) {
                zzfmr.zzu = (int[]) this.zzu.clone();
            }
            if (this.zzw != null) {
                zzfmr.zzw = (zzfmt) this.zzw.clone();
            }
            return zzfmr;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzfmr zza(com.google.android.gms.internal.zzflj r8) throws java.io.IOException {
        /*
            r7 = this;
            r1 = 0
        L_0x0001:
            int r0 = r8.zza()
            switch(r0) {
                case 0: goto L_0x000e;
                case 8: goto L_0x000f;
                case 18: goto L_0x0016;
                case 26: goto L_0x001d;
                case 34: goto L_0x005c;
                case 50: goto L_0x0063;
                case 58: goto L_0x006a;
                case 66: goto L_0x007b;
                case 74: goto L_0x0082;
                case 80: goto L_0x0094;
                case 88: goto L_0x009c;
                case 96: goto L_0x00a4;
                case 106: goto L_0x00ac;
                case 114: goto L_0x00b4;
                case 120: goto L_0x00bc;
                case 130: goto L_0x00c4;
                case 136: goto L_0x00d6;
                case 146: goto L_0x00de;
                case 152: goto L_0x00e6;
                case 160: goto L_0x0119;
                case 162: goto L_0x014d;
                case 168: goto L_0x018f;
                case 176: goto L_0x0197;
                case 186: goto L_0x019f;
                case 194: goto L_0x01b1;
                case 200: goto L_0x01b9;
                default: goto L_0x0008;
            }
        L_0x0008:
            boolean r0 = super.zza(r8, r0)
            if (r0 != 0) goto L_0x0001
        L_0x000e:
            return r7
        L_0x000f:
            long r2 = r8.zzb()
            r7.zza = r2
            goto L_0x0001
        L_0x0016:
            java.lang.String r0 = r8.zze()
            r7.zzg = r0
            goto L_0x0001
        L_0x001d:
            r0 = 26
            int r2 = com.google.android.gms.internal.zzflv.zza(r8, r0)
            com.google.android.gms.internal.zzfms[] r0 = r7.zzk
            if (r0 != 0) goto L_0x0049
            r0 = r1
        L_0x0028:
            int r2 = r2 + r0
            com.google.android.gms.internal.zzfms[] r2 = new com.google.android.gms.internal.zzfms[r2]
            if (r0 == 0) goto L_0x0032
            com.google.android.gms.internal.zzfms[] r3 = r7.zzk
            java.lang.System.arraycopy(r3, r1, r2, r1, r0)
        L_0x0032:
            int r3 = r2.length
            int r3 = r3 + -1
            if (r0 >= r3) goto L_0x004d
            com.google.android.gms.internal.zzfms r3 = new com.google.android.gms.internal.zzfms
            r3.<init>()
            r2[r0] = r3
            r3 = r2[r0]
            r8.zza(r3)
            r8.zza()
            int r0 = r0 + 1
            goto L_0x0032
        L_0x0049:
            com.google.android.gms.internal.zzfms[] r0 = r7.zzk
            int r0 = r0.length
            goto L_0x0028
        L_0x004d:
            com.google.android.gms.internal.zzfms r3 = new com.google.android.gms.internal.zzfms
            r3.<init>()
            r2[r0] = r3
            r0 = r2[r0]
            r8.zza(r0)
            r7.zzk = r2
            goto L_0x0001
        L_0x005c:
            byte[] r0 = r8.zzf()
            r7.zzl = r0
            goto L_0x0001
        L_0x0063:
            byte[] r0 = r8.zzf()
            r7.zzc = r0
            goto L_0x0001
        L_0x006a:
            com.google.android.gms.internal.zzfmo r0 = r7.zzp
            if (r0 != 0) goto L_0x0075
            com.google.android.gms.internal.zzfmo r0 = new com.google.android.gms.internal.zzfmo
            r0.<init>()
            r7.zzp = r0
        L_0x0075:
            com.google.android.gms.internal.zzfmo r0 = r7.zzp
            r8.zza(r0)
            goto L_0x0001
        L_0x007b:
            java.lang.String r0 = r8.zze()
            r7.zzn = r0
            goto L_0x0001
        L_0x0082:
            com.google.android.gms.internal.zzfmp r0 = r7.zzm
            if (r0 != 0) goto L_0x008d
            com.google.android.gms.internal.zzfmp r0 = new com.google.android.gms.internal.zzfmp
            r0.<init>()
            r7.zzm = r0
        L_0x008d:
            com.google.android.gms.internal.zzfmp r0 = r7.zzm
            r8.zza(r0)
            goto L_0x0001
        L_0x0094:
            boolean r0 = r8.zzd()
            r7.zzj = r0
            goto L_0x0001
        L_0x009c:
            int r0 = r8.zzc()
            r7.zzh = r0
            goto L_0x0001
        L_0x00a4:
            int r0 = r8.zzc()
            r7.zzi = r0
            goto L_0x0001
        L_0x00ac:
            java.lang.String r0 = r8.zze()
            r7.zzo = r0
            goto L_0x0001
        L_0x00b4:
            java.lang.String r0 = r8.zze()
            r7.zzq = r0
            goto L_0x0001
        L_0x00bc:
            long r2 = r8.zzg()
            r7.zzd = r2
            goto L_0x0001
        L_0x00c4:
            com.google.android.gms.internal.zzfmq r0 = r7.zzr
            if (r0 != 0) goto L_0x00cf
            com.google.android.gms.internal.zzfmq r0 = new com.google.android.gms.internal.zzfmq
            r0.<init>()
            r7.zzr = r0
        L_0x00cf:
            com.google.android.gms.internal.zzfmq r0 = r7.zzr
            r8.zza(r0)
            goto L_0x0001
        L_0x00d6:
            long r2 = r8.zzb()
            r7.zzb = r2
            goto L_0x0001
        L_0x00de:
            byte[] r0 = r8.zzf()
            r7.zze = r0
            goto L_0x0001
        L_0x00e6:
            int r2 = r8.zzm()
            int r3 = r8.zzc()     // Catch:{ IllegalArgumentException -> 0x010c }
            switch(r3) {
                case 0: goto L_0x0115;
                case 1: goto L_0x0115;
                case 2: goto L_0x0115;
                default: goto L_0x00f1;
            }     // Catch:{ IllegalArgumentException -> 0x010c }
        L_0x00f1:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x010c }
            r5 = 45
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x010c }
            r6.<init>(r5)     // Catch:{ IllegalArgumentException -> 0x010c }
            java.lang.StringBuilder r3 = r6.append(r3)     // Catch:{ IllegalArgumentException -> 0x010c }
            java.lang.String r5 = " is not a valid enum InternalEvent"
            java.lang.StringBuilder r3 = r3.append(r5)     // Catch:{ IllegalArgumentException -> 0x010c }
            java.lang.String r3 = r3.toString()     // Catch:{ IllegalArgumentException -> 0x010c }
            r4.<init>(r3)     // Catch:{ IllegalArgumentException -> 0x010c }
            throw r4     // Catch:{ IllegalArgumentException -> 0x010c }
        L_0x010c:
            r3 = move-exception
            r8.zze(r2)
            r7.zza(r8, r0)
            goto L_0x0001
        L_0x0115:
            r7.zzt = r3     // Catch:{ IllegalArgumentException -> 0x010c }
            goto L_0x0001
        L_0x0119:
            r0 = 160(0xa0, float:2.24E-43)
            int r2 = com.google.android.gms.internal.zzflv.zza(r8, r0)
            int[] r0 = r7.zzu
            if (r0 != 0) goto L_0x013f
            r0 = r1
        L_0x0124:
            int r2 = r2 + r0
            int[] r2 = new int[r2]
            if (r0 == 0) goto L_0x012e
            int[] r3 = r7.zzu
            java.lang.System.arraycopy(r3, r1, r2, r1, r0)
        L_0x012e:
            int r3 = r2.length
            int r3 = r3 + -1
            if (r0 >= r3) goto L_0x0143
            int r3 = r8.zzc()
            r2[r0] = r3
            r8.zza()
            int r0 = r0 + 1
            goto L_0x012e
        L_0x013f:
            int[] r0 = r7.zzu
            int r0 = r0.length
            goto L_0x0124
        L_0x0143:
            int r3 = r8.zzc()
            r2[r0] = r3
            r7.zzu = r2
            goto L_0x0001
        L_0x014d:
            int r0 = r8.zzh()
            int r3 = r8.zzc(r0)
            int r2 = r8.zzm()
            r0 = r1
        L_0x015a:
            int r4 = r8.zzl()
            if (r4 <= 0) goto L_0x0166
            r8.zzc()
            int r0 = r0 + 1
            goto L_0x015a
        L_0x0166:
            r8.zze(r2)
            int[] r2 = r7.zzu
            if (r2 != 0) goto L_0x0184
            r2 = r1
        L_0x016e:
            int r0 = r0 + r2
            int[] r0 = new int[r0]
            if (r2 == 0) goto L_0x0178
            int[] r4 = r7.zzu
            java.lang.System.arraycopy(r4, r1, r0, r1, r2)
        L_0x0178:
            int r4 = r0.length
            if (r2 >= r4) goto L_0x0188
            int r4 = r8.zzc()
            r0[r2] = r4
            int r2 = r2 + 1
            goto L_0x0178
        L_0x0184:
            int[] r2 = r7.zzu
            int r2 = r2.length
            goto L_0x016e
        L_0x0188:
            r7.zzu = r0
            r8.zzd(r3)
            goto L_0x0001
        L_0x018f:
            long r2 = r8.zzb()
            r7.zzf = r2
            goto L_0x0001
        L_0x0197:
            long r2 = r8.zzb()
            r7.zzv = r2
            goto L_0x0001
        L_0x019f:
            com.google.android.gms.internal.zzfmt r0 = r7.zzw
            if (r0 != 0) goto L_0x01aa
            com.google.android.gms.internal.zzfmt r0 = new com.google.android.gms.internal.zzfmt
            r0.<init>()
            r7.zzw = r0
        L_0x01aa:
            com.google.android.gms.internal.zzfmt r0 = r7.zzw
            r8.zza(r0)
            goto L_0x0001
        L_0x01b1:
            java.lang.String r0 = r8.zze()
            r7.zzs = r0
            goto L_0x0001
        L_0x01b9:
            boolean r0 = r8.zzd()
            r7.zzx = r0
            goto L_0x0001
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfmr.zza(com.google.android.gms.internal.zzflj):com.google.android.gms.internal.zzfmr");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfmr)) {
            return false;
        }
        zzfmr zzfmr = (zzfmr) obj;
        if (this.zza != zzfmr.zza) {
            return false;
        }
        if (this.zzb != zzfmr.zzb) {
            return false;
        }
        if (this.zzf != zzfmr.zzf) {
            return false;
        }
        if (this.zzg == null) {
            if (zzfmr.zzg != null) {
                return false;
            }
        } else if (!this.zzg.equals(zzfmr.zzg)) {
            return false;
        }
        if (this.zzh != zzfmr.zzh) {
            return false;
        }
        if (this.zzi != zzfmr.zzi) {
            return false;
        }
        if (this.zzj != zzfmr.zzj) {
            return false;
        }
        if (!zzflq.zza((Object[]) this.zzk, (Object[]) zzfmr.zzk)) {
            return false;
        }
        if (!Arrays.equals(this.zzl, zzfmr.zzl)) {
            return false;
        }
        if (this.zzm == null) {
            if (zzfmr.zzm != null) {
                return false;
            }
        } else if (!this.zzm.equals(zzfmr.zzm)) {
            return false;
        }
        if (!Arrays.equals(this.zzc, zzfmr.zzc)) {
            return false;
        }
        if (this.zzn == null) {
            if (zzfmr.zzn != null) {
                return false;
            }
        } else if (!this.zzn.equals(zzfmr.zzn)) {
            return false;
        }
        if (this.zzo == null) {
            if (zzfmr.zzo != null) {
                return false;
            }
        } else if (!this.zzo.equals(zzfmr.zzo)) {
            return false;
        }
        if (this.zzp == null) {
            if (zzfmr.zzp != null) {
                return false;
            }
        } else if (!this.zzp.equals(zzfmr.zzp)) {
            return false;
        }
        if (this.zzq == null) {
            if (zzfmr.zzq != null) {
                return false;
            }
        } else if (!this.zzq.equals(zzfmr.zzq)) {
            return false;
        }
        if (this.zzd != zzfmr.zzd) {
            return false;
        }
        if (this.zzr == null) {
            if (zzfmr.zzr != null) {
                return false;
            }
        } else if (!this.zzr.equals(zzfmr.zzr)) {
            return false;
        }
        if (!Arrays.equals(this.zze, zzfmr.zze)) {
            return false;
        }
        if (this.zzs == null) {
            if (zzfmr.zzs != null) {
                return false;
            }
        } else if (!this.zzs.equals(zzfmr.zzs)) {
            return false;
        }
        if (this.zzt != zzfmr.zzt) {
            return false;
        }
        if (!zzflq.zza(this.zzu, zzfmr.zzu)) {
            return false;
        }
        if (this.zzv != zzfmr.zzv) {
            return false;
        }
        if (this.zzw == null) {
            if (zzfmr.zzw != null) {
                return false;
            }
        } else if (!this.zzw.equals(zzfmr.zzw)) {
            return false;
        }
        if (this.zzx != zzfmr.zzx) {
            return false;
        }
        return (this.zzax == null || this.zzax.zzb()) ? zzfmr.zzax == null || zzfmr.zzax.zzb() : this.zzax.equals(zzfmr.zzax);
    }

    public final int hashCode() {
        int i = 1231;
        int i2 = 0;
        int hashCode = (((((this.zzj ? 1231 : 1237) + (((((((this.zzg == null ? 0 : this.zzg.hashCode()) + ((((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.zza ^ (this.zza >>> 32)))) * 31) + ((int) (this.zzb ^ (this.zzb >>> 32)))) * 31) + ((int) (this.zzf ^ (this.zzf >>> 32)))) * 31)) * 31) + this.zzh) * 31) + this.zzi) * 31)) * 31) + zzflq.zza((Object[]) this.zzk)) * 31) + Arrays.hashCode(this.zzl);
        zzfmp zzfmp = this.zzm;
        int hashCode2 = (this.zzo == null ? 0 : this.zzo.hashCode()) + (((this.zzn == null ? 0 : this.zzn.hashCode()) + (((((zzfmp == null ? 0 : zzfmp.hashCode()) + (hashCode * 31)) * 31) + Arrays.hashCode(this.zzc)) * 31)) * 31);
        zzfmo zzfmo = this.zzp;
        int hashCode3 = (((this.zzq == null ? 0 : this.zzq.hashCode()) + (((zzfmo == null ? 0 : zzfmo.hashCode()) + (hashCode2 * 31)) * 31)) * 31) + ((int) (this.zzd ^ (this.zzd >>> 32)));
        zzfmq zzfmq = this.zzr;
        int hashCode4 = (((((((this.zzs == null ? 0 : this.zzs.hashCode()) + (((((zzfmq == null ? 0 : zzfmq.hashCode()) + (hashCode3 * 31)) * 31) + Arrays.hashCode(this.zze)) * 31)) * 31) + this.zzt) * 31) + zzflq.zza(this.zzu)) * 31) + ((int) (this.zzv ^ (this.zzv >>> 32)));
        zzfmt zzfmt = this.zzw;
        int hashCode5 = ((zzfmt == null ? 0 : zzfmt.hashCode()) + (hashCode4 * 31)) * 31;
        if (!this.zzx) {
            i = 1237;
        }
        int i3 = (hashCode5 + i) * 31;
        if (this.zzax != null && !this.zzax.zzb()) {
            i2 = this.zzax.hashCode();
        }
        return i3 + i2;
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        int zza2 = super.zza();
        if (this.zza != 0) {
            zza2 += zzflk.zze(1, this.zza);
        }
        if (this.zzg != null && !this.zzg.equals("")) {
            zza2 += zzflk.zzb(2, this.zzg);
        }
        if (this.zzk != null && this.zzk.length > 0) {
            int i = zza2;
            for (zzfms zzfms : this.zzk) {
                if (zzfms != null) {
                    i += zzflk.zzb(3, (zzfls) zzfms);
                }
            }
            zza2 = i;
        }
        if (!Arrays.equals(this.zzl, zzflv.zzh)) {
            zza2 += zzflk.zzb(4, this.zzl);
        }
        if (!Arrays.equals(this.zzc, zzflv.zzh)) {
            zza2 += zzflk.zzb(6, this.zzc);
        }
        if (this.zzp != null) {
            zza2 += zzflk.zzb(7, (zzfls) this.zzp);
        }
        if (this.zzn != null && !this.zzn.equals("")) {
            zza2 += zzflk.zzb(8, this.zzn);
        }
        if (this.zzm != null) {
            zza2 += zzflk.zzb(9, (zzfls) this.zzm);
        }
        if (this.zzj) {
            zza2 += zzflk.zzb(10) + 1;
        }
        if (this.zzh != 0) {
            zza2 += zzflk.zzb(11, this.zzh);
        }
        if (this.zzi != 0) {
            zza2 += zzflk.zzb(12, this.zzi);
        }
        if (this.zzo != null && !this.zzo.equals("")) {
            zza2 += zzflk.zzb(13, this.zzo);
        }
        if (this.zzq != null && !this.zzq.equals("")) {
            zza2 += zzflk.zzb(14, this.zzq);
        }
        if (this.zzd != 180000) {
            zza2 += zzflk.zzf(15, this.zzd);
        }
        if (this.zzr != null) {
            zza2 += zzflk.zzb(16, (zzfls) this.zzr);
        }
        if (this.zzb != 0) {
            zza2 += zzflk.zze(17, this.zzb);
        }
        if (!Arrays.equals(this.zze, zzflv.zzh)) {
            zza2 += zzflk.zzb(18, this.zze);
        }
        if (this.zzt != 0) {
            zza2 += zzflk.zzb(19, this.zzt);
        }
        if (this.zzu != null && this.zzu.length > 0) {
            int i2 = 0;
            for (int zza3 : this.zzu) {
                i2 += zzflk.zza(zza3);
            }
            zza2 = zza2 + i2 + (this.zzu.length * 2);
        }
        if (this.zzf != 0) {
            zza2 += zzflk.zze(21, this.zzf);
        }
        if (this.zzv != 0) {
            zza2 += zzflk.zze(22, this.zzv);
        }
        if (this.zzw != null) {
            zza2 += zzflk.zzb(23, (zzfls) this.zzw);
        }
        if (this.zzs != null && !this.zzs.equals("")) {
            zza2 += zzflk.zzb(24, this.zzs);
        }
        return this.zzx ? zza2 + zzflk.zzb(25) + 1 : zza2;
    }

    public final void zza(zzflk zzflk) throws IOException {
        if (this.zza != 0) {
            zzflk.zzb(1, this.zza);
        }
        if (this.zzg != null && !this.zzg.equals("")) {
            zzflk.zza(2, this.zzg);
        }
        if (this.zzk != null && this.zzk.length > 0) {
            for (zzfms zzfms : this.zzk) {
                if (zzfms != null) {
                    zzflk.zza(3, (zzfls) zzfms);
                }
            }
        }
        if (!Arrays.equals(this.zzl, zzflv.zzh)) {
            zzflk.zza(4, this.zzl);
        }
        if (!Arrays.equals(this.zzc, zzflv.zzh)) {
            zzflk.zza(6, this.zzc);
        }
        if (this.zzp != null) {
            zzflk.zza(7, (zzfls) this.zzp);
        }
        if (this.zzn != null && !this.zzn.equals("")) {
            zzflk.zza(8, this.zzn);
        }
        if (this.zzm != null) {
            zzflk.zza(9, (zzfls) this.zzm);
        }
        if (this.zzj) {
            zzflk.zza(10, this.zzj);
        }
        if (this.zzh != 0) {
            zzflk.zza(11, this.zzh);
        }
        if (this.zzi != 0) {
            zzflk.zza(12, this.zzi);
        }
        if (this.zzo != null && !this.zzo.equals("")) {
            zzflk.zza(13, this.zzo);
        }
        if (this.zzq != null && !this.zzq.equals("")) {
            zzflk.zza(14, this.zzq);
        }
        if (this.zzd != 180000) {
            zzflk.zzd(15, this.zzd);
        }
        if (this.zzr != null) {
            zzflk.zza(16, (zzfls) this.zzr);
        }
        if (this.zzb != 0) {
            zzflk.zzb(17, this.zzb);
        }
        if (!Arrays.equals(this.zze, zzflv.zzh)) {
            zzflk.zza(18, this.zze);
        }
        if (this.zzt != 0) {
            zzflk.zza(19, this.zzt);
        }
        if (this.zzu != null && this.zzu.length > 0) {
            for (int zza2 : this.zzu) {
                zzflk.zza(20, zza2);
            }
        }
        if (this.zzf != 0) {
            zzflk.zzb(21, this.zzf);
        }
        if (this.zzv != 0) {
            zzflk.zzb(22, this.zzv);
        }
        if (this.zzw != null) {
            zzflk.zza(23, (zzfls) this.zzw);
        }
        if (this.zzs != null && !this.zzs.equals("")) {
            zzflk.zza(24, this.zzs);
        }
        if (this.zzx) {
            zzflk.zza(25, this.zzx);
        }
        super.zza(zzflk);
    }

    public final /* synthetic */ zzflm zzc() throws CloneNotSupportedException {
        return (zzfmr) clone();
    }

    public final /* synthetic */ zzfls zzd() throws CloneNotSupportedException {
        return (zzfmr) clone();
    }
}
