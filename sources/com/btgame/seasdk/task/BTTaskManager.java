package com.btgame.seasdk.task;

import android.text.TextUtils;
import com.btgame.seasdk.btcore.common.constant.StatusCode;
import com.btgame.seasdk.btcore.common.constant.TrackEventType;
import com.btgame.seasdk.btcore.common.entity.DeviceProperties;
import com.btgame.seasdk.btcore.common.entity.PaymentInfo;
import com.btgame.seasdk.btcore.common.entity.RoleInfo;
import com.btgame.seasdk.btcore.common.event.LifecycleEvent;
import com.btgame.seasdk.btcore.common.event.SdkEventManager;
import com.btgame.seasdk.btcore.common.event.data.TrackEvent;
import com.btgame.seasdk.btcore.common.event.init.ReqInitEvent;
import com.btgame.seasdk.btcore.common.event.login.ThirdAccountEvent;
import com.btgame.seasdk.btcore.common.event.login.ThirdAccountResultEvent;
import com.btgame.seasdk.btcore.common.event.pay.CreateOrderEvent;
import com.btgame.seasdk.btcore.common.event.pay.CreateOrderResultEvent;
import com.btgame.seasdk.btcore.common.event.pay.NotifyServerEvent;
import com.btgame.seasdk.btcore.common.event.pay.NotifyServerResultEvent;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.ContextUtil;
import com.btgame.seasdk.btcore.common.util.SharedPreferencesUtils;
import com.btgame.seasdk.btcore.common.util.http.OkHttpCallBack;
import com.btgame.seasdk.btcore.common.util.http.OkHttpUtil;
import com.btgame.seasdk.task.constant.TaskConfig;
import com.btgame.seasdk.task.entity.OpType;
import com.btgame.seasdk.task.entity.request.AccountLoginData;
import com.btgame.seasdk.task.entity.request.AutoLoginData;
import com.btgame.seasdk.task.entity.request.BasePostData;
import com.btgame.seasdk.task.entity.request.BindMailData;
import com.btgame.seasdk.task.entity.request.ChangePwdData;
import com.btgame.seasdk.task.entity.request.CreateOrderData;
import com.btgame.seasdk.task.entity.request.GetMailCodeData;
import com.btgame.seasdk.task.entity.request.GuestLoginData;
import com.btgame.seasdk.task.entity.request.GuestLoginData.Builder;
import com.btgame.seasdk.task.entity.request.PayNotifyData;
import com.btgame.seasdk.task.entity.request.RegisterData;
import com.btgame.seasdk.task.entity.request.ReqInitData;
import com.btgame.seasdk.task.entity.request.RetrievePwdData;
import com.btgame.seasdk.task.entity.request.ThirdLoginData;
import com.btgame.seasdk.task.entity.response.BaseResult;
import com.btgame.seasdk.task.entity.response.CreateOrderResult;
import com.btgame.seasdk.task.entity.response.LoginResult;
import com.btgame.seasdk.task.entity.response.OpResult;
import com.btgame.seasdk.task.entity.response.PayBaseResult;
import com.btgame.seasdk.task.entity.response.PayNotifyResult;
import com.btgame.seasdk.task.entity.response.ReqInitResult;
import com.google.gson.Gson;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BTTaskManager {
    private Integer appId;
    private Gson gson;
    private Integer packageId;

    private static class BTTaskManagerHolder {
        /* access modifiers changed from: private */
        public static BTTaskManager btTaskManager = new BTTaskManager();

        private BTTaskManagerHolder() {
        }
    }

    private BTTaskManager() {
        this.gson = OkHttpUtil.getInstance(ContextUtil.getApplicationContext()).getGson();
        this.appId = Integer.valueOf(BTResourceUtil.getApplicationMetaIntData("btAppId"));
        this.packageId = BTResourceUtil.findIntegerByName("pay_packageid");
        SdkEventManager.register(this);
    }

    public static BTTaskManager getInstance() {
        return BTTaskManagerHolder.btTaskManager;
    }

    public DeviceProperties getDevice() {
        return new DeviceProperties(ContextUtil.getApplicationContext());
    }

    private GuestLoginData getGuestAccountData() {
        String guestAccount = SharedPreferencesUtils.getString(TaskConfig.LOGIN_SP_GUEST_ACCOUNT);
        return new Builder().account(guestAccount).identity(SharedPreferencesUtils.getString(TaskConfig.LOGIN_SP_GUEST_IDENTITY)).build();
    }

    /* access modifiers changed from: private */
    public void saveGuestInfo(LoginResult loginResult) {
        SharedPreferencesUtils.setString(TaskConfig.LOGIN_SP_GUEST_ACCOUNT, loginResult.getAccount());
        SharedPreferencesUtils.setString(TaskConfig.LOGIN_SP_GUEST_IDENTITY, loginResult.getIdentity());
    }

    /* access modifiers changed from: private */
    public void cleanGuestInfo() {
        SharedPreferencesUtils.setString(TaskConfig.LOGIN_SP_GUEST_ACCOUNT, null);
        SharedPreferencesUtils.setString(TaskConfig.LOGIN_SP_GUEST_IDENTITY, null);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLifecycleEvent(LifecycleEvent lifecycleEvent) {
        switch (lifecycleEvent.getLifecycleEventType()) {
            case onApplicationCreate:
                TaskConfig.initConfig(lifecycleEvent.getApplication());
                return;
            default:
                return;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGuestLoginEvent(ThirdAccountEvent loginEvent) {
        if (loginEvent.getPlatform().equals(TaskConfig.LOGIN_GUEST_FLAG)) {
            GuestLoginData guestAccountData = getGuestAccountData();
            String reqUrl = TaskConfig.URL_VISTORAUTO;
            if (!TextUtils.isEmpty(guestAccountData.getAccount()) && !TextUtils.isEmpty(guestAccountData.getIdentity())) {
                reqUrl = TaskConfig.URL_AUTOLOGIN;
            }
            postLoginData(guestAccountData, reqUrl);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBTRegister(RegisterData data) {
        postLoginData(data, TaskConfig.URL_REGISTER);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBTLoginEvent(AccountLoginData data) {
        postLoginData(data, TaskConfig.URL_ACCOUNTLOGIN);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAutoLoginEvent(AutoLoginData data) {
        postLoginData(data, TaskConfig.URL_AUTOLOGIN);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBTChangePwdEvent(ChangePwdData data) {
        postOpData(data, TaskConfig.URL_CHPW);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBTRetrievePwdEvent(RetrievePwdData data) {
        postOpData(data, TaskConfig.URL_FPWD_EMAIL);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBTGetMailCodeEvent(GetMailCodeData data) {
        postOpData(data, TaskConfig.URL_MAILCODE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBTBindMailEvent(BindMailData data) {
        postOpData(data, TaskConfig.URL_BINDMAIL);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onThirdLoginResultEvent(ThirdAccountResultEvent thirdAccountResultEvent) {
        switch (thirdAccountResultEvent.getStatusCode()) {
            case LOGIN_SUCCESS:
                postLoginData(new ThirdLoginData.Builder().platform(thirdAccountResultEvent.getPlatform()).thirdId(thirdAccountResultEvent.getThirdId()).thirdUserId(thirdAccountResultEvent.getPlatformUid()).thirdToken(thirdAccountResultEvent.getPlatformToken()).identity(thirdAccountResultEvent.getBindId()).extArgs(null).build(), TaskConfig.URL_THIRD_PART_LOGIN);
                return;
            default:
                return;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCreateOrderEvent(CreateOrderEvent createOrderEvent) {
        postCreateOrderData(createOrderEvent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPayResultEvent(NotifyServerEvent notifyServerEvent) {
        postPayNotifyData(notifyServerEvent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onInitEvent(ReqInitEvent reqInitEvent) {
        postInitData();
    }

    private void postInitData() {
        final ReqInitData data = new ReqInitData.Builder().appId(this.appId).device(getDevice()).packageId(this.packageId).build();
        String postData = this.gson.toJson((Object) data);
        OkHttpUtil.getInstance(ContextUtil.getApplicationContext()).postJsonDataAsyn(TaskConfig.buildUrl(TaskConfig.URL_INIT, data.getDevice()), postData, new OkHttpCallBack<ReqInitResult>() {
            public void onFailure() {
                SdkEventManager.postEvent(new ReqInitResult.Builder().res(new BaseResult(data.getOpType(), -1, "")).build());
            }

            public void onResponse(ReqInitResult reqInitResult) {
                if (reqInitResult == null) {
                    onFailure();
                    return;
                }
                reqInitResult.getRes().setOpType(data.getOpType());
                SdkEventManager.postEvent(reqInitResult);
            }
        }, ReqInitResult.class);
    }

    private void postLoginData(final BasePostData data, final String url) {
        data.setDevice(getDevice());
        data.setAppId(this.appId);
        OkHttpUtil.getInstance(ContextUtil.getApplicationContext()).postJsonDataAsyn(TaskConfig.buildUrl(url, data.getDevice()), this.gson.toJson((Object) data), new OkHttpCallBack<LoginResult>() {
            public void onFailure() {
                LoginResult loginResult = new LoginResult.Builder().result(new BaseResult(data.getOpType(), -1, "")).build();
                if (data instanceof ThirdLoginData) {
                    loginResult.setPlatform(((ThirdLoginData) data).getPlatform());
                }
                SdkEventManager.postEvent(loginResult);
                if (!TaskConfig.URL_REGISTER.equals(url) && OpType.OP_LOGIN_BT.equals(loginResult.getOpType())) {
                    SdkEventManager.postEvent(new TrackEvent.Builder().eventType(TrackEventType.UB_LOGIN_FAILED).code(Integer.valueOf(loginResult.getRes().getCode())).description(loginResult.getRes().getMsg()).build());
                }
            }

            public void onResponse(LoginResult loginResult) {
                String platform = null;
                String thirdUserId = null;
                if (data instanceof ThirdLoginData) {
                    platform = ((ThirdLoginData) data).getPlatform();
                    thirdUserId = ((ThirdLoginData) data).getThirdUserId();
                }
                if (loginResult == null) {
                    onFailure();
                    return;
                }
                loginResult.setOpType(data.getOpType());
                if (StatusCode.LOGIN_SUCCESS.getCode() == loginResult.getRes().getCode()) {
                    if (OpType.OP_LOGIN_GUEST.equals(data.getOpType())) {
                        BTTaskManager.this.saveGuestInfo(loginResult);
                        platform = TaskConfig.LOGIN_GUEST_FLAG;
                    }
                    if (!TextUtils.isEmpty(data.getIdentity()) && ((data instanceof RegisterData) || OpType.OP_LOGIN_THIRD.equals(data.getOpType()))) {
                        BTTaskManager.this.cleanGuestInfo();
                    }
                    if (OpType.OP_LOGIN_BT.equals(data.getOpType())) {
                        platform = TaskConfig.LOGIN_BT_FLAG;
                    }
                    if (TaskConfig.URL_REGISTER.equals(url)) {
                        SdkEventManager.postEvent(new TrackEvent(TrackEventType.UB_REGISTER_SUCCESS));
                    } else if (OpType.OP_LOGIN_BT.equals(data.getOpType())) {
                        SdkEventManager.postEvent(new TrackEvent(TrackEventType.UB_LOGIN_SUCCESS));
                    }
                } else if (!TaskConfig.URL_REGISTER.equals(url) && OpType.OP_LOGIN_BT.equals(loginResult.getOpType())) {
                    SdkEventManager.postEvent(new TrackEvent.Builder().eventType(TrackEventType.UB_LOGIN_FAILED).code(Integer.valueOf(loginResult.getRes().getCode())).description(loginResult.getRes().getMsg()).build());
                }
                loginResult.setPlatform(platform);
                loginResult.setThirdUserId(thirdUserId);
                SdkEventManager.postEvent(loginResult);
            }
        }, LoginResult.class);
    }

    private void postOpData(final BasePostData data, String url) {
        data.setDevice(getDevice());
        data.setAppId(this.appId);
        String postData = this.gson.toJson((Object) data);
        OkHttpUtil.getInstance(ContextUtil.getApplicationContext()).postJsonDataAsyn(TaskConfig.buildUrl(url, data.getDevice()), postData, new OkHttpCallBack<OpResult>() {
            public void onFailure() {
                SdkEventManager.postEvent(new OpResult(new BaseResult(data.getOpType(), -1, "")));
            }

            public void onResponse(OpResult opResult) {
                if (opResult == null) {
                    onFailure();
                    return;
                }
                opResult.getRes().setOpType(data.getOpType());
                SdkEventManager.postEvent(opResult);
            }
        }, OpResult.class);
    }

    private void postCreateOrderData(final CreateOrderEvent createOrderEvent) {
        RoleInfo roleInfo = createOrderEvent.getRoleInfo();
        PaymentInfo paymentInfo = createOrderEvent.getPaymentInfo();
        DeviceProperties deviceProperties = getDevice();
        String postData = this.gson.toJson((Object) new CreateOrderData.Builder().appId(this.appId).packageId(TaskConfig.PAY_PACKAGEID).userId(roleInfo.getUserId()).serverId(roleInfo.getServerId()).serverName(roleInfo.getServerName()).roleId(roleInfo.getRoleId()).roleName(roleInfo.getRoleName()).productCode(paymentInfo.getSku()).callBackInfo(paymentInfo.getCallBackInfo()).notifyUrl(paymentInfo.getNotifyUrl()).gameOrderId(paymentInfo.getGameOrderId()).payChannelId(400).sign(paymentInfo.getSign()).device(deviceProperties).build());
        OkHttpUtil.getInstance(ContextUtil.getApplicationContext()).postJsonDataAsynNormal(TaskConfig.buildPayUrl(TaskConfig.URL_GOOGLE_CREATEORDER, deviceProperties), postData, new OkHttpCallBack<CreateOrderResult>() {
            public void onFailure() {
                CreateOrderResult createOrderResult = new CreateOrderResult();
                PayBaseResult payBaseResult = new PayBaseResult();
                payBaseResult.setCode(-1);
                payBaseResult.setMsg("");
                createOrderResult.setRes(payBaseResult);
                BTTaskManager.this.handleCreataOrderResult(createOrderResult, createOrderEvent);
            }

            public void onResponse(CreateOrderResult createOrderResult) {
                if (createOrderResult == null) {
                    onFailure();
                } else {
                    BTTaskManager.this.handleCreataOrderResult(createOrderResult, createOrderEvent);
                }
            }
        }, CreateOrderResult.class);
    }

    /* access modifiers changed from: private */
    public void handleCreataOrderResult(CreateOrderResult createOrderResult, CreateOrderEvent createOrderEvent) {
        if (createOrderResult.getRes().getCode() == 0) {
            createOrderEvent.getPaymentInfo().setBtOrderId(createOrderResult.getData().getOrderId());
            SdkEventManager.postEvent(new CreateOrderResultEvent.Builder().statusCode(StatusCode.PAY_CREATRORDER_SUCCESS).activity(createOrderEvent.getActivity()).platform(createOrderEvent.getPlatform()).roleInfo(createOrderEvent.getRoleInfo()).paymentInfo(createOrderEvent.getPaymentInfo()).build());
            return;
        }
        SdkEventManager.postEvent(new CreateOrderResultEvent.Builder().statusCode(StatusCode.PAY_CREATRORDER_FAIL).activity(createOrderEvent.getActivity()).platform(createOrderEvent.getPlatform()).roleInfo(createOrderEvent.getRoleInfo()).paymentInfo(createOrderEvent.getPaymentInfo()).build());
    }

    private void postPayNotifyData(final NotifyServerEvent notifyServerEvent) {
        DeviceProperties deviceProperties = getDevice();
        String postData = this.gson.toJson((Object) new PayNotifyData.Builder().appId(this.appId).packageId(this.packageId).orderStatus(notifyServerEvent.getOrderStatus()).orderDesc(notifyServerEvent.getOrderDesc()).userId(notifyServerEvent.getUserId()).type(notifyServerEvent.getType()).serverId(notifyServerEvent.getServerId()).serverName(notifyServerEvent.getServerName()).roleId(notifyServerEvent.getRoleId()).roleName(notifyServerEvent.getRoleName()).orderId(notifyServerEvent.getBtOrderId()).orderData(notifyServerEvent.getOrderData()).orderStatus(notifyServerEvent.getOrderStatus()).orderDesc(notifyServerEvent.getOrderDesc()).sign(notifyServerEvent.getSign()).device(deviceProperties).build());
        OkHttpUtil.getInstance(ContextUtil.getApplicationContext()).postJsonDataAsynNormal(TaskConfig.buildPayUrl(TaskConfig.URL_GOOGLE_NOTIFY, deviceProperties), postData, new OkHttpCallBack<PayNotifyResult>() {
            public void onFailure() {
                PayBaseResult payBaseResult = new PayBaseResult();
                payBaseResult.setCode(-1);
                payBaseResult.setMsg("");
                BTTaskManager.this.handlePayNotifyResult(new PayNotifyResult.Builder().res(payBaseResult).build(), notifyServerEvent);
            }

            public void onResponse(PayNotifyResult payNotifyResult) {
                if (payNotifyResult == null) {
                    onFailure();
                } else {
                    BTTaskManager.this.handlePayNotifyResult(payNotifyResult, notifyServerEvent);
                }
            }
        }, PayNotifyResult.class);
    }

    /* access modifiers changed from: private */
    public void handlePayNotifyResult(PayNotifyResult payNotifyResult, NotifyServerEvent notifyServerEvent) {
        if (payNotifyResult.getRes().getCode() == 0) {
            SdkEventManager.postEvent(new NotifyServerResultEvent.Builder().platform(notifyServerEvent.getPlatform()).statusCode(StatusCode.PAY_NOTIFYSERVER_SUCCESS).userId(notifyServerEvent.getUserId()).btOrderId(notifyServerEvent.getBtOrderId()).paymentInfo(notifyServerEvent.getPaymentInfo()).orderData(notifyServerEvent.getOrderData()).orderStatus(notifyServerEvent.getOrderStatus()).roleInfo(notifyServerEvent.getRoleInfo()).build());
        } else {
            SdkEventManager.postEvent(new NotifyServerResultEvent.Builder().platform(notifyServerEvent.getPlatform()).statusCode(StatusCode.PAY_NOTIFYSERVER_FAIL).userId(notifyServerEvent.getUserId()).btOrderId(notifyServerEvent.getBtOrderId()).paymentInfo(notifyServerEvent.getPaymentInfo()).orderData(notifyServerEvent.getOrderData()).orderStatus(notifyServerEvent.getOrderStatus()).roleInfo(notifyServerEvent.getRoleInfo()).build());
        }
    }
}
