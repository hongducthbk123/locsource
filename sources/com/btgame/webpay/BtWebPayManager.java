package com.btgame.webpay;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.support.p000v4.util.ArrayMap;
import com.btgame.seasdk.btcore.common.constant.LifecycleEventType;
import com.btgame.seasdk.btcore.common.constant.StatusCode;
import com.btgame.seasdk.btcore.common.entity.PaymentInfo;
import com.btgame.seasdk.btcore.common.entity.RoleInfo;
import com.btgame.seasdk.btcore.common.event.LifecycleEvent;
import com.btgame.seasdk.btcore.common.event.SdkEvent;
import com.btgame.seasdk.btcore.common.event.SdkEventManager;
import com.btgame.seasdk.btcore.common.event.pay.SdkPayEvent;
import com.btgame.seasdk.btcore.common.event.pay.SdkPayResultEvent.Builder;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.BtsdkLog;
import com.btgame.seasdk.btcore.common.util.ContextUtil;
import com.btgame.seasdk.btcore.widget.LoadingDialog;
import com.btgame.seasdk.btcore.widget.webview.BtWebApi;
import com.btgame.seasdk.btcore.widget.webview.WebDialog;
import com.btgame.webpay.constant.WebPayConfig;
import java.math.BigDecimal;
import java.util.Map;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BtWebPayManager {
    private Activity curGameActivity;

    private static class BtWebPayManagerHolder {
        /* access modifiers changed from: private */
        public static final BtWebPayManager btWebPayManager = new BtWebPayManager();

        private BtWebPayManagerHolder() {
        }
    }

    private BtWebPayManager() {
        SdkEventManager.register(this);
        WebPayConfig.initConf();
    }

    public static BtWebPayManager getInstance() {
        return BtWebPayManagerHolder.btWebPayManager;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLifecycleEvent(LifecycleEvent lifecycleEvent) {
        LifecycleEventType lifecycleEventType = lifecycleEvent.getLifecycleEventType();
        Activity activity = lifecycleEvent.getActivity();
        switch (lifecycleEventType) {
            case onGameActivityCreate:
                this.curGameActivity = activity;
                return;
            case onGameActivityResume:
                this.curGameActivity = activity;
                return;
            case onGameActivityDestroy:
                if (this.curGameActivity == activity) {
                    this.curGameActivity = null;
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSdkPayEvent(SdkEvent sdkEvent) {
        if (sdkEvent instanceof SdkPayEvent) {
            final SdkPayEvent sdkPayEvent = (SdkPayEvent) sdkEvent;
            if (WebPayConfig.PAY_WEB_FLAG.equalsIgnoreCase(sdkPayEvent.getPlatform())) {
                Activity activity = sdkPayEvent.getActivity();
                RoleInfo roleInfo = sdkPayEvent.getRoleInfo();
                PaymentInfo paymentInfo = sdkPayEvent.getPaymentInfo();
                Map<String, String> params = new ArrayMap<>();
                params.put("appId", BTResourceUtil.getApplicationMetaData("btAppId") + "");
                params.put("gameName", BTResourceUtil.getAppName());
                params.put("serverId", roleInfo.getServerId());
                params.put("serverName", roleInfo.getServerName());
                params.put("userId", roleInfo.getUserId());
                params.put("roleId", roleInfo.getRoleId());
                params.put("roleName", roleInfo.getRoleName());
                params.put("goodsId", paymentInfo.getGoodsId());
                params.put("goodsName", paymentInfo.getSkuName());
                params.put("usdAmount", new BigDecimal(paymentInfo.getAmount()).multiply(new BigDecimal("100")).intValue() + "");
                params.put("purchaseType", paymentInfo.getExtraInfo());
                params.put("gameLang", BTResourceUtil.getAppLocale(ContextUtil.getApplicationContext()));
                params.put("device", "android");
                String url = WebPayConfig.buildUrl("", params);
                BtsdkLog.m1429d(url);
                WebDialog webDialog = new WebDialog(activity, url);
                webDialog.addJavascriptInterface(new BtWebApi(activity, webDialog.getWebView(), webDialog));
                webDialog.setOnDismissListener(new OnDismissListener() {
                    public void onDismiss(DialogInterface dialogInterface) {
                        BtWebPayManager.this.sendSdkPayResultEvent(StatusCode.PAY_FINISH, sdkPayEvent);
                    }
                });
                webDialog.show();
            }
        }
    }

    /* access modifiers changed from: private */
    public void sendSdkPayResultEvent(StatusCode statusCode, SdkPayEvent sdkPayEvent) {
        LoadingDialog.hiddenDialog();
        SdkEventManager.postEvent(new Builder().platform(WebPayConfig.PAY_WEB_FLAG).statusCode(statusCode).userId(sdkPayEvent.getRoleInfo().getUserId()).btOrderId(sdkPayEvent.getPaymentInfo().getBtOrderId()).paymentInfo(sdkPayEvent.getPaymentInfo()).build());
    }
}
