package com.btgame.onesdk.frame;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.util.Log;
import com.baitian.datasdk.util.ContextUtil;
import com.btgame.onesdk.frame.eneity.onesdk.GameRoleInfo;
import com.btgame.onesdk.frame.eneity.onesdk.SDKInitInfo;
import com.btgame.onesdk.frame.eneity.onesdk.SDKPaymentInfo;
import com.btgame.onesdk.frame.listener.OnSDKListener;
import com.btgame.onesdk.frame.utils.BuglyHelper;
import com.btgame.sdk.util.BtUtils;
import com.btgame.sdk.util.BtsdkLog;
import java.lang.reflect.Constructor;
import java.util.Map;

public class IBtOneSdkAllManager implements ISDKOpenInterface {
    protected static IBtOneSdkAllManager instance;
    protected static Activity mCtx;
    protected static ISDKInterface sdkReuqest;

    protected IBtOneSdkAllManager() {
        instance = this;
    }

    public static IBtOneSdkAllManager getInstance() {
        if (instance == null) {
            return new IBtOneSdkAllManager();
        }
        return instance;
    }

    public static void init(Activity activity) {
        mCtx = activity;
    }

    public static void sdkInit(SDKInitInfo initInfo, OnSDKListener sdkListener) {
        Log.d(BtsdkLog.TAG, "sdkInit");
        if (initInfo == null) {
            Log.d(BtsdkLog.TAG, "initInfo == null");
            return;
        }
        mCtx = (Activity) initInfo.getmCtx();
        ContextUtil.init(mCtx);
        BuglyHelper.initBugly(mCtx);
        if (sdkListener == null) {
            BuglyHelper.postCatchedException("sdkListener == null");
            Log.d(BtsdkLog.TAG, "sdkListener == null ");
            return;
        }
        if (sdkReuqest == null) {
            try {
                Constructor c0 = Class.forName("com.btgame.onesdk.BtOneSDKManager").getDeclaredConstructor(new Class[0]);
                c0.setAccessible(true);
                c0.newInstance(new Object[0]);
            } catch (Exception e) {
                BuglyHelper.postCatchedException((Throwable) e);
                e.printStackTrace();
                Log.d(BtsdkLog.TAG, "Exception in init SDKManager");
                sdkListener.onInit(-13);
                return;
            }
        }
        if (sdkReuqest == null) {
            BuglyHelper.postCatchedException("sdkReuqest is  null");
            throw new NullPointerException("sdkReuqest == null because  please newinstance sdkReqest in XXwanManager()");
        } else {
            sdkReuqest.sdkInited(initInfo, sdkListener);
        }
    }

    private void throwOnInstanceNull(IBtOneSdkAllManager manager) {
        if (manager == null) {
            throw new IllegalStateException("IBtOneSdkAllManager = null, please init instance frist !");
        }
    }

    public void sdkLogin() {
        throwOnInstanceNull(instance);
        if (sdkReuqest != null) {
            sdkReuqest.sdkLogin();
        }
    }

    public void sdkPay(SDKPaymentInfo paymentInfo) {
        throwOnInstanceNull(instance);
        if (sdkReuqest != null) {
            sdkReuqest.sdkPay(paymentInfo);
        }
    }

    public void sdkLogout() {
        throwOnInstanceNull(instance);
        if (sdkReuqest != null) {
            sdkReuqest.sdkLogout();
        }
    }

    public void sdkExit() {
        throwOnInstanceNull(instance);
        if (sdkReuqest != null) {
            sdkReuqest.sdkExit();
        }
    }

    public void sdkDestroy() {
        throwOnInstanceNull(instance);
        if (sdkReuqest != null) {
            sdkReuqest.sdkDestroy();
            sdkReuqest = null;
        }
    }

    public void showUserCenter() {
        throwOnInstanceNull(instance);
        if (sdkReuqest != null) {
            sdkReuqest.showUserCenter();
        }
    }

    public void showBBSpage() {
        throwOnInstanceNull(instance);
        if (sdkReuqest != null) {
            sdkReuqest.showBBSpage();
        }
    }

    public void onResume(Activity activity) {
        throwOnInstanceNull(instance);
        if (sdkReuqest != null) {
            sdkReuqest.onResume(activity);
        }
    }

    public void onPause(Activity activity) {
        if (sdkReuqest == null) {
            Log.d(BtsdkLog.TAG, "onPause---- After sdkDestroy ");
            instance = null;
            return;
        }
        sdkReuqest.onPause(activity);
    }

    public void onStart(Activity activity) {
        throwOnInstanceNull(instance);
        if (sdkReuqest != null) {
            sdkReuqest.onStart(activity);
        }
    }

    public void onStop(Activity activity) {
        if (sdkReuqest == null) {
            Log.d(BtsdkLog.TAG, "onStop---- After sdkDestroy ");
            instance = null;
            return;
        }
        sdkReuqest.onStop(activity);
    }

    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        throwOnInstanceNull(instance);
        if (sdkReuqest != null) {
            sdkReuqest.onActivityResult(activity, requestCode, resultCode, data);
        }
    }

    public void onNewIntent(Activity activity, Intent intent) {
        throwOnInstanceNull(instance);
        if (sdkReuqest != null) {
            sdkReuqest.onNewIntent(activity, intent);
        }
    }

    public Context attachBaseContext(Context context) {
        throwOnInstanceNull(instance);
        return sdkReuqest == null ? context : sdkReuqest.attachBaseContext(context);
    }

    public void onRequestPermissionsResult(Activity activity, int requestCode, String[] permissions, int[] grantResults) {
        throwOnInstanceNull(instance);
        if (sdkReuqest != null) {
            sdkReuqest.onRequestPermissionsResult(activity, requestCode, permissions, grantResults);
        }
    }

    public void onWindowFocusChanged(Activity activity, boolean hasFocus) {
        throwOnInstanceNull(instance);
        if (sdkReuqest != null) {
            sdkReuqest.onWindowFocusChanged(activity, hasFocus);
        }
    }

    public void onBackPressed(Activity activity) {
        throwOnInstanceNull(instance);
        if (sdkReuqest != null) {
            sdkReuqest.onBackPressed(activity);
        }
    }

    public void setcurrentmCtx(Activity activity) {
        throwOnInstanceNull(instance);
        if (sdkReuqest != null) {
            sdkReuqest.setcurrentmCtx(activity);
        }
    }

    public void upgradRole(GameRoleInfo gameInfo) {
        throwOnInstanceNull(instance);
        if (sdkReuqest != null) {
            sdkReuqest.upgradRole(gameInfo);
        }
    }

    public void createRole(GameRoleInfo gameInfo) {
        throwOnInstanceNull(instance);
        if (sdkReuqest != null) {
            sdkReuqest.createRole(gameInfo);
        }
    }

    public void enterGame(GameRoleInfo gameInfo) {
        throwOnInstanceNull(instance);
        if (sdkReuqest != null) {
            sdkReuqest.enterGame(gameInfo);
        }
    }

    public boolean isShowUserCenterButton() {
        throwOnInstanceNull(instance);
        if (sdkReuqest == null) {
            return false;
        }
        return sdkReuqest.isShowUserCenterButton();
    }

    public void rateUs(Activity activity) {
        throwOnInstanceNull(instance);
        if (sdkReuqest != null) {
            sdkReuqest.rateUs(activity);
        }
    }

    public void onFinishNewRoleTutorial() {
        throwOnInstanceNull(instance);
        if (sdkReuqest != null) {
            sdkReuqest.onFinishNewRoleTutorial();
        }
    }

    public void onObtainNewRolePack() {
        throwOnInstanceNull(instance);
        if (sdkReuqest != null) {
            sdkReuqest.onObtainNewRolePack();
        }
    }

    public void trackEvent(String eventName, String eventToken, Map<String, String> extendParams) {
        throwOnInstanceNull(instance);
        if (sdkReuqest != null) {
            sdkReuqest.trackEvent(eventName, eventToken, extendParams);
        }
    }

    public void showWebBrowser(Activity activity, String url, boolean isUseBrowser) {
        throwOnInstanceNull(instance);
        if (sdkReuqest != null) {
            sdkReuqest.showWebBrowser(activity, url, isUseBrowser);
        }
    }

    public String getDevJson() {
        throwOnInstanceNull(instance);
        if (sdkReuqest == null) {
            return null;
        }
        return sdkReuqest.getDevJson();
    }

    public int getRunningType() {
        int debugFlag = BtUtils.getIntNoXMeTaData(mCtx, "debugconfig");
        if (debugFlag == -1) {
            return 3;
        }
        return debugFlag;
    }

    public static void showNetSettingAlert() {
        Builder ab = new Builder(mCtx);
        ab.setMessage("网络未连接,请设置网络");
        ab.setPositiveButton("设置", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                IBtOneSdkAllManager.mCtx.startActivityForResult(new Intent("android.settings.SETTINGS"), 0);
            }
        });
        ab.setNegativeButton("退出", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        });
        ab.show();
    }

    public String getCurrentUserId() {
        throwOnInstanceNull(instance);
        if (sdkReuqest == null) {
            return null;
        }
        return sdkReuqest.getCurrentUserId();
    }

    public void setRestartFlag(boolean isRestart) {
        throwOnInstanceNull(instance);
        if (sdkReuqest != null) {
            sdkReuqest.setRestartFlag(isRestart);
        }
    }

    public void beforeGameStop(GameRoleInfo gameRoleInfo) {
        throwOnInstanceNull(instance);
        if (sdkReuqest != null) {
            sdkReuqest.beforeGameStop(gameRoleInfo);
        }
    }

    public void onRestart(Activity activity) {
        throwOnInstanceNull(instance);
        if (sdkReuqest != null) {
            sdkReuqest.onRestart(activity);
        }
    }

    public void onDestroy(Activity activity) {
        throwOnInstanceNull(instance);
        if (sdkReuqest != null) {
            sdkReuqest.onDestroy(activity);
        }
    }
}
