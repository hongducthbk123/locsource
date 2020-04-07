package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.SparseArray;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzc;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.common.util.zzr;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class zzbia extends zzbhs {
    public static final Creator<zzbia> CREATOR = new zzbib();
    private final int zza;
    private final Parcel zzb;
    private final int zzc = 2;
    private final zzbhv zzd;
    private final String zze;
    private int zzf;
    private int zzg;

    zzbia(int i, Parcel parcel, zzbhv zzbhv) {
        this.zza = i;
        this.zzb = (Parcel) zzbq.zza(parcel);
        this.zzd = zzbhv;
        if (this.zzd == null) {
            this.zze = null;
        } else {
            this.zze = this.zzd.zza();
        }
        this.zzf = 2;
    }

    private static HashMap<String, String> zza(Bundle bundle) {
        HashMap<String, String> hashMap = new HashMap<>();
        for (String str : bundle.keySet()) {
            hashMap.put(str, bundle.getString(str));
        }
        return hashMap;
    }

    private static void zza(StringBuilder sb, int i, Object obj) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                sb.append(obj);
                return;
            case 7:
                sb.append("\"").append(zzq.zza(obj.toString())).append("\"");
                return;
            case 8:
                sb.append("\"").append(zzc.zza((byte[]) obj)).append("\"");
                return;
            case 9:
                sb.append("\"").append(zzc.zzb((byte[]) obj));
                sb.append("\"");
                return;
            case 10:
                zzr.zza(sb, (HashMap) obj);
                return;
            case 11:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException("Unknown type = " + i);
        }
    }

    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v36, types: [double[]] */
    /* JADX WARNING: type inference failed for: r0v37, types: [double[]] */
    /* JADX WARNING: type inference failed for: r0v41, types: [java.math.BigInteger[]] */
    /* JADX WARNING: type inference failed for: r0v42, types: [java.lang.Object[]] */
    /* JADX WARNING: type inference failed for: r0v45 */
    /* JADX WARNING: type inference failed for: r0v46 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0
      assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], java.math.BigInteger[], double[]]
      uses: [double[], java.lang.Object[], ?[OBJECT, ARRAY][]]
      mth insns count: 159
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
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zza(java.lang.StringBuilder r8, com.google.android.gms.internal.zzbhq<?, ?> r9, android.os.Parcel r10, int r11) {
        /*
            r7 = this;
            r0 = 0
            r2 = 0
            boolean r1 = r9.zzd
            if (r1 == 0) goto L_0x00c7
            java.lang.String r1 = "["
            r8.append(r1)
            int r1 = r9.zzc
            switch(r1) {
                case 0: goto L_0x0018;
                case 1: goto L_0x0032;
                case 2: goto L_0x0060;
                case 3: goto L_0x0068;
                case 4: goto L_0x0070;
                case 5: goto L_0x0087;
                case 6: goto L_0x008f;
                case 7: goto L_0x0097;
                case 8: goto L_0x009f;
                case 9: goto L_0x009f;
                case 10: goto L_0x009f;
                case 11: goto L_0x00a7;
                default: goto L_0x0010;
            }
        L_0x0010:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Unknown field type out."
            r0.<init>(r1)
            throw r0
        L_0x0018:
            int[] r0 = com.google.android.gms.internal.zzbgm.zzw(r10, r11)
            int r1 = r0.length
        L_0x001d:
            if (r2 >= r1) goto L_0x003f
            if (r2 == 0) goto L_0x0026
            java.lang.String r3 = ","
            r8.append(r3)
        L_0x0026:
            r3 = r0[r2]
            java.lang.String r3 = java.lang.Integer.toString(r3)
            r8.append(r3)
            int r2 = r2 + 1
            goto L_0x001d
        L_0x0032:
            int r1 = com.google.android.gms.internal.zzbgm.zza(r10, r11)
            int r3 = r10.dataPosition()
            if (r1 != 0) goto L_0x0045
        L_0x003c:
            com.google.android.gms.common.util.zzb.zza(r8, (T[]) r0)
        L_0x003f:
            java.lang.String r0 = "]"
            r8.append(r0)
        L_0x0044:
            return
        L_0x0045:
            int r4 = r10.readInt()
            java.math.BigInteger[] r0 = new java.math.BigInteger[r4]
        L_0x004b:
            if (r2 >= r4) goto L_0x005b
            java.math.BigInteger r5 = new java.math.BigInteger
            byte[] r6 = r10.createByteArray()
            r5.<init>(r6)
            r0[r2] = r5
            int r2 = r2 + 1
            goto L_0x004b
        L_0x005b:
            int r1 = r1 + r3
            r10.setDataPosition(r1)
            goto L_0x003c
        L_0x0060:
            long[] r0 = com.google.android.gms.internal.zzbgm.zzx(r10, r11)
            com.google.android.gms.common.util.zzb.zza(r8, r0)
            goto L_0x003f
        L_0x0068:
            float[] r0 = com.google.android.gms.internal.zzbgm.zzy(r10, r11)
            com.google.android.gms.common.util.zzb.zza(r8, r0)
            goto L_0x003f
        L_0x0070:
            int r1 = com.google.android.gms.internal.zzbgm.zza(r10, r11)
            int r2 = r10.dataPosition()
            if (r1 != 0) goto L_0x007e
        L_0x007a:
            com.google.android.gms.common.util.zzb.zza(r8, r0)
            goto L_0x003f
        L_0x007e:
            double[] r0 = r10.createDoubleArray()
            int r1 = r1 + r2
            r10.setDataPosition(r1)
            goto L_0x007a
        L_0x0087:
            java.math.BigDecimal[] r0 = com.google.android.gms.internal.zzbgm.zzz(r10, r11)
            com.google.android.gms.common.util.zzb.zza(r8, (T[]) r0)
            goto L_0x003f
        L_0x008f:
            boolean[] r0 = com.google.android.gms.internal.zzbgm.zzv(r10, r11)
            com.google.android.gms.common.util.zzb.zza(r8, r0)
            goto L_0x003f
        L_0x0097:
            java.lang.String[] r0 = com.google.android.gms.internal.zzbgm.zzaa(r10, r11)
            com.google.android.gms.common.util.zzb.zza(r8, r0)
            goto L_0x003f
        L_0x009f:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            java.lang.String r1 = "List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported"
            r0.<init>(r1)
            throw r0
        L_0x00a7:
            android.os.Parcel[] r1 = com.google.android.gms.internal.zzbgm.zzae(r10, r11)
            int r3 = r1.length
            r0 = r2
        L_0x00ad:
            if (r0 >= r3) goto L_0x003f
            if (r0 <= 0) goto L_0x00b6
            java.lang.String r4 = ","
            r8.append(r4)
        L_0x00b6:
            r4 = r1[r0]
            r4.setDataPosition(r2)
            java.util.Map r4 = r9.zzc()
            r5 = r1[r0]
            r7.zza(r8, r4, r5)
            int r0 = r0 + 1
            goto L_0x00ad
        L_0x00c7:
            int r0 = r9.zzc
            switch(r0) {
                case 0: goto L_0x00d4;
                case 1: goto L_0x00dd;
                case 2: goto L_0x00e6;
                case 3: goto L_0x00ef;
                case 4: goto L_0x00f8;
                case 5: goto L_0x0101;
                case 6: goto L_0x010a;
                case 7: goto L_0x0113;
                case 8: goto L_0x012c;
                case 9: goto L_0x0145;
                case 10: goto L_0x015d;
                case 11: goto L_0x01bc;
                default: goto L_0x00cc;
            }
        L_0x00cc:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Unknown field type out"
            r0.<init>(r1)
            throw r0
        L_0x00d4:
            int r0 = com.google.android.gms.internal.zzbgm.zzg(r10, r11)
            r8.append(r0)
            goto L_0x0044
        L_0x00dd:
            java.math.BigInteger r0 = com.google.android.gms.internal.zzbgm.zzk(r10, r11)
            r8.append(r0)
            goto L_0x0044
        L_0x00e6:
            long r0 = com.google.android.gms.internal.zzbgm.zzi(r10, r11)
            r8.append(r0)
            goto L_0x0044
        L_0x00ef:
            float r0 = com.google.android.gms.internal.zzbgm.zzl(r10, r11)
            r8.append(r0)
            goto L_0x0044
        L_0x00f8:
            double r0 = com.google.android.gms.internal.zzbgm.zzn(r10, r11)
            r8.append(r0)
            goto L_0x0044
        L_0x0101:
            java.math.BigDecimal r0 = com.google.android.gms.internal.zzbgm.zzp(r10, r11)
            r8.append(r0)
            goto L_0x0044
        L_0x010a:
            boolean r0 = com.google.android.gms.internal.zzbgm.zzc(r10, r11)
            r8.append(r0)
            goto L_0x0044
        L_0x0113:
            java.lang.String r0 = com.google.android.gms.internal.zzbgm.zzq(r10, r11)
            java.lang.String r1 = "\""
            java.lang.StringBuilder r1 = r8.append(r1)
            java.lang.String r0 = com.google.android.gms.common.util.zzq.zza(r0)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r1 = "\""
            r0.append(r1)
            goto L_0x0044
        L_0x012c:
            byte[] r0 = com.google.android.gms.internal.zzbgm.zzt(r10, r11)
            java.lang.String r1 = "\""
            java.lang.StringBuilder r1 = r8.append(r1)
            java.lang.String r0 = com.google.android.gms.common.util.zzc.zza(r0)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r1 = "\""
            r0.append(r1)
            goto L_0x0044
        L_0x0145:
            byte[] r0 = com.google.android.gms.internal.zzbgm.zzt(r10, r11)
            java.lang.String r1 = "\""
            java.lang.StringBuilder r1 = r8.append(r1)
            java.lang.String r0 = com.google.android.gms.common.util.zzc.zzb(r0)
            r1.append(r0)
            java.lang.String r0 = "\""
            r8.append(r0)
            goto L_0x0044
        L_0x015d:
            android.os.Bundle r3 = com.google.android.gms.internal.zzbgm.zzs(r10, r11)
            java.util.Set r1 = r3.keySet()
            r1.size()
            java.lang.String r0 = "{"
            r8.append(r0)
            r0 = 1
            java.util.Iterator r4 = r1.iterator()
            r1 = r0
        L_0x0174:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x01b4
            java.lang.Object r0 = r4.next()
            java.lang.String r0 = (java.lang.String) r0
            if (r1 != 0) goto L_0x0187
            java.lang.String r1 = ","
            r8.append(r1)
        L_0x0187:
            java.lang.String r1 = "\""
            java.lang.StringBuilder r1 = r8.append(r1)
            java.lang.StringBuilder r1 = r1.append(r0)
            java.lang.String r5 = "\""
            r1.append(r5)
            java.lang.String r1 = ":"
            r8.append(r1)
            java.lang.String r1 = "\""
            java.lang.StringBuilder r1 = r8.append(r1)
            java.lang.String r0 = r3.getString(r0)
            java.lang.String r0 = com.google.android.gms.common.util.zzq.zza(r0)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r1 = "\""
            r0.append(r1)
            r1 = r2
            goto L_0x0174
        L_0x01b4:
            java.lang.String r0 = "}"
            r8.append(r0)
            goto L_0x0044
        L_0x01bc:
            android.os.Parcel r0 = com.google.android.gms.internal.zzbgm.zzad(r10, r11)
            r0.setDataPosition(r2)
            java.util.Map r1 = r9.zzc()
            r7.zza(r8, r1, r0)
            goto L_0x0044
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbia.zza(java.lang.StringBuilder, com.google.android.gms.internal.zzbhq, android.os.Parcel, int):void");
    }

    private final void zza(StringBuilder sb, zzbhq<?, ?> zzbhq, Object obj) {
        if (zzbhq.zzb) {
            ArrayList arrayList = (ArrayList) obj;
            sb.append("[");
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (i != 0) {
                    sb.append(",");
                }
                zza(sb, zzbhq.zza, arrayList.get(i));
            }
            sb.append("]");
            return;
        }
        zza(sb, zzbhq.zza, obj);
    }

    private final void zza(StringBuilder sb, Map<String, zzbhq<?, ?>> map, Parcel parcel) {
        SparseArray sparseArray = new SparseArray();
        for (Entry entry : map.entrySet()) {
            sparseArray.put(((zzbhq) entry.getValue()).zzf, entry);
        }
        sb.append('{');
        int zza2 = zzbgm.zza(parcel);
        boolean z = false;
        while (parcel.dataPosition() < zza2) {
            int readInt = parcel.readInt();
            Entry entry2 = (Entry) sparseArray.get(65535 & readInt);
            if (entry2 != null) {
                if (z) {
                    sb.append(",");
                }
                String str = (String) entry2.getKey();
                zzbhq zzbhq = (zzbhq) entry2.getValue();
                sb.append("\"").append(str).append("\":");
                if (zzbhq.zzb()) {
                    switch (zzbhq.zzc) {
                        case 0:
                            zza(sb, zzbhq, zza(zzbhq, Integer.valueOf(zzbgm.zzg(parcel, readInt))));
                            break;
                        case 1:
                            zza(sb, zzbhq, zza(zzbhq, zzbgm.zzk(parcel, readInt)));
                            break;
                        case 2:
                            zza(sb, zzbhq, zza(zzbhq, Long.valueOf(zzbgm.zzi(parcel, readInt))));
                            break;
                        case 3:
                            zza(sb, zzbhq, zza(zzbhq, Float.valueOf(zzbgm.zzl(parcel, readInt))));
                            break;
                        case 4:
                            zza(sb, zzbhq, zza(zzbhq, Double.valueOf(zzbgm.zzn(parcel, readInt))));
                            break;
                        case 5:
                            zza(sb, zzbhq, zza(zzbhq, zzbgm.zzp(parcel, readInt)));
                            break;
                        case 6:
                            zza(sb, zzbhq, zza(zzbhq, Boolean.valueOf(zzbgm.zzc(parcel, readInt))));
                            break;
                        case 7:
                            zza(sb, zzbhq, zza(zzbhq, zzbgm.zzq(parcel, readInt)));
                            break;
                        case 8:
                        case 9:
                            zza(sb, zzbhq, zza(zzbhq, zzbgm.zzt(parcel, readInt)));
                            break;
                        case 10:
                            zza(sb, zzbhq, zza(zzbhq, zza(zzbgm.zzs(parcel, readInt))));
                            break;
                        case 11:
                            throw new IllegalArgumentException("Method does not accept concrete type.");
                        default:
                            throw new IllegalArgumentException("Unknown field out type = " + zzbhq.zzc);
                    }
                } else {
                    zza(sb, zzbhq, parcel, readInt);
                }
                z = true;
            }
        }
        if (parcel.dataPosition() != zza2) {
            throw new zzbgn("Overread allowed size end=" + zza2, parcel);
        }
        sb.append('}');
    }

    private Parcel zzb() {
        switch (this.zzf) {
            case 0:
                this.zzg = zzbgo.zza(this.zzb);
                break;
            case 1:
                break;
        }
        zzbgo.zza(this.zzb, this.zzg);
        this.zzf = 2;
        return this.zzb;
    }

    public String toString() {
        zzbq.zza(this.zzd, (Object) "Cannot convert to JSON on client side.");
        Parcel zzb2 = zzb();
        zzb2.setDataPosition(0);
        StringBuilder sb = new StringBuilder(100);
        zza(sb, this.zzd.zza(this.zze), zzb2);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzbhv zzbhv;
        int zza2 = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 1, this.zza);
        zzbgo.zza(parcel, 2, zzb(), false);
        switch (this.zzc) {
            case 0:
                zzbhv = null;
                break;
            case 1:
                zzbhv = this.zzd;
                break;
            case 2:
                zzbhv = this.zzd;
                break;
            default:
                throw new IllegalStateException("Invalid creation type: " + this.zzc);
        }
        zzbgo.zza(parcel, 3, (Parcelable) zzbhv, i, false);
        zzbgo.zza(parcel, zza2);
    }

    public final Object zza(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    public final Map<String, zzbhq<?, ?>> zza() {
        if (this.zzd == null) {
            return null;
        }
        return this.zzd.zza(this.zze);
    }

    public final boolean zzb(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }
}
