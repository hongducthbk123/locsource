package com.google.android.gms.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

final class zzan {
    long zza;
    final String zzb;
    final String zzc;
    final long zzd;
    final long zze;
    final long zzf;
    final long zzg;
    final List<zzl> zzh;

    zzan(String str, zzc zzc2) {
        this(str, zzc2.zzb, zzc2.zzc, zzc2.zzd, zzc2.zze, zzc2.zzf, zzc2.zzh != null ? zzc2.zzh : zzap.zzb(zzc2.zzg));
        this.zza = (long) zzc2.zza.length;
    }

    private zzan(String str, String str2, long j, long j2, long j3, long j4, List<zzl> list) {
        this.zzb = str;
        if ("".equals(str2)) {
            str2 = null;
        }
        this.zzc = str2;
        this.zzd = j;
        this.zze = j2;
        this.zzf = j3;
        this.zzg = j4;
        this.zzh = list;
    }

    static zzan zza(zzao zzao) throws IOException {
        if (zzam.zza((InputStream) zzao) == 538247942) {
            return new zzan(zzam.zza(zzao), zzam.zza(zzao), zzam.zzb((InputStream) zzao), zzam.zzb((InputStream) zzao), zzam.zzb((InputStream) zzao), zzam.zzb((InputStream) zzao), zzam.zzb(zzao));
        }
        throw new IOException();
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza(OutputStream outputStream) {
        try {
            zzam.zza(outputStream, 538247942);
            zzam.zza(outputStream, this.zzb);
            zzam.zza(outputStream, this.zzc == null ? "" : this.zzc);
            zzam.zza(outputStream, this.zzd);
            zzam.zza(outputStream, this.zze);
            zzam.zza(outputStream, this.zzf);
            zzam.zza(outputStream, this.zzg);
            List<zzl> list = this.zzh;
            if (list != null) {
                zzam.zza(outputStream, list.size());
                for (zzl zzl : list) {
                    zzam.zza(outputStream, zzl.zza());
                    zzam.zza(outputStream, zzl.zzb());
                }
            } else {
                zzam.zza(outputStream, 0);
            }
            outputStream.flush();
            return true;
        } catch (IOException e) {
            zzaf.zzb("%s", e.toString());
            return false;
        }
    }
}
