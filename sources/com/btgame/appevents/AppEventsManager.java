package com.btgame.appevents;

import android.content.Context;
import android.text.TextUtils;
import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustEvent;
import com.btgame.appevents.constant.AppEventsConfig;
import com.btgame.onesdk.btadjust.AdjustImpl;
import com.btgame.seasdk.btcore.common.constant.StatusCode;
import com.btgame.seasdk.btcore.common.entity.RoleInfo;
import com.btgame.seasdk.btcore.common.event.AppEventsEvent;
import com.btgame.seasdk.btcore.common.event.AppEventsEvent.Builder;
import com.btgame.seasdk.btcore.common.event.LifecycleEvent;
import com.btgame.seasdk.btcore.common.event.SdkEvent;
import com.btgame.seasdk.btcore.common.event.SdkEventManager;
import com.btgame.seasdk.btcore.common.event.ShareResultEvent;
import com.btgame.seasdk.btcore.common.event.UpdateRoleInfoEvent;
import com.btgame.seasdk.btcore.common.event.init.ReqInitEvent;
import com.btgame.seasdk.btcore.common.event.login.SdkAccountResultEvent;
import com.btgame.seasdk.btcore.common.event.login.ThirdAccountEvent;
import com.btgame.seasdk.btcore.common.event.pay.SdkPayResultEvent;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.BtFileUtil;
import com.btgame.seasdk.btcore.common.util.BtUtils;
import com.btgame.seasdk.btcore.common.util.BtsdkLog;
import com.btgame.seasdk.btcore.common.util.ContextUtil;
import com.btgame.seasdk.btcore.common.util.SharedPreferencesUtils;
import com.btgame.seasdk.btcore.common.util.TimeUtils;
import com.btgame.seasdk.task.constant.TaskConfig;
import java.util.Map;
import java.util.Map.Entry;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class AppEventsManager {
    private RoleInfo mRoleInfo;
    private String roleId;
    private String userId;

    private static class AppEventsManagerHolder {
        public static final AppEventsManager appEventsManager = new AppEventsManager();

        private AppEventsManagerHolder() {
        }
    }

    public AppEventsManager() {
        AppEventsConfig.initConfig();
        SdkEventManager.register(this);
    }

    public static AppEventsManager getInstance() {
        return AppEventsManagerHolder.appEventsManager;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLifecycleEvent(LifecycleEvent lifecycleEvent) {
        if (AppEventsConfig.isLogEvent) {
            switch (lifecycleEvent.getLifecycleEventType()) {
                case onApplicationCreate:
                    sendAppEventsEvent(AppEventsConfig.ev_activateApp, AppEventsConfig.getEventToken(AppEventsConfig.ev_activateApp), null, null, null);
                    return;
                case onGameActivityResume:
                    AdjustImpl.getInstance().onResume();
                    return;
                case onGameActivityPause:
                    AdjustImpl.getInstance().onPause();
                    return;
                default:
                    return;
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSdkEvent(SdkEvent sdkEvent) {
        if (AppEventsConfig.isLogEvent) {
            switch (sdkEvent.getEventType()) {
                case LOGIN_RES:
                    SdkAccountResultEvent loginResultEvent = (SdkAccountResultEvent) sdkEvent;
                    this.userId = loginResultEvent.getUserId();
                    if (!StatusCode.LOGIN_SUCCESS.equals(loginResultEvent.getStatusCode())) {
                        return;
                    }
                    if (loginResultEvent.isFirstJoin()) {
                        sendAppEventsEvent(AppEventsConfig.ev_Registration, AppEventsConfig.getEventToken(AppEventsConfig.ev_Registration), null, null, null);
                        return;
                    }
                    String eventName = AppEventsConfig.buildNDaysLoginEventName(TimeUtils.daysBetween(loginResultEvent.getFirstJoinDate(), System.currentTimeMillis()));
                    if (AppEventsConfig.ev_events_tokens.containsKey(eventName) && !SharedPreferencesUtils.getBoolean(this.userId + "_" + eventName)) {
                        SharedPreferencesUtils.setBoolean(this.userId + "_" + eventName, true);
                        sendAppEventsEvent(eventName, AppEventsConfig.getEventToken(eventName), null, null, null);
                        return;
                    }
                    return;
                case PAY_RES:
                    SdkPayResultEvent sdkPayResultEvent = (SdkPayResultEvent) sdkEvent;
                    if (StatusCode.PAY_SUCCESS.equals(sdkPayResultEvent.getStatusCode())) {
                        sendAppEventsEvent(AppEventsConfig.ev_ActiveNew, AppEventsConfig.getEventToken(AppEventsConfig.ev_ActiveNew), sdkPayResultEvent.getBtOrderId(), sdkPayResultEvent.getPaymentInfo().getAmount(), sdkPayResultEvent.getPaymentInfo().getCurrencyCode());
                        return;
                    }
                    return;
                case SHARE_RES:
                    ShareResultEvent shareResultEvent = (ShareResultEvent) sdkEvent;
                    if (StatusCode.SHARE_SUCCESS.equals(shareResultEvent.getStatusCode()) || StatusCode.SHARE_CANCEL.equals(shareResultEvent.getStatusCode()) || StatusCode.SHARE_FAIL.equals(shareResultEvent.getStatusCode())) {
                    }
                    return;
                default:
                    return;
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateRoleInfoEvent(UpdateRoleInfoEvent updateRoleInfoEvent) {
        if (AppEventsConfig.isLogEvent) {
            this.mRoleInfo = updateRoleInfoEvent.getRoleInfo();
            if (this.mRoleInfo == null) {
                this.userId = null;
                this.roleId = null;
                return;
            }
            this.userId = this.mRoleInfo.getUserId();
            this.roleId = this.mRoleInfo.getRoleId();
            String eventName = AppEventsConfig.buildLvEventName(updateRoleInfoEvent.getRoleInfo().getRoleLevel());
            if (AppEventsConfig.ev_events_tokens.containsKey(eventName)) {
                sendAppEventsEvent(eventName, AppEventsConfig.getEventToken(eventName), null, null, null);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAppEventsEvent(AppEventsEvent appEventsEvent) {
        Context context = appEventsEvent.getContext();
        String platforms = appEventsEvent.getPlatforms();
        String channelId = appEventsEvent.getChannelId();
        String userId2 = appEventsEvent.getUserId();
        String roleId2 = appEventsEvent.getRoleId();
        String eventName = appEventsEvent.getEventName();
        String eventToken = appEventsEvent.getEventToken();
        String orderId = appEventsEvent.getOrderId();
        String amount = appEventsEvent.getAmount();
        String currencyCode = appEventsEvent.getCurrencyCode();
        Map<String, String> extendParams = appEventsEvent.getExtendParams();
        if (platforms.contains("Adjust")) {
            if (AppEventsConfig.ev_activateApp.equals(eventName)) {
                AdjustImpl.getInstance().setDefaultTracker(BTResourceUtil.findStringByName(context, "tracker_default"));
                AdjustImpl.getInstance().setIMEI(BtUtils.getIMEI(context, BtFileUtil.getGameDir(context)));
                AdjustImpl.getInstance().onApplicationCreate(context, eventToken, false);
            } else if (!TextUtils.isEmpty(orderId)) {
                try {
                    AdjustImpl.getInstance().onActiveNew(Integer.parseInt(channelId), eventToken, orderId, Float.parseFloat(amount), currencyCode);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } else if (extendParams == null || extendParams.isEmpty()) {
                try {
                    AdjustImpl.getInstance().onEventAction(Integer.parseInt(channelId), eventToken);
                } catch (NumberFormatException e2) {
                    e2.printStackTrace();
                }
            } else {
                AdjustEvent event = new AdjustEvent(eventToken);
                StringBuilder stringBuilder = new StringBuilder();
                for (Entry<String, String> extendParamEntry : extendParams.entrySet()) {
                    stringBuilder.append((String) extendParamEntry.getKey()).append(":").append((String) extendParamEntry.getValue());
                    event.addPartnerParameter((String) extendParamEntry.getKey(), (String) extendParamEntry.getValue());
                }
                Adjust.trackEvent(event);
                BtsdkLog.m1429d(stringBuilder.toString());
            }
        }
    }

    public void sendAppEventsEvent(String eventName, String eventToken, String orderId, String amount, String currencyCode) {
        BtsdkLog.m1429d("sendAppEventsEvent->eventName:" + eventName + ", eventToken:" + eventToken + ", orderId:" + orderId + ", amount:" + amount + ", currencyCode:" + currencyCode);
        SdkEventManager.postEvent(new Builder().context(ContextUtil.getApplicationContext()).platforms("FB,Adjust").channelId(AppEventsConfig.btplatformId + "").userId(this.userId).roleId(this.roleId).eventName(eventName).eventToken(eventToken).orderId(orderId).amount(amount).currencyCode(currencyCode).build());
    }

    public void trackEvent(String eventName, String eventToken, Map<String, String> extendParams) {
        BtsdkLog.m1429d("eventName:" + eventName + ", eventToken:" + eventToken);
        SdkEventManager.postEvent(new Builder().context(ContextUtil.getApplicationContext()).platforms("FB,Adjust").channelId(AppEventsConfig.btplatformId + "").userId(this.userId).roleId(this.roleId).eventName(eventName).eventToken(eventToken).extendParams(extendParams).build());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onThirdAccountEvent(ThirdAccountEvent loginEvent) {
        if (loginEvent.getPlatform().equals(TaskConfig.LOGIN_GUEST_FLAG)) {
            String token = AppEventsConfig.getEventToken(AppEventsConfig.ev_guestLogin);
            if (TextUtils.isEmpty(token)) {
                BtsdkLog.m1430e("ev_guest_login 为空");
            } else {
                trackEvent(AppEventsConfig.ev_guestLogin, token, null);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onInitEvent(ReqInitEvent reqInitEvent) {
        String token = AppEventsConfig.getEventToken(AppEventsConfig.ev_init_ub_sdk);
        if (TextUtils.isEmpty(token)) {
            BtsdkLog.m1430e("ev_init_ub_sdk 为空");
        } else {
            trackEvent(AppEventsConfig.ev_init_ub_sdk, token, null);
        }
    }
}
