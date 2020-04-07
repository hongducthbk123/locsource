package p004cn.jiguang.p015d;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import p004cn.jiguang.p013b.C0380b;
import p004cn.jiguang.p015d.p021d.C0453j;
import p004cn.jiguang.p031g.p032a.C0507a;

/* renamed from: cn.jiguang.d.b */
final class C0394b implements ServiceConnection {
    C0394b() {
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        C0507a.m999a(C0380b.m216a(iBinder));
        if (C0385a.f198e != null) {
            C0453j.m665a().mo6568b(C0385a.f198e, "intent.INIT", new Bundle());
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
    }
}
