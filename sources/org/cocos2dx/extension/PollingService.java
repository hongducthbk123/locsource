package org.cocos2dx.extension;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.p000v4.app.NotificationCompat.Builder;
import com.facebook.share.internal.ShareConstants;
import com.ubj.zwfzgp.C2023R;
import org.cocos2dx.lua.AppActivity;

public class PollingService extends Service {
    public static final String ACTION = "com.ryantang.service.PollingService";
    private Intent mIntent = null;
    private NotificationManager mManager;
    private Notification mNotification;

    class PollingThread extends Thread {
        PollingThread() {
        }

        public void run() {
            System.out.println("Polling...");
            PollingService.this.showNotification();
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        initNotifiManager();
    }

    public void onStart(Intent intent, int startId) {
        this.mIntent = intent;
        new PollingThread().start();
    }

    private void initNotifiManager() {
        this.mManager = (NotificationManager) getSystemService("notification");
    }

    /* access modifiers changed from: private */
    public void showNotification() {
        if (this.mIntent != null) {
            String title = this.mIntent.getStringExtra("title");
            String msg = this.mIntent.getStringExtra(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
            String extrInfo = this.mIntent.getStringExtra("extrInfo");
            int notifyId = this.mIntent.getIntExtra("notifyId", 0);
            Intent i = new Intent(this, AppActivity.class);
            if (extrInfo != null) {
                i.putExtra("extrInfo", extrInfo);
            }
            if (title != null) {
                i.putExtra("title", title);
            }
            if (msg != null) {
                i.putExtra(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, msg);
            }
            this.mNotification = new Builder(this).setContentTitle(title).setContentText(msg).setContentIntent(PendingIntent.getActivity(this, 0, i, 134217728)).setSmallIcon(C2023R.C2024drawable.icon).setWhen(System.currentTimeMillis()).setAutoCancel(true).build();
            this.mManager.notify(notifyId, this.mNotification);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        System.out.println("Service:onDestroy");
    }
}
