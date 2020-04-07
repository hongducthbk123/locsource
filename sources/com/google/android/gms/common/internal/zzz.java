package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.zzc;
import com.google.android.gms.common.zzf;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class zzz extends zzbgl {
    public static final Creator<zzz> CREATOR = new zzaa();
    String zza;
    IBinder zzb;
    Scope[] zzc;
    Bundle zzd;
    Account zze;
    zzc[] zzf;
    private int zzg;
    private int zzh;
    private int zzi;

    public zzz(int i) {
        this.zzg = 3;
        this.zzi = zzf.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        this.zzh = i;
    }

    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v1, types: [android.accounts.Account] */
    /* JADX WARNING: type inference failed for: r0v4, types: [com.google.android.gms.common.internal.zzap] */
    /* JADX WARNING: type inference failed for: r0v5, types: [com.google.android.gms.common.internal.zzan] */
    /* JADX WARNING: type inference failed for: r0v6, types: [com.google.android.gms.common.internal.zzan] */
    /* JADX WARNING: type inference failed for: r0v7, types: [android.accounts.Account] */
    /* JADX WARNING: type inference failed for: r0v8 */
    /* JADX WARNING: type inference failed for: r0v9 */
    /* JADX WARNING: type inference failed for: r0v10 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0
      assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], android.accounts.Account, com.google.android.gms.common.internal.zzap, com.google.android.gms.common.internal.zzan]
      uses: [android.accounts.Account, com.google.android.gms.common.internal.zzan]
      mth insns count: 30
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 4 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    zzz(int r3, int r4, int r5, java.lang.String r6, android.os.IBinder r7, com.google.android.gms.common.api.Scope[] r8, android.os.Bundle r9, android.accounts.Account r10, com.google.android.gms.common.zzc[] r11) {
        /*
            r2 = this;
            r0 = 0
            r2.<init>()
            r2.zzg = r3
            r2.zzh = r4
            r2.zzi = r5
            java.lang.String r1 = "com.google.android.gms"
            boolean r1 = r1.equals(r6)
            if (r1 == 0) goto L_0x002a
            java.lang.String r1 = "com.google.android.gms"
            r2.zza = r1
        L_0x0016:
            r1 = 2
            if (r3 >= r1) goto L_0x0040
            if (r7 == 0) goto L_0x0021
            if (r7 != 0) goto L_0x002d
        L_0x001d:
            android.accounts.Account r0 = com.google.android.gms.common.internal.zza.zza(r0)
        L_0x0021:
            r2.zze = r0
        L_0x0023:
            r2.zzc = r8
            r2.zzd = r9
            r2.zzf = r11
            return
        L_0x002a:
            r2.zza = r6
            goto L_0x0016
        L_0x002d:
            java.lang.String r0 = "com.google.android.gms.common.internal.IAccountAccessor"
            android.os.IInterface r0 = r7.queryLocalInterface(r0)
            boolean r1 = r0 instanceof com.google.android.gms.common.internal.zzan
            if (r1 == 0) goto L_0x003a
            com.google.android.gms.common.internal.zzan r0 = (com.google.android.gms.common.internal.zzan) r0
            goto L_0x001d
        L_0x003a:
            com.google.android.gms.common.internal.zzap r0 = new com.google.android.gms.common.internal.zzap
            r0.<init>(r7)
            goto L_0x001d
        L_0x0040:
            r2.zzb = r7
            r2.zze = r10
            goto L_0x0023
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zzz.<init>(int, int, int, java.lang.String, android.os.IBinder, com.google.android.gms.common.api.Scope[], android.os.Bundle, android.accounts.Account, com.google.android.gms.common.zzc[]):void");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zza2 = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 1, this.zzg);
        zzbgo.zza(parcel, 2, this.zzh);
        zzbgo.zza(parcel, 3, this.zzi);
        zzbgo.zza(parcel, 4, this.zza, false);
        zzbgo.zza(parcel, 5, this.zzb, false);
        zzbgo.zza(parcel, 6, (T[]) this.zzc, i, false);
        zzbgo.zza(parcel, 7, this.zzd, false);
        zzbgo.zza(parcel, 8, (Parcelable) this.zze, i, false);
        zzbgo.zza(parcel, 10, (T[]) this.zzf, i, false);
        zzbgo.zza(parcel, zza2);
    }
}
