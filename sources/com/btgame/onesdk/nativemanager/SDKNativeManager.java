package com.btgame.onesdk.nativemanager;

import android.app.Activity;
import android.content.Context;
import com.btgame.onesdk.BtOneSDKManager;
import com.btgame.onesdk.PlatfromUtils;
import com.btgame.onesdk.frame.IBtOneSdkAllManager;
import com.btgame.onesdk.frame.eneity.onesdk.GameRoleInfo;
import com.btgame.onesdk.frame.eneity.onesdk.LoginReusltInfo;
import com.btgame.onesdk.frame.eneity.onesdk.SDKInitInfo;
import com.btgame.onesdk.frame.eneity.onesdk.SDKPaymentInfo;
import com.btgame.onesdk.frame.listener.OnSDKListener;
import com.btgame.sdk.util.BtUtils;
import com.btgame.sdk.util.BtsdkLog;

public class SDKNativeManager {
    public static Context mCtx;
    static OnSDKListener onSDKListener = new OnSDKListener() {
        public void onPay(int arg0) {
            if (arg0 == 0) {
                SDKNativeManager.callcppPay(true);
            } else {
                SDKNativeManager.callcppPay(false);
            }
        }

        public void onLogout(int arg0) {
            if (arg0 == 0) {
                SDKNativeManager.callcpplogout(true);
            } else {
                SDKNativeManager.callcpplogout(false);
            }
        }

        public void onLogin(LoginReusltInfo arg0, int arg1) {
            SDKNativeManager.callcpplogin(arg0, arg1, new PlatfromUtils().getplatformId(SDKNativeManager.mCtx));
        }

        public void onInit(int arg0) {
            if (arg0 == 0) {
                SDKNativeManager.callcppInit(true);
            } else {
                SDKNativeManager.callcppInit(false);
            }
        }

        public void onExit(int arg0) {
            if (arg0 == 0) {
                SDKNativeManager.callcppExit(true);
                BtOneSDKManager.getInstance().sdkDestroy();
                return;
            }
            SDKNativeManager.callcppExit(false);
        }
    };
    public String TAG;

    public static native void callcppDestory();

    public static native void callcppExit(boolean z);

    public static native void callcppInit(boolean z);

    public static native void callcppPay(boolean z);

    public static native void callcppUsercenter();

    public static native void callcpplogin(Object obj, int i, int i2);

    public static native void callcpplogout(boolean z);

    public static native void initNative();

    public static void init(Context Ctx) {
        mCtx = Ctx;
    }

    public static void initSDK() {
        ((Activity) mCtx).runOnUiThread(new Runnable() {
            public void run() {
                BtOneSDKManager.getInstance();
                IBtOneSdkAllManager.sdkInit(SDKNativeManager.getInitInfo(), SDKNativeManager.onSDKListener);
            }
        });
    }

    public static void loginSDK() {
        ((Activity) mCtx).runOnUiThread(new Runnable() {
            public void run() {
                BtOneSDKManager.getInstance().sdkLogin();
            }
        });
    }

    public static void cpplog(String msg) {
        BtsdkLog.m1423d("ccp msg = " + msg);
    }

    public static void paySDK(final SDKPaymentInfo sdkPaymentInfo) {
        ((Activity) mCtx).runOnUiThread(new Runnable() {
            public void run() {
                if (sdkPaymentInfo == null) {
                    BtsdkLog.m1423d("sdkPaymentInfo==null ");
                } else {
                    BtOneSDKManager.getInstance().sdkPay(sdkPaymentInfo);
                }
            }
        });
    }

    public static void logoutSDK() {
        ((Activity) mCtx).runOnUiThread(new Runnable() {
            public void run() {
                BtOneSDKManager.getInstance().sdkLogout();
            }
        });
    }

    public static void exitSDK() {
        ((Activity) mCtx).runOnUiThread(new Runnable() {
            public void run() {
                BtOneSDKManager.getInstance().sdkExit();
            }
        });
    }

    public static void showUserCenter() {
        ((Activity) mCtx).runOnUiThread(new Runnable() {
            public void run() {
                BtOneSDKManager.getInstance().showUserCenter();
            }
        });
    }

    public static void showBBSpage() {
        ((Activity) mCtx).runOnUiThread(new Runnable() {
            public void run() {
                BtOneSDKManager.getInstance().showBBSpage();
            }
        });
    }

    public static int getRunningType() {
        return BtUtils.getIntNoXMeTaData(mCtx, "debugconfig");
    }

    private static void setIsrestartFlag(boolean issupportRestart) {
        BtOneSDKManager.getInstance().setRestartFlag(issupportRestart);
    }

    public static void upgradeRoleinfo(final int type, final GameRoleInfo roleInfo) {
        ((Activity) mCtx).runOnUiThread(new Runnable() {
            public void run() {
                switch (type) {
                    case 1:
                        BtOneSDKManager.getInstance().createRole(roleInfo);
                        return;
                    case 2:
                        BtOneSDKManager.getInstance().enterGame(roleInfo);
                        return;
                    case 3:
                        BtOneSDKManager.getInstance().upgradRole(roleInfo);
                        return;
                    default:
                        return;
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public static SDKInitInfo getInitInfo() {
        SDKInitInfo info = new SDKInitInfo();
        info.setmCtx(mCtx);
        info.setSupportReStart(false);
        return info;
    }

    public static void onDestory() {
        BtOneSDKManager.getInstance().sdkDestroy();
        callcppDestory();
        mCtx = null;
    }

    public static String getDevJson() {
        return BtOneSDKManager.getInstance().getDevJson();
    }

    public static int getplatformId() {
        return new PlatfromUtils().getplatformId(mCtx);
    }

    public static void sdklog() {
        BtsdkLog.m1423d("Cpp call android test");
    }
}
