package p004cn.jpush.android.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import p004cn.jiguang.api.JCoreInterface;
import p004cn.jiguang.p015d.p016a.C0389d;
import p004cn.jiguang.p015d.p017b.C0395a;
import p004cn.jiguang.p015d.p021d.C0450g;
import p004cn.jiguang.p031g.C0506a;

/* renamed from: cn.jpush.android.service.AlarmReceiver */
public class AlarmReceiver extends BroadcastReceiver {
    private static final String TAG = "AlarmReceiver";

    public void onReceive(Context context, Intent intent) {
        if (JCoreInterface.init(context.getApplicationContext(), false)) {
            if (C0389d.m339j(context)) {
                C0395a.m383a(context, false);
                return;
            }
            if (VERSION.SDK_INT >= 19) {
                C0506a.m976j(context.getApplicationContext());
            }
            C0450g.m659a();
            C0450g.m661b(context);
        }
    }
}
