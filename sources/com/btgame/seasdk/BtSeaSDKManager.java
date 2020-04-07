package com.btgame.seasdk;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import com.btgame.appevents.AppEventsManager;
import com.btgame.appevents.constant.AppEventsConfig;
import com.btgame.seasdk.btcore.common.constant.EventType;
import com.btgame.seasdk.btcore.common.constant.LifecycleEventType;
import com.btgame.seasdk.btcore.common.constant.StatusCode;
import com.btgame.seasdk.btcore.common.constant.TrackEventType;
import com.btgame.seasdk.btcore.common.entity.PaymentInfo;
import com.btgame.seasdk.btcore.common.entity.RoleInfo;
import com.btgame.seasdk.btcore.common.entity.SdkPaymentInfo;
import com.btgame.seasdk.btcore.common.event.LifecycleEvent;
import com.btgame.seasdk.btcore.common.event.SdkCheckPermisionEvent;
import com.btgame.seasdk.btcore.common.event.SdkEvent;
import com.btgame.seasdk.btcore.common.event.SdkEventManager;
import com.btgame.seasdk.btcore.common.event.ShareEvent;
import com.btgame.seasdk.btcore.common.event.ShareResultEvent;
import com.btgame.seasdk.btcore.common.event.UpdateRoleInfoEvent;
import com.btgame.seasdk.btcore.common.event.data.TrackEvent;
import com.btgame.seasdk.btcore.common.event.init.ReqInitEvent.Builder;
import com.btgame.seasdk.btcore.common.event.init.ReqInitResultEvent;
import com.btgame.seasdk.btcore.common.event.login.SdkAccountEvent;
import com.btgame.seasdk.btcore.common.event.login.SdkAccountResultEvent;
import com.btgame.seasdk.btcore.common.event.pay.SdkPayEvent;
import com.btgame.seasdk.btcore.common.event.pay.SdkPayResultEvent;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.BtFileUtil;
import com.btgame.seasdk.btcore.common.util.BtUtils;
import com.btgame.seasdk.btcore.common.util.BtsdkLog;
import com.btgame.seasdk.btcore.common.util.ContextUtil;
import com.btgame.seasdk.btcore.common.util.permission.BTPermissionChecker;
import com.btgame.seasdk.btcore.entrance.BtGameSdk;
import com.btgame.seasdk.listener.SdkListener;
import java.util.Map;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BtSeaSDKManager extends BtGameSdk {
    private static final String TAG = "BtSeaSDKManager";
    private Boolean initSuccess;
    private Boolean passPermision;
    private RoleInfo roleInfo;
    private SdkListener sdkListener;

    private static class BtSeaSDKManagerHolder {
        /* access modifiers changed from: private */
        public static final BtSeaSDKManager btSeaSDKManager = new BtSeaSDKManager();

        private BtSeaSDKManagerHolder() {
        }
    }

    private BtSeaSDKManager() {
        this.initSuccess = null;
        this.passPermision = null;
        SdkEventManager.register(this);
    }

    public static BtSeaSDKManager getInstance() {
        return BtSeaSDKManagerHolder.btSeaSDKManager;
    }

    public SdkListener getSdkListener() {
        if (this.sdkListener != null) {
            return this.sdkListener;
        }
        Log.e(TAG, "setSdkListener must be call");
        return null;
    }

    public void setSdkListener(SdkListener sdkListener2) {
        this.sdkListener = sdkListener2;
    }

    public void init(Activity activity) {
        SdkEventManager.postEvent(new Builder().build());
        SdkEventManager.postEvent(new SdkCheckPermisionEvent.Builder(EventType.CHECK_PERMISSIONS_REQ).activity(activity).build());
        SdkEventManager.postEvent(new TrackEvent(TrackEventType.SDK_INIT_START));
    }

    public void login() {
        this.roleInfo = null;
        SdkEventManager.postEvent(new SdkAccountEvent(EventType.LOGIN_REQ));
    }

    public void logout() {
        SdkEventManager.postEvent(new SdkAccountEvent(EventType.LOGOUT_REQ));
    }

    public void updateRoleInfo(RoleInfo roleInfo2) {
        this.roleInfo = roleInfo2;
        SdkEventManager.postEvent(new UpdateRoleInfoEvent(roleInfo2));
    }

    public void pay(Activity activity, SdkPaymentInfo sdkPaymentInfo) {
        if (this.roleInfo == null) {
            BtsdkLog.m1430e("roleInfo is null, make sure you had call the updateRoleInfo method when user login");
            return;
        }
        SdkEventManager.postEvent(new SdkPayEvent.Builder().platform(BTResourceUtil.getApplicationMetaData("btPayPlatform")).activity(activity).roleInfo(this.roleInfo).paymentInfo(new PaymentInfo.Builder().goodsId(sdkPaymentInfo.getGoodsId()).sku(sdkPaymentInfo.getSku()).skuName(sdkPaymentInfo.getSkuName()).extraInfo(sdkPaymentInfo.getExtraInfo()).amount(sdkPaymentInfo.getAmount()).currencyCode(sdkPaymentInfo.getCurrencyCode()).callBackInfo(sdkPaymentInfo.getCallBackInfo()).notifyUrl(sdkPaymentInfo.getNotifyUrl()).gameOrderId(sdkPaymentInfo.getGameOrderId()).sign(sdkPaymentInfo.getSign()).build()).build());
    }

    public void share(Activity activity, String shareUrl, String shareImagePath) {
        SdkEventManager.postEvent(new ShareEvent.Builder().platform("FB").activity(activity).shareUrl(shareUrl).shareImagePath(shareImagePath).build());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSdkEvent(SdkEvent sdkEvent) {
        switch (sdkEvent.getEventType()) {
            case INIT_RES:
                ReqInitResultEvent reqInitResultEvent = (ReqInitResultEvent) sdkEvent;
                if (StatusCode.INIT_SUCCESS == reqInitResultEvent.getStatusCode()) {
                    SdkEventManager.postEvent(new TrackEvent(TrackEventType.SDK_INIT_SUCCESS));
                    this.initSuccess = Boolean.valueOf(true);
                } else {
                    this.initSuccess = Boolean.valueOf(false);
                }
                BtFileUtil.initLogFile(ContextUtil.getApplicationContext());
                if (this.initSuccess != null && this.initSuccess.booleanValue() && this.passPermision != null && this.passPermision.booleanValue()) {
                    getSdkListener().onInitSuccess();
                    this.passPermision = null;
                    this.initSuccess = null;
                    return;
                } else if (this.passPermision != null) {
                    getSdkListener().onInitFail(reqInitResultEvent.getStatusCode().getCode(), "init failed!");
                    this.passPermision = null;
                    this.initSuccess = null;
                    return;
                } else {
                    return;
                }
            case CHECK_PERMISSIONS_RES:
                BtFileUtil.initLogFile(ContextUtil.getApplicationContext());
                SdkCheckPermisionEvent sdkCheckPermisionEvent = (SdkCheckPermisionEvent) sdkEvent;
                if (sdkCheckPermisionEvent.getStatusCode() == 0) {
                    this.passPermision = Boolean.valueOf(true);
                } else if (BTPermissionChecker.getInstance().isPermissionForceAgree()) {
                    this.passPermision = Boolean.valueOf(false);
                } else {
                    this.passPermision = Boolean.valueOf(true);
                }
                if (this.passPermision.booleanValue()) {
                    SdkEventManager.postEvent(new TrackEvent(TrackEventType.CHECK_PERMISSION_PASS));
                } else {
                    SdkEventManager.postEvent(new TrackEvent(TrackEventType.CHECK_PERMISSION_FAIL));
                }
                if (this.initSuccess != null && this.initSuccess.booleanValue() && this.passPermision != null && this.passPermision.booleanValue()) {
                    getSdkListener().onInitSuccess();
                    this.passPermision = null;
                    this.initSuccess = null;
                    return;
                } else if (this.initSuccess != null) {
                    getSdkListener().onInitFail(sdkCheckPermisionEvent.getStatusCode(), sdkCheckPermisionEvent.getDes());
                    this.passPermision = null;
                    this.initSuccess = null;
                    return;
                } else {
                    return;
                }
            case LOGIN_RES:
                SdkAccountResultEvent loginResultEvent = (SdkAccountResultEvent) sdkEvent;
                if (StatusCode.LOGIN_SUCCESS.equals(loginResultEvent.getStatusCode())) {
                    getSdkListener().onLogin(loginResultEvent.getUserId(), loginResultEvent.getToken(), loginResultEvent.getAccount());
                    return;
                }
                updateRoleInfo(null);
                getSdkListener().onLoginFail();
                return;
            case LOGOUT_RES:
                getSdkListener().onLogoutSuccess();
                updateRoleInfo(null);
                return;
            case PAY_RES:
                SdkPayResultEvent sdkPayResultEvent = (SdkPayResultEvent) sdkEvent;
                if (StatusCode.PAY_SUCCESS.equals(sdkPayResultEvent.getStatusCode()) || StatusCode.PAY_FINISH.equals(sdkPayResultEvent.getStatusCode())) {
                    getSdkListener().onPaySuccess(sdkPayResultEvent.getPaymentInfo().getCallBackInfo());
                    return;
                } else {
                    getSdkListener().onPayFail(sdkPayResultEvent.getPaymentInfo().getCallBackInfo(), "");
                    return;
                }
            case SHARE_RES:
                ShareResultEvent shareResultEvent = (ShareResultEvent) sdkEvent;
                if (StatusCode.SHARE_SUCCESS.equals(shareResultEvent.getStatusCode())) {
                    getSdkListener().onShareSuccess();
                    return;
                } else if (StatusCode.SHARE_CANCEL.equals(shareResultEvent.getStatusCode())) {
                    getSdkListener().onShareCancel();
                    return;
                } else if (StatusCode.SHARE_FAIL.equals(shareResultEvent.getStatusCode())) {
                    getSdkListener().onShareError();
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    public void onApplicationCreate(Application application) {
        SdkEventManager.initEventListener(application);
        SdkEventManager.postEvent(new LifecycleEvent.Builder().lifecycleEventType(LifecycleEventType.onApplicationCreate).application(application).build());
    }

    public Context attachBaseContext(Context newBase) {
        return changeLocale(newBase, null);
    }

    public Context changeLocale(Context context, String defaultLocale) {
        return BTResourceUtil.changeLocale(context, defaultLocale);
    }

    public void onCreate(Activity activity, Bundle savedInstanceState) {
        SdkEventManager.postEvent(new LifecycleEvent.Builder().lifecycleEventType(LifecycleEventType.onGameActivityCreate).activity(activity).savedInstanceState(savedInstanceState).build());
    }

    public void onStart(Activity activity) {
        SdkEventManager.postEvent(new LifecycleEvent.Builder().lifecycleEventType(LifecycleEventType.onGameActivityStart).activity(activity).build());
    }

    public void onRestart(Activity activity) {
        SdkEventManager.postEvent(new LifecycleEvent.Builder().lifecycleEventType(LifecycleEventType.onGameActivityRestart).activity(activity).build());
    }

    public void onResume(Activity activity) {
        SdkEventManager.postEvent(new LifecycleEvent.Builder().lifecycleEventType(LifecycleEventType.onGameActivityResume).activity(activity).build());
    }

    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        SdkEventManager.postEvent(new LifecycleEvent.Builder().lifecycleEventType(LifecycleEventType.onGameActivityResult).activity(activity).requestCode(requestCode).resultCode(resultCode).data(data).build());
    }

    public void onPause(Activity activity) {
        SdkEventManager.postEvent(new LifecycleEvent.Builder().lifecycleEventType(LifecycleEventType.onGameActivityPause).activity(activity).build());
    }

    public void onStop(Activity activity) {
        SdkEventManager.postEvent(new LifecycleEvent.Builder().lifecycleEventType(LifecycleEventType.onGameActivityStop).activity(activity).build());
    }

    public void onDestroy(Activity activity) {
        SdkEventManager.postEvent(new LifecycleEvent.Builder().lifecycleEventType(LifecycleEventType.onGameActivityDestroy).activity(activity).build());
    }

    public void onGameExit(final Activity activity) {
        Spanned btnCancel = Html.fromHtml(BTResourceUtil.findStringByName("dl_btn_exit_cancel"));
        AlertDialog alertDialog = new AlertDialog.Builder(activity).setMessage(Html.fromHtml(BTResourceUtil.findStringByName("tips_exit"))).setNegativeButton(btnCancel, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton(Html.fromHtml(BTResourceUtil.findStringByName("dl_btn_exit_ok")), new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                SdkEventManager.postEvent(new LifecycleEvent.Builder().lifecycleEventType(LifecycleEventType.onGameExit).activity(activity).build());
                BtSeaSDKManager.this.getSdkListener().onExit(0);
            }
        }).create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public void onRequestPermissionsResult(Activity activity, int requestCode, String[] permissions, int[] grantResults) {
        SdkEventManager.postEvent(new LifecycleEvent.Builder().lifecycleEventType(LifecycleEventType.onRequestPermissionsResult).activity(activity).requestCode(requestCode).permissions(permissions).grantResults(grantResults).build());
    }

    public void onWindowFocusChanged(Activity activity, boolean hasFocus) {
        SdkEventManager.postEvent(new LifecycleEvent.Builder().lifecycleEventType(LifecycleEventType.onSdkWindowFocusChanged).activity(activity).hasFocus(hasFocus).build());
    }

    public void onBackPressed(Activity activity) {
        SdkEventManager.postEvent(new LifecycleEvent.Builder().lifecycleEventType(LifecycleEventType.onGameActivityBackPressed).activity(activity).build());
    }

    public String getSdkVersion() {
        return "v1.0";
    }

    public void rateUs(final Activity activity) {
        Spanned btnCancel = Html.fromHtml(BTResourceUtil.findStringByName("dl_btn_rateus_cancel"));
        AlertDialog alertDialog = new AlertDialog.Builder(activity).setMessage(Html.fromHtml(BTResourceUtil.findStringByName("tips_rateus"))).setNegativeButton(btnCancel, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                SdkEventManager.postEvent(new TrackEvent(TrackEventType.RATE_CANCEL));
            }
        }).setPositiveButton(Html.fromHtml(BTResourceUtil.findStringByName("dl_btn_rateus_ok")), new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                BtUtils.toGooglePlay(activity, BTResourceUtil.findStringByName("play_packagename"));
                SdkEventManager.postEvent(new TrackEvent(TrackEventType.RATE_CONFIRM));
            }
        }).create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public void onFinishNewRoleTutorial() {
        AppEventsManager.getInstance().sendAppEventsEvent(AppEventsConfig.ev_newRole_tutorial, AppEventsConfig.getEventToken(AppEventsConfig.ev_newRole_tutorial), null, null, null);
    }

    public void onObtainNewRolePack() {
        AppEventsManager.getInstance().sendAppEventsEvent(AppEventsConfig.ev_newRole_pack, AppEventsConfig.getEventToken(AppEventsConfig.ev_newRole_pack), null, null, null);
    }

    public void trackEvent(String eventName, String eventToken, Map<String, String> extendParams) {
        AppEventsManager.getInstance().trackEvent(eventName, eventToken, extendParams);
    }
}
