package com.btgame.onesdk.frame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.btgame.onesdk.frame.eneity.onesdk.GameRoleInfo;
import com.btgame.onesdk.frame.eneity.onesdk.SDKPaymentInfo;
import java.util.Map;

public interface ISDKOpenInterface {
    Context attachBaseContext(Context context);

    void beforeGameStop(GameRoleInfo gameRoleInfo);

    void createRole(GameRoleInfo gameRoleInfo);

    void enterGame(GameRoleInfo gameRoleInfo);

    String getCurrentUserId();

    String getDevJson();

    int getRunningType();

    boolean isShowUserCenterButton();

    void onActivityResult(Activity activity, int i, int i2, Intent intent);

    void onBackPressed(Activity activity);

    void onDestroy(Activity activity);

    void onFinishNewRoleTutorial();

    void onNewIntent(Activity activity, Intent intent);

    void onObtainNewRolePack();

    void onPause(Activity activity);

    void onRequestPermissionsResult(Activity activity, int i, String[] strArr, int[] iArr);

    void onRestart(Activity activity);

    void onResume(Activity activity);

    void onStart(Activity activity);

    void onStop(Activity activity);

    void onWindowFocusChanged(Activity activity, boolean z);

    void rateUs(Activity activity);

    void sdkDestroy();

    void sdkExit();

    void sdkLogin();

    void sdkLogout();

    void sdkPay(SDKPaymentInfo sDKPaymentInfo);

    void setRestartFlag(boolean z);

    void setcurrentmCtx(Activity activity);

    void showBBSpage();

    @Deprecated
    void showUserCenter();

    void showWebBrowser(Activity activity, String str, boolean z);

    void trackEvent(String str, String str2, Map<String, String> map);

    void upgradRole(GameRoleInfo gameRoleInfo);
}
