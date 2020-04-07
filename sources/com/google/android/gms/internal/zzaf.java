package com.google.android.gms.internal;

import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class zzaf {
    public static boolean zza;
    private static String zzb;

    static class zza {
        public static final boolean zza = zzaf.zza;
        private final List<zzag> zzb = new ArrayList();
        private boolean zzc = false;

        zza() {
        }

        /* access modifiers changed from: protected */
        public final void finalize() throws Throwable {
            if (!this.zzc) {
                zza("Request on the loose");
                zzaf.zzc("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
            }
        }

        public final synchronized void zza(String str) {
            long j;
            this.zzc = true;
            if (this.zzb.size() == 0) {
                j = 0;
            } else {
                j = ((zzag) this.zzb.get(this.zzb.size() - 1)).zzc - ((zzag) this.zzb.get(0)).zzc;
            }
            if (j > 0) {
                long j2 = ((zzag) this.zzb.get(0)).zzc;
                zzaf.zzb("(%-4d ms) %s", Long.valueOf(j), str);
                long j3 = j2;
                for (zzag zzag : this.zzb) {
                    long j4 = zzag.zzc;
                    zzaf.zzb("(+%-4d) [%2d] %s", Long.valueOf(j4 - j3), Long.valueOf(zzag.zzb), zzag.zza);
                    j3 = j4;
                }
            }
        }

        public final synchronized void zza(String str, long j) {
            if (this.zzc) {
                throw new IllegalStateException("Marker added to finished log");
            }
            this.zzb.add(new zzag(str, j, SystemClock.elapsedRealtime()));
        }
    }

    static {
        String str = "Volley";
        zzb = str;
        zza = Log.isLoggable(str, 2);
    }

    public static void zza(String str, Object... objArr) {
        if (zza) {
            Log.v(zzb, zzd(str, objArr));
        }
    }

    public static void zza(Throwable th, String str, Object... objArr) {
        Log.e(zzb, zzd(str, objArr), th);
    }

    public static void zzb(String str, Object... objArr) {
        Log.d(zzb, zzd(str, objArr));
    }

    public static void zzc(String str, Object... objArr) {
        Log.e(zzb, zzd(str, objArr));
    }

    private static String zzd(String str, Object... objArr) {
        String str2;
        if (objArr != null) {
            str = String.format(Locale.US, str, objArr);
        }
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        String str3 = "<unknown>";
        int i = 2;
        while (true) {
            if (i >= stackTrace.length) {
                str2 = str3;
                break;
            } else if (!stackTrace[i].getClass().equals(zzaf.class)) {
                String className = stackTrace[i].getClassName();
                String substring = className.substring(className.lastIndexOf(46) + 1);
                String substring2 = substring.substring(substring.lastIndexOf(36) + 1);
                String methodName = stackTrace[i].getMethodName();
                str2 = new StringBuilder(String.valueOf(substring2).length() + 1 + String.valueOf(methodName).length()).append(substring2).append(".").append(methodName).toString();
                break;
            } else {
                i++;
            }
        }
        return String.format(Locale.US, "[%d] %s: %s", new Object[]{Long.valueOf(Thread.currentThread().getId()), str2, str});
    }
}
