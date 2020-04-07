package com.btgame.ubsdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.btgame.data.business.UbSaTrackManager;
import com.btgame.onesdk.frame.BtSdkBusiness;
import com.btgame.onesdk.frame.eneity.Args;
import com.btgame.onesdk.frame.eneity.ChargeResult;
import com.btgame.onesdk.frame.eneity.SdkLoginCallBack;
import com.btgame.onesdk.frame.eneity.onesdk.GameRoleInfo;
import com.btgame.onesdk.frame.eneity.onesdk.SDKPaymentInfo;
import com.btgame.onesdk.frame.listener.OnTargetsdkListener;
import com.btgame.onesdk.share.ShareManager;
import com.btgame.sdk.util.DebugUtils;
import com.btgame.sdk.util.RunningType;
import com.btgame.seasdk.BtSeaSDKManager;
import com.btgame.seasdk.btcore.common.entity.RoleInfo.Builder;
import com.btgame.seasdk.btcore.common.entity.SdkPaymentInfo;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.BtsdkLog;
import com.btgame.seasdk.btcore.common.util.ContextUtil;
import com.btgame.seasdk.btcore.common.util.TimeUtils;
import com.btgame.seasdk.listener.SdkListener;
import com.btgame.webpay.constant.WebPayConfig;
import com.facebook.appevents.AppEventsConstants;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

public class UbSdkManager extends BtSdkBusiness {
    /* access modifiers changed from: private */
    public static OnTargetsdkListener onTargetsdkListener;
    public static SdkListener sdkListener = new SdkListener() {
        public void onInitSuccess() {
            UbSdkManager.onTargetsdkListener.onInitSuccess();
        }

        public void onInitFail(int i, String s) {
            UbSdkManager.onTargetsdkListener.onInitFail(s, i);
        }

        public void onLogin(String userId, String token, String account) {
            UbSdkManager.userId = userId;
            SdkLoginCallBack sdkLoginCallBack = new SdkLoginCallBack();
            sdkLoginCallBack.userId = userId;
            sdkLoginCallBack.token = token;
            Args args = new Args();
            args.arg1 = account;
            UbSdkManager.onTargetsdkListener.onLoginSuccess(sdkLoginCallBack, args);
        }

        public void onLoginFail() {
            UbSdkManager.onTargetsdkListener.onLoginFail("", -1);
        }

        public void onLogoutSuccess() {
            UbSdkManager.onTargetsdkListener.onLogoutSuccess();
        }

        public void onLogoutFail() {
            UbSdkManager.onTargetsdkListener.onLoginFail("", -1);
        }

        public void onExit(int i) {
            UbSdkManager.onTargetsdkListener.onExitSuccess();
        }

        public void onPaySuccess(String s) {
            UbSdkManager.onTargetsdkListener.onPaySuccess(s);
        }

        public void onPayFail(String s, String s1) {
            UbSdkManager.onTargetsdkListener.onPayFail(s, -35);
        }

        public void onShareSuccess() {
            if (ShareManager.listener != null) {
                ShareManager.listener.onComplete(null, 0, null);
            }
        }

        public void onShareCancel() {
            if (ShareManager.listener != null) {
                ShareManager.listener.onCancel(null, -1);
            }
        }

        public void onShareError() {
            if (ShareManager.listener != null) {
                ShareManager.listener.onError(null, -1, new RuntimeException("分享过程出错，请查看log"));
            }
        }
    };
    /* access modifiers changed from: private */
    public static String userId;

    public UbSdkManager(Context mCtx) {
        super(mCtx);
        String oneSdkUrl = BTResourceUtil.findStringByName(mCtx, "url_sea_onesdk");
        if (TextUtils.isEmpty(oneSdkUrl)) {
            oneSdkUrl = "https://sdk-sg-zwfz.ubeejoy.com";
        }
        DebugUtils.getInstance().setCustomDomain(oneSdkUrl);
        DebugUtils.getInstance().setDebug(RunningType.YangCaoServer);
    }

    public void targetsdkLogin(OnTargetsdkListener onTargetsdkListener2) {
        onTargetsdkListener = onTargetsdkListener2;
        BtSeaSDKManager.getInstance().login();
    }

    public void targetsdkLogout(OnTargetsdkListener onTargetsdkListener2) {
        onTargetsdkListener = onTargetsdkListener2;
        BtSeaSDKManager.getInstance().logout();
    }

    public void showToolFloat(Activity activity) {
    }

    public void removeToolFloat(Activity activity) {
    }

    public String getSDKVersionName() {
        return BtSeaSDKManager.getInstance().getSdkVersion();
    }

    public void targetSdkInit(OnTargetsdkListener onTargetsdkListener2) {
        onTargetsdkListener = onTargetsdkListener2;
        BtSeaSDKManager.getInstance().init(ContextUtil.getCurrentActivity());
    }

    public void enterGame(GameRoleInfo roleInfo) {
        sendGameInfo(roleInfo, "500231");
        updateRoleInfo(roleInfo);
        super.enterGame(roleInfo);
    }

    public void upgradRole(GameRoleInfo roleInfo) {
        updateRoleInfo(roleInfo);
        super.upgradRole(roleInfo);
    }

    public void createRole(GameRoleInfo roleInfo) {
        updateRoleInfo(roleInfo);
        sendGameInfo(roleInfo, "500230");
        super.createRole(roleInfo);
    }

    private void sendGameInfo(GameRoleInfo roleInfo, String eventId) {
        if (roleInfo != null) {
            Map<String, String> properties = new HashMap<>();
            properties.put("areaId", roleInfo.getServerId());
            properties.put("areaName", roleInfo.getServerName());
            properties.put("userId", userId);
            properties.put("charId", roleInfo.getRoleId());
            properties.put("userBirthday", "");
            properties.put("userLevel", roleInfo.getRoleLevel() + "");
            try {
                properties.put("roleRegTime", TimeUtils.getTime(roleInfo.getRoleCTime()));
            } catch (Exception e) {
                e.printStackTrace();
                BtsdkLog.m1430e("格式化创角时间失败");
            }
            properties.put("isVIP", "" + (roleInfo.getVipLevel() > 0 ? 1 : 0));
            properties.put("VIPLevel", roleInfo.getVipLevel() + "");
            UbSaTrackManager.trackEvent(eventId, properties);
        }
    }

    private void updateRoleInfo(GameRoleInfo roleInfo) {
        if (roleInfo != null) {
            BtSeaSDKManager.getInstance().updateRoleInfo(new Builder().serverId(roleInfo.getServerId()).serverName(roleInfo.getServerName()).userId(userId).roleId(roleInfo.getRoleId()).roleName(roleInfo.getRoleName()).roleLevel(roleInfo.getRoleLevel()).vipLevel(roleInfo.getVipLevel()).battleStrength(roleInfo.getPower()).build());
        }
    }

    public void sdkPay(SDKPaymentInfo paymentInfo) {
        if (!WebPayConfig.PAY_WEB_FLAG.equals(BTResourceUtil.findStringByName("btPayPlatform"))) {
            super.sdkPay(paymentInfo);
        } else {
            targetSDkPay(paymentInfo, new ChargeResult(), onTargetsdkListener);
        }
    }

    public void targetSDkPay(SDKPaymentInfo sdkPaymentInfo, ChargeResult chargeResult, OnTargetsdkListener onTargetsdkListener2) {
        onTargetsdkListener = onTargetsdkListener2;
        String purchaseType = AppEventsConstants.EVENT_PARAM_VALUE_NO;
        if (sdkPaymentInfo.args != null && !TextUtils.isEmpty(sdkPaymentInfo.args.arg1)) {
            purchaseType = sdkPaymentInfo.args.arg1;
            com.btgame.sdk.util.BtsdkLog.m1423d("游戏传过来的purchaseType为:" + purchaseType);
        }
        BtSeaSDKManager.getInstance().pay(ContextUtil.getCurrentActivity(), new SdkPaymentInfo.Builder().goodsId(sdkPaymentInfo.getGoodsId()).sku(sdkPaymentInfo.platformGoodsId).skuName(sdkPaymentInfo.getProductName()).amount(sdkPaymentInfo.getMoney() + "").currencyCode("USD").notifyUrl(chargeResult.notifyUrl).callBackInfo(sdkPaymentInfo.getCallBackStr()).gameOrderId(chargeResult.orderId).extraInfo(purchaseType).sign(chargeResult.extData != null ? chargeResult.extData.arg1 : null).build());
    }

    public void targetsdkExit(OnTargetsdkListener onTargetsdkListener2) {
        onTargetsdkListener = onTargetsdkListener2;
        BtSeaSDKManager.getInstance().onGameExit(ContextUtil.getCurrentActivity());
    }

    public boolean isexitbyExitView() {
        return true;
    }

    public void showUserCenter() {
    }

    public void onResume(Activity activity) {
        handlerToolFloat(activity, true);
        BtSeaSDKManager.getInstance().onResume(activity);
    }

    public void onPause(Activity activity) {
        handlerToolFloat(activity, false);
        BtSeaSDKManager.getInstance().onPause(activity);
    }

    public void onStop(Activity activity) {
        BtSeaSDKManager.getInstance().onStop(activity);
    }

    public void onStart(Activity activity) {
        BtSeaSDKManager.getInstance().onStart(activity);
    }

    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        BtSeaSDKManager.getInstance().onActivityResult(activity, requestCode, resultCode, data);
    }

    public void onNewIntent(Activity activity, Intent intent) {
    }

    public void onRestart(Activity activity) {
        BtSeaSDKManager.getInstance().onRestart(activity);
    }

    public void onDestroy(Activity activity) {
        BtSeaSDKManager.getInstance().onDestroy(activity);
        sdkDestroy();
    }

    public Context attachBaseContext(Context context) {
        return BtSeaSDKManager.getInstance().attachBaseContext(context);
    }

    public void onRequestPermissionsResult(Activity activity, int requestCode, String[] permissions, int[] grantResults) {
        BtSeaSDKManager.getInstance().onRequestPermissionsResult(activity, requestCode, permissions, grantResults);
    }

    public void onWindowFocusChanged(Activity activity, boolean hasFocus) {
        BtSeaSDKManager.getInstance().onWindowFocusChanged(activity, hasFocus);
    }

    public void onBackPressed(Activity activity) {
        BtSeaSDKManager.getInstance().onBackPressed(activity);
    }

    public String getDevJson() {
        return new Gson().toJson((Object) BtSeaSDKManager.getInstance().getDeviceProperties());
    }

    public void share(Activity activity, String shareUrl, String shareImagePath) {
        BtSeaSDKManager.getInstance().share(activity, shareUrl, shareImagePath);
    }

    public void rateUs(Activity activity) {
        BtSeaSDKManager.getInstance().rateUs(activity);
    }

    public void onFinishNewRoleTutorial() {
        BtSeaSDKManager.getInstance().onFinishNewRoleTutorial();
    }

    public void onObtainNewRolePack() {
        BtSeaSDKManager.getInstance().onObtainNewRolePack();
    }

    public void trackEvent(String eventName, String eventToken, Map<String, String> extendParams) {
        BtSeaSDKManager.getInstance().trackEvent(eventName, eventToken, extendParams);
    }
}
