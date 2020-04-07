package p004cn.jpush.android.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import p004cn.jpush.android.p040d.C0582e;

/* renamed from: cn.jpush.android.service.TagAliasReceiver */
public class TagAliasReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            C0582e.m1306c("TagAliasReceiver", "TagAliasOperator onReceive intent is null");
            return;
        }
        long longExtra = intent.getLongExtra("tagalias_seqid", -1);
        int intExtra = intent.getIntExtra("tagalias_errorcode", 0);
        if (longExtra != -1) {
            C0607d.m1397a().mo7000a(context.getApplicationContext(), longExtra, intExtra, intent);
        }
    }
}
