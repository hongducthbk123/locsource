package p004cn.jiguang.p015d.p017b;

import android.content.Context;
import java.io.File;

/* renamed from: cn.jiguang.d.b.c */
final class C0416c extends Thread {

    /* renamed from: a */
    final /* synthetic */ Context f284a;

    C0416c(Context context) {
        this.f284a = context;
    }

    public final void run() {
        File filesDir = this.f284a.getFilesDir();
        if (filesDir != null) {
            File file = new File(filesDir, ".servicesaveFile");
            if (!file.exists()) {
                file.mkdirs();
            }
        }
    }
}
