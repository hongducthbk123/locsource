package com.btgame.onesdk;

import com.btgame.onesdk.frame.IBtOneSdkAllManager;
import com.btgame.seasdk.BtSeaSDKManager;
import com.btgame.ubsdk.UbSdkManager;

public class BtOneSDKManager extends IBtOneSdkAllManager {
    private BtOneSDKManager() {
        IBtOneSdkAllManager.instance = this;
        IBtOneSdkAllManager.sdkReuqest = new UbSdkManager(IBtOneSdkAllManager.mCtx);
        BtSeaSDKManager.getInstance().setSdkListener(UbSdkManager.sdkListener);
        BtSeaSDKManager.getInstance().onCreate(IBtOneSdkAllManager.mCtx, null);
    }
}
