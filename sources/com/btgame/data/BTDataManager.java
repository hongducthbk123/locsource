package com.btgame.data;

import android.support.p000v4.util.ArrayMap;
import android.text.TextUtils;
import com.btgame.data.business.UbSaTrackManager;
import com.btgame.seasdk.btcore.common.constant.StatusCode;
import com.btgame.seasdk.btcore.common.event.SdkEventManager;
import com.btgame.seasdk.btcore.common.event.data.TrackEvent;
import com.btgame.seasdk.btcore.common.event.login.ThirdAccountEvent;
import com.btgame.seasdk.btcore.common.event.login.ThirdAccountResultEvent;
import com.btgame.seasdk.btcore.common.util.BtsdkLog;
import com.btgame.seasdk.btcore.common.util.ContextUtil;
import com.btgame.seasdk.btcore.common.util.SharedPreferencesUtils;
import com.btgame.seasdk.btcore.common.util.http.OkHttpUtil;
import com.btgame.seasdk.task.constant.TaskConfig;
import com.btgame.seasdk.task.entity.response.LoginResult;
import java.util.HashMap;
import java.util.Map;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BTDataManager {
    private static BTDataManager dataManager;
    private String waitingBindFbId;
    private String waitingBindVisitorAccount;
    private String waitingBindVisitorId;
    private String waitingBindVisitorIdentity;

    private BTDataManager() {
        UbSaTrackManager.getInstance(ContextUtil.getApplicationContext());
        SdkEventManager.register(this);
    }

    public static BTDataManager getInstance() {
        if (dataManager == null) {
            dataManager = new BTDataManager();
        }
        return dataManager;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTrackEvent(TrackEvent trackEvent) {
        Map<String, String> extMap = null;
        try {
            extMap = trackEvent.properties;
        } catch (Exception e) {
            BtsdkLog.m1430e(e.getMessage());
        }
        if (TextUtils.isEmpty(trackEvent.code) && !TextUtils.isEmpty(trackEvent.description)) {
            if (extMap == null) {
                extMap = new HashMap<>();
            }
            extMap.put("code", trackEvent.code);
            extMap.put("description", trackEvent.description);
        }
        UbSaTrackManager.sensorsTrack(trackEvent.getEventType().eventId, (Map) extMap, true);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onThirdAccountEvent(ThirdAccountEvent loginEvent) {
        if (loginEvent.getPlatform().equalsIgnoreCase("FB")) {
            switch (loginEvent.getEventType()) {
                case LOGIN_REQ:
                    if (!TextUtils.isEmpty(loginEvent.getBindId())) {
                        String guestAccount = SharedPreferencesUtils.getString(TaskConfig.LOGIN_SP_GUEST_ACCOUNT);
                        String identity = SharedPreferencesUtils.getString(TaskConfig.LOGIN_SP_GUEST_IDENTITY);
                        String visitorUserId = ((LoginResult) OkHttpUtil.getInstance(ContextUtil.getApplicationContext()).getGson().fromJson(SharedPreferencesUtils.getString("LOGIN_SP_RESULT"), LoginResult.class)).getUserId();
                        BtsdkLog.m1429d("visitorUserId: " + visitorUserId + " visitorIdentity:" + identity + " visitorAccount:" + guestAccount);
                        Map<String, String> extMap = new ArrayMap<>();
                        extMap.put("visitorUserId", visitorUserId);
                        extMap.put("visitorIdentity", identity);
                        extMap.put("visitorAccount", guestAccount);
                        UbSaTrackManager.sensorsTrack("500289", (Map) extMap, true);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onThirdLoginResultEvent(ThirdAccountResultEvent thirdAccountResultEvent) {
        switch (thirdAccountResultEvent.getStatusCode()) {
            case LOGIN_SUCCESS:
                if (thirdAccountResultEvent.getPlatform().equalsIgnoreCase("FB") && !TextUtils.isEmpty(thirdAccountResultEvent.getBindId())) {
                    this.waitingBindFbId = thirdAccountResultEvent.getPlatformUid();
                    this.waitingBindVisitorIdentity = thirdAccountResultEvent.getBindId();
                    LoginResult loginResult = (LoginResult) OkHttpUtil.getInstance(ContextUtil.getApplicationContext()).getGson().fromJson(SharedPreferencesUtils.getString("LOGIN_SP_RESULT"), LoginResult.class);
                    this.waitingBindVisitorId = loginResult.getUserId();
                    this.waitingBindVisitorAccount = loginResult.getAccount();
                    BtsdkLog.m1429d("visitorUserId: " + this.waitingBindVisitorId + " visitorIdentity:" + this.waitingBindVisitorIdentity + " visitorAccount:" + this.waitingBindVisitorAccount + " facebookUserId:" + thirdAccountResultEvent.getPlatformUid());
                    Map<String, String> extMap = new ArrayMap<>();
                    extMap.put("visitorUserId", this.waitingBindVisitorId);
                    extMap.put("visitorIdentity", this.waitingBindVisitorIdentity);
                    extMap.put("visitorAccount", this.waitingBindVisitorAccount);
                    extMap.put("facebookUserId", thirdAccountResultEvent.getPlatformUid());
                    UbSaTrackManager.sensorsTrack("500290", (Map) extMap, true);
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginResultEvent(LoginResult loginResult) {
        if (!TextUtils.isEmpty(this.waitingBindFbId) && loginResult.getPlatform().equalsIgnoreCase("FB")) {
            int code = loginResult.getRes().getCode();
            if (StatusCode.LOGIN_SUCCESS.getCode() != code) {
                Map<String, String> extMap = new ArrayMap<>();
                extMap.put("visitorUserId", this.waitingBindVisitorId);
                extMap.put("visitorIdentity", this.waitingBindVisitorIdentity);
                extMap.put("visitorAccount", this.waitingBindVisitorAccount);
                extMap.put("failCode", code + "");
                extMap.put("failMsg", loginResult.getRes().getMsg());
                extMap.put("facebookUserId", this.waitingBindFbId);
                UbSaTrackManager.sensorsTrack("500292", (Map) extMap, true);
                BtsdkLog.m1429d("visitorUserId: " + this.waitingBindVisitorId + " visitorIdentity:" + this.waitingBindVisitorIdentity + " visitorAccount:" + this.waitingBindVisitorAccount + " facebookUserId:" + this.waitingBindFbId);
            } else if (!this.waitingBindFbId.equals(loginResult.getThirdUserId())) {
                this.waitingBindVisitorAccount = null;
                this.waitingBindVisitorIdentity = null;
                this.waitingBindVisitorId = null;
                this.waitingBindFbId = null;
                return;
            } else {
                Map<String, String> extMap2 = new ArrayMap<>();
                extMap2.put("visitorUserId", this.waitingBindVisitorId);
                extMap2.put("visitorIdentity", this.waitingBindVisitorIdentity);
                extMap2.put("visitorAccount", this.waitingBindVisitorAccount);
                extMap2.put("bindUserId", loginResult.getUserId());
                extMap2.put("bindAccount", loginResult.getAccount());
                extMap2.put("facebookUserId", loginResult.getThirdUserId());
                UbSaTrackManager.sensorsTrack("500291", (Map) extMap2, true);
                BtsdkLog.m1429d("visitorUserId: " + this.waitingBindVisitorId + " visitorIdentity:" + this.waitingBindVisitorIdentity + " visitorAccount:" + this.waitingBindVisitorAccount + " facebookUserId:" + loginResult.getThirdUserId());
            }
            this.waitingBindVisitorAccount = null;
            this.waitingBindVisitorIdentity = null;
            this.waitingBindVisitorId = null;
            this.waitingBindFbId = null;
        }
    }
}
