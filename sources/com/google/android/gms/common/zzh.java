package com.google.android.gms.common;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzat;
import com.google.android.gms.common.internal.zzau;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

@Hide
abstract class zzh extends zzau {
    private int zza;

    protected zzh(byte[] bArr) {
        zzbq.zzb(bArr.length == 25);
        this.zza = Arrays.hashCode(bArr);
    }

    protected static byte[] zza(String str) {
        try {
            return str.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof zzat)) {
            return false;
        }
        try {
            zzat zzat = (zzat) obj;
            if (zzat.zzc() != hashCode()) {
                return false;
            }
            IObjectWrapper zzb = zzat.zzb();
            if (zzb == null) {
                return false;
            }
            return Arrays.equals(zza(), (byte[]) zzn.zza(zzb));
        } catch (RemoteException e) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
            return false;
        }
    }

    public int hashCode() {
        return this.zza;
    }

    /* access modifiers changed from: 0000 */
    public abstract byte[] zza();

    public final IObjectWrapper zzb() {
        return zzn.zza(zza());
    }

    public final int zzc() {
        return hashCode();
    }
}
