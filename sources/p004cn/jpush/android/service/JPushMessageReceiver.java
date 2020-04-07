package p004cn.jpush.android.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import p004cn.jpush.android.api.JPushMessage;
import p004cn.jpush.android.p037a.C0542a;

/* renamed from: cn.jpush.android.service.JPushMessageReceiver */
public abstract class JPushMessageReceiver extends BroadcastReceiver {
    public final void onReceive(Context context, Intent intent) {
        C0542a.m1122a().mo6774a(context.getApplicationContext(), this, intent);
    }

    public void onTagOperatorResult(Context context, JPushMessage jPushMessage) {
    }

    public void onCheckTagOperatorResult(Context context, JPushMessage jPushMessage) {
    }

    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
    }

    public void onMobileNumberOperatorResult(Context context, JPushMessage jPushMessage) {
    }
}
