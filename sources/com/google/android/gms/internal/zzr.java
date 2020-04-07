package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.Collections;
import java.util.Map;

public abstract class zzr<T> implements Comparable<zzr<T>> {
    /* access modifiers changed from: private */
    public final zza zza;
    private final int zzb;
    private final String zzc;
    private final int zzd;
    private final Object zze;
    private zzy zzf;
    private Integer zzg;
    private zzv zzh;
    private boolean zzi;
    private boolean zzj;
    private boolean zzk;
    private boolean zzl;
    private zzab zzm;
    private zzc zzn;
    private zzt zzo;

    public zzr(int i, String str, zzy zzy) {
        int i2;
        this.zza = zza.zza ? new zza() : null;
        this.zze = new Object();
        this.zzi = true;
        this.zzj = false;
        this.zzk = false;
        this.zzl = false;
        this.zzn = null;
        this.zzb = i;
        this.zzc = str;
        this.zzf = zzy;
        this.zzm = new zzh();
        if (!TextUtils.isEmpty(str)) {
            Uri parse = Uri.parse(str);
            if (parse != null) {
                String host = parse.getHost();
                if (host != null) {
                    i2 = host.hashCode();
                    this.zzd = i2;
                }
            }
        }
        i2 = 0;
        this.zzd = i2;
    }

    public /* synthetic */ int compareTo(Object obj) {
        zzr zzr = (zzr) obj;
        zzu zzu = zzu.NORMAL;
        zzu zzu2 = zzu.NORMAL;
        return zzu == zzu2 ? this.zzg.intValue() - zzr.zzg.intValue() : zzu2.ordinal() - zzu.ordinal();
    }

    public String toString() {
        String str = "0x";
        String valueOf = String.valueOf(Integer.toHexString(this.zzd));
        String str2 = valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
        String str3 = "[ ] ";
        String str4 = this.zzc;
        String valueOf2 = String.valueOf(zzu.NORMAL);
        String valueOf3 = String.valueOf(this.zzg);
        return new StringBuilder(String.valueOf(str3).length() + 3 + String.valueOf(str4).length() + String.valueOf(str2).length() + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length()).append(str3).append(str4).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(str2).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(valueOf2).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(valueOf3).toString();
    }

    public final int zza() {
        return this.zzb;
    }

    public final zzr<?> zza(int i) {
        this.zzg = Integer.valueOf(i);
        return this;
    }

    public final zzr<?> zza(zzc zzc2) {
        this.zzn = zzc2;
        return this;
    }

    public final zzr<?> zza(zzv zzv) {
        this.zzh = zzv;
        return this;
    }

    /* access modifiers changed from: protected */
    public abstract zzx<T> zza(zzp zzp);

    public final void zza(zzae zzae) {
        zzy zzy;
        synchronized (this.zze) {
            zzy = this.zzf;
        }
        if (zzy != null) {
            zzy.zza(zzae);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzt zzt) {
        synchronized (this.zze) {
            this.zzo = zzt;
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzx<?> zzx) {
        zzt zzt;
        synchronized (this.zze) {
            zzt = this.zzo;
        }
        if (zzt != null) {
            zzt.zza(this, zzx);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void zza(T t);

    public final void zza(String str) {
        if (zza.zza) {
            this.zza.zza(str, Thread.currentThread().getId());
        }
    }

    public final int zzb() {
        return this.zzd;
    }

    /* access modifiers changed from: 0000 */
    public final void zzb(String str) {
        if (this.zzh != null) {
            this.zzh.zzb(this);
        }
        if (zza.zza) {
            long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new zzs(this, str, id));
                return;
            }
            this.zza.zza(str, id);
            this.zza.zza(toString());
        }
    }

    public final String zzc() {
        return this.zzc;
    }

    public final zzc zzd() {
        return this.zzn;
    }

    public final boolean zze() {
        synchronized (this.zze) {
        }
        return false;
    }

    public Map<String, String> zzf() throws zza {
        return Collections.emptyMap();
    }

    public byte[] zzg() throws zza {
        return null;
    }

    public final boolean zzh() {
        return this.zzi;
    }

    public final int zzi() {
        return this.zzm.zza();
    }

    public final zzab zzj() {
        return this.zzm;
    }

    public final void zzk() {
        synchronized (this.zze) {
            this.zzk = true;
        }
    }

    public final boolean zzl() {
        boolean z;
        synchronized (this.zze) {
            z = this.zzk;
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    public final void zzm() {
        zzt zzt;
        synchronized (this.zze) {
            zzt = this.zzo;
        }
        if (zzt != null) {
            zzt.zza(this);
        }
    }
}
