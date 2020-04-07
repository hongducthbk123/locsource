package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public final class zzdk {
    public static final Status zza = new Status(8, "The connection to Google Play services was lost");
    private static final BasePendingResult<?>[] zzc = new BasePendingResult[0];
    final Set<BasePendingResult<?>> zzb = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));
    private final zzdn zzd = new zzdl(this);
    private final Map<zzc<?>, zze> zze;

    public zzdk(Map<zzc<?>, zze> map) {
        this.zze = map;
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [com.google.android.gms.common.api.internal.zzdn, com.google.android.gms.common.api.ResultCallback, com.google.android.gms.common.api.zze, com.google.android.gms.common.api.internal.zzdl] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r7v0, types: [com.google.android.gms.common.api.internal.zzdn, com.google.android.gms.common.api.ResultCallback, com.google.android.gms.common.api.zze, com.google.android.gms.common.api.internal.zzdl]
      assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY]]
      uses: [com.google.android.gms.common.api.internal.zzdn, com.google.android.gms.common.api.ResultCallback, com.google.android.gms.common.api.zze, com.google.android.gms.common.api.internal.zzdl]
      mth insns count: 49
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
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza() {
        /*
            r8 = this;
            r1 = 0
            r7 = 0
            java.util.Set<com.google.android.gms.common.api.internal.BasePendingResult<?>> r0 = r8.zzb
            com.google.android.gms.common.api.internal.BasePendingResult<?>[] r2 = zzc
            java.lang.Object[] r0 = r0.toArray(r2)
            com.google.android.gms.common.api.internal.BasePendingResult[] r0 = (com.google.android.gms.common.api.internal.BasePendingResult[]) r0
            int r4 = r0.length
            r3 = r1
        L_0x000e:
            if (r3 >= r4) goto L_0x008b
            r2 = r0[r3]
            r2.zza(r7)
            java.lang.Integer r1 = r2.zzb()
            if (r1 != 0) goto L_0x002a
            boolean r1 = r2.zzf()
            if (r1 == 0) goto L_0x0026
            java.util.Set<com.google.android.gms.common.api.internal.BasePendingResult<?>> r1 = r8.zzb
            r1.remove(r2)
        L_0x0026:
            int r1 = r3 + 1
            r3 = r1
            goto L_0x000e
        L_0x002a:
            r2.setResultCallback(r7)
            java.util.Map<com.google.android.gms.common.api.Api$zzc<?>, com.google.android.gms.common.api.Api$zze> r5 = r8.zze
            r1 = r2
            com.google.android.gms.common.api.internal.zzm r1 = (com.google.android.gms.common.api.internal.zzm) r1
            com.google.android.gms.common.api.Api$zzc r1 = r1.zzc()
            java.lang.Object r1 = r5.get(r1)
            com.google.android.gms.common.api.Api$zze r1 = (com.google.android.gms.common.api.Api.zze) r1
            android.os.IBinder r1 = r1.zzv()
            boolean r5 = r2.zze()
            if (r5 == 0) goto L_0x0054
            com.google.android.gms.common.api.internal.zzdm r5 = new com.google.android.gms.common.api.internal.zzdm
            r5.<init>(r2, r7, r1, r7)
            r2.zza(r5)
        L_0x004e:
            java.util.Set<com.google.android.gms.common.api.internal.BasePendingResult<?>> r1 = r8.zzb
            r1.remove(r2)
            goto L_0x0026
        L_0x0054:
            if (r1 == 0) goto L_0x0079
            boolean r5 = r1.isBinderAlive()
            if (r5 == 0) goto L_0x0079
            com.google.android.gms.common.api.internal.zzdm r5 = new com.google.android.gms.common.api.internal.zzdm
            r5.<init>(r2, r7, r1, r7)
            r2.zza(r5)
            r6 = 0
            r1.linkToDeath(r5, r6)     // Catch:{ RemoteException -> 0x0069 }
            goto L_0x004e
        L_0x0069:
            r1 = move-exception
            r2.cancel()
            java.lang.Integer r1 = r2.zzb()
            int r1 = r1.intValue()
            r7.zza(r1)
            goto L_0x004e
        L_0x0079:
            r2.zza(r7)
            r2.cancel()
            java.lang.Integer r1 = r2.zzb()
            int r1 = r1.intValue()
            r7.zza(r1)
            goto L_0x004e
        L_0x008b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zzdk.zza():void");
    }

    /* access modifiers changed from: 0000 */
    public final void zza(BasePendingResult<? extends Result> basePendingResult) {
        this.zzb.add(basePendingResult);
        basePendingResult.zza(this.zzd);
    }

    public final void zzb() {
        for (BasePendingResult zzd2 : (BasePendingResult[]) this.zzb.toArray(zzc)) {
            zzd2.zzd(zza);
        }
    }
}
