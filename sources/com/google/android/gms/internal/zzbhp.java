package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzc;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.common.util.zzr;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class zzbhp {
    protected static <O, I> I zza(zzbhq<I, O> zzbhq, Object obj) {
        return zzbhq.zzk != null ? zzbhq.zza(obj) : obj;
    }

    private static void zza(StringBuilder sb, zzbhq zzbhq, Object obj) {
        if (zzbhq.zza == 11) {
            sb.append(((zzbhp) zzbhq.zzg.cast(obj)).toString());
        } else if (zzbhq.zza == 7) {
            sb.append("\"");
            sb.append(zzq.zza((String) obj));
            sb.append("\"");
        } else {
            sb.append(obj);
        }
    }

    private static void zza(StringBuilder sb, zzbhq zzbhq, ArrayList<Object> arrayList) {
        sb.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(",");
            }
            Object obj = arrayList.get(i);
            if (obj != null) {
                zza(sb, zzbhq, obj);
            }
        }
        sb.append("]");
    }

    public String toString() {
        Map zza = zza();
        StringBuilder sb = new StringBuilder(100);
        for (String str : zza.keySet()) {
            zzbhq zzbhq = (zzbhq) zza.get(str);
            if (zza(zzbhq)) {
                Object zza2 = zza(zzbhq, zzb(zzbhq));
                if (sb.length() == 0) {
                    sb.append("{");
                } else {
                    sb.append(",");
                }
                sb.append("\"").append(str).append("\":");
                if (zza2 != null) {
                    switch (zzbhq.zzc) {
                        case 8:
                            sb.append("\"").append(zzc.zza((byte[]) zza2)).append("\"");
                            break;
                        case 9:
                            sb.append("\"").append(zzc.zzb((byte[]) zza2)).append("\"");
                            break;
                        case 10:
                            zzr.zza(sb, (HashMap) zza2);
                            break;
                        default:
                            if (!zzbhq.zzb) {
                                zza(sb, zzbhq, zza2);
                                break;
                            } else {
                                zza(sb, zzbhq, (ArrayList) zza2);
                                break;
                            }
                    }
                } else {
                    sb.append("null");
                }
            }
        }
        if (sb.length() > 0) {
            sb.append("}");
        } else {
            sb.append("{}");
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public abstract Object zza(String str);

    public abstract Map<String, zzbhq<?, ?>> zza();

    /* access modifiers changed from: protected */
    public boolean zza(zzbhq zzbhq) {
        if (zzbhq.zzc != 11) {
            return zzb(zzbhq.zze);
        }
        if (zzbhq.zzd) {
            String str = zzbhq.zze;
            throw new UnsupportedOperationException("Concrete type arrays not supported");
        }
        String str2 = zzbhq.zze;
        throw new UnsupportedOperationException("Concrete types not supported");
    }

    /* access modifiers changed from: protected */
    public Object zzb(zzbhq zzbhq) {
        String str = zzbhq.zze;
        if (zzbhq.zzg == null) {
            return zza(zzbhq.zze);
        }
        zza(zzbhq.zze);
        zzbq.zza(true, "Concrete field shouldn't be value object: %s", zzbhq.zze);
        boolean z = zzbhq.zzd;
        try {
            char upperCase = Character.toUpperCase(str.charAt(0));
            String substring = str.substring(1);
            return getClass().getMethod(new StringBuilder(String.valueOf(substring).length() + 4).append("get").append(upperCase).append(substring).toString(), new Class[0]).invoke(this, new Object[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: protected */
    public abstract boolean zzb(String str);
}
