package com.btgame.seasdk;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

public class GameApplication extends Application {
    public void onCreate() {
        super.onCreate();
        BtSeaSDKManager.getInstance().onApplicationCreate(this);
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context base) {
        super.attachBaseContext(BtSeaSDKManager.getInstance().attachBaseContext(base));
        MultiDex.install(this);
    }
}
