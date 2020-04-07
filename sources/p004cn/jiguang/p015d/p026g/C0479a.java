package p004cn.jiguang.p015d.p026g;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/* renamed from: cn.jiguang.d.g.a */
public final class C0479a {
    /* renamed from: a */
    public static C0481b m773a(Context context) {
        C0481b bVar = new C0481b();
        try {
            Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (registerReceiver == null) {
                return bVar;
            }
            bVar.f491a = registerReceiver.getIntExtra("level", -1);
            bVar.f492b = registerReceiver.getIntExtra("scale", -1);
            switch (registerReceiver.getIntExtra("status", -1)) {
                case 1:
                    bVar.f493c = 0;
                    break;
                case 2:
                    bVar.f493c = 2;
                    break;
                case 3:
                case 4:
                    bVar.f493c = 1;
                    break;
                case 5:
                    bVar.f493c = 3;
                    break;
            }
            bVar.f494d = registerReceiver.getIntExtra("voltage", -1);
            bVar.f495e = registerReceiver.getIntExtra("temperature", -1);
            return bVar;
        } catch (Throwable th) {
            return null;
        }
    }
}
