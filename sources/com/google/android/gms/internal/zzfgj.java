package com.google.android.gms.internal;

import com.google.android.gms.internal.zzfgj;
import com.google.android.gms.internal.zzfgk;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public abstract class zzfgj<MessageType extends zzfgj<MessageType, BuilderType>, BuilderType extends zzfgk<MessageType, BuilderType>> implements zzfjc {
    private static boolean zzb = false;
    protected int zza = 0;

    protected static <T> void zza(Iterable<T> iterable, List<? super T> list) {
        zzfgk.zza(iterable, list);
    }

    public final void zza(OutputStream outputStream) throws IOException {
        zzfhg zza2 = zzfhg.zza(outputStream, zzfhg.zza(zza()));
        zza(zza2);
        zza2.zza();
    }

    public final zzfgs zzp() {
        try {
            zzfgx zzb2 = zzfgs.zzb(zza());
            zza(zzb2.zzb());
            return zzb2.zza();
        } catch (IOException e) {
            String str = "ByteString";
            String name = getClass().getName();
            throw new RuntimeException(new StringBuilder(String.valueOf(name).length() + 62 + String.valueOf(str).length()).append("Serializing ").append(name).append(" to a ").append(str).append(" threw an IOException (should never happen).").toString(), e);
        }
    }

    public final byte[] zzq() {
        try {
            byte[] bArr = new byte[zza()];
            zzfhg zza2 = zzfhg.zza(bArr);
            zza(zza2);
            zza2.zzc();
            return bArr;
        } catch (IOException e) {
            String str = "byte array";
            String name = getClass().getName();
            throw new RuntimeException(new StringBuilder(String.valueOf(name).length() + 62 + String.valueOf(str).length()).append("Serializing ").append(name).append(" to a ").append(str).append(" threw an IOException (should never happen).").toString(), e);
        }
    }
}
