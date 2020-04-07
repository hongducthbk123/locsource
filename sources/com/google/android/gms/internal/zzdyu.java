package com.google.android.gms.internal;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;

final class zzdyu extends zzdyr {
    private final zzdys zza = new zzdys();

    zzdyu() {
    }

    public final void zza(Throwable th, PrintStream printStream) {
        th.printStackTrace(printStream);
        List<Throwable> zza2 = this.zza.zza(th, false);
        if (zza2 != null) {
            synchronized (zza2) {
                for (Throwable th2 : zza2) {
                    printStream.print("Suppressed: ");
                    th2.printStackTrace(printStream);
                }
            }
        }
    }

    public final void zza(Throwable th, PrintWriter printWriter) {
        th.printStackTrace(printWriter);
        List<Throwable> zza2 = this.zza.zza(th, false);
        if (zza2 != null) {
            synchronized (zza2) {
                for (Throwable th2 : zza2) {
                    printWriter.print("Suppressed: ");
                    th2.printStackTrace(printWriter);
                }
            }
        }
    }
}
