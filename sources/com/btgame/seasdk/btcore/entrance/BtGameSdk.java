package com.btgame.seasdk.btcore.entrance;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.btgame.seasdk.btcore.common.entity.DeviceProperties;
import com.btgame.seasdk.btcore.common.entity.RoleInfo;
import com.btgame.seasdk.btcore.common.entity.SdkPaymentInfo;
import com.btgame.seasdk.btcore.common.util.ContextUtil;
import java.util.Map;

public abstract class BtGameSdk {
    public abstract Context attachBaseContext(Context context);

    public abstract String getSdkVersion();

    public abstract void login();

    public abstract void logout();

    public abstract void onActivityResult(Activity activity, int i, int i2, Intent intent);

    public abstract void onApplicationCreate(Application application);

    public abstract void onBackPressed(Activity activity);

    public abstract void onCreate(Activity activity, Bundle bundle);

    public abstract void onDestroy(Activity activity);

    public abstract void onFinishNewRoleTutorial();

    public abstract void onGameExit(Activity activity);

    public abstract void onObtainNewRolePack();

    public abstract void onPause(Activity activity);

    public abstract void onRequestPermissionsResult(Activity activity, int i, String[] strArr, int[] iArr);

    public abstract void onRestart(Activity activity);

    public abstract void onResume(Activity activity);

    public abstract void onStart(Activity activity);

    public abstract void onStop(Activity activity);

    public abstract void onWindowFocusChanged(Activity activity, boolean z);

    public abstract void pay(Activity activity, SdkPaymentInfo sdkPaymentInfo);

    public abstract void rateUs(Activity activity);

    public abstract void share(Activity activity, String str, String str2);

    public abstract void trackEvent(String str, String str2, Map<String, String> map);

    public abstract void updateRoleInfo(RoleInfo roleInfo);

    public DeviceProperties getDeviceProperties() {
        return new DeviceProperties(ContextUtil.getApplicationContext());
    }
}
