package com.google.android.gms.internal;

import com.google.android.gms.internal.zzfhu.zzd;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeSet;
import org.apache.commons.p052io.IOUtils;

final class zzfjf {
    static String zza(zzfjc zzfjc, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ").append(str);
        zza(zzfjc, sb, 0);
        return sb.toString();
    }

    private static final String zza(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(charAt));
        }
        return sb.toString();
    }

    private static void zza(zzfjc zzfjc, StringBuilder sb, int i) {
        Method[] declaredMethods;
        boolean booleanValue;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        TreeSet<String> treeSet = new TreeSet<>();
        for (Method method : zzfjc.getClass().getDeclaredMethods()) {
            hashMap2.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                hashMap.put(method.getName(), method);
                if (method.getName().startsWith("get")) {
                    treeSet.add(method.getName());
                }
            }
        }
        for (String replaceFirst : treeSet) {
            String replaceFirst2 = replaceFirst.replaceFirst("get", "");
            if (replaceFirst2.endsWith("List") && !replaceFirst2.endsWith("OrBuilderList")) {
                String valueOf = String.valueOf(replaceFirst2.substring(0, 1).toLowerCase());
                String valueOf2 = String.valueOf(replaceFirst2.substring(1, replaceFirst2.length() - 4));
                String str = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                String str2 = "get";
                String valueOf3 = String.valueOf(replaceFirst2);
                Method method2 = (Method) hashMap.get(valueOf3.length() != 0 ? str2.concat(valueOf3) : new String(str2));
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    zza(sb, i, zza(str), zzfhu.zza(method2, (Object) zzfjc, new Object[0]));
                }
            }
            String str3 = "set";
            String valueOf4 = String.valueOf(replaceFirst2);
            if (((Method) hashMap2.get(valueOf4.length() != 0 ? str3.concat(valueOf4) : new String(str3))) != null) {
                if (replaceFirst2.endsWith("Bytes")) {
                    String str4 = "get";
                    String valueOf5 = String.valueOf(replaceFirst2.substring(0, replaceFirst2.length() - 5));
                    if (hashMap.containsKey(valueOf5.length() != 0 ? str4.concat(valueOf5) : new String(str4))) {
                    }
                }
                String valueOf6 = String.valueOf(replaceFirst2.substring(0, 1).toLowerCase());
                String valueOf7 = String.valueOf(replaceFirst2.substring(1));
                String str5 = valueOf7.length() != 0 ? valueOf6.concat(valueOf7) : new String(valueOf6);
                String str6 = "get";
                String valueOf8 = String.valueOf(replaceFirst2);
                Method method3 = (Method) hashMap.get(valueOf8.length() != 0 ? str6.concat(valueOf8) : new String(str6));
                String str7 = "has";
                String valueOf9 = String.valueOf(replaceFirst2);
                Method method4 = (Method) hashMap.get(valueOf9.length() != 0 ? str7.concat(valueOf9) : new String(str7));
                if (method3 != null) {
                    Object zza = zzfhu.zza(method3, (Object) zzfjc, new Object[0]);
                    if (method4 == null) {
                        boolean z = zza instanceof Boolean ? !((Boolean) zza).booleanValue() : zza instanceof Integer ? ((Integer) zza).intValue() == 0 : zza instanceof Float ? ((Float) zza).floatValue() == 0.0f : zza instanceof Double ? ((Double) zza).doubleValue() == 0.0d : zza instanceof String ? zza.equals("") : zza instanceof zzfgs ? zza.equals(zzfgs.zza) : zza instanceof zzfjc ? zza == ((zzfjc) zza).zzw() : zza instanceof Enum ? ((Enum) zza).ordinal() == 0 : false;
                        booleanValue = !z;
                    } else {
                        booleanValue = ((Boolean) zzfhu.zza(method4, (Object) zzfjc, new Object[0])).booleanValue();
                    }
                    if (booleanValue) {
                        zza(sb, i, zza(str5), zza);
                    }
                }
            }
        }
        if (zzfjc instanceof zzd) {
            Iterator zzb = ((zzd) zzfjc).zzd.zzb();
            if (zzb.hasNext()) {
                ((Entry) zzb.next()).getKey();
                throw new NoSuchMethodError();
            }
        }
        if (((zzfhu) zzfjc).zzb != null) {
            ((zzfhu) zzfjc).zzb.zza(sb, i);
        }
    }

    static final void zza(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            for (Object zza : (List) obj) {
                zza(sb, i, str, zza);
            }
            return;
        }
        sb.append(10);
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(' ');
        }
        sb.append(str);
        if (obj instanceof String) {
            sb.append(": \"").append(zzfkh.zza(zzfgs.zza((String) obj))).append('\"');
        } else if (obj instanceof zzfgs) {
            sb.append(": \"").append(zzfkh.zza((zzfgs) obj)).append('\"');
        } else if (obj instanceof zzfhu) {
            sb.append(" {");
            zza((zzfhu) obj, sb, i + 2);
            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
            for (int i3 = 0; i3 < i; i3++) {
                sb.append(' ');
            }
            sb.append("}");
        } else {
            sb.append(": ").append(obj.toString());
        }
    }
}
