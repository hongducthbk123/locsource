package org.cocos2dx.extension;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.IBinder;
import android.util.Log;

public class NetworkStateService extends Service {
    public static final String NETWORKSTATE = "org.cocos2dx.extension.NetworkStateService.statechange";
    public static int networkStatus;
    /* access modifiers changed from: private */
    public ConnectivityManager connectivityManager;
    /* access modifiers changed from: private */
    public NetworkInfo info;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            Log.i("NetworkStateService: ", "onReceive");
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                NetworkStateService.this.connectivityManager = (ConnectivityManager) NetworkStateService.this.getSystemService("connectivity");
                NetworkStateService.this.info = NetworkStateService.this.connectivityManager.getActiveNetworkInfo();
                if (NetworkStateService.this.info == null || !NetworkStateService.this.info.isAvailable()) {
                    NetworkStateService.networkStatus = 0;
                    Intent it = new Intent();
                    it.putExtra("networkStatus", NetworkStateService.networkStatus);
                    it.setAction(NetworkStateService.NETWORKSTATE);
                    NetworkStateService.this.sendBroadcast(it);
                    return;
                }
                if (NetworkStateService.this.info.getTypeName().equals("WIFI")) {
                    NetworkStateService.networkStatus = 1;
                } else {
                    NetworkStateService.networkStatus = 2;
                }
                Intent it2 = new Intent();
                it2.putExtra("networkStatus", NetworkStateService.networkStatus);
                it2.setAction(NetworkStateService.NETWORKSTATE);
                NetworkStateService.this.sendBroadcast(it2);
            }
        }
    };

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(this.mReceiver, mFilter);
    }

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.mReceiver);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo[] info2 = ((ConnectivityManager) context.getSystemService("connectivity")).getAllNetworkInfo();
        if (info2 != null) {
            for (NetworkInfo state : info2) {
                if (state.getState() == State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }
}
