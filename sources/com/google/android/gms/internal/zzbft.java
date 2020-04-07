package com.google.android.gms.internal;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.clearcut.ClearcutLogger.zza;
import com.google.android.gms.phenotype.Phenotype;
import com.google.android.gms.phenotype.PhenotypeFlag;
import com.google.android.gms.phenotype.PhenotypeFlag.Factory;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import p004cn.jiguang.net.HttpUtils;

public final class zzbft implements zza {
    private static final Charset zza = Charset.forName("UTF-8");
    private static final Factory zzb = new Factory(Phenotype.getContentProviderUri("com.google.android.gms.clearcut.public")).withGservicePrefix("gms:playlog:service:sampling_").withPhenotypePrefix("LogSampling__");
    private static Map<String, PhenotypeFlag<String>> zzd = null;
    private static Boolean zze = null;
    private static Long zzf = null;
    private final Context zzc;

    public zzbft(Context context) {
        this.zzc = context;
        if (zzd == null) {
            zzd = new HashMap();
        }
        if (this.zzc != null) {
            PhenotypeFlag.maybeInit(this.zzc);
        }
    }

    private static zzbfu zza(String str) {
        int i = 0;
        if (str == null) {
            return null;
        }
        String str2 = "";
        int indexOf = str.indexOf(44);
        if (indexOf >= 0) {
            str2 = str.substring(0, indexOf);
            i = indexOf + 1;
        }
        int indexOf2 = str.indexOf(47, i);
        if (indexOf2 <= 0) {
            String str3 = "LogSamplerImpl";
            String str4 = "Failed to parse the rule: ";
            String valueOf = String.valueOf(str);
            Log.e(str3, valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4));
            return null;
        }
        try {
            long parseLong = Long.parseLong(str.substring(i, indexOf2));
            long parseLong2 = Long.parseLong(str.substring(indexOf2 + 1));
            if (parseLong >= 0 && parseLong2 >= 0) {
                return new zzbfu(str2, parseLong, parseLong2);
            }
            Log.e("LogSamplerImpl", "negative values not supported: " + parseLong + HttpUtils.PATHS_SEPARATOR + parseLong2);
            return null;
        } catch (NumberFormatException e) {
            NumberFormatException numberFormatException = e;
            String str5 = "LogSamplerImpl";
            String str6 = "parseLong() failed while parsing: ";
            String valueOf2 = String.valueOf(str);
            Log.e(str5, valueOf2.length() != 0 ? str6.concat(valueOf2) : new String(str6), numberFormatException);
            return null;
        }
    }

    private static boolean zza(Context context) {
        if (zze == null) {
            zze = Boolean.valueOf(zzbih.zza(context).zza("com.google.android.providers.gsf.permission.READ_GSERVICES") == 0);
        }
        return zze.booleanValue();
    }

    public final boolean zza(String str, int i) {
        String str2;
        long j;
        long j2;
        long j3;
        long j4;
        if (str == null || str.isEmpty()) {
            str = i >= 0 ? String.valueOf(i) : null;
        }
        if (str == null) {
            return true;
        }
        if (this.zzc == null || !zza(this.zzc)) {
            str2 = null;
        } else {
            PhenotypeFlag phenotypeFlag = (PhenotypeFlag) zzd.get(str);
            if (phenotypeFlag == null) {
                phenotypeFlag = zzb.createFlag(str, null);
                zzd.put(str, phenotypeFlag);
            }
            str2 = (String) phenotypeFlag.get();
        }
        zzbfu zza2 = zza(str2);
        if (zza2 == null) {
            return true;
        }
        String str3 = zza2.zza;
        Context context = this.zzc;
        if (zzf == null) {
            if (context == null) {
                j = 0;
                if (str3 != null || str3.isEmpty()) {
                    j2 = zzbfo.zza(ByteBuffer.allocate(8).putLong(j).array());
                } else {
                    byte[] bytes = str3.getBytes(zza);
                    ByteBuffer allocate = ByteBuffer.allocate(bytes.length + 8);
                    allocate.put(bytes);
                    allocate.putLong(j);
                    j2 = zzbfo.zza(allocate.array());
                }
                j3 = zza2.zzb;
                j4 = zza2.zzc;
                if (j3 >= 0 || j4 < 0) {
                    throw new IllegalArgumentException("negative values not supported: " + j3 + HttpUtils.PATHS_SEPARATOR + j4);
                }
                if (j4 > 0) {
                    if ((j2 >= 0 ? j2 % j4 : (((j2 & Long.MAX_VALUE) % j4) + ((Long.MAX_VALUE % j4) + 1)) % j4) < j3) {
                        return true;
                    }
                }
                return false;
            } else if (zza(context)) {
                zzf = Long.valueOf(zzdnm.zza(context.getContentResolver(), "android_id", 0));
            } else {
                zzf = Long.valueOf(0);
            }
        }
        j = zzf.longValue();
        if (str3 != null) {
        }
        j2 = zzbfo.zza(ByteBuffer.allocate(8).putLong(j).array());
        j3 = zza2.zzb;
        j4 = zza2.zzc;
        if (j3 >= 0) {
        }
        throw new IllegalArgumentException("negative values not supported: " + j3 + HttpUtils.PATHS_SEPARATOR + j4);
    }
}
