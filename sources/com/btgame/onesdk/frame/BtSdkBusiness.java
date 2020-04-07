package com.btgame.onesdk.frame;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.os.Handler;
import android.os.Message;
import android.support.p000v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.baitian.datasdk.BtDataSdkManager;
import com.baitian.datasdk.constants.Event;
import com.baitian.datasdk.constants.TopicField;
import com.baitian.datasdk.util.ContextUtil;
import com.baitian.datasdk.util.ResourceUtil;
import com.btgame.onesdk.PlatfromUtils;
import com.btgame.onesdk.frame.constants.Constants;
import com.btgame.onesdk.frame.eneity.AppChannelDefine;
import com.btgame.onesdk.frame.eneity.AppChannelDefineRespData;
import com.btgame.onesdk.frame.eneity.Args;
import com.btgame.onesdk.frame.eneity.ChargeData;
import com.btgame.onesdk.frame.eneity.ChargeResult;
import com.btgame.onesdk.frame.eneity.OneUserInfo;
import com.btgame.onesdk.frame.eneity.OrderInfo;
import com.btgame.onesdk.frame.eneity.OrderRespData;
import com.btgame.onesdk.frame.eneity.SdkLoginCallBack;
import com.btgame.onesdk.frame.eneity.SessionRespData;
import com.btgame.onesdk.frame.eneity.onesdk.GameRoleInfo;
import com.btgame.onesdk.frame.eneity.onesdk.LoginReusltInfo;
import com.btgame.onesdk.frame.eneity.onesdk.SDKInitInfo;
import com.btgame.onesdk.frame.eneity.onesdk.SDKPaymentInfo;
import com.btgame.onesdk.frame.listener.OnSDKListener;
import com.btgame.onesdk.frame.listener.OnTargetsdkListener;
import com.btgame.onesdk.frame.p044ui.SplashDialog;
import com.btgame.onesdk.frame.utils.AssetsUtil;
import com.btgame.onesdk.frame.utils.BuglyHelper;
import com.btgame.sdk.http.NetworkUtils;
import com.btgame.sdk.http.OkHttpCallBack;
import com.btgame.sdk.loadingui.SDKDialog;
import com.btgame.sdk.util.BtUtils;
import com.btgame.sdk.util.BtsdkLog;
import com.btgame.sdk.util.DebugUtils;
import com.btgame.sdk.util.FileUtil;
import com.facebook.appevents.AppEventsConstants;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@SuppressLint({"WrongConstant"})
public abstract class BtSdkBusiness implements ISDKInterface {
    private static final String Exit_Dialog_Cancel = "bt_exit_cancel_btn_text";
    private static final String Exit_Dialog_Comfirm = "bt_exit_comfirm_btn_text";
    private static final String Exit_Dialog_MSG = "bt_exit_msg";
    private static final String Exit_Dialog_Title = "bt_exit_title";
    private static final int SPLASH_MSG = 18;
    private static final int SPLASH_MSG_DISMISS = 19;
    private static long delayuploadCrash = 0;
    /* access modifiers changed from: private */
    public OneUserInfo currentUserInfo;
    /* access modifiers changed from: private */
    public IGetDataImpl iGetDataImpl;
    protected boolean isGetInitData;
    /* access modifiers changed from: private */
    public boolean isGetInitSuccess;
    /* access modifiers changed from: private */
    public boolean isInit;
    private boolean isLandScape;
    /* access modifiers changed from: private */
    public boolean isLogin;
    /* access modifiers changed from: private */
    public boolean isShowSplashFirst = true;
    private boolean isShowingSplash;
    /* access modifiers changed from: private */
    public boolean isTargetSDKInitSuccess;
    /* access modifiers changed from: private */
    public AppChannelDefine mAppChanelInfo;
    protected Context mCtx;
    /* access modifiers changed from: private */
    public SDKInitInfo mInitInfo;
    /* access modifiers changed from: private */
    public SDKDialog mSdkDialog;
    /* access modifiers changed from: private */
    public OnSDKListener mSdkListener;
    /* access modifiers changed from: private */
    public GameRoleInfo roleInfo;
    /* access modifiers changed from: private */
    public SplashDialog splashDialog;
    /* access modifiers changed from: private */
    @SuppressLint({"HandlerLeak"})
    public Handler splashHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 18) {
                BtSdkBusiness btSdkBusiness = BtSdkBusiness.this;
                Constants.getInstance();
                btSdkBusiness.showDialog("");
                BtSdkBusiness.this.splashHandler.sendEmptyMessageDelayed(19, 300);
                BtSdkBusiness.this.splashDialog.dismiss();
            } else if (msg.what == 19) {
                BtSdkBusiness.this.hideDialog();
                if (BtSdkBusiness.this.isShowSplashFirst) {
                    BtSdkBusiness.this.getPlatformInfo(BtSdkBusiness.this.mSdkListener);
                    return;
                }
                BtsdkLog.m1423d("splash dismiss");
                if (BtSdkBusiness.this.isTargetSDKInitSuccess) {
                    ((Activity) BtSdkBusiness.this.mCtx).runOnUiThread(new Runnable() {
                        public void run() {
                            if (BtSdkBusiness.this.mSdkListener != null) {
                                BtSdkBusiness.this.mSdkListener.onInit(0);
                            }
                        }
                    });
                } else {
                    ((Activity) BtSdkBusiness.this.mCtx).runOnUiThread(new Runnable() {
                        public void run() {
                            if (BtSdkBusiness.this.mSdkListener != null) {
                                BtSdkBusiness.this.mSdkListener.onInit(-12);
                            }
                        }
                    });
                }
            }
        }
    };
    OnTargetsdkListener targetsdkListener = new OnTargetsdkListener() {
        public void onInitSuccess() {
            BtSdkBusiness.this.isTargetSDKInitSuccess = true;
            BtDataSdkManager.getInstance(BtSdkBusiness.this.mCtx).sumbitBaseData(9, TopicField.TOPICINAME_PLATFORM_INIT_SUCCESS);
            BtDataSdkManager.getInstance(BtSdkBusiness.this.mCtx).submitBaseEventData(Event.EVENT_ID_INIT_SUCCEED, "", "", "");
            BtsdkLog.m1423d("Targetsdk init Success");
            BtSdkBusiness.this.isInit = true;
            if (!BtSdkBusiness.this.isShowSplashFirst) {
                BtSdkBusiness.this.showSplash();
            } else {
                ((Activity) BtSdkBusiness.this.mCtx).runOnUiThread(new Runnable() {
                    public void run() {
                        if (BtSdkBusiness.this.mSdkListener != null) {
                            BtSdkBusiness.this.mSdkListener.onInit(0);
                        }
                    }
                });
            }
        }

        public void onInitFail(String msg, final int statusCode) {
            BtsdkLog.m1423d("Targetsdk init fail statusCode=" + statusCode + ", msg=" + msg);
            BtSdkBusiness.this.isTargetSDKInitSuccess = false;
            BtDataSdkManager.getInstance(BtSdkBusiness.this.mCtx).submitBaseEventData(Event.EVENT_ID_INIT_FAILED, "SDK初始化失败", "", "");
            if (!BtSdkBusiness.this.isShowSplashFirst) {
                BtSdkBusiness.this.showSplash();
                return;
            }
            BuglyHelper.postCatchedException("Targetsdk init fail msg = " + msg + " ,statusCode = " + statusCode);
            ((Activity) BtSdkBusiness.this.mCtx).runOnUiThread(new Runnable() {
                public void run() {
                    if (BtSdkBusiness.this.mSdkListener != null) {
                        BtSdkBusiness.this.mSdkListener.onInit(statusCode);
                    }
                }
            });
        }

        public void onLoginSuccess(SdkLoginCallBack loginbackInfo, Args args) {
            BtDataSdkManager.getInstance(BtSdkBusiness.this.mCtx).submitBaseEventData(Event.EVENT_ID_SDK_LOGIN_SUCCEED, "", "", "");
            if (loginbackInfo != null) {
                BtsdkLog.m1423d("token = " + loginbackInfo.token);
                BtSdkBusiness.this.showDialog("");
                BtSdkBusiness.this.iGetDataImpl.getBtSession(BtSdkBusiness.this.mAppChanelInfo, loginbackInfo, args, new OkHttpCallBack<SessionRespData>() {
                    public void onFailure() {
                        BtDataSdkManager.getInstance(BtSdkBusiness.this.mCtx).submitBaseEventData(Event.EVENT_ID_GET_SESSION_FAILED, "", "", "");
                        BtSdkBusiness.this.hideDialog();
                        LoginReusltInfo reusltInfo = new LoginReusltInfo();
                        reusltInfo.desc = "未知原因，登录失败";
                        if (BtSdkBusiness.this.mSdkListener != null) {
                            BtSdkBusiness.this.mSdkListener.onLogin(reusltInfo, -22);
                        }
                        BtSdkBusiness.this.afterLoginSuccess();
                    }

                    public void onResponse(SessionRespData srd) {
                        BtDataSdkManager.getInstance(BtSdkBusiness.this.mCtx).submitBaseEventData(Event.EVENT_ID_GET_SESSION_SUCCEED, "", "", "");
                        BtSdkBusiness.this.hideDialog();
                        if (srd.result == null || srd.result.getResultCode() != 200 || srd.oneUserInfo == null) {
                            LoginReusltInfo resultInfo = new LoginReusltInfo();
                            resultInfo.desc = "未知原因，登录失败";
                            if (BtSdkBusiness.this.mSdkListener != null) {
                                BtSdkBusiness.this.mSdkListener.onLogin(resultInfo, -22);
                            }
                            String errorMsg = "unknow error";
                            if (srd.result != null) {
                                errorMsg = srd.result.getResultDescr();
                            }
                            BuglyHelper.postCatchedException("Get session fail , error msg = " + errorMsg);
                            return;
                        }
                        BtSdkBusiness.this.isLogin = true;
                        LoginReusltInfo reusltInfo = new LoginReusltInfo();
                        reusltInfo.btSessionId = srd.sessionId;
                        reusltInfo.desc = "登录成功";
                        reusltInfo.platfromId = Integer.valueOf(BtSdkBusiness.this.mAppChanelInfo.platformId).intValue();
                        reusltInfo.statusCode = 0;
                        BtSdkBusiness.this.currentUserInfo = srd.oneUserInfo;
                        if (BtSdkBusiness.this.mSdkListener != null) {
                            BtSdkBusiness.this.mSdkListener.onLogin(reusltInfo, 0);
                        }
                        BtSdkBusiness.this.afterLoginSuccess();
                    }
                });
                return;
            }
            onLoginFail("登录失败", -23);
        }

        public void onLoginFail(String msg, final int statusCode) {
            BtDataSdkManager.getInstance(BtSdkBusiness.this.mCtx).submitBaseEventData(Event.EVENT_ID_SDK_LOGIN_FAILED, "", "", "");
            BtsdkLog.m1423d("fail statusCode" + statusCode + ", msg=" + msg);
            BuglyHelper.postCatchedException("TargetSDK loginFail , PlatFormId =" + BtSdkBusiness.this.getTargetPlatformId() + " ,fail statusCode =  " + statusCode + " , fail msg" + msg);
            final LoginReusltInfo reusltInfo = new LoginReusltInfo();
            reusltInfo.desc = msg;
            reusltInfo.statusCode = statusCode;
            if (statusCode == 0) {
                reusltInfo.statusCode = -26;
            }
            ((Activity) BtSdkBusiness.this.mCtx).runOnUiThread(new Runnable() {
                public void run() {
                    if (BtSdkBusiness.this.mSdkListener != null) {
                        BtSdkBusiness.this.mSdkListener.onLogin(reusltInfo, statusCode);
                    }
                }
            });
        }

        public void onLogoutSuccess() {
            BtSdkBusiness.this.isLogin = false;
            ((Activity) BtSdkBusiness.this.mCtx).runOnUiThread(new Runnable() {
                public void run() {
                    if (BtSdkBusiness.this.mInitInfo.isSupportReStart()) {
                        BtSdkBusiness.this.restartGame("Logout success, the game is about to restart");
                    } else if (BtSdkBusiness.this.mSdkListener != null) {
                        BtSdkBusiness.this.mSdkListener.onLogout(0);
                    }
                }
            });
        }

        public void onLogoutFail(String msg, final int statusCode) {
            BtsdkLog.m1423d("fail statusCode" + statusCode + ", msg=" + msg);
            if (statusCode == 0) {
                throw new IllegalArgumentException("失败状态码不符合");
            }
            ((Activity) BtSdkBusiness.this.mCtx).runOnUiThread(new Runnable() {
                public void run() {
                    if (BtSdkBusiness.this.mSdkListener != null) {
                        BtSdkBusiness.this.mSdkListener.onLogout(statusCode);
                    }
                }
            });
        }

        public void onExitSuccess() {
            BtsdkLog.m1423d("退出SDK成功");
            BtSdkBusiness.this.isLogin = false;
            ((Activity) BtSdkBusiness.this.mCtx).runOnUiThread(new Runnable() {
                public void run() {
                    if (BtSdkBusiness.this.mSdkListener != null) {
                        BtSdkBusiness.this.mSdkListener.onExit(0);
                    }
                }
            });
        }

        public void onExitFail(String msg, final int statusCode) {
            BtsdkLog.m1423d("退出SDK失败-----");
            ((Activity) BtSdkBusiness.this.mCtx).runOnUiThread(new Runnable() {
                public void run() {
                    if (BtSdkBusiness.this.mSdkListener != null) {
                        BtSdkBusiness.this.mSdkListener.onExit(statusCode);
                    }
                }
            });
        }

        public void onPaySuccess(String platfromOreder) {
            ((Activity) BtSdkBusiness.this.mCtx).runOnUiThread(new Runnable() {
                public void run() {
                    if (BtSdkBusiness.this.mSdkListener != null) {
                        BtSdkBusiness.this.mSdkListener.onPay(0);
                    }
                }
            });
        }

        public void onPayFail(String msg, final int statusCode) {
            BtsdkLog.m1423d("fail statusCode" + statusCode + ", msg=" + msg);
            BuglyHelper.postCatchedException("TargetSDK onPayFail , PlatFormId =" + BtSdkBusiness.this.getTargetPlatformId() + " ,fail statusCode =  " + statusCode + " , fail msg" + msg);
            ((Activity) BtSdkBusiness.this.mCtx).runOnUiThread(new Runnable() {
                public void run() {
                    if (statusCode == 0) {
                        BtsdkLog.m1423d("errorcode wrong");
                        if (BtSdkBusiness.this.mSdkListener != null) {
                            BtSdkBusiness.this.mSdkListener.onPay(-36);
                        }
                    } else if (BtSdkBusiness.this.mSdkListener != null) {
                        BtSdkBusiness.this.mSdkListener.onPay(statusCode);
                    }
                }
            });
        }
    };

    public abstract String getSDKVersionName();

    public abstract boolean isexitbyExitView();

    public abstract void removeToolFloat(Activity activity);

    public abstract void showToolFloat(Activity activity);

    public abstract void targetSDkPay(SDKPaymentInfo sDKPaymentInfo, ChargeResult chargeResult, OnTargetsdkListener onTargetsdkListener);

    public abstract void targetSdkInit(OnTargetsdkListener onTargetsdkListener);

    public abstract void targetsdkExit(OnTargetsdkListener onTargetsdkListener);

    public abstract void targetsdkLogin(OnTargetsdkListener onTargetsdkListener);

    public abstract void targetsdkLogout(OnTargetsdkListener onTargetsdkListener);

    public BtSdkBusiness(Context mCtx2) {
        boolean z = true;
        this.mCtx = mCtx2;
        ContextUtil.init(mCtx2);
        FileUtil.init(mCtx2);
        this.iGetDataImpl = GetDataImpl.getIntance(mCtx2);
        if (mCtx2.getResources().getConfiguration().orientation != 2) {
            z = false;
        }
        this.isLandScape = z;
        this.mSdkDialog = new SDKDialog(mCtx2);
        this.isShowSplashFirst = BtUtils.getbooleanMeTaData(mCtx2, Constants.SPLASH_FLAG);
        BtDataSdkManager.getInstance(mCtx2);
    }

    public void sdkInited(SDKInitInfo initInfo, OnSDKListener sdkListener) {
        if (sdkListener == null) {
            BtsdkLog.m1423d("sdkListener == null");
            return;
        }
        this.mInitInfo = initInfo;
        this.mSdkListener = sdkListener;
        sdkOncreate();
        BtsdkLog.m1423d("isShowSplashFirst = " + this.isShowSplashFirst);
        if (this.isShowSplashFirst) {
            showSplash();
            return;
        }
        BtsdkLog.m1423d("先获取参数，进行初始化第三方渠道");
        getPlatformInfo(sdkListener);
    }

    /* access modifiers changed from: private */
    public void showSplash() {
        int time;
        this.isShowingSplash = true;
        List<String> assetsPics = AssetsUtil.getSplashFile(this.mCtx);
        if (assetsPics != null && assetsPics.size() > 0) {
            try {
                hideDialog();
                String splashTime = AssetsUtil.getVauleInConfig(this.mCtx, Constants.SPLASH_TIME_KEY);
                if (TextUtils.isEmpty(splashTime)) {
                    time = Constants.SPLASH_TIME;
                } else {
                    time = Integer.parseInt(splashTime);
                }
                int totalSplashTime = time * assetsPics.size();
                this.splashDialog = new SplashDialog(this.mCtx, assetsPics, (long) time);
                BtDataSdkManager.getInstance(this.mCtx).sumbitBaseData(7, TopicField.TOPICINAME_SPLASH);
                BtsdkLog.m1423d("begin to show the splash");
                this.splashDialog.show();
                this.splashHandler.sendEmptyMessageDelayed(18, (long) totalSplashTime);
            } catch (Exception e) {
                this.isShowingSplash = false;
                BtsdkLog.m1423d(e.getMessage());
            }
        } else if (this.isShowSplashFirst) {
            getPlatformInfo(this.mSdkListener);
        } else {
            BtsdkLog.m1423d("No splash dissmiss");
            if (this.isTargetSDKInitSuccess) {
                ((Activity) this.mCtx).runOnUiThread(new Runnable() {
                    public void run() {
                        if (BtSdkBusiness.this.mSdkListener != null) {
                            BtSdkBusiness.this.mSdkListener.onInit(0);
                        }
                    }
                });
            } else {
                ((Activity) this.mCtx).runOnUiThread(new Runnable() {
                    public void run() {
                        if (BtSdkBusiness.this.mSdkListener != null) {
                            BtSdkBusiness.this.mSdkListener.onInit(-12);
                        }
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public void doTargetSDKInit() {
        ((Activity) this.mCtx).runOnUiThread(new Runnable() {
            public void run() {
                BtsdkLog.m1423d("begin to init targetSdk");
                BtSdkBusiness.this.hideDialog();
                if (BtSdkBusiness.this.isGetInitData) {
                    BtSdkBusiness.this.targetSdkInit(BtSdkBusiness.this.targetsdkListener);
                }
            }
        });
    }

    public void beginGetPlatformInfo() {
    }

    /* access modifiers changed from: private */
    public void getPlatformInfo(final OnSDKListener sdkListener) {
        if (sdkListener == null) {
            BtsdkLog.m1423d("sdkListener = null");
            return;
        }
        beginGetPlatformInfo();
        BtDataSdkManager.getInstance(this.mCtx).submitBaseEventData(Event.EVENT_ID_SDK_INIT, "", "", "");
        this.mAppChanelInfo = new AppChannelDefine();
        this.mAppChanelInfo.oneId = BtUtils.getIntNoXMeTaData(this.mCtx, "btoneId");
        int btChannelId = BtUtils.getBtChannelId(this.mCtx);
        this.mAppChanelInfo.appExt1 = btChannelId == -1 ? AppEventsConstants.EVENT_PARAM_VALUE_NO : btChannelId + "";
        if (this.mAppChanelInfo.oneId == -1) {
            BtsdkLog.m1423d("oneId配置错误，请联系技术人员");
            sdkListener.onInit(-10);
            BuglyHelper.postCatchedException("oneId is wrong,please check the manifest!");
            BtDataSdkManager.getInstance(this.mCtx).submitBaseEventData(Event.EVENT_ID_INIT_FAILED, "oneId配置错误", "", "");
            return;
        }
        AppChannelDefine appChannelDefine = this.mAppChanelInfo;
        String targetPlatformId = getTargetPlatformId();
        appChannelDefine.platformId = targetPlatformId;
        if (targetPlatformId.equals(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
            BtsdkLog.m1423d("SDK中平台信息配置错误，请联系技术人员");
            sdkListener.onInit(-10);
            BuglyHelper.postCatchedException("oneId is 0,please check the manifest!");
            BtDataSdkManager.getInstance(this.mCtx).submitBaseEventData(Event.EVENT_ID_INIT_FAILED, "渠道ID配置错误", "", "");
            return;
        }
        if (!((Activity) this.mCtx).isFinishing() && !this.isShowingSplash) {
            showDialog("");
        }
        BtsdkLog.m1423d("begin to get PlatformInfo from SdkServe");
        if (!NetworkUtils.isNetworkConnected(this.mCtx)) {
            BtsdkLog.m1424e("No network ");
            BuglyHelper.postCatchedException("Cannot connect the network!");
        }
        GetDataImpl.getIntance(this.mCtx).setPlatFromVersion(getSDKVersionName());
        this.iGetDataImpl.getAppChannelDefine(this.mAppChanelInfo, new OkHttpCallBack<AppChannelDefineRespData>() {
            public void onFailure() {
                BtSdkBusiness.this.isGetInitSuccess = true;
                BtsdkLog.m1423d("get PlatformInfo fail because network");
                BtSdkBusiness.this.hideDialog();
                sdkListener.onInit(-11);
                BtDataSdkManager.getInstance(BtSdkBusiness.this.mCtx).submitBaseEventData(Event.EVENT_ID_INIT_FAILED, "获取平台信息失败", "", "");
            }

            public void onResponse(AppChannelDefineRespData respData) {
                BtSdkBusiness.this.isGetInitSuccess = true;
                BtsdkLog.m1423d("isGetInitSuccess = true");
                BtSdkBusiness.this.hideDialog();
                if (respData == null) {
                    BuglyHelper.postCatchedException("Get AppChannelDefineRespData fail , error msg = " + "response data is null");
                    if (BtSdkBusiness.this.mSdkListener != null) {
                        BtSdkBusiness.this.mSdkListener.onInit(-11);
                    }
                    BtDataSdkManager.getInstance(BtSdkBusiness.this.mCtx).submitBaseEventData(Event.EVENT_ID_INIT_FAILED, "获取平台信息失败", "", "");
                } else if (respData.getAppChannelDefine() == null) {
                    String errorMsg = "unknown error";
                    if (respData.getCommonResult() != null) {
                        errorMsg = respData.getCommonResult().getResultDescr();
                    }
                    BuglyHelper.postCatchedException("Get AppChannelDefineRespData fail , error msg = " + errorMsg);
                    if (BtSdkBusiness.this.mSdkListener != null) {
                        BtSdkBusiness.this.mSdkListener.onInit(-11);
                    }
                    BtDataSdkManager.getInstance(BtSdkBusiness.this.mCtx).submitBaseEventData(Event.EVENT_ID_INIT_FAILED, "获取平台信息失败", "", "");
                } else {
                    BtSdkBusiness.this.mAppChanelInfo = respData.getAppChannelDefine();
                    BtSdkBusiness.this.isGetInitData = true;
                    BtSdkBusiness.this.doTargetSDKInit();
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void sdkOncreate() {
    }

    public void sdkLogin() {
        BtDataSdkManager.getInstance(this.mCtx).sumbitBaseData(11, TopicField.TOPICINAME_SDK_LOGIN_PAGE);
        BtDataSdkManager.getInstance(this.mCtx).submitBaseEventData(Event.EVENT_ID_SDK_LOGIN, "", "", "");
        if (!this.isInit) {
            LoginReusltInfo loginReusltInfo = new LoginReusltInfo();
            loginReusltInfo.desc = "尚未初始化";
            if (this.mSdkListener != null) {
                this.mSdkListener.onLogin(loginReusltInfo, -21);
            }
            BuglyHelper.postCatchedException("there is not init before login");
            return;
        }
        BtsdkLog.m1423d("begin to login sdk");
        targetsdkLogin(this.targetsdkListener);
    }

    public void sdkLogout() {
        if (!this.isLogin) {
            BtsdkLog.m1423d("is not login");
            if (this.mSdkListener != null) {
                this.mSdkListener.onLogout(-1);
                return;
            }
            return;
        }
        targetsdkLogout(this.targetsdkListener);
    }

    public void onResume(Activity activity) {
        handlerToolFloat(activity, true);
    }

    public void onPause(Activity activity) {
        handlerToolFloat(activity, false);
    }

    public void onStop(Activity activity) {
    }

    public void onStart(Activity activity) {
    }

    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
    }

    public void onNewIntent(Activity activity, Intent intent) {
    }

    public void onRestart(Activity activity) {
    }

    public void onDestroy(Activity activity) {
        sdkDestroy();
    }

    public Context attachBaseContext(Context context) {
        return context;
    }

    public void onRequestPermissionsResult(Activity activity, int requestCode, String[] permissions, int[] grantResults) {
    }

    public void onWindowFocusChanged(Activity activity, boolean hasFocus) {
    }

    public void onBackPressed(Activity activity) {
    }

    public void setcurrentmCtx(Activity activity) {
        this.mCtx = activity;
    }

    /* access modifiers changed from: protected */
    public void afterLoginSuccess() {
    }

    /* access modifiers changed from: protected */
    public void showDialog(final String msg) {
        ((Activity) this.mCtx).runOnUiThread(new Runnable() {
            public void run() {
                if (BtSdkBusiness.this.mSdkDialog == null) {
                    BtSdkBusiness.this.mSdkDialog = new SDKDialog(BtSdkBusiness.this.mCtx);
                }
                BtSdkBusiness.this.mSdkDialog.showDialog(msg);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void hideDialog() {
        ((Activity) this.mCtx).runOnUiThread(new Runnable() {
            public void run() {
                if (BtSdkBusiness.this.mSdkDialog != null && BtSdkBusiness.this.mSdkDialog.isShowing()) {
                    BtSdkBusiness.this.mSdkDialog.dismiss();
                    BtSdkBusiness.this.mSdkDialog.cancel();
                    BtSdkBusiness.this.mSdkDialog = null;
                }
            }
        });
    }

    public String getTargetPlatformId() {
        return String.valueOf(BtUtils.getIntNoXMeTaData(this.mCtx, PlatfromUtils.PLATFROMID_KEY));
    }

    /* access modifiers changed from: protected */
    public void handlerToolFloat(Activity currentAct, boolean isShow) {
        this.mCtx = currentAct;
        if (isShow) {
            showToolFloat(currentAct);
        } else {
            removeToolFloat(currentAct);
        }
    }

    public OnTargetsdkListener getTargetsdkListener() {
        return this.targetsdkListener;
    }

    public void sdkPay(SDKPaymentInfo paymentInfo) {
        if (TextUtils.isEmpty(paymentInfo.getRoleId()) || TextUtils.isEmpty(paymentInfo.getProductName()) || TextUtils.isEmpty(paymentInfo.getOutOrderNo())) {
            Log.d(BtsdkLog.TAG, "提交的订单参数不足 必须参数没有找到");
            BtsdkLog.m1427p("payInfo：", paymentInfo.toString());
            if (this.mSdkListener != null) {
                this.mSdkListener.onPay(-31);
                return;
            }
            return;
        }
        BtsdkLog.m1427p("payInfo: ", paymentInfo.toString());
        requestOrderId(paymentInfo);
    }

    private void requestOrderId(final SDKPaymentInfo payInfo) {
        if (this.currentUserInfo == null || !this.isLogin) {
            BtsdkLog.m1423d("尚未登录");
            if (this.mSdkListener != null) {
                this.mSdkListener.onPay(-33);
                return;
            }
            return;
        }
        ChargeData chargeData = new ChargeData();
        chargeData.money = new BigDecimal(String.valueOf(payInfo.getMoney())).multiply(new BigDecimal("100")).intValue();
        chargeData.callBackInfo = payInfo.getCallBackStr();
        chargeData.roleId = payInfo.getRoleId();
        chargeData.roleName = this.roleInfo.getRoleName();
        chargeData.roleLevel = this.roleInfo.getRoleLevel();
        chargeData.serverName = this.roleInfo.getServerName();
        chargeData.serverId = this.roleInfo.getServerId();
        chargeData.goodsDesc = payInfo.getProductName();
        chargeData.goodsId = payInfo.getGoodsId();
        chargeData.userId = this.currentUserInfo.userId;
        Args args = new Args();
        args.arg1 = String.valueOf(payInfo.getRate());
        if (payInfo.getExStr() != null) {
            chargeData.filterParam = payInfo.getExStr();
        }
        if (payInfo.getOutOrderNo() != null) {
            chargeData.outOrderNo = payInfo.getOutOrderNo();
        }
        showDialog("");
        BtsdkLog.m1423d("OneSDK开始下单");
        this.iGetDataImpl.getBtOrderInfo(chargeData, this.currentUserInfo, args, new OkHttpCallBack<OrderRespData>() {
            public void onFailure() {
                BtsdkLog.m1423d("iGetDataImpl getBtOrderInfo onFail");
                BtSdkBusiness.this.hideDialog();
                if (BtSdkBusiness.this.mSdkListener != null) {
                    BtSdkBusiness.this.mSdkListener.onPay(-34);
                }
            }

            public void onResponse(OrderRespData orderRespData) {
                BtSdkBusiness.this.hideDialog();
                if (orderRespData != null && orderRespData.orderInfo != null) {
                    BtsdkLog.m1423d("getBtOrderInfo success = " + orderRespData.result.getResultDescr());
                    BtSdkBusiness.this.showSdkPay(orderRespData.orderInfo, orderRespData.extData, payInfo);
                } else if (BtSdkBusiness.this.mSdkListener != null) {
                    BtSdkBusiness.this.mSdkListener.onPay(-34);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void showSdkPay(OrderInfo orderInfo, Args extData, SDKPaymentInfo payInfo) {
        if (orderInfo == null) {
            getTargetsdkListener().onPayFail("can not get the orderId", -34);
            return;
        }
        ChargeResult chargeResult = new ChargeResult();
        chargeResult.produceDesc = orderInfo.goodsDesc;
        chargeResult.notifyUrl = orderInfo.notifyUrl;
        chargeResult.orderId = orderInfo.orderId;
        chargeResult.rate = orderInfo.rate;
        chargeResult.goodsName = orderInfo.currency;
        chargeResult.extData = extData;
        targetSDkPay(payInfo, chargeResult, this.targetsdkListener);
    }

    public void registerAccount(GameRoleInfo gameRoleInfo) {
        if (DebugUtils.getInstance().isLogFlag()) {
            Toast.makeText(this.mCtx, "register account userId =" + getCurrentUserId(), 0).show();
        }
        BtsdkLog.m1427p("registerAccount", gameRoleInfo.toString());
    }

    public void createRole(GameRoleInfo roleInfo2) {
        BtsdkLog.m1427p("threadname = ", Thread.currentThread().getName());
        BtsdkLog.m1427p("threadid = ", "" + Thread.currentThread().getId());
        this.roleInfo = roleInfo2;
        if (DebugUtils.getInstance().isLogFlag()) {
            Toast.makeText(this.mCtx, "create role rolename =" + roleInfo2.getRoleName(), 0).show();
        }
        BtsdkLog.m1427p("createRole ", roleInfo2.toString());
    }

    public void enterGame(GameRoleInfo roleInfo2) {
        this.roleInfo = roleInfo2;
        if (DebugUtils.getInstance().isLogFlag()) {
            Toast.makeText(this.mCtx, "rolename =" + roleInfo2.getRoleName() + "enter game", 0).show();
        }
        BtsdkLog.m1427p("startGame ", roleInfo2.toString());
    }

    public void upgradRole(GameRoleInfo roleInfo2) {
        this.roleInfo = roleInfo2;
        if (DebugUtils.getInstance().isLogFlag()) {
            Toast.makeText(this.mCtx, "role upgrade  roleLevel =" + roleInfo2.getRoleLevel(), 0).show();
        }
        BtsdkLog.m1427p("upgradRole ", roleInfo2.toString());
    }

    public boolean isLandScape() {
        return this.isLandScape;
    }

    public AppChannelDefine getplatformInfo() {
        return this.mAppChanelInfo;
    }

    public void setPlatformInfo(AppChannelDefine info) {
        this.mAppChanelInfo = info;
    }

    public boolean isLogin() {
        return this.isLogin;
    }

    public void setLogin(boolean isLogin2) {
        this.isLogin = isLogin2;
    }

    public OnSDKListener getmSdkListener() {
        return this.mSdkListener;
    }

    public OneUserInfo getCurrentUserInfo() {
        return this.currentUserInfo;
    }

    public GameRoleInfo getRoleInfo() {
        return this.roleInfo;
    }

    public void sdkExit() {
        String dialog_Msg;
        String dialog_Title;
        String positiveBtn_text;
        String cancleBtn_text;
        if (isexitbyExitView()) {
            targetsdkExit(this.targetsdkListener);
            return;
        }
        try {
            dialog_Msg = this.mCtx.getResources().getString(ResourceUtil.getStringId(this.mCtx, Exit_Dialog_MSG));
        } catch (NotFoundException e) {
            dialog_Msg = "确定退出游戏吗？";
        }
        try {
            dialog_Title = this.mCtx.getResources().getString(ResourceUtil.getStringId(this.mCtx, Exit_Dialog_Title));
        } catch (NotFoundException e2) {
            dialog_Title = "温馨提示";
        }
        try {
            positiveBtn_text = this.mCtx.getResources().getString(ResourceUtil.getStringId(this.mCtx, Exit_Dialog_Comfirm));
        } catch (NotFoundException e3) {
            positiveBtn_text = "是";
        }
        try {
            cancleBtn_text = this.mCtx.getResources().getString(ResourceUtil.getStringId(this.mCtx, Exit_Dialog_Cancel));
        } catch (NotFoundException e4) {
            cancleBtn_text = "否";
        }
        Builder builder = new Builder(this.mCtx, 5);
        builder.setMessage(dialog_Msg);
        builder.setTitle(dialog_Title);
        builder.setPositiveButton(positiveBtn_text, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                BtSdkBusiness.this.targetsdkExit(BtSdkBusiness.this.targetsdkListener);
            }
        });
        builder.setNegativeButton(cancleBtn_text, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (BtSdkBusiness.this.mSdkListener != null) {
                    BtSdkBusiness.this.mSdkListener.onExit(-37);
                }
            }
        });
        builder.create().show();
    }

    public void restartGame(String toastStr) {
        restartGame(toastStr, Constants.SPLASH_TIME);
    }

    private void restartGame(final String toastStr, int time) {
        if (time == 0) {
            time = Constants.SPLASH_TIME;
        }
        ((Activity) this.mCtx).runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(BtSdkBusiness.this.mCtx, toastStr, 0).show();
            }
        });
        new Handler().postDelayed(new Runnable() {
            public void run() {
                new Intent();
                ((AlarmManager) BtSdkBusiness.this.mCtx.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(1, System.currentTimeMillis() + 0, PendingIntent.getActivity(BtSdkBusiness.this.mCtx, 123456, BtSdkBusiness.this.mCtx.getPackageManager().getLaunchIntentForPackage(BtSdkBusiness.this.mCtx.getPackageName()), 268435456));
                System.exit(0);
            }
        }, (long) time);
    }

    public void sdkDestroy() {
        BtsdkLog.m1423d("进行sdkDestroy");
        ((Activity) this.mCtx).runOnUiThread(new Runnable() {
            public void run() {
                BtSdkBusiness.this.mSdkListener = null;
                BtSdkBusiness.this.mAppChanelInfo = null;
                if (BtSdkBusiness.this.mSdkDialog != null && BtSdkBusiness.this.mSdkDialog.isShowing()) {
                    BtSdkBusiness.this.mSdkDialog.dismiss();
                    BtSdkBusiness.this.mSdkDialog.cancel();
                }
                BtSdkBusiness.this.mSdkDialog = null;
                if (BtSdkBusiness.this.iGetDataImpl != null) {
                    BtSdkBusiness.this.iGetDataImpl.recyle();
                }
                BtSdkBusiness.this.isInit = false;
                BtSdkBusiness.this.isLogin = false;
                BtSdkBusiness.this.currentUserInfo = null;
                BtSdkBusiness.this.roleInfo = null;
                BtDataSdkManager.getInstance(BtSdkBusiness.this.mCtx).destory();
            }
        });
    }

    public String getDevJson() {
        return this.iGetDataImpl.getDevJson();
    }

    public void showBBSpage() {
    }

    public String getCurrentUserId() {
        BtsdkLog.m1423d("userId = " + this.currentUserInfo.userId);
        if (!this.isLogin || this.currentUserInfo == null) {
            return "";
        }
        return this.currentUserInfo.userId;
    }

    public void setRestartFlag(boolean isRestart) {
        this.mInitInfo.setSupportReStart(isRestart);
    }

    public void beforeGameStop(GameRoleInfo gameRoleInfo) {
        BtsdkLog.m1423d("beforeGameStop gameRoleInfo" + gameRoleInfo.toString());
        this.roleInfo = gameRoleInfo;
    }

    public boolean isShowUserCenterButton() {
        return BtUtils.getbooleanMeTaDataFalse(this.mCtx, "btisShowUserCenterBtn");
    }

    public void rateUs(Activity activity) {
    }

    public void onFinishNewRoleTutorial() {
    }

    public void onObtainNewRolePack() {
    }

    public void trackEvent(String eventName, String eventToken, Map<String, String> map) {
    }

    public void showWebBrowser(Activity activity, String url, boolean isUseBrowser) {
    }
}
