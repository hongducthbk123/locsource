package com.google.android.gms.clearcut;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.internal.zzbfi;
import com.google.android.gms.internal.zzbfn;
import com.google.android.gms.internal.zzbft;
import com.google.android.gms.internal.zzbfv;
import com.google.android.gms.internal.zzfmr;
import com.google.android.gms.phenotype.ExperimentTokens;
import java.util.ArrayList;
import java.util.TimeZone;

@KeepForSdk
public final class ClearcutLogger {
    @Deprecated
    public static final Api<NoOptions> zza = new Api<>("ClearcutLogger.API", zzc, zzb);
    @Hide
    private static zzf<zzbfn> zzb = new zzf<>();
    @Hide
    private static com.google.android.gms.common.api.Api.zza<zzbfn, NoOptions> zzc = new zza();
    private static final ExperimentTokens[] zzd = new ExperimentTokens[0];
    private static final String[] zze = new String[0];
    private static final byte[][] zzf = new byte[0][];
    /* access modifiers changed from: private */
    public final String zzg;
    /* access modifiers changed from: private */
    public final int zzh;
    /* access modifiers changed from: private */
    public String zzi;
    /* access modifiers changed from: private */
    public int zzj = -1;
    private String zzk;
    private String zzl;
    /* access modifiers changed from: private */
    public final boolean zzm;
    private int zzn = 0;
    /* access modifiers changed from: private */
    public final zzb zzo;
    /* access modifiers changed from: private */
    public final zze zzp;
    /* access modifiers changed from: private */
    public zzc zzq;
    /* access modifiers changed from: private */
    public final zza zzr;

    public class LogEventBuilder {
        private int zza;
        private String zzb;
        private String zzc;
        private String zzd;
        private int zze;
        private final zzb zzf;
        private ArrayList<Integer> zzg;
        private ArrayList<String> zzh;
        private ArrayList<Integer> zzi;
        private ArrayList<ExperimentTokens> zzj;
        private ArrayList<byte[]> zzk;
        private boolean zzl;
        private final zzfmr zzm;
        private boolean zzn;

        private LogEventBuilder(ClearcutLogger clearcutLogger, byte[] bArr) {
            this(bArr, (zzb) null);
        }

        private LogEventBuilder(byte[] bArr, zzb zzb2) {
            this.zza = ClearcutLogger.this.zzj;
            this.zzb = ClearcutLogger.this.zzi;
            ClearcutLogger clearcutLogger = ClearcutLogger.this;
            this.zzc = null;
            ClearcutLogger clearcutLogger2 = ClearcutLogger.this;
            this.zzd = null;
            this.zze = 0;
            this.zzg = null;
            this.zzh = null;
            this.zzi = null;
            this.zzj = null;
            this.zzk = null;
            this.zzl = true;
            this.zzm = new zzfmr();
            this.zzn = false;
            this.zzc = null;
            this.zzd = null;
            this.zzm.zza = ClearcutLogger.this.zzp.zza();
            this.zzm.zzb = ClearcutLogger.this.zzp.zzb();
            zzfmr zzfmr = this.zzm;
            ClearcutLogger.this.zzq;
            zzfmr.zzd = (long) (TimeZone.getDefault().getOffset(this.zzm.zza) / 1000);
            if (bArr != null) {
                this.zzm.zzc = bArr;
            }
            this.zzf = null;
        }

        /* synthetic */ LogEventBuilder(ClearcutLogger clearcutLogger, byte[] bArr, zza zza2) {
            this(clearcutLogger, bArr);
        }

        @KeepForSdk
        public void log() {
            if (this.zzn) {
                throw new IllegalStateException("do not reuse LogEventBuilder");
            }
            this.zzn = true;
            zze zze2 = new zze(new zzbfv(ClearcutLogger.this.zzg, ClearcutLogger.this.zzh, this.zza, this.zzb, this.zzc, this.zzd, ClearcutLogger.this.zzm, 0), this.zzm, null, null, ClearcutLogger.zzb(null), null, ClearcutLogger.zzb(null), null, null, this.zzl);
            zzbfv zzbfv = zze2.zza;
            if (ClearcutLogger.this.zzr.zza(zzbfv.zzb, zzbfv.zza)) {
                ClearcutLogger.this.zzo.zza(zze2);
            } else {
                PendingResults.zza(Status.zza, (GoogleApiClient) null);
            }
        }
    }

    public interface zza {
        boolean zza(String str, int i);
    }

    public interface zzb {
        byte[] zza();
    }

    public static class zzc {
    }

    private ClearcutLogger(Context context, int i, String str, String str2, String str3, boolean z, zzb zzb2, zze zze2, zzc zzc2, zza zza2) {
        this.zzg = context.getPackageName();
        this.zzh = zza(context);
        this.zzj = -1;
        this.zzi = str;
        this.zzk = null;
        this.zzl = null;
        this.zzm = true;
        this.zzo = zzb2;
        this.zzp = zze2;
        this.zzq = new zzc();
        this.zzn = 0;
        this.zzr = zza2;
        zzbq.zzb(true, "can't be anonymous with an upload account");
    }

    @KeepForSdk
    public static ClearcutLogger anonymousLogger(Context context, String str) {
        return new ClearcutLogger(context, -1, str, null, null, true, zzbfi.zza(context), zzi.zzd(), null, new zzbft(context));
    }

    private static int zza(Context context) {
        boolean z = false;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            Log.wtf("ClearcutLogger", "This can't happen.", e);
            return z;
        }
    }

    /* access modifiers changed from: private */
    public static int[] zzb(ArrayList<Integer> arrayList) {
        if (arrayList == null) {
            return null;
        }
        int[] iArr = new int[arrayList.size()];
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        int i2 = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            int intValue = ((Integer) obj).intValue();
            int i3 = i2 + 1;
            iArr[i2] = intValue;
            i2 = i3;
        }
        return iArr;
    }

    @KeepForSdk
    public final LogEventBuilder newEvent(byte[] bArr) {
        return new LogEventBuilder(this, bArr, (zza) null);
    }
}
