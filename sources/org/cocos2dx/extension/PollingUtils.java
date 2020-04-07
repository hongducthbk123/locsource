package org.cocos2dx.extension;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.p000v4.app.NotificationCompat;
import com.facebook.share.internal.ShareConstants;

public class PollingUtils {
    private static int timerId = 1;
    private static final int timerIdFrom = 1;

    public static void startPollingService(Context context, Class<?> cls, String action, long triggertime, long intervalSec, String title, String msg, String extrInfo, int notifyId) {
        AlarmManager manager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intent intent = new Intent(context, cls);
        intent.setAction(action);
        intent.putExtra("title", title);
        intent.putExtra(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, msg);
        intent.putExtra("extrInfo", extrInfo);
        intent.putExtra("notifyId", notifyId);
        int i = timerId;
        timerId = i + 1;
        manager.setRepeating(0, 1000 * triggertime, 1000 * intervalSec, PendingIntent.getService(context, i, intent, 134217728));
    }

    public static void stopPollingService(Context context, Class<?> cls, String action) {
        PendingIntent pendingIntent;
        AlarmManager manager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intent intent = new Intent(context, cls);
        intent.setAction(action);
        int curTimerId = 1;
        do {
            pendingIntent = PendingIntent.getService(context, curTimerId, intent, 536870912);
            curTimerId++;
            if (pendingIntent != null) {
                manager.cancel(pendingIntent);
                continue;
            }
        } while (pendingIntent != null);
        timerId = 1;
    }
}
