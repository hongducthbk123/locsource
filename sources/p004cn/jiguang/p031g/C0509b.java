package p004cn.jiguang.p031g;

import android.content.Context;
import android.widget.Toast;

/* renamed from: cn.jiguang.g.b */
final class C0509b implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Context f569a;

    /* renamed from: b */
    final /* synthetic */ String f570b;

    C0509b(Context context, String str) {
        this.f569a = context;
        this.f570b = str;
    }

    public final void run() {
        Toast.makeText(this.f569a, this.f570b, 0).show();
    }
}
