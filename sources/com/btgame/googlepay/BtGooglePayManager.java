package com.btgame.googlepay;

import android.app.Activity;
import android.content.DialogInterface.OnCancelListener;
import android.support.p000v4.util.ArrayMap;
import android.text.TextUtils;
import com.btgame.googlepay.constant.GpConfig;
import com.btgame.googlepay.util.InAppPurchaseProxy;
import com.btgame.googlepay.util.Purchase;
import com.btgame.seasdk.btcore.common.constant.LifecycleEventType;
import com.btgame.seasdk.btcore.common.constant.StatusCode;
import com.btgame.seasdk.btcore.common.entity.PaymentInfo;
import com.btgame.seasdk.btcore.common.entity.RoleInfo;
import com.btgame.seasdk.btcore.common.event.LifecycleEvent;
import com.btgame.seasdk.btcore.common.event.SdkEvent;
import com.btgame.seasdk.btcore.common.event.SdkEventManager;
import com.btgame.seasdk.btcore.common.event.UpdateRoleInfoEvent;
import com.btgame.seasdk.btcore.common.event.pay.CreateOrderEvent.Builder;
import com.btgame.seasdk.btcore.common.event.pay.CreateOrderResultEvent;
import com.btgame.seasdk.btcore.common.event.pay.NotifyServerEvent;
import com.btgame.seasdk.btcore.common.event.pay.NotifyServerResultEvent;
import com.btgame.seasdk.btcore.common.event.pay.SdkPayEvent;
import com.btgame.seasdk.btcore.common.event.pay.SdkPayResultEvent;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.ContextUtil;
import com.btgame.seasdk.btcore.common.util.http.OkHttpUtil;
import com.btgame.seasdk.btcore.widget.LoadingDialog;
import com.facebook.appevents.AppEventsConstants;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BtGooglePayManager {
    private Activity curGameActivity;
    private ArrayMap<String, InAppPurchaseProxy> inAppPurchaseProxyArrayMap;
    private RoleInfo mRoleInfo;
    private ArrayMap<String, Purchase> notifyingPurchaseMap;

    private static class BtGooglePayManagerHolder {
        /* access modifiers changed from: private */
        public static final BtGooglePayManager btGooglePayManager = new BtGooglePayManager();

        private BtGooglePayManagerHolder() {
        }
    }

    private BtGooglePayManager() {
        SdkEventManager.register(this);
        this.inAppPurchaseProxyArrayMap = new ArrayMap<>();
        this.notifyingPurchaseMap = new ArrayMap<>();
    }

    public static BtGooglePayManager getInstance() {
        return BtGooglePayManagerHolder.btGooglePayManager;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLifecycleEvent(LifecycleEvent lifecycleEvent) {
        LifecycleEventType lifecycleEventType = lifecycleEvent.getLifecycleEventType();
        Activity activity = lifecycleEvent.getActivity();
        switch (lifecycleEventType) {
            case onGameActivityCreate:
                InAppPurchaseProxy inAppPurchaseProxy = new InAppPurchaseProxy();
                this.curGameActivity = activity;
                this.inAppPurchaseProxyArrayMap.put(activity.toString(), inAppPurchaseProxy);
                inAppPurchaseProxy.onCreate(activity);
                return;
            case onGameActivityResume:
                this.curGameActivity = activity;
                ((InAppPurchaseProxy) this.inAppPurchaseProxyArrayMap.get(activity.toString())).onResume();
                return;
            case onGameActivityDestroy:
                if (this.curGameActivity == activity) {
                    this.curGameActivity = null;
                }
                ((InAppPurchaseProxy) this.inAppPurchaseProxyArrayMap.get(activity.toString())).onDestroy(activity);
                this.inAppPurchaseProxyArrayMap.remove(activity.toString());
                return;
            case onGameActivityResult:
                ((InAppPurchaseProxy) this.inAppPurchaseProxyArrayMap.get(activity.toString())).onActivityResult(lifecycleEvent.getRequestCode(), lifecycleEvent.getResultCode(), lifecycleEvent.getData());
                return;
            default:
                return;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSdkPayEvent(SdkEvent sdkEvent) {
        if (sdkEvent instanceof SdkPayEvent) {
            SdkPayEvent sdkPayEvent = (SdkPayEvent) sdkEvent;
            if (GpConfig.PAY_GP_FLAG.equalsIgnoreCase(sdkPayEvent.getPlatform())) {
                LoadingDialog.showDialog(sdkPayEvent.getActivity(), BTResourceUtil.findStringByName("tips_loading"), (OnCancelListener) null);
                SdkEventManager.postEvent(new Builder().platform(sdkPayEvent.getPlatform()).activity(sdkPayEvent.getActivity()).paymentInfo(sdkPayEvent.getPaymentInfo()).roleInfo(sdkPayEvent.getRoleInfo()).build());
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCreateOrderResultEvent(CreateOrderResultEvent createOrderResultEvent) {
        if (GpConfig.PAY_GP_FLAG.equalsIgnoreCase(createOrderResultEvent.getPlatform())) {
            if (StatusCode.PAY_CREATRORDER_SUCCESS.equals(createOrderResultEvent.getStatusCode())) {
                ((InAppPurchaseProxy) this.inAppPurchaseProxyArrayMap.get(createOrderResultEvent.getActivity().toString())).startPay(createOrderResultEvent.getActivity(), createOrderResultEvent.getRoleInfo(), createOrderResultEvent.getPaymentInfo());
                return;
            }
            sendSdkPayResultEvent(createOrderResultEvent.getStatusCode(), new NotifyServerResultEvent.Builder().userId(createOrderResultEvent.getRoleInfo().getUserId()).paymentInfo(createOrderResultEvent.getPaymentInfo()).build());
        }
    }

    public void sendNotifyServerEvent(StatusCode statusCode, RoleInfo roleInfo, PaymentInfo paymentInfo, List<Purchase> purchases) {
        PaymentInfo paymentInfo2;
        if (purchases == null) {
            NotifyServer(statusCode, roleInfo, paymentInfo, null, AppEventsConstants.EVENT_PARAM_VALUE_NO);
        } else if (paymentInfo != null) {
            Purchase purchase = (Purchase) purchases.get(0);
            paymentInfo.setPlatformOrderId(purchase.getOrderId());
            this.notifyingPurchaseMap.put(purchase.getOrderId(), purchase);
            NotifyServer(statusCode, roleInfo, paymentInfo, purchase, AppEventsConstants.EVENT_PARAM_VALUE_NO);
        } else {
            for (Purchase purchase2 : purchases) {
                String type = AppEventsConstants.EVENT_PARAM_VALUE_NO;
                if (!TextUtils.isEmpty(purchase2.getDeveloperPayload())) {
                    paymentInfo2 = (PaymentInfo) OkHttpUtil.getInstance(ContextUtil.getApplicationContext()).getGson().fromJson(purchase2.getDeveloperPayload(), PaymentInfo.class);
                    paymentInfo2.setPlatformOrderId(purchase2.getOrderId());
                } else if (this.mRoleInfo != null) {
                    paymentInfo2 = new PaymentInfo.Builder().build();
                    paymentInfo2.setPlatformOrderId(purchase2.getOrderId());
                    type = "1";
                } else {
                    return;
                }
                this.notifyingPurchaseMap.put(purchase2.getOrderId(), purchase2);
                NotifyServer(statusCode, roleInfo, paymentInfo2, purchase2, type);
            }
        }
    }

    private void NotifyServer(StatusCode statusCode, RoleInfo roleInfo, PaymentInfo paymentInfo, Purchase purchase, String type) {
        String btOrderId;
        String originalJson;
        String signature;
        String btOrderId2;
        String str = null;
        Integer orderStatus = null;
        String orderDesc = null;
        if (StatusCode.PAY_SUCCESS.equals(statusCode)) {
            orderStatus = GpConfig.PAY_STATUSCODE_SUCCESS;
            orderDesc = "Pay Success!";
        } else if (StatusCode.PAY_CANCEL.equals(statusCode)) {
            orderStatus = GpConfig.PAY_STATUSCODE_CANCEL;
            orderDesc = "Pay Cancel!";
        } else if (StatusCode.PAY_FAIL.equals(statusCode)) {
            orderStatus = GpConfig.PAY_STATUSCODE_FAIL;
            orderDesc = "Pay Fail!";
        }
        NotifyServerEvent.Builder platform = new NotifyServerEvent.Builder().platform(GpConfig.PAY_GP_FLAG);
        String userId = roleInfo == null ? this.mRoleInfo == null ? null : this.mRoleInfo.getUserId() : roleInfo.getUserId();
        NotifyServerEvent.Builder type2 = platform.userId(userId).type(type);
        String serverId = roleInfo == null ? this.mRoleInfo == null ? null : this.mRoleInfo.getServerId() : roleInfo.getServerId();
        NotifyServerEvent.Builder serverId2 = type2.serverId(serverId);
        String serverName = roleInfo == null ? this.mRoleInfo == null ? null : this.mRoleInfo.getServerName() : roleInfo.getServerName();
        NotifyServerEvent.Builder serverName2 = serverId2.serverName(serverName);
        String roleId = roleInfo == null ? this.mRoleInfo == null ? null : this.mRoleInfo.getRoleId() : roleInfo.getRoleId();
        NotifyServerEvent.Builder roleId2 = serverName2.roleId(roleId);
        String roleName = roleInfo == null ? this.mRoleInfo == null ? null : this.mRoleInfo.getRoleName() : roleInfo.getRoleName();
        NotifyServerEvent.Builder roleName2 = roleId2.roleName(roleName);
        if (paymentInfo == null) {
            btOrderId = null;
        } else {
            btOrderId = paymentInfo.getBtOrderId();
        }
        NotifyServerEvent.Builder paymentInfo2 = roleName2.btOrderId(btOrderId).roleInfo(roleInfo).paymentInfo(paymentInfo);
        if (purchase == null) {
            originalJson = null;
        } else {
            originalJson = purchase.getOriginalJson();
        }
        NotifyServerEvent.Builder orderDesc2 = paymentInfo2.orderData(originalJson).orderStatus(orderStatus).orderDesc(orderDesc);
        if (purchase == null) {
            signature = null;
        } else {
            signature = purchase.getSignature();
        }
        SdkEventManager.postEvent(orderDesc2.sign(signature).build());
        if (!StatusCode.PAY_SUCCESS.equals(statusCode) && roleInfo != null) {
            NotifyServerResultEvent.Builder platform2 = new NotifyServerResultEvent.Builder().platform(GpConfig.PAY_GP_FLAG);
            String userId2 = roleInfo == null ? this.mRoleInfo == null ? null : this.mRoleInfo.getUserId() : roleInfo.getUserId();
            NotifyServerResultEvent.Builder userId3 = platform2.userId(userId2);
            if (paymentInfo == null) {
                btOrderId2 = null;
            } else {
                btOrderId2 = paymentInfo.getBtOrderId();
            }
            NotifyServerResultEvent.Builder paymentInfo3 = userId3.btOrderId(btOrderId2).roleInfo(roleInfo).paymentInfo(paymentInfo);
            if (purchase != null) {
                str = purchase.getOriginalJson();
            }
            sendSdkPayResultEvent(statusCode, paymentInfo3.orderData(str).build());
        }
    }

    private void sendSdkPayResultEvent(StatusCode statusCode, NotifyServerResultEvent notifyServerResultEvent) {
        LoadingDialog.hiddenDialog();
        SdkEventManager.postEvent(new SdkPayResultEvent.Builder().platform(GpConfig.PAY_GP_FLAG).statusCode(statusCode).userId(notifyServerResultEvent.getUserId()).btOrderId(notifyServerResultEvent.getBtOrderId()).paymentInfo(notifyServerResultEvent.getPaymentInfo()).orderData(notifyServerResultEvent.getOrderData()).build());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNotifyServerResultEvent(NotifyServerResultEvent notifyServerResultEvent) {
        if (GpConfig.PAY_GP_FLAG.equalsIgnoreCase(notifyServerResultEvent.getPlatform())) {
            if (GpConfig.PAY_STATUSCODE_SUCCESS.equals(notifyServerResultEvent.getOrderStatus())) {
                if (notifyServerResultEvent.getRoleInfo() != null) {
                    sendSdkPayResultEvent(StatusCode.PAY_NOTIFYSERVER_SUCCESS.equals(notifyServerResultEvent.getStatusCode()) ? StatusCode.PAY_SUCCESS : notifyServerResultEvent.getStatusCode(), notifyServerResultEvent);
                }
                if (StatusCode.PAY_NOTIFYSERVER_SUCCESS.equals(notifyServerResultEvent.getStatusCode()) && this.curGameActivity != null) {
                    InAppPurchaseProxy inAppPurchaseProxy = (InAppPurchaseProxy) this.inAppPurchaseProxyArrayMap.get(this.curGameActivity.toString());
                    if (inAppPurchaseProxy != null) {
                        List<Purchase> purchases = new ArrayList<>();
                        purchases.add(this.notifyingPurchaseMap.remove(notifyServerResultEvent.getPaymentInfo().getPlatformOrderId()));
                        if (purchases.size() > 0) {
                            inAppPurchaseProxy.consumeAsync(purchases);
                        }
                    }
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateRoleInfoEvent(UpdateRoleInfoEvent updateRoleInfoEvent) {
        this.mRoleInfo = updateRoleInfoEvent.getRoleInfo();
    }
}
