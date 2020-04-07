package com.google.android.gms.internal;

import com.google.common.net.HttpHeaders;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.TreeMap;
import p004cn.jiguang.net.HttpUtils;

public final class zzap {
    private static long zza(String str) {
        try {
            return zza().parse(str).getTime();
        } catch (ParseException e) {
            zzaf.zza(e, "Unable to parse dateStr: %s, falling back to 0", str);
            return 0;
        }
    }

    public static zzc zza(zzp zzp) {
        boolean z;
        boolean z2;
        long j;
        long j2;
        long currentTimeMillis = System.currentTimeMillis();
        Map<String, String> map = zzp.zzc;
        long j3 = 0;
        long j4 = 0;
        long j5 = 0;
        String str = (String) map.get("Date");
        if (str != null) {
            j3 = zza(str);
        }
        String str2 = (String) map.get(HttpHeaders.CACHE_CONTROL);
        if (str2 != null) {
            String[] split = str2.split(",");
            z = false;
            long j6 = 0;
            long j7 = 0;
            for (String trim : split) {
                String trim2 = trim.trim();
                if (trim2.equals("no-cache") || trim2.equals("no-store")) {
                    return null;
                }
                if (trim2.startsWith("max-age=")) {
                    try {
                        j7 = Long.parseLong(trim2.substring(8));
                    } catch (Exception e) {
                    }
                } else if (trim2.startsWith("stale-while-revalidate=")) {
                    try {
                        j6 = Long.parseLong(trim2.substring(23));
                    } catch (Exception e2) {
                    }
                } else if (trim2.equals("must-revalidate") || trim2.equals("proxy-revalidate")) {
                    z = true;
                }
            }
            j4 = j7;
            j5 = j6;
            z2 = true;
        } else {
            z = false;
            z2 = false;
        }
        String str3 = (String) map.get(HttpHeaders.EXPIRES);
        long j8 = str3 != null ? zza(str3) : 0;
        String str4 = (String) map.get(HttpHeaders.LAST_MODIFIED);
        long j9 = str4 != null ? zza(str4) : 0;
        String str5 = (String) map.get(HttpHeaders.ETAG);
        if (z2) {
            j2 = currentTimeMillis + (1000 * j4);
            j = z ? j2 : (1000 * j5) + j2;
        } else if (j3 <= 0 || j8 < j3) {
            j = 0;
            j2 = 0;
        } else {
            j = (j8 - j3) + currentTimeMillis;
            j2 = j;
        }
        zzc zzc = new zzc();
        zzc.zza = zzp.zzb;
        zzc.zzb = str5;
        zzc.zzf = j2;
        zzc.zze = j;
        zzc.zzc = j3;
        zzc.zzd = j9;
        zzc.zzg = map;
        zzc.zzh = zzp.zzd;
        return zzc;
    }

    static String zza(long j) {
        return zza().format(new Date(j));
    }

    public static String zza(Map<String, String> map) {
        String str = "ISO-8859-1";
        String str2 = (String) map.get("Content-Type");
        if (str2 != null) {
            String[] split = str2.split(";");
            for (int i = 1; i < split.length; i++) {
                String[] split2 = split[i].trim().split(HttpUtils.EQUAL_SIGN);
                if (split2.length == 2 && split2[0].equals("charset")) {
                    return split2[1];
                }
            }
        }
        return str;
    }

    private static SimpleDateFormat zza() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat;
    }

    static Map<String, String> zza(List<zzl> list) {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (zzl zzl : list) {
            treeMap.put(zzl.zza(), zzl.zzb());
        }
        return treeMap;
    }

    static List<zzl> zzb(Map<String, String> map) {
        ArrayList arrayList = new ArrayList(map.size());
        for (Entry entry : map.entrySet()) {
            arrayList.add(new zzl((String) entry.getKey(), (String) entry.getValue()));
        }
        return arrayList;
    }
}
